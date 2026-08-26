package com.example.patterns.facade.subsystem;

import com.example.patterns.facade.domain.CustomerId;
import com.example.patterns.facade.domain.RiskScore;

import java.util.ArrayList;
import java.util.List;

public class InMemoryComplianceLog implements ComplianceLog {

    public record Entry(String event, CustomerId customer, RiskScore risk) {
    }

    private final List<Entry> entries = new ArrayList<>();

    @Override
    public void record(String event, CustomerId customer, RiskScore risk) {
        entries.add(new Entry(event, customer, risk));
    }

    public List<Entry> entries() {
        return List.copyOf(entries);
    }

    public long count(String event) {
        return entries.stream().filter(entry -> entry.event().equals(event)).count();
    }
}
