package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;
import sun.security.util.PendingException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Митрич on 17.04.2017.
 */
public class ContactDataGenerator {

  @Parameter (names = "-c", description = "Contacts count")
  public int count;

  @Parameter (names = "-f", description = "Target file")
  public String file;

  @Parameter (names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (PendingException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(contacts, new File(file));
    } else if (format.equals("json")) {
      saveAsJson(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts) {
      writer.write(String.format("%s;%s;%s\n", contact.getFirstname(), contact.getLastname(),
              contact.getAddress(), contact.getHomeNumber(), contact.getMobileNumber(), contact.getWorkNumber(),
              contact.getEmail(), contact.getEmail2(),contact.getEmail3()));
    }
    writer.close();
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contact = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contact.add(new ContactData().withFirstname(String.format("Vovan %s", i)).withLastname(String.format("Ivanov %s", i))
              .withAddress(String.format("Street %s", i))
              .withHomeNumber(String.format("HomePhone %s", i)).withMobileNumber(String.format("MobilePhone %s", i))
              .withWorkNumber(String.format("WorkPhone %s", i)).withEmail(String.format("12%s@mail.ru", i))
              .withEmail2(String.format("%s23@mail.ru", i)).withEmail3(String.format("1%s3@mail.ru", i)));
    }
    return contact;
  }
}

