package ru.kim.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.kim.addressbook.model.ContactData;

import java.util.List;

public class ContractDeletionTests extends TestBase {

    @Test
    public void testContractDeletion() {
        if (!app.getHomeHelper().isThereAContact()) {
            app.getHomeHelper().createContact(new ContactData("testFirstName1", "testMiddleName1", "testLastName1",
                    "testNickName1", "testing Company1", "Testing Address1", "choson@bk.ru"), app);
        }
        List<ContactData> before = app.getHomeHelper().getContactList();
        app.getHomeHelper().selectContract(before.size() - 1);
        app.getHomeHelper().deleteSelectedContact();
        app.getHomeHelper().acceptAlert();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getHomeHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(after, before);
    }
}
