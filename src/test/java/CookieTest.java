import org.helpers.PropertyProvider;
import org.pages.sql.HomePage;
import org.testng.Assert;
import org.testng.annotations.*;

public class CookieTest extends BaseTest {

    HomePage homePage = new HomePage(driver);

    @DataProvider(name = "Login data")
    public Object[][] dpMethod() {
        return new Object[][]{
                {PropertyProvider.getInstance().getProperty("sql.site.login"), PropertyProvider.getInstance().getProperty("sql.site.password")},
        };
    }

    /**
     * Действия перед тестом.
     */
    @BeforeMethod
    public final void setup() {
        homePage = new HomePage(driver);
        driver.get("https://www.sql-ex.ru/index.php");
    }

    /**
     * Тест с загрузкой куки
     */
    @Test(dataProvider = "Login data")
    public void authTest(String login, String password) {
        homePage.authWithCookies(login, password);
        Assert.assertTrue(homePage.verifyLoggedInState(), "Авторизации через куки не происходит");
    }
}
