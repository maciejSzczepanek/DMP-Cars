package com.sda.dmpcars.validator;

import com.sda.dmpcars.dto.RentDto;

public class RentDtoValidator implements Validator<RentDto> {

    @Override
    public boolean validate(RentDto rentDto) {
        if (rentDto == null)
            return false;
        if (rentDto.getId() == null)
            return false;
        return !(rentDto.getId() == null) &&
                !(rentDto.getFromDate() == null) &&
                !(rentDto.getToDate() == null) &&
                !(rentDto.getTotalPrice() == null) &&
                !(rentDto.getCarDto().getBrand().isEmpty()) &&
                !(rentDto.getCarDto().getModel().isEmpty()) &&
                !(rentDto.getAccountDto().getRole().isEmpty());
    }
}
