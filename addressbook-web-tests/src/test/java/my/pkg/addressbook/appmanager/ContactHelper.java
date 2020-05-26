package my.pkg.addressbook.appmanager;

import my.pkg.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ContactHelper {
    private WebDriver wd;

    public ContactHelper(WebDriver wd) {
        this.wd =wd;
    }

    public void fillAddressForm(GroupData groupData) {
      wd.findElement(By.name("firstname")).click();
      wd.findElement(By.name("firstname")).clear();
      wd.findElement(By.name("firstname")).sendKeys(groupData.getFirstname());
      wd.findElement(By.name("middlename")).click();
      wd.findElement(By.name("middlename")).clear();
      wd.findElement(By.name("middlename")).sendKeys(groupData.getMiddlename());
      wd.findElement(By.name("lastname")).click();
      wd.findElement(By.name("lastname")).clear();
      wd.findElement(By.name("lastname")).sendKeys(groupData.getLastname());
      wd.findElement(By.name("nickname")).click();
      wd.findElement(By.name("nickname")).clear();
      wd.findElement(By.name("nickname")).sendKeys(groupData.getNickname());
      wd.findElement(By.name("address")).click();
      wd.findElement(By.name("address")).clear();
      wd.findElement(By.name("address")).sendKeys(groupData.getAddress());
      wd.findElement(By.name("email")).clear();
      wd.findElement(By.name("email")).sendKeys(groupData.getEmail());
      wd.findElement(By.name("email2")).clear();
      wd.findElement(By.name("email2")).sendKeys(groupData.getEmail2());
      wd.findElement(By.name("email3")).clear();
      wd.findElement(By.name("email3")).sendKeys(groupData.getEmail3());
      wd.findElement(By.name("bday")).click();
      new Select(wd.findElement(By.name("bday"))).selectByVisibleText(groupData.getBday());
      wd.findElement(By.name("bday")).click();
      wd.findElement(By.name("bmonth")).click();
      new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(groupData.getBmonth());
      wd.findElement(By.name("bmonth")).click();
      wd.findElement(By.name("byear")).click();
      wd.findElement(By.name("byear")).clear();
      wd.findElement(By.name("byear")).sendKeys(groupData.getByear());
      wd.findElement(By.xpath("//input[21]")).click();
    }

    public void initAdressCreation(String s) {
      wd.findElement(By.linkText(s)).click();
    }

    public void deleteAdress(By xpath) {
      wd.findElement(xpath).click();
    }

    public void selectAdress(By id) {
      wd.findElement(id).click();
    }
}
