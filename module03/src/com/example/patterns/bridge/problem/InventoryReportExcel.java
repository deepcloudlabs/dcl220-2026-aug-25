package com.example.patterns.bridge.problem;

import com.example.patterns.bridge.common.ReportPeriod;
import com.example.patterns.bridge.data.StockLine;
import com.example.patterns.bridge.data.StockRepository;
import com.example.patterns.bridge.library.SpreadsheetWorkbook;

import java.time.LocalDate;
import java.util.List;

/** PROBLEM: stock data with the spreadsheet library - the fifth copy of one of two things. */
public class InventoryReportExcel extends Report {

    private final StockRepository stock;

    public InventoryReportExcel(StockRepository stock) {
        this.stock = stock;
    }

    @Override
    public byte[] generate(ReportPeriod period) {
        SpreadsheetWorkbook workbook = new SpreadsheetWorkbook();
        SpreadsheetWorkbook.Worksheet sheet = workbook.createSheet("Stock levels at " + period.end());
        sheet.appendRow(List.of("SKU", "Warehouse", "Quantity"));
        for (StockLine line : stock.levelsAt(period.end())) {
            sheet.appendRow(List.of(line.sku(), line.warehouse(), String.valueOf(line.quantity())));
        }
        if (stock.hasShortages(period.end())) {
            sheet.appendRow(List.of("Warning: some items are below reorder level."));
        }
        sheet.appendRow(List.of("Generated " + LocalDate.now()));
        return workbook.toBytes();
    }
}
