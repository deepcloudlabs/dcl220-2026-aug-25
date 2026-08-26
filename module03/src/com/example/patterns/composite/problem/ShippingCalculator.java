package com.example.patterns.composite.problem;

/**
 * PROBLEM: the second hand-written walk. It was never updated for nested bundles, so the
 * weight of a bundle inside a bundle is silently ignored - and postage is wrong.
 */
public class ShippingCalculator {

    public int totalWeightGrams(Cart cart) {
        int total = 0;
        for (Object item : cart.items()) {
            if (item instanceof Product product) {
                total += product.weightGrams();
            } else if (item instanceof Bundle bundle) {
                for (Object inner : bundle.items()) {
                    if (inner instanceof Product product) {
                        total += product.weightGrams();
                    }
                    // a Bundle inside a Bundle falls through here and weighs nothing
                }
            }
        }
        return total;
    }
}
