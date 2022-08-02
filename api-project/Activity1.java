package activities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


    public class Activity1 {
        RequestSpecification requestspec;
        ResponseSpecification responseSpec;
        String sshkey ;
        int id ;
        String Base_URL = "https://api.github.com";

        @BeforeClass
            public void beforeClass(){
            requestspec = new RequestSpecBuilder().setContentType(ContentType.JSON).addHeader("Authorization","token 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").setBaseUri("https://api.github.com").build();
            }

        @Test
        public void addSSHKey(){

            String reqBody = """
                    {
                        "title": "TestAPIKey",
                        "key": "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABgQDEXt8Ti3sNAQ1TJQVfKdawgp3OTuVjbMX4Ccp/OuCUuOYd9vmAbJWMfTS0kj1iacO5Nm29niIFc/MLQZie+Az60xJIXi7jq08HFVBNKCs3FOMvNH/NVx885ICTs2cw6YHVnAj59YgG4py2nGWIubcTag1Xg0fdSx88639mefCT/bzzj3BR6darQRV8WBc8cH+eVJ5sF/iq8BonMK20vbFSPKgNeeEHvoN1rNa5s19qQWJhlbJ0PrF8maiJPoqL0H+uif6hha8nxW5ft97rwEwwga4nddq4DN6/+5vTbR2PEjqF4Jxmo+MtGBxCsAQpg9opi4o3Ex9Zrgb4Avph6Ln0qeemWW/k9pUH8bqnFB/NkfjpUDmgehWE6YdOsP7UAlEvNpiYsQGvEL+1Uza7mCUnF7wpj8EiqaIxj4IIU4sSW6xo9koE9tuKKlLrzpTgdqq/5d8G5BXQ1oSs7x011Z8QZ+w+Y0nChL0jWIoecC+GRZU1kdfq0e7baslevlH4C2M= gmx\\\\0035oa744@WIN-AHGJRKCMM8T"
                    }
                    """;

            Response response =
                    given().spec(requestspec)
                            .body(reqBody) // Add request body
                            .when().post("/user/keys"); // Send POST request

            System.out.println(response.asPrettyString());
            id = response.path("id");
            responseSpec = new ResponseSpecBuilder().expectStatusCode(201).build();
        }

        @Test(priority = 2)
        public void getRequest(){
            Response response = given().spec(requestspec).when().get();
            System.out.println(response.asPrettyString());
            responseSpec = new ResponseSpecBuilder().expectStatusCode(200).build();
        }
        @Test(priority = 3)
        public void deleteRequest(){
            Response response = given().spec(requestspec).when().pathParam("keyID",id).delete("/{keyID}");
            System.out.println(response.asPrettyString());
            responseSpec = new ResponseSpecBuilder().expectStatusCode(204).build();
        }
    }
