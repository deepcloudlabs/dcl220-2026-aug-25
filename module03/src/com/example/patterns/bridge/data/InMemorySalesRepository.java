package com.example.patterns.bridge.data;

import com.example.patterns.bridge.common.ReportPeriod;

import java.math.BigDecimal;
import java.util.List;

/** Fixed sample data; a real implementation would query a database. */
public class InMemorySalesRepository implements SalesRepository {

    @Override
    public List<SalesLine> totalsByRegion(ReportPeriod period) {
        return List.of(
                new SalesLine("Marmara", 1240, new BigDecimal("184320.50")),
                new SalesLine("Aegean", 615, new BigDecimal("90115.00")),
                new SalesLine("Central Anatolia", 402, new BigDecimal("61780.25")));
    }
}
