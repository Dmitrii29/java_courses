package mantis.appmanager;

import mantis.tests.TestBase;
import org.openqa.selenium.By;

/**
 * Created by Митрич on 02.05.2017.
 */
public class ChangePasswordHelper extends HelperBase {

  private String user;
  private String password;
  private String username;

  public ChangePasswordHelper(ApplicationManager app) {
    super(app);
  }

  public void login(String user, String password){
    this.user = user;
    this.password = password;
    type(By.name("username"), user);
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }

  public void resetPassword(String user) {
    this.username = user;
    click(By.linkText("Manage Users"));
    click(By.linkText(username));
    click(By.cssSelector("input[value='Reset Password']"));
  }

  public void finish(String confirmationLink, String newpassword) {
    wd.get(confirmationLink);
    type(By.name("password"),newpassword);
    type(By.name("password_confirm"),newpassword);
    click((By.cssSelector("input[value='Update User']")));
  }
}
