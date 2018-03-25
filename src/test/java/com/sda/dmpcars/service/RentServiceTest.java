/*
package com.sda.dmpcars.service;

import com.sda.dmpcars.dao.RentDao;
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

import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RentServiceTest {

    @Mock
    RentDao rentDao;

    @InjectMocks
    RentService rentService;

    @Test
    public void getAllRentsByCarId() {
        List<Rent> rents = new ArrayList<>();
        Rent r1 = Rent
                .builder()
                .id(1)
                .dateFrom(LocalDate.of(2018, 10, 10))
                .dateTo(LocalDate.of(2018, 10, 20))
                .totalPrice(BigDecimal.valueOf(100))
                .build();
        rents.add(r1);

        Mockito.when(rentDao.findRentByCarId(1)).thenReturn(rents);
        List<Rent> actual = rentService.getAllRentsByCarId(1);
        Assert.assertEquals(1, actual.size());
    }
//
//    @Test
//    public void getAllRentsByAccountId() {
//        List<Rent> rents = new ArrayList<>();
//        Rent r1 = Rent.builder()
//                .id(1)
//                .dateFrom(LocalDate.of(2018, 10, 10))
//                .dateTo(LocalDate.of(2018, 10, 20))
//                .totalPrice(BigDecimal.valueOf(100))
//                .build();
//        rents.add(r1);
//
//        Mockito.when(rentDao.findRentByAccountId(1)).thenReturn(rents);
//        List<Rent> actual = rentService.getAllRentsByAccountId(1);
//        Assert.assertEquals(1, actual.size());
//    }

    @Test
    public void deleteRent() {
    }

    @Test
    public void deleteAllRents() {
    }

    @Test
    public void addRent() {
    }

    @Test
    public void updateRent() {
    }

}*/
