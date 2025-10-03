import io.qameta.allure.*;
import org.pages.bank.*;
import org.pages.login.LoggedPage;
import org.pages.login.LoginPage;
import org.pages.site.HomePage;
import org.pages.site.MembershipPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Finder;
import utils.Generator;

/**
 * Класс тестов для way2automation.com
 */
public class Way2automationTests extends BaseTest {

    @BeforeMethod
    public final void setup() {
        String webUrl = "https://www.way2automation.com/";
        driver.get(webUrl);
        homePage = new HomePage(driver);
    }

    @Test(description = "Тест сайта way2automation.com (проверка отображения основных элементов, содержания хедера и футера, функциональности кнопок навигации (блок с курсами Most Popular Software Testing Courses))", priority = 1)
    @Severity(value = SeverityLevel.NORMAL)
    @Epic(value = "UI")
    @Feature(value = "Check elements")
    @Story(value = "As user")
    public void TestOne() {
        SoftAssert softAssert = new SoftAssert();
        // 1.1 Все основные элементы отображаются
        Assert.assertTrue(homePage.checkMainElements(), "Основные элементы (хедер с контактной информацией, блок с навигацией, кнопка регистрации, список курсов (под текстом Best Selenium Certification Course Online), футер) не отображаются");
        // 1.2 Хедер содержит номера телефонов, ссылку на skype, почту и ссылки на соц.сети.
        softAssert.assertTrue(homePage.getInfoHeaderText().contains("""
                +919711-111-558
                +919711-191-558
                +1 646-480-0603
                seleniumcoaching
                trainer@way2automation.com"""), "Хедер не содержит номера телефонов, ссылку на skype, почту и ссылки на соц.сети.");
        // 1.3 Проверка кнопок навигации (вперед и назад): работают корректно (меняют слайды)
        softAssert.assertTrue(homePage.checkNavButtonNext(), "Кнопка навигации вперёд не работает (блок с курсами Most Popular Software Testing Courses)");
        softAssert.assertTrue(homePage.checkNavButtonBack(), "Кнопка навигации назад не работает (блок с курсами Most Popular Software Testing Courses)");
        // 1.4 Футер отображается, содержит: адрес, номера телефонов и эмейлы
        softAssert.assertTrue(homePage.getFooterText().contains("""
                CDR Complex, 3rd Floor, Naya Bans Market, Sector 15, Noida, Near sec-16 Metro Station
                +91 97111-11-558
                +91 97111-91-558
                trainer@way2automation.com
                seleniumcoaching@gmail.com"""), "Футер не содержит адреса, номера телефонов и эмейлы");
        softAssert.assertAll();
    }

    @Test(description = "Отображение меню при скроллинге страницы вниз на сайте way2automation.com: меню должно оставаться видимым после прокрутки страницы", priority = 2)
    @Severity(value = SeverityLevel.TRIVIAL)
    @Epic(value = "UI")
    @Feature(value = "Check elements")
    @Story(value = "As user")
    public void TestTwo() {
        // 2. Отображение меню при скроллинге страницы вниз: меню должно оставаться видимым после прокрутки страницы
        Assert.assertTrue(homePage.checkNavItemAfterScroll(), "Меню навигации не отображается при скроллинге вниз");
    }

    @Test(description = "Проверка перехода по меню навигации на другие страницы на сайте way2automation.com", priority = 3)
    @Severity(value = SeverityLevel.NORMAL)
    @Epic(value = "UI")
    @Feature(value = "Check elements")
    @Story(value = "As user")
    public void TestThree() {
        // 3. Проверка перехода по меню навигации на другие страницы
        MembershipPage membershipPage = homePage.goToMembershipPage();
        Assert.assertEquals(membershipPage.getUrl(), "https://www.way2automation.com/lifetime-membership-club/", "Адрес страницы не совпадает");
        Assert.assertTrue(membershipPage.getTitle().contains("Lifetime Membership Club"), "Заголовок не содержит Lifetime Membership Club");
    }

