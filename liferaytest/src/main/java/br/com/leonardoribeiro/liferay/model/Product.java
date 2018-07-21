package br.com.leonardoribeiro.liferay.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.com.leonardoribeiro.liferay.utils.TaxUtils;

public class Product {

    private String       productName;
    private BigDecimal   productPrice;
    private ProductTypes productType;
    private boolean      imported;
    private BigDecimal   productPriceIncludingTax;
    private int          quantity;

    public Product(String productName, BigDecimal productPrice, ProductTypes productType, boolean imported) {
        this.productName              = productName;
        this.productPrice             = productPrice;
        this.productType              = productType;
        this.imported                 = imported;
        this.productPriceIncludingTax = TaxUtils.addTaxes(this);
        this.quantity                 = 1;
    }

    public boolean isTenPercentExempt() {
        return productType.equals(ProductTypes.BOOK) || productType.equals(ProductTypes.FOOD) || productType.equals(ProductTypes.MEDICAL);
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public ProductTypes getProductType() {
        return productType;
    }

    public void setProductType(ProductTypes productType) {
        this.productType = productType;
    }

    public boolean isImported() {
        return imported;
    }

    public void setImported(boolean imported) {
        this.imported = imported;
    }

    public BigDecimal getProductPriceIncludingTax() {
        return productPriceIncludingTax.setScale(2, RoundingMode.HALF_UP);
    }

    public void setProductPriceIncludingTax(BigDecimal totalTax) {
        this.productPriceIncludingTax = totalTax;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
