package com.example.patterns.composite.problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** PROBLEM: the cart, too, can only hold {@code Object}. */
public class Cart {

    private final List<Object> items = new ArrayList<>();

    public Cart add(Object item) {
        items.add(item);
        return this;
    }

    public List<Object> items() {
        return Collections.unmodifiableList(items);
    }
}
