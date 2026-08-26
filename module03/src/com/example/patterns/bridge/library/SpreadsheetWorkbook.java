package com.example.patterns.bridge.library;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Simulated spreadsheet library (stands in for Apache POI). Produces a readable pseudo-XLSX.
 */
public class SpreadsheetWorkbook {

    private final StringBuilder content = new StringBuilder("SIMULATED-XLSX\n");

    public Worksheet createSheet(String name) {
        content.append("[Sheet: ").append(name).append("]\n");
        return new Worksheet();
    }

    public byte[] toBytes() {
        return content.toString().getBytes(StandardCharsets.UTF_8);
    }

    /** A sheet; rows are appended in order. */
    public class Worksheet {

        public void appendRow(List<String> cells) {
            content.append(String.join("\t", cells)).append('\n');
        }
    }
}
