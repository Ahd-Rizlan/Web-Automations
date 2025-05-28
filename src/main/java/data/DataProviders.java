package data;

import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.DataProvider;
import utils.XlsReader;

import static utils.Drivers.property;


public class DataProviders {

    public static class LoginDataProvider {
        private static final String filePath;

        static {
            String projectRoot = System.getProperty("user.dir");
            filePath = projectRoot + "/" + property.getProperty("gui-config", "TESTDATA_PATH");
        }

        @DataProvider(name = "LoginData")
        public static Iterator<Object[]> getLoginData() {
            String[] columnNames = {"userName", "password" ,"emailSentSuccessMsg"};
            return XlsReader.getDataFromSheet(filePath, "LoginData", columnNames).iterator();
        }
        @DataProvider(name = "LoginDataAlternateOne")
        public static Iterator<Object[]> getLoginDataAlternateOne() {
            String[] columnNames = {"userName", "password" ,"emailSentSuccessMsg"};
            return XlsReader.getDataFromSheet(filePath, "LoginDataAlternateOne", columnNames).iterator();
        }
        @DataProvider(name = "LoginDataAlternateTwo")
        public static Iterator<Object[]> getLoginDataAlternateTwo() {
            String[] columnNames = {"userName", "password" ,"emailSentSuccessMsg"};
            return XlsReader.getDataFromSheet(filePath, "LoginDataAlternateTwo", columnNames).iterator();
        }
        @DataProvider(name = "LoginDataAlternateThree")
        public static Iterator<Object[]> getLoginDataAlternateThree() {
            String[] columnNames = {"userName", "password" ,"emailSentSuccessMsg"};
            return XlsReader.getDataFromSheet(filePath, "LoginDataAlternateThree", columnNames).iterator();
        }
        @DataProvider(name = "LoginDataAlternateFive")
        public static Iterator<Object[]> getLoginDataAlternateFive() {
            String[] columnNames = {"userName", "password" ,"emailSentSuccessMsg"};
            return XlsReader.getDataFromSheet(filePath, "LoginDataAlternateFive", columnNames).iterator();
        }

        @DataProvider(name = "InvalidPassword")
        public static Iterator<Object[]> getIncorrectPasswordData() {
            String[] columnNames = {"userName", "password" ,"incorrectPassword", "errorMessage"};
            return XlsReader.getDataFromSheet(filePath, "InvalidLoginCredentials", columnNames).iterator();
        }
        @DataProvider(name = "InvalidUserId")
        public static Iterator<Object[]> getIncorrectUserData() {
            String[] columnNames = {"incorrectUser", "errorMessage"};
            return XlsReader.getDataFromSheet(filePath, "InvalidResetUser", columnNames).iterator();
        }
        @DataProvider(name = "InvalidAnswers")
        public static Iterator<Object[]> getIncorrectSecurityData() {
            String[] columnNames = {"userName", "errorMessage"};
            return XlsReader.getDataFromSheet(filePath, "InvalidSecurityAnswer", columnNames).iterator();
        }

        @DataProvider(name = "LogoutData")
        public static Iterator<Object[]> getLogoutData() {
            String[] columnNames = {"userName", "password","emailSentSuccessMsg", "popupText"};
            return XlsReader.getDataFromSheet(filePath, "LogoutData", columnNames).iterator();
        }
        @DataProvider(name = "LockedUser")
        public static Iterator<Object[]> getLockedUserData() {
            String[] columnNames = {"userName", "password","errorMessage"};
            return XlsReader.getDataFromSheet(filePath, "LockedCredential", columnNames).iterator();
        }
        @DataProvider(name = "LockedUserReset")
        public static Iterator<Object[]> getLockedUserResetData() {
            String[] columnNames = {"userName", "errorMessage"};
            return XlsReader.getDataFromSheet(filePath, "LockedUserReset", columnNames).iterator();
        }

    }
    public static class DashboardDataProvider {
        private static final String filePath;

        static {
            String projectRoot = System.getProperty("user.dir");
            filePath = projectRoot + "/" + property.getProperty("gui-config", "TESTDATA_PATH");
        }

