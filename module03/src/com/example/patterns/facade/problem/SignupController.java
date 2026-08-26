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
 * PROBLEM - the web channel (a {@code @RestController} in the real system). It knows all seven
 * subsystem classes and choreographs them by hand. This copy of the workflow happens to be
 * correct; the other three channels are not.
 */
public class SignupController {

    private static final Currency EUR = Currency.getInstance("EUR");

    private final IdentityVerifier identity;
    private final RiskEngine riskEngine;
    private final CustomerDirectory customers;
    private final AccountLedger ledger;
    private final CardIssuer cardIssuer;
    private final WelcomeMessenger messenger;
    private final ComplianceLog compliance;

    public SignupController(IdentityVerifier identity, RiskEngine riskEngine,
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

    /** {@code @PostMapping("/signup")} in the real system. */
    public SignupResponse signup(SignupRequest request) {
        Applicant applicant = request.toApplicant();
        VerificationResult verification =
                identity.verify(request.toDocument(), request.toSelfie());
        if (!verification.passed()) {
            return SignupResponse.rejected("Identity not verified");
        }
        RiskScore risk = riskEngine.score(applicant, verification);
        if (risk.isHigh()) {
            compliance.record("SIGNUP_REJECTED_RISK", null, risk);
            return SignupResponse.rejected("Application declined");
        }
        CustomerId customer = customers.createCustomer(applicant);
        AccountNumber account = ledger.openAccount(customer, EUR);
        cardIssuer.issueDebitCard(customer, account);
        messenger.sendWelcome(customer, account);
        compliance.record("CUSTOMER_ONBOARDED", customer, risk);
        return SignupResponse.accepted(customer, account);
    }
}
