package ru.kim.rest.test;

import org.testng.annotations.Test;

public class TestingTests extends TestBase {

    @Test
    public void testingIssueStatus() {
        skipIfNotFixed(1275);
    }
}
