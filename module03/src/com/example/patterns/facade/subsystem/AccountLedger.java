package com.example.patterns.facade.subsystem;

import com.example.patterns.facade.domain.AccountNumber;
import com.example.patterns.facade.domain.CustomerId;

import java.util.Currency;

public interface AccountLedger {
    AccountNumber openAccount(CustomerId customer, Currency currency);
}
