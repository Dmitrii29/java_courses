package ru.stqa.pft.addressbook.model;

public class ContactData {
  private int id;
  private String firstname;
  private String middlename;
  private String lastname;
  private String nickname;
  private String title;
  private String company;
  private String address;
  private String homeNumber;
  private String mobileNumber;
  private String workNumber;
  private String faxNumber;
  private String email;
  private String email2;
  private String email3;
  private String homepage;
  private String bday;
  private String bmounth;
  private String byear;
  private String anniversaryDay;
  private String anniversaryMounth;
  private String anniversaryYear;
  private String group;

  public ContactData(int id, String firstname, String lastname){
    this.id = Integer.MAX_VALUE;
    this.firstname = firstname;
    this.lastname = lastname;
  }


  public ContactData(String firstname, String middlename, String lastname, String nickname, String title, String company, String address, String homeNumber, String mobileNmunber, String workNumber, String faxNumber, String email, String email2, String email3, String homepage, String bday, String bmounth, String byear, String anniversaryDay, String anniversaryMounth, String anniversaryYear, String group) {
    this.id = id;
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.nickname = nickname;
    this.title = title;
    this.company = company;
    this.address = address;
    this.homeNumber = homeNumber;
    this.mobileNumber = mobileNmunber;
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

  public int getId() {
    return id;
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

  public String getMobileNumber() {
    return mobileNumber;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
  }

  @Override
  public int hashCode() {
    int result = firstname != null ? firstname.hashCode() : 0;
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }
}
