package com.github.shniu.slides.examples.maximizingmocks;


import com.github.shniu.slides.examples.maximizingmocks.domain.Company;
import com.github.shniu.slides.examples.maximizingmocks.domain.User;
import com.github.shniu.slides.examples.maximizingmocks.infra.DomainLogger;
import com.github.shniu.slides.examples.maximizingmocks.infra.EventDispatcher;
import com.github.shniu.slides.examples.maximizingmocks.infra.MessageBus;

// @RestController
public class UserController {
    private Database database;
    private EventDispatcher eventDispatcher;

    public UserController(Database database, MessageBus messageBus, DomainLogger domainLogger) {
        this.database = database;
        this.eventDispatcher = new EventDispatcher(messageBus, domainLogger);
    }

    public String changeEmail(int userId, String email) {
        Object[] userData = database.getUserById(userId);
        User user = UserFactory.create(userData);

        if (!user.canChangeEmail()) {
            throw new EmailCanNotChangeException();
        }

        Object[] companyData = database.getCompany(user.getCompanyId());
        Company company = CompanyFactory.create(companyData);

        user.changeEmail(email, company);

        database.saveCompany(company);
        database.saveUser(user);
        eventDispatcher.dispatch(user.getDomainEvents());

        return "Success";
    }
}
