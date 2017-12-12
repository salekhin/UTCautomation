package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by salekhin on 12.12.2017.
 */
public class Homepage extends BasePage {
    @FindBy(xpath = ICON_XPATH)
    public WebElement icon;


    public static final String ICON_XPATH = "//a[@href='/']";

}
