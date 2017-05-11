package ru.stqa.pft.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * Created by Mitrich on 10.05.2017.
 */
public class RestTests extends TestBase {

  private int issueId = 3;

  @Test
  public void  testCreateIssue() throws IOException {
    skipIfNotFixed(issueId);
    Set<Issue> oldIssues = app.rest().getIssues(); //множество существующих объектов issues из трекера
    //создаём новый объект withSubject и withDescription
    Issue newIssue = new Issue().withSubject("Test issue Dimas").withDescription("New test issue Dimas");
    //createIssue возвращает id созданного Issue и записывает в issueId
    int issueId = app.rest().createIssue(newIssue);
    Set<Issue> newIssues = app.rest().getIssues(); //получаем новое множество объектов issues из трекера
    oldIssues.add(newIssue.withId(issueId));//в старое множество добавляем новый Issue и присваиваем id
    assertEquals(newIssues,oldIssues);
  }
}
