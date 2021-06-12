package ru.kim.addressbook.tests;

import org.testng.annotations.Test;
import ru.kim.addressbook.model.ContactData;

public class ContactEditTests extends TestBase {

    @Test
    public void testContactEdition() {
        if (!app.getHomeHelper().isThereAContact()) {
            app.getHomeHelper().createContact(new ContactData("testFirstName1", "testMiddleName1", "testLastName1",
                    "testNickName1", "testing Company1", "Testing Address1", "choson@bk.ru"), app);
        }
        app.getHomeHelper().clickToEditContract();
        app.getHomeHelper().fillNewContact(new ContactData("testFirstNameEdit", "testMiddleNameEdit", "testLastNameEdit",
                "testNickNameEdit", "testing CompanyEdit", "Testing Address Edit", "choson@bk.ru"));
        app.getHomeHelper().clickToUpdateContact();
        app.getNavigationHelper().gotoHomePage();
    }
}
