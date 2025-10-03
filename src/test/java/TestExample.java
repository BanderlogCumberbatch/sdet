import org.testng.Assert;
import org.testng.annotations.Test;

public class TestExample extends BaseTest {

    @Test
    public void testSuccess() {
        driver.get("https://example.com");
        Assert.assertTrue(driver.getTitle().contains("Example"));
    }

    @Test(priority = 1)
    public void testFailure() {
        driver.get("https://example.com");
        // Этот тест упадёт и будет сделан скриншот
        Assert.assertTrue(driver.getTitle().contains("Nonexistent"));
    }

}
