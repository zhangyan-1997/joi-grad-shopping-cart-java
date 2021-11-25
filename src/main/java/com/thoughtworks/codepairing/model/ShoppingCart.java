package com.thoughtworks.codepairing.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShoppingCart {
    private List<Product> products;
    private Customer customer;

    public ShoppingCart(Customer customer, List<Product> products) {
        this.customer = customer;
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }


    public Order checkout() {
        double totalPrice = 0;

        int loyaltyPointsEarned = 0;
        Map<String, Integer> map = new HashMap<>();
        for (Product product : products) {
            double discount = 0;
            if (product.getProductCode().startsWith("DIS_10")) {
                discount = (product.getPrice() * 0.1);
                loyaltyPointsEarned += (product.getPrice() / 10);
            } else if (product.getProductCode().startsWith("DIS_15")) {
                discount = (product.getPrice() * 0.15);
                loyaltyPointsEarned += (product.getPrice() / 15);
            } else if (product.getProductCode().startsWith("DIS_20")) {
                discount = (product.getPrice() * 0.2);
                loyaltyPointsEarned += (product.getPrice() / 20);
            }else if (product.getProductCode().startsWith("BULK_BUY_2_GET_1")) {
                map.put(product.getProductCode(), map.getOrDefault(product.getProductCode(), 0) + 1);
                if(map.get(product.getProductCode())%3 == 0){
                    discount += product.getPrice();
                }
            }else {
                loyaltyPointsEarned += (product.getPrice() / 5);
            }

            totalPrice += product.getPrice() - discount;
        }
        if(totalPrice >= 500) totalPrice *= 0.95;

        return new Order(totalPrice, loyaltyPointsEarned);
    }

    @Override
    public String toString() {
        return "Customer: " + customer.getName() + "\n" + "Bought:  \n" + products.stream().map(p -> "- " + p.getName()+ ", "+p.getPrice()).collect(Collectors.joining("\n"));
    }
}
