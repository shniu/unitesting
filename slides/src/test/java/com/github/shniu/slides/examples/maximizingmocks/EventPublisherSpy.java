package com.github.shniu.slides.examples.maximizingmocks;

import com.github.shniu.slides.examples.maximizingmocks.infra.mq.EventPublisher;
import org.assertj.core.util.Lists;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventPublisherSpy implements EventPublisher {

    private List<String> sentMessages = Lists.newArrayList();

    @Override
    public void publish(String message) {
        sentMessages.add(message);
    }

    public EventPublisherSpy shouldExceptedTimes(int exceptedSendTimes) {
        assertEquals(exceptedSendTimes, sentMessages.size());
        return this;
    }

    public EventPublisherSpy withEmailChangedMessage(int userId, String newEmail) {
        String expectedMessage = "Type: user email changed; Id: " + userId + "; NewEmail: " + newEmail;
        assertTrue(sentMessages.contains(expectedMessage));
        return this;
    }

}
