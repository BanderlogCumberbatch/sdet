package org.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Базовый класс тестов.
 */
public class BasePage {
    protected final WebDriver driver;

    public BasePage(final WebDriver webDriver) {
        try {
            PageFactory.initElements(webDriver, this);
            this.driver = webDriver;
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Получить текущий URL
     * @return String
     */
    @Step("Get URL")
    public String getUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Проверить вертикальный скролл на странице (JavascriptExecutor)
     * @return Boolean
     */
    @Step("Check vertical scroll")
    public Boolean checkVerticalScroll() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (Boolean) js.executeScript("return document.documentElement.scrollHeight > document.documentElement.clientHeight;");
    }

    /**
     * Проверить горизонтальный скролл на странице (JavascriptExecutor)
     * @return Boolean
     */
    @Step("Check horizontal scroll")
    public Boolean checkHorizontalScroll() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (Boolean) js.executeScript("return document.documentElement.scrollWidth > document.documentElement.clientWidth;");
    }

    /**
     * Убрать фокус от активного элемента (JavascriptExecutor)
     */
    @Step("Unfocus active element")
    public void unfocusActiveElement() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.activeElement.blur();");
    }

}
