package com.sda.dmpcars.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private String password;
    @NotNull
    private int accountTypeId;
    @NotNull
    private int accountDetailId;

    @ManyToOne
    @JoinColumn(name = "accountTypeId")
    private AccountType accountType;

    @ManyToOne
    @JoinColumn(name = "accountBetailId")
    private AccountDetail accountDetail;
}
