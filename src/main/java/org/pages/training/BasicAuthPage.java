package org.pages.training;

import org.helpers.ElementHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.BasePage;

public class BasicAuthPage extends BasePage {

    /**
     * Кнопка отображения изображения (через авторизацию)
     */
    @FindBy(xpath = "(//input[@value='Display Image'])")
    WebElement displayImageButton;

    /**
     * Изображение
     */
    @FindBy(id = "downloadImg")
    WebElement image;

    public BasicAuthPage(WebDriver webDriver) { super(webDriver); }

    /**
     * Получить ссылку на изображение
     * @return String
     */
    public String getImageLink() {
        ElementHelper.clickElement(driver, displayImageButton);
        return image.getDomAttribute("src");
    }

    /**
     * Перейти на страницу
     */
    public void getPage() {
        String webUrl = "https://www.httpwatch.com/httpgallery/authentication/#showExample10";
        driver.get(webUrl);
    }

    /**
     * Перейти на страницу авторизованным
     */
    public void getAuthorized() {
        String webUrl = "https://httpwatch:httpwatch@www.httpwatch.com/httpgallery/authentication/#showExample10";
        driver.get(webUrl);
    }
}
