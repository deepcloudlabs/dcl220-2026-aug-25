package com.example.patterns.composite.problem;

/** PROBLEM: the third hand-written walk, this time to render the contents. */
public class ProductPagePresenter {

    public String render(Object item) {
        StringBuilder out = new StringBuilder();
        render(item, 0, out);
        return out.toString();
    }

    private void render(Object item, int depth, StringBuilder out) {
        String indent = "  ".repeat(depth);
        if (item instanceof Product product) {
            out.append(indent).append(product.name()).append(" [").append(product.sku()).append("] ")
               .append(product.price()).append('\n');
        } else if (item instanceof Bundle bundle) {
            out.append(indent).append(bundle.name()).append(" (bundle, -")
               .append(bundle.discountPercent().stripTrailingZeros().toPlainString()).append("%)\n");
            for (Object inner : bundle.items()) {
                render(inner, depth + 1, out);
            }
        } else {
            out.append(indent).append("?? unknown item: ").append(item).append('\n');
        }
    }
}
