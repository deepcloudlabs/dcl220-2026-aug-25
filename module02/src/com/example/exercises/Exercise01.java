package com.example.exercises;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

interface Endpoint {
	URL getEndpoint();
}

interface Credentials {
	String getUsername();

	String getPassword();
}

interface DataChannel { // cohesive
	void send(byte[] data);

	byte[] receive();
}

interface Connection { // cohesive
	void handShake(Endpoint endpoint, Credentials credentials);

	void hangup();
}

interface Modem extends DataChannel, Connection {}

class SatellateModem implements DataChannel {

	@Override
	public void send(byte[] data) {
		System.out.println("[SatellateModem] Sending data...");
		
	}

	@Override
	public byte[] receive() {
		System.out.println("[SatellateModem] Receiving data...");
		return "new data...".getBytes();
	}
	
}
class AdslModem implements Modem {
	private Endpoint endpoint;

	@Override
	public void send(byte[] data) {
		System.out.println("[AdslModem] Sending data...");

	}

	@Override
	public byte[] receive() {
		System.out.println("[AdslModem] Receiving data...");
		return "new data...".getBytes();
	}

	@Override
	public void handShake(Endpoint endpoint, Credentials credentials) {
		System.out.println("[AdslModem] Handshaing with the endpoint %s".formatted(endpoint.getEndpoint()));
		System.out.println("[AdslModem] Using credentials...");
		this.endpoint = endpoint;
	}

	@Override
	public void hangup() {
		System.out.println("[AdslModem] Closing connection to the endpoint %s".formatted(endpoint.getEndpoint()));
	}

}

class FiberModem implements Modem {
	private Endpoint endpoint;

	@Override
	public void send(byte[] data) {
		System.out.println("[FiberModem] Sending data...");

	}

	@Override
	public byte[] receive() {
		System.out.println("[FiberModem] Receiving data...");
		return "new data...".getBytes();
	}

	@Override
	public void handShake(Endpoint endpoint, Credentials credentials) {
		System.out.println("[FiberModem] Handshaing with the endpoint %s".formatted(endpoint.getEndpoint()));
		System.out.println("[FiberModem] Using credentials...");
		this.endpoint = endpoint;
	}

	@Override
	public void hangup() {
		System.out.println("[FiberModem] Closing connection to the endpoint %s".formatted(endpoint.getEndpoint()));
	}

}

final class TelemetryPublisher {

	private final DataChannel channel;

	public TelemetryPublisher(DataChannel channel) {
		this.channel = channel;
	}

	public void publish(byte[] payload) {
		channel.send(payload);
	}
}

public class Exercise01 {

	public static void main(String[] args) {
		var dataChannel = new FiberModem();
		Credentials credentials = null;
		Endpoint endpoint = () -> {
			try {
				return URL.of(URI.create("https://www.service.net"),null);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			return null;
		};
		dataChannel.handShake(endpoint, credentials);
		var publisher = new TelemetryPublisher(dataChannel);
		publisher.publish("new data".getBytes());
	}

}
