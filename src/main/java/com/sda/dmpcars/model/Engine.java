package com.sda.dmpcars.model;

import com.sda.dmpcars.helper.EngineType;
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
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;
    @NotNull
    private String type;
    @NotNull
    private EngineType engineType;

    @OneToMany
    @JoinColumn(name = "id")
    private Collection<Car> cars;

}
