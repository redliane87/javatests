package my.pkg.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{



    public NavigationHelper(WebDriver wd) {
        super (wd);
    }

    public void gotoHomePage(String home, ContactHelper contactHelper) {
        contactHelper.selectAdress(By.linkText(home));
    }
}
