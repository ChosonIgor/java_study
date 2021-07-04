package ru.kim.addressbook.tests;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.kim.addressbook.model.GroupData;
import ru.kim.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroupFromJson() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/groups.json"));
        String json = "";
        String line = reader.readLine();
        while (line != null) {
            json += line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType());
        return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

    @Test(dataProvider = "validGroupFromJson")
    public void testGroupCreation(GroupData group) {
        app.goTo().GroupPage();
        Groups before = app.group().all();
//        group = new GroupData().withName("test1").withHeader("test2").withFooter("test3");
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size() + 1));
        Groups after = app.group().all();
        assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}
