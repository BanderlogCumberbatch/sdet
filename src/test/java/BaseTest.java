import listeners.AllureTestListener;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import factory.BrowserFactory;
import org.testng.annotations.Listeners;

import java.time.Duration;

/**
 * Базовый класс тестов.
 */
@Getter
@Listeners(AllureTestListener.class)
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
