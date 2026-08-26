package com.example.patterns.bridge.library;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Simulated PDF library (stands in for iText, PDFBox and friends). It produces a readable
 * pseudo-PDF so the project has no external dependencies; its API shape is what matters.
 */
public class PdfDocument {

    private final StringBuilder content = new StringBuilder("%PDF-SIM-1.0\n");

    public void addTitle(String text) {
        content.append("/Title (").append(text).append(")\n");
    }

    public PdfTable addTable(List<String> headers) {
        return new PdfTable(content, headers);
    }

    public void addParagraph(String text) {
        content.append("/Paragraph (").append(text).append(")\n");
    }

    public byte[] toBytes() {
        return (content.toString() + "%%EOF\n").getBytes(StandardCharsets.UTF_8);
    }
}
