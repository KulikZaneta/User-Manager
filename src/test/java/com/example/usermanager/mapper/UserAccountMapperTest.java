package com.example.usermanager.mapper;

import com.example.usermanager.mapper.impl.UserAccountMapperImpl;
import com.example.usermanager.model.Gender;
import com.example.usermanager.model.UserAccount;
import com.example.usermanager.model.dto.UserAccountDto;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserAccountMapperTest {

    private UserAccountMapperImpl userAccountMapper;

    @Before
    public void setUp() {
        userAccountMapper = new UserAccountMapperImpl();
    }

    @Test
    public void test_User_Dto_To_User() {
        // Given
        UserAccountDto userDto = new UserAccountDto();
        userDto.setId(1L);
        userDto.setUsername("test_user");
        userDto.setPassword("password");
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setEmail("john.doe@example.com");
        userDto.setAge(30);
        userDto.setGender(Gender.MALE);

        // When
        UserAccount user = userAccountMapper.mapUserAccountDtoToUser(userDto);

        // Then
        assertNotNull(user);
        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getUsername(), user.getUsername());
        assertEquals(userDto.getPassword(), user.getPassword());
        assertEquals(userDto.getFirstName(), user.getFirstName());
        assertEquals(userDto.getLastName(), user.getLastName());
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(userDto.getAge(), user.getAge());
        assertEquals(userDto.getGender(), user.getGender());
    }

    @Test
    public void test_User_To_User_Dto() {
        // Given
        UserAccount user = new UserAccount();
        user.setId(1L);
        user.setUsername("test_user");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setAge(30);
        user.setGender(Gender.MALE);

        // When
        UserAccountDto userDto = userAccountMapper.mapUserAccountToUserAccountDto(user);

        // Then
        assertNotNull(userDto);
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getUsername(), userDto.getUsername());
        assertEquals(user.getPassword(), userDto.getPassword());
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getLastName(), userDto.getLastName());
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getAge(), userDto.getAge());
        assertEquals(user.getGender(), userDto.getGender());
    }

    @Test
    public void test_User_List_To_User_Dto_List() {
        // Given
        List<UserAccount> userAccounts = new ArrayList<>();
        userAccounts.add(new UserAccount(1L, "test_user1", "password", "John", "Doe", "john.doe@example.com", Gender.MALE, 30, LocalDateTime.now()));
        userAccounts.add(new UserAccount(2L, "test_user2", "password", "Jane", "Smith", "jane.smith@example.com", Gender.FEMALE, 25, LocalDateTime.now()));

        // When
        List<UserAccountDto> userDtoList = userAccountMapper.userListToUserDtoList(userAccounts);

        // Then
        assertEquals(userAccounts.size(), userDtoList.size());
        assertEquals(userAccounts.get(0).getId(), userDtoList.get(0).getId());
        assertEquals(userAccounts.get(0).getUsername(), userDtoList.get(0).getUsername());
        assertEquals(userAccounts.get(0).getFirstName(), userDtoList.get(0).getFirstName());
        assertEquals(userAccounts.get(0).getLastName(), userDtoList.get(0).getLastName());
        assertEquals(userAccounts.get(0).getEmail(), userDtoList.get(0).getEmail());
        assertEquals(userAccounts.get(0).getAge(), userDtoList.get(0).getAge());
        assertEquals(userAccounts.get(0).getGender(), userDtoList.get(0).getGender());

        assertEquals(userAccounts.get(1).getId(), userDtoList.get(1).getId());
        assertEquals(userAccounts.get(1).getUsername(), userDtoList.get(1).getUsername());
        assertEquals(userAccounts.get(1).getFirstName(), userDtoList.get(1).getFirstName());
        assertEquals(userAccounts.get(1).getLastName(), userDtoList.get(1).getLastName());
        assertEquals(userAccounts.get(1).getEmail(), userDtoList.get(1).getEmail());
        assertEquals(userAccounts.get(1).getAge(), userDtoList.get(1).getAge());
        assertEquals(userAccounts.get(1).getGender(), userDtoList.get(1).getGender());
    }

    @Test
    public void test_User_Account_Dto_To_User_With_Details() {
        // Given
        UserAccountDto userDto = new UserAccountDto();
        userDto.setId(1L);
        userDto.setAge(30);
        userDto.setGender(Gender.MALE);

        // When
        UserAccount user = userAccountMapper.mapUserDtoToUserWithDetails(userDto);

        // Then
        assertNotNull(user);
        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getAge(), user.getAge());
        assertEquals(userDto.getGender(), user.getGender());
    }

    @Test
    public void test_User_Account_To_User_Dto_With_Details() {
        // Given
        UserAccount user = new UserAccount();
        user.setId(1L);
        user.setAge(30);
        user.setGender(Gender.MALE);

        // When
        UserAccountDto userDto = userAccountMapper.mapUserAccountToUserDtoWithDetails(user);

        // Then
        assertNotNull(userDto);
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getAge(), userDto.getAge());
        assertEquals(user.getGender(), userDto.getGender());
    }
}
