import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.pages.HomePage;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseTest {

    WebDriver driver;

    HomePage homePage;

    /**
     * Действия при инициализации.
     */
    @BeforeClass
    void init(final ITestContext context) {
        int pageLoadTimeout = 10;

        driver = new ChromeDriver(new ChromeOptions()
                .addArguments("--remote-allow-origins=*")
                .addArguments("--disable-gpu")
                .addArguments("--start-maximized")
                .addArguments("--disable-dev-shm-usage"));

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
