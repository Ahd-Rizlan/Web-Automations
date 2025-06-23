package gui.test;

import data.DataProviders;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.OldVishwaPage;
import utils.constants.BillerConstants;
import utils.constants.LoginConstants;
import utils.constants.OldVishwaConstants;


public class OldVishwaInquiriesTest extends BaseTest {

    @Test(priority = 1, dataProvider = "LoginDataAlternateSix", description = "Pre-Requisite :: Login to the Sampath vishwa application as alternate user two", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void logIntoDahsboardAlternateUserSix(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {

        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        dashboardPage.validateTheTitle();
    }

    @Test(priority = 2, description = "Old vishwa inquiries| 1,2")
    public void validateAcknowledgementMsg() {
        dashboardPage.navigateToMessages();
        OldVishwaPage.navigateToOldVishwaMailAndValidateInboxAndSentMsg();
    }

    @Test(priority = 3, dataProvider = "InboxMessagesData",  description = "Old vishwa inquiries| 3",  dataProviderClass = DataProviders.OldVishwaInquiryDataProvider.class)
    public void validateInboxMessages(String subjectHeader) {
        dashboardPage.navigateToMessages();
        OldVishwaPage.navigateToOldVishwaMailAndValidateInboxMessage(subjectHeader);
    }
    @Test(priority = 4, description = "Old vishwa inquiries| 12")
    public void validatePaymentsTableAndData() {
        dashboardPage.selectQuickActions(BillerConstants.BUTTON_SEND_MONEY);
        billPaymentPage.selectHeaderTab(OldVishwaConstants.KEYWORD_TRANSACTION_HISTORY);
        OldVishwaPage.navigatePayeeAndValidateOldVishwaTransactions();
    }
    @Test(priority = 5, description = "Old vishwa inquiries| 13")
    public void validateBillPaymentTableAndData() {
        dashboardPage.selectQuickActions(BillerConstants.BUTTON_BILL_PAYMENT);
        billPaymentPage.selectHeaderTab(OldVishwaConstants.KEYWORD_BILL_PAYMENT_HISTORY);
        OldVishwaPage.navigateBillersAndValidateOldVishwaTransactions();
    }
    @AfterMethod(description = "Rollback to dashboard")
    public void rollBackToDashboard() {
        dashboardPage.navigateBackToDashboard();
        extent.flush();
    }

}

