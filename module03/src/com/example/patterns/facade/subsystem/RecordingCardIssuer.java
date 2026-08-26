package com.example.patterns.facade.subsystem;

import com.example.patterns.facade.domain.AccountNumber;
import com.example.patterns.facade.domain.CustomerId;

import java.util.ArrayList;
import java.util.List;

public class RecordingCardIssuer implements CardIssuer {

    public record IssuedCard(CustomerId customer, AccountNumber account) {
    }

    private final List<IssuedCard> issued = new ArrayList<>();

    @Override
    public void issueDebitCard(CustomerId customer, AccountNumber account) {
        issued.add(new IssuedCard(customer, account));
    }

    public List<IssuedCard> issuedCards() {
        return List.copyOf(issued);
    }

    public boolean hasCardFor(CustomerId customer) {
        return issued.stream().anyMatch(card -> card.customer().equals(customer));
    }
}
