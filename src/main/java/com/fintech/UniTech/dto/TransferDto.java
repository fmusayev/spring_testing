package com.fintech.UniTech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferDto {

    private Long fromAccount;

    private Long toAccount;

    private Double amount;
}
