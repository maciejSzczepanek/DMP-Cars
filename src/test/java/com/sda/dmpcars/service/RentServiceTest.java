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
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.Date;

import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
public class RentServiceTest {

    @Mock
    RentDao rentDao;

    private RentService rentService;
    private CarService carService;
    private AccountService accountService;

    @Before
    public void setUp() {
        initMocks(this);
        rentService = new RentService(rentDao, carService, accountService);
    }

    @Test
    public void shouldAddRentHappyPath() {
        RentDto expected = getDefaultRentDto();

        Rent rentReturnedFromDb = getDefaultRent(1);

        Mockito.when(rentDao.save(Mockito.any(Rent.class))).thenReturn(rentReturnedFromDb);

        RentDto actual = rentService.addRent(expected);

        Assert.assertEquals(expected, actual);
        Mockito.verify(rentDao, Mockito.timeout(1)).save(Mockito.any(Rent.class));
    }

    @Test
    public void shouldReturnEmptyRentDtoWhenToDateIsNull() {
        RentDto expected = new RentDto();

        RentDto toSave = getDefaultRentDto();
        toSave.setToDate(null);

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
        rent.setCar(Car
                .builder()
                .brand(Brand
                        .builder()
                        .name("brand")
                        .build())
                .model("model")
                .build());
        rent.setAccount(Account
                .builder()
                .accountType(AccountType
                        .builder()
                        .role("role")
                        .build())
                .build());

        return rent;
    }

    @Test
    public void getRentsByCarId() {
    }

    @Test
    public void getRentsByAccountId() {
    }

    @Test
    public void getRentById() {
    }

    @Test
    public void addRent() {
    }

    @Test
    public void updateRent() {
    }

    @Test
    public void deleteRent() {
    }

    @Test
    public void deleteAllRents() {
    }
}