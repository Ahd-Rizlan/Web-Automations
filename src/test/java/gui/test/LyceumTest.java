package gui.test;

import data.DataProviders;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utils.constants.BillerConstants;
import utils.constants.LoginConstants;


public class LyceumTest extends BaseTest {

    @Test(priority = 1, dataProvider = "LoginDataAlternateTwo", description = "Pre-Requisite :: Login to the Sampath vishwa application as alternate user two", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void logIntoDahsboardAlternateUserTwo(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {

        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        dashboardPage.validateTheTitle();

    }

    @Test(priority = 2, dataProvider = "LyceumData", description = "Lyceum and Leaf Payments| 1,2,4,5,", dataProviderClass = DataProviders.LyceumDataProvider.class)
    public void validateLyceum(String category, String billerName, String billerNameTwo, String studentNo, String successMsgSecondPage, String accountNo) {

        dashboardPage.selectQuickActions(BillerConstants.BUTTON_BILL_PAYMENT);
        billPaymentPage.selectCategory(category, billerName);
        billPaymentPage.initiateBillPaymentsForLyceumAndValidate(accountNo, studentNo, successMsgSecondPage, BillerConstants.CONSTANTS_MAP);
        dashboardPage.navigateBackToDashboard();
        dashboardPage.selectQuickActions(BillerConstants.BUTTON_BILL_PAYMENT);
        billPaymentPage.selectCategory(category, billerNameTwo);
        billPaymentPage.initiateBillPaymentsForLyceumAndValidate(accountNo, studentNo, successMsgSecondPage, BillerConstants.CONSTANTS_MAP);

    }

    @AfterMethod(description = "Rollback to dashboard")
    public void rollBackToDashboard() {
        extent.flush();
    }

}

