package com.sda.dmpcars.service;

import com.sda.dmpcars.dao.CarDao;
import com.sda.dmpcars.dto.CarDto;
import com.sda.dmpcars.model.*;
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

    public List<CarDto> getAllCars() {
        List<CarDto> result = new ArrayList<>();
        carDao.findAll().forEach(car -> CarDto
                .builder()
                .id(car.getId())
                .model(car.getModel())
                .yearOfProduction(car.getYearOfProduction())
                .capacity(car.getCapacity())
                .powerKm(car.getPowerKm())
                .available(car.isAvailable())
                .price(car.getPrice())
                .brand(car.getBrand().getName())
                .type(car.getType().getName())
                .engine(car.getEngine().getType())
                .color(car.getColor().getName())
                .regNumber(car.getRegNumber().getNumber())
                .build());
        return result;
    }

    public CarDto getCarById(Integer carId) {
        Car car = carDao.findById(carId).orElse(null);
        if (car != null) {
            return CarDto.builder()
                    .id(car.getId())
                    .model(car.getModel())
                    .yearOfProduction(car.getYearOfProduction())
                    .capacity(car.getCapacity())
                    .powerKm(car.getPowerKm())
                    .available(car.isAvailable())
                    .price(car.getPrice())
                    .brand(car.getBrand().getName())
                    .type(car.getType().getName())
                    .engine(car.getEngine().getType())
                    .color(car.getColor().getName())
                    .regNumber(car.getRegNumber().getNumber())
                    .build();
        }
        return new CarDto();
    }

    public void addCar(CarDto carDto) {
        Car car = Car
                .builder()
                .id(carDto.getId())
                .model(carDto.getModel())
                .yearOfProduction(carDto.getYearOfProduction())
                .capacity(carDto.getCapacity())
                .powerKm(carDto.getPowerKm())
                .available(carDto.isAvailable())
                .price(carDto.getPrice())
                .brand(Brand
                        .builder()
                        .name(carDto.getBrand())
                        .build())
                .type(Type
                        .builder()
                        .name(carDto.getType())
                        .build())
                .engine(Engine
                        .builder()
                        .type(carDto.getEngine())
                        .build())
                .color(Color
                        .builder()
                        .name(carDto.getColor())
                        .build())
                .regNumber(RegNumber
                        .builder()
                        .number(carDto.getRegNumber())
                        .build())
                .build();
        carDao.save(car);
    }

    public void deleteCar(CarDto carDto) {
        Car car = Car.builder()
                .id(carDto.getId())
                .regNumber(RegNumber
                        .builder()
                        .number(carDto.getRegNumber())
                        .build())
                .build();
        carDao.delete(car);
    }

    public void deleteAllCars() {
        carDao.deleteAll();
    }

    public CarDto updateCar(CarDto carDto) {
        Car car = Car
                .builder()
                .id(carDto.getId())
                .model(carDto.getModel())
                .yearOfProduction(carDto.getYearOfProduction())
                .capacity(carDto.getCapacity())
                .powerKm(carDto.getPowerKm())
                .available(carDto.isAvailable())
                .price(carDto.getPrice())
                .brand(Brand
                        .builder()
                        .name(carDto.getBrand())
                        .build())
                .type(Type
                        .builder()
                        .name(carDto.getType())
                        .build())
                .engine(Engine
                        .builder()
                        .type(carDto.getEngine())
                        .build())
                .color(Color
                        .builder()
                        .name(carDto.getColor())
                        .build())
                .regNumber(RegNumber
                        .builder()
                        .number(carDto.getRegNumber())
                        .build())
                .build();
        car = carDao.save(car);
        return CarDto.builder()
                .id(car.getId())
                .model(car.getModel())
                .yearOfProduction(car.getYearOfProduction())
                .capacity(car.getCapacity())
                .powerKm(car.getPowerKm())
                .available(car.isAvailable())
                .price(car.getPrice())
                .brand(car.getBrand().getName())
                .type(car.getType().getName())
                .engine(car.getEngine().getType())
                .color(car.getColor().getName())
                .regNumber(car.getRegNumber().getNumber())
                .build();
    }

    public Car getRawCar(Integer id) {
        Car car = carDao.findById(id).orElse(null);
        if (car != null)
            return car;

        return new Car();
    }

}