import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.pages.bank.*;
import org.pages.login.LoginPage;
import org.pages.site.HomePage;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * Базовый класс тестов.
 */
public class BaseTest {
    /**
     * Веб-браузер.
     */
    WebDriver driver;

    HomePage homePage;
    LoginPage loginPage;
    StartPage startPage;
    SampleFormPage sampleFormPage;
    AddCustomerPage addCustomerPage;
    OpenAccountPage openAccountPage;
    CustomerLoginPage customerLoginPage;
    CustomerControlPage customerControlPage;
    TransactionsPage transactionsPage;
    CustomersPage customersPage;

    /**
     * Действия при инициализации.
     */
    @BeforeClass
    void init(final ITestContext context) {
        int pageLoadTimeout = 10;
        ChromeOptions options = new ChromeOptions()
                .addArguments("--remote-allow-origins=*")
                .addArguments("--disable-gpu")
                .addArguments("--disable-dev-shm-usage")
                .addArguments("--disable-notifications");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts()
                .pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
        context.setAttribute("driver", driver);
        String webUrl = "https://www.way2automation.com/";
        driver.get(webUrl);
        homePage = new HomePage(driver);
    }

    /**
     * Закрытие драйвера.
     */
    @AfterTest
    public final void tearDown() { driver.quit(); }
}
