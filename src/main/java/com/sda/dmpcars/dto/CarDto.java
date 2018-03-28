package com.sda.dmpcars.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDto {
    private Integer id;
    private String model;
    private Date yearOfProduction;
    private Double capacity;
    private Integer powerKm;
    private boolean available;
    private BigDecimal price;
    private String brand;
    private String color;
    private String engine;
    private String type;
    private String regNumber;
    private String regVin;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CarDto carDto = (CarDto) o;
        return this.id == carDto.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), this.id);
    }
}
