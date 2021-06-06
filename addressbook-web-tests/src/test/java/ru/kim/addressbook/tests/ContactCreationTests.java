package ru.kim.addressbook.tests;

import org.testng.annotations.Test;
import ru.kim.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().initNewContact();
        app.getContactHelper().fillNewContact(new ContactData("testFirstName", "testMiddleName", "testLastName",
                "testNickName", "testing Company", "Testing Address"));
        app.getContactHelper().submitNewContact();
        app.getNavigationHelper().gotoHomePage();
    }

}
