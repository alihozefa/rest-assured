package twitter.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import twitter.utils.PayloadProvider;
import twitter.utils.ResourceProvider;

public class PostAndUpdateTweetsTest {

    ResourceProvider resource = new ResourceProvider();
    PayloadProvider payload = new PayloadProvider();
    String tweetId = "993639713037692929";

    @Test
    public void postTweet(){
        RestAssured.baseURI = resource.provideHost();

        Response response = RestAssured.given().
                auth().oauth(resource.provideAPIKey(),resource.provideAPISecret(),resource.provideAccessToken(),resource.provideAccessTokenSecret()).
                queryParam("status",payload.tweetData()).
                when().
                post(resource.postTweetResource()).
                then().
                assertThat().statusCode(200).and().
                extract().response();

        JsonPath json = new JsonPath(response.asString());
        tweetId = json.get("id").toString();
        System.out.println("Tweet posted successfully with the post id: "+json.get("id")+"\n"+"with the Text: "+json.get("text"));
    }

    @Test
    public void tweetDelete(){
        RestAssured.baseURI = resource.provideHost();

        RestAssured.given().
                auth().oauth(resource.provideAPIKey(),resource.provideAPISecret(),resource.provideAccessToken(),resource.provideAccessTokenSecret()).
                when().
                post(resource.deleteTweetResource().replace("<id>",tweetId)).
                then().
                assertThat().statusCode(200);

        System.out.println("Tweet deleted successfully with the id: "+tweetId);
    }
}
