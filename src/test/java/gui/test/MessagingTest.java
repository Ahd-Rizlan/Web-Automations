package gui.test;

import com.aventstack.extentreports.ExtentTest;
import data.DataProviders;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utils.Drivers;
import utils.constants.LoginConstants;
import utils.report.TestContext;

import java.lang.reflect.Method;

public class MessagingTest extends Drivers {

    DashboardPage dashboardPage;
    LoginPage loginPage;
    OTPPage otpPage;
    VishwaRetailAdminLoginPage vishwaRetailAdminLoginPage;
    VishwaRetailAdminTaskPage vishwaRetailAdminTaskPage;
    MessagesPage messagesPage;
    ExtentTest exTest;


    @BeforeMethod
    private void OpenURL(Method method) {
        String methodName = method.getName();
        exTest = extent.createTest(methodName);
        TestContext.setExtentTest(exTest);
        dashboardPage = new DashboardPage(driver);
        loginPage = new LoginPage(driver);
        otpPage = new OTPPage(driver);
        vishwaRetailAdminLoginPage = new VishwaRetailAdminLoginPage(driver);
        vishwaRetailAdminTaskPage = new VishwaRetailAdminTaskPage(driver);
        messagesPage = new MessagesPage(driver);
    }

    @Test(priority = 1, dataProvider = "LoginDataAlternateTwo", description = "Pre-Requisite :: Login to the Sampath vishwa application as alternate user two", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void logIntoDahsboardAlternateUserOne(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {

        driver.get(url);
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        dashboardPage.validateTheTitle();

    }

// --------  Work in progress ---

//    @Test(priority = 2, description = "Fixed deposit inquiry and sent mail | 5,6,8,2,3")
//    public void fdInquiryAndSendMail() throws InterruptedException {
//
//        dashboardPage.navigateToMessages();
//        //Fd inquiry to be done
//        String messageID = messagesPage.fixedDepositInquiry("Fixed Deposit Inquiry","Hikkaduwa","Test message 1","SMS sent successfully to the registered mobile num","111111","Created New Message Thread Successfully");
//        System.out.print(messageID);
//        //Sent mail admin validation
////        vishwaRetailAdminLoginPage.loginTOAdminModule("Login to Vishwa Retail Admin","kolithal","abc@2134");
//////        vishwaRetailAdminLoginPage.navigateToTab(1);
////        driver.get(retailAdminURL);
////        vishwaRetailAdminLoginPage.navigateToTab(1);
////        vishwaRetailAdminTaskPage.filterMails("","","","");
//    }

//    @Test(priority = 3, description = "Card Centre | 9,11")
//    public void validateCardCenterMessages() throws InterruptedException {
//
//        dashboardPage.navigateToMessages();
//        //Fd inquiry to be done
//        String messageID = messagesPage.cardCenterValidations("Card Center","Fund Transfer Request","Test message 1","SMS sent successfully to the registered mobile num","111111","Created New Message Thread Successfully");
//        System.out.print("Message ID :"+messageID);
//        //Sent mail admin validation
////        vishwaRetailAdminLoginPage.loginTOAdminModule("Login to Vishwa Retail Admin","kolithal","abc@2134");
//////        vishwaRetailAdminLoginPage.navigateToTab(1);
////        driver.get(retailAdminURL);
////        vishwaRetailAdminLoginPage.navigateToTab(1);
////        vishwaRetailAdminTaskPage.filterMails("","","","");
//    }

//    @Test(priority = 4, description = "Fund Tranfer Request | 12,13,15")
//    public void validateFundTranferRequest() throws InterruptedException {
//
//        dashboardPage.navigateToMessages();
//        //Fd inquiry to be done
//        String messageID = messagesPage.fundTransferRequestValidations("Fund Transfer Request",Integer.parseInt("20"),"Test message 1","SMS sent successfully to the registered mobile num","111111","Created New Message Thread Successfully");
//        System.out.print("Message ID :"+messageID);
//        //Sent mail admin validation
////        vishwaRetailAdminLoginPage.loginTOAdminModule("Login to Vishwa Retail Admin","kolithal","abc@2134");
//////        vishwaRetailAdminLoginPage.navigateToTab(1);
////        driver.get(retailAdminURL);
////        vishwaRetailAdminLoginPage.navigateToTab(1);
////        vishwaRetailAdminTaskPage.filterMails("","","","");
//    }




    @AfterMethod(description = "Rollback to dashboard")
    public void rollBackToDashboard() {
        dashboardPage.navigateBackToDashboard();

    }

}
