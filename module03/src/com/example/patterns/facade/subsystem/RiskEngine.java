package com.example.patterns.facade.subsystem;

import com.example.patterns.facade.domain.Applicant;
import com.example.patterns.facade.domain.RiskScore;
import com.example.patterns.facade.domain.VerificationResult;

public interface RiskEngine {
    RiskScore score(Applicant applicant, VerificationResult verification);
}
