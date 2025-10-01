package org.pages.bank;

import io.qameta.allure.Step;
import org.helpers.ElementHelper;
import org.helpers.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Класс для страницы формы регистрации Way2Automation Banking App
 */
public class SampleFormPage {
    protected final WebDriver driver;

    /**
     * Поле для ввода имени пользователя
     */
    @FindBy(id = "firstName")
     WebElement firstNameInput;

    /**
     * Поле для ввода фамилии пользователя
     */
    @FindBy(id = "lastName")
    WebElement lastNameInput;

    /**
     * Поле для ввода почты пользователя
     */
    @FindBy(id = "email")
    WebElement emailInput;

    /**
     * Поле для ввода пароля
     */
    @FindBy(id = "password")
    WebElement passwordInput;

    /**
     * Чекбокс для выбора хобби 'Sports'
     */
    @FindBy(xpath = "//input[@value='Sports']")
    WebElement sportsCheckbox;

    /**
     * Чекбокс
     */
    @FindBy(xpath = "//div[contains(@class, 'checkbox-group')]")
    WebElement checkbox;

    /**
     * Поле для ввода пола пользователя
     */
    @FindBy(id = "gender")
    WebElement genderSelect;

    /**
     * Поле для ввода информации о пользователе
     */
    @FindBy(id = "about")
    WebElement aboutTextarea;

    /**
     * Кнопка для регистрации
     */
    @FindBy(xpath = "//button[@type='submit']")
    WebElement registerButton;

    /**
     * Сообщение об успешной авторизации
     */
    @FindBy(id = "successMessage")
    WebElement successMessage;

    public SampleFormPage(final WebDriver webDriver) {
        try {
            PageFactory.initElements(webDriver, this);
            this.driver = webDriver;
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Получить массив строк всех вариантов из чекбокса
     * @return String[]
     */
    @Step("Get checkbox variants")
    public String[] getCheckboxVariants() {
        Wait.waitUntilVisible(driver, checkbox);
        return checkbox.getText().split(" ");
    }

    /**
     * Заполнение форму данными
     */
    @Step("Fill form")
    public void fillForm(String firstName, String lastName, String email, String password, String gender, String about) {
        Wait.waitUntilVisible(driver, firstNameInput);
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        genderSelect.sendKeys(gender);
        aboutTextarea.sendKeys(about);
    }

    /**
     * Выбрать хобби 'Sports' в чекбоксе
     */
    @Step("Select sports hobby")
    public void selectSportsHobby() {
        if (!sportsCheckbox.isSelected()) {
            ElementHelper.clickElement(driver, sportsCheckbox);
        }
    }

    /**
     * Нажать на кнопку регистрации
     */
    @Step("Click register")
    public void clickRegister() {
        ElementHelper.clickElement(driver, registerButton);
    }

    /**
     * Получить текст сообщения об успешной регистрации
     * @return String
     */
    @Step("Get success message text")
    public String getSuccessMessageText() {
        Wait.waitUntilVisible(driver, successMessage);
        return successMessage.getText();
    }
}
