package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().GroupPage();

        List<GroupData> before = app.group().list();

        GroupData group = new GroupData("test2", null, null);

        app.group().create(group);

        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() + 1);

//        Проверяем через множества
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        group.setId(after.stream().max(byId).get().getId());
        before.add(group);

        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

//        Проверяем по отсортированным спискам
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);

    }

}
