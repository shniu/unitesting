package com.github.shniu.slides.examples.maximizingmocks.domain;

import com.github.shniu.slides.examples.maximizingmocks.DomainEvent;

public class UserEmailChangedEvent implements DomainEvent {
    private int userId;
    private String newEmail;

    public UserEmailChangedEvent(int userId, String newEmail) {
        this.userId = userId;
        this.newEmail = newEmail;
    }

    public int getUserId() {
        return 0;
    }

    public String getNewEmail() {
        return "new@gmail.com";
    }
}
