package gui.test;

import data.DataProviders;
import org.testng.annotations.Test;
import utils.constants.LoginConstants;

public class WebCardDetailedViewTest extends BaseTest {

    @Test(priority = 1, dataProvider = "LoginDataAlternateTen", description = "Pre-Requisite :: Login to the Sampath vishwa application", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void logIntoDahsboard(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        dashboardPage.validateTheTitle();
    }

    @Test(priority = 2, dataProvider = "WebCardDetailViewData", description = "validate card details tables card topup and cvv  TC 2,3,4,5,6,7,8", dataProviderClass = DataProviders.WebCardDetailViewDataProvider.class)
    public void validateWebCardDetailsAndSettlement (String payingAccountNumberUSD,String paymentAmountUSD,String payingAccountNumber,String paymentAmount,String successMsg,String acknowlagmentmessage,String successMsgPayment, String successMsgCCV){
        webCardDetailedViewPage.NavogatetoWebCardDetailsPage();
        webCardDetailedViewPage.validateCardDetails();
        webCardDetailedViewPage.validateBillingSettlementSection();
        webCardDetailedViewPage.validateWebCardTopUp(payingAccountNumberUSD,paymentAmountUSD);
        webCardDetailedViewPage.enterOTPAndContinueWebCardPage(successMsg,LoginConstants.OTP,acknowlagmentmessage,successMsgPayment);
        webCardDetailedViewPage.validateWebCardTopUp(payingAccountNumber,paymentAmount);
        webCardDetailedViewPage.enterOTPAndContinueWebCardPage(successMsg,LoginConstants.OTP,acknowlagmentmessage,successMsgPayment);
        webCardDetailedViewPage.validateCVV(LoginConstants.OTP,successMsgCCV);

    }
    @Test(priority = 3, dataProvider = "WebCardBlockData", description = "validate card block TC 9", dataProviderClass = DataProviders.WebCardDetailViewDataProvider.class)
    public void validateWebCardBlockUnBlock (String successMsgForBlocking,String emailSentSuccessMsg){
        webCardDetailedViewPage.ValidateCardBlockUnblock(LoginConstants.OTP,successMsgForBlocking,emailSentSuccessMsg);

    }
    @Test(priority = 4, dataProvider = "LoginDataSavingsFD", description = "Pre-Requisite :: Login to the Sampath vishwa application", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void logIntoDahsboardToApplyWebCard(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {
        driver.get(url);
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        dashboardPage.validateTheTitle();
    }

    @Test(priority = 5,dataProvider = "WebCardInfoData",description = "validate Apply Webcard conditions bug id - 1276 U1 ", dataProviderClass = DataProviders.WebCardDetailViewDataProvider.class)
    public void validateApplyWebcard (String residenceLable,String expectedInfoRaw){
        dashboardPage.selectQuickActions("web card");
        webCardDetailedViewPage.NavogatetoWebCardDetailsPageFromDashBoard(residenceLable,expectedInfoRaw);
    }
}
