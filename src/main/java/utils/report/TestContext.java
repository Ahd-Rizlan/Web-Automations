
package utils.report;
import com.aventstack.extentreports.ExtentTest;

public class TestContext {
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static final ThreadLocal<ExtentTest> parentExtentTest = new ThreadLocal<>();
    private static final ThreadLocal<String> parentTestName = new ThreadLocal<>();

    public static void setExtentTest(ExtentTest test) {
        extentTest.set(test);
    }

    public static ExtentTest getExtentTest() {
        return extentTest.get();
    }

    public static void clearExtentTest() {
        extentTest.remove();
    }

    public static void setParentExtentTest(ExtentTest test, String name) {
        parentExtentTest.set(test);
        parentTestName.set(name);
    }
    public static ExtentTest getParentExtentTest() { return parentExtentTest.get(); }
    public static String getParentTestName() { return parentTestName.get(); }

    public static void clear() {
        extentTest.remove();
        parentExtentTest.remove();
        parentTestName.remove();
    }
}