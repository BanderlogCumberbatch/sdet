package org.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementHelper {
    /**
     * Клик по элементу с ожиданием (10 секунд)
     * @param driver текущий веб-драйвер
     * @param element веб-элемент
     */
    public static void clickElement(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(element))
                .click();
    }
}
