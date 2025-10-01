package gui.test;

import data.DataProviders;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utils.constants.BillerConstants;
import utils.constants.LoginConstants;

import static utils.constants.MultipleBillersConstants.*;


public class MultipleBillersTest extends BaseTest {


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

    @Test(priority = 2, description = "Access Saved Billers Page")
    public void AccessSavedBillersPage() {
        dashboardPage.selectQuickActions(BillerConstants.BUTTON_BILL_PAYMENT);
        multipleBillersPage.validateDataAvailablity(BillerConstants.BUTTON_TEXT_SAVED_BILLERS);
    }

    @Test(priority = 2, description = "Access Saved Billers Page Through NavBar")
    public void AccessSavedBillersPageThroughNavBar() {
        multipleBillersPage.navigateToPayeeAndBillers();
        multipleBillersPage.validateDataAvailablity(BillerConstants.BUTTON_TEXT_SAVED_BILLERS);

    }

    @Test(priority = 3,description = "Validate saved Payee contents")
    public void ValidatePageContents(){
        multipleBillersPage.navigateToPayeeAndBillers();
        multipleBillersPage.validateSavedBillersPage();
    }
    @Test(priority = 4,description = "Pay for a single Biller")
    public void PaySingleBillers(){
        multipleBillersPage.navigateToPayeeAndBillers();
        multipleBillersPage.selectOneSavedBillerRecord(PAGINATION_LIMIT);
        multipleBillersPage.validateAndPayBillForSingleBiller();
        multipleBillersPage.clearUsedIndexes();
    }

    @Test(priority = 5,description = "Pay for Multiple Billers")
    public void PayMultipleBillers(){
        multipleBillersPage.navigateToPayeeAndBillers();
        multipleBillersPage.SelectMultipleSavedBillers(MAX_SELECTABLE_SAVED_BLLERS);
        multipleBillersPage.clearUsedIndexes();
    
    }


    @AfterMethod(description = "Rollback to dashboard")
    public void NavigateBackToDashboard() {
        multipleBillersPage.navigateBackToDashboard();
    }
}