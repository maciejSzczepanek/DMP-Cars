package com.sda.dmpcars.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)

public class AccountDto {
    private Integer id;
    private String login;
    private String password;
    private String role;
    private String firstName;
    private String lastName;
    private String email;
    private Date yearOfBirth;
    private String phoneNumber;
}
