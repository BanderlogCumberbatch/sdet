package org.pages.training;

import org.helpers.FrameHelper;
import org.helpers.TabHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.BasePage;

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
        FrameHelper.switchToIframe(driver, iframe);
    }

    /**
     * Открыть новую вкладку
     */
    public void openNewTab() {
        WebElement newTabButton = driver.findElement(By.xpath("(//a[text()='New Browser Tab'])"));
        TabHelper.openAndSwitchToNewTab(driver, newTabButton);
    }

    /**
     * Получить кол-во всех открытых вкладок
     * @return Integer
     */
    public Integer getTabsCount() {
        return TabHelper.getTabsCount(driver);
    }

    /**
     * Перейти на страницу
     */
    public void getPage() {
        String webUrl = "http://way2automation.com/way2auto_jquery/frames-and-windows.php#load_box";
        driver.get(webUrl);
    }

}
