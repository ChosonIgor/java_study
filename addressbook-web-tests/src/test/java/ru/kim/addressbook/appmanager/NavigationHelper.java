package ru.kim.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void initNewContact() {
        click(By.linkText("add new"));
    }

    public void groupPage() {
        click(By.linkText("groups"));
    }

    public void HomePage() {
        click(By.linkText("home"));
    }
}
