package com.github.shniu.slides.examples.observablebehavior;

public class Database {
    User getUserById(String uid) {
        return new User();
    }

    int saveUser(User user) {
        return 1;
    }

    UserV2 getUserByIdV2(String uid) {
        return new UserV2();
    }

    int saveUserV2(UserV2 user) {
        return 1;
    }
}
