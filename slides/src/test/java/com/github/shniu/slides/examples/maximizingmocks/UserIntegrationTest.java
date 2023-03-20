package com.github.shniu.slides.examples.maximizingmocks;

import com.github.shniu.slides.examples.maximizingmocks.domain.Company;
import com.github.shniu.slides.examples.maximizingmocks.domain.User;
import com.github.shniu.slides.examples.maximizingmocks.infra.DomainLogger;
import com.github.shniu.slides.examples.maximizingmocks.infra.MessageBus;
import com.github.shniu.slides.examples.maximizingmocks.infra.MessageBusImpl;
import com.github.shniu.slides.examples.maximizingmocks.infra.mq.EventPublisher;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserIntegrationTest {

    // happy path
    @Test
    void should_change_email_from_corporate_to_non_corporate() {
        // given
        Database database = new Database("mysql://127.0.0.1:3306/users");
        User user = createUser("Test01", "test@corp.com", false, database);

        MessageBus messageBus = Mockito.mock(MessageBus.class);
        DomainLogger domainLogger = Mockito.mock(DomainLogger.class);

        UserController sut = new UserController(database, messageBus, domainLogger);

        // when
        String result = sut.changeEmail(user.getUserId(), "new@gmail.com");

        // then
        assertEquals("Success", result);

        Object[] userData = database.getUserById(user.getUserId());
        User userFromDb = UserFactory.create(userData);
        assertEquals("new@gmail.com", userFromDb.getEmail());

        Object[] companyData = database.getCompany(user.getCompanyId());
        Company company = CompanyFactory.create(companyData);
        assertEquals(0, company.members());

        Mockito.verify(messageBus, Mockito.times(1))
                .sendEmailChangedEvent(user.getUserId(), "new@gmail.com");
        Mockito.verifyNoMoreInteractions(messageBus);
    }

    @Test
    void should_throw_email_can_not_change_when_user_is_locked() {
        // A sad path...
    }

    @Test
    void should_change_email_from_corporate_to_non_corporate_v2() {
        // given
        Database database = new Database("mysql://127.0.0.1:3306/users");
        User user = createUser("Test01", "test@corp.com", false, database);

        // 不要 mock MessageBus，去 Mock EventPublisher
        // MessageBus messageBus = Mockito.mock(MessageBus.class);
        EventPublisher eventPublisher = Mockito.mock(EventPublisher.class);
        // 我们改造以后，MessageBus 接口就不在了，只留 MessageBusImpl 即可，可以改名为 MessageBus 更好一些
        MessageBusImpl messageBus = new MessageBusImpl(eventPublisher);
        DomainLogger domainLogger = Mockito.mock(DomainLogger.class);

        // when
        UserController sut = new UserController(database, messageBus, domainLogger);
        String result = sut.changeEmail(user.getUserId(), "new@gmail.com");

        // then
        assertEquals("Success", result);

        Object[] userData = database.getUserById(user.getUserId());
        User userFromDb = UserFactory.create(userData);
        assertEquals("new@gmail.com", userFromDb.getEmail());

        Object[] companyData = database.getCompany(user.getCompanyId());
        Company company = CompanyFactory.create(companyData);
        assertEquals(0, company.members());

//        Mockito.verify(messageBus, Mockito.times(1))
//                .sendEmailChangedEvent(user.getUserId(), "new@gmail.com");
        String expectedPublishContent
                = "Type: user email changed; Id: " + user.getUserId() + "; NewEmail: new@gmail.com";
        Mockito.verify(eventPublisher, Mockito.times(1)).publish(expectedPublishContent);
    }

    @Test
    void should_change_email_from_corporate_to_non_corporate_v3() {
        // given
        Database database = new Database("mysql://127.0.0.1:3306/users");
        User user = createUser("Test01", "test@corp.com", false, database);

        // 不要 mock MessageBus，去 Mock EventPublisher
        // MessageBus messageBus = Mockito.mock(MessageBus.class);
        // EventPublisher eventPublisher = Mockito.mock(EventPublisher.class);
        // 这里使用 Spy 代替 Mock
        EventPublisherSpy eventPublisher = new EventPublisherSpy();

        // 我们改造以后，MessageBus 接口就不在了，只留 MessageBusImpl 即可，可以改名为 MessageBus 更好一些
        MessageBusImpl messageBus = new MessageBusImpl(eventPublisher);
        DomainLogger domainLogger = Mockito.mock(DomainLogger.class);

        // when
        UserController sut = new UserController(database, messageBus, domainLogger);
        String result = sut.changeEmail(user.getUserId(), "new@gmail.com");

        // then
        assertEquals("Success", result);

        Object[] userData = database.getUserById(user.getUserId());
        User userFromDb = UserFactory.create(userData);
        assertEquals("new@gmail.com", userFromDb.getEmail());

        Object[] companyData = database.getCompany(user.getCompanyId());
        Company company = CompanyFactory.create(companyData);
        assertEquals(0, company.members());

//        Mockito.verify(messageBus, Mockito.times(1))
//                .sendEmailChangedEvent(user.getUserId(), "new@gmail.com");
//        String expectedPublishContent
//                = "Type: user email changed; Id: " + user.getUserId() + "; NewEmail: new@gmail.com";
        // Mockito.verify(eventPublisher, Mockito.times(1)).publish(expectedPublishContent);
        eventPublisher.shouldExceptedTimes(1)
                .withEmailChangedMessage(user.getUserId(), "new@gmail.com");
    }

    User createUser(String username, String email, boolean locked, Database db) {
        User user = new User();
        // user.setUsername(username);
        // user.setEmail(email);
        // ...

        db.saveUser(user);
        return user;
    }

    Company createCompany(String companyName, String addr, Database database) {
        Company company = new Company();
        // company.setCompanyName(companyName);
        // ...
        database.saveCompany(company);
        return company;
    }
}