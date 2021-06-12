package ru.kim.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.kim.addressbook.model.ContactData;

public class HomeHelper extends HelperBase {

    public HomeHelper(WebDriver wd) {
        super(wd);
    }

    public void fillNewContact(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("email"), contactData.getEmail());
    }

    public void submitNewContact() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void selectContract() {
        click(By.name("selected[]"));
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void addSelectedContactToGroup() {
        click(By.name("add"));
    }

    public void clickToSendEmail() {
        click(By.xpath("//input[@value='Send e-Mail']"));
    }

    public void clickToEditContract() {
        click(By.xpath("//img[@alt='Edit']"));
    }
    public void clickToUpdateContact() {
        click(By.name("update"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void createContact(ContactData contact, ApplicationManager app) {
        app.getNavigationHelper().initNewContact();
        fillNewContact(contact);
        submitNewContact();
        app.getNavigationHelper().gotoHomePage();
    }
}
