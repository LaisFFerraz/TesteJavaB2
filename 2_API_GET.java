package scr.test.java;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static io.restassured.RestAssured.given;

public class 2_API_GET {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/";
    }

    @Test
public void testGetNonExistentPet() {
    int petId = 999999; // ID que provavelmente não existe
    Response response = given()
            .when()
            .get("/pet/" + petId)
            .then()
            .extract().response();

    assertThat(response.getStatusCode()).isEqualTo(404); // Espera-se um erro 404 para pet não encontrado
    }
}

