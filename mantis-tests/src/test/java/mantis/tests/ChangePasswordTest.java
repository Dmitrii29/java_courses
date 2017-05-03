package mantis.tests;

import mantis.model.MailMessage;
import mantis.model.UserData;
import mantis.model.Users;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by Митрич on 02.05.2017.
 */
public class ChangePasswordTest extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void changePasswordTest() throws IOException, MessagingException {
    app.getDriver();
    //изменение паролья администратором
    app.changePassword().login("administrator", "root");
    //получение пользователя из БД
    Users listOfUsers  = app.db().users();
    UserData selectedUser = listOfUsers.iterator().next();
    String username = selectedUser.getUsername();
    String email = username + "@localhost.localdomain";
    app.changePassword().resetPassword(username);
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    String newpassword = "newpassword";
    app.changePassword().finish(confirmationLink, newpassword);
    assertTrue(app.newSession().login(username,newpassword));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
