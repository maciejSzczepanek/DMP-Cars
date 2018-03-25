package com.sda.dmpcars.dao;

import com.sda.dmpcars.model.Rent;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface RentDao extends CrudRepository<Rent, Integer> {
    Set<Rent> findRentsByCarId(Integer id);

    Set<Rent> findRentsByAccountId(Integer id);
}
