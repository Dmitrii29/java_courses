package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Митрич on 30.03.2017.
 */
public class GroupModificationTests extends TestBase {

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
  public void testGroupModification() {
    //Groups before = app.group().all(); - получение кол-ва групп с интерфейса
    Groups before = app.db().groups();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId())
            .withName("modify Group")
            .withHeader("header")
            .withFooter("footer");
    app.goTo().groupPage();
    app.group().modify(group);
    assertThat(app.group().getGroupCount(), equalTo(before.size()));
    //Groups after = app.group().all(); - получение кол-ва групп с интерфейса
    Groups after = app.db().groups();

    assertThat(after, equalTo(before.withOut(modifiedGroup).withAdded(group)));
    verifyGroupListInUI(); //добавлен булевыый параметр в конфигурации теста -DverifyUI=true

  }



}
