package com.example.exercises.isp;

public final class IspDemo {
	public static void main() {
		System.out.println("\n[ISP] Warehouse peripherals");
		ProblematicWarehouseDevice scanner = new ProblematicBarcodeScanner();
		System.out.println("scanned=" + scanner.scanBarcode());
		try {
			scanner.printLabel("BOX-42");
		} catch (UnsupportedOperationException ex) {
			System.out.println("fat interface forced unsupported method: " + ex.getMessage());
		}

	}

	interface ProblematicWarehouseDevice {
		String scanBarcode();

		void printLabel(String text);

		double weighKg();
	}

	static final class ProblematicBarcodeScanner implements ProblematicWarehouseDevice {
		public String scanBarcode() {
			return "SKU-123";
		}

		public void printLabel(String text) {
			throw new UnsupportedOperationException("scanner cannot print");
		}

		public double weighKg() {
			throw new UnsupportedOperationException("scanner cannot weigh");
		}
	}

}
