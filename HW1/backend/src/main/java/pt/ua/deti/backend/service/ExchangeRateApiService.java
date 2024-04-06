package pt.ua.deti.backend.service;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.net.http.HttpClient;
import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import pt.ua.deti.backend.cache.InMemoryCache;
import pt.ua.deti.backend.cache.CacheStatistics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.stream.Collectors;

@Service
public class ExchangeRateApiService {
    @Value("${app.api.key}") // in order to avoid a security hotspot, the api key is stored in the application.properties file
    private String apiKey;
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private static final String BASE_CURRENCY = "EUR";
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
            exchangeRateCacheStatistics.incrementHits();
            return exchangeRate;
        }

        exchangeRateCacheStatistics.incrementMisses();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(BASE_URL + apiKey + "/latest/" + BASE_CURRENCY)).build();

        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            log.info("Exchange rates retrieved from API successfully");
            // get the "conversion_rates" object from the response
            String rates = response.body().split("\"conversion_rates\":\\{")[1].split("\\}")[0];
            // split the object into a map of currency and exchange rate
            Map<String, Float> formatedResponse = List.of(rates.split(",")).stream().map(rate -> rate.split(":"))
                    .collect(Collectors.toMap(rate -> rate[0].replace("\"", "").replace("\n", "").trim(),
                            rate -> Float.parseFloat(rate[1])));

            exchangeRateCache.put("exchangeRate", formatedResponse, 120);
            exchangeRateCacheStatistics.incrementPuts();
            return formatedResponse;
            
            } catch (InterruptedException e) {
                // Restore interrupted state
                Thread.currentThread().interrupt();
                log.error("Thread was interrupted while retrieving exchange rates: {}", e.getMessage());
                return new HashMap<>();
            } catch (Exception e) {
                log.error("Failed to retrieve exchange rates: {}", e.getMessage());
                return new HashMap<>();
            }
    }

    public List<String> getCoins() {
        List<String> coinsFromCache = coinsCache.get("exchangeRateCoins");
        if (coinsFromCache != null) {
            log.info("Coins retrieved from cache successfully");
            coinsCacheStatistics.incrementHits();
            return coinsFromCache;
        }

        coinsCacheStatistics.incrementMisses();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(BASE_URL + apiKey + "/latest/" + BASE_CURRENCY)).build();

        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            log.info("Coins retrieved from API successfully");
            // get the "conversion_rates" object from the response
            String coins = response.body().split("\"conversion_rates\":\\{")[1].split("\\}")[0];
            // split the object into a list of coins
            List<String> formatedResponse = List.of(coins.split(",")).stream().map(coin -> coin.split(":")[0]
                    .replace("\"", "").replace("\n", "").trim()).sorted()
                    .collect(Collectors.toList());

            coinsCache.put("exchangeRateCoins", formatedResponse, 60 * 60);
            coinsCacheStatistics.incrementPuts();
            return formatedResponse;
            
        } catch (InterruptedException e) {
            // Restore interrupted state
            Thread.currentThread().interrupt();
            log.error("Thread was interrupted while retrieving coins: {}", e.getMessage());
            return List.of();
        } catch (Exception e) {
            log.error("Failed to retrieve coins: {}", e.getMessage());
            return List.of();
        }

    }
}
