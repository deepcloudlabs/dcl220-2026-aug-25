package com.example.patterns.adapter.model;

import java.util.Locale;

/** A postal address as the application understands it. */
public record Address(String street, String postalCode, String city, String countryCode) {

    public Address {
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("street is required");
        }
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("city is required");
        }
        if (countryCode == null || countryCode.length() != 2) {
            throw new IllegalArgumentException("countryCode must be an ISO 3166-1 alpha-2 code");
        }
        countryCode = countryCode.toUpperCase(Locale.ROOT);
    }

    @Override
    public String toString() {
        return street + ", " + postalCode + " " + city + " (" + countryCode + ")";
    }
}
