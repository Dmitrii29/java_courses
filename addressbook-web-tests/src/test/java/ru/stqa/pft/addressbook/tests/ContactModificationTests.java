package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Митрич on 30.03.2017.
 */
public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Andruxa").withLastname("Kim")
              .withHomeNumber("111").withMobileNumber("222").withWorkNumber("333").withGroup("test1")
              .withAddress("Street 142 kv.2").withEmail("123@mail.ru").withEmail2("321@mail.ru").withEmail3("654@mail.ru"));
      app.goTo().goToHomePage();
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstname("ModificationAdmin")
            .withLastname("ModificationAdminovich")
            .withMiddlename("brabrabra");
    app.contact().modify(contact);
    app.goTo().goToHomePage();
    assertThat(app.contact().getContactCount(), equalTo(before.size()));
    Contacts after = app.contact().all();

    assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
  }
}
