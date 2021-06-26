package ru.kim.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.kim.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ContactEditTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData().withFirstName("testFirstName1").withMiddleName("testMiddleName1")
                    .withLastName("testLastName1").withNickName("testNickName1").withCompany("testing Company1")
                    .withAddress("Testing Address1").withEmail("choson@bk.ru"), app);
        }
    }

    @Test
    public void testContactEdition() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().clickToEditContract(index);
        ContactData contact = new ContactData().withId(before.get(before.size() - 1).getId()).withFirstName("testFirstNameEdit")
                .withMiddleName("testMiddleNameEdit").withLastName("testLastNameEdit").withNickName("testNickNameEdit")
                .withCompany("testing CompanyEdit").withAddress("Testing Address Edit").withEmail("choson@bk.ru");
        app.contact().modify(contact, app);
        List<ContactData> after = app.contact().list();
        assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        assertEquals(before, after);
    }


}
