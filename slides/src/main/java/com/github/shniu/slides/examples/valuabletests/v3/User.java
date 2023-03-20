package com.github.shniu.slides.examples.valuabletests.v3;

import lombok.Getter;

// v3
// User response for the domain logic
public class User {
    private int userId;
    @Getter
    private String email;
    @Getter
    private UserType userType;

    public User(int userId, String email, UserType userType) {
        this.userId = userId;
        this.email = email;
        this.userType = userType;
    }

    public void changeEmail(String newEmail, Company company) {
        if (this.email.equals(newEmail)) {
            return;
        }

        UserType newType = company.isEmailCorporate(newEmail)
                ? UserType.Employee : UserType.Customer;

        if (!newType.equals(this.userType)) {
            int delta = newType.equals(UserType.Employee) ? 1 : -1;
            company.changeNumberOfEmployees(delta);
        }

        this.email = newEmail;
        this.userType = newType;
    }

    public enum UserType {
        Customer,
        Employee,
        ;
    }
}
