package com.sda.dmpcars.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "cars")
public class Car implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String model;
    @NotNull
    private LocalDate yearOfProduction;
    @NotNull
    private Double capacity;
    @NotNull
    private Integer powerKm;
    @NotNull
    private boolean available; 
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
    @JoinColumn(name = "regNumberId")
    private RegNumber regNumber;

    @OneToMany(mappedBy = "car")
    private List<Rent> rents = new ArrayList<>();

}
