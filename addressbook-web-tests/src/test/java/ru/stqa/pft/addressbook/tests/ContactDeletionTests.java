package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.concurrent.TimeUnit;

/**
 * Created by Митрич on 30.03.2017.
 */

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion(){
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Admin", "Adminovich", "Admiskii", "Super admin", "Title", "java-courses", "address", "567555", "555567", "123456", "654321", "123456@mail.ru", "123456@mail.ru", "123456@mail.ru", "123456@mail.ru", "//div[@id='content']/form/select[1]//option[3]", "//div[@id='content']/form/select[2]//option[2]", "1992", "//div[@id='content']/form/select[3]//option[3]", "//div[@id='content']/form/select[4]//option[7]", "1992", "test1"));
      app.getNavigationHelper().goToHomePage();
    }
    app.getContactHelper().deleteContact();
    app.getNavigationHelper().goToHomePage();
  }
}
