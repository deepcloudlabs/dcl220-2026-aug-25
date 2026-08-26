package com.example.exercises.lsp;

import java.math.BigDecimal;

public final class LspDemo {

    public static void main() {
        System.out.println("\n[LSP] Banking account capabilities");
        ProblematicAccount deposit = new ProblematicFixedTermDeposit("FTD-1", new BigDecimal("1000"));
        try {
            ProblematicTransferService.withdrawForTransfer(deposit, new BigDecimal("100"));
        } catch (UnsupportedOperationException ex) {
            System.out.println("  problem -> subtype breaks base withdrawal contract: " + ex.getMessage());
        }
    }

    // Problem: the base type promises withdraw(), but one subtype cannot honor it.
    static abstract class ProblematicAccount {
        private final String id;
        protected BigDecimal balance;
        ProblematicAccount(String id, BigDecimal balance) { this.id = id; this.balance = balance; }
        String id() { return id; }
        BigDecimal balance() { return balance; }
        abstract void withdraw(BigDecimal amount);
    }
    static final class ProblematicFixedTermDeposit extends ProblematicAccount {
        ProblematicFixedTermDeposit(String id, BigDecimal balance) { super(id, balance); }
        @Override void withdraw(BigDecimal amount) {
            throw new UnsupportedOperationException("withdrawal before maturity is forbidden");
        }
    }
    static final class ProblematicTransferService {
        static void withdrawForTransfer(ProblematicAccount account, BigDecimal amount) { account.withdraw(amount); }
    }

}
