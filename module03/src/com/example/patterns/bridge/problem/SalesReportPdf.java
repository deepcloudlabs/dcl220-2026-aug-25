package com.example.patterns.bridge.problem;

import com.example.patterns.bridge.common.ReportPeriod;
import com.example.patterns.bridge.data.SalesLine;
import com.example.patterns.bridge.data.SalesRepository;
import com.example.patterns.bridge.library.PdfDocument;
import com.example.patterns.bridge.library.PdfTable;

import java.time.LocalDate;
import java.util.List;

/** PROBLEM: sales content + PDF rendering, welded together in one class. */
public class SalesReportPdf extends Report {

    private final SalesRepository sales;

    public SalesReportPdf(SalesRepository sales) {
        this.sales = sales;
    }

    @Override
    public byte[] generate(ReportPeriod period) {
        PdfDocument pdf = new PdfDocument();
        pdf.addTitle("Sales by region, " + period);
        PdfTable table = pdf.addTable(List.of("Region", "Orders", "Revenue"));
        for (SalesLine line : sales.totalsByRegion(period)) {
            table.addRow(List.of(line.region(),
                                 String.valueOf(line.orders()),
                                 line.revenue().toPlainString()));
        }
        pdf.addParagraph("Generated " + LocalDate.now());
        return pdf.toBytes();
    }
}
