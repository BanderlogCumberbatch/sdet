package org.helpers;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.ByteArrayInputStream;

public class AllureHelper {

    public static void takeScreenshot(WebDriver driver, String testName) {
        if (driver instanceof TakesScreenshot) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Скриншот " + testName, "image/png",
                    new ByteArrayInputStream(screenshot), "png");
        }
    }
}
