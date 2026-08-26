package com.example.patterns.bridge.problem;

import com.example.patterns.bridge.common.ReportPeriod;
import com.example.patterns.bridge.data.StockLine;
import com.example.patterns.bridge.data.StockRepository;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

/** PROBLEM: stock data as HTML - the sixth class, and the second copy of {@code escape}. */
public class InventoryReportHtml extends Report {

    private final StockRepository stock;

    public InventoryReportHtml(StockRepository stock) {
        this.stock = stock;
    }

    @Override
    public byte[] generate(ReportPeriod period) {
        StringBuilder html = new StringBuilder("<html><body>");
        html.append("<h1>").append(escape("Stock levels at " + period.end())).append("</h1>");
        html.append("<table><tr><th>SKU</th><th>Warehouse</th><th>Quantity</th></tr>");
        for (StockLine line : stock.levelsAt(period.end())) {
            html.append("<tr><td>").append(escape(line.sku())).append("</td><td>")
                .append(escape(line.warehouse())).append("</td><td>")
                .append(line.quantity()).append("</td></tr>");
        }
        html.append("</table>");
        if (stock.hasShortages(period.end())) {
            html.append("<p>Warning: some items are below reorder level.</p>");
        }
        html.append("<p>Generated ").append(LocalDate.now()).append("</p>");
        html.append("</body></html>");
        return html.toString().getBytes(StandardCharsets.UTF_8);
    }

    // duplicated from SalesReportHtml
    private static String escape(String text) {
        return text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}
