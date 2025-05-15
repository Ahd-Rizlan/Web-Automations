package gui.test;

import data.DataProviders;
import utils.constants.LoginConstants;
import org.testng.annotations.*;
import pages.DashboardPage;
import pages.LoginPage;
import pages.OTPPage;
import utils.Drivers;
import com.aventstack.extentreports.ExtentTest;
import utils.report.TestContext;
import java.lang.reflect.Method;

public class LoginTest extends BaseTest {

    @Test(priority = 1, description = "Validate the loading of logging page")
    public void validateTheLoginPage() {
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
    }

    @Test(priority = 2, dataProvider = "LoginData", description = "Validate the successful logging with correct user ID & Password", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void validateTheSuccessfulLogin(String userName, String password, String emailSentSuccessMsg) {
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.TRUE);
    }

    @Test(priority = 3, dataProvider = "LoginData", description = "Browser back and forward functions", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void validateTheBrowserBackAndForward(String userName, String password,String emailSentSuccessMsg) {
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

    @Test(priority = 4, dataProvider = "InvalidPassword", description = "Validate the logging with correct user ID & invalid password.", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void validateTheUnSuccessfulLoginWithIncorrectPassword(String userName, String password, String IncorrectPassword, String ErrorMessage) {
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.ValidateLoginWithIncorrectPassword(userName, password, IncorrectPassword, ErrorMessage);
    }

    @Test(priority = 5, dataProvider = "LogoutData", description = "Validate the successful logout", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void validateTheSuccessfulLogout(String userName, String password, String emailSentSuccessMsg, String popupText) {
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        dashboardPage.validateTheTitle();
        dashboardPage.logoutFromSampathVishwaWeb(LoginConstants.LOGOUT_BUTTON_TEXT, popupText, LoginConstants.CONFIRM_AND_LOGOUT_BUTTON_TEXT, LoginConstants.LOGIN_TILE_NAME);
    }
    @Test(priority = 6, dataProvider = "InvalidUserId", description = "Validate the invalid user ID for forget password journey", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void validateTheUnSuccessfulLoginWithIncorrectPassword(String invalidUN, String errorMessage) {
        loginPage.ValidateForgotPasswordIncorrectUser(LoginConstants.RESET_BUTTON_TEXT,invalidUN,errorMessage);
    }
//    @Test(priority = 7, dataProvider = "InvalidAnswers", description = "Validate the incorrect answers on security questions for forget password journey", dataProviderClass = DataProviders.LoginDataProvider.class)
//    public void validateIncorrectAnswersForSecurityQuestions(String userName, String errorMessage) {
//        loginPage.ValidateForgotPasswordIncorrectSecurityAnswers(LoginConstants.RESET_BUTTON_TEXT, CommonUtils.randomAlphaNumeric(5),userName,errorMessage);
//    }
    @Test(priority = 8, dataProvider = "LockedUser", description = "Validate the unsuccessful login with locked user ID", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void ValidateLoginAttemptWithLockedUserID(String userName,String password, String errorMessage) {
        loginPage.ValidateSuccessfulLoginAttemptWithLockedUserID(LoginConstants.LOGIN_BUTTON_TEXT, userName,password,errorMessage,true);
        loginPage.ValidateSuccessfulLoginAttemptWithLockedUserID(LoginConstants.LOGIN_BUTTON_TEXT, userName,password,errorMessage,false);
    }
    @Test(priority = 9, dataProvider = "LockedUserReset", description = "Validate the locked user ID for forget password journey", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void validateTheUnSuccessfulResetWithLockedUser(String invalidUN, String errorMessage) {
        loginPage.ValidateForgotPasswordLockedUser(LoginConstants.RESET_BUTTON_TEXT, invalidUN, errorMessage);
    }

    //---------------------------  Work-in progress -------------------------------


//    @Test(dataProvider = "ForgotPasswordData", description = "Validate the forgot password journey.", dataProviderClass = DataProviders.LoginDataProvider.class)
//    public void validateForgotPasswordJourney(String restButton, String nextButton, String vishwaID, String userName, String userInput, String maidName, String petName,
//                                              String enterPassword, String confirmPassword, String unaReqFieldText, String questionRFieldText, String newPwReqFldText,
//                                              String conPwReqFldText, String submitButton, String headingMessage, String expectedItems,String newPassword) {
//        loginPage.ClickOnRestOrSignupButton(restButton);
//        loginPage.ValidateForgotPasswordSteps(userInput, vishwaID, userName, nextButton, maidName, petName, enterPassword, confirmPassword, questionRFieldText, unaReqFieldText, newPwReqFldText, conPwReqFldText, submitButton, headingMessage);
//        loginPage.ValidatePasswordPolicy();
//        loginPage.validateListItems(expectedItems);
//        loginPage.validateAndTypeNewPassword(newPassword, expectedItems);
//    }


    @AfterTest
    public void afterMethod() {
        TestContext.clearExtentTest();
    }
}
