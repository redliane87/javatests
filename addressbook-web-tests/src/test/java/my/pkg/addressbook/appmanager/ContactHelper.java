package my.pkg.addressbook.appmanager;

import my.pkg.addressbook.model.ContactData;
import my.pkg.addressbook.model.Contacts;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;


import static org.testng.Assert.assertTrue;

public class ContactHelper extends HelperBase {
    public boolean acceptNextAlert = true;

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public String closeAlertAndGetItsText() {
        try {
            Alert alert = wd.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }

    public void sobmitContactCreation() {
        click(By.xpath("//input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getfName());
        type(By.name("middlename"), contactData.getMidName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"),contactData.getAddress());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("email"), contactData.getEmail());
        type(By.name("mobile"), contactData.getMobPhone());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("work"), contactData.getWorkPhone());
        if(creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group"))); // контроль над тем, что поля прикрепления контакта к группам нет совсем (если пявится тест упадет)
        }


    }

    public void initContact() {
        click(By.linkText("add new"));
    }

    public void delete() {
        click(By.xpath("//input[@value='Delete']"));
        assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    }

    public void select(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();

    }
    public void selectById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();

    }
    public ContactData infoFormEditForm(ContactData contactData){
        initModificationsById(contactData.getId());
        String fname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contactData.getId()).withFName(fname).withLastName(lastname).
            withHomePhone(home).withMobPhone(mobile).withWorkPhone(work);
    }

    public void initModifications(int index) {
        wd.findElements (By.xpath("//img[@alt='Edit']")).get(index).click();

    }
    public void initModificationsById(int id) {
        wd.findElement (By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();

    }

    public void submitContactModification() {
        click(By.xpath("//input[22]"));
    }

    public void create(ContactData contactData, boolean creation) {
        initContact();
        fillContactForm(contactData,creation);
        sobmitContactCreation();
        contactCache = null;
    }
    public void modify(int index, ContactData contactData) {
        initModifications(index);
        fillContactForm(contactData, false);
        submitContactModification();
    }
    public void modify(ContactData contactData) {
        initModificationsById(contactData.getId());
        fillContactForm(contactData, false);
        submitContactModification();
        contactCache = null;
    }


    public void delete(int index) {
        select(index);
        acceptNextAlert = true;
        delete();
    }
    public void delete(ContactData delContact) {
        selectById(delContact.getId());
        acceptNextAlert = true;
        delete();
        contactCache = null;
    }

    public boolean isTheAreContact() {
        return isElementPresent(By.name("selected[]"));
    }


    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String fName = element.findElement(By.xpath(".//td[3]")).getText();
            String lastName = element.findElement(By.xpath(".//td[2]")).getText();
            String address = element.findElement(By.xpath(".//td[4]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFName(fName).withLastName(lastName).withAddress(address));
        }

return contacts;

    }
    public Contacts all() {
        if (contactCache != null){
            return new Contacts(contactCache);
        }
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String fName = element.findElement(By.xpath(".//td[3]")).getText();
            String lastName = element.findElement(By.xpath(".//td[2]")).getText();
            String address = element.findElement(By.xpath(".//td[4]")).getText();
            String allphones = element.findElement(By.xpath(".//td[6]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFName(fName).withLastName(lastName).withAddress(address)
                    .withAllphones(allphones));
        }

        return contacts;

    }



}
