package com.sda.dmpcars.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class RentDto {
    private int id;
    private Date fromDate;
    private Date toDate;
    private BigDecimal totalPrice;

    private AccountDto accountDto;
    private CarDto carDto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentDto rentDto = (RentDto) o;
        return id == rentDto.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
