package my.pkg.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.stream.Collectors;

import static org.openqa.selenium.By.cssSelector;

public class RegistrationHelper extends HelperBase {

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(cssSelector("input[value='Signup']"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(cssSelector("input[value='Update User']"));
    }
    public void loginWithAdmin() {
//        wd.get(app.getProperty("web.baseUrl"));
        type(By.name("username"), app.getProperty("web.adminLogin"));
        type(By.name("password"), app.getProperty("web.adminPassword"));
        click(cssSelector("[type=submit]"));
    }

    public void selectUser() {
        app.getDriver().findElements(cssSelector("tbody [href]"))
                .stream().filter(u -> !u.getText().equals("administrator")).collect(Collectors.toList())
                .get(0).click();
    }
}
