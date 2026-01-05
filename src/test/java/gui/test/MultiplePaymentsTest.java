package gui.test;

import data.DataProviders;
import org.testng.annotations.Test;
import utils.constants.BillerConstants;
import utils.constants.LoginConstants;

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

    @Test(priority = 3,description = "Validate saved Payee contents")
    public void ValidatePageContents(){
        multiplePaymentsPage.navigateToPayeeAndBillers();
        multiplePaymentsPage.validateSavedPayeePage();
    }

}
