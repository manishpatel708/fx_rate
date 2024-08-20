package com.fx.rates.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fx.rates.model.FxRate;
import com.fx.rates.service.FxRateService;

@RestController
@RequestMapping("/fx")
public class FxRateController {

    @Autowired
    FxRateService fxRateService;

    @GetMapping
    public ResponseEntity<Map<String, List<FxRate>>> getFxRates() {
        Map<String, List<FxRate>> fxRates = fxRateService.getFxRates();
        return ResponseEntity.ok(fxRates);
    }

    @GetMapping("/{targetCurrency}")
    public ResponseEntity<Map<LocalDate, FxRate>> getFxRateTimeSeries(@PathVariable String targetCurrency) {
        Map<LocalDate, FxRate> fxRateTimeSeries = fxRateService.getFxRateTimeSeries(targetCurrency);
        return ResponseEntity.ok(fxRateTimeSeries);
    }
}