package ru.kim.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import ru.kim.addressbook.appmanager.ApplicationManager;
import ru.kim.addressbook.model.ContactData;
import ru.kim.addressbook.model.Contacts;
import ru.kim.addressbook.model.GroupData;
import ru.kim.addressbook.model.Groups;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {
    Logger logger = (Logger) LoggerFactory.getLogger(TestBase.class);
    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

    @BeforeSuite()
    public void setUp() throws IOException {
        app.init();
    }

    @AfterSuite()
    public void tearDown() {
        app.stop();
    }

    @BeforeMethod(alwaysRun = true)
    public void logTestStart(Method m) {
        logger.info("Start test " + m.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m) {
        logger.info("Stop test " + m.getName());
    }


    public void verifyGroupListInUi() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            assertThat(uiGroups, equalTo(dbGroups.stream()
                    .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }


    public void verifyContactListInUi() {
        if (Boolean.getBoolean("verifyUI")) {
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.contact().all();
            assertThat(uiContacts, equalTo(dbContacts.stream()
                    .map((g) -> new ContactData().withId(g.getId()).withLastName(g.getLastName()).withFirstName(g.getFirstName())
                    .withAddress(g.getAddress()).withEmail(g.getEmail()))
                    .collect(Collectors.toSet())));
        }
    }

}
