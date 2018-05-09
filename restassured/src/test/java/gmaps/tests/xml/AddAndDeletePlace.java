package gmaps.tests.xml;

import gmaps.utils.GenericMethods;
import gmaps.utils.ResourceProvider;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
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
                    body(GenericMethods.getStringFromXML("C:\\Users\\Hozefaa\\IdeaProjects\\restassured\\src\\test\\java\\gmaps\\tests\\xml\\placeData.xml")).
                when().
                    post(ResourceProvider.provideAddPlaceResourceXML()).
                then().
                    assertThat().statusCode(200).and().
                    contentType(ContentType.XML).and().
                extract().response();

        //2. Extracting response generated from place added

        XmlPath xml = GenericMethods.convertRawToXML(response);

        //3. Grab place id

        String placeId = xml.get("PlaceAddResponse.place_id");
        System.out.println("place created successfully with the place id= " + placeId);

        System.out.println("Response is: "+response.prettyPrint());

        //4. Delete the place created above by place id
        RestAssured.given().
                    queryParam("key",GenericMethods.getKeyValue()).
                    body("{\n" +
                            "  \"place_id\": \""+placeId+"\"\n" +
                            "}").
                    when().
                        post(ResourceProvider.provideDeletePlaceResourceXML()).
                    then().
                        assertThat().statusCode(200).and().
                        body("status",Matchers.equalTo("OK"));

        System.out.println("Place deleted successfully!");

    }


}
