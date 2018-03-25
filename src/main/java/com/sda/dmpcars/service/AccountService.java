package com.sda.dmpcars.service;

import com.sda.dmpcars.dao.AccountDao;
import com.sda.dmpcars.dto.AccountDto;
import com.sda.dmpcars.model.Account;
import com.sda.dmpcars.model.AccountDetail;
import com.sda.dmpcars.model.AccountType;
import com.sda.dmpcars.validator.AccountDtoValidator;
import com.sda.dmpcars.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("accountService")
public class AccountService {

    private final AccountDao accountDao;
    private final Validator<AccountDto> validator = new AccountDtoValidator();

    @Autowired
    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public boolean addAccount(AccountDto accountDto) {
        if (validator.validate(accountDto)) {
            Account account = Account.builder().id(accountDto.getId()).username(accountDto.getLogin())
                    .password(accountDto.getPassword()).accountDetail(
                            AccountDetail.builder().firstName(accountDto.getFirstName())
                                    .lastName(accountDto.getLastName()).email(accountDto.getEmail())
                                    .phoneNumber(accountDto.getPhoneNumber()).yearOfBirth(accountDto.getYearOfBirth())
                                    .build()
                    ).accountType(
                            AccountType.builder().role("USER").username(accountDto.getLogin()).build()
                    ).build();
            accountDao.save(account);
            return true;
        }
        return false;
    }

    public AccountDto getAccountById(Integer id) {

        Account account = accountDao.findById(id).orElse(null);
        if (account != null) {
            return AccountDto.builder().id(account.getId()).login(account.getUsername())
                    .password(account.getPassword()).firstName(account.getAccountDetail().getFirstName())
                    .lastName(account.getAccountDetail().getLastName()).email(account.getAccountDetail().getEmail())
                    .phoneNumber(account.getAccountDetail().getPhoneNumber())
                    .yearOfBirth(account.getAccountDetail().getYearOfBirth())
                    .build();
        }

        return new AccountDto();
    }

    public List<AccountDto> getAllAccounts() {
        List<AccountDto> result = new ArrayList<>();

        accountDao.findAll().forEach(account -> result.add(AccountDto.builder().id(account.getId()).login(account.getUsername())
                .password(account.getPassword()).firstName(account.getAccountDetail().getFirstName())
                .lastName(account.getAccountDetail().getLastName()).email(account.getAccountDetail().getEmail())
                .phoneNumber(account.getAccountDetail().getPhoneNumber())
                .yearOfBirth(account.getAccountDetail().getYearOfBirth())
                .build()));

        return result;
    }

    public boolean deleteAccount(AccountDto accountDto) {
        if (validator.validate(accountDto)) {
            Account account = Account.builder().id(accountDto.getId()).username(accountDto.getLogin()).build();
            accountDao.delete(account);
            return true;
        }
        return false;
    }

    public void deleteAllAccounts() {
        accountDao.deleteAll();
    }

    public AccountDto updateAccount(AccountDto accountDto) {
        if (validator.validate(accountDto)) {
            Account account = Account.builder().id(accountDto.getId()).username(accountDto.getLogin())
                    .password(accountDto.getPassword()).accountDetail(
                            AccountDetail.builder().firstName(accountDto.getFirstName())
                                    .lastName(accountDto.getLastName()).email(accountDto.getEmail())
                                    .phoneNumber(accountDto.getPhoneNumber()).yearOfBirth(accountDto.getYearOfBirth())
                                    .build()).build();

            account = accountDao.save(account);

            return AccountDto.builder().id(account.getId()).login(account.getUsername())
                    .password(account.getPassword()).firstName(account.getAccountDetail().getFirstName())
                    .lastName(account.getAccountDetail().getLastName()).email(account.getAccountDetail().getEmail())
                    .phoneNumber(account.getAccountDetail().getPhoneNumber())
                    .yearOfBirth(account.getAccountDetail().getYearOfBirth())
                    .build();
        }
        return new AccountDto();
    }

    public Account getRawAccount(Integer id) {
        Account account = accountDao.findById(id).orElse(null);
        if (account != null)
            return account;

        return new Account();
    }
}

