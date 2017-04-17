package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/pic1.jpg");
    ContactData contact = new ContactData().withFirstname("Admin").withLastname("Adminskii")
            .withPhoto(photo).withHomeNumber("111").withMobileNumber("222").withWorkNumber("333")
            .withAddress("Street 142 kv.2")
            .withEmail("123@mail.ru").withEmail2("321@mail.ru").withEmail3("654@mail.ru");
    app.contact().create(contact);
    app.goTo().goToHomePage();
    assertThat(app.contact().getContactCount(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();

    assertThat(after,
            equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
