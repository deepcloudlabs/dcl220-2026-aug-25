package com.example.patterns.facade.subsystem;

import com.example.patterns.facade.domain.AccountNumber;
import com.example.patterns.facade.domain.CustomerId;

public interface CardIssuer {
    void issueDebitCard(CustomerId customer, AccountNumber account);
}