        @DataProvider(name = "DashboardSavingsValidationData")
        public static Iterator<Object[]> getDashboardSavingsValidationData() {
            String[] columnNames = {"savingsAccountNumber", "currencyAndAvailableBalance", "productName"};
            return XlsReader.getDataFromSheet(filePath, "DashboardSavingsValidationData", columnNames).iterator();
        }

        @DataProvider(name = "DashboardFDValidationData")
        public static Iterator<Object[]> getDashboardFDValidationData() {
            String[] columnNames = {"fDAccountNumber", "currencyAndAvailableBalance", "maturityAmount", "maturityDate", "interestRate"};
            return XlsReader.getDataFromSheet(filePath, "DashboardFDValidation", columnNames).iterator();
        }
        @DataProvider(name = "DashboardLoanValidationData")
        public static Iterator<Object[]> getDashboardLoanValidationData() {
            String[] columnNames = {"loanAccountNumber", "loanAmt", "outstanding", "loanPeriod", "interestRate"};
            return XlsReader.getDataFromSheet(filePath, "DashboardLoanValidation", columnNames).iterator();
        }
        @DataProvider(name = "DashboardRVTTransferData")
        public static Iterator<Object[]> getDashboardRVTTransferData() {
            String[] columnNames = {"accountName", "currencyAndAmount", "date"};
            return XlsReader.getDataFromSheet(filePath, "DashboardRVTTransferValidation", columnNames).iterator();
        }
        @DataProvider(name = "DashboardRVTPaymentPopup")
        public static Iterator<Object[]> getDashboardRVTPaymentPopupData() {
            String[] columnNames = {"toAccount"};
            return XlsReader.getDataFromSheet(filePath, "DashboardRVTPaymentPopup", columnNames).iterator();
        }

        @DataProvider(name = "DashboardAccountPortfolio")
        public static Iterator<Object[]> getDashboardAccountPortfolioData() {
            String[] columnNames = {"imgLocation","userName","threshold"};
            return XlsReader.getDataFromSheet(filePath, "DashboardAccountPortfolio", columnNames).iterator();
        }

    }
    public static class BillersDataProvider {
        private static final String filePath;

        static {
            String projectRoot = System.getProperty("user.dir");
            filePath = projectRoot + "/" + property.getProperty("gui-config", "TESTDATA_PATH");
        }

        @DataProvider(name = "BillPaymentsData")
        public static Iterator<Object[]> getBillPaymentsData() {
            String[] columnNames = {"category","billerName", "paymentUsing", "transferMode","fromAccount","amount","mobileNo","accountNumber","errorMsgOne","errorMsgTwo","errorMsgThree","errorMsgFour","errorMsgFive","errorMsgSix","nicNo","name","policyNumber","admissionNumber","classID","purpose","date","code","referenceOrReservationNo","employeeID","branch","email"};
            return XlsReader.getDataFromSheet(filePath, "BillPayments", columnNames).iterator();
        }


    }
    public static class SettingsPageDataProvider {

        private static final String filePath;

        static {
            String projectRoot = System.getProperty("user.dir");
            filePath = projectRoot + "/" + property.getProperty("gui-config", "TESTDATA_PATH");
        }

        @DataProvider(name = "SettingsPanelUserData")
        public static Iterator<Object[]> getSettingsPanelUserData() {
            String[] columnNames = {"settingsUserDetails"};
            return XlsReader.getDataFromSheet(filePath, "SettingsPanelUserData", columnNames).iterator();
        }

        @DataProvider(name = "LoginDataSettings")
        public static Iterator<Object[]> getLoginDataSettings() {
            String[] columnNames = {"userName", "password", "emailSentSuccessMsg"};
            return XlsReader.getDataFromSheet(filePath, "LoginDataSettings", columnNames).iterator();
        }

        @DataProvider(name = "PasswordChangeDataSettings")
        public static Iterator<Object[]> getpasswordChange() {
            String[] columnNames = {"password", "newPassword"};
            return XlsReader.getDataFromSheet(filePath, "PasswordChangeDataSettings", columnNames).iterator();
        }

