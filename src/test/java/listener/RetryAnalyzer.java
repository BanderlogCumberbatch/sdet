package listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int maxRetryCount = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            if (retryCount < maxRetryCount) {
                retryCount++;
                System.out.println("Перезапускаем тест " + result.getName() + " со статусом " +
                        result.getStatus() + " в " + retryCount + " раз");
                return true;
            }
        }
        return false;
    }
}
