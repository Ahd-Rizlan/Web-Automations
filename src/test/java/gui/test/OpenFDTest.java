package gui.test;

import utils.Drivers;
import pages.*;
import data.DataProviders;
import utils.constants.BillerConstants;
import utils.constants.LoginConstants;
import utils.constants.SaveAccountConstants;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentTest;
import utils.report.TestContext;
import java.lang.reflect.Method;
import java.util.List;


public class OpenFDTest extends BaseTest {

    @Test(priority = 1, description = "Validate the FD creation 226,227,228,229,230,233,234,235, bug id - 1205 u1", dataProvider = "FDCompleteFlowData", dataProviderClass = DataProviders.SavingsandFDDataProvider.class)
    public void runFullFDFlow(String userName, String password, String emailSentSuccessMsg, String product, String accountNumber, String month, String rate, String amount, String interest, String totalAmount,String nickName) {

        driver.get(url);
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        dashboardPage.validateTheTitle();
        String generatedAccountNumber = fdPage.initiateFDcreationAndValidate(product, accountNumber,month, rate, amount, interest, totalAmount,nickName,LoginConstants.OTP);
        savingsPage.searchAndSelectAccountList(generatedAccountNumber);
    }

    @Test(priority = 2, dataProvider = "LoginDataSavingsFD", description = "Pre-Requisite :: Login to the Sampath vishwa application", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void logIntoDahsboard(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {

        driver.get(url);
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        dashboardPage.validateTheTitle();
    }
    @Test(priority = 3, description = "FD Product error message validation 1202,1110 - U2,U3", dataProvider = "FDAccountValidationData", dataProviderClass = DataProviders.SavingsandFDDataProvider.class)
    public void ValidationProductValues(List<Object[]> rawData) {
        fdPage.NavigateTotheFDOpen();
        fdPage.validateAllFDAccounts(rawData);

    }

}

