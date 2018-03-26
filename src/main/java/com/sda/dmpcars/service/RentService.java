package com.sda.dmpcars.service;

import com.sda.dmpcars.dao.RentDao;
import com.sda.dmpcars.dto.RentDto;
import com.sda.dmpcars.model.Account;
import com.sda.dmpcars.model.Car;
import com.sda.dmpcars.model.Rent;
import com.sda.dmpcars.validator.RentDtoValidator;
import com.sda.dmpcars.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service(value = "rentService")
public class RentService {

    private final RentDao rentDao;
    private final CarService carService;
    private final AccountService accountService;
    private final Validator<RentDto> validator = new RentDtoValidator();

    @Autowired
    public RentService(RentDao rentDao, CarService carService, AccountService accountService) {
        this.rentDao = rentDao;
        this.carService = carService;
        this.accountService = accountService;
    }

    public RentDto addRent(RentDto rentDto) {
        if (validator.validate(rentDto)) {
            Rent rent = rentDao.save(convertToRent(rentDto));
            return convertToRentDto(rent);
        }

        return new RentDto();
    }

    public RentDto getRentById(Integer id) {
        Rent result = rentDao.findById(id).orElse(null);
        if (result != null) {
            return convertToRentDto(result);
        }
        return new RentDto();
    }

    public Set<RentDto> getRentsByAccountId(Integer id) {
        Set<RentDto> result = new HashSet<>();
        rentDao.findRentsByAccountId(id).forEach(rent -> result.add(convertToRentDto(rent)));

        if (result.size() == 0)
            return new HashSet<>();

        return result;
    }

    public Set<RentDto> getRentsByCarId(Integer id) {
        Set<RentDto> result = new HashSet<>();
        rentDao.findRentsByCarId(id).forEach(rent -> result.add(convertToRentDto(rent)));

        if (result.size() == 0)
            return new HashSet<>();

        return result;
    }

    public RentDto updateRent(RentDto rentDto) {
        Rent rent = convertToRent(rentDto);
        rent.setId(rentDto.getId());

        return convertToRentDto(rentDao.save(rent));
    }

    public void deleteRent(RentDto rentDto) {
        Rent rent = convertToRent(rentDto);
        rent.setId(rentDto.getId());
        rentDao.delete(rent);
    }

    public void deleteAllRents() {
        rentDao.deleteAll();
    }

    /**
     * Convert to rent has id set to null as it will auto generate on persist, when updating
     * you need to set it as it equals() use id to compare
     *
     * @param rentDto that came as a json from client through controller
     * @return RentDao converted to Rent
     */
    private Rent convertToRent(RentDto rentDto) {
        return Rent.builder().fromDate(rentDto.getFromDate()).toDate(rentDto.getToDate())
                .totalPrice(rentDto.getTotalPrice())
                .account(Account.builder().id(rentDto.getAccountDto().getId()).username(rentDto.getAccountDto().getUsername())
                        .build())
                .car(Car.builder().id(rentDto.getCarDto().getId()).build())
                .build();
    }

    /**
     * This is similar to convertToRent but it provides full data i.e. id will be sent back to client
     *
     * @param rent it's raw from db
     * @return raw rent converted to RentDto
     */
    private RentDto convertToRentDto(Rent rent) {
        return RentDto
                .builder()
                .id(rent.getId())
                .fromDate(rent.getFromDate())
                .toDate(rent.getToDate())
                .carDto(carService.convertToCarDto(rent.getCar()))
                .accountDto(accountService.convertToAccountDto(rent.getAccount()))
                .build();
    }
}