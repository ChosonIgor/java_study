package ru.kim.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.kim.mantis.appmanager.model.Issue;
import ru.kim.mantis.appmanager.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

public class SoapTests extends TestBase{

    @Test
    public void testGetProject() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProject();
        System.out.println(projects.size());
        for (Project project : projects) {
            System.out.println(project.getName());
            System.out.println(project.getId());
        }
    }

    @Test
    public void testCreatedIssue() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProject();
        Issue issue = new Issue().withSummary("Test summary").withDescription("Test description")
                .withProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);
        assertEquals(issue.getSummary(), created.getSummary());
    }
}
