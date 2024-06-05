package com.example.usermanager.service.impl;

import com.example.usermanager.model.Gender;
import com.example.usermanager.model.UserAccount;
import com.example.usermanager.repository.UserAccountRepository;
import com.example.usermanager.service.UserAccountService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    @Override
    public List<UserAccount> getAllUserAccounts() {
        return userAccountRepository.findAll();
    }

    @Override
    public UserAccount findUserAccountById(Long accountId) {
        return userAccountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("User account with ID " + accountId + " not found"));
    }

    @Override
    public UserAccount saveUserAccount(UserAccount newAccount) {
        validateUniqueUsername(newAccount.getUsername());
        validateUniqueEmail(newAccount.getEmail());
        return userAccountRepository.save(newAccount);
    }

    private void validateUniqueUsername(String username) {
        if (!isUniqueUsername(username)) {
            throw new IllegalArgumentException("Username already exists");
        }
    }

    private void validateUniqueEmail(String email) {
        if (!isUniqueEmail(email)) {
            throw new IllegalArgumentException("Email already exists");
        }
    }

    @Override
    public UserAccount updateUserAccount(Long userId, UserAccount userAccount) {
        UserAccount existingUserAccount = userAccountRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " not found"));

        validateUniqueFields(userAccount, existingUserAccount);

        existingUserAccount.setUsername(userAccount.getUsername());
        existingUserAccount.setFirstName(userAccount.getFirstName());
        existingUserAccount.setLastName(userAccount.getLastName());
        existingUserAccount.setPassword(userAccount.getPassword());
        existingUserAccount.setEmail(userAccount.getEmail());
        existingUserAccount.setGender(userAccount.getGender());
        existingUserAccount.setAge(userAccount.getAge());
        existingUserAccount.setAccountCreationDate(userAccount.getAccountCreationDate());

        return userAccountRepository.save(existingUserAccount);
    }

    private void validateUniqueFields(UserAccount updatedAccount, UserAccount existingAccount) {
        if (!existingAccount.getUsername().equals(updatedAccount.getUsername()) && !isUniqueUsername(updatedAccount.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (!existingAccount.getEmail().equals(updatedAccount.getEmail()) && !isUniqueEmail(updatedAccount.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
    }

    @Override
    public boolean isUniqueUsername(String username) {
        return !userAccountRepository.existsByUsername(username);
    }

    @Override
    public boolean isUniqueEmail(String email) {
        return !userAccountRepository.existsByEmail(email);
    }

    @Override
    public UserAccount updateUserAccountWithDetails(Long userId, Gender newGender, Integer newAge) {
        UserAccount account = userAccountRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " not found"));

        if (newGender != null) {
            account.setGender(newGender);
        }
        if (newAge != null) {
            account.setAge(newAge);
        }
        return userAccountRepository.save(account);
    }

    @Override
    public void deleteUserAccount(Long userAccountId) {
        userAccountRepository.deleteById(userAccountId);
    }

    @Override
    public Page<UserAccount> getUserAccountsPage(Pageable pageable) {
        return userAccountRepository.findAll(pageable);
    }
}
