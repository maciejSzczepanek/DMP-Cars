package com.sda.dmpcars.service;

import com.sda.dmpcars.dao.CarDao;
import com.sda.dmpcars.dto.CarDto;
import com.sda.dmpcars.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

    @Mock
    private CarDao carDao;

    private CarService carService;

    @Before
    public void setUp() {
        initMocks(this);
        carService = new CarService(carDao);
    }

    @Test
    public void shouldAddCarHappyPath() {
        CarDto expected = getDefaultCarDto();

        Car carReturnedFromDb = getDefaultCar(1);

        Mockito.when(carDao.save(Mockito.any(Car.class))).thenReturn(carReturnedFromDb);

        CarDto actual = carService.addCar(expected);

        Assert.assertEquals(expected,actual);
        Mockito.verify(carDao,Mockito.timeout(1)).save(Mockito.any(Car.class));
    }


    @Test
    public void shouldReturnEmptyCarDtoWhenRegNumberIsNull(){
        CarDto expected = new CarDto();

        CarDto toSave = getDefaultCarDto();
        toSave.setRegNumber(null);

        CarDto actual = carService.addCar(toSave);

        Assert.assertEquals(expected,actual);

    }
    @Test
    public void shouldReturnSetOfCarsHappyPath(){
        Set<CarDto>expected = new HashSet<>();
        expected.add(getDefaultCarDto());

        Set<Car>setCarsFromDb = new HashSet<>();
        setCarsFromDb.add(getDefaultCar(1));

        Mockito.when(carDao.findAll()).thenReturn(setCarsFromDb);

        Set<CarDto>actual = carService.getAllCars();

        Assert.assertEquals(expected,actual);
        Mockito.verify(carDao,Mockito.timeout(1)).findAll();
    }

    @Test
    public void shouldReturnEmptySetCarsDtoWhenSizeOfSetIsZero(){
        Set<CarDto> expected = new HashSet<>();

        Set<CarDto>actual = carService.getAllCars();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldGetCarHappyPath(){
        CarDto expected = getDefaultCarDto();

        Car carReturnedFromDb = getDefaultCar(1);

        Mockito.when(carDao.findById(1)).thenReturn(Optional.of(carReturnedFromDb));

        CarDto actual = carService.getCarById(1);

        Assert.assertEquals(expected,actual);
        Mockito.verify(carDao,Mockito.timeout(1)).findById(1);
    }

    @Test
    public void shouldReturnEmptyCarDtoWhenIdNumberIsWrong(){
        CarDto expected = new CarDto();

        CarDto actual = carService.getCarById(1);

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void shouldUpdateCarHappyPath(){
        CarDto expected = getDefaultCarDto();

        Car carReturnedFromDb = getDefaultCar(1);

        Mockito.when(carDao.save(Mockito.any(Car.class))).thenReturn(carReturnedFromDb);

        CarDto actual = carService.updateCar(expected);

        Assert.assertEquals(expected,actual);
        Mockito.verify(carDao,Mockito.timeout(1)).save(Mockito.any(Car.class));
    }

    @Test
    public void shouldDeleteCarHappyPath(){
        CarDto carToDelete = getDefaultCarDto();
        boolean expected = true;

        Mockito.when(carDao.existsById(1)).thenReturn(true);
        Mockito.doNothing().when(carDao).deleteById(1);

        boolean actual = carService.deleteCarById(carToDelete);

         Assert.assertEquals(expected, actual);
         Mockito.verify(carDao,Mockito.timeout(1)).existsById(1);
    }


    private CarDto getDefaultCarDto() {
        return CarDto.builder().price(new BigDecimal(20_000))
                .available(true).powerKm(150).color("red")
                .model("golf").brand("vw").capacity(1.4)
                .regNumber("KR 777").engine("petrol").regVin("vin").id(1).type("combi").build();
    }

    private Car getDefaultCar(Integer id) {
        Car car = new Car();
        car.setAvailable(true);
        car.setBrand(Brand.builder().name("vw").build());
        car.setCapacity(1.4);
        car.setModel("golf");
        car.setColor(Color.builder().name("red").build());
        car.setPowerKm(150);
        car.setPrice(new BigDecimal(20_000));
        car.setRegNumber(RegNumber.builder().vin("vin").number("KR 777").build());
        car.setEngine(Engine.builder().type("petrol").build());
        car.setId(id);
        car.setType(Type.builder().name("combi").build());

        return car;
    }
}