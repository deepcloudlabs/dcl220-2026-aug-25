package com.example.exercises.ocp;

import java.math.BigDecimal;

public final class OcpDemo {
	public static void main() {
		System.out.println("\n[OCP] Shipping fee policies");
		ProblematicShippingCalculator bad = new ProblematicShippingCalculator();
		System.out.println("EXPRESS fee=" + bad.calculate("EXPRESS", new BigDecimal("120")));
	}

	enum ShippingMethod {
		STANDARD, EXPRESS, OVERNIGHT
	}

	static final class ProblematicShippingCalculator {
		BigDecimal calculate(String method, BigDecimal orderTotal) {
			return switch (method) {
			case "STANDARD" -> new BigDecimal("5.00");
			case "EXPRESS" -> orderTotal.multiply(new BigDecimal("0.10"));
			case "OVERNIGHT" -> new BigDecimal("30.00");
			default -> throw new IllegalArgumentException("Unexpected value: " + method);
			};
		}
	}

}
