package scr.test.java;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static io.restassured.RestAssured.given;

public class 3_API_PUT {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/";
    }

    @Test
    public void testUpdatePet() {
        String requestBody = "{"
                + "\"id\": 1,"
                + "\"name\": \"Bobby Updated\","
                + "\"status\": \"sold\""
                + "}";
    
        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .put("/pet")
                .then()
                .extract().response();
    
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getInt("id")).isEqualTo(1);
        assertThat(response.jsonPath().getString("name")).isEqualTo("Bobby Updated");
        assertThat(response.jsonPath().getString("status")).isEqualTo("sold");
    }
    
}

