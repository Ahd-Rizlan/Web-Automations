package utils.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestNgListener implements ITestListener {


    ExtentReports reports;

    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();


    @Override
    public void onTestStart(ITestResult result) {

        String className = result.getTestClass().getRealClass().getSimpleName();
        String methodName = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription();

        // Final test name: methodName + description
        String testDisplayName = methodName + (description != null && !description.isEmpty() ? " - " + description : "");

        // Create top-level test
        ExtentTest test = reports.createTest(testDisplayName);

        // Assign category for grouping
        test.assignCategory(className);
        TestContext.setExtentTest(test);


    }


    @Override
    public void onTestFailure(ITestResult result) {
        TestContext.getExtentTest().fail(result.getThrowable());
        // Flushing after failure
        if (reports != null) {
            reports.flush();
        }
    }

    @Override
    public void onStart(ITestContext context) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH.mm").format(new Date());
        String reportPath = System.getProperty("user.dir") + "/extent-reports/Execution_Report_"+ timestamp +".html";
        reports = ExtentReportManager.createExtentReport(
                reportPath,
                "Automation Report",
                "Regression Tests",
                "Automation Team"
        );
    }

    @Override
    public void onFinish(ITestContext context) {
        if (reports != null)
            reports.flush();
    }
}
