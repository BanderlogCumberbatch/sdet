package grid;

import org.pages.login.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JavaScriptExecutorGridGridTest extends BaseGridTest {
    LoginPage loginPage;

    @Test
    public void test() {
        driver.get("https://www.way2automation.com/angularjs-protractor/registeration/#/login");
        loginPage = new LoginPage(driver);
        Assert.assertFalse(loginPage.checkHorizontalScroll() || loginPage.checkVerticalScroll(), "Ожидается отстуствие скролла на странице");
        loginPage.focusOnUsernameInput();
        Assert.assertEquals(driver.switchTo().activeElement().getDomAttribute("ng-model"), "Auth.user.name", "Активный элемент не соответствует полю ввода");
        loginPage.unfocusActiveElement();
        Assert.assertNotEquals(driver.switchTo().activeElement().getDomAttribute("ng-model"), "Auth.user.name", "Активный элемент соответствует полю ввода");
    }
}
