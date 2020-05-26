package my.pkg.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    WebDriver wd;
    private AlertHelper alertHelper;
    private SessionHelper sessionHelper;
    private  NavigationHelper navigationHelper;
    private ContactHelper contactHelper;

    public void init() {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/");
        contactHelper = new ContactHelper(wd);
        sessionHelper = new SessionHelper(wd);
        alertHelper = new AlertHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper.login("admin", "secret");
    }



    public void stop() {
        navigationHelper.gotoHomePage("home", contactHelper);
        logout("Logout");
        wd.quit();
    }

    public void logout(String logout) {
        contactHelper.selectAdress(By.linkText(logout));
    }

    public boolean isElementPresent(By by) {
      try {
        wd.findElement(by);
        return true;
      } catch (NoSuchElementException e) {
        return false;
      }
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public AlertHelper getAlertHelper() {
        return alertHelper;
    }
}
