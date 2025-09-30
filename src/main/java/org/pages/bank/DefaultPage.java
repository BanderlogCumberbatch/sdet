package org.pages.bank;

import org.helpers.ElementHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.BasePage;

/**
 * Класс страницы с кнопкой возвращения на стартовую страницу Way2Automation Banking App
 */
public class DefaultPage extends BasePage {
    /**
     * Кнопка для возвращения на стартовую страницу.
     */
    @FindBy(xpath = "//*[contains(@ng-click, 'home()')]")
    WebElement toStartPageButton;

    public DefaultPage(final WebDriver webDriver) {super(webDriver);}

    /**
     * Переходит на стартовую страницу.
     * @return текущий экземпляр класса
     */
    public StartPage goToHomePage() {
        ElementHelper.clickElement(driver, toStartPageButton);
        return new StartPage(driver);
    }
}
