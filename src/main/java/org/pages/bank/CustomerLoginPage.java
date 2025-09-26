package org.pages.bank;

import org.helpers.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Класс страницы входа в качестве пользователя Way2Automation Banking App
 */
public class CustomerLoginPage extends BankBasePage {
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
     * Кнопка входа в качестве пользователя
     */
    @FindBy(xpath = "(//button[text()='Login'])")
    WebElement processButton;

    public CustomerLoginPage(final WebDriver webDriver) {super(webDriver);}

    /**
     * Вход в качестве пользователя
     * @return текущий экземпляр класс
     */
    public CustomerControlPage customerLogin() {
        Wait.waitThenClick(driver, customerMenuItem);
        Wait.waitThenClick(driver, optionMarySue);
        Wait.waitThenClick(driver, processButton);
        return new CustomerControlPage(driver);
    }
}
