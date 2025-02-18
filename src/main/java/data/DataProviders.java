package data;

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
            String[] columnNames = {"emailSentSuccessMsg"};
            return XlsReader.getDataFromSheet(filePath, "LoginData", columnNames).iterator();
        }

        @DataProvider(name = "InvalidPassword")
        public static Iterator<Object[]> getIncorrectPasswordData() {
            String[] columnNames = {"incorrectPassword", "errorMessage"};
            return XlsReader.getDataFromSheet(filePath, "InvalidLoginCredentials", columnNames).iterator();
        }

        @DataProvider(name = "LogoutData")
        public static Iterator<Object[]> getLogoutData() {
            String[] columnNames = {"emailSentSuccessMsg", "popupText"};
            return XlsReader.getDataFromSheet(filePath, "LogoutData", columnNames).iterator();
        }

        @DataProvider(name = "FDValidationData")
        public static Iterator<Object[]> FDValidationData() {
            String[] columnNames = {"fDAccountNumber", "currencyAndAvailableBalance", "maturityAmount", "maturityDate", "interestRate"};
            return XlsReader.getDataFromSheet(filePath, "FDValidation", columnNames).iterator();
        }

    }
}
