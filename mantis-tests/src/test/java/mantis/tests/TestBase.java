package mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;


/**
 * Created by Митрич on 27.03.2017.
 */
public class TestBase {


  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser" , BrowserType.FIREFOX));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(
            new File("src/test/resources/config_inc.php"),
            "config_inc.php",
            "config_inc.php.bak");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
    app.ftp().restore("config_inc.php.bak","config_inc.php");
    app.stop();
  }

  public boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    IssueData issueData = app.soap().getIssue(issueId);
    return !issueData.getStatus().getName().equals("closed");
  }

  public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    if (isIssueOpen(issueId)) {
      System.out.println("Skipped because issue " + issueId + " isn't closed");
    }
  }


}
