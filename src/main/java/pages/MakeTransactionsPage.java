/*
 *   @author - salman R
 */
package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.CommonUtils;
import utils.constants.BillerConstants;
import utils.constants.MyAccountsConstants;
import utils.constants.TransactionConstants;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static utils.CommonUtils.*;

public class MakeTransactionsPage extends BasePage {

    public MakeTransactionsPage(WebDriver driver) {
        super(driver);
    }

    public enum ElementType {
        button, label, span, div, input
    }

    private static final By lblSavingsAccountNo = By.xpath("//span[contains(text(),'Savings Account')]/parent::div/span[2]");
    private static final By lblPageHeader = By.xpath("//div[contains(text(),'Make Transactions')]");
    private static final By ddFromAccount = By.xpath("//select[@id='accountfrom']");
    private static final By ddToAccount = By.xpath("//select[@id='accountto']");
    private static final By ddBank = By.xpath("//select[@name='bank']");
    private static final By ddMCashBank = By.xpath("//select[@id='bank']");
    private static final By ddBranch = By.xpath("//select[@name='branch']");
    private static final By tfEnterAmount = By.xpath("//input[@placeholder='Enter Amount']");
    private static final By tfToAccount = By.xpath("//input[@name='toAccountNumber']");
    private static final By tfToAccountReEnter = By.xpath("//input[@name='reToAccountNumber']");
    private static final By tfNameOfTheReceiver = By.xpath("//input[@name='accountName']");
    private static final By ddPurpose = By.xpath("//select[@name='purposeofTransfer']");
    private static final By tfEnterSenderRemark = By.xpath("//input[@name='senderRemark']");
    private static final By tfEnterBeneficiaryRemark = By.xpath("//input[@name='beneficiaryRemark']");
    private static final By btnSubmit = By.xpath("//button[@type='submit']");
    private static final By rdoOTTransaction = By.xpath("//input[@value='ONLINE']");
    private static final By rdoSchadule = By.xpath("//input[@value='SCHEDULE']");
    private static final By btnClosePopup = By.xpath("//button[contains(@aria-label,'close')]");
    private static final By btnOTPClosePopup = By.xpath("//button[contains(@class,'absolute top')]/img");
    private static final By lblToAccountGrayLoader = By.xpath("//div[contains(@class,'animate-pulse bg-gray')]");
    private static final By lblAnimatePulseLoader = By.xpath("//div[contains(@class,'animate-pulse')]");
    private static final By lblSubmitLoading = By.xpath("//button[@type='submit' and @disabled]");
    private static final By btnConfirm = By.xpath("//button[contains(normalize-space(text()),'Confirm')]");
    private static final By btnNextLoading = By.xpath("//div[contains(@class,'BillPayment_customloader')]");
    private static final By lblSuccess = By.xpath("//span[text()='Success']");
    private static final By lblRefernceID = By.xpath("//span[text()='Success']//following::span[2]");
    private static final By btnPrint = By.xpath("//button[normalize-space()='Print']");
    private static final By popUpPDFDownload = By.xpath("//div[text()='PDF downloaded successfully!']");
    private static final By lstRecentTransactions = By.xpath("//div[contains(@class,'RecentTransactions_scroll')]/div");
    private static final By tfEnterCreditCardNo = By.xpath("//input[@name='CAN']");
    private static final By tfReEnterCreditCardNo = By.xpath("//input[@name='reCAN']");
    private static final By tfEnterCreditCardName = By.xpath("//input[@name='cardName']");
    private static final By tfOTPConfirmationHeaderContent = By.xpath("//div[@class='flex flex-col ']");
    private static final By chkSavePayee = By.xpath("//input[@id='savePayee']");
    private static final By tfNickName = By.xpath("//input[@name='nickName']");
    private static final By btnSearchPayee = By.xpath("//button[@type='submit']");
    private static final By tfSearch = By.xpath("//input[@placeholder='Search']");
    private static final By icnSavedPayeeGridLoading = By.xpath("//div[contains(@class,'dark')]");
    private static final By tblRows = By.xpath("//table//tbody/tr");
    private static final By lblConversionMsg = By.xpath("//div[@class='mt-1']/span");
    private static final By chkAckMsg = By.xpath("//label/input[@type='checkbox']");
    private static final By lblAvailableBal = By.xpath("//span[text()='Available Balance']/parent::div/span[1]");


    private static By lblSavedPayeeTemplateName(int row) {
        return By.xpath("(//img[contains(@src,'Bin') and @alt='']/ancestor::tr/td[4])[" + row + "]");
    }

    private static By btnDeleteSavedPayeeTemplate(String templateName) {
        return By.xpath("//td[normalize-space()='" + templateName + "']/parent::tr//img[contains(@src,'Bin') and @alt='']/ancestor::tr/td[8]//button[2]");
    }

    private static By btnEditSavedPayeeTemplate(String templateName) {
        return By.xpath("//td[normalize-space()='" + templateName + "']/parent::tr//img[contains(@src,'Bin') and @alt='']/ancestor::tr/td[8]//button[1]");
    }

    private static By tabHeader(String tabName) {
        return By.xpath("//div[contains(@class,'flex')]/div[text()='" + tabName + "']");
    }

    private static By tfDisabledName(String name) {
        return By.xpath("//span[normalize-space()='" + name + "']/parent::div/input[@disabled]");
    }

    private static By tfQFTTextField(String name) {
        return By.xpath("//span[normalize-space()='" + name + "']/parent::div/input");
    }

    private static By lblErrorMessage(String errorMsg) {
        return By.xpath("//span[normalize-space()=\"" + errorMsg + "\"]");
    }

    private static By getPopUpMsg(String title) {
        return By.xpath("//div[contains(text(),'" + title + "')]");
    }

    private static By tfOtpConfirmation(String type) {
        return By.xpath("//span[contains(normalize-space(),'" + type + "' )]/parent::div/input[@disabled]");
    }

    private static By tfOTP(int Index) {
        return By.xpath("//input[contains(@class,'otp-box')][" + Index + "]");
    }

    private static By getElementByName(ElementType type, String text) {
        return By.xpath("//" + type.name() + "[@name= \"" + text + "\"]");
    }

    private static By getElementByTypeAndText(ElementType type, String text) {
        return By.xpath("//" + type.name() + "[contains(normalize-space(text()), '" + text + "')]");
    }

    /**
     * Select header tab
     *
     * @param headerTab - Main tab
     */
    public void selectHeaderTab(String headerTab) {
        try {
            waitForElementPresence(tabHeader(headerTab));
            clickOnElement(tabHeader(headerTab));
            addToReport("Successfully selected the '" + headerTab + "' tab under Send Money", Status.PASS, false);
        } catch (Exception e) {
            addToReport("Failed to select the '" + headerTab + "' tab under Send Money", Status.FAIL);
            throw new RuntimeException("Failed to select tab" + e.getMessage(), e);
        }
    }

    /**
     * Select sub tab under send money
     *
     * @param subHeaderTab - Main tabs Eg.Send money
     */
    public void selectTabUnderSendMoney(String subHeaderTab) {
        try {

            waitForElementPresence(tabHeader(subHeaderTab));
            clickOnElement(tabHeader(subHeaderTab));
            addToReport("Successfully selected the '" + subHeaderTab + "' tab under Send Money", Status.PASS, false);
        } catch (Exception e) {
            addToReport("Failed to select the '" + subHeaderTab + "' tab under Send Money", Status.FAIL);
            throw new RuntimeException("Failed to select sub tab" + e.getMessage(), e);
        }
    }


