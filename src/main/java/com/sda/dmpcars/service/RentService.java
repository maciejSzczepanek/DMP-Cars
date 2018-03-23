package com.sda.dmpcars.service;

import com.sda.dmpcars.dao.RentDao;
import com.sda.dmpcars.model.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "rentService")
public class RentService {

    private final RentDao rentDao;

    @Autowired
    public RentService(RentDao rentDao) {
        this.rentDao = rentDao;
    }

    public List<Rent> getAllRentsByCarId(Integer carId) {
        List<Rent> rents = rentDao.findRentByCarId(carId);
        return rents;
    }

    public List<Rent> getAllRentsByAccountId(Integer accountId) {
        List<Rent> rents = rentDao.findRentByAccountId(accountId);
        return rents;
    }

    public void deleteRent(Rent rent) {
        rentDao.delete(rent);
    }

    public void deleteAllRents() {
        rentDao.deleteAll();
    }

    public void addRent(Rent rent) {
        rentDao.save(rent);
    }

    public Rent updateRent(Rent rent) {
        return rentDao.save(rent);
    }

}
