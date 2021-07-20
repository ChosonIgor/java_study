package ru.kim.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.kim.mantis.appmanager.model.MailMessage;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public class ChangePasswordTests extends TestBase {

    List<String> userData;
    @BeforeMethod
    public void startMailServer() {
        userData = app.getData().getUser();
        app.mail().start();
    }

    @Test
    public void testRegistration() throws MessagingException, IOException {
        String password ="newPassword";
        app.changePass().resetPassword(userData.get(0));
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, userData.get(1));
        app.changePass().inputNewPassword(confirmationLink, password);
        app.newSession().login(userData.get(0), password); // проверка происходит в методе login()
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter(m -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }



}
