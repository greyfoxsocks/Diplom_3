package stellar.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class UserAPI {
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/api";

    @Step("Регистрация пользователя: email={email}, name={name}")
    public static void registerUser(String email, String password, String name) {
        Map<String, String> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);
        body.put("name", name);

        given()
                .contentType("application/json")
                .body(body)
                .when()
                .post(BASE_URL + "/auth/register");
    }

    @Step("Авторизация пользователя: email={email}")
    public static String loginUser(String email, String password) {
        Map<String, String> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);

        Response response = given()
                .contentType("application/json")
                .body(body)
                .when()
                .post(BASE_URL + "/auth/login");

        return response.jsonPath().getString("accessToken");
    }

    @Step("Удаление пользователя")
    public static void deleteUser(String token) {
        given()
                .header("Authorization", token)
                .when()
                .delete(BASE_URL + "/auth/user");
    }
}