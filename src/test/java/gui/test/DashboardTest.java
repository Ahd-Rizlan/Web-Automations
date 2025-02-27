package gui.test;

import data.DataProviders;
import gui.utils.constants.DashboardConstants;
import gui.utils.constants.LoginConstants;
import org.testng.annotations.*;
import pages.DashboardPage;
import pages.LoginPage;
import pages.OTPPage;
import utils.Drivers;
import com.aventstack.extentreports.ExtentTest;
import utils.report.TestContext;
import java.lang.reflect.Method;

public class DashboardTest extends Drivers {

    LoginPage loginPage;
    OTPPage otpPage;
    ExtentTest exTest;
    DashboardPage dashboardPage;

    @BeforeMethod
    private void OpenURL(Method method) {
        String methodName = method.getName();
        exTest = extent.createTest(methodName);
        TestContext.setExtentTest(exTest);
        dashboardPage = new DashboardPage(driver);
        loginPage = new LoginPage(driver);
        otpPage = new OTPPage(driver);
    }


    //Pre-requisite
    @Test(priority = 1, dataProvider = "LoginData", description = "Login to the Sampath vishwa application", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void validateTheBrowserBackAndForward(String userName, String password,String emailSentSuccessMsg) {
        driver.get(url);
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        dashboardPage.closeAlertPopup();
        dashboardPage.validateTheTitle();
    }

    @Test(priority = 2, dataProvider = "DashboardSavingsValidationData", description = "Validate the 6 key points in savings account", dataProviderClass = DataProviders.DashboardDataProvider.class)
    public void validateSavingsAccountAtDashboard(String savingsAccountNumber, String currencyAndAvailableBalance, String productName) {
        dashboardPage.validateSavingsAccountAtDashboard(savingsAccountNumber, currencyAndAvailableBalance, DashboardConstants.STATUS_PRIMARY, DashboardConstants.STATUS_ACTIVE, productName);
    }

    @Test(priority = 3, dataProvider = "DashboardFDValidationData", description = "Validate the 6 key points in FD account", dataProviderClass = DataProviders.DashboardDataProvider.class)
    public void validateFixedDepositAccountAtDashboard(String fDAccountNumber, String currencyAndAvailableBalance, String maturityAmount, String maturityDate, String interestRate) {
        dashboardPage.navigateBackToDashboard();
        dashboardPage.validateFixedDepositAccountAtDashboard(fDAccountNumber, currencyAndAvailableBalance, maturityAmount, maturityDate, interestRate);
    }

    @Test(priority = 4, description = "Validate the availability of options under 'Quick Action Widget'")
    public void validateQuickActionAtDashboard() {
        dashboardPage.navigateBackToDashboard();
        dashboardPage.validateQuickActionAtDashboard();
    }

    @Test(priority = 5, description = "Validate the direction to the respective journeys upon clicking on Quick Action options")
    public void validateQuickActionsWidgetsFunctionality() {
        dashboardPage.navigateBackToDashboard();
        dashboardPage.validateQuickActionsWidgetsFunctionality(DashboardConstants.BUTTON_TEXT_DASHBOARD);
    }


    @Test(priority = 6, description = "Validate that availability of msg/ads set by bank admins in the dashboard")
    public void validateMsgOrAdvertisements() {
        dashboardPage.navigateBackToDashboard();
        dashboardPage.validateMessagesAndAdvertisements();
    }

    @Test(priority = 7, description = "Validate that availability of RVT transfer in dashboard")
    public void validateAvailabilityOfRVTTransfer() {
        dashboardPage.navigateBackToDashboard();
        dashboardPage.validateRVTTransferWidgetRecords(DashboardConstants.CURRENCY_VALUES);
    }

    @Test(priority = 8, description = "Validate that availability of RVT payments in dashboard")
    public void validateAvailabilityOfRVTPayments() {
        dashboardPage.navigateBackToDashboard();
        dashboardPage.validateRVTPaymentWidgetRecords(DashboardConstants.CURRENCY_VALUES);
    }

    @Test(priority = 9, description = "Validate that availability of RVT payments in dashboard")
    public void validateRVTMobileCashWidgetRecords() {
        dashboardPage.navigateBackToDashboard();
        dashboardPage.validateRVTMobileCashWidgetRecords(DashboardConstants.CURRENCY_VALUES);
    }
    @Test(priority = 10, description = "Validate the availability of favourite payee in dashboard")
    public void validateAvailabilityOfFavouritePayee() {
        dashboardPage.navigateBackToDashboard();
        dashboardPage.validateFavouritePayeeWidget();
    }

//    @Test(priority = 8, description = "Validate visibility of total values in graphical view")
//    public void validateAccountPortfolio() {
//        dashboardPage.navigateBackToDashboard();
//        dashboardPage.validateImage();
//    }


//
//    @Test(priority = 10, description = "Validate that settings option is available on the user info dropdown menu at dashboard")
//    public void validateSettingsOption() {
//        dashboardPage.navigateBackToDashboard();
//        dashboardPage.validateSettingsOption(DashboardConstants.OPTION_SETTINGS);
//    }
}
