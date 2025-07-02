package com.iara.genero.generostats.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RawEPHRecord {
    private int year;
    private int quarter;
    private String gender;
    private double income;
    private String location;
}
