package gui.test;

import com.aventstack.extentreports.ExtentTest;
import data.DataProviders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utils.Drivers;
import utils.constants.DashboardConstants;
import utils.constants.LoginConstants;
import utils.report.TestContext;

import java.lang.reflect.Method;

public class ObtainLoanTest extends BaseTest {

    @Test(priority = 1, dataProvider = "LoginDataObtainLoan", description = "Pre-Requisite :: Login to the Sampath vishwa application", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void logIntoDahsboard(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        dashboardPage.validateTheTitle();
    }

    @Test(priority = 2, description = "Validate the obtain loan that in the fixed deposit section - 118 ")
    public void validateObtainLoanInFixedDeposits (){
        obtainLoanPage.ValidateObtainLoaninFixedDepositSection();
    }

    @Test(priority = 3, dataProvider = "LoanDetails", description = "Obtain loan that in quick action section and validating the loan creation confirmation - 119,121,122,123,124,125,126,", dataProviderClass = DataProviders.ObtainLoanDataProvider.class)
    public void validateObtainLoanInQauickActions (String accountNumber1,String minimumAmount, String maximumAmount, String actualAmount, String wrongMonth, String correctMonth, String purpose, String accountNumber2){
        dashboardPage.navigateBackToDashboard();
        obtainLoanPage.obtainAllAccountTypes();
        dashboardPage.selectQuickActions("Obtain");
        obtainLoanPage.ValidateObtainLoanPageContent(accountNumber1,minimumAmount, maximumAmount, actualAmount, wrongMonth, correctMonth, purpose, accountNumber2);
        obtainLoanPage.ValidateObtainLoanConfirmation();
        obtainLoanPage.enterOTPAndContinueSettingsPage(LoginConstants.OTP);
    }
}
