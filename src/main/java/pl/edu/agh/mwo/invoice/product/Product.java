package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public abstract class Product {
    private final String name;

    private final BigDecimal price;

    private final BigDecimal taxPercent;

    protected Product(String name, BigDecimal price, BigDecimal tax) {
        if (name == null) {
            throw new IllegalArgumentException("Product name is null");
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Product name is empty");
        }
        if (price == null) {
            throw new IllegalArgumentException("Product price is null");
        }
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.name = name;
        this.price = price;
        this.taxPercent = tax;
    }

    public String getName() {

        return name;
    }

    public BigDecimal getPrice() {

        return price;
    }

    public BigDecimal getTaxPercent() {
        return taxPercent;
    }

    public BigDecimal getPriceWithTax() {

        return price.add(price.multiply(getTaxPercent()));
    }
}
