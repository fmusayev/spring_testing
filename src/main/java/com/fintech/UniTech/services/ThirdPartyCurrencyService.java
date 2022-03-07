package com.fintech.UniTech.services;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;

@Component
public class ThirdPartyCurrencyService implements Serializable {

    private final HashMap<String, Double> currencyRates = new HashMap<>() {{
        put("USD/AZN", 1.7);
        put("AZN/TL", 8.0);
    }};

    @Cacheable("currencies")
    public HashMap<String, Double> getCurrencies() {
        System.out.println("Loading Currencies");
        return currencyRates;
    }
}
