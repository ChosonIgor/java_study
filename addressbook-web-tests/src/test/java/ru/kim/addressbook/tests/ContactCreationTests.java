package ru.kim.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.kim.addressbook.model.ContactData;
import ru.kim.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        List<ContactData> before = app.getHomeHelper().getContactList();
        ContactData contact = new ContactData("testFirstName1", "testMiddleName1", "testLastName1",
                "testNickName1", "testing Company1", "Testing Address1", "choson@bk.ru");
        app.getHomeHelper().createContact(contact, app);
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getHomeHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
