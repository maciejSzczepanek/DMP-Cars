package com.sda.dmpcars.service;

import com.sda.dmpcars.dao.RentDao;
import com.sda.dmpcars.dto.AccountDto;
import com.sda.dmpcars.dto.CarDto;
import com.sda.dmpcars.dto.RentDto;
import com.sda.dmpcars.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class RentServiceTest {

    @Mock
    RentDao rentDao;

    private RentService rentService;

    @Before
    public void setUp() {
        initMocks(this);
        rentService = new RentService(rentDao);
    }

    @Test
    public void shouldAddRentHappyPath() {
        RentDto expected = getDefaultRentDto();

        Rent rentReturnedFromDb = getDefaultRent(1);

        Mockito.when(rentDao.save(Mockito.any(Rent.class))).thenReturn(rentReturnedFromDb);

        RentDto actual = rentService.addRent(expected);

        Assert.assertEquals(expected, actual);
        Mockito.verify(rentDao, Mockito.times(1)).save(Mockito.any(Rent.class));
    }

    @Test
    public void shouldGetRentHappyPath() {
        RentDto excpected = getDefaultRentDto();

        Rent rentReturnedFromDb = getDefaultRent(1);

        Mockito.when(rentDao.findById(1)).thenReturn(Optional.of(rentReturnedFromDb));

        RentDto actual = rentService.getRentById(1);

        Assert.assertEquals(excpected, actual);
        Mockito.verify(rentDao, Mockito.times(1)).findById(1);
    }

    @Test
    public void shouldUpdateRentHappyPath() {
        RentDto expected = getDefaultRentDto();
        Rent returnedFromDb = getDefaultRent(1);

        Mockito.when(rentDao.save(Mockito.any(Rent.class))).thenReturn(returnedFromDb);

        RentDto actual = rentService.updateRent(expected);

        Assert.assertEquals(expected, actual);

        Mockito.verify(rentDao, Mockito.times(1)).save(Mockito.any(Rent.class));
    }

    @Test
    public void shouldDeleteRentHappyPath() {
        RentDto rentToDelete = getDefaultRentDto();
        boolean expected = true;

        Mockito.when(rentDao.existsById(1)).thenReturn(true);

        boolean actual = rentService.deleteRentById(rentToDelete);

        Assert.assertEquals(expected, actual);
        Mockito.verify(rentDao, Mockito.times(1)).existsById(1);
    }

    @Test
    public void shouldGetAllRentsByCarHappyPath() {
        Set<RentDto> expected = new HashSet<>();
        expected.add(getDefaultRentDto());

        Set<Rent> rentsReturnedFromDb = new HashSet<>();
        rentsReturnedFromDb.add(Rent
                .builder()
                .id(1)
                .car(Car
                        .builder()
                        .build())
                .build()
        );

        Mockito.when(rentDao.findRentsByCarId(1)).thenReturn(rentsReturnedFromDb);

        Set<RentDto> actual = rentService.getRentsByCarId(1);
        Assert.assertEquals(expected, actual);

        Mockito.verify(rentDao, Mockito.times(1)).findRentsByCarId(1);
    }

    @Test
    public void shouldGetAllRentsByAccountHappyPath() {
        Set<RentDto> expected = new HashSet<>();
        expected.add(getDefaultRentDto());

        Set<Rent> rentsReturnedFromDb = new HashSet<>();
        rentsReturnedFromDb.add(Rent
                .builder()
                .id(1)
                .account(Account
                        .builder()
                        .build())
                .build()
        );

        Mockito.when(rentDao.findRentsByAccountId(1)).thenReturn(rentsReturnedFromDb);

        Set<RentDto> actual = rentService.getRentsByAccountId(1);
        Assert.assertEquals(expected, actual);

        Mockito.verify(rentDao, Mockito.times(1)).findRentsByAccountId(1);
    }

    @Test
    public void shouldReturnEmptyRentDtoWhenSetSizeIsZero() {
        Set<RentDto> expected = new HashSet<>();
        Set<RentDto> actual = rentService.getRentsByCarId(1);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptyRentDtoWhenIdNumberIsWrong() {
        RentDto expected = new RentDto();
        RentDto actual = rentService.getRentById(1);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptyRentDtoWhenTotalPriceIsNull() {
        RentDto expected = new RentDto();

        RentDto toSave = getDefaultRentDto();
        toSave.setTotalPrice(null);

        RentDto actual = rentService.addRent(toSave);

        Assert.assertEquals(expected, actual);
    }

    private RentDto getDefaultRentDto() {
        return RentDto
                .builder()
                .id(1)
                .fromDate(Date.valueOf("2000-1-1"))
                .toDate(Date.valueOf("2000-2-2"))
                .totalPrice(BigDecimal.valueOf(10))
                .carDto(CarDto
                        .builder()
                        .brand("brand")
                        .model("model")
                        .build())
                .accountDto(AccountDto
                        .builder()
                        .role("role")
                        .build())
                .build();
    }

    private Rent getDefaultRent(Integer id) {
        Rent rent = new Rent();
        rent.setId(id);
        rent.setFromDate(Date.valueOf("2000-1-1"));
        rent.setToDate(Date.valueOf("2000-2-2"));
        rent.setTotalPrice(BigDecimal.valueOf(10));

        return rent;
    }

    private Rent getAllDefaultRents() {
        return Rent
                .builder()
                .id(1)
                .fromDate(Date.valueOf("2000-1-1"))
                .toDate(Date.valueOf("2000-2-2"))
                .totalPrice(BigDecimal.valueOf(10))
                .car(Car
                        .builder()
                        .id(1)
                        .build())
                .account(Account
                        .builder()
                        .id(1)
                        .build())
                .build();
    }

}