package gui.test;

import data.DataProviders;
import org.testng.annotations.Test;
import utils.constants.BillerConstants;
import utils.constants.LoginConstants;
import utils.constants.MyAccountsConstants;

public class SavingsAccountDetailedTest extends BaseTest {



    @Test(priority = 1, dataProvider = "LoginDataAlternateFive", description = "Pre-Requisite :: Login to the Sampath vishwa application as alternate user Five", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void logIntoDahsboardAlternateUserFive(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {

        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        dashboardPage.validateTheTitle();
    }

    @Test(priority = 2, dataProvider = "SavingsAccountData", description = "Validate that the customer able to view summary of savings account and the advanced search PDF view | 1,2,3", dataProviderClass = DataProviders.MyAccountPageDataProvider.class)
    public void validateSADetails(String accountNumber, String accHolderName, String systemReserved, String lienAmount, String accOpenedOn, String accountBalance, String floatBalance, String amountFrom, String amountTo) throws InterruptedException {
        dashboardPage.navigateToMainMenu(BillerConstants.BUTTON_MY_ACCOUNTS);
        myAccountsPage.ValidateSavingsAccountDetails(MyAccountsConstants.TAB_NAMES[0],MyAccountsConstants.TILE_HEADERS[0],accountNumber,accHolderName,systemReserved,lienAmount,accOpenedOn,accountBalance,floatBalance,amountFrom,amountTo,dowloadLocation);
    }

}
