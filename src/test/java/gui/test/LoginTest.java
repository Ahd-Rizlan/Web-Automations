package gui.test;

import data.DataProviders;
import gui.utils.constants.LoginConstants;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import utils.Drivers;
import com.aventstack.extentreports.ExtentTest;
import utils.TestContext;

import java.lang.reflect.Method;

public class LoginTest extends Drivers {

    HomePage homePage;
    LoginPage loginPage;
    ExtentTest test;

    @BeforeMethod
    private void OpenURL(Method method) {
        String methodName = method.getName();
        test = extent.createTest(methodName);
        TestContext.setExtentTest(test);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        driver.get(url);
    }

    @Test(priority = 1, description= "Validate the loading of logging page")
    public void validateTheLoginPage() {
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
    }

    @Test(priority = 2, description = "Validate the successful logging with correct user ID & Password")
    public void validateTheSuccessfulLogin() {
        loginPage.loginToSampathVishwaWeb(userName, password, LoginConstants.BOTH_EMAIL_AND_SMS_SENT_SUCCESSFULLY_MSG, LoginConstants.OTP_PAGE_HEADER);
    }

    @Test(priority = 3, dataProvider = "InvalidPassword" , description = "Validate the logging with correct user ID & invalid password.", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void validateTheUnSuccessfulLoginWithIncorrectPassword(String IncorrectPassword, String errorMessage) {
        loginPage.ValidateLoginWithIncorrectPassword(userName,password,IncorrectPassword,errorMessage);
    }

//    @Test(priority = 2, dataProvider = "IncorrectUserID", description = "Validate the logging with incorrect user ID & valid password.", dataProviderClass = DataProviders.LoginDataProvider.class)
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
