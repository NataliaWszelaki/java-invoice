package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {

    private static int nextNumber = 1;
    private String number;
    int currentYear = LocalDate.now().getYear();

    public Invoice() {
        number = "FV/" + currentYear + "/" + nextNumber++;
    }

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
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            subtotal = subtotal.add(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
        }
        return subtotal;
    }

    public BigDecimal getTax() {

        BigDecimal tax = BigDecimal.ZERO;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            tax =  tax.add(product.getTaxValue().multiply(BigDecimal.valueOf(quantity)));
        }
        return tax;
    }

    public BigDecimal getTotal() {

        return getSubtotal().add(getTax());
    }

    public String getNumber() {

        return number;
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        sb.append(number)
                .append("\n");

        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            sb.append(product.getName())
                    .append(" ")
                    .append(quantity)
                    .append(" ")
                    .append(product.getPrice())
                    .append("\n");
        }
        sb.append("Liczba pozycji: ")
                .append(products.size());
        return sb.toString();
    }
}
