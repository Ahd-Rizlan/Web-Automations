package gui.test;

import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import pages.DashboardPage;
import pages.LoginPage;
import pages.OTPPage;
import utils.Drivers;
import utils.report.TestContext;

import java.lang.reflect.Method;

public class MakeTransactionsTest extends Drivers {

    DashboardPage dashboardPage;
    LoginPage loginPage;
    OTPPage otpPage;
    ExtentTest exTest;

    @BeforeMethod
    private void OpenURL(Method method) {
        String methodName = method.getName();
        exTest = extent.createTest(methodName);
        TestContext.setExtentTest(exTest);
        dashboardPage = new DashboardPage(driver);
        loginPage = new LoginPage(driver);
        otpPage = new OTPPage(driver);
        driver.get(url);
    }



//    @Test(priority = 2, dataProvider = "LoginData", description = "Validate the successful logging with correct user ID & Password", dataProviderClass = DataProviders.LoginDataProvider.class)
//    public void validateTheSuccessfulLogin(String userName, String password, String emailSentSuccessMsg) {
//        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
//        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.TRUE);
//    }

    @AfterTest
    public void afterMethod() {
        TestContext.clearExtentTest();
    }
}
