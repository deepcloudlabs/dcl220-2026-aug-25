package com.example.patterns.bridge.problem;

import com.example.patterns.bridge.common.ReportPeriod;
import com.example.patterns.bridge.data.SalesLine;
import com.example.patterns.bridge.data.SalesRepository;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

/** PROBLEM: the same query and rows a third time, as HTML tags. */
public class SalesReportHtml extends Report {

    private final SalesRepository sales;

    public SalesReportHtml(SalesRepository sales) {
        this.sales = sales;
    }

    @Override
    public byte[] generate(ReportPeriod period) {
        StringBuilder html = new StringBuilder("<html><body>");
        html.append("<h1>").append(escape("Sales by region, " + period)).append("</h1>");
        html.append("<table><tr><th>Region</th><th>Orders</th><th>Revenue</th></tr>");
        for (SalesLine line : sales.totalsByRegion(period)) {
            html.append("<tr><td>").append(escape(line.region())).append("</td><td>")
                .append(line.orders()).append("</td><td>")
                .append(line.revenue().toPlainString()).append("</td></tr>");
        }
        html.append("</table>");
        html.append("<p>Generated ").append(LocalDate.now()).append("</p>");
        html.append("</body></html>");
        return html.toString().getBytes(StandardCharsets.UTF_8);
    }

    // duplicated in InventoryReportHtml
    private static String escape(String text) {
        return text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}
