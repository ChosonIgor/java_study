package ru.kim.rest.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import org.testng.Assert;
import ru.kim.rest.test.Issue;

import java.util.Set;

public class RestHelper {

    public Set<Issue> getIssues() {
        String json = RestAssured.get("https://bugify.stqa.ru/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }

    public int createIssue(Issue newIssue) {
        String json = RestAssured.given()
                .param("subject", newIssue.getSubject())
                .param("description", newIssue.getDescription())
                .post("https://bugify.stqa.ru/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

    public boolean isIssueOpen(int issueId) {
        String json = RestAssured.get(String.format("https://bugify.stqa.ru/api/issues/%s.json", issueId)).asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonArray issues = parsed.getAsJsonObject().getAsJsonArray("issues");
        if (issues.get(0).getAsJsonObject().get("state_name").getAsString().equals("Open")) {
            return true;
        } else {
            return false;
        }
    }

}
