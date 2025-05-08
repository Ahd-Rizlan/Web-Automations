package utils.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ExtentReportManager {


    static String os = System.getProperty("os.name");
    static String user = System.getProperty("user.name");
    static String hostName = "Unknown";



    public static ExtentReports createExtentReport(String filePath,String reportName,String documentTitle,String Author) {
        ExtentSparkReporter sparkReports = new ExtentSparkReporter(filePath);
        sparkReports.config().setReportName(reportName);
        sparkReports.config().setDocumentTitle(documentTitle);
        sparkReports.config().setTheme(Theme.STANDARD);


        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            hostName = inetAddress.getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        ExtentReports reports = new ExtentReports();
        reports.attachReporter(sparkReports);
        // Set system info in the report
        reports.setSystemInfo("Machine", hostName);
        reports.setSystemInfo("OS", os);
        reports.setSystemInfo("User", user);



        return reports;
    }


    //test case detail logging
    public static void logPassDetails(String log) {
        TestNgListener.extentTest.get().pass(MarkupHelper.createLabel(log, ExtentColor.GREEN));
    }
    public static void logFailureDetails(String log) {
        TestNgListener.extentTest.get().fail(MarkupHelper.createLabel(log, ExtentColor.RED));
    }
    public static void logExceptionDetails(String log) {
        TestNgListener.extentTest.get().fail(log);
    }
    public static void logInfoDetails(String log) {
        TestNgListener.extentTest.get().info(MarkupHelper.createLabel(log, ExtentColor.GREY));
    }

    public static void logJson(String json) {
        TestNgListener.extentTest.get().info(MarkupHelper.createCodeBlock(json, CodeLanguage.JSON));
    }
}
