package pt.ua.deti.backend.service;

import pt.ua.deti.backend.cache.CacheStatistics;
import pt.ua.deti.backend.cache.InMemoryCache;

import java.util.Map;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class ExchangeRateApiServiceTest {
    @Mock(lenient = true)
    private RequestService requestService;

    @InjectMocks
    private ExchangeRateApiService exchangeRateApi;

    @Test
    void testGetExchangeRateNoCache() {
        when(requestService.makeExchangeRateRequest()).thenReturn("EUR:1.0,USD:1.2,GBP:0.8,JPY:130.0");


        Map<String, Float> exchangeRate = exchangeRateApi.getExchangeRate();
        assertNotNull(exchangeRate);
        assertTrue(exchangeRate.containsKey("EUR"));
        assertTrue(exchangeRate.containsKey("USD"));
        assertTrue(exchangeRate.containsKey("GBP"));
        assertTrue(exchangeRate.containsKey("JPY"));

        assertTrue(exchangeRate.containsValue(1.0f));
        assertTrue(exchangeRate.containsValue(1.2f));
        assertTrue(exchangeRate.containsValue(0.8f));
        assertTrue(exchangeRate.containsValue(130.0f));
    }

    @Test
    void testGetExchangeRateCache() {
        when(requestService.makeExchangeRateRequest()).thenReturn("EUR:1.0,USD:1.2,GBP:0.8,JPY:130.0");

        InMemoryCache<String, Map<String, Float>> exchangeRateCache = exchangeRateApi.getExchangeRateCache();
        assertNull(exchangeRateCache.get("exchangeRate"));
        Map<String, Float> exchangeRate = exchangeRateApi.getExchangeRate();
        assertNotNull(exchangeRate);
        assertTrue(exchangeRate.containsKey("EUR"));
        assertTrue(exchangeRate.containsKey("USD"));
        assertTrue(exchangeRate.containsKey("GBP"));
        assertTrue(exchangeRate.containsKey("JPY"));

        assertTrue(exchangeRate.containsValue(1.0f));
        assertTrue(exchangeRate.containsValue(1.2f));
        assertTrue(exchangeRate.containsValue(0.8f));
        assertTrue(exchangeRate.containsValue(130.0f));

        Map<String, Float> exchangeRateFromCache = exchangeRateCache.get("exchangeRate");
        assertNotNull(exchangeRateFromCache);
        assertTrue(exchangeRateFromCache.containsKey("EUR"));
        assertTrue(exchangeRateFromCache.containsKey("USD"));
        assertTrue(exchangeRateFromCache.containsKey("GBP"));
        assertTrue(exchangeRateFromCache.containsKey("JPY"));

        assertTrue(exchangeRateFromCache.containsValue(1.0f));
        assertTrue(exchangeRateFromCache.containsValue(1.2f));
        assertTrue(exchangeRateFromCache.containsValue(0.8f));
        assertTrue(exchangeRateFromCache.containsValue(130.0f));
    }

    @Test
    void testGetCoinsNoCache() {
        when(requestService.makeExchangeRateRequest()).thenReturn("EUR:1.0,USD:1.2,GBP:0.8,JPY:130.0");

        List<String> coins = exchangeRateApi.getCoins();
        assertNotNull(coins);
        assertTrue(coins.contains("EUR"));
        assertTrue(coins.contains("USD"));
        assertTrue(coins.contains("GBP"));
        assertTrue(coins.contains("JPY"));
    }

    @Test
    void testGetCoinsCache() {
        when(requestService.makeExchangeRateRequest()).thenReturn("EUR:1.0,USD:1.2,GBP:0.8,JPY:130.0");

        InMemoryCache<String, List<String>> coinsCache = exchangeRateApi.getCoinsCache();
        assertNull(coinsCache.get("exchangeRateCoins"));
        List<String> coins = exchangeRateApi.getCoins();
        assertNotNull(coins);
        assertTrue(coins.contains("EUR"));
        assertTrue(coins.contains("USD"));
        assertTrue(coins.contains("GBP"));
        assertTrue(coins.contains("JPY"));

        List<String> coinsFromCache = coinsCache.get("exchangeRateCoins");
        assertNotNull(coinsFromCache);
        assertTrue(coinsFromCache.contains("EUR"));
        assertTrue(coinsFromCache.contains("USD"));
        assertTrue(coinsFromCache.contains("GBP"));
        assertTrue(coinsFromCache.contains("JPY"));
    }

    @Test
    void testGetExchangeRateCacheStatistics() {
        CacheStatistics exchangeRateCacheStatistics = exchangeRateApi.getExchangeRateCacheStatistics();
        when(requestService.makeExchangeRateRequest()).thenReturn("EUR:1.0,USD:1.2,GBP:0.8,JPY:130.0");
    
        exchangeRateApi.getExchangeRate();
        exchangeRateApi.getExchangeRate();
        exchangeRateApi.getExchangeRate();

        exchangeRateCacheStatistics = exchangeRateApi.getExchangeRateCacheStatistics();
        assertNotNull(exchangeRateCacheStatistics);
        assertEquals(2, exchangeRateCacheStatistics.getHits());
        assertEquals(1, exchangeRateCacheStatistics.getMisses());
        assertEquals(1, exchangeRateCacheStatistics.getPuts());
    }

    @Test
    void testGetCoinsCacheStatistics() {
        CacheStatistics coinsCacheStatistics = exchangeRateApi.getCoinsCacheStatistics();
        when(requestService.makeExchangeRateRequest()).thenReturn("EUR:1.0,USD:1.2,GBP:0.8,JPY:130.0");

        exchangeRateApi.getCoins();
        exchangeRateApi.getCoins();
        exchangeRateApi.getCoins();
        exchangeRateApi.getCoins();

        coinsCacheStatistics = exchangeRateApi.getCoinsCacheStatistics();
        assertNotNull(coinsCacheStatistics);
        assertEquals(3, coinsCacheStatistics.getHits());
        assertEquals(1, coinsCacheStatistics.getMisses());
        assertEquals(1, coinsCacheStatistics.getPuts());
    }
        
}
