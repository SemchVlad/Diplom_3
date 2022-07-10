package data;

import client.UserClient;
import data.User;
import data.UserResponse;

import java.util.UUID;

public class FactoryTestData {
    UserClient userClient = new UserClient();

    public static User createNewTestUser(){
        String name = "user_" + UUID.randomUUID();
        String email = name + "@ya.ru";
        return new User(email, TestConsts.USER_PASS, name);
    }
}
