import org.pages.training.AlertsPage;
import org.pages.training.BasicAuthPage;
import org.pages.training.DragAndDropPage;
import org.pages.training.FramesAndWindowsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TrainingTests extends BaseTest {
    DragAndDropPage dragAndDropPage;
    FramesAndWindowsPage framesAndWindowsPage;
    AlertsPage alertsPage;
    BasicAuthPage basicAuthPage;

    @Test(description = "Тест перетаскивания элемента на way2automation.com", priority = 1)
    public void dragAndDropTest() {
        dragAndDropPage = new DragAndDropPage(driver);
        dragAndDropPage.getPage();
        dragAndDropPage.dragAndDrop();
        String actual = dragAndDropPage.getDroppableText();
        String excepted = "Dropped!";
        Assert.assertEquals(actual, excepted, "Текст принимающего элемента не соответствует ожидаемому");
    }

    @Test(description = "Тест открытия новых вкладок на way2automation.com", priority = 2)
    public void windowsAndFramesTest() {
        framesAndWindowsPage = new FramesAndWindowsPage(driver);
        framesAndWindowsPage.getPage();
        framesAndWindowsPage.switchToIframe();
        framesAndWindowsPage.openNewTab();
        framesAndWindowsPage.openNewTab();
        Integer actual = framesAndWindowsPage.getTabsCount();
        Assert.assertEquals(actual, 3, "Открыто не 3 вкладки");
    }

    @Test(description = "Тест ввода данных в Input Alert на way2automation.com", priority = 3)
    public void alertsTest() {
        alertsPage = new AlertsPage(driver);
        alertsPage.getPage();
        String input = "Mary Sue";
        String actual = alertsPage.sendKeysToInputBox(input);
        String expected = "Hello " + input + "! How are you today?";
        Assert.assertEquals(actual, expected, "Ожидаемый текст не совпадает");

    }

    @Test(description = "Тест базовой аутентификация на httpwatch.com", priority = 4)
    public void basicAuthTest() {
        basicAuthPage = new BasicAuthPage(driver);
        basicAuthPage.getAuthorized();
        String actual = basicAuthPage.getImageLink();
        Assert.assertNotNull(actual, "Ожидаемое изображение отсутствует (Авторизация не пройдена)");
    }

}
