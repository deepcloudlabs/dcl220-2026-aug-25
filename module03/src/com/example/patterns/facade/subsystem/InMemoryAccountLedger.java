package com.example.patterns.facade.subsystem;

import com.example.patterns.facade.domain.AccountNumber;
import com.example.patterns.facade.domain.CustomerId;

import java.util.Collections;
import java.util.Currency;
import java.util.LinkedHashMap;
import java.util.Map;

public class InMemoryAccountLedger implements AccountLedger {

    private final Map<AccountNumber, CustomerId> accounts = new LinkedHashMap<>();
    private int sequence = 1000;

    @Override
    public AccountNumber openAccount(CustomerId customer, Currency currency) {
        AccountNumber number = new AccountNumber("ACC-" + currency.getCurrencyCode() + "-" + (++sequence));
        accounts.put(number, customer);
        return number;
    }

    public Map<AccountNumber, CustomerId> accounts() {
        return Collections.unmodifiableMap(accounts);
    }

    public boolean hasAccountFor(CustomerId customer) {
        return accounts.containsValue(customer);
    }
}
