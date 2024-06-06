package com.example.usermanager.mapper;

import com.example.usermanager.model.UserAccount;
import com.example.usermanager.model.dto.UserAccountDto;

import java.util.List;

public interface UserAccountMapper {
    UserAccount mapUserAccountDtoToUser(UserAccountDto userAccountDto);

    UserAccountDto mapUserAccountToUserAccountDto(UserAccount userAccount);

    List<UserAccountDto> userListToUserDtoList(List<UserAccount> userAccountList);
}