        @DataProvider(name = "AccountSuccessMessageSettings")
        public static Iterator<Object[]> getOTPMessage() {
            String[] columnNames = {"primaryAccountSentSuccessMsg"};
            return XlsReader.getDataFromSheet(filePath, "AccountSuccessMessageSettings", columnNames).iterator();
        }

        @DataProvider(name = "LoginAfteThePasswordChange")
        public static Iterator<Object[]> getLoginDataAfterthePasswordChagne() {
            String[] columnNames = {"userName", "password", "newPassword", "LoginErrorMessage", "emailSentSuccessMsg"};
            return XlsReader.getDataFromSheet(filePath, "LoginAfteThePasswordChange", columnNames).iterator();
        }

        @DataProvider(name = "SuccessMessageSettings")
        public static Iterator<Object[]> getSuccessmessage() {
            String[] columnNames = {"emailSentSuccessMsg"};
            return XlsReader.getDataFromSheet(filePath, "SuccessMessageSettings", columnNames).iterator();
        }
    }

    public static class SavingsandFDDataProvider {
        private static final String filePath;

        static {
            String projectRoot = System.getProperty("user.dir");
            filePath = projectRoot + "/" + property.getProperty("gui-config", "TESTDATA_PATH");
        }
        @DataProvider(name = "LoginDataSavingsFD")
        public static Iterator<Object[]> getLoginDataSavingFD() {
            String[] columnNames = {"userName", "password", "emailSentSuccessMsg"};
            return XlsReader.getDataFromSheet(filePath, "LoginDataAlternateFour", columnNames).iterator();
        }
        @DataProvider(name = "SavingsAccountNumber")
        public static Iterator<Object[]> getAccountNumberSavingFD() {
            String[] columnNames = {"accountNumber"};
            return XlsReader.getDataFromSheet(filePath, "SavingsAccountNumber", columnNames).iterator();
        }
        @DataProvider(name = "AmountSavingsFD")
        public static Iterator<Object[]> getAccountDataSavingFD() {
            String[] columnNames = {"amount", "nickName"};
            return XlsReader.getDataFromSheet(filePath, "AmountSavingsFD", columnNames).iterator();
        }

        @DataProvider(name = "FDCompleteFlowData")
        public static Iterator<Object[]> getCompleteFlowData() {
            String[] columnNames = {"userName","password" ,"emailSentSuccessMsg","product","accountNumber","month","rate","amount","interest","totalAmount","nickName"};
            return XlsReader.getDataFromSheet(filePath, "FDCompleteFlowData", columnNames).iterator();
        }
    }

    public static class TransfersPageDataProvider {

        private static final String filePath;

        static {
            String projectRoot = System.getProperty("user.dir");
            filePath = projectRoot + "/" + property.getProperty("gui-config", "TESTDATA_PATH");
        }

        @DataProvider(name = "OwnAccountTransferData")
        public static Iterator<Object[]> getOwnAccountTransferData() {
            String[] columnNames = {
                    "errorMsg1", "errorMsg2", "minAmount", "maxAmount", "minAmountMsg",
                    "maxAmountMsg", "toAccount", "amount", "transferMode","noAmount","errMinimumTransferAmount"
            };
            return XlsReader.getDataFromSheet(filePath, "OwnAccountTransferData", columnNames).iterator();
        }


        @DataProvider(name = "OtherAccountTransfersData")
        public static Iterator<Object[]> getOtherAccountTransfersData() {
            String[] columnNames = {
                    "errorMsgInsufficientFunds", "minAmountEntry", "maxAmountEntry",
                    "minAmountMsg", "maxAmountMsg", "toAccount", "amount",
                    "transferMode", "receiverName", "purpose",
                    "bankName", "actualTransactionAmount"
            };
            return XlsReader.getDataFromSheet(filePath, "OtherAccountTransfersData", columnNames).iterator();
        }


