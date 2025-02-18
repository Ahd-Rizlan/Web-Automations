package utils.report;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Drivers;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class helpers {
    private static final WebDriver driver = Drivers.getDriver();
    private static final Logger logger = LoggerFactory.getLogger(helpers.class);
    private static final int MAX_ACTION_NAME_LENGTH = 260;
    static String file_path = System.getProperty("user.dir");
    public helpers() {
    }

    public static void addScreenshotToTheReport(String actionName, Status status) {
        actionName = sanitizeActionName(actionName);
        try {
            String dateName = new SimpleDateFormat("yyyy-MM-dd_HH.mm").format(new Date());
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String destination = file_path + "/screenshots/" + actionName + "_" + dateName + ".png";
            FileUtils.copyFile(source, new File(destination));
            logger.info("Screenshot captured for action: {}", actionName);

            ExtentTest test = TestContext.getExtentTest();
            if (test != null) {
                test.log(status, actionName, MediaEntityBuilder.createScreenCaptureFromPath(destination).build());
            }
            //Use when the scripts are run on the CI/CD
                 /*   String dateName = new SimpleDateFormat("yyyy-MM-dd_HH.mm").format(new Date());
            String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            logger.info("Screenshot captured for action: {} at {}", actionName, dateName);
            ExtentTest test = TestContext.getExtentTest();
            if (test != null) {
                test.log(status, actionName + " at " + dateName, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
            }*/

        } catch (Exception e) {
            logger.error("Failed to capture screenshot for action: {}", actionName, e);
        }
    }

    private static String sanitizeActionName(String actionName) {
        String sanitizedActionName = actionName.trim()
                .replaceAll("[\\\\/:*?\"'<>|]", "-") .replaceAll("\\s{2,}", " ");
        if (sanitizedActionName.length() > MAX_ACTION_NAME_LENGTH) {
            sanitizedActionName = sanitizedActionName.substring(0, MAX_ACTION_NAME_LENGTH);
            logger.warn("Action name truncated to '{}' due to length constraints.", sanitizedActionName);
        }
        return sanitizedActionName;
    }
}

