package com.sda.dmpcars.service;

import com.sda.dmpcars.dao.AccountDao;
import com.sda.dmpcars.dto.AccountDto;
import com.sda.dmpcars.model.Account;
import com.sda.dmpcars.model.AccountDetail;
import com.sda.dmpcars.model.AccountType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @Mock
    private AccountDao accountDao;

    private AccountService accountService;

    @Before
    public void setUp() {
        initMocks(this);
        accountService = new AccountService(accountDao);
    }

    @Test
    public void shouldAddAccountHappyPath() {
        AccountDto expected = getDefaultAccountDto();
        Account accountReturnedFromDb = getDefaultAccount(1);

        Mockito.when(accountDao.save(Mockito.any(Account.class))).thenReturn(accountReturnedFromDb);

        AccountDto actual = accountService.addAccount(expected);

        Assert.assertEquals(expected, actual);
        Mockito.verify(accountDao, Mockito.times(1)).save(Mockito.any(Account.class));
    }

    @Test
    public void shouldReturnEmptyCarDtoWhenEmailIsNull() {
        AccountDto expected = new AccountDto();

        AccountDto toSave = getDefaultAccountDto();
        toSave.setId(null);

        AccountDto actual = accountService.addAccount(toSave);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnSetOfCarsHappyPath() {
        Set<AccountDto> expected = new HashSet<>();
        expected.add(getDefaultAccountDto());

        Set<Account> setAccountFromDb = new HashSet<>();
        setAccountFromDb.add(getDefaultAccount(1));

        Mockito.when(accountDao.findAll()).thenReturn(setAccountFromDb);

        Set<AccountDto> actual = accountService.getAllAccounts();

        Assert.assertEquals(expected, actual);
        Mockito.verify(accountDao, Mockito.timeout(1)).findAll();
    }

    @Test
    public void shouldReturnEmptySetAccountDtoWhenSizeOfSetIsZero() {
        Set<AccountDto> expected = new HashSet<>();

        Set<AccountDto> actual = accountService.getAllAccounts();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldGetAccountHappyPath() {
        AccountDto expected = getDefaultAccountDto();

        Account accoountReturnedFromDb = getDefaultAccount(1);

        Mockito.when(accountDao.findById(1)).thenReturn(Optional.of(accoountReturnedFromDb));

        AccountDto actual = accountService.getAccountById(1);

        Assert.assertEquals(expected, actual);
        Mockito.verify(accountDao, Mockito.timeout(1)).findById(1);
    }

    @Test
    public void shouldReturnEmptyAccountDtoWhenIdNumberIsWrong() {
        AccountDto expected = new AccountDto();

        AccountDto actual = accountService.getAccountById(1);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldUpdateCarHappyPath() {
        AccountDto expected = getDefaultAccountDto();

        Account accountReturnedFromDb = getDefaultAccount(1);

        Mockito.when(accountDao.save(Mockito.any(Account.class))).thenReturn(accountReturnedFromDb);

        AccountDto actual = accountService.updateAccount(expected);

        Assert.assertEquals(expected, actual);
        Mockito.verify(accountDao, Mockito.times(1)).save(Mockito.any(Account.class));
    }

    @Test
    public void shouldDeleteAccountHappyPath() {
        AccountDto accountToDelete = getDefaultAccountDto();
        boolean expected = true;

        Mockito.when(accountDao.existsById(1)).thenReturn(true);

        boolean actual = accountService.deleteAccount(accountToDelete);

        Assert.assertEquals(expected, actual);
        Mockito.verify(accountDao, Mockito.times(1)).existsById(1);
    }

    private AccountDto getDefaultAccountDto() {
        return AccountDto
                .builder()
                .id(1)
                .username("username")
                .password("password")
                .role("role")
                .firstName("name")
                .lastName("last")
                .email("email")
                .phoneNumber("number")
                .build();
    }

    private Account getDefaultAccount(Integer id) {
        Account account = new Account();
        account.setId(id);
        account.setUsername("username");
        account.setPassword("password");
        account.setAccountType(AccountType
                .builder()
                .id(1)
                .role("role")
                .build());
        account.setAccountDetail(AccountDetail
                .builder()
                .id(1)
                .firstName("name")
                .lastName("last")
                .phoneNumber("number")
                .email("email")
                .build());

        return account;
    }

}