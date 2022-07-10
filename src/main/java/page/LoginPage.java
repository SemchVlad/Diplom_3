package page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {

    @FindBy(how = How.XPATH, using="//div[@class='Auth_login__3hAey']/h2")
    private SelenideElement loginLabel;

    @FindBy(how = How.XPATH, using="//label[text()='Email']/../input")
    private SelenideElement email;
    @FindBy(how = How.XPATH, using="//label[text()='Пароль']/../input")
    private SelenideElement password;
    @FindBy(how = How.XPATH, using="//button[text()='Войти']")
    private SelenideElement loginButton;

    public void setEmail(String value) {
        email.setValue(value);
    }

    public void setPassword(String value) {
        password.setValue(value);
    }

    public String getLoginLabel() {
        return loginLabel.getText();
    }

    public void clickLoginButton() {
        loginButton.click();
    }
}
