package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.github.bonigarcia.wdm.managers.EdgeDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Drivers{

    public static WebDriver driver;
    public static ExtentReports extent;
    public static propertyFileReader property = new propertyFileReader();
    public static String browser = property.getProperty("gui-config", "BROWSER");
    public static String url = property.getProperty("gui-config", "URL");
    public static String userName = property.getProperty("gui-config", "USERNAME");
    public static String password = property.getProperty("gui-config", "PASSWORD");

    String projectRoot = System.getProperty("user.dir");
    String webDriverRoot = projectRoot + "/" + property.getProperty("gui-config","WEBDRIVERS");
    String chromeDriverPath = webDriverRoot + "chromedriver.exe";
    String firefoxDriverPath = webDriverRoot + "geckodriver.exe";
    String edgeDriverPath = webDriverRoot + "msedgedriver.exe";

    @BeforeSuite
    public void setDriversAndExtentReports() {

        String className = this.getClass().getSimpleName();
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH.mm").format(new Date());
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(
                System.getProperty("user.dir") + "/extent-reports/" + className + "_Report_" + timestamp + ".html"
        );
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Uses chrome driver by default
        if (browser == null) {
            ChromeDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        } else {

            switch (browser) {
                case "Chrome":
//                    ChromeDriverManager.chromedriver().setup();
                    System.setProperty("webdriver.chrome.driver", chromeDriverPath);
//                    driver = new ChromeDriver();
                    ChromeOptions chromeOptionss = new ChromeOptions();
                    chromeOptionss.addArguments("--no-sandbox");
                    chromeOptionss.addArguments("--disable-dev-shm-usage");
                    driver = new ChromeDriver(chromeOptionss);
                    driver.manage().window().maximize();
                    break;

                case "Headless":
                    ChromeDriverManager.chromedriver().setup();
                    //System.setProperty("webdriver.chrome.driver", chromeDriverPath);
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless");
                    driver = new ChromeDriver(chromeOptions);
                    driver.manage().window().maximize();
                    break;
                case "Firefox":
                    FirefoxDriverManager.firefoxdriver().setup();
                    //System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    break;
                case "Edge":
                    EdgeDriverManager.edgedriver().setup();
                    //System.setProperty("webdriver.edge.driver", edgeDriverPath);
                    driver = new EdgeDriver();
                    driver.manage().window().maximize();
                    break;

                default:
                    ChromeDriverManager.chromedriver().setup();
                    //System.setProperty("webdriver.chrome.driver", chromeDriverPath);
                    driver = new ChromeDriver();
                    break;
            }
            driver.manage().window().maximize();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                if (driver != null) {
                    driver.quit();
                }
                if (extent != null) {
                    extent.flush();
                }
            }));

        }
    }
    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (extent != null) {
            extent.flush();
        }
    }
    public static WebDriver getDriver() {
        return driver;
    }
}
