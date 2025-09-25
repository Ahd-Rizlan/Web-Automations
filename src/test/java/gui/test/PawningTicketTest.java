package gui.test;

import data.DataProviders;
import org.testng.annotations.Test;
import utils.constants.LoginConstants;

public class PawningTicketTest extends BaseTest{

    @Test(priority = 1, dataProvider = "LoginDataAlternateFourteen", description = "Pre-Requisite :: Login to the Sampath vishwa application", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void logIntoDahsboard(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        dashboardPage.validateTheTitle();
    }

    @Test(priority = 2, dataProvider = "PawningData", description = "Pawning settlement and validation -187,188,189,190, Uplift - u1,u2,u3,u4,u5,u6,u8 ", dataProviderClass = DataProviders.PawningDataProvider.class)
    public void validatePawnSettlement (String maxiumAmount, String expectedMessage, String incorrectAmount, String lowBalanceAccount, String amountHigherBalance, String expectedinsufficientFundMessage, String correctAccount, String correctAmount, String successMsg, String maxRetries){
        pawningTicketPage.NavogatetoPawningPage();
        pawningTicketPage.ValidatingPawningAccountSummary();
        pawningTicketPage.ValidatetheSettlement( maxiumAmount,  expectedMessage,  incorrectAmount,  lowBalanceAccount,  amountHigherBalance,  expectedinsufficientFundMessage,  correctAccount,correctAmount);
        pawningTicketPage.enterOTPAndContinueSettingsPage(LoginConstants.OTP,successMsg);
        pawningTicketPage.validateOutstandingAmountWithRetry(maxRetries);

    }

}




