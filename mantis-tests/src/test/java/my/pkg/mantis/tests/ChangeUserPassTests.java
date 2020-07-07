package my.pkg.mantis.tests;

import my.pkg.mantis.model.MailMessage;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import java.io.IOException;
import java.util.List;

public class ChangeUserPassTests extends TestBase {
    @Test
    public void ChangeUserPass() throws IOException {
        app.registration().loginWithAdmin();
        app.getDriver().get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
        app.registration().selectUser();

    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter(m -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }
}
