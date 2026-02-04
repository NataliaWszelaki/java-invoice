package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {

    private Map<Product, Integer> products = new HashMap<>();

    public void addProduct(Product product) {

        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        products.put(product, products.getOrDefault(product, 0) + 1);
    }

    public void addProduct(Product product, Integer quantity) {

        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Product quantity must be greater than zero");
        }
        products.put(product, products.getOrDefault(product, 0) + quantity);
    }

    public BigDecimal getSubtotal() {

        BigDecimal subtotal = BigDecimal.ZERO;
        for (Map.Entry<Product, Integer> entry : products.entrySet() ) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            subtotal = subtotal.add(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
        }
        return subtotal;
    }

    public BigDecimal getTax() {

        BigDecimal tax = BigDecimal.ZERO;
        for (Map.Entry<Product, Integer> entry : products.entrySet() ) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            tax =  tax.add(product.getPrice().multiply(BigDecimal.valueOf(quantity)).multiply(product.getTaxPercent()));
        }
        return tax;
    }

    public BigDecimal getTotal() {

        return getSubtotal().add(getTax());
    }
}
