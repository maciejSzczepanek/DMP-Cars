/*
package com.sda.dmpcars.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;
    @NotNull
    private String model;
    @NotNull
    private int brandId;
    @NotNull
    private int typeId;
    @NotNull
    private int engineId;
    @NotNull
    private LocalDate yearOfProduction;
    @NotNull
    private int colorId;
    @NotNull
    private double capacity;
    @NotNull
    private int powerKm;
    @NotNull
    private byte available; // w bazie jest binary
    @NotNull
    private int regNumberId;
    @NotNull
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "brandId")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "typeId")
    private Type type;

    @ManyToOne
    @JoinColumn(name = "engineId")
    private Engine engine;

    @ManyToOne
    @JoinColumn(name = "colorId")
    private Color color;

    @OneToOne
    @JoinColumn(name = "regNumber")
    private RegNumber regNumber;

    @OneToMany
    @JoinColumn(name = "car")
    private Collection<Rent> rents;

}
*/
