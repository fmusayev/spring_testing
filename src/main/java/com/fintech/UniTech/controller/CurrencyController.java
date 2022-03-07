package com.fintech.UniTech.controller;

import com.fintech.UniTech.dto.CurrencyDto;
import com.fintech.UniTech.dto.TransferDto;
import com.fintech.UniTech.dto.TransferResultDto;
import com.fintech.UniTech.services.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("/currencies")
    public List<CurrencyDto> getCurrencies() {
        return currencyService.getCurrencies();
    }
}
