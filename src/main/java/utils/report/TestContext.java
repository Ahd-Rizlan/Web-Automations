
package utils.report;
import com.aventstack.extentreports.ExtentTest;

public class TestContext {
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static void setExtentTest(ExtentTest test) {
        extentTest.set(test);
    }

    public static ExtentTest getExtentTest() {
        return extentTest.get();
    }

    public static void clearExtentTest() {
        extentTest.remove();
    }

}