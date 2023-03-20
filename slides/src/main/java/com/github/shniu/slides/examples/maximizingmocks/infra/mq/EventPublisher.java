package com.github.shniu.slides.examples.maximizingmocks.infra.mq;

public interface EventPublisher {
    void publish(String message);
}
