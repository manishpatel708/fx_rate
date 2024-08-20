package com.fx.rates.controller;

import com.fx.rates.model.FxRate;
import com.fx.rates.service.FxRateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FxRateController.class)
class FxRateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FxRateService fxRateService;

    @Test
    void getFxRates() throws Exception {
        Map<String, List<FxRate>> fxRatesMap = new HashMap<>();
        FxRate fxRate1 = new FxRate(1L, LocalDate.now(), "USD", "GBP", 0.77206);
        FxRate fxRate2 = new FxRate(2L, LocalDate.now(), "USD", "EUR", 0.91234);
        fxRatesMap.put("GBP", Arrays.asList(fxRate1));
        fxRatesMap.put("EUR", Arrays.asList(fxRate2));

        when(fxRateService.getFxRates()).thenReturn(fxRatesMap);

        mockMvc.perform(get("/fx")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.GBP[0].value").value(0.77206))
                .andExpect(jsonPath("$.EUR[0].value").value(0.91234));
    }

    // Add more tests for other endpoints and scenarios
}