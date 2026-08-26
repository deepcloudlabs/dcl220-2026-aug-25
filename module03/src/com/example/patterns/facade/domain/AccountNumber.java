package com.example.patterns.facade.domain;

public record AccountNumber(String value) {

    @Override
    public String toString() {
        return value;
    }
}
