package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Митрич on 30.03.2017.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getContactHelper().selectContact();
    app.getContactHelper().editContact();
    app.getContactHelper().fillContactForm(new ContactData("ModificationAdmin", "ModificationAdminovich", "ModificationAdmiskii", "Super admin", "Title", "java-courses", "address", "567555", "555567", "123456", "654321", "123456@mail.ru", "123456@mail.ru", "123456@mail.ru", "123456@mail.ru", "//div[@id='content']/form/select[1]//option[3]", "//div[@id='content']/form/select[2]//option[2]", "1992", "//div[@id='content']/form/select[3]//option[3]", "//div[@id='content']/form/select[4]//option[7]", "1992",null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().goToHomePage();
  }
}
