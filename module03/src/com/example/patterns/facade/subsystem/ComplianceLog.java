package com.example.patterns.facade.subsystem;

import com.example.patterns.facade.domain.CustomerId;
import com.example.patterns.facade.domain.RiskScore;

public interface ComplianceLog {
    /** Records an event; {@code customer} may be null when no customer record exists yet. */
    void record(String event, CustomerId customer, RiskScore risk);
}
