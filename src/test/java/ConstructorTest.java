import com.codeborne.selenide.Configuration;
import data.User;
import data.UserResponse;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import page.AccountPage;
import page.MainPage;
import utils.RestUtils;

import java.io.IOException;
import java.util.UUID;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.junit.Assert.assertEquals;

public class ConstructorTest {
    MainPage mainPage;
    AccountPage accountPage;
    private UserResponse user;

    @Before
    public void setUp() throws IOException {
        // Растягиваем браузер по размерам экрана пользователя.
        Configuration.startMaximized = true;
        mainPage = open("https://stellarburgers.nomoreparties.site/",
                MainPage.class);
        String name = "user_" + UUID.randomUUID();
        String email = name + "@ya.ru";
        user = RestUtils.createUser(new User(email, "password", name)).as(UserResponse.class);
        user.getUser().setPassword("password");
        mainPage.clickOpenLoginPageButton();
        TestSteps.executeLogin(user.getUser().getEmail(), user.getUser().getPassword());
        mainPage.clickOpenAccountPageButton();
        accountPage = page(AccountPage.class);
    }

    @After
    public void tearDown() throws IOException {
        if(user.getAccessToken() != null) {
            RestUtils.deleteUser(user.getAccessToken());
        }
        user = null;
    }

    @Test
    @DisplayName("Открытие страницы конструктора со страницы аккаунта")
    @Description("Выполнение перехода со страницы аккаунта на страницу конструктора через кнопку Конструктор")
    public void shouldClickConstructorLinkForOpenMainPageFromAccountPage() {
        accountPage.clickConstructorLink();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
        assertEquals("Не отображена текст \"Соберите бургер\"", "Соберите бургер", mainPage.getCreateBurgerLabel()
        );
    }

    @Test
    @DisplayName("Открытие страницы конструктора со страницы аккаунта и открытие вкладки Булки")
    @Description("Выполнение перехода со страницы аккаунта на страницу конструктора через логотип Stellar Burgers " +
            "и проверка переключения на кладку Булки")
    public void shouldClickLogoSBForOpenMainPageAndOpenTabBun() {
        accountPage.clickBSLogoLink();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
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
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
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
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
        assertEquals("Не отображена текст \"Соберите бургер\"", "Соберите бургер", mainPage.getCreateBurgerLabel()
        );
        mainPage.selectConstructorTab("Начинки");
        assertEquals("Не выбрана вкладка \"Начинки\"", "Начинки", mainPage.getTextActiveConstructorTab()
        );
    }

}
