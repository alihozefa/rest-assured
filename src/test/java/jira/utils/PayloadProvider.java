package jira.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PayloadProvider {

    public static Properties provideHostValue() {
        Properties properties = new Properties();
        try {

            FileInputStream fis = new FileInputStream("C:\\Users\\Hozefaa\\IdeaProjects\\restassured\\src\\test\\java\\jira\\utils\\env.properties");
            properties.load(fis);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        return properties;
    }

    public String provideSessionData(){
        String sessionData = "{ \"username\": \"alihozefa\", \"password\": \"Monday11\" }";
        return sessionData;
    }

    public String provideCreateIssueData(){
        String createIssueData = "{\n" +
                "        \"fields\": {\n" +
                "        \"project\": {\n" +
                "            \"key\": \"RES\"\n" +
                "        },\n" +
                "        \"summary\": \"Payment debit issue\",\n" +
                "                \"description\": \"Payment debit is not getting through\",\n" +
                "                \"issuetype\": {\n" +
                "            \"name\": \"Bug\"\n" +
                "        }\n" +
                "    }\n" +
                "    }";
        return createIssueData;
    }

    public String provideCommentData(){
        String commentData = "{\n" +
                "    \"body\": \"Payment last screen is throwing error while processing payment.\",\n" +
                "    \"visibility\": {\n" +
                "        \"type\": \"role\",\n" +
                "        \"value\": \"Administrators\"\n" +
                "    }\n" +
                "}";

        return commentData;
    }


}
