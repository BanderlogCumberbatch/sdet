import org.helpers.PropertyProvider;
import org.pages.sql.HomePage;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.File;
import static org.helpers.CookieHelper.loadCookiesFromFile;
import static org.helpers.CookieHelper.saveCookiesToFile;

public class CookieTest extends BaseTest {
    private final File cookieFile = new File("cookies.json");

    HomePage homePage = new HomePage(driver);

    @DataProvider(name = "Login data")
    public Object[][] dpMethod() {
        return new Object[][]{
                {PropertyProvider.getInstance().getProperty("secret.login"), PropertyProvider.getInstance().getProperty("secret.password")},
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
    @Test(invocationCount = 2, dataProvider = "Login data")
    public void authTest(String login, String password) {
        // Первый запуск - авторизация и сохранение cookies
        if (cookieFile.length() == 0) {
            homePage.auth(login, password);
            saveCookiesToFile(driver, cookieFile);
        }
        // Повторный запуск - загрузка cookies
        else {
            loadCookiesFromFile(driver, cookieFile);
            driver.navigate().refresh(); // Обновляем страницу после загрузки cookies
            Assert.assertTrue(homePage.verifyLoggedInState(), "Авторизации через куки не происходит");
        }
    }

    /**
     * Действия после теста.
     */
    @AfterMethod
    public final void clearCookies() {
        driver.manage().deleteAllCookies();
    }

    /**
     * Закрытие драйвера.
     */
    @Override
    @AfterTest
    public void tearDown() {
        cookieFile.delete();
        driver.quit();
    }
}
