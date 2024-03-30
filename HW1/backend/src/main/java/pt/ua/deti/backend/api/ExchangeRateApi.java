package pt.ua.deti.backend.api;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.net.URI;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ExchangeRateApi {
    private static final String API_KEY = "d0f50677a029e81b0b014c68";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";
    private static final String BASE_CURRENCY = "EUR";
    private static final Logger log = LoggerFactory.getLogger(ExchangeRateApi.class);

    public ExchangeRateApi() {
    }

    @Cacheable("exchangeRate")
    public String getExchangeRate() {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(BASE_URL + BASE_CURRENCY)).build();

        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            log.info("Exchange rate API response: " + response.body());
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error getting exchange rates: " + e.getMessage());
            return null;
        }
    }

}
