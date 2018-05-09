package gmaps.tests.json;

import gmaps.utils.GenericMethods;
import gmaps.utils.PayLoadProvider;
import gmaps.utils.ResourceProvider;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class AddAndDeletePlace {

    //1. Adding a place into google maps
    @Test
    public void addAndDeletePlace(){

        RestAssured.baseURI=GenericMethods.getHostValue();

        Response response = RestAssured.given().
                    queryParam("key",GenericMethods.getKeyValue()).
                    body(PayLoadProvider.provideAddPlaceData()).
                when().
                    post(ResourceProvider.provideAddPlaceResource()).
                then().
                    assertThat().statusCode(200).and().
                    contentType(ContentType.JSON).
                    body("status",Matchers.equalTo("OK")).
                extract().response();

        //2. Extracting response generated from place added

        String responseStr = response.asString();
        JsonPath json = new JsonPath(responseStr);

        //3. Grab place id

        String placeId = json.getString("place_id");
        System.out.println("place created successfully with the place id= " + placeId);

        //4. Delete the place created above by place id
        RestAssured.given().
                    queryParam("key","AIzaSyDYhCGsSarqLIjeSnI41JArA6fdbDojL4E").
                    body("{\n" +
                            "  \"place_id\": \""+placeId+"\"\n" +
                            "}").
                    when().
                        post(ResourceProvider.provideDeletePlaceResource()).
                    then().
                        assertThat().statusCode(200).and().
                        body("status",Matchers.equalTo("OK"));

        System.out.println("Place deleted successfully!");

    }
}