        @DataProvider(name = "OtherCCardTransferData")
        public static Iterator<Object[]> getOtherCreditCardTransferData() {
            String projectRoot = System.getProperty("user.dir");
            String filePath = projectRoot + "/" + property.getProperty("gui-config", "TESTDATA_PATH");
            String[] columnNames = {
                    "errCreditCardNumberRequired", "errReEnterCreditCardNumberRequired",
                    "errNameOnCardRequired", "errBankRequired", "errAmountRequired",
                    "errPurposeRequired", "errBeneficiaryRemarkRequired",
                    "creditCardNumber", "reEnterCardNumber", "nameOnCard",
                    "bankName", "branchName", "mdtAmount", "purpose", "transferMode", "errorMessageMaxTransactionLimit",
                    "amount", "minAmount",
                    "errInsufficientfunds", "errMinimumTransferAmount"
            };
            return XlsReader.getDataFromSheet(filePath, "OtherCreditCardTransferData", columnNames).iterator();
        }

    }
    public static class MessagesPageDataProvider {

        private static final String filePath;

        static {
            String projectRoot = System.getProperty("user.dir");
            filePath = projectRoot + "/" + property.getProperty("gui-config", "TESTDATA_PATH");
        }

        @DataProvider(name = "DraftMessageValidationData")
        public static Iterator<Object[]> getDraftMessageValidationData() {
            String[] columnNames = { "subject", "successMsg", "otp", "messageCreationSuccessMsg", "message", "updatedMsg", "deletionSuccessMsg", "filePath"
            };
            return XlsReader.getDataFromSheet(filePath, "MDraftMessageData", columnNames).iterator();
        }
        @DataProvider(name = "BillPaymentDisputeValidationData")
        public static Iterator<Object[]> getBillPaymentDisputeValidationData() {
            String[] columnNames = { "subject", "successMsg", "otp", "messageCreationSuccessMsg", "message", "filePath", "headerTile", "userName", "password"
            };
            return XlsReader.getDataFromSheet(filePath, "MBillPaymentDisputeData", columnNames).iterator();
        }
        @DataProvider(name = "InboxAndRestoreMessagesValidationData")
        public static Iterator<Object[]> getInboxAndRestoreMessagesValidationData() {
            String[] columnNames = { "subject", "successMsg", "otp", "messageCreationSuccessMsg", "message", "responseMsg", "messageDeletionSuccessMsg", "messageRecoverySuccessMsg", "headerTile", "userName", "password","filePath"
            };
            return XlsReader.getDataFromSheet(filePath, "MInboxAndRestoreData", columnNames).iterator();
        }

        @DataProvider(name = "OtherSubjectValidationData")
        public static Iterator<Object[]> getOtherSubjectValidationData() {
            String[] columnNames = { "subject", "successMsg", "otp", "messageCreationSuccessMsg", "message", "branch", "filePath", "headerTile", "userName", "password"
            };
            return XlsReader.getDataFromSheet(filePath, "MOtherSubjectValidationData", columnNames).iterator();
        }

        @DataProvider(name = "FundTransferDisputeValidationData")
        public static Iterator<Object[]> getFundTransferDisputeValidationData() {
            String[] columnNames = { "subject", "successMsg", "otp", "messageCreationSuccessMsg", "message", "headerTile", "userName", "password","filePath"
            };
            return XlsReader.getDataFromSheet(filePath, "MFundTransferDisputeData", columnNames).iterator();
        }
        @DataProvider(name = "BalanceConfirmationRequestValidationData")
        public static Iterator<Object[]> getBalanceConfirmationRequestValidationData() {
            String[] columnNames = { "subject", "successMsg", "otp", "messageCreationSuccessMsg", "message", "headerTile", "userName", "password"
            };
            return XlsReader.getDataFromSheet(filePath, "MBalanceConfirmationData", columnNames).iterator();
        }

        @DataProvider(name = "FundTransferRequestValidationData")
        public static Iterator<Object[]> getFundTransferRequestValidationData() {
            String[] columnNames = { "subject", "amount", "successMsg", "otp", "messageCreationSuccessMsg", "message", "accountNo", "remark", "headerTile", "userName", "password"
            };
            return XlsReader.getDataFromSheet(filePath, "MFundTransferRequestData", columnNames).iterator();
        }

        @DataProvider(name = "CardCenterMessageValidationData")
        public static Iterator<Object[]> getCardCenterMessageValidationData() {
            String[] columnNames = { "subject", "subCategory", "successMsg", "otp", "messageCreationSuccessMsg", "message", "headerTile", "userName", "password"
            };
            return XlsReader.getDataFromSheet(filePath, "MCardCenterValidationData", columnNames).iterator();
        }

