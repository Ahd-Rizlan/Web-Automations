package gui.test;

import com.aventstack.extentreports.ExtentTest;

import data.DataProviders;
import org.testng.annotations.*;
import pages.*;
import utils.Drivers;
import utils.report.TestContext;

import java.lang.reflect.Method;

public class TopNavigationBarTest extends Drivers {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    ExtentTest test;


    @BeforeMethod
    private void OpenURL(Method method) {

        String methodName = method.getName();
        test = extent.createTest(methodName);
        TestContext.setExtentTest(test);
        loginPage = new LoginPage(driver);
        driver.get(url);
    }


    @Test(priority = 1,dataProvider = "YourDataProviderName", description = "Your test description", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void YourTextMethodOne(){
    }

    @Test(priority = 2,dataProvider = "YourDataProviderName", description = "Your test description", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void YourTextMethodTwo() {
    }

    @Test(priority = 3,dataProvider = "YourDataProviderName", description = "Your test description", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void YourTextMethodThree()  {
    }

    @AfterTest
    public void afterMethod() {
        TestContext.clearExtentTest();
    }
}