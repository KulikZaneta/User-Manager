package com.example.usermanager.service;

import com.example.usermanager.model.Gender;
import com.example.usermanager.model.UserAccount;
import com.example.usermanager.repository.UserAccountRepository;
import com.example.usermanager.service.impl.UserAccountServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserAccountServiceTest {

    @InjectMocks
    private UserAccountServiceImpl userAccountService;

    @Mock
    private UserAccountRepository userAccountRepository;

    @Test
    public void test_Get_All_UserAccounts_With_Empty_List() {
        when(userAccountRepository.findAll()).thenReturn(new ArrayList<>());
        List<UserAccount> userAccounts = userAccountService.getAllUserAccounts();
        assertEquals(0, userAccounts.size());
    }

    @Test
    public void test_Get_All_UserAccounts() {
        // Given
        List<UserAccount> userAccounts = new ArrayList<>();
        userAccounts.add(new UserAccount(1L, "john_doe", "password", "John", "Doe", "john.doe@example.com", Gender.MALE, 25, LocalDateTime.now()));
        userAccounts.add(new UserAccount(2L, "jane_smith", "password", "Jane", "Smith", "jane.smith@example.com", Gender.FEMALE, 30, LocalDateTime.now()));

        when(userAccountRepository.findAll()).thenReturn(userAccounts);

        // When
        List<UserAccount> result = userAccountService.getAllUserAccounts();

        // Then
        assertEquals(2, result.size());
        assertEquals(Long.valueOf(1L), result.get(0).getId());
        assertEquals("john_doe", result.get(0).getUsername());
        assertEquals("password", result.get(0).getPassword());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Doe", result.get(0).getLastName());
        assertEquals(Gender.MALE, result.get(0).getGender());
        assertEquals("john.doe@example.com", result.get(0).getEmail());
        assertEquals(25, result.get(0).getAge());

        assertEquals(Long.valueOf(2L), result.get(1).getId());
        assertEquals("jane_smith", result.get(1).getUsername());
        assertEquals("password", result.get(1).getPassword());
        assertEquals("Jane", result.get(1).getFirstName());
        assertEquals("Smith", result.get(1).getLastName());
        assertEquals(Gender.FEMALE, result.get(1).getGender());
        assertEquals("jane.smith@example.com", result.get(1).getEmail());
        assertEquals(30, result.get(1).getAge());
    }

    @Test
    public void test_Get_UserAccount_By_Id() {
        // Given
        Long id = 1L;
        UserAccount userAccount = new UserAccount(id, "username", "password", "firstName", "lastName", "email", Gender.MALE, 25, LocalDateTime.now());
        when(userAccountRepository.findById(id)).thenReturn(Optional.of(userAccount));

        // When
        UserAccount result = userAccountService.findUserAccountById(id);

        // Then
        assertEquals(userAccount, result);
    }

    @Test
    public void test_Save_UserAccount_Non_UniqueUsername() {
        // Given
        UserAccount userAccount = new UserAccount(null, "existing_username", "password", "firstName", "lastName", "email", Gender.MALE, 25, LocalDateTime.now());

        //when
        when(userAccountRepository.existsByUsername("existing_username")).thenReturn(true);

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            userAccountService.saveUserAccount(userAccount);
        });
    }

    @Test
    public void test_Delete_UserAccount_By_Id() {
        // Given
        Long id = 1L;
        UserAccount userAccount = new UserAccount(id, "username", "password", "firstName", "lastName", "email", Gender.MALE, 25, LocalDateTime.now());

        // When
        userAccountService.deleteUserAccount(id);

        // Then
        verify(userAccountRepository, times(1)).deleteById(id);
    }

    @Test
    public void test_Get_UserAccount_Page() {
        Pageable pageable = PageRequest.of(0, 10);
        List<UserAccount> userAccounts = new ArrayList<>();
        userAccounts.add(new UserAccount(1L, "username1", "password", "firstName", "lastName", "email", Gender.MALE, 25, LocalDateTime.now()));
        userAccounts.add(new UserAccount(2L, "username2", "password", "firstName", "lastName", "email", Gender.MALE, 25, LocalDateTime.now()));
        when(userAccountRepository.findAll(pageable)).thenReturn(new PageImpl<>(userAccounts, pageable, userAccounts.size()));

        // When
        Page<UserAccount> result = userAccountService.getUserAccountsPage(pageable);

        // Then
        assertEquals(2, result.getTotalElements());
        assertEquals(userAccounts, result.getContent());

    }
}

