package com.sda.dmpcars.service;

import com.sda.dmpcars.dao.AccountDao;
import com.sda.dmpcars.dao.CarDao;
import com.sda.dmpcars.dao.RentDao;
import com.sda.dmpcars.dto.AccountDto;
import com.sda.dmpcars.dto.CarDto;
import com.sda.dmpcars.dto.RentDto;
import com.sda.dmpcars.model.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "rentService")
public class RentService {

    private final RentDao rentDao;

    @Autowired
    private CarDao carDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private CarService carService;

    @Autowired
    private AccountService accountService;

    private CarDto carDto;
    private AccountDto accountDto;

    @Autowired
    public RentService(RentDao rentDao) {
        this.rentDao = rentDao;
    }

    public List<RentDto> getAllRentsByCarId(Integer carId) {
        List<RentDto> result = new ArrayList<>();

        rentDao
                .findRentByCarId(carId)
                .forEach(rent -> RentDto
                        .builder()
                        .id(rent.getId())
                        .dateFrom(rent.getDateFrom())
                        .dateTo(rent.getDateTo())
                        .totalPrice(rent.getTotalPrice())
                        .brand(rent.getCar().getBrand().getName())
                        .model(rent.getCar().getModel())
                        .regNumber(rent.getCar().getRegNumber().getNumber())
                        .price(rent.getTotalPrice())
                        .login(rent.getAccount().getLogin())
                        .email(rent.getAccount().getAccountDetail().getEmail())
                        .phoneNumber(rent.getAccount().getAccountDetail().getPhoneNumber())
                        .build());

        return result;
    }

    public List<RentDto> getAllRentsByAccountId(Integer accountId) {
        List<RentDto> result = new ArrayList<>();

        rentDao
                .findRentByCarId(accountId)
                .forEach(rent -> RentDto
                        .builder()
                        .id(rent.getId())
                        .dateFrom(rent.getDateFrom())
                        .dateTo(rent.getDateTo())
                        .totalPrice(rent.getTotalPrice())
                        .brand(rent.getCar().getBrand().getName())
                        .model(rent.getCar().getModel())
                        .regNumber(rent.getCar().getRegNumber().getNumber())
                        .price(rent.getTotalPrice())
                        .login(rent.getAccount().getLogin())
                        .email(rent.getAccount().getAccountDetail().getEmail())
                        .phoneNumber(rent.getAccount().getAccountDetail().getPhoneNumber())
                        .build());

        return result;
    }

    public void addRent(RentDto rentDto) {

        Rent rent
                = Rent
                .builder()
                .id(rentDto.getId())
                .dateFrom(rentDto.getDateFrom())
                .dateTo(rentDto.getDateTo())
                .totalPrice(rentDto.getTotalPrice())
                .build();

        rent.setAccount(accountService.getRawAccount(accountDto));
        rent.setCar(carService.getRawCar(carDto));

        rentDao.save(rent);
    }

    public RentDto updateRent(RentDto rentDto) {

        Rent rent
                = Rent
                .builder()
                .id(rentDto.getId())
                .dateFrom(rentDto.getDateFrom())
                .dateTo(rentDto.getDateTo())
                .totalPrice(rentDto.getTotalPrice())
                .build();

        rent.setAccount(accountService.getRawAccount(accountDto));
        rent.setCar(carService.getRawCar(carDto));

        rent = rentDao.save(rent);

        return RentDto
                .builder()
                .id(rent.getId())
                .dateFrom(rent.getDateFrom())
                .dateTo(rent.getDateTo())
                .totalPrice(rent.getTotalPrice())
                .brand(rent.getCar().getBrand().getName())
                .model(rent.getCar().getModel())
                .regNumber(rent.getCar().getRegNumber().getNumber())
                .price(rent.getTotalPrice())
                .login(rent.getAccount().getLogin())
                .email(rent.getAccount().getAccountDetail().getEmail())
                .phoneNumber(rent.getAccount().getAccountDetail().getPhoneNumber())
                .build();
    }

    public void deleteRent(RentDto rentDto) {
        Rent rent
                = Rent
                .builder()
                .id(rentDto.getId())
                .build();

        rentDao.delete(rent);
    }

    public void deleteAllRents() {
        rentDao.deleteAll();
    }

}
