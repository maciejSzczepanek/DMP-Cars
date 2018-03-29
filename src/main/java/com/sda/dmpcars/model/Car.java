package com.sda.dmpcars.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "cars")
public class Car implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String model;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date yearOfProduction;
    @NotNull
    private Double capacity;
    @NotNull
    private Integer powerKm;
    @NotNull
    private boolean available;
    @NotNull
    private BigDecimal price;
    @NotNull
    private String brand;
    @NotNull
    private String type;
    @NotNull
    private String engine;
    @NotNull
    private String color;
    @NotNull
    private String regNumber;
    @NotNull
    private String regVin;
    
    @JsonIgnore
    @OneToMany(mappedBy = "car")
    private Set<Rent> rents;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id);
    }
}
