package gui.test;

import utils.constants.BillerConstants;
import utils.constants.DashboardConstants;
import pages.*;
//import pages.OpenSavingAccountPage;
import utils.Drivers;
import data.DataProviders;
import utils.constants.LoginConstants;
import utils.constants.SaveAccountConstants;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentTest;
import utils.report.TestContext;
import java.lang.reflect.Method;
import java.util.List;


public class OpenSavingAccountTest extends Drivers {

    DashboardPage dashboardPage;
    LoginPage loginPage;
    OTPPage otpPage;
    ExtentTest exTest;
    SettingsPage settingsPage;
    OpenSavingAccountPage savingsPage;

    @BeforeMethod
    private void OpenURL(Method method) {
        String methodName = method.getName();
        exTest = extent.createTest(methodName);
        TestContext.setExtentTest(exTest);
        dashboardPage = new DashboardPage(driver);
        loginPage = new LoginPage(driver);
        otpPage = new OTPPage(driver);
        settingsPage = new SettingsPage(driver);
        savingsPage = new OpenSavingAccountPage(driver);
    }

    @Test(priority = 1, dataProvider = "LoginDataSavingsFD", description = "Pre-Requisite :: Login to the Sampath vishwa application", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void logIntoDahsboard(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {
        driver.get(url);
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        dashboardPage.validateTheTitle();
    }

    @Test(priority = 2, description = "Navigate to the Saving account section and validating the account numbers are relevant - 216,218")
    public void getLoginDataSavingFD (){
        dashboardPage.obtainAllAccountTypes(DashboardConstants.STATUS_PRIMARY);
        dashboardPage.selectQuickActions("Open Saving");
        savingsPage.NavigateTotheSavingAccountOpen();
       savingsPage.validatingTheAccountNumber();
    }

    @Test(priority = 3, description = "Selecting the product selection for the sanhida user 217")
    public void validateDataSavingFD (){
        savingsPage.SelfAccountOpening();
    }
    @Test(priority = 4,description = "Selecting the debiting account number 218", dataProvider = "SavingsAccountNumber", dataProviderClass = DataProviders.SavingsandFDDataProvider.class)
    public void selectAccountNumberDropdown(String accountNumber) {
        savingsPage.selectAccountNumberFromDropdown(accountNumber);

    }
    @Test(priority = 5,description = "Entering the user data", dataProvider = "AmountSavingsFD", dataProviderClass = DataProviders.SavingsandFDDataProvider.class)
    public void enteringFundDetails(String amount, String nickName) {
        savingsPage.enteringFundDetails(amount,nickName);
    }

    @Test(priority = 6,description = "Validating Savings Product", dataProvider = "SavingValidationOneRun", dataProviderClass = DataProviders.SavingsandFDDataProvider.class)
    public void ValidatingSavingProductsDetails(List<Object[]> rawData) {
        savingsPage.validateAllSavingsAccounts(rawData);
    }

    @Test(priority = 7, description = "Validating the user details - 225 , uplift - Product validations", dataProvider = "SavingSuccessMsg", dataProviderClass = DataProviders.SavingsandFDDataProvider.class)
    public void validatingAccountDetails(String successMsg, String suffixMsg) {
        savingsPage.validateSavingConfirmationDetails();
        savingsPage.enterOTPAndContinueSettingsPage(LoginConstants.OTP,successMsg,suffixMsg);
        savingsPage.validateSaveAccountConfirmation();
        // Extract account number after confirmation
        String accountNumber = savingsPage.validateSaveAccountConfirmation();
        dashboardPage.navigateBackToDashboard();
        dashboardPage.navigateToMainMenu(BillerConstants.BUTTON_MY_ACCOUNTS);
        savingsPage.searchAndSelectAccountList(accountNumber);

    }
}
