package com.example.patterns.bridge.problem;

import com.example.patterns.bridge.common.ReportPeriod;
import com.example.patterns.bridge.data.StockLine;
import com.example.patterns.bridge.data.StockRepository;
import com.example.patterns.bridge.library.PdfDocument;
import com.example.patterns.bridge.library.PdfTable;

import java.time.LocalDate;
import java.util.List;

/** PROBLEM: the same PdfDocument calls as {@link SalesReportPdf}, with stock data. */
public class InventoryReportPdf extends Report {

    private final StockRepository stock;

    public InventoryReportPdf(StockRepository stock) {
        this.stock = stock;
    }

    @Override
    public byte[] generate(ReportPeriod period) {
        PdfDocument pdf = new PdfDocument();
        pdf.addTitle("Stock levels at " + period.end());
        PdfTable table = pdf.addTable(List.of("SKU", "Warehouse", "Quantity"));
        for (StockLine line : stock.levelsAt(period.end())) {
            table.addRow(List.of(line.sku(), line.warehouse(), String.valueOf(line.quantity())));
        }
        if (stock.hasShortages(period.end())) {
            pdf.addParagraph("Warning: some items are below reorder level.");
        }
        pdf.addParagraph("Generated " + LocalDate.now());
        return pdf.toBytes();
    }
}
