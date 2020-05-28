package my.pkg.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    public WebDriver wd;
    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;

    public void init() {
       wd = new FirefoxDriver();
       wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
       wd.get("http://127.0.0.1/addressbook/");
       groupHelper = new GroupHelper(wd);
       contactHelper = new ContactHelper(wd);
       navigationHelper = new NavigationHelper(wd);
       sessionHelper = new SessionHelper(wd);
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



    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }
}
