package gui.test;

import com.aventstack.extentreports.ExtentTest;

import data.DataProviders;
import org.testng.annotations.*;
import pages.ExamplePageThree;
import pages.HomePage;
import pages.LoginPage;
import utils.Drivers;
import utils.TestContext;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExampleOneTest extends Drivers {

    HomePage homePage ;
    LoginPage loginpage;
    ExamplePageThree exPageOne;
    ExtentTest test;


    @BeforeMethod
    private void OpenURL(Method method) {

        String methodName = method.getName();
        test = extent.createTest(methodName);
        TestContext.setExtentTest(test);
        homePage = new HomePage(driver);
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
