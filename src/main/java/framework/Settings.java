package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by salekhin on 12.12.2017.
 */
public class Settings {


    private static final String SELENIUM_BASEURL = "selenium.baseUrl";
    private static final String SELENIUM_ISBASICAUTH = "selenium.isBasicAuthUrl";
    private static final String SELENIUM_BROWSER = "selenium.browser";
    private static final String PROPERTIES_PROPERTIES = "properties.properties";

    private String baseUrl;
    private String basicAuthUrl;
    private boolean isBasicAuthUrl;
    private BrowserType browser;
    private Properties properties = new Properties();

    public Settings() {
        loadSettings();
    }

    private void loadSettings() {
        properties = loadPropertiesFile();
        baseUrl = getPropertyOrThrowException(SELENIUM_BASEURL);
        isBasicAuthUrl = Boolean.parseBoolean(getPropertyOrThrowException(SELENIUM_ISBASICAUTH));
        browser = BrowserType.Browser(getPropertyOrThrowException(SELENIUM_BROWSER));
    }

    private Properties loadPropertiesFile() {
        try {
            String filename = getPropertyOrNull(PROPERTIES_PROPERTIES);
            if (filename == null) {
                filename = PROPERTIES_PROPERTIES;
            }

            InputStream stream = getClass().getClassLoader().getResourceAsStream(filename);
            if (stream == null) {
                stream = new FileInputStream(new File(filename));
            }
            Properties result = new Properties();
            result.load(stream);
            return result;
        } catch (IOException e) {
            throw new UnknownPropertyException("Property file is not found");
        }
    }

    public String getPropertyOrNull(String name) {
        return getProperty(name, false);
    }

    public String getPropertyOrThrowException(String name) {
        return getProperty(name, true);
    }

    private String getProperty(String name, boolean forceExceptionIfNotDefined) {
        String result;
        if ((result = System.getProperty(name, null)) != null && result.length() > 0) {
            return result;
        } else if ((result = getPropertyFromPropertiesFile(name)) != null && result.length() > 0) {
            return result;
        } else if (forceExceptionIfNotDefined) {
            throw new UnknownPropertyException("Unknown property: [" + name + "]");
        }
        return result;
    }

    private String getPropertyFromPropertiesFile(String name) {
        Object result = properties.get(name);
        if (result == null) {
            return null;
        } else {
            return result.toString();
        }
    }

    private WebDriver getDriver(BrowserType browserType) {
        switch (browserType) {
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", ".//src//test//resources//geckodriver.exe");
                return new FirefoxDriver();
            case IE:
                System.setProperty("webdriver.ie.driver", ".//src//test//resources//IEDriverServer.exe");
                return new InternetExplorerDriver();
            case GC:
                System.setProperty("webdriver.chrome.driver", ".//src//test//resources//chromedriver.exe");
                return new ChromeDriver();
            default:
                throw new UnknownBrowserException("Cannot create driver for unknown browser type");
        }
    }

    public WebDriver getDriver() {
        return getDriver(browser);
    }

    public String getBaseUrl() {
        if (isBasicAuthUrl == true) {
            basicAuthUrl = "http://admin:admin@localhost:8080";
            return basicAuthUrl;
        } else {
            return baseUrl;
        }
    }
}
