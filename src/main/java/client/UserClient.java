package client;

import data.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserClient extends RestApiClient {
    final static private String PATH = "/api/auth/";

    @Step("Создание пользователя")
    public Response createUser(User user) {
        return given()
                .spec(getSpec())
                .body(user)
                .post(PATH + "register");
    }

    @Step("Логин пользователя")
    public Response loginUser(User user) {
        return given().spec(getSpec())
                .body(user)
                .post(PATH + "login");
    }

    @Step("Удаление пользователя")
    public void deleteUser(String accessToken){
        given().spec(getSpec()).
                headers("Authorization", accessToken).
                delete(PATH + "user");
    }
}
