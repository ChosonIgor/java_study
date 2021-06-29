package ru.kim.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.kim.addressbook.model.ContactData;
import ru.kim.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
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

    private void selectById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void delete(ContactData contact, ApplicationManager app) {
        selectById(contact.getId());
        deleteSelectedContact();
        acceptAlert();
        contactsCash = null;
        app.goTo().HomePage();
    }

    public void modify(ContactData contact) {
        fillNewContact(contact);
        clickToUpdateContact();
        contactsCash = null;
        returnToHomePage();
    }

    public void addSelectedContactToGroup() {
        click(By.name("add"));
    }

    public void clickToSendEmail() {
        click(By.xpath("//input[@value='Send e-Mail']"));
    }

    public void clickToEditContract(ContactData contact) {
        wd.findElement(By.xpath("//input[@id='" + contact.getId() + "']/ancestor::tr/td[8]")).click();
    }
    public void clickToUpdateContact() {
        click(By.name("update"));
    }

    public void create(ContactData contact, ApplicationManager app) {
        app.goTo().initNewContact();
        fillNewContact(contact);
        submitNewContact();
        contactsCash = null;
        app.goTo().HomePage();
    }

    private Contacts contactsCash = null;

    public Contacts all() {
        if(contactsCash != null) {
            return new Contacts(contactsCash);
        }
        contactsCash = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.xpath(".//input")).getAttribute("value"));
            String lastName = element.findElement(By.xpath("./td[2]")).getText();
            String firstName = element.findElement(By.xpath("./td[3]")).getText();
            String address = element.findElement(By.xpath("./td[4]")).getText();
            String allEmail = element.findElement(By.xpath("./td[5]")).getText();
            String allPhones = element.findElement(By.xpath("./td[6]")).getText();
            ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName).withAddress(address)
                    .withAllEmail(allEmail).withAllPhones(allPhones);
            contactsCash.add(contact);
        }
        return new Contacts(contactsCash);
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public ContactData infoFromEditForm(ContactData contact) {
        clickToEditContract(contact);
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String middleName = wd.findElement(By.name("middlename")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String nickName = wd.findElement(By.name("nickname")).getAttribute("value");
        String company = wd.findElement(By.name("company")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String homePhone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = wd.findElement(By.name("work")).getAttribute("value");

        return new ContactData().withId(contact.getId()).withFirstName(firstName).withMiddleName(middleName).withLastName(lastName)
                .withNickName(nickName).withCompany(company).withAddress(address).withEmail(email).withEmail2(email2)
                .withEmail3(email3).withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone);
    }
}
