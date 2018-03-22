package com.sda.dmpcars.service;

import com.sda.dmpcars.dao.AccountDao;
import com.sda.dmpcars.model.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;


import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @Mock
    private AccountDao accountDao;

    @InjectMocks
    private AccountService accountService;

    @Test
    public void addAccount() {
    }

    @Test
    public void should_getAccountById() {
        //given
        Integer id = 1;
        Account expected = Account.builder().id(1).build();

        //when
        Mockito.when(accountDao.findById(id)).thenReturn(Optional.of(expected));

        //then
        Account actual = accountService.getAccountById(id);

        assertEquals(expected, actual);

    }

    @Test
    public void should_return_blank_account_getAccountById(){
        //given
        Integer id = 2;
        Account expected = Account.builder().id(id).build();

        //when
        Mockito.when(accountDao.findById(id)).thenReturn(Optional.of(new Account()));
        Account actual = accountService.getAccountById(id);

        //then
        assertNotEquals(expected, actual);
    }

    @Test
    public void should_getAllAccounts() {
        //given
        Account test1 = new Account();
        Account test2 = new Account();

        List<Account> expected = new ArrayList<>(Arrays.asList(test1, test2));

        //when
        Mockito.when(accountDao.findAll()).thenReturn(expected);

        List<Account> actual = accountService.getAllAccounts();

        //then
        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void should_loginAccount() {
        //given
        Account expected = Account.builder().login("test").password("test").build();
        String login = "test",
                password = "test";

        //when
        Mockito.when(accountDao.findAccountByLoginAndPassword(login, password)).thenReturn(expected);

        Account actual = accountService.loginAccount(login, password);

        //then
        assertEquals(expected.getLogin(), actual.getLogin());
        assertEquals(expected.getPassword(), actual.getPassword());

    }

    @Test
    public void deleteAccount() {
    }

    @Test
    public void deleteAllAccounts() {
    }

    @Test
    public void updateAccount() {
    }
}