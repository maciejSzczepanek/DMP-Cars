package com.sda.dmpcars.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;


@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;
    @NotNull
    private String name;

    @OneToMany
    @JoinColumn(name = "id")
    private Collection<Car> cars;

}
