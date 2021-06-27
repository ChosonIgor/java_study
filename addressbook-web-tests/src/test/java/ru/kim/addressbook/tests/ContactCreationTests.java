package ru.kim.addressbook.tests;

import org.testng.annotations.Test;
import ru.kim.addressbook.model.ContactData;
import ru.kim.addressbook.model.Contacts;
import java.util.Set;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstName("testFirstName1").withMiddleName("testMiddleName1")
                .withLastName("testLastName1").withNickName("testNickName1").withCompany("testing Company1")
                .withAddress("Testing Address1").withEmail("choson@bk.ru");
        app.contact().create(contact, app);
        app.goTo().HomePage();
        Set<ContactData> after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}

