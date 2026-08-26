package com.example.patterns.adapter.vendor;

/** Checked exception of the (simulated) NaviCorp SDK. */
public class NaviCorpException extends Exception {

    private static final long serialVersionUID = 1L;

    public NaviCorpException(String message) {
        super(message);
    }
}
