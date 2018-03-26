package com.sda.dmpcars.controller;

import com.sda.dmpcars.dto.CarDto;
import com.sda.dmpcars.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    CarService carService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CarDto getCar(@PathVariable Integer id) {
        return carService.getCarById(id);
    }

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public Set<CarDto> getAllCars() {
        return carService.getAllCars();
    }

    @RequestMapping(value = "/car", method = RequestMethod.POST)
    public ResponseEntity<String> addCar(@RequestBody CarDto carDto) {
        CarDto result = carService.addCar(carDto);
        if (result.getId() != 0) {
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCar(@RequestBody CarDto carDto) {
        if (carService.deleteCarById(carDto)) {
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/car", method = RequestMethod.PUT)
    public ResponseEntity<String> updateCar(@RequestBody CarDto carDto){
        if(carDto.getId() == carService.updateCar(carDto).getId()){
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
        }
    }
}
