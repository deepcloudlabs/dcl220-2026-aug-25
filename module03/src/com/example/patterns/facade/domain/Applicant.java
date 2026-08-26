package com.example.patterns.facade.domain;

import java.time.LocalDate;

/** Someone applying for an account. {@code sanctionsListHit} is a (simulated) screening flag. */
public record Applicant(String fullName, LocalDate dateOfBirth, String email, boolean sanctionsListHit) {
}
