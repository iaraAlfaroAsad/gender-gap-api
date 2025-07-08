package com.iara.genero.generostats.service;

import com.iara.genero.generostats.dto.GenderIncomeStats;
import com.iara.genero.generostats.model.RawEPHRecord;
import com.iara.genero.generostats.util.EphDataHolder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Map;

import static com.iara.genero.generostats.util.TestUtilDataGenerator.getMockData;
import static org.mockito.Mockito.when;

public class StatisticsServiceTest {

    private StatisticsService statisticsService;
    private EphDataHolder ephDataHolder;

    @BeforeEach
    void setUp() {
        ephDataHolder = Mockito.mock(EphDataHolder.class);
        statisticsService = new StatisticsService(ephDataHolder);
    }

    @Test
    void testGetAverageIncomeByGender() {
        // Arrange
        List<RawEPHRecord> mockData = getMockData();
        when(ephDataHolder.getRecords()).thenReturn(mockData);

        // Act
        GenderIncomeStats stats = statisticsService.getAverageIncomeByGender();

        // Assert
        Assertions.assertEquals(375000.0, stats.getMaleAverage());
        Assertions.assertEquals(327500.0, stats.getFemaleAverage());
        Assertions.assertEquals(12.67, stats.getGapPercentage(), 0.01);
    }

    @Test
    void testGetHouseholdCountByGender() {
        // Arrange
        List<RawEPHRecord> mockData = getMockData();
        when(ephDataHolder.getRecords()).thenReturn(mockData);

        // Act
        Map<String, Long> countMap = statisticsService.getHouseholdCountByGender();

        // Assert
        Assertions.assertEquals(2, countMap.get("Male"));
        Assertions.assertEquals(2, countMap.get("Female"));
    }

    @Test
    void testGetAverageIncomeByGender_withEmptyList() {
        // Arrange
        when(ephDataHolder.getRecords()).thenReturn(List.of());

        // Act
        GenderIncomeStats stats = statisticsService.getAverageIncomeByGender();

        // Assert
        Assertions.assertEquals(0.0, stats.getMaleAverage());
        Assertions.assertEquals(0.0, stats.getFemaleAverage());
        Assertions.assertEquals(0.0, stats.getGapPercentage(), 0.01);
    }

    @Test
    void testGetAverageIncomeByGender_withNullIncomes() {
        // Arrange
        List<RawEPHRecord> mockData = List.of(
                new RawEPHRecord(2024, 4, "Male", null, "Gran Buenos Aires"),
                new RawEPHRecord(2024, 4, "Female", null, "Gran Buenos Aires")
        );
        when(ephDataHolder.getRecords()).thenReturn(mockData);

        // Act
        GenderIncomeStats stats = statisticsService.getAverageIncomeByGender();

        // Assert
        Assertions.assertEquals(0.0, stats.getMaleAverage());
        Assertions.assertEquals(0.0, stats.getFemaleAverage());
        Assertions.assertEquals(0.0, stats.getGapPercentage(), 0.01);
    }

    @Test
    void testGetHouseholdCountByGender_withEmptyList() {
        // Arrange
        when(ephDataHolder.getRecords()).thenReturn(List.of());

        // Act
        Map<String, Long> countMap = statisticsService.getHouseholdCountByGender();

        // Assert
        Assertions.assertTrue(countMap.isEmpty());
    }
}
