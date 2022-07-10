import com.codeborne.selenide.Configuration;
import data.TestConsts;
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

public class RegisterTest extends BaseTest {
    // переменная Главной страницы
    RegisterPage registerPage;
    LoginPage loginPage = page(LoginPage.class);
    private String name;
    private String email;

    @Before
    @Override
    public void setUp() {
        super.setUp();
        registerPage = open(TestConsts.BASE_URL + "/register",
                RegisterPage.class);

        name = "user_" + UUID.randomUUID();
        email = name + "@ya.ru";
    }

    @Test
    @DisplayName("Регистрация пользователя")
    @Description("Регистрация пользователя с корректными данными")
    public void shouldRegisterUser() {
        registerPage.setRegisterData(name, email, TestConsts.USER_PASS);
        webdriver().shouldHave(url(TestConsts.BASE_URL + "/login"));
        assertEquals("Не отображена страница Логина", "Вход", loginPage.getLoginLabel()
        );
    }

    @Test
    @DisplayName("Некорректная регистрация пользователя")
    @Description("Выполнение регистрации с указанием пароля меньше 6 символов")
    public void shouldGetErrorForIncorrectPassword() {
        registerPage.setRegisterData(name, email,TestConsts.USER_PASS_NOT_VALID);
        assertEquals("Не получено ожидаемое сообщение об ошибке",
                "Некорректный пароль", registerPage.getErrorMessage());
    }

    @Test
    @DisplayName("Переход со страницы регистрации на страницу логина")
    public void shouldOpenLoginFromRegisterPage() {
        registerPage.clickLoginLink();
        webdriver().shouldHave(url(TestConsts.BASE_URL + "/login"));
        assertEquals("Не отображена страница Логина", "Вход", loginPage.getLoginLabel());
    }
}
