package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class BottleOfWine extends Product {

    private BigDecimal exciseTax;

    public BottleOfWine(String name, BigDecimal price) {
        super(name, price, new BigDecimal("0.23"));
        exciseTax = new BigDecimal("5.56");
    }

    @Override
    public BigDecimal getExciseTax() {
        return exciseTax;
    }
}
