package com.iara.genero.generostats.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenderIncomeStats {
    private double maleAverage;
    private double femaleAverage;
    private double gapPercentage;
}
