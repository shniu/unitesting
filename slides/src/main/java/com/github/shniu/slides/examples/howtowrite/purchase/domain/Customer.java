package com.github.shniu.slides.examples.howtowrite.purchase.domain;

import com.google.common.collect.Maps;

import java.util.Map;

public class Customer {
    private long uid;
    private String username;
    private Map<Product, Integer> products;

    public Customer() {
        products = Maps.newHashMap();
    }

    public boolean purchase(Store store, Product product, int amount) {
        boolean success = store.deductInventory(product, amount);
        if (!success) {
            return false;
        }

        products.put(product, amount);
        return true;
    }
}
