package gui.test;

import data.DataProviders;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utils.CommonUtils;
import utils.constants.BillerConstants;
import utils.constants.DashboardConstants;
import utils.constants.LoginConstants;
import utils.constants.TransactionConstants;

public class MakeTransactionsTest extends BaseTest {


    @Test(priority = 1, dataProvider = "LoginDataAlternateTwo", description = "Pre-Requisite :: Login to the Sampath vishwa application as alternate user two", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void logIntoDahsboardAlternateUserOne(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {

        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
//        Uncomment below once popup feature is deployed
//        dashboardPage.closeAlertPopup();
        dashboardPage.validateTheTitle();
    }

    @Test(priority = 2, description = "Get the account types for transfer")
    public void obtainAccountTypes() {
        dashboardPage.obtainAllAccountTypes(DashboardConstants.STATUS_PRIMARY);
    }


    @Test(priority = 3, dataProvider = "OwnAccountTransferData", dataProviderClass = DataProviders.TransfersPageDataProvider.class, description = "Payments to own accounts | 41,42,44,46,47,48,49,51")
    public void validateOwnAccountTransfers(String errorMsg1, String errorMsg2, String minAmount, String maxAmount, String minAmountMsg, String maxAmountMsg, String toAccount, String amount, String transferMode, String noAmount, String errMinimumTransferAmount) {
        //Obtain account and send for validations
        dashboardPage.selectQuickActions(BillerConstants.BUTTON_SEND_MONEY);
        //Select appropriate tab
        makeTransactionsPage.selectHeaderTab(TransactionConstants.TAB_NAME_SEND_MONEY);
        makeTransactionsPage.selectTabUnderSendMoney(TransactionConstants.TAB_NAME_OWN_ACCOUNT);
        makeTransactionsPage.makeOwnAccountTransactions(errorMsg1, errorMsg2, minAmount, maxAmount, minAmountMsg, maxAmountMsg, toAccount, amount, CommonUtils.randomAlphaNumeric(20), CommonUtils.randomAlphaNumeric(20), transferMode, TransactionConstants.CONSTANTS_MAP, TransactionConstants.CURRENCY_VALUES[0], LoginConstants.OTP, noAmount, errMinimumTransferAmount);
    }


    @Test(priority = 4, description = "Transfer to other accounts | 41,42,44,46,47,48,49,51 ", dataProvider = "OtherAccountTransfersData", dataProviderClass = DataProviders.TransfersPageDataProvider.class)
    public void validateOtherAccountTransfers(
            String errorMsgInsufficientFunds, String minAmountEntry, String maxAmountEntry,
            String minAmountMsg, String maxAmountMsg, String toAccount, String amount,
            String transferMode, String receiverName, String purpose,
            String bankName, String branch, String actualTransactionAmount) {
        //Obtain account and send for validations
        dashboardPage.selectQuickActions(BillerConstants.BUTTON_SEND_MONEY);
        //Select appropriate tab
        makeTransactionsPage.selectHeaderTab(TransactionConstants.TAB_NAME_SEND_MONEY);
        makeTransactionsPage.selectTabUnderSendMoney(TransactionConstants.TAB_NAME_OTHER_ACCOUNT);

        makeTransactionsPage.makeOtherAccountTransactions(
                errorMsgInsufficientFunds, minAmountEntry, maxAmountEntry,
                minAmountMsg, maxAmountMsg, toAccount, amount,
                CommonUtils.randomAlphaNumeric(20), CommonUtils.randomAlphaNumeric(20), transferMode,
                TransactionConstants.CONSTANTS_MAP, TransactionConstants.CURRENCY_VALUES[0],
                LoginConstants.OTP, receiverName, purpose, bankName, branch, actualTransactionAmount
        );
    }

    @Test(priority = 5, description = "Transfer to other credit cards | 24,27,28,30,31,32,33,34, Transfers | U2", dataProvider = "OtherCCardTransferData", dataProviderClass = DataProviders.TransfersPageDataProvider.class)
    public void validateOtherCreditCardTransfers(String errCreditCardNumberRequired, String errReEnterCreditCardNumberRequired, String errNameOnCardRequired, String errBankRequired, String errAmountRequired,
                                                 String errPurposeRequired, String errBeneficiaryRemarkRequired,
                                                 String creditCardNumber, String reEnterCardNumber, String nameOnCard,
                                                 String bankName, String branchName, String mdtAmount, String purpose, String transferMode, String errorMessageMaxTransactionLimit,
                                                 String amount, String minAmount,
                                                 String errInsufficientfunds, String errMinimumTransferAmount) {
        //Obtain account and send for validations
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
                TransactionConstants.CURRENCY_VALUES[0],dowloadLocation);

    }

    @Test(priority = 6, description = "Transfer to Mobile Cash | 36,37,38,39,40,41,42 Transfers | U4,U5 ", dataProvider = "MobileCashTransferData", dataProviderClass = DataProviders.TransfersPageDataProvider.class)
    public void validateMobileCashTransfers(String errNICNumberRequired, String errMobileNumberRequired, String errReEnterMobileNumberRequired, String errNameRequired, String errAmountRequired, String errPurposeRequired, String errBeneficiaryRemarkRequired, String errorMessageMultipleOfHundred, String nicNumber, String mobileNo, String name, String purpose, String errorMessageMaxTransactionLimit, String amount, String minAmount, String errInsufficientfunds, String errMinimumTransferAmount, String maxAmountInHundred) {
        //Obtain account and send for validations
        dashboardPage.selectQuickActions(BillerConstants.BUTTON_SEND_MONEY);
        //Select appropriate tab
        makeTransactionsPage.selectHeaderTab(TransactionConstants.TAB_NAME_SEND_MONEY);
        makeTransactionsPage.selectTabUnderSendMoney(TransactionConstants.TAB_NAME_MOBILE_CASH);
        makeTransactionsPage.makeMobileCashTransactions(errNICNumberRequired, errMobileNumberRequired, errReEnterMobileNumberRequired, errNameRequired, errAmountRequired, errPurposeRequired, errBeneficiaryRemarkRequired, errorMessageMultipleOfHundred, nicNumber, mobileNo, name, purpose, CommonUtils.randomAlphaNumeric(20), errorMessageMaxTransactionLimit, amount, LoginConstants.OTP, minAmount, errInsufficientfunds, errMinimumTransferAmount, TransactionConstants.CURRENCY_VALUES[0], maxAmountInHundred, dowloadLocation);
    }


    @Test(priority = 7, description = "Transfer to within same bank accounts | 41,42,44,46,47,48,49,51 ", dataProvider = "OwnBankTransactions", dataProviderClass = DataProviders.TransfersPageDataProvider.class)
    public void validateWithinSameBankTransfers(
            String errorMsgInsufficientFunds, String minAmountEntry, String maxAmountEntry,
            String minAmountMsg, String maxAmountMsg, String toAccount, String amount,
            String transferMode, String receiverName, String purpose,
            String bankName, String bankNameTwo, String actualTransactionAmount) {
        //Obtain account and send for validations
        dashboardPage.selectQuickActions(BillerConstants.BUTTON_SEND_MONEY);
        //Select appropriate tab
        makeTransactionsPage.selectHeaderTab(TransactionConstants.TAB_NAME_SEND_MONEY);
        makeTransactionsPage.selectTabUnderSendMoney(TransactionConstants.TAB_NAME_OTHER_ACCOUNT);

        makeTransactionsPage.makeWithinOwnBankTransactions(
                errorMsgInsufficientFunds, minAmountEntry, maxAmountEntry,
                minAmountMsg, maxAmountMsg, toAccount, amount,
                CommonUtils.randomAlphaNumeric(20), CommonUtils.randomAlphaNumeric(20), transferMode,
                TransactionConstants.CONSTANTS_MAP, TransactionConstants.CURRENCY_VALUES[0],
                LoginConstants.OTP, receiverName, purpose, bankName, bankNameTwo, actualTransactionAmount
        );
    }

    @Test(priority = 8, description = "Transfer to staff accounts | 41,42,44,46,47,48,49,51 ", dataProvider = "StaffAccountTransfersData", dataProviderClass = DataProviders.TransfersPageDataProvider.class)
    public void validateStaffAccountTransfers(
            String errorMsg, String toAccount, String amount,
            String transferMode, String receiverName, String purpose,
            String bankName) {
        //Obtain account and send for validations
        dashboardPage.selectQuickActions(BillerConstants.BUTTON_SEND_MONEY);
        //Select appropriate tab
        makeTransactionsPage.selectHeaderTab(TransactionConstants.TAB_NAME_SEND_MONEY);
        makeTransactionsPage.selectTabUnderSendMoney(TransactionConstants.TAB_NAME_OTHER_ACCOUNT);

        makeTransactionsPage.makeStaffAccountTransactions(errorMsg,toAccount,CommonUtils.randomAlphaNumeric(20),transferMode,TransactionConstants.CONSTANTS_MAP,TransactionConstants.CURRENCY_VALUES[0],receiverName,purpose,bankName,amount);

    }



    @AfterMethod(description = "Rollback to dashboard")
    public void rollBackToDashboard() {
        dashboardPage.navigateBackToDashboard();

    }
}