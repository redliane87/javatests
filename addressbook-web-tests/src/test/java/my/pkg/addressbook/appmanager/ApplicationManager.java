package my.pkg.addressbook.appmanager;

import my.pkg.addressbook.model.ContactData;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class ApplicationManager {
    public WebDriver wd;
    private AlertHelper alertHelper;
    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;

    public void init() {
       wd = new FirefoxDriver();
       wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
       wd.get("http://127.0.0.1/addressbook/group.php");
       groupHelper = new GroupHelper(wd);
       navigationHelper = new NavigationHelper(wd);
       sessionHelper = new SessionHelper(wd);
       alertHelper = new AlertHelper(wd);
       sessionHelper.login("admin", "secret");
    }


    public void stop() {
        gotoHomePage();
        logout();
        wd.quit();
    }

    public void logout() {
      wd.findElement(By.linkText("Logout")).click();
    }

    public void gotoHomePage() {
      wd.findElement(By.linkText("home")).click();
    }

    public boolean isElementPresent(By by) {
      try {
        wd.findElement(by);
        return true;
      } catch (NoSuchElementException e) {
        return false;
      }
    }

    public void sobmitContactCreation() {
      wd.findElement(By.xpath("//input[21]")).click();
    }

    public void fillContactForm(ContactData contactData) {
      wd.findElement(By.name("firstname")).click();
      wd.findElement(By.name("firstname")).clear();
      wd.findElement(By.name("firstname")).sendKeys(contactData.getfName());
      wd.findElement(By.name("middlename")).click();
      wd.findElement(By.name("middlename")).clear();
      wd.findElement(By.name("middlename")).sendKeys(contactData.getMidName());
      wd.findElement(By.name("lastname")).click();
      wd.findElement(By.name("lastname")).clear();
      wd.findElement(By.name("lastname")).sendKeys(contactData.getLastName());
      wd.findElement(By.name("nickname")).click();
      wd.findElement(By.name("nickname")).clear();
      wd.findElement(By.name("nickname")).sendKeys(contactData.getNickName());
      wd.findElement(By.name("mobile")).click();
      wd.findElement(By.name("mobile")).clear();
      wd.findElement(By.name("mobile")).sendKeys(contactData.getMobPhone());
      wd.findElement(By.name("email")).click();
      wd.findElement(By.name("email")).clear();
      wd.findElement(By.name("email")).sendKeys(contactData.getEmail());
    }

    public void initContact() {
      wd.findElement(By.linkText("add new")).click();
    }

    public void selectDeleteContact() {
      wd.findElement(By.xpath("//input[@value='Delete']")).click();
      assertTrue(alertHelper.closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    }

    public void selectContact() {
      wd.findElement(By.xpath("//tr[8]/td/input")).click();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public AlertHelper getAlertHelper() {
        return alertHelper;
    }
}
