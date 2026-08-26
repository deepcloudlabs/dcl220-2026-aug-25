package com.example.patterns.facade.channel;

import com.example.patterns.facade.domain.AccountNumber;
import com.example.patterns.facade.domain.CustomerId;

/** What a channel answers to the applicant. */
public record SignupResponse(boolean accepted, String message, CustomerId customer, AccountNumber account) {

    public static SignupResponse accepted(CustomerId customer, AccountNumber account) {
        return new SignupResponse(true, "Welcome aboard", customer, account);
    }

    public static SignupResponse rejected(String message) {
        return new SignupResponse(false, message, null, null);
    }

    @Override
    public String toString() {
        return accepted ? "accepted (" + customer + ", " + account + ")" : "rejected: " + message;
    }
}
