package com.sda.dmpcars.dao;

import com.sda.dmpcars.model.Rent;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RentDao extends CrudRepository<Rent, Integer> {
    List<Rent> findRentByCarId(Integer id);

    List<Rent> findRentByAccountId(Integer id);
}