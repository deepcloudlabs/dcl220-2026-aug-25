package com.example.patterns.composite.problem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * PROBLEM: ...and a bundle is a different thing. When nested bundles were requested the
 * {@code List<Product>} became a {@code List<Object>}, and every client has to know what may
 * be inside.
 */
public class Bundle {

    private final String name;
    private final BigDecimal discountPercent;
    // Product or Bundle: became List<Object> when nesting was requested
    private final List<Object> items = new ArrayList<>();

    public Bundle(String name, BigDecimal discountPercent) {
        this.name = name;
        this.discountPercent = discountPercent;
    }

    public Bundle add(Object item) {
        items.add(item);
        return this;
    }

    public String name() {
        return name;
    }

    public BigDecimal discountPercent() {
        return discountPercent;
    }

    public List<Object> items() {
        return Collections.unmodifiableList(items);
    }
}
