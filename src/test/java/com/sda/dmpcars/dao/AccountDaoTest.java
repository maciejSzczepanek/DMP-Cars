package com.sda.dmpcars.dao;

import com.sda.dmpcars.model.Account;
import com.sda.dmpcars.model.AccountDetail;
import com.sda.dmpcars.model.AccountType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

import static org.junit.Assert.*;

@TestComponent
public class AccountDaoTest {

    @Autowired
    AccountDao accountDao;

    @Autowired
    AccountTypeDao accountTypeDao;

    @Autowired
    AccountDetailDao accountDetailDao;

    @Test
    public void should_test_creation_of_tables(){
        Account account = new Account();
        account.setPassword("password");

        AccountDetail accountDetail = new AccountDetail();

        AccountType accountType = AccountType.builder().role("USER").build();

        accountDetailDao.save(accountDetail);
        accountTypeDao.save(accountType);
        accountDao.save(account);
    }

}