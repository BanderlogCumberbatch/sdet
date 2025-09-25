package org.pages;

import org.helpers.Wait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected final WebDriver driver;
    /**
     * Хедер с контактной информацией
     */
    @FindBy(xpath = "//*[contains(@class, 'ast-above-header-wrap  ')]")
    WebElement contactInfoHeader;

    /**
     * Блок с навигацией
     */
    @FindBy(xpath = "//*[contains(@class, 'ast-flex-grow-1 navigation-accessibility site-header-focus-item')]")
    WebElement navigationItem;

    /**
     * Выпадающее меню со всеми курсами
     */
    @FindBy(xpath = "//*[contains(@id, 'menu-item-27580')]")
    WebElement allCoursesMenuItem;

    /**
     *  Кнопка перехода на страницу Lifetime membership club.
     */
    @FindBy(xpath = "//*[contains(@id, 'menu-item-27581')]")
    WebElement toMembershipButton;

    /**
     * Футер
     */
    @FindBy(xpath = "//*[contains(@class, 'elementor elementor-25361 elementor-location-footer nitro-offscreen')]")
    WebElement footer;

    public BasePage(final WebDriver webDriver) {
        try {
            PageFactory.initElements(webDriver, this);
            this.driver = webDriver;
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Получить текст из хедера с контактной информацией
     * @return String
     */
    public String getInfoHeaderText() {
        Wait.waitUntilVisible(driver, contactInfoHeader);
        return contactInfoHeader.getText();
    }


    /**
     * Получить текст из футера
     * @return String
     */
    public String getFooterText() {
        Wait.waitUntilVisible(driver, footer);
        return footer.getText();
    }

    /**
     * Проверка отображения меню при скроллинге страницы вниз
     */
    public void checkNavItemAfterScroll() {
        Wait.waitUntilVisible(driver, navigationItem);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        new WebDriverWait(driver, Duration.ofSeconds(1000));
        navigationItem.isDisplayed();
    }


    /**
     * Получить текущий URL
     * @return String
     */
    public String getUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Переходит на страницу Lifetime membership club.
     * @return текущий экземпляр класса
     */
    public MembershipPage goToMembershipPage() {
        Wait.waitThenClick(driver, allCoursesMenuItem);
        Wait.waitThenClick(driver, toMembershipButton);
        return new MembershipPage(driver);
    }

}
