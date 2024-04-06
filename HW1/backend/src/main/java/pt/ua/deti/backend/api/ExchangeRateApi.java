package pt.ua.deti.backend.api;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.net.http.HttpClient;
import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.stream.Collectors;

@Service
public class ExchangeRateApi {
    @Value("${app.api.key}") // in order to avoid a security hotspot, the api key is stored in the application.properties file
    private String apiKey;
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private static final String BASE_CURRENCY = "EUR";
    private static final Logger log = LoggerFactory.getLogger(ExchangeRateApi.class);

    public ExchangeRateApi() {
    }

    @Cacheable("exchangeRate")
    public Map<String, Float> getExchangeRate() {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(BASE_URL + apiKey + "/latest/" + BASE_CURRENCY)).build();

        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            log.info("Exchange rates retrieved successfully");
            // get the "conversion_rates" object from the response
            String rates = response.body().split("\"conversion_rates\":\\{")[1].split("\\}")[0];
            // split the object into a map of currency and exchange rate
            return List.of(rates.split(",")).stream().map(rate -> rate.split(":"))
                    .collect(Collectors.toMap(rate -> rate[0].replace("\"", "").replace("\n", "").trim(),
                            rate -> Float.parseFloat(rate[1])));
        } catch (Exception e) {
            log.error("Failed to retrieve exchange rates: " + e.getMessage());
            return null;
        }
    }

    @Cacheable("exchangeRateCoins")
    public List<String> getCoins() {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(BASE_URL + apiKey + "/latest/" + BASE_CURRENCY)).build();

        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            log.info("Coins retrieved successfully");
            // get the "conversion_rates" object from the response
            String coins = response.body().split("\"conversion_rates\":\\{")[1].split("\\}")[0];
            // split the object into a list of coins
            return List.of(coins.split(",")).stream().map(coin -> coin.split(":")[0].replace("\"", "").replace("\n", "").trim()).sorted()
                    .collect(Collectors.toList());
            
        } catch (Exception e) {
            log.error("Failed to retrieve coins: " + e.getMessage());
            return null;
        }

    }

    @CacheEvict(value = "exchangeRate", allEntries = true)
    @Scheduled(fixedRate = 600000) // 10 minutes in milliseconds
    public void evictCache() {
        log.info("Cache evicted");
    }
}
