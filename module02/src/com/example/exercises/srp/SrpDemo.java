package com.example.exercises.srp;

import java.math.BigDecimal;
import java.util.List;

public final class SrpDemo {

	public static void main() {
		System.out.println("\n[SRP] Invoice processing");
		ProblematicInvoiceService bad = new ProblematicInvoiceService();
		bad.process(new Invoice("INV-1001", "customer@example.com",
				List.of(new InvoiceLine("Consulting", new BigDecimal("1200.00")),
						new InvoiceLine("Support", new BigDecimal("300.00")))));
	}

	public record Invoice(String id, String customerEmail, List<InvoiceLine> lines) {
	}

	public record InvoiceLine(String description, BigDecimal amount) {
	}

	static final class ProblematicInvoiceService {
		public void process(Invoice invoice) {
			BigDecimal total = invoice.lines().stream().map(InvoiceLine::amount).reduce(BigDecimal.ZERO,
					BigDecimal::add);
			System.out.printf("SAVE invoice=%s total=%s%n", invoice.id(), total);
			System.out.printf("EMAIL %s total=%s%n", invoice.customerEmail(), total);
			System.out.printf("AUDIT invoice.processed id=%s%n", invoice.id());
		}
	}

}
