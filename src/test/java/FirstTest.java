import framework.Utils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.BasePage;
import pages.Homepage;
import pages.LoginPage;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by salekhin on 12.12.2017.
 */
public class FirstTest extends BaseTest {
    private LoginPage loginPage;
    private Homepage homepage;


    @BeforeMethod(alwaysRun = true)
    public void init() {
        loginPage = BasePage.initPage(LoginPage.class);
    }

    @Test
    public void getHome() {
        homepage = loginPage.login();
        assertThat(Utils.isElementPresent(homepage.icon)).isTrue();
    }
}
