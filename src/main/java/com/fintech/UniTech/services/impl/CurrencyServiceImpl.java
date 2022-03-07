package com.fintech.UniTech.services.impl;

import com.fintech.UniTech.dto.CurrencyDto;
import com.fintech.UniTech.services.CurrencyService;
import com.fintech.UniTech.services.ThirdPartyCurrencyService;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.InjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final ThirdPartyCurrencyService thirdPartyCurrencyService;

    @Override
    public List<CurrencyDto> getCurrencies() {
        return thirdPartyCurrencyService.getCurrencies()
                .entrySet()
                .stream()
                .map(c -> new CurrencyDto(c.getKey().split("/")[0], c.getKey().split("/")[1], c.getValue()))
                .collect(Collectors.toList());
    }
}
