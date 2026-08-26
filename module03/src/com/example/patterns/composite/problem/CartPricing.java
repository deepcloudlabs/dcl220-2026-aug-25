package com.example.patterns.composite.problem;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * PROBLEM: the first hand-written walk over the structure. The recursive branch was added
 * after a production incident with nested bundles.
 */
public class CartPricing {

    public BigDecimal total(Cart cart) {
        BigDecimal total = BigDecimal.ZERO;
        for (Object item : cart.items()) {
            if (item instanceof Product product) {
                total = total.add(product.price());
            } else if (item instanceof Bundle bundle) {
                total = total.add(bundlePrice(bundle));
            }
        }
        return total;
    }

    private BigDecimal bundlePrice(Bundle bundle) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Object item : bundle.items()) {
            if (item instanceof Product product) {
                sum = sum.add(product.price());
            } else if (item instanceof Bundle inner) {
                sum = sum.add(bundlePrice(inner));  // added after a bug
            }
        }
        return applyDiscount(sum, bundle.discountPercent());
    }

    private static BigDecimal applyDiscount(BigDecimal amount, BigDecimal discountPercent) {
        BigDecimal factor = BigDecimal.ONE.subtract(discountPercent.movePointLeft(2));
        return amount.multiply(factor).setScale(2, RoundingMode.HALF_UP);
    }
}
