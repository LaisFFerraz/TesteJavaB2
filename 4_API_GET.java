package scr.test.java;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static io.restassured.RestAssured.given;

public class 4_API_GET {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/";
    }

    @Test
public void testFindPetsByStatusPending() {
    String status = "pending";
    Response response = given()
            .queryParam("status", status)
            .when()
            .get("/pet/findByStatus")
            .then()
            .extract().response();

    assertThat(response.getStatusCode()).isEqualTo(200);
    assertThat(response.jsonPath().getList("$")).isNotEmpty();
    assertThat(response.jsonPath().getList("status")).contains(status);
    }    
}

