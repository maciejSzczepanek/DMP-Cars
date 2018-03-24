package com.sda.dmpcars.service;

import com.sda.dmpcars.dao.AccountDao;
import com.sda.dmpcars.dto.AccountDto;
import com.sda.dmpcars.model.Account;
import com.sda.dmpcars.model.AccountDetail;
import com.sda.dmpcars.model.AccountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("accountService")
public class AccountService {

    private final AccountDao accountDao;

    @Autowired
    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void addAccount(AccountDto accountDto){

        Account account = Account.builder().id(accountDto.getId()).login(accountDto.getLogin())
                .password(accountDto.getPassword()).accountDetail(
                        AccountDetail.builder().firstName(accountDto.getFirstName())
                                .lastName(accountDto.getLastName()).email(accountDto.getEmail())
                                .phoneNumber(accountDto.getPhoneNumber()).yearOfBirth(accountDto.getYearOfBirth())
                                .build()
                ).accountType(
                        AccountType.builder().role("USER").build()
                ).build();

        accountDao.save(account);
    }

    public AccountDto getAccountById(Integer id){

        Account account = accountDao.findById(id).orElse(null);
        if(account != null){
            return AccountDto.builder().id(account.getId()).login(account.getLogin())
                    .password(account.getPassword()).firstName(account.getAccountDetail().getFirstName())
                    .lastName(account.getAccountDetail().getLastName()).email(account.getAccountDetail().getEmail())
                    .phoneNumber(account.getAccountDetail().getPhoneNumber())
                    .yearOfBirth(account.getAccountDetail().getYearOfBirth())
                    .build();
        }

        return new AccountDto();
    }

    public List<AccountDto> getAllAccounts(){
        List<AccountDto> result = new ArrayList<>();

        accountDao.findAll().forEach(account -> AccountDto.builder().id(account.getId()).login(account.getLogin())
                .password(account.getPassword()).firstName(account.getAccountDetail().getFirstName())
                .lastName(account.getAccountDetail().getLastName()).email(account.getAccountDetail().getEmail())
                .phoneNumber(account.getAccountDetail().getPhoneNumber())
                .yearOfBirth(account.getAccountDetail().getYearOfBirth())
                .build());

        return result;
    }

    public void deleteAccount(AccountDto accountDto){
        Account account = Account.builder().id(accountDto.getId()).login(accountDto.getLogin()).build();
        accountDao.delete(account);
    }

    public void deleteAllAccounts(){
        accountDao.deleteAll();
    }

    public AccountDto updateAccount(AccountDto accountDto){
        Account account = Account.builder().id(accountDto.getId()).login(accountDto.getLogin())
                .password(accountDto.getPassword()).accountDetail(
                        AccountDetail.builder().firstName(accountDto.getFirstName())
                                .lastName(accountDto.getLastName()).email(accountDto.getEmail())
                                .phoneNumber(accountDto.getPhoneNumber()).yearOfBirth(accountDto.getYearOfBirth())
                                .build()).build();

        account = accountDao.save(account);

        return AccountDto.builder().id(account.getId()).login(account.getLogin())
                .password(account.getPassword()).firstName(account.getAccountDetail().getFirstName())
                .lastName(account.getAccountDetail().getLastName()).email(account.getAccountDetail().getEmail())
                .phoneNumber(account.getAccountDetail().getPhoneNumber())
                .yearOfBirth(account.getAccountDetail().getYearOfBirth())
                .build();
    }
}
