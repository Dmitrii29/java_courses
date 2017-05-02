package mantis.appmanager;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Митрич on 30.04.2017.
 */
public class HttpSession {

  private CloseableHttpClient httpClient;
  private ApplicationManager app;

  public HttpSession(ApplicationManager app) {
    this.app = app;
    //создаётся новая сессия по http, т.е объект который будет отправлять запросы на сервер
    httpClient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
  }


  public boolean login(String username, String password) throws IOException {
    //создаётся пустой post
    HttpPost post = new HttpPost(app.getProperty("web.baseUrl")+ "/login.php");

    //params формируют body запроса
    List<NameValuePair> params = new ArrayList<>();
    params.add(new BasicNameValuePair("username",username));
    params.add(new BasicNameValuePair("password",password));
    params.add(new BasicNameValuePair("secure_session","on"));
    params.add(new BasicNameValuePair("return","index.php"));

    //params упаковываются в post
    post.setEntity(new UrlEncodedFormEntity(params));

    //выполняется post
    CloseableHttpResponse response = httpClient.execute(post); //выполяется get и получаем ответ
    String body = getTextFrom(response);//получает текст ответа

    // проверка: текст страницы содержит имя юзера
    return body.contains(String.format("<span class=\"italic\">%s</span>",username));
  }

  private String getTextFrom(CloseableHttpResponse response) throws IOException {
    try {
      return EntityUtils.toString(response.getEntity());
    } finally {
      response.close();
    }
  }

  public boolean isLoggedInAs(String username) throws IOException {
    //переход на главную страницу
    HttpGet get = new HttpGet(app.getProperty("web.baseUrl")+ "/index.php");
    CloseableHttpResponse response = httpClient.execute(get); //выполяется get и получаем ответ
    String body = getTextFrom(response); // получаю текст ответа
    // проверка: текст страницы содержит имя юзера
    return body.contains(String.format("<span class=\"italic\">%s</span>",username));
  }


}
