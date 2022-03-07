package com.fintech.UniTech.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String pin;

    private String password;
}