    @Test(description = "Проверка авторизации на way2automation.com", priority = 4)
    @Severity(value = SeverityLevel.NORMAL)
    @Epic(value = "Authorization")
    @Features(value = {@Feature(value = "Check elements"), @Feature(value = "Login"), @Feature(value = "Logout")})
    @Story(value = "As user")
    public void TestFour() {
        // 4.1 Проверка полей ввода
        driver.get("https://www.way2automation.com/angularjs-protractor/registeration/#/login");
        loginPage = new LoginPage(driver);
        Assert.assertEquals(loginPage.getUsername(), "", "Поле для ввода имени не пустое");
        Assert.assertEquals(loginPage.getPassword(), "", "Поле для ввода пароля не пустое");
        Assert.assertEquals(loginPage.getLoginButtonDisabled(), "true", "Кнопка регистрации задизейблена");
        // 4.2 Проверка успешной авторизации
        LoggedPage loggedPage = loginPage.login("angular", "password", "***");
        Assert.assertTrue(loggedPage.checkMessage("You're logged in!!"), "Сообщение \"You're logged in!!\" не отображается");
        // 4.4 Проверка успешного разлогирования
        loginPage = loggedPage.logout();
        Assert.assertEquals(loginPage.getUsername(), "", "Поле для ввода имени не пустое");
        Assert.assertEquals(loginPage.getPassword(), "", "Поле для ввода пароля не пустое");
        // 4.3 Проверка авторизации с невалидными данными
        loginPage.login("angular", "passwor", "***");
        Assert.assertEquals(loginPage.getInvalidAuthMessage(), "Username or password is incorrect", "Текст сообщения о некорректных данных при авторизации не совпадает");
    }

    @Test(description = "Проверка формы регистрации Sample Form в банковском приложении way2automation.com", priority = 5)
    @Severity(value = SeverityLevel.TRIVIAL)
    @Epic(value = "Registration")
    @Features(value = {@Feature(value = "Check elements"), @Feature(value = "Register")})
    @Story(value = "As user")
    public void TestFivePointOne() {
        // 5.1 Перейти в интерфейс Sample Form
        driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/login");
        startPage = new StartPage(driver);
        sampleFormPage = startPage.goToSampleFormPage();
        String[] hobbies = sampleFormPage.getCheckboxVariants();
        String longestWord = Finder.findLongestString(hobbies);
        String aboutText = "Самое длинное слово из предложенных хобби - " + longestWord;
        sampleFormPage.fillForm(
                "Mary",
                "Sue",
                "mary.sue@example.com",
                "Password123",
                "female",
                aboutText
        );
        sampleFormPage.selectSportsHobby();
        sampleFormPage.clickRegister();
        Assert.assertEquals(sampleFormPage.getSuccessMessageText(),
                "User registered successfully!",
                "Текст сообщения не совпадает");
    }

