package ru.kim.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.kim.addressbook.model.ContactData;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class ContractDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData().withFirstName("testFirstName1").withLastName("testLastName1"), app);
        }
    }

    @Test
    public void testContractDeletion() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index, app);
        List<ContactData> after = app.contact().list();
        assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        assertEquals(after, before);
    }


}
