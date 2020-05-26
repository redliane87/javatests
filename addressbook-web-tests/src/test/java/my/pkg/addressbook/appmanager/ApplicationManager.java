package my.pkg.addressbook.appmanager;

import my.pkg.addressbook.model.GroupData;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    public WebDriver wd;
    public boolean acceptNextAlert = true;

    public void init() {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/");
        login("admin", "secret");
    }

    public void login(String username, String password) {
      wd.findElement(By.name("user")).clear();
      wd.findElement(By.name("user")).sendKeys(username);
      wd.findElement(By.name("pass")).clear();
      wd.findElement(By.name("pass")).sendKeys(password);
      wd.findElement(By.id("LoginForm")).submit();
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

    public void stop() {
        gotoHomePage("home");
        logout("Logout");
        wd.quit();
    }

    public void logout(String logout) {
      wd.findElement(By.linkText(logout)).click();
    }

    public void gotoHomePage(String home) {
      wd.findElement(By.linkText(home)).click();
    }

    public boolean isElementPresent(By by) {
      try {
        wd.findElement(by);
        return true;
      } catch (NoSuchElementException e) {
        return false;
      }
    }

    public boolean isAlertPresent() {
      try {
        wd.switchTo().alert();
        return true;
      } catch (NoAlertPresentException e) {
        return false;
      }
    }

    public void deleteAdress(By xpath) {
      wd.findElement(xpath).click();
    }

    public void selectAdress(By id) {
      wd.findElement(id).click();
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
}
