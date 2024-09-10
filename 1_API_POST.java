package scr.test.java;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static io.restassured.RestAssured.given;

public class 1_API_POST {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/";
    }

    @Test
    public void testCreateOrder() {
        // Definindo o corpo da requisição
        String requestBody = "{"
                + "\"id\": 1,"
                + "\"petId\": 1,"
                + "\"quantity\": 1,"
                + "\"shipDate\": \"2024-09-10T00:00:00.000Z\","
                + "\"status\": \"placed\","
                + "\"complete\": true"
                + "}";

        // Enviando a requisição POST para o endpoint /store/order
        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/store/order")
                .then()
                .extract().response();

        // Validando a resposta
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getInt("id")).isEqualTo(1);
        assertThat(response.jsonPath().getInt("petId")).isEqualTo(1);
        assertThat(response.jsonPath().getInt("quantity")).isEqualTo(1);
        assertThat(response.jsonPath().getString("status")).isEqualTo("placed");
        assertThat(response.jsonPath().getBoolean("complete")).isTrue();
    }
}
