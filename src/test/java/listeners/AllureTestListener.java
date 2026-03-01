package listeners;

import org.helpers.AllureHelper;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

/**
 * Слушатель для прикрепления данных к тестовым методам отчёта Allure.
 */
public class AllureTestListener implements IInvokedMethodListener {
    /* ITestListener onTestFailure() не подходит под прикрепление скриншотов Allure,
    /* так как он вызывается не в контексте тестового метода */

    /**
     * Метод, вызываемый после вызова тестового метода, переопределённый для прикрепления скриншота к проваленным тестовым методам.
     * @param method Вызванный метод
     * @param result Результат теста
     */
    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result) {
        if (method.isTestMethod() && !result.isSuccess()) {
            Object testClass = result.getInstance();
            try {
                WebDriver driver = (WebDriver) testClass.getClass()
                        .getMethod("getDriver")
                        .invoke(testClass);
                AllureHelper.takeScreenshot(driver, testClass.getClass().getName());
            } catch (Exception e) {
                System.out.println("Не удалось сделать скриншот: " + e.getMessage());
            }
        }
    }
}
