package ru.stqa.pft.rest.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by Mitrich on 11.05.2017.
 */
public class RestHelper {
  private final ApplicationManager app;

  public RestHelper(ApplicationManager app) {
    this.app = app;
  }

  public Set<Issue> getIssues() throws IOException {
    String json = getExecutor().execute(Request.Get(app.getProperty("bugify.url.issue")))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
    }.getType());
  }

  public Executor getExecutor() {
    return Executor.newInstance().auth(app.getProperty("bugify.auth"), app.getProperty("bugify.password"));
  }

  public int createIssue(Issue newIssue) throws IOException {
    String json = getExecutor().execute(Request.Post(app.getProperty("bugify.url.issue"))
            .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                    new BasicNameValuePair("description", newIssue.getDescription())))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

  public Issue getIssue(int issueId) throws IOException {
    String json = app.rest().getExecutor()
            .execute(Request.Get(String.format(app.getProperty("bugify.url.issue_id"), issueId)))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    List<Issue> list = new Gson().fromJson(issues, new TypeToken<List<Issue>>() {}.getType());
    return list.get(0);
  }
}

