package com.sda.dmpcars.validator;

import com.sda.dmpcars.dto.CarDto;

public class CarDtoVallidator implements Validator<CarDto>{
    @Override
    public boolean validate(CarDto carDto) {
        if(carDto == null)
            return false;
        if(carDto.getRegNumber() == null)
            return false;
        return !carDto.getModel().isEmpty() && !carDto.getBrand().isEmpty()
                && !carDto.getColor().isEmpty() && !carDto.getEngine().isEmpty()
                && !carDto.getRegNumber().isEmpty() && !carDto.getRegVin().isEmpty();
    }
}