package activities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestDemo {

    @Test

    public void GetPetDetails() {

        String baseURI = "https://petstore.swagger.io/v2/pet";

        Response response =

                given().contentType(ContentType.JSON) // Set headers

                        .when().get(baseURI + "/findByStatus?status=sold"); // Run GET request


        String responseBody = response.getBody().asString();

        System.out.println("Response Body is =>  " + responseBody);
        response.then().statusCode(200);

        response.then().body("[0].status", equalTo("sold"));

    }
}
