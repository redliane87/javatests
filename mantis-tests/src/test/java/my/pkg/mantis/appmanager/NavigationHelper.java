package my.pkg.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {
    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void usersManageTab() {
        click(By.cssSelector("a[href='/mantis-1.2.20/manage_user_page.php']"));

    }

    public void managePage() {
        click(By.cssSelector("a[href='/mantis-1.2.20/manage_overview_page.php']"));

    }
}
