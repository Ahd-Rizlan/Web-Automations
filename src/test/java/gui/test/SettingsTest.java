package gui.test;

import pages.SettingsPage;
import utils.Drivers;
import data.DataProviders;
import gui.utils.constants.LoginConstants;
import org.testng.annotations.*;
import pages.DashboardPage;
import pages.LoginPage;
import pages.OTPPage;
import com.aventstack.extentreports.ExtentTest;
import utils.report.TestContext;
import java.lang.reflect.Method;

public class SettingsTest extends Drivers {


    DashboardPage dashboardPage;
    LoginPage loginPage;
    OTPPage otpPage;
    ExtentTest exTest;
    SettingsPage settingsPage;

    @BeforeMethod
    private void OpenURL(Method method) {
        String methodName = method.getName();
        exTest = extent.createTest(methodName);
        TestContext.setExtentTest(exTest);
        dashboardPage = new DashboardPage(driver);
        loginPage = new LoginPage(driver);
        otpPage = new OTPPage(driver);
        settingsPage = new SettingsPage(driver);
    }

    @Test(priority = 1, dataProvider = "LoginDataSettings", description = "Pre-Requisite :: Login to the Sampath vishwa application", dataProviderClass = DataProviders.SettingsPageDataProvider.class)
    public void logIntoDahsboard(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {
        driver.get(url);
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
//        Uncomment below once popup feature is deployed
//        dashboardPage.closeAlertPopup();
        dashboardPage.validateTheTitle();
    }

    @Test(priority = 2,dataProvider = "SuccessMessageSettings",description = "Validate the settings panel navigation", dataProviderClass = DataProviders.SettingsPageDataProvider.class)
    public void navigateToSettings (String successMsg){
        settingsPage.navigateToSettings(successMsg);
        settingsPage.enterOTPAndContinueSettingsPage(LoginConstants.OTP);
    }

    @Test(priority = 3,dataProvider = "SettingsPanelUserData" , description = "Validate the settings panel data", dataProviderClass = DataProviders.SettingsPageDataProvider.class)
    public void validateUserSettingsData (String settingsUserDetails) {
    settingsPage.validateUserSettingsData( settingsUserDetails);
    }

    @Test(priority = 6, description = "Navigate to the OTP Mode Verfication")
    public void NavigatetoToOTPVerificationMode (){
        settingsPage.NavigatetoToOTPVerificationMode();
        settingsPage.validateTheSettingsOTPPage();


    }
    @Test(priority = 7, description = "Navigate to the Password section")
    public void navigateToPasswordSection (){
        settingsPage.navigateToPasswordSection();

    }
    @Test(priority = 8,dataProvider = "PasswordChangeDataSettings", description = "Changing the user's Password", dataProviderClass = DataProviders.SettingsPageDataProvider.class)
    public void passwordChange ( String password, String newPassword){
        settingsPage.passwordChange(password, newPassword);
    }

    @Test(priority = 4, description = "Validating the primary account")
    public void validatePrimaryAccount (){
        settingsPage.validatePrimaryAccount();
    }

    @Test(priority = 5,dataProvider = "AccountSuccessMessageSettings", description = "After changing the primary account entering the OTP", dataProviderClass = DataProviders.SettingsPageDataProvider.class)
    public void enterOTPToChangeThePrimaryAccount (String primaryAccountSentSuccessMsg ){
        settingsPage.enterOTPAndContinueSettingsPage(LoginConstants.OTP);
        settingsPage.enterOTPToChangeThePrimaryAccount(primaryAccountSentSuccessMsg );
    }

    @Test(priority = 9, dataProvider = "LoginAfteThePasswordChange", description = "Login after the password change", dataProviderClass = DataProviders.SettingsPageDataProvider.class)
    public void loginAfterChangingThePassword (String userName, String password, String newPassword, String LoginErrorMessage,String successMsg) {
        settingsPage.loginAfterChangingThePassword(userName,password,newPassword,LoginErrorMessage,successMsg);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        dashboardPage.validateTheTitle();
        settingsPage.navigateToSettings(successMsg);
        settingsPage.enterOTPAndContinueSettingsPage(LoginConstants.OTP);
        settingsPage.NavigatetoToSecuritysection();
        settingsPage.navigateToPasswordSection();
        settingsPage.revertPasswordToOldValue(newPassword,password);
    }

}
