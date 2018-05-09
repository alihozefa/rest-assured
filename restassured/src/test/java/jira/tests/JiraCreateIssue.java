package jira.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jira.utils.GenericFunctions;
import org.testng.annotations.Test;

public class JiraCreateIssue {

    GenericFunctions genericFunctions = new GenericFunctions();
    String issueId = "";

    @Test
    public void createJiraIssue(){

        String jSessionId = genericFunctions.createJiraSession();

        RestAssured.baseURI = genericFunctions.resourceProvider.provideHost();

        Response response = RestAssured.given().log().all().
                    header("Content-Type","application/json").
                    header("Cookie","JSESSIONID="+jSessionId).
                    body(genericFunctions.payloadProvider.provideCreateIssueData()).
                when().log().all().
                    post(genericFunctions.resourceProvider.provideCreateIssueResource()).
                then().
                    assertThat().statusCode(201).
                extract().response();

        issueId = new JsonPath(response.asString()).getString("id");

        System.out.println("New issue has been created successfully with the issue id: "+issueId);
    }
}
