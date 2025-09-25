package gui.test;

import data.DataProviders;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import utils.CommonUtils;
import utils.constants.LoginConstants;
import utils.report.TestContext;

import java.util.Arrays;
import java.util.List;


public class LoginTest extends BaseTest {


    @Test(priority = 2, description = "Validate the loading of logging page")
    public void validateTheLoginPage() {
        driver.get(url);
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.validateLinksAtLogin();
        loginPage.validateVersion();
    }

    @Test(priority = 3, dataProvider = "LoginData", description = "Validate the successful logging with correct user ID & Password | 1 ", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void validateTheSuccessfulLogin(String userName, String password, String emailSentSuccessMsg) {
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.TRUE);
    }

    @Test(priority = 4, dataProvider = "LoginData", description = "Browser back and forward functions| 10 ", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void validateTheBrowserBackAndForward(String userName, String password, String emailSentSuccessMsg) {
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        dashboardPage.validateTheTitle();
        dashboardPage.ValidateUserProfileIcon();
        dashboardPage.browserNavigateBack();
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        dashboardPage.browserNavigateForward();
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
    }

    @Test(priority = 5, dataProvider = "InvalidPassword", description = "Validate the logging with correct user ID & invalid password | 2, 3", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void validateTheUnSuccessfulLoginWithIncorrectPassword(String userName, String password, String IncorrectPassword, String ErrorMessage) {

        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.ValidateLoginWithIncorrectPassword(userName, password, IncorrectPassword, ErrorMessage);
    }

    @Test(priority = 6, dataProvider = "LogoutData", description = "Validate the successful logout | 5", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void validateTheSuccessfulLogout(String userName, String password, String emailSentSuccessMsg, String popupText) {
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        dashboardPage.validateTheTitle();
        dashboardPage.logoutFromSampathVishwaWeb(LoginConstants.LOGOUT_BUTTON_TEXT, popupText, LoginConstants.CONFIRM_AND_LOGOUT_BUTTON_TEXT, LoginConstants.LOGIN_TILE_NAME);
    }

    @Test(priority = 7, dataProvider = "InvalidUserId", description = "Validate the invalid user ID for forget password journey | 6, 7 ", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void validateTheUnSuccessfulLoginWithIncorrectUser(String invalidUN, String errorMessage) {
        loginPage.ValidateForgotPasswordIncorrectUser(LoginConstants.RESET_BUTTON_TEXT, invalidUN, errorMessage);
    }

    @Test(priority = 8, dataProvider = "InvalidSecurityAnswer", description = "Validate the incorrect answers on security questions for forget password journey| 8 ", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void validateIncorrectAnswersForSecurityQuestions(String userName, String successMsg, String invalidMsg) {
        loginPage.ValidateForgotPasswordIncorrectSecurityAnswers(userName, successMsg, LoginConstants.OTP, CommonUtils.randomAlphaNumeric(5), invalidMsg);
    }

    @Test(priority = 9, dataProvider = "LockedUser", description = "Validate the unsuccessful login with locked user ID", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void ValidateLoginAttemptWithLockedUserID(String userName, String password, String errorMessage) {
        loginPage.ValidateSuccessfulLoginAttemptWithLockedUserID(LoginConstants.LOGIN_BUTTON_TEXT, userName, password, errorMessage, true);
        loginPage.ValidateSuccessfulLoginAttemptWithLockedUserID(LoginConstants.LOGIN_BUTTON_TEXT, userName, password, errorMessage,    true);
    }

    @Test(priority = 10, dataProvider = "LockedUserReset", description = "Validate the locked user ID for forget password journey", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void validateTheUnSuccessfulResetWithLockedUser(String invalidUN, String errorMessage) {
        loginPage.ValidateForgotPasswordLockedUser(LoginConstants.RESET_BUTTON_TEXT, invalidUN, errorMessage);
    }

    @Test(priority = 11, dataProvider = "PasswordReset", description = "Validate the Password reset", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void ValidatetheResetPassword(String userName, String passwordLessThatEightCharacters, String passwordRepeatedCharacters, String passwordNoUppercaseCharacters, String passwordNoLowercaseCharacters, String passwordNonumber, String passwordNoSpecialCharater, String successMsg) {
        driver.get(url);
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.ValidateForgotPassword(userName, passwordLessThatEightCharacters, passwordRepeatedCharacters, passwordNoUppercaseCharacters, passwordNoLowercaseCharacters, passwordNonumber, passwordNoSpecialCharater, LoginConstants.OTP, successMsg);
    }

    @Test(priority = 12, dataProvider = "PasswordTestData", description = "Resetting the Password", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void ValidatetheResetPasswordSetNewPassword(String passwords, String successMessage, String userName, String emailSentSuccessMsg) {
        String successfulPassword = loginPage.tryPasswordsUntilSuccess(passwords, successMessage);
        loginPage.loginToSampathVishwaWeb(userName, successfulPassword, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        dashboardPage.validateTheTitle();
    }

    @Test(priority = 1, dataProvider = "SignUpData", description = "Validate the Sign Up Page Navigation | 1 ", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void validateTheSignUp(String expectedPart) {

        loginPage.Signup();
        loginPage.validateURL(expectedPart);
    }


    @AfterTest
    public void afterMethod() {

        TestContext.clearExtentTest();
    }
}
