package my.pkg.addressbook.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper {

    public void gotoHomePage(String home, ContactHelper contactHelper) {
        contactHelper.selectAdress(By.linkText(home));
    }
}
