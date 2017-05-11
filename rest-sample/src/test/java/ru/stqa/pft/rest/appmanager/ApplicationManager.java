package ru.stqa.pft.rest.appmanager;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Митрич on 29.03.2017.
 */
public class ApplicationManager {

  private final Properties properties;
  private RestHelper restHelper;

  public ApplicationManager() {
    properties = new Properties();
  }
  public  String getProperty(String key) {
    return properties.getProperty(key);
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(String.format("src/test/resources/%s.properties", target)));
  }

  public RestHelper rest(){
    if(restHelper == null){
      restHelper = new RestHelper(this);
    }
    return restHelper;
  }

}