        @DataProvider(name = "FdInquiryAndSendMailValidationData")
        public static Iterator<Object[]> getFdInquiryAndSendMailValidationData() {
            String[] columnNames = {"subject", "branch", "successMsg", "otp", "messageCreationSuccessMsg", "message", "forwardMessage", "headerTile", "branchUserName", "branchPassword", "CBOUUserName", "CBOUPassword", "userName"
            };
            return XlsReader.getDataFromSheet(filePath, "MFdInquiryAndSendMailData", columnNames).iterator();
        }

        @DataProvider(name = "ComposeNewMessageValidationData")
        public static Iterator<Object[]> getComposeNewMessageValidationData() {
            String[] columnNames = {"subject", "uploadErrorMsg", "fileNameOne", "fileNameTwo", "fileNameThree", "fileNameFour", "fileNameFive", "pastedText", "sanitizedExpected", "testInputWithSpecialCharacters"};
            return XlsReader.getDataFromSheet(filePath, "MComposeNewMessageData", columnNames).iterator();
        }


    }
    public static class PawningDataProvider {

        private static final String filePath;

        static {
            String projectRoot = System.getProperty("user.dir");
            filePath = projectRoot + "/" + property.getProperty("gui-config", "TESTDATA_PATH");
        }

        @DataProvider(name = "PawningData")
        public static Iterator<Object[]> getPawningData() {
            String[] columnNames = {
                    "maxiumAmount", "expectedMessage", "incorrectAmount", "lowBalanceAccount", "amountHigherBalance", "expectedinsufficientFundMessage", "correctAccount", "correctAmount"};
            return XlsReader.getDataFromSheet(filePath, "PawningData", columnNames).iterator();
        }


    }
    public static class ObtainLoanDataProvider {
        private static final String filePath;

        static {
            String projectRoot = System.getProperty("user.dir");
            filePath = projectRoot + "/" + property.getProperty("gui-config", "TESTDATA_PATH");
        }

        @DataProvider(name = "LoanDetails")
        public static Iterator<Object[]> getAccountDataSavingFD() {
            String[] columnNames = {"minimumAmount", "maximumAmount", "actualAmount", "wrongMonth", "correctMonth", "purpose"};
            return XlsReader.getDataFromSheet(filePath, "LoanDetails", columnNames).iterator();
        }

    }
    public static class MyAccountPageDataProvider {
        private static final String filePath;

        static {
            String projectRoot = System.getProperty("user.dir");
            filePath = projectRoot + "/" + property.getProperty("gui-config", "TESTDATA_PATH");
        }

        @DataProvider(name = "SavingsAccountData")
        public static Iterator<Object[]> getSavingsAccountData() {
            String[] columnNames = {"accountNumber", "accHolderName",
                    "systemReserved", "lienAmount", "accOpenedOn", "accountBalance",
                    "floatBalance", "amountFrom", "amountTo"
            };
            return XlsReader.getDataFromSheet(filePath, "SADetailedViewData", columnNames).iterator();
        }

        @DataProvider(name = "ChequeRequestData")
        public static Iterator<Object[]> getChequeRequestData() {
            String[] columnNames = {"accountNumber"};
            return XlsReader.getDataFromSheet(filePath, "ChequeRequestData", columnNames).iterator();
        }

        @DataProvider(name = "CurrentAccountData")
        public static Iterator<Object[]> getCurrAccountData() {
            String[] columnNames = {
                     "accountNumber", "odLimit",
                    "tempOdLimit", "overdueLiability", "reservedAmount",
                    "accountBalance", "openedOn"
            };
            return XlsReader.getDataFromSheet(filePath, "CADetailedViewData", columnNames).iterator();
        }
        @DataProvider(name = "AdvancedSearchData")
        public static Iterator<Object[]> getAdvancedSearchData() {
            String[] columnNames = {
                    "accountNumber", "month", "year",
                        "from", "to", "fullDate", "amountFrom", "amountTo"
            };
            return XlsReader.getDataFromSheet(filePath, "AdvancedSearchData", columnNames).iterator();
        }


        }




}
