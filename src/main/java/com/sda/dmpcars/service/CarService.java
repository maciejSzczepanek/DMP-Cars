package com.sda.dmpcars.service;

import com.sda.dmpcars.dao.CarDao;
import com.sda.dmpcars.model.Car;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("carService")
public class CarService {

    private final CarDao carDao;

    @Autowired
    public CarService(CarDao carDao) {
        this.carDao = carDao;
    }

    public List<Car>getAllCars() {
        List<Car>result = new ArrayList<>();
        carDao.findAll().forEach(result::add);
        return result;
    }

     public Car getCarById(Integer carId){
         return carDao.findById(carId).orElse(new Car());
     }

     public void addCar(Car car){
        carDao.save(car);
     }

     public void deleteCarById(Car car){
        carDao.delete(car);
     }

     public void deleteAllCars(){
        carDao.deleteAll();
     }

     public Car updateCar(Car car){
        return carDao.save(car);
     }
}
