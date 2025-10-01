package org.pages;

import io.qameta.allure.Step;
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
}
