package com.example.patterns.bridge.problem;

import com.example.patterns.bridge.common.ReportPeriod;
import com.example.patterns.bridge.data.SalesLine;
import com.example.patterns.bridge.data.SalesRepository;
import com.example.patterns.bridge.library.SpreadsheetWorkbook;

import java.time.LocalDate;
import java.util.List;

/** PROBLEM: the same query and the same rows as {@link SalesReportPdf}, with a spreadsheet library. */
public class SalesReportExcel extends Report {

    private final SalesRepository sales;

    public SalesReportExcel(SalesRepository sales) {
        this.sales = sales;
    }

    @Override
    public byte[] generate(ReportPeriod period) {
        SpreadsheetWorkbook workbook = new SpreadsheetWorkbook();
        SpreadsheetWorkbook.Worksheet sheet = workbook.createSheet("Sales by region, " + period);
        sheet.appendRow(List.of("Region", "Orders", "Revenue"));
        for (SalesLine line : sales.totalsByRegion(period)) {
            sheet.appendRow(List.of(line.region(),
                                    String.valueOf(line.orders()),
                                    line.revenue().toPlainString()));
        }
        sheet.appendRow(List.of("Generated " + LocalDate.now()));
        return workbook.toBytes();
    }
}
