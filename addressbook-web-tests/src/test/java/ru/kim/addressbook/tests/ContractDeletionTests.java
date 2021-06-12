package ru.kim.addressbook.tests;

import org.testng.annotations.Test;
import ru.kim.addressbook.model.ContactData;

public class ContractDeletionTests extends TestBase {

    @Test
    public void testContractDeletion() {
        if (!app.getHomeHelper().isThereAContact()) {
            app.getHomeHelper().createContact(new ContactData("testFirstName1", "testMiddleName1", "testLastName1",
                    "testNickName1", "testing Company1", "Testing Address1", "choson@bk.ru"), app);
        }
        app.getHomeHelper().selectContract();
        app.getHomeHelper().deleteSelectedContact();
        app.getHomeHelper().acceptAlert();
        app.getNavigationHelper().gotoHomePage();
    }
}
