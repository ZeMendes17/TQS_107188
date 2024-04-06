package pt.ua.deti.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import pt.ua.deti.backend.service.ExchangeRateApiService;
import pt.ua.deti.backend.cache.CacheStatistics;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.Map;
import java.util.List;

import pt.ua.deti.backend.cache.InMemoryCache;

@CrossOrigin(origins = "http://localhost:3030")
@RestController
@RequestMapping("/api/v1")
public class CacheController {

    private ExchangeRateApiService exchangeRateApi;
    private static final Logger log = LoggerFactory.getLogger(CacheController.class);

    @Autowired
    public CacheController(ExchangeRateApiService exchangeRateApi) {
        this.exchangeRateApi = exchangeRateApi;
    }

    @GetMapping("/cache")
    public String getCache() {
        log.info("GET /cache");
        InMemoryCache<String, Map<String, Float>> exchangeRateCache = exchangeRateApi.getExchangeRateCache();
        InMemoryCache<String, List<String>> coinsCache = exchangeRateApi.getCoinsCache();

        return "[ exchangeRateCache: " + exchangeRateCache.toString() + ", coinsCache: " + coinsCache.toString() + " ]";
    }

    @GetMapping("/cache/statistics")
    public String getCacheStatistics() {
        log.info("GET /cache/statistics");
        CacheStatistics exchangeRateCacheStatistics = exchangeRateApi.getExchangeRateCacheStatistics();
        CacheStatistics coinsCacheStatistics = exchangeRateApi.getCoinsCacheStatistics();

        return "[ exchangeRateCacheStatistics: " + exchangeRateCacheStatistics.toString() + ", coinsCacheStatistics: "
                + coinsCacheStatistics.toString() + " ]";
    }
}
