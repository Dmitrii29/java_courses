package ru.stqa.pft.addressbook.appmanager;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.tests.ContactDetailsTests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    //attach(By.name("photo"), contactData.getPhoto());
    //type(By.name("middlename"), contactData.getMiddlename());
//    type(By.name("nickname"), contactData.getNickname());
//    type(By.name("title"), contactData.getTitle());
//    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomeNumber());
    type(By.name("mobile"), contactData.getMobileNumber());
    type(By.name("work"), contactData.getWorkNumber());
    type(By.name("fax"), contactData.getFaxNumber());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
//    type(By.name("homepage"), contactData.getHomepage());
//    selectDateOrMounth(contactData.getBday());
//    selectDateOrMounth(contactData.getBmounth());
//    type(By.name("byear"), contactData.getByear());
//    selectDateOrMounth(contactData.getAnniversaryDay());
//    selectDateOrMounth(contactData.getAnniversaryMounth());
//    type(By.name("ayear"), contactData.getAnniversaryYear());
/*    if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
      }*/
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
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }

  public void initContactDetailsById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", id))).click();
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
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
      ContactData contact = new ContactData().withFirstname("Admin").withLastname("Adminskii");
      contacts.add(contact);
    }
    return contacts;
  }

  private Contacts contactsCache = null;

  public Contacts all() {
    if (contactsCache != null) {
      return new Contacts(contactsCache);
    }
    contactsCache = new Contacts();
    List<WebElement>  rows = wd.findElements(By.xpath("//*[@id=\"maintable\"]//tr[@name]"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("id"));
      String lastName = cells.get(1).getText();
      String firstName = cells.get(2).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      String allPhones = cells.get(5).getText();
      ContactData contact = new ContactData().withId(id).withFirstname(firstName).withLastname(lastName)
              .withAllPhones(allPhones).withAddress(address)
              .withAllEmails(allEmails);
      contactsCache.add(contact);
    }
    return new Contacts(contactsCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    editContactById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withHomeNumber(home).withMobileNumber(mobile).withWorkNumber(work)
            .withEmail(email).withEmail2(email2).withEmail3(email3);
  }

  public ContactData addressFromEditForm(ContactData contact) {
    editContactById(contact.getId());
    String address =wd.findElement(By.xpath("//textarea[@name='address']")).getText();
    wd.navigate().back();
    return new ContactData().withAddress(address);
  }

  public String infoFromDetailsForm(int contactId) {
    initContactDetailsById(contactId);
    String[] allInfo = wd.findElement(By.xpath("//*[@id='content']")).getText().split("\n");
    wd.navigate().back();
    return Arrays.stream(allInfo)
            .filter((s) -> !s.equals("")).map(ContactDetailsTests::phoneCleaned).collect(Collectors.joining(";"));
  }

  public ContactData phonesFromEditForm(int contactId) {
    editContactById(contactId);
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    if (StringUtils.isNotBlank(home)) {
      home = "H: " + home;
    }
    if (StringUtils.isNotBlank(mobile)) {
      mobile = "M: " + mobile;
    }
    if (StringUtils.isNotBlank(work)) {
      work = "W: " + work;
    }
    return new ContactData().withId(contactId).withHomeNumber(home).withMobileNumber(mobile).withWorkNumber(work);
  }

  public void addContactToGroup(int id, String name) {
    selectContactById(id);
    selectGroupInAddDropdownList(name);
    initAddContactInGroup();
  }

  private void selectGroupInAddDropdownList(String name) {
    new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(name);
  }

  private void initAddContactInGroup() {
    click(By.name("add"));
  }

  public void removeContactFromGroup(int id) {
    wd.findElement(By.cssSelector("select[name='group']>option[value='" + id + "']")).click();
  }
}
