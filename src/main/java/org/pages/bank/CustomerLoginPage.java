package org.pages.bank;

import io.qameta.allure.Step;
import org.helpers.ElementHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Класс страницы входа в качестве пользователя Way2Automation Banking App
 */
public class CustomerLoginPage extends DefaultPage {
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
    @Step("Customer login")
    public CustomerControlPage customerLogin() {
        ElementHelper.clickElement(driver, customerMenuItem);
        ElementHelper.clickElement(driver, optionMarySue);
        ElementHelper.clickElement(driver, processButton);
        return new CustomerControlPage(driver);
    }
}
