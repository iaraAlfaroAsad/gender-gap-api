package com.iara.genero.generostats.util;

import com.iara.genero.generostats.model.RawEPHRecord;
import com.iara.genero.generostats.service.EphFileReaderService;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * Loads EPH household data once and provides it to services that need access.
 */
@Component
public class EphDataHolder {

    private final List<RawEPHRecord> records;

    public EphDataHolder(EphFileReaderService readerService) {
        File file = new File("src/main/resources/data/eph/hogar_T422.txt");
        this.records = readerService.readRecords(file);
    }

    /**
     * Returns the preloaded EPH records.
     *
     * @return list of RawEPHRecord entries
     */
    public List<RawEPHRecord> getRecords() {
        return records;
    }
}
