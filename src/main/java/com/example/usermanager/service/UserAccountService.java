package com.example.usermanager.service;

import com.example.usermanager.model.Gender;
import com.example.usermanager.model.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserAccountService {
   List<UserAccount> getAllUserAccounts();

   UserAccount findUserAccountById(Long id);

   UserAccount saveUserAccount(UserAccount user);

   UserAccount updateUserAccount(Long id, UserAccount userAccount);

   void deleteUserAccount(Long id);

   UserAccount updateUserAccountWithDetails(Long id, Gender gender, Integer age);

   boolean isUniqueUsername(String username);

   boolean isUniqueEmail(String email);

   Page<UserAccount> getUserAccountsPage(Pageable pageable);
}
