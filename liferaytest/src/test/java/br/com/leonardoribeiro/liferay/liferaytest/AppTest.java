package br.com.leonardoribeiro.liferay.liferaytest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import br.com.leonardoribeiro.liferay.model.Product;
import br.com.leonardoribeiro.liferay.model.ProductTypes;
import br.com.leonardoribeiro.liferay.model.Receipt;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase
{
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void testInputOne() {
        List<Product> products = new ArrayList<Product>();

        assertTrue("List is empty", products.isEmpty());
        System.out.println("Input 1: ");
        Product book           = new Product("some book", new BigDecimal(12.49), ProductTypes.BOOK, false);
        Product avantasiaAlbum = new Product("Avantasia", new BigDecimal(14.99), ProductTypes.MUSIC, false);
        Product chocolateBar   = new Product("Chocate Bar", new BigDecimal(0.85), ProductTypes.FOOD, false);

        products.add(book);
        products.add(avantasiaAlbum);
        products.add(chocolateBar);

        assertTrue("Output one added to the list.", !products.isEmpty() && products.size() == 3);

        Receipt receipt = new Receipt(products);
        assertTrue("Receipt not empty", receipt != null);
        List<Product> receiptProducts = receipt.getProducts();
        assertTrue("Products exsits", !receiptProducts.isEmpty());

        assertEquals("1 book tax is empty", new BigDecimal(0).setScale(2, RoundingMode.HALF_UP), receiptProducts.get(0).getProductPriceIncludingTax());
        assertEquals("1 book", 12.49, receiptProducts.get(0).getProductPrice().doubleValue());
        System.out.println(receiptProducts.get(0).getQuantity() + " " + receiptProducts.get(0).getProductName() + ":" + receiptProducts.get(0).getProductPriceIncludingTax());
        assertTrue("1 music CD tax not empty", receiptProducts.get(1).getProductPriceIncludingTax() != null);
        assertEquals("1 music CD", 16.49, receiptProducts.get(1).getProductPriceIncludingTax().doubleValue());

        assertEquals("1 chocolate bar tax is empty", new BigDecimal(0).setScale(2, RoundingMode.HALF_UP), receiptProducts.get(2).getProductPriceIncludingTax());
        assertEquals("1 chocolate bar", 0.85, receiptProducts.get(2).getProductPrice().doubleValue());

        assertEquals("Sales tax", new BigDecimal(1.50).setScale(2, RoundingMode.HALF_UP), receipt.getSalesTax());
        assertEquals("Sales tax as doubleValue", new BigDecimal(1.50).setScale(2, RoundingMode.HALF_UP).doubleValue(), receipt.getSalesTax().doubleValue());

        assertEquals("Total", new BigDecimal(29.83).setScale(2, RoundingMode.HALF_UP), receipt.getTotal());
        assertEquals("Total as double", new BigDecimal(29.83).setScale(2, RoundingMode.HALF_UP).doubleValue(), receipt.getTotal().doubleValue());

        System.out.println("============= PRINT RECEIPT =================");
        System.out.println(receipt.printReceipt());
    }

    public void testInputTwo() {
        List<Product> products = new ArrayList<Product>();
        System.out.println("Input 2: ");
        Product importedChocolateBox = new Product("Chocolate Box Imported", new BigDecimal(10.00), ProductTypes.FOOD, true);
        Product importedPerfume      = new Product("Imported Perfume", new BigDecimal(47.50), ProductTypes.UTILITIES, true);

        products.add(importedChocolateBox);
        products.add(importedPerfume);

        Receipt receipt = new Receipt(products);
        assertTrue("Receipt not empty", receipt != null);
        List<Product> receiptProducts = receipt.getProducts();
        assertTrue("Products exsits", !receiptProducts.isEmpty());

        assertEquals("1 imported chocolate box value", new BigDecimal(10.50).setScale(2, RoundingMode.HALF_UP), receiptProducts.get(0).getProductPriceIncludingTax());
        assertEquals("1 imported chocolate box as double value", new BigDecimal(10.50).setScale(2, RoundingMode.HALF_UP).doubleValue(), receiptProducts.get(0).getProductPriceIncludingTax().doubleValue());

        assertEquals("1 imported bottle of perfume value", new BigDecimal(54.65).setScale(2, RoundingMode.HALF_UP), receiptProducts.get(1).getProductPriceIncludingTax());

        assertEquals("Sales tax", new BigDecimal(7.65).setScale(2, RoundingMode.HALF_UP), receipt.getSalesTax());

        assertEquals("Total", new BigDecimal(65.15).setScale(2, RoundingMode.HALF_UP), receipt.getTotal());
        System.out.println("============= PRINT RECEIPT =================");
        System.out.println(receipt.printReceipt());
    }

    public void testInputThree() {
        List<Product> products = new ArrayList<Product>();
        System.out.println("Input 3: ");
        Product importedBottlePerfume = new Product("Bottle of perfume imported", new BigDecimal(27.99), ProductTypes.UTILITIES, true);
        Product notImportedPerfume    = new Product("Not imported Perfume", new BigDecimal(18.99), ProductTypes.UTILITIES, false);
        Product headachePills         = new Product("Headache pills", new BigDecimal(9.75), ProductTypes.MEDICAL, false);
        Product importedChocolateBox  = new Product("Chocolate Box Imported", new BigDecimal(11.25), ProductTypes.FOOD, true);

        products.add(importedBottlePerfume);
        products.add(notImportedPerfume);
        products.add(headachePills);
        products.add(importedChocolateBox);

        Receipt receipt = new Receipt(products);
        assertTrue("Receipt not empty", receipt != null);
        List<Product> receiptProducts = receipt.getProducts();
        assertTrue("Products exsits", !receiptProducts.isEmpty());

        assertEquals("1 imported bottle of perfume", new BigDecimal(32.19).setScale(2, RoundingMode.HALF_UP), receiptProducts.get(0).getProductPriceIncludingTax());
        assertEquals("1 imported bottle of perfume", new BigDecimal(32.19).setScale(2, RoundingMode.HALF_UP).doubleValue(), receiptProducts.get(0).getProductPriceIncludingTax().doubleValue());

        assertEquals("1 bottle of perfume", new BigDecimal(20.89).setScale(2, RoundingMode.HALF_UP), receiptProducts.get(1).getProductPriceIncludingTax());
        assertEquals("1 bottle of perfume", new BigDecimal(20.89).setScale(2, RoundingMode.HALF_UP).doubleValue(), receiptProducts.get(1).getProductPriceIncludingTax().doubleValue());

        assertEquals("1 package headache pills tax exempt", new BigDecimal(0).setScale(2, RoundingMode.HALF_UP), receiptProducts.get(2).getProductPriceIncludingTax());
        assertEquals("1 package headache pills tax exempt", new BigDecimal(9.75).setScale(2, RoundingMode.HALF_UP), receiptProducts.get(2).getProductPrice());

        assertEquals("1 imported box of chocolates", new BigDecimal(11.85).setScale(2, RoundingMode.HALF_UP), receiptProducts.get(3).getProductPriceIncludingTax());

        assertEquals("Sales tax", new BigDecimal(6.70).setScale(2, RoundingMode.HALF_UP), receipt.getSalesTax());

        assertEquals("Total", new BigDecimal(74.68).setScale(2, RoundingMode.HALF_UP), receipt.getTotal());
        System.out.println("============= PRINT RECEIPT =================");
        System.out.println(receipt.printReceipt());
    }
}
