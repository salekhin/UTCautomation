package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by salekhin on 12.12.2017.
 */
public class LoginPage extends BasePage {

    @FindBy(id = "password")
    WebElement passwordField;
    @FindBy(xpath = LOGIN_BUTTON_XPATH)
    WebElement loginButton;

    public Homepage login() {
        String password = "access4site!";
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();
        return initPage(Homepage.class);
    }

    public static final String LOGIN_BUTTON_XPATH = "//input[@value='LOGIN']";

}
