package ru.kim.rest.test;

import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestAssuredTests extends TestBase{

    @Test
    public void testCreateIssue() {
        Set<Issue> oldIssues = rh.getIssues();
        Issue newIssue = new Issue().withSubject("New test issue").withDescription("New test description");
        int issueId = rh.createIssue(newIssue);
        Set<Issue> newIssues = rh.getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);
    }
}
