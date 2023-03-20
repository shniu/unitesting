package com.github.shniu.slides.examples.valuabletests.v4;

import com.github.shniu.slides.examples.valuabletests.v4.event.UserEmailChangedEvent;

// v2
// UserController as the role of Application Service
public class UserController {
    private Database database = new Database();
    private MessageBus messageBus = new MessageBus();

    //// 来个新的需求 -> 一旦用户确认了邮件，就不能再修改了

    public String changeEmail(int userId, String newEmail) {
        Object[] data = database.getUserById(userId);
        User user = UserFactory.create(data);

        Object[] companyData = database.getCompany();
        Company company = CompanyFactory.create(companyData);

        // 如果用户已经确认过了，不能被修改，但是这里会把 company 先查询出来，会带来不必要的性能和资源损耗
        String error = user.changeEmail(newEmail, company);
        if (error != null) {
            return error;
        }

        database.saveCompany(company);
        database.saveUser(user);

        messageBus.sendEmailChangedEvent(userId, newEmail);

        return "Success";
    }

    public String changeEmailV2(int userId, String newEmail) {
        Object[] data = database.getUserById(userId);
        User user = UserFactory.create(data);

        // 但是这样，就又会导致另外一个问题，changeEmail 和 isEmailConfirmed 是分离的
        // 直接调用 changeEmail 仍然可以修改邮箱，没有起到保护作用，容易写出有 bug 的代码
        if (user.isEmailConfirmed()) {
            return "Email can't change once confirmed.";
        }

        Object[] companyData = database.getCompany();
        Company company = CompanyFactory.create(companyData);

        String error = user.changeEmail(newEmail, company);
        if (error != null) {
            return error;
        }

        database.saveCompany(company);
        database.saveUser(user);

        messageBus.sendEmailChangedEvent(userId, newEmail);

        return "Success";
    }

    /// A better one.
    public String changeEmailV3(int userId, String newEmail) {
        Object[] data = database.getUserById(userId);
        User user = UserFactory.create(data);

        String error = user.canChangeEmail();
        if (error != null) {
            return error;
        }

        Object[] companyData = database.getCompany();
        Company company = CompanyFactory.create(companyData);

        error = user.changeEmail(newEmail, company);
        if (error != null) {
            return error;
        }

        database.saveCompany(company);
        database.saveUser(user);

        /// 这里存在一个问题：无论email是否修改了，都会发送message出去
        messageBus.sendEmailChangedEvent(userId, newEmail);

        return "Success";
    }

    /// A better one.
    public String changeEmailV4(int userId, String newEmail) {
        Object[] data = database.getUserById(userId);
        User user = UserFactory.create(data);

        String error = user.canChangeEmail();
        if (error != null) {
            return error;
        }

        Object[] companyData = database.getCompany();
        Company company = CompanyFactory.create(companyData);

        error = user.changeEmail(newEmail, company);
        if (error != null) {
            return error;
        }

        database.saveCompany(company);
        database.saveUser(user);

        for (UserEmailChangedEvent userDomainEvent : user.getUserDomainEvents()) {
            messageBus.sendEmailChangedEvent(userDomainEvent.getUserId(), userDomainEvent.getNewEmail());
        }

        return "Success";
    }
}
