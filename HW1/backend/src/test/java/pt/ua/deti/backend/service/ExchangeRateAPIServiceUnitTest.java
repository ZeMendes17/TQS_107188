package pt.ua.deti.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pt.ua.deti.backend.service.ExchangeRateApiService;

class ExchangeRateAPIServiceUnitTest {
    private ExchangeRateApiService exchangeRateApiService;
    
    @BeforeEach
    void setUp() {
        exchangeRateApiService = new ExchangeRateApiService();
    }

    @Test
    void testGetExchangeRate() {
        
    }
}
