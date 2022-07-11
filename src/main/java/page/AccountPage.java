package page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AccountPage {
    @FindBy(how = How.XPATH, using = "//a[text()='Профиль']")
    private SelenideElement accountLink;
    @FindBy(how = How.XPATH, using = "//button[text()='Выход']")
    private SelenideElement exitButton;

    @FindBy(how = How.XPATH, using = "//p[text()='Конструктор']/..")
    private SelenideElement constructorLink;
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'AppHeader_header__logo')]/a")
    private SelenideElement bSLogoLink;

    public String getAccountLabel() {
        return accountLink.getText();
    }

    public void clickExitButton() {
        exitButton.click();
    }

    public void clickBSLogoLink() {
        bSLogoLink.click();
    }

    public void clickConstructorLink() {
        constructorLink.click();
    }
}
