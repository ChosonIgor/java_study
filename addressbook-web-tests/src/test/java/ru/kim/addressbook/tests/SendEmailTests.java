package ru.kim.addressbook.tests;

import org.testng.annotations.Test;

public class SendEmailTests extends TestBase {

    @Test
    public void testContactSendEmail() {
        app.contact().select(0);
        app.contact().clickToSendEmail();
    }
}
