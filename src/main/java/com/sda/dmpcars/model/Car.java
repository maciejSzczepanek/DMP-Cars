package com.sda.dmpcars.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String model;
    @NotNull
    private Integer yearOfProduction;
    @NotNull
    private Double capacity;
    @NotNull
    private Integer powerKm;
    @NotNull
    private boolean available; // w bazie jest binary
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
/*
    @OneToMany
    @JoinColumn(name = "car")
    private Collection<Rent> rents;
*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Car car = (Car) o;
        return available == car.available &&
                Objects.equals(id, car.id) &&
                Objects.equals(regNumber, car.regNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id, model, yearOfProduction, capacity, powerKm, available, price, brand, type, engine, color, regNumber);
    }
}
