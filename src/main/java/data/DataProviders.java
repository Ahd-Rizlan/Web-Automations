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
            String[] columnNames = {"userName", "password", "emailSentSuccessMsg"};
            return XlsReader.getDataFromSheet(filePath, "LoginData", columnNames).iterator();
        }

        @DataProvider(name = "LoginDataAlternateOne")
        public static Iterator<Object[]> getLoginDataAlternateOne() {
            String[] columnNames = {"userName", "password", "emailSentSuccessMsg"};
            return XlsReader.getDataFromSheet(filePath, "LoginDataAlternateOne", columnNames).iterator();
        }

        @DataProvider(name = "InvalidPassword")
        public static Iterator<Object[]> getIncorrectPasswordData() {
            String[] columnNames = {"userName", "password", "incorrectPassword", "errorMessage"};
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
            String[] columnNames = {"userName", "password", "emailSentSuccessMsg", "popupText"};
            return XlsReader.getDataFromSheet(filePath, "LogoutData", columnNames).iterator();
        }

        @DataProvider(name = "LockedUser")
        public static Iterator<Object[]> getLockedUserData() {
            String[] columnNames = {"userName", "password", "errorMessage"};
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
            String[] columnNames = {"imgLocation", "userName", "threshold"};
            return XlsReader.getDataFromSheet(filePath, "DashboardAccountPortfolio", columnNames).iterator();
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
}
