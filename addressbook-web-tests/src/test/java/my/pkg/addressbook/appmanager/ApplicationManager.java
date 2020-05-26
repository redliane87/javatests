package my.pkg.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private final AlertHelper alertHelper = new AlertHelper();
    private SessionHelper sessionHelper;
    private final NavigationHelper navigationHelper = new NavigationHelper();
    private ContactHelper contactHelper;

    public void init() {
        alertHelper.wd = new FirefoxDriver();
        alertHelper.wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        alertHelper.wd.get("http://localhost/addressbook/");
        contactHelper = new ContactHelper(alertHelper.wd);
        sessionHelper = new SessionHelper(alertHelper.wd);
        sessionHelper.login("admin", "secret");
    }



    public void stop() {
        navigationHelper.gotoHomePage("home", contactHelper);
        logout("Logout");
        alertHelper.wd.quit();
    }

    public void logout(String logout) {
        contactHelper.selectAdress(By.linkText(logout));
    }

    public boolean isElementPresent(By by) {
      try {
        alertHelper.wd.findElement(by);
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
