package com.example.patterns.adapter.model;

/** Thrown when a geocoding provider fails (as opposed to simply not knowing an address). */
public class GeocodingException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public GeocodingException(String message, Throwable cause) {
        super(message, cause);
    }
}
