package gui.test;

import utils.Drivers;
import pages.*;
import data.DataProviders;
import utils.constants.LoginConstants;
import utils.constants.SaveAccountConstants;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentTest;
import utils.report.TestContext;
import java.lang.reflect.Method;


public class OpenFDTest extends BaseTest {

    @Test(priority = 1, description = "Validate the FD creation 226,227,228,229,230,233,234,235", dataProvider = "FDCompleteFlowData", dataProviderClass = DataProviders.SavingsandFDDataProvider.class)
    public void runFullFDFlow(String userName, String password, String emailSentSuccessMsg, String product, String accountNumber, String month, String rate, String amount, String interest, String totalAmount,String nickName) {

        driver.get(url);
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        dashboardPage.validateTheTitle();
        fdPage.initiateFDcreationAndValidate(product, accountNumber,month, rate, amount, interest, totalAmount,nickName,LoginConstants.OTP);
    }

}

