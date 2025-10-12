package org.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

    /**
     * Перетащить один элемент в другой
     * @param driver текущий веб-драйвер
     * @param draggableElem перетаскиваемый элемент
     * @param droppableElem принимающий элемент
     */
    public static void dragAndDrop(WebDriver driver, WebElement draggableElem, WebElement droppableElem) {
        Wait.waitUntilVisible(driver, draggableElem);
        Wait.waitUntilVisible(driver, droppableElem);
        Actions actions = new Actions(driver);
        actions.dragAndDrop(draggableElem, droppableElem).perform();
    }
}
