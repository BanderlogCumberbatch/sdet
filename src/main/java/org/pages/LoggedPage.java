package org.pages;

import org.helpers.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoggedPage {
    protected final WebDriver driver;

    /**
     * Кнопка разлогирования
     */
    @FindBy(xpath = "//*[contains(@href, '#/login')]")
    WebElement logoutButton;

    /**
     * Сообщение об успешной авторизации
     */
    @FindBy(xpath = "(//p[text()=\"You're logged in!!\"])")
    WebElement loggedMessage;

    public LoggedPage(final WebDriver webDriver) {
        try {
            PageFactory.initElements(webDriver, this);
            this.driver = webDriver;
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Проверка отображения сообщения об успешной авторизации
     */
    public void checkLogged() {
        Wait.waitUntilVisible(driver, loggedMessage);
    }

    /**
     * Разлогиниться
     * @return текущий экземпляр класса
     */
    public LoginPage logout() {
        Wait.waitThenClick(driver, logoutButton);
        return new LoginPage(driver);
    }
}
