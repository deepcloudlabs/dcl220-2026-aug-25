package com.example.patterns.bridge.data;

import java.time.LocalDate;
import java.util.List;

/** Fixed sample data; a real implementation would query the warehouse system. */
public class InMemoryStockRepository implements StockRepository {

    private static final int REORDER_LEVEL = 10;

    private static final List<StockLine> LINES = List.of(
            new StockLine("DSK-1", "Gebze", 42),
            new StockLine("CHR-1", "Gebze", 7),
            new StockLine("LMP-1", "Izmir", 120));

    @Override
    public List<StockLine> levelsAt(LocalDate date) {
        return LINES;
    }

    @Override
    public boolean hasShortages(LocalDate date) {
        return LINES.stream().anyMatch(line -> line.quantity() < REORDER_LEVEL);
    }
}
