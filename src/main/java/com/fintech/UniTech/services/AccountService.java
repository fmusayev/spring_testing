package com.fintech.UniTech.services;

import com.fintech.UniTech.dto.AccountDto;
import com.fintech.UniTech.dto.TransferDto;
import com.fintech.UniTech.dto.TransferResultDto;

import java.util.List;

public interface AccountService {

    List<AccountDto> getUserAccounts(Long userId);

    TransferResultDto transfer(Long userId, TransferDto transferDto);

}
