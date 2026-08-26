package com.example.patterns.facade.channel;

import com.example.patterns.facade.domain.Applicant;
import com.example.patterns.facade.domain.IdentityDocument;
import com.example.patterns.facade.domain.Selfie;

import java.time.LocalDate;

/** The raw data a channel receives (a web form, a JSON body, a CSV line). */
public record SignupRequest(String fullName,
                            LocalDate dateOfBirth,
                            String email,
                            boolean sanctionsListHit,
                            String documentNumber,
                            String issuingCountry,
                            LocalDate documentExpiry,
                            String selfieReference) {

    public Applicant toApplicant() {
        return new Applicant(fullName, dateOfBirth, email, sanctionsListHit);
    }

    public IdentityDocument toDocument() {
        return new IdentityDocument(documentNumber, issuingCountry, documentExpiry);
    }

    public Selfie toSelfie() {
        return new Selfie(selfieReference);
    }
}
