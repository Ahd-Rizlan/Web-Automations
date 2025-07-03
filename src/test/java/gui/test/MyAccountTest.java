package gui.test;

import data.DataProviders;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utils.constants.BillerConstants;
import utils.constants.LoginConstants;
import utils.constants.MyAccountsConstants;

public class MyAccountTest extends BaseTest {


    @Test(priority = 1, dataProvider = "LoginDataAlternateFive", description = "Pre-Requisite :: Login to the Sampath vishwa application as alternate user Five", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void logIntoDahsboardAlternateUserOne(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {

        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        dashboardPage.validateTheTitle();

    }

    @Test(priority = 2, description = "Validate 'My Accounts' items except pawning Includes detailed view of T-bills & repo | 1,2,3,4,6,7,9,10,11,12,13,14,15")
    public void validateMyAccountProductsExceptPawning() throws InterruptedException {
        dashboardPage.navigateToMainMenu(BillerConstants.BUTTON_MY_ACCOUNTS);
        myAccountsPage.navigateToAccountProductTypeAndValidate(MyAccountsConstants.TAB_NAMES, MyAccountsConstants.TILE_HEADERS);
    }

    @Test(priority = 3, description = "Validate update of nicknames  | 5,8")
    public void validateMyAccountNickName() throws InterruptedException {

        dashboardPage.navigateToMainMenu(BillerConstants.BUTTON_MY_ACCOUNTS);
        myAccountsPage.UpdateAndValidateNickName(MyAccountsConstants.TAB_NAMES_FOR_NICKNAME, MyAccountsConstants.TILE_HEADERS_FOR_NICKNAME);

    }

    @Test(priority = 4, dataProvider = "LoginDataAlternateNine", description = "Pre-Requisite :: Login to the Sampath vishwa application as alternate user two", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void logIntoDahsboardAlternateUserTwo(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {

        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        dashboardPage.validateTheTitle();
    }

    @Test(priority = 5, description = "Validate 'My Accounts' items only pawning | 16,17")
    public void validateMyAccountPawning() throws InterruptedException {

        dashboardPage.navigateToMainMenu(BillerConstants.BUTTON_MY_ACCOUNTS);
//        dashboardPage.hoverOverMenuAndClick(BillerConstants.BUTTON_MY_ACCOUNTS,"Pawning");
        myAccountsPage.navigateToAccountProductTypeAndValidate(MyAccountsConstants.TAB_NAME_PAWNING, MyAccountsConstants.TILE_HEADER_PAWNING);
    }

    @Test(priority = 6, description = "Validate 'My Accounts' items credit card | 18,19,20")
    public void validateMyAccountCreditCard() throws InterruptedException {

        dashboardPage.navigateToMainMenu(BillerConstants.BUTTON_MY_ACCOUNTS);
//        dashboardPage.hoverOverMenuAndClick(BillerConstants.BUTTON_MY_ACCOUNTS,"Credit Cards");
        myAccountsPage.ValidateCreditCardDetails(MyAccountsConstants.CREDIT_CARDS, MyAccountsConstants.ACCOUNTS_CREDIT_CARDS,"");
    }

    @AfterMethod(description = "Rollback to dashboard")
    public void rollBackToDashboard() {
        dashboardPage.navigateBackToDashboard();

    }

}
