package ru.kim.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.kim.addressbook.model.GroupData;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().GroupPage();
        if (app.Group().list().size() == 0) {
            app.Group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupDeletion() {
        List<GroupData> before = app.Group().list();
        int index = before.size() - 1;
        app.Group().deleteSelectedGroup(index);
        List<GroupData> after = app.Group().list();
        assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        assertEquals(after, before);
    }



}
