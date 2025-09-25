package org.pages;

import org.helpers.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {
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
    WebElement slideOneImg;

    public HomePage(WebDriver webDriver) { super(webDriver); }

    /**
     * Все основные элементы (хедер с контактной информацией, блок с навигацией, кнопка регистрации, список курсов (под текстом Best Selenium Certification Course Online), футер) отображаются.
     */
    public void checkMainElements() {
        Wait.waitUntilVisible(driver, contactInfoHeader);
        contactInfoHeader.isDisplayed();
        navigationItem.isDisplayed();
        registrationButton.isDisplayed();
        coursesListContainer.isDisplayed();
        footer.isDisplayed();
    }

    /**
     * Блок с курсами (Most Popular Software Testing Courses)
     * - Получить класс изображения на первом слайде после клика по кнопке навигации вперёд
     * @return String
     */
    public String checkNavButtonNext() {
        Wait.waitThenClick(driver, navButtonNext);
        new WebDriverWait(driver, Duration.ofSeconds(10));
        return slideOneImg.getDomAttribute("class");
    }

    /**
     * Блок с курсами (Most Popular Software Testing Courses)
     * - Получить класс изображения на первом слайде после клика по кнопке навигации назад
     * @return String
     */
    public String checkNavButtonPrev() {
        Wait.waitThenClick(driver, navButtonPrev);
        new WebDriverWait(driver, Duration.ofSeconds(10));
        return slideOneImg.getDomAttribute("class");
    }

}
