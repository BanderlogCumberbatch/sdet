package org.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected final WebDriver driver;

    /**
     * Хедер с контактной информацией
     */
    @FindBy(xpath = "//*[contains(@class, 'ast-above-header-wrap  ')]")
    WebElement contactInfoHeader;

    /**
     * Блок с навигацией
     */
    @FindBy(xpath = "//*[contains(@class, 'ast-flex-grow-1 navigation-accessibility site-header-focus-item')]")
    WebElement navigationItem;

    /**
     * Футер
     */
    @FindBy(xpath = "//*[contains(@class, 'elementor elementor-25361 elementor-location-footer nitro-offscreen')]")
    WebElement footer;

    public BasePage(final WebDriver webDriver) {
        try {
            PageFactory.initElements(webDriver, this);
            this.driver = webDriver;
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Получить текст из хедера с контактной информацией
     */
    public String getInfoHeaderText() {
        contactInfoHeader.isDisplayed();
        return contactInfoHeader.getText();
    }


    /**
     * Получить текст из футера
     */
    public String getFooterText() {
        footer.isDisplayed();
        return footer.getText();
    }

    /**
     * Проверка отображения меню при скроллинге страницы вниз
     */
    public void checkNavItemAfterScroll() {
        navigationItem.isDisplayed();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        new WebDriverWait(driver, Duration.ofSeconds(1000));
        navigationItem.isDisplayed();
    }

}
