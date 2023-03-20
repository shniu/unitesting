package com.github.shniu.slides.examples.valuabletests.v1;

/// v1 is overcomplicated code.
public class User {
    private int userId;
    private String email;
    private UserType userType;

    public void changeEmail(int userId, String newEmail) {
        // 省略了 newEmail 的正确性检查，用户是否存在等 ...

        Object[] data = Database.getUserById(userId);
        this.userId = userId;
        this.email = (String) data[1];
        this.userType = (UserType) data[2];

        if (this.email.equals(newEmail)) {
            return;
        }

        Object[] companyData = Database.getCompany();
        String companyDomainName = (String) companyData[0];
        int numberOfEmployees = (int) companyData[1];

        String newEmailDomain = newEmail.split("@")[1];
        boolean isEmailCorporate = newEmailDomain.equals(companyDomainName);
        UserType newType = isEmailCorporate ? UserType.Employee : UserType.Customer;

        if (!newType.equals(this.userType)) {
            int delta = newType.equals(UserType.Employee) ? 1 : -1;
            int newNumber = numberOfEmployees + delta;
            Database.saveCompany(newNumber);
        }

        this.email = newEmail;
        this.userType = newType;

        Database.saveUser(this);
        MessageBus.sendEmailChangedEvent(this.userId, this.email);
    }

    public enum UserType {
        Customer,
        Employee,
        ;
    }
}




/// Opt 1: 隐式依赖变成显示依赖
/// Opt 2: User 是 Domain，不应该和外部依赖沟通，或者依赖进程外资源，所以就不要负责通信的事情了
/// --- So we can introduce an application service
