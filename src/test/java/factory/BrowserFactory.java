package factory;

import org.helpers.PropertyProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс, для создания экземпляров драйвера
 */
public class BrowserFactory {
    private static WebDriver driver;

    /**
     * Получить драйвер браузера, указанного в .properties.
     * @return драйвер браузера
     */
    public static WebDriver getDriver() {

        if (driver == null) {
            String browser = PropertyProvider.getEnvLocalInstance().getProperty("browser");

            switch (browser.toLowerCase()) {
                case "firefox":
                    FirefoxOptions ffOptions = new FirefoxOptions();
                    driver = new FirefoxDriver(ffOptions);
                    break;
                case "chrome":
                    ChromeOptions chOptions = new ChromeOptions()
                            .addArguments("--remote-allow-origins=*")
                            .addArguments("--disable-gpu")
                            .addArguments("--disable-dev-shm-usage")
                            .addArguments("--disable-notifications");
                    Map<String, Object> prefs = new HashMap<>();
                    prefs.put("profile.password_manager_leak_detection", false);
                    chOptions.setExperimentalOption("prefs", prefs);
                    driver = new ChromeDriver(chOptions);
                    break;
                case "edge":
                    EdgeOptions edOptions = new EdgeOptions();
                    driver = new EdgeDriver(edOptions);
                    break;
                case "ie":
                    InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                    driver = new InternetExplorerDriver(ieOptions);
                    break;
            }

        }
        return driver;
    }

    /**
     * Получить драйвер браузера для Selenium Grid, указанного в .properties.
     * @return драйвер Selenium Grid браузера
     */
    public static WebDriver getGridDriver() throws MalformedURLException {
        if (driver == null) {
            String browser = PropertyProvider.getEnvLocalInstance().getProperty("browser");
            DesiredCapabilities capabilities = new DesiredCapabilities();

            switch (browser.toLowerCase()) {
                case "firefox":
                    capabilities.setBrowserName("firefox");
                    break;
                case "chrome":
                    capabilities.setBrowserName("chrome");
                    break;
                case "edge":
                    capabilities.setBrowserName("MicrosoftEdge");
                    break;
                case "ie":
                    capabilities.setBrowserName("internet explorer");
                    break;
            }
            driver = new RemoteWebDriver(new URL(PropertyProvider.getEnvLocalInstance().getProperty("grid.hub.url")), capabilities);
        }
        return driver;
    }
}
