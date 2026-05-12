package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import pl.edu.agh.mwo.invoice.product.Product;

public class ProductTest {
    @Test
    public void testProductNameIsCorrect() {
        Product product = new OtherProduct("buty", new BigDecimal("100.0"));
        Assert.assertEquals("buty", product.getName());
    }

    @Test
    public void testProductPriceAndTaxWithDefaultTax() {
        Product product = new OtherProduct("Ogorki", new BigDecimal("100.0"));
        Assert.assertThat(new BigDecimal("100"), Matchers.comparesEqualTo(product.getPrice()));
        Assert.assertThat(new BigDecimal("0.23"), Matchers.comparesEqualTo(product.getTaxPercent()));
    }

    @Test
    public void testProductPriceAndTaxWithDairyProduct() {
        Product product = new DairyProduct("Szarlotka", new BigDecimal("100.0"));
        Assert.assertThat(new BigDecimal("100"), Matchers.comparesEqualTo(product.getPrice()));
        Assert.assertThat(new BigDecimal("0.08"), Matchers.comparesEqualTo(product.getTaxPercent()));
    }

    @Test
    public void testPriceWithTax() {
        Product product = new DairyProduct("Oscypek", new BigDecimal("100.0"));
        Assert.assertThat(new BigDecimal("108"), Matchers.comparesEqualTo(product.getPriceWithTax()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithNullName() {
        new OtherProduct(null, new BigDecimal("100.0"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithEmptyName() {
        new TaxFreeProduct("", new BigDecimal("100.0"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithNullPrice() {
        new DairyProduct("Banany", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithNegativePrice() {
        new TaxFreeProduct("Mandarynki", new BigDecimal("-1.00"));
    }

    @Test
    public void testProductNameAndPriceWithBottleOfWineProduct() {
        Product product = new BottleOfWine("Riesling", new BigDecimal("40.0"));
        Assert.assertEquals("Riesling", product.getName());
        Assert.assertThat(new BigDecimal("40"), Matchers.comparesEqualTo(product.getPrice()));
    }

    @Test
    public void testProductNameAndPriceWithFuelCanister() {
        Product product = new FuelCanister("Diesel", new BigDecimal("6.00"));
        Assert.assertEquals("Diesel", product.getName());
        Assert.assertThat(new BigDecimal("6.00"), Matchers.comparesEqualTo(product.getPrice()));
    }

    @Test
    public void testProductExciseTaxWithBottleOfWine() {
        Product product = new BottleOfWine("Riesling", new BigDecimal("40.0"));
        Assert.assertThat(new BigDecimal("5.56"), Matchers.comparesEqualTo(product.getExciseTax()));
    }

    @Test
    public void testProductExciseTaxWithFuelCanister() {
        Product product = new FuelCanister("20L Diesel Canister", new BigDecimal("120"));
        Assert.assertThat(new BigDecimal("5.56"), Matchers.comparesEqualTo(product.getExciseTax()));
    }

    @Test
    public void testProductPriceWithTaxWithBottleOfWine() {
        Product product = new BottleOfWine("Riesling", new BigDecimal("40.00"));
        Assert.assertThat(new BigDecimal("54.76"), Matchers.comparesEqualTo(product.getPriceWithTax()));
    }

    @Test
    public void testProductPriceWithTaxWithFuelCanister() {
        Product product = new FuelCanister("20L Diesel Canister", new BigDecimal("120.00"));
        Assert.assertThat(new BigDecimal("125.56"), Matchers.comparesEqualTo(product.getPriceWithTax()));
    }
}
