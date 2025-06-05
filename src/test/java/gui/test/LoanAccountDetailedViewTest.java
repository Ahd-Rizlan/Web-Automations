package gui.test;

import data.DataProviders;
import org.testng.annotations.Test;
import utils.constants.DashboardConstants;
import utils.constants.LoginConstants;

public class LoanAccountDetailedViewTest extends BaseTest{

    @Test(priority = 1, dataProvider = "LoginDataAlternateOne", description = "Pre-Requisite :: Login to the Sampath vishwa application", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void logIntoDahsboard(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        dashboardPage.validateTheTitle();
    }

    @Test(priority = 2, dataProvider = "ExpectederrorMsg", description = "Pre-Requisite :: Login to the Sampath vishwa application", dataProviderClass = DataProviders.LoanDetailsViewDataProvider.class)
    public void validatingAndValidationLoan(String errorMsg) throws InterruptedException {
         loanAccountDetailedViewPage.NavogatetoPawningPage();
         loanAccountDetailedViewPage.ValidatingLoanDetails(DashboardConstants.CURRENCY_VALUES,errorMsg);
    }
    @Test(priority = 3, dataProvider = "LoanDetailsView", description = "Pre-Requisite :: Login to the Sampath vishwa application", dataProviderClass = DataProviders.LoanDetailsViewDataProvider.class)
    public void validatingPartialSettlement(String loanAccountNumber,String accountNumber, String customAmount,String successMsg) throws InterruptedException {
        loanAccountDetailedViewPage.ValidatethePartialSettlement(loanAccountNumber,accountNumber,customAmount,successMsg);
        pawningTicketPage.enterOTPAndContinueSettingsPage(LoginConstants.OTP);
        loanAccountDetailedViewPage.ValidatetheInstallmentDetails();
    }

}
