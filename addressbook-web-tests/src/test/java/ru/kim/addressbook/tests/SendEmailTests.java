package ru.kim.addressbook.tests;

import org.testng.annotations.Test;

public class SendEmailTests extends TestBase {

    @Test
    public void testContactSendEmail() {
        app.getHomeHelper().selectContract(0);
        app.getHomeHelper().clickToSendEmail();


    }
}
