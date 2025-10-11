package org.pages.training;

import org.helpers.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.pages.BasePage;

public class DragAndDropPage extends BasePage {

    /**
     * Iframe элемент окна DragAndDrop
     */
    @FindBy(xpath = "//*[contains(@class, 'demo-frame')]")
    WebElement iframe;

    public DragAndDropPage(WebDriver webDriver) { super(webDriver); }

    /**
     * Перетащить draggable элемент в droppable
     */
    public void dragAndDrop() {
        Wait.waitUntilVisible(driver, iframe);
        // Переключить контекст
        driver.switchTo().frame(iframe);
        try {
            WebElement draggableElem = driver.findElement(By.id("draggable"));
            WebElement droppableElem = driver.findElement(By.id("droppable"));
            Wait.waitUntilVisible(driver, draggableElem);
            Wait.waitUntilVisible(driver, droppableElem);
            Actions actions = new Actions(driver);
            actions.dragAndDrop(draggableElem, droppableElem).perform();
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    /**
     * Получить текст принимающего элемента
     * @return String
     */
    public String getDroppableText() {
        Wait.waitUntilVisible(driver, iframe);
        driver.switchTo().frame(iframe);
        try {
            WebElement droppableElem = driver.findElement(By.id("droppable"));
            Wait.waitUntilVisible(driver, droppableElem, 3);
            return droppableElem.getText();
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    /**
     * Перейти на страницу
     */
    public void getPage() {
        String webUrl = "https://way2automation.com/way2auto_jquery/droppable.php#load_box";
        driver.get(webUrl);
    }

}