    /**
     * Validate Performing Own Account Transaction From and To accounts availability
     *
     * @param errorMsg1      The first expected error message to validate (if applicable)
     * @param errorMsg2      The second expected error message to validate (if applicable)
     * @param minAmount      The minimum transaction amount to be entered during validation
     * @param maxAmount      The maximum transaction amount to be entered during validation
     * @param minAmountMsg   The expected popup message when the minimum amount is violated
     * @param maxAmountMsg   The expected popup message when the maximum amount is exceeded
     * @param toAccount      The value to be selected in the 'To Account' dropdown
     * @param amount         The main transaction amount to be used in the transfer flow
     * @param sRemark        Sender’s remark text (max 20 characters)
     * @param bRemark        Beneficiary’s remark text (max 20 characters)
     * @param transferMode   The selected transfer mode (e.g., One-Time Transaction or Scheduled)
     * @param kwTransfersMap A map containing keyword constants used to resolve transfer mode options
     */
    public void makeOwnAccountTransactions(String errorMsg1, String errorMsg2, String minAmount, String maxAmount, String minAmountMsg, String maxAmountMsg, String toAccount, String amount, String sRemark, String bRemark, String transferMode, Map<String, String> kwTransfersMap, String currencyType, String OTPValue, String noAmount, String errMinimumTransferAmount) {

        addToReport("----------Start of validation of all eligible accounts to perform bill payments are available in the From Account Drop down and default primary account is selected----------", Status.PASS, false);
        //Obtain account numbers from dashboard
        List<String> accountNumbers = getValues();

        //Validate page title
        waitForElementPresence(lblPageHeader, SHORT_WAIT);
        waitForPageLoadCompleteJS();

        // Validate the account number
        String pAccountNo = getTextFromElement(lblSavingsAccountNo);
        String expectedAccountNo = accountNumbers.get(0);

        if (pAccountNo.equals(expectedAccountNo)) {
            addToReport("Primary account number validated successfully. Expected: '" + expectedAccountNo + "', Found: '" + pAccountNo + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate primary account number. Expected: '" + expectedAccountNo + "', Found: '" + pAccountNo + "'", Status.FAIL);
        }

        //Temporary wait due to sporadic failure in ALL_OPTIONS_VALUE selection
        waitFor(SHORT_WAIT);

        //Obtain the first selected value from the dropdown
        List<String> fromAccDropdownValue = getSelectedOptionText(ddFromAccount, "FIRST_SELECTED");
        if (fromAccDropdownValue.get(0).contains(accountNumbers.get(0))) {
            addToReport("Primary account number " + getSelectedOptionText(ddFromAccount, "FIRST_SELECTED") + " is displayed by default in the pay from dropdown", Status.PASS, true);
        } else {
            addToReport("Primary account number is not displayed by default in the pay from dropdown :" + getSelectedOptionText(ddFromAccount, "FIRST_SELECTED"), Status.FAIL);
        }


        addToReport("----------End of validation of all eligible accounts to perform bill payments are available in the From Account Drop down and default primary account is selected----------", Status.PASS, false);
        addToReport("----------Start of validation of whether all eligible accounts to receive own account transfers are available in the  To Account Drop down----------", Status.PASS, false);

        List<String> toAccDropdownValue = getSelectedOptionText(ddToAccount, "ALL_OPTIONS");

        if (!toAccDropdownValue.contains(fromAccDropdownValue.get(0))) {
            addToReport("From account [" + fromAccDropdownValue + "] is correctly NOT listed in 'To Account' dropdown", Status.PASS, true);
        } else {
            addToReport("From account [" + fromAccDropdownValue + "] is incorrectly listed in 'To Account' dropdown", Status.FAIL);
        }

        String primaryAccount = accountNumbers.get(0);
        // Copy accountNumbers so we don’t mutate the original
        List<String> expectedAccounts = new ArrayList<>(accountNumbers);
        expectedAccounts.remove(primaryAccount);

        if (CommonUtils.compareTwoArraylist(toAccDropdownValue, expectedAccounts, true)) {
            addToReport("All eligible accounts are correctly displayed in the 'To Account' dropdown (excluding the selected 'From Account')", Status.PASS, true);
        } else {
            addToReport("Mismatch in expected 'To Account' dropdown values. Expected: " + expectedAccounts + " Actual: " + toAccDropdownValue, Status.FAIL);
        }

        addToReport("----------End of validation of whether all eligible accounts to receive own account transfers are available in the  To Account Drop down----------", Status.PASS, false);
        addToReport("----------Start of validation of whether accessing next page without mandatory field----------", Status.PASS, false);

        clickOnElement(btnSubmit);

        try {
            // Validate the first error message if it's not null or empty
            if (errorMsg1 != null && !errorMsg1.trim().isEmpty()) {
                if (isElementPresentBy(lblErrorMessage(errorMsg1))) {
                    addToReport("Error message: '" + errorMsg1 + "' is correctly displayed", Status.PASS, false);
                } else {
                    addToReport("Error message: '" + errorMsg1 + "' is NOT displayed as expected", Status.FAIL);
                }
            } else {
                addToReport("Skipping check for error message 1 as it's empty or null", Status.INFO);
            }

            // Validate the second error message if it's not null or empty
            if (errorMsg2 != null && !errorMsg2.trim().isEmpty()) {
                if (isElementPresentBy(lblErrorMessage(errorMsg2))) {
                    addToReport("Error message: '" + errorMsg2 + "' is correctly displayed", Status.PASS, false);
                } else {
                    addToReport("Error message: '" + errorMsg2 + "' is NOT displayed as expected", Status.FAIL);
                }
            } else {
                addToReport("Skipping check for error message 2 as it's empty or null", Status.INFO);
            }

        } catch (Exception e) {
            addToReport("Error while checking the error messages: " + e.getMessage(), Status.FAIL);
        }

        addToReport("----------End of validation of whether accessing next page without mandatory field----------", Status.PASS, false);
        addToReport("----------Start of validation of whether accessing next page without mandatory field, transaction limit & remark character ----------", Status.PASS, false);
        // Perform a transaction using the given parameters
        performTransaction(primaryAccount, toAccount, minAmount, sRemark, bRemark, transferMode, kwTransfersMap);


        addToReport("----------Start of validation of whether a Both sender and beneficiary remark fields needs to accept upto 20 number of charcters ----------", Status.PASS, false);
        if (getCharacterCount(getAttributeOrText(tfEnterSenderRemark, "value")) == 20) {
            addToReport("Sender remark has accepted up to 20 number of characters", Status.PASS, false);
        } else {
            addToReport("Sender remark has not accepted up to 20 number of characters", Status.FAIL);
        }
        if (getCharacterCount(getAttributeOrText(tfEnterBeneficiaryRemark, "value")) == 20) {
            addToReport("Beneficiary remark has accepted up to 20 number of characters", Status.PASS, false);
        } else {
            addToReport("Beneficiary remark has not accepted up to 20 number of characters", Status.FAIL);
        }
        addToReport("----------End of validation of whether a Both sender and beneficiary remark fields needs to accept up to 20 number of characters ----------", Status.PASS, false);

        // Click on the Submit button to initiate the transfer
        clickOnElement(btnSubmit);

        // Validate if the correct popup message is displayed for minimum amount restriction
        checkPopupMessage(minAmountMsg);

        // Repeat the above steps with the maximum amount for limit validation
        performTransaction(primaryAccount, toAccount, maxAmount, sRemark, bRemark, transferMode, kwTransfersMap);

        addToReport("----------Start of validation of whether a Both sender and beneficiary remark fields needs to accept up to 20 number of characters ----------", Status.PASS, false);
        if (getCharacterCount(getAttributeOrText(tfEnterSenderRemark, "value")) == 20) {
            addToReport("Sender remark has accepted up to 20 number of characters", Status.PASS, false);
        } else {
            addToReport("Sender remark has not accepted up to 20 number of characters", Status.FAIL);
        }
        if (getCharacterCount(getAttributeOrText(tfEnterBeneficiaryRemark, "value")) == 20) {
            addToReport("Beneficiary remark has accepted up to 20 number of characters", Status.PASS, false);
        } else {
            addToReport("Beneficiary remark has not accepted up to 20 number of characters", Status.FAIL);
        }
        addToReport("----------End of validation of whether a Both sender and beneficiary remark fields needs to accept up to 20 number of characters ----------", Status.PASS, false);

        //Commenting out below as the transfer limit is unlimited might need on other env
        // Submit the transfer with the maximum amount
        // clickOnElement(btnSubmit);

        // Validate if the correct popup message is displayed for exceeding the maximum limit
        // checkPopupMessage(maxAmountMsg);
        // addToReport("----------End of validation of whether accessing next page without mandatory fields, transaction limit & remark characters ----------", Status.PASS, false);

        addToReport("----------Start of validation of entering amount ----------", Status.PASS, false);

        //Daily transaction limit was already checked
        //Check for minimum amount of the transaction category "0"
        //validate the default loaded account on both tile and dropdown
        selectTabUnderSendMoney(MyAccountsConstants.TAB_OTHER_ACCOUNTS);
        scrollPageToTop();
        selectTabUnderSendMoney(MyAccountsConstants.TAB_OWN_ACCOUNT);

        waitForPageLoadCompleteJS();
        waitForElementToBeInvisible(lblToAccountGrayLoader, LONG_WAIT);

        // Repeat the above steps with the maximum amount for limit validation
        selectFromDropdown(ddFromAccount, primaryAccount, "value");
        List<String> fromAccAmt = getSelectedOptionText(ddFromAccount, "FIRST_SELECTED");

        // Select the same 'To Account' again from the dropdown
        selectFromDropdown(ddToAccount, toAccount, "value");

        // Enter the minimum amount of the transaction category
        sendKeysToElement(tfEnterAmount, noAmount);
        try {
            // Validate the error message
            if (isElementPresentBy(lblErrorMessage(errMinimumTransferAmount))) {
                addToReport("Error message: '" + errMinimumTransferAmount + "' is correctly displayed", Status.PASS, true);
            } else {
                addToReport("Error message: '" + errMinimumTransferAmount + "' is NOT displayed as expected", Status.FAIL);
            }
        } catch (Exception e) {
            addToReport("Error while checking the error messages: " + e.getMessage(), Status.FAIL);
        }

        String[] amt = fromAccAmt.get(0).split(currencyType);

        addToReport(amt[1], Status.PASS, false);

        // Enter the maximum amount into the amount field
        String amountStr = amt[1].trim();

        // Remove comma
        amountStr = amountStr.replace(",", "");
        // Convert to double
        double amountDouble = Double.parseDouble(amountStr);

        // Convert to whole number (round or cast)
        int wholeAmount = (int) Math.round(amountDouble);
        try {
            if (wholeAmount > 55) {
                sendKeysToElement(tfEnterAmount, Keys.BACK_SPACE, 4);
                sendKeysToElement(tfEnterAmount, String.valueOf(amount));
            } else {
                addToReport("Amount in the account : '" + wholeAmount + "' is NOT sufficient to perform a transfer", Status.FAIL);
                throw new RuntimeException("Not sufficient funds in the account");
            }
        } catch (Exception e) {
            addToReport("Error Not sufficient funds in the account :" + e.getMessage(), Status.FAIL);
        }
        // Enter the sender's remark (max 20 characters)
        sendKeysToElement(tfEnterSenderRemark, sRemark);

        // Enter the beneficiary's remark (max 20 characters)
        sendKeysToElement(tfEnterBeneficiaryRemark, bRemark);

        // Select the transfer mode based on the input type
        if (transferMode.equals(kwTransfersMap.get("KW_ONE_TIME_TRANSACTION"))) {
            clickOnElement(rdoOTTransaction); // Select 'One Time Transaction'
        } else if (transferMode.equals(kwTransfersMap.get("KW_SETUP_STANDING_ORDER_SCHEDULE"))) {
            clickOnElement(rdoSchadule); // Select 'Schedule Transaction'
        }

        clickOnElement(btnSubmit);
        waitForElementToBeInvisible(lblSubmitLoading, LONG_WAIT);

        addToReport("----------Start of validation of OTP confirmation page----------", Status.PASS, false);
        //Validate the OTP confirmation
        validateOtpPageDetails(primaryAccount, String.valueOf(amount), toAccount, sRemark, bRemark, transferMode, kwTransfersMap, currencyType, "", "", "", "");

        //Enter OTP
        waitForElementPresence(tfOTP(1), LONG_WAIT);
        sendKeysToElement(tfOTP(1), String.valueOf(OTPValue));
        clickOnElement(btnConfirm);
        waitForElementToBeInvisible(btnNextLoading, LONG_WAIT);

        addToReport("----------End of validation of OTP confirmation page----------", Status.PASS, true);
        addToReport("----------Start of validation of OTP success page----------", Status.PASS, false);

        //Validate the success label,payee name,pay from,amount,payment mode and entered reference while retrieving the reference number
        if (isElementPresentBy(lblSuccess)) {
            addToReport("Validated the success message in the OTP success page", Status.PASS, false);
        } else {
            addToReport("Failed to validate the success message in the OTP success page", Status.FAIL);
        }
        String[] referenceNumber = getTextFromElement(lblRefernceID).split("- ");
        if (referenceNumber[1] != null) {
            addToReport("Obtained the payment reference number " + referenceNumber[1], Status.PASS, false);
        } else {
            addToReport("Failed to get the reference number", Status.FAIL);
        }

        //Validate the OTP success
        validateOtpPageDetails(primaryAccount, String.valueOf(amount), toAccount, sRemark, bRemark, transferMode, kwTransfersMap, currencyType, "", "", "", "");

        //Check for download option
        if (isElementPresentBy(btnPrint)) {
            addToReport("Validated the download option availability in OTP success page", Status.PASS, false);
        } else {
            addToReport("Failed to validate the download option availability in the OTP success page", Status.FAIL);
        }

        addToReport("----------End of validation of OTP success page----------", Status.PASS, true);
        //Close the popup
        waitForElementToBeClickable(btnOTPClosePopup, LONG_WAIT);
        clickOnElement(btnOTPClosePopup);
    }

    /**
     * Validate Performing Own Account Transaction From and To accounts availability
     *
     * @param errorMsg1      The first expected error message to validate (if applicable)
     * @param errorMsg2      The second expected error message to validate (if applicable)
     * @param minAmount      The minimum transaction amount to be entered during validation
     * @param maxAmount      The maximum transaction amount to be entered during validation
     * @param minAmountMsg   The expected popup message when the minimum amount is violated
     * @param maxAmountMsg   The expected popup message when the maximum amount is exceeded
     * @param toAccount      The value to be selected in the 'To Account' dropdown
     * @param amount         The main transaction amount to be used in the transfer flow
     * @param sRemark        Sender’s remark text (max 20 characters)
     * @param bRemark        Beneficiary’s remark text (max 20 characters)
     * @param transferMode   The selected transfer mode (e.g., One-Time Transaction or Scheduled)
     * @param kwTransfersMap A map containing keyword constants used to resolve transfer mode options
     */
    public void makeOwnAccountTransactionsFC(String errorMsg1, String errorMsg2, String minAmount, String maxAmount, String minAmountMsg, String maxAmountMsg, String toAccount, String amount, String sRemark, String bRemark, String transferMode, Map<String, String> kwTransfersMap, String currencyType, String OTPValue, String noAmount, String errMinimumTransferAmount, String fromAccount) {

        addToReport("----------Start of validation of all eligible accounts to perform bill payments are available in the From Account Drop down and default primary account is selected----------", Status.PASS, false);
        //Obtain account numbers from dashboard
        List<String> accountNumbers = getValues();

        //Validate page title
        waitForElementPresence(lblPageHeader, SHORT_WAIT);
        waitForPageLoadCompleteJS();

        // Validate the account number
        String pAccountNo = getTextFromElement(lblSavingsAccountNo);
        String expectedAccountNo = accountNumbers.get(0);

        if (pAccountNo.equals(expectedAccountNo)) {
            addToReport("Primary account number validated successfully. Expected: '" + expectedAccountNo + "', Found: '" + pAccountNo + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate primary account number. Expected: '" + expectedAccountNo + "', Found: '" + pAccountNo + "'", Status.FAIL);
        }

        //Temporary wait due to sporadic failure in ALL_OPTIONS_VALUE selection
        waitFor(SHORT_WAIT);

        //Obtain the first selected value from the dropdown
        List<String> fromAccDropdownValue = getSelectedOptionText(ddFromAccount, "FIRST_SELECTED");
        if (fromAccDropdownValue.get(0).contains(accountNumbers.get(0))) {
            addToReport("Primary account number " + getSelectedOptionText(ddFromAccount, "FIRST_SELECTED") + " is displayed by default in the pay from dropdown", Status.PASS, false);
        } else {
            addToReport("Primary account number is not displayed by default in the pay from dropdown :" + getSelectedOptionText(ddFromAccount, "FIRST_SELECTED"), Status.FAIL);
        }
        addToReport("----------End of validation of all eligible accounts to perform bill payments are available in the From Account Drop down and default primary account is selected----------", Status.PASS, false);
        addToReport("----------Start of validation of whether all eligible accounts to receive own account transfers are available in the  To Account Drop down----------", Status.PASS, false);
        selectFromDropdown(ddFromAccount, fromAccount, "value");
        List<String> toAccDropdownValue = getSelectedOptionText(ddToAccount, "ALL_OPTIONS");

        if (!toAccDropdownValue.contains(fromAccount)) {
            addToReport("From account [" + fromAccDropdownValue + "] is correctly NOT listed in 'To Account' dropdown", Status.PASS, true);
        } else {
            addToReport("From account [" + fromAccDropdownValue + "] is incorrectly listed in 'To Account' dropdown", Status.FAIL);
        }

        String primaryAccount = accountNumbers.get(0);
        // Copy accountNumbers so we don’t mutate the original
        List<String> expectedAccounts = new ArrayList<>(accountNumbers);


        List<String> actualCleaned = cleanAccountList(toAccDropdownValue);
        //expectedAccounts.remove(primaryAccount);
        expectedAccounts.remove(fromAccount);

        if (CommonUtils.compareTwoArraylist(actualCleaned, expectedAccounts, true)) {
            addToReport("All eligible accounts are correctly displayed in the 'To Account' dropdown (excluding the selected 'From Account')", Status.PASS, false);
        } else {
            addToReport("Mismatch in expected 'To Account' dropdown values. Expected: " + expectedAccounts + " Actual: " + actualCleaned, Status.FAIL);
        }

        addToReport("----------End of validation of whether all eligible accounts to receive own account transfers are available in the  To Account Drop down----------", Status.PASS, false);
        addToReport("----------Start of validation of whether accessing next page without mandatory field----------", Status.PASS, false);

        clickOnElement(btnSubmit);

        try {
            // Validate the first error message if it's not null or empty
            if (errorMsg1 != null && !errorMsg1.trim().isEmpty()) {
                if (isElementPresentBy(lblErrorMessage(errorMsg1))) {
                    addToReport("Error message: '" + errorMsg1 + "' is correctly displayed", Status.PASS, false);
                } else {
                    addToReport("Error message: '" + errorMsg1 + "' is NOT displayed as expected", Status.FAIL);
                }
            } else {
                addToReport("Skipping check for error message 1 as it's empty or null", Status.INFO);
            }

            // Validate the second error message if it's not null or empty
            if (errorMsg2 != null && !errorMsg2.trim().isEmpty()) {
                if (isElementPresentBy(lblErrorMessage(errorMsg2))) {
                    addToReport("Error message: '" + errorMsg2 + "' is correctly displayed", Status.PASS, false);
                } else {
                    addToReport("Error message: '" + errorMsg2 + "' is NOT displayed as expected", Status.FAIL);
                }
            } else {
                addToReport("Skipping check for error message 2 as it's empty or null", Status.INFO);
            }

        } catch (Exception e) {
            addToReport("Error while checking the error messages: " + e.getMessage(), Status.FAIL);
        }

        addToReport("----------End of validation of whether accessing next page without mandatory field----------", Status.PASS, false);
        addToReport("----------Start of validation of whether accessing next page without mandatory field, transaction limit & remark character ----------", Status.PASS, false);
        // Perform a transaction using the given parameters
        performTransaction(fromAccount, toAccount, minAmount, sRemark, bRemark, transferMode, kwTransfersMap);


        addToReport("----------Start of validation of whether a Both sender and beneficiary remark fields needs to accept upto 20 number of charcters ----------", Status.PASS, false);
        if (getCharacterCount(getAttributeOrText(tfEnterSenderRemark, "value")) == 20) {
            addToReport("Sender remark has accepted up to 20 number of characters", Status.PASS, false);
        } else {
            addToReport("Sender remark has not accepted up to 20 number of characters", Status.FAIL);
        }
        if (getCharacterCount(getAttributeOrText(tfEnterBeneficiaryRemark, "value")) == 20) {
            addToReport("Beneficiary remark has accepted up to 20 number of characters", Status.PASS, false);
        } else {
            addToReport("Beneficiary remark has not accepted up to 20 number of characters", Status.FAIL);
        }
        addToReport("----------End of validation of whether a Both sender and beneficiary remark fields needs to accept up to 20 number of characters ----------", Status.PASS, false);

        // Click on the Submit button to initiate the transfer
        clickOnElement(btnSubmit);

        // Validate if the correct popup message is displayed for minimum amount restriction
        addToReport("minAmountMsg", Status.PASS, false);

        checkPopupMessage(minAmountMsg);

        addToReport("----------Start of validation of whether a Both sender and beneficiary remark fields needs to accept up to 20 number of characters ----------", Status.PASS, false);
        if (getCharacterCount(getAttributeOrText(tfEnterSenderRemark, "value")) == 20) {
            addToReport("Sender remark has accepted up to 20 number of characters", Status.PASS, false);
        } else {
            addToReport("Sender remark has not accepted up to 20 number of characters", Status.FAIL);
        }
        if (getCharacterCount(getAttributeOrText(tfEnterBeneficiaryRemark, "value")) == 20) {
            addToReport("Beneficiary remark has accepted up to 20 number of characters", Status.PASS, false);
        } else {
            addToReport("Beneficiary remark has not accepted up to 20 number of characters", Status.FAIL);
        }
        addToReport("----------End of validation of whether a Both sender and beneficiary remark fields needs to accept up to 20 number of characters ----------", Status.PASS, false);

        //Commenting out below as the transfer limit is unlimited might need on other env
        // Submit the transfer with the maximum amount
        //clickOnElement(btnSubmit);

        // Validate if the correct popup message is displayed for exceeding the maximum limit
        // checkPopupMessage(maxAmountMsg);
        // addToReport("----------End of validation of whether accessing next page without mandatory fields, transaction limit & remark characters ----------", Status.PASS, false);

        addToReport("----------Start of validation of entering amount ----------", Status.PASS, false);

        //Daily transaction limit was already checked
        //Check for minimum amount of the transaction category "0"
        //validate the default loaded account on both tile and dropdown
        selectTabUnderSendMoney(MyAccountsConstants.TAB_OTHER_ACCOUNTS);
        scrollPageToTop();
        selectTabUnderSendMoney(MyAccountsConstants.TAB_OWN_ACCOUNT);

        waitForPageLoadCompleteJS();
        waitForElementToBeInvisible(lblToAccountGrayLoader, LONG_WAIT);

        // Repeat the above steps with the maximum amount for limit validation
        selectFromDropdown(ddFromAccount, fromAccount, "value");
        List<String> fromAccAmt = getSelectedOptionText(ddFromAccount, "FIRST_SELECTED");

        // Select the same 'To Account' again from the dropdown
        selectFromDropdown(ddToAccount, toAccount, "value");

        // Enter the minimum amount of the transaction category
        sendKeysToElement(tfEnterAmount, noAmount);
        try {
            // Validate the error message
            if (isElementPresentBy(lblErrorMessage(errMinimumTransferAmount))) {
                addToReport("Error message: '" + errMinimumTransferAmount + "' is correctly displayed", Status.PASS, true);
            } else {
                addToReport("Error message: '" + errMinimumTransferAmount + "' is NOT displayed as expected", Status.FAIL);
            }
        } catch (Exception e) {
            addToReport("Error while checking the error messages: " + e.getMessage(), Status.FAIL);
        }

        String[] amt = fromAccAmt.get(0).split(currencyType);

        addToReport(amt[1], Status.PASS, false);

        // Enter the maximum amount into the amount field
        String amountStr = amt[1].trim();

        // Remove comma
        amountStr = amountStr.replace(",", "");
        // Convert to double
        double amountDouble = Double.parseDouble(amountStr);

        // Convert to whole number (round or cast)
        int wholeAmount = (int) Math.round(amountDouble);
        try {
            if (wholeAmount > 1) {
                sendKeysToElement(tfEnterAmount, Keys.BACK_SPACE, 4);
                sendKeysToElement(tfEnterAmount, String.valueOf(amount));
            } else {
                addToReport("Amount in the account : '" + wholeAmount + "' is NOT sufficient to perform a transfer", Status.FAIL);
                throw new RuntimeException("Not sufficient funds in the account");
            }
        } catch (Exception e) {
            addToReport("Error Not sufficient funds in the account :" + e.getMessage(), Status.FAIL);
        }
        // Enter the sender's remark (max 20 characters)
        sendKeysToElement(tfEnterSenderRemark, sRemark);

        // Enter the beneficiary's remark (max 20 characters)
        sendKeysToElement(tfEnterBeneficiaryRemark, bRemark);

        // Select the transfer mode based on the input type
        if (transferMode.equals(kwTransfersMap.get("KW_ONE_TIME_TRANSACTION"))) {
            clickOnElement(rdoOTTransaction); // Select 'One Time Transaction'
        } else if (transferMode.equals(kwTransfersMap.get("KW_SETUP_STANDING_ORDER_SCHEDULE"))) {
            clickOnElement(rdoSchadule); // Select 'Schedule Transaction'
        }

        clickOnElement(btnSubmit);

        waitForElementToBeInvisible(lblSubmitLoading, LONG_WAIT);

        addToReport("----------Start of validation of OTP confirmation page----------", Status.PASS, false);
        //Validate the OTP confirmation
        validateOtpPageDetails(fromAccount, String.valueOf(amount), toAccount, sRemark, bRemark, transferMode, kwTransfersMap, currencyType, "", "", "", "");

        addToReport("Start of validation of rate conversion message ", Status.PASS, false);
        String conversionMsg = getTextFromElement(lblConversionMsg);

        String expectedRegex = BillerConstants.APPROX_LKR_LABEL_PREFIX
                + " \\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2} " + BillerConstants.TIME_PERIOD_REGEX
                + " - " + BillerConstants.LKR_TEXT + "\\s*[\\d,]+\\.\\d{2} \\("
                + BillerConstants.USD_EXCHANGE_PREFIX + " [\\d,]+\\.\\d{2}\\)";

        if (conversionMsg == null) {
            addToReport("Failed to validate label. Reason: conversionMsg is null", Status.FAIL, true);
        } else if (conversionMsg.matches(expectedRegex)) {
            addToReport("Successfully validated dynamic label: " + conversionMsg, Status.PASS, false);
        } else {
            addToReport("Failed to validate label. Actual: '" + conversionMsg + "' | Expected pattern: '" + expectedRegex + "'", Status.FAIL, true);
        }
        addToReport("End of validation of rate conversion message ", Status.PASS, false);
        clickOnElement(chkAckMsg);

        //Enter OTP
        waitForElementPresence(tfOTP(1), LONG_WAIT);
        sendKeysToElement(tfOTP(1), String.valueOf(OTPValue));
        clickOnElement(btnConfirm);
        waitForElementToBeInvisible(btnNextLoading, LONG_WAIT);

        addToReport("----------End of validation of OTP confirmation page----------", Status.PASS, true);
        addToReport("----------Start of validation of OTP success page----------", Status.PASS, false);

        //Validate the success label,payee name,pay from,amount,payment mode and entered reference while retrieving the reference number
        if (isElementPresentBy(lblSuccess)) {
            addToReport("Validated the success message in the OTP success page", Status.PASS, false);
        } else {
            addToReport("Failed to validate the success message in the OTP success page", Status.FAIL);
        }
        String[] referenceNumber = getTextFromElement(lblRefernceID).split("- ");
        if (referenceNumber[1] != null) {
            addToReport("Obtained the payment reference number " + referenceNumber[1], Status.PASS, false);
        } else {
            addToReport("Failed to get the reference number", Status.FAIL);
        }

        //Validate the OTP success
        validateOtpPageDetails(fromAccount, String.valueOf(amount), toAccount, sRemark, bRemark, transferMode, kwTransfersMap, currencyType, "", "", "", "");

        //Check for download option
        if (isElementPresentBy(btnPrint)) {
            addToReport("Validated the download option availability in OTP success page", Status.PASS, false);
        } else {
            addToReport("Failed to validate the download option availability in the OTP success page", Status.FAIL);
        }

        addToReport("----------End of validation of OTP success page----------", Status.PASS, true);
        //Close the popup
        waitForElementToBeClickable(btnOTPClosePopup, LONG_WAIT);
        clickOnElement(btnOTPClosePopup);
    }

    /**
     * Validates OTP confirmation fields against expected values
     *
     * @param payFrom            Expected "Transfer From" value
     * @param amount             Expected amount value
     * @param beneficiaryAccount Expected beneficiary account number
     * @param senderRemark       Expected sender name
     * @param beneficiaryRemarks Expected beneficiary remarks
     * @param transferMode       Expected transfer mode
     */
    public void validateOtpPageDetails(String payFrom, String amount, String beneficiaryAccount, String senderRemark, String beneficiaryRemarks, String transferMode, Map<String, String> constantsMap, String currencyType, String bank, String date, String purpose, String beneficiaryCardNo) {

        validateOtpConfirmationField(constantsMap.get("KW_Transfer_FROM"), payFrom.replace(" ", ""));
        validateOtpConfirmationField(constantsMap.get("KW_AMOUNT"), currencyType + " " + amount);
        validateOtpConfirmationField(constantsMap.get("KW_BENEFICIARY_ACCOUNT_NUMBER"), beneficiaryAccount.replace(" ", ""));
        validateOtpConfirmationField(constantsMap.get("KW_SENDER"), senderRemark);
        validateOtpConfirmationField(constantsMap.get("KW_BENEFICIARY_REMARKS"), beneficiaryRemarks);
        validateOtpConfirmationField(constantsMap.get("KW_TRANSFER_MODE"), transferMode);
        validateOtpConfirmationField(constantsMap.get("KW_TRANSFER_MODE"), transferMode);
        validateOtpConfirmationField(constantsMap.get("KW_BANK"), bank);
        validateOtpConfirmationField(constantsMap.get("KW_TRANSFER_DATE"), date);
        validateOtpConfirmationField(constantsMap.get("KW_PURPOSE"), purpose);
        validateOtpConfirmationField(constantsMap.get("KW_BENEFICIARY_CARD_NUMBER"), beneficiaryCardNo);
    }


    /**
     * Helper method to validate a single field in the OTP confirmation page
     *
     * @param label         The label of the OTP field (e.g., "Amount", "Sender")
     * @param expectedValue The expected value to be matched
     */
    private void validateOtpConfirmationField(String label, String expectedValue) {
        // Skip validation if expected value is null or empty
        if (expectedValue == null || expectedValue.trim().isEmpty()) {
            addToReport("Skipping validation for '" + label + "' as expected value is empty or null.", Status.INFO);
            return;
        }
        waitForElementPresence(tfOtpConfirmation(label), LONG_WAIT);
        String actualValue = getAttributeOrText(tfOtpConfirmation(label), "value").replaceAll("\\s+", "");
        expectedValue = expectedValue.replaceAll("\\s+", "");
        if (expectedValue.equals(actualValue)) {
            addToReport("Validated the value '" + actualValue + "' for '" + label + "' in the OTP page", Status.PASS, false);
        } else {
            addToReport("Failed to validate '" + label + "'. Expected: '" + expectedValue + "', Found: '" + actualValue + "'", Status.FAIL);
        }


    }

    /**
     * Performs a transaction between the given primary and destination accounts.
     *
     * @param primaryAccount The source (primary) account number or identifier from which funds will be debited
     * @param toAccount      The destination account number or identifier to which funds will be credited
     * @param amount         The transaction amount as a string
     * @param sRemark        Short remark or note for the transaction
     * @param bRemark        Beneficiary remark or note for the transaction
     * @param transferMode   The mode of transfer
     * @param kwBillersMap   A map of key–value pairs representing biller information,
     *                       where the key is the biller name/ID and the value is the associated reference or account number
     */
    public void performTransaction(String primaryAccount, String toAccount, String amount, String sRemark, String bRemark, String transferMode, Map<String, String> kwBillersMap) {
        // Repeat the above steps with the maximum amount for limit validation
        selectFromDropdown(ddFromAccount, primaryAccount, "value");

        // Select the same 'To Account' again from the dropdown
        selectFromDropdown(ddToAccount, toAccount, "value");

        //Delete the previous amount
        sendKeysToElement(tfEnterAmount, Keys.BACK_SPACE, 6);

        // Enter the maximum amount into the amount field
        sendKeysToElement(tfEnterAmount, amount);

        // Enter the sender's remark
        sendKeysToElement(tfEnterSenderRemark, sRemark);

        // Enter the beneficiary's remark
        sendKeysToElement(tfEnterBeneficiaryRemark, bRemark);


        // Select the transfer mode again based on the input
        if (transferMode.equals(kwBillersMap.get("KW_ONE_TIME_TRANSACTION"))) {
            clickOnElement(rdoOTTransaction);
        } else if (transferMode.equals(kwBillersMap.get("KW_SETUP_STANDING_ORDER_SCHEDULE"))) {
            clickOnElement(rdoSchadule);
        }
    }


    /**
     * This method checks if a popup message is displayed based on the provided title/error message
     * It will only check if the message is not empty or null
     *
     * @param popupMessage The error message to validate in the popup
     */
    public void checkPopupMessage(String popupMessage) {
        try {
            waitForElementPresence(btnClosePopup);
            waitForElementToBeClickable(btnClosePopup, LONG_WAIT);
            waitForPageLoadCompleteJS();
            addToReport("Popup error message", Status.INFO, true);
            // Check if the popup message is available
            if (isElementPresentBy(getPopUpMsg(popupMessage)) && isElementClickable(btnClosePopup)) {
                waitFor(1);
                addToReport("Popup error message: '" + popupMessage + "' is correctly displayed.", Status.PASS, true);
                waitFor(1);
            } else {
                addToReport("Popup error message: '" + popupMessage + "' is NOT displayed as expected.", Status.FAIL);
            }

            clickOnElement(btnClosePopup);
        } catch (Exception e) {
            addToReport("Error while checking the popup message: " + e.getMessage(), Status.FAIL);
        }
    }


    /**
     * This method performs the process of making an own account transaction. It validates inputs, performs checks,
     * and simulates the process of transferring funds, including the validation of errors and confirmation of the transaction.
     *
     * @param minAmountEntry          The minimum transfer amount to be entered during the transaction.
     * @param maxAmountEntry          The maximum transfer amount to be entered during the transaction.
     * @param minAmountMsg            The actual error message for the minimum amount.
     * @param maxAmountMsg            The actual error message for the maximum amount.
     * @param toAccount               The recipient's account number to which funds will be transferred.
     * @param amount                  The transaction amount that is to be transferred.
     * @param sRemark                 The sender's remark for the transaction.
     * @param bRemark                 The beneficiary's remark for the transaction.
     * @param transferMode            The transfer mode (e.g., One-time Transaction or Scheduled).
     * @param kwTransfersMap          A map that contains the transfer mode constants (e.g., "KW_ONE_TIME_TRANSACTION").
     * @param currencyType            The currency type to be used for the transaction (e.g., "LKR").
     * @param OTPValue                The one-time password (OTP) used for confirming the transaction.
     * @param receiverName            The name of the receiver of the funds.
     * @param purpose                 The purpose of the transaction (e.g., "Profit Income").
     * @param bankName                The bank name through which the transaction is processed.
     * @param actualTransactionAmount The final transaction amount to be used after validation.
     */
    public void makeOtherAccountTransactions(String errorMsgInsufficientFunds, String minAmountEntry, String maxAmountEntry,
                                             String minAmountMsg, String maxAmountMsg, String toAccount, String amount,
                                             String sRemark, String bRemark, String transferMode, Map<String, String> kwTransfersMap,
                                             String currencyType, String OTPValue, String receiverName, String purpose,
                                             String bankName, String branch, String actualTransactionAmount) {

        // Report the start of the validation process
        addToReport("----------Start of validation of all eligible accounts to perform bill payments are available in the From Account Drop down and default primary account is selected----------", Status.PASS, false);

        // Obtain account numbers from dashboard
        List<String> accountNumbers = getValues();
        addToReport("Accounts Found: '" + accountNumbers + "'", Status.PASS, false);

        // Scroll to top and wait for page load
        scrollPageToTop();
        waitForPageLoadCompleteJS();
        waitForElementToBeInvisible(lblToAccountGrayLoader, LONG_WAIT);

        // Validate the account number
        String pAccountNo = getTextFromElement(lblSavingsAccountNo);
        String expectedAccountNo = accountNumbers.get(0);

        if (pAccountNo.equals(expectedAccountNo)) {
            addToReport("Primary account number validated successfully. Expected: '" + expectedAccountNo + "', Found: '" + pAccountNo + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate primary account number. Expected: '" + expectedAccountNo + "', Found: '" + pAccountNo + "'", Status.FAIL);
        }

        // Get balance text from UI and remove commas
        String[] bal = getAttributeOrText(lblAvailableBal, "text").split(" ");
        String numericValue = bal[1].replace(",", "");

        // Convert to BigDecimal (include decimal part)
        BigDecimal balance = new BigDecimal(numericValue).setScale(2, RoundingMode.HALF_UP);


        // Wait for 6 seconds due to sporadic failures in ALL_OPTIONS_VALUE selection
        waitFor(SHORT_WAIT);

        // Obtain the first selected value from the dropdown
        List<String> fromAccDropdownValue = getSelectedOptionText(ddFromAccount, "FIRST_SELECTED");
        if (fromAccDropdownValue.get(0).contains(accountNumbers.get(0))) {
            addToReport("Primary account number " + getSelectedOptionText(ddFromAccount, "FIRST_SELECTED") + " is displayed by default in the pay from dropdown", Status.PASS, true);
        } else {
            addToReport("Primary account number is not displayed by default in the pay from dropdown :" + getSelectedOptionText(ddFromAccount, "FIRST_SELECTED"), Status.FAIL);
        }

        // End of validation for From Account dropdown
        addToReport("----------End of validation of all eligible accounts to perform bill payments are available in the From Account Drop down and default primary account is selected----------", Status.PASS, false);
        addToReport("----------Start of validation of whether entering the amount , amount (Amount+ commission if it is an other bank transfer) needs to be in order----------", Status.PASS, false);

        // Select a bank from the dropdown
        selectFromDropdown(ddBank, bankName, "visibletext");
//        waitForElementToBeClickable(ddBranch, SHORT_WAIT);
//        selectFromDropdown(ddBranch, branch, "visibletext");

        // Enter account and account re-entry
        sendKeysToElement(tfToAccount, toAccount);
        sendKeysToElement(tfToAccountReEnter, toAccount);

        // Enter receiver name
        sendKeysToElement(tfNameOfTheReceiver, receiverName);

        // Select purpose
        selectFromDropdown(ddPurpose, purpose, "value");

        // Enter sender's and beneficiary remarks
        sendKeysToElement(tfEnterBeneficiaryRemark, bRemark);

        if (getCharacterCount(getAttributeOrText(tfEnterBeneficiaryRemark, "value")) == 20) {
            addToReport("Beneficiary remark has accepted up to 20 number of characters", Status.PASS, false);
        } else {
            addToReport("Beneficiary remark has not accepted up to 20 number of characters", Status.FAIL);
        }

        // Select transfer mode based on the input transferMode
        if (transferMode.equals(kwTransfersMap.get("KW_ONE_TIME_TRANSACTION"))) {
            clickOnElement(rdoOTTransaction); // Select 'One Time Transaction'
        } else if (transferMode.equals(kwTransfersMap.get("KW_SETUP_STANDING_ORDER_SCHEDULE"))) {
            clickOnElement(rdoSchadule); // Select 'Schedule Transaction'
        }
        // Enter amount and validate
        scrollPageToTop();
        sendKeysToElement(tfEnterAmount, minAmountEntry);

        // Validate the error message for the minimum transfer amount
        try {
            if (isElementPresentBy(lblErrorMessage(minAmountMsg))) {
                addToReport("Error message: '" + minAmountMsg + "' is correctly displayed", Status.PASS, true);
            } else {
                addToReport("Error message: '" + minAmountMsg + "' is NOT displayed as expected", Status.FAIL);
            }
        } catch (Exception e) {
            addToReport("Error while checking the error messages: " + e.getMessage(), Status.FAIL);
        }

        // Delete the previous amount entry
        sendKeysToElement(tfEnterAmount, Keys.BACK_SPACE, 3);
        sendKeysToElement(tfEnterAmount, maxAmountEntry);

        clickOnElement(btnSubmit);

        // Validate the error message for exceeding the max transfer amount
        checkPopupMessage(maxAmountMsg);

        // Validate daily limit by checking available balance from account
        String[] amt = fromAccDropdownValue.get(0).split(currencyType);
        String amountStr = amt[1].trim().replace(",", "");
        double amountDouble = Double.parseDouble(amountStr);
        int wholeAmount = (int) Math.round(amountDouble);
        int maxAmount = wholeAmount + 1;

        sendKeysToElement(tfEnterAmount, Keys.BACK_SPACE, 8);
        sendKeysToElement(tfEnterAmount, String.valueOf(maxAmount));
        scrollToWebElement(btnSubmit);
        // Click on the Submit button to validate error
        clickOnElement(btnSubmit);

        scrollPageToTop();
        // Validate the error message for insufficient funds
        try {
            if (isElementPresentBy(lblErrorMessage(errorMsgInsufficientFunds))) {
                addToReport("Error message: '" + errorMsgInsufficientFunds + "' is correctly displayed", Status.PASS, true);
            } else {
                addToReport("Error message: '" + errorMsgInsufficientFunds + "' is NOT displayed as expected", Status.FAIL);
            }
        } catch (Exception e) {
            addToReport("Error while checking the error messages: " + e.getMessage(), Status.FAIL);
        }

        // Enter correct values for the transaction
        try {
            if (wholeAmount > 1) {
                sendKeysToElement(tfEnterAmount, Keys.BACK_SPACE, 8);
                sendKeysToElement(tfEnterAmount, actualTransactionAmount);
            } else {
                addToReport("Amount in the account : '" + wholeAmount + "' is NOT sufficient to perform a transfer", Status.FAIL);
                throw new RuntimeException("Not sufficient funds in the account");
            }
        } catch (Exception e) {
            addToReport("Error: Not sufficient funds in the account: " + e.getMessage(), Status.FAIL);
        }
        addToReport("----------Start of addition of favourite payee----------", Status.PASS, false);

        //Check on favourite payee
        waitForElementToBeClickable(chkSavePayee, MODERATE_WAIT);
        clickOnElement(chkSavePayee);

        //Type nickname for favourite payee
        String nickName = generateRandomName(6);
        waitForElementToBeClickable(tfNickName, MODERATE_WAIT);
        sendKeysToElement(tfNickName, nickName);

        addToReport("----------End of addition of favourite payee----------", Status.PASS, true);

        // Click on the Submit button to initiate the transfer
        clickOnElement(btnSubmit);
        waitForElementToBeInvisible(lblSubmitLoading, LONG_WAIT);
        if (isElementClickable(btnClosePopup)) {
            addToReport(" Popup appeared ", Status.PASS, true);
        } else {
            addToReport(" Popup didn't appear ", Status.PASS, true);
        }

        // OTP confirmation page validation
        addToReport("----------Start of validation of OTP confirmation page----------", Status.PASS, true);
        validateOtpPageDetails(accountNumbers.get(0), actualTransactionAmount, toAccount, "", bRemark, transferMode, kwTransfersMap, currencyType,
                bankName, CommonUtils.getTodayDateFormatted("yyyy-MM-dd"), purpose, "");

        // Enter OTP and submit
        waitForElementPresence(tfOTP(1), LONG_WAIT);
        sendKeysToElement(tfOTP(1), OTPValue);
        clickOnElement(btnConfirm);
        waitForElementToBeInvisible(btnNextLoading, LONG_WAIT);

        addToReport("----------End of validation of OTP confirmation page----------", Status.PASS, true);
        addToReport("----------Start of validation of OTP success page----------", Status.PASS, false);

        // Validate the success label and other information
        if (isElementPresentBy(lblSuccess)) {
            addToReport("Validated the success message in the OTP success page", Status.PASS, false);
        } else {
            addToReport("Failed to validate the success message in the OTP success page", Status.FAIL);
        }

        // Validate reference number
        String[] referenceNumber = getTextFromElement(lblRefernceID).split("- ");
        if (referenceNumber[1] != null) {
            addToReport("Obtained the payment reference number " + referenceNumber[1], Status.PASS, false);
        } else {
            addToReport("Failed to get the reference number", Status.FAIL);
        }

        // OTP success validation
        validateOtpPageDetails(accountNumbers.get(0), actualTransactionAmount, toAccount, "", bRemark, transferMode, kwTransfersMap, currencyType,
                bankName, CommonUtils.getTodayDateFormatted("yyyy-MM-dd"), purpose, "");

        // Check for download option in the success page
        if (isElementPresentBy(btnPrint)) {
            addToReport("Validated the download option availability in OTP success page", Status.PASS, false);
        } else {
            addToReport("Failed to validate the download option availability in OTP success page", Status.FAIL);
        }

        addToReport("----------End of validation of OTP success page----------", Status.PASS, true);
        //Close the popup
        scrollPageToTop();
        waitForElementToBeClickable(btnOTPClosePopup, MODERATE_WAIT);
        clickOnElement(btnOTPClosePopup);
        addToReport("----------Start of validation of deduction of service charge----------", Status.PASS, false);

        waitForElementToBeInvisible(lblToAccountGrayLoader, MODERATE_WAIT);
        waitForElementToBeInvisible(icnSavedPayeeGridLoading, MODERATE_WAIT);
        waitForElementToBeClickable(ddFromAccount, MODERATE_WAIT);

        // Select a bank from the dropdown
        selectFromDropdown(ddFromAccount, pAccountNo, "value");

        // Convert transaction amount to BigDecimal (exact from string)
        BigDecimal transactionAmountBD = new BigDecimal(actualTransactionAmount).setScale(2, RoundingMode.HALF_UP);
        BigDecimal fee = new BigDecimal("50.00");

        // Calculate updated balance
        BigDecimal updatedBalance = balance.subtract(transactionAmountBD.add(fee)).setScale(2, RoundingMode.HALF_UP);

        // Get updated balance from UI as string, remove commas
        String uBalText = getAttributeOrText(lblAvailableBal, "text").split(" ")[1].replace(",", "");
        BigDecimal uBalance = new BigDecimal(uBalText).setScale(2, RoundingMode.HALF_UP);

        // Validate
        if (uBalance.compareTo(updatedBalance) == 0) {
            addToReport(
                    "Balance correctly updated. Calculation: "
                            + updatedBalance + " = " + balance + " - (" + transactionAmountBD + " + " + fee + ")"
                            + ". Found: " + uBalance,
                    Status.PASS, true
            );
        } else {
            addToReport(
                    "Balance mismatch. Calculation: "
                            + updatedBalance + " = " + balance + " - (" + transactionAmountBD + " + " + fee + ")"
                            + ". Found: " + uBalance,
                    Status.FAIL, true
            );
        }

        addToReport("----------End of validation of deduction of service charge----------", Status.PASS, false);
        addToReport("----------Start of validation of availability of last 10 records----------", Status.PASS, false);

        //Validate the search results
        int recordCount = isElementsPresentBy(lstRecentTransactions);
        if (recordCount == 10) {
            addToReport("Successfully validated that the last 10 transaction records are available", Status.PASS, true);
            //validate if the transactions are in chronological order
            validateTransactionChronology(lstRecentTransactions);
        } else {
            addToReport("Validation failed: Expected 10 transaction records, but found " + recordCount + ".", Status.FAIL, true);
        }
        addToReport("----------End of validation of availability of last 10 records----------", Status.PASS, false);
        addToReport("----------Start of validation of saved payee----------", Status.PASS, false);
        //Validate and delete saved payee
        selectTabUnderSendMoney(TransactionConstants.TAB_NAME_SAVED_PAYEE);

        waitForElementToBeClickable(btnSearchPayee, LONG_WAIT);
        scrollPageToTop();
        sendKeysToElement(tfSearch, nickName);
        clickOnElementUsingJS(btnSearchPayee);

        waitForElementToBeInvisible(icnSavedPayeeGridLoading, LONG_WAIT);

        //Validate the search results
        recordCount = isElementsPresentBy(tblRows);
        if (recordCount != 0) {
            for (int inc = 1; inc <= recordCount; inc++) {
                //Table retrieved value equals template name then delete
                if (getTextFromElement(lblSavedPayeeTemplateName(inc)).equals(nickName)) {

                    addToReport("Saved payee loaded successfully", Status.PASS, true);
                    //Delete template
                    waitForElementToBeClickable(btnDeleteSavedPayeeTemplate(nickName), LONG_WAIT);
                    addToReport(" Clicked saved template " + nickName, Status.PASS, true);
                    clickOnElementUsingJS(btnDeleteSavedPayeeTemplate(nickName));

                    // Enter OTP and submit
                    waitForElementPresence(tfOTP(1), LONG_WAIT);
                    sendKeysToElement(tfOTP(1), OTPValue);
                    clickOnElement(btnConfirm);
                    waitForElementToBeInvisible(btnNextLoading, LONG_WAIT);

                    if (isElementPresentBy(getPopUpMsg(TransactionConstants.BENEFICIARY_DELETED))) {
                        waitFor(2);
                        addToReport("Popup delete message: '" + TransactionConstants.BENEFICIARY_DELETED + "' is correctly displayed.", Status.PASS, true);
                        waitFor(1);
                    } else {
                        addToReport("Popup delete message: '" + TransactionConstants.BENEFICIARY_DELETED + "' is NOT displayed as expected.", Status.FAIL);
                    }
                    recordCount = isElementsPresentBy(tblRows, VERY_SHORT_WAIT);
                    if (recordCount == 0) {
                        addToReport("Saved payee deleted successfully", Status.PASS, true);
                    } else {
                        addToReport("Saved payee was not deleted successfully", Status.FAIL, true);
                    }
                    break;
                } else {
                    addToReport("Saved beneficiary is not loaded", Status.FAIL);
                }
            }
            addToReport("----------End of validation of saved payee----------", Status.PASS, false);
        }
    }

    /**
     * Validates that transaction entries identified by the given locator
     * are displayed in chronological order based on their
     * transaction date and time
     *
     * @param locator The locator used to find the transaction
     */
    public static void validateTransactionChronology(By locator) {
        List<WebElement> elements = getDriver().findElements(locator);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy 'at' h:mma");

        LocalDateTime previousDate = null;
        boolean inOrder = true;

        for (WebElement element : elements) {
            // Extract the date text from the transaction card
            String dateText = element.getText().trim();

            // Match lines like: "Jul 30, 2025 at 11:00AM"
            if (dateText.matches("[A-Za-z]{3} \\d{1,2}, \\d{4} at \\d{1,2}:\\d{2}[AP]M")) {
                LocalDateTime currentDate = LocalDateTime.parse(dateText, formatter);

                if (previousDate != null && currentDate.isAfter(previousDate)) {
                    addToReport("Chronological order check failed. '" + currentDate + "' is after '" + previousDate + "'", Status.FAIL, true);
                    inOrder = false;
                    break;
                }
                previousDate = currentDate;
            }
        }
        if (inOrder) {
            addToReport("All transactions are displayed in chronological order (latest first).", Status.PASS, true);
        }
    }

    /**
     * Executes other credit card transactions after validating various input fields.
     *
     * @param kwTransfersMap                     Map containing keyword-transfer mappings
     * @param errCreditCardNumberRequired        Error message for missing credit card number
     * @param errReEnterCreditCardNumberRequired Error message for missing re-entered credit card number
     * @param errNameOnCardRequired              Error message for missing name on card
     * @param errBankRequired                    Error message for missing bank name
     * @param errAmountRequired                  Error message for missing amount
     * @param errPurposeRequired                 Error message for missing transaction purpose
     * @param errBeneficiaryRemarkRequired       Error message for missing beneficiary remark
     * @param creditCardNumber                   Entered credit card number
     * @param reEnterCardNumber                  Re-entered credit card number for validation
     * @param nameOnCard                         Name as it appears on the credit card
     * @param bankName                           Name of the bank issuing the credit card
     * @param branchName                         Name of the bank branch
     * @param mdtAmount                          Monetary amount for the transaction in string format
     * @param purpose                            Purpose of the credit card transaction
     * @param sRemark                            Sender remark or additional note
     * @param bRemark                            Beneficiary remark or additional note
     * @param transferMode                       Mode of transfer
     * @param errorMessageMaxTransactionLimit    Error message when max transaction limit is exceeded
     * @param amount                             Actual amount to be transferred
     * @param templateName                       Name of the transaction template used
     * @param otpValue                           One-time password value for verification
     * @param savedPayees                        Saved payee details or references
     * @param minAmount                          Minimum amount required for the transaction
     * @param errInsufficientfunds               Error message for insufficient funds
     * @param errMinimumTransferAmount           Error message for not meeting minimum transfer amount
     * @param currencyValue                      Currency in which the amount is being transferred
     */
    public void makeOtherCreditCardTransactions(Map<String, String> kwTransfersMap, String errCreditCardNumberRequired, String errReEnterCreditCardNumberRequired, String errNameOnCardRequired, String errBankRequired, String errAmountRequired, String errPurposeRequired, String errBeneficiaryRemarkRequired, String creditCardNumber, String reEnterCardNumber, String nameOnCard, String bankName, String branchName, String mdtAmount, String purpose, String sRemark, String bRemark, String transferMode, String errorMessageMaxTransactionLimit, String amount, String templateName, String otpValue, String savedPayees, String minAmount, String errInsufficientfunds, String errMinimumTransferAmount, String currencyValue, String downloadDirectory) {
        // Report the start of the validation process
        addToReport("----------Start of validation of all eligible accounts to perform transfer are available in the From Account Drop down and default primary account is selected----------", Status.PASS, false);

        // Obtain account numbers from dashboard
        List<String> accountNumbers = getValues();

        // Scroll to top and wait for page load
        scrollPageToTop();
        waitForPageLoadCompleteJS();
        waitForElementToBeInvisible(lblToAccountGrayLoader, LONG_WAIT);

        // Validate the account number
        String pAccountNo = getTextFromElement(lblSavingsAccountNo);
        String expectedAccountNo = accountNumbers.get(0);

        if (pAccountNo.equals(expectedAccountNo)) {
            addToReport("Primary account number validated successfully. Expected: '" + expectedAccountNo + "', Found: '" + pAccountNo + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate primary account number. Expected: '" + expectedAccountNo + "', Found: '" + pAccountNo + "'", Status.FAIL);
        }

        // Wait for 6 seconds due to sporadic failures in ALL_OPTIONS_VALUE selection
        waitFor(SHORT_WAIT);

        // Obtain the first selected value from the dropdown
        List<String> fromAccDropdownValue = getSelectedOptionText(ddFromAccount, "FIRST_SELECTED");
        if (fromAccDropdownValue.get(0).contains(accountNumbers.get(0))) {
            addToReport("Primary account number " + getSelectedOptionText(ddFromAccount, "FIRST_SELECTED") + " is displayed by default in the pay from dropdown", Status.PASS, true);
        } else {
            addToReport("Primary account number is not displayed by default in the pay from dropdown :" + getSelectedOptionText(ddFromAccount, "FIRST_SELECTED"), Status.FAIL);
        }

        // End of validation for From Account dropdown
        addToReport("----------End of validation of all eligible accounts to perform transfer are available in the From Account Drop down and default primary account is selected----------", Status.PASS, false);
        addToReport("----------Start of validation of errors on mandatory fields----------", Status.PASS, false);
        clickOnElement(btnSubmit);

        // Map of field names and their corresponding error messages
        Map<String, String> mandatoryFieldErrors = new LinkedHashMap<>();
        mandatoryFieldErrors.put("Credit Card Number", errCreditCardNumberRequired);
        mandatoryFieldErrors.put("Re-enter Credit Card Number", errReEnterCreditCardNumberRequired);
        mandatoryFieldErrors.put("Name on Card", errNameOnCardRequired);
        mandatoryFieldErrors.put("Bank", errBankRequired);
        mandatoryFieldErrors.put("Amount", errAmountRequired);
        mandatoryFieldErrors.put("Purpose", errPurposeRequired);
        mandatoryFieldErrors.put("Beneficiary Remark", errBeneficiaryRemarkRequired);
        try {
            // Loop through the map and validate error messages
            for (Map.Entry<String, String> field : mandatoryFieldErrors.entrySet()) {
                String fieldName = field.getKey();
                String errorMessage = field.getValue();

                if (isElementPresentBy(lblErrorMessage(errorMessage))) {
                    addToReport("Error message: '" + errorMessage + "' for field '" + fieldName + "' is correctly displayed", Status.PASS, false);
                } else {
                    addToReport("Error message: '" + errorMessage + "' for field '" + fieldName + "' is NOT displayed as expected", Status.FAIL);
                }
            }

        } catch (Exception e) {
            addToReport("Error while checking the error messages: " + e.getMessage(), Status.FAIL);
        }
        addToReport("----------End of validation of errors on mendatory fields----------", Status.PASS, true);
        addToReport("----------Start of validation of whether the correct Maximum daily transaction limit  for other credit cards is shown----------", Status.PASS, false);

        sendKeysToElement(tfEnterCreditCardNo, creditCardNumber);
        sendKeysToElement(tfReEnterCreditCardNo, reEnterCardNumber);
        sendKeysToElement(tfEnterCreditCardName, nameOnCard);
        selectFromDropdown(ddBank, bankName, "visibletext");

//        if (branchName != null && !branchName.trim().isEmpty()) {
//            waitForElementToBeInvisible(lblToAccountGrayLoader, LONG_WAIT);
//            waitForElementToBeClickable(ddBranch, 15);
//            selectFromDropdown(ddBranch, branchName, "visibletext");
//        }
        sendKeysToElement(tfEnterAmount, mdtAmount);
        // Select purpose
        selectFromDropdown(ddPurpose, purpose, "value");

        // Enter the sender's remark (max 20 characters)
        sendKeysToElement(tfEnterSenderRemark, sRemark);

        // Enter the beneficiary's remark (max 20 characters)
        sendKeysToElement(tfEnterBeneficiaryRemark, bRemark);

        if (getCharacterCount(getAttributeOrText(tfEnterSenderRemark, "value")) == 20) {
            addToReport("Sender remark has accepted upto 20 number of characters", Status.PASS, false);
        } else {
            addToReport("Sender remark has not accepted upto 20 number of characters", Status.FAIL);
        }
        if (getCharacterCount(getAttributeOrText(tfEnterBeneficiaryRemark, "value")) == 20) {
            addToReport("Beneficiary remark has accepted upto 20 number of characters", Status.PASS, false);
        } else {
            addToReport("Beneficiary remark has not accepted upto 20 number of characters", Status.FAIL);
        }

        // Select the transfer mode based on the input type
        if (transferMode.equals(kwTransfersMap.get("KW_ONE_TIME_TRANSACTION"))) {
            clickOnElement(rdoOTTransaction); // Select 'One Time Transaction'
        } else if (transferMode.equals(kwTransfersMap.get("KW_SETUP_STANDING_ORDER_SCHEDULE"))) {
            clickOnElement(rdoSchadule); // Select 'Schedule Transaction'
        }
        clickOnElement(chkSavePayee);
        waitForElementToBeClickable(tfNickName, MODERATE_WAIT);
        sendKeysToElement(tfNickName, templateName);

        clickOnElement(btnSubmit);
        checkPopupMessage(errorMessageMaxTransactionLimit);

        addToReport("----------End of validation of whether the correct Maximum daily transaction limit  for other credit cards is shown----------", Status.PASS, false);
        addToReport("----------Start of validation of entering the amount under multiple criteria----------", Status.PASS, false);

        String[] amt = fromAccDropdownValue.get(0).split(currencyValue);

        // Validate daily limit by checking available balance from account
        String amountStr = amt[1].trim().replace(",", "");
        double amountDouble = Double.parseDouble(amountStr);
        int wholeAmount = (int) Math.round(amountDouble);
        int maxAmount = wholeAmount + 1;
        sendKeysToElement(tfEnterAmount, Keys.BACK_SPACE, 8);
        sendKeysToElement(tfEnterAmount, String.valueOf(maxAmount));
        clickOnElement(btnSubmit);

        try {
            // Validate the error message
            if (isElementPresentBy(lblErrorMessage(errInsufficientfunds))) {
                addToReport("Error message: '" + errInsufficientfunds + "' is correctly displayed", Status.PASS, true);
            } else {
                addToReport("Error message: '" + errInsufficientfunds + "' is NOT displayed as expected", Status.FAIL);
            }
        } catch (Exception e) {
            addToReport("Error while checking the error messages: " + e.getMessage(), Status.FAIL);
        }


        sendKeysToElement(tfEnterAmount, Keys.BACK_SPACE, 8);
        // Enter the minimum amount of the transaction category as 0
        sendKeysToElement(tfEnterAmount, String.valueOf(minAmount));
        try {
            // Validate the error message
            if (isElementPresentBy(lblErrorMessage(errMinimumTransferAmount))) {
                addToReport("Error message: '" + errMinimumTransferAmount + "' is correctly displayed", Status.PASS, true);
            } else {
                addToReport("Error message: '" + errMinimumTransferAmount + "' is NOT displayed as expected", Status.FAIL);
            }
        } catch (Exception e) {
            addToReport("Error while checking the error messages: " + e.getMessage(), Status.FAIL);
        }


        //Enter amount within the accepted parameter
        try {
            if (wholeAmount > 500) {
                sendKeysToElement(tfEnterAmount, Keys.BACK_SPACE, 3);
                sendKeysToElement(tfEnterAmount, String.valueOf(amount));
            } else {
                addToReport("Amount in the account : '" + wholeAmount + "' is NOT sufficient to perform a transfer", Status.FAIL);
                throw new RuntimeException("Not sufficient funds in the account");
            }
        } catch (Exception e) {
            addToReport("Error Not sufficient funds in the account :" + e.getMessage(), Status.FAIL);
        }
        clickOnElement(btnSubmit);

        addToReport("----------End of validation of entering the amount under multiple criteria----------", Status.PASS, true);
        addToReport("----------Start of validation of OTP confirmation page----------", Status.PASS, false);
        //Validate the OTP confirmation
        validateOtpPageDetails(accountNumbers.get(0), String.valueOf(amount), "", sRemark, bRemark, transferMode, kwTransfersMap, currencyValue, bankName, "", "", creditCardNumber);
        String headerContent = getTextFromElement(tfOTPConfirmationHeaderContent);

        if (headerContent.contains(nameOnCard)) {
            addToReport("Card name : '" + headerContent + "' is available under header content", Status.FAIL);
        } else {
            addToReport("Card name : '" + wholeAmount + "' is NOT available under header content", Status.FAIL);
        }

        //Enter OTP
        waitForElementPresence(tfOTP(1), LONG_WAIT);
        sendKeysToElement(tfOTP(1), String.valueOf(otpValue));
        clickOnElement(btnConfirm);
        waitForElementToBeInvisible(btnNextLoading, LONG_WAIT);

        addToReport("----------End of validation of OTP confirmation page----------", Status.PASS, true);
        addToReport("----------Start of validation of OTP success page----------", Status.PASS, false);

        //Validate the success label,payee name,pay from,amount,payment mode and entered reference while retrieving the reference number
        if (isElementPresentBy(lblSuccess)) {
            addToReport("Validated the success message in the OTP success page", Status.PASS, false);
        } else {
            addToReport("Failed to validate the success message in the OTP success page", Status.FAIL);
        }
        String[] referenceNumber = getTextFromElement(lblRefernceID).split("- ");
        if (referenceNumber[1] != null) {
            addToReport("Obtained the payment reference number " + referenceNumber[1], Status.PASS, false);
        } else {
            addToReport("Failed to get the reference number", Status.FAIL);
        }

        //Validate the OTP success
        validateOtpPageDetails(accountNumbers.get(0), amount, "", sRemark, bRemark, transferMode, kwTransfersMap, currencyValue, bankName, "", "", creditCardNumber);

        //Check for download option
        if (isElementPresentBy(btnPrint)) {
            addToReport("Validated the download option availability in OTP success page", Status.PASS, false);
        } else {
            addToReport("Failed to validate the download option availability in the OTP success page", Status.FAIL);
        }
        clickOnElement(btnPrint);

        //Wait for download to initiate - update this with dynamic once stabilized
        waitFor(5);

        // Get the latest downloaded file
        File latestFile = getLatestDownloadedFile(downloadDirectory);

        if (latestFile != null) {
            // Extract text from the PDF
            String extractedText = extractTextFromPDF(latestFile.getAbsolutePath()).replace("/n", "");

            addToReport(" Latest downloaded pdf :  : '" + extractedText, Status.INFO, false);
            System.out.println("Latest Downloaded PDF: " + extractedText);

            waitForElementToBeInvisible(popUpPDFDownload, LONG_WAIT);

            //validate bank name
            if (extractedText.contains(bankName.trim())) {
                addToReport(" Validated bank name " + bankName + " in the downloaded record", Status.PASS, false);
            } else {
                addToReport(" Failed to validate bank name in the downloaded record", Status.FAIL, false);
            }
        } else {
            addToReport(" Failed to download the payment record", Status.FAIL, false);
        }


        //Validate bank name

        addToReport("----------End of validation of OTP success page----------", Status.PASS, true);

        addToReport("----------Start of validation of search saved template and delete----------", Status.PASS, false);

        //Close the popup
        waitForElementToBeClickable(btnOTPClosePopup, SHORT_WAIT);
        clickOnElement(btnOTPClosePopup);
        scrollPageToTop();
        selectTabUnderSendMoney(savedPayees);

        waitForElementToBeClickable(btnSearchPayee, LONG_WAIT);
        scrollPageToTop();
        sendKeysToElement(tfSearch, templateName);
        clickOnElementUsingJS(btnSearchPayee);

        waitForElementToBeInvisible(icnSavedPayeeGridLoading, LONG_WAIT);

        //Validate the search results
        int recordCount = isElementsPresentBy(tblRows);
        if (recordCount != 0) {
            for (int inc = 1; inc <= recordCount; inc++) {
                //Table retrieved value equals template name then delete
                if (getTextFromElement(lblSavedPayeeTemplateName(inc)).equals(templateName)) {

                    //Validate if account name is disabled under edit
                    clickOnElementUsingJS(btnEditSavedPayeeTemplate(templateName));

                    if (isElementPresentBy(tfDisabledName(TransactionConstants.CARD_NAME))) {
                        addToReport("Card name is disabled", Status.PASS, true);
                    } else {
                        addToReport("Card name is not disabled", Status.FAIL);
                    }

                    String cName = getAttributeOrText(tfDisabledName(TransactionConstants.CARD_NAME), "value");

                    if (nameOnCard.equals(cName)) {
                        addToReport("Name on card validated successfully. Expected: '" + cName + "', Found: '" + nameOnCard + "'", Status.PASS, true);
                    } else {
                        addToReport("Failed to validate name on card. Expected: '" + cName + "', Found: '" + nameOnCard + "'", Status.FAIL);
                    }

                    clickOnElementUsingJS(getElementByTypeAndText(ElementType.button, TransactionConstants.BACK));

                    //Validate if account name is loaded correctly under pay now
                    clickOnElementUsingJS(lblSavedPayeeTemplateName(inc));

                    waitForElementToBeClickable(getElementByTypeAndText(ElementType.button, TransactionConstants.PAY_NOW), MODERATE_WAIT);

                    clickOnElementUsingJS(getElementByTypeAndText(ElementType.button, TransactionConstants.PAY_NOW));

                    waitForElementToBeInvisible(lblToAccountGrayLoader, LONG_WAIT);

                    cName = getAttributeOrText(tfQFTTextField(TransactionConstants.CARD_NAME), "value");

                    if (nameOnCard.equals(cName)) {
                        addToReport("Name on QFT popup validated successfully. Expected: '" + cName + "', Found: '" + nameOnCard + "'", Status.PASS, true);
                    } else {
                        addToReport("Failed to validate name on QFT popup. Expected: '" + cName + "', Found: '" + nameOnCard + "'", Status.FAIL);
                    }

                    waitForElementToBeClickable(getElementByTypeAndText(ElementType.button, TransactionConstants.BACK), LONG_WAIT);
                    clickOnElementUsingJS(getElementByTypeAndText(ElementType.button, TransactionConstants.BACK));

                    //Delete template
                    waitForElementToBeClickable(btnDeleteSavedPayeeTemplate(templateName), LONG_WAIT);
                    clickOnElementUsingJS(lblSavedPayeeTemplateName(inc));
                    addToReport(" Clicked saved template " + templateName, Status.PASS, true);
                    clickOnElementUsingJS(btnDeleteSavedPayeeTemplate(templateName));
                    break;
                }
            }

            //Delete the template
            clickOnElement(btnClosePopup);
            waitForElementPresence(tfOTP(1), LONG_WAIT);
            sendKeysToElement(tfOTP(1), String.valueOf(otpValue));
            addToReport(" OTP confirmation for template deletion of " + templateName, Status.PASS, true);
            clickOnElement(btnConfirm);
            waitForElementToBeInvisible(btnNextLoading, LONG_WAIT);

            addToReport(" Clicked confirm deletion for template : " + templateName, Status.PASS, true);

            selectTabUnderSendMoney(savedPayees);
            waitForElementToBeClickable(btnSearchPayee, LONG_WAIT);

            sendKeysToElement(tfSearch, templateName);
            clickOnElement(btnSearchPayee);

            waitForElementToBeInvisible(icnSavedPayeeGridLoading, LONG_WAIT);

            //Validate the search results
            recordCount = isElementsPresentBy(tblRows);
            if (recordCount != 0) {
                addToReport(" Template is still available under saved payee search " + templateName, Status.FAIL, true);
            } else {
                addToReport(" Template " + templateName + " has been successfully deleted", Status.PASS, true);
            }
        } else {
            addToReport(" Failed to search saved template " + templateName, Status.FAIL);
        }
        addToReport("----------End of validation of search saved template and delete----------", Status.PASS, false);


    }


    /**
     * Executes a mobile cash transaction after validating all required fields and business rules.
     *
     * @param errNICNumberRequired            Error message shown when NIC number is not provided
     * @param errMobileNumberRequired         Error message shown when mobile number is not provided
     * @param errReEnterMobileNumberRequired  Error message shown when re-entered mobile number is not provided
     * @param errNameRequired                 Error message shown when the name is not provided
     * @param errAmountRequired               Error message shown when the amount is not provided
     * @param errPurposeRequired              Error message shown when the transaction purpose is not provided
     * @param errBeneficiaryRemarkRequired    Error message shown when beneficiary remark is not provided
     * @param errorMessageMultipleOfHundred   Error message when the amount is not a multiple of 100
     * @param nicNumber                       National Identity Card number of the receiver
     * @param mobileNo                        Receiver's mobile number
     * @param name                            Receiver's name
     * @param purpose                         Purpose of the transaction
     * @param sRemark                         Sender's remark or additional note
     * @param errorMessageMaxTransactionLimit Error message when the transaction amount exceeds the allowed limit
     * @param amount                          Amount to be transferred
     * @param otpValue                        One-time password used for validating the transaction
     * @param minAmount                       Minimum transaction amount allowed
     * @param errInsufficientfunds            Error message shown when there are insufficient funds in sender's account
     * @param errMinimumTransferAmount        Error message shown when the transfer amount is below the allowed minimum
     * @param currencyValue                   Currency in which the transaction is performed
     */
    public void makeMobileCashTransactions(String errNICNumberRequired, String errMobileNumberRequired, String errReEnterMobileNumberRequired, String errNameRequired, String errAmountRequired, String errPurposeRequired, String errBeneficiaryRemarkRequired, String errorMessageMultipleOfHundred, String nicNumber, String mobileNo, String name, String purpose, String sRemark, String errorMessageMaxTransactionLimit, String amount, String otpValue, String minAmount, String errInsufficientfunds, String errMinimumTransferAmount, String currencyValue, String maxAmountInHundred, String downloadDirectory) {
        // Report the start of the validation process
        addToReport("----------Start of validation of all eligible accounts to perform transfer are available in the From Account Drop down and default primary account is selected----------", Status.PASS, false);

        // Obtain account numbers from dashboard
        List<String> accountNumbers = getValues();

        // Scroll to top and wait for page load
        scrollPageToTop();
        waitForPageLoadCompleteJS();
        waitForElementToBeInvisible(lblToAccountGrayLoader, LONG_WAIT);


        // Validate the account number
        String pAccountNo = getTextFromElement(lblSavingsAccountNo);
        String expectedAccountNo = accountNumbers.get(0);

        if (pAccountNo.equals(expectedAccountNo)) {
            addToReport("Primary account number validated successfully. Expected: '" + expectedAccountNo + "', Found: '" + pAccountNo + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate primary account number. Expected: '" + expectedAccountNo + "', Found: '" + pAccountNo + "'", Status.FAIL);
        }

        // Wait for 6 seconds due to sporadic failures in ALL_OPTIONS_VALUE selection
        waitFor(SHORT_WAIT);

        // Obtain the first selected value from the dropdown
        List<String> fromAccDropdownValue = getSelectedOptionText(ddFromAccount, "FIRST_SELECTED");
        if (fromAccDropdownValue.get(0).contains(accountNumbers.get(0))) {
            addToReport("Primary account number " + getSelectedOptionText(ddFromAccount, "FIRST_SELECTED") + " is displayed by default in the pay from dropdown", Status.PASS, true);
        } else {
            addToReport("Primary account number is not displayed by default in the pay from dropdown :" + getSelectedOptionText(ddFromAccount, "FIRST_SELECTED"), Status.FAIL);
        }

        // End of validation for From Account dropdown
        addToReport("----------End of validation of all eligible accounts to perform transfer are available in the From Account Drop down and default primary account is selected----------", Status.PASS, false);
        addToReport("----------Start of validation of errors on mendatory fields----------", Status.PASS, false);
        clickOnElement(btnSubmit);

        // Map of field names and their corresponding error messages
        Map<String, String> mandatoryFieldErrors = new LinkedHashMap<>();
        mandatoryFieldErrors.put(TransactionConstants.CONSTANTS_MAP.get("KW_NIC_NUMBER"), errNICNumberRequired);
        mandatoryFieldErrors.put(TransactionConstants.CONSTANTS_MAP.get("KW_MOBILE_NUMBER"), errMobileNumberRequired);
        mandatoryFieldErrors.put(TransactionConstants.CONSTANTS_MAP.get("KW_RE_ENTER_MOBILE_NUMBER"), errReEnterMobileNumberRequired);
        mandatoryFieldErrors.put(TransactionConstants.CONSTANTS_MAP.get("KW_NAME"), errNameRequired);
        mandatoryFieldErrors.put(TransactionConstants.CONSTANTS_MAP.get("KW_PURPOSE"), errPurposeRequired);
        mandatoryFieldErrors.put(TransactionConstants.CONSTANTS_MAP.get("KW_AMOUNT"), errAmountRequired);
        mandatoryFieldErrors.put(TransactionConstants.CONSTANTS_MAP.get("KW_BENEFICIARY_REMARKS"), errBeneficiaryRemarkRequired);
        try {
            // Loop through the map and validate error messages
            for (Map.Entry<String, String> field : mandatoryFieldErrors.entrySet()) {
                String fieldName = field.getKey();
                String errorMessage = field.getValue();

                if (isElementPresentBy(lblErrorMessage(errorMessage))) {
                    addToReport("Error message: '" + errorMessage + "' for field '" + fieldName + "' is correctly displayed", Status.PASS, false);
                } else {
                    addToReport("Error message: '" + errorMessage + "' for field '" + fieldName + "' is NOT displayed as expected", Status.FAIL);
                }
            }
            addToReport("----------End of validation of errors on mandatory fields without any values----------", Status.PASS, true);
            sendKeysToElement(getElementByName(ElementType.input, TransactionConstants.AMOUNT), TransactionConstants.NUMERICAL_ONE);
            clickOnElement(btnSubmit);
            if (isElementPresentBy(lblErrorMessage(errorMessageMultipleOfHundred))) {
                addToReport("Error message: '" + errorMessageMultipleOfHundred + "' for field amount is correctly displayed", Status.PASS, true);
            } else {
                addToReport("Error message: '" + errorMessageMultipleOfHundred + "' for field amount is NOT displayed as expected", Status.FAIL);
            }


        } catch (Exception e) {
            addToReport("Error while checking the error messages: " + e.getMessage(), Status.FAIL);
        }
        addToReport("----------End of validation of errors on mandatory fields----------", Status.PASS, true);

        addToReport("----------Start of validation of whether the correct Maximum daily transaction limit  for mobile cash is shown----------", Status.PASS, false);

        //Generate random mobile no
        mobileNo = mobileNo + generateNumbers(8);

        sendKeysToElement(getElementByName(ElementType.input, TransactionConstants.CONSTANTS_MAP.get("KW_NIC")), nicNumber);
        sendKeysToElement(getElementByName(ElementType.input, TransactionConstants.MOBILE_NO), mobileNo);
        sendKeysToElement(getElementByName(ElementType.input, TransactionConstants.REMOBILE_NO), mobileNo);
        sendKeysToElement(getElementByName(ElementType.input, TransactionConstants.NAME), name);
        selectFromDropdown(ddMCashBank, purpose, "value");
        sendKeysToElement(tfEnterAmount, Keys.BACK_SPACE, 3);
        sendKeysToElement(getElementByName(ElementType.input, TransactionConstants.AMOUNT), maxAmountInHundred);
        // Enter the sender's remark (max 20 characters)
        sendKeysToElement(getElementByName(ElementType.input, TransactionConstants.REMARK), sRemark);

        if (getCharacterCount(getAttributeOrText(getElementByName(ElementType.input, TransactionConstants.REMARK), "value")) == 20) {
            addToReport("Remark has accepted upto 20 number of characters", Status.PASS, false);
        } else {
            addToReport("Remark has not accepted upto 20 number of characters", Status.FAIL);
        }

        clickOnElement(btnSubmit);
        checkPopupMessage(errorMessageMaxTransactionLimit);

        addToReport("----------End of validation of whether the correct Maximum daily transaction limit  for other credit cards is shown----------", Status.PASS, false);
        addToReport("----------Start of validation of entering the amount under multiple criteria----------", Status.PASS, false);

        String[] amt = fromAccDropdownValue.get(0).split(currencyValue);
        String amountStr = amt[1].trim().replace(",", "");
        double amountDouble = Double.parseDouble(amountStr);
        int wholeAmount = (int) Math.round(amountDouble);
        // Round up to next highest hundred
        int maxAmount = ((wholeAmount + 99) / 100) * 100;

        // Validate daily limit by checking available balance from account
        sendKeysToElement(tfEnterAmount, Keys.BACK_SPACE, 8);
        sendKeysToElement(tfEnterAmount, String.valueOf(maxAmount));
        clickOnElement(btnSubmit);

        try {
            // Validate the error message
            if (isElementPresentBy(lblErrorMessage(errInsufficientfunds))) {
                addToReport("Error message: '" + errInsufficientfunds + "' is correctly displayed", Status.PASS, true);
            } else {
                addToReport("Error message: '" + errInsufficientfunds + "' is NOT displayed as expected", Status.FAIL);
            }
        } catch (Exception e) {
            addToReport("Error while checking the error messages: " + e.getMessage(), Status.FAIL);
        }


        sendKeysToElement(tfEnterAmount, Keys.BACK_SPACE, 8);
        // Enter the minimum amount of the transaction category as 1
        sendKeysToElement(tfEnterAmount, String.valueOf(minAmount));
        clickOnElement(btnSubmit);
        checkPopupMessage(errMinimumTransferAmount);


        //Enter amount within the accepted parameter
        try {
            if (wholeAmount > 800) {
                sendKeysToElement(tfEnterAmount, Keys.BACK_SPACE, 3);
                sendKeysToElement(tfEnterAmount, String.valueOf(amount));
            } else {
                addToReport("Amount in the account : '" + wholeAmount + "' is NOT sufficient to perform a transfer", Status.FAIL);
                throw new RuntimeException("Not sufficient funds in the account");
            }
        } catch (Exception e) {
            addToReport("Error Not sufficient funds in the account :" + e.getMessage(), Status.FAIL);
        }
        clickOnElement(btnSubmit);

        addToReport("----------End of validation of entering the amount under multiple criteria----------", Status.PASS, true);
        addToReport("----------Start of validation of OTP confirmation page----------", Status.PASS, false);

        if (isElementClickable(btnClosePopup)) {
            addToReport("Popup message appeared", Status.INFO, true);
        }
        //Validate the OTP confirmation
        validateOtpConfirmationField(TransactionConstants.CONSTANTS_MAP.get("KW_Transfer_FROM"), accountNumbers.get(0).replace(" ", ""));
        validateOtpConfirmationField(TransactionConstants.CONSTANTS_MAP.get("KW_PURPOSE"), purpose);
        validateOtpConfirmationField(TransactionConstants.CONSTANTS_MAP.get("KW_RECEIVERS_MOBILE_NUMBER"), mobileNo);
        validateOtpConfirmationField(TransactionConstants.CONSTANTS_MAP.get("KW_AMOUNT"), currencyValue + amount);
        validateOtpConfirmationField(TransactionConstants.CONSTANTS_MAP.get("KW_RECEIVERS_NIC"), nicNumber);
        validateOtpConfirmationField(TransactionConstants.CONSTANTS_MAP.get("KW_REMARKS"), sRemark);

        String headerContent = getTextFromElement(tfOTPConfirmationHeaderContent);

        if (headerContent.contains(TransactionConstants.MOBILE_CASH)) {
            addToReport("Header : '" + headerContent + "' is available under header content", Status.FAIL);
        } else {
            addToReport("Header : '" + wholeAmount + "' is NOT available under header content", Status.FAIL);
        }

        //Enter OTP
        waitForElementPresence(tfOTP(1), LONG_WAIT);
        sendKeysToElement(tfOTP(1), String.valueOf(otpValue));
        clickOnElement(btnConfirm);
        waitForElementToBeInvisible(btnNextLoading, LONG_WAIT);
        // Check if the popup message is available
        if (isElementClickable(btnClosePopup)) {
            waitFor(1);
            addToReport("Popup message is correctly", Status.PASS, true);
            waitFor(1);
        } else {
            addToReport("Popup message is NOT displayed", Status.FAIL);
        }

        addToReport("----------End of validation of OTP confirmation page----------", Status.PASS, true);
        addToReport("----------Start of validation of OTP success page----------", Status.PASS, false);

        //Validate the success label,payee name,pay from,amount,payment mode and entered reference while retrieving the reference number
        if (isElementPresentBy(lblSuccess)) {
            waitForElementToBeClickable(btnOTPClosePopup, SHORT_WAIT);
            addToReport("Validated the success message in the OTP success page", Status.PASS, true);
        } else {
            addToReport("Failed to validate the success message in the OTP success page", Status.FAIL);
        }
        String[] referenceNumber = getTextFromElement(lblRefernceID).split("- ");
        if (referenceNumber[1] != null) {
            addToReport("Obtained the payment reference number " + referenceNumber[1], Status.PASS, false);
        } else {
            addToReport("Failed to get the reference number", Status.FAIL);
        }

        //Validate the OTP success
        validateOtpConfirmationField(TransactionConstants.CONSTANTS_MAP.get("KW_Transfer_FROM"), accountNumbers.get(0).replace(" ", ""));
        validateOtpConfirmationField(TransactionConstants.CONSTANTS_MAP.get("KW_PURPOSE"), purpose);
        validateOtpConfirmationField(TransactionConstants.CONSTANTS_MAP.get("KW_RECEIVERS_MOBILE_NUMBER"), mobileNo);
        validateOtpConfirmationField(TransactionConstants.CONSTANTS_MAP.get("KW_AMOUNT"), currencyValue + amount);
        validateOtpConfirmationField(TransactionConstants.CONSTANTS_MAP.get("KW_RECEIVERS_NIC"), nicNumber);
        validateOtpConfirmationField(TransactionConstants.CONSTANTS_MAP.get("KW_REMARKS"), sRemark);

        //Check for download option
        if (isElementPresentBy(btnPrint)) {
            addToReport("Validated the download option availability in OTP success page", Status.PASS, false);
        } else {
            addToReport("Failed to validate the download option availability in the OTP success page", Status.FAIL);
        }
        addToReport("----------End of validation of OTP success page----------", Status.PASS, true);
        addToReport("----------Start of validation of mobile number in downloaded file ----------", Status.PASS, true);
        clickOnElement(btnPrint);

        //Wait for download to initiate - update this with dynamic once stabilized
        waitFor(5);

        // Get the latest downloaded file
        File latestFile = getLatestDownloadedFile(downloadDirectory);

        if (latestFile != null) {
            // Extract text from the PDF
            String extractedText = extractTextFromPDF(latestFile.getAbsolutePath()).replace("/n", "");

            addToReport(" Latest downloaded pdf :  : '" + extractedText, Status.INFO, false);
            System.out.println("Latest Downloaded PDF: " + extractedText);

            waitForElementToBeInvisible(popUpPDFDownload, LONG_WAIT);

            //validate payment id
            if (extractedText.contains(mobileNo.trim())) {
                addToReport(" Validated mobile no " + mobileNo + " in the downloaded record", Status.PASS, false);
            } else {
                addToReport(" Failed to validate mobile number in the downloaded record", Status.FAIL, false);
            }
        } else {
            addToReport(" Failed to download the payment record", Status.FAIL, false);
        }

        clickOnElement(btnOTPClosePopup);
        addToReport("----------End of validation of mobile number in downloaded file ----------", Status.PASS, true);
        addToReport("----------Start of validation of successful mobile cash transfer inquiry----------", Status.PASS, false);
        waitForElementToBeClickable(getElementByTypeAndText(ElementType.div, TransactionConstants.SENT), MODERATE_WAIT);
        clickOnElementUsingJS(getElementByTypeAndText(ElementType.div, TransactionConstants.SENT));
        waitForElementToBeInvisible(lblAnimatePulseLoader, MODERATE_WAIT);
        scrollToWebElement(getElementByTypeAndText(ElementType.div, mobileNo));

        if (isElementPresentBy(getElementByTypeAndText(ElementType.div, mobileNo))) {
            addToReport("Validated the mobile cash transfer is available for inquiry under sent for mobile number " + mobileNo, Status.PASS, true);
        } else {
            addToReport("Failed to validate the mobile cash transfer is available for inquiry under sent", Status.FAIL);
        }

        addToReport("----------End of validation of successful mobile cash transfer inquiry----------", Status.PASS, false);
    }


    /**
     * This method performs the process of making an own account transaction. It validates inputs, performs checks,
     * and simulates the process of transferring funds, including the validation of errors and confirmation of the transaction.
     *
     * @param minAmountEntry          The minimum transfer amount to be entered during the transaction.
     * @param maxAmountEntry          The maximum transfer amount to be entered during the transaction.
     * @param minAmountMsg            The actual error message for the minimum amount.
     * @param maxAmountMsg            The actual error message for the maximum amount.
     * @param toAccount               The recipient's account number to which funds will be transferred.
     * @param amount                  The transaction amount that is to be transferred.
     * @param sRemark                 The sender's remark for the transaction.
     * @param bRemark                 The beneficiary's remark for the transaction.
     * @param transferMode            The transfer mode (e.g., One-time Transaction or Scheduled).
     * @param kwTransfersMap          A map that contains the transfer mode constants (e.g., "KW_ONE_TIME_TRANSACTION").
     * @param currencyType            The currency type to be used for the transaction (e.g., "LKR").
     * @param OTPValue                The one-time password (OTP) used for confirming the transaction.
     * @param receiverName            The name of the receiver of the funds.
     * @param purpose                 The purpose of the transaction (e.g., "Profit Income").
     * @param bankName                The bank name through which the transaction is processed.
     * @param actualTransactionAmount The final transaction amount to be used after validation.
     */
    public void makeWithinOwnBankTransactions(String errorMsgInsufficientFunds, String minAmountEntry, String maxAmountEntry,
                                              String minAmountMsg, String maxAmountMsg, String toAccount, String amount,
                                              String sRemark, String bRemark, String transferMode, Map<String, String> kwTransfersMap,
                                              String currencyType, String OTPValue, String receiverName, String purpose,
                                              String bankName, String bankNameTwo, String actualTransactionAmount) {

        // Report the start of the validation process
        addToReport("----------Start of validation of all eligible accounts to perform bill payments are available in the From Account Drop down and default primary account is selected----------", Status.PASS, false);

        // Obtain account numbers from dashboard
        List<String> accountNumbers = getValues();

        // Scroll to top and wait for page load
        scrollPageToTop();
        waitForPageLoadCompleteJS();
        waitForElementToBeInvisible(lblToAccountGrayLoader, LONG_WAIT);

        // Validate the account number
        String pAccountNo = getTextFromElement(lblSavingsAccountNo);
        String expectedAccountNo = accountNumbers.get(0);

        if (pAccountNo.equals(expectedAccountNo)) {
            addToReport("Primary account number validated successfully. Expected: '" + expectedAccountNo + "', Found: '" + pAccountNo + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate primary account number. Expected: '" + expectedAccountNo + "', Found: '" + pAccountNo + "'", Status.FAIL);
        }

        // Wait for 6 seconds due to sporadic failures in ALL_OPTIONS_VALUE selection
        waitFor(SHORT_WAIT);

        // Obtain the first selected value from the dropdown
        List<String> fromAccDropdownValue = getSelectedOptionText(ddFromAccount, "FIRST_SELECTED");
        if (fromAccDropdownValue.get(0).contains(accountNumbers.get(0))) {
            addToReport("Primary account number " + getSelectedOptionText(ddFromAccount, "FIRST_SELECTED") + " is displayed by default in the pay from dropdown", Status.PASS, true);
        } else {
            addToReport("Primary account number is not displayed by default in the pay from dropdown :" + getSelectedOptionText(ddFromAccount, "FIRST_SELECTED"), Status.FAIL);
        }

        // End of validation for From Account dropdown
        addToReport("----------End of validation of all eligible accounts to perform bill payments are available in the From Account Drop down and default primary account is selected----------", Status.PASS, false);
        addToReport("----------Start of validation of whether entering the amount , amount (Amount+ commission if it is an other bank transfer) needs to be in order----------", Status.PASS, false);

        // Select a bank from the dropdown
        addToReport("----------Start of validation of beneficiary remark showing only for sampath bank ----------", Status.PASS, false);

        // Select bank Name two
        selectFromDropdown(ddBank, bankNameTwo, "visibletext");
        if (!isElementPresentBy(tfEnterSenderRemark, VERY_SHORT_WAIT)) {
            addToReport("Sender remark field is not displayed", Status.PASS, true);
        } else {
            addToReport("Sender remark field is displayed", Status.FAIL, true);
        }

        selectFromDropdown(ddBank, bankName, "visibletext");
        if (isElementPresentBy(tfEnterSenderRemark)) {
            addToReport("Sender remark field is displayed", Status.PASS, true);
        } else {
            addToReport("Sender remark field is not displayed", Status.FAIL, true);
        }

        addToReport("----------End of validation of beneficiary remark showing only for sampath bank----------", Status.PASS, false);
        // Enter account and account re-entry
        sendKeysToElement(tfToAccount, toAccount);
        sendKeysToElement(tfToAccountReEnter, toAccount);

        // Enter receiver name
        sendKeysToElement(tfNameOfTheReceiver, receiverName);

        // Select purpose
        selectFromDropdown(ddPurpose, purpose, "value");

        // Enter sender's and beneficiary remarks
        sendKeysToElement(tfEnterSenderRemark, sRemark);
        sendKeysToElement(tfEnterBeneficiaryRemark, bRemark);

        if (getCharacterCount(getAttributeOrText(tfEnterSenderRemark, "value")) == 20) {
            addToReport("Sender remark has accepted up to 20 number of characters", Status.PASS, false);
        } else {
            addToReport("Sender remark has not accepted up to 20 number of characters", Status.FAIL);
        }
        if (getCharacterCount(getAttributeOrText(tfEnterBeneficiaryRemark, "value")) == 20) {
            addToReport("Beneficiary remark has accepted up to 20 number of characters", Status.PASS, false);
        } else {
            addToReport("Beneficiary remark has not accepted up to 20 number of characters", Status.FAIL);
        }

        // Select transfer mode based on the input transferMode
        if (transferMode.equals(kwTransfersMap.get("KW_ONE_TIME_TRANSACTION"))) {
            clickOnElement(rdoOTTransaction); // Select 'One Time Transaction'
        } else if (transferMode.equals(kwTransfersMap.get("KW_SETUP_STANDING_ORDER_SCHEDULE"))) {
            clickOnElement(rdoSchadule); // Select 'Schedule Transaction'
        }
        // Enter amount and validate
        scrollPageToTop();
        sendKeysToElement(tfEnterAmount, minAmountEntry);

        // Validate the error message for the minimum transfer amount
        try {
            if (isElementPresentBy(lblErrorMessage(minAmountMsg)) && isElementClickable(btnClosePopup)) {
                addToReport("Error message: '" + minAmountMsg + "' is correctly displayed", Status.PASS, true);
            } else {
                addToReport("Error message: '" + minAmountMsg + "' is NOT displayed as expected", Status.FAIL);
            }
        } catch (Exception e) {
            addToReport("Error while checking the error messages: " + e.getMessage(), Status.FAIL);
        }

        // Delete the previous amount entry
        sendKeysToElement(tfEnterAmount, Keys.BACK_SPACE, 3);
        sendKeysToElement(tfEnterAmount, maxAmountEntry);

        clickOnElement(btnSubmit);

        // Validate the error message for exceeding the max transfer amount
        checkPopupMessage(maxAmountMsg);

        // Validate daily limit by checking available balance from account
        String[] amt = fromAccDropdownValue.get(0).split(currencyType);
        String amountStr = amt[1].trim().replace(",", "");
        double amountDouble = Double.parseDouble(amountStr);
        int wholeAmount = (int) Math.round(amountDouble);
        int maxAmount = wholeAmount + 1;

        sendKeysToElement(tfEnterAmount, Keys.BACK_SPACE, 8);
        sendKeysToElement(tfEnterAmount, String.valueOf(maxAmount));
        scrollToWebElement(btnSubmit);
        // Click on the Submit button to validate error
        clickOnElement(btnSubmit);

        scrollPageToTop();
        // Validate the error message for insufficient funds
        try {
            if (isElementPresentBy(lblErrorMessage(errorMsgInsufficientFunds)) && isElementClickable(btnClosePopup)) {
                addToReport("Error message: '" + errorMsgInsufficientFunds + "' is correctly displayed", Status.PASS, true);
            } else {
                addToReport("Error message: '" + errorMsgInsufficientFunds + "' is NOT displayed as expected", Status.FAIL);
            }
        } catch (Exception e) {
            addToReport("Error while checking the error messages: " + e.getMessage(), Status.FAIL);
        }

        // Enter correct values for the transaction
        try {
            if (wholeAmount > 1) {
                sendKeysToElement(tfEnterAmount, Keys.BACK_SPACE, 8);
                sendKeysToElement(tfEnterAmount, actualTransactionAmount);
            } else {
                addToReport("Amount in the account : '" + wholeAmount + "' is NOT sufficient to perform a transfer", Status.FAIL);
                throw new RuntimeException("Not sufficient funds in the account");
            }
        } catch (Exception e) {
            addToReport("Error: Not sufficient funds in the account: " + e.getMessage(), Status.FAIL);
        }
        addToReport("----------Start of addition of favourite payee----------", Status.PASS, false);

        //Check on favourite payee
        waitForElementToBeClickable(chkSavePayee, MODERATE_WAIT);
        clickOnElement(chkSavePayee);

        //Type nickname for favourite payee
        String nickName = generateRandomName(6);
        waitForElementToBeClickable(tfNickName, MODERATE_WAIT);
        sendKeysToElement(tfNickName, nickName);

        addToReport("----------End of addition of favourite payee----------", Status.PASS, true);

        // Click on the Submit button to initiate the transfer
        clickOnElement(btnSubmit);
        waitForElementToBeInvisible(lblSubmitLoading, LONG_WAIT);
        // OTP confirmation page validation
        addToReport("----------Start of validation of OTP confirmation page----------", Status.PASS, true);
        validateOtpPageDetails(accountNumbers.get(0), actualTransactionAmount, toAccount, sRemark, bRemark, transferMode, kwTransfersMap, currencyType,
                bankName, CommonUtils.getTodayDateFormatted("yyyy-MM-dd"), purpose, "");

        // Enter OTP and submit
        waitForElementPresence(tfOTP(1), LONG_WAIT);
        sendKeysToElement(tfOTP(1), OTPValue);
        clickOnElement(btnConfirm);
        waitForElementToBeInvisible(btnNextLoading, LONG_WAIT);

        addToReport("----------End of validation of OTP confirmation page----------", Status.PASS, true);
        addToReport("----------Start of validation of OTP success page----------", Status.PASS, false);

        // Validate the success label and other information
        if (isElementPresentBy(lblSuccess)) {
            addToReport("Validated the success message in the OTP success page", Status.PASS, false);
        } else {
            addToReport("Failed to validate the success message in the OTP success page", Status.FAIL);
        }

        // Validate reference number
        String[] referenceNumber = getTextFromElement(lblRefernceID).split("- ");
        if (referenceNumber[1] != null) {
            addToReport("Obtained the payment reference number " + referenceNumber[1], Status.PASS, false);
        } else {
            addToReport("Failed to get the reference number", Status.FAIL);
        }

        // OTP success validation
        validateOtpPageDetails(accountNumbers.get(0), actualTransactionAmount, toAccount, sRemark, bRemark, transferMode, kwTransfersMap, currencyType,
                bankName, CommonUtils.getTodayDateFormatted("yyyy-MM-dd"), purpose, "");

        // Check for download option in the success page
        if (isElementPresentBy(btnPrint)) {
            addToReport("Validated the download option availability in OTP success page", Status.PASS, false);
        } else {
            addToReport("Failed to validate the download option availability in OTP success page", Status.FAIL);
        }

        addToReport("----------End of validation of OTP success page----------", Status.PASS, true);
        //Close the popup
        scrollPageToTop();
        waitForElementToBeClickable(btnOTPClosePopup, MODERATE_WAIT);
        clickOnElement(btnOTPClosePopup);

        addToReport("----------Start of validation of availability of last 10 records----------", Status.PASS, false);

        //Validate the search results
        int recordCount = isElementsPresentBy(lstRecentTransactions);
        if (recordCount == 10) {
            addToReport("Successfully validated that the last 10 transaction records are available", Status.PASS, true);
            //validate if the transactions are in chronological order
            validateTransactionChronology(lstRecentTransactions);
        } else {
            addToReport("Validation failed: Expected 10 transaction records, but found " + recordCount + ".", Status.FAIL, true);
        }
        addToReport("----------End of validation of availability of last 10 records----------", Status.PASS, false);
        addToReport("----------Start of validation of saved payee----------", Status.PASS, false);
        //Validate and delete saved payee
        selectTabUnderSendMoney(TransactionConstants.TAB_NAME_SAVED_PAYEE);

        waitForElementToBeClickable(btnSearchPayee, LONG_WAIT);
        scrollPageToTop();
        sendKeysToElement(tfSearch, nickName);
        clickOnElementUsingJS(btnSearchPayee);

        waitForElementToBeInvisible(icnSavedPayeeGridLoading, LONG_WAIT);

        //Validate the search results
        recordCount = isElementsPresentBy(tblRows);
        if (recordCount != 0) {
            for (int inc = 1; inc <= recordCount; inc++) {
                //Table retrieved value equals template name then delete
                if (getTextFromElement(lblSavedPayeeTemplateName(inc)).equals(nickName)) {

                    addToReport("Saved payee loaded successfully", Status.PASS, true);
                    //Delete template
                    waitForElementToBeClickable(btnDeleteSavedPayeeTemplate(nickName), LONG_WAIT);
                    addToReport(" Clicked saved template " + nickName, Status.PASS, true);
                    clickOnElementUsingJS(btnDeleteSavedPayeeTemplate(nickName));

                    // Enter OTP and submit
                    waitForElementPresence(tfOTP(1), LONG_WAIT);
                    sendKeysToElement(tfOTP(1), OTPValue);
                    clickOnElement(btnConfirm);
                    waitForElementToBeInvisible(btnNextLoading, LONG_WAIT);

                    if (isElementPresentBy(getPopUpMsg(TransactionConstants.BENEFICIARY_DELETED)) && isElementClickable(btnClosePopup)) {
                        addToReport("Popup delete message: '" + TransactionConstants.BENEFICIARY_DELETED + "' is correctly displayed.", Status.PASS, true);

                    } else {
                        addToReport("Popup delete message: '" + TransactionConstants.BENEFICIARY_DELETED + "' is NOT displayed as expected.", Status.FAIL);
                    }
                    recordCount = isElementsPresentBy(tblRows, VERY_SHORT_WAIT);
                    if (recordCount == 0) {
                        addToReport("Saved payee deleted successfully", Status.PASS, true);
                    } else {
                        addToReport("Saved payee was not deleted successfully", Status.FAIL, true);
                    }
                    break;
                } else {
                    addToReport("Saved beneficiary is not loaded", Status.FAIL);
                }
            }
            addToReport("----------End of validation of saved payee----------", Status.PASS, false);
        }
    }


    /**
     * This method performs the process of making an own account transaction. It validates inputs, performs checks,
     * and simulates the process of transferring funds, including the validation of errors and confirmation of the transaction.
     *
     * @param toAccount               The recipient's account number to which funds will be transferred.
     * @param bRemark                 The beneficiary's remark for the transaction.
     * @param transferMode            The transfer mode (e.g., One-time Transaction or Scheduled).
     * @param kwTransfersMap          A map that contains the transfer mode constants (e.g., "KW_ONE_TIME_TRANSACTION").
     * @param currencyType            The currency type to be used for the transaction (e.g., "LKR").
     * @param receiverName            The name of the receiver of the funds.
     * @param purpose                 The purpose of the transaction (e.g., "Profit Income").
     * @param bankName                The bank name through which the transaction is processed.
     * @param actualTransactionAmount The final transaction amount to be used after validation.
     */
    public void makeStaffAccountTransactions(String errorMsg, String toAccount, String bRemark, String transferMode, Map<String, String> kwTransfersMap,
                                             String currencyType, String receiverName, String purpose, String bankName, String actualTransactionAmount) {

        // Report the start of the validation process
        addToReport("----------Start of validation of all eligible accounts to perform bill payments are available in the From Account Drop down and default primary account is selected----------", Status.PASS, false);

        // Obtain account numbers from dashboard
        List<String> accountNumbers = getValues();
        addToReport("Accounts Found: '" + accountNumbers + "'", Status.PASS, false);

        // Scroll to top and wait for page load
        scrollPageToTop();
        waitForPageLoadCompleteJS();
        waitForElementToBeInvisible(lblToAccountGrayLoader, LONG_WAIT);

        // Validate the account number
        String pAccountNo = getTextFromElement(lblSavingsAccountNo);
        String expectedAccountNo = accountNumbers.get(0);

        if (pAccountNo.equals(expectedAccountNo)) {
            addToReport("Primary account number validated successfully. Expected: '" + expectedAccountNo + "', Found: '" + pAccountNo + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate primary account number. Expected: '" + expectedAccountNo + "', Found: '" + pAccountNo + "'", Status.FAIL);
        }

        // Wait for 6 seconds due to sporadic failures in ALL_OPTIONS_VALUE selection
        waitFor(SHORT_WAIT);

        // Obtain the first selected value from the dropdown
        List<String> fromAccDropdownValue = getSelectedOptionText(ddFromAccount, "FIRST_SELECTED");
        if (fromAccDropdownValue.get(0).contains(accountNumbers.get(0))) {
            addToReport("Primary account number " + getSelectedOptionText(ddFromAccount, "FIRST_SELECTED") + " is displayed by default in the pay from dropdown", Status.PASS, true);
        } else {
            addToReport("Primary account number is not displayed by default in the pay from dropdown :" + getSelectedOptionText(ddFromAccount, "FIRST_SELECTED"), Status.FAIL);
        }

        // End of validation for From Account dropdown
        addToReport("----------End of validation of all eligible accounts to perform bill payments are available in the From Account Drop down and default primary account is selected----------", Status.PASS, false);
        addToReport("----------Start of validation of staff user amount transfer----------", Status.PASS, false);

        // Select a bank from the dropdown
        selectFromDropdown(ddBank, bankName, "visibletext");

        // Enter account and account re-entry
        sendKeysToElement(tfToAccount, toAccount);
        sendKeysToElement(tfToAccountReEnter, toAccount);

        // Enter receiver name
        sendKeysToElement(tfNameOfTheReceiver, receiverName);

        // Select purpose
        selectFromDropdown(ddPurpose, purpose, "value");

        // Enter sender's and beneficiary remarks
        sendKeysToElement(tfEnterSenderRemark, bRemark);
        sendKeysToElement(tfEnterBeneficiaryRemark, bRemark);


        // Select transfer mode based on the input transferMode
        if (transferMode.equals(kwTransfersMap.get("KW_ONE_TIME_TRANSACTION"))) {
            clickOnElement(rdoOTTransaction); // Select 'One Time Transaction'
        } else if (transferMode.equals(kwTransfersMap.get("KW_SETUP_STANDING_ORDER_SCHEDULE"))) {
            clickOnElement(rdoSchadule); // Select 'Schedule Transaction'
        }

        // Validate daily limit by checking available balance from account
        String[] amt = fromAccDropdownValue.get(0).split(currencyType);
        String amountStr = amt[1].trim().replace(",", "");
        double amountDouble = Double.parseDouble(amountStr);
        int wholeAmount = (int) Math.round(amountDouble);
        int maxAmount = wholeAmount + 1;

        // Enter correct values for the transaction
        try {
            if (wholeAmount > 1) {
                sendKeysToElement(tfEnterAmount, Keys.BACK_SPACE, 8);
                sendKeysToElement(tfEnterAmount, actualTransactionAmount);
            } else {
                addToReport("Amount in the account : '" + wholeAmount + "' is NOT sufficient to perform a transfer", Status.FAIL);
                throw new RuntimeException("Not sufficient funds in the account");
            }
        } catch (Exception e) {
            addToReport("Error: Not sufficient funds in the account: " + e.getMessage(), Status.FAIL);
        }

        // Click on the Submit button to initiate the transfer
        clickOnElement(btnSubmit);
        waitForElementToBeInvisible(lblSubmitLoading, LONG_WAIT);

        // Validate the error message for exceeding the max transfer amount
        checkPopupMessage(errorMsg);


        addToReport("----------End of validation of staff user amount transfer----------", Status.PASS, false);
    }


}
