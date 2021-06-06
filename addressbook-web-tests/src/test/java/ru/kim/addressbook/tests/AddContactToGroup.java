package ru.kim.addressbook.tests;

import org.testng.annotations.Test;

public class AddContactToGroup extends TestBase {

    @Test
    public void testAddContactToGroup() {
        app.getHomeHelper().selectContract();
        app.getHomeHelper().addSelectedContactToGroup();
        app.getNavigationHelper().gotoHomePage();
    }
}
