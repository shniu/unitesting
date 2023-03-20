package com.github.shniu.slides.examples.maximizingmocks.infra.mq;

public class KafkaEventPublisher implements EventPublisher {

    // private KafkaProducer kafkaProducer;

    @Override
    public void publish(String message) {
        // kafkaProducer.send(message);
    }
}
