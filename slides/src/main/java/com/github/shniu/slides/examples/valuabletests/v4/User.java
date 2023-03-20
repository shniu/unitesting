package com.github.shniu.slides.examples.valuabletests.v4;

import com.github.shniu.slides.examples.valuabletests.v4.event.UserEmailChangedEvent;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.List;

// v4
// User response for the domain logic
public class User {
    private int userId;
    @Getter
    private String email;
    @Getter
    private UserType userType;

    @Getter
    private boolean emailConfirmed;

    @Getter
    private List<UserEmailChangedEvent> userDomainEvents;

    // 构造参数中加入 emailConfirmed
    public User(int userId, String email, UserType userType) {
        this.userId = userId;
        this.email = email;
        this.userType = userType;

        userDomainEvents = Lists.newArrayList();
    }

    public String canChangeEmail() {
        if (emailConfirmed) {
            return "Email can't change once confirmed.";
        }
        return null;
    }

    public String changeEmail(String newEmail, Company company) {
        Preconditions.checkState(canChangeEmail() == null);
//
//        if (emailConfirmed) {
//            return "Email can't change once confirmed.";
//        }

        if (this.email.equals(newEmail)) {
            return null;
        }

        UserType newType = company.isEmailCorporate(newEmail)
                ? UserType.Employee : UserType.Customer;

        if (!newType.equals(this.userType)) {
            int delta = newType.equals(UserType.Employee) ? 1 : -1;
            company.changeNumberOfEmployees(delta);
        }

        this.email = newEmail;
        this.userType = newType;

        userDomainEvents.add(new UserEmailChangedEvent());

        return null;
    }

    public enum UserType {
        Customer,
        Employee,
        ;
    }
}
