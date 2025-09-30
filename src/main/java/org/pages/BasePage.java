package org.pages;

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
}
