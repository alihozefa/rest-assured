package jira.utils;

import gmaps.utils.GenericMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GenericFunctions {

    public ResourceProvider resourceProvider = new ResourceProvider();
    public PayloadProvider payloadProvider = new PayloadProvider();

    public String createJiraSession(){

        RestAssured.baseURI=resourceProvider.provideHost();
        Response response;

        response = RestAssured.given().log().all().
                header("Content-Type","application/json").
                body(payloadProvider.provideSessionData()).
                when().log().all().
                post(resourceProvider.provideSessionResource()).
                then().
                assertThat().statusCode(200).and().
                extract().response();

        JsonPath jsonPath = new JsonPath(response.asString());

        String jSessionId = jsonPath.get("session.value");

        return jSessionId;
    }

    public Response createJiraIssue(){

        String jSessionId = createJiraSession();

        RestAssured.baseURI = resourceProvider.provideHost();

        Response response = RestAssured.given().log().all().
                header("Content-Type","application/json").
                header("Cookie","JSESSIONID="+jSessionId).
                body(payloadProvider.provideCreateIssueData()).
                when().log().all().
                post(resourceProvider.provideCreateIssueResource()).
                then().
                assertThat().statusCode(201).
                extract().response();

        return response;
    }

    public Response addComment(String issueId){

        String sessionId = "";
        RestAssured.baseURI = resourceProvider.provideHost();

        if(issueId==null){
            Response issueResponse = createJiraIssue();
            issueId = GenericMethods.convertRawToJson(issueResponse).get("id");
        } else {
            sessionId = createJiraSession();
        }

        Response response = RestAssured.given().
                header("Content-Type","application/json").
                header("Cookie","JSESSIONID="+sessionId).
                body(payloadProvider.provideCommentData()).
                when().
                post("/rest/api/2/issue/"+issueId+"/comment").
                then().log().all().
                assertThat().statusCode(201).
                extract().response();

        return response;

    }
}
