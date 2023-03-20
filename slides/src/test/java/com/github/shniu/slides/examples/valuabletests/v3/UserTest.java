package com.github.shniu.slides.examples.valuabletests.v3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void should_change_email_from_non_corporate_to_corporate() {
        Company company = new Company("crypto.com", 1);
        User sut = new User(100, "abc@gmail.com", User.UserType.Customer);

        sut.changeEmail("one@crypto.com", company);

        assertEquals(2, company.getNumberOfEmployees());
        assertEquals("one@crypto.com", sut.getEmail());
        assertEquals(User.UserType.Employee, sut.getUserType());
    }

    @Test
    void should_change_email_from_corporate_to_non_corporate() {
        // ...
    }

    @Test
    void should_change_email_without_initial_user_type() {
        // ...
    }

    @Test
    void should_not_change_email_when_given_the_same_email() {
        // ...
    }

    // ...
}