package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.text;

public class MainPage {
    @FindBy(how = How.XPATH, using="//button[text()='Войти в аккаунт']")
    private SelenideElement loginButton;

    @FindBy(how = How.XPATH, using="//p[text()='Личный Кабинет']/..")
    private SelenideElement accountButton;

    @FindBy(how = How.XPATH, using="//h1[text()='Соберите бургер']")
    private SelenideElement createBurgerLabel;

    @FindBy(how = How.XPATH, using="//div[contains(@class, 'tab_tab__1SPyG')]")
    private ElementsCollection constructorTabs;

    public void clickOpenLoginPageButton() {
        loginButton.click();
    }

    public void clickOpenAccountPageButton() {
        accountButton.click();
    }

    public String getCreateBurgerLabel() {
        return createBurgerLabel.getText();
    }

    public void selectConstructorTab(String value){
        constructorTabs.findBy(text(value)).click();
    }

    public String getTextActiveConstructorTab(){
        return constructorTabs.find(Condition.attribute("class",
                "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect")).getText();
    }
}
