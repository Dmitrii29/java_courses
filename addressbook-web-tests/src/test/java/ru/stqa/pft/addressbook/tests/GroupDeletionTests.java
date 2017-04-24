package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.equalToObject;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    //проверка предусловия для списка получаемых групп с ИНТЕРФЕЙСА
    /*app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1").withHeader(null).withFooter(null));
    }*/

    //проверка предусловия для списков получаемых с бд
    if(app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withHeader(null).withFooter(null));
    }
  }

  @Test
  public void testGroupDeletion() {
    //Groups before = app.group().all();  - получение кол-ва групп с интерфейса
    Groups before = app.db().groups();
    GroupData deletedGroup = before.iterator().next();
    app.goTo().groupPage();
    app.group().delete(deletedGroup);
    assertThat(app.group().getGroupCount(), equalTo(before.size() - 1));
    //Groups after = app.group().all();  - получение кол-ва групп с интерфейса
    Groups after = app.db().groups();

    assertThat(after, equalToObject(before.withOut(deletedGroup)));
  }
}
