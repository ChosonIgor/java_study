package ru.kim.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.kim.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase {

    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();
        session.login("administrator", "root");
        assertTrue(session.isLoggedInAs("administrator"));
    }

}
