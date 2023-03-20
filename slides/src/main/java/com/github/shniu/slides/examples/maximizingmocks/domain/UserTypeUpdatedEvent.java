package com.github.shniu.slides.examples.maximizingmocks.domain;

import com.github.shniu.slides.examples.maximizingmocks.DomainEvent;

public class UserTypeUpdatedEvent implements DomainEvent {
    public int getUserId() {
        return 0;
    }

    public String getOldType() {
        return "Viewer";
    }

    public String getNewType() {
        return "Editor";
    }
}
