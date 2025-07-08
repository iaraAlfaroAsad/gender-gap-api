package com.iara.genero.generostats.service;

import com.iara.genero.generostats.dto.GenderIncomeStats;
import com.iara.genero.generostats.model.RawEPHRecord;
import com.iara.genero.generostats.util.EphDataHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service layer containing business logic for statistical operations on EPH data.
 * Provides methods to compute income statistics segmented by gender and other criteria.
 */
@Service
public class StatisticsService {

    private final EphDataHolder ephDataHolder;

    public StatisticsService(EphDataHolder ephDataHolder) {
        this.ephDataHolder = ephDataHolder;
    }

    /**
     * Calculates the average income by gender and the income gap percentage.
     * @return GenderIncomeStats object containing average incomes and gap percentage
     */
    public GenderIncomeStats getAverageIncomeByGender () {
        List<RawEPHRecord> records = ephDataHolder.getRecords();
        List<Double> femaleIncomes = records.stream()
                .filter(record -> record.getGender().equalsIgnoreCase("Female")
                        && record.getIncome() != null)
                .map(RawEPHRecord::getIncome)
                .toList();

        List<Double> maleIncomes = records.stream()
                .filter(record -> record.getGender().equalsIgnoreCase("Male")
                        && record.getIncome() != null)
                .map(RawEPHRecord::getIncome)
                .toList();

        double femaleAvg = Math.round(average(femaleIncomes) * 100.0) / 100.0;
        double maleAvg = Math.round(average(maleIncomes) * 100.0) / 100.0;
        double gapPercentage = maleAvg != 0 ? ((maleAvg - femaleAvg) / maleAvg) * 100 : 0;
        gapPercentage = BigDecimal.valueOf(Math.abs(gapPercentage))
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
        return new GenderIncomeStats(maleAvg, femaleAvg, gapPercentage);
    }

    /**
     * Counts the number of households by gender of the head of household.
     * @return A map with gender as key and household count as value.
     */
    public Map<String, Long> getHouseholdCountByGender () {
        List<RawEPHRecord> records = ephDataHolder.getRecords();
        return records.stream()
                .filter(record -> record.getGender() != null)
                .collect(Collectors.groupingBy(
                        RawEPHRecord::getGender,
                        Collectors.counting()));
    }

    /**
     * Utility method to calculate the average of a list of Double values.
     * @param values the list of Double values to average
     * @return the average value, or 0.0 if the list is empty
     */
    private Double average(List<Double> values) {
        return values.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }
}
