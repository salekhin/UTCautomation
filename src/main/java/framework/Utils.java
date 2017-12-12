package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static pages.BasePage.driver;

/**
 * Created by salekhin on 12.12.2017.
 */
public class Utils {

    public static void waitForElementPresent(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement element = wait.until(ExpectedConditions.visibilityOf(webElement));
    }


    public static boolean isElementPresent(String xpath) {
        return driver.findElements(By.xpath(xpath)).size() > 0;
    }

    public static boolean isElementPresent(By by) {
        return driver.findElements(by).size() > 0;
    }

    public static boolean isElementPresent(WebElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
