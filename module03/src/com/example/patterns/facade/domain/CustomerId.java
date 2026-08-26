package com.example.patterns.facade.domain;

public record CustomerId(String value) {

    @Override
    public String toString() {
        return value;
    }
}
