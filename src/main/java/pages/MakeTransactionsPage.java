/*
 *   @author - salman R
 */
package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import utils.CommonUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static utils.CommonUtils.getCharacterCount;

public class MakeTransactionsPage extends BasePage {

    public MakeTransactionsPage(WebDriver driver) {
        super(driver);
    }

    public enum ElementType {
        button, label, span, div;
    }

    private static final By lblSavingsAccountNo = By.xpath("//span[contains(text(),'Savings Account')]/parent::div/span[2]");
    private static final By lblPageHeader = By.xpath("//div[contains(text(),'Make Transactions')]");
    private static final By ddFromAccount = By.xpath("//select[@id='accountfrom']");
    private static final By ddToAccount = By.xpath("//select[@id='accountto']");
    private static final By ddBank = By.xpath("//select[@name='bank']");
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
    private static final By lblSubmitLoading = By.xpath("//button[@type='submit' and @disabled]");
    private static final By btnConfirm = By.xpath("//button[contains(normalize-space(text()),'Confirm')]");
    private static final By btnNextLoading = By.xpath("//div[contains(@class,'BillPayment_customloader')]");
    private static final By lblSuccess = By.xpath("//span[text()='Success']");
    private static final By lblRefernceID = By.xpath("//span[text()='Success']//following::span[2]");
    private static final By btnPrint = By.xpath("//button[normalize-space()='Print']");
    private static final By lstRecentTransactions = By.xpath("//span[text()='Recent Transactions']/parent::div/following-sibling::div/div");
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

    private static By lblSavedPayeeTemplateName(int row) {
        return By.xpath("(//img[contains(@src,'Bin') and @alt='']/ancestor::tr/td[4])[" + row + "]");
    }

    private static By btnDeleteSavedPayeeTemplate(String templateName) {
        return By.xpath("//td[normalize-space()='"+templateName+"']/parent::tr//img[contains(@src,'Bin') and @alt='']/ancestor::tr/td[8]//button[2]");
    }

    private static By tabHeader(String tabName) {
        return By.xpath("//div[contains(@class,'flex')]/div[text()='" + tabName + "']");
    }
    private static By lblErrorMessage(String errorMsg) {
        return By.xpath("//span[normalize-space()='"+errorMsg+"']");
    }
    private static By getPopUpMsg(String title) {
        return By.xpath("//div[contains(text(),'" + title + "')]");
    }
    private static By tfOtpConfirmation(String type) {
        return By.xpath("//span[contains(normalize-space(),'" + type +"' )]/parent::div/input[@disabled]");
    }
    private static By tfOTP(int Index) {
        return By.xpath("//input[contains(@class,'otp-box')][" + Index + "]");
    }

    /**
     * Select header tab
     *
     * @param headerTab - Main tabs Eg.Send money
     *
     */
    public void selectHeaderTab(String headerTab) {
        try {
            waitForElementPresence(tabHeader(headerTab));
            clickOnElement(tabHeader(headerTab));
            addToReport("Successfully selected the '" + headerTab + "' tab under Send Money", Status.PASS,false);
        } catch (Exception e) {
            addToReport("Failed to select the '" + headerTab + "' tab under Send Money", Status.FAIL);
            throw new RuntimeException("Failed to select tab" + e.getMessage(), e);
        }
    }

    /**
     * Select sub tab under send money
     *
     * @param subHeaderTab - Main tabs Eg.Send money
     *
     */
    public void selectTabUnderSendMoney(String subHeaderTab) {
        try {
            waitForElementPresence(tabHeader(subHeaderTab));
            clickOnElement(tabHeader(subHeaderTab));
            addToReport("Successfully selected the '" + subHeaderTab + "' tab under Send Money", Status.PASS,false);
        } catch (Exception e) {
            addToReport("Failed to select the '" + subHeaderTab + "' tab under Send Money", Status.FAIL);
            throw new RuntimeException("Failed to select sub tab" + e.getMessage(), e);
        }
    }


