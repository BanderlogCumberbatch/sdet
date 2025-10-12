package grid;

import io.qameta.allure.*;
import org.pages.login.LoggedPage;
import org.pages.login.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTestGrid extends BaseTest {
    LoginPage loginPage;

    @DataProvider(name = "Login data")
    public Object[][] dpMethod() {
        return new Object[][]{
                {"angular", "password", "***"},
                {"angular", "passwor", "*!*"},
                {"angula", "password", "!**"}
        };
    }

    /**
     * Действия перед тестом.
     */
    @BeforeMethod
    public final void setup() {
        driver.get("https://www.way2automation.com/angularjs-protractor/registeration/#/login");
        loginPage = new LoginPage(driver);
    }

    @Test(description = "Проверка авторизации на way2automation.com",dataProvider = "Login data")
    @Severity(value = SeverityLevel.NORMAL)
    @Epic(value = "Authorization")
    @Features(value = {@Feature(value = "Check elements"), @Feature(value = "Login")})
    @Story(value = "As user")
    public void AuthTest(String login, String password, String desc) {
        LoggedPage loggedPage = loginPage.login(login, password, desc);
        // Проверка авторизации
        Assert.assertTrue(loggedPage.checkMessage("You're logged in!!"), "Сообщение \"You're logged in!!\" не отображается");
    }

    /**
     * Действия после теста.
     */
    @AfterMethod
    public final void refreshAndClearCookies() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }
}
