package org.pages.bank;

import org.helpers.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.concurrent.TimeUnit;

/**
 * Класс со страницей управления пользователя
 */
public class CustomerControlPage extends BankBasePage {
    /**
     * Приветственное сообщение
     */
    @FindBy(css = "body > div.ng-scope > div > div.ng-scope > div > div:nth-child(1) > strong")
    WebElement welcomeMessage;

    /**
     * Баланс средств
     */
    @FindBy(css = "body > div.ng-scope > div > div.ng-scope > div > div:nth-child(3) > strong:nth-child(2)")
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
    public String getWelcomeMessageText() {
        Wait.waitUntilVisible(driver, welcomeMessage);
        return welcomeMessage.getText();
    }

    /**
     * Совершить депозит
     * @param amount кол-во средств для совершения депозита
     */
    public void deposit(String amount) {
        Wait.waitThenClick(driver, depositButton);
        Wait.waitUntilVisible(driver, amountInput);
        amountInput.sendKeys(amount);
        submitButton.click();
    }

    /**
     * Совершить снятие средств
     * @param amount кол-во средств для снятия
     */
    public void withdraw(String amount) {
        Wait.waitThenClick(driver, withdrawlButton);
        Wait.waitUntilVisible(driver, amountInput);
        amountInput.sendKeys(amount);
        submitButton.click();
    }

    /**
     * Появилось ли сообщение об успешной транзакции
     * @return Boolean
     */
    public Boolean isTransactionMessageDisplayed() {
        return transactionMessage.isDisplayed();
    }

    /**
     * Получить сообщение об успешной транзакции
     * @return String
     */
    public String getTransactionMessageText() {
        Wait.waitUntilVisible(driver, transactionMessage);
        return transactionMessage.getText();
    }

    /**
     *  Подождать 1 секунду для проведения транзакции и перейти на страницу со списком транзакций
     */
    public TransactionsPage goToTransactionPage() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Wait.waitThenClick(driver, transactionsButton);
        return new TransactionsPage(driver);
    }

    /**
     * Получить баланс средств
     * @return int
     */
    public int getBalance() {
        Wait.waitUntilVisible(driver, balance);
        return Integer.parseInt(balance.getText());
    }
}
