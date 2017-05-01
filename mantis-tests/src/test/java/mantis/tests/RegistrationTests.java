package mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by Митрич on 30.04.2017.
 */
public class RegistrationTests extends TestBase {

  @Test
  public void testRegistration() {
    app.registration().start("user1", "user1@localhost.localdomain");

  }
}
