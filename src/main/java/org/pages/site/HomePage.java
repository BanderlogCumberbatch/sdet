package org.pages.site;

import io.qameta.allure.Step;
import org.helpers.ElementHelper;
import org.helpers.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Класс стартовой страницы для way2automation.com
 */
public class HomePage extends NavigationPage {
    /**
     * Кнопка регистрации
     */
    @FindBy(xpath = "//*[contains(@class, 'elementor-button elementor-slide-button elementor-size-sm')]")
    WebElement registrationButton;

    /**
     *  Список курсов (под текстом Best Selenium Certification Course Online).
     */
    @FindBy(xpath = "//*[contains(@class, 'elementor-container elementor-column-gap-default')]")
    WebElement coursesListContainer;

    /**
     *  Кнопка навигации назад (под Most Popular Software Testing Courses).
     */
    @FindBy(xpath = "//*[contains(@class, 'pp-slider-arrow elementor-swiper-button-prev swiper-button-prev-c50f9f0')]")
    WebElement navButtonPrev;

    /**
     *  Кнопка навигации вперёд (под Most Popular Software Testing Courses).
     */
    @FindBy(xpath = "//*[contains(@class, 'pp-slider-arrow elementor-swiper-button-next swiper-button-next-c50f9f0')]")
    WebElement navButtonNext;

    /**
     *  Изображение на первом слайде блока с курсами (Most Popular Software Testing Courses).
     */
    @FindBy(css = "#NjY4OjE0Mw\\=\\=-1")
    WebElement slideFirstImg;

    /**
     *  Изображение на последнем слайде блока с курсами (Most Popular Software Testing Courses).
     */
    @FindBy(css = "#MTA4ODoxNTM\\=-1")
    WebElement slideLastImg;

    public HomePage(WebDriver webDriver) { super(webDriver); }

    /**
     * Все основные элементы (хедер с контактной информацией, блок с навигацией, кнопка регистрации, список курсов (под текстом Best Selenium Certification Course Online), футер) отображаются.
     * @return true - все основные элементы отображаются, false - нет
     */
    @Step("Check main elements")
    public Boolean checkMainElements() {
        Wait.waitUntilVisible(driver, contactInfoHeader);
        List<WebElement> elements = Arrays.asList(navigationItem, registrationButton, coursesListContainer, footer);
        for (WebElement i : elements) {
            if (!i.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Блок с курсами (Most Popular Software Testing Courses)
     * Проверяет работоспособность навигации вперёд
     * @return true - навигация вперёд работает, false - нет
     */
    @Step("Check navigation next")
    public Boolean checkNavButtonNext() {
        ElementHelper.clickElement(driver, navButtonNext);
        String sld = slideFirstImg.getDomAttribute("class");
        ElementHelper.clickElement(driver, navButtonPrev);
        return Objects.equals(sld, "nitro-lazy");
    }

    /**
     * Блок с курсами (Most Popular Software Testing Courses)
     * Проверяет работоспособность навигации назад
     * @return true - навигация назад работает, false - нет
     */
    @Step("Check navigation back")
    public Boolean checkNavButtonBack() {
        ElementHelper.clickElement(driver, navButtonPrev);
        String sld = slideLastImg.getDomAttribute("class");
        ElementHelper.clickElement(driver, navButtonNext);
        return Objects.equals(sld, "lazyloaded");
    }

}
