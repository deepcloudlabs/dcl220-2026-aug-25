package com.example.exercises;

import java.util.List;

interface DiscountPolicy {
	double calculate(double amount);
}

interface DiscountLimiter {
	double limitDiscount(double discountAmount, double amount);
}

class HardDiscountLimiter implements DiscountLimiter {
	private final double discountHardLimitRate;
	
	public HardDiscountLimiter(double discountHardLimitRate) {
		this.discountHardLimitRate = discountHardLimitRate;
	}

	@Override
	public double limitDiscount(double discountAmount, double amount) {
		double discountAmountLimit = amount * discountHardLimitRate;
		if (discountAmount > discountAmountLimit)
			return discountAmountLimit;
		return discountAmount;
	}
	
}

class DiscountCalculator implements DiscountPolicy {
	private final List<DiscountPolicy> policies;
	private final DiscountLimiter discountLimiter;

	public DiscountCalculator(List<DiscountPolicy> policies, DiscountLimiter discountLimiter) {
		this.policies = policies;
		this.discountLimiter = discountLimiter;
	}

	@Override
	public double calculate(double amount) {
		if (policies.isEmpty())
			return 0.0;
		return discountLimiter.limitDiscount(
				policies.stream().map(policy -> policy.calculate(amount)).mapToDouble(Double::valueOf).sum(), amount);
	}

}

class VipDiscountPolicy implements DiscountPolicy {

	@Override
	public double calculate(double amount) {
		return amount * 0.2;
	}

}

class SeasonalEmployeeDiscountPolicy implements DiscountPolicy {
	private final List<DiscountPolicy> policies = List.of(new EmployeeDiscountPolicy(), new SeasonalDiscountPolicy());

	@Override
	public double calculate(double amount) {
		if (policies.isEmpty())
			return 0.0;
		return policies.stream().map(policy -> policy.calculate(amount)).mapToDouble(Double::valueOf).sum();
	}

}

class EmployeeDiscountPolicy implements DiscountPolicy {

	@Override
	public double calculate(double amount) {
		return amount * 0.05;
	}

}

class SeasonalDiscountPolicy implements DiscountPolicy {

	@Override
	public double calculate(double amount) {
		return amount * 0.10;
	}

}

public class Exercise02 {

	public static void main(String[] args) {
		var calculator = new DiscountCalculator(
				List.of(new SeasonalEmployeeDiscountPolicy(), new VipDiscountPolicy()),
				new HardDiscountLimiter(0.2));
		var amount = 10_000.0;
		System.out.println("amount: %f, discount: %f".formatted(amount, calculator.calculate(amount)));
	}

}
