package utils;

import data.User;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestUtils {

    private static RequestSpecification getSpec() throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream("src/main/resources/env.properties"));
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(props.getProperty("baseURI"))
                .build();
    }

    @Step("Создание пользователя")
    public static Response createUser(User user) throws IOException {
        return given()
                .spec(getSpec())
                .body(user)
                .post("/api/auth/register");
    }

    @Step("Логин пользователя")
    public static Response loginUser(User user) throws IOException{
        return given().spec(getSpec())
                .body(user)
                .post("/api/auth/login");
    }

    @Step("Удаление пользователя")
    public static void deleteUser(String accessToken) throws IOException{
        given().spec(getSpec()).headers("Authorization", accessToken, "Content-Type",
                        ContentType.JSON).delete("/api/auth/user");
    }
}
