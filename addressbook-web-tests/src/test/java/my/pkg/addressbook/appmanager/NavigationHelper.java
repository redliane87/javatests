package my.pkg.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper {

    private WebDriver wd;

    public NavigationHelper(WebDriver wd) {
        this.wd =wd;
    }

    public void gotoHomePage(String home, ContactHelper contactHelper) {
        contactHelper.selectAdress(By.linkText(home));
    }
}
