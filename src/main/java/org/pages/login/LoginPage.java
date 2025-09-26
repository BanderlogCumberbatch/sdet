package org.pages.login;

import org.helpers.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Класс страницы для проверки авторизации
 */
public class LoginPage {
    protected final WebDriver driver;

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

    public LoginPage(final WebDriver webDriver) {
        try {
            PageFactory.initElements(webDriver, this);
            this.driver = webDriver;
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Получить текст из формы для ввода имени пользователя
     * @return String
     */
    public String getUsername() {
        Wait.waitUntilVisible(driver, usernameInput);
        return usernameInput.getText();
    }

    /**
     * Получить текст из формы для ввода пароля
     * @return String
     */
    public String getPassword() {
        Wait.waitUntilVisible(driver, passwordInput);
        return passwordInput.getText();
    }

    /**
     * Получить свойство disabled кнопки авторизации
     * @return String
     */
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
    public LoggedPage login(String username, String password, String usernameDesc) {
        Wait.waitUntilVisible(driver, usernameInput);
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        usernameDescInput.sendKeys(usernameDesc);
        loginButton.click();
        return new LoggedPage(driver);
    }

    /**
     * Проверка отображения сообщения о некорректных данных при авторизации
     */
    public void checkInvalidAuth() {
        Wait.waitUntilVisible(driver, invalidAuthMessage);
    }
}
