import org.pages.training.DragAndDropPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainingTests extends BaseTest {
    DragAndDropPage dragAndDropPage;

    @BeforeMethod
    public final void setup() {
    }

    @Test
    public void dragAndDropTest() {
        dragAndDropPage = new DragAndDropPage(driver);
        dragAndDropPage.getPage();
        dragAndDropPage.dragAndDrop();
        String actual = dragAndDropPage.getDroppableText();
        String excepted = "Dropped!";
        Assert.assertEquals(actual, excepted, "Текст принимающего элемента не соответствует ожидаемому");
    }

}
