package gui.test;

import data.DataProviders;
import org.testng.annotations.Test;
import utils.constants.LoginConstants;

public class CreditCardDetailedViewTest extends BaseTest {

    @Test(priority = 1, dataProvider = "LoanDetailsUserData", description = "Pre-Requisite :: Login to the Sampath vishwa application", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void logIntoDahsboard(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        dashboardPage.validateTheTitle();
    }

    @Test(priority = 2, dataProvider = "CreditCardDetailViewData", description = "#", dataProviderClass = DataProviders.CreditCardDetailViewDataProvider.class)
    public void validateCreditCardDetailsAndSettlement (String payingAccountNumber,String insufficientamountErr,String ZeroAmount,String paymentAmount,String errorMsg,String successMsg,String successMsgForBlocking,String emailSentSuccessMsg){
        creditCardDetailedViewPage.NavogatetoCreditCardDetailsPage();
        creditCardDetailedViewPage.validateCardDetails();
        creditCardDetailedViewPage.validateTransactionTables();
        creditCardDetailedViewPage.validateCreditCardSettlement(payingAccountNumber,insufficientamountErr,ZeroAmount,paymentAmount,errorMsg);
        creditCardDetailedViewPage.enterOTPAndContinueSettingsPage(LoginConstants.OTP, successMsg);
       creditCardDetailedViewPage.ValidateCardBlockUnblock(LoginConstants.OTP,successMsgForBlocking,emailSentSuccessMsg);;

    }

}
