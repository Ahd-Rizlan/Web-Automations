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

        @DataProvider(name = "InvalidPassword")
        public static Iterator<Object[]> getIncorrectPasswordData() {
            String[] columnNames = {"userName", "password" ,"incorrectPassword", "errorMessage"};
            return XlsReader.getDataFromSheet(filePath, "InvalidLoginCredentials", columnNames).iterator();
        }

        @DataProvider(name = "LogoutData")
        public static Iterator<Object[]> getLogoutData() {
            String[] columnNames = {"userName", "password","emailSentSuccessMsg", "popupText"};
            return XlsReader.getDataFromSheet(filePath, "LogoutData", columnNames).iterator();
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

    }
}
