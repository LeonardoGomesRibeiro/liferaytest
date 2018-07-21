package br.com.leonardoribeiro.liferay.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.com.leonardoribeiro.liferay.model.Product;

public class TaxUtils {

    public static BigDecimal addTaxes(Product product) {

        if (product.isImported() && !product.isTenPercentExempt()) {
        	double tax = ((product.getProductPrice().floatValue() * 15) / 100);
            // round off to nearest 0.05
            tax = Math.ceil(tax / 0.05) * 0.05;
            BigDecimal taxes = BigDecimal.valueOf(tax).setScale(2,RoundingMode.HALF_UP);
            return product.getProductPrice().add(taxes);

        } else if (product.isImported() && product.isTenPercentExempt()) {
            double tax = ((product.getProductPrice().floatValue() * 5) / 100);
            // round off to nearest 0.05
            tax = Math.ceil(tax / 0.05) * 0.05;
            BigDecimal taxes = BigDecimal.valueOf(tax).setScale(2,RoundingMode.HALF_UP);
            return product.getProductPrice().add(taxes);

        } else if (!product.isImported() && !product.isTenPercentExempt()) {
        	double tax = ((product.getProductPrice().floatValue() * 10) / 100);
            // round off to nearest 0.05
            tax = Math.ceil(tax / 0.05) * 0.05;
            BigDecimal taxes = BigDecimal.valueOf(tax).setScale(2,RoundingMode.HALF_UP);
            return product.getProductPrice().add(taxes);
        }

        return BigDecimal.ZERO;
    }
}
