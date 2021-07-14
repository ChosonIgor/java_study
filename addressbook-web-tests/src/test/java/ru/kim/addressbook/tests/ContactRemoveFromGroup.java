package ru.kim.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.kim.addressbook.model.ContactData;
import ru.kim.addressbook.model.Contacts;
import ru.kim.addressbook.model.GroupData;
import ru.kim.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveFromGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstName("testFirstName1").withMiddleName("testMiddleName1")
                    .withLastName("testLastName1").withNickName("testNickName1").withCompany("testing Company1")
                    .withAddress("Testing Address1").withEmail("choson@bk.ru"), app);
        }

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
            app.goTo().HomePage();
        }
    }

    @Test
    public void testContactRemoveFromGroup() {
        Contacts contacts = app.db().contacts();
        int contactsSize = contacts.size();
        ContactData contactForRemoveFromGroup = null;
        GroupData groupOfRemove = null;
        int contactId = 0;
        for (ContactData contact : contacts) {
            contactsSize--;
            if(contact.getGroups().size() > 0) {
                contactForRemoveFromGroup = contact;
                groupOfRemove = contact.getGroups().iterator().next();
                contactForRemoveFromGroup.removeGroup(groupOfRemove);
                contactId = contactForRemoveFromGroup.getId();
                break;
            }
            if(contactsSize == 0) {
                contactForRemoveFromGroup = contacts.iterator().next();
                contactId = contactForRemoveFromGroup.getId();
                groupOfRemove = app.db().groups().iterator().next();
                app.contact().addSelectedContactToGroup(contactForRemoveFromGroup, groupOfRemove, app);
            }
        }
        Groups groupsForCheck = contactForRemoveFromGroup.getGroups();
        app.contact().removeSelectedContactFromGroup(contactForRemoveFromGroup, groupOfRemove, app);
        Contacts after = app.db().contacts();
        for (ContactData contact : after) {
            if (contact.getId() == contactId) {
                assertThat(contact.getGroups(), equalTo(groupsForCheck));
            }
        }
    }



}
