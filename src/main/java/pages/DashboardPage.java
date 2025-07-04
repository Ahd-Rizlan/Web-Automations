/*
 *   @author - salman R
 */
package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.CommonUtils;

import java.io.File;
import java.text.NumberFormat;
import java.util.*;

import static utils.Drivers.*;


public class DashboardPage extends BasePage {

    public enum ElementType {
        button, label, span, div
    }

    private static final By alertPopup = By.xpath("//span[text()='X']");
    private static final By iconUser = By.xpath("//div[contains(@class,'NavBar_userContainer')]/img");
    private static final By title = By.xpath("//title[text()='Sampath Vishwa | Dashboard']");
    private static final By btn_freeTrial = By.xpath("//input[@id='linkadd']");
    private static final By btn_BookAFreeDemo = By.xpath("//li/a[text()='Book a Free Demo']");
    private static final By lbl_freeTrial = By.xpath("//h1[text()='Your free trial']");
    private static final By lblAccountsOrCards = By.xpath("//h1[contains(text(),'Accounts / Cards')]");
    private static final By icnMessage = By.xpath("//a[contains(@href,'/dashboard/inbox')]");
    private static final By icnNotification = By.xpath("//div[contains(@class,'flex items-center')]/img[contains(@srcset,'notification')]");
    private static final By lblMessage = By.xpath("//span[contains(text(),'Message')]");
    private static final By lblVishwaAccountSettings = By.xpath("//div[contains(text(),'Vishwa Account Settings')]");
    private static final By lblSavingsACNumber = By.xpath("//span[text()='Savings Account']/ancestor::div[contains(@class,'flex justify-between')]/following::span[contains(@class,'flex flex-col')]");
    private static final By lblACNumber = By.xpath("//span/ancestor::div[contains(@class,'flex justify-between')]/following::span[contains(@class,'flex flex-col')]");
    private static final By lblSavingsPrimaryStatus = By.xpath("//span[text()='Savings Account']/ancestor::div[contains(@class,'flex flex-col')]/following::div[contains(@class,'text-white font-bold')][1]");
    private static final By lblCurrentPrimaryStatus = By.xpath("//span[text()='Current Account']/ancestor::div[contains(@class,'flex flex-col')]/following::div[contains(@class,'text-white font-bold')][1]");
    private static final By lblSavingsAccountStatus = By.xpath("//span[text()='Savings Account']/ancestor::div[contains(@class,'flex flex-col')]/following::div[contains(@class,'text-white font-bold')][2]");
    private static final By lblAccountStatus = By.xpath("//span/ancestor::div[contains(@class,'flex flex-col')]/following::div[contains(@class,'text-white font-bold')][1]");
    private static final By lblAccountStat = By.xpath("//span/ancestor::div[contains(@class,'flex flex-col')]/following::div[contains(@class,'text-white font-bold')][2]");
    private static final By lblAccountStatuses = By.xpath("//span/ancestor::div[contains(@class,'flex flex-col')]/following::div[contains(@class,'text-white font-bold')]");
    private static final By lblSavingsAccountProductName = By.xpath("//span[text()='Savings Account']/ancestor::div[contains(@class,'flex flex-col')]/following::span[contains(@class,'self-end')]");
    private static final By lblAccountProductName = By.xpath("//span/ancestor::div[contains(@class,'flex flex-col')]/following::span[contains(@class,'self-end')]");
    private static final By icnAccounts = By.xpath("//div[contains(@class,'flex flex-col items-center')]/div[3]/div[1]");
    private static final By lblCurrentAccount = By.xpath("//span[text()='Current Account']");
    private static final By btnNextArrow = By.xpath("//div[contains(@class,'flex gap-2')]/div[2]");
    private static final By lblCurrencyAndAvailableBalance = By.xpath("//div[contains(text(),'Available')]/following-sibling::div/span[@class='text-black']");
    private static final By btnDeposits = By.xpath("//div[contains(@class,'Container_body')]//div[contains(text(),'Deposits')]");
    private static final By btnLoans = By.xpath("//div[contains(@class,'Container_body')]//div[contains(text(),'Loans')]");
    private static final By btnAccounts = By.xpath("//div[text()='Accounts']");
    private static final By lblLoadingIcon = By.xpath("//div[contains(@class,'AccountsCards_loader')]");
    private static final By lblFDMaturityValue = By.xpath("//span[contains(text(),'Maturity Value')]/parent::div/span[1]");
    private static final By lblFDMaturityDate = By.xpath("//span[contains(text(),'Maturity Date')]/parent::div/span[1]");
    private static final By lblFDInterestRate = By.xpath("//span[contains(text(),'Interest Rate')]/parent::div/span[1]");
    private static final By lblFDAccountNumber = By.xpath(" //span[contains(text(),'Fixed Deposit')]/ancestor::div[contains(@class,'full justify-center flex')]//div[contains(@class,'text-base')]/span");
    private static final By lblLoanACNumber = By.xpath("//span[text()='Loans']/ancestor::div[contains(@class,'flex justify-between')]/following::span[contains(@class,'flex flex-col')]");
    private static final By lblLoanGrantAmt = By.xpath("//span[contains(text(),'Loan Amount')]/parent::div/span[1]");
    private static final By lblLoanCurrOutstanding = By.xpath("//span[contains(text(),'Current Outstanding')]/ancestor::div[contains(@class,'center flex flex-col')]/div[2]/span");
    private static final By lblLoanPeriod = By.xpath("//span[contains(text(),'Loan Period')]/parent::div/span[1]");
    private static final By lblLoanInterestRate = By.xpath("//span[contains(text(),'Interest Rate')]/parent::div/span[1]");
    private static final By btnQActionsSendMoney = By.xpath("//h1[text()='Quick Actions']/ancestor::div[contains(@class,'Container_container')]//span[contains(text(),'Send Money')]");
    private static final By btnQActionsBillPayment = By.xpath("//h1[text()='Quick Actions']/ancestor::div[contains(@class,'Container_container')]//span[contains(text(),'Bill Payment')]");
    private static final By btnQActionsSampathSlipless = By.xpath("//h1[text()='Quick Actions']/ancestor::div[contains(@class,'Container_container')]//span[contains(text(),'Sampath')]/parent::div/span[contains(text(),'Slipless')]");
    private static final By btnQActionsOpenNewFD = By.xpath("//h1[text()='Quick Actions']/ancestor::div[contains(@class,'Container_container')]//span[contains(text(),'Open New')]/parent::div/span[contains(text(),'Fixed Deposit')]");
    private static final By btnQActionsOpenSA = By.xpath("//h1[text()='Quick Actions']/ancestor::div[contains(@class,'Container_container')]//span[contains(text(),'Open Saving')]/parent::div/span[contains(text(),'Account')]");
    private static final By btnQActionsApplyWebCard = By.xpath("//h1[text()='Quick Actions']/ancestor::div[contains(@class,'Container_container')]//span[contains(text(),'Apply')]/parent::div/span[contains(text(),'Web Card')]");
    private static final By btnQActionsStopCard = By.xpath("//h1[text()='Quick Actions']/ancestor::div[contains(@class,'Container_container')]//span[contains(text(),'Stop Card')]");
    private static final By btnQActionsStopCheque = By.xpath("//h1[text()='Quick Actions']/ancestor::div[contains(@class,'Container_container')]//span[contains(text(),'Stop Cheque')]");
    private static final By btnQActionsMobileCash = By.xpath("//h1[text()='Quick Actions']/ancestor::div[contains(@class,'Container_container')]//span[contains(text(),'Mobile Cash')]");
    private static final By btnQActionsSavingsAccount = By.xpath("//h1[text()='Quick Actions']/ancestor::div[contains(@class,'Container_container')]//span[contains(text(),'Open Saving')]");
    private static final By btnQActionsObtainNewLoan = By.xpath("//h1[text()='Quick Actions']/ancestor::div[contains(@class,'Container_container')]//span[contains(text(),'Obtain')]/parent::div/span[contains(text(),'New Loan')]");
    private static final By btnTransfer = By.xpath("//div[contains(@class,'RecentVishwaTransactions')]//div[contains(text(),'Transfer')]");
    private static final By btnPayment = By.xpath("//div[contains(@class,'RecentVishwaTransactions')]//div[contains(text(),'Payment')]");
    private static final By btnMobileCash = By.xpath("//div[contains(@class,'RecentVishwaTransactions')]//div[contains(text(),'Mobile Cash')]");
    private static final By lblRVTTransferRecord = By.xpath("//div[contains(@class,'RecentVishwaTransactions_scrollContainer')]//div[contains(@class,'RecentVishwaTransactions_transactionCard')]");
    private static final By lblRVTPaymentAccountName = By.xpath("//div[contains(@class,'RecentVishwaTransactions_transactionCard')][1]//div[contains(@class,'transactionDetails')]/span[1]");
    private static final By lblSendMoneyHeader = By.xpath("//span[text()='Send Money']/parent::div/span[contains(text(),'Sampath Bank Accounts')]");
    private static final By lblMobileCashHeader = By.xpath(" //span[text()='Send Money']/parent::div/span[contains(text(),'Mobile Cash')]");
    private static final By lblBillPaymentHeader = By.xpath("//span[text()='Bill Payments']/parent::div[contains(@class,'sm font-medium')]/span[contains(text(),'New Payment')]");
    private static final By imgAccountPortfolio = By.xpath("//canvas[@role='img']");
    private static final By imgAdvertisement = By.xpath("//div[contains(@class,'auto relative group')]/img");
    private static final By lblOpenFDPopupHeader = By.xpath("//span[text()='Fixed Deposits']");
    private static final By lblOpenSavingsAccountHeader = By.xpath("//div[contains(text(),'Are you a resident of Sri Lanka?')]");
    private static final By btnClosePopup = By.xpath("//button[contains(text(),'Close')]");
    private static final By btnDownload = By.xpath("//span[text()='Download']");
    private static final By popUpPDFDownload = By.xpath("//div[text()='PDF downloaded successfully!']");
    private static final By btnAddBiller = By.xpath("//span[contains(text(),'Favorite Billers')]/parent::div/parent::div//span[contains(text(),'Add to favorites')]");
    private static final By btnAddPayee = By.xpath("//span[contains(text(),'Favorite Payees')]/following::div[contains(@class,'grid grid-cols')]/div/span[contains(text(),'Add to favorites')]");
    private static final By lblFavouritePayeeWidgetRow = By.xpath("//span[contains(text(),'Favorite Payee')]/ancestor::div[contains(@class,'flex flex-col w')]//span[3]");
    private static final By btnDashboard = By.xpath("//button/a[contains(normalize-space(text()), 'Dashboard')]");
    private static final By lblFavouriteBillerWidgetRow = By.xpath("//span[contains(text(),'Favorite Billers')]/parent::div/parent::div//div[contains(@class,'grid grid-cols')]/div");
    private static final By btnConfirm = By.xpath("//button[contains(normalize-space(text()),'Confirm')]");
    private static final By lblRVTTransferTransactionDetailsPopup = By.xpath("//div[contains(text(),'Transaction Details')]");
    private static final By lblRVTPaymentDetailsPopup = By.xpath("//div[contains(text(),'Payment Details')]");
    private static final By lblRVTTransferRecordOA = By.xpath("//span[contains(text(),'Own Account')]/ancestor::div[contains(@class,'cursor-pointer')]//div[contains(@class,'transactionDetails')]");
    private static final By lblRVTPaymentsRecords = By.xpath("//div[contains(@class,'RecentVishwaTransactions_transactionDetails')]");
    private static final By lblBillerGrayLoader = By.xpath("//div[contains(@class,'rounded-lg dark')]");
    private static final By imgSavedBillerFavRecords = By.xpath("//img[contains(@srcset,'.c7bd4030') and @alt='']");
    private static final By imgSampathPreLoader = By.xpath("//img[contains(@srcset,'Fpreloader')]");
    private static final By imgSavedPayeeFavRecords = By.xpath("//table//img[contains(@srcset,'.c7bd4030') and @alt='']");
    private static final By lblQFTSavingsAccountName = By.xpath("//span[text()='Savings Account']/ancestor::div[contains(@class,'flex relative justify-between')]//span[contains(text(),'Available Balance')]");
    private static final By lblAccountPortfolioRows = By.xpath("//h1[contains(text(),'Account Portfolio')]/ancestor::div[contains(@class,'ContainerMd_container')]//div[contains(@class,'flex rounded-lg justify-between')]");
    private static final By lblAccountNumber = By.xpath("//div[contains(@class,'full justify-center flex')]//div[contains(@class,'text-base')]/span");
    private static final By btnCloseBillerPopup = By.xpath("//img[contains(@alt,'Close')]");
    private static final By lblAccountListLoading = By.xpath("//div[contains(@class,'dark:bg-gray')]");

    private static By tfOTP(int Index) {
        return By.xpath("//input[contains(@class,'otp-box')][" + Index + "]");
    }

    private static By txtlogoutPopup(String popupText) {
        return By.xpath("//div[contains(@class,' justify-center flex')]//div[contains(normalize-space(text()), '" + popupText + "')]");
    }

    private static By getElementByTypeAndText(DashboardPage.ElementType type, String text) {
        return By.xpath("//" + type.name() + "[contains(normalize-space(text()), \"" + text + "\")]");
    }

    private static By getPageTitle(String title) {
        return By.xpath("//title[contains(text(),'" + title + "')]");
    }

    private static By btnUserIcnDynamic(String buttonText) {
        return By.xpath("//div[contains(@class,'NavBar_userDropDown_')]//div[contains(normalize-space(text()), '" + buttonText + "')]");
    }

    private static By btnMenuOptions(String buttonText) {
        return By.xpath("//button/a[contains(text(),'" + buttonText + "')]");
    }

    private static By lblRVTAccountName(int index) {
        return By.xpath("//div[contains(@class,'RecentVishwaTransactions_transactionCard')][" + index + "]//div[contains(@class,'transactionDetails')]/span[1]");
    }

    private static By lblRVTAmtAndCurrency(int index) {
        return By.xpath("//div[contains(@class,'RecentVishwaTransactions_transactionCard')][" + index + "]//div[contains(@class,'amountDebit')]/span");
    }

    private static By lblRVTMobileCAmtAndCurrency(int index) {
        return By.xpath("//div[contains(@class,'RecentVishwaTransactions_transactionCard')][" + index + "]//div[contains(@class,'amountDebit')]/span");
    }

    private static By lblRVTDate(int index) {
        return By.xpath("//div[contains(@class,'RecentVishwaTransactions_transactionCard')][" + index + "]//div[contains(@class,'transactionDetails')]/span[2]");
    }

    private static By lblRVTMobileCDate(int index) {
        return By.xpath("//div[contains(@class,'RecentVishwaTransactions_transactionCard')][" + index + "]//div[contains(@class,'transactionDetails')]/span[2]");
    }

    private static By lblRVTPaymentAccountName(int index) {
        return By.xpath("//div[contains(@class,'RecentVishwaTransactions_transactionCard')][" + index + "]//div[contains(@class,'transactionDetails')]/span[1]");
    }

    private static By lblRVTMobileCAccountName(int index) {
        return By.xpath("//div[contains(@class,'RecentVishwaTransactions_transactionCard')][" + index + "]//div[contains(@class,'transactionDetails')]/span[1]");
    }

    private static By lblRVTPaymentReference(int index) {
        return By.xpath("//div[contains(@class,'RecentVishwaTransactions_transactionCard')][" + index + "]//div[contains(@class,'transactionDetails')]/span[contains(@class,'RecentVishwaTransactions')][1]");
    }

    private static By lblRVTPaymentDate(int index) {
        return By.xpath("//div[contains(@class,'RecentVishwaTransactions_transactionCard')][" + index + "]//div[contains(@class,'transactionDetails')]/span[contains(@class,'RecentVishwaTransactions')][2]");
    }

    private static By lblFavouritePayeeNickName(int index) {
        return By.xpath("//span[contains(text(),'Favorite Payees')]/following::div[contains(@class,'flex flex-col tex')][" + index + "]/span[1]");
    }

    private static By lblFavouritePayeeBankName(int index) {
        return By.xpath("//span[contains(text(),'Favorite Payees')]/following::div[contains(@class,'flex flex-col tex')][" + index + "]/span[2]");
    }

    private static By lblFavouritePayeeRefernce(int index) {
        return By.xpath("//span[contains(text(),'Favorite Payees')]/following::div[contains(@class,'flex flex-col tex')][" + index + "]/span[3]");
    }

    private static By lblFavouriteBillerTempName(int index) {
        return By.xpath("//span[contains(text(),'Favorite Billers')]/parent::div/parent::div//div[contains(@class,'flex items')][" + index + "]//span[1]");
    }

    private static By lblFavouriteBillerBName(int index) {
        return By.xpath("//span[contains(text(),'Favorite Billers')]/parent::div/parent::div//div[contains(@class,'flex items')][" + index + "]//span[2]");
    }

    private static By lblFavouritePayeeName(int index) {
        return By.xpath("(//span[contains(text(),'Favorite Payee')]/ancestor::div[contains(@class,'flex flex-col w')]//span[contains(@class,'block')][1])[" + index + "]");
    }

    private static By lblFavouriteBillerFieldName(int index) {
        return By.xpath("//span[contains(text(),'Favorite Billers')]/parent::div/parent::div//div[contains(@class,'flex items')][" + index + "]//span[3]");
    }

    private static By lblFavouritePayeeFieldName(int index) {
        return By.xpath("//span[contains(text(),'Favorite Payee')]/parent::div/parent::div//div[contains(@class,'flex items')][" + index + "]//span[3]");
    }

    private static By lblRVTTransferRecordOAName(int index) {
        return By.xpath("(//span[contains(text(),'Own Account')]/ancestor::div[contains(@class,'cursor-pointer')]//div[contains(@class,'transactionDetails')]/span[1])[" + index + "]");
    }

    private static By lblRVTTransferRecordOtherAccName(String otherAccount, int index) {
        return By.xpath("(//span[contains(text(),'" + otherAccount + "')]/ancestor::div[contains(@class,'cursor-pointer')]//div[contains(@class,'transactionDetails')]/span[1])[" + index + "]");
    }

    private static By lblRVTPaymentTo(String to, int index) {
        return By.xpath("(//span[contains(text(),'" + to + "')]/ancestor::div[contains(@class,'cursor-pointer')]//div[contains(@class,'transactionDetails')]/span[1])[" + index + "]");
    }

    private static By lblRVTPaymentDate(String to, int index) {
        return By.xpath("(//span[contains(text(),'" + to + "')]/ancestor::div[contains(@class,'cursor-pointer')]//div[contains(@class,'transactionDetails')]/span[3])[" + index + "]");
    }

    private static By lblRVTPaymentAmt(String to, int index) {
        return By.xpath("(//span[contains(text(),'" + to + "')]/ancestor::div[contains(@class,'cursor-pointer')]//div[contains(@class,'_amountDebit')])[" + index + "]");
    }

