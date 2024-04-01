package pt.ua.deti.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.ua.deti.backend.api.ExchangeRateApi;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class ExchangeRateController {
    
    private ExchangeRateApi exchangeRateApi;
    private static final Logger log = LoggerFactory.getLogger(ExchangeRateController.class);

    @Autowired
    public ExchangeRateController(ExchangeRateApi exchangeRateApi) {
        this.exchangeRateApi = exchangeRateApi;
    }

    // methods

    // method to get the exchange rate
    @GetMapping("/exchange-rate")
    public String getExchangeRate() {
        log.info("GET /exchange-rate");
        return exchangeRateApi.getExchangeRate();
    }
}
