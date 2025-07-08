package com.iara.genero.generostats.controller;

import com.iara.genero.generostats.model.RawEPHRecord;
import com.iara.genero.generostats.util.EphDataHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.iara.genero.generostats.util.TestUtilDataGenerator.getMockData;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StatisticsControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EphDataHolder ephDataHolder;

    @BeforeEach
    void setUp() {
        List<RawEPHRecord> mockData = getMockData();
        ephDataHolder.getRecords().clear();
        ephDataHolder.getRecords().addAll(mockData);
    }

    @Test
    void testGetAverageIncomeEndpoint_returnsCorrectJson() throws Exception {
        mockMvc.perform(get("/api/eph/stats/average-income"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.maleAverage").value(375000.0))
            .andExpect(jsonPath("$.femaleAverage").value(327500.0))
            .andExpect(jsonPath("$.gapPercentage").value(12.67));
    }

    @Test
    void testGetHouseholdCountEndpoint_returnsCorrectJson() throws Exception {
        mockMvc.perform(get("/api/eph/stats/household-count"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Male").value(2))
                .andExpect(jsonPath("$.Female").value(2));
    }
}
