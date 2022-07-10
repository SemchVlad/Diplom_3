import io.qameta.allure.Step;
import page.LoginPage;
import page.MainPage;
import page.RegisterPage;

import static com.codeborne.selenide.Selenide.page;

public class TestSteps {
    static RegisterPage registerPage = page(RegisterPage.class);
    static LoginPage loginPage = page(LoginPage.class);
    static MainPage mainPage = page(MainPage.class);

    @Step("Ввод данных на форме регистрации")
    public static void setRegisterData(String name, String email, String password) {
        registerPage.setName(name);
        registerPage.setEmail(email);
        registerPage.setPassword(password);
        registerPage.clickButtonRegister();
    }

    @Step("Выполнение входа")
    public static void executeLogin(String email, String password) {
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();
    }

    @Step("Перейти в личный кабинет")
    public static void openAccount() {
        mainPage.clickOpenAccountPageButton();
    }
}
