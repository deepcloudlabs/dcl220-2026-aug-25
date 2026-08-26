package com.example.patterns.facade.subsystem;

import com.example.patterns.facade.domain.AccountNumber;
import com.example.patterns.facade.domain.CustomerId;

public interface WelcomeMessenger {
    void sendWelcome(CustomerId customer, AccountNumber account);
}
