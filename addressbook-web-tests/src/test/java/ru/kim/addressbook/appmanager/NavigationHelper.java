package ru.kim.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper {
    private WebDriver wd;

    public NavigationHelper(WebDriver wd) {
        this.wd = wd;
    }
    public void submitNewContact() {
        wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    }
    public void gotoGroupPage() {
        wd.findElement(By.linkText("groups")).click();
    }

    public void getHome() {
        wd.findElement(By.linkText("home")).click();
    }
}
