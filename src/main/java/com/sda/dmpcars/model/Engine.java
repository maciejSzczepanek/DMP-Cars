package com.sda.dmpcars.model;

import com.sda.dmpcars.helper.EngineType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "engines")
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String type;
/*    @NotNull
    private EngineType engineType;*/

    @OneToMany(mappedBy = "engine")
    private List<Car> cars = new ArrayList<>();

}
