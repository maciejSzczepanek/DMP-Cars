package com.sda.dmpcars.dao;

import com.sda.dmpcars.model.Rent;
import org.springframework.data.repository.CrudRepository;

public interface RentDao extends CrudRepository<Rent, Integer> {
}
