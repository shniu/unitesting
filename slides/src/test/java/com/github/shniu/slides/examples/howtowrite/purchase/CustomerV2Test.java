package com.github.shniu.slides.examples.howtowrite.purchase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.github.shniu.slides.examples.howtowrite.purchase.domain.Customer;
import com.github.shniu.slides.examples.howtowrite.purchase.domain.Product;
import com.github.shniu.slides.examples.howtowrite.purchase.domain.Store;

import static org.junit.jupiter.api.Assertions.*;

class CustomerV2Test {
    // private Store store = new Store();  // this is a shared state, it's bad.
    private Store store;
    private Customer sut;

    @BeforeEach
    void setUp() {
//        store = new Store();
//        store.addInventory(Product.Book, 30);
//        sut = new Customer();
        createStoreWithInventory();
        createCustomer();
    }

    void createStoreWithInventory() {
        store = new Store();
        store.addInventory(Product.Book, 30);
    }

    void createCustomer() {
        sut = new Customer();
    }

    @Test
    void should_purchase_succeeds_when_enough_inventory() {
        // When
        boolean success = sut.purchase(store, Product.Book, 5);

        // Then
        assertTrue(success);
        assertEquals(25, store.getInventory(Product.Book));
    }

    @DisplayName("Should purchase failure when not enough inventory")
    @Test
    void should_purchase_failure_when_not_enough_inventory() {
        boolean success = sut.purchase(store, Product.Book, 35);

        assertFalse(success);
        assertEquals(30, store.getInventory(Product.Book));
    }

    @Test
    void should_purchase_failure_when_product_not_exist() {
        boolean success = sut.purchase(store, Product.Shampoo, 20);

        assertFalse(success);
        assertEquals(30, store.getInventory(Product.Book));
        assertEquals(0, store.getInventory(Product.Shampoo));
    }

    @Test
    void should_throw_npe_when_product_is_null() {
        assertThrows(NullPointerException.class, () -> {
            sut.purchase(store, null, 0);
        });
    }

    // Bad samples.

    @Test
    void testPurchase1() {

    }

    @Test
    void testCustomer() {

    }

    @Test
    void purchase_enoughInventory_purchaseSucceeds() {

    }
}