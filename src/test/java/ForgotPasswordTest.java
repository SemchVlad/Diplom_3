import com.codeborne.selenide.Configuration;
import data.TestConsts;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import page.ForgotPasswordPage;
import page.LoginPage;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.junit.Assert.assertEquals;

public class ForgotPasswordTest extends BaseTest{
    ForgotPasswordPage forgotPasswordPage;
    LoginPage loginPage = page(LoginPage.class);;

    @Before
    @Override
    public void setUp() {
        // Растягиваем браузер по размерам экрана пользователя.
        Configuration.startMaximized = true;
        forgotPasswordPage = open(TestConsts.BASE_URL + "/forgot-password",
                ForgotPasswordPage.class);
    }

    @Test
    @DisplayName("Выполнение перехода со страницы восстановления пароля на страницу логина")
    public void shouldAccountPageFromMainPage() {
        forgotPasswordPage.clickLoginLink();
        webdriver().shouldHave(url(TestConsts.BASE_URL + "/login"));
        assertEquals("Не отображена страница Логина", "Вход", loginPage.getLoginLabel());
    }
}
