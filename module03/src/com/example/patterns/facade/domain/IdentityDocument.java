package com.example.patterns.facade.domain;

import java.time.LocalDate;

public record IdentityDocument(String number, String issuingCountry, LocalDate expiryDate) {
}
