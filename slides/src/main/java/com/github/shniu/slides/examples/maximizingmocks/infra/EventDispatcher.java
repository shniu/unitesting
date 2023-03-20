package com.github.shniu.slides.examples.maximizingmocks.infra;

import com.github.shniu.slides.examples.maximizingmocks.DomainEvent;
import com.github.shniu.slides.examples.maximizingmocks.domain.UserEmailChangedEvent;
import com.github.shniu.slides.examples.maximizingmocks.domain.UserTypeUpdatedEvent;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public class EventDispatcher {
    private MessageBus messageBus;
    private DomainLogger domainLogger;

    public EventDispatcher(MessageBus messageBus, DomainLogger domainLogger) {
        this.messageBus = messageBus;
        this.domainLogger = domainLogger;
    }

    public void dispatch(List<DomainEvent> domainEvents) {
        if (CollectionUtils.isEmpty(domainEvents)) {
            // log...
            return;
        }

        for (DomainEvent domainEvent : domainEvents) {
            if (domainEvent instanceof UserEmailChangedEvent) {
                UserEmailChangedEvent event = (UserEmailChangedEvent) domainEvent;
                messageBus.sendEmailChangedEvent(event.getUserId(), event.getNewEmail());
            }

            if (domainEvent instanceof UserTypeUpdatedEvent) {
                UserTypeUpdatedEvent event = (UserTypeUpdatedEvent) domainEvent;
                domainLogger.logUserTypeUpdatedEvent(event.getUserId(), event.getOldType(), event.getNewType());
            }

            // Ignore...
        }

    }
}
