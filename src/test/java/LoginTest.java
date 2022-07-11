import data.FactoryTestData;
import data.TestConsts;
import data.UserResponse;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import page.LoginPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.junit.Assert.assertEquals;

public class LoginTest extends BaseTest{
    LoginPage loginPage = page(LoginPage.class);

    @Before
    public void setUp() {
        super.setUp();

        user = userClient.createUser(FactoryTestData.createNewTestUser()).as(UserResponse.class);
        user.getUser().setPassword(TestConsts.USER_PASS);
    }

    @Test
    @DisplayName("Переход на страницу логина и выполнение входа")
    @Description("Выполнение перехода с главной страницы на страницу логина и выполнение входа")
    public void shouldOpenLoginFromMainPageAndSuccessLogin() {
        mainPage.clickOpenLoginPageButton();
        webdriver().shouldHave(url(TestConsts.URL_LOGIN));
        assertEquals("Не отображена страница Логина", "Вход", loginPage.getLoginLabel()
        );
        loginPage.executeLogin(user.getUser().getEmail(), user.getUser().getPassword());
        webdriver().shouldHave(url(TestConsts.URL_MAIN_PAGE));
        assertEquals("Не отображена текст \"Соберите бургер\" на главной странице", "Соберите бургер", mainPage.getCreateBurgerLabel()
        );
    }

    @Test
    @DisplayName("Выполнение входа по нажатию кнопки страницы аккаунта")
    @Description("Выполнение перехода с главной страницы на страницу логина кнопку \"Войти в аккаунт\" и выполнение входа")
    public void shouldClickButtonOpenAccountPageFromMainPageAndSuccessLogin() {
        mainPage.clickOpenAccountPageButton();
        webdriver().shouldHave(url(TestConsts.URL_LOGIN));
        assertEquals("Не отображена страница Логина", "Вход", loginPage.getLoginLabel());
        loginPage.executeLogin(user.getUser().getEmail(), user.getUser().getPassword());
        webdriver().shouldHave(url(TestConsts.URL_MAIN_PAGE));
        assertEquals("Не отображена текст \"Соберите бургер\" на главной странице", "Соберите бургер", mainPage.getCreateBurgerLabel()
        );
    }
}
