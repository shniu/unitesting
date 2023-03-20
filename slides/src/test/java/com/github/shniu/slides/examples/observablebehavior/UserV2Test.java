package com.github.shniu.slides.examples.observablebehavior;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserV2Test {

    @Test
    void should_normalize_name_when_new_name_longer_than_excepted() {
        UserV2 sut = new UserV2();

        sut.setName("should_normalize_name_when_new_name_longer_than_excepted_should_normalize_name_when_new_name_longer_than_excepted");

        assertEquals(50, sut.getName().length());
    }

    @Test
    void should_not_normalize_name_when_new_name_shorter_than_excepted() {
        UserV2 sut = new UserV2();

        sut.setName("abc");

        assertEquals(3, sut.getName().length());
    }

}