package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

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
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomeNumber());
    type(By.name("mobile"), contactData.getMobileNmunber());
    type(By.name("work"), contactData.getWorkNumber());
    type(By.name("fax"), contactData.getFaxNumber());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("homepage"), contactData.getHomepage());
    selectDateOrMounth(contactData.getBday());
    selectDateOrMounth(contactData.getBmounth());
    type(By.name("byear"), contactData.getByear());
    selectDateOrMounth(contactData.getAnniversaryDay());
    selectDateOrMounth(contactData.getAnniversaryMounth());
    type(By.name("ayear"), contactData.getAnniversaryYear());

    if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
    }



  public void goToAddNewContact() {
    click(By.linkText("add new"));
  }

  public void selectContact() {
    if (!wd.findElement(By.name("selected[]")).isSelected()) {
      click(By.name("selected[]"));
    }
  }

  public void editContact() {
    click(By.xpath(".//*[@id='maintable']/tbody/tr[3]/td[8]/a/img"));
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
}
