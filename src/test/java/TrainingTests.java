import org.pages.training.DragAndDropPage;
import org.pages.training.FramesAndWindowsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainingTests extends BaseTest {
    DragAndDropPage dragAndDropPage;
    FramesAndWindowsPage framesAndWindowsPage;

    @BeforeMethod
    public final void setup() {}

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
        Integer actual = framesAndWindowsPage.getFramesCount();
        Assert.assertEquals(actual, 3, "Открыто не 3 вкладки");
    }

}
