package com.example.patterns.bridge.library;

import java.util.List;

/** A table inside a {@link PdfDocument}. */
public class PdfTable {

    private final StringBuilder content;

    PdfTable(StringBuilder content, List<String> headers) {
        this.content = content;
        content.append("/Table\n  ").append(String.join(" | ", headers)).append('\n');
    }

    public void addRow(List<String> cells) {
        content.append("  ").append(String.join(" | ", cells)).append('\n');
    }
}
