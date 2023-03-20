package com.github.shniu.slides.examples.trivial;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    // 这个测试属于 Trivial tests
    // 这个测试的价值几乎为 0
    @Test
    void should_get_set_customer() {
        Customer customer = new Customer();
        customer.setCid("123");
        customer.setName("Test");
        customer.setVipLevel(0);

        assertEquals("123", customer.getCid());
        assertEquals("Test", customer.getName());
        assertEquals(0, customer.getVipLevel());
    }

}