package org.pages.bank;

import org.helpers.ElementHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Класс страницы менеджера Way2Automation Banking App
 */
public class ManagerPage extends DefaultPage {
    /**
     * Кнопка перехода на страницу добавления пользователя
     */
    @FindBy(xpath = "//*[contains(@ng-click, 'addCust()')]")
    WebElement addCustomerPageButton;

    /**
     * Кнопка перехода на страницу входа в аккаунт
     */
    @FindBy(xpath = "//*[contains(@ng-click, 'openAccount()')]")
    WebElement openAccountButton;

    /**
     * Кнопка перехода на страницу вывода списка пользователей
     */
    @FindBy(xpath = "//*[contains(@ng-click, 'showCust()')]")
    WebElement customersButton;

    public ManagerPage(final WebDriver webDriver) {super(webDriver);}

    /**
     * Переходит на страницу добавления пользователя
     * @return текущий экземпляр класса
     */
    public AddCustomerPage goToAddCustomerPage() {
        ElementHelper.clickElement(driver, addCustomerPageButton);
        return new AddCustomerPage(driver);
    }

    /**
     * Переходит на страницу входа в аккаунт.
     * @return текущий экземпляр класса
     */
    public OpenAccountPage goToOpenAccountPage() {
        ElementHelper.clickElement(driver, openAccountButton);
        return new OpenAccountPage(driver);
    }

    /**
     * Переходит на страницу со списком пользователей.
     * @return текущий экземпляр класса
     */
    public CustomersPage goToCustomersPage() {
        ElementHelper.clickElement(driver, customersButton);
        return new CustomersPage(driver);
    }
}

