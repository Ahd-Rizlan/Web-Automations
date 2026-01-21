package gui.test;

import data.DataProviders;
import org.testng.annotations.Test;
import utils.constants.CreditCardConstants;
import utils.constants.LoginConstants;

public class CreditCardDetailedViewTest extends BaseTest {

    @Test(priority = 1, dataProvider = "LoginDataAlternateNine", description = "Pre-Requisite :: Login to the Sampath vishwa application", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void logIntoDahsboard(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        dashboardPage.validateTheTitle();
    }

    @Test(priority = 2, dataProvider = "CreditCardDetailViewData", description = "1,2,3,4,5,6,7,8,10,11,12Acknowledgement message TC 445,446,447, uplift - 1,2,3,4,5,6,13,14,15,16,17,19,20", dataProviderClass = DataProviders.CreditCardDetailViewDataProvider.class)
    public void validateCreditCardDetailsAndSettlement (String payingAccountNumberUSD,String payingAccountNumber,String InsufficientamountErrUSD,String InsufficientamountErr,String ZeroAmountUSD,String ZeroAmount,String errorMsgUSD,String errorMsg,String paymentAmountUSD,String paymentAmount,String acknowlagmentmessage,String successMsg,String successMsgForBlocking,String emailSentSuccessMsg){
        creditCardDetailedViewPage.NavogatetoCreditCardDetailsPage();
        creditCardDetailedViewPage.validateCardDetails();
        creditCardDetailedViewPage.validateTransactionTables();
        creditCardDetailedViewPage.validateCreditCardSettlement(payingAccountNumberUSD, InsufficientamountErrUSD,ZeroAmountUSD,errorMsgUSD,paymentAmountUSD);
        creditCardDetailedViewPage.enterOTPAndContinueSettingsPage(LoginConstants.OTP,acknowlagmentmessage, successMsg);
        creditCardDetailedViewPage.validateDeductAmount(payingAccountNumberUSD);
        creditCardDetailedViewPage.validateCreditCardSettlement(payingAccountNumber, InsufficientamountErr,ZeroAmount,errorMsg,paymentAmount);
        creditCardDetailedViewPage.enterOTPAndContinueSettingsPage(LoginConstants.OTP,acknowlagmentmessage, successMsg);
        creditCardDetailedViewPage.validateDeductAmount(payingAccountNumber);
        creditCardDetailedViewPage.ValidateCardBlockUnblock(LoginConstants.OTP,successMsgForBlocking,emailSentSuccessMsg);
        creditCardDetailedViewPage.validateBillingByYearAndMonth(); //bug id -1166
        creditCardDetailedViewPage.validateStatementSection(dowloadLocation);
        creditCardDetailedViewPage.ValidatingRedeem();
        dashboardPage.captureBaseWindowHandle();
        dashboardPage.navigateToTab(1,false);
        creditCardDetailedViewPage.validateURL(CreditCardConstants.EXPECTED_PART);
    }
}
