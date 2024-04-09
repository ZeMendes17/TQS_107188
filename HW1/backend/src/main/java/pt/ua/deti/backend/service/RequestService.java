package pt.ua.deti.backend.service;

import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RequestService {
    private static final Logger log = LoggerFactory.getLogger(RequestService.class);

    private static final String URL = "https://v6.exchangerate-api.com/v6/d0f50677a029e81b0b014c68/latest/EUR";

    // make a request to the API
    public String makeExchangeRateRequest() {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL)).build();

        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            log.info("Exchange rates retrieved from API successfully");
            // get the "conversion_rates" object from the response
            return response.body().split("\"conversion_rates\":\\{")[1].split("\\}")[0];
            
            } catch (InterruptedException e) {
                // Restore interrupted state
                Thread.currentThread().interrupt();
                log.error("Thread was interrupted while retrieving exchange rates: {}", e.getMessage());
                return null;
            } catch (Exception e) {
                log.error("Failed to retrieve exchange rates: {}", e.getMessage());
                return null;
            }
    }
}
