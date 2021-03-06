package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Митрич on 16.04.2017.
 */
public class ContactEmailTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Andruxa").withLastname("Kim")
              .withHomeNumber("111").withMobileNumber("222").withWorkNumber("333").withAddress("Street 142 kv.2")
              .withEmail("123@mail.ru").withEmail2("321@mail.ru").withEmail3("654@mail.ru"));
      app.goTo().goToHomePage();
    }
  }

  @Test
  public void testContactEmailsFromHomePageEqualEditForm() {
    app.goTo().goToHomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData emailInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllEmails(), equalTo(mergeEmails(emailInfoFromEditForm)));
  }

  public String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
        .stream().filter((s) -> ! s.equals(""))
        .map(ContactPhoneTests::cleaned)
        .collect(Collectors.joining("\n"));
    }

  public static String cleaned(String phone) {
     return phone.replaceAll("\\s","");
  }
}
