package com.sda.dmpcars.validator;

import com.sda.dmpcars.dto.AccountDto;

import java.time.LocalDate;

public class AccountDtoValidator implements Validator<AccountDto>{
    @Override
    public boolean validate(AccountDto accountDto) {
        if (accountDto == null)
            return false;
        if(accountDto.getLogin() == null)
            return false;
        return !accountDto.getLogin().isEmpty() && !accountDto.getPassword().isEmpty()
                && !accountDto.getFirstName().isEmpty() && !accountDto.getLastName().isEmpty() &&
                !accountDto.getEmail().isEmpty() && !accountDto.getPhoneNumber().isEmpty();
    }
}
