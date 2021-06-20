package ru.kim.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.kim.addressbook.model.ContactData;
import ru.kim.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactEditTests extends TestBase {

    @Test
    public void testContactEdition() {
        if (!app.getHomeHelper().isThereAContact()) {
            app.getHomeHelper().createContact(new ContactData("testFirstName1", "testMiddleName1", "testLastName1",
                    "testNickName1", "testing Company1", "Testing Address1", "choson@bk.ru"), app);
        }
        List<ContactData> before = app.getHomeHelper().getContactList();
        app.getHomeHelper().clickToEditContract(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "testFirstNameEdit", "testMiddleNameEdit", "testLastNameEdit",
                "testNickNameEdit", "testing CompanyEdit", "Testing Address Edit", "choson@bk.ru");
        app.getHomeHelper().fillNewContact(contact);
        app.getHomeHelper().clickToUpdateContact();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getHomeHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
