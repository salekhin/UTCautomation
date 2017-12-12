package pages;


import framework.Settings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by salekhin on 12.12.2017.
 */
public class BasePage {
    public static WebDriver driver;
    public static Settings settings;

    public static <T extends BasePage> T initPage(Class<T> pageClass) {
        return PageFactory.initElements(driver, pageClass);
    }
}
