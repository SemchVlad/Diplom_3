import com.codeborne.selenide.Configuration;
import data.User;
import data.UserResponse;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import page.ForgotPasswordPage;
import page.LoginPage;
import page.MainPage;
import utils.RestUtils;

import java.io.IOException;
import java.util.UUID;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.junit.Assert.assertEquals;

public class ForgotPasswordTest {
    ForgotPasswordPage forgotPasswordPage;
    LoginPage loginPage;

    @Before
    public void setUp() throws IOException {
        // Растягиваем браузер по размерам экрана пользователя.
        Configuration.startMaximized = true;
        forgotPasswordPage = open("https://stellarburgers.nomoreparties.site/forgot-password",
                ForgotPasswordPage.class);
        loginPage = page(LoginPage.class);
    }

    @Test
    @DisplayName("Выполнение перехода со страницы восстановления пароля на страницу логина")
    public void shouldAccountPageFromMainPage() {
        forgotPasswordPage.clickLoginLink();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/login"));
        assertEquals("Не отображена страница Логина", "Вход", loginPage.getLoginLabel());
    }
}
