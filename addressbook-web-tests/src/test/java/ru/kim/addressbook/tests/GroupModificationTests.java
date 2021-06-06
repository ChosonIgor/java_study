package ru.kim.addressbook.tests;

import org.testng.annotations.Test;
import ru.kim.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test5", "test6", "test7"));
        app.getGroupHelper().submitGroupModification();
        app.getNavigationHelper().gotoHomePage();

    }

}
