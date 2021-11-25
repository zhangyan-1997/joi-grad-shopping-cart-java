package com.thoughtworks.codepairing.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class ShoppingCartTest {

    public static final int PRICE = 100;
    public static final String PRODUCT = "Product";

    Customer customer;

    @Before
    public void setUp() throws Exception {
        customer = new Customer("test");
    }

    @Test
    public void shouldCalculatePriceWithNoDiscount() {
        List<Product> products = asList(new Product(PRICE, "", PRODUCT));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();
        assertEquals(100.0, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculateLoyaltyPointsWithNoDiscount() {
        List<Product> products = asList(new Product(PRICE, "", PRODUCT));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(20, order.getLoyaltyPoints());
    }

    @Test
    public void shouldCalculatePriceFor10PercentDiscount() {
        List<Product> products = asList(new Product(PRICE, "DIS_10_ABCD", PRODUCT));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(90.0, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculateLoyaltyPointsFor10PercentDiscount() {
        List<Product> products = asList(new Product(PRICE, "DIS_10_ABCD", PRODUCT));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(10, order.getLoyaltyPoints());
    }

    @Test
    public void shouldCalculatePriceFor15PercentDiscount() {
        List<Product> products = asList(new Product(PRICE, "DIS_15_ABCD", PRODUCT));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(85.0, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculateLoyaltyPointsFor15PercentDiscount() {
        List<Product> products = asList(new Product(PRICE, "DIS_15_ABCD", PRODUCT));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(6, order.getLoyaltyPoints());
    }
    @Test
    public void shouldCalculatePriceFor20PercentDiscount() {
        List<Product> products = asList(new Product(PRICE, "DIS_20_ABCD", PRODUCT));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(80.0, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculateLoyaltyPointsFor20PercentDiscount() {
        List<Product> products = asList(new Product(PRICE, "DIS_20_ABCD", PRODUCT));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(5, order.getLoyaltyPoints());
    }

    @Test
    public void shouldCalculateBuyTwoGetThree() {
        Product p1 = new Product(PRICE, "BULK_BUY_2_GET_1", PRODUCT);
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            products.add(p1);
        }
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(200, order.getTotalPrice(), 0.0);
    }
    @Test
    public void shouldCalculateBuyTwoGetThreeNotGet() {
        Product p1 = new Product(PRICE, "BULK_BUY_2_GET_1", PRODUCT);
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            products.add(p1);
        }
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(200, order.getTotalPrice(), 0.0);
    }
    @Test
    public void shouldCalculateBuyTwoGetThreeNotGet2() {
        Product p1 = new Product(PRICE, "BULK_BUY_2_GET_1_A", PRODUCT);
        Product p2 = new Product(80, "BULK_BUY_2_GET_1_B", PRODUCT);
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            products.add(p1);
        }
        products.add(p2);
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(280, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculateBuyTwoGetThreeNotGet3() {
        Product p1 = new Product(PRICE, "BULK_BUY_2_GET_1_A", PRODUCT);
        Product p2 = new Product(80, "BULK_BUY_2_GET_1_B", PRODUCT);
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            products.add(p1);
        }
        for (int i = 0; i < 3; i++) {
            products.add(p2);
        }
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(360, order.getTotalPrice(), 0.0);
    }
    @Test
    public void shouldCalculateBuyTwoGetThreeNotGet4() {
        Product p1 = new Product(PRICE, "BULK_BUY_2_GET_1_A", PRODUCT);
        Product p2 = new Product(80, "BULK_BUY_2_GET_1_B", PRODUCT);
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            products.add(p1);
        }
        for (int i = 0; i < 2; i++) {
            products.add(p2);
        }
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(360, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculateOver500Get5PercentDiscount() {
        Product p1 = new Product(PRICE, "DIS_00_ABCD", PRODUCT);
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            products.add(p1);
        }
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(475, order.getTotalPrice(), 0.0);
    }
    @Test
    public void shouldCalculateOver500Get5PercentDiscount2() {
        Product p1 = new Product(PRICE, "DIS_00_ABCD", PRODUCT);
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            products.add(p1);
        }
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(400, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculateOver500Get5PercentDiscount3() {
        Product p1 = new Product(PRICE, "DIS_15_ABCD", PRODUCT);
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            products.add(p1);
        }
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(484.5, order.getTotalPrice(), 0.0);
    }
}
