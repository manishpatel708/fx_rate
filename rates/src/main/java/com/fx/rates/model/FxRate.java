package com.fx.rates.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class FxRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String sourceCurrency;
    private String targetCurrency;
    private double exchangeRate;

    public FxRate(LocalDate date, String source, String target, double value) {
        this.date = date;
        this.sourceCurrency = source;
        this.targetCurrency = target;
        this.exchangeRate = value;
    }

    public FxRate() {

    }
}
