package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Nikolay Pechenin on 10.05.2017.
 */
public class GroupModificationTests extends TestBase {

    @BeforeGroups
    public void ensurePreconditions() {
        app.goTo().GroupPage();

        if (! app.group().isThereAGroup()) {
            app.group().create(new GroupData("test1", null, null));
        }

    }

    @Test
    public void testGroupModification() {
        List<GroupData> before = app.group().list();
        int index = before.size() - 1;

        GroupData group = new GroupData(before.get(index).getId(), "test1", null, "test3");

        app.group().modify(index, group);

        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size());

//        Проверяем через множества
        before.remove(index);
        before.add(group);

        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

//        Проверяем по отсортированным спискам
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);

    }
}
