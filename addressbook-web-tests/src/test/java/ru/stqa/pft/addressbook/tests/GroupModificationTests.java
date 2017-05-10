package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by Nikolay Pechenin on 10.05.2017.
 */
public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test1_edit", "test2_edit", "test3_edit"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();

    }
}
