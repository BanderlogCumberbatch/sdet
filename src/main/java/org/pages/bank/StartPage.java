package org.pages.bank;

import org.helpers.ElementHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Класс стартовой страницы Way2Automation Banking App
 */
public class StartPage extends DefaultPage {
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

    public StartPage(final WebDriver webDriver) {super(webDriver);}

    /**
     * Переход на страницу менеджера
     * @return текущий экземпляр класса
     */
    public ManagerPage goToBankManagerPage() {
        ElementHelper.clickElement(driver, bankMangerLoginButton);
        return new ManagerPage(driver);
    }

    /**
     * Переход на форму регистрации
     * @return текущий экземпляр класса
     */
    public SampleFormPage goToSampleFormPage() {
        ElementHelper.clickElement(driver, sampleFormButton);
        return new SampleFormPage(driver);
    }

    /**
     * Переход на страницу входа в качестве пользователя
     * @return текущий экземпляр класса
     */
    public CustomerLoginPage goToCustomerLoginPage() {
        ElementHelper.clickElement(driver, customerLoginButton);
        return new CustomerLoginPage(driver);
    }
}