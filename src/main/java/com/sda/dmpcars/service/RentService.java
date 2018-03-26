package com.sda.dmpcars.service;

import com.sda.dmpcars.dao.RentDao;
import com.sda.dmpcars.dto.RentDto;
import com.sda.dmpcars.model.Account;
import com.sda.dmpcars.model.Car;
import com.sda.dmpcars.model.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;

@Service(value = "rentService")
public class RentService {

    private final RentDao rentDao;
    private final CarService carService;
    private final AccountService accountService;

    @Autowired
    public RentService(RentDao rentDao, CarService carService, AccountService accountService) {
        this.rentDao = rentDao;
        this.carService = carService;
        this.accountService = accountService;
    }

    public RentDto addRent(RentDto rentDto){
        Rent rent = rentDao.save(convertToRent(rentDto));
        return converToRentDto(rent);
    }

    public RentDto getRentById(Integer id){
        Rent result = rentDao.findById(id).orElse(null);
        if (result != null){
            return converToRentDto(result);
        }
        return new RentDto();
    }

    public Set<RentDto> getRentsByAccountId(Integer id){
        Set<Rent> rents = rentDao.findRentsByAccountId(id);

        if(rents.size() == 0){
            return new HashSet<>();
        }

        Set<RentDto> result = new HashSet<>();
        rents.forEach(rent -> result.add(converToRentDto(rent)));
        return result;
    }

    public Set<RentDto> getRentsByCarId(Integer id){
        Set<Rent> rents = rentDao.findRentsByCarId(id);

        if(rents.size() == 0){
            return new HashSet<>();
        }

        Set<RentDto> result = new HashSet<>();
        rents.forEach(rent -> result.add(converToRentDto(rent)));
        return result;
    }

    /**
     * Convert to rent has id set to null as it will auto generate on persist, when updating
     * you need to set it as it equals() use id to compare
     * @param rentDto that came as a json from client through controller
     * @return RentDao converted to Rent
     */
    private Rent convertToRent(RentDto rentDto){
        return Rent.builder().fromDate(rentDto.getFromDate()).toDate(rentDto.getToDate())
                .totalPrice(rentDto.getTotalPrice())
                .account(Account.builder().id(rentDto.getAccountDto().getId()).username(rentDto.getAccountDto().getUsername())
                        .build())
                .car(Car.builder().id(rentDto.getCarDto().getId()).build())
                .build();
    }

    /**
     * This is similar to convertToRent but it provides full data i.e. id will be sent back to client
     * @param rent it's raw from db
     * @return raw rent converted to RentDto
     */
    private RentDto converToRentDto(Rent rent){
        return RentDto.builder().id(rent.getId()).fromDate(rent.getFromDate()).toDate(rent.getToDate())
                .carDto(carService.convertToCarDto(rent.getCar()))
                .accountDto(accountService.convertToAccountDto(rent.getAccount()))
                .build();
    }
}
