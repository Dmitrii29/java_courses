package ru.stqa.pft.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * Created by Mitrich on 10.05.2017.
 */
public class RestAssuredTests {

  @BeforeClass
  public void init() {
    RestAssured.authentication = RestAssured.basic("LSGjeU4yP1X493ud1hNniA==", "");
  }

  @Test
  public void  testCreateIssue() throws IOException {
    Set<Issue> oldIssues = getIssues(); //множество существующих объектов issues из трекера
    //создаём новый объект withSubject и withDescription
    Issue newIssue = new Issue().withSubject("Test issue Dimas").withDescription("New test issue Dimas");
    //createIssue возвращает id созданного Issue и записывает в issueId
    int issueId = createIssue(newIssue);
    Set<Issue> newIssues = getIssues(); //получаем новое множество объектов issues из трекера
    oldIssues.add(newIssue.withId(issueId));//в старое множество добавляем новый Issue и присваиваем id
    assertEquals(newIssues,oldIssues);
  }


  private Set<Issue> getIssues() throws IOException {
    String json = RestAssured.get("http://demo.bugify.com/api/issues.json").asString();

    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
  }

  private int createIssue(Issue newIssue) throws IOException {
    String json = RestAssured.given().param("subject", newIssue.getSubject())
                       .param("description", newIssue.getDescription())
                       .post("http://demo.bugify.com/api/issues.json").asString();

    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }
}
