package com.github.shniu.slides.examples.maximizingmocks;

import com.github.shniu.slides.examples.maximizingmocks.domain.User;

public class UserFactory {
    public static User create(Object[] fields) {
        return new User();
    }
}
