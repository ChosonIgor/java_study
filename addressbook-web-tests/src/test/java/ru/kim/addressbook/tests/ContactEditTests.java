package ru.kim.addressbook.tests;

import org.testng.annotations.Test;
import ru.kim.addressbook.model.ContactData;

public class ContactEditTests extends TestBase {

    @Test
    public void testContactEdition() {
        app.getHomeHelper().clickToEditContract();
        app.getHomeHelper().fillNewContact(new ContactData("testFirstNameEdit", "testMiddleNameEdit", "testLastNameEdit",
                "testNickNameEdit", "testing CompanyEdit", "Testing Address Edit", "choson@bk.ru"));
        app.getHomeHelper().clickToUpdateContact();
        app.getNavigationHelper().gotoHomePage();
    }
}
