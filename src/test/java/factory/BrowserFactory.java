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


public class BrowserFactory {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = PropertyProvider.getInstance().getProperty("browser");

            switch (browser.toLowerCase()) {
                case "firefox":
                    FirefoxOptions ffOptions = new FirefoxOptions();
                    driver = new FirefoxDriver(ffOptions);
                    break;
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    driver = new ChromeDriver(chromeOptions);
                    break;
                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    driver = new EdgeDriver(edgeOptions);
                    break;
                case "ie":
                    InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                    driver = new InternetExplorerDriver(ieOptions);
                    break;
            }

        }
        return driver;
    }

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