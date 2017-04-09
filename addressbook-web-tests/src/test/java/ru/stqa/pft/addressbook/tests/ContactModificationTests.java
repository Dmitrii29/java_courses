package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Митрич on 30.03.2017.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Admin", "Adminovich", "Admiskii", "Super admin", "Title", "java-courses", "address", "567555", "555567", "123456", "654321", "123456@mail.ru", "123456@mail.ru", "123456@mail.ru", "123456@mail.ru", "//div[@id='content']/form/select[1]//option[3]", "//div[@id='content']/form/select[2]//option[2]", "1992", "//div[@id='content']/form/select[3]//option[3]", "//div[@id='content']/form/select[4]//option[7]", "1992", "test1"));
      app.getNavigationHelper().goToHomePage();
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().editContact(before.size() - 1);
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "ModificationAdmin","ModificationAdminovich");
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    after.remove(before.size() - 1);
    after.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after,before);
  }
}
