import org.pages.LoggedPage;
import org.pages.LoginPage;
import org.pages.MembershipPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Way2automationTests extends BaseTest {
    LoginPage loginPage;

    @Test(description = "1.")
    public void TestOne() {
        // Все основные элементы отображаются
        homePage.checkMainElements();
        SoftAssert softAssert = new SoftAssert();
        // Хедер содержит номера телефонов, ссылку на skype, почту и ссылки на соц.сети.
        softAssert.assertEquals(homePage.getInfoHeaderText(), """
                +919711-111-558
                +919711-191-558
                +1 646-480-0603
                seleniumcoaching
                trainer@way2automation.com""");
        // Проверка кнопок навигации (вперед и назад): работают корректно (меняют слайды)
        softAssert.assertNotEquals(homePage.checkNavButtonNext(), "lazyloaded");
        softAssert.assertEquals(homePage.checkNavButtonPrev(), "lazyloaded");
        // Футер отображается, содержит: адрес, номера телефонов и эмейлы
        softAssert.assertTrue(homePage.getFooterText().contains("""
                CDR Complex, 3rd Floor, Naya Bans Market, Sector 15, Noida, Near sec-16 Metro Station
                +91 97111-11-558
                +91 97111-91-558
                trainer@way2automation.com
                seleniumcoaching@gmail.com"""));
        softAssert.assertAll();
    }

    @Test(description = "2.", priority = 1)
    public void TestTwo() {
        // Отображение меню при скроллинге страницы вниз: меню должно оставаться видимым после прокрутки страницы
        homePage.checkNavItemAfterScroll();
    }

    @Test(description = "3.", priority = 2)
    public void TestThree() {
        // Проверка перехода по меню навигации на другие страницы
        MembershipPage membershipPage = homePage.goToMembershipPage();
        Assert.assertEquals(membershipPage.getUrl(), "https://www.way2automation.com/lifetime-membership-club/");
        Assert.assertEquals(membershipPage.getTitle(), "LIFETIME MEMBERSHIP CLUB");
    }

    @Test(description = "4.", priority = 3)
    public void TestFour() {
        // Проверка полей ввода
        driver.get("https://www.way2automation.com/angularjs-protractor/registeration/#/login");
        loginPage = new LoginPage(driver);
        Assert.assertEquals(loginPage.getUsername(), "");
        Assert.assertEquals(loginPage.getPassword(), "");
        Assert.assertEquals(loginPage.getLoginButtonDisabled(), "true");
        // Проверка успешной авторизации
        LoggedPage loggedPage = loginPage.login("angular", "password", "***");
        loggedPage.checkLogged();
        // Проверка успешного разлогирования
        loginPage = loggedPage.logout();
        Assert.assertEquals(loginPage.getUsername(), "");
        Assert.assertEquals(loginPage.getPassword(), "");
        // Проверка авторизации с невалидными данными
        loginPage.login("angular", "passwor", "***");
        loginPage.checkInvalidAuth();
    }
}
