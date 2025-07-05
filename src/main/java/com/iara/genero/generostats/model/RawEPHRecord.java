package com.iara.genero.generostats.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Domain class representing a row from the EPH data file.
 * Contains household-level survey information used for gender-based statistics.
 */
@Data
@AllArgsConstructor
public class RawEPHRecord {
    private Integer year;
    private Integer quarter;
    private String gender;
    private Double income;
    private String location;
}
