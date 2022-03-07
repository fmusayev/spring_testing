package com.fintech.UniTech.domain;

import com.fintech.UniTech.enums.AccountStatus;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    private User user;

    private String name;

    private Double balance;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;
}
