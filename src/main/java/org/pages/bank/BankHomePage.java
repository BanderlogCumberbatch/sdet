package org.pages.bank;

import org.helpers.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Класс стартовой страницы Way2Automation Banking App
 */
public class BankHomePage extends BankBasePage {
    /**
     * Кнопка входа в качестве пользователя
     */
    @FindBy(xpath = "//*[contains(@ng-click, 'customer()')]")
    WebElement customerLoginButton;

    /**
     * Кнопка входа в качестве менеджера
     */
    @FindBy(xpath = "//*[contains(@ng-click, 'manager()')]")
    WebElement bankMangerLoginButton;

    /**
     * Кнопка перехода на форму регистрации
     */
    @FindBy(xpath = "//*[contains(@href, 'https://www.way2automation.com/angularjs-protractor/banking/registrationform.html')]")
    WebElement sampleFormButton;

    public BankHomePage(final WebDriver webDriver) {super(webDriver);}

    /**
     * Переход на страницу менеджера
     * @return текущий экземпляр класса
     */
    public ManagerPage goToBankManagerPage() {
        Wait.waitThenClick(driver, bankMangerLoginButton);
        return new ManagerPage(driver);
    }

    /**
     * Переход на форму регистрации
     * @return текущий экземпляр класса
     */
    public SampleFormPage goToSampleFormPage() {
        Wait.waitThenClick(driver, sampleFormButton);
        return new SampleFormPage(driver);
    }

    /**
     * Переход на страницу входа в качестве пользователя
     * @return текущий экземпляр класса
     */
    public CustomerLoginPage goToCustomerLoginPage() {
        Wait.waitThenClick(driver, customerLoginButton);
        return new CustomerLoginPage(driver);
    }
}