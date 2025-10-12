package org.pages.bank;

import io.qameta.allure.Step;
import org.helpers.ElementHelper;
import org.helpers.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Класс со страницей управления пользователя
 */
public class CustomerControlPage extends DefaultPage {
    /**
     * Приветственное сообщение
     */
    @FindBy(xpath = "//div[@class='borderM box padT20 ng-scope']/div/strong")
    WebElement welcomeMessage;

    /**
     * Баланс средств
     */
    @FindBy(xpath = "//div[@ng-hide='noAccount']/strong[2]")
    WebElement balance;

    /**
     * Кнопка депозита
     */
    @FindBy(xpath = "//*[contains(@ng-click, 'deposit()')]")
    WebElement depositButton;

    /**
     * Кнопка снятия средств
     */
    @FindBy(xpath = "//*[contains(@ng-click, 'withdrawl()')]")
    WebElement withdrawlButton;

    /**
     * Кнопка просмотра списка транзакций
     */
    @FindBy(xpath = "//*[contains(@ng-click, 'transactions()')]")
    WebElement transactionsButton;

    /**
     * Форма для ввода кол-ва средств
     */
    @FindBy(xpath = "//*[contains(@ng-model, 'amount')]")
    WebElement amountInput;

    /**
     * Кнопка подтверждения транзакции
     */
    @FindBy(xpath = "//*[contains(@type, 'submit')]")
    WebElement submitButton;

    /**
     * Сообщение об успешной транзакции
     */
    @FindBy(xpath = "//*[contains(@ng-show, 'message')]")
    WebElement transactionMessage;

    public CustomerControlPage(WebDriver webDriver) {super(webDriver);}

    /**
     * Получить текст из приветственного сообщения
     * @return String
     */
    @Step("Get welcome message text")
    public String getWelcomeMessageText() {
        Wait.waitUntilVisible(driver, welcomeMessage);
        return welcomeMessage.getText();
    }

    /**
     * Совершить депозит
     * @param amount кол-во средств для совершения депозита
     */
    @Step("Make a deposit")
    public void deposit(String amount) {
        ElementHelper.clickElement(driver, depositButton);
        Wait.waitUntilVisible(driver, amountInput);
        amountInput.sendKeys(amount);
        submitButton.click();
    }

    /**
     * Совершить снятие средств
     * @param amount кол-во средств для снятия
     */
    @Step("Make a withdraw")
    public void withdraw(String amount) {
        ElementHelper.clickElement(driver, withdrawlButton);
        Wait.waitUntilVisible(driver, amountInput);
        amountInput.sendKeys(amount);
        submitButton.click();
    }

    /**
     * Появилось ли сообщение об успешной транзакции
     * @return Boolean
     */
    @Step("Transaction message displayed check")
    public Boolean isTransactionMessageDisplayed() {
        return transactionMessage.isDisplayed();
    }

    /**
     * Получить сообщение об успешной транзакции
     * @return String
     */
    @Step("Get transaction message text")
    public String getTransactionMessageText() {
        Wait.waitUntilVisible(driver, transactionMessage);
        return transactionMessage.getText();
    }

    /**
     *  Подождать 800 миллисекунд для проведения транзакции и перейти на страницу со списком транзакций
     */
    @Step("Go to transaction page")
    public TransactionsPage goToTransactionPage() {
        Wait.wait(800);
        ElementHelper.clickElement(driver, transactionsButton);
        return new TransactionsPage(driver);
    }

    /**
     * Получить баланс средств
     * @return int
     */
    @Step("Get balance")
    public int getBalance() {
        Wait.waitUntilVisible(driver, balance);
        return Integer.parseInt(balance.getText());
    }
}
