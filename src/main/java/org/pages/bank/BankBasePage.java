package org.pages.bank;

import org.helpers.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Класс базовой страницы для Way2Automation Banking App
 */
public class BankBasePage {
    protected final WebDriver driver;
    /**
     * Кнопка для возвращения на стартовую страницу.
     */
    @FindBy(xpath = "//*[contains(@ng-click, 'home()')]")
    WebElement toStartPageButton;

    public BankBasePage(final WebDriver webDriver) {
        try {
            PageFactory.initElements(webDriver, this);
            this.driver = webDriver;
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Переходит на стартовую страницу.
     * @return текущий экземпляр класса
     */
    public BankHomePage goToStartPage() {
        Wait.waitThenClick(driver, toStartPageButton);
        return new BankHomePage(driver);
    }
}
