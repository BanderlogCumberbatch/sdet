package listener;
import org.helpers.AllureHelper;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureTestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();

        try {
            if (testClass != null) {
                WebDriver driver = (WebDriver) testClass.getClass()
                        .getMethod("getDriver")
                        .invoke(testClass);
                AllureHelper.takeScreenshot(driver, testClass.getClass().getName());
            }
        } catch (Exception e) {
            System.out.println("Не удалось сделать скриншот: " + e.getMessage());
        }
    }
}
