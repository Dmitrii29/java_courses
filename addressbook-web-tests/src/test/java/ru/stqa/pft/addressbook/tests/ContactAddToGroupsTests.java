package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Митрич on 25.04.2017.
 */
public class ContactAddToGroupsTests extends TestBase {

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
  public void testContactAddToGroup() {
    Contacts contact = app.db().contacts();
    Groups group = app.db().groups();
    ContactData contactForAddToGroup = contact.iterator().next();
    GroupData groupForAddContact = group.iterator().next();
    Groups contactInGroupsBeforeAdded = app.db().contactInGroups();
    app.goTo().goToHomePage();
    app.contact().addContactToGroup(contactForAddToGroup.getId(), groupForAddContact.getName());
    app.goTo().goToHomePage();
    Contacts after = app.db().contacts();
    assertThat(after.size(), equalTo(contact.size()));
    Groups contactInGroupsAfterAdded = app.db().contactInGroups();

    assertThat((contactInGroupsAfterAdded), equalTo(new Groups(contactInGroupsBeforeAdded.withAdded(groupForAddContact))));
  }
}
