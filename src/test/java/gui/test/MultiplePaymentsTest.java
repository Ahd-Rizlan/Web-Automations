package gui.test;

import data.DataProviders;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utils.constants.BillerConstants;
import utils.constants.LoginConstants;

import static utils.constants.MultipleBillersConstants.MAX_SELECTABLE_SAVED_BLLERS;
import static utils.constants.MultipleBillersConstants.OTP;
import static utils.constants.MultiplePaymentsConstants.MAX_SELECTABLE_SAVED_PAYEES;

public class MultiplePaymentsTest extends BaseTest{


    @Test(priority = 1, dataProvider = "LoginDataAlternateTwo", description = "Pre-Requisite :: Login to the Sampath vishwa application", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void logIntoDahsboard(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
//        Uncomment below once popup feature is deployed
//        dashboardPage.closeAlertPopup();
        dashboardPage.validateTheTitle();
    }


    @Test(priority = 2, description = "Access Saved Payees Page")
    public void AccessSavedPayeesPage() {
        dashboardPage.selectQuickActions(BillerConstants.BUTTON_SEND_MONEY);
        multiplePaymentsPage.validateDataAvailablity(BillerConstants.BUTTON_TEXT_SAVED_PAYEES);
    }


    @Test(priority = 2, description = "Access Saved Payees Page Through NavBar")
    public void AccessSavedPayeesPageThroughNavBar() {
        multiplePaymentsPage.navigateToPayeeAndBillers();
        multiplePaymentsPage.validateDataAvailablity(BillerConstants.BUTTON_TEXT_SAVED_PAYEES);
    }


    @Test(priority = 3,description = "Validate saved Payee Pagination")
    public void ValidatePagination(){
        multiplePaymentsPage.navigateToPayeeAndBillers();
        multiplePaymentsPage.validateSavedPayeePage();
    }

    @Test(priority = 4,description = "Validate saved Payee Tabs and Contents")
    public void ValidatePageTabsAndRecords(){
        multiplePaymentsPage.navigateToPayeeAndBillers();
        multiplePaymentsPage.validateTransferTypeTabsAndContents();

    }

    @Test(priority = 4,description = "Validate saved Payee Tabs and Contents")
    public void ValidateModelPagePopupUponSelectingMultipleRecords(){
        multiplePaymentsPage.navigateToPayeeAndBillers();

        multiplePaymentsPage.SelectMultipleSavedBillers(MAX_SELECTABLE_SAVED_BLLERS);
        multiplePaymentsPage.clickPayNowButton();
        multiplePaymentsPage.ValidatePayBillModelPageForMultipleSelectedBiller();
//        multiplePaymentsPage.validateMultipleBillerOTPConfirmationPage();
//        multiplePaymentsPage.enterOTPAndContinue(OTP);
//        multiplePaymentsPage.validateMultipleBillerPaymentSuccessPage();
//        multiplePaymentsPage.clearUsedIndexes();


    }



    @AfterMethod(description = "Rollback to dashboard")
    public void NavigateBackToDashboard() {
        multiplePaymentsPage.navigateBackToDashboard();
    }
}
