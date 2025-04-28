package gui.test;

import com.aventstack.extentreports.ExtentTest;
import data.DataProviders;
import utils.constants.BillerConstants;
import utils.constants.DashboardConstants;
import utils.constants.LoginConstants;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utils.CommonUtils;
import utils.Drivers;
import utils.report.TestContext;

import java.lang.reflect.Method;

public class PaymentsAndBillerMaintenanceTest extends Drivers {

    DashboardPage dashboardPage;
    LoginPage loginPage;
    OTPPage otpPage;
    ExtentTest exTest;
    BillPaymentPage billPaymentPage;
    MyAccountsPage myAccountsPage;

    @BeforeMethod
    private void OpenURL(Method method) {
        String methodName = method.getName();
        exTest = extent.createTest(methodName);
        TestContext.setExtentTest(exTest);
        dashboardPage = new DashboardPage(driver);
        loginPage = new LoginPage(driver);
        otpPage = new OTPPage(driver);
        billPaymentPage = new BillPaymentPage(driver);
        myAccountsPage = new MyAccountsPage(driver);
    }

    @Test(priority = 1, dataProvider = "LoginDataAlternateTwo", description = "Pre-Requisite :: Login to the Sampath vishwa application", dataProviderClass = DataProviders.LoginDataProvider.class)
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
    @Test(priority = 2, description = "Validate the availability of favourite icon | 13, 49")
    public void validateFavouriteIcons() {
        dashboardPage.selectQuickActions(BillerConstants.BUTTON_BILL_PAYMENT);
        billPaymentPage.validateAddToFavColumn(BillerConstants.BUTTON_TEXT_SAVED_BILLERS);
        billPaymentPage.validateFavouriteBillerList(BillerConstants.BUTTON_NEW_PAYMENT,BillerConstants.BUTTON_TEXT_SAVED_BILLERS);
    }
    @Test(priority = 3, description = "Validate the saved biller filter functionality | 24,25")
    public void validateSavedBillerFilterOptions() {
        dashboardPage.selectQuickActions(BillerConstants.BUTTON_BILL_PAYMENT);
        billPaymentPage.validateBillPaymentHistoryFilterOptions(BillerConstants.BUTTON_TEXT_BILL_PAYMENT_HISTORY,dowloadLocation);
        billPaymentPage.validateReinitiationOfTransactionFromHistory(BillerConstants.KW_DIALOG_MOBILE,BillerConstants.OTP,BillerConstants.CONSTANTS_MAP.get("KW_AMOUNT"),BillerConstants.CONSTANTS_MAP.get("KW_MOBILE_NUMBER_WITHOUT"));
    }

    @Test(priority = 4, description = "Validate search option is available to categories | 2")
    public void validateBillPaymentSearchCategories() {
        dashboardPage.selectQuickActions("Bill Payment");
        billPaymentPage.searchBillPaymentCategories("Logistics");
    }

    @Test(priority = 5, dataProvider = "LoginDataAlternateTwo", description = "Pre-Requisite :: Login to the Sampath vishwa application as alternate user two", dataProviderClass = DataProviders.LoginDataProvider.class)
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

    @Test(priority = 6, dataProvider = "BillPaymentsData", description = "Bill payments trough accounts | 41,42,44,46,47,48,49,51", dataProviderClass = DataProviders.BillersDataProvider.class)
    public void validateBillPaymentsThroughAccounts(String category, String billerName, String paymentUsing, String transferMode, String fromAccount, String amount, String mobileNo, String accountNumber, String errorMsgOne, String errorMsgTwo, String errorMsgThree, String errorMsgFour, String errorMsgFive, String errorMsgSix, String nicNo, String name, String policyNumber, String admissionNumber, String classID, String purpose, String date, String code, String referenceOrReservationNo, String employeeID, String branch, String email) {

        dashboardPage.obtainAllAccountTypes(DashboardConstants.STATUS_PRIMARY);
        dashboardPage.selectQuickActions(BillerConstants.BUTTON_BILL_PAYMENT);
        billPaymentPage.selectCategory(category,billerName);
        String[] billerData = billPaymentPage.initiateBillPaymentsViaCategoriesSaveTemplateAndValidate(BillerConstants.OTP,dowloadLocation,category,billerName,paymentUsing,transferMode, amount,mobileNo,accountNumber,"Auto_"+CommonUtils.randomAlphaNumeric(5),errorMsgOne,errorMsgTwo,errorMsgThree,errorMsgFour,errorMsgFive,errorMsgSix,nicNo,name,policyNumber,admissionNumber,classID,purpose,date,code,referenceOrReservationNo, branch,email,BillerConstants.CONSTANTS_MAP);
        dashboardPage.navigateBackToDashboard();
        dashboardPage.navigateToMainMenu(BillerConstants.BUTTON_MY_ACCOUNTS);
        myAccountsPage.selectTab(BillerConstants.BUTTON_ACCOUNTS);
        myAccountsPage.searchAndSelectAccountList(billerData[0]);
        myAccountsPage.searchReferenceInAccountsHistory(billerData[1]);

    }

    @Test(priority = 7, dataProvider = "LoginDataAlternateThree", description = "Pre-Requisite :: Login to the Sampath vishwa application as alternate user two", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void logIntoDahsboardAlternateUserThree(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {

        driver.get(url);
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
//        Uncomment below once popup feature is deployed
//        dashboardPage.closeAlertPopup();
        dashboardPage.validateTheTitle();
    }
    @Test(priority =8, description = "Validate no saved billers are marked as fav | 14")
    public void validateNoSavedBillersAreMarkedAsFav() {
        dashboardPage.selectQuickActions("Bill Payment");
        billPaymentPage.selectHeaderTab("Saved Billers");
        billPaymentPage.validateNoSavedBillersAreMarkedAsFavourites();
    }

    @AfterMethod(description = "Rollback to dashboard")
    public void rollBackToDashboard() {
        dashboardPage.navigateBackToDashboard();

    }

}

