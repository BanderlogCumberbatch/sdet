import org.pages.login.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JavaScriptExecutorTest extends BaseTest {
    LoginPage loginPage;

    @Test
    public void test() {
        driver.get("https://www.way2automation.com/angularjs-protractor/registeration/#/login");
        loginPage = new LoginPage(driver);
        Boolean horizontalScroll = loginPage.checkHorizontalScroll();
        Boolean verticalScroll = loginPage.checkVerticalScroll();
        Assert.assertFalse(horizontalScroll || verticalScroll, "Ожидается отстуствие скролла на странице");
        loginPage.focusOnUsernameInput();
        String elementDomAttribute = driver.switchTo().activeElement().getDomAttribute("ng-model");
        Assert.assertEquals(elementDomAttribute, "Auth.user.name", "Активный элемент не соответствует полю ввода");
        loginPage.unfocusActiveElement();
        elementDomAttribute = driver.switchTo().activeElement().getDomAttribute("ng-model");
        Assert.assertNotEquals(elementDomAttribute, "Auth.user.name", "Активный элемент соответствует полю ввода");
    }
}
