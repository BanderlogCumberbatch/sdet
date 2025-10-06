package org.pages.sql;

import io.qameta.allure.Step;
import org.helpers.ElementHelper;
import org.helpers.Wait;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.BasePage;

/**
 * Класс домашней страницы sql-ex.ru
 */
public class HomePage extends BasePage {
    /**
     * Кнопка авторизации
     */
    @FindBy(xpath = "//*[contains(@name, 'subm1')]")
    WebElement authButton;

    /**
     * Кнопка авторизации без регистрации
     */
    @FindBy(xpath = "//*[contains(@name, 'subm2')]")
    WebElement authWithoutRegistrationButton;

    /**
     * Поле для ввода логина
     */
    @FindBy(xpath = "//input[contains(@name, 'login')]")
    WebElement loginInput;

    /**
     * Поле для ввода пароля
     */
    @FindBy(xpath = "//input[contains(@name, 'psw')]")
    WebElement passwordInput;

    /**
     * Кнопка для выхода из аккаунта
     */
    @FindBy(xpath = "//a[contains(@href, '/logout.php')]")
    WebElement logoutButton;

    /**
     * Псевдоним
     */
    @FindBy(xpath = "//a[contains(@href, '/personal.php')]")
    WebElement pseudonymElem;

    public HomePage(WebDriver webDriver) { super(webDriver); }

    /**
     * Войти как гость
     */
    @Step("Authorization without registration")
    public void authWithoutRegistrationButton() {
        ElementHelper.clickElement(driver, authWithoutRegistrationButton);
    }

    /**
     * Авторизация
     * @param login Логин
     * @param password Пароль
     */
    @Step("Authorization")
    public void auth(String login, String password) {
        Wait.waitUntilVisible(driver, loginInput);
        loginInput.sendKeys(login);
        passwordInput.sendKeys(password);
        ElementHelper.clickElement(driver, authButton);
    }

    /**
     * Проверить состояние авторизации
     * @param pseudonym ожидаемый псевдоним
     * @return Boolean
     */
    @Step("Verify logged in state")
    public Boolean verifyLoggedInState(String pseudonym) {
        Wait.waitUntilVisible(driver, logoutButton);
        return pseudonymElem.getText().equals(pseudonym);
    }

    /**
     * Проверить состояние авторизации
     * @return true - авторизован, false - нет
     */
    @Step("Verify logged in state")
    public Boolean verifyLoggedInState() {
        try {
            Wait.waitUntilVisible(driver, logoutButton, 1);
        }
        catch (TimeoutException e) {
            return false;
        }
        return logoutButton.isDisplayed();
    }

    /**
     * Выйти из аккаунта
     */
    @Step("Logout")
    public void logout() {
        ElementHelper.clickElement(driver, logoutButton);
    }
}
