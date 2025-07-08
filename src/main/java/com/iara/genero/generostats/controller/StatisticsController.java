package com.iara.genero.generostats.controller;

import com.iara.genero.generostats.service.StatisticsService;
import com.iara.genero.generostats.dto.GenderIncomeStats;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * REST controller for handling statistical queries related to EPH data.
 */
@RestController
@RequestMapping("/api/eph/stats")
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    /**
     * Returns the average income by gender and the gap percentage.
     * @return GenderIncomeStats object containing average incomes and gap percentage
     */
    @GetMapping("/average-income")
    public GenderIncomeStats getAverageIncome() {
        return statisticsService.getAverageIncomeByGender();
    }

    /**
     * Returns the count of households grouped by gender.
     * @return A map with the count of households
     */
    @GetMapping("/household-count")
    public Map<String, Long> getHouseholdCountByGender() {
        return statisticsService.getHouseholdCountByGender();
    }
}
