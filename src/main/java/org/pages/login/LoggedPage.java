package org.pages.login;

import io.qameta.allure.Step;
import org.helpers.ElementHelper;
import org.helpers.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.BasePage;

/**
 * Класс страницы после успешной авторизации
 */
public class LoggedPage extends BasePage {
    /**
     * Кнопка разлогирования
     */
    @FindBy(xpath = "//*[contains(@href, '#/login')]")
    WebElement logoutButton;

    public LoggedPage(WebDriver webDriver) { super(webDriver); }

    /**
     * Проверка появления ожидаемого сообщения
     * @param message Ожидаемое сообщение
     * @return true - сообщение отображается, false - нет
     */
    @Step("Check message")
    public Boolean checkMessage(String message) {
        Wait.waitUntilVisible(driver, logoutButton);
        return driver.findElement(By.xpath(String.format("(//p[text()=\"%s\"])", message))).isDisplayed();
    }

    /**
     * Разлогиниться
     * @return текущий экземпляр класса
     */
    @Step("Logout")
    public LoginPage logout() {
        ElementHelper.clickElement(driver, logoutButton);
        return new LoginPage(driver);
    }
}
