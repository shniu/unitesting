package com.github.shniu.slides.examples.howtowrite.purchase;

import com.github.shniu.slides.examples.howtowrite.purchase.domain.Customer;
import com.github.shniu.slides.examples.howtowrite.purchase.domain.Product;
import com.github.shniu.slides.examples.howtowrite.purchase.domain.Store;
import com.google.common.collect.Maps;

import java.util.Map;

// @Service
public class CustomerService {

    public String purchase(long uid, long storeId, Map<String, Integer> products) {
        Customer customer = getByUid(uid);
        Store store = getByStoreId(storeId);

        for (Map.Entry<String, Integer> entry : products.entrySet()) {
            boolean success = customer.purchase(store, Product.valueOf(entry.getKey()), entry.getValue());
            if (!success) {
                throw new RuntimeException("Insufficient inventory.");
            }
        }

        purchaseThenUpdateStoreInventory(customer, store);

        return "Success";
    }

    private void purchaseThenUpdateStoreInventory(Customer customer, Store store) {
        // ...
    }

    Customer getByUid(long uid) {
        // from db or cache ...
        return new Customer();
    }

    Store getByStoreId(long storeId) {
        // from db or cache ...
        return new Store();
    }

    void enrichInventory(Store store, Map<String, Integer> products) {
        // get inventory by products and storeId ...
        Map<Product, Integer> inventories = Maps.newHashMap();
        for (Map.Entry<Product, Integer> entry : inventories.entrySet()) {
            store.addInventory(entry.getKey(), entry.getValue());
        }
        // ...
    }
}
