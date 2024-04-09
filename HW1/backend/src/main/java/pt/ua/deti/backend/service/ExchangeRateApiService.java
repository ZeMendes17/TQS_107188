package pt.ua.deti.backend.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.ua.deti.backend.cache.InMemoryCache;
import pt.ua.deti.backend.cache.CacheStatistics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.stream.Collectors;

@Service
public class ExchangeRateApiService {
    private RequestService requestService;

    @Autowired
    public ExchangeRateApiService(RequestService requestService) {
        this.requestService = requestService;
    }

    private static final Logger log = LoggerFactory.getLogger(ExchangeRateApiService.class);

    // Cache
    private final InMemoryCache<String, Map<String, Float>> exchangeRateCache = new InMemoryCache<>();
    private final InMemoryCache<String, List<String>> coinsCache = new InMemoryCache<>();
    // Cache Statistics
    private final CacheStatistics exchangeRateCacheStatistics = new CacheStatistics();
    private final CacheStatistics coinsCacheStatistics = new CacheStatistics();

    public Map<String, Float> getExchangeRate() {
        Map<String, Float> exchangeRate = exchangeRateCache.get("exchangeRate");
        if (exchangeRate != null) {
            log.info("Exchange rates retrieved from cache successfully");
            log.info("Cache hit");
            exchangeRateCacheStatistics.incrementHits();
            return exchangeRate;
        }

        log.info("Cache miss");
        exchangeRateCacheStatistics.incrementMisses();
        
        // make a request to the API
        String response = requestService.makeExchangeRateRequest();

        // split the object into a map of currency and exchange rate
        Map<String, Float> formatedResponse = List.of(response.split(",")).stream().map(rate -> rate.split(":"))
        .collect(Collectors.toMap(rate -> rate[0].replace("\"", "").replace("\n", "").trim(),
                rate -> Float.parseFloat(rate[1])));

        log.info("Cache put");
        exchangeRateCache.put("exchangeRate", formatedResponse, 120);
        exchangeRateCacheStatistics.incrementPuts();
        return formatedResponse;
    }

    public List<String> getCoins() {
        List<String> coinsFromCache = coinsCache.get("exchangeRateCoins");
        if (coinsFromCache != null) {
            log.info("Coins retrieved from cache successfully");
            log.info("Cache hit");
            coinsCacheStatistics.incrementHits();
            return coinsFromCache;
        }

        log.info("Cache miss");
        coinsCacheStatistics.incrementMisses();

        // make a request to the API
        String response = requestService.makeExchangeRateRequest();

        List<String> formatedResponse = List.of(response.split(",")).stream().map(coin -> coin.split(":")[0]
                    .replace("\"", "").replace("\n", "").trim()).sorted()
                    .collect(Collectors.toList());

        log.info("Cache put");
        coinsCache.put("exchangeRateCoins", formatedResponse, 60 * 60);
        coinsCacheStatistics.incrementPuts();
        return formatedResponse;
    }

    public CacheStatistics getExchangeRateCacheStatistics() {
        log.info("Getting exchange rate cache statistics: {}", exchangeRateCacheStatistics);
        return exchangeRateCacheStatistics;
    }

    public CacheStatistics getCoinsCacheStatistics() {
        log.info("Getting coins cache statistics: {}", coinsCacheStatistics);
        return coinsCacheStatistics;
    }

    public InMemoryCache<String, Map<String, Float>> getExchangeRateCache() {
        log.info("Getting exchange rate cache: {}", exchangeRateCache);
        return exchangeRateCache;
    }

    public InMemoryCache<String, List<String>> getCoinsCache() {
        log.info("Getting coins cache: {}", coinsCache);
        return coinsCache;
    }
}
