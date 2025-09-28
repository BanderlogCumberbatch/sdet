import org.pages.*;
import org.pages.bank.*;
import org.pages.login.LoggedPage;
import org.pages.login.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Finder;
import utils.Generator;

public class Way2automationTests extends BaseTest {
    LoginPage loginPage;
    BankHomePage bankHomePage;
    SampleFormPage sampleFormPage;
    AddCustomerPage addCustomerPage;
    OpenAccountPage openAccountPage;
    CustomerLoginPage customerLoginPage;
    CustomerControlPage customerControlPage;
    TransactionsPage transactionsPage;
    CustomersPage customersPage;

    @Test(description = "1.", priority = 1)
    public void TestOne() {
        SoftAssert softAssert = new SoftAssert();
        // 1.1 Все основные элементы отображаются
        homePage.checkMainElements();
        // 1.2 Хедер содержит номера телефонов, ссылку на skype, почту и ссылки на соц.сети.
        softAssert.assertTrue(homePage.getInfoHeaderText().contains("""
                +919711-111-558
                +919711-191-558
                +1 646-480-0603
                seleniumcoaching
                trainer@way2automation.com"""), "Хедер не содержит номера телефонов, ссылку на skype, почту и ссылки на соц.сети.");
        // 1.3 Проверка кнопок навигации (вперед и назад): работают корректно (меняют слайды)
        softAssert.assertTrue(homePage.checkNavButtonNext(), "Кнопка навигации вперёд не работает");
        softAssert.assertTrue(homePage.checkNavButtonPrev(), "Кнопка навигации назад не работает");
        // 1.4 Футер отображается, содержит: адрес, номера телефонов и эмейлы
        softAssert.assertTrue(homePage.getFooterText().contains("""
                CDR Complex, 3rd Floor, Naya Bans Market, Sector 15, Noida, Near sec-16 Metro Station
                +91 97111-11-558
                +91 97111-91-558
                trainer@way2automation.com
                seleniumcoaching@gmail.com"""), "Футер не содержит адреса, номера телефонов и эмейлы");
        softAssert.assertAll();
    }

    @Test(description = "2.", priority = 2)
    public void TestTwo() {
        // 2. Отображение меню при скроллинге страницы вниз: меню должно оставаться видимым после прокрутки страницы
        homePage.checkNavItemAfterScroll();
    }

    @Test(description = "3.", priority = 3)
    public void TestThree() {
        // 3. Проверка перехода по меню навигации на другие страницы
        MembershipPage membershipPage = homePage.goToMembershipPage();
        Assert.assertEquals(membershipPage.getUrl(), "https://www.way2automation.com/lifetime-membership-club/", "Адрес страницы не совпадает");
        Assert.assertEquals(membershipPage.getTitle(), "LIFETIME MEMBERSHIP CLUB", "Сообщение не отображается");
    }

    @Test(description = "4.", priority = 4)
    public void TestFour() {
        // 4.1 Проверка полей ввода
        driver.get("https://www.way2automation.com/angularjs-protractor/registeration/#/login");
        loginPage = new LoginPage(driver);
        Assert.assertEquals(loginPage.getUsername(), "", "Поле для ввода имени не пустое");
        Assert.assertEquals(loginPage.getPassword(), "", "Поле для ввода пароля не пустое");
        Assert.assertEquals(loginPage.getLoginButtonDisabled(), "true", "Кнопка регистрации задизейблена");
        // 4.2 Проверка успешной авторизации
        LoggedPage loggedPage = loginPage.login("angular", "password", "***");
        loggedPage.checkLogged();
        // 4.3 Проверка успешного разлогирования
        loginPage = loggedPage.logout();
        Assert.assertEquals(loginPage.getUsername(), "", "Поле для ввода имени не пустое");
        Assert.assertEquals(loginPage.getPassword(), "", "Поле для ввода пароля не пустое");
        // 4.4 Проверка авторизации с невалидными данными
        loginPage.login("angular", "passwor", "***");
        loginPage.checkInvalidAuth();
    }

    @Test(description = "5.", priority = 5)
    public void TestFive() {
        // 5.1 Перейти в интерфейс Sample Form
        driver.get("https://www.way2automation.com/angularjs-protractor/banking/#/login");
        bankHomePage = new BankHomePage(driver);
        sampleFormPage = bankHomePage.goToSampleFormPage();
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
        // 5.2 Перейти в интерфейс Bank Manager Login
        bankHomePage = sampleFormPage.backToStartPage();
        addCustomerPage = bankHomePage.goToBankManagerPage().goToAddCustomerPage();
        // 5.2.1 Добавление покупателя
        addCustomerPage.addCustomer("Mary", "Sue", "34");
        // 5.2.2 Открытие аккаунта
        openAccountPage = addCustomerPage.goToOpenAccountPage();
        openAccountPage.openAccount();
        bankHomePage = openAccountPage.goToStartPage();
        // 5.3 Перейти в интерфейс Customer Login
        customerLoginPage = bankHomePage.goToCustomerLoginPage();
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
        customersPage = customerControlPage.goToStartPage()
                .goToBankManagerPage()
                .goToCustomersPage();
        String marySue = customersPage.findCustomer("34");
        Assert.assertEquals(marySue, "Mary Sue 34 1016 Delete", "Этот персонаж не Мэри Сью");
        customersPage.deleteCustomerWithFirstName("Mary");
        customersPage.eraseSearchInput();
        Assert.assertEquals(customersPage.getCustomerFirstName(marySue), "", "Мэри Сью не удалилась");
    }
}
