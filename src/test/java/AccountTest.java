import data.FactoryTestData;
import data.TestConsts;
import data.UserResponse;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import page.AccountPage;
import page.LoginPage;
import page.MainPage;
import client.UserClient;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.junit.Assert.assertEquals;

public class AccountTest extends BaseTest{
    MainPage mainPage = page(MainPage.class);
    AccountPage accountPage = page(AccountPage.class);
    LoginPage loginPage = page(LoginPage.class);
    UserClient userClient = new UserClient();

    @Before
    @Override
    public void setUp() {
        super.setUp();

        user = userClient.createUser(FactoryTestData.createNewTestUser()).as(UserResponse.class);
        user.getUser().setPassword(TestConsts.USER_PASS);
        mainPage.clickOpenLoginPageButton();
        loginPage.executeLogin(user.getUser().getEmail(), user.getUser().getPassword());
    }

    @Test
    @DisplayName("Открытие страницы аккаунта")
    @Description("Выполнение перехода с главной страницы на страницу аккаунта")
    public void shouldOpenAccountFromMainPage() {
        mainPage.clickOpenAccountPageButton();
        webdriver().shouldHave(url(TestConsts.BASE_URL + "/account/profile"));
        assertEquals("Не отображена текст страницы \"Профиль\"", "Профиль", accountPage.getAccountLabel()
        );
    }

    @Test
    @DisplayName("Выполнение выхода из аккаунта")
    @Description("Выполнение перехода на страницу аккаунта и выхода из аккаунта")
    public void shouldExitFromAccountPage() {
        mainPage.clickOpenAccountPageButton();
        webdriver().shouldHave(url(TestConsts.BASE_URL + "/account/profile"));
        assertEquals("Не отображена текст страницы \"Профиль\"", "Профиль", accountPage.getAccountLabel());
        accountPage.clickExitButton();
        webdriver().shouldHave(url(TestConsts.BASE_URL + "/login"));
        LoginPage loginPage = page(LoginPage.class);
        assertEquals("Не отображена страница Логина", "Вход", loginPage.getLoginLabel());
    }
}
