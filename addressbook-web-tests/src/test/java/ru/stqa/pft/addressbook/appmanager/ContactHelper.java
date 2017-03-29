package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Митрич on 29.03.2017.
 */
public class ContactHelper extends HelperBase {


  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

   public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData) {
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
    type(By.name("homepage"), contactData.getEmail3());



    //нужно доделать

    if (!wd.findElement(By.xpath(contactData.getBday())).isSelected()) {
      wd.findElement(By.xpath(contactData.getBday())).click();
    }
    if (!wd.findElement(By.xpath(contactData.getBmounth())).isSelected()) {
      wd.findElement(By.xpath(contactData.getBmounth())).click();
    }
    wd.findElement(By.name("byear")).click();
    wd.findElement(By.name("byear")).clear();
    wd.findElement(By.name("byear")).sendKeys(contactData.getByear());

    if (!wd.findElement(By.xpath(contactData.getAnniversaryDay())).isSelected()) {
      wd.findElement(By.xpath(contactData.getAnniversaryDay())).click();
    }
    if (!wd.findElement(By.xpath(contactData.getAnniversaryMounth())).isSelected()) {
      wd.findElement(By.xpath(contactData.getAnniversaryMounth())).click();
    }

    wd.findElement(By.name("ayear")).click();
    wd.findElement(By.name("ayear")).clear();
    wd.findElement(By.name("ayear")).sendKeys(contactData.getAnniversaryYear());
  }

  public void goToAddNewContact() {
    click(By.linkText("add new"));
  }
}
