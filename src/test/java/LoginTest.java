import com.codeborne.selenide.Configuration;
import data.User;
import data.UserResponse;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import page.LoginPage;
import page.MainPage;
import utils.RestUtils;

import java.io.IOException;
import java.util.UUID;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.junit.Assert.assertEquals;

public class LoginTest {
    // переменная Главной страницы
    MainPage mainPage;
    LoginPage loginPage;
    private UserResponse user;

    @Before
    public void setUp() throws IOException {
        // Растягиваем браузер по размерам экрана пользователя.
        Configuration.startMaximized = true;
        mainPage = open("https://stellarburgers.nomoreparties.site/",
                MainPage.class);
        loginPage = page(LoginPage.class);
        String name = "user_" + UUID.randomUUID();
        String email = name + "@ya.ru";
        user = RestUtils.createUser(new User(email, "password", name)).as(UserResponse.class);
        user.getUser().setPassword("password");
    }

    @After
    public void tearDown() throws IOException {
        if(user.getAccessToken() != null) {
            RestUtils.deleteUser(user.getAccessToken());
        }
        user = null;
    }

    @Test
    @DisplayName("Переход на страницу логина и выполнение входа")
    @Description("Выполнение перехода с главной страницы на страницу логина и выполнение входа")
    public void shouldOpenLoginFromMainPageAndSuccessLogin() {
        mainPage.clickOpenLoginPageButton();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/login"));
        assertEquals("Не отображена страница Логина", "Вход", loginPage.getLoginLabel()
        );
        TestSteps.executeLogin(user.getUser().getEmail(), user.getUser().getPassword());
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
        assertEquals("Не отображена текст \"Соберите бургер\" на главной странице", "Соберите бургер", mainPage.getCreateBurgerLabel()
        );
    }

    @Test
    @DisplayName("Выполнение входа по нажатию кнопки страницы аккаунта")
    @Description("Выполнение перехода с главной страницы на страницу логина кнопку \"Войти в аккаунт\" и выполнение входа")
    public void shouldClickButtonOpenAccountPageFromMainPageAndSuccessLogin() {
        mainPage.clickOpenAccountPageButton();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/login"));
        assertEquals("Не отображена страница Логина", "Вход", loginPage.getLoginLabel());
        TestSteps.executeLogin(user.getUser().getEmail(), user.getUser().getPassword());
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
        assertEquals("Не отображена текст \"Соберите бургер\" на главной странице", "Соберите бургер", mainPage.getCreateBurgerLabel()
        );
    }
}
