package com.example.patterns.facade.subsystem;

import com.example.patterns.facade.domain.IdentityDocument;
import com.example.patterns.facade.domain.Selfie;
import com.example.patterns.facade.domain.VerificationResult;

public interface IdentityVerifier {
    VerificationResult verify(IdentityDocument document, Selfie selfie);
}
