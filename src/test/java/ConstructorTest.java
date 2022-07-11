import com.codeborne.selenide.Configuration;
import data.FactoryTestData;
import data.TestConsts;
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
import client.UserClient;

import java.io.IOException;
import java.util.UUID;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.junit.Assert.assertEquals;

public class ConstructorTest extends BaseTest{
    MainPage mainPage = page(MainPage.class);
    AccountPage accountPage = page(AccountPage.class);
    LoginPage loginPage = page(LoginPage.class);

    @Before
    @Override
    public void setUp() {
        super.setUp();
        user = userClient.createUser(FactoryTestData.createNewTestUser()).as(UserResponse.class);
        user.getUser().setPassword(TestConsts.USER_PASS);
        mainPage.clickOpenLoginPageButton();
        loginPage.executeLogin(user.getUser().getEmail(), user.getUser().getPassword());
        mainPage.clickOpenAccountPageButton();
    }

    @Test
    @DisplayName("Открытие страницы конструктора со страницы аккаунта")
    @Description("Выполнение перехода со страницы аккаунта на страницу конструктора через кнопку Конструктор")
    public void shouldClickConstructorLinkForOpenMainPageFromAccountPage() {
        accountPage.clickConstructorLink();
        webdriver().shouldHave(url(TestConsts.URL_MAIN_PAGE));
        assertEquals("Не отображена текст \"Соберите бургер\"", "Соберите бургер", mainPage.getCreateBurgerLabel()
        );
    }

    @Test
    @DisplayName("Открытие страницы конструктора со страницы аккаунта и открытие вкладки Булки")
    @Description("Выполнение перехода со страницы аккаунта на страницу конструктора через логотип Stellar Burgers " +
            "и проверка переключения на кладку Булки")
    public void shouldClickLogoSBForOpenMainPageAndOpenTabBun() {
        accountPage.clickBSLogoLink();
        webdriver().shouldHave(url(TestConsts.URL_MAIN_PAGE));
        assertEquals("Не отображена текст \"Соберите бургер\"", "Соберите бургер", mainPage.getCreateBurgerLabel()
        );
        mainPage.selectConstructorTab("Соусы");
        mainPage.selectConstructorTab("Булки");
        assertEquals("Не выбрана вкладка \"Булки\"", "Булки", mainPage.getTextActiveConstructorTab()
        );
    }

    @Test
    @DisplayName("Открытие страницы конструктора со страницы аккаунта и открытие вкладки Соусы")
    @Description("Выполнение перехода со страницы аккаунта на страницу конструктора через логотип Stellar Burgers " +
            "и нажатие на кладку Соусы")
    public void shouldClickLogoSBForOpenMainPageAndOpenTabSauces() {
        accountPage.clickBSLogoLink();
        webdriver().shouldHave(url(TestConsts.URL_MAIN_PAGE));
        assertEquals("Не отображена текст \"Соберите бургер\"", "Соберите бургер", mainPage.getCreateBurgerLabel()
        );
        mainPage.selectConstructorTab("Соусы");
        assertEquals("Не выбрана вкладка \"Соусы\"", "Соусы", mainPage.getTextActiveConstructorTab()
        );
    }

    @Test
    @DisplayName("Открытие страницы конструктора со страницы аккаунта и открытие вкладки Начинки")
    @Description("Выполнение перехода со страницы аккаунта на страницу конструктора через логотип Stellar Burgers " +
            "и нажатие на кладку Начинки")
    public void shouldClickLogoSBForOpenMainPageAndOpenTabFilling() {
        accountPage.clickBSLogoLink();
        webdriver().shouldHave(url(TestConsts.URL_MAIN_PAGE));
        assertEquals("Не отображена текст \"Соберите бургер\"", "Соберите бургер", mainPage.getCreateBurgerLabel()
        );
        mainPage.selectConstructorTab("Начинки");
        assertEquals("Не выбрана вкладка \"Начинки\"", "Начинки", mainPage.getTextActiveConstructorTab()
        );
    }

}
