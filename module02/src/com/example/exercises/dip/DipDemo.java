package com.example.exercises.dip;

import java.math.BigDecimal;

public final class DipDemo {
	public static void main() {
		System.out.println("[DIP] Order checkout infrastructure");
		new ProblematicCheckoutService().checkout(new Order("ORD-1", "buyer@example.com", new BigDecimal("125.00")));
	}

	record Order(String id, String customerEmail, BigDecimal total) {
	}

	static final class ProblematicCheckoutService {
		void checkout(Order order) {
			LegacyPaymentClient payment = new LegacyPaymentClient();
			LegacyOrderStore store = new LegacyOrderStore();
			LegacyEmailClient email = new LegacyEmailClient();
			String paymentId = payment.charge(order.total());
			store.save(order, paymentId);
			email.send(order.customerEmail(), "Order confirmed: " + order.id());
		}
	}

	static final class LegacyPaymentClient {
		String charge(BigDecimal amount) {
			String id = "PAY-LEGACY";
			System.out.printf("legacy payment %s amount=%s%n", id, amount);
			return id;
		}
	}

	static final class LegacyOrderStore {
		void save(Order order, String paymentId) {
			System.out.printf("legacy order store=%s,%s%n", order, paymentId);
		}
	}

	static final class LegacyEmailClient {
		void send(String to, String text) {
			System.out.printf("legacy email to=%s%n", to);
		}
	}

}
