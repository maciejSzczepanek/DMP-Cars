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
@Table(name = "rents")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private LocalDate dateFrom;
    @NotNull
    private LocalDate dateTo;
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "rent")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "rent")
    private Account account;

}
