package my.pkg.mantis.tests;

import my.pkg.mantis.model.MailMessage;
import my.pkg.mantis.model.UserData;
import my.pkg.mantis.model.Users;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangeUserPassTests extends TestBase {
    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }
    @Test
    public void ChangeUserPass() throws IOException {
        Users users = app.db().usersWithoutAdmin();

        UserData user = users.iterator().next();
        String userName = user.getName();
        String userEmail = user.getEmail();
        String newPassword = "qwerty";
        app.session().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        app.goTo().managePage();
        app.goTo().usersManageTab();
        app.user().selectById(user.getId());
        app.user().initPasswordReset();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 60000);
        String confirmationLink = app.mail().findConfirmationLink(mailMessages, userEmail);
        app.registration().finish(confirmationLink, newPassword);
        assertTrue(app.newSession().login(userName, newPassword));
    }
   @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }

}
  /*  public void ChangeUserPass() throws IOException {
        app.registration().loginWithAdmin();
        app.getDriver().get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
        app.registration().selectUser();

    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter(m -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }*/

