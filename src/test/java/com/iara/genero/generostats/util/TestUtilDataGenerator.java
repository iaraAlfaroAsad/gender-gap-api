package com.iara.genero.generostats.util;

import com.iara.genero.generostats.model.RawEPHRecord;

import java.util.List;

public class TestUtilDataGenerator {

    public static List<RawEPHRecord> getMockData() {
        return List.of(
                new RawEPHRecord(2024, 4, "Male", 300000.0, "Gran Buenos Aires"),
                new RawEPHRecord(2024, 4, "Female", 255000.0, "Gran Buenos Aires"),
                new RawEPHRecord(2024, 4, "Male", 450000.0, "Gran Buenos Aires"),
                new RawEPHRecord(2024, 4, "Female", 400000.0, "Gran Buenos Aires"));
    }
}
