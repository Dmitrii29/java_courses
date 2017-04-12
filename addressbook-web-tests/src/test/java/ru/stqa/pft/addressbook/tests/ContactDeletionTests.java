package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by Митрич on 30.03.2017.
 */

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion(){
    if (! app.getContactHelper().isThereAContact()) {
      ContactData contact = new ContactData("Admin", "Adminovich", "Admiskii", "Super admin", "Title", "java-courses", "address", "567555", "555567", "123456", "654321", "123456@mail.ru", "123456@mail.ru", "123456@mail.ru", "123456@mail.ru", "//div[@id='content']/form/select[1]//option[3]", "//div[@id='content']/form/select[2]//option[2]", "1992", "//div[@id='content']/form/select[3]//option[3]", "//div[@id='content']/form/select[4]//option[7]", "1992");
      app.getContactHelper().createContact(contact);
      app.goTo().goToHomePage();
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() -1);
    app.getContactHelper().deleteContact();
    app.goTo().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }
}
