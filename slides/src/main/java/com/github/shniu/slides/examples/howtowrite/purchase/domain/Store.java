package com.github.shniu.slides.examples.howtowrite.purchase.domain;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Objects;

public class Store {
    private long storeId;
    private String name;
    private Map<Product, Integer> inventories;

    public Store() {
        inventories = Maps.newHashMap();
    }

    public void addInventory(Product product, int amount) {
        Objects.requireNonNull(product);
        assert amount > 0;

        final Integer currInventory = inventories.get(product);
        if (currInventory == null) {
            inventories.put(product, amount);
        } else {
            inventories.put(product, currInventory + amount);
        }
    }

    public int getInventory(Product product) {
        if (product == null) return 0;
        return inventories.getOrDefault(product, 0);
    }

    public boolean deductInventory(Product product, int amount) {
        Objects.requireNonNull(product);
        assert amount > 0;

        Integer currInventory = inventories.getOrDefault(product, 0);
        if (currInventory < amount) {
            return false;
        }

        inventories.put(product, currInventory - amount);
        return true;
    }
}
