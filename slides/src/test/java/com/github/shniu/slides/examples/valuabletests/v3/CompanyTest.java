package com.github.shniu.slides.examples.valuabletests.v3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CompanyTest {

    // Spock is more better.
    @ParameterizedTest
    @MethodSource("provideTestData")
    void should_differentiates_a_corporate_email_from_non_corporate(String domain, String email, boolean exceptedResult) {
        Company sut = new Company(domain, 0);
        boolean isEmailCorporate = sut.isEmailCorporate(email);
        assertEquals(exceptedResult, isEmailCorporate);
    }

    static Stream<Arguments> provideTestData() {
        return Stream.of(
                Arguments.of("cc.com", "my1@cc.com", true),
                Arguments.of("cc.com", "my2@gmail.com", false)
        );
    }

    @Test
    void should_add_number_of_employees_when_email_changed_to_corporate() {
        // ...
    }

    @Test
    void should_sub_number_of_employees_when_email_changed_to_non_corporate() {
        // ...
    }

    @Test
    void should_throw_check_error_when_total_number_is_negative() {
        Company sut = new Company("aa.com", 2);

        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> {
            sut.changeNumberOfEmployees(-3);
        });

        assertEquals("....", ex.getMessage());
    }
}