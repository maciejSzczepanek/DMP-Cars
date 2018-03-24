package com.sda.dmpcars.service;

import com.sda.dmpcars.dao.AccountDao;
import com.sda.dmpcars.dto.AccountDto;
import com.sda.dmpcars.model.Account;
import com.sda.dmpcars.model.AccountDetail;
import com.sda.dmpcars.model.AccountType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;


import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class AccountServiceTest {

    @Mock
    private AccountDao accountDao;

    @InjectMocks
    private AccountService accountService;

    @Test
    public void should_fail_to_add_account(){
        //given
        boolean expected = false;
        Account account = new Account();

        //when
        Mockito.when(accountDao.save(account)).thenReturn(account);
        boolean actual = accountService.addAccount(new AccountDto());

        //then
        assertEquals(expected, actual);
        Mockito.verify(accountDao, Mockito.times(0)).save(account);
    }

    @Test
    public void should_add_account(){
        //given
        AccountDto accountDto = new AccountDto();
        accountDto.setId(1);
        accountDto.setLogin("login");
        accountDto.setFirstName("first");
        accountDto.setLastName("last");
        accountDto.setPassword("password");
        accountDto.setEmail("asd");
        accountDto.setPhoneNumber("123");
        accountDto.setYearOfBirth(LocalDate.now());

        AccountDetail accountDetail = new AccountDetail();
        accountDetail.setFirstName("first");
        accountDetail.setLastName("last");
        accountDetail.setYearOfBirth(LocalDate.now());
        accountDetail.setEmail("asd");
        accountDetail.setPhoneNumber("123");
        AccountType accountType = new AccountType();
        accountType.setRole("USER");
        Account account = new Account();
        account.setId(1);
        account.setLogin("login");
        account.setPassword("password");
        account.setAccountType(accountType);
        account.setAccountDetail(accountDetail);

    }
}