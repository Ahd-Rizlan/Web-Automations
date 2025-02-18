package gui.test;

import data.DataProviders;
import gui.utils.constants.LoginConstants;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import pages.OTPPage;
import utils.Drivers;
import com.aventstack.extentreports.ExtentTest;
import utils.TestContext;

import java.lang.reflect.Array;
import java.lang.reflect.Method;

public class LoginTest extends Drivers {

    HomePage homePage;
    LoginPage loginPage;
    OTPPage otpPage;
    ExtentTest exTest;

    @BeforeMethod
    private void OpenURL(Method method) {
        String methodName = method.getName();
        exTest = extent.createTest(methodName);
        TestContext.setExtentTest(exTest);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        otpPage = new OTPPage(driver);
        driver.get(url);
    }

    @Test(priority = 1, description = "Validate the loading of logging page")
    public void validateTheLoginPage() {
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
    }

    @Test(priority = 2, dataProvider = "LoginData", description = "Validate the successful logging with correct user ID & Password", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void validateTheSuccessfulLogin(String emailSentSuccessMsg) {
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.TRUE);
    }

    @Test(priority = 3, dataProvider = "LoginData", description = "Browser back and forward functions", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void validateTheBrowserBackAndForward(String emailSentSuccessMsg) {
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        homePage.validateTheTitle();
        homePage.ValidateUserProfileIcon();
        homePage.browserNavigateBack();
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        homePage.browserNavigateForward();
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
    }

    @Test(priority = 4, dataProvider = "InvalidPassword", description = "Validate the logging with correct user ID & invalid password.", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void validateTheUnSuccessfulLoginWithIncorrectPassword(String IncorrectPassword, String ErrorMessage) {
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.ValidateLoginWithIncorrectPassword(userName, password, IncorrectPassword, ErrorMessage);
    }

    @Test(priority = 5, dataProvider = "LogoutData", description = "Validate the successful logout", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void validateTheSuccessfulLogout(String emailSentSuccessMsg, String popupText) {
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        homePage.validateTheTitle();
        homePage.logoutFromSampathVishwaWeb(LoginConstants.LOGOUT_BUTTON_TEXT, popupText, LoginConstants.CONFIRM_AND_LOGOUT_BUTTON_TEXT, LoginConstants.LOGIN_TILE_NAME);
    }

    //---------------------------  Work-in progress -------------------------------
//    @Test(priority = 7, dataProvider = "FDValidationData", description = "Validate the 6 key points in FD account", dataProviderClass = DataProviders.LoginDataProvider.class)
//    public void validateFixedDepositAccountAtDashboard(String fDAccountNumber,String currencyAndAvailableBalance,String maturityAmount,String maturityDate,String interestRate) {
//        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
//        loginPage.loginToSampathVishwaWeb(userName, password, LoginConstants.BOTH_EMAIL_AND_SMS_SENT_SUCCESSFULLY_MSG, LoginConstants.OTP_PAGE_HEADER,LoginConstants.FALASE);
//        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE,LoginConstants.OTP_PAGE_HEADER);
//        otpPage.enterOTPAndContinue(LoginConstants.OTP);
//        homePage.closeAlertPopup();
//        homePage.validateTheTitle();
//        homePage.validateFixedDepositAccountAtDashboard(fDAccountNumber,currencyAndAvailableBalance,maturityAmount,maturityDate,interestRate);
//    }


//    @Test(priority = 1, dataProvider = "IncorrectUserID", description = "Validate the logging with incorrect user ID & valid password.", dataProviderClass = DataProviders.LoginDataProvider.class)
//    public void validateTheUnSuccessfulLoginWithIncorrectUserName(String password, String buttonName, String incorrectUserName, String errorText) {
//        loginPage.ValidateLoginWithIncorrectUserID(incorrectUserName, password, buttonName, errorText);
//    }


//    @Test(dataProvider = "LoginData", description = "Validate the loading of logging page", dataProviderClass = DataProviders.LoginDataProvider.class)
//    public void validateTheLoginPage(String expectedTitle, String loginTileName) {
//        loginPage.validateTheLoginPage(expectedTitle, LoginConatants.LOGIN_TILE_NAME);
//    }
//


//
//    @Test(priority = 4, dataProvider = "onlyUserID", description = "Validate the logging with Valid User ID & without password/logging without User ID & with password.", dataProviderClass = DataProviders.LoginDataProvider.class)
//    public void validateTheUnSuccessfulLoginWithOnlyUserID(String userName, String password, String buttonName, String passwordPlaceholderText, String usernamePlaceholderText) {
//        loginPage.validateLoginWithOnlyUserIDOrPassword(userName, buttonName, passwordPlaceholderText, true);
//        loginPage.validateLoginWithOnlyUserIDOrPassword(password, buttonName, usernamePlaceholderText, false);
//    }
//
//    @Test(priority = 5, dataProvider = "LogoutData", description = "Validate the successful logging with correct user ID & Password.By clicking the mouse and pressing the Enter Key / logout from the application", dataProviderClass = DataProviders.LoginDataProvider.class)
//    public void validateTheUnSuccessfulLoginWithEnterAndMouseClick(String userName, String password, String dashboardPageTitle, String buttonText, String popupText,
//                                                                   String confirmButtonText, String loginPageTitle, String logout) {
//        loginPage.loginToSampathVishwaWebUsingEnterAndMouseClick(userName, password, dashboardPageTitle, buttonText, true);
//        loginPage.logoutFromSampathVishwaWeb(logout, popupText, confirmButtonText, loginPageTitle);
//        loginPage.loginToSampathVishwaWebUsingEnterAndMouseClick(userName, password, dashboardPageTitle, buttonText, false);
//    }
//
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
