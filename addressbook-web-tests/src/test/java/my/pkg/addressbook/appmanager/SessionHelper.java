package my.pkg.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {


    public SessionHelper(WebDriver wd) {
        super(wd);
    }
    public void login(String username, String password) {
        type (By.name("user"),username);
        type (By.name("pass"),password);
        supmit(By.id("LoginForm"));
    }

    public void supmit(By locator) {
        wd.findElement(locator).submit();
    }
}
