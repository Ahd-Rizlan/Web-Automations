package gui.test;

import com.aventstack.extentreports.ExtentTest;
import data.DataProviders;
import gui.utils.constants.BillerConstants;
import gui.utils.constants.DashboardConstants;
import gui.utils.constants.LoginConstants;
import gui.utils.constants.TransactionConstants;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.MakeTransactionsPage;
import pages.OTPPage;
import utils.CommonUtils;
import utils.Drivers;
import utils.report.TestContext;

import java.lang.reflect.Method;

public class MakeTransactionsTest extends Drivers {

    DashboardPage dashboardPage;
    LoginPage loginPage;
    OTPPage otpPage;
    ExtentTest exTest;
    MakeTransactionsPage makeTransactionsPage;

    @BeforeMethod
    private void OpenURL(Method method) {
        String methodName = method.getName();
        exTest = extent.createTest(methodName);
        TestContext.setExtentTest(exTest);
        dashboardPage = new DashboardPage(driver);
        loginPage = new LoginPage(driver);
        otpPage = new OTPPage(driver);
        makeTransactionsPage = new MakeTransactionsPage(driver);
    }

    @Test(priority = 1, dataProvider = "LoginDataAlternateTwo", description = "Pre-Requisite :: Login to the Sampath vishwa application as alternate user two", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void logIntoDahsboardAlternateUserOne(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {

        driver.get(url);
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
//        Uncomment below once popup feature is deployed
//        dashboardPage.closeAlertPopup();
        dashboardPage.validateTheTitle();
    }


    @Test(priority = 2, dataProvider = "OwnAccountTransferData", dataProviderClass = DataProviders.TransfersPageDataProvider.class, description = "Payments to own accounts | 41,42,44,46,47,48,49,51")
    public void validateOwnAccountTransfers(String errorMsg1, String errorMsg2, String minAmount, String maxAmount, String minAmountMsg, String maxAmountMsg, String toAccount, String amount, String transferMode ,String noAmount, String errMinimumTransferAmount) {
        //Obtain account and send for validations
        dashboardPage.obtainAllAccountTypes(DashboardConstants.STATUS_PRIMARY);
        dashboardPage.selectQuickActions(BillerConstants.BUTTON_SEND_MONEY);

        //Select appropriate tab
        makeTransactionsPage.selectHeaderTab(TransactionConstants.TAB_NAME_SEND_MONEY);
        makeTransactionsPage.selectTabUnderSendMoney(TransactionConstants.TAB_NAME_OWN_ACCOUNT);

        makeTransactionsPage.makeOwnAccountTransactions(errorMsg1, errorMsg2, minAmount, maxAmount, minAmountMsg, maxAmountMsg, toAccount, amount, CommonUtils.randomAlphaNumeric(20), CommonUtils.randomAlphaNumeric(20), transferMode, TransactionConstants.CONSTANTS_MAP, TransactionConstants.CURRENCY_VALUES[0], LoginConstants.OTP,noAmount,errMinimumTransferAmount);
    }


    @Test(priority = 3, description = "Payments to other accounts | 41,42,44,46,47,48,49,51", dataProvider = "OtherAccountTransfersData", dataProviderClass = DataProviders.TransfersPageDataProvider.class)
    public void validateOtherAccountTransfers(
            String errorMsgInsufficientFunds, String minAmountEntry, String maxAmountEntry,
            String minAmountMsg, String maxAmountMsg, String toAccount, String amount,
            String transferMode, String receiverName, String purpose,
            String bankName, String actualTransactionAmount) {
        //Obtain account and send for validations
        dashboardPage.obtainAllAccountTypes(DashboardConstants.STATUS_PRIMARY);
        dashboardPage.selectQuickActions(BillerConstants.BUTTON_SEND_MONEY);

        //Select appropriate tab
        makeTransactionsPage.selectHeaderTab(TransactionConstants.TAB_NAME_SEND_MONEY);
        makeTransactionsPage.selectTabUnderSendMoney(TransactionConstants.TAB_NAME_OTHER_ACCOUNT);

        makeTransactionsPage.makeOtherAccountTransactions(
                errorMsgInsufficientFunds, minAmountEntry, maxAmountEntry,
                minAmountMsg, maxAmountMsg, toAccount, amount,
                CommonUtils.randomAlphaNumeric(20), CommonUtils.randomAlphaNumeric(20), transferMode,
                TransactionConstants.CONSTANTS_MAP, TransactionConstants.CURRENCY_VALUES[0],
                LoginConstants.OTP, receiverName, purpose, bankName, actualTransactionAmount
        );
    }

    @Test(priority = 4, description = "Payments to other credit cards | 24,27,28,30,31,32,33,34,", dataProvider = "OtherCCardTransferData", dataProviderClass = DataProviders.TransfersPageDataProvider.class)
    public void validateOtherCreditCardTransfers(String errCreditCardNumberRequired, String errReEnterCreditCardNumberRequired, String errNameOnCardRequired, String errBankRequired, String errAmountRequired,
                                                 String errPurposeRequired, String errBeneficiaryRemarkRequired,
                                                 String creditCardNumber, String reEnterCardNumber, String nameOnCard,
                                                 String bankName, String branchName, String mdtAmount, String purpose, String transferMode, String errorMessageMaxTransactionLimit,
                                                 String amount, String minAmount,
                                                 String errInsufficientfunds, String errMinimumTransferAmount) {
        //Obtain account and send for validations
        dashboardPage.obtainAllAccountTypes(DashboardConstants.STATUS_PRIMARY);
        dashboardPage.selectQuickActions(BillerConstants.BUTTON_SEND_MONEY);

        //Select appropriate tab
        makeTransactionsPage.selectHeaderTab(TransactionConstants.TAB_NAME_SEND_MONEY);
        makeTransactionsPage.selectTabUnderSendMoney(TransactionConstants.TAB_NAME_OTHER_CREDIT_CARDS);

        makeTransactionsPage.makeOtherCreditCardTransactions(TransactionConstants.CONSTANTS_MAP,
                errCreditCardNumberRequired, errReEnterCreditCardNumberRequired,
                errNameOnCardRequired, errBankRequired, errAmountRequired,
                errPurposeRequired, errBeneficiaryRemarkRequired,
                creditCardNumber, reEnterCardNumber, nameOnCard,
                bankName, branchName, mdtAmount, purpose,
                CommonUtils.randomAlphaNumeric(20), CommonUtils.randomAlphaNumeric(20),
                transferMode, errorMessageMaxTransactionLimit, amount,
                TransactionConstants.AUTO + CommonUtils.randomAlphaNumeric(5), LoginConstants.OTP,
                TransactionConstants.TAB_NAME_SAVED_PAYEE, minAmount, errInsufficientfunds, errMinimumTransferAmount,
                TransactionConstants.CURRENCY_VALUES[0]);

    }

    @AfterMethod(description = "Rollback to dashboard")
    public void rollBackToDashboard() {
        dashboardPage.navigateBackToDashboard();

    }
}
