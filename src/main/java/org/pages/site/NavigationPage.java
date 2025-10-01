package org.pages.site;

import io.qameta.allure.Step;
import org.helpers.ElementHelper;
import org.helpers.Wait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.BasePage;

/**
 * Класс страницы с блоком навигации way2automation.com
 */
public class NavigationPage extends BasePage {
    /**
     * Хедер с контактной информацией
     */
    @FindBy(xpath = "//*[contains(@class, 'ast-above-header-wrap  ')]")
    WebElement contactInfoHeader;

    /**
     * Блок с навигацией
     */
    @FindBy(xpath = "//*[contains(@id, 'site-navigation')]")
    WebElement navigationItem;

    /**
     * Выпадающее меню со всеми курсами
     */
    @FindBy(xpath = "//ul[contains(@id, 'ast-hf-menu-1')]/li[2]")
    WebElement allCoursesMenuItem;

    /**
     *  Кнопка перехода на страницу Lifetime membership club.
     */
    @FindBy(xpath = "//ul[contains(@id, 'ast-hf-menu-1')]/li[2]/ul/li[1]")
    WebElement toMembershipButton;

    /**
     * Футер
     */
    @FindBy(xpath = "//*[contains(@data-elementor-type, 'footer')]")
    WebElement footer;

    public NavigationPage(final WebDriver webDriver) {super(webDriver);}

    /**
     * Получить текст из хедера с контактной информацией
     * @return String
     */
    @Step("Get info header text")
    public String getInfoHeaderText() {
        Wait.waitUntilVisible(driver, contactInfoHeader);
        return contactInfoHeader.getText();
    }


    /**
     * Получить текст из футера
     * @return String
     */
    @Step("Get footer text")
    public String getFooterText() {
        Wait.waitUntilVisible(driver, footer);
        return footer.getText();
    }

    /**
     * Проверка отображения меню при скроллинге страницы вниз
     * @return true - меню отображается, false - нет
     */
    @Step("Check navigation item after scroll")
    public Boolean checkNavItemAfterScroll() {
        Wait.waitUntilVisible(driver, navigationItem);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        return navigationItem.isDisplayed();
    }


    /**
     * Получить текущий URL
     * @return String
     */
    @Step("Get URL")
    public String getUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Переходит на страницу Lifetime membership club.
     * @return текущий экземпляр класса
     */
    @Step("Go to membership page")
    public MembershipPage goToMembershipPage() {
        ElementHelper.clickElement(driver, allCoursesMenuItem);
        ElementHelper.clickElement(driver, toMembershipButton);
        return new MembershipPage(driver);
    }

    /**
     * Получить заголовок страницы
     * @return String
     */
    @Step("Get title")
    public String getTitle() {
        return driver.getTitle();
    }

}
