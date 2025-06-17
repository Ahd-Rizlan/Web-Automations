package gui.test;

import data.DataProviders;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utils.constants.BillerConstants;
import utils.constants.LoginConstants;


public class LPOPPTest extends BaseTest {

    @Test(priority = 1, dataProvider = "LoginDataAlternateTwo", description = "Pre-Requisite :: Login to the Sampath vishwa application as alternate user two", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void logIntoDahsboardAlternateUserTwo(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {

        driver.get(url);
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        dashboardPage.validateTheTitle();
        dashboardPage.captureBaseWindowHandle();

    }

    @Test(priority = 2,  dataProvider = "LPOPPData", description = "LPOPP Payments| 1,2", dataProviderClass = DataProviders.LPOPPDataProvider.class)
    public void validateLPOPP(String category,String billerName,String paymentUsing,String expectedPart) {

        dashboardPage.selectQuickActions(BillerConstants.BUTTON_BILL_PAYMENT);
        billPaymentPage.selectCategory(category,billerName);
        billPaymentPage.initiateBillPaymentsForLPOPAndValidate(BillerConstants.OTP,category,billerName,paymentUsing,BillerConstants.CONSTANTS_MAP);
        dashboardPage.captureBaseWindowHandle();
        vishwaRetailAdminLoginPage.navigateToTab(1);
        billPaymentPage.validateURL(expectedPart);
        billPaymentPage.closeBrowser();
    }

    @AfterMethod(description = "Rollback to dashboard")
    public void rollBackToDashboard() {
//        dashboardPage.navigateBackToDashboard();
        extent.flush();
    }

}

