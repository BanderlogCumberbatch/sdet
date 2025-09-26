package org.pages.bank;

import org.helpers.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.stream.Collectors;

public class TransactionsPage extends BankBasePage {
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
     * Селектор выбирающий из таблицы элемент второго столбца первой строки
     */
    private final String firstRowDataSelector = "//table[contains(@class, 'table-bordered')]/tbody/tr[1]/td[2]";

    /**
     * Селектор выбирающий таблицу
     */
    private final String tableSelector = "//table[contains(@class, 'table-bordered')]/tbody/tr";

    public TransactionsPage(WebDriver webDriver) {super(webDriver);}

    /**
     * Сортирует по возрастанию даты-времени и возвращает строку с данными второго столбца первой строки таблицы (средства последней совершённой транзакции)
     * @return String
     */
    public final String getLastTransactionAmount() {
        Wait.waitThenClick(driver, sortByFirstNameButton);
        return driver
                .findElements(By.xpath(firstRowDataSelector))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.joining(""));
    }

    /**
     * Возвращает сумму всех средств.
     * @return List<String>
     */
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
    public final int getTransactionsCount() {
        return driver.findElements(By.xpath(tableSelector)).size();
    }

    /**
     * Очистить все транзакции
     */
    public void resetTransactions() {
        Wait.waitThenClick(driver, resetButton);
    }

    /**
     * Перейти на страницу управления пользователя
     * @return текущий экземпляр класса
     */
    public CustomerControlPage goToCustomerPage() {
        Wait.waitThenClick(driver, backButton);
        return new CustomerControlPage(driver);
    }
}
