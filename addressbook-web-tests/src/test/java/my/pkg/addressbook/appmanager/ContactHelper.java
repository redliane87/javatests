package my.pkg.addressbook.appmanager;

import my.pkg.addressbook.model.ContactData;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillAddressForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("address"), contactData.getAddress());
        clear(By.name("email"));
        sendKeys(By.name("email"), contactData.getEmail());
        clear(By.name("email2"));
        sendKeys(By.name("email2"), contactData.getEmail2());
        clear(By.name("email3"));
        sendKeys(By.name("email3"), contactData.getEmail3());
        getClick(By.name("bday"));
        Select("bday").selectByVisibleText(contactData.getBday());
        getClick(By.name("bday"));
        getClick(By.name("bmonth"));
        Select("bmonth").selectByVisibleText(contactData.getBmonth());
        getClick(By.name("bmonth"));
        type(By.name("byear"), contactData.getByear());
        getClick(By.xpath("//input[21]"));
    }

    @NotNull
    public Select Select(String bday) {
        return new Select(wd.findElement(By.name(bday)));
    }

    public void initAdressCreation() {
        getClick(By.linkText("add new"));
    }

    public void deleteAdress(By xpath) {
        getClick(xpath);
    }

    public void selectAdress(By id) {
        getClick(id);
    }
}
