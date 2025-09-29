package org.pages.login;

import org.helpers.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Класс страницы после успешной авторизации
 */
public class LoggedPage {
    protected final WebDriver driver;

    /**
     * Кнопка разлогирования
     */
    @FindBy(xpath = "//*[contains(@href, '#/login')]")
    WebElement logoutButton;

    public LoggedPage(final WebDriver webDriver) {
        try {
            PageFactory.initElements(webDriver, this);
            this.driver = webDriver;
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Проверка появления ожидаемого сообщения
     * @param message Ожидаемое сообщение
     * @return true - сообщение отображается, false - нет
     */
    public Boolean checkMessage(String message) {
        Wait.waitUntilVisible(driver, logoutButton);
        return driver.findElement(By.xpath(String.format("(//p[text()=\"%s\"])", message))).isDisplayed();
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
