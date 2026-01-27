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
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;


public class BrowserFactory {
    private static WebDriver driver;

    /**
     * Получить драйвер браузера, указанного в .properties
     * @param driverOptions опции для браузера
     * @return драйвер браузера
     */
    public static WebDriver getDriver(AbstractDriverOptions<?> driverOptions) {
        if (driver == null) {
            String browser = PropertyProvider.getInstance().getProperty("browser");

            switch (browser.toLowerCase()) {
                case "firefox":
                    driver = new FirefoxDriver((FirefoxOptions) driverOptions);
                    break;
                case "chrome":
                    driver = new ChromeDriver((ChromeOptions) driverOptions);
                    break;
                case "edge":
                    driver = new EdgeDriver((EdgeOptions) driverOptions);
                    break;
                case "ie":
                    driver = new InternetExplorerDriver((InternetExplorerOptions) driverOptions);
                    break;
            }

        }
        return driver;
    }

    /**
     * Получить драйвер браузера для Selenium Grid, указанного в .properties
     * @return драйвер Selenium Grid браузера
     */
    public static WebDriver getGridDriver() throws MalformedURLException {
        if (driver == null) {
            String browser = PropertyProvider.getInstance().getProperty("browser");
            DesiredCapabilities capabilities = new DesiredCapabilities();

            switch (browser.toLowerCase()) {
                case "firefox":
                    capabilities.setBrowserName("firefox");
                    driver = new RemoteWebDriver(new URL(PropertyProvider.getInstance().getProperty("grid.hub.url")), capabilities);
                    break;
                case "chrome":
                    capabilities.setBrowserName("chrome");
                    driver = new RemoteWebDriver(new URL(PropertyProvider.getInstance().getProperty("grid.hub.url")), capabilities);
                    break;
                case "edge":
                    capabilities.setBrowserName("MicrosoftEdge");
                    driver = new RemoteWebDriver(new URL(PropertyProvider.getInstance().getProperty("grid.hub.url")), capabilities);
                    break;
                case "ie":
                    capabilities.setBrowserName("internet explorer");
                    driver = new RemoteWebDriver(new URL(PropertyProvider.getInstance().getProperty("grid.hub.url")), capabilities);
                    break;
            }

        }
        return driver;
    }
}