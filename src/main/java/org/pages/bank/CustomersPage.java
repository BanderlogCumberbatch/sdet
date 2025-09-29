package org.pages.bank;

import org.helpers.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Класс страницы с таблицей с данными всех пользователей
 */
public class CustomersPage extends ManagerPage {

    /**
     * Столбец таблицы с именами
     */
    @FindBy(xpath = "//table[contains(@class, 'table-bordered')]/tbody/tr/td[1]")
    WebElement productsName;

    /**
     * Поле для ввода данных для поиска
     */
    @FindBy(css = "body > div.ng-scope > div > div.ng-scope > div > div.ng-scope > div > form > div > div > input")
    WebElement searchInput;

    /**
     * Селектор выбирающий из таблицы первый и второй столбец(имя, фамилия)
     */
    private final String productNamesSelector = "//table[contains(@class, 'table-bordered')]/tbody/tr[td[1] and td[2]]";

    /**
     * Селектор выбирающий из таблицы элемент первого столбца и второго столбца первой строки (имя, фамилия)
     */
    private final String firstRowDataSelector = "//table[contains(@class, 'table-bordered')]/tbody/tr[1]";

    /**
     * Селектор выбирающий из таблицы 5-й элемент строки (кнопку удаления) с определённым значением 1-го элемента (имя)
     */
    private final String deleteButtonSelector = "//table[contains(@class, 'table-bordered')]/tbody/tr[td[1][text()='%s']]/td[5]/button";

    public CustomersPage(final WebDriver webDriver) { super(webDriver); }

    /**
     * Возвращает имя пользователя с определённым именем
     * @param firstName имя пользователя
     * @return String имя пользователя
     */
    public final String getCustomerFirstName(String firstName) {
        Wait.waitUntilVisible(driver, productsName);
        return driver
                .findElements(By.xpath(productNamesSelector))
                .stream()
                .map(WebElement::getText)
                .filter(s -> s.equals(firstName))
                .collect(Collectors.joining(""));
    }

    /**
     * Совершает поиск пользователя через интерфейс и возвращает имя найденного пользователя
     * @param customerData данные пользователя
     * @return текущий экземпляр класса
     */
    public String findCustomer(String customerData) {
        Wait.waitUntilVisible(driver, searchInput);
        searchInput.sendKeys(customerData);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return driver
                .findElements(By.xpath(firstRowDataSelector))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.joining(""));
    }

    /**
     * Удаляет из таблицы пользователя с именем
     * @param firstName имя пользователя
     */
    public void deleteCustomerWithFirstName(String firstName) {
        WebElement deleteButton = driver.findElement(By.xpath(String.format(deleteButtonSelector, firstName)));
        Wait.waitThenClick(driver, deleteButton);
    }

    /**
     * Очищает поле для поиска
     */
    public void eraseSearchInput() {
        Wait.waitUntilVisible(driver, searchInput);
        searchInput.clear();
    }

}
