package gui.test;

import data.DataProviders;
import org.testng.annotations.Test;
import utils.constants.DashboardConstants;
import utils.constants.LoginConstants;


public class FDDetailViewTest extends BaseTest {

    @Test(priority = 1, dataProvider = "LoginDataAlternateTwo", description = "Pre-Requisite :: Login to the Sampath vishwa application ", dataProviderClass = DataProviders.LoginDataProvider.class)
    public void logIntoDahsboard(String userName, String password, String emailSentSuccessMsg) throws InterruptedException {
        loginPage.validateTheLoginPage(LoginConstants.EXPECTED_TITLE, LoginConstants.LOGIN_TILE_NAME);
        loginPage.loginToSampathVishwaWeb(userName, password, emailSentSuccessMsg, LoginConstants.OTP_PAGE_HEADER, LoginConstants.FALASE);
        otpPage.validateTheOTPPage(LoginConstants.EXPECTED_TITLE, LoginConstants.OTP_PAGE_HEADER);
        otpPage.enterOTPAndContinue(LoginConstants.OTP);
        dashboardPage.validateTheTitle();

    }

    @Test(priority = 2, dataProvider = "FDDetailView", description = "Validate the FD detail view,closure data,confirmation page 1,2,3", dataProviderClass = DataProviders.FDDetailsveiwDataProvider.class)
    public void validateFDdetailsView (String expectedMessage, String fdAccountNumber){

        fdDetailViewPage.NavogatetoFDDetailViewPage();
        fdDetailViewPage.validateAllFDAccountsAtDashboard(DashboardConstants.CURRENCY_VALUES,expectedMessage,fdAccountNumber);

    }
}