import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import listener.AllureTestListener;
import java.util.Objects;

@Listeners(AllureTestListener.class)
public class TestExample extends BaseTest {

    @Test
    public void testSuccess() {
        driver.get("https://example.com");
        Assert.assertTrue(Objects.requireNonNull(driver.getTitle()).contains("Example"));
    }

    @Test(priority = 1)
    public void testFailure() {
        driver.get("https://example.com");
        // Этот тест упадёт и будет сделан скриншот
        Assert.assertTrue(Objects.requireNonNull(driver.getTitle()).contains("Nonexistent"));
    }

}
