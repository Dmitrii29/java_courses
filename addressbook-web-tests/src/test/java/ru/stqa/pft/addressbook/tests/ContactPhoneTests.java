package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Митрич on 15.04.2017.
 */
public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Andruxa").withLastname("Kim").withHomeNumber("111").withMobileNumber("222").withWorkNumber("333").withGroup("test1"));
      app.goTo().goToHomePage();
    }
  }

  @Test
  public void testContactPhones() {
    app.goTo().goToHomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getHomeNumber(), equalTo(contactInfoFromEditForm.getHomeNumber()));
    assertThat(contact.getMobileNumber(), equalTo(contactInfoFromEditForm.getMobileNumber()));
    assertThat(contact.getWorkNumber(), equalTo(contactInfoFromEditForm.getWorkNumber()));
  }
}
