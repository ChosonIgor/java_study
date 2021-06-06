package ru.kim.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        initNewContact();
        fillNewContact(new ContactData("testFirstName", "testMiddleName", "testLastName",
                "testNickName", "testing Company", "Testing Address"));
        submitNewContact();
        getHome();
    }

}
