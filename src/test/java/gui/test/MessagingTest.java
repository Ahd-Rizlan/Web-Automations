package gui.test;

import data.DataProviders;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utils.constants.AdminTaskConstants;
import utils.constants.LoginConstants;
import static utils.CommonUtils.randomAlphaNumeric;

public class MessagingTest extends BaseTest {

    String message = "";
    @Test(priority = 1, dataProvider = "LoginDataAlternateTwo", description = "Pre-Requisite :: Login to the Sampath vishwa application as alternate user two", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void logIntoDahsboardAlternateUserOne(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {

        driver.get(url);
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        dashboardPage.validateTheTitle();
        dashboardPage.captureBaseWindowHandle();


    }

    @Test(priority = 3, description = "Fixed deposit inquiry and sent mail | 5,6,8,2,3",dataProvider = "FdInquiryAndSendMailValidationData", dataProviderClass = DataProviders.MessagesPageDataProvider.class)
    public void fdInquiryAndSendMail(String subject,String branch,String successMsg, String otp,String messageCreationSuccessMsg,String message,String forwardMessage,String headerTile,String branchUserName,String branchPassword,String CBOUUserName,String CBOUPassword,String userName) throws InterruptedException {

        dashboardPage.navigateToMessages();
        //Fd inquiry to be done
        message=message+" "+randomAlphaNumeric(5);
        messagesPage.fixedDepositInquiry(subject,branch,message,successMsg,otp,messageCreationSuccessMsg);
        dashboardPage.captureBaseWindowHandle();
        //Sent mail admin validation
        vishwaRetailAdminLoginPage.navigateToTab(1);
        driver.get(retailAdminURL);
        vishwaRetailAdminLoginPage.loginTOAdminModule(headerTile,branchUserName,branchPassword);
        vishwaRetailAdminTaskPage.validateFDInquiryRequest(subject);
        vishwaRetailAdminTaskPage.validateSentMails(message,subject,forwardMessage+message,branchUserName,AdminTaskConstants.CBOU);
        driver.get(retailAdminURL);
        vishwaRetailAdminLoginPage.loginTOAdminModule(headerTile,CBOUUserName,CBOUPassword);
        vishwaRetailAdminTaskPage.validateForwardedMailsBranch(message,subject, new String[]{message, forwardMessage + message}, new String[]{userName, branchUserName},AdminTaskConstants.CBOU);
    }


    @Test(priority = 3, description = "Card Centre | 9,11",dataProvider = "CardCenterMessageValidationData", dataProviderClass = DataProviders.MessagesPageDataProvider.class)
    public void validateCardCenterMessages(String subject,String subCategory,String successMsg, String otp,String messageCreationSuccessMsg,String message,String headerTile,String userName,String password) throws InterruptedException {

        dashboardPage.navigateToMessages();
        //Fd inquiry to be done
        message=message+" "+randomAlphaNumeric(5);
        message = messagesPage.cardCenterValidations(subject,subCategory,message,successMsg,otp,messageCreationSuccessMsg);
        dashboardPage.captureBaseWindowHandle();
        //Sent mail admin validation
        vishwaRetailAdminLoginPage.navigateToTab(1);
        driver.get(retailAdminURL);
        vishwaRetailAdminLoginPage.loginTOAdminModule(headerTile,userName,password);
        vishwaRetailAdminTaskPage.validateCardCenterRequest(message);


    }

    @Test(priority = 4, description = "Fund Tranfer Request | 12,13,15",dataProvider = "FundTransferRequestValidationData", dataProviderClass = DataProviders.MessagesPageDataProvider.class)
    public void validateFundTranferRequest(String subject,String amount,String successMsg, String otp,String messageCreationSuccessMsg,String message,String accountNo,String remark,String headerTile,String userName,String password) throws InterruptedException {

        dashboardPage.navigateToMessages();
        //Ft request to be done
        message=message+" "+randomAlphaNumeric(5);
        messagesPage.fundTransferRequestValidations(subject,Integer.parseInt(amount),remark,successMsg,otp,messageCreationSuccessMsg,accountNo,remark);
        dashboardPage.captureBaseWindowHandle();
        //Sent mail admin validation
        vishwaRetailAdminLoginPage.navigateToTab(1);
        driver.get(retailAdminURL);
        vishwaRetailAdminLoginPage.loginTOAdminModule(headerTile,userName,password);
        vishwaRetailAdminTaskPage.validateFundTransferRequest(subject,remark);

    }


    @Test(priority = 2, description = "Balance Confirmation Request | 16,18",dataProvider = "BalanceConfirmationRequestValidationData", dataProviderClass = DataProviders.MessagesPageDataProvider.class)
    public void validateBalanceConfirmationRequest(String subject,String successMsg, String otp,String messageCreationSuccessMsg,String message,String headerTile,String userName,String password) throws InterruptedException {

        dashboardPage.navigateToMessages();
        dashboardPage.captureBaseWindowHandle();
        message=message+" "+randomAlphaNumeric(5);
        //Fd inquiry to be done
        messagesPage.BalanceConfirmationValidations(subject,successMsg,otp,messageCreationSuccessMsg, message);
        //Sent mail admin validation
        vishwaRetailAdminLoginPage.navigateToTab(1);
        driver.get(retailAdminURL);
        vishwaRetailAdminLoginPage.loginTOAdminModule(headerTile,userName,password);
        vishwaRetailAdminTaskPage.validateFundTransferRequest(subject,message);
    }


    @Test(priority = 6, description = "Fund Transfer dispute | 19,20",dataProvider = "FundTransferDisputeValidationData", dataProviderClass = DataProviders.MessagesPageDataProvider.class)
    public void validateFundTransferDispute(String subject,String successMsg, String otp,String messageCreationSuccessMsg,String message,String headerTile,String userName,String password,String filePath) throws InterruptedException {

        dashboardPage.navigateToMessages();
        //Fd inquiry to be done
        message=message+" "+randomAlphaNumeric(5);
        messagesPage.FundTransferDisputeValidations(subject,successMsg,otp,messageCreationSuccessMsg, message,filePath);
        dashboardPage.captureBaseWindowHandle();
        //Sent mail admin validation
        vishwaRetailAdminLoginPage.navigateToTab(1);
        driver.get(retailAdminURL);
        vishwaRetailAdminLoginPage.loginTOAdminModule(headerTile,userName,password);
        vishwaRetailAdminTaskPage.validateFundTransferRequest(subject,message);

    }

    @Test(priority = 7, description = "Other Subject | 26,27",dataProvider = "OtherSubjectValidationData", dataProviderClass = DataProviders.MessagesPageDataProvider.class)
    public void validateOtherMessages(String subject,String successMsg, String otp,String messageCreationSuccessMsg,String message,String branch,String filePath,String headerTile,String userName,String password) throws InterruptedException {

        dashboardPage.navigateToMessages();
        message=message+" "+randomAlphaNumeric(5);
        //Fd inquiry to be done
        messagesPage.OtherSubjectValidations(subject,successMsg,otp,messageCreationSuccessMsg, message,branch,filePath);
        dashboardPage.captureBaseWindowHandle();
        //Sent mail admin validation
        vishwaRetailAdminLoginPage.navigateToTab(1);
        driver.get(retailAdminURL);
        vishwaRetailAdminLoginPage.loginTOAdminModule(headerTile,userName,password);
        vishwaRetailAdminTaskPage.validateFundTransferRequest(subject,message);
    }


    @Test(priority = 8, description = "inbox messages and trash msg | 29,30,32,40",dataProvider = "InboxAndRestoreMessagesValidationData", dataProviderClass = DataProviders.MessagesPageDataProvider.class)
    public void validateInboxAndRestoreMessages(String subject,String successMsg, String otp,String messageCreationSuccessMsg,String message,String responseMsg,String messageDeletionSuccessMsg,String messageRecoverySuccessMsg,String headerTile,String userName,String password,String filePath) throws InterruptedException {

        dashboardPage.navigateToMessages();
        //prerequisite of message
        message=message+" "+randomAlphaNumeric(5);
        messagesPage.FundTransferDisputeValidations(subject,successMsg,otp,messageCreationSuccessMsg, message,filePath);
        dashboardPage.captureBaseWindowHandle();
        //Sent mail admin validation
        vishwaRetailAdminLoginPage.navigateToTab(1);
        driver.get(retailAdminURL);
        vishwaRetailAdminLoginPage.loginTOAdminModule(headerTile,userName,password);
        vishwaRetailAdminTaskPage.validateFundTransferRequest(subject,message);
        vishwaRetailAdminTaskPage.replyToMail(message,responseMsg,subject);
        vishwaRetailAdminLoginPage.navigateToTab(0);
        messagesPage.inboxMessagesValidation(subject,message,responseMsg,messageDeletionSuccessMsg,messageRecoverySuccessMsg);
    }


    @Test(priority = 9, description = "Draft messages | 38,39",dataProvider = "DraftMessageValidationData", dataProviderClass = DataProviders.MessagesPageDataProvider.class)
    public void validateDraftMessages(String subject,String successMsg,String otp,String messageCreationSuccessMsg,String message,String updatedMsg,String deletionSuccessMsg,String filePath) throws InterruptedException {

        dashboardPage.navigateToMessages();
        dashboardPage.captureBaseWindowHandle();
        messagesPage.draftMessageValidations(subject,successMsg,otp,messageCreationSuccessMsg, message,updatedMsg,deletionSuccessMsg,filePath);

    }

    @Test(priority = 10, description = "Bill payment dispute | 22,23,24",dataProvider = "BillPaymentDisputeValidationData", dataProviderClass = DataProviders.MessagesPageDataProvider.class)
    public void validateBillPaymentDispute(String subject,String successMsg, String otp,String messageCreationSuccessMsg,String message,String filePath,String headerTile,String userName,String password) throws InterruptedException {


        dashboardPage.navigateToMessages();
        //Fd inquiry to be done
        message=message+" "+randomAlphaNumeric(5);
        messagesPage.BillPaymentDisputeValidations(subject,successMsg,otp,messageCreationSuccessMsg,message,filePath);
        dashboardPage.captureBaseWindowHandle();
        //Sent mail admin validation
        vishwaRetailAdminLoginPage.navigateToTab(1);
        driver.get(retailAdminURL);
        vishwaRetailAdminLoginPage.loginTOAdminModule(headerTile,userName,password);
        vishwaRetailAdminTaskPage.validateFundTransferRequest(subject,message);

    }
    @Test(priority = 11, description = "Compose new message | 41, 42",dataProvider = "ComposeNewMessageValidationData", dataProviderClass = DataProviders.MessagesPageDataProvider.class)
    public void validateComposeNewMessage(String subject, String uploadErrorMsg,String fileNameOne,String fileNameTwo,String fileNameThree,String fileNameFour,String fileNameFive,String pastedText,String sanitizedExpected,String testInputWithSpecialCharacters) throws InterruptedException {

        dashboardPage.navigateToMessages();
        //compose message validations
        messagesPage.validateComposeMessageFields(subject, uploadErrorMsg, fileNameOne, fileNameTwo, fileNameThree, fileNameFour, fileNameFive, pastedText, sanitizedExpected, testInputWithSpecialCharacters
        );
    }





    @AfterMethod(description = "Rollback to dashboard")
    public void rollBackToDashboard() {
        dashboardPage.navigateBackToDashboardCloseOtherWindows();

    }

}
