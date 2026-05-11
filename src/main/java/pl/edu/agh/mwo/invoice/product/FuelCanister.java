package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class FuelCanister extends Product {

    private BigDecimal exciseTax;

    public FuelCanister(String name, BigDecimal price) {
        super(name, price, BigDecimal.ZERO);
        exciseTax = new BigDecimal("5.56");
    }

    public BigDecimal getExciseTax() {
        return exciseTax;
    }
}
