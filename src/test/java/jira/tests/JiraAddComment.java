package jira.tests;

import io.restassured.RestAssured;
import jira.utils.GenericFunctions;
import org.testng.annotations.Test;

public class JiraAddComment {

    GenericFunctions genericFunctions = new GenericFunctions();

    @Test
    public void addComment(){

        RestAssured.baseURI = genericFunctions.resourceProvider.provideHost();

        String sessionId = genericFunctions.createJiraSession();

//        Response issueResponse = genericFunctions.createJiraIssue();

//        String issueId = GenericMethods.convertRawToJson(issueResponse).get("id");
        String issueId = "RES-8";

        RestAssured.given().
                        header("Content-Type","application/json").
                        header("Cookie","JSESSIONID="+sessionId).
                        body(genericFunctions.payloadProvider.provideCommentData()).
                    when().
                        post(genericFunctions.resourceProvider.provideAddCommentResource().replace("{issueIdOrKey}",issueId)).
//                        post("/rest/api/2/issue/"+issueId+"/comment").
                    then().log().all().
                        assertThat().statusCode(201);

    }
}
