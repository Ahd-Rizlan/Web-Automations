package gui.test;

import data.DataProviders;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utils.constants.DashboardConstants;
import utils.constants.LoginConstants;

public class LoanAccountDetailedViewTest extends BaseTest{

    @Test(priority = 1, dataProvider = "LoginDataAlternateNine", description = "Pre-Requisite :: Login to the Sampath vishwa application ", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void logIntoDahsboard(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        dashboardPage.validateTheTitle();
    }

    @Test(priority = 2, dataProvider = "ExpectederrorMsg", description = "Navigation and validation the loan details - 1 ", dataProviderClass = DataProviders.LoanDetailsViewDataProvider.class)
    public void validatingAndValidationLoan(String errorMsg) throws InterruptedException {
         loanAccountDetailedViewPage.NavigateToLoanPage();
         loanAccountDetailedViewPage.ValidatingLoanDetails(DashboardConstants.CURRENCY_VALUES,errorMsg);
    }
    @Test(priority = 3, dataProvider = "LoanDetailsView", description = "Partial settlement and paid instalment validation - 2,4,5,6,7,", dataProviderClass = DataProviders.LoanDetailsViewDataProvider.class)
    public void validatingPartialSettlement(String loanAccountNumber,String accountNumber,String successMsg) throws InterruptedException {
        loanAccountDetailedViewPage.NavigateToLoanPage();
        loanAccountDetailedViewPage.ValidatethePartialSettlement(loanAccountNumber,accountNumber,successMsg);
        loanAccountDetailedViewPage.enterOTPAndContinueLoanDetailsPage(LoginConstants.OTP);
        loanAccountDetailedViewPage.ValidatetheInstallmentDetails();
    }
    @AfterMethod(description = "Rollback to dashboard")
    public void rollBackToDashboard() {
        dashboardPage.navigateBackToDashboard();
        extent.flush();
    }

}