    private static By lblRVTTransferRecordOtherAccDate(String otherAccount, int index) {
        return By.xpath("(//span[contains(text(),'" + otherAccount + "')]/ancestor::div[contains(@class,'cursor-pointer')]//div[contains(@class,'transactionDetails')]/span[2])[" + index + "]");
    }

    private static By lblRVTTransferRecordOtherAccAmt(String otherAccount, int index) {
        return By.xpath("(//span[contains(text(),'" + otherAccount + "')]/ancestor::div[contains(@class,'cursor-pointer')]//div[contains(@class,'_amountDebit')])[" + index + "]");
    }

    private static By lblRVTTransferRecordOADate(int index) {
        return By.xpath("(//span[contains(text(),'Own Account')]/ancestor::div[contains(@class,'cursor-pointer')]//div[contains(@class,'transactionDetails')]/span[2])[" + index + "]");
    }

    private static By lblRVTTransferRecordOAAmt(int index) {
        return By.xpath("(//span[contains(text(),'Own Account')]/ancestor::div[contains(@class,'cursor-pointer')]//div[contains(@class,'_amountDebit')])[" + index + "]");
    }

    private static By lblRVTTransferPopupRecords(int col, int row) {
        return By.xpath("(//table//tr/td[" + col + "])[" + row + "]");
    }

    private static By lblSavedBillerTemplateName(int row) {
        return By.xpath("(//img[contains(@srcset,'.c7bd4030') and @alt='']/ancestor::tr/td[3])[" + row + "]");
    }

    private static By lblSavedPayeeTemplateName(int row) {
        return By.xpath("(//img[contains(@srcset,'.c7bd4030') and @alt='']/ancestor::tr/td[4])[" + row + "]");
    }
    private static By lblAccountPortfolioValues(int row) {
        return By.xpath("//h1[contains(text(),'Account Portfolio')]/ancestor::div[contains(@class,'ContainerMd_container')]//div[contains(@class,'flex rounded-lg justify-between')][" + row + "]/div[2]/span");
    }
    private static By btnMainMenu(String menuName) {
        return By.xpath("//button/a[contains(normalize-space(text()), '"+menuName+"')]");
    }
    private static By btnSubMenu(String subMenuName) {
        return By.xpath("//a/div[text()='"+subMenuName+"']");
    }



    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Logs out from Sampath Vishwa Web
     *
     * @param buttonName        - logout button text
     * @param popupText         - logout popup text
     * @param confirmButtonText - confirm logout button text
     * @param loginPageTitle    - login page title
     */

    public void logoutFromSampathVishwaWeb(String buttonName, String popupText, String confirmButtonText, String loginPageTitle) {
        try {
//            uncomment once deployed
//            clickOnElement(alertPopup);

            //validate user profile icon and click
            boolean userProfileIcon = isElementPresentBy(iconUser);
            if (userProfileIcon) {
                clickOnElement(iconUser);
                addToReport("Successfully clicked on user profile icon on top navigation bar.", Status.PASS);
            } else {
                addToReport("User profile icon is not visible.", Status.FAIL);
                throw new RuntimeException("Error - User profile icon is not visible.");
            }

            //validate logout button
            boolean logoutButton = isElementPresentBy(btnUserIcnDynamic(buttonName));
            if (logoutButton) {
                clickOnElement(btnUserIcnDynamic(buttonName));
                addToReport("Successfully clicked on the logout button.", Status.PASS);
            } else {
                addToReport("Logout button inside user dropdown is not visible.", Status.FAIL);
                throw new RuntimeException("Error - Logout button inside user dropdown is not visible. ");
            }

            //validate popup
            boolean popup = isElementPresentBy(txtlogoutPopup(popupText));
            if (popup) {
                addToReport("'" + popupText + "' Logout popup is visible.", Status.PASS);
            } else {
                addToReport("'" + popupText + "' Logout popup is not visible.", Status.FAIL);
                throw new RuntimeException("'" + popupText + "' Logout pop is not visible.");
            }

            //Validate confirm button
            boolean confirmButton = isElementPresentBy(getElementByTypeAndText(DashboardPage.ElementType.button, buttonName));
            if (confirmButton) {
                addToReport("'" + confirmButtonText + "'Logout button is visible.", Status.PASS);
                clickOnElement(getElementByTypeAndText(DashboardPage.ElementType.button, buttonName));
                addToReport("'" + confirmButtonText + "'Logout button is clicked.", Status.PASS);
            } else {
                addToReport("'" + confirmButtonText + "'Logout button is not visible.", Status.FAIL);
                throw new RuntimeException("'" + confirmButtonText + "'Logout button is not visible.");
            }

            // Validate landing page on logout
            boolean loginPage = isElementPresentBy(getPageTitle(loginPageTitle));
            if (loginPage) {
                addToReport("Successfully logged out from the sampath vishwa application.", Status.PASS);
            } else {
                addToReport("Unable to logged out from the sampath vishwa application.", Status.FAIL);
                throw new RuntimeException("Error - User is unable to logged out from the sampath vishwa application.");
            }

        } catch (Exception e) {
            addToReport("Unable to logged out from the system.", Status.FAIL);
            throw new RuntimeException("Error - Unable to logged out from the system.", e);
        }
    }

    /**
     * Validate the user profile icon
     */
    public void ValidateUserProfileIcon() {
        try {
            waitForElementToBeInvisible(imgSampathPreLoader, VERY_SHORT_WAIT);
            waitForElementToBeInvisible(lblLoadingIcon, VERY_SHORT_WAIT);
            //validate user profile icon
            boolean userProfileIcon = isElementPresentBy(iconUser);
            if (userProfileIcon) {
                addToReport("Successfully validated user profile icon on top navigation bar", Status.PASS);
            } else {
                addToReport("User profile icon is not visible", Status.FAIL);
                throw new RuntimeException("Error -User profile icon is not visible");
            }
        } catch (Exception e) {
            addToReport("User profile icon is not visible", Status.FAIL);
            throw new RuntimeException("Error in loading user profile icon", e);
        }
    }

    /**
     * Validate title
     */
    public void validateTheTitle() {
        waitFor(VERY_SHORT_WAIT);
        waitForElementToBeInvisible(imgSampathPreLoader, LONG_WAIT);
        waitForElementPresence(title);
        addToReport("Successfully validated the title '" + title + "'", Status.PASS);
        waitForElementPresence(imgAdvertisement, LONG_WAIT);
        waitFor(VERY_SHORT_WAIT);
    }

    /**
     * Validate free trial navigation
     */
    public void validateFreeTrialNavigation() {
        clickOnElement(btn_freeTrial);
        Assert.assertEquals(getTextFromElement(lbl_freeTrial), "Your free trial\n" +
                "is almost ready!");
    }

    /**
     * Navigate to book a free demo
     */
    public void navigateToBookAFreeDemo() {
        clickOnElement(btn_BookAFreeDemo);
    }

    /**
     * Validate the availability of 6 key points in savings account
     *
     * @param savingsAccountNumber        - Savings account number
     * @param currencyAndAvailableBalance - Currency and available balance
     * @param primaryStatus               - Primary status
     * @param accountStatus               - Account status
     * @param productName                 - Product Name
     */
    public void validateSavingsAccountAtDashboard(String savingsAccountNumber, String currencyAndAvailableBalance, String primaryStatus, String accountStatus, String productName) {

        try {


            waitForElementToBeInvisible(lblLoadingIcon, MODERATE_WAIT);

            isElementClickable(btnDeposits);

            //Validate savings account details
            String SavingAccountNo = getTextFromElement(lblSavingsACNumber);
            if (SavingAccountNo.equalsIgnoreCase(savingsAccountNumber)) {
                addToReport("Successfully validated account number : '" + SavingAccountNo + "'", Status.PASS, false);
            } else {
                addToReport("Account number is not validated", Status.FAIL);
                throw new RuntimeException("Error - Account number validation failed");
            }
            //Validate currency and balance
            String CurrencyAndAvailableBalance = getTextFromElement(lblCurrencyAndAvailableBalance);
            if (CurrencyAndAvailableBalance.equalsIgnoreCase(currencyAndAvailableBalance)) {
                addToReport("Successfully validated currency and amount : '" + CurrencyAndAvailableBalance + "'", Status.PASS, false);
            } else {
                addToReport("Currency and amount is not validated", Status.FAIL);
                throw new RuntimeException("Error - Currency and amount validation failed");
            }
            //Validate primary status
            String PrimaryStatus = getTextFromElement(lblSavingsPrimaryStatus);
            if (PrimaryStatus.equalsIgnoreCase(primaryStatus)) {
                addToReport("Successfully validated primary status : '" + PrimaryStatus + "'", Status.PASS, false);
            } else {
                addToReport("Primary status is not validated", Status.FAIL);
                throw new RuntimeException("Error - Primary status validation failed");
            }
            //Validate account status
            String AccountStatus = getTextFromElement(lblSavingsAccountStatus);
            if (AccountStatus.equalsIgnoreCase(accountStatus)) {
                addToReport("Successfully validated account status : '" + AccountStatus + "'", Status.PASS, false);
            } else {
                addToReport("Account status is not validated", Status.FAIL);
                throw new RuntimeException("Error - Account status validation failed");
            }
            //Validate product name
            String ProductName = getTextFromElement(lblSavingsAccountProductName);
            if (ProductName.equalsIgnoreCase(productName)) {
                addToReport("Successfully validated product name : '" + ProductName + "'", Status.PASS);
            } else {
                addToReport("Product Name is not validated", Status.FAIL);
                throw new RuntimeException("Error - Product Name validation failed");
            }

        } catch (Exception e) {
            addToReport("Validate Savings Account At Dashboard failed", Status.FAIL);
            throw new RuntimeException("Error  - Validate Savings Account At Dashboard", e);
        }

    }

    /**
     * Validate all account details in dashboard
     *
     * @param currencyType - currency type
     * @param accountStat  - account status
     */
    public void validateAllAccountsAtDashboard(String[] currencyType, String[] accountStat) {

        waitForElementToBeClickable(imgAdvertisement,VERY_LONG_WAIT);
        //wait for the loading icon to diminish
        waitForLoadingToBeInvisible();
        //waitForElementPresence(lblLoadingIcon);
        waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
        waitForElementToBeClickable(icnAccounts, MODERATE_WAIT);

        //Obtain pagination value
        String[] cardCount=CommonUtils.splitText(getAttributeOrText(icnAccounts,"text"),"/");

        //Obtain the accounts record count
        int recordCount = Integer.parseInt(cardCount[1]);
        if (recordCount != 0) {

            for (int inc = 0; inc < recordCount; inc++) {
                //Navigate to next account
                clickOnElement(btnNextArrow);

                //waitForElementPresence(lblLoadingIcon);
                waitForElementToBeInvisible(lblLoadingIcon, MODERATE_WAIT);

                //Validate savings account details
                String AccountNo = CommonUtils.removeSpaceCharacters(getTextFromElement(lblACNumber));
                if (CommonUtils.containsNumericCharacters(AccountNo)) {
                    addToReport("Successfully validated account number : '" + AccountNo + "'", Status.PASS, false);
                } else {
                    addToReport("Account number is not validated", Status.FAIL);
                }

                //Validate currency and balance
                String[] CurrencyAndAmt = getTextFromElement(lblCurrencyAndAvailableBalance).split(" ");
                if (CurrencyAndAmt[0].equals("0.00")) {
                    addToReport("Successfully validated currency and amount as 0.00", Status.PASS, false);
                } else if (Arrays.asList(currencyType).contains(CurrencyAndAmt[0]) &&
                        CommonUtils.containsNumericCharacters(CurrencyAndAmt[1])) {
                    addToReport("Successfully validated currency and amount " + CurrencyAndAmt[0] + CurrencyAndAmt[1], Status.PASS, false);
                } else {
                    addToReport("Currency and amount is not validated", Status.FAIL);
                }

                //Validate account status
                String AccountStatus = getTextFromElement(lblAccountStatus);
                if (Arrays.asList(accountStat).contains(AccountStatus)) {
                    addToReport("Successfully validated account status : '" + AccountStatus + "'", Status.PASS, false);
                } else {
                    addToReport("Account status is not validated", Status.FAIL);
                }
                //Validate product name
                String ProductName = getTextFromElement(lblAccountProductName);
                if (!ProductName.isEmpty() &&
                        CommonUtils.containsAlphaHypenAndSpaceCharacters(ProductName)) {
                    addToReport("Successfully validated product name : '" + ProductName + "'", Status.PASS);
                } else {
                    addToReport("Product Name is not validated", Status.FAIL);
                    throw new RuntimeException("Error - Product Name validation failed");
                }

            }

        }

    }

    /**
     * Validate all current account details in dashboard
     *
     * @param currencyType - currency type
     * @param accountStat  - account status
     */
    public void validateAllCurrentAccountsAtDashboard(String[] currencyType, String[] accountStat) {
        waitForElementToBeClickable(imgAdvertisement,VERY_LONG_WAIT);
        //wait for the loading icon to diminish
        waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
        waitForElementToBeClickable(icnAccounts, MODERATE_WAIT);

        //click button accounts
        if (isElementClickable(btnAccounts)) {
            clickOnElement(btnAccounts);
        } else {
            addToReport("Unable to find accounts button", Status.FAIL);
            throw new RuntimeException("Error - accounts button is not found");
        }
        waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
//        waitForElementToBeInvisible(icnAccounts, LONG_WAIT);
        //Obtain pagination value
        String[] cardCount=CommonUtils.splitText(getAttributeOrText(icnAccounts,"text"),"/");

        //Obtain the accounts record count
        int recordCount = Integer.parseInt(cardCount[1]);
        if (recordCount != 0) {

            for (int inc = 1; inc <= recordCount; inc++) {


                if (isElementPresentBy(lblCurrentAccount)) {

                    int accCount =isElementsPresentBy(lblAccountStatuses);
                    if(accCount==2){

                        if (isElementPresentBy(lblAccountStatus)) {
                            addToReport("Successfully validated primary status", Status.PASS, false);
                        }else {
                            addToReport("Primary account status is not validated", Status.FAIL);
                        }
                        //Validate account status
                        String AccountStatus = getTextFromElement(lblAccountStat);
                        if (Arrays.asList(accountStat).contains(AccountStatus)) {
                            addToReport("Successfully validated account status : '" + AccountStatus + "' for record :" + inc, Status.PASS, false);
                        } else {
                            addToReport("Account status is not validated", Status.FAIL);
                        }
                    }
                    else {
                        //Validate account status
                        String AccountStatus = getTextFromElement(lblAccountStatus);
                        if (Arrays.asList(accountStat).contains(AccountStatus)) {
                            addToReport("Successfully validated account status : '" + AccountStatus + "' for record :" + inc, Status.PASS, false);
                        } else {
                            addToReport("Account status is not validated", Status.FAIL);
                        }
                    }

                    //Validate current account details
                    String AccountNo = CommonUtils.removeSpaceCharacters(getTextFromElement(lblACNumber));
                    if (CommonUtils.containsNumericCharacters(AccountNo)) {
                        addToReport("Successfully validated account number : '" + AccountNo + "' for record :" + inc, Status.PASS, false);
                    } else {
                        addToReport("Account number is not validated", Status.FAIL);
                    }

                    //Validate currency and balance
                    String[] CurrencyAndAmt = getTextFromElement(lblCurrencyAndAvailableBalance).split(" ");
                    if (CurrencyAndAmt[0].equals("0.00")) {
                        addToReport("Successfully validated currency and amount as 0.00", Status.PASS, false);
                    } else if (Arrays.asList(currencyType).contains(CurrencyAndAmt[0]) &&
                            CommonUtils.containsNumericCharacters(CurrencyAndAmt[1])) {
                        addToReport("Successfully validated currency and amount " + CurrencyAndAmt[0] + CurrencyAndAmt[1] + " for record :" + inc, Status.PASS, false);
                    } else {
                        addToReport("Currency and amount is not validated", Status.FAIL);
                    }

                    //Validate product name
                    String ProductName = getTextFromElement(lblAccountProductName);
                    if (!ProductName.isEmpty() &&
                            CommonUtils.containsAlphaHypenAndSpaceCharacters(ProductName)) {
                        addToReport("Successfully validated product name : '" + ProductName + "' for record :" + inc, Status.PASS, true);
                    } else {
                        addToReport("Product Name is not validated", Status.FAIL);
                    }
                }
                //Navigate to next account
                clickOnElement(btnNextArrow);

                //waitForElementPresence(lblLoadingIcon);
                waitForElementToBeInvisible(lblLoadingIcon, MODERATE_WAIT);
            }
        }
    }


    /**
     * Validate all fd account details in dashboard
     *
     * @param currencyType - currency type
     */
    public void validateAllFDAccountsAtDashboard(String[] currencyType) {
        waitFor(VERY_SHORT_WAIT);
        waitForPageLoadCompleteJS();
        clickOnElement(btnLoans);
        waitForElementToBeInvisible(lblAccountListLoading,VERY_LONG_WAIT);
        waitForElementToBeClickable(btnDeposits,LONG_WAIT);
        waitForElementToBeInvisible(lblLoadingIcon,MODERATE_WAIT);
        clickOnElementUsingJS(btnDeposits);
        waitForPageLoadCompleteJS();
        waitForElementToBeInvisible(lblLoadingIcon,VERY_LONG_WAIT);
        waitForElementToBeClickable(icnAccounts,LONG_WAIT);
        //Obtain pagination value
        String[] cardCount=CommonUtils.splitText(getAttributeOrText(icnAccounts,"text"),"/");

        //Obtain the accounts record count
        int recordCount = Integer.parseInt(cardCount[1]);
        if (recordCount != 0) {
            boolean flag = true;

            for (int inc = 0; inc < recordCount; inc++) {

                //Validate deposit account details
                String AccountNo = CommonUtils.removeSpaceCharacters(getTextFromElement(lblACNumber));
                if (CommonUtils.containsNumericCharacters(AccountNo)) {
                    addToReport("Successfully validated deposit account number : '" + AccountNo + "'", Status.PASS, false);
                } else {
                    addToReport("Deposit account number is not validated", Status.FAIL);
                    flag = false;
                }

                //Validate currency and balance
                String[] CurrencyAndAmt = getTextFromElement(lblCurrencyAndAvailableBalance).split(" ");
                if (CurrencyAndAmt[0].equals("0.00")) {
                    addToReport("Successfully validated currency and amount as 0.00", Status.PASS, false);
                } else if (Arrays.asList(currencyType).contains(CurrencyAndAmt[0]) &&
                        CommonUtils.containsNumericCharacters(CurrencyAndAmt[1])) {
                    addToReport("Successfully validated currency and amount " + CurrencyAndAmt[0] + CurrencyAndAmt[1], Status.PASS, false);
                } else {
                    addToReport("Currency and amount is not validated", Status.FAIL);
                    flag = false;
                }

                //Validate Maturity Amount
                String FDMaturityValue = getTextFromElement(lblFDMaturityValue);
                if (CommonUtils.containsNumericCharacters(FDMaturityValue)) {
                    addToReport("Successfully validated maturity amount : '" + FDMaturityValue + "'", Status.PASS, false);
                } else {
                    addToReport("Maturity amount : '" + FDMaturityValue + "' is not validated", Status.FAIL);
                    flag = false;
                }

                //Validate Maturity Date
                String FDMaturityDate = getTextFromElement(lblFDMaturityDate);
                if (CommonUtils.containsValuesOnDate(FDMaturityDate)) {
                    addToReport("Successfully validated maturity date : '" + FDMaturityDate, Status.PASS, false);
                } else {
                    addToReport("Maturity date : '" + FDMaturityDate + "' is not validated", Status.FAIL);
                    flag = false;
                }

                //Validate Interest Rate
                String InterestRate = getTextFromElement(lblFDInterestRate);
                if (CommonUtils.containsAlphNumAndSpecialCharacters(InterestRate)) {
                    addToReport("Successfully validated interest rate : '" + InterestRate + "'", Status.PASS, false);
                } else {
                    addToReport("Interest rate : '" + InterestRate + "' is not validated", Status.FAIL);
                    flag = false;
                }
                if (flag) {
                    addToReport("Successfully validated fixed deposit : '" + AccountNo, Status.PASS, true);
                } else {
                    flag = true;
                }

                //Navigate to next account
                clickOnElement(btnNextArrow);

                //waitForElementPresence(lblLoadingIcon);
                waitForElementToBeInvisible(lblLoadingIcon, MODERATE_WAIT);

            }

        }

    }

    /**
     * Validate all loan account details in dashboard
     *
     * @param currencyType - currency type
     */
    public void validateAllLoanAccountsAtDashboard(String[] currencyType) {

        waitForPageLoadCompleteJS();
        waitForElementToBeInvisible(lblAccountListLoading,VERY_LONG_WAIT);
        waitForElementToBeClickable(btnLoans,LONG_WAIT);

        //click button Loans
        if (isElementClickable(btnLoans)) {
            clickOnElementUsingJS(btnLoans);
        } else {
            addToReport("Unable to find loans button", Status.FAIL);
            throw new RuntimeException("Error - Loans button is not found");
        }

        try {
            waitForElementToBeClickable(icnAccounts, LONG_WAIT);
            //Obtain pagination value
            String[] cardCount=CommonUtils.splitText(getAttributeOrText(icnAccounts,"text"),"/");

            //Obtain the accounts record count
            int recordCount = Integer.parseInt(cardCount[1]);
            if (recordCount != 0) {

                for (int inc = 0; inc < recordCount; inc++) {
                    //Navigate to next account
                    clickOnElement(btnNextArrow);

                    //wait for the loading icon to diminish
                    waitForLoadingToBeInvisible();
                    //waitForElementPresence(lblLoadingIcon);
                    waitForElementToBeInvisible(lblLoadingIcon, MODERATE_WAIT);

                    //Validate loan account number
                    String[] LoanAccountNo = CommonUtils.splitText(getTextFromElement(lblLoanACNumber), "\n");
                    if (CommonUtils.containsNumericCharacters(LoanAccountNo[0])) {
                        addToReport("Successfully validated loan account number : '" + LoanAccountNo[0] + "'", Status.PASS, false);
                    } else {
                        addToReport("Loan Account number : '" + LoanAccountNo[0] + "' is not validated", Status.FAIL);
                    }

                    //Validate loan grant
                    String[] Grant = CommonUtils.splitText(getTextFromElement(lblLoanGrantAmt), " ");
                    if (Arrays.asList(currencyType).contains(Grant[0]) &&
                            CommonUtils.containsNumericCharacters(Grant[1])) {
                        addToReport("Successfully validated grant : '" + Grant[0] + Grant[1] + "'", Status.PASS, false);
                    } else {
                        addToReport("Grant amt : '" + Grant[1] + "' is not validated", Status.FAIL);
                    }

                    //Validate Outstanding Amount
                    String[] CurrOutstanding = CommonUtils.splitText(getTextFromElement(lblLoanCurrOutstanding), " ");
                    if (Arrays.asList(currencyType).contains(CurrOutstanding[0]) &&
                            CommonUtils.containsNumericCharactersWithNegativeValues(CurrOutstanding[1])) {
                        addToReport("Successfully validated outstanding amount : '" + CurrOutstanding[0] + CurrOutstanding[1] + "'", Status.PASS, false);
                    } else {
                        addToReport("Outstanding amount : '" + CurrOutstanding[1] + "' is not validated", Status.FAIL);
                    }

                    //Validate Loan period
                    String[] LoanPeriod = CommonUtils.splitText(getTextFromElement(lblLoanPeriod), " ");
                    if (CommonUtils.containsNumericCharacters(LoanPeriod[0])) {
                        addToReport("Successfully validated loan period : '" + LoanPeriod[0] + "'", Status.PASS, false);
                    } else {
                        addToReport("Loan period : '" + LoanPeriod[0] + "' is not validated", Status.FAIL);
                    }

                    //Validate Interest Rate
                    String InterestRate = getTextFromElement(lblLoanInterestRate);
                    if (CommonUtils.containsAlphNumAndSpecialCharacters(InterestRate)) {
                        addToReport("Successfully validated interest rate : '" + InterestRate + "'", Status.PASS, false);
                    } else {
                        addToReport("Interest rate : '" + InterestRate + "' is not validated", Status.FAIL);
                    }
                    addToReport("Loan account validations complete for account " + inc, Status.PASS, true);
                }

            }
        } catch (Exception e) {
            addToReport("Loan accounts key points validation failed", Status.FAIL);
            throw new RuntimeException("Failed - Loan accounts key points validation failed", e);
        }

    }

    /**
     * Validate the availability of 6 key points in savings account
     *
     * @param fDAccountNumber             - fixed deposit account number
     * @param currencyAndAvailableBalance - fixed deposit currency type and available balance
     * @param maturityAmount              - fixed deposit maturity amount
     * @param interestRate                - fixed deposit interest rate
     */
    public void validateFixedDepositAccountAtDashboard(String fDAccountNumber, String currencyAndAvailableBalance, String maturityAmount, String maturityDate, String interestRate) {
        try {
            //wait for the loading icon to diminish
            waitForLoadingToBeInvisible();
            waitForElementToBeInvisible(lblLoadingIcon, MODERATE_WAIT);

            //click button deposit
            isElementClickable(btnDeposits);
            clickOnElement(btnDeposits);

            //wait for the loading icon to diminish
            waitForLoadingToBeInvisible();
            //waitForElementPresence(lblLoadingIcon);
            waitForElementToBeInvisible(lblLoadingIcon, MODERATE_WAIT);

            //Validate fd account number
            String FDAccountNo = getTextFromElement(lblFDAccountNumber);
            if (FDAccountNo.equalsIgnoreCase(fDAccountNumber)) {
                addToReport("Successfully validated fd account number : '" + FDAccountNo + "'", Status.PASS, false);
            } else {
                addToReport("FD Account number : '" + FDAccountNo + "' is not validated", Status.FAIL);
            }
            //Validate currency and balance
            String CurrencyAndAvailableBalance = getTextFromElement(lblCurrencyAndAvailableBalance);
            if (CurrencyAndAvailableBalance.equalsIgnoreCase(currencyAndAvailableBalance)) {
                addToReport("Successfully validated currency and amount : '" + CurrencyAndAvailableBalance + "'", Status.PASS, false);
            } else {
                addToReport("Currency and amount : '" + CurrencyAndAvailableBalance + "' is not validated", Status.FAIL);
            }
            //Validate Maturity Amount
            String FDMaturityValue = getTextFromElement(lblFDMaturityValue);
            if (FDMaturityValue.equalsIgnoreCase(maturityAmount)) {
                addToReport("Successfully validated maturity amount : '" + FDMaturityValue + "'", Status.PASS, false);
            } else {
                addToReport("Maturity amount : '" + FDMaturityValue + "' is not validated", Status.FAIL);
            }
            //Validate Maturity Date
            String[] FDMaturityDate = CommonUtils.splitText(getTextFromElement(lblFDMaturityDate), " ");
            if (FDMaturityDate[0].equalsIgnoreCase(maturityDate)) {
                addToReport("Successfully validated maturity date : '" + FDMaturityDate[0] + "'", Status.PASS, false);
            } else {
                addToReport("Maturity date : '" + FDMaturityDate[0] + "' is not validated", Status.FAIL);
            }
            //Validate Interest Rate
            String InterestRate = getTextFromElement(lblFDInterestRate);
            if (InterestRate.equalsIgnoreCase(interestRate)) {
                addToReport("Successfully validated interest rate : '" + InterestRate + "'", Status.PASS, true);
            } else {
                addToReport("Interest rate : '" + InterestRate + "' is not validated", Status.FAIL);
            }

        } catch (Exception e) {
            addToReport("Fixed deposit key points validation failed", Status.FAIL);
            throw new RuntimeException("Failed - Fixed deposit key points validation failed", e);
        }
    }

    /**
     * Validate quick action in dashboard
     */
    public void validateQuickActionAtDashboard() {
        try {
            //validate quick action send money
            boolean sendMoney = isElementPresentBy(btnQActionsSendMoney);
            if (sendMoney) {
                addToReport("Successfully validated quick action send money button ", Status.PASS, false);
            } else {
                addToReport("Quick action send money button is not visible", Status.FAIL);
                throw new RuntimeException("Error - Quick action send money button is not visible");
            }
            //validate quick action bill payment
            boolean billPayment = isElementPresentBy(btnQActionsBillPayment);
            if (billPayment) {
                addToReport("Successfully validated quick action bill payment button ", Status.PASS, false);
            } else {
                addToReport("Quick action bill payment button is not visible", Status.FAIL);
                throw new RuntimeException("Error - Quick action bill payment button is not visible");
            }
            //validate quick action sampath slipless
            boolean sampathSleepless = isElementPresentBy(btnQActionsSampathSlipless);
            if (sampathSleepless) {
                addToReport("Successfully validated quick action sampath slipless button ", Status.PASS, false);
            } else {
                addToReport("Quick action  sampath slipless is not visible", Status.FAIL);
                throw new RuntimeException("Error - Quick action  sampath slipless is not visible");
            }
            //validate quick action open fd
            boolean openFD = isElementPresentBy(btnQActionsOpenNewFD);
            if (openFD) {
                addToReport("Successfully validated quick action open fixed deposit button ", Status.PASS, false);
            } else {
                addToReport("Quick action  open fixed deposit is not visible", Status.FAIL);
                throw new RuntimeException("Error - Quick action  open fixed deposit is not visible");
            }
            //validate quick action open SA
            boolean openSA = isElementPresentBy(btnQActionsOpenSA);
            if (openSA) {
                addToReport("Successfully validated quick action open savings account button ", Status.PASS, false);
            } else {
                addToReport("Quick action  open savings account is not visible", Status.FAIL);
                throw new RuntimeException("Error - Quick action  open savings account is not visible");
            }
            //validate quick action apply for web card
            boolean webCard = isElementPresentBy(btnQActionsApplyWebCard);
            if (webCard) {
                addToReport("Successfully validated quick action web card  button ", Status.PASS, false);
            } else {
                addToReport("Quick action  web card is not visible", Status.FAIL);
                throw new RuntimeException("Error - Quick action  web card is not visible");
            }
            //validate quick action stop card
            boolean stopCard = isElementPresentBy(btnQActionsStopCard);
            if (stopCard) {
                addToReport("Successfully validated quick action stop card  button ", Status.PASS, false);
            } else {
                addToReport("Quick action  stop card is not visible", Status.FAIL);
                throw new RuntimeException("Error - Quick action  stop card is not visible");
            }
            //validate quick action stop cheque
            boolean stopCheque = isElementPresentBy(btnQActionsStopCheque);
            if (stopCheque) {
                addToReport("Successfully validated quick action stop cheque  button ", Status.PASS);
            } else {
                addToReport("Quick action  stop cheque is not visible", Status.FAIL);
                throw new RuntimeException("Error - Quick action  stop cheque is not visible");
            }
            scrollDownPage();
            //validate quick action mobile cash
            boolean mobileCash = isElementPresentBy(btnQActionsMobileCash);
            if (mobileCash) {
                addToReport("Successfully validated quick action mobile cash  button ", Status.PASS, false);
            } else {
                addToReport("Quick action  mobile cash is not visible", Status.FAIL);
                throw new RuntimeException("Error - Quick action  mobile cash is not visible");
            }
            //validate quick action obtain new loan
            boolean newLoan = isElementPresentBy(btnQActionsObtainNewLoan);
            if (newLoan) {
                addToReport("Successfully validated quick action obtain new loan  button ", Status.PASS);
            } else {
                addToReport("Quick action  obtain new loan is not visible", Status.FAIL);
                throw new RuntimeException("Error - Quick action  obtain new loan is not visible");
            }

        } catch (Exception e) {
            addToReport("Quick action buttons validation failed", Status.FAIL);
            throw new RuntimeException("Failed - Quick action buttons validation failed", e);
        }
    }

    /**
     * Validate settings option
     *
     * @param buttonName - button text
     */
    public void validateSettingsOption(String buttonName) {
        try {
            //validate user profile icon and click
            boolean userProfileIcon = isElementPresentBy(iconUser);
            if (userProfileIcon) {
                clickOnElement(iconUser);
                addToReport("Successfully clicked on user profile icon on top navigation bar", Status.PASS);
            } else {
                addToReport("User profile icon is not visible", Status.FAIL);
                throw new RuntimeException("Error - User profile icon is not visible");
            }
            //validate logout button
            boolean logoutButton = isElementPresentBy(btnUserIcnDynamic(buttonName));
            if (logoutButton) {
                addToReport("Successfully validated settings icon", Status.PASS);
            } else {
                addToReport("Settings icon is not visible", Status.FAIL);
                throw new RuntimeException("Settings icon is not visible ");
            }

        } catch (Exception e) {
            addToReport("Settings icon is not visible", Status.FAIL);
            throw new RuntimeException("Error - Settings icon is not visible", e);
        }
    }

    /**
     * Validate messages and advertisements
     */
    public void validateMessagesAndAdvertisements() {
        waitForElementPresence(imgAdvertisement, VERY_LONG_WAIT);
        try {
            //wait for the loading icon to diminish
            waitForLoadingToBeInvisible();
            waitForElementPresence(lblLoadingIcon);
            waitForElementToBeInvisible(lblLoadingIcon, SHORT_WAIT);

            scrollDownPage();
            //validate advertisement at dashboard
            boolean advertisement = isElementPresentBy(imgAdvertisement);
            if (advertisement) {
                addToReport("Successfully validated the published advertisement", Status.PASS);
            } else {
                addToReport("Advertisement is not visible.", Status.FAIL);
                throw new RuntimeException("Error - Advertisement is not visible.");
            }
        } catch (Exception e) {
            addToReport("Advertisement is not visible", Status.FAIL);
            throw new RuntimeException("Error - Advertisement is not visible in the dashboard", e);
        }
    }

    /**
     * Validate settings option
     *
     * @param btnDashboard - button text for dashboard button
     */
    public void validateQuickActionsWidgetsFunctionality(String btnDashboard) {
        try {

            /*
            Validations for below are not in-corporated due unavailability of functionality
            sampath slipless
            apply for web card
            stop card
            stop cheque
            obtain new loan*/

            //validate send money header
            clickOnElement(btnQActionsSendMoney);
            boolean sendMoney = isElementPresentBy(lblSendMoneyHeader);
            if (sendMoney) {
                addToReport("Successfully validated the send money page header", Status.PASS);
            } else {
                addToReport("Send money page header is not visible.", Status.FAIL);
                throw new RuntimeException("Error - Send money page header is not visible.");
            }
            waitForElementToBeInvisible(btnQActionsSendMoney, VERY_SHORT_WAIT);
            clickOnElement(btnMenuOptions(btnDashboard));
            waitForElementToBeInvisible(lblLoadingIcon, VERY_SHORT_WAIT);

            //validate bill payment header
            clickOnElement(btnQActionsBillPayment);
            boolean billPayment = isElementPresentBy(lblBillPaymentHeader);
            if (billPayment) {
                addToReport("Successfully validated the bill payment page header", Status.PASS);
            } else {
                addToReport("Bill payment page header is not visible.", Status.FAIL);
                throw new RuntimeException("Error - Bill payment page header is not visible.");
            }
            waitForElementToBeInvisible(btnQActionsBillPayment, VERY_SHORT_WAIT);
            clickOnElement(btnMenuOptions(btnDashboard));
            waitForElementToBeInvisible(lblLoadingIcon, VERY_SHORT_WAIT);

            //validate open new fixed deposit header
            clickOnElement(btnQActionsOpenNewFD);
            boolean openFD = isElementPresentBy(lblOpenFDPopupHeader);
            if (openFD) {
                addToReport("Successfully validated the open new fixed deposit page header", Status.PASS);
                clickOnElement(btnClosePopup);
            } else {
                addToReport("Open new fixed deposit page header is not visible.", Status.FAIL);
                throw new RuntimeException("Error - Open new fixed deposit page header is not visible.");
            }

            clickOnElement(btnMenuOptions(btnDashboard));
            waitForElementToBeInvisible(lblLoadingIcon, VERY_SHORT_WAIT);

            //validate open savings account header
            clickOnElement(btnQActionsOpenSA);
            boolean openSA = isElementPresentBy(lblOpenSavingsAccountHeader);
            if (openSA) {
                addToReport("Successfully validated the open savings account page header", Status.PASS);
            } else {
                addToReport("Open savings account page header is not visible.", Status.FAIL);
                throw new RuntimeException("Error - Open savings account page header is not visible.");
            }
            waitForElementToBeInvisible(btnQActionsOpenSA, VERY_SHORT_WAIT);
            clickOnElement(btnMenuOptions(btnDashboard));
            waitForElementToBeInvisible(lblLoadingIcon, VERY_SHORT_WAIT);

            //validate mobile cash header
            scrollDownPage();
            clickOnElement(btnQActionsMobileCash);
            boolean mobileCash = isElementPresentBy(lblMobileCashHeader);
            if (mobileCash) {
                addToReport("Successfully validated the mobile cash page header", Status.PASS);
            } else {
                addToReport("Mobile cash page header is not visible.", Status.FAIL);
                throw new RuntimeException("Error - Mobile cash page header is not visible.");
            }
        } catch (Exception e) {
            addToReport("Quick action button function failed", Status.FAIL);
            throw new RuntimeException("Error - Quick action button function failed", e);
        }
    }

    /**
     * close the alert popup
     */
    public void closeAlertPopup() {

        //validate user profile icon and click
        boolean alert = isElementPresentBy(alertPopup);
        if (alert) {
            clickOnElement(alertPopup);
            addToReport("Successfully closed alert popup", Status.PASS);
        } else {
            addToReport("User profile icon is not visible.", Status.INFO);
        }
    }

    /**
     * Validate recent vishwa transactions transfer widget
     *
     * @param currencyType - currency types compared from constant array
     */
    public void validateRVTTransferWidgetRecords(String[] currencyType) {
        try {

            //wait for the loading icon to diminish
            waitForLoadingToBeInvisible();
            waitForElementPresence(lblLoadingIcon);
            waitForElementToBeInvisible(lblLoadingIcon, SHORT_WAIT);

            //select transfer tab
            clickOnElement(btnTransfer);

            //Declare list to extract from table
            ArrayList<String> AccName = new ArrayList<>();
            ArrayList<String> AmtAndCurrency = new ArrayList<>();
            ArrayList<String> Date = new ArrayList<>();

            //Obtain the record count
            int recordCount = isElementsPresentBy(lblRVTTransferRecord);
            if (recordCount != 10) {
                addToReport(" Recent vishwa transactions displayed is not 10", Status.FAIL);
                throw new RuntimeException("Error - Incorrect number of Recent vishwa transactions displayed");
            }

            //Extract the latest records from the list
            for (int inc = 1; inc <= recordCount; inc++) {
                AccName.add(inc - 1, getTextFromElement(lblRVTAccountName(inc)));
                AmtAndCurrency.add(inc - 1, getTextFromElement(lblRVTAmtAndCurrency(inc)).trim());
                Date.add(getTextFromElement(lblRVTDate(inc)));
            }

            //Validate based on text and numerals
            for (int inc = 0; inc < recordCount; inc++) {

                //Validate the account name
                if (!AccName.get(inc).isEmpty() &&
                        ((AccName.get(inc).contains("Account") && CommonUtils.containsAlphabaticCharacters(AccName.get(inc))) ||
                                CommonUtils.containsNumericCharacters(AccName.get(inc)))) {
                    addToReport(" Recent vishwa transactions of record number : '" + inc + "' where Account name : '" + AccName.get(inc), Status.PASS, false);
                } else {
                    addToReport(" Failed to validate Recent vishwa transactions of record number : '" + inc + "' where Account name : '" + AccName.get(inc), Status.FAIL);
                    throw new RuntimeException("Error - Account name of Recent vishwa transactions not displayed");
                }

                //Validate currency and amount
                String[] CurrencyAndAmt = AmtAndCurrency.get(inc).split(" ");
                if (!AmtAndCurrency.get(inc).isEmpty() &&
                        Arrays.asList(currencyType).contains(CurrencyAndAmt[0]) &&
                        CommonUtils.containsNumericCharacters(CurrencyAndAmt[1])) {
                    addToReport(" Recent vishwa transactions of record number : '" + inc + "' where Account name : '" + AccName.get(inc) + "' , currency and amount : '" + AmtAndCurrency.get(inc), Status.PASS, false);
                } else {
                    addToReport(" Failed to validate Recent vishwa transactions of record number : '" + inc + "' where currency and amount : '" + AmtAndCurrency.get(inc), Status.FAIL);
                    throw new RuntimeException("Error - Currency amd amount of Recent vishwa transactions not displayed");
                }

                //Validate the date
                String[] dateContent = Date.get(inc).split(" ");
                if (!Date.get(inc).isEmpty() && Date.get(inc).contains("at") &&
                        CommonUtils.containsAlphabaticCharacters(dateContent[0]) &&
                        CommonUtils.containsNumericCharacters(dateContent[1]) &&
                        CommonUtils.containsNumericCharacters(dateContent[2]) &&
                        CommonUtils.containsAlphAndNumCharacters(dateContent[4])) {
                    addToReport(" Recent vishwa transactions of record number : '" + inc + "' where Account name : '" + AccName.get(inc) + "' , Currency and amount : '" + AmtAndCurrency.get(inc) + "' and date : '" + Date.get(inc) + "'", Status.PASS, false);
                } else {
                    addToReport(" Failed to validate Recent vishwa transactions of record number : '" + inc + "' where date : '" + Date.get(inc), Status.FAIL);
                    throw new RuntimeException("Error - Incorrect date for Recent vishwa transactions displayed");
                }
            }
            addToReport(" Recent vishwa transactions of records are successfully validated", Status.PASS);
        } catch (Exception e) {
            addToReport("Recent vishawa transfer validation of transactions failed", Status.FAIL);
            throw new RuntimeException("Error - Validation of transfer under recent vishawa transactions failed", e);
        }
    }

    /**
     * Validate recent vishwa transactions payment widget
     *
     * @param currencyType - currency types compared from constant array
     */
    public void validateRVTPaymentWidgetRecords(String[] currencyType) {
        try {
            //Click recent transfer and wait till account name appear
            clickOnElement(btnPayment);
            waitForElementPresence(lblRVTPaymentAccountName);

            //Declare list to extract from table
            ArrayList<String> AccName = new ArrayList<>();
            ArrayList<String> AmtAndCurrency = new ArrayList<>();
            ArrayList<String> PaymentReference = new ArrayList<>();
            ArrayList<String> Date = new ArrayList<>();

            //Obtain the record count
            int recordCount = isElementsPresentBy(lblRVTTransferRecord);
            if (recordCount != 10) {
                addToReport(" Recent vishwa payments displayed is not 10", Status.FAIL);
                throw new RuntimeException("Error - Incorrect number of Recent vishwa payments displayed");
            }

            //Extract the latest records from the list
            for (int inc = 1; inc <= recordCount; inc++) {
                AccName.add(inc - 1, getTextFromElement(lblRVTPaymentAccountName(inc)));
                AmtAndCurrency.add(inc - 1, getTextFromElement(lblRVTAmtAndCurrency(inc)));
                PaymentReference.add(inc - 1, getTextFromElement(lblRVTPaymentReference(inc)));
                Date.add(getTextFromElement(lblRVTPaymentDate(inc)));
            }

            //validate context
            for (int inc = 0; inc < recordCount; inc++) {

                //Validate account name
                if (!AccName.get(inc).isEmpty() && CommonUtils.containsAlphabaticCharacters(AccName.get(inc).trim())) {
                    addToReport(" Recent vishwa payments of record number : '" + inc + "' where Account name : '" + AccName.get(inc), Status.PASS, false);
                } else {
                    addToReport(" Failed to validate account name : '" + AccName.get(inc) + "' for recent vishwa payment of record number : '" + inc, Status.FAIL);
                    throw new RuntimeException("Error - Incorrect account name for payment under recent vishwa transactions displayed");
                }

                //Validate currency and amount
                String[] CurrencyAndAmt = AmtAndCurrency.get(inc).split(" ");
                if (!AmtAndCurrency.get(inc).isEmpty() && Arrays.asList(currencyType).contains(CurrencyAndAmt[0].trim()) && CommonUtils.containsNumericCharacters(CurrencyAndAmt[1].trim())) {
                    addToReport(" Recent vishwa payments of record number : '" + inc + "' where account name : '" + AccName.get(inc) + "' , currency and amount : '" + AmtAndCurrency.get(inc) + "' and date : '" + Date.get(inc) + "'", Status.PASS, false);
                } else {
                    addToReport(" Failed to validate currency and amount : '" + AmtAndCurrency.get(inc) + "' of recent vishva payment of record number : '" + inc, Status.FAIL);
                    throw new RuntimeException("Error - Incorrect amount for Recent vishwa payment displayed");
                }

                //Validate payment reference
                if (!PaymentReference.get(inc).isEmpty() && CommonUtils.containsAlphNumAndSpecialCharacters(PaymentReference.get(inc).trim())) {
                    addToReport(" Recent vishwa payments of record number : '" + inc + "' where payment reference : '" + PaymentReference.get(inc), Status.PASS, false);
                } else {
                    addToReport(" Failed to validate payment reference : '" + PaymentReference.get(inc) + "' of recent vishwa payment of record number : '" + inc, Status.FAIL);
                    throw new RuntimeException("Error - Incorrect payment reference for Recent vishwa payment displayed");
                }

                //Validate transaction date
                String[] dateContent = Date.get(inc).split(" ");
                if (!Date.get(inc).isEmpty() && Date.get(inc).contains("at") &&
                        CommonUtils.containsAlphabaticCharacters(dateContent[0]) &&
                        CommonUtils.containsNumericCharacters(dateContent[1]) &&
                        CommonUtils.containsNumericCharacters(dateContent[2]) &&
                        CommonUtils.containsAlphAndNumCharacters(dateContent[4])) {
                    addToReport(" Recent vishwa transactions of record number : '" + inc + "' where Account name : '" + AccName.get(inc) + "' , Currency and amount : '" + AmtAndCurrency.get(inc) + "' and date : '" + Date.get(inc) + "'", Status.PASS);
                } else {
                    addToReport(" Failed to validate Recent vishwa transactions of record number : '" + inc + "' where date : '" + Date.get(inc), Status.FAIL);
                    throw new RuntimeException("Error - Incorrect date for Recent vishwa transactions displayed");
                }
            }
        } catch (Exception e) {
            addToReport("Recent vishawa transfer validation of payments record failed", Status.FAIL);
            throw new RuntimeException("Error - Recent vishawa transfer validation of payments record failed", e);
        }
    }

    /**
     * Validate recent vishwa transactions mobile cash widget
     *
     * @param currencyType - currency types compared from constant array
     */
    public void validateRVTMobileCashWidgetRecords(String[] currencyType) {
        try {

            //wait for the loading icon to diminish
            waitForLoadingToBeInvisible();
            waitForElementPresence(lblLoadingIcon);
            waitForElementToBeInvisible(lblLoadingIcon, SHORT_WAIT);

            clickOnElement(btnMobileCash);

            //Declare list to extract from table
            ArrayList<String> MobileNo = new ArrayList<>();
            ArrayList<String> AmtAndCurrency = new ArrayList<>();
            ArrayList<String> Date = new ArrayList<>();

            //Obtain the record count
            int recordCount = isElementsPresentBy(lblRVTTransferRecord);
            if (recordCount != 10) {
                addToReport(" Recent vishwa transactions displayed is not 10", Status.FAIL);
                throw new RuntimeException("Error - Incorrect number of Recent vishwa transactions displayed");
            }

            //Extract the latest records from the list
            for (int inc = 1; inc <= recordCount; inc++) {
                MobileNo.add(inc - 1, getTextFromElement(lblRVTMobileCAccountName(inc)));
                AmtAndCurrency.add(inc - 1, getTextFromElement(lblRVTMobileCAmtAndCurrency(inc)).trim());
                Date.add(getTextFromElement(lblRVTMobileCDate(inc)));
            }

            //Validate based on text and numerals
            for (int inc = 0; inc < recordCount; inc++) {
                //Validate the account name
                if (!MobileNo.get(inc).isEmpty() && CommonUtils.containsNumericCharacters(MobileNo.get(inc))) {
                    addToReport(" Recent vishwa transactions of record number : '" + inc + "' where mobile number : '" + MobileNo.get(inc), Status.PASS);
                } else {
                    addToReport(" Failed to validate Recent vishva transactions of record number : '" + inc + "' where mobile number : '" + MobileNo.get(inc), Status.FAIL);
                    throw new RuntimeException("Error - Mobile number of Recent vishwa transactions not displayed");
                }

                //Validate currency and amount
                String[] CurrencyAndAmt = AmtAndCurrency.get(inc).split(" ");
                if (!AmtAndCurrency.get(inc).isEmpty() &&
                        Arrays.asList(currencyType).contains(CurrencyAndAmt[0]) &&
                        CommonUtils.containsNumericCharacters(CurrencyAndAmt[1])) {
                    addToReport(" Recent vishwa transactions of record number : '" + inc + "' where account name : '" + MobileNo.get(inc) + "' , currency and amount : '" + AmtAndCurrency.get(inc), Status.PASS);
                } else {
                    addToReport(" Failed to validate Recent vishwa transactions of record number : '" + inc + "' where currency and amount : '" + AmtAndCurrency.get(inc), Status.FAIL);
                    throw new RuntimeException("Error - Currency amd amount of Recent vishwa transactions not displayed");
                }

                //Validate the date
                String[] dateContent = Date.get(inc).split(" ");
                if (!Date.get(inc).isEmpty() && Date.get(inc).contains("at") &&
                        CommonUtils.containsAlphabaticCharacters(dateContent[0]) &&
                        CommonUtils.containsNumericCharacters(dateContent[1]) &&
                        CommonUtils.containsNumericCharacters(dateContent[2]) &&
                        CommonUtils.containsAlphAndNumCharacters(dateContent[4])) {
                    addToReport(" Recent vishwa transactions of record number : '" + inc + "' where account name : '" + MobileNo.get(inc) + "' , currency and amount : '" + AmtAndCurrency.get(inc) + "' and date : '" + Date.get(inc) + "'", Status.PASS);
                } else {
                    addToReport(" Failed to validate Recent vishwa transactions of record number : '" + inc + "' where date : '" + Date.get(inc), Status.FAIL);
                    throw new RuntimeException("Error - Incorrect date for Recent vishwa transactions displayed");
                }
            }
        } catch (Exception e) {
            addToReport("Recent vishawa transfer validation for mobile cash failed", Status.FAIL);
            throw new RuntimeException("Error - Recent vishawa transfer validation for mobile cash failed", e);
        }
    }

    /**
     * Validate favourite payee widget
     */
    public void validateFavouritePayeeWidget() {
        try {

            //Wait for loading icon to be invisible
            waitForElementToBeInvisible(lblLoadingIcon, VERY_SHORT_WAIT);

            //Declare list to extract from table
            ArrayList<String> AccNickName = new ArrayList<>();
            ArrayList<String> AccountNumber = new ArrayList<>();
            ArrayList<String> BankName = new ArrayList<>();

            //Obtain the record count
            int recordCount = isElementsPresentBy(lblFavouritePayeeWidgetRow);
            if (recordCount == 0) {
                addToReport("Favourite payee records are not displayed", Status.FAIL);
                throw new RuntimeException("Error - Favourite payee records are not displayed");
            }
            //Extract the latest records from the list
            for (int inc = 0; inc < recordCount; inc++) {
                AccNickName.add(inc, getTextFromElement(lblFavouritePayeeNickName(inc + 1)));
                AccountNumber.add(inc, getTextFromElement(lblFavouritePayeeRefernce(inc + 1)));
                BankName.add(inc, getTextFromElement(lblFavouritePayeeBankName(inc + 1)));
            }

            //Validate the account nickname
            int rCount = recordCount - 1;
            for (int inc = 0; inc < recordCount - 1; inc++) {

                //Validate the account name
                if (!AccNickName.get(inc).isEmpty()) {
                    addToReport(" Favourite payee nickname : '" + AccNickName.get(inc) + "' for the record number : '" + inc, Status.PASS, false);
                } else {
                    addToReport(" Failed to validate favourite payee nickname : '" + AccNickName.get(inc) + "' for the record number : '" + inc, Status.FAIL);
                    throw new RuntimeException("Error - Favourite payee is not displayed");
                }

                //Validate the bank name
                if (!BankName.get(inc).isEmpty() && CommonUtils.containsAlphabaticCharacters(BankName.get(inc).trim())) {
                    addToReport(" Favourite payee bank name : '" + BankName.get(inc) + "' for the record number : '" + inc, Status.PASS, false);
                } else {
                    addToReport(" Failed to validate favourite payee bank name : '" + BankName.get(inc) + "' for the record number : '" + inc, Status.FAIL);
                    throw new RuntimeException("Error - Favourite payee is not displayed");
                }

                //Validate the account number
                if (!AccountNumber.get(inc).isEmpty() && CommonUtils.containsNumericCharacters(AccountNumber.get(inc))) {
                    addToReport(" Favourite payee account number : '" + AccountNumber.get(inc) + "' for the record number : '" + inc, Status.PASS, false);
                } else {
                    addToReport(" Failed to validate favourite payee account name : '" + AccountNumber.get(inc) + "' for the record number : '" + inc, Status.FAIL);
                    throw new RuntimeException("Error - Favourite payee is not displayed");
                }
            }
            scrollDownPage();
            addToReport(" Favourite payee validation successful'", Status.PASS, true);
        } catch (Exception e) {
            addToReport("Recent vishawa transfer validation for favourite payee failed", Status.FAIL);
            throw new RuntimeException("Error - Recent vishawa transfer validation of favourite payee failed", e);
        }
    }

    /**
     * Navigate back to dashboard
     */
    public void navigateBackToDashboard() {
        waitFor(VERY_SHORT_WAIT);
        if (waitForElementPresence(btnCloseBillerPopup, VERY_SHORT_WAIT)) {
            clickOnElement(btnCloseBillerPopup);
            waitForElementToBeInvisible(btnCloseBillerPopup, SHORT_WAIT);
        }

        waitForElementPresence(btnDashboard);
        clickOnElement(btnDashboard);
        waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
        waitFor(VERY_SHORT_WAIT);
    }

    /**
     * Navigate back to dashboard while closing other windows
     */
    public void navigateBackToDashboardCloseOtherWindows() {
        closeAllExceptParentWindow();

        if (waitForElementPresence(btnCloseBillerPopup, VERY_SHORT_WAIT)) {
            clickOnElement(btnCloseBillerPopup);
            waitForElementToBeInvisible(btnCloseBillerPopup, MODERATE_WAIT);
        }

        waitForElementPresence(btnDashboard);
        clickOnElement(btnDashboard);
        waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
    }



    /**
     * Select quick actions
     *
     * @param quickActionButton - quick action button name
     */
    public void selectQuickActions(String quickActionButton) {

        switch (quickActionButton.toLowerCase()) {
            case "bill payment":
                clickOnElement(btnQActionsBillPayment);
                waitForElementToBeInvisible(btnQActionsBillPayment, MODERATE_WAIT);
                break;
            case "send money":
                clickOnElement(btnQActionsSendMoney);
                waitForElementToBeInvisible(btnQActionsSendMoney, MODERATE_WAIT);
                break;
            case "mobile cash":
                clickOnElement(btnQActionsMobileCash);
                waitForElementToBeInvisible(btnQActionsMobileCash, MODERATE_WAIT);
                break;
            case "open saving":
                clickOnElement(btnQActionsSavingsAccount);
                waitForElementToBeInvisible(btnQActionsSavingsAccount, MODERATE_WAIT);
                break;
            case "obtain":
                clickOnElement(btnQActionsObtainNewLoan);
                break;
            default:
                throw new IllegalArgumentException("Unable to click on quick action button :" + quickActionButton);
        }


    }

    /**
     * Navigate back to favourite payee
     */
    public void navigateToAddFavouritePayee() {
        waitForElementPresence(btnAddPayee);
        clickOnElement(btnAddPayee);
    }

    /**
     * Capture first record details of favourite biller widget
     */
    public String[] getFBWidgetFirstRecordDetails() {
        String[] FRecordData;
        waitForElementPresence(imgAdvertisement,VERY_LONG_WAIT);
        try {

            //Click recent transfer and wait till account name appear
            waitForElementPresence(lblRVTPaymentAccountName);

            //Declare string to extract from widget
            String TempName = getTextFromElement(lblFavouriteBillerTempName(1));
            String BName = getTextFromElement(lblFavouriteBillerBName(1));
            String FieldName = getTextFromElement(lblFavouriteBillerFieldName(1));

            FRecordData = new String[]{TempName, BName, FieldName};
            //Validate the template name
            if (!TempName.isEmpty()) {
                addToReport(" Favourite biller template name : '" + TempName + "' for the record number : 1 '", Status.PASS, false);
            } else {
                addToReport(" Failed to validate favourite biller template name : '" + TempName + "' for the record number : 1'", Status.FAIL);
                throw new RuntimeException("Error - Favourite biller template name is not displayed");
            }
            //Validate the biller name
            if (!BName.isEmpty() && CommonUtils.containsAlphabaticCharacters(BName.trim())) {
                addToReport(" Favourite biller name : '" + BName + "' for the record number : 1'", Status.PASS, false);
            } else {
                addToReport(" Failed to validate favourite biller name : '" + BName + "' for the record number : 1'", Status.FAIL);
                throw new RuntimeException("Error - Favourite biller is not displayed");
            }

            //Validate the field name
            if (!FieldName.isEmpty() && (CommonUtils.containsNumericCharacters(FieldName) || CommonUtils.containsAlphabaticCharacters(FieldName))) {
                addToReport(" Favourite biller field name : '" + FieldName + "' for the record number : 1'", Status.PASS, true);
            } else {
                addToReport(" Failed to validate favourite field name : '" + FieldName + "' for the record number : 1'", Status.FAIL);
                throw new RuntimeException("Error - Favourite field name is not displayed");
            }

        } catch (Exception e) {
            addToReport("Recent vishawa transfer data extraction for favourite biller failed", Status.FAIL);
            throw new RuntimeException("Error - Recent vishawa transfer data extraction of favourite biller failed", e);
        }

        //Select the first record
        clickOnElement(lblFavouriteBillerTempName(1));
        waitFor(6);

        return FRecordData;
    }

    /**
     * Capture first record details of favourite payee widget
     *
     * @return - Favourite payee records
     */
    public String[] getFPWidgetFirstRecordDetails() {
        String[] FRecordData;
        try {

            //Click recent transfer and wait till account name appear
            waitForElementPresence(lblRVTPaymentAccountName);


            //Declare string to extract from widget
            String NickName = getTextFromElement(lblFavouritePayeeNickName(1));
            String ANumber = getTextFromElement(lblFavouritePayeeRefernce(1));
            String BName = getTextFromElement(lblFavouritePayeeBankName(1));

            FRecordData = new String[]{NickName, BName, ANumber};
            //Validate the account name
            if (!NickName.isEmpty()) {
                addToReport(" Favourite payee nickname : '" + NickName + "' for the record number : 1'", Status.PASS, false);
            } else {
                addToReport(" Failed to validate favourite payee nickname : '" + NickName + "' for the record number : 1'", Status.FAIL);
                throw new RuntimeException("Error - Favourite payee is not displayed");
            }

            //Validate the bank name
            if (!BName.isEmpty() && CommonUtils.containsAlphabaticCharacters(BName.trim())) {
                addToReport(" Favourite payee bank name : '" + BName + "' for the record number : 1'", Status.PASS, true);
            } else {
                addToReport(" Failed to validate favourite payee bank name : '" + BName + "' for the record number : 1'", Status.FAIL);
                throw new RuntimeException("Error - Favourite payee is not displayed");
            }

            //Validate the account number
            if (!ANumber.isEmpty() && CommonUtils.containsNumericCharacters(ANumber)) {
                addToReport(" Favourite payee account number : '" + ANumber + "' for the record number : 1'", Status.PASS, false);
            } else {
                addToReport(" Failed to validate favourite payee account name : '" + ANumber + "' for the record number : 1'", Status.FAIL);
                throw new RuntimeException("Error - Favourite payee is not displayed");
            }

        } catch (Exception e) {
            addToReport("Recent vishawa transfer data extraction for favourite payee failed", Status.FAIL);
            throw new RuntimeException("Error - Recent vishawa transfer data extraction of favourite payee failed", e);
        }

        //Select the first record
        clickOnElement(lblFavouritePayeeNickName(1));
        waitFor(15);
        waitForElementPresence(lblQFTSavingsAccountName, MODERATE_WAIT);

        return FRecordData;
    }

    /**
     * Validate the availability of 6 key points in savings account
     *
     * @param loanAccountNumber - loan account number
     * @param loanAmt           - loan account grant
     * @param outstanding       - loan account outstanding
     * @param loanPeriod        - loan account period
     * @param interestRate      - loan account interest rate
     */
    public void validateLoanAccountAtDashboard(String loanAccountNumber, String loanAmt, String outstanding, String loanPeriod, String interestRate) {
        try {
            //wait for the loading icon to diminish
            waitForLoadingToBeInvisible();
            //waitForElementPresence(lblLoadingIcon);
            waitForElementToBeInvisible(lblLoadingIcon, MODERATE_WAIT);

            //click button Loans
            if (isElementClickable(btnLoans)) {
                clickOnElement(btnLoans);
            } else {
                addToReport("Unable to find loans button", Status.FAIL);
                throw new RuntimeException("Error - Loans button is not found");
            }

            //wait for the loading icon to diminish
            waitForLoadingToBeInvisible();
            //waitForElementPresence(lblLoadingIcon);
            waitForElementToBeInvisible(lblLoadingIcon, MODERATE_WAIT);

            //Validate loan account number
            String[] LoanAccountNo = CommonUtils.splitText(getTextFromElement(lblLoanACNumber), "\n");
            if (LoanAccountNo[0].equalsIgnoreCase(loanAccountNumber)) {
                addToReport("Successfully validated loan account number : '" + LoanAccountNo[0] + "'", Status.PASS, false);
            } else {
                addToReport("Loan Account number : '" + LoanAccountNo[0] + "' is not validated", Status.FAIL);
                throw new RuntimeException("Error - Loan Account number validation failed");
            }

            //Validate loan grant
            String Grant = getTextFromElement(lblLoanGrantAmt);
            if (Grant.equalsIgnoreCase(loanAmt)) {
                addToReport("Successfully validated grant : '" + Grant + "'", Status.PASS, false);
            } else {
                addToReport("Grant amt : '" + Grant + "' is not validated", Status.FAIL);
                throw new RuntimeException("Error - Grant validation failed");
            }

            //Validate Outstanding Amount
            String CurrOutstanding = getTextFromElement(lblLoanCurrOutstanding);
            if (CurrOutstanding.equalsIgnoreCase(outstanding)) {
                addToReport("Successfully validated outstanding amount : '" + CurrOutstanding + "'", Status.PASS, false);
            } else {
                addToReport("Outstanding amount : '" + CurrOutstanding + "' is not validated", Status.FAIL);
                throw new RuntimeException("Error - Outstanding validation failed");
            }

            //Validate Loan period
            String[] LoanPeriod = CommonUtils.splitText(getTextFromElement(lblLoanPeriod), " ");
            if (LoanPeriod[0].equalsIgnoreCase(loanPeriod)) {
                addToReport("Successfully validated loan period : '" + LoanPeriod[0] + "'", Status.PASS, false);
            } else {
                addToReport("Loan period : '" + LoanPeriod[0] + "' is not validated", Status.FAIL);
                throw new RuntimeException("Error - Loan period validation failed");
            }

            //Validate Interest Rate
            String InterestRate = getTextFromElement(lblLoanInterestRate);
            if (InterestRate.equalsIgnoreCase(interestRate)) {
                addToReport("Successfully validated interest rate : '" + InterestRate + "'", Status.PASS, false);
            } else {
                addToReport("Interest rate : '" + InterestRate + "' is not validated", Status.FAIL);
                throw new RuntimeException("Error - Interest rate validation failed");
            }
            addToReport("Loan account validations complete", Status.PASS, true);
        } catch (Exception e) {
            addToReport("Loan account key points validation failed", Status.FAIL);
            throw new RuntimeException("Failed - Loan account key points validation failed", e);
        }
    }

    /**
     * Validate favourite biller widget
     */
    public void validateFavouriteBillerWidget() {
        try {

            //Wait for loading icon to be invisible
            waitForElementToBeInvisible(lblLoadingIcon, MODERATE_WAIT);

            //Declare list to extract from table
            ArrayList<String> TemplateName = new ArrayList<>();
            ArrayList<String> BillerName = new ArrayList<>();
            ArrayList<String> FieldName = new ArrayList<>();

            //Obtain the record count
            int recordCount = isElementsPresentBy(lblFavouriteBillerWidgetRow);
            if (recordCount == 0) {
                addToReport("Favourite biller records are not displayed", Status.FAIL);
                throw new RuntimeException("Error - Favourite biller records are not displayed");
            }
            //Extract the latest records from the list
            for (int inc = 0; inc < recordCount - 1; inc++) {
                TemplateName.add(inc, getTextFromElement(lblFavouriteBillerTempName(inc + 1)));
                BillerName.add(inc, getTextFromElement(lblFavouriteBillerBName(inc + 1)));
                FieldName.add(inc, getTextFromElement(lblFavouriteBillerFieldName(inc + 1)));
            }

            //Validate the record values
            int rCount = 1;
            for (int inc = 0; inc < recordCount - 1; inc++) {

                //Validate the template name
                if (!TemplateName.get(inc).isEmpty()) {
                    addToReport(" Favourite biller template name : '" + TemplateName.get(inc) + "' for the record number : '" + rCount, Status.PASS, false);
                } else {
                    addToReport(" Failed to validate favourite biller template name : '" + TemplateName.get(inc) + "' for the record number : '" + rCount, Status.FAIL);
                    throw new RuntimeException("Error - Favourite biller is not displayed");
                }

                //Validate the biller name
                if (!BillerName.get(inc).isEmpty() && CommonUtils.containsAlphabaticCharacters(BillerName.get(inc).trim())) {
                    addToReport(" Favourite biller name : '" + BillerName.get(inc) + "' for the record number : '" + rCount, Status.PASS, false);
                } else {
                    addToReport(" Failed to validate favourite biller name : '" + BillerName.get(inc) + "' for the record number : '" + rCount, Status.FAIL);
                    throw new RuntimeException("Error - Favourite biller is not displayed");
                }

                //Validate the field name
                if (!FieldName.get(inc).isEmpty() && (CommonUtils.containsNumericCharacters(FieldName.get(inc)) || CommonUtils.containsAlphabaticCharacters(FieldName.get(inc)))) {
                    if (inc > 5) {
                        scrollToWebElement(lblFavouriteBillerFieldName(inc));
                    }
                    addToReport(" Favourite biller field name : '" + FieldName.get(inc) + "' for the record number : '" + rCount, Status.PASS, true);
                } else {
                    addToReport(" Failed to validate favourite biller field name : '" + FieldName.get(inc) + "' for the record number : '" + rCount, Status.FAIL);
                    throw new RuntimeException("Error - Favourite biller field name is not displayed");
                }
                rCount++;
            }
        } catch (Exception e) {
            addToReport("Recent vishawa transfer validation for favourite biller failed", Status.FAIL);
            throw new RuntimeException("Error - Recent vishawa transfer validation of favourite biller failed", e);
        }
    }

    /**
     * Validate top bar functionality
     *
     * @param btnNameSettings - button text settings
     * @param btnNameLogout   - button text logout
     * @param otp             - otp number
     * @param popupText       - popup text on logout
     * @param btnBack         - button text back
     */
    public void validateFunctionalityOfTopBarIconsInDashboard(String btnNameSettings, String btnNameLogout, String otp, String popupText, String btnBack) {
        try {
            //Wait for advertisement image to appear
            waitForElementPresence(imgAdvertisement, LONG_WAIT);

            /*Add financial calandar once it's deployed*/

            //validate dashboard page
            boolean accountsOrCards = isElementPresentBy(lblAccountsOrCards);
            if (accountsOrCards) {
                addToReport("Successfully validated dashboard page", Status.PASS, true);
            } else {
                addToReport("Dashboard page is not visible.", Status.FAIL, true);
                throw new RuntimeException("Error - Dashboard page is not visible.");
            }

            //validate messages page
            boolean icnMessages = isElementPresentBy(icnMessage);
            if (icnMessages) {
                addToReport("Successfully validated message icon", Status.PASS, false);
                clickOnElement(icnMessage);
                boolean messagelbl = isElementPresentBy(lblMessage);
                if (messagelbl) {
                    addToReport("Successfully validated messages page", Status.PASS, true);
                } else {
                    addToReport("Messages page is not visible.", Status.FAIL, true);
                    throw new RuntimeException("Error - Messages page is not visible.");
                }
            } else {
                addToReport("Dashboard page is not visible.", Status.FAIL, true);
                throw new RuntimeException("Error - Dashboard page is not visible.");
            }
            navigateBackToDashboard();

            //Validate notifications
            /*Update once notifications are deployed*/
            boolean icnNotifications = isElementPresentBy(icnNotification);
            if (icnNotifications) {
                addToReport("Successfully validated notifications icon", Status.PASS, false);
            } else {
                addToReport("Notifications icon is not visible.", Status.FAIL, true);
                throw new RuntimeException("Error - Notifications icon is not visible.");
            }

            //validate user profile icon and click
            boolean userProfileIcon = isElementPresentBy(iconUser);
            if (userProfileIcon) {
                clickOnElement(iconUser);
                addToReport("Successfully clicked on user profile icon on top navigation bar.", Status.PASS);
            } else {
                addToReport("User profile icon is not visible.", Status.FAIL);
                throw new RuntimeException("Error - User profile icon is not visible.");
            }

            //validate settings functionality
            boolean settingsButton = isElementPresentBy(btnUserIcnDynamic(btnNameSettings));
            if (settingsButton) {
                waitForElementPresence(btnUserIcnDynamic(btnNameSettings));
                clickOnElement(btnUserIcnDynamic(btnNameSettings));
                addToReport("Successfully clicked on the settings button.", Status.PASS, false);

                waitForElementToBeClickable(tfOTP(1),LONG_WAIT);
                //Enter OTP values and continue
                sendKeysToElement(tfOTP(1), String.valueOf(otp));
                addToReport("Successfully entered OTP", Status.PASS, true);
                clickOnElement(btnConfirm);

                //validate settings label
                boolean labelSettings = isElementPresentBy(lblVishwaAccountSettings);
                if (labelSettings) {
                    addToReport("Successfully validated settings page", Status.PASS, true);
                } else {
                    addToReport("Settings page is not validated", Status.FAIL);
                    throw new RuntimeException("Error - Settings page is not validated");
                }
            } else {
                addToReport("Settings button inside user dropdown is not visible.", Status.FAIL);
                throw new RuntimeException("Error - Settings button inside user dropdown is not visible. ");
            }
            navigateBackToDashboard();
            //validate user profile icon and click
            boolean ProfileIcon = isElementPresentBy(iconUser);
            if (ProfileIcon) {
                clickOnElement(iconUser);
                addToReport("Successfully clicked on user profile icon on top navigation bar.", Status.PASS);
            } else {
                addToReport("User profile icon is not visible.", Status.FAIL);
                throw new RuntimeException("Error - User profile icon is not visible.");
            }

            //validate logout button
            boolean logoutButton = isElementPresentBy(btnUserIcnDynamic(btnNameLogout));
            if (logoutButton) {
                clickOnElement(btnUserIcnDynamic(btnNameLogout));
                addToReport("Successfully clicked on the logout button.", Status.PASS, true);
            } else {
                addToReport("Logout button inside user dropdown is not visible", Status.FAIL);
                throw new RuntimeException("Error - Logout button inside user dropdown is not visible ");
            }
            //validate popup
            boolean popup = isElementPresentBy(txtlogoutPopup(popupText));
            if (popup) {
                addToReport("'" + popupText + "' Logout popup is visible.", Status.PASS);
            } else {
                addToReport("'" + popupText + "' Logout popup is not visible.", Status.FAIL);
                throw new RuntimeException("'" + popupText + "' Logout pop is not visible.");
            }

            //Validate back button
            boolean confirmButton = isElementPresentBy(getElementByTypeAndText(DashboardPage.ElementType.button, btnBack));
            if (confirmButton) {
                addToReport("'" + btnBack + "'Logout button is visible.", Status.PASS);
                clickOnElement(getElementByTypeAndText(DashboardPage.ElementType.button, btnBack));
                addToReport("'" + btnBack + "'Logout button is clicked.", Status.PASS);
            } else {
                addToReport("'" + btnBack + "'Logout button is not visible.", Status.FAIL);
                throw new RuntimeException("'" + btnBack + "'Logout button is not visible.");
            }

        } catch (Exception e) {
            addToReport("Recent vishawa top bar functionality failed", Status.FAIL);
            throw new RuntimeException("Error - Top bar functionality failed", e);
        }
        waitForElementPresence(imgAdvertisement, LONG_WAIT);
    }

    /**
     * Validate recent vishwa transactions transfer widget data retrieval
     *
     * @param currencyType - currency types compared from constant array
     * @param stat         - status of transaction
     */
    public void validateRVTTransferRetrievalOfTransfersOwnAcc(String[] currencyType, String stat) {
        try {

            //wait for the loading icon to diminish
            waitForLoadingToBeInvisible();
            waitForElementPresence(lblLoadingIcon);
            waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);

            //select transfer tab
            clickOnElement(btnTransfer);

            //check if own accounts are available
            int recordCount = isElementsPresentBy(lblRVTTransferRecordOA);
            if (recordCount == 0) {
                addToReport(" Recent vishwa transactions couldn't find any own account recent transfers", Status.FAIL);
                throw new RuntimeException("Error - couldn't find any own account recent transfers under recent vishwa transactions");
            } else {

                //Declare list to extract from table
                String AccName = getTextFromElement(lblRVTTransferRecordOAName(1));
                String Date = getTextFromElement(lblRVTTransferRecordOADate(1));
                String AmtAndCurrency = getTextFromElement(lblRVTTransferRecordOAAmt(1));

                //Select the record for validation
                clickOnElement(lblRVTTransferRecordOAName(1));

                //validate popup
                boolean popup = isElementPresentBy(lblRVTTransferTransactionDetailsPopup);
                if (popup) {
                    addToReport("Transfer transaction details popup is visible", Status.PASS);
                } else {
                    addToReport("Transfer transaction details popup is not visible", Status.FAIL);
                    throw new RuntimeException("Error - Transfer transaction details popup is not visible");
                }

                // Store field values to local variables for validation and log without screenshot
                String Refer = getTextFromElement(lblRVTTransferPopupRecords(1, 1));
                addToReport("Extracted Reference: " + Refer, Status.INFO, false);

                String FromAccount = getTextFromElement(lblRVTTransferPopupRecords(2, 1));
                addToReport("Extracted From Account: " + FromAccount, Status.INFO, false);

                String ToAccount = getTextFromElement(lblRVTTransferPopupRecords(2, 2));
                addToReport("Extracted To Account: " + ToAccount, Status.INFO, false);

                String TransactionCategory = getTextFromElement(lblRVTTransferPopupRecords(2, 3));
                addToReport("Extracted Transaction Category: " + TransactionCategory, Status.INFO, false);

                String status = getTextFromElement(lblRVTTransferPopupRecords(2, 4));
                addToReport("Extracted Status: " + status, Status.INFO, false);

                String TransactionTime = getTextFromElement(lblRVTTransferPopupRecords(2, 5));
                addToReport("Extracted Transaction Time: " + TransactionTime, Status.INFO, false);

                String Remarks = getTextFromElement(lblRVTTransferPopupRecords(2, 6));
                addToReport("Extracted Remarks: " + Remarks, Status.INFO, false);

                String Amount = getTextFromElement(lblRVTTransferPopupRecords(2, 9));
                addToReport("Extracted Amount: " + Amount, Status.INFO, false);

                String[] ref = Refer.split(" ");
                addToReport("Split Reference into parts: " + Arrays.toString(ref), Status.INFO, false);

                // Validate the content based on the extracted values from dashboard
                //Validate the account name
                if (TransactionCategory.contains(AccName) &&
                        CommonUtils.containsAlphabaticCharacters(AccName)) {
                    addToReport(" Recent vishwa transactions transfers from account : '" + FromAccount + "' is validated ", Status.PASS, false);
                } else {
                    addToReport(" Failed to validate Recent vishwa transactions from account : '" + FromAccount, Status.FAIL);
                }

                //Validate currency and amount
                String[] CurrencyAndAmt = Amount.split(" ");
                if (Arrays.asList(currencyType).contains(CurrencyAndAmt[0]) &&
                        AmtAndCurrency.equalsIgnoreCase(Amount)) {
                    addToReport(" Validated recent vishwa transactions currency and amount : '" + AmtAndCurrency, Status.PASS, false);
                } else {
                    addToReport(" Failed to validate Recent vishwa transactions currency and amount : '" + AmtAndCurrency, Status.FAIL, true);
                }

                //Validate date and time
                if (Date.equalsIgnoreCase(TransactionTime)) {
                    addToReport(" Validated recent vishwa transactions date : '" + TransactionTime, Status.PASS, false);
                } else {
                    addToReport(" Failed to validate recent vishwa transactions date : '" + TransactionTime, Status.FAIL, true);
                }

                //Validate reference
                if (CommonUtils.containsNumericCharacters(ref[2])) {
                    addToReport(" Validated recent vishwa transactions reference : '" + ref[2], Status.PASS, false);
                } else {
                    addToReport(" Failed to validate recent vishwa transactions reference : '" + ref[2], Status.FAIL, true);
                }

                //validate to account
                if (CommonUtils.containsNumericCharacters(ToAccount)) {
                    addToReport(" Validated recent vishwa transactions to account : '" + ToAccount, Status.PASS, false);
                } else {
                    addToReport(" Failed to validate recent vishwa transactions to account : '" + ToAccount, Status.FAIL, true);
                }

                //validate status
                if (status.equalsIgnoreCase(stat)) {
                    addToReport(" Validated recent vishwa transactions status : '" + status, Status.PASS, false);
                } else {
                    addToReport(" Failed to validate recent vishwa transactions status : '" + status, Status.FAIL, true);
                }

                //validate remark
                if (CommonUtils.containsAlphabaticCharacters(Remarks)) {
                    addToReport(" Validated recent vishwa transactions remark : '" + Remarks, Status.PASS, true);
                } else {
                    addToReport(" Failed to validate recent vishwa transactions remark : '" + Remarks, Status.FAIL, true);
                }

                clickOnElement(btnClosePopup);


            }
        } catch (Exception e) {
            addToReport("Recent vishawa transfer validation of moble cash failed", Status.FAIL);
            throw new RuntimeException("Error - Validation of transfer under recent vishawa transactions failed", e);
        }
    }

    /**
     * Validate recent vishwa transactions transfer widget data retrieval
     *
     * @param currencyType        - currency types compared from constant array
     * @param stat                - status of transaction
     * @param transactionCategory - Transaction category
     * @param accountType         - Type of the account
     */
    public void validateRVTTransferRetrievalOfTransfersOtherAcc(String[] currencyType, String stat, String transactionCategory, String accountType) {
        try {

            //wait for the loading icon to diminish
            waitForLoadingToBeInvisible();
            waitForElementPresence(lblLoadingIcon);
            waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);

            //select transfer tab
            clickOnElement(btnTransfer);

            //Obtain the record count
            int recCount = isElementsPresentBy(lblRVTTransferRecord);
            if (recCount != 10) {
                addToReport(" Recent vishwa transactions displayed is not 10", Status.FAIL);
                throw new RuntimeException("Error - Incorrect number of Recent vishwa transactions displayed");
            }

            //Obtain to account
            String toAcc = "";
            //Extract the recent record from the list
            for (int inc = 1; inc <= recCount; inc++) {
                if (!getTextFromElement(lblRVTAccountName(inc)).equals(accountType)) {
                    toAcc = getTextFromElement(lblRVTAccountName(inc));
                    break;
                }
            }

            //Check if there is third party transaction record available
            if (toAcc.isEmpty()) {
                addToReport(" Recent vishwa transactions transfers doesn't have other accounts", Status.FAIL);
                throw new RuntimeException("Error - Recent vishwa transactions transfers doesn't have other accounts");
            }

            //Check if own accounts are available
            int recordCount = isElementsPresentBy(lblRVTTransferRecordOtherAccAmt(toAcc, 1));
            if (recordCount == 0) {
                addToReport(" Recent vishwa transactions couldn't find any own account recent transfers", Status.FAIL);
                throw new RuntimeException("Error - couldn't find any own account recent transfers under recent vishwa transactions");
            } else {

                //Declare list to extract from table
                String AccName = getTextFromElement(lblRVTTransferRecordOtherAccName(toAcc, 1)).trim();
                String Date = getTextFromElement(lblRVTTransferRecordOtherAccDate(toAcc, 1)).trim();
                String AmtAndCurrency = getTextFromElement(lblRVTTransferRecordOtherAccAmt(toAcc, 1)).trim();

                //Select the record for validation
                clickOnElement(lblRVTTransferRecordOtherAccName(toAcc, 1));

                //validate popup
                boolean popup = isElementPresentBy(lblRVTTransferTransactionDetailsPopup);
                if (popup) {
                    addToReport("Transfer transaction details popup is visible", Status.PASS);
                } else {
                    addToReport("Transfer transaction details popup is not visible", Status.FAIL);
                    throw new RuntimeException("Error - Transfer transaction details popup is not visible");
                }

                //Store field values to local variables for validation
                String Refer = getTextFromElement(lblRVTTransferPopupRecords(1, 1));
                String FromAccount = getTextFromElement(lblRVTTransferPopupRecords(2, 1));
                String ToAccount = getTextFromElement(lblRVTTransferPopupRecords(2, 2));
                String TransactionCategory = getTextFromElement(lblRVTTransferPopupRecords(2, 3));
                String status = getTextFromElement(lblRVTTransferPopupRecords(2, 4));
                String TransactionTime = getTextFromElement(lblRVTTransferPopupRecords(2, 5));
                String Remarks = getTextFromElement(lblRVTTransferPopupRecords(2, 6));
                String Amount = getTextFromElement(lblRVTTransferPopupRecords(2, 9));
                String[] ref = Refer.split(" ");

                // Validate the content based on the extracted values from dashboard
                //Validate the from account number
                if (CommonUtils.containsNumericCharacters(AccName)) {
                    addToReport(" Recent vishwa transactions transfers from account : '" + FromAccount + "' is validated ", Status.PASS, false);
                } else {
                    addToReport(" Failed to validate Recent vishwa transactions from account : '" + FromAccount, Status.FAIL);
                }

                //Validate the transaction category
                if (TransactionCategory.equalsIgnoreCase(transactionCategory)) {
                    addToReport(" Recent vishwa transactions transaction category  : '" + TransactionCategory + "' is validated ", Status.PASS, false);
                } else {
                    addToReport(" Failed to validate Recent vishwa transactions transaction category : '" + TransactionCategory, Status.FAIL);
                }

                //Validate currency and amount
                String[] CurrencyAndAmt = Amount.split(" ");
                if (Arrays.asList(currencyType).contains(CurrencyAndAmt[0]) &&
                        AmtAndCurrency.equalsIgnoreCase(Amount)) {
                    addToReport(" Validated recent vishwa transactions currency and amount : '" + AmtAndCurrency, Status.PASS, false);
                } else {
                    addToReport(" Failed to validate Recent vishwa transactions currency and amount : '" + AmtAndCurrency, Status.FAIL);
                }

                //Validate date and time
                if (Date.equalsIgnoreCase(TransactionTime)) {
                    addToReport(" Validated recent vishwa transactions date : '" + TransactionTime, Status.PASS, false);
                } else {
                    addToReport(" Failed to validate recent vishwa transactions date : '" + TransactionTime, Status.FAIL, false);
                }

                //Validate reference
                if (CommonUtils.containsNumericCharacters(ref[2])) {
                    addToReport(" Validated recent vishwa transactions reference : '" + ref[2], Status.PASS, false);
                } else {
                    addToReport(" Failed to validate recent vishwa transactions reference : '" + ref[2], Status.FAIL, false);
                }

                //validate to account
                if (CommonUtils.containsNumericCharacters(ToAccount)) {
                    addToReport(" Validated recent vishwa transactions to account : '" + ToAccount, Status.PASS, false);
                } else {
                    addToReport(" Failed to validate recent vishwa transactions to account : '" + ToAccount, Status.FAIL, false);
                }

                //validate status
                if (status.equalsIgnoreCase(stat)) {
                    addToReport(" Validated recent vishwa transactions status : '" + status, Status.PASS, false);
                } else {
                    addToReport(" Failed to validate recent vishwa transactions status : '" + status, Status.FAIL, false);
                }

                //validate remark
                if (CommonUtils.containsAlphabaticCharacters(Remarks)) {
                    addToReport(" Validated recent vishwa transactions remark : '" + Remarks, Status.PASS, false);
                } else {
                    addToReport(" Failed to validate recent vishwa transactions remark : '" + Remarks, Status.FAIL, false);
                }

                clickOnElement(btnClosePopup);

            }
        } catch (Exception e) {
            addToReport("Recent vishawa transfer validation of transactions failed", Status.FAIL);
            throw new RuntimeException("Error - Validation of transfer under recent vishawa transactions failed", e);
        }
    }

    /**
     * Validate recent vishwa transactions payment widget data retrieval
     *
     * @param currencyType - currency types compared from constant array
     * @param stat         - status of transaction
     * @param toAcc        - To account
     */
    public void validateRVTTransferRetrievalOfPayment(String[] currencyType, String stat, String toAcc) {
        try {

            //wait for the loading icon to diminish
            waitForLoadingToBeInvisible();
            waitForElementPresence(lblLoadingIcon);
            waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);

            //select transfer tab
            clickOnElement(btnPayment);

            //check if own accounts are available
            int recordCount = isElementsPresentBy(lblRVTPaymentsRecords);
            if (recordCount == 0) {
                addToReport(" Recent vishwa transactions couldn't find any own account recent transfers", Status.FAIL);
                throw new RuntimeException("Error - couldn't find any own account recent transfers under recent vishwa transactions");
            } else {

                //Declare list to extract from table
                String To = getTextFromElement(lblRVTPaymentTo(toAcc, 1)).trim();
                String Date = getTextFromElement(lblRVTPaymentDate(toAcc, 1)).trim();
                String AmtAndCurrency = getTextFromElement(lblRVTPaymentAmt(toAcc, 1)).trim();

                //Select the record for validation
                clickOnElement(lblRVTPaymentTo(toAcc, 1));

                //validate popup
                boolean popup = isElementPresentBy(lblRVTPaymentDetailsPopup);
                if (popup) {
                    addToReport("Payment transaction details popup is visible", Status.PASS);
                } else {
                    addToReport("Payment transaction details popup is not visible", Status.FAIL);
                    throw new RuntimeException("Error - Payment transaction details popup is not visible");
                }

                //Store field values to local variables for validation
                String Refer = getTextFromElement(lblRVTTransferPopupRecords(1, 1));
                String FromAccount = getTextFromElement(lblRVTTransferPopupRecords(2, 1));
                String ToAccount = getTextFromElement(lblRVTTransferPopupRecords(2, 2));
                String status = getTextFromElement(lblRVTTransferPopupRecords(2, 3));
                String TransactionTime = getTextFromElement(lblRVTTransferPopupRecords(2, 4));
                String Amount = getTextFromElement(lblRVTTransferPopupRecords(2, 5));
                String[] ref = Refer.split(" ");

                // Validate the content based on the extracted values from dashboard
                //Validate the to account number
                if (CommonUtils.containsNumericCharacters(FromAccount)) {
                    addToReport(" Recent vishwa transactions payment from account  : '" + FromAccount + "' is validated ", Status.PASS, false);
                } else {
                    addToReport(" Failed to validate Recent vishwa transactions from account : '" + FromAccount, Status.FAIL);
                }

                //Validate currency and amount
                String[] CurrencyAndAmt = Amount.split(" ");
                if (Arrays.asList(currencyType).contains(CurrencyAndAmt[0]) &&
                        AmtAndCurrency.equalsIgnoreCase(Amount)) {
                    addToReport(" Validated recent vishwa payment currency and amount : '" + AmtAndCurrency, Status.PASS, false);
                } else {
                    addToReport(" Failed to validate Recent vishwa payment currency and amount : '" + AmtAndCurrency, Status.FAIL);
                }

                //Validate date and time
                if (Date.equalsIgnoreCase(TransactionTime)) {
                    addToReport(" Validated recent vishwa payment date : '" + TransactionTime, Status.PASS, false);
                } else {
                    addToReport(" Failed to validate recent vishwa payment date : '" + TransactionTime, Status.FAIL, false);
                }

                //Validate reference
                if (CommonUtils.containsNumericCharacters(ref[2])) {
                    addToReport(" Validated recent vishwa payment reference : '" + ref[2], Status.PASS, false);
                } else {
                    addToReport(" Failed to validate recent vishwa payment reference : '" + ref[2], Status.FAIL, false);
                }

                //validate to account
                if (CommonUtils.containsAlphabaticCharacters(ToAccount)) {
                    addToReport(" Validated recent vishwa payment to account : '" + ToAccount, Status.PASS, false);
                } else {
                    addToReport(" Failed to validate recent vishwa payment to account : '" + ToAccount, Status.FAIL, false);
                }

                //validate status
                if (status.equalsIgnoreCase(stat)) {
                    addToReport(" Validated recent vishwa payment status : '" + status, Status.PASS, false);
                } else {
                    addToReport(" Failed to validate recent vishwa payment status : '" + status, Status.FAIL, false);
                }

                clickOnElement(btnClosePopup);


            }
        } catch (Exception e) {
            addToReport("Recent vishawa transfer validation of transactions failed", Status.FAIL);
            throw new RuntimeException("Error - Validation of transfer under recent vishawa transactions failed", e);
        }
    }

    /**
     * Validate recent vishwa transactions mobile cash widget data retrieval
     *
     * @param currencyType - currency types compared from constant array
     * @param stat         - status of transaction
     */
    public void validateRVTTransferRetrievalOfMobileCash(String[] currencyType, String stat) {
        try {

            //wait for the loading icon to diminish
            waitForLoadingToBeInvisible();
            waitForElementPresence(lblLoadingIcon);
            waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);

            //select transfer tab
            clickOnElement(btnMobileCash);

            //check if own accounts are available
            int recordCount = isElementsPresentBy(lblRVTTransferRecord);
            if (recordCount == 0) {
                addToReport(" Recent vishwa transactions couldn't find any own account recent transfers", Status.FAIL);
                throw new RuntimeException("Error - couldn't find any own account recent transfers under recent vishwa transactions");
            } else {

                //Declare list to extract from table
                String ToAccName = getTextFromElement(lblRVTMobileCAccountName(1));
                String AmtAndCurrency = getTextFromElement(lblRVTMobileCAmtAndCurrency(1));
                String Date = getTextFromElement(lblRVTMobileCDate(1));

                //Select the first record for validation
                clickOnElement(lblRVTMobileCAccountName(1));

                //validate popup
                boolean popup = isElementPresentBy(lblRVTTransferTransactionDetailsPopup);
                if (popup) {
                    addToReport("Transfer transaction details popup is visible", Status.PASS);
                } else {
                    addToReport("Transfer transaction details popup is not visible", Status.FAIL);
                    throw new RuntimeException("Error - Transfer transaction details popup is not visible");
                }

                //Store field values to local variables for validation
                String Refer = getTextFromElement(lblRVTTransferPopupRecords(1, 1));
                String FromAccount = getTextFromElement(lblRVTTransferPopupRecords(2, 1));
                String ToAccount = getTextFromElement(lblRVTTransferPopupRecords(2, 2));
                String status = getTextFromElement(lblRVTTransferPopupRecords(2, 3));
                String TransactionTime = getTextFromElement(lblRVTTransferPopupRecords(2, 4));
                String Amount = getTextFromElement(lblRVTTransferPopupRecords(2, 5));
                String[] ref = Refer.split(" ");

                // Validate the content based on the extracted values from dashboard
                //Validate the from account
                if (CommonUtils.containsNumericCharacters(FromAccount)) {
                    addToReport(" Recent vishwa transactions transfers from account : '" + FromAccount + "' is validated ", Status.PASS, false);
                } else {
                    addToReport(" Failed to validate Recent vishwa transactions from account : '" + FromAccount, Status.FAIL);
                }

                //Validate currency and amount
                String[] CurrencyAndAmt = Amount.split(" ");
                if (Arrays.asList(currencyType).contains(CurrencyAndAmt[0]) &&
                        AmtAndCurrency.equalsIgnoreCase(Amount)) {
                    addToReport(" Validated recent vishwa transactions currency and amount : '" + AmtAndCurrency, Status.PASS, false);
                } else {
                    addToReport(" Failed to validate Recent vishwa transactions currency and amount : '" + AmtAndCurrency, Status.FAIL);
                }

                //Validate date and time
                if (Date.equalsIgnoreCase(TransactionTime)) {
                    addToReport(" Validated recent vishwa transactions date : '" + TransactionTime, Status.PASS, false);
                } else {
                    addToReport(" Failed to validate recent vishwa transactions date : '" + TransactionTime, Status.FAIL, false);
                }

                //Validate reference
                if (CommonUtils.containsNumericCharacters(ref[2])) {
                    addToReport(" Validated recent vishwa transactions reference : '" + ref[2], Status.PASS, false);
                } else {
                    addToReport(" Failed to validate recent vishwa transactions reference : '" + ref[2], Status.FAIL, false);
                }

                //Validate to account
                if (ToAccName.equalsIgnoreCase(ToAccount)) {
                    addToReport(" Validated recent vishwa transactions to account : '" + ToAccount, Status.PASS, false);
                } else {
                    addToReport(" Failed to validate recent vishwa transactions to account : '" + ToAccount, Status.FAIL, false);
                }

                //Validate status
                if (status.equalsIgnoreCase(stat)) {
                    addToReport(" Validated recent vishwa transactions status : '" + status, Status.PASS, false);
                } else {
                    addToReport(" Failed to validate recent vishwa transactions status : '" + status, Status.FAIL, false);
                }

                clickOnElement(btnClosePopup);

            }
        } catch (Exception e) {
            addToReport("Recent vishawa transfer validation of mobile cash failed", Status.FAIL);
            throw new RuntimeException("Error - Validation of mobile cash under recent vishawa transactions failed", e);
        }
    }

    /**
     * Validate the maximum limit of fav billers as 9 per page in Dashboard
     */
    public void validateMarkedBillersAsFavouriteIsVisibleInBillerWidget() {
        scrollDownPage();
        try {

            //Wait for loading icon to be invisible
            waitForElementToBeInvisible(lblBillerGrayLoader, LONG_WAIT);
            waitForElementToBeClickable(btnAddBiller,LONG_WAIT);
            waitForElementPresence(btnAddBiller);

            //Wait for the element to be clickable
            waitForElementToBeClickable(btnAddBiller, LONG_WAIT);
            clickOnElement(btnAddBiller);
            waitForElementToBeInvisible(btnAddBiller, LONG_WAIT);

            //Declare list to extract from table
            ArrayList<String> TemplateNameSavedBillers = new ArrayList<>();

            //Obtain the record count
            int recordCount = isElementsPresentBy(imgSavedBillerFavRecords);
            if (recordCount == 0) {
                addToReport("Favourite biller records are not displayed", Status.FAIL);
                throw new RuntimeException("Error - Favourite biller records are not displayed in table");
            }
            //Extract the latest records from the list
            for (int inc = 0; inc < recordCount; inc++) {
                TemplateNameSavedBillers.add(inc, getTextFromElement(lblSavedBillerTemplateName(inc + 1)));
                if (inc == 9) {
                    scrollToWebElement(lblSavedBillerTemplateName(inc + 1));
                    addToReport("Obtained 9 records from favourite billers under saved billers", Status.PASS, true);
                }
            }
            addToReport("Completion of addition of biller template names ", Status.PASS, true);

            //Navigate Back to dashboard
            navigateBackToDashboard();
            waitForElementPresence(btnAddBiller);

            //Wait for the element to be clickable
            waitForElementToBeClickable(btnAddBiller, LONG_WAIT);

            //Declare list to extract from fav widget
            ArrayList<String> TemplateName = new ArrayList<>();

            //Obtain the record count
            recordCount = isElementsPresentBy(lblFavouriteBillerWidgetRow);
            if (recordCount == 0 || recordCount > 10) {
                addToReport("Favourite biller records are not displayed as required", Status.FAIL);
                throw new RuntimeException("Error - Favourite biller records are not displayed in widget");
            } else if (recordCount == 10) {
                addToReport("Maximum of nine favourite biller records displayed as required", Status.PASS, true);
            } else {
                addToReport(recordCount+ " Favourite biller records displayed as required", Status.PASS, true);
            }

            //Extract the latest records from the list
            for (int inc = 0; inc < recordCount - 1; inc++) {
                if (recordCount == 5) {
                    scrollToWebElement(lblFavouriteBillerFieldName(inc));
                }
                TemplateName.add(inc, getTextFromElement(lblFavouriteBillerTempName(inc + 1)));
            }

            //Compare two list for template names
            if (CommonUtils.compareTwoArraylist(TemplateNameSavedBillers, TemplateName, true)) {
                addToReport("Favourite biller is validated with the values from saved billers", Status.PASS, true);

                //Add validation content to report
                for (int inc = 0; inc < recordCount - 1; inc++) {
                    addToReport("Favourite biller template name extracted from saved biller is validated with the values from saved billers : " + TemplateName.get(inc), Status.PASS, false);
                }
            } else {
                addToReport(" Failed to validate favourite biller with the values from saved billers", Status.FAIL);
            }
        } catch (Exception e) {
            addToReport("Recent vishawa transfer validation for favourite biller failed", Status.FAIL);
            throw new RuntimeException("Error - Recent vishawa transfer validation of favourite biller failed", e);
        }
        scrollPageToTop();
    }

    /**
     * Validate the maximum limit of fav payees as 9 per page in Dashboard and marked favourite payee is displayed in dashboard
     */
    public void validateMarkedPayeesAsFavouriteIsVisibleInPayeeWidget() {
        scrollDownPage();
        try {

            //Wait for loading icon to be invisible
            waitForElementToBeInvisible(lblBillerGrayLoader, LONG_WAIT);
            waitForElementPresence(btnAddPayee);

            //Wait for the element to be clickable
            waitForElementToBeClickable(btnAddPayee, LONG_WAIT);
            clickOnElement(btnAddPayee);
            waitForElementToBeInvisible(btnAddPayee, LONG_WAIT);

            //Declare list to extract from table
            ArrayList<String> TemplateNameSavedPayee = new ArrayList<>();

            //Obtain the record count
            int recordCount = isElementsPresentBy(imgSavedPayeeFavRecords);
            if (recordCount == 0) {
                addToReport("Favourite payee records are not displayed", Status.FAIL);
                throw new RuntimeException("Error - Favourite payee records are not displayed in table");
            }
            //Extract the latest records from the list
            for (int inc = 0; inc < recordCount; inc++) {
                TemplateNameSavedPayee.add(inc, getTextFromElement(lblSavedPayeeTemplateName(inc + 1)));
                if (inc + 1 == recordCount) {
                    scrollToWebElement(lblSavedPayeeTemplateName(inc + 1));
                    addToReport("Obtained " + recordCount + " records from favourite payee under saved payee", Status.PASS, true);
                }
            }

            //Navigate Back to dashboard
            navigateBackToDashboard();
            waitForElementPresence(btnAddPayee);

            //Wait for the element to be clickable
            waitForElementToBeClickable(btnAddPayee, LONG_WAIT);

            //Declare list to extract from fav widget
            ArrayList<String> TemplateName = new ArrayList<>();

            //Obtain the record count
            recordCount = isElementsPresentBy(lblFavouritePayeeWidgetRow);
            if (recordCount == 0 || recordCount > 10) {
                addToReport("Favourite payee records are not displayed as required", Status.FAIL);
                throw new RuntimeException("Error - Favourite payee records are not displayed in widget");
            } else if (recordCount == 10) {
                addToReport("Maximum of nine favourite payee records displayed as required", Status.PASS, false);
            } else {
                addToReport("Favourite payee records displayed as required", Status.PASS, true);
            }

            //Extract the latest records from the list
            for (int inc = 0; inc < recordCount; inc++) {
                if (recordCount == 7) {
                    scrollToWebElement(lblFavouritePayeeFieldName(inc));
                }
                TemplateName.add(inc, getTextFromElement(lblFavouritePayeeName(inc + 1)));
            }

            scrollDownPage();
            //Compare two list for template names
            if (CommonUtils.compareTwoArraylist(TemplateNameSavedPayee, TemplateName, true)) {
                addToReport("Favourite payee is validated with the values from saved payees", Status.PASS, true);

                //Add validation content to report
                for (int inc = 0; inc < recordCount - 1; inc++) {
                    addToReport("Favourite payee template name extracted from saved payee is validated with the values from saved payee : " + TemplateName.get(inc), Status.PASS, false);
                }
            } else {
                addToReport(" Failed to validate favourite payee with the values from saved payee", Status.FAIL);
            }
        } catch (Exception e) {
            addToReport("Recent vishawa transfer validation for favourite payee failed", Status.FAIL);
            throw new RuntimeException("Error - Recent vishawa transfer validation of favourite payee failed", e);
        }
        scrollPageToTop();
    }

    /**
     * Validate top bar functions in dashboard
     *
     * @param urlDashboard              - URL prefix of dashboard
     * @param urlMyAccount              - URL prefix of my account
     * @param urlManageSchedule         - URL prefix of manage schedule
     * @param btnNameMyAccounts         - button name my accounts
     * @param btnNameManageSchedule     - button name manage schedules
     * @param lblNameScheduleManagement - label name schedule management
     */
    public void validateTopBarFunctions(String urlDashboard, String urlMyAccount, String urlManageSchedule, String btnNameMyAccounts, String btnNameManageSchedule, String lblNameScheduleManagement) {
        try {
            //Url to validate
            String url = "";

            //Wait for loading icon to be invisible
            waitForElementToBeInvisible(lblBillerGrayLoader, LONG_WAIT);

            //validate dashboard page by element and url
            boolean dashboardElement = isElementPresentBy(lblAccountsOrCards);
            if (dashboardElement) {
                addToReport("Dashboard page element " + lblAccountsOrCards + " validated successfully", Status.PASS, false);
            } else {
                addToReport("Unable to find dashboard page element " + lblAccountsOrCards, Status.FAIL);
                throw new RuntimeException("Error - Dashboard element validation failed");
            }
            url = getCurrentURL();
            if (url.contains(urlDashboard)) {
                addToReport("Dashboard page url :" + url + "  was validated successfully", Status.PASS, true);
            } else {
                addToReport("Dashboard page url " + url + " was not validated successfully", Status.FAIL);
                throw new RuntimeException("Error - Dashboard url validation failed");
            }

            clickOnElement(btnMenuOptions(btnNameMyAccounts));

            //Validate my accounts page by element and url
            boolean myAccountElement = isElementPresentBy(getElementByTypeAndText(DashboardPage.ElementType.span, btnNameMyAccounts));
            if (myAccountElement) {
                addToReport("My Account page element " + getElementByTypeAndText(DashboardPage.ElementType.span, btnNameMyAccounts) + " validated successfully", Status.PASS, false);
            } else {
                addToReport("Unable to find My Account page element " + getElementByTypeAndText(DashboardPage.ElementType.span, btnNameMyAccounts), Status.FAIL);
                throw new RuntimeException("Error - My Account element validation failed");
            }
            url = getCurrentURL();
            if (url.contains(urlMyAccount)) {
                addToReport("My Account page url : " + url + " was validated successfully", Status.PASS, true);
            } else {
                addToReport("My Account page url " + url + " was not validated successfully", Status.FAIL);
                throw new RuntimeException("Error - My Account url validation failed");
            }

            clickOnElement(btnMenuOptions(btnNameManageSchedule));

            //Validate manage schedules page by element and url
            boolean manageSchedules = isElementPresentBy(getElementByTypeAndText(DashboardPage.ElementType.div, lblNameScheduleManagement));
            if (manageSchedules) {
                addToReport("Manage schedules page element " + getElementByTypeAndText(DashboardPage.ElementType.div, lblNameScheduleManagement) + " validated successfully", Status.PASS, false);
            } else {
                addToReport("Unable to find Manage schedules page element " + getElementByTypeAndText(DashboardPage.ElementType.div, lblNameScheduleManagement), Status.FAIL);
                throw new RuntimeException("Error - Manage schedules element validation failed");
            }
            url = getCurrentURL();
            if (url.contains(urlManageSchedule)) {
                addToReport("Manage schedules page url : " + url + "  was validated successfully", Status.PASS, true);
            } else {
                addToReport("Manage schedules page url " + url + " was not validated successfully", Status.FAIL);
                throw new RuntimeException("Error - Manage schedules url validation failed");
            }

            //Add validation of payees and billers, services once deployed

        } catch (Exception e) {
            addToReport("Vishawa application top bar validation failed", Status.FAIL);
            throw new RuntimeException("Error - RVishawa application validation of top bar failed", e);
        }

    }

    /**
     * Validate account portfolio in dashboard
     *
     * @param imgLocation    - location of the image for validation
     * @param userName       - specific user
     * @param thresholdValue - threshold value for image comparison
     * @param currencyType   - currency type
     */
    public void validateAccountPortfolio(String imgLocation, String userName, String thresholdValue, String[] currencyType) {
        try {

            //Wait for loading icon to be invisible
            waitForElementToBeInvisible(lblBillerGrayLoader, LONG_WAIT);

            //validate dashboard page element account portfolio
            boolean dashboardElement = isElementPresentBy(imgAccountPortfolio);
            if (dashboardElement) {
                addToReport("Dashboard page element account portfolio " + lblAccountsOrCards + " validated successfully", Status.PASS, false);
            } else {
                addToReport("Unable to find dashboard page element  account portfolio " + lblAccountsOrCards, Status.FAIL);
                throw new RuntimeException("Error - Dashboard element account portfolio validation failed");
            }

            if (userName.equals("settlement")) {
                addToReport("Using the image from :"+imgLocation, Status.INFO, false);
                if (compareImage(imgAccountPortfolio, imgLocation, Integer.parseInt(thresholdValue))) {
                    addToReport("Dashboard page account portfolio pie chart validated successfully", Status.PASS, true);
                } else {
                    addToReport("Unable to find dashboard page element account portfolio pie chart " + lblAccountsOrCards, Status.FAIL);
                    throw new RuntimeException("Error - Dashboard element element element account portfolio pie chart validation failed");
                }
            }
            //Obtain the accounts record count
            int recordCount = isElementsPresentBy(lblAccountPortfolioRows);
            if (recordCount == 0) {
                addToReport(" Recent vishwa transactions displayed no records", Status.FAIL);
                throw new RuntimeException("Error - Incorrect number of Recent vishwa transactions displayed");
            }
            for (int row = 1; row <= recordCount; row++) {
                String currencyValue = getTextFromElement(lblAccountPortfolioValues(row));
                //Validate currency and amount
                String[] CurrencyAndAmt = currencyValue.split(" ");

                // Remove commas and parse the number to negate negative values
                NumberFormat format = NumberFormat.getInstance(Locale.US);
                Number number = format.parse(CurrencyAndAmt[1]);
                double value = number.doubleValue();

                if (value < 0) {
                    if (!currencyValue.isEmpty() &&
                            Arrays.asList(currencyType).contains(CurrencyAndAmt[0]) &&
                            CommonUtils.containsNumericCharactersWithNegativeValues(CurrencyAndAmt[1])) {
                        addToReport(" Account portfolio record number : " + row + " where account value : '" + currencyValue, Status.PASS, false);
                    } else {
                        addToReport(" Failed to validate account portfolio record", Status.FAIL);
                        throw new RuntimeException("Error - Failed to validate account portfolio record");
                    }
                } else {
                    if (!currencyValue.isEmpty() &&
                            Arrays.asList(currencyType).contains(CurrencyAndAmt[0]) &&
                            CommonUtils.containsNumericCharacters(CurrencyAndAmt[1])) {
                        addToReport(" Account portfolio record number : " + row + " where account value : '" + currencyValue, Status.PASS, false);
                    } else {
                        addToReport(" Failed to validate account portfolio record", Status.FAIL);
                        throw new RuntimeException("Error - Failed to validate account portfolio record");
                    }
                }
            }
        } catch (Exception e) {
            addToReport("Dashboard page account portfolio validation failed", Status.FAIL);
            throw new RuntimeException("Error - Dashboard page account portfolio validation failed", e);
        }

    }

    /**
     * Download transfer details and validate the downloaded file vs record
     * @param downloadDirectory - Directory path of downloads
     *
     */
    public void validateRVTDownloadedRecordTransfer(String downloadDirectory) {
        //Transfer record
        addToReport(" -------------Start of validation of RVT Downloaded Record Transfer-------------", Status.PASS);
        //wait for the loading icon to diminish
        waitForLoadingToBeInvisible();
        waitForElementPresence(lblLoadingIcon);
        waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);

        //select transfer tab
        clickOnElement(btnTransfer);

        //Obtain the record count
        int recCount = isElementsPresentBy(lblRVTTransferRecord);
        if (recCount != 10) {
            addToReport(" Recent vishwa transactions displayed is not 10", Status.INFO);
        }
        //Select the record for validation
        clickOnElement(lblRVTTransferRecordOtherAccName(getTextFromElement(lblRVTAccountName(1)), 1));

        //validate popup
        boolean popup = isElementPresentBy(lblRVTTransferTransactionDetailsPopup);
        if (popup) {
            addToReport("Transfer transaction details popup is visible", Status.PASS);
        } else {
            addToReport("Transfer transaction details popup is not visible", Status.FAIL);
            throw new RuntimeException("Error - Transfer transaction details popup is not visible");
        }

        // Store field values to local variables for validation and log each
        String Refer = getTextFromElement(lblRVTTransferPopupRecords(1, 1));
        addToReport("Extracted Reference: " + Refer, Status.INFO,false);

        String FromAccount = getTextFromElement(lblRVTTransferPopupRecords(2, 1));
        addToReport("Extracted From Account: " + FromAccount, Status.INFO,false);

        String ToAccount = getTextFromElement(lblRVTTransferPopupRecords(2, 2));
        addToReport("Extracted To Account: " + ToAccount, Status.INFO,false);

        String TransactionCategory = getTextFromElement(lblRVTTransferPopupRecords(2, 3));
        addToReport("Extracted Transaction Category: " + TransactionCategory, Status.INFO,false);

        String status = getTextFromElement(lblRVTTransferPopupRecords(2, 4));
        addToReport("Extracted Status: " + status, Status.INFO,false);

        String TransactionTime = getTextFromElement(lblRVTTransferPopupRecords(2, 5));
        addToReport("Extracted Transaction Time: " + TransactionTime, Status.INFO,false);

        String Remarks = getTextFromElement(lblRVTTransferPopupRecords(2, 6));
        addToReport("Extracted Remarks: " + Remarks, Status.INFO,false);

        String Amount = getTextFromElement(lblRVTTransferPopupRecords(2, 9));
        addToReport("Extracted Amount: " + Amount, Status.INFO,false);

        String Bank = getTextFromElement(lblRVTTransferPopupRecords(2, 7)).toUpperCase();
        addToReport("Extracted Bank: " + Bank, Status.INFO,false);

        // Optional split and log each part of the reference
        String[] ref = Refer.split(" ");
        addToReport("Split Reference into parts: " + Arrays.toString(ref), Status.INFO);


        // Validate the content based on the extracted values from dashboard vs downloaded pdf
        clickOnElement(btnDownload);
        waitForElementToBeInvisible(popUpPDFDownload, LONG_WAIT);
        waitFor(5);
        // Get the latest downloaded file
        File latestFile = getLatestDownloadedFile(downloadDirectory);

        try {
            if (latestFile != null) {
                System.out.println("Latest Downloaded PDF name: " + latestFile.getName());

                // Extract text from the PDF
                String extractedText = extractTextFromPDF(latestFile.getAbsolutePath()).replace("/n", "");

                addToReport(" Recent vishwa transactions transfers downloaded record : '" + extractedText, Status.INFO, false);
                System.out.println("Latest Downloaded PDF: " + extractedText);

                //validate status
                String[] stat = status.split(" ");
                if (extractedText.contains(stat[1])) {
                    addToReport(" Validated recent vishwa transactions status : '" + status + "' is validated from downloaded record", Status.PASS, false);
                } else {
                    addToReport(" Failed to validate recent vishwa transactions status : '" + status + "' is validated from downloaded record", Status.FAIL, false);
                }

                //Validate currency and amount
                String[] CurrencyAndAmt = Amount.split(" ");
                if (extractedText.contains(CurrencyAndAmt[0]) &&
                        extractedText.contains(CurrencyAndAmt[1])) {
                    addToReport(" Validated recent vishwa transactions currency and amount : '" + Amount + "' is validated from downloaded record", Status.PASS, false);
                } else {
                    addToReport(" Failed to validate Recent vishwa transactions currency and amount : '" + Amount + " from downloaded record", Status.FAIL);
                }

                //Validate reference
                if (extractedText.contains(ref[2])) {
                    addToReport(" Validated recent vishwa transactions reference : '" + ref[2] + "' is validated from downloaded record", Status.PASS, false);
                } else {
                    addToReport(" Failed to validate recent vishwa transactions reference : '" + ref[2] + "' is validated from downloaded record", Status.FAIL, false);
                }

                //Validate date and time
                String transDateTime = CommonUtils.convertDateTime(TransactionTime);
                if (extractedText.contains(transDateTime)) {
                    addToReport(" Validated recent vishwa transactions date : '" + transDateTime + "' is validated from downloaded record", Status.PASS, false);
                } else {
                    addToReport(" Failed to validate recent vishwa transactions date : '" + transDateTime + "' is validated from downloaded record", Status.FAIL, false);
                }

                //Validate the from account number
                if (extractedText.contains(CommonUtils.suffix(FromAccount, 4))) {
                    addToReport(" Recent vishwa transactions transfers from account : '" + FromAccount + "' is validated from downloaded record as " + CommonUtils.suffix(FromAccount, 4), Status.PASS, false);
                } else {
                    addToReport(" Failed to validate Recent vishwa transactions from account : '" + FromAccount + " from downloaded record as " + CommonUtils.suffix(FromAccount, 4), Status.FAIL);
                }

                //validate to account
                if (extractedText.contains(ToAccount)) {
                    addToReport(" Validated recent vishwa transactions to account : '" + ToAccount + "' is validated from downloaded record ", Status.PASS, false);
                } else {
                    addToReport(" Failed to validate recent vishwa transactions to account : '" + ToAccount + "' is validated from downloaded record ", Status.FAIL, false);
                }

                //validate remark
                if (extractedText.contains(Remarks)) {
                    addToReport(" Validated recent vishwa transactions remark : '" + Remarks + "' is validated from downloaded record", Status.PASS, false);
                } else {
                    addToReport(" Failed to validate recent vishwa transactions remark : '" + Remarks + " from downloaded record", Status.FAIL, false);
                }

                //validate bank
                String[] Bnk = Bank.split("-");
                if (extractedText.contains(Bnk[0])) {
                    addToReport(" Validated recent vishwa transactions bank : '" + Bank + "' is validated from downloaded record", Status.PASS, false);
                } else {
                    addToReport(" Failed to validate recent vishwa transactions bank : " + Bnk[0] + " from downloaded record : ", Status.FAIL, false);
                }
            } else {
                addToReport("Recent vishawa transfer failed to download PDF", Status.FAIL);
            }

            clickOnElement(btnClosePopup);

        } catch (Exception e) {
            addToReport("Recent vishawa transfer downloaded PDF validation of transaction failed", Status.FAIL);
            throw new RuntimeException("Error - Validation of transfer under recent vishawa transactions failed", e);
        }
        addToReport(" -------------End of validation of RVT Downloaded Record Transfer-------------", Status.PASS);
    }

    /**
     * Download payment details and validate the downloaded file vs record
     * @param downloadDirectory - Directory path of downloads
     */
    public void validateRVTDownloadedRecordPayment(String downloadDirectory) {
        addToReport(" -------------Start of validation of RVT Downloaded Record Payment-------------", Status.PASS);

        //Payment record
        //wait for the loading icon to diminish
        waitForLoadingToBeInvisible();
        waitForElementPresence(lblLoadingIcon);
        waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);

        //select transfer tab
        clickOnElement(btnPayment);

        //Obtain the record count
        int recCount = isElementsPresentBy(lblRVTPaymentsRecords);
        if (recCount != 10) {
            addToReport(" Recent vishwa transactions displayed is not 10", Status.INFO);
        }
        //Select the record for validation
        clickOnElement(lblRVTPaymentAccountName(1));

        //validate popup
        boolean popup = isElementPresentBy(lblRVTPaymentDetailsPopup);
        if (popup) {
            addToReport("Transfer transaction details popup is visible", Status.PASS);
        } else {
            addToReport("Transfer transaction details popup is not visible", Status.FAIL);
            throw new RuntimeException("Error - Transfer transaction details popup is not visible");
        }

        //Store field values to local variables for validation
        String Refer = getTextFromElement(lblRVTTransferPopupRecords(1, 1));
        String FromAccount = getTextFromElement(lblRVTTransferPopupRecords(2, 1));
        String ToAccount = getTextFromElement(lblRVTTransferPopupRecords(2, 2));
        String status = getTextFromElement(lblRVTTransferPopupRecords(2, 3));
        String TransactionTime = getTextFromElement(lblRVTTransferPopupRecords(2, 4));
        String Amount = getTextFromElement(lblRVTTransferPopupRecords(2, 5));
        String[] ref = Refer.split(" ");

        // Validate the content based on the extracted values from dashboard vs downloaded pdf
        clickOnElement(btnDownload);
        waitForElementToBeInvisible(popUpPDFDownload, LONG_WAIT);
        waitFor(10);
        try {
            // Get the latest downloaded file
            File latestFile = getLatestDownloadedFile(downloadDirectory);

            if (latestFile != null) {
                System.out.println("Latest Downloaded PDF name: " + latestFile.getName());

                // Extract text from the PDF
                String extractedText = extractTextFromPDF(latestFile.getAbsolutePath());
                waitFor(5);
                addToReport(" Recent vishwa transactions payments downloaded record : '" + extractedText, Status.INFO, false);
                System.out.println("Latest Downloaded PDF: " + extractedText);

                // Validate the content based on the extracted values from dashboard
                //validate status
                String[] stat = status.split(" ");
                if (extractedText.contains(stat[1])) {
                    addToReport(" Validated recent vishwa payment status : '" + status + "' from downloaded record", Status.PASS, false);
                } else {
                    addToReport(" Failed to validate recent vishwa payment status : '" + status + "' from downloaded record", Status.FAIL, false);
                }

                //Validate currency and amount
                String[] CurrencyAndAmt = Amount.split(" ");
                if (extractedText.contains(CurrencyAndAmt[0]) &&
                        extractedText.contains(CurrencyAndAmt[1])) {
                    addToReport(" Validated recent vishwa payment currency and amount : '" + Amount + "' from downloaded record", Status.PASS, false);
                } else {
                    addToReport(" Failed to validate Recent vishwa payment currency and amount : '" + Amount + "' from downloaded record", Status.FAIL);
                }

                //Validate reference
                if (extractedText.contains(ref[2])) {
                    addToReport(" Validated recent vishwa payment reference : '" + ref[2] + "' from downloaded record", Status.PASS, false);
                } else {
                    addToReport(" Failed to validate recent vishwa payment reference : '" + ref[2] + "' from downloaded record", Status.FAIL, false);
                }

                //Validate date and time
                String transDateTime = CommonUtils.convertDateTime(TransactionTime);
                if (extractedText.contains(transDateTime)) {
                    addToReport(" Validated recent vishwa payment date : '" + transDateTime + "' from downloaded record", Status.PASS, false);
                } else {
                    addToReport(" Failed to validate recent vishwa payment date : '" + transDateTime + "' from downloaded record", Status.FAIL, false);
                }

                //Validate the from account number
                if (extractedText.contains(CommonUtils.suffix(FromAccount, 4))) {
                    addToReport(" Recent vishwa transactions payment from account  : '" + FromAccount + "' is validated from downloaded record", Status.PASS, false);
                } else {
                    addToReport(" Failed to validate Recent vishwa transactions from account from downloaded record '" + FromAccount, Status.FAIL);
                }

                //validate to account
                if (extractedText.contains(ToAccount)) {
                    addToReport(" Validated recent vishwa payment to account : '" + ToAccount + "' from downloaded record", Status.PASS, false);
                } else {
                    addToReport(" Failed to validate recent vishwa payment to account : '" + ToAccount + "' from downloaded record", Status.FAIL, false);
                }
            } else {
                addToReport("Recent vishawa transfer failed to download PDF", Status.FAIL);
            }
            clickOnElement(btnClosePopup);

        } catch (Exception e) {
            addToReport("Recent vishawa transactions downloaded PDF validation of payment failed", Status.FAIL);
            throw new RuntimeException("Error - Validation of transfer under recent vishawa payment failed", e);
        }
        addToReport(" -------------End of validation of RVT Downloaded Record Payment-------------", Status.PASS);
    }

    /**
     * Download payment details and validate the downloaded file vs record
     * @param downloadDirectory - Directory path of downloads
     *
     */
    public void validateRVTDownloadedRecordMobileCash(String downloadDirectory) {
        addToReport(" -------------Start of validation of RVT Downloaded Record Mobile Cash-------------", Status.PASS);
        //mobile cash record
        //wait for the loading icon to diminish
        waitForLoadingToBeInvisible();
        waitForElementPresence(lblLoadingIcon);
        waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);

        //select transfer tab
        clickOnElement(btnMobileCash);

        //Obtain the record count
        int recCount = isElementsPresentBy(lblRVTPaymentsRecords);
        if (recCount != 10) {
            addToReport(" Recent vishwa transactions displayed is not 10", Status.INFO);
        }
        //Select the first record for validation
        clickOnElement(lblRVTMobileCAccountName(1));

        //validate popup
        boolean popup = isElementPresentBy(lblRVTTransferTransactionDetailsPopup);
        if (popup) {
            addToReport("Mobile cash transaction details popup is visible", Status.PASS);
        } else {
            addToReport("Mobile cash details popup is not visible", Status.FAIL);
            throw new RuntimeException("Error - Mobile cash details popup is not visible");
        }

        //Store field values to local variables for validation
        String Refer = getTextFromElement(lblRVTTransferPopupRecords(1, 1));
        String FromAccount = getTextFromElement(lblRVTTransferPopupRecords(2, 1));
        String ToAccount = getTextFromElement(lblRVTTransferPopupRecords(2, 2));
        String status = getTextFromElement(lblRVTTransferPopupRecords(2, 3));
        String TransactionTime = getTextFromElement(lblRVTTransferPopupRecords(2, 4));
        String Amount = getTextFromElement(lblRVTTransferPopupRecords(2, 5));
        String[] ref = Refer.split(" ");

        // Validate the content based on the extracted values from dashboard vs downloaded pdf
        clickOnElement(btnDownload);
        waitForElementToBeInvisible(popUpPDFDownload, LONG_WAIT);
        waitFor(10);
        try {
            // Get the latest downloaded file
            File latestFile = getLatestDownloadedFile(downloadDirectory);

            if (latestFile != null) {
                System.out.println("Latest Downloaded PDF name: " + latestFile.getName());

                // Extract text from the PDF
                String extractedText = extractTextFromPDF(latestFile.getAbsolutePath());
                waitFor(5);
                addToReport(" Recent vishwa transactions mobile cash downloaded record : '" + extractedText, Status.INFO, false);
                System.out.println("Latest Downloaded PDF: " + extractedText);

                // Validate the content based on the extracted values from dashboard
                //Validate reference
                if (extractedText.contains(ref[2])) {
                    addToReport(" Validated recent vishwa mobile cash reference : '" + ref[2] + " for downloaded record ", Status.PASS, false);
                } else {
                    addToReport(" Failed to validate recent vishwa mobile cash reference : '" + ref[2] + " for downloaded record ", Status.FAIL, false);
                }

                //Validate the from account
                if (extractedText.contains(CommonUtils.suffix(FromAccount, 4))) {
                    addToReport(" Recent vishwa transactions mobile cash from account : '" + FromAccount + " for downloaded record ", Status.PASS, false);
                } else {
                    addToReport(" Failed to validate Recent vishwa transactions mobile cash from account : '" + FromAccount + " for downloaded record ", Status.FAIL);
                }

                //Validate to account
                if (extractedText.contains(ToAccount)) {
                    addToReport(" Validated recent vishwa transactions to account : '" + ToAccount + " for downloaded record ", Status.PASS, false);
                } else {
                    addToReport(" Failed to validate recent vishwa transactions to account : '" + ToAccount + " for downloaded record ", Status.FAIL, false);
                }

                //Validate status
                String[] stat = status.split(" ");
                if (extractedText.contains(stat[1])) {
                    addToReport(" Validated recent vishwa transactions status : '" + status + " for downloaded record ", Status.PASS, false);
                } else {
                    addToReport(" Failed to validate recent vishwa transactions status : '" + status + " for downloaded record ", Status.FAIL, false);
                }

                //Validate date and time
                String transDateTime = CommonUtils.convertDateTime(TransactionTime);
                if (extractedText.contains(transDateTime)) {
                    addToReport(" Validated recent vishwa transactions date : '" + TransactionTime + " for downloaded record ", Status.PASS, false);
                } else {
                    addToReport(" Failed to validate recent vishwa transactions date : '" + TransactionTime + " for downloaded record ", Status.FAIL, false);
                }

                //Validate currency and amount
                String[] CurrencyAndAmt = Amount.split(" ");
                if (extractedText.contains(CurrencyAndAmt[0]) && extractedText.contains(CurrencyAndAmt[1])) {
                    addToReport(" Validated recent vishwa transactions currency and amount : '" + Amount + " for downloaded record ", Status.PASS, false);
                } else {
                    addToReport(" Failed to validate Recent vishwa transactions currency and amount : '" + Amount + " for downloaded record ", Status.FAIL);
                }
                clickOnElement(btnClosePopup);
            } else {
                addToReport("Recent vishawa transfer failed to download PDF", Status.FAIL);
            }
        } catch (Exception e) {
            addToReport("Recent vishawa transactions downloaded PDF validation of mobile cash failed", Status.FAIL);
            throw new RuntimeException("Error - Validation of transfer under recent vishawa mobile cash failed", e);
        }
        addToReport(" -------------End of validation of RVT Downloaded Record Mobile Cash-------------", Status.PASS);
    }

    /**
     * Obtain all available accounts with the first being primary
     * @param primaryStatus               - Primary status
     */
    public void obtainAllAccountTypes(String primaryStatus) {
        waitFor(VERY_SHORT_WAIT);
        waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
        waitForElementToBeInvisible(lblAccountListLoading, LONG_WAIT);

        if (isElementPresentBy(icnAccounts)) {
            waitForElementToBeClickable(icnAccounts, LONG_WAIT);
            waitFor(VERY_SHORT_WAIT);
            // Pagination exists — proceed with multiple account handling
            String[] cardCount = CommonUtils.splitText(getAttributeOrText(icnAccounts, "text"), "/");
            int recordCount = Integer.parseInt(cardCount[1].trim());

            if (recordCount != 0) {
                String PrimaryStatus = getTextFromElement(lblSavingsPrimaryStatus);
                if (PrimaryStatus.equalsIgnoreCase(primaryStatus)) {
                    addToReport("Successfully validated primary status: '" + PrimaryStatus + "'", Status.PASS, false);
                } else {
                    addToReport("Primary status is not validated", Status.FAIL);
                    throw new RuntimeException("Primary status validation failed");
                }

                for (int inc = 0; inc < recordCount; inc++) {
                    waitForElementToBeClickable(lblAccountNumber, LONG_WAIT);
                    String accNumber = getTextFromElement(lblAccountNumber);
                    addValue(inc, accNumber);
                    addToReport("Account number added: '" + accNumber + "'", Status.PASS, true);

                    if (inc < recordCount - 1) {
                        clickOnElement(btnNextArrow);
                        waitForElementToBeInvisible(lblLoadingIcon, MODERATE_WAIT);
                    }
                }
            } else {
                addToReport("No accounts found via pagination", Status.FAIL);
                throw new RuntimeException("No accounts found");
            }

        } else {
            // Pagination not present — handle single account
            waitForElementToBeClickable(lblAccountNumber, LONG_WAIT);
            String accNumber = getTextFromElement(lblAccountNumber);
            addValue(0, accNumber);
            addToReport("Only one account available. Account number: '" + accNumber + "'", Status.PASS, true);
        }
    }

    /**
     * Navigate to main Menu
     *
     * @param menuName - currency type
     */
    public void navigateToMainMenu(String menuName) {

        waitForElementPresence(btnMainMenu(menuName));
        clickOnElement(btnMainMenu(menuName));
        waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
        addToReport("Clicked menu tab "+menuName, Status.PASS);
    }

    /**
     * Navigate to the messages page
     */
    public void navigateToMessages() {
        boolean icnMessages = isElementPresentBy(icnMessage);
        if (icnMessages) {
            addToReport("Successfully validated message icon", Status.PASS, false);
            clickOnElement(icnMessage);
            boolean messagelbl = isElementPresentBy(lblMessage);
            if (messagelbl) {
                addToReport("Successfully validated messages page", Status.PASS, true);
            } else {
                addToReport("Messages page is not visible.", Status.FAIL, true);
                throw new RuntimeException("Error - Messages page is not visible.");
            }
        } else {
            addToReport("Dashboard page is not visible.", Status.FAIL, true);
            throw new RuntimeException("Error - Dashboard page is not visible.");
        }
    }


    /**
     * Hover over menu and validate
     * @param menuName  Menu name
     * @param subHeaders  Sub headers to validate
     */
    public void hoverOverMenuAndValidate(String menuName,String[] subHeaders) {
        waitForElementPresence(btnMainMenu(menuName));
        mouseHover(btnMainMenu(menuName));

        for (String sHeader : subHeaders) {
            boolean isPresent = isElementPresentBy(btnSubMenu(sHeader));

            addToReport(""+btnSubMenu(sHeader), Status.PASS,false);
            if (isPresent) {
                addToReport("Successfully validated sub header: " + sHeader, Status.PASS, false);
            } else {
                addToReport("Sub header not found under My Accounts: " + sHeader, Status.FAIL, true);
            }
        }

        addToReport("Successfully validated sub headers", Status.PASS, true);
    }

    /**
     * Hover over menu and validate
     * @param menuName  Menu name
     * @param subHeader  Sub headers to click
     */
    public void hoverOverMenuAndClick(String menuName,String subHeader) {
        waitForElementPresence(btnMainMenu(menuName));
        mouseHover(btnMainMenu(menuName));
        waitForElementToBeClickable(btnSubMenu(subHeader),MODERATE_WAIT);
        boolean isPresent = isElementPresentBy(btnSubMenu(subHeader));
        if (isPresent) {
            addToReport("Successfully validated sub header: " + subHeader, Status.PASS, true);
        } else {
            addToReport("Sub header not found under My Accounts: " + subHeader, Status.FAIL, true);
        }
        clickOnElement(btnSubMenu(subHeader));
        clickOnElement(iconUser);

        addToReport("Successfully clicked sub header "+subHeader, Status.PASS, true);
    }


}