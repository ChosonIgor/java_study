package ru.kim.addressbook.tests;

import org.testng.annotations.Test;
import ru.kim.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {

    @Test
    public void testContactEmail() {
        app.goTo().HomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllEmail(), equalTo(mergeEmail(contactFromEditForm)));
    }

    private String mergeEmail(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().map(ContactEmailTests::cleaned)
                .filter((s) -> !s.equals("")).collect(Collectors.joining("\n"));
    }

    public static String cleaned(String email) {
        return email.replace("\\s", "").replace("[-()]", "");
    }


}
