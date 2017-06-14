package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by Nikolay Pechenin on 14.06.2017.
 */
public class RegistrationTests extends TestBase {

    @Test
    public void testRegistration() {
        app.registration().start("user1", "user1@localhost.localdomain");

    }
}
