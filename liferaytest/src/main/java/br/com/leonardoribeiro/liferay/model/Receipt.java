package br.com.leonardoribeiro.liferay.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class Receipt {

    private List<Product> products;
    private double salesTax;

    public Receipt(List<Product> products) {
        this.products = products;
    }

    public BigDecimal getSalesTax() {
        double totalTax = 0;

        List<Product> filtered = products.stream().filter(p -> p.getProductPriceIncludingTax().doubleValue() != 0).collect(Collectors.toList());
        if (filtered != null) {
            for (Product product : filtered) {
                totalTax += product.getProductPriceIncludingTax().doubleValue() - product.getProductPrice().doubleValue();
            }
        }
        this.salesTax = totalTax;
        return new BigDecimal(this.salesTax).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getProductPrice().doubleValue();
        }
        total += this.salesTax;
        return new BigDecimal(total).setScale(2, RoundingMode.HALF_UP);
    }

    public List<Product> getProducts() {
        return products;
    }

    public String printReceipt() {
        StringBuffer sb = new StringBuffer();
        for (Product product : products) {
            sb.append(product.getQuantity()).append(" ").append(product.getProductName()).append(": ");
            if (product.isTenPercentExempt() && !product.isImported()) {
                sb.append(product.getProductPrice().setScale(2, RoundingMode.HALF_UP));
            } else {
                sb.append(product.getProductPriceIncludingTax().setScale(2, RoundingMode.HALF_UP));
            }
            sb.append("\n");
        }

        sb.append("Sales Taxes: ").append(getSalesTax());
        sb.append("\n");
        sb.append("Total: ").append(getTotal());
        sb.append("\n");

        return sb.toString();
    }
}
