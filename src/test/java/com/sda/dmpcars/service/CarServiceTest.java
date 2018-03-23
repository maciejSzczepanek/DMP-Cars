package com.sda.dmpcars.service;

import com.sda.dmpcars.dao.CarDao;
import com.sda.dmpcars.model.Car;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

    @Mock
    CarDao carDao;

    @InjectMocks
    CarService carService;

    @Test
    public void getAllCars() {
        //given
        List<Car> expected = new ArrayList<>();
        expected.add(Car.builder().id(1).build());
        expected.add(Car.builder().id(2).build());
        expected.add(Car.builder().id(3).build());

        //when
        Mockito.when(carDao.findAll()).thenReturn(expected);

        //Then
        List<Car>actual = carService.getAllCars();
        assertThat(actual, is(expected));
    }

    @Test
    public void getCarById() {
        //given
        Integer id = 2;
        Car expected = Car.builder().id(id).build();

        //when
        Mockito.when(carDao.findById(id)).thenReturn(Optional.of(expected));

        //Then
        Car actual = carService.getCarById(id);
        assertEquals(expected, actual);

    }

    @Test
    public void addCar() {

    }

    @Test
    public void deleteCarById() {
    }

    @Test
    public void deleteAllCars() {
    }

    @Test
    public void updateCar() {
    }
}