package com.github.shniu.slides.examples.maximizingmocks.domain;

import com.github.shniu.slides.examples.maximizingmocks.DomainEvent;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.List;

public class User {
    @Getter
    private int userId;
    private String username;
    @Getter
    private String email;
    private int userStatus;
    private boolean locked;

    private int companyId;

    @Getter
    private List<DomainEvent> domainEvents = Lists.newArrayList();

    public boolean canChangeEmail() {
        return !locked && userStatus >= 2;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void changeEmail(String newEmail, Company company) {
        Preconditions.checkState(canChangeEmail());

        this.email = newEmail;
        company.emailUpdated();

        domainEvents.add(new UserEmailChangedEvent(userId, newEmail));
    }
}
