package com.sda.dmpcars.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class RegNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;
    @NotNull
    private String number;
    @NotNull
    private String vin;

    @OneToOne
    @JoinColumn(name = "id")
    private Car car;

}
