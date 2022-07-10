import com.codeborne.selenide.Configuration;
import data.User;
import data.UserResponse;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import page.AccountPage;
import page.LoginPage;
import page.MainPage;
import utils.RestUtils;

import java.io.IOException;
import java.util.UUID;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.junit.Assert.assertEquals;

public class AccountTest {
    MainPage mainPage;
    AccountPage accountPage;
    private UserResponse user;

    @Before
    public void setUp() throws IOException {
        // Растягиваем браузер по размерам экрана пользователя.
        Configuration.startMaximized = true;
        mainPage = open("https://stellarburgers.nomoreparties.site/",
                MainPage.class);
        accountPage = page(AccountPage.class);
        String name = "user_" + UUID.randomUUID();
        String email = name + "@ya.ru";
        user = RestUtils.createUser(new User(email, "password", name)).as(UserResponse.class);
        user.getUser().setPassword("password");
        mainPage.clickOpenLoginPageButton();
        TestSteps.executeLogin(user.getUser().getEmail(), user.getUser().getPassword());
    }

    @After
    public void tearDown() throws IOException {
        if(user.getAccessToken() != null) {
            RestUtils.deleteUser(user.getAccessToken());
        }
        user = null;
    }

    @Test
    @DisplayName("Открытие страницы аккаунта")
    @Description("Выполнение перехода с главной страницы на страницу аккаунта")
    public void shouldOpenAccountFromMainPage() {
        TestSteps.openAccount();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/account/profile"));
        assertEquals("Не отображена текст страницы \"Профиль\"", "Профиль", accountPage.getAccountLabel()
        );
    }

    @Test
    @DisplayName("Выполнение выхода из аккаунта")
    @Description("Выполнение перехода на страницу аккаунта и выхода из аккаунта")
    public void shouldExitFromAccountPage() {
        TestSteps.openAccount();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/account/profile"));
        assertEquals("Не отображена текст страницы \"Профиль\"", "Профиль", accountPage.getAccountLabel());
        accountPage.clickExitButton();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/login"));
        LoginPage loginPage = page(LoginPage.class);
        assertEquals("Не отображена страница Логина", "Вход", loginPage.getLoginLabel());
    }
}
