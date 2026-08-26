package com.example.patterns.bridge.data;

import java.time.LocalDate;
import java.util.List;

public interface StockRepository {

    List<StockLine> levelsAt(LocalDate date);

    boolean hasShortages(LocalDate date);
}
