package com.github.shniu.slides.examples.valuabletests.v3;

// v2
// UserController as the role of Application Service
public class UserController {
    private Database database = new Database();
    private MessageBus messageBus = new MessageBus();

    public String changeEmail(int userId, String newEmail) {
        Object[] data = database.getUserById(userId);
        User user = UserFactory.create(data);

        Object[] companyData = database.getCompany();
        Company company = CompanyFactory.create(companyData);

        user.changeEmail(newEmail, company);

        database.saveCompany(company);
        database.saveUser(user);

        messageBus.sendEmailChangedEvent(userId, newEmail);

        return "Success";
    }




    //// Opt: 来个新的需求 -> 一旦用户确认了邮件，就不能再修改了
    public String changeEmailV2(int userId, String newEmail) {
        Object[] data = database.getUserById(userId);
        User user = UserFactory.create(data);

        Object[] companyData = database.getCompany();
        Company company = CompanyFactory.create(companyData);

        user.changeEmail(newEmail, company);

        database.saveCompany(company);
        database.saveUser(user);

        messageBus.sendEmailChangedEvent(userId, newEmail);

        return "Success";
    }
}
