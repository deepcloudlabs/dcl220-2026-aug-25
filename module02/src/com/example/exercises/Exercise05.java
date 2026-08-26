package com.example.exercises;

record Order(double total) {
}

interface PaymentGatewayService {
	public void charge(double amount);
}

interface OrderRepository {
	public void save(Order order);
}

class OracleOrderRepository implements OrderRepository {

	@Override
	public void save(Order order) {
		System.out.println("Saving order [%s] in oracle db".formatted(order));
	}
	
}

class MongoOrderRepository implements OrderRepository {

	@Override
	public void save(Order order) {
		System.out.println("Saving order [%s] in mongodb".formatted(order));
	}
	
}

class StripePaymentGateway implements PaymentGatewayService {
	@Override
	public void charge(double amount) {
		System.out.println("StripePaymentGateway receiving the payment: %f".formatted(amount));
	}
}

class GarantiBBVAPaymentGateway implements PaymentGatewayService {
	@Override
	public void charge(double amount) {
		System.out.println("GarantiBBVAPaymentGateway receiving the payment: %f".formatted(amount));
	}
}

class PayPalPaymentGateway implements PaymentGatewayService {
	@Override
	public void charge(double amount) {
		System.out.println("PayPalPaymentGateway receiving the payment: %f".formatted(amount));
	}
}

// Low-level Business
class OrderService {
	// High-level business
	private final PaymentGatewayService paymentGatewayService;
	private final OrderRepository orderRepository;

	public OrderService(PaymentGatewayService paymentGatewayService, OrderRepository orderRepository) {
		this.paymentGatewayService = paymentGatewayService;
		this.orderRepository = orderRepository;
	}

	public void receiveOrder(Order order) {
		paymentGatewayService.charge(order.total());
		orderRepository.save(order);
		System.out.println("Order is accepted: %s".formatted(order));
	}

}

public class Exercise05 {

	public static void main(String[] args) {
		var order = new Order(100.0);
		// OCP + DIP
		// var paymentGatewayService = new StripePaymentGateway();
		// var paymentGatewayService = new GarantiBBVAPaymentGateway();
		var paymentGatewayService = new PayPalPaymentGateway();
		//var orderrepository = new OracleOrderRepository();
		var orderrepository = new MongoOrderRepository();
		var orderService = new OrderService(paymentGatewayService,orderrepository);
		orderService.receiveOrder(order);
	}

}
