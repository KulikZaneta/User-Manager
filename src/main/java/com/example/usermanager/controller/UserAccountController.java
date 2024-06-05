package com.example.usermanager.controller;

import com.example.usermanager.mapper.UserAccountMapper;
import com.example.usermanager.model.Gender;
import com.example.usermanager.model.UserAccount;
import com.example.usermanager.model.dto.UserAccountDto;
import com.example.usermanager.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/userManager")
@RequiredArgsConstructor
public class UserAccountController {

    private final UserAccountService userAccountService;
    private final UserAccountMapper userAccountMapper;

    @GetMapping("/users")
    public List<UserAccountDto> getAllUsersAccount() {
        return userAccountService.getAllUserAccounts().stream().map(userAccountMapper::mapUserAccountToUserAccountDto).collect(Collectors.toList());
    }

    @GetMapping("/users/{id}")
    public UserAccountDto getUserAccountById(@PathVariable Long id) {
        UserAccount userAccount = userAccountService.findUserAccountById(id);
        return userAccountMapper.mapUserAccountToUserAccountDto(userAccount);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserAccountDto addUserAccount(@RequestBody UserAccountDto userAccountDto) {
        UserAccount newUserAccount = userAccountMapper.mapUserAccountDtoToUser(userAccountDto);
        UserAccount createdUserAccount = userAccountService.saveUserAccount(newUserAccount);
        return userAccountMapper.mapUserAccountToUserAccountDto(createdUserAccount);
    }

    @PutMapping("/users/{id}")
    public UserAccountDto updateUserAccount(@PathVariable Long id, @RequestBody UserAccountDto userAccountDto) {
        userAccountDto.setId(id);

        UserAccount updatedUserAccount = userAccountMapper.mapUserAccountDtoToUser(userAccountDto);
        UserAccount updatedUser = userAccountService.updateUserAccount(id, updatedUserAccount);

        return userAccountMapper.mapUserAccountToUserAccountDto(updatedUser);
    }

    @PatchMapping("/users/{id}")
    public UserAccountDto updateUserAccountWithDetails(@PathVariable Long id, @RequestParam(required = false) Gender newGender, @RequestParam(required = false) Integer newAge) {
        UserAccount updatedUserAccount = userAccountService.updateUserAccountWithDetails(id, newGender, newAge);
        return userAccountMapper.mapUserAccountToUserAccountDto(updatedUserAccount);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserAccountById(@PathVariable Long id) {
        userAccountService.deleteUserAccount(id);
    }

    @GetMapping("/users/page/sortedByUsername")
    public Page<UserAccountDto> getUserAccountPageSortedByUsername(@RequestParam int page, @RequestParam int size, @RequestParam(required = false, defaultValue = "username") String sortBy, @RequestParam(required = false, defaultValue = "ASC") String sortOrder) {
        Sort.Direction direction = Sort.Direction.fromOptionalString(sortOrder).orElse(Sort.Direction.ASC);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return userAccountService.getUserAccountsPage(pageable).map(userAccountMapper::mapUserAccountToUserAccountDto);
    }

    @GetMapping("/users/page/sortedByCreationDate")
    public Page<UserAccountDto> getUserAccountPageSortedByCreationDate(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "accountCreationDate"));
        return userAccountService.getUserAccountsPage(pageable).map(userAccountMapper::mapUserAccountToUserAccountDto);
    }

    @GetMapping("/users/page/sortedByAge")
    public Page<UserAccountDto> getUserAccountPageSortedByAge(@RequestParam int page, @RequestParam int size) {
        Pageable pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "age"));
        return userAccountService.getUserAccountsPage(pageRequest).map(userAccountMapper::mapUserAccountToUserAccountDto);
    }

    @GetMapping("/users/page/sortedByGender")
    public Page<UserAccountDto> getUserAccountPageSortedByGender(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC,"gender"));
        return userAccountService.getUserAccountsPage(pageable).map(userAccountMapper::mapUserAccountToUserAccountDto);
    }
}
