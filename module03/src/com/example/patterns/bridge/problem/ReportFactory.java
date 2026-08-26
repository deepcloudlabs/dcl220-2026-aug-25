package com.example.patterns.bridge.problem;

import com.example.patterns.bridge.common.OutputFormat;
import com.example.patterns.bridge.common.ReportType;
import com.example.patterns.bridge.data.SalesRepository;
import com.example.patterns.bridge.data.StockRepository;

/**
 * PROBLEM: choosing a report means a nested switch with one branch per combination. The CSV
 * format that was added later has no branches at all - it would need two more classes first.
 */
public class ReportFactory {

    private final SalesRepository sales;
    private final StockRepository stock;

    public ReportFactory(SalesRepository sales, StockRepository stock) {
        this.sales = sales;
        this.stock = stock;
    }

    public Report create(ReportType type, OutputFormat format) {
        return switch (type) {
            case SALES -> switch (format) {
                case PDF -> new SalesReportPdf(sales);
                case EXCEL -> new SalesReportExcel(sales);
                case HTML -> new SalesReportHtml(sales);
                case CSV -> throw new UnsupportedOperationException(
                        "CSV output needs a new class SalesReportCsv first");
            };
            case INVENTORY -> switch (format) {
                case PDF -> new InventoryReportPdf(stock);
                case EXCEL -> new InventoryReportExcel(stock);
                case HTML -> new InventoryReportHtml(stock);
                case CSV -> throw new UnsupportedOperationException(
                        "CSV output needs a new class InventoryReportCsv first");
            };
        };
    }
}
