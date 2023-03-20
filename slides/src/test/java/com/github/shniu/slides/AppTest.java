package com.github.shniu.slides;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AppTest {

    @Test
    void should_reject_when_number_is_odd() {
        App app = new App();
        boolean b = app.onlyForTesting(3);
        assertFalse(b);
    }

    @Test
    void should_pass_when_number_is_even() {
        App app = new App();
        boolean b = app.onlyForTesting(4);
        assertTrue(b);
    }

}