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
    protected VishwaRetailAdminLoginPage vishwaRetailAdminLoginPage;
    protected VishwaRetailAdminTaskPage vishwaRetailAdminTaskPage;
    protected MyAccountsPage myAccountsPage;
    protected BillPaymentPage billPaymentPage;
    protected OldVishwaPage OldVishwaPage;
    protected PawningTicketPage pawningTicketPage;
    protected  ObtainLoanPage obtainLoanPage;
    protected FDDetailViewPage fdDetailViewPage;
    protected LoanAccountDetailedViewPage loanAccountDetailedViewPage;
    protected  CreditCardDetailedViewPage creditCardDetailedViewPage;
    protected  OpenFDPage fdPage;
    protected OpenSavingAccountPage savingsPage;
    protected WebCardDetailedViewPage webCardDetailedViewPage;
    protected MultiplePaymentsPage multiplePaymentsPage;
    protected MultipleBillersPage multipleBillersPage;

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
        myAccountsPage = new MyAccountsPage(driver);
        billPaymentPage = new BillPaymentPage(driver);
        OldVishwaPage = new  OldVishwaPage(driver);
        fdDetailViewPage = new FDDetailViewPage(driver);
        loanAccountDetailedViewPage = new LoanAccountDetailedViewPage(driver);
        creditCardDetailedViewPage = new CreditCardDetailedViewPage(driver);
        fdPage = new OpenFDPage(driver);
        savingsPage = new OpenSavingAccountPage(driver);
        webCardDetailedViewPage = new WebCardDetailedViewPage(driver);
        multiplePaymentsPage = new MultiplePaymentsPage(driver);
        multipleBillersPage = new MultipleBillersPage(driver);
        driver.get(url);
    }


    @AfterClass
    public void cleanup() {
        TestContext.clear();
    }
}