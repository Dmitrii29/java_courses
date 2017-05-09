package mantis.tests;

import mantis.appmanager.HttpSession;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.io.IOException;

import static org.testng.Assert.assertTrue;

/**
 * Created by Митрич on 30.04.2017.
 */
public class LoginTests extends TestBase {

  int issueId = 2;

  @Test
  public void testLogin() throws IOException, ServiceException {
    skipIfNotFixed(issueId);
    HttpSession session = app.newSession();
    assertTrue(session.login("administrator", "root"));
    assertTrue(session.isLoggedInAs("administrator"));
  }
}
