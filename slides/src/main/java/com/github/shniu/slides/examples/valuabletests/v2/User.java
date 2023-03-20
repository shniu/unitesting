package com.github.shniu.slides.examples.valuabletests.v2;

// v2
// User response for the domain logic
public class User {
    private int userId;
    private String email;
    private UserType userType;

    public User(int userId, String email, UserType userType) {
        this.userId = userId;
        this.email = email;
        this.userType = userType;
    }

    public int changeEmail(String newEmail, String companyDomainName, int numberOfEmployees) {
        if (this.email.equals(newEmail)) {
            return numberOfEmployees;
        }

        String newEmailDomain = newEmail.split("@")[1];
        boolean isEmailCorporate = newEmailDomain.equals(companyDomainName);
        UserType newType = isEmailCorporate ? UserType.Employee : UserType.Customer;

        int newNumber = numberOfEmployees;
        if (!newType.equals(this.userType)) {
            int delta = newType.equals(UserType.Employee) ? 1 : -1;
            newNumber = numberOfEmployees + delta;
        }

        this.email = newEmail;
        this.userType = newType;

        return newNumber;
    }

    public enum UserType {
        Customer,
        Employee,
        ;
    }
}
