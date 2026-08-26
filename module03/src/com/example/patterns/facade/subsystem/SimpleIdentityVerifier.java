package com.example.patterns.facade.subsystem;

import com.example.patterns.facade.domain.IdentityDocument;
import com.example.patterns.facade.domain.Selfie;
import com.example.patterns.facade.domain.VerificationResult;

import java.time.LocalDate;

/** Accepts any unexpired document accompanied by a selfie. */
public class SimpleIdentityVerifier implements IdentityVerifier {

    private final LocalDate today;

    public SimpleIdentityVerifier(LocalDate today) {
        this.today = today;
    }

    @Override
    public VerificationResult verify(IdentityDocument document, Selfie selfie) {
        if (document == null || document.expiryDate().isBefore(today)) {
            return VerificationResult.failed("identity document missing or expired");
        }
        if (selfie == null || selfie.imageReference() == null || selfie.imageReference().isBlank()) {
            return VerificationResult.failed("selfie missing");
        }
        return VerificationResult.ok();
    }
}
