package gui.test;

import data.DataProviders;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utils.constants.BillerConstants;
import utils.constants.LoginConstants;
import utils.constants.MyAccountsConstants;

public class MyAccountTest extends BaseTest {


    @Test(priority = 1, dataProvider = "LoginDataAlternateTwo", description = "Pre-Requisite :: Login to the Sampath vishwa application as alternate user Five", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void logIntoDahsboardAlternateUserOne(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {

        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        dashboardPage.validateTheTitle();

    }

    @Test(priority = 2, description = "Validate 'My Accounts' items for savings and current account | 1,2,3,4,6,7,9,10,11,12,13,14,15,23,25")
    public void validateMyAccountProductsSavingsCurrent() throws InterruptedException {
        dashboardPage.navigateToMainMenu(BillerConstants.BUTTON_MY_ACCOUNTS);
        myAccountsPage.navigateToSavingsAndCurrentAccountAndValidate(MyAccountsConstants.TAB_NAMES[0], MyAccountsConstants.TILE_HEADERS[0]);
    }

    @Test(priority = 3, description = "Validate 'My Accounts' items for deposit account | 1,2,3,4,6,7,9,10,11,12,13,14,15,18")
    public void validateMyAccountProductsDeposit() throws InterruptedException {
        dashboardPage.navigateToMainMenu(BillerConstants.BUTTON_MY_ACCOUNTS);
        myAccountsPage.navigateToDepositAccountAndValidate(MyAccountsConstants.TAB_NAMES[1], MyAccountsConstants.TILE_HEADERS[1]);
    }

    @Test(priority = 4, description = "Validate 'My Accounts' items for pawning account | 1,2,3,4,6,7,9,10,11,12,13,14,15")
    public void validateMyAccountProductsPawning() throws InterruptedException {
        dashboardPage.navigateToMainMenu(BillerConstants.BUTTON_MY_ACCOUNTS);
        myAccountsPage.navigateToPawningAccountAndValidate(MyAccountsConstants.TAB_PAWNING, MyAccountsConstants.TILE_HEADER_PAWNING[0]);
    }

    @Test(priority = 5, description = "Validate 'My Accounts' items for t bill | 1,2,3,4,6,7,9,10,11,12,13,14,15")
    public void validateMyAccountProductsTBill() throws InterruptedException {
        dashboardPage.navigateToMainMenu(BillerConstants.BUTTON_MY_ACCOUNTS);
        myAccountsPage.navigateToTBillAndValidate(MyAccountsConstants.TAB_NAMES[3], MyAccountsConstants.TILE_HEADERS[3]);
    }

    @Test(priority = 6, description = "Validate 'My Accounts' items for repo | 1,2,3,4,6,7,9,10,11,12,13,14,15")
    public void validateMyAccountProductsRepo() throws InterruptedException {
        dashboardPage.navigateToMainMenu(BillerConstants.BUTTON_MY_ACCOUNTS);
        myAccountsPage.navigateToRepoAndValidate(MyAccountsConstants.TAB_NAMES[4], MyAccountsConstants.TILE_HEADERS[4]);
    }

    @Test(priority = 7, dataProvider = "LoginDataAlternateTwelve", description = "Pre-Requisite :: Login to the Sampath vishwa application as alternate user Five", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void logIntoDahsboardAlternateUserTwelve(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {

        driver.get(url);
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        dashboardPage.validateTheTitle();

    }

    @Test(priority = 8, description = "Validate 'My Accounts' items for loan account | 1,2,3,4,6,7,9,10,11,12,13,14,15")
    public void validateMyAccountProductsLoan() throws InterruptedException {
        dashboardPage.navigateToMainMenu(BillerConstants.BUTTON_MY_ACCOUNTS);
        myAccountsPage.navigateToLoanAccountAndValidate(MyAccountsConstants.TAB_NAMES[2], MyAccountsConstants.TILE_HEADERS[2]);
    }


    @Test(priority = 9, description = "Validate update of nicknames  | 5,8,24")
    public void validateMyAccountNickName() throws InterruptedException {

        dashboardPage.navigateToMainMenu(BillerConstants.BUTTON_MY_ACCOUNTS);
        myAccountsPage.UpdateAndValidateNickName(MyAccountsConstants.TAB_NAMES_FOR_NICKNAME, MyAccountsConstants.TILE_HEADERS_FOR_NICKNAME);

    }

    @Test(priority = 10, dataProvider = "LoginDataAlternateNine", description = "Pre-Requisite :: Login to the Sampath vishwa application as alternate user two", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void logIntoDahsboardAlternateUserTwo(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {

        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        dashboardPage.validateTheTitle();
    }

    @Test(priority = 11, description = "Validate 'My Accounts' items only pawning | 16,17")
    public void validateMyAccountPawning() throws InterruptedException {

        dashboardPage.navigateToMainMenu(BillerConstants.BUTTON_MY_ACCOUNTS);
        myAccountsPage.navigateToAccountProductTypeAndValidate(MyAccountsConstants.TAB_NAME_PAWNING, MyAccountsConstants.TILE_HEADER_PAWNING);
    }

    @Test(priority = 12, description = "Validate 'My Accounts' items credit card | 18,19,20")
    public void validateMyAccountCreditCard() throws InterruptedException {

        dashboardPage.navigateToMainMenu(BillerConstants.BUTTON_MY_ACCOUNTS);
        myAccountsPage.ValidateCreditCardDetails(MyAccountsConstants.CREDIT_CARDS, MyAccountsConstants.ACCOUNTS_CREDIT_CARDS, "");
    }

    @AfterMethod(description = "Rollback to dashboard")
    public void rollBackToDashboard() {
        dashboardPage.navigateBackToDashboard();

    }

}
