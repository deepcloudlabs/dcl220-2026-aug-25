package com.example.patterns.facade.domain;

public record VerificationResult(boolean passed, String details) {

    public static VerificationResult ok() {
        return new VerificationResult(true, "verified");
    }

    public static VerificationResult failed(String details) {
        return new VerificationResult(false, details);
    }
}
