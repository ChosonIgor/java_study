package ru.kim.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.kim.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.assertEquals;


public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData().withFirstName("testFirstName1").withMiddleName("testMiddleName1")
                .withLastName("testLastName1").withNickName("testNickName1").withCompany("testing Company1")
                .withAddress("Testing Address1").withEmail("choson@bk.ru");
        app.contact().create(contact, app);
        app.goTo().HomePage();
        List<ContactData> after = app.contact().list();
        assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        assertEquals(before, after);
    }
}
