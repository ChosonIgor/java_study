package ru.kim.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.kim.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().GroupPage();
        List<GroupData> before = app.Group().list();
        GroupData group = new GroupData().withName("test1").withHeader("test2").withFooter("test3");
        app.Group().create(group);
        List<GroupData> after = app.Group().list();
        assertEquals(after.size(), before.size() + 1);


        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        assertEquals(before, after);
    }


}
