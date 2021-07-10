package ru.kim.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.kim.addressbook.model.GroupData;
import ru.kim.addressbook.model.Groups;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupModification() {
        Groups before = app.db().groups();
        GroupData modifyGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifyGroup.getId()).withName("test5").withHeader("test6").withFooter("test7");
        app.goTo().groupPage();
        app.group().modify(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.without(modifyGroup).withAdded(group)));
    }
}
