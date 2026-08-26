package com.example.patterns.facade.subsystem;

import java.time.LocalDate;

/** Bundles fresh in-memory implementations of the seven collaborators (for demos and tests). */
public record InMemorySubsystem(SimpleIdentityVerifier identity,
                                RuleBasedRiskEngine riskEngine,
                                InMemoryCustomerDirectory customers,
                                InMemoryAccountLedger ledger,
                                RecordingCardIssuer cardIssuer,
                                RecordingWelcomeMessenger messenger,
                                InMemoryComplianceLog compliance) {

    public static InMemorySubsystem create(LocalDate today) {
        return new InMemorySubsystem(
                new SimpleIdentityVerifier(today),
                new RuleBasedRiskEngine(),
                new InMemoryCustomerDirectory(),
                new InMemoryAccountLedger(),
                new RecordingCardIssuer(),
                new RecordingWelcomeMessenger(),
                new InMemoryComplianceLog());
    }
}
