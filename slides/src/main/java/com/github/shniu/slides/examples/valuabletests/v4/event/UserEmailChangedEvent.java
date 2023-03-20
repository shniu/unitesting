package com.github.shniu.slides.examples.valuabletests.v4.event;

import lombok.Getter;

public class UserEmailChangedEvent extends DomainEvent {
    @Getter
    private int userId;
    @Getter
    private String newEmail;
}