    @Test(description = "Общая проверка функциональности банковского приложения way2automation.com", priority = 6)
    @Severity(value = SeverityLevel.NORMAL)
    @Epic(value = "User Management")
    @Features(value = {@Feature(value = "Open account"), @Feature(value = "Login"), @Feature(value = "Deposit"), @Feature(value = "Withdraw"), @Feature(value = "Delete account")})
    @Stories(value = {@Story(value = "As administrator"), @Story(value = "As user")})
    public void TestFivePointTwo() {
        // 5.2 Перейти в интерфейс Bank Manager Login
        driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/login");
        startPage = new StartPage(driver);
        addCustomerPage = startPage.goToBankManagerPage().goToAddCustomerPage();
        // 5.2.1 Добавление покупателя
        addCustomerPage.addCustomer("Mary", "Sue", "34");
        // 5.2.2 Открытие аккаунта
        openAccountPage = addCustomerPage.goToOpenAccountPage();
        openAccountPage.openAccount();
        startPage = openAccountPage.goToHomePage();
        // 5.3 Перейти в интерфейс Customer Login
        customerLoginPage = startPage.goToCustomerLoginPage();
        customerControlPage = customerLoginPage.customerLogin();
        Assert.assertEquals(customerControlPage.getWelcomeMessageText(), "Welcome Mary Sue !!", "Текст сообщения не совпадает");
        // 5.3.1 Успешное пополнение счета
        customerControlPage.deposit("100321");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(customerControlPage.isTransactionMessageDisplayed(), "Cообщение об успешной транзакции не появилось");
        softAssert.assertEquals(customerControlPage.getTransactionMessageText(), "Deposit Successful", "Текст сообщения не совпадает");
        transactionsPage = customerControlPage.goToTransactionPage();
        softAssert.assertEquals(transactionsPage.getLastTransactionAmount(), "100321", "Средства последней транзакции не равны 100321");
        softAssert.assertAll();
        customerControlPage = transactionsPage.goToCustomerPage();
        // 5.3.2 Неуспешное пополнение счета
        customerControlPage.deposit("0");
        softAssert.assertFalse(customerControlPage.isTransactionMessageDisplayed(), "Появилось сообщение об успешной транзакции");
        transactionsPage = customerControlPage.goToTransactionPage();
        softAssert.assertNotEquals(transactionsPage.getLastTransactionAmount(), "0", "Средства последней транзакции равно 0");
        softAssert.assertAll();
        customerControlPage = transactionsPage.goToCustomerPage();
        // 5.3.3 Успешное снятие средств
        String amount = Integer.toString(Generator.generate(customerControlPage.getBalance()));
        customerControlPage.withdraw(amount);
        softAssert.assertTrue(customerControlPage.isTransactionMessageDisplayed(), "Cообщение об успешной транзакции не появилось");
        softAssert.assertEquals(customerControlPage.getTransactionMessageText(), "Transaction successful", "Текст сообщения не совпадает");
        transactionsPage = customerControlPage.goToTransactionPage();
        softAssert.assertEquals(transactionsPage.getLastTransactionAmount(), amount, "Средства последней транзакции не равны " + amount);
        softAssert.assertAll();
        customerControlPage = transactionsPage.goToCustomerPage();
        // 5.3.4 Неуспешное снятие средств
        customerControlPage.withdraw("1000000");
        softAssert.assertTrue(customerControlPage.isTransactionMessageDisplayed(), "Cообщение о проваленной транзакции не появилось");
        softAssert.assertEquals(customerControlPage.getTransactionMessageText(), "Transaction Failed. You can not withdraw amount more than the balance.", "Текст сообщения не совпадает");
        transactionsPage = customerControlPage.goToTransactionPage();
        softAssert.assertNotEquals(transactionsPage.getLastTransactionAmount(), "1000000", "Средства последней транзакции равны 1000000");
        softAssert.assertAll();
        customerControlPage = transactionsPage.goToCustomerPage();
        // 5.3.4 Проверка баланса
        Double balance = (double) customerControlPage.getBalance();
        transactionsPage = customerControlPage.goToTransactionPage();
        Assert.assertEquals(transactionsPage.getAmountSum(), balance, "Данные о балансе не сходятся");
        customerControlPage = transactionsPage.goToCustomerPage();
        // 5.3.5 Снятие оставшихся средств
        customerControlPage.withdraw(String.valueOf(balance));
        softAssert.assertTrue(customerControlPage.isTransactionMessageDisplayed(), "Cообщение об успешной транзакции не появилось");
        softAssert.assertEquals(customerControlPage.getTransactionMessageText(), "Transaction successful", "Текст сообщения не совпадает");
        softAssert.assertEquals(String.valueOf(customerControlPage.getBalance()), "0", "Баланс не равен 0");
        softAssert.assertAll();
        // 5.3.7 Очистка истории транзакций
        transactionsPage = customerControlPage.goToTransactionPage();
        Integer transactionsCount = transactionsPage.getTransactionsCount();
        transactionsPage.resetTransactions();
        Assert.assertNotEquals(transactionsPage.getTransactionsCount(), transactionsCount, "Все транзакции не очистились");
        customerControlPage = transactionsPage.goToCustomerPage();
        Assert.assertEquals(String.valueOf(customerControlPage.getBalance()), "0", "Баланс не равен 0");
        // 5.3.8 Удаление покупателя
        customersPage = customerControlPage.goToHomePage()
                .goToBankManagerPage()
                .goToCustomersPage();
        String marySue = customersPage.findCustomer("34");
        Assert.assertEquals(marySue, "Mary Sue 34 1016 Delete", "Этот персонаж не Мэри Сью");
        customersPage.deleteCustomerWithFirstName("Mary");
        customersPage.eraseSearchInput();
        Assert.assertEquals(customersPage.getCustomerFirstName(marySue), "", "Мэри Сью не удалилась");
    }

    /**
     * Действия после теста.
     */
    @AfterMethod
    public final void clearCookies() {
        driver.manage().deleteAllCookies();
    }
}
