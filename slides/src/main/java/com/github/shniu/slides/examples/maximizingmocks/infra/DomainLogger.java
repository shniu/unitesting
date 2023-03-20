package com.github.shniu.slides.examples.maximizingmocks.infra;

public interface DomainLogger {
    void logUserTypeUpdatedEvent(int userId, String oldType, String newType);
}
