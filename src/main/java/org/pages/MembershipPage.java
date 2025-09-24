package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MembershipPage extends BasePage {
    /**
     * Заголовок
     */
    @FindBy(xpath = "//*[contains(@class, 'elementor-heading-title elementor-size-default')]")
    WebElement pageTitle;

    public MembershipPage(WebDriver webDriver) { super(webDriver); }

    /**
     * Получить заголовок страницы
     * @return String
     */
    public String getTitle() {
        return pageTitle.getText();
    }
}
