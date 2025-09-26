package org.pages.bank;

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
    public String[] getCheckboxVariants() {
        Wait.waitUntilVisible(driver, checkbox);
        return checkbox.getText().split(" ");
    }

    /**
     * Заполнение формы данными
     */
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
    public void selectSportsHobby() {
        if (!sportsCheckbox.isSelected()) {
            Wait.waitThenClick(driver, sportsCheckbox);
        }
    }

    /**
     * Нажать на кнопку регистрации
     */
    public void clickRegister() {
        Wait.waitThenClick(driver, registerButton);
    }

    /**
     * Получить текст сообщения об успешной регистрации
     * @return String
     */
    public String getSuccessMessageText() {
        Wait.waitUntilVisible(driver, successMessage);
        return successMessage.getText();
    }

    /**
     * Перейти обратно на стартовую страницу
     * @return текущий экземпляр класс
     */
    public BankHomePage backToStartPage() {
        driver.navigate().back();
        return new BankHomePage(driver);
    }
}
