package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Product {

    private final String name;
    private final BigDecimal price;
    private final BigDecimal taxPercent;

    protected Product(String name, BigDecimal price, BigDecimal tax) {
        if (name == null) {
            throw new IllegalArgumentException("Product name cannot be null");
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (price == null) {
            throw new IllegalArgumentException("Product price cannot be null");
        }
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Product price cannot be negative");
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
