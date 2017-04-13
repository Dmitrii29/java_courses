package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Митрич on 29.03.2017.
 */
public class ContactHelper extends HelperBase {


  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    //type(By.name("middlename"), contactData.getMiddlename());
//    type(By.name("nickname"), contactData.getNickname());
//    type(By.name("title"), contactData.getTitle());
//    type(By.name("company"), contactData.getCompany());
//    type(By.name("address"), contactData.getAddress());
//    type(By.name("home"), contactData.getHomeNumber());
//    type(By.name("mobile"), contactData.getMobileNumber());
//    type(By.name("work"), contactData.getWorkNumber());
//    type(By.name("fax"), contactData.getFaxNumber());
//    type(By.name("email"), contactData.getEmail());
//    type(By.name("email2"), contactData.getEmail2());
//    type(By.name("email3"), contactData.getEmail3());
//    type(By.name("homepage"), contactData.getHomepage());
//    selectDateOrMounth(contactData.getBday());
//    selectDateOrMounth(contactData.getBmounth());
//    type(By.name("byear"), contactData.getByear());
//    selectDateOrMounth(contactData.getAnniversaryDay());
//    selectDateOrMounth(contactData.getAnniversaryMounth());
//    type(By.name("ayear"), contactData.getAnniversaryYear());

    if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
    }

  public void goToAddNewContact() {
    click(By.linkText("add new"));
  }

  public void submitContactModification() {
    click(By.xpath(".//*[@id='content']/form[1]/input[22]"));
  }

  public void deleteSelectedContact() {
    click(By.xpath(".//*[@id='content']/form[2]/div[2]/input"));
  }

  public void acceptContactDeletion() {
    wd.switchTo().alert().accept();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("entry"));
  }

  public void selectContact(int index) {
    if (!wd.findElement(By.name("selected[]")).isSelected()) {
      wd.findElements(By.name("selected[]")).get(index).click();
    }
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='"+ id + "']")).click();
  }

  public void create(ContactData contact) {
    goToAddNewContact();
    fillContactForm(contact, true);
    submitContactCreation();
    contactsCache = null;
  }

  public void editContact(int index) {
    wd.findElements(By.cssSelector("img[title='Edit']")).get(index).click();
  }

  public void editContactById(int id) {
    wd.findElement(By.cssSelector("a[href='edit.php?id="+id+"']")).click();
  }


  public void modify(ContactData contact) {
    editContactById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactsCache = null;
  }

  public void deleteContact() {
    deleteSelectedContact();
    acceptContactDeletion();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    contactsCache = null;
  }

  //работа со списками
  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.xpath("//*[@id=\"maintable\"]//tr[@name]"));
    for (WebElement element : elements) {
      String lastName = element.findElement(By.xpath(".//td[2]")).getText();
      String firstName = element.findElement(By.xpath(".//td[3]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      ContactData contact = new ContactData().withFirstname("Admin").withLastname("Adminskii").withGroup("test1");
      contacts.add(contact);
    }
    return contacts;
  }

  private Contacts contactsCache = null;

  public Contacts all() {
    if (contactsCache != null) {
      return new Contacts(contactsCache);
    }
    contactsCache   = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//*[@id=\"maintable\"]//tr[@name]"));
    for (WebElement element : elements) {
      String lastName = element.findElement(By.xpath(".//td[2]")).getText();
      String firstName = element.findElement(By.xpath(".//td[3]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      ContactData contact = new ContactData().withId(id).withFirstname(firstName).withLastname(lastName);
      contactsCache.add(contact);
    }
    return new Contacts(contactsCache);
  }
}
