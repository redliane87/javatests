package my.pkg.addressbook.appmanager;

import my.pkg.addressbook.model.GroupData;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillAddressForm(GroupData groupData) {
        type(By.name("firstname"), groupData.getFirstname());
        type(By.name("middlename"), groupData.getMiddlename());
        type(By.name("lastname"), groupData.getLastname());
        type(By.name("nickname"), groupData.getNickname());
        type(By.name("address"), groupData.getAddress());
        clear(By.name("email"));
        sendKeys(By.name("email"), groupData.getEmail());
        clear(By.name("email2"));
        sendKeys(By.name("email2"), groupData.getEmail2());
        clear(By.name("email3"));
        sendKeys(By.name("email3"), groupData.getEmail3());
        getClick(By.name("bday"));
        Select("bday").selectByVisibleText(groupData.getBday());
        getClick(By.name("bday"));
        getClick(By.name("bmonth"));
        Select("bmonth").selectByVisibleText(groupData.getBmonth());
        getClick(By.name("bmonth"));
        type(By.name("byear"), groupData.getByear());
        getClick(By.xpath("//input[21]"));
    }

    @NotNull
    public Select Select(String bday) {
        return new Select(wd.findElement(By.name(bday)));
    }

    public void initAdressCreation(String s) {
        getClick(By.linkText(s));
    }

    public void deleteAdress(By xpath) {
        getClick(xpath);
    }

    public void selectAdress(By id) {
        getClick(id);
    }
}
