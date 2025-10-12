package org.pages.login;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.helpers.ElementHelper;
import org.helpers.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.BasePage;

/**
 * Класс страницы для проверки авторизации
 */
public class LoginPage extends BasePage {

    /**
     * Поле для ввода имени пользователя
     */
    @FindBy(xpath = "//*[contains(@ng-model, 'Auth.user.name')]")
    WebElement usernameInput;

    /**
     * Поле для ввода пароля
     */
    @FindBy(xpath = "//*[contains(@ng-model, 'Auth.user.password')]")
    WebElement passwordInput;

    /**
     * Поле для ввода описания
     */
    @FindBy(xpath = "//*[contains(@ng-model, 'model[options.key]')]")
    WebElement usernameDescInput;

    /**
     * Кнопка авторизации
     */
    @FindBy(xpath = "//*[contains(@ng-click, 'Auth.login()')]")
    WebElement loginButton;

    /**
     * Сообщение о некорректных данных при авторизации
     */
    @FindBy(xpath = "//*[contains(@ng-if, 'Auth.error')]")
    WebElement invalidAuthMessage;

    public LoginPage(WebDriver webDriver) {super(webDriver);}

    /**
     * Получить текст из формы для ввода имени пользователя
     * @return String
     */
    @Step("Get username")
    public String getUsername() {
        Wait.waitUntilVisible(driver, usernameInput);
        return usernameInput.getText();
    }

    /**
     * Получить текст из формы для ввода пароля
     * @return String
     */
    @Step("Get password")
    public String getPassword() {
        Wait.waitUntilVisible(driver, passwordInput);
        return passwordInput.getText();
    }

    /**
     * Получить свойство disabled кнопки авторизации
     * @return String
     */
    @Step("Get login button disabled")
    public String getLoginButtonDisabled() {
        Wait.waitUntilVisible(driver, loginButton);
        return loginButton.getDomAttribute("disabled");
    }

    /**
     * Производит авторизацию
     * @param username логин
     * @param password пароль
     * @param usernameDesc ?
     * @return текущий экземпляр класса
     */
    @Epic(value = "Authorization")
    @Feature(value = "Login")
    @Story(value = "As user")
    @Step("Login")
    public LoggedPage login(String username, String password, String usernameDesc) {
        Wait.waitUntilVisible(driver, usernameInput);
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        usernameDescInput.sendKeys(usernameDesc);
        loginButton.click();
        return new LoggedPage(driver);
    }

    /**
     * Получить текст сообщения о некорректных данных при авторизации
     * @return String
     */
    @Step("Get invalid authorize message")
    public String getInvalidAuthMessage() {
        Wait.waitUntilVisible(driver, invalidAuthMessage);
        return invalidAuthMessage.getText();
    }

    /**
     * Перевести фокус на поле ввода имени пользователя
     */
    @Step("Focus on username input")
    public void focusOnUsernameInput() {
        ElementHelper.clickElement(driver, usernameInput);
    }
}
