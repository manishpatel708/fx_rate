package com.fx.rates.util;

import com.fx.rates.model.FxRate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class FxRateClient {

    @Value("${external.api.url}")
    private String externalApiUrl;

    public List<FxRate> getLatestFxRates() {
        String url = externalApiUrl + "/latest?from=USD";
        ResponseEntity<Map> response = new RestTemplate().getForEntity(url, Map.class);
        Map<String, Double> rates = (Map<String, Double>) response.getBody().get("rates");

        FxRate[] fxRates = new FxRate[rates.size()];
        int i = 0;
        for (Map.Entry<String, Double> entry : rates.entrySet()) {
            fxRates[i++] = new FxRate(LocalDate.now(), "USD", entry.getKey(), entry.getValue());
        }

        return Arrays.asList(fxRates);
    }

    public List<FxRate> getLatestFxRates(String targetCurrency) {
        String url = externalApiUrl + "/2024-03-18?to=USD,GBP,JPY,CZK";
        ResponseEntity<Map> response = new RestTemplate().getForEntity(url, Map.class);
        Map<String, Double> rates = (Map<String, Double>) response.getBody().get(targetCurrency);

        FxRate[] fxRates = new FxRate[1];
        fxRates[0] = new FxRate(LocalDate.of(2024, 3, 18), "USD", targetCurrency, rates.get("USD"));

        return Arrays.asList(fxRates);
    }
}