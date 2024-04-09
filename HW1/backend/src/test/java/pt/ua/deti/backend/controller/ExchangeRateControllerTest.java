package pt.ua.deti.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import pt.ua.deti.backend.service.ExchangeRateApiService;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;

@WebMvcTest(ExchangeRateController.class)
class ExchangeRateControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExchangeRateApiService exchangeRateApi;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    void getExchangeRateTest() {
        Map<String, Float> map = Map.of("EUR", 1.0f, "USD", 1.2f, "GBP", 0.8f);
        when(exchangeRateApi.getExchangeRate()).thenReturn(map);

        try {
            mockMvc.perform(get("/api/v1/exchange-rate")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }

        verify(exchangeRateApi, times(1)).getExchangeRate();
    }

    @Test
    void getExchangeRateCoinTest() {
        Map<String, Float> map = Map.of("EUR", 1.0f, "USD", 1.2f, "GBP", 0.8f);
        when(exchangeRateApi.getExchangeRate()).thenReturn(map);

        try {
            mockMvc.perform(get("/api/v1/exchange-rate/coin")
                .param("coin", "USD")
                .param("price", String.valueOf(12.0f))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> result.getResponse().getContentAsString().equals("14.40"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        verify(exchangeRateApi, times(1)).getExchangeRate();
    }

    @Test
    void getCoinsTest() {
        when(exchangeRateApi.getCoins()).thenReturn(java.util.List.of("EUR", "USD", "GBP"));

        try {
            mockMvc.perform(get("/api/v1/exchange-rate/coins")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> result.getResponse().getContentAsString().equals("[\"EUR\",\"USD\",\"GBP\"]"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        verify(exchangeRateApi, times(1)).getCoins();
    }
}
