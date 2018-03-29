package com.sda.dmpcars.service;

import com.sda.dmpcars.dao.CarDao;
import com.sda.dmpcars.dto.CarDto;
import com.sda.dmpcars.model.*;
import com.sda.dmpcars.validator.CarDtoVallidator;
import com.sda.dmpcars.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service("carService")
public class CarService {

    private final CarDao carDao;
    private final Validator<CarDto> validator = new CarDtoVallidator();

    @Autowired
    public CarService(CarDao carDao) {
        this.carDao = carDao;
    }

    public CarDto addCar(CarDto carDto) {
        if (validator.validate(carDto)) {
            Car result = carDao.save(convertToCar(carDto));
            return convertToCarDto(result);
        }
        return new CarDto();
    }

    public Set<CarDto> getAllCars() {
        Set<CarDto> result = new HashSet<>();

        carDao.findAll().forEach(car -> result.add(convertToCarDto(car)));

        if (result.size() == 0)
            return new HashSet<>();

        return result;
    }

    public CarDto getCarById(Integer id) {
        Car car = carDao.findById(id).orElse(null);
        if (car != null) {
            return convertToCarDto(car);
        }
        return new CarDto();
    }

    public CarDto updateCar(CarDto carDto) {
        Car car = convertToCar(carDto);
        car.setId(carDto.getId());
        return convertToCarDto(carDao.save(car));
    }

    public boolean deleteCarById(CarDto carDto) {
        if (!carDao.existsById(carDto.getId())) {
            return false;
        }
        if(validator.validate(carDto)) {
            Car car = convertToCar(carDto);
            car.setId(carDto.getId());
            carDao.delete(car);
            return true;
        }
        return false;
    }

    public void deleteAllCars() {
        carDao.deleteAll();
    }

    CarDto convertToCarDto(Car car) {
        return CarDto
                .builder()
                .id(car.getId())
                .model(car.getModel())
                .yearOfProduction(car.getYearOfProduction())
                .capacity(car.getCapacity())
                .powerKm(car.getPowerKm())
                .available(car.isAvailable())
                .price(car.getPrice())
                .brand(car.getBrand().getName())
                .color(car.getColor().getName())
                .engine(car.getEngine().getType())
                .type(car.getType().getName())
                .regNumber(car.getRegNumber().getNumber())
                .regVin(car.getRegNumber().getVin())
                .build();
    }

    private Car convertToCar(CarDto carDto) {
        return Car
                .builder()
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
                .color(Color
                        .builder()
                        .name(carDto.getColor())
                        .build())
                .engine(Engine
                        .builder()
                        .type(carDto.getEngine())
                        .build())
                .type(Type
                        .builder()
                        .name(carDto.getType())
                        .build())
                .regNumber(RegNumber
                        .builder()
                        .number(carDto.getRegNumber())
                        .vin(carDto.getRegVin())
                        .build())
                .build();
    }
}