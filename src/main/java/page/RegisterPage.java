package page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegisterPage {
    @FindBy(how = How.XPATH, using="//label[text()='Имя']/../input")
    private SelenideElement name;
    @FindBy(how = How.XPATH, using="//label[text()='Email']/../input")
    private SelenideElement email;
    @FindBy(how = How.XPATH, using="//label[text()='Пароль']/../input")
    private SelenideElement password;
    @FindBy(how = How.XPATH, using="//button[text()='Зарегистрироваться']")
    private SelenideElement registerButton;
    @FindBy(how = How.XPATH, using="//*/div/main/div/form/fieldset[3]/div/p")
    private SelenideElement errorMessage;
    @FindBy(how = How.XPATH, using="//a[text()='Войти']")
    private SelenideElement loginLink;

    public void setName(String value) {
        name.setValue(value);
    }

    public void setEmail(String value) {
        email.setValue(value);
    }

    public void setPassword(String value) {
        password.setValue(value);
    }

    public void clickButtonRegister() {
        registerButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.text();
    }

    public void clickLoginLink() {
        loginLink.click();
    }

    @Step("Ввод данных на форме регистрации")
    public void setRegisterData(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickButtonRegister();
    }
}
