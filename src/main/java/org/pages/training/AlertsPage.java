package org.pages.training;

import org.helpers.ElementHelper;
import org.helpers.Wait;
import org.openqa.selenium.Alert;
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
     * Ввести кастомный тексты в Input Alert
     * @param text кастомный текст
     * @return текст появляющийся в результате
     */
    public String sendKeysToInputBox(String text) {
        ElementHelper.clickElement(driver, inputAlertButton);
        Wait.waitUntilVisible(driver, iframe);
        // Переключить контекст
        driver.switchTo().frame(iframe);
        try {
            WebElement elem = driver.findElement(By.xpath("(//button[@onclick='myFunction()'])"));

            ElementHelper.clickElement(driver, elem);

            Alert alert = driver.switchTo().alert();
            Wait.waitUntilAlert(driver);
            alert.sendKeys(text);
            alert.accept();

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
