package ru.kim.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.kim.addressbook.model.ContactData;
import ru.kim.addressbook.model.Contacts;
import ru.kim.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.json"))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contact = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType());
            return contact.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validContactFromJson")
    public void testContactCreation(ContactData contact) {
        File photo = new File("src/test/resources/tiger.jpg");
        contact.withPhoto(photo);
        Contacts before = app.db().contacts();
        app.contact().create(contact, app);
        app.goTo().HomePage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Set<ContactData> after = app.db().contacts();;
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}