    /**
     * Validate Performing Own Account Transaction From and To accounts availability
     * @param errorMsg1       The first expected error message to validate (if applicable)
     * @param errorMsg2       The second expected error message to validate (if applicable)
     * @param minAmount       The minimum transaction amount to be entered during validation
     * @param maxAmount       The maximum transaction amount to be entered during validation
     * @param minAmountMsg    The expected popup message when the minimum amount is violated
     * @param maxAmountMsg    The expected popup message when the maximum amount is exceeded
     * @param toAccount       The value to be selected in the 'To Account' dropdown
     * @param amount          The main transaction amount to be used in the transfer flow
     * @param sRemark         Sender’s remark text (max 20 characters)
     * @param bRemark         Beneficiary’s remark text (max 20 characters)
     * @param transferMode    The selected transfer mode (e.g., One-Time Transaction or Scheduled)
     * @param kwTransfersMap    A map containing keyword constants used to resolve transfer mode options
     */
    public void makeOwnAccountTransactions(String errorMsg1, String errorMsg2, String minAmount, String maxAmount, String minAmountMsg, String maxAmountMsg, String toAccount, String amount, String sRemark, String bRemark, String transferMode, Map<String, String> kwTransfersMap,String currencyType,String OTPValue,String noAmount,String errMinimumTransferAmount) {

        addToReport("----------Start of validation of all eligible accounts to perform bill payments are available in the From Account Drop down and default primary account is selected----------", Status.PASS, false);
        //Obtain account numbers from dashboard
        List<String> accountNumbers = getValues();

        //Validate page title
        waitForElementPresence(lblPageHeader,10);
        waitForPageLoadCompleteJS();

        //Validate the account number
        String pAccountNo =getTextFromElement(lblSavingsAccountNo);
        if (pAccountNo.equals(accountNumbers.get(0))) {
            addToReport(" Primary account number : '" + pAccountNo , Status.PASS, false);
        } else {
            addToReport(" Error retrieving primary account number : '" + pAccountNo, Status.FAIL);
        }

        //Temporary wait due to sporadic failure in ALL_OPTIONS_VALUE selection
        waitFor(6);

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
        accountNumbers.remove(accountNumbers.get(0));

        if (!CommonUtils.compareTwoArraylist(toAccDropdownValue, accountNumbers, true)){
            addToReport("All eligible accounts are correctly displayed in the 'To Account' dropdown (excluding the selected 'From Account')", Status.PASS, true);
        } else {
            addToReport("Mismatch in expected 'To Account' dropdown values.Expected: " + accountNumbers + "Actual: "+ toAccDropdownValue, Status.FAIL);
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
        performTransaction(primaryAccount, toAccount, minAmount, sRemark, bRemark, transferMode,kwTransfersMap);


        addToReport("----------Start of validation of whether a Both sender and beneficiary remark fields needs to accept upto 20 number of charcters ----------", Status.PASS, false);
        if (getCharacterCount(getAttributeOrText(tfEnterSenderRemark,"value"))==20){
            addToReport("Sender remark has accepted up to 20 number of characters", Status.PASS, false);
        }else {
            addToReport("Sender remark has not accepted up to 20 number of characters", Status.FAIL);
        }
        if (getCharacterCount(getAttributeOrText(tfEnterBeneficiaryRemark,"value"))==20){
            addToReport("Beneficiary remark has accepted up to 20 number of characters", Status.PASS, false);
        }else {
            addToReport("Beneficiary remark has not accepted up to 20 number of characters", Status.FAIL);
        }
        addToReport("----------End of validation of whether a Both sender and beneficiary remark fields needs to accept up to 20 number of characters ----------", Status.PASS, false);

        // Click on the Submit button to initiate the transfer
        clickOnElement(btnSubmit);

        // Validate if the correct popup message is displayed for minimum amount restriction
        checkPopupMessage(minAmountMsg);

        // Repeat the above steps with the maximum amount for limit validation
        performTransaction(primaryAccount, toAccount, maxAmount, sRemark, bRemark, transferMode,kwTransfersMap);

        addToReport("----------Start of validation of whether a Both sender and beneficiary remark fields needs to accept up to 20 number of characters ----------", Status.PASS, false);
        if (getCharacterCount(getAttributeOrText(tfEnterSenderRemark,"value"))==20){
            addToReport("Sender remark has accepted up to 20 number of characters", Status.PASS, false);
        }else {
            addToReport("Sender remark has not accepted up to 20 number of characters", Status.FAIL);
        }
        if (getCharacterCount(getAttributeOrText(tfEnterBeneficiaryRemark,"value"))==20){
            addToReport("Beneficiary remark has accepted up to 20 number of characters", Status.PASS, false);
        }else {
            addToReport("Beneficiary remark has not accepted up to 20 number of characters", Status.FAIL);
        }
        addToReport("----------End of validation of whether a Both sender and beneficiary remark fields needs to accept up to 20 number of characters ----------", Status.PASS, false);

        // Submit the transfer with the maximum amount
        clickOnElement(btnSubmit);

        // Validate if the correct popup message is displayed for exceeding the maximum limit
        checkPopupMessage(maxAmountMsg);
        addToReport("----------End of validation of whether accessing next page without mandatory fields, transaction limit & remark characters ----------", Status.PASS, false);
        addToReport("----------Start of validation of entering amount ----------", Status.PASS, false);

        //Daily transaction limit was already checked
        //Check for minimum amount of the transaction category "0"
        //validate the default loaded account on both tile and dropdown
        selectTabUnderSendMoney("Other Accounts");
        scrollPageToTop();
        selectTabUnderSendMoney("Own Account");

        waitForPageLoadCompleteJS();
        waitForElementToBeInvisible(lblToAccountGrayLoader,20);

        // Repeat the above steps with the maximum amount for limit validation
        selectFromDropdown(ddFromAccount,primaryAccount,"value");
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
        //sendKeysToElement(tfEnterAmount, amt[1]);

        String amountStr = amt[1].trim(); // This will be something like "8,254.05"

        // Remove comma
        amountStr = amountStr.replace(",", "");
        // Convert to double
        double amountDouble = Double.parseDouble(amountStr);

        // Convert to whole number (round or cast)
        int wholeAmount = (int) Math.round(amountDouble);
        try {
            if (wholeAmount>55){
                sendKeysToElement(tfEnterAmount,Keys.BACK_SPACE,4);
                sendKeysToElement(tfEnterAmount,String.valueOf(amount));
            }else {
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

        waitForElementToBeInvisible(lblSubmitLoading,20);

        addToReport("----------Start of validation of OTP confirmation page----------", Status.PASS, false);
        //Validate the OTP confirmation
        validateOtpPageDetails(primaryAccount,String.valueOf(amount),toAccount,sRemark,bRemark,transferMode, kwTransfersMap,currencyType,"","","","");

        //Enter OTP
        waitForElementPresence(tfOTP(1), 20);
        sendKeysToElement(tfOTP(1), String.valueOf(OTPValue));
        clickOnElement(btnConfirm);
        waitForElementToBeInvisible(btnNextLoading, 20);

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
        validateOtpPageDetails(primaryAccount,String.valueOf(amount),toAccount,sRemark,bRemark,transferMode, kwTransfersMap,currencyType,"","","","");

        //Check for download option
        if (isElementPresentBy(btnPrint)) {
            addToReport("Validated the download option availability in OTP success page", Status.PASS, false);
        } else {
            addToReport("Failed to validate the download option availability in the OTP success page", Status.FAIL);
        }

        addToReport("----------End of validation of OTP success page----------", Status.PASS, true);
        //Close the popup
        waitForElementToBeClickable(btnOTPClosePopup, 10);
        clickOnElement(btnOTPClosePopup);
    }

    /**
     * Validates OTP confirmation fields against expected values
     *
     * @param payFrom                Expected "Transfer From" value
     * @param amount                 Expected amount value
     * @param beneficiaryAccount     Expected beneficiary account number
     * @param senderRemark           Expected sender name
     * @param beneficiaryRemarks     Expected beneficiary remarks
     * @param transferMode           Expected transfer mode
     */
    public void validateOtpPageDetails(String payFrom, String amount, String beneficiaryAccount, String senderRemark, String beneficiaryRemarks, String transferMode,Map<String, String> constantsMap,String currencyType,String bank,String date,String purpose,String beneficiaryCardNo) {

        validateOtpConfirmationField(constantsMap.get("KW_Transfer_FROM"), payFrom.replace(" ",""));
        validateOtpConfirmationField(constantsMap.get("KW_AMOUNT"), currencyType+" "+amount);
        validateOtpConfirmationField(constantsMap.get("KW_BENEFICIARY_ACCOUNT_NUMBER"), beneficiaryAccount.replace(" ",""));
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
     * @param label           The label of the OTP field (e.g., "Amount", "Sender")
     * @param expectedValue   The expected value to be matched
     */
    private void validateOtpConfirmationField(String label, String expectedValue) {
        // Skip validation if expected value is null or empty
        if (expectedValue == null || expectedValue.trim().isEmpty()) {
            addToReport("Skipping validation for '" + label + "' as expected value is empty or null.", Status.INFO);
            return;
        }
        waitForElementPresence(tfOtpConfirmation(label),15);
        String actualValue = getAttributeOrText(tfOtpConfirmation(label), "value").replaceAll("\\s+", "");
        expectedValue = expectedValue.replaceAll("\\s+", "");
        if (expectedValue.equals(actualValue)) {
            addToReport("Validated the value '" + actualValue + "' for '" + label + "' in the OTP page", Status.PASS, false);
        } else {
            addToReport("Failed to validate '" + label + "'. Expected: '" + expectedValue + "', Found: '" + actualValue + "'", Status.FAIL);
        }


    }

    public void performTransaction(String primaryAccount, String toAccount,String amount,String sRemark,String bRemark,String transferMode, Map<String, String> kwBillersMap ) {
        // Repeat the above steps with the maximum amount for limit validation
        selectFromDropdown(ddFromAccount, primaryAccount, "value");

        // Select the same 'To Account' again from the dropdown
        selectFromDropdown(ddToAccount, toAccount, "value");

        //Delete the previous amount
        sendKeysToElement(tfEnterAmount,Keys.BACK_SPACE,6);

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
     * This method checks if a popup message is displayed based on the provided title/error message.
     * It will only check if the message is not empty or null.
     *
     * @param popupMessage The error message to validate in the popup.
     */
    public void checkPopupMessage(String popupMessage) {
        try {
            waitForElementPresence(btnClosePopup);
            waitForElementToBeClickable(btnClosePopup,20);
            waitForPageLoadCompleteJS();
            // Check if the popup message is available
            if (isElementPresentBy(getPopUpMsg(popupMessage))) {
                waitFor(2);
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
    public void makeOtherAccountTransactions( String errorMsgInsufficientFunds, String minAmountEntry, String maxAmountEntry,
                                              String minAmountMsg, String maxAmountMsg, String toAccount, String amount,
                                              String sRemark, String bRemark, String transferMode, Map<String, String> kwTransfersMap,
                                              String currencyType, String OTPValue, String receiverName, String purpose,
                                              String bankName, String actualTransactionAmount) {

        // Report the start of the validation process
        addToReport("----------Start of validation of all eligible accounts to perform bill payments are available in the From Account Drop down and default primary account is selected----------", Status.PASS, false);

        // Obtain account numbers from dashboard
        List<String> accountNumbers = getValues();

        // Scroll to top and wait for page load
        scrollPageToTop();
        waitForPageLoadCompleteJS();
        waitForElementToBeInvisible(lblToAccountGrayLoader, 20);

        // Validate the account number
        String pAccountNo = getTextFromElement(lblSavingsAccountNo);
        if (pAccountNo.equals(accountNumbers.get(0))) {
            addToReport(" Primary account number : '" + pAccountNo, Status.PASS, false);
        } else {
            addToReport(" Error retrieving primary account number : '" + pAccountNo, Status.FAIL);
        }

        // Wait for 6 seconds due to sporadic failures in ALL_OPTIONS_VALUE selection
        waitFor(6);

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

        if (getCharacterCount(getAttributeOrText(tfEnterSenderRemark,"value"))==20){
            addToReport("Sender remark has accepted up to 20 number of characters", Status.PASS, false);
        }else {
            addToReport("Sender remark has not accepted up to 20 number of characters", Status.FAIL);
        }
        if (getCharacterCount(getAttributeOrText(tfEnterBeneficiaryRemark,"value"))==20){
            addToReport("Beneficiary remark has accepted up to 20 number of characters", Status.PASS, false);
        }else {
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
        sendKeysToElement(tfEnterAmount, Keys.BACK_SPACE, 1);
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

        sendKeysToElement(tfEnterAmount, Keys.BACK_SPACE, 6);
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

        // Click on the Submit button to initiate the transfer
        clickOnElement(btnSubmit);

        // OTP confirmation page validation
        addToReport("----------Start of validation of OTP confirmation page----------", Status.PASS, true);
        validateOtpPageDetails(accountNumbers.get(0), actualTransactionAmount, toAccount, sRemark, bRemark, transferMode, kwTransfersMap, currencyType,
                bankName, CommonUtils.getTodayDateFormatted("yyyy-MM-dd"), purpose,"");

        // Enter OTP and submit
        waitForElementPresence(tfOTP(1), 20);
        sendKeysToElement(tfOTP(1), OTPValue);
        clickOnElement(btnConfirm);
        waitForElementToBeInvisible(btnNextLoading, 20);

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
                bankName, CommonUtils.getTodayDateFormatted("yyyy-MM-dd"), purpose,"");

        // Check for download option in the success page
        if (isElementPresentBy(btnPrint)) {
            addToReport("Validated the download option availability in OTP success page", Status.PASS, false);
        } else {
            addToReport("Failed to validate the download option availability in OTP success page", Status.FAIL);
        }

        addToReport("----------End of validation of OTP success page----------", Status.PASS, true);
        //Close the popup
        scrollPageToTop();
        waitForElementToBeClickable(btnOTPClosePopup, 10);
        clickOnElement(btnOTPClosePopup);

        addToReport("----------Start of validation of availability of last 10 records----------", Status.PASS, false);
        //Validate the search results
        int recordCount = isElementsPresentBy(lstRecentTransactions);
        if (recordCount == 10) {
            addToReport("Successfully validated that the last 10 transaction records are available.", Status.PASS, true);
        } else {
            addToReport("Validation failed: Expected 10 transaction records, but found " + recordCount + ".", Status.FAIL, true);
        }
        addToReport("----------End of validation of availability of last 10 records----------", Status.PASS, false);

    }


    public void makeOtherCreditCardTransactions(    Map<String, String> kwTransfersMap,String errCreditCardNumberRequired, String errReEnterCreditCardNumberRequired,String errNameOnCardRequired,String errBankRequired,String errAmountRequired,String errPurposeRequired,String errBeneficiaryRemarkRequired,String creditCardNumber, String reEnterCardNumber, String nameOnCard,String bankName,String branchName, String mdtAmount, String purpose,String sRemark, String bRemark,String transferMode,String errorMessageMaxTransactionLimit,String amount,String templateName,String otpValue,String savedPayees,String minAmount,String errInsufficientfunds,String errMinimumTransferAmount,String currencyValue) {
        // Report the start of the validation process
        addToReport("----------Start of validation of all eligible accounts to perform transfer are available in the From Account Drop down and default primary account is selected----------", Status.PASS, false);

        // Obtain account numbers from dashboard
        List<String> accountNumbers = getValues();

        // Scroll to top and wait for page load
        scrollPageToTop();
        waitForPageLoadCompleteJS();
        waitForElementToBeInvisible(lblToAccountGrayLoader, 20);

        // Validate the account number
        String pAccountNo = getTextFromElement(lblSavingsAccountNo);
        if (pAccountNo.equals(accountNumbers.get(0))) {
            addToReport(" Primary account number : '" + pAccountNo, Status.PASS, false);
        } else {
            addToReport(" Error retrieving primary account number : '" + pAccountNo, Status.FAIL);
        }

        // Wait for 6 seconds due to sporadic failures in ALL_OPTIONS_VALUE selection
        waitFor(6);

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

        sendKeysToElement(tfEnterCreditCardNo,creditCardNumber);
        sendKeysToElement(tfReEnterCreditCardNo,reEnterCardNumber);
        sendKeysToElement(tfEnterCreditCardName,nameOnCard);
        selectFromDropdown(ddBank,bankName, "visibletext");

        if (branchName != null && !branchName.trim().isEmpty()){
            waitForElementToBeInvisible(lblToAccountGrayLoader,15);
            waitForElementToBeClickable(ddBranch,15);
            selectFromDropdown(ddBranch,branchName, "visibletext");
        }
        sendKeysToElement(tfEnterAmount,mdtAmount);
        // Select purpose
        selectFromDropdown(ddPurpose, purpose, "value");

        // Enter the sender's remark (max 20 characters)
        sendKeysToElement(tfEnterSenderRemark, sRemark);

        // Enter the beneficiary's remark (max 20 characters)
        sendKeysToElement(tfEnterBeneficiaryRemark, bRemark);

        if (getCharacterCount(getAttributeOrText(tfEnterSenderRemark,"value"))==20){
            addToReport("Sender remark has accepted upto 20 number of characters", Status.PASS, false);
        }else {
            addToReport("Sender remark has not accepted upto 20 number of characters", Status.FAIL);
        }
        if (getCharacterCount(getAttributeOrText(tfEnterBeneficiaryRemark,"value"))==20){
            addToReport("Beneficiary remark has accepted upto 20 number of characters", Status.PASS, false);
        }else {
            addToReport("Beneficiary remark has not accepted upto 20 number of characters", Status.FAIL);
        }

        // Select the transfer mode based on the input type
        if (transferMode.equals(kwTransfersMap.get("KW_ONE_TIME_TRANSACTION"))) {
            clickOnElement(rdoOTTransaction); // Select 'One Time Transaction'
        } else if (transferMode.equals(kwTransfersMap.get("KW_SETUP_STANDING_ORDER_SCHEDULE"))) {
            clickOnElement(rdoSchadule); // Select 'Schedule Transaction'
        }
        clickOnElement(chkSavePayee);
        waitForElementToBeClickable(tfNickName,10);
        sendKeysToElement(tfNickName,templateName);

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
        sendKeysToElement(tfEnterAmount,Keys.BACK_SPACE,6);
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


        sendKeysToElement(tfEnterAmount,Keys.BACK_SPACE,6);
        // Enter the minimum amount of the transaction catergory as 0
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
            if (wholeAmount>500){
                sendKeysToElement(tfEnterAmount,Keys.BACK_SPACE);
                sendKeysToElement(tfEnterAmount,String.valueOf(amount));
            }else {
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
        validateOtpPageDetails(accountNumbers.get(0),String.valueOf(amount),"",sRemark,bRemark,transferMode, kwTransfersMap,currencyValue,bankName,"","",creditCardNumber);

        String headerContent = getTextFromElement(tfOTPConfirmationHeaderContent);

        if (headerContent.contains(nameOnCard)){
            addToReport("Card name : '" + headerContent + "' is available under header content", Status.FAIL);
        }else {
            addToReport("Card name : '" + wholeAmount + "' is NOT available under header content", Status.FAIL);
        }

        //Enter OTP
        waitForElementPresence(tfOTP(1), 20);
        sendKeysToElement(tfOTP(1), String.valueOf(otpValue));
        clickOnElement(btnConfirm);
        waitForElementToBeInvisible(btnNextLoading, 20);

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
        validateOtpPageDetails(accountNumbers.get(0),amount,"",sRemark,bRemark,transferMode, kwTransfersMap,currencyValue,bankName,"","",creditCardNumber);

        //Check for download option
        if (isElementPresentBy(btnPrint)) {
            addToReport("Validated the download option availability in OTP success page", Status.PASS, false);
        } else {
            addToReport("Failed to validate the download option availability in the OTP success page", Status.FAIL);
        }

        addToReport("----------End of validation of OTP success page----------", Status.PASS, true);

        addToReport("----------Start of validation of search saved template and delete----------", Status.PASS, false);

        //Close the popup
        waitForElementToBeClickable(btnOTPClosePopup, 10);
        clickOnElement(btnOTPClosePopup);
        scrollPageToTop();
        selectTabUnderSendMoney(savedPayees);

        waitForElementToBeClickable(btnSearchPayee, 20);
        scrollPageToTop();
        sendKeysToElement(tfSearch, templateName);
        clickOnElementUsingJS(btnSearchPayee);

        waitForElementToBeInvisible(icnSavedPayeeGridLoading, 20);

        //Validate the search results
        int recordCount = isElementsPresentBy(tblRows);
        if (recordCount != 0) {
            for (int inc = 1; inc <= recordCount; inc++) {
                //Table retrieved value equals template name then delete
                if (getTextFromElement(lblSavedPayeeTemplateName(inc)).equals(templateName)) {
                    addToReport(" Clicked saved template " + templateName, Status.PASS, true);
                    clickOnElementUsingJS(btnDeleteSavedPayeeTemplate(templateName));
                    break;
                }
            }

            //Delete the template
            waitForElementPresence(tfOTP(1), 20);
            sendKeysToElement(tfOTP(1), String.valueOf(otpValue));
            addToReport(" OTP confirmation for template deletion of " + templateName, Status.PASS, true);
            clickOnElement(btnConfirm);
            waitForElementToBeInvisible(btnNextLoading, 20);

            selectTabUnderSendMoney(savedPayees);
            waitForElementToBeClickable(btnSearchPayee, 20);

            sendKeysToElement(tfSearch, templateName);
            clickOnElement(btnSearchPayee);

            waitForElementToBeInvisible(icnSavedPayeeGridLoading, 20);

            //Validate the search results
            recordCount = isElementsPresentBy(tblRows);
            if (recordCount != 0) {
                addToReport(" Template is still available under saved payee search " + templateName, Status.FAIL, true);
            } else {
                addToReport(" Template " + templateName + " has been successfully deleted", Status.PASS, false);
            }
        } else {
            addToReport(" Failed to search saved template " + templateName, Status.FAIL);
        }
        addToReport("----------End of validation of search saved template and delete----------", Status.PASS, false);


    }

}
