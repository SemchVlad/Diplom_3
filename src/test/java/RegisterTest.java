import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import page.LoginPage;
import page.RegisterPage;

import java.util.UUID;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegisterTest {
    // переменная Главной страницы
    RegisterPage registerPage;
    LoginPage loginPage;
    private String name;
    private String email;

    @Before
    public void setUp() {
        // Растягиваем браузер по размерам экрана пользователя.
        Configuration.startMaximized = true;
        registerPage = open("https://stellarburgers.nomoreparties.site/register",
                RegisterPage.class);
        loginPage = page(LoginPage.class);
        name = "user_" + UUID.randomUUID();
        email = name + "@ya.ru";
    }

    @Test
    @DisplayName("Регистрация пользователя")
    @Description("Регистрация пользователя с корректными данными")
    public void shouldRegisterUser() {
        TestSteps.setRegisterData(name,email,"teasd123");
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/login"));
        assertEquals("Не отображена страница Логина", "Вход", loginPage.getLoginLabel()
        );
    }

    @Test
    @DisplayName("Некорректная регистрация пользователя")
    @Description("Выполнение регистрации с указанием пароля меньше 6 символов")
    public void shouldGetErrorForIncorrectPassword() {
        TestSteps.setRegisterData(name,email,"tea3");
        assertEquals("Не получено ожидаемое сообщение об ошибке",
                "Некорректный пароль", registerPage.getErrorMessage());
    }

    @Test
    @DisplayName("Переход со страницы регистрации на страницу логина")
    public void shouldOpenLoginFromRegisterPage() {
        registerPage.clickLoginLink();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/login"));
        assertEquals("Не отображена страница Логина", "Вход", loginPage.getLoginLabel());
    }
}
