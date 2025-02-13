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


        @DataProvider(name = "LoginPage")
        public static Iterator<Object[]> getLoginPage() {
            String[] columnNames = {"userName", "password","expectedTitle"};
            return XlsReader.getDataFromSheet(filePath, "LoginCredentials", columnNames).iterator();
        }

        @DataProvider(name = "LoginData")
        public static Iterator<Object[]> getLoginData() {
            String[] columnNames = {"userName", "password","otpPageHeader"};
            return XlsReader.getDataFromSheet(filePath, "LoginCredentials", columnNames).iterator();
        }

        @DataProvider(name = "InvalidPassword")
        public static Iterator<Object[]> getIncorrectPasswordData() {
            String[] columnNames = {"IncorrectPassword", "errorMessage"};
            return XlsReader.getDataFromSheet(filePath, "InvalidLoginCredentials", columnNames).iterator();
        }


    }
}
