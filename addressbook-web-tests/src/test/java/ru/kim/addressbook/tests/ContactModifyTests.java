package ru.kim.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.kim.addressbook.model.ContactData;
import ru.kim.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModifyTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstName("testFirstName1").withMiddleName("testMiddleName1")
                    .withLastName("testLastName1").withNickName("testNickName1").withCompany("testing Company1")
                    .withAddress("Testing Address1").withEmail("choson@bk.ru"), app);
        }
    }

    @Test
    public void testContactEdition() {
        Contacts before = app.contact().all();
        ContactData modifyContact = before.iterator().next();
        app.contact().clickToEditContract(modifyContact);
        ContactData contact = new ContactData().withId(modifyContact.getId()).withFirstName("testFirstNameEdit")
                .withMiddleName("testMiddleNameEdit").withLastName("testLastNameEdit").withNickName("testNickNameEdit")
                .withCompany("testing CompanyEdit").withAddress("Testing Address Edit").withEmail("choson@bk.ru");
        app.contact().modify(contact, app);
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size()));;
        assertThat(after, equalTo(before.without(modifyContact).withAdded(contact)));
    }


}
