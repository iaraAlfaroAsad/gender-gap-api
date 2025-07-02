package com.iara.genero.generostats.controller;

import com.iara.genero.generostats.model.RawEPHRecord;
import com.iara.genero.generostats.service.EphFileReaderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

/**
 * REST controller that exposes raw records from the EPH data file.
 * This endpoint is used for testing file reading and basic API functionality.
 */
@RestController
@RequestMapping("/api/eph")
public class EphController {

    private final EphFileReaderService ephFileReaderService;

    public EphController(EphFileReaderService ephFileReaderService) {
        this.ephFileReaderService = ephFileReaderService;
    }

    /**
     * Sample endpoint that returns the first 10 parsed records from the EPH data file.
     *
     * @return List of RawEPHRecord entries parsed from the text file
     */
    @GetMapping("/sample")
    public List<RawEPHRecord> getSampleRecords() {
        File file = new File("src/main/resources/data/eph/hogar_T422.txt");

        // Read and return the first 10 entries
        return ephFileReaderService.readRecords(file)
                .stream()
                .limit(10)
                .toList();
    }
}
