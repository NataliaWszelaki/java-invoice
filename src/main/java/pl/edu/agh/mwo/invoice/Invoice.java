package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private HashMap<Product, Integer> products = new HashMap<>();

    public void addProduct(Product product) {
        products.put(product, products.getOrDefault(product, 0) + 1);
    }

    public void addProduct(Product product, Integer quantity) {
        products.put(product, quantity);
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
