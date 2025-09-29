package org.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Класс ожиданий
 */
public class Wait {

    /**
     * Простое ожидание
     * @param timeoutSeconds время ожидания в секундах
     */
    public static void wait(int timeoutSeconds) {
        try {
            TimeUnit.SECONDS.sleep(timeoutSeconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Ожидание видимости веб-элемента (10 секунд)
     * @param driver текущий веб-драйвер
     * @param element веб-элемент
     */
    public static void waitUntilVisible(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Ожидание видимости веб-элемента с настройкой времени
     * @param driver текущий веб-драйвер
     * @param element веб-элемент
     * @param timeoutSeconds время ожидания в секундах
     */
    public static void waitUntilVisible(WebDriver driver, WebElement element, int timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Ожидание видимости веб-элемента (10 секунд) и клик по нему
     * @param driver текущий веб-драйвер
     * @param element веб-элемент
     */
    public static void waitThenClick(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(element))
                .click();
    }

    /**
     * Ожидание видимости веб-элемента с настройкой времени и клик по нему
     * @param driver текущий веб-драйвер
     * @param element веб-элемент
     * @param timeoutSeconds время ожидания в секундах
     */
    public static void waitThenClick(WebDriver driver, WebElement element, int timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOf(element))
                .click();
    }

    /**
     * Ожидание появления алерта (10 секунд)
     * @param driver текущий веб-драйвер
     */
    public static void waitUntilAlert(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());
    }

    /**
     * Ожидание появления алерта с настройкой времени
     * @param driver текущий веб-драйвер
     * @param timeoutSeconds время ожидания в секундах
     */
    public static void waitUntilAlert(WebDriver driver, int timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.alertIsPresent());
    }
}
