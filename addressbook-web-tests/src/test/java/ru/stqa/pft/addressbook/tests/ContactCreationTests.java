package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("Admin", "Adminovich", "Admiskii", "Super admin", "Title", "java-courses", "address", "567555", "555567", "123456", "654321", "123456@mail.ru", "123456@mail.ru", "123456@mail.ru", "123456@mail.ru", "//div[@id='content']/form/select[1]//option[3]", "//div[@id='content']/form/select[2]//option[2]", "1992", "//div[@id='content']/form/select[3]//option[3]", "//div[@id='content']/form/select[4]//option[7]", "1992", "test1");
    app.getContactHelper().createContact(contact);
    app.goTo().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() +1);

    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }
}
