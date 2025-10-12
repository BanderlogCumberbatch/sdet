import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import factory.BrowserFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * Базовый класс тестов.
 */
@Getter
public class BaseTest {
    // Метод для получения драйвера (используется в listener)
    /**
     * Веб-браузер.
     */
    WebDriver driver;

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
        driver = BrowserFactory.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts()
                .pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
        context.setAttribute("driver", driver);
    }

    /**
     * Закрытие драйвера.
     */
    @AfterTest
    public void tearDown() { driver.quit(); }

}
