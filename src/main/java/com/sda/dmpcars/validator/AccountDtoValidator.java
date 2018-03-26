package com.sda.dmpcars.validator;

import com.sda.dmpcars.dto.AccountDto;

public class AccountDtoValidator implements Validator<AccountDto>{
    @Override
    public boolean validate(AccountDto accountDto) {
        if (accountDto == null)
            return false;
        if(accountDto.getUsername() == null)
            return false;
        return !accountDto.getUsername().isEmpty() && !accountDto.getPassword().isEmpty()
                && !accountDto.getFirstName().isEmpty() && !accountDto.getLastName().isEmpty() &&
                !accountDto.getEmail().isEmpty() && !accountDto.getPhoneNumber().isEmpty();
    }
}
