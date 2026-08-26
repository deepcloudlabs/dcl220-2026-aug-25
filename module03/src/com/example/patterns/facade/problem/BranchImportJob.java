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

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

/**
 * PROBLEM - the branch import tool: it creates the customer, opens the account and issues the
 * card <em>before</em> the risk check, so a rejected applicant still ends up with an account
 * and a debit card.
 */
public class BranchImportJob {

    private static final Currency EUR = Currency.getInstance("EUR");

    private final IdentityVerifier identity;
    private final RiskEngine riskEngine;
    private final CustomerDirectory customers;
    private final AccountLedger ledger;
    private final CardIssuer cardIssuer;
    private final WelcomeMessenger messenger;
    private final ComplianceLog compliance;

    public BranchImportJob(IdentityVerifier identity, RiskEngine riskEngine,
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

    public List<SignupResponse> run(List<SignupRequest> applications) {
        List<SignupResponse> responses = new ArrayList<>();
        for (SignupRequest request : applications) {
            Applicant applicant = request.toApplicant();
            VerificationResult verification =
                    identity.verify(request.toDocument(), request.toSelfie());
            if (!verification.passed()) {
                responses.add(SignupResponse.rejected("Identity not verified"));
                continue;
            }
            // wrong order: everything is created before the risk decision
            CustomerId customer = customers.createCustomer(applicant);
            AccountNumber account = ledger.openAccount(customer, EUR);
            cardIssuer.issueDebitCard(customer, account);
            RiskScore risk = riskEngine.score(applicant, verification);
            if (risk.isHigh()) {
                compliance.record("SIGNUP_REJECTED_RISK", customer, risk);
                responses.add(SignupResponse.rejected("Application declined"));
                continue;   // ...but the customer, the account and the card already exist
            }
            messenger.sendWelcome(customer, account);
            compliance.record("CUSTOMER_ONBOARDED", customer, risk);
            responses.add(SignupResponse.accepted(customer, account));
        }
        return responses;
    }
}
