package com.sda.dmpcars.service;

import com.sda.dmpcars.dao.RentDao;
import com.sda.dmpcars.dto.AccountDto;
import com.sda.dmpcars.dto.CarDto;
import com.sda.dmpcars.dto.RentDto;
import com.sda.dmpcars.model.Rent;
import lombok.Builder;
import org.hibernate.mapping.Array;
import org.hibernate.mapping.Collection;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class RentServiceTest {

    @Mock
    private RentDao rentDao;

    @InjectMocks
    private RentService rentService;

    @Test
    public void should_fail_to_add_rent() {
        boolean expected = false;
        Rent rent = new Rent();

        Mockito.when(rentDao.save(rent)).thenReturn(rent);
        boolean actual = rentService.addRent(new RentDto());

        assertEquals(expected, actual);
        Mockito.verify(rentDao, Mockito.times(0)).save(rent);
    }

    @Test
    public void should_add_rent() {
        RentDto rentDto
                = RentDto
                .builder()
                .id(1)
                .dateFrom(LocalDate.of(2018, 1, 1))
                .dateTo(LocalDate.of(2018, 1, 15))
                .totalPrice(BigDecimal.valueOf(0))
                .brand("")
                .model("")
                .regNumber("")
                .price(BigDecimal.valueOf(0))
                .login("")
                .email("")
                .phoneNumber("")
                .carDto(new CarDto())
                .accountDto(new AccountDto())
                .build();

        Rent rent
                = Rent
                .builder()
                .build();

        boolean expected = true;

        Mockito.when(rentDao.save(rent)).thenReturn(rent);
        boolean actual = rentService.addRent(rentDto);

        assertEquals(expected, actual);
        Mockito.verify(rentDao, Mockito.times(1)).save(rent);

    }

}