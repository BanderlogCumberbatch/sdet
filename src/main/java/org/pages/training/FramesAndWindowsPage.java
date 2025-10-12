package org.pages.training;

import org.helpers.ElementHelper;
import org.helpers.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.BasePage;

import java.util.ArrayList;
import java.util.Set;

public class FramesAndWindowsPage extends BasePage {

    /**
     * Iframe элемент окна
     */
    @FindBy(xpath = "//*[contains(@class, 'demo-frame')]")
    WebElement iframe;

    public FramesAndWindowsPage(WebDriver webDriver) { super(webDriver); }

    /**
     * Переключить контекст на iframe
     */
    public void switchToIframe() {
        Wait.waitUntilVisible(driver, iframe);
        driver.switchTo().frame(iframe);
    }

    /**
     * Открыть новую вкладку
     */
    public void openNewTab() {
        // Запомнить текущие вкладки до клика
        Set<String> oldTabs = driver.getWindowHandles();

        WebElement elem = driver.findElement(By.xpath("(//a[text()='New Browser Tab'])"));
        // Выполнить действие, открывающее новую вкладку
        ElementHelper.clickElement(driver, elem);

        Wait.waitUntilNewTab(driver, oldTabs);
        // Найти новую вкладку
        ArrayList<String> newTabs = new ArrayList<>(driver.getWindowHandles());
        newTabs.removeAll(oldTabs);
        // Переключиться на новую вкладку
        driver.switchTo().window(newTabs.get(0));
    }

    /**
     * Получить кол-во всех открытых вкладок
     * @return Integer
     */
    public Integer getFramesCount() {
        return new ArrayList<>(driver.getWindowHandles()).size();
    }

    /**
     * Перейти на страницу
     */
    public void getPage() {
        String webUrl = "http://way2automation.com/way2auto_jquery/frames-and-windows.php#load_box";
        driver.get(webUrl);
    }

}
