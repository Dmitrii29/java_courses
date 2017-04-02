package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String nickname;
  private final String title;
  private final String company;
  private final String address;
  private final String homeNumber;
  private final String mobileNmunber;
  private final String workNumber;
  private final String faxNumber;
  private final String email;
  private final String email2;
  private final String email3;
  private final String homepage;
  private final String bday;
  private final String bmounth;
  private final String byear;
  private final String anniversaryDay;
  private final String anniversaryMounth;
  private final String anniversaryYear;
  private String group;

  public ContactData(String firstname, String middlename, String lastname, String nickname, String title, String company, String address, String homeNumber, String mobileNmunber, String workNumber, String faxNumber, String email, String email2, String email3, String homepage, String bday, String bmounth, String byear, String anniversaryDay, String anniversaryMounth, String anniversaryYear, String group) {
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.nickname = nickname;
    this.title = title;
    this.company = company;
    this.address = address;
    this.homeNumber = homeNumber;
    this.mobileNmunber = mobileNmunber;
    this.workNumber = workNumber;
    this.faxNumber = faxNumber;
    this.email = email;
    this.email2 = email2;
    this.email3 = email3;
    this.homepage = homepage;
    this.bday = bday;
    this.bmounth = bmounth;
    this.byear = byear;
    this.anniversaryDay = anniversaryDay;
    this.anniversaryMounth = anniversaryMounth;
    this.anniversaryYear = anniversaryYear;
    this.group = group;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public String getTitle() {
    return title;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getHomeNumber() {
    return homeNumber;
  }

  public String getMobileNmunber() {
    return mobileNmunber;
  }

  public String getWorkNumber() {
    return workNumber;
  }

  public String getFaxNumber() {
    return faxNumber;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getHomepage() {
    return homepage;
  }

  public String getBday() {
    return bday;
  }

  public String getBmounth() {
    return bmounth;
  }

  public String getByear() {
    return byear;
  }

  public String getAnniversaryDay() {
    return anniversaryDay;
  }

  public String getAnniversaryMounth() {
    return anniversaryMounth;
  }

  public String getAnniversaryYear() {
    return anniversaryYear;
  }

  public String getGroup() {
    return group;
  }
}
