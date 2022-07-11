package page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ForgotPasswordPage {
    @FindBy(how = How.XPATH, using="//a[text()='Войти']")
    private SelenideElement loginLink;

    public void clickLoginLink() {
        loginLink.click();
    }

}
