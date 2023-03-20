package com.github.shniu.slides.examples.maximizingmocks.infra;

import com.github.shniu.slides.examples.maximizingmocks.infra.mq.EventPublisher;

// 可见，MessageBus 接口是多余的
// 去掉 MessageBus 接口，MessageBusImpl rename to MessageBus
public class MessageBusImpl implements MessageBus {

    private EventPublisher eventPublisher;

    public MessageBusImpl(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void sendEmailChangedEvent(int userId, String newEmail) {
        String message = "Type: user email changed; Id: " + userId + "; NewEmail: " + newEmail;
        eventPublisher.publish(message);
    }
}
