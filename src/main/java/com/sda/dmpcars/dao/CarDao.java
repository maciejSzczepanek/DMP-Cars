package com.sda.dmpcars.dao;

import com.sda.dmpcars.model.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarDao extends CrudRepository<Car, Integer> {
}
