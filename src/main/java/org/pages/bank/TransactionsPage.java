package org.pages.bank;

import io.qameta.allure.Step;
import org.helpers.ElementHelper;
import org.helpers.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс страницы с таблицей транзакций
 */
public class TransactionsPage extends DefaultPage {
    /**
     * Кнопка возвращения на страницу управления пользователя
     */
    @FindBy(xpath = "//*[contains(@ng-click, 'back()')]")
    WebElement backButton;

    /**
     * Кнопка очистки всех транзакций
     */
    @FindBy(xpath = "//*[contains(@ng-click, 'reset()')]")
    WebElement resetButton;

    /**
     * Кнопка сортировки по дате-времени.
     */
    @FindBy(xpath = "//table[contains(@class, 'table-bordered')]/thead/tr/td[1]/a")
    WebElement sortByFirstNameButton;

    /**
     * Элемент второго столбца первой строки
     */
    @FindBy(xpath = "//table[contains(@class, 'table-bordered')]/tbody/tr[1]/td[2]")
    WebElement firstRowAmount;

    /**
     * Селектор выбирающий таблицу
     */
    private final String tableSelector = "//table[contains(@class, 'table-bordered')]/tbody/tr";

    public TransactionsPage(WebDriver webDriver) {super(webDriver);}

    /**
     * Сортирует по возрастанию даты-времени и возвращает строку с данными второго столбца первой строки таблицы (средства последней совершённой транзакции)
     * @return String
     */
    @Step("Get last transaction amount")
    public final String getLastTransactionAmount() {
        ElementHelper.clickElement(driver, sortByFirstNameButton);
        return Stream.of(firstRowAmount)
                .map(WebElement::getText)
                .collect(Collectors.joining(""));
    }

    /**
     * Возвращает сумму всех средств.
     * @return List<String>
     */
    @Step("Get amount sum")
    public final Double getAmountSum() {
        Wait.waitUntilVisible(driver, sortByFirstNameButton);
        return driver
                .findElements(By.xpath(tableSelector))
                .stream()
                .mapToDouble(row -> {
                    try {
                        String amountText = row.findElement(By.xpath("./td[2]")).getText();
                        String operationType = row.findElement(By.xpath("./td[3]")).getText();

                        double amount = Double.parseDouble(amountText.trim());

                        if ("Debit".equalsIgnoreCase(operationType)) {
                            return -amount;
                        } else if ("Credit".equalsIgnoreCase(operationType)) {
                            return amount;
                        } else {
                            return 0;
                        }
                    } catch (Exception e) {
                        return 0;
                    }
                })
                .sum();
    }

    /**
     * Подсчитывает общее количество операций в таблице транзакций
     * @return int
     */
    @Step("Get transactions count")
    public final int getTransactionsCount() {
        return driver.findElements(By.xpath(tableSelector)).size();
    }

    /**
     * Очистить все транзакции
     */
    @Step("Reset transactions")
    public void resetTransactions() {
        ElementHelper.clickElement(driver, resetButton);
    }

    /**
     * Перейти на страницу управления пользователя
     * @return текущий экземпляр класса
     */
    @Step("Go to customer page")
    public CustomerControlPage goToCustomerPage() {
        ElementHelper.clickElement(driver, backButton);
        return new CustomerControlPage(driver);
    }
}
