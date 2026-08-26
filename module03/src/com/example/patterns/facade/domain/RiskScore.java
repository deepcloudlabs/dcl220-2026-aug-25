package com.example.patterns.facade.domain;

/** Risk score from 0 (no risk) to 100. */
public record RiskScore(int value) {

    public static final int HIGH_RISK_THRESHOLD = 70;

    public boolean isHigh() {
        return value >= HIGH_RISK_THRESHOLD;
    }
}
