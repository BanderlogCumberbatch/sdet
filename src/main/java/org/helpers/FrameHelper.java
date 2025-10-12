package org.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FrameHelper {
    /**
     * Переключить контекст на iframe
     */
    public static void switchToIframe(WebDriver driver, WebElement iframe) {
        Wait.waitUntilVisible(driver, iframe);
        driver.switchTo().frame(iframe);
    }
}
