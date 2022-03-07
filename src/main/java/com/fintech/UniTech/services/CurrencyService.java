package com.fintech.UniTech.services;

import com.fintech.UniTech.dto.CurrencyDto;

import java.util.List;

public interface CurrencyService {

    List<CurrencyDto> getCurrencies();
}
