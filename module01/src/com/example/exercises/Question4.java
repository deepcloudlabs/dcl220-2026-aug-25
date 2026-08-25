package com.example.exercises;

class Point extends Object {
    final int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Point p
                && p.x == x
                && p.y == y;
    }
}

class ColorPoint extends Point {
    final String color;

    ColorPoint(int x, int y, String color) {
        super(x, y);
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ColorPoint p
                && super.equals(p)
                && color.equals(p.color);
    }
}


public class Question4 {

	public static void main(String[] args) {
		Point p = new Point(1, 2);
		Point cp1 = new ColorPoint(1, 2, "red");
		Point cp2 = new ColorPoint(1, 2, "red");
		System.out.println(p.equals(p)); // true
		System.out.println(cp1.equals(cp1)); // true
		System.out.println(p.equals(cp1)); // true
		System.out.println(cp1.equals(p)); // false
		System.out.println(p.equals(cp1)); // transitivity: true
		System.out.println(cp1.equals(cp2)); // true
		System.out.println(p.equals(cp2)); // must be true

	}

}
