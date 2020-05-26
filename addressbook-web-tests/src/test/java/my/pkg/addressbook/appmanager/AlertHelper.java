package my.pkg.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class AlertHelper extends HelperBase {
    public boolean acceptNextAlert = true;

    public AlertHelper(WebDriver wd) {
        super(wd);
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
