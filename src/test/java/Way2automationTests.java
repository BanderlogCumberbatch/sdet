import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Way2automationTests extends BaseTest {

    @Test(description = "Check info header")
    public void basePageTest() {
        // Все основные элементы отображаются
        homePage.checkMainElements();
        SoftAssert softAssert = new SoftAssert();
        // Хедер содержит номера телефонов, ссылку на skype, почту и ссылки на соц.сети.
        softAssert.assertEquals(homePage.getInfoHeaderText(), """
                +919711-111-558
                +919711-191-558
                +1 646-480-0603
                seleniumcoaching
                trainer@way2automation.com""");
        // Проверка кнопок навигации (вперед и назад): работают корректно (меняют слайды)
        softAssert.assertNotEquals(homePage.checkNavButtonNext(), "lazyloaded");
        softAssert.assertEquals(homePage.checkNavButtonPrev(), "lazyloaded");
        // Футер отображается, содержит: адрес, номера телефонов и эмейлы
        softAssert.assertTrue(homePage.getFooterText().indexOf("CDR Complex, 3rd Floor, Naya Bans Market, Sector 15, Noida, Near sec-16 Metro Station\n" +
                "+91 97111-11-558\n" +
                "+91 97111-91-558\n" +
                "trainer@way2automation.com\n" +
                "seleniumcoaching@gmail.com") != -1);
        softAssert.assertAll();
        // Отображение меню при скроллинге страницы вниз: меню должно оставаться видимым после прокрутки страницы
        homePage.checkNavItemAfterScroll();
    }
}
