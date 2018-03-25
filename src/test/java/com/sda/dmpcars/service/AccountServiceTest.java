/*
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
        AccountDto accountDto = AccountDto.builder().id(1).login("login").password("password").firstName("first")
                .lastName("last").phoneNumber("123").email("asd").yearOfBirth("123").build();

        Account account = Account.builder().id(accountDto.getId()).login(accountDto.getLogin())
                .password(accountDto.getPassword()).accountDetail(AccountDetail.builder()
                        .firstName(accountDto.getFirstName()).lastName(accountDto.getLastName())
                        .email(accountDto.getEmail()).phoneNumber(accountDto.getPhoneNumber())
                        .yearOfBirth(accountDto.getYearOfBirth()).build()).accountType(AccountType.builder()
                        .role("USER").build()).build();

        boolean expected = true;

        //when
        Mockito.when(accountDao.save(account)).thenReturn(account);
        boolean actual = accountService.addAccount(accountDto);

        //then
        assertEquals(expected, actual);
//        Mockito.verify(accountDao, Mockito.times(1)).save(account);

    }
}*/
