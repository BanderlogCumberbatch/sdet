package org.pages.bank;

import org.helpers.Wait;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Класс страницы входа в аккаунт в режиме менеджера Way2Automation Banking App
 */
public class OpenAccountPage extends ManagerPage {
    /**
     * Выпадающее меню с выбором пользователя
     */
    @FindBy(id = "userSelect")
    WebElement customerMenuItem;

    /**
     * Опция с добавленным пользователем
     */
    @FindBy(xpath = "(//option[text()='Mary Sue'])")
    WebElement optionMarySue;

    /**
     * Выпадающее меню с выбором валюты
     */
    @FindBy(id = "currency")
    WebElement currencyMenuItem;

    /**
     * Доллар
     */
    @FindBy(xpath = "(//option[text()='Dollar'])")
    WebElement optionDollar;

    /**
     * Кнопка входа в аккаунт
     */
    @FindBy(xpath = "(//button[text()='Process'])")
    WebElement processButton;

    public OpenAccountPage(WebDriver webDriver) { super(webDriver); }

    /**
     * Вход в аккаунт
     */
    public void openAccount() {
        Wait.waitThenClick(driver, customerMenuItem);
        Wait.waitThenClick(driver, optionMarySue);
        Wait.waitThenClick(driver, currencyMenuItem);
        Wait.waitThenClick(driver, optionDollar);
        Wait.waitThenClick(driver, processButton);
        Alert alert = driver.switchTo().alert();
        Wait.waitUntilAlert(driver);
        alert.accept();
    }
}
