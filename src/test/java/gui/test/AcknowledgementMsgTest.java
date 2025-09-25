package gui.test;

import data.DataProviders;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utils.CommonUtils;
import utils.constants.BillerConstants;
import utils.constants.DashboardConstants;
import utils.constants.LoginConstants;
import utils.constants.TransactionConstants;


public class AcknowledgementMsgTest extends BaseTest {

    @Test(priority = 1, dataProvider = "LoginDataAlternateEleven", description = "Pre-Requisite :: Login to the Sampath vishwa application as alternate user two", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void logIntoDahsboardAlternateUserEleven(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {

        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        dashboardPage.validateTheTitle();
    }

    @Test(priority = 2, description = "Get the account types for transfer")
    public void obtainAccountTypes() {
        dashboardPage.obtainAllAccountTypes(DashboardConstants.STATUS_PRIMARY);
    }

    @Test(priority = 3, dataProvider = "AcknowledgementMessagesData", description = "Acknowledgement message| 1,2,3,4,5,6,7,U1,U2", dataProviderClass = DataProviders.AcknowledgementMessagesDataProvider.class)
    public void validateAcknowledgementMsg(String category, String billerName, String paymentUsing, String transferMode, String fromAccount, String amount, String mobileNo, String ackMsg, String paymentProcessingFeeMsg) {
        dashboardPage.selectQuickActions(BillerConstants.BUTTON_BILL_PAYMENT);
        billPaymentPage.selectCategory(category, billerName);
        billPaymentPage.initiateBillPaymentsUsingForexAndValidate(BillerConstants.OTP, category, billerName, paymentUsing, transferMode, amount, mobileNo, fromAccount, ackMsg, "", "", "", "", "", "", "", "", "", "", "", BillerConstants.CONSTANTS_MAP, paymentProcessingFeeMsg, BillerConstants.LKR_TEXT);

    }

    @Test(priority = 4, dataProvider = "AcknowledgementOwnAccountTransferData", dataProviderClass = DataProviders.AcknowledgementMessagesDataProvider.class, description = "Acknowledgement message | 6,7")
    public void validateOwnAccountTransfersUsingFC(String errorMsg1, String errorMsg2, String minAmount, String maxAmount, String minAmountMsg, String maxAmountMsg, String toAccount, String amount, String transferMode, String noAmount, String errMinimumTransferAmount, String fromAccount) {
        //Obtain account and send for validations
        dashboardPage.selectQuickActions(BillerConstants.BUTTON_SEND_MONEY);

        //Select appropriate tab
        makeTransactionsPage.selectHeaderTab(TransactionConstants.TAB_NAME_SEND_MONEY);
        makeTransactionsPage.selectTabUnderSendMoney(TransactionConstants.TAB_NAME_OWN_ACCOUNT);
        makeTransactionsPage.makeOwnAccountTransactionsFC(errorMsg1, errorMsg2, minAmount, maxAmount, minAmountMsg, maxAmountMsg, toAccount, amount, CommonUtils.randomAlphaNumeric(20), CommonUtils.randomAlphaNumeric(20), transferMode, TransactionConstants.CONSTANTS_MAP, TransactionConstants.CURRENCY_VALUES[1], LoginConstants.OTP, noAmount, errMinimumTransferAmount, fromAccount);
    }

    @AfterMethod(description = "Rollback to dashboard")
    public void rollBackToDashboard() {
        dashboardPage.navigateBackToDashboard();
        extent.flush();
    }

}

