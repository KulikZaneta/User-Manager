package com.example.usermanager.mapper.impl;

import com.example.usermanager.mapper.UserAccountMapper;
import com.example.usermanager.model.UserAccount;
import com.example.usermanager.model.dto.UserAccountDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserAccountMapperImpl implements UserAccountMapper {

    @Override
    public UserAccount mapUserAccountDtoToUser(UserAccountDto dto) {
        return UserAccount.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .age(dto.getAge())
                .gender(dto.getGender())
                .accountCreationDate(dto.getAccountCreationDate())
                .build();
    }

    @Override
    public UserAccountDto mapUserAccountToUserAccountDto(UserAccount userAccount) {
        return UserAccountDto.builder()
                .id(userAccount.getId())
                .username(userAccount.getUsername())
                .password(userAccount.getPassword())
                .firstName(userAccount.getFirstName())
                .lastName(userAccount.getLastName())
                .email(userAccount.getEmail())
                .age(userAccount.getAge())
                .gender(userAccount.getGender())
                .accountCreationDate(userAccount.getAccountCreationDate())
                .build();
    }

    @Override
    public List<UserAccountDto> userListToUserDtoList(List<UserAccount> userAccountList) {
        return userAccountList.stream()
                .map(this::mapUserAccountToUserAccountDto)
                .collect(Collectors.toList());
    }
}

