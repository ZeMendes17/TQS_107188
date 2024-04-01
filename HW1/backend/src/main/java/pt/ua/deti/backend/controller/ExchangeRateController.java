package pt.ua.deti.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pt.ua.deti.backend.api.ExchangeRateApi;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.List;
import java.util.Map;

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
    public Map<String, Float> getExchangeRate() {
        log.info("GET /exchange-rate");
        return exchangeRateApi.getExchangeRate();
    }

    // method to get the exchange rate of a specific coin
    @GetMapping("/exchange-rate/coin")
    public Float getExchangeRateCoin(@RequestParam String coin, @RequestParam Float price) {
        log.info("GET /exchange-rate/{}", coin);
        Map<String, Float> exchangeRate = exchangeRateApi.getExchangeRate();
        Float value = exchangeRate.get(coin) * price;
        // 2 decimal places
        return (float) (Math.round(value * 100.0) / 100.0);
    }

    // method to get the coins in the exchange rate
    @GetMapping("/exchange-rate/coins")
    public List<String> getCoins() {
        log.info("GET /exchange-rate/coins");
        return exchangeRateApi.getCoins();
    }
    
}
