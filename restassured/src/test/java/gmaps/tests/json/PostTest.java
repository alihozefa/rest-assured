package gmaps.tests.json;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class PostTest {

    //Add below place into google map
    @Test
    public void postAPITest(){
        RestAssured.baseURI="https://maps.googleapis.com";

        RestAssured.given().
                    queryParam("key","AIzaSyDYhCGsSarqLIjeSnI41JArA6fdbDojL4E").
                    body("{\n" +
                            "  \"location\": {\n" +
                            "    \"lat\": -33.8669710,\n" +
                            "    \"lng\": 151.1958750\n" +
                            "  },\n" +
                            "  \"accuracy\": 50,\n" +
                            "  \"name\": \"Google Shoes!\",\n" +
                            "  \"phone_number\": \"(02) 9374 4000\",\n" +
                            "  \"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\",\n" +
                            "  \"types\": [\"shoe_store\"],\n" +
                            "  \"website\": \"http://www.google.com.au/\",\n" +
                            "  \"language\": \"en-AU\"\n" +
                            "}").
                when().
                    post("maps/api/place/add/json").
                then().
                    assertThat().contentType(ContentType.JSON).and().
                    statusCode(200);

    }
}
