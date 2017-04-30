package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Митрич on 26.04.2017.
 */
public class ContactRemoveFromGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Andruxa").withLastname("Kim")
              .withHomeNumber("111").withMobileNumber("222")
              .withWorkNumber("333").withAddress("Street 142 kv.2")
              .withEmail("123@mail.ru").withEmail2("321@mail.ru").withEmail3("654@mail.ru"));
      app.goTo().goToHomePage();
    }

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withFooter(null).withHeader(null));
    }
  }

  @Test
  public void testDeleteContactFromGroup(){

    Contacts before = app.db().contacts();
    app.goTo().goToHomePage();
    ContactData modifiedContact = before.iterator().next();
    GroupData removeGroup = modifiedContact.getGroups().iterator().next();
    app.contact().removeContactFromGroup(removeGroup.getId());
    app.contact().selectContactById(modifiedContact.getId());
    app.contact().click(By.cssSelector("input[name='remove']"));
    Contacts after = app.db().contacts();
    after.remove(modifiedContact);
    ContactData modifiedContact2 = new ContactData();
    for(ContactData contact : before) {
      if (contact.equals(modifiedContact)) {
        contact.getGroups().remove(removeGroup);
        after.add(contact);
      }
    }
    modifiedContact2.getGroups().remove(removeGroup);
    System.out.println(modifiedContact2);

    assertThat(after, equalTo(before));
  }


}
