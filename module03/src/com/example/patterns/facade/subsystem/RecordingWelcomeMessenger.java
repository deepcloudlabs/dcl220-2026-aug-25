package com.example.patterns.facade.subsystem;

import com.example.patterns.facade.domain.AccountNumber;
import com.example.patterns.facade.domain.CustomerId;

import java.util.ArrayList;
import java.util.List;

public class RecordingWelcomeMessenger implements WelcomeMessenger {

    public record Welcome(CustomerId customer, AccountNumber account) {
    }

    private final List<Welcome> sent = new ArrayList<>();

    @Override
    public void sendWelcome(CustomerId customer, AccountNumber account) {
        sent.add(new Welcome(customer, account));
    }

    public List<Welcome> sentWelcomes() {
        return List.copyOf(sent);
    }

    public boolean welcomed(CustomerId customer) {
        return sent.stream().anyMatch(welcome -> welcome.customer().equals(customer));
    }
}
