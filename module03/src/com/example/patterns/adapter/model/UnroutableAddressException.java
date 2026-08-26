package com.example.patterns.adapter.model;

/** Thrown when an address cannot be placed on the map and therefore cannot be part of a route. */
public class UnroutableAddressException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final Address address;

    public UnroutableAddressException(Address address) {
        super("Address cannot be routed: " + address);
        this.address = address;
    }

    public UnroutableAddressException(Address address, Throwable cause) {
        super("Address cannot be routed: " + address, cause);
        this.address = address;
    }

    public Address address() {
        return address;
    }
}
