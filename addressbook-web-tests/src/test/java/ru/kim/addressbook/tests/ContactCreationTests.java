package ru.kim.addressbook.tests;

import org.testng.annotations.Test;
import ru.kim.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().initNewContact();
        app.getHomeHelper().fillNewContact(new ContactData("testFirstName1", "testMiddleName1", "testLastName1",
                "testNickName1", "testing Company1", "Testing Address1", "choson@bk.ru"));
        app.getHomeHelper().submitNewContact();
        app.getNavigationHelper().gotoHomePage();
    }

}
