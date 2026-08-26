package com.example.patterns.bridge.common;

import java.time.LocalDate;

public record ReportPeriod(LocalDate start, LocalDate end) {

    public static ReportPeriod quarter(int year, int quarter) {
        LocalDate start = LocalDate.of(year, (quarter - 1) * 3 + 1, 1);
        return new ReportPeriod(start, start.plusMonths(3).minusDays(1));
    }

    @Override
    public String toString() {
        return start + " to " + end;
    }
}
