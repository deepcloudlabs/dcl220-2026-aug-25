package com.example.patterns.facade.subsystem;

import com.example.patterns.facade.domain.Applicant;
import com.example.patterns.facade.domain.RiskScore;
import com.example.patterns.facade.domain.VerificationResult;

import java.util.Locale;
import java.util.Set;

/** A deliberately simple scoring rule set. */
public class RuleBasedRiskEngine implements RiskEngine {

    private static final Set<String> DISPOSABLE_MAIL_DOMAINS =
            Set.of("mailinator.com", "tempmail.example", "throwaway.example");

    @Override
    public RiskScore score(Applicant applicant, VerificationResult verification) {
        int score = 15;
        if (applicant.sanctionsListHit()) {
            score += 60;
        }
        String email = applicant.email() == null ? "" : applicant.email();
        String domain = email.substring(email.indexOf('@') + 1).toLowerCase(Locale.ROOT);
        if (DISPOSABLE_MAIL_DOMAINS.contains(domain)) {
            score += 15;
        }
        if (!verification.passed()) {
            score += 25;
        }
        return new RiskScore(Math.min(score, 100));
    }
}
