package com.sda.dmpcars.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RentDto {

    private Integer id;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private BigDecimal totalPrice;

    private String brand;
    private String model;
    private String regNumber;
    private BigDecimal price;

    private String login;
    private String email;
    private String phoneNumber;

    private CarDto carDto;
    private AccountDto accountDto;

}

