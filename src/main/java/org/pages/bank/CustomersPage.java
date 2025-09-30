package org.pages.bank;

import org.helpers.ElementHelper;
import org.helpers.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    @FindBy(xpath = "//*[contains(@class, 'input-group')]/input")
    WebElement searchInput;

    /**
     * Первый и второй столбец таблицы(имя, фамилия)
     */
    @FindBy(xpath = "//table[contains(@class, 'table-bordered')]/tbody/tr[td[1] and td[2]]")
    WebElement productNames;

    /**
     * Элемент первого столбца и второго столбца первой строки (имя, фамилия)
     */
    @FindBy(xpath = "//table[contains(@class, 'table-bordered')]/tbody/tr[1]")
    WebElement firstRowData;

    /**
     * Селектор выбирающий из таблицы 5-й элемент строки (кнопку удаления) с определённым значением 1-го элемента (имя)
     */
    String deleteButtonSelector = "//table[contains(@class, 'table-bordered')]/tbody/tr[td[1][text()='%s']]/td[5]/button";

    public CustomersPage(final WebDriver webDriver) { super(webDriver); }

    /**
     * Возвращает имя пользователя с определённым именем
     * @param firstName имя пользователя
     * @return String имя пользователя
     */
    public final String getCustomerFirstName(String firstName) {
        Wait.waitUntilVisible(driver, productsName);
        return Stream.of(productNames)
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
        return Stream.of(firstRowData)
                .map(WebElement::getText)
                .collect(Collectors.joining(""));
    }

    /**
     * Удаляет из таблицы пользователя с именем
     * @param firstName имя пользователя
     */
    public void deleteCustomerWithFirstName(String firstName) {
        WebElement deleteButton = driver.findElement(By.xpath(String.format(deleteButtonSelector, firstName)));
        ElementHelper.clickElement(driver, deleteButton);
    }

    /**
     * Очищает поле для поиска
     */
    public void eraseSearchInput() {
        Wait.waitUntilVisible(driver, searchInput);
        searchInput.clear();
    }

}
