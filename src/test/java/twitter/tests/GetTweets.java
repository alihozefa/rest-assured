package twitter.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import twitter.utils.ResourceProvider;

public class GetTweets {

    public ResourceProvider resource = new ResourceProvider();

    @Test
    public JsonPath getAllTweets(){

        RestAssured.baseURI= resource.provideHost();
        Response response;

        response = RestAssured.given().
                auth().oauth(resource.provideAPIKey(), resource.provideAPISecret(), resource.provideAccessToken(), resource.provideAccessTokenSecret()).
                when().log().all().
                get(resource.getTweetsResource()).
                then().log().all().
                assertThat().statusCode(200).and().
                extract().response();

        JsonPath jsonPath = new JsonPath(response.asString());

        return jsonPath;
    }

    @Test
    public JsonPath getAllTweets(Integer count){

        RestAssured.baseURI= resource.provideHost();
        Response response;

        response = RestAssured.given().
                auth().oauth(resource.provideAPIKey(), resource.provideAPISecret(), resource.provideAccessToken(), resource.provideAccessTokenSecret()).
                queryParam("count",count).
                when().log().all().
                get(resource.getTweetsResource()).
                then().log().all().
                assertThat().statusCode(200).and().
                extract().response();

        JsonPath jsonPath = new JsonPath(response.asString());

        return jsonPath;
    }
}
