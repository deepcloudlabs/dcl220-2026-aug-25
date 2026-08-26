package com.example.patterns.facade.subsystem;

import com.example.patterns.facade.domain.Applicant;
import com.example.patterns.facade.domain.CustomerId;

public interface CustomerDirectory {
    CustomerId createCustomer(Applicant applicant);
}
