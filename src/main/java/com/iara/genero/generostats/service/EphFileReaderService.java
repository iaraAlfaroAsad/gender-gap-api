package com.iara.genero.generostats.service;

import com.iara.genero.generostats.model.RawEPHRecord;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Service responsible for reading raw EPH household data files (.txt).
 * Parses each line into structured Java objects (RawEPHRecord).
 */
@Service
public class EphFileReaderService {


    // Map of agglomerate codes to human-readable city/region names.
    private final Map<String, String> aglomeradoMap = Map.ofEntries(
            Map.entry("32", "Gran Buenos Aires"),
            Map.entry("08", "Gran Córdoba"),
            Map.entry("10", "Gran Mendoza"),
            Map.entry("04", "Gran Rosario"),
            Map.entry("30", "Gran Tucumán"),
            Map.entry("02", "Gran La Plata"),
            Map.entry("42", "Gran Salta"),
            Map.entry("34", "Neuquén - Plottier")
    );

    /**
     * Reads and parses a semicolon-delimited EPH household data file.
     * Converts each line into a RawEPHRecord object.
     * @param ephFile the input file (e.g., hogar_T422.txt)
     * @return a list of parsed EPH records
     */
    public List<RawEPHRecord> readRecords(File ephFile) {
        List<RawEPHRecord> records = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(ephFile),
                StandardCharsets.UTF_8))) {
            String headerLine = br.readLine();
            String[] headers = headerLine.split(";");

            int yearIdx = findIndex(headers, "ANO4");
            int quarterIdx = findIndex(headers, "TRIMESTRE");
            int incomeIdx = findIndex(headers, "ITF");
            int sexIdx = findIndex(headers, "V2");
            int agloIdx = findIndex(headers, "AGLOMERADO");

            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(";");
                try {
                    int year = Integer.parseInt(fields[yearIdx]);
                    int quarter = Integer.parseInt(fields[quarterIdx]);
                    double income = Double.parseDouble(fields[incomeIdx].replace(",", "."));
                    int sexCode = Integer.parseInt(fields[sexIdx]);
                    String gender = sexCode == 1 ? "Male" : sexCode == 2 ? "Female" : "Other";
                    String agglomerate = aglomeradoMap.getOrDefault(fields[agloIdx], "Otro");

                    records.add(new RawEPHRecord(year, quarter, gender, income, agglomerate));
                } catch (Exception e) {
                    System.err.println("Error parsing line: " + line);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    /**
     * Helper method to find the index of a column in the header.
     * @param headers the array of header names
     * @param columnName the name of the column to find
     * @return the index of the column
     */
    private int findIndex(String[] headers, String columnName) {
        for (int i = 0; i < headers.length; i++) {
            if (headers[i].trim().equalsIgnoreCase(columnName)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Column " + columnName + " not found in headers.");
    }
}
