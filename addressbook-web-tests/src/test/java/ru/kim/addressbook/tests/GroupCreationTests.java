package ru.kim.addressbook.tests;
import org.testng.annotations.Test;
import ru.kim.addressbook.model.GroupData;
import ru.kim.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().GroupPage();
        Groups before = app.Group().all();
        GroupData group = new GroupData().withName("test1").withHeader("test2").withFooter("test3");
        app.Group().create(group);
        Groups after = app.Group().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}
