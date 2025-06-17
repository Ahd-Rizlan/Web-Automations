package gui.test;

import data.DataProviders;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utils.constants.BillerConstants;
import utils.constants.LoginConstants;


public class AcknowledgementMsgTest extends BaseTest {

    @Test(priority = 1, dataProvider = "LoginDataAlternateTwo", description = "Pre-Requisite :: Login to the Sampath vishwa application as alternate user two", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void logIntoDahsboardAlternateUserTwo(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {

        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        dashboardPage.validateTheTitle();
    }

    @Test(priority = 2, dataProvider = "AcknowledgementMessagesData", description = "Acknowledgement message| 1,2,3,4,5,6,7", dataProviderClass = DataProviders.AcknowledgementMessagesDataProvider.class)
    public void validateAcknowledgementMsg(String category, String billerName, String paymentUsing, String transferMode, String fromAccount, String amount, String mobileNo, String ackMsg) {
        dashboardPage.selectQuickActions(BillerConstants.BUTTON_BILL_PAYMENT);
        billPaymentPage.selectCategory(category,billerName);
        billPaymentPage.initiateBillPaymentsUsingForexAndValidate(BillerConstants.OTP,category,billerName,paymentUsing,transferMode,amount,mobileNo,fromAccount,ackMsg,"","","","","","","","","","","",BillerConstants.CONSTANTS_MAP);

    }

    @AfterMethod(description = "Rollback to dashboard")
    public void rollBackToDashboard() {
        dashboardPage.navigateBackToDashboard();
        extent.flush();
    }

}

