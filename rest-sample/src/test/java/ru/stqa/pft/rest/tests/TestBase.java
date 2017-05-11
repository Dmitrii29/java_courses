package ru.stqa.pft.rest.tests;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.rest.appmanager.ApplicationManager;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;

/**
 * Created by Mitrich on 11.05.2017.
 */
public class TestBase {
  protected static final ApplicationManager app = new ApplicationManager();

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  public boolean isIssueOpen(int issueId) throws IOException {
    Issue issue = app.rest().getIssue(issueId);
    return !issue.getState_name().equals("closed");
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}
