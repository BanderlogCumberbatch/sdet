package org.helpers;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class AlertHelper {

    /**
     * Принять alert
     * @param driver текущий-веб драйвер
     */
    public static void acceptAlert(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        Wait.waitUntilAlert(driver);
        alert.accept();
    }

    /**
     * Ввести текст в alert и принять его
     * @param driver текущий-веб драйвер
     * @param text текст для ввода в Input Box
     */
    public static void inputTextInAlert(WebDriver driver, String text) {
        Alert alert = driver.switchTo().alert();
        Wait.waitUntilAlert(driver);
        alert.sendKeys(text);
        alert.accept();
    }
}
