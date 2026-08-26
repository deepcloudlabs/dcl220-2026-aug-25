package com.example.patterns.facade.subsystem;

import com.example.patterns.facade.domain.Applicant;
import com.example.patterns.facade.domain.CustomerId;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class InMemoryCustomerDirectory implements CustomerDirectory {

    private final Map<CustomerId, Applicant> customers = new LinkedHashMap<>();
    private int sequence = 0;

    @Override
    public CustomerId createCustomer(Applicant applicant) {
        CustomerId id = new CustomerId(String.format("CUST-%04d", ++sequence));
        customers.put(id, applicant);
        return id;
    }

    public Map<CustomerId, Applicant> customers() {
        return Collections.unmodifiableMap(customers);
    }
}
