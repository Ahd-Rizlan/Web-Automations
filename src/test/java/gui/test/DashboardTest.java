package gui.test;

import data.DataProviders;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utils.constants.DashboardConstants;
import utils.constants.LoginConstants;

public class DashboardTest extends BaseTest {

    @Test(dataProvider = "LoginData", description = "Pre-Requisite :: Login to the Sampath vishwa application", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void logIntoDahsboard(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {

        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
//        Uncomment below once popup feature is deployed
//        dashboardPage.closeAlertPopup();
        dashboardPage.validateTheTitle();
    }

    @Test(dataProvider = "DashboardSavingsValidationData", description = "Validate the 6 key points in savings account | 01", dataProviderClass = DataProviders.DashboardDataProvider.class)
    public void validateSavingsAccountAtDashboard(String savingsAccountNumber, String currencyAndAvailableBalance, String productName) {
        dashboardPage.validateSavingsAccountAtDashboard(savingsAccountNumber, currencyAndAvailableBalance, DashboardConstants.STATUS_PRIMARY, DashboardConstants.STATUS_ACTIVE, productName);
    }

    @Test(dataProvider = "DashboardFDValidationData", description = "Validate the 6 key points in FD account | 03", dataProviderClass = DataProviders.DashboardDataProvider.class)
    public void validateFixedDepositAccountAtDashboard(String fDAccountNumber, String currencyAndAvailableBalance, String maturityAmount, String maturityDate, String interestRate) {
        dashboardPage.validateFixedDepositAccountAtDashboard(fDAccountNumber, currencyAndAvailableBalance, maturityAmount, maturityDate, interestRate);
    }

    @Test(dataProvider = "DashboardLoanValidationData", description = "Validate the 6 key points in loan account | 04", dataProviderClass = DataProviders.DashboardDataProvider.class)
    public void validateLoanAccountAtDashboard(String loanAccountNumber, String loanAmt, String outstanding, String loanPeriod, String interestRate) {
        dashboardPage.validateLoanAccountAtDashboard(loanAccountNumber, loanAmt, outstanding, loanPeriod, interestRate);
    }

    @Test(description = "Validate the availability of options under 'Quick Action Widget' | 10")
    public void validateQuickActionAtDashboard() {
        dashboardPage.validateQuickActionAtDashboard();
    }

    @Test(description = "Validate the direction to the respective journeys upon clicking on Quick Action options | 10")
    public void validateQuickActionsWidgetsFunctionality() {
        dashboardPage.validateQuickActionsWidgetsFunctionality(DashboardConstants.BUTTON_TEXT_DASHBOARD);
    }

    @Test(description = "Validate that availability of RVT - transfer in dashboard | 11")
    public void validateAvailabilityOfRVTTransfer() {
        dashboardPage.validateRVTTransferWidgetRecords(DashboardConstants.CURRENCY_VALUES);
    }

    @Test(description = "Validate that availability of RVT - payments in dashboard | 12")
    public void validateAvailabilityOfRVTPayments() {
        dashboardPage.validateRVTPaymentWidgetRecords(DashboardConstants.CURRENCY_VALUES);
    }

    @Test(description = "Validate that availability of RVT - mobile cash in dashboard | 13")
    public void validateRVTMobileCashWidgetRecords() {
        dashboardPage.validateRVTMobileCashWidgetRecords(DashboardConstants.CURRENCY_VALUES);
    }

    @Test(description = "Validate retrieval of transfers done in vishwa recent own accounts | 15")
    public void validateRVTTransactionRetrievalOA() {
        dashboardPage.validateRVTTransferRetrievalOfTransfersOwnAcc(DashboardConstants.CURRENCY_VALUES, DashboardConstants.STATUS_PAYMENT_SUCCESS);
    }

    @Test(description = "Validate retrieval of transfers done in vishwa recent other accounts | 16")
    public void validateRVTTransactionRetrievalOtherAcc() {
        dashboardPage.validateRVTTransferRetrievalOfTransfersOtherAcc(DashboardConstants.CURRENCY_VALUES, DashboardConstants.STATUS_PAYMENT_SUCCESS, DashboardConstants.CATEGORY_THIRD_PARTY_SAMPATH_TRANSFER, DashboardConstants.OWN_ACCOUNT);
    }

    @Test(description = "Validate retrieval of payments done in vishwa dashboard | 18", dataProvider = "DashboardRVTPaymentPopup", dataProviderClass = DataProviders.DashboardDataProvider.class)
    public void validateRVTPaymentRetrieval(String toAccount) {
        dashboardPage.validateRVTTransferRetrievalOfPayment(DashboardConstants.CURRENCY_VALUES, DashboardConstants.STATUS_PAYMENT_SUCCESS, toAccount);
    }

    @Test(description = "Validate retrieval of transfers done in vishwa mobile cash | 19")
    public void validateRVTMobileCashRetrieval() {
        dashboardPage.validateRVTTransferRetrievalOfMobileCash(DashboardConstants.CURRENCY_VALUES, DashboardConstants.STATUS_PAYMENT_SUCCESS);
    }

    @Test(description = "Validate the maximum limit of fav billers as 9 per page in Dashboard | 22")
    public void validateMarkedBillersAsFavouriteIsVisibleInBillerWidget() {
        dashboardPage.validateMarkedBillersAsFavouriteIsVisibleInBillerWidget();
    }

    @Test(description = "Validate the availability of favourite biller in dashboard | 23")
    public void validateAvailabilityOfFavouriteBiller() {
        dashboardPage.validateFavouriteBillerWidget();
    }

    @Test(description = "Validate the re-initiation of transactions from favourite biller in dashboard | 24")
    public void validateReInitiationOfTransactionsFromFavouriteBiller() {
        savedPayeesPage.validateFBillerPopup(dashboardPage.getFBWidgetFirstRecordDetails());
    }

    @Test(description = "Validate the display of favourite payee in dashboard | 27")
    public void validateAvailabilityOfFavouritePayee() {
        dashboardPage.validateFavouritePayeeWidget();
    }

    @Test(description = "Validate the re-initiation of transactions from favourite payee in dashboard | 29")
    public void validateReInitiationOfTransactionsFromFavouritePayee() {
        savedPayeesPage.validateQFTPopup(dashboardPage.getFPWidgetFirstRecordDetails());
    }

    @Test(description = "Validate the add new fav payee from the saved payees list | 30")
    public void validateAddNewPayeeFromFavouritePayee() {
        dashboardPage.navigateToAddFavouritePayee();
        savedPayeesPage.addNewFavouritePayee();
    }

    @Test(description = "Validate that availability of msg/ads set by bank admins in the dashboard | 34")
    public void validateMsgOrAdvertisements() {
        dashboardPage.validateMessagesAndAdvertisements();
    }

    @Test(description = "Validate the functionality of options in top bar in dashboard 1.FCalandar 2.Msg 3. Notification 4.Profile | 39")
    public void validateTopBarIconsInDashboard() {
        dashboardPage.validateFunctionalityOfTopBarIconsInDashboard(DashboardConstants.OPTION_SETTINGS, DashboardConstants.BUTTON_LOGOUT_TEXT, LoginConstants.OTP, DashboardConstants.CONFIRM_LOGOUT_MSG, DashboardConstants.BUTTON_TEXT_BACK);
    }

    @Test(description = "Validate the maximum limit of fav payee as 9 per page in Dashboard AND Marked payees as fav should be visible in dashboard  | 25 & 26")
    public void validateMarkedPayeesAsFavouriteIsVisibleInPayeeWidget() {
        dashboardPage.validateMarkedPayeesAsFavouriteIsVisibleInPayeeWidget();
    }

    @Test(description = "Validate the functionality of display options in top bar in dashboard  | 38")
    public void validateTopBarFunctions() {
        dashboardPage.validateTopBarFunctions(DashboardConstants.DASHBOARD, DashboardConstants.MY_ACCOUNT, DashboardConstants.MANAGE_SCHEDULE, DashboardConstants.OPTION_MY_ACCOUNTS, DashboardConstants.OPTION_MANAGE_SCHEDULES, DashboardConstants.SCHEDULE_MANAGEMENT);
    }

    @Test(description = "Validate the 6 key points in accounts | 36")
    public void validateKeyPointsInAccounts() {
        dashboardPage.validateAllAccountsAtDashboard(DashboardConstants.CURRENCY_VALUES, DashboardConstants.STATUS_VALUES);
    }

    @Test(description = "Validate the 6 key points in deposits | 37")
    public void validateKeyPointsInDeposits() {
        dashboardPage.validateAllFDAccountsAtDashboard(DashboardConstants.CURRENCY_VALUES);
    }

    @Test(description = "Validate the 6 key points in loan | 40")
    public void validateKeyPointsInLoans() {
        dashboardPage.validateAllLoanAccountsAtDashboard(DashboardConstants.CURRENCY_VALUES);
    }

    @Test(description = "Validate the downloaded transaction record of transfers, payments, mobile cash | 14")
    public void validateDownloadedRecord() {

        dashboardPage.validateRVTDownloadedRecordTransfer(dowloadLocation);
        dashboardPage.validateRVTDownloadedRecordPayment(dowloadLocation);
        dashboardPage.validateRVTDownloadedRecordMobileCash(dowloadLocation);
    }

    @Test(description = "Validate the Account NickName and amount section empty 41,42 ")
    public void validateAccountNickName() {
        dashboardPage.validateAccountNickName();
        dashboardPage.PaymentPopUpEmpty();
    }

    @Test(dataProvider = "LoginDataAlternateThirteen", description = "Pre-Requisite :: Login to the Sampath vishwa application as alternate user", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void logIntoDahsboardAlternateUserOne(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {

        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
//        Uncomment below once popup feature is deployed
//        dashboardPage.closeAlertPopup();
        dashboardPage.validateTheTitle();
    }

    @Test(description = "Validate the visibility of total values LKR  | 31", dataProvider = "DashboardAccountPortfolio", dataProviderClass = DataProviders.DashboardDataProvider.class)
    public void validatePortfolioioFunctions(String imgLocation, String userName, String threshold) {
        dashboardPage.validateAccountPortfolio(imgLocation, userName, threshold, DashboardConstants.CURRENCY_VALUES);
    }

    @Test(description = "Validate the 6 key points in all current accounts | 2")
    public void validateKeyPointsInCurrentAccount() {
        dashboardPage.validateAllCurrentAccountsAtDashboard(DashboardConstants.CURRENCY_VALUES, DashboardConstants.STATUS_VALUES);
    }

    @Test(dataProvider = "LoginDataAlternateNine", description = "Pre-Requisite :: Login to the Sampath vishwa application as alternate user", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void logIntoDahsboardAlternateUserNine(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {


        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
//        Uncomment below once popup feature is deployed
//        dashboardPage.closeAlertPopup();
        dashboardPage.validateTheTitle();
    }

    @Test(description = "Validate the 6 key points in Credit Card | ")
    public void validateKeyPointsInCredit() {
        dashboardPage.validateAllCreditCardAtDashboard(DashboardConstants.CURRENCY_VALUES);
    }

    //    -------- WIP
    @Test(description = "Validate that settings option is available on the user info dropdown menu at dashboard")
    public void validateSettingsOption() {
        dashboardPage.validateSettingsOption(DashboardConstants.OPTION_SETTINGS);
    }


    @AfterMethod(description = "Rollback to dashboard")
    public void rollBackToDashboard() {
        dashboardPage.navigateBackToDashboard();
    }
}
