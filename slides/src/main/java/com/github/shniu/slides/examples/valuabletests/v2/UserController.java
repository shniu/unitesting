package com.github.shniu.slides.examples.valuabletests.v2;

// v2
// UserController as the role of Application Service
public class UserController {
    private Database database = new Database();
    private MessageBus messageBus = new MessageBus();

    public UserController(Database database, MessageBus messageBus) {
        this.database = database;
        this.messageBus = messageBus;
    }

    public String changeEmail(int userId, String newEmail) {
        Object[] data = database.getUserById(userId);
        String currEmail = (String) data[1];
        User.UserType currType = (User.UserType) data[2];
        User user = new User(userId, currEmail, currType);

        Object[] companyData = database.getCompany();
        String companyDomainName = (String) companyData[0];
        int numberOfEmployees = (int) companyData[1];

        int newNumberOfEmployees = user.changeEmail(newEmail, companyDomainName, numberOfEmployees);

        database.saveCompany(newNumberOfEmployees);
        database.saveUser(user);
        messageBus.sendEmailChangedEvent(userId, newEmail);

        return "Success";
    }
}



/// Opt 1: UserController 同样承担了过多的事情，最起码两件
///  1. 编排业务，和 DB / MessageBus 通信
///  2. 构造数据
///  无形中增加了复杂性，并且构造数据的部分也不好用测试来验证，因为要么需要大量的 Mock，要么需要集成测
///  考虑把构造数据和业务编排分离

/// Opt 2: Company 应该提取为一个 Entity
