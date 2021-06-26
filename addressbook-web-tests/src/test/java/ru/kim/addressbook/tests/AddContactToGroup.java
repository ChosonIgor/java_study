package ru.kim.addressbook.tests;

import org.testng.annotations.Test;

public class AddContactToGroup extends TestBase {

    @Test
    public void testAddContactToGroup() {
        app.contact().select(0);
        app.contact().addSelectedContactToGroup();
        app.goTo().HomePage();
    }
}
