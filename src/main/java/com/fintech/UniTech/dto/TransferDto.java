package com.fintech.UniTech.dto;

import lombok.Data;

@Data
public class TransferDto {

    private Long fromAccount;

    private Long toAccount;

    private Double amount;
}
