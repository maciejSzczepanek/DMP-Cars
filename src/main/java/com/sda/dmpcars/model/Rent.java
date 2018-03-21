package com.sda.dmpcars.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;
    @NotNull
    private LocalDate dateFrom;
    @NotNull
    private LocalDate dateTo;
    @NotNull
    private BigDecimal totalPrice;
    @NotNull
    private int carId;
    @NotNull
    private Integer accountId;

 @ManyToOne
    @JoinColumn(name = "carId")
    private Car car;


    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;


}
