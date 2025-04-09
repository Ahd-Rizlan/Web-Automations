package gui.test;

import data.DataProviders;
import gui.utils.constants.LoginConstants;
import gui.utils.constants.DashboardConstants;
import org.testng.annotations.*;
import pages.DashboardPage;
import pages.LoginPage;
import pages.OTPPage;
import pages.SavedPayeesPage;
import utils.Drivers;
import com.aventstack.extentreports.ExtentTest;
import utils.report.TestContext;

import java.lang.reflect.Method;

public class DashboardTest extends Drivers {

    LoginPage loginPage;
    OTPPage otpPage;
    ExtentTest exTest;
    DashboardPage dashboardPage;
    SavedPayeesPage savedPayeesPage;

    @BeforeMethod
    private void OpenURL(Method method) {
        String methodName = method.getName();
        exTest = extent.createTest(methodName);
        TestContext.setExtentTest(exTest);
        dashboardPage = new DashboardPage(driver);
        loginPage = new LoginPage(driver);
        otpPage = new OTPPage(driver);
        savedPayeesPage = new SavedPayeesPage(driver);
    }

    @Test(priority = 1, dataProvider = "LoginData", description = "Pre-Requisite :: Login to the Sampath vishwa application", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void logIntoDahsboard(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {
        driver.get(url);
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
//        Uncomment below once popup feature is deployed
//        dashboardPage.closeAlertPopup();
        dashboardPage.validateTheTitle();
    }

    @Test(priority = 18, dataProvider = "DashboardSavingsValidationData", description = "Validate the 6 key points in savings account | 01", dataProviderClass = DataProviders.DashboardDataProvider.class)
    public void validateSavingsAccountAtDashboard(String savingsAccountNumber, String currencyAndAvailableBalance, String productName) {
        dashboardPage.validateSavingsAccountAtDashboard(savingsAccountNumber, currencyAndAvailableBalance, DashboardConstants.STATUS_PRIMARY, DashboardConstants.STATUS_ACTIVE, productName);
    }

    @Test(priority = 3, dataProvider = "DashboardFDValidationData", description = "Validate the 6 key points in FD account | 03", dataProviderClass = DataProviders.DashboardDataProvider.class)
    public void validateFixedDepositAccountAtDashboard(String fDAccountNumber, String currencyAndAvailableBalance, String maturityAmount, String maturityDate, String interestRate) {
        dashboardPage.validateFixedDepositAccountAtDashboard(fDAccountNumber, currencyAndAvailableBalance, maturityAmount, maturityDate, interestRate);
    }

    @Test(priority = 4, dataProvider = "DashboardLoanValidationData", description = "Validate the 6 key points in loan account | 04", dataProviderClass = DataProviders.DashboardDataProvider.class)
    public void validateLoanAccountAtDashboard(String loanAccountNumber, String loanAmt, String outstanding, String loanPeriod, String interestRate) {
        dashboardPage.validateLoanAccountAtDashboard(loanAccountNumber, loanAmt, outstanding, loanPeriod, interestRate);
    }

    @Test(priority = 5, description = "Validate the availability of options under 'Quick Action Widget' | 10")
    public void validateQuickActionAtDashboard() {
        dashboardPage.validateQuickActionAtDashboard();
    }

    @Test(priority = 6, description = "Validate the direction to the respective journeys upon clicking on Quick Action options | 10")
    public void validateQuickActionsWidgetsFunctionality() {
        dashboardPage.validateQuickActionsWidgetsFunctionality(DashboardConstants.BUTTON_TEXT_DASHBOARD);
    }

    @Test(priority = 7, description = "Validate that availability of RVT - transfer in dashboard | 11")
    public void validateAvailabilityOfRVTTransfer() {
        dashboardPage.validateRVTTransferWidgetRecords(DashboardConstants.CURRENCY_VALUES);
    }

    @Test(priority = 8, description = "Validate that availability of RVT - payments in dashboard | 12")
    public void validateAvailabilityOfRVTPayments() {
        dashboardPage.validateRVTPaymentWidgetRecords(DashboardConstants.CURRENCY_VALUES);
    }

    @Test(priority = 9, description = "Validate that availability of RVT - mobile cash in dashboard | 13")
    public void validateRVTMobileCashWidgetRecords() {
        dashboardPage.validateRVTMobileCashWidgetRecords(DashboardConstants.CURRENCY_VALUES);
    }

    @Test(priority = 10, description = "Validate retrieval of transfers done in vishwa recent own accounts | 15")
    public void validateRVTTransactionRetrievalOA() {
        dashboardPage.validateRVTTransferRetrievalOfTransfersOwnAcc(DashboardConstants.CURRENCY_VALUES, DashboardConstants.STATUS_PAYMENT_SUCCESS);
    }

    @Test(priority = 11, description = "Validate retrieval of transfers done in vishwa recent other accounts | 16")
    public void validateRVTTransactionRetrievalOtherAcc() {
        dashboardPage.validateRVTTransferRetrievalOfTransfersOtherAcc(DashboardConstants.CURRENCY_VALUES, DashboardConstants.STATUS_PAYMENT_SUCCESS, DashboardConstants.CATEGORY_THIRD_PARTY_SAMPATH_TRANSFER, DashboardConstants.OWN_ACCOUNT);
    }

    @Test(priority = 12, description = "Validate retrieval of payments done in vishwa dashboard | 18", dataProvider = "DashboardRVTPaymentPopup", dataProviderClass = DataProviders.DashboardDataProvider.class)
    public void validateRVTPaymentRetrieval(String toAccount) {
        dashboardPage.validateRVTTransferRetrievalOfPayment(DashboardConstants.CURRENCY_VALUES, DashboardConstants.STATUS_PAYMENT_SUCCESS, toAccount);
    }

    @Test(priority = 13, description = "Validate retrieval of transfers done in vishwa mobile cash | 19")
    public void validateRVTMobileCashRetrieval() {
        dashboardPage.validateRVTTransferRetrievalOfMobileCash(DashboardConstants.CURRENCY_VALUES, DashboardConstants.STATUS_PAYMENT_SUCCESS);
    }

    @Test(priority = 14, description = "Validate the maximum limit of fav billers as 9 per page in Dashboard | 22")
    public void validateMarkedBillersAsFavouriteIsVisibleInBillerWidget() {
        dashboardPage.validateMarkedBillersAsFavouriteIsVisibleInBillerWidget();
    }

    @Test(priority = 15, description = "Validate the availability of favourite biller in dashboard | 23")
    public void validateAvailabilityOfFavouriteBiller() {
        dashboardPage.validateFavouriteBillerWidget();
    }

    @Test(priority = 16, description = "Validate the re-initiation of transactions from favourite biller in dashboard | 24")
    public void validateReInitiationOfTransactionsFromFavouriteBiller() {
        savedPayeesPage.validateFBillerPopup(dashboardPage.getFBWidgetFirstRecordDetails());
    }

    @Test(priority = 17, description = "Validate the display of favourite payee in dashboard | 27")
    public void validateAvailabilityOfFavouritePayee() {
        dashboardPage.validateFavouritePayeeWidget();
    }

    @Test(priority = 2, description = "Validate the re-initiation of transactions from favourite payee in dashboard | 29")
    public void validateReInitiationOfTransactionsFromFavouritePayee() {
        savedPayeesPage.validateQFTPopup(dashboardPage.getFPWidgetFirstRecordDetails());
    }

    @Test(priority = 19, description = "Validate the add new fav payee from the saved payees list | 30")
    public void validateAddNewPayeeFromFavouritePayee() {
        dashboardPage.navigateToAddFavouritePayee();
        savedPayeesPage.addNewFavouritePayee();
    }

    @Test(priority = 20, description = "Validate that availability of msg/ads set by bank admins in the dashboard | 34")
    public void validateMsgOrAdvertisements() {
        dashboardPage.validateMessagesAndAdvertisements();
    }

    @Test(priority = 21, description = "Validate the functionality of options in top bar in dashboard 1.FCalandar 2.Msg 3. Notification 4.Profile | 39")
    public void validateTopBarIconsInDashboard() {
        dashboardPage.validateFunctionalityOfTopBarIconsInDashboard(DashboardConstants.OPTION_SETTINGS, DashboardConstants.BUTTON_LOGOUT_TEXT, LoginConstants.OTP, DashboardConstants.CONFIRM_LOGOUT_MSG, DashboardConstants.BUTTON_TEXT_BACK);
    }

    @Test(priority = 22, description = "Validate the maximum limit of fav payee as 9 per page in Dashboard AND Marked payees as fav should be visible in dashboard  | 25 & 26")
    public void validateMarkedPayeesAsFavouriteIsVisibleInPayeeWidget() {
        dashboardPage.validateMarkedPayeesAsFavouriteIsVisibleInPayeeWidget();
    }

    @Test(priority = 23, description = "Validate the functionality of display options in top bar in dashboard  | 38")
    public void validateTopBarFunctions() {
        dashboardPage.validateTopBarFunctions(DashboardConstants.DASHBOARD, DashboardConstants.MY_ACCOUNT, DashboardConstants.MANAGE_SCHEDULE, DashboardConstants.OPTION_MY_ACCOUNTS, DashboardConstants.OPTION_MANAGE_SCHEDULES, DashboardConstants.SCHEDULE_MANAGEMENT);
    }

    @Test(priority = 24, description = "Validate the 6 key points in accounts | 36")
    public void validateKeyPointsInAccounts() {
        dashboardPage.validateAllAccountsAtDashboard(DashboardConstants.CURRENCY_VALUES, DashboardConstants.STATUS_VALUES);
    }

    @Test(priority = 25, description = "Validate the 6 key points in deposits | 37")
    public void validateKeyPointsInDeposits() {
        dashboardPage.validateAllFDAccountsAtDashboard(DashboardConstants.CURRENCY_VALUES);
    }

    @Test(priority = 26, description = "Validate the 6 key points in loan | 40")
    public void validateKeyPointsInLoans() {
        dashboardPage.validateAllLoanAccountsAtDashboard(DashboardConstants.CURRENCY_VALUES);
    }

    @Test(priority = 27, description = "Validate the downloaded transaction record of transfers, payments, mobile cash | 14")
    public void validateDownloadedRecord() {

        dashboardPage.validateRVTDownloadedRecordTransfer(dowloadLocation);
        dashboardPage.validateRVTDownloadedRecordPayment(dowloadLocation);
        dashboardPage.validateRVTDownloadedRecordMobileCash(dowloadLocation);
    }

    @Test(priority = 28, dataProvider = "LoginDataAlternateOne", description = "Pre-Requisite :: Login to the Sampath vishwa application as alternate user", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void logIntoDahsboardAlternateUserOne(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {

        driver.get(url);
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
//        Uncomment below once popup feature is deployed
//        dashboardPage.closeAlertPopup();
        dashboardPage.validateTheTitle();
    }

    @Test(priority = 29, description = "Validate the visibility of total values LKR  | 31", dataProvider = "DashboardAccountPortfolio", dataProviderClass = DataProviders.DashboardDataProvider.class)
    public void validatePortfolioioFunctions(String imgLocation, String userName, String threshold) {
        dashboardPage.validateAccountPortfolio(imgLocation, userName, threshold, DashboardConstants.CURRENCY_VALUES);
    }

    @Test(priority = 30, description = "Validate the 6 key points in all current accounts | 2")
    public void validateKeyPointsInCurrentAccount() {
        dashboardPage.validateAllCurrentAccountsAtDashboard(DashboardConstants.CURRENCY_VALUES, DashboardConstants.STATUS_VALUES);
    }


//    -------- WIP
    @Test(priority = 10, description = "Validate that settings option is available on the user info dropdown menu at dashboard")
    public void validateSettingsOption() {
        dashboardPage.validateSettingsOption(DashboardConstants.OPTION_SETTINGS);
    }


    @AfterMethod(description = "Rollback to dashboard")
    public void rollBackToDashboard() {
        dashboardPage.navigateBackToDashboard();
    }
}
