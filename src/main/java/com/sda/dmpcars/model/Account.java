package com.sda.dmpcars.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String password;

    @ManyToOne
    @JoinColumn(name = "accountTypeId")
    private AccountType accountType;

    @OneToOne
    @JoinColumn(name = "accountDetailId")
    private AccountDetail accountDetail;

    @OneToMany(mappedBy = "account")
    private List<Rent> rents = new ArrayList<>();

}
