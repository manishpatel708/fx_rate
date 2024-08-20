package com.fx.rates.service;

import com.fx.rates.model.FxRate;
import com.fx.rates.repository.FxRateRepository;
import com.fx.rates.util.FxRateClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FxRateService {

    @Autowired
    private FxRateRepository fxRateRepository;

    @Autowired
    private FxRateClient fxRateClient;

    public Map<String, List<FxRate>> getFxRates() {
        // Fetch the latest exchange rates from the database
        List<FxRate> fxRates = fxRateRepository.findAll();

        // If the database is empty, fetch the latest rates from the external API
        if (fxRates.isEmpty()) {
            fxRates = fxRateClient.getLatestFxRates();
            fxRateRepository.saveAll(fxRates);
        }

        // Group the exchange rates by target currency
        Map<String, List<FxRate>> fxRatesMap = new HashMap<>();
        for (FxRate fxRate : fxRates) {
            fxRatesMap.computeIfAbsent(fxRate.getTargetCurrency(), k -> new ArrayList<>())
                    .add(fxRate);
        }

        return fxRatesMap;
    }

    public Map<LocalDate, FxRate> getFxRateTimeSeries(String targetCurrency) {
        // Fetch the 3 latest exchange rates for the given target currency from the database
        List<FxRate> fxRateTimeSeries = fxRateRepository.findTop3BySourceCurrencyAndTargetCurrencyOrderByDateDesc("USD", targetCurrency);

        // If the database doesn't have enough data, fetch the latest rates from the external API
        if (fxRateTimeSeries.size() < 3) {
            fxRateTimeSeries = fxRateClient.getLatestFxRates(targetCurrency);
            fxRateRepository.saveAll(fxRateTimeSeries);
        }

        // Create a map with the exchange rates indexed by date
        Map<LocalDate, FxRate> fxRateTimeSeriesMap = new HashMap<>();
        for (FxRate fxRate : fxRateTimeSeries) {
            fxRateTimeSeriesMap.put(fxRate.getDate(), fxRate);
        }
        return fxRateTimeSeriesMap;
    }
}