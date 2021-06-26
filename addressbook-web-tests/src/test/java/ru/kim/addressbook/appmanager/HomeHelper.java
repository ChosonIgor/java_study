package ru.kim.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.kim.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

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

    public void select(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void delete(int index, ApplicationManager app) {
       select(index);
        deleteSelectedContact();
        acceptAlert();
        app.goTo().HomePage();
    }

    public void modify(ContactData contact, ApplicationManager app) {
        fillNewContact(contact);
        clickToUpdateContact();
        app.goTo().HomePage();
    }

    public void addSelectedContactToGroup() {
        click(By.name("add"));
    }

    public void clickToSendEmail() {
        click(By.xpath("//input[@value='Send e-Mail']"));
    }

    public void clickToEditContract(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
//        click(By.xpath("//img[@alt='Edit']"));
    }
    public void clickToUpdateContact() {
        click(By.name("update"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void create(ContactData contact, ApplicationManager app) {
        app.goTo().initNewContact();
        fillNewContact(contact);
        submitNewContact();
        app.goTo().HomePage();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {
            String lastName = element.findElement(By.xpath("./td[2]")).getText();
            String firstName = element.findElement(By.xpath("./td[3]")).getText();
            String address = element.findElement(By.xpath("./td[4]")).getText();
            String email = element.findElement(By.xpath("./td[5]/a")).getText();
            int id = Integer.parseInt(element.findElement(By.xpath(".//input")).getAttribute("value"));
            ContactData contact = new ContactData(id, firstName, null, lastName, null, null, address, email);
            contacts.add(contact);
        }
        return contacts;
    }
}
