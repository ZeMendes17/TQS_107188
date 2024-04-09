package pt.ua.deti.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pt.ua.deti.backend.cache.InMemoryCache;
import pt.ua.deti.backend.service.ExchangeRateApiService;
import pt.ua.deti.backend.cache.CacheStatistics;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;

@WebMvcTest(CacheController.class)
class CacheControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExchangeRateApiService exchangeRateApi;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    void getCacheTest() {
        InMemoryCache<String, Map<String, Float>> cache = new InMemoryCache<String, Map<String, Float>>();
        Map<String, Float> map = new HashMap<>();
        map.put("EUR", 1.0f);
        map.put("USD", 1.2f);
        map.put("GBP", 0.8f);
        cache.put("exchangeRate", map);
        when(exchangeRateApi.getExchangeRateCache()).thenReturn(cache);

        InMemoryCache<String, List<String>> coinsCache = new InMemoryCache<String, List<String>>();
        List<String> coins = new ArrayList<>();
        coins.add("EUR");
        coins.add("USD");
        coins.add("GBP");
        coinsCache.put("coins", coins);
        when(exchangeRateApi.getCoinsCache()).thenReturn(coinsCache);

        try {
            mockMvc.perform(get("/api/v1/cache")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }

        verify(exchangeRateApi, times(1)).getExchangeRateCache();
        verify(exchangeRateApi, times(1)).getCoinsCache();
    }

    @Test
    void getCacheStatisticsTest() {
        CacheStatistics exchangeRateCacheStatistics = new CacheStatistics();
        exchangeRateCacheStatistics.setPuts(2);
        exchangeRateCacheStatistics.setHits(10);
        exchangeRateCacheStatistics.setMisses(2);
        when(exchangeRateApi.getExchangeRateCacheStatistics()).thenReturn(exchangeRateCacheStatistics);

        CacheStatistics coinsCacheStatistics = new CacheStatistics();
        exchangeRateCacheStatistics.setPuts(2);
        exchangeRateCacheStatistics.setHits(10);
        exchangeRateCacheStatistics.setMisses(2);
        when(exchangeRateApi.getCoinsCacheStatistics()).thenReturn(coinsCacheStatistics);

        try {
            mockMvc.perform(get("/api/v1/cache/statistics")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }

        verify(exchangeRateApi, times(1)).getExchangeRateCacheStatistics();
        verify(exchangeRateApi, times(1)).getCoinsCacheStatistics();
    }
}
