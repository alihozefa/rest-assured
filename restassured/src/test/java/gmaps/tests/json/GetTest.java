package gmaps.tests.json;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;


public class GetTest {

    @Test
    public void getAPITest(){
        RestAssured.baseURI="https://maps.googleapis.com";

        RestAssured.given().
                param("location","-33.8670522,151.1957362").
                param("radius","500").
                param("key","AIzaSyDYhCGsSarqLIjeSnI41JArA6fdbDojL4E").
                when().
                get("/maps/api/place/nearbysearch/json").
                then().assertThat().statusCode(200).and()
                .header("Server","scaffolding on HTTPServer2")
                .and().
                body("results[0].name",Matchers.is("Sydney"));
    }
}
