package org.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Set;

public class TabHelper {

    /**
     * Получить кол-во всех открытых вкладок
     * @return Integer
     */
    public static Integer getTabsCount(WebDriver driver) {
        return new ArrayList<>(driver.getWindowHandles()).size();
    }

    /**
     * Открыть новую вкладку и переключиться на неё (через кнопку)
     * @param button Кнопка, открывающая новую вкладку
     */
    public static void openAndSwitchToNewTab(WebDriver driver, WebElement button) {
        // Запомнить текущие вкладки до клика
        Set<String> oldTabs = driver.getWindowHandles();
        // Выполнить действие, открывающее новую вкладку
        ElementHelper.clickElement(driver, button);
        Wait.waitUntilNewTab(driver, oldTabs);
        // Найти новую вкладку
        ArrayList<String> newTabs = new ArrayList<>(driver.getWindowHandles());
        newTabs.removeAll(oldTabs);
        // Переключиться на новую вкладку
        driver.switchTo().window(newTabs.get(0));
    }
}
