package org.pages.training;

import org.helpers.AlertHelper;
import org.helpers.ElementHelper;
import org.helpers.FrameHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.BasePage;

public class AlertsPage extends BasePage {

    /**
     * Кнопка перехода на Input Alert
     */
    @FindBy(xpath = "(//a[text()='Input Alert'])")
    WebElement inputAlertButton;

    /**
     * Iframe элемент окна
     */
    @FindBy(xpath = "//iframe[@src='alert/input-alert.html']")
    WebElement iframe;

    public AlertsPage(WebDriver webDriver) { super(webDriver); }

    /**
     * Ввести кастомный текст в Input Alert
     * @param text кастомный текст
     * @return текст появляющийся в результате
     */
    public String sendKeysToInputBox(String text) {
        ElementHelper.clickElement(driver, inputAlertButton);
        FrameHelper.switchToIframe(driver, iframe);
        try {
            WebElement openInputBoxButton = driver.findElement(By.xpath("(//button[@onclick='myFunction()'])"));
            ElementHelper.clickElement(driver, openInputBoxButton);
            AlertHelper.inputTextInAlert(driver, text);
            WebElement expectedElem = driver.findElement(By.xpath("(//p[@id='demo'])"));
            return expectedElem.getText();
        }
        finally {
            driver.switchTo().defaultContent();
        }
    }

    /**
     * Перейти на страницу
     */
    public void getPage() {
        String webUrl = "http://way2automation.com/way2auto_jquery/alert.php";
        driver.get(webUrl);
    }
}
