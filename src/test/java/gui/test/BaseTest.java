package gui.test;

import org.testng.annotations.*;
import pages.*;
import utils.Drivers;
import utils.report.TestContext;

public class BaseTest extends Drivers {

    protected DashboardPage dashboardPage;
    protected LoginPage loginPage;
    protected OTPPage otpPage;
    protected SettingsPage settingsPage;
    protected SavedPayeesPage savedPayeesPage;
    protected MessagesPage messagesPage;
    protected MakeTransactionsPage makeTransactionsPage;
    VishwaRetailAdminLoginPage vishwaRetailAdminLoginPage;
    VishwaRetailAdminTaskPage vishwaRetailAdminTaskPage;
    PawningTicketPage pawningTicketPage;
    ObtainLoanPage obtainLoanPage;

    @BeforeClass
    public void setup() {
        dashboardPage = new DashboardPage(driver);
        loginPage = new LoginPage(driver);
        otpPage = new OTPPage(driver);
        settingsPage = new SettingsPage(driver);
        savedPayeesPage = new SavedPayeesPage(driver);
        makeTransactionsPage = new MakeTransactionsPage(driver);
        messagesPage = new MessagesPage(driver);
        vishwaRetailAdminLoginPage = new VishwaRetailAdminLoginPage(driver);
        vishwaRetailAdminTaskPage = new VishwaRetailAdminTaskPage(driver);
        pawningTicketPage = new PawningTicketPage (driver);
        obtainLoanPage = new ObtainLoanPage(driver);
        driver.get(url);
    }


    @AfterClass
    public void cleanup() {
        TestContext.clear();
    }
}