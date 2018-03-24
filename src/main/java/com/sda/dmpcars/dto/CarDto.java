package com.sda.dmpcars.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sda.dmpcars.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDto {
    private Integer id;
    private String model;
    private Integer yearOfProduction;
    private Double capacity;
    private Integer powerKm;
    private boolean available;
    private BigDecimal price;
    private String brand;
    private String type;
    private String engine;
    private String color;
    private String regNumber;
}