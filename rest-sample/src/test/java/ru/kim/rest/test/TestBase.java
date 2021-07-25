package ru.kim.rest.test;

import io.restassured.RestAssured;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import ru.kim.rest.appmanager.RestHelper;


public class TestBase {

    protected RestHelper rh;

    @BeforeClass
    public void init() {
        RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
        rh = new RestHelper();
    }

    boolean isIssueOpen(int issueId) {
        String json = RestAssured.get(String.format("https://bugify.stqa.ru/api/issues/%s.json", issueId)).asString();
        if (json.contains("\"state_name\":\"Open\"")) {
            return true;
        } else {
            return false;
        }
    }

    public void skipIfNotFixed(int issueId) {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
