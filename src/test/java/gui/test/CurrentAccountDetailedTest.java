package gui.test;

import data.DataProviders;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utils.constants.BillerConstants;
import utils.constants.LoginConstants;
import utils.constants.MyAccountsConstants;

import java.util.Arrays;

public class CurrentAccountDetailedTest extends BaseTest {


    @Test(priority = 1, dataProvider = "LoginDataAlternateThirteen", description = "Pre-Requisite :: Login to the Sampath vishwa application as alternate user Five", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void logIntoDahsboardAlternateUserFive(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {

        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        dashboardPage.validateTheTitle();

    }

    @Test(priority = 2, dataProvider = "CurrentAccountData", description = "Validate that user should be able to view the Current account details as mentioned  | 1", dataProviderClass = DataProviders.MyAccountPageDataProvider.class)
    public void validateCADetails(String accountNumber, String odLimit, String tempOdLimit, String overdueLiability, String reservedAmount, String accountBalance, String openedOn) throws InterruptedException {
        dashboardPage.navigateToMainMenu(BillerConstants.BUTTON_MY_ACCOUNTS);
        myAccountsPage.ValidateCurrentAccountDetails(MyAccountsConstants.TAB_NAMES[0], MyAccountsConstants.TILE_HEADERS[0], accountNumber, odLimit, tempOdLimit, overdueLiability, reservedAmount, accountBalance, openedOn);
    }

    @Test(priority = 3, dataProvider = "ChequeRequestData", description = "Validate that user should be able to view the mentioned fields when requesting the cheques  | 4,5", dataProviderClass = DataProviders.MyAccountPageDataProvider.class)
    public void validateCAChequeRequestFields(String accountNumber, String contactNo, String branch, String successMsg) throws InterruptedException {
        dashboardPage.navigateToMainMenu(BillerConstants.BUTTON_MY_ACCOUNTS);
        myAccountsPage.ValidateChequeRequestDetails(MyAccountsConstants.TAB_NAMES[0], MyAccountsConstants.TILE_HEADERS[0], accountNumber, Arrays.asList(MyAccountsConstants.CHEQUEBOOK_LEAFS), Arrays.asList(MyAccountsConstants.CHEQUEBOOK_COUNT), contactNo, branch, MyAccountsConstants.OTP, successMsg);
    }

    @Test(priority = 4, dataProvider = "AdvancedSearchData", description = "Validate that transaction Filter  | 2,3 Upliftment U3,U7", dataProviderClass = DataProviders.MyAccountPageDataProvider.class)
    public void validateCAFilter(String accountNumber, String month, String year, String from, String to, String fullDate, String amountFrom, String amountTo, String name, String address, String accountNo, String currency) throws InterruptedException {
        dashboardPage.navigateToMainMenu(BillerConstants.BUTTON_MY_ACCOUNTS);
        myAccountsPage.ValidateAdvancedSearch(MyAccountsConstants.TAB_NAMES[0], MyAccountsConstants.TILE_HEADERS[0], accountNumber, month, year, from, to, fullDate, amountFrom, amountTo, dowloadLocation, name, address, accountNo, currency);
    }


    @Test(priority = 5, dataProvider = "StopAndRevokeChequeData", description = "Validate stop cheque and revoke | 6,11,12", dataProviderClass = DataProviders.MyAccountPageDataProvider.class)
    public void validateStopAndRevokeCheque(String accountNumber, String chequeBookNo, String stopChequeMsg, String proceedingMsg, String reason, String stopChequeSuccessMsg) throws InterruptedException {
        dashboardPage.navigateToMainMenu(BillerConstants.BUTTON_MY_ACCOUNTS);
        myAccountsPage.ValidateStopChequeAndRevokeRequest(MyAccountsConstants.TAB_NAMES[0], MyAccountsConstants.TILE_HEADERS[0], accountNumber, chequeBookNo, stopChequeMsg, proceedingMsg, reason, stopChequeSuccessMsg);
    }

    @Test(priority = 6, description = "Validate inward and transfer cheques | 7,8,9,13")
    public void validateInwardAndTransferCheque() throws InterruptedException {
        dashboardPage.navigateToMainMenu(BillerConstants.BUTTON_MY_ACCOUNTS);
        myAccountsPage.ValidateInwardAndTransferRequest(MyAccountsConstants.TAB_NAMES[0], MyAccountsConstants.TILE_HEADERS[0], "001259000021");
    }


    @AfterMethod(description = "Rollback to dashboard")
    public void rollBackToDashboard() {
        dashboardPage.navigateBackToDashboard();

    }


}
