package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Митрич on 30.03.2017.
 */
public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    //проверка предусловия для списка получаемых контактов с ИНТЕРФЕЙСА
 /*   if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Andruxa").withLastname("Kim")
              .withHomeNumber("111").withMobileNumber("222").withWorkNumber("333").withAddress("Street 142 kv.2")
              .withEmail("123@mail.ru").withEmail2("321@mail.ru").withEmail3("654@mail.ru"));
      app.goTo().goToHomePage();
    }*/
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Andruxa").withLastname("Kim")
              .withHomeNumber("111").withMobileNumber("222").withWorkNumber("333").withAddress("Street 142 kv.2")
              .withEmail("123@mail.ru").withEmail2("321@mail.ru").withEmail3("654@mail.ru"));
      app.goTo().goToHomePage();
    }
  }

  @Test
  public void testContactModification() {
    //Contacts before = app.contact().all(); // - получение кол-ва контактов с интерфейса
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    File photo = new File("src/test/resources/pic1.jpg");
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstname("ModificationAdmin")
            .withLastname("ModificationAdminovich")
            .withMiddlename("brabrabra")
            .withAddress("almaty 16")
            .withHomeNumber("555444").withMobileNumber("444-555").withWorkNumber("656565")
            .withEmail("123@mail.ru").withEmail2("3456@mail.ru").withEmail3("321@mail.ru");
    app.contact().modify(contact);
    app.goTo().goToHomePage();
    assertThat(app.contact().getContactCount(), equalTo(before.size()));
    //Contacts after = app.contact().all(); // - получение кол-ва контактов с интерфейса
    Contacts after = app.db().contacts();

    assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
  }
}
