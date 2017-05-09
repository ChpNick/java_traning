package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class UserCreationTests extends TestBase {

    @Test
    public void testUserCreation() {
        gotoAddNewUserPage();
        fillAddNewUserForm(new UserData("test", "testovich", "testov", "Chp", "i like coding", "social quantum",
                "Ivanovo", "888888", "89234567890", "12345678900", "-", "chpnick@mail.ru", "chpnick1@gmail.ru",
                "-", "-", 1, 2, "1983", 1, 2, "1990", "-", "-", "-"));
        submitNewUserCreation();
        gotoHomePage();
    }

}
