package com.example.patterns.facade.problem;

import com.example.patterns.facade.channel.SignupRequest;
import com.example.patterns.facade.channel.SignupResponse;
import com.example.patterns.facade.domain.AccountNumber;
import com.example.patterns.facade.domain.Applicant;
import com.example.patterns.facade.domain.CustomerId;
import com.example.patterns.facade.domain.RiskScore;
import com.example.patterns.facade.domain.VerificationResult;
import com.example.patterns.facade.subsystem.AccountLedger;
import com.example.patterns.facade.subsystem.CardIssuer;
import com.example.patterns.facade.subsystem.ComplianceLog;
import com.example.patterns.facade.subsystem.CustomerDirectory;
import com.example.patterns.facade.subsystem.IdentityVerifier;
import com.example.patterns.facade.subsystem.RiskEngine;
import com.example.patterns.facade.subsystem.WelcomeMessenger;

import java.util.Currency;

/**
 * PROBLEM - the mobile channel: a copy of the web workflow that never writes the compliance
 * entry when an application is rejected for risk. A regulator would call that a defect.
 */
public class MobileSignupHandler {

    private static final Currency EUR = Currency.getInstance("EUR");

    private final IdentityVerifier identity;
    private final RiskEngine riskEngine;
    private final CustomerDirectory customers;
    private final AccountLedger ledger;
    private final CardIssuer cardIssuer;
    private final WelcomeMessenger messenger;
    private final ComplianceLog compliance;

    public MobileSignupHandler(IdentityVerifier identity, RiskEngine riskEngine,
                               CustomerDirectory customers, AccountLedger ledger,
                               CardIssuer cardIssuer, WelcomeMessenger messenger,
                               ComplianceLog compliance) {
        this.identity = identity;
        this.riskEngine = riskEngine;
        this.customers = customers;
        this.ledger = ledger;
        this.cardIssuer = cardIssuer;
        this.messenger = messenger;
        this.compliance = compliance;
    }

    public SignupResponse handle(SignupRequest request) {
        Applicant applicant = request.toApplicant();
        VerificationResult verification =
                identity.verify(request.toDocument(), request.toSelfie());
        if (!verification.passed()) {
            return SignupResponse.rejected("We could not verify your identity");
        }
        RiskScore risk = riskEngine.score(applicant, verification);
        if (risk.isHigh()) {
            // the compliance.record(...) call is missing here
            return SignupResponse.rejected("We cannot open an account for you at this time");
        }
        CustomerId customer = customers.createCustomer(applicant);
        AccountNumber account = ledger.openAccount(customer, EUR);
        cardIssuer.issueDebitCard(customer, account);
        messenger.sendWelcome(customer, account);
        compliance.record("CUSTOMER_ONBOARDED", customer, risk);
        return SignupResponse.accepted(customer, account);
    }
}
