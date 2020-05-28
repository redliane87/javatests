package my.pkg.addressbook.appmanager;

import my.pkg.addressbook.model.ContactData;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getfName());
        type(By.name("middlename"), contactData.getMidName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("mobile"), contactData.getMobPhone());
        type(By.name("email"), contactData.getEmail());

    }

    public void initContact() {
        click(By.linkText("add new"));
    }

    public void selectDeleteContact() {
        click(By.xpath("//input[@value='Delete']"));
        assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    }

    public void selectContact() {
        click(By.xpath("//tr[8]/td/input"));
    }

    public void initMidificationContact() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactModification() {
        click(By.xpath("//input[22]"));
    }
}
