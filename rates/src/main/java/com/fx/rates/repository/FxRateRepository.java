package com.fx.rates.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fx.rates.model.FxRate;

@Repository
public interface FxRateRepository extends JpaRepository<FxRate, Long> {
    List<FxRate> findByTargetCurrencyAndDateBetween(String targetCurrency, LocalDate startDate, LocalDate endDate);

    FxRate findFirstByTargetCurrencyOrderByDateDesc(String targetCurrency);
    List<FxRate> findTop3BySourceCurrencyAndTargetCurrencyOrderByDateDesc(String source, String target);
//    List<FxRate> findBySourceAndTargetCurrencyAndDateBetween(String source, String target, LocalDate startDate, LocalDate endDate);
}
