package com.sda.dmpcars.service;

import com.sda.dmpcars.dao.AccountDao;
import com.sda.dmpcars.dto.AccountDto;
import com.sda.dmpcars.model.Account;
import com.sda.dmpcars.validator.AccountDtoValidator;
import com.sda.dmpcars.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service("accountService")
public class AccountService {

    private final AccountDao accountDao;
    private final Validator<AccountDto> validator = new AccountDtoValidator();

    @Autowired
    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public AccountDto addAccount(AccountDto accountDto) {
        if(validator.validate(accountDto)) {
            Account account = convertToAccount(accountDto);
            account.setAccountType("USER");
            Account result = accountDao.save(account);
            return convertToAccountDto(result);
        }
        return new AccountDto();
    }

    public AccountDto getAccountById(Integer id) {
        Account account = accountDao.findById(id).orElse(null);
        if (account != null){
            return convertToAccountDto(account);
        }
        return new AccountDto();
    }

    public Set<AccountDto> getAllAccounts() {
        Set<AccountDto> result = new HashSet<>();
        accountDao.findAll().forEach(account -> result.add(convertToAccountDto(account)));

        if (result.size() == 0)
            return new HashSet<>();

        return result;
    }

    public AccountDto updateAccount(AccountDto accountDto){
        Account account = convertToAccount(accountDto);
        account.setId(accountDto.getId());
        account.setAccountType(accountDto.getAccountType());

        return convertToAccountDto(accountDao.save(account));
    }

    public void deleteAccount(AccountDto accountDto) {
        if(validator.validate(accountDto)) {
            Account account = convertToAccount(accountDto);
            account.setId(accountDto.getId());
            accountDao.delete(account);
        }
    }

    public void deleteAllAccounts() {
        accountDao.deleteAll();
    }


    public Account getRawAccount(Integer id) {
        Account account = accountDao.findById(id).orElse(null);
        if(account != null){
            return account;
        }
        return new Account();
    }

    private Account convertToAccount(AccountDto accountDto) {
        if(accountDto == null){
            return new Account();
        }
        return Account.builder()
                .username(accountDto.getUsername())
                .password(accountDto.getPassword())
                .firstName(accountDto.getFirstName())
                .lastName(accountDto.getLastName())
                .email(accountDto.getEmail())
                .phoneNumber(accountDto.getPhoneNumber())
                .yearOfBirth(accountDto.getYearOfBirth())
                .accountType(accountDto.getAccountType()).build();
    }

    AccountDto convertToAccountDto(Account account) {
        if(account == null){
            return new AccountDto();
        }
        return AccountDto.builder()
                .id(account.getId())
                .username(account.getUsername())
                .password(account.getPassword())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .email(account.getEmail())
                .phoneNumber(account.getPhoneNumber())
                .yearOfBirth(account.getYearOfBirth())
                .accountType(account.getAccountType()).build();
    }
}

