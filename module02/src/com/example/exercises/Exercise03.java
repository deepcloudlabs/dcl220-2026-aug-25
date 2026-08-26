package com.example.exercises;

interface Shape {
	public abstract double area();

	public abstract double circumference();
	
	public abstract Shape resize(double scale);
}

record Rectangle(double width, double height) implements Shape {
	public double area() {
		return this.width * this.height;
	}

	public double circumference() {
		return 2.0 * (this.width * this.height);
	}

	@Override
	public Shape resize(double scale) {
		return new Rectangle(this.width * scale,this.height*scale);
	}

}

record Square(double edge) implements Shape {

	public double area() {
		return this.edge * this.edge;
	}

	public double circumference() {
		return 4.0 * this.edge;
	}

	@Override
	public Shape resize(double scale) {
		return new Square(scale * edge);
	}

}

public class Exercise03 {

	public static void main(String[] args) {
		// var rectangle = new Square(50);
		var rectangle = new Rectangle(10,50);
		play(rectangle,2000);
	}

	// Generic -> Shape (abstraction -> interface/abstract class)
	private static void play(Shape shape,double expectedArea) {
		var scaledShape = shape.resize(2);
		if (scaledShape.area() == expectedArea)
			System.out.println("area is %f".formatted(expectedArea));
		else
			throw new IllegalStateException("Something is wrong"); // LSP
	}

}
