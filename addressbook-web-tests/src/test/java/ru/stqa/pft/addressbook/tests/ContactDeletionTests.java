package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by Митрич on 30.03.2017.
 */

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion(){
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().acceptContactDeletion();

  }
}
