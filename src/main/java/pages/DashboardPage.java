/*
 *   @author - salman R
 */
package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.CommonUtils;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class DashboardPage extends BasePage {

    public enum ElementType {
        button, label, span, div;
    }

    private static final By alertPopup = By.xpath("//span[text()='X']");
    private static final By iconUser = By.xpath("//div[contains(@class,'NavBar_userContainer')]/img");
    private static final By optionSettings = By.xpath("//div[contains(@class,'UserMenu_subMenuItem') and contains(text(),'Settings')]");
    private static final By title = By.xpath("//title[text()='Sampath Vishwa | Dashboard']");
    private static final By btn_freeTrial = By.xpath("//input[@id='linkadd']");
    private static final By btn_BookAFreeDemo = By.xpath("//li/a[text()='Book a Free Demo']");
    private static final By lbl_header = By.xpath("//h3[text()='See OrangeHRM in Action']");
    private static final By lbl_freeTrial = By.xpath("//h1[text()='Your free trial']");
    private static final By lblSavingsAccount = By.xpath("//span[text()='Savings Account']");
    private static final By lblSavingsACNumber = By.xpath("//span[text()='Savings Account']/ancestor::div[contains(@class,'flex justify-between')]/following::span[contains(@class,'flex flex-col')]");
    private static final By lblSavingsPrimaryStatus = By.xpath("//span[text()='Savings Account']/ancestor::div[contains(@class,'flex flex-col')]/following::div[contains(@class,'text-white font-bold')][1]");
    private static final By lblSavingsAccountStatus = By.xpath("//span[text()='Savings Account']/ancestor::div[contains(@class,'flex flex-col')]/following::div[contains(@class,'text-white font-bold')][2]");
    private static final By lblSavingsAccountProductName = By.xpath("//span[text()='Savings Account']/ancestor::div[contains(@class,'flex flex-col')]/following::span[contains(@class,'self-end')]");
    private static final By lblCurrencyAndAvailableBalance = By.xpath("//div[contains(text(),'Available')]/following-sibling::div/span[@class='text-black']");
    private static final By btnDeposits = By.xpath("//div[contains(@class,'Container_body')]//div[contains(text(),'Deposits')]");
    private static final By lblLoadingIcon = By.xpath("//div[contains(@class,'AccountsCards_loader')]");
    private static final By lblFDMaturityValue = By.xpath("//span[contains(text(),'Maturity Value')]/parent::div/span[1]");
    private static final By lblFDMaturityDate = By.xpath("//span[contains(text(),'Maturity Date')]/parent::div/span[1]");
    private static final By lblFDInterestRate = By.xpath("//span[contains(text(),'Interest Rate')]/parent::div/span[1]");
    private static final By lblFDAccountNumber = By.xpath(" //span[contains(text(),'Fixed Deposit')]/ancestor::div[contains(@class,'full justify-center flex')]//div[contains(@class,'text-base')]/span");
    private static final By btnQActionsSendMoney = By.xpath("//h1[text()='Quick Actions']/ancestor::div[contains(@class,'Container_container')]//span[contains(text(),'Send Money')]");
    private static final By btnQActionsBillPayment = By.xpath("//h1[text()='Quick Actions']/ancestor::div[contains(@class,'Container_container')]//span[contains(text(),'Bill Payment')]");
    private static final By btnQActionsSampathSlipless = By.xpath("//h1[text()='Quick Actions']/ancestor::div[contains(@class,'Container_container')]//span[contains(text(),'Sampath')]/parent::div/span[contains(text(),'Slipless')]");
    private static final By btnQActionsOpenNewFD = By.xpath("//h1[text()='Quick Actions']/ancestor::div[contains(@class,'Container_container')]//span[contains(text(),'Open New')]/parent::div/span[contains(text(),'Fixed Deposit')]");
    private static final By btnQActionsOpenSA = By.xpath("//h1[text()='Quick Actions']/ancestor::div[contains(@class,'Container_container')]//span[contains(text(),'Open Saving')]/parent::div/span[contains(text(),'Account')]");
    private static final By btnQActionsApplyWebCard = By.xpath("//h1[text()='Quick Actions']/ancestor::div[contains(@class,'Container_container')]//span[contains(text(),'Apply')]/parent::div/span[contains(text(),'Web Card')]");
    private static final By btnQActionsStopCard = By.xpath("//h1[text()='Quick Actions']/ancestor::div[contains(@class,'Container_container')]//span[contains(text(),'Stop Card')]");
    private static final By btnQActionsStopCheque = By.xpath("//h1[text()='Quick Actions']/ancestor::div[contains(@class,'Container_container')]//span[contains(text(),'Stop Cheque')]");
    private static final By btnQActionsMobileCash = By.xpath("//h1[text()='Quick Actions']/ancestor::div[contains(@class,'Container_container')]//span[contains(text(),'Mobile Cash')]");
    private static final By btnQActionsObtainNewLoan = By.xpath("//h1[text()='Quick Actions']/ancestor::div[contains(@class,'Container_container')]//span[contains(text(),'Obtain')]/parent::div/span[contains(text(),'New Loan')]");
    private static final By btnTransfer = By.xpath("//div[contains(@class,'RecentVishwaTransactions')]//div[contains(text(),'Transfer')]");
    private static final By btnPayment = By.xpath("//div[contains(@class,'RecentVishwaTransactions')]//div[contains(text(),'Payment')]");
    private static final By btnMobileCash = By.xpath("//div[contains(@class,'RecentVishwaTransactions')]//div[contains(text(),'Mobile Cash')]");
    private static final By lblRVTTransferRecord = By.xpath("//div[contains(@class,'RecentVishwaTransactions_scrollContainer')]//div[contains(@class,'RecentVishwaTransactions_transactionCard')]");
    private static final By lblRVTAmtAndCurrency = By.xpath("//div[contains(@class,'RecentVishwaTransactions_transactionCard')][1]//div[contains(@class,'amountDebit')]/span");
    private static final By lblRVTPaymentAccountName = By.xpath("//div[contains(@class,'RecentVishwaTransactions_transactionCard')][1]//div[contains(@class,'transactionDetails')]/span[1]");
    private static final By lblRVTPaymentReference = By.xpath("//div[contains(@class,'RecentVishwaTransactions_transactionCard')][1]//div[contains(@class,'transactionDetails')]/span[contains(@class,'RecentVishwaTransactions')][1]");
    private static final By lblRVTPaymentDate = By.xpath("//div[contains(@class,'RecentVishwaTransactions_transactionCard')][1]//div[contains(@class,'transactionDetails')]/span[contains(@class,'RecentVishwaTransactions')][2]");
    private static final By lblSendMoneyHeader = By.xpath("//div[text()='Make Transactions']/ancestor::div[contains(@class,'flex-col')]/following-sibling::div/span[contains(text(),'Send Money')]");
    private static final By lblMobileCashHeader = By.xpath(" //div[text()='Make Transactions']/ancestor::div[contains(@class,'flex-col')]/following-sibling::div/span[contains(text(),'Mobile Cash')]");
    private static final By lblBillPaymentHeader = By.xpath("//span[text()='Bill Payments']/ancestor::div[contains(@class,'flex-col')]/following-sibling::div/span[contains(text(),'Bill Payments')]");
    private static final By imgAccountPortfolio = By.xpath("//canvas[@role='img']");
    private static final By imgAdvertisement = By.xpath("//span[contains(text(),'Maintenance & Updates')]/parent::div//img[contains(@src,'/SVRClientWebV4/_next/image')]");
    private static final By lblOpenFDPopupHeader = By.xpath("//span[text()='Open Fixed Deposit']");
    private static final By lblFDHeader = By.xpath("//span[text()='Fixed Deposits']");
    private static final By lblOpenSavingsAccountHeader = By.xpath("//div[contains(text(),'Are you a resident of Sri Lanka?')]");
    private static final By btnCloseFDPopup = By.xpath("//button[contains(text(),'Close')]");
    private static final By btnAddPayee = By.xpath("//span[contains(text(),'Favorite Payees')]/following::div[contains(@class,'grid grid-cols')]/div/span[contains(text(),'Add to favorites')]");
    private static final By lblFavouritePayeeWidgetRow = By.xpath("//span[contains(text(),'Favorite Payees')]/following::div[contains(@class,'grid grid-cols')]/div");
    private static final By btnDashboard = By.xpath("//button/a[contains(normalize-space(text()), 'Dashboard')]");

    private static By lblAccountNumber(String accountNumber) {
        return By.xpath("//div[contains(@class,' justify-center flex')]//div[contains(normalize-space(text()), '" + accountNumber + "')]");
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
    private static By logoutButton(String buttonText) {
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
    private static By lblFavouritePayeeWidgetRow(int index) {
        return By.xpath("//span[contains(text(),'Favorite Payees')]/following::div[contains(@class,'grid grid-cols')]/div");
    }
    private static By lblFavouritePayeeNickName(int index) {
        return By.xpath("//span[contains(text(),'Favorite Payees')]/following::div[contains(@class,'grid grid-cols')]/div[" + index + "]/div[contains(@class,'flex flex')]/span[contains(@class,'text-black')]");
    }
    private static By lblFavouritePayeeBankName(int index) {
        return By.xpath("//span[contains(text(),'Favorite Payees')]/following::div[contains(@class,'grid grid-cols')]/div[" + index + "]/div[contains(@class,'flex flex')]/span[2]");
    }
    private static By lblFavouritePayeeRefernce(int index) {
        return By.xpath("//span[contains(text(),'Favorite Payees')]/following::div[contains(@class,'grid grid-cols')]/div[" + index + "]/div[contains(@class,'flex flex')]/span[3]");
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
            clickOnElement(alertPopup);

            //validate user profile icon and click
            boolean userProfileIcon = isElementPresentBy(iconUser);
            if (userProfileIcon) {
                clickOnElement(iconUser);
                addScreenshotToTheReport("Successfully clicked on user profile icon on top navigation bar.", Status.PASS);
            } else {
                addScreenshotToTheReport("User profile icon is not visible.", Status.FAIL);
                throw new RuntimeException("Error - User profile icon is not visible.");
            }

            //validate logout button
            boolean logoutButton = isElementPresentBy(logoutButton(buttonName));
            if (logoutButton) {
                clickOnElement(logoutButton(buttonName));
                addScreenshotToTheReport("Successfully clicked on the logout button.", Status.PASS);
            } else {
                addScreenshotToTheReport("Logout button inside user dropdown is not visible.", Status.FAIL);
                throw new RuntimeException("Error - Logout button inside user dropdown is not visible. ");
            }

            //validate popup
            boolean popup = isElementPresentBy(txtlogoutPopup(popupText));
            if (popup) {
                addScreenshotToTheReport("'" + popupText + "' Logout popup is visible.", Status.PASS);
            } else {
                addScreenshotToTheReport("'" + popupText + "' Logout popup is not visible.", Status.FAIL);
                throw new RuntimeException("'" + popupText + "' Logout pop is not visible.");
            }

            //Validate confirm button
            boolean confirmButton = isElementPresentBy(getElementByTypeAndText(DashboardPage.ElementType.button, buttonName));
            if (confirmButton) {
                addScreenshotToTheReport("'" + confirmButtonText + "'Logout button is visible.", Status.PASS);
                clickOnElement(getElementByTypeAndText(DashboardPage.ElementType.button, buttonName));
                addScreenshotToTheReport("'" + confirmButtonText + "'Logout button is clicked.", Status.PASS);
            } else {
                addScreenshotToTheReport("'" + confirmButtonText + "'Logout button is not visible.", Status.FAIL);
                throw new RuntimeException("'" + confirmButtonText + "'Logout button is not visible.");
            }

            // Validate landing page on logout
            boolean loginPage = isElementPresentBy(getPageTitle(loginPageTitle));
            if (loginPage) {
                addScreenshotToTheReport("Successfully logged out from the sampath vishwa application.", Status.PASS);
            } else {
                addScreenshotToTheReport("Unable to logged out from the sampath vishwa application.", Status.FAIL);
                throw new RuntimeException("Error - User is unable to logged out from the sampath vishwa application.");
            }

        } catch (Exception e) {
            addScreenshotToTheReport("Unable to logged out from the system.", Status.FAIL);
            throw new RuntimeException("Error - Unable to logged out from the system.", e);
        }
    }

    /**
     * Validate the user profile icon
     */
    public void ValidateUserProfileIcon() {
        try {
            waitForElementToBeInvisible(lblLoadingIcon, 5);
            //validate user profile icon
            boolean userProfileIcon = isElementPresentBy(iconUser);
            if (userProfileIcon) {
                addScreenshotToTheReport("Successfully validated user profile icon on top navigation bar", Status.PASS);
            } else {
                addScreenshotToTheReport("User profile icon is not visible", Status.FAIL);
                throw new RuntimeException("Error -User profile icon is not visible");
            }
        } catch (Exception e) {
            addScreenshotToTheReport("User profile icon is not visible", Status.FAIL);
            throw new RuntimeException("Error in loading user profile icon", e);
        }
    }

    /**
     * Validate title
     */
    public void validateTheTitle() {
        waitForElementPresence(title);
        addScreenshotToTheReport("Successfully validated the title '" + title + "'", Status.PASS);
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

            waitForElementToBeInvisible(lblLoadingIcon, 5);
            isElementClickable(btnDeposits);

            //Validate savings account details
            String SavingAccountNo = getTextFromElement(lblSavingsACNumber).toString();
            if (SavingAccountNo.equalsIgnoreCase(savingsAccountNumber)) {
                addScreenshotToTheReport("Successfully validated account number : '" + SavingAccountNo + "'", Status.PASS);
            } else {
                addScreenshotToTheReport("Account number is not validated", Status.FAIL);
                throw new RuntimeException("Error - Account number validation failed");
            }
            //Validate currency and balance
            String CurrencyAndAvailableBalance = getTextFromElement(lblCurrencyAndAvailableBalance).toString();
            if (CurrencyAndAvailableBalance.equalsIgnoreCase(currencyAndAvailableBalance)) {
                addScreenshotToTheReport("Successfully validated currency and amount : '" + CurrencyAndAvailableBalance + "'", Status.PASS);
            } else {
                addScreenshotToTheReport("Currency and amount is not validated", Status.FAIL);
                throw new RuntimeException("Error - Currency and amount validation failed");
            }
            //Validate primary status
            String PrimaryStatus = getTextFromElement(lblSavingsPrimaryStatus).toString();
            if (PrimaryStatus.equalsIgnoreCase(primaryStatus)) {
                addScreenshotToTheReport("Successfully validated primary status : '" + PrimaryStatus + "'", Status.PASS);
            } else {
                addScreenshotToTheReport("Primary status is not validated", Status.FAIL);
                throw new RuntimeException("Error - Primary status validation failed");
            }
            //Validate account status
            String AccountStatus = getTextFromElement(lblSavingsAccountStatus).toString();
            if (AccountStatus.equalsIgnoreCase(accountStatus)) {
                addScreenshotToTheReport("Successfully validated account status : '" + AccountStatus + "'", Status.PASS);
            } else {
                addScreenshotToTheReport("Account status is not validated", Status.FAIL);
                throw new RuntimeException("Error - Account status validation failed");
            }
            //Validate primary status
            String ProductName = getTextFromElement(lblSavingsAccountProductName).toString();
            if (ProductName.equalsIgnoreCase(productName)) {
                addScreenshotToTheReport("Successfully validated product name : '" + ProductName + "'", Status.PASS);
            } else {
                addScreenshotToTheReport("Product Name is not validated", Status.FAIL);
                throw new RuntimeException("Error - Product Name validation failed");
            }

        } catch (Exception e) {
            addScreenshotToTheReport("Validate Savings Account At Dashboard failed", Status.FAIL);
            throw new RuntimeException("Error  - Validate Savings Account At Dashboard", e);
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
            waitForElementPresence(lblLoadingIcon);
            waitForElementToBeInvisible(lblLoadingIcon, 10);

            //click button deposit
            isElementClickable(btnDeposits);
            clickOnElement(btnDeposits);

            //wait for the loading icon to diminish
            waitForLoadingToBeInvisible();
            waitForElementPresence(lblLoadingIcon);
            waitForElementToBeInvisible(lblLoadingIcon, 10);

            //Validate fd account number
            String FDAccountNo = getTextFromElement(lblFDAccountNumber);
            if (FDAccountNo.equalsIgnoreCase(fDAccountNumber)) {
                addScreenshotToTheReport("Successfully validated fd account number : '" + FDAccountNo + "'", Status.PASS);
            } else {
                addScreenshotToTheReport("FD Account number : '" + FDAccountNo + "' is not validated", Status.FAIL);
                throw new RuntimeException("Error - FD Account number validation failed");
            }
            //Validate currency and balance
            String CurrencyAndAvailableBalance = getTextFromElement(lblCurrencyAndAvailableBalance);
            if (CurrencyAndAvailableBalance.equalsIgnoreCase(currencyAndAvailableBalance)) {
                addScreenshotToTheReport("Successfully validated currency and amount : '" + CurrencyAndAvailableBalance + "'", Status.PASS);
            } else {
                addScreenshotToTheReport("Currency and amount : '" + CurrencyAndAvailableBalance + "' is not validated", Status.FAIL);
                throw new RuntimeException("Error - Currency and amount validation failed");
            }
            //Validate Maturity Amount
            String FDMaturityValue = getTextFromElement(lblFDMaturityValue);
            if (FDMaturityValue.equalsIgnoreCase(maturityAmount)) {
                addScreenshotToTheReport("Successfully validated maturity amount : '" + FDMaturityValue + "'", Status.PASS);
            } else {
                addScreenshotToTheReport("Maturity amount : '" + FDMaturityValue + "' is not validated", Status.FAIL);
                throw new RuntimeException("Error - Maturity amount validation failed");
            }
            //Validate Maturity Date
            String[] FDMaturityDate = CommonUtils.splitText(getTextFromElement(lblFDMaturityDate), " ");
            if (FDMaturityDate[0].equalsIgnoreCase(maturityDate)) {
                addScreenshotToTheReport("Successfully validated maturity date : '" + FDMaturityDate[0] + "'", Status.PASS);
            } else {
                addScreenshotToTheReport("Maturity date : '" + FDMaturityDate[0] + "' is not validated", Status.FAIL);
                throw new RuntimeException("Error - Maturity date validation failed");
            }
            //Validate Interest Rate
            String InterestRate = getTextFromElement(lblFDInterestRate);
            if (InterestRate.equalsIgnoreCase(interestRate)) {
                addScreenshotToTheReport("Successfully validated interest rate : '" + InterestRate + "'", Status.PASS);
            } else {
                addScreenshotToTheReport("Interest rate : '" + InterestRate + "' is not validated", Status.FAIL);
                throw new RuntimeException("Error - Interest rate validation failed");
            }

        } catch (Exception e) {
            addScreenshotToTheReport("Fixed deposit key points validation failed", Status.FAIL);
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
                addScreenshotToTheReport("Successfully validated quick action send money button ", Status.PASS);
            } else {
                addScreenshotToTheReport("Quick action send money button is not visible", Status.FAIL);
                throw new RuntimeException("Error - Quick action send money button is not visible");
            }
            //validate quick action bill payment
            boolean billPayment = isElementPresentBy(btnQActionsBillPayment);
            if (billPayment) {
                addScreenshotToTheReport("Successfully validated quick action bill payment button ", Status.PASS);
            } else {
                addScreenshotToTheReport("Quick action bill payment button is not visible", Status.FAIL);
                throw new RuntimeException("Error - Quick action bill payment button is not visible");
            }
            //validate quick action sampath slipless
            boolean sampathSleepless = isElementPresentBy(btnQActionsSampathSlipless);
            if (sampathSleepless) {
                addScreenshotToTheReport("Successfully validated quick action sampath slipless button ", Status.PASS);
            } else {
                addScreenshotToTheReport("Quick action  sampath slipless is not visible", Status.FAIL);
                throw new RuntimeException("Error - Quick action  sampath slipless is not visible");
            }
            //validate quick action open fd
            boolean openFD = isElementPresentBy(btnQActionsOpenNewFD);
            if (openFD) {
                addScreenshotToTheReport("Successfully validated quick action open fixed deposit button ", Status.PASS);
            } else {
                addScreenshotToTheReport("Quick action  open fixed deposit is not visible", Status.FAIL);
                throw new RuntimeException("Error - Quick action  open fixed deposit is not visible");
            }
            //validate quick action open SA
            boolean openSA = isElementPresentBy(btnQActionsOpenSA);
            if (openSA) {
                addScreenshotToTheReport("Successfully validated quick action open savings account button ", Status.PASS);
            } else {
                addScreenshotToTheReport("Quick action  open savings account is not visible", Status.FAIL);
                throw new RuntimeException("Error - Quick action  open savings account is not visible");
            }
            //validate quick action apply for web card
            boolean webCard = isElementPresentBy(btnQActionsApplyWebCard);
            if (webCard) {
                addScreenshotToTheReport("Successfully validated quick action web card  button ", Status.PASS);
            } else {
                addScreenshotToTheReport("Quick action  web card is not visible", Status.FAIL);
                throw new RuntimeException("Error - Quick action  web card is not visible");
            }
            //validate quick action stop card
            boolean stopCard = isElementPresentBy(btnQActionsStopCard);
            if (stopCard) {
                addScreenshotToTheReport("Successfully validated quick action stop card  button ", Status.PASS);
            } else {
                addScreenshotToTheReport("Quick action  stop card is not visible", Status.FAIL);
                throw new RuntimeException("Error - Quick action  stop card is not visible");
            }
            //validate quick action stop cheque
            boolean stopCheque = isElementPresentBy(btnQActionsStopCheque);
            if (stopCheque) {
                addScreenshotToTheReport("Successfully validated quick action stop cheque  button ", Status.PASS);
            } else {
                addScreenshotToTheReport("Quick action  stop cheque is not visible", Status.FAIL);
                throw new RuntimeException("Error - Quick action  stop cheque is not visible");
            }
            scrollDownPage();
            //validate quick action mobile cash
            boolean mobileCash = isElementPresentBy(btnQActionsMobileCash);
            if (mobileCash) {
                addScreenshotToTheReport("Successfully validated quick action mobile cash  button ", Status.PASS);
            } else {
                addScreenshotToTheReport("Quick action  mobile cash is not visible", Status.FAIL);
                throw new RuntimeException("Error - Quick action  mobile cash is not visible");
            }
            //validate quick action obtain new loan
            boolean newLoan = isElementPresentBy(btnQActionsObtainNewLoan);
            if (newLoan) {
                addScreenshotToTheReport("Successfully validated quick action obtain new loan  button ", Status.PASS);
            } else {
                addScreenshotToTheReport("Quick action  obtain new loan is not visible", Status.FAIL);
                throw new RuntimeException("Error - Quick action  obtain new loan is not visible");
            }

        } catch (Exception e) {
            addScreenshotToTheReport("Quick action buttons validation failed", Status.FAIL);
            throw new RuntimeException("Failed - Quick action buttons validation failed", e);
        }
    }
    /**
     * Validate settings option
     *
     * @param buttonName             - button text
     */
    public void validateSettingsOption(String buttonName) {
        try {
            //validate user profile icon and click
            boolean userProfileIcon = isElementPresentBy(iconUser);
            if (userProfileIcon) {
                clickOnElement(iconUser);
                addScreenshotToTheReport("Successfully clicked on user profile icon on top navigation bar", Status.PASS);
            } else {
                addScreenshotToTheReport("User profile icon is not visible", Status.FAIL);
                throw new RuntimeException("Error - User profile icon is not visible");
            }
            //validate logout button
            boolean logoutButton = isElementPresentBy(logoutButton(buttonName));
            if (logoutButton) {
                addScreenshotToTheReport("Successfully validated settings icon", Status.PASS);
            } else {
                addScreenshotToTheReport("Settings icon is not visible", Status.FAIL);
                throw new RuntimeException("Settings icon is not visible ");
            }

        } catch (Exception e) {
            addScreenshotToTheReport("Settings icon is not visible", Status.FAIL);
            throw new RuntimeException("Error - Settings icon is not visible", e);
        }
    }

    /**
     * Validate messages and advertisements
     */
    public void validateMessagesAndAdvertisements() {
        try {
            //wait for the loading icon to diminish
            waitForLoadingToBeInvisible();
            waitForElementPresence(lblLoadingIcon);
            waitForElementToBeInvisible(lblLoadingIcon, 10);

            //validate advertisement at dashboard
            boolean advertisement = isElementPresentBy(imgAdvertisement);
            if (advertisement) {
                addScreenshotToTheReport("Successfully validated the published advertisement", Status.PASS);
            } else {
                addScreenshotToTheReport("Advertisement is not visible.", Status.FAIL);
                throw new RuntimeException("Error - Advertisement is not visible.");
            }
        } catch (Exception e) {
            addScreenshotToTheReport("Advertisement is not visible", Status.FAIL);
            throw new RuntimeException("Error - Advertisement is not visible in the dashboard", e);
        }
    }

    /**
     * Validate settings option
     *
     * @param btnDashboard             - button text for dashboard button
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
                addScreenshotToTheReport("Successfully validated the send money page header", Status.PASS);
            } else {
                addScreenshotToTheReport("Send money page header is not visible.", Status.FAIL);
                throw new RuntimeException("Error - Send money page header is not visible.");
            }
            waitForElementToBeInvisible(btnMenuOptions(btnDashboard), 5);
            clickOnElement(btnMenuOptions(btnDashboard));
            waitForElementToBeInvisible(lblLoadingIcon, 5);

            //validate bill payment header
            clickOnElement(btnQActionsBillPayment);
            boolean billPayment = isElementPresentBy(lblBillPaymentHeader);
            if (billPayment) {
                addScreenshotToTheReport("Successfully validated the bill payment page header", Status.PASS);
            } else {
                addScreenshotToTheReport("Bill payment page header is not visible.", Status.FAIL);
                throw new RuntimeException("Error - Bill payment page header is not visible.");
            }
            waitForElementToBeInvisible(btnMenuOptions(btnDashboard), 5);
            clickOnElement(btnMenuOptions(btnDashboard));
            waitForElementToBeInvisible(lblLoadingIcon, 5);

            //validate open new fixed deposit header
            clickOnElement(btnQActionsOpenNewFD);
            boolean openFD = isElementPresentBy(lblOpenFDPopupHeader);
            if (openFD) {
                addScreenshotToTheReport("Successfully validated the open new fixed deposit page header", Status.PASS);
            } else {
                addScreenshotToTheReport("Open new fixed deposit page header is not visible.", Status.FAIL);
                throw new RuntimeException("Error - Open new fixed deposit page header is not visible.");
            }
            clickOnElement(btnCloseFDPopup);
            waitForElementToBeInvisible(btnMenuOptions(btnDashboard), 5);
            clickOnElement(btnMenuOptions(btnDashboard));
            waitForElementToBeInvisible(lblLoadingIcon, 5);

            //validate open savings account header
            clickOnElement(btnQActionsOpenSA);
            boolean openSA = isElementPresentBy(lblOpenSavingsAccountHeader);
            if (openSA) {
                addScreenshotToTheReport("Successfully validated the open savings account page header", Status.PASS);
            } else {
                addScreenshotToTheReport("Open savings account page header is not visible.", Status.FAIL);
                throw new RuntimeException("Error - Open savings account page header is not visible.");
            }
            waitForElementToBeInvisible(btnMenuOptions(btnDashboard), 5);
            clickOnElement(btnMenuOptions(btnDashboard));
            waitForElementToBeInvisible(lblLoadingIcon, 5);

            //validate mobile cash header
            scrollDownPage();
            clickOnElement(btnQActionsMobileCash);
            boolean mobileCash = isElementPresentBy(lblMobileCashHeader);
            if (mobileCash) {
                addScreenshotToTheReport("Successfully validated the mobile cash page header", Status.PASS);
            } else {
                addScreenshotToTheReport("Mobile cash page header is not visible.", Status.FAIL);
                throw new RuntimeException("Error - Mobile cash page header is not visible.");
            }
        } catch (Exception e) {
            addScreenshotToTheReport("Quick action button function failed", Status.FAIL);
            throw new RuntimeException("Error - Quick action button function failed", e);
        }
    }

    /**
     * close the alert popup
     *
     */
    public void closeAlertPopup() {

        //validate user profile icon and click
        boolean alert = isElementPresentBy(alertPopup);
        if (alert) {
            clickOnElement(alertPopup);
            addScreenshotToTheReport("Successfully closed alert popup", Status.PASS);
        } else {
            addScreenshotToTheReport("User profile icon is not visible.", Status.INFO);
        }
    }

    /**
     * Validate recent vishwa transactions transfer widget
     *
     * @param currencyType             - currency types compared from constant array
     */
    public void validateRVTTransferWidgetRecords(String[] currencyType) {
        try {

            //wait for the loading icon to diminish
            waitForLoadingToBeInvisible();
            waitForElementPresence(lblLoadingIcon);
            waitForElementToBeInvisible(lblLoadingIcon, 10);

            //select transfer tab
            clickOnElement(btnTransfer);

            //Declare list to extract from table
            ArrayList<String> AccName = new ArrayList<>();
            ArrayList<String> AmtAndCurrency = new ArrayList<>();
            ArrayList<String> Date = new ArrayList<>();

            //Obtain the record count
            int recordCount = isElementsPresentBy(lblRVTTransferRecord);
            if (recordCount != 10) {
                addScreenshotToTheReport(" Recent vishwa transactions displayed is not 10", Status.FAIL);
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
                        AccName.get(inc).contains("Account") &&
                        CommonUtils.containsAlphabaticCharacters(AccName.get(inc))) {
                    addScreenshotToTheReport(" Recent vishwa transactions of record number : '" + inc + "' where Account name : '" + AccName.get(inc), Status.PASS);
                } else {
                    addScreenshotToTheReport(" Failed to validate Recent vishwa transactions of record number : '" + inc + "' where Account name : '" + AccName.get(inc), Status.FAIL);
                    throw new RuntimeException("Error - Account name of Recent vishwa transactions not displayed");
                }

                //Validate currency and amount
                String[] CurrencyAndAmt = AmtAndCurrency.get(inc).split(" ");
                if (!AmtAndCurrency.get(inc).isEmpty() &&
                        Arrays.asList(currencyType).contains(CurrencyAndAmt[0]) &&
                        CommonUtils.containsNumericCharacters(CurrencyAndAmt[1])) {
                    addScreenshotToTheReport(" Recent vishwa transactions of record number : '" + inc + "' where Account name : '" + AccName.get(inc) + "' , currency and amount : '" + AmtAndCurrency.get(inc), Status.PASS);
                } else {
                    addScreenshotToTheReport(" Failed to validate Recent vishwa transactions of record number : '" + inc + "' where currency and amount : '" + AmtAndCurrency.get(inc), Status.FAIL);
                    throw new RuntimeException("Error - Currency amd amount of Recent vishwa transactions not displayed");
                }

                //Validate the date
                String[] dateContent = Date.get(inc).split(" ");
                if (!Date.get(inc).isEmpty() && Date.get(inc).contains("at") &&
                        CommonUtils.containsAlphabaticCharacters(dateContent[0]) &&
                        CommonUtils.containsNumericCharacters(dateContent[1]) &&
                        CommonUtils.containsNumericCharacters(dateContent[2]) &&
                        CommonUtils.containsAlphAndNumCharacters(dateContent[4])) {
                    addScreenshotToTheReport(" Recent vishwa transactions of record number : '" + inc + "' where Account name : '" + AccName.get(inc) + "' , Currency and amount : '" + AmtAndCurrency.get(inc) + "' and date : '" + Date.get(inc) + "'", Status.PASS);
                } else {
                    addScreenshotToTheReport(" Failed to validate Recent vishwa transactions of record number : '" + inc + "' where date : '" + Date.get(inc), Status.FAIL);
                    throw new RuntimeException("Error - Incorrect date for Recent vishwa transactions displayed");
                }
            }
        } catch (Exception e) {
            addScreenshotToTheReport("Recent vishawa transfer validation of transactions failed", Status.FAIL);
            throw new RuntimeException("Error - Validation of transfer under recent vishawa transactions failed", e);
        }
    }

    /**
     * Validate recent vishwa transactions payment widget
     *
     * @param currencyType             - currency types compared from constant array
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
                addScreenshotToTheReport(" Recent vishwa payments displayed is not 10", Status.FAIL);
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
                    addScreenshotToTheReport(" Recent vishwa payments of record number : '" + inc + "' where Account name : '" + AccName.get(inc), Status.PASS);
                } else {
                    addScreenshotToTheReport(" Failed to validate account name : '" + AccName.get(inc)+"' for recent vishwa payment of record number : '" + inc , Status.FAIL);
                    throw new RuntimeException("Error - Incorrect account name for payment under recent vishwa transactions displayed");
                }

                //Validate currency and amount
                String[] CurrencyAndAmt = AmtAndCurrency.get(inc).split(" ");
                if (!AmtAndCurrency.get(inc).isEmpty() && Arrays.asList(currencyType).contains(CurrencyAndAmt[0].trim()) && CommonUtils.containsNumericCharacters(CurrencyAndAmt[1].trim())) {
                    addScreenshotToTheReport(" Recent vishwa payments of record number : '" + inc + "' where account name : '" + AccName.get(inc) + "' , currency and amount : '" + AmtAndCurrency.get(inc) + "' and date : '" + Date.get(inc) + "'", Status.PASS);
                } else {
                    addScreenshotToTheReport(" Failed to validate currency and amount : '" + AmtAndCurrency.get(inc) + "' of recent vishva payment of record number : '" + inc , Status.FAIL);
                    throw new RuntimeException("Error - Incorrect amount for Recent vishwa payment displayed");
                }

                //Validate payment reference
                if (!PaymentReference.get(inc).isEmpty() &&  CommonUtils.containsAlphNumAndSpecialCharacters(PaymentReference.get(inc).trim())) {
                    addScreenshotToTheReport(" Recent vishwa payments of record number : '" + inc + "' where payment reference : '" + PaymentReference.get(inc)  , Status.PASS);
                } else {
                    addScreenshotToTheReport(" Failed to validate payment reference : '" + PaymentReference.get(inc) + "' of recent vishwa payment of record number : '" + inc , Status.FAIL);
                    throw new RuntimeException("Error - Incorrect payment reference for Recent vishwa payment displayed");
                }

                //Validate transaction date
                String[] dateContent = Date.get(inc).split(" ");
                if (!Date.get(inc).isEmpty() && Date.get(inc).contains("at") &&
                        CommonUtils.containsAlphabaticCharacters(dateContent[0]) &&
                        CommonUtils.containsNumericCharacters(dateContent[1]) &&
                        CommonUtils.containsNumericCharacters(dateContent[2]) &&
                        CommonUtils.containsAlphAndNumCharacters(dateContent[4])) {
                    addScreenshotToTheReport(" Recent vishwa transactions of record number : '" + inc + "' where Account name : '" + AccName.get(inc) + "' , Currency and amount : '" + AmtAndCurrency.get(inc) + "' and date : '" + Date.get(inc) + "'", Status.PASS);
                } else {
                    addScreenshotToTheReport(" Failed to validate Recent vishwa transactions of record number : '" + inc + "' where date : '" + Date.get(inc), Status.FAIL);
                    throw new RuntimeException("Error - Incorrect date for Recent vishwa transactions displayed");
                }
            }
        } catch (Exception e) {
            addScreenshotToTheReport("Recent vishawa transfer validation of payments record failed", Status.FAIL);
            throw new RuntimeException("Error - Recent vishawa transfer validation of payments record failed", e);
        }
    }
    /**
     * Validate recent vishwa transactions mobile cash widget
     *
     * @param currencyType             - currency types compared from constant array
     */
    public void validateRVTMobileCashWidgetRecords(String[] currencyType) {
        try {

            //wait for the loading icon to diminish
            waitForLoadingToBeInvisible();
            waitForElementPresence(lblLoadingIcon);
            waitForElementToBeInvisible(lblLoadingIcon, 10);

            clickOnElement(btnMobileCash);

            //Declare list to extract from table
            ArrayList<String> MobileNo = new ArrayList<>();
            ArrayList<String> AmtAndCurrency = new ArrayList<>();
            ArrayList<String> Date = new ArrayList<>();

            //Obtain the record count
            int recordCount = isElementsPresentBy(lblRVTTransferRecord);
            if (recordCount != 10) {
                addScreenshotToTheReport(" Recent vishwa transactions displayed is not 10", Status.FAIL);
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
                    addScreenshotToTheReport(" Recent vishwa transactions of record number : '" + inc + "' where mobile number : '" + MobileNo.get(inc), Status.PASS);
                } else {
                    addScreenshotToTheReport(" Failed to validate Recent vishva transactions of record number : '" + inc + "' where mobile number : '" + MobileNo.get(inc), Status.FAIL);
                    throw new RuntimeException("Error - Mobile number of Recent vishwa transactions not displayed");
                }

                //Validate currency and amount
                String[] CurrencyAndAmt = AmtAndCurrency.get(inc).split(" ");
                if (!AmtAndCurrency.get(inc).isEmpty() &&
                        Arrays.asList(currencyType).contains(CurrencyAndAmt[0]) &&
                        CommonUtils.containsNumericCharacters(CurrencyAndAmt[1])) {
                    addScreenshotToTheReport(" Recent vishwa transactions of record number : '" + inc + "' where account name : '" + MobileNo.get(inc) + "' , currency and amount : '" + AmtAndCurrency.get(inc), Status.PASS);
                } else {
                    addScreenshotToTheReport(" Failed to validate Recent vishwa transactions of record number : '" + inc + "' where currency and amount : '" + AmtAndCurrency.get(inc), Status.FAIL);
                    throw new RuntimeException("Error - Currency amd amount of Recent vishwa transactions not displayed");
                }

                //Validate the date
                String[] dateContent = Date.get(inc).split(" ");
                if (!Date.get(inc).isEmpty() && Date.get(inc).contains("at") &&
                        CommonUtils.containsAlphabaticCharacters(dateContent[0]) &&
                        CommonUtils.containsNumericCharacters(dateContent[1]) &&
                        CommonUtils.containsNumericCharacters(dateContent[2]) &&
                        CommonUtils.containsAlphAndNumCharacters(dateContent[4])) {
                    addScreenshotToTheReport(" Recent vishwa transactions of record number : '" + inc + "' where account name : '" + MobileNo.get(inc) + "' , currency and amount : '" + AmtAndCurrency.get(inc) + "' and date : '" + Date.get(inc) + "'", Status.PASS);
                } else {
                    addScreenshotToTheReport(" Failed to validate Recent vishwa transactions of record number : '" + inc + "' where date : '" + Date.get(inc), Status.FAIL);
                    throw new RuntimeException("Error - Incorrect date for Recent vishwa transactions displayed");
                }
            }
        } catch (Exception e) {
            addScreenshotToTheReport("Recent vishawa transfer validation for mobile cash failed", Status.FAIL);
            throw new RuntimeException("Error - Recent vishawa transfer validation for mobile cash failed", e);
        }
    }

    /**
     * Validate favourite payee widget
     *
     */
    public void validateFavouritePayeeWidget() {
        try {

            //Click recent transfer and wait till account name appear
            waitForElementPresence(lblRVTPaymentAccountName);

            //Declare list to extract from table
            ArrayList<String> AccNickName = new ArrayList<>();
            ArrayList<String> AccountNumber = new ArrayList<>();
            ArrayList<String> BankName = new ArrayList<>();

            //Obtain the record count
            int recordCount = isElementsPresentBy(lblFavouritePayeeWidgetRow);
            if (recordCount == 0) {
                addScreenshotToTheReport("Favourite payee records are not displayed", Status.FAIL);
                throw new RuntimeException("Error - Favourite payee records are not displayed");
            }
            //Extract the latest records from the list
            for (int inc = 1; inc < recordCount; inc++) {
                AccNickName.add(inc - 1, getTextFromElement(lblFavouritePayeeNickName(inc + 1)));
                AccountNumber.add(inc - 1, getTextFromElement(lblFavouritePayeeRefernce(inc + 1)));
                BankName.add(inc - 1, getTextFromElement(lblFavouritePayeeBankName(inc + 1)));
            }

            //Validate the account nickname
            int rCount = recordCount-1;
            for (int inc = 0; inc < recordCount-1; inc++) {

                //Validate the account name
                if (!AccNickName.get(inc).isEmpty()) {
                    addScreenshotToTheReport(" Favourite payee nickname : '" + AccNickName.get(inc) + "' for the record number : '" + inc, Status.PASS);
                } else {
                    addScreenshotToTheReport(" Failed to validate favourite payee nickname : '" + AccNickName.get(inc) + "' for the record number : '" + inc, Status.FAIL);
                    throw new RuntimeException("Error - Favourite payee is not displayed");
                }

                //Validate the bank name
                if (!BankName.get(inc).isEmpty() && CommonUtils.containsAlphabaticCharacters(BankName.get(inc).trim())) {
                    addScreenshotToTheReport(" Favourite payee bank name : '" + BankName.get(inc) + "' for the record number : '" + inc, Status.PASS);
                } else {
                    addScreenshotToTheReport(" Failed to validate favourite payee bank name : '" + BankName.get(inc) + "' for the record number : '" + inc, Status.FAIL);
                    throw new RuntimeException("Error - Favourite payee is not displayed");
                }

                //Validate the account number
                if (!AccountNumber.get(inc).isEmpty() && CommonUtils.containsNumericCharacters(AccountNumber.get(inc))) {
                    addScreenshotToTheReport(" Favourite payee account number : '" + AccountNumber.get(inc) + "' for the record number : '" + inc, Status.PASS);
                } else {
                    addScreenshotToTheReport(" Failed to validate favourite payee account name : '" + AccountNumber.get(inc) + "' for the record number : '" + inc, Status.FAIL);
                    throw new RuntimeException("Error - Favourite payee is not displayed");
                }
            }
        } catch (Exception e) {
            addScreenshotToTheReport("Recent vishawa transfer validation for favourite payee failed", Status.FAIL);
            throw new RuntimeException("Error - Recent vishawa transfer validation of favourite payee failed", e);
        }
    }

    /**
     * Navigate back to dashboard
     *
     */
    public void navigateBackToDashboard(){
        clickOnElement(btnDashboard);
        waitForElementToBeInvisible(lblLoadingIcon, 7);
    }


    //----------------- WIP--------------------

    /**
     * Validate image
     *
     */
//    public void validateImage() {
//        try {
//            waitForLoadingToBeInvisible();
//            waitForElementPresence(imgAccountPortfolio);
//
//            WebElement elem = driver.findElement(By.xpath("//canvas[@role='img']"));
//            File screenshot = elem.getScreenshotAs(OutputType.FILE);
//            BufferedImage actualImage = ImageIO.read(screenshot);
//            //ImageIO.write(actualImage,png,"src\\test\\resources");
//
//            File expectedImageFile = new File("src\\test\\resources\\test1.png");
//            BufferedImage expectedImage = ImageIO.read(expectedImageFile);
//
//            if (compareImages(actualImage, expectedImage, 60)) {
//                System.out.println("image verified");
//            } else {
//                System.out.println("image not verified");
//
//            }
//        } catch (Exception e) {
//            addScreenshotToTheReport("Settings icon is not visible", Status.FAIL);
//            throw new RuntimeException("Error - Settings icon is not visible", e);
//        }
//    }

}
