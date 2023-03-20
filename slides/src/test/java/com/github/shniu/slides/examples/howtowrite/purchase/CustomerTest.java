package com.github.shniu.slides.examples.howtowrite.purchase;

import com.github.shniu.slides.examples.howtowrite.purchase.domain.Customer;
import com.github.shniu.slides.examples.howtowrite.purchase.domain.Product;
import com.github.shniu.slides.examples.howtowrite.purchase.domain.Store;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @BeforeEach
    void setUp() {
        // ...
    }

    @AfterEach
    void tearDown() {
        // ...
    }

    @Test
    void should_purchase_succeeds_when_enough_inventory() {
        // Given
        Store store = new Store();
        store.addInventory(Product.Book, 30);
        Customer customer = new Customer();

        // When
        boolean success = customer.purchase(store, Product.Book, 5);

        // Then
        assertTrue(success);
        assertEquals(25, store.getInventory(Product.Book));
    }

    @DisplayName("Should purchase failure when not enough inventory")
    @Test
    void should_purchase_failure_when_not_enough_inventory() {
        Store store = new Store();
        store.addInventory(Product.Book, 30);
        Customer customer = new Customer();

        boolean success = customer.purchase(store, Product.Book, 35);

        assertFalse(success);
        assertEquals(30, store.getInventory(Product.Book));
    }

    @Test
    void should_purchase_failure_when_product_not_exist() {
        Store store = new Store();
        store.addInventory(Product.Book, 30);
        Customer customer = new Customer();

        boolean success = customer.purchase(store, Product.Shampoo, 20);

        assertFalse(success);
        assertEquals(30, store.getInventory(Product.Book));
        assertEquals(0, store.getInventory(Product.Shampoo));
    }

    @Test
    void should_throw_npe_when_product_is_null() {
        Store store = new Store();
        store.addInventory(Product.Book, 30);
        Customer customer = new Customer();

        assertThrows(NullPointerException.class, () -> {
            customer.purchase(store, null, 0);
        });
    }

    // Bad samples.

    @Test
    void testPurchase1() {

    }

    @Test
    void testCustomer() {

    }
}