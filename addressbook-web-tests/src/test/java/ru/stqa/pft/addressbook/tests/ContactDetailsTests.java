package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Митрич on 16.04.2017.
 */
public class ContactDetailsTests extends TestBase {


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
  public void testContactInfoFromViewFormEqualEditForm() {
    ContactData contact = app.contact().all().iterator().next();
    String contactInfoFromDetailsForm = app.contact().infoFromDetailsForm(contact.getId());
    assertThat(mergeContact(contact), equalTo(contactInfoFromDetailsForm));
  }

  private String mergeContact(ContactData contact) {
    ContactData editContact = app.contact().phonesFromEditForm(contact.getId());
    return Stream.of(cleaned(contact.getFirstname() + "" + contact.getLastname()), splitInfoString(contact.getAddress()),
            editContact.getHomeNumber(), editContact.getMobileNumber(), editContact.getWorkNumber(),
            splitInfoString(contact.getAllEmails()))
            .filter((s) -> !s.equals("")).map(ContactDetailsTests::phoneCleaned)
            .collect(Collectors.joining(";"));
  }
  public static String phoneCleaned(String phone) {
    return phone.replaceAll("[\\s-()]", "");
  }

  private static String splitInfoString(String multiline) {
    return Arrays.stream(multiline.split("\n")).filter(s -> !s.equals(""))
            .map(ContactDetailsTests::cleaned).collect(Collectors.joining(";"));
  }

  private static String cleaned(String str) {
    return str.replaceAll("[-()\\s]", "");
  }
}
