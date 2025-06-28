package stellar.api;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UserAPI {
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/api";

    public static void registerUser(String email, String password, String name) {
        given()
                .contentType("application/json")
                .body(String.format(
                        "{\"email\":\"%s\",\"password\":\"%s\",\"name\":\"%s\"}",
                        email, password, name))
                .when()
                .post(BASE_URL + "/auth/register");
    }

    public static String loginUser(String email, String password) {
        Response response = given()
                .contentType("application/json")
                .body(String.format(
                        "{\"email\":\"%s\",\"password\":\"%s\"}",
                        email, password))
                .when()
                .post(BASE_URL + "/auth/login");

        return response.jsonPath().getString("accessToken");
    }

    public static void deleteUser(String token) {
        given()
                .header("Authorization", token)
                .when()
                .delete(BASE_URL + "/auth/user");
    }
}