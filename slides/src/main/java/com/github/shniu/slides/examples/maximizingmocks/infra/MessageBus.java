package com.github.shniu.slides.examples.maximizingmocks.infra;

public interface MessageBus {
    void sendEmailChangedEvent(int userId, String newEmail);
}
