package com.example.patterns.composite.problem;

import java.math.BigDecimal;

/** PROBLEM: a product is one thing... */
public class Product {

    private final String sku;
    private final String name;
    private final BigDecimal price;
    private final int weightGrams;

    public Product(String sku, String name, BigDecimal price, int weightGrams) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.weightGrams = weightGrams;
    }

    public String sku() {
        return sku;
    }

    public String name() {
        return name;
    }

    public BigDecimal price() {
        return price;
    }

    public int weightGrams() {
        return weightGrams;
    }
}
