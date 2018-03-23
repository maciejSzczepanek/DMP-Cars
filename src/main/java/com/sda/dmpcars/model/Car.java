package com.sda.dmpcars.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

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
    @Cascade({org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.ALL})
    @JoinColumn(name = "brandId")
    private Brand brand;

    @ManyToOne
    @Cascade({org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.ALL})
    @JoinColumn(name = "typeId")
    private Type type;

    @ManyToOne
    @Cascade({org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.ALL})
    @JoinColumn(name = "engineId")
    private Engine engine;

    @ManyToOne
    @Cascade({org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.ALL})
    @JoinColumn(name = "colorId")
    private Color color;

    @OneToOne
    @Cascade({org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.ALL})
    @JoinColumn(name = "regNumberId")
    private RegNumber regNumber;
}
