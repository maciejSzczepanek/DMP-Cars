package com.sda.dmpcars.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "reg_number")
public class RegNumber implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String number;
    @NotNull
    private String vin;

    @JsonIgnore
    @OneToOne(mappedBy = "regNumber")
    private Car car;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegNumber regNumber = (RegNumber) o;
        return Objects.equals(number, regNumber.number) &&
                Objects.equals(vin, regNumber.vin);
    }

    @Override
    public int hashCode() {

        return Objects.hash( number, vin);
    }
}
