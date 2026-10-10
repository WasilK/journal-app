package com.wasil.journal_app.services;

import com.wasil.journal_app.app_cache.AppCache;
import com.wasil.journal_app.externalApi.Quotes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class QuoteService {
    @Value("${external.api.key}")
    String apiKey;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private AppCache appCache;

    public Quotes getQuote(){
        String api = appCache.appCache.get("QUOTES_API_KEY");
        log.info("API URL = {}", api);
        HttpHeaders headers = new HttpHeaders();
        log.info("API key loaded: {}", apiKey != null && !apiKey.isBlank());
        headers.set("X-Api-Key", apiKey);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<Quotes> response = restTemplate.exchange(
                api,
                HttpMethod.GET,
                entity,
                Quotes.class
        );

        return response.getBody();
    }
}
