package org.pages;

import org.helpers.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Класс начальной страницы для way2automation.com
 */
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
    WebElement slideFirstImg;

    /**
     *  Изображение на последнем слайде блока с курсами (Most Popular Software Testing Courses).
     */
    @FindBy(css = "#MTA4ODoxNTM\\=-1")
    WebElement slideLastImg;

    public HomePage(WebDriver webDriver) { super(webDriver); }

    /**
     * Все основные элементы (хедер с контактной информацией, блок с навигацией, кнопка регистрации, список курсов (под текстом Best Selenium Certification Course Online), футер) отображаются.
     */
    public void checkMainElements() {
        Wait.waitUntilVisible(driver, contactInfoHeader);
        Wait.waitUntilVisible(driver, contactInfoHeader);
        Wait.waitUntilVisible(driver, navigationItem);
        Wait.waitUntilVisible(driver, registrationButton);
        Wait.waitUntilVisible(driver, coursesListContainer);
        footer.isDisplayed();
    }

    /**
     * Блок с курсами (Most Popular Software Testing Courses)
     * Проверяет работоспособность навигации вперёд
     * @return true - навигация вперёд работает, false - нет
     */
    public Boolean checkNavButtonNext() {
        Wait.waitThenClick(driver, navButtonNext);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String sld = slideFirstImg.getDomAttribute("class");
        Wait.waitThenClick(driver, navButtonPrev);
        return Objects.equals(sld, "nitro-lazy");
    }

    /**
     * Блок с курсами (Most Popular Software Testing Courses)
     * Проверяет работоспособность навигации назад
     * @return true - навигация назад работает, false - нет
     */
    public Boolean checkNavButtonPrev() {
        Wait.waitThenClick(driver, navButtonPrev);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String sld = slideLastImg.getDomAttribute("class");
        Wait.waitThenClick(driver, navButtonNext);
        return Objects.equals(sld, "lazyloaded");
    }

}
