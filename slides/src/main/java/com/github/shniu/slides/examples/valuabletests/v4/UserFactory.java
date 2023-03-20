package com.github.shniu.slides.examples.valuabletests.v4;

import com.google.common.base.Preconditions;

public class UserFactory {
    public static User create(Object[] data) {
        Preconditions.checkState(data.length >= 3, "...");

        int userId = (int) data[0];
        String currEmail = (String) data[1];
        User.UserType currType = (User.UserType) data[2];

        return new User(userId, currEmail, currType);
    }
}
