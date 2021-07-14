package ru.kim.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.kim.addressbook.model.ContactData;
import ru.kim.addressbook.model.Contacts;
import ru.kim.addressbook.model.GroupData;
import ru.kim.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroup extends TestBase {

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
    public void testAddContactToGroup() {
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        GroupData groupOfAdd = null;
        ContactData contactForAddingInGroup = null;
        int contactId = 0;
        int contactsSize = contacts.size();
        for (ContactData contact : contacts) {
            contactsSize--;
            Groups groupDiff = new Groups(groups);
            groupDiff.removeIf(contact.getGroups() :: contains);
            if (groupDiff.size() > 0) {
                contactForAddingInGroup = contact;
                groupOfAdd = groupDiff.iterator().next();
                contact.inGroup(groupOfAdd);
                contactId = contactForAddingInGroup.getId();
                break;
            }
            if (contactsSize == 0) {
                contactForAddingInGroup = new ContactData().withFirstName("testFirstName1").withLastName("testLastName1");
                app.contact().create(contactForAddingInGroup, app);
                groupOfAdd = groups.iterator().next();
                contacts = app.db().contacts();
                contactForAddingInGroup.withId(contacts.stream().mapToInt((g) -> g.getId()).max().getAsInt()).inGroup(groupOfAdd);
                contactId = contactForAddingInGroup.getId();
            }
        }
        Groups groupsForCheck = contactForAddingInGroup.getGroups();
        app.contact().addSelectedContactToGroup(contactForAddingInGroup, groupOfAdd, app);
        Contacts after = app.db().contacts();
        for (ContactData contact : after) {
            if (contact.getId() == contactId) {
                assertThat(contact.getGroups(), equalTo(groupsForCheck));
            }
        }
    }
}
