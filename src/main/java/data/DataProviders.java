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

        @DataProvider(name = "LoginPageValidation")
        public static Iterator<Object[]> getLoginTitleData() {
            String[] columnNames = {"loginPageTitle", "LoginTileName"};
            return XlsReader.getDataFromSheet(filePath, "ValidateLoginPage", columnNames).iterator();
        }

        @DataProvider(name = "LoginData")
        public static Iterator<Object[]> getLoginData() {
            String[] columnNames = {"userName", "password", "dashboardPageTitle", "buttonText"};
            return XlsReader.getDataFromSheet(filePath, "LoginCredentials", columnNames).iterator();
        }

        @DataProvider(name = "IncorrectUserID")
        public static Iterator<Object[]> getIncorrectUserIDData() {
            String[] columnNames = {"password", "buttonText", "IncorrectUseName", "errorMessage"};
            return XlsReader.getDataFromSheet(filePath, "IncorrectCredentials", columnNames).iterator();
        }

        @DataProvider(name = "IncorrectPassword")
        public static Iterator<Object[]> getIncorrectPasswordData() {
            String[] columnNames = {"userName", "buttonText", "IncorrectPassword", "errorMessageWithAttempts"};
            return XlsReader.getDataFromSheet(filePath, "IncorrectCredentials", columnNames).iterator();
        }

        @DataProvider(name = "onlyUserID")
        public static Iterator<Object[]> getLoginUsingUserIDData() {
            String[] columnNames = {"userName", "password", "buttonText", "passwordPlaceholderText", "usernamePlaceholderText"};
            return XlsReader.getDataFromSheet(filePath, "IncorrectCredentials", columnNames).iterator();
        }

        @DataProvider(name = "LogoutData")
        public static Iterator<Object[]> logoutData() {
            String[] columnNames = {"userName", "password", "dashboardPageTitle", "buttonText", "popupText", "confirmButtonText", " loginPageTitle", "logoutButton"};
            return XlsReader.getDataFromSheet(filePath, "LogoutValues", columnNames).iterator();
        }

        @DataProvider(name = "ForgotPasswordData")
        public static Iterator<Object[]> ForgotPasswordData() {
            String[] columnNames = {"resetButton", "nextButton", "vishwaID", "username", "textToType", "maidName", "petName", "enterPassword", "confirmPassword",
                    "questionRFieldText", "	unaReqFieldText", "	newPwReqFldText", "conPwReqFldText", "submitButton", "headingMessage", "policyList","newPassword"};
            return XlsReader.getDataFromSheet(filePath, "ForgotPassword", columnNames).iterator();
        }
    }
}
