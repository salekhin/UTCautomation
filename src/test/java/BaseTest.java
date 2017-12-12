import framework.Settings;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pages.BasePage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static pages.BasePage.driver;

/**
 * Created by salekhin on 12.12.2017.
 */
public class BaseTest {

    private static Settings settings = new Settings();


    @BeforeSuite(alwaysRun = true)
    public static void beforeSuite() {
        BasePage.driver = settings.getDriver();
        BasePage.settings = settings;
        BasePage.driver.get(settings.getBaseUrl());
        BasePage.driver.manage().window().maximize();
        BasePage.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @AfterSuite(alwaysRun = true)
    public static void afterClass() {
        BasePage.driver.quit();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                TakesScreenshot ts = (TakesScreenshot) driver;
                File source = ts.getScreenshotAs(OutputType.FILE);
                String dateTime = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                FileUtils.copyFile(source, new File("./src/test/resources/Screenshots/" + result.getName() + dateTime + ".png"));
                System.out.println("Screenshot taken");
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }
    }
}
