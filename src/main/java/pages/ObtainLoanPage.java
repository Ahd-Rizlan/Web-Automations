package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import utils.CommonUtils;
import utils.constants.ObtainLoanConstants;
import utils.constants.PawnConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static utils.Drivers.LONG_WAIT;
import static utils.Drivers.MODERATE_WAIT;

public class ObtainLoanPage extends BasePage {


    private String expectedLoanError;
    private List<String> allAccountNumbers = new ArrayList<>();

    public ObtainLoanPage(WebDriver driver) {
        super(driver);
    }


    public enum ElementType {
        button, label, span, div;
    }


    private static final By imgmyAccount = By.xpath("(//a[@class='NavBar_navlink__CRz3E NavBar_navlinkHover__eiXyp'])[1]");
    private static final By lblFixedDeposits = By.xpath("(//a[@class='SubMenu_subMenuItem___oYCo'])[2]");
    private static final By imgObtainLoan = By.xpath("(//img[@class='object-contain p-0.5'])[2]");
    private static final By btncancel = By.xpath("//button[@class='bg-gray-500 p-3 px-7 rounded-lg text-white']");
    private static final By tblinterestAmount = By.xpath("//table[@class='min-w-full border-separate border-spacing-y-2 whitespace-nowrap']");
    private static final By dddepositAccount = By.xpath("//select[@id='depostAccount']");
    private static final By txtenterLoanAmount = By.xpath("(//input[@inputmode='numeric'])[1]");
    private static final By txtenterMonth = By.xpath("(//input[@inputmode='numeric'])[2]");
    private static final By txterrorMessageForLoanAmount = By.xpath("//span[@class='text-red-500 text-xs']");
    private static final By txtpurposeForLoan = By.xpath("//input[@name='purpose']");
    private static final By lblserviceFee = By.xpath("//span[normalize-space(text())='Processing Fee']/following-sibling::span[contains(@class, 'font-bold')]");
    private static final By lblMonthlyCapitalInstallmentFee = By.xpath("//span[normalize-space(text())='Your Approx. Monthly Capital Installment']/following-sibling::span[contains(@class, 'font-bold')]");
    private static final By ddrepayment = By.xpath("//select[@id='accountfrom']");
    private static final By btnsubmit = By.xpath("//button[@type='submit']");
    private static final By btnviewLoanAgreement = By.xpath("//button[@type='button' and @class='p-2 border-orange-500 border rounded-lg text-orange-500 self-start w-[230px] flex items-center justify-center h-[38px]']");
    private static final By btndownloadPdf = By.xpath("//button[@type='button' and @class='bg-green-500 p-3 px-7 rounded-lg text-white hover:bg-green-600 transition-colors']");
    private static final By btnback = By.xpath("//button[@type='button' and @class='bg-gray-500 p-3 px-7 rounded-lg text-white hover:bg-gray-600 transition-colors']");
    private static final By rdagreementBtn = By.xpath("//input[@class='peer appearance-none h-5 w-5 border-2 border-gray-400 bg-gray-200 rounded-md checked:bg-white hover:cursor-pointer']");
    private static final By icnAccounts = By.xpath("//div[contains(@class,'flex flex-col items-center')]/div[3]/div[1]");
    private static final By lblAccountNumber = By.xpath("//div[contains(@class,'full justify-center flex')]//div[contains(@class,'text-base')]/span");
    private static final By btnNextArrow = By.xpath("//div[contains(@class,'flex gap-2')]/div[2]");
    private static final By lblLoadingIcon = By.xpath("//div[contains(@class,'AccountsCards_loader')]");
    private static final By depositsSection = By.xpath("//div[normalize-space(text())='Deposits']");

    private static By txtInputFieldByIndex(int index) {
        return By.xpath("(//input[@type='text'])[" + index + "]");
    }

    public By lblObtainLoanFDSection(String sectionText) {
        return By.xpath("//span[normalize-space(text())='" + sectionText.trim() + "']");
    }

    private static By tfOTP(int Index) {
        return By.xpath("//input[contains(@class,'otp-box')][" + Index + "]");
    }

    public By lblLoanConfirmationSection(String sectionText) {
        return By.xpath("//div[contains(@class, 'flex flex-col') and contains(., 'Loan Granted Successfully')]//div[contains(text(), '" + sectionText + "')]");
    }

    public By lblLoanAccountNumber(String prefix) {
        return By.xpath("//span[contains(text(),'" + prefix + "')]");
    }

    public String getLoanConfirmationFieldValue(String fieldName) {
        String xpath = "//td[normalize-space(text())='" + fieldName + "']/following-sibling::td[1]";
        return getTextFromElement(By.xpath(xpath)).trim();
    }

    private static By getSuccessfulMsg(String title) {
        return By.xpath("//div[contains(text(),'" + title + "')]");
    }

    public By lblLoanConfirmationValue(String fieldName) {
        return By.xpath("//div[text()='" + fieldName + "']/following-sibling::div/span");
    }


    /**
     * This method will navigate and validate the Obtain loan from fixed deposit section
     */

    public void ValidateObtainLoaninFixedDepositSection() {

        addToReport("----------Starting the Obtain Loan validation from fixed deposits section ----------", Status.INFO, false);

        waitForElementPresence(imgmyAccount);
        hoverOverElement(imgmyAccount);
        addToReport("Hover on the My accounts tab ", Status.PASS);

        waitForElementPresence(lblFixedDeposits);
        clickOnElement(lblFixedDeposits);
        addToReport("Clicked on the Fixed deposits tab ", Status.PASS);

        if (waitForElementPresence(tblinterestAmount, PawnConstants.WAIT_EXTRA_LONG)) {
            addToReport("Interest amount table is visible.", Status.PASS, false);
        } else {
            addToReport("Interest amount table is not visible.", Status.FAIL);
        }

        if (isElementPresentBy(imgObtainLoan)) {
            addToReport("obtain Loan button is visible", Status.PASS);
        } else {
            addToReport("obtain Loan button is not visible.", Status.FAIL);
        }

        clickOnElement(imgObtainLoan);
        addToReport("Clicked on the obtain Loan button", Status.PASS, false);


        if (waitForElementPresence(dddepositAccount, PawnConstants.WAIT_EXTRA_LONG)) {
            addToReport("Deposited account is visible", Status.PASS, false);
        } else {
            addToReport("Deposited account is not visible.", Status.FAIL);
        }

        if (getTextFromElement(lblObtainLoanFDSection(ObtainLoanConstants.OBTAIN_LOAN_FIXED_DEPOSIT_SECTION)).equals(ObtainLoanConstants.OBTAIN_LOAN_FIXED_DEPOSIT_SECTION)) {
            addToReport("Validate the Obtain Loan from the Fixed deposits section ", Status.PASS);
        } else {
            addToReport("Obtain Loan from the Fixed deposits section is unsuccessful", Status.FAIL);
        }

        clickOnElement(btncancel);

        addToReport("----------Ending the Obtain Loan validation from fixed deposits section ----------", Status.INFO, false);
        addToReport("----------Starting the Obtain Loan validation from quick action section ----------", Status.INFO, false);
    }


    /**
     * Obtain all available accounts
     */
    public void obtainAllAccountTypes() {
        // waitForElementPresence(depositsSection,SHORT_WAIT);
        waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
        waitFor(5);
        clickOnElement(depositsSection);
        waitFor(2);
        waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
        waitFor(5);

        String[] cardCount = CommonUtils.splitText(getAttributeOrText(icnAccounts, "text"), "/");
        int recordCount = Integer.parseInt(cardCount[1]);

        if (recordCount != 0) {
            for (int inc = 0; inc < recordCount; inc++) {
                String accNum = getTextFromElement(lblAccountNumber).replaceAll("\\s+", "").trim();
                allAccountNumbers.add(accNum);
                addToReport("Successfully added account number: '" + accNum + "'", Status.PASS, true);

                // Navigate to next account only if not on last card
                if (inc < recordCount - 1) {
                    clickOnElement(btnNextArrow);
                    waitForElementToBeInvisible(lblLoadingIcon, MODERATE_WAIT);
                }
            }
        } else {
            addToReport("No accounts found", Status.FAIL);
            throw new RuntimeException("Error - No accounts found");
        }
    }


    /**
     * This method will enter values and validate the error messages from the quick action section
     *
     * @param accountNumber1 - Account number one
     * @param minimumAmount  - Minimum amount for validation
     * @param maximumAmount  - Maximum amount for validation
     * @param actualAmount   - Correct amount for proceed
     * @param wrongMonth     - Wrong amount of months for validation
     * @param correctMonth   - Correct month
     * @param purpose        - The purpose for the loan
     * @param accountNumber2 - Account number two
     */

    public void ValidateObtainLoanPageContent(String accountNumber1, String minimumAmount, String maximumAmount, String actualAmount, String wrongMonth, String correctMonth, String purpose, String accountNumber2) {

        if (waitForElementPresence(dddepositAccount, PawnConstants.WAIT_EXTREME_LONG)) {
            addToReport("Deposited account is visible", Status.PASS);
        } else {
            addToReport("Deposited account is not visible.", Status.FAIL);
        }

        clickOnElement(dddepositAccount);
        // Get all dropdown values
        List<String> dropdownAccounts = getSelectedOptionText(dddepositAccount, "ALL_OPTIONS_VALUE");

        List<String> extractedAccountNumbers = new ArrayList<>();
        for (String fullOptionText : dropdownAccounts) {
            String[] parts = fullOptionText.split(" - ");
            if (parts.length > 0) {
                extractedAccountNumbers.add(parts[0].trim());
            }
        }

        for (String account : allAccountNumbers) {
            if (extractedAccountNumbers.contains(account)) {
                addToReport("Dropdown contains account: " + account, Status.PASS, false);
            } else {
                addToReport("Missing account in dropdown: " + account, Status.FAIL);
            }
        }

        try {
            waitFor(5);
            selectFromDropdown(dddepositAccount, accountNumber1, PawnConstants.DROPDOWN_SELECT_BY_VALUE);
            addToReport("Successfully selected the first item from the deposit account dropdown.", Status.PASS);
            List<String> selectedAccountTextList = getSelectedOptionText(dddepositAccount, PawnConstants.FIRST_SELECTED_OPTION);
// Extract AVL from first item if available
            if (selectedAccountTextList != null && !selectedAccountTextList.isEmpty()) {
                String selectedAccountText = selectedAccountTextList.get(0); // e.g. "217557792199 - AVL. 360,000.00"
                String avlAmount = selectedAccountText.split("AVL\\. ")[1].split(" ")[0].split("\\.")[0].trim(); // → "360,000"
                expectedLoanError = ObtainLoanConstants.LOAN_ERROR_MSG_02 + avlAmount;
            }


        } catch (Exception e) {
            addToReport("Failed to select the first item from the deposit account dropdown. Error: " + e.getMessage(), Status.FAIL);
        }

        addToReport("---------- Start Validating maximum and minimum amounts----------", Status.INFO, false);

        // Step 1: Minimum Loan
        sendKeysToElement(txtenterLoanAmount, minimumAmount);
        addToReport("Used '" + minimumAmount + "' for the new loan", Status.PASS, false);
        waitForElementPresence(txterrorMessageForLoanAmount, PawnConstants.WAIT_MEDIUM);
        validateMessage(txterrorMessageForLoanAmount, ObtainLoanConstants.LOAN_ERROR_MSG_01);

        // Step 2: Correct Month
        sendKeysToElement(txtenterMonth, correctMonth);
        addToReport("Used '" + correctMonth + "' for Monthly Capital Installment validation", Status.PASS, false);
        waitForElementPresence(lblserviceFee, PawnConstants.WAIT_MEDIUM);
        waitFor(10);
        validateMessage(lblserviceFee, ObtainLoanConstants.SERVICE_FEE_O2);

        // Step 3: Maximum Loan
        sendKeysToElement(txtenterLoanAmount, Keys.BACK_SPACE, 10);
        sendKeysToElement(txtenterLoanAmount, maximumAmount);
        waitForPageLoadCompleteJS();
        waitFor(10);
        addToReport("Used '" + maximumAmount + "' for the new loan", Status.PASS, false);
        waitForElementPresence(txterrorMessageForLoanAmount, PawnConstants.WAIT_MEDIUM);
        waitForElementPresence(lblserviceFee, PawnConstants.WAIT_MEDIUM);
        validateMessage(txterrorMessageForLoanAmount, expectedLoanError);
        validateMessage(lblserviceFee, ObtainLoanConstants.SERVICE_FEE_O1);

        addToReport("---------- End Validating maximum and minimum amounts----------", Status.INFO, false);

        // Step 4: Actual Loan
        sendKeysToElement(txtenterLoanAmount, Keys.BACK_SPACE, 10);
        sendKeysToElement(txtenterLoanAmount, actualAmount);
        waitFor(10);
        addToReport("Used '" + actualAmount + "' for the new loan", Status.PASS, false);

        addToReport("---------- Start Validating repayment period exceed message----------", Status.INFO, false);

        // Step 5: Wrong Month
        sendKeysToElement(txtenterMonth, Keys.BACK_SPACE, 5);
        sendKeysToElement(txtenterMonth, wrongMonth);
        waitFor(10);
        addToReport("Used '" + wrongMonth + "' for the new loan", Status.PASS, false);
        waitForElementPresence(txterrorMessageForLoanAmount, PawnConstants.WAIT_MEDIUM);
        validateMessage(txterrorMessageForLoanAmount, ObtainLoanConstants.LOAN_ERROR_MSG_03);

        addToReport("---------- End Validating repayment period exceed message----------", Status.INFO, false);

        // Step 6: Correct Month Again
        sendKeysToElement(txtenterMonth, Keys.BACK_SPACE, 5);
        sendKeysToElement(txtenterMonth, correctMonth);
        waitFor(10);
        addToReport("Used '" + correctMonth + "' for the new loan", Status.PASS, false);

        // Step 7: Purpose
        sendKeysToElement(txtpurposeForLoan, purpose);
        addToReport("Used '" + purpose + "' for the new loan", Status.PASS);

        // Step 8: Repayment Dropdown
        selectFromDropdown(ddrepayment, accountNumber2, PawnConstants.DROPDOWN_SELECT_BY_VALUE);
        addToReport("Selected first account: " + accountNumber2 + ".", Status.PASS);

        addToReport("---------- Starting Validating Capital portion and instalment amount----------", Status.INFO, false);

        // Step 9: Validate Monthly Installment
        try {
            String loanAmountStr = getAttributeOrText(txtenterLoanAmount, "value").replaceAll("[^0-9]", "").trim();
            String monthStr = getAttributeOrText(txtenterMonth, "value").replaceAll("[^0-9]", "").trim();

            int loanAmount = Integer.parseInt(loanAmountStr);
            int months = Integer.parseInt(monthStr);
            int expectedInstallment = loanAmount / months;

            waitForPageLoadCompleteJS();
            String fullText = getTextFromElement(lblMonthlyCapitalInstallmentFee).trim(); // e.g., "LKR 833.33"
            addToReport("Capital Portion" + fullText + "visible", Status.PASS);
            String numberOnly = fullText.replaceAll("[^0-9.]", ""); // "833.33"
            String actualInstallmentStr = numberOnly.split("\\.")[0]; // "833"

            int actualInstallment = Integer.parseInt(actualInstallmentStr);

            //Validate installment
            if (expectedInstallment == actualInstallment) {
                addToReport("Monthly Installment matched: " + actualInstallment, Status.PASS);
            } else {
                addToReport("Installment mismatch. Expected: " + expectedInstallment + ", Actual: " + actualInstallment, Status.FAIL);
            }
        } catch (Exception e) {
            addToReport("Error during loan installment calculation validation: " + e.getMessage(), Status.FAIL);
        }

        addToReport("---------- End Validating Capital portion and instalment amount----------", Status.INFO, false);
    }


    /**
     * This method validates the customer enter details
     */

    // Global variables for loan details
    private String depositAccount, expectedLoanAmount, repaymentAccount, expectedMonth, expectedPurpose, cleanRepaymentAccount, cleanDepositAccount;

    public void ValidateObtainLoanConfirmation() {

        addToReport("---------- Start of Validating customer entered values----------", Status.INFO, false);

        try {
            // Selected Deposit Account
            List<String> expectedDepositeAccount = getSelectedOptionText(dddepositAccount, PawnConstants.FIRST_SELECTED_OPTION); //action
            if (expectedDepositeAccount != null && !expectedDepositeAccount.isEmpty()) {
                depositAccount = Optional.ofNullable(expectedDepositeAccount.get(0)).orElse("").trim();
            }
            cleanDepositAccount = depositAccount.split("-")[0].replaceAll("\\s+", "");
            addToReport("Selected Deposit Account: " + depositAccount, Status.INFO, false);

            // Loan Amount
            expectedLoanAmount = Optional.ofNullable(getAttributeOrText(txtenterLoanAmount, PawnConstants.DROPDOWN_SELECT_BY_VALUE)).orElse("").trim(); //attribute
            addToReport("Get Loan Amount: " + expectedLoanAmount, Status.INFO, false);

            // Repayment Account
            List<String> expectedRepaymentAccount = getSelectedOptionText(ddrepayment, PawnConstants.FIRST_SELECTED_OPTION);
            if (expectedRepaymentAccount != null && !expectedRepaymentAccount.isEmpty()) {
                repaymentAccount = Optional.ofNullable(expectedRepaymentAccount.get(0)).orElse("").trim();
            }
            cleanRepaymentAccount = repaymentAccount.split("-")[0].replaceAll("\\s+", "");
            addToReport("Selected Repayment Account: " + repaymentAccount, Status.INFO, false);

            // Repayment Period (Months)
            expectedMonth = Optional.ofNullable(getAttributeOrText(txtenterMonth, PawnConstants.DROPDOWN_SELECT_BY_VALUE)).orElse("").trim();
            addToReport("Get Repayment Period (Months): " + expectedMonth, Status.INFO, false);

            // Purpose
            expectedPurpose = Optional.ofNullable(getAttributeOrText(txtpurposeForLoan, PawnConstants.DROPDOWN_SELECT_BY_VALUE)).orElse("").trim();
            addToReport("Get Purpose for Loan: " + expectedPurpose, Status.INFO, false);


        } catch (Exception e) {
            addToReport("Error while capturing loan details: " + e.getMessage(), Status.FAIL);
        }

        clickOnElement(btnsubmit);
        waitForPageLoadCompleteJS();
        waitForElementPresence(btnviewLoanAgreement, PawnConstants.WAIT_LONG);
        boolean headingAccountOptionsSection = isElementPresentBy(btnviewLoanAgreement);
        if (headingAccountOptionsSection) {
            addToReport("Successfully open the Loan Confirm section ", Status.PASS);
        } else {
            addToReport("Unable to open the Loan Confirm section", Status.FAIL);
            throw new RuntimeException("Error - Loan Confirmation is not visible.");
        }
        validateMessage(lblObtainLoanFDSection(ObtainLoanConstants.OBTAIN_LOAN_FIXED_DEPOSIT_SECTION), ObtainLoanConstants.OBTAIN_LOAN_FIXED_DEPOSIT_SECTION);


        // Expected values list
        List<String> expectedValues = Arrays.asList(
                depositAccount,
                expectedLoanAmount,
                repaymentAccount,
                expectedMonth,
                expectedPurpose
        );

        // Validate confirmation preview fields
        for (int i = 1; i <= expectedValues.size(); i++) {
            String actualValue = Optional.ofNullable(getAttributeOrText(txtInputFieldByIndex(i), PawnConstants.DROPDOWN_SELECT_BY_VALUE)).orElse("").trim();
            String expectedValue = Optional.ofNullable(expectedValues.get(i - 1)).orElse("").trim();

            if (actualValue.equalsIgnoreCase(expectedValue)) {
                addToReport("Field " + i + " matched: " + actualValue, Status.PASS, false);
            } else {
                addToReport("Field " + i + " mismatch. Expected: " + expectedValue + ", Actual: " + actualValue, Status.FAIL);
            }

            addToReport("---------- End of Validating customer entered values----------", Status.INFO, false);
        }

        clickOnElement(btnviewLoanAgreement);
        addToReport("Clicked on the Loan agreement", Status.PASS, false);
        waitForPageLoadCompleteJS();

        if (waitForElementPresence(btndownloadPdf, PawnConstants.WAIT_EXTRA_LONG)) {
            addToReport("Loan agreement is visible.", Status.PASS);
        } else {
            addToReport("Loan agreement is not visible.", Status.FAIL);
        }

        clickOnElement(btnback);
        addToReport("Navigate back to the loan agreement section", Status.PASS);

        if (waitForElementPresence(rdagreementBtn, PawnConstants.WAIT_MEDIUM)) {
            clickOnElement(rdagreementBtn);
            addToReport("Agreed to the terms and conditions.", Status.PASS);
        } else {
            addToReport("Unable to click on the agreed to the terms and conditions section", Status.FAIL);
        }
    }

    /**
     * This method is entering the OTP to navigates and validates the success message
     *
     * @param otp - OTP
     */
    public void enterOTPAndContinueSettingsPage(String otp, String successMsg) {

        //Enter OTP values and continue
        try {
            sendKeysToElement(tfOTP(1), String.valueOf(otp));

            clickOnElement(btnsubmit);
        } catch (Exception e) {
            addToReport("Error when entering OTP", Status.FAIL);
            throw new RuntimeException("Failed to enter OTP " + e.getMessage(), e);
        }

        waitForElementPresence(getSuccessfulMsg(successMsg), 20); //Request successful
        //Validate the error message
        if (isElementPresentBy(getSuccessfulMsg(successMsg))) {
            addToReport("'" + successMsg + "' message is present.", Status.PASS, true);
        } else {
            addToReport("'" + successMsg + "'  message is not present.", Status.FAIL);
            throw new RuntimeException("Error message validation is unsuccessful.");
        }

        waitForElementToBeInvisible(rdagreementBtn, LONG_WAIT);
    }

    /**
     * THis Method will validate the loan confirmation summary
     */

    public void ValidateObtainLoanConfirmationSummary() {

        String loanAmount = getTextFromElement(lblLoanConfirmationValue("Loan Amount")).split("\\.")[0].trim();
        String interestInfo = getTextFromElement(lblLoanConfirmationSection("p.a. Interest Rate")).trim();
        String accountNumber = getTextFromElement(lblLoanAccountNumber("Loan Account")).trim().split("-")[1].trim();
        String transactionId = getLoanConfirmationFieldValue("Transaction ID");
        String transactionDate = getLoanConfirmationFieldValue("Transaction Date");
        String loanAmount1 = getLoanConfirmationFieldValue("Loan Amount").split("\\.")[0].trim();
        String loanAccountNumber = getLoanConfirmationFieldValue("Loan Account Number");
        String repaymentAccountNumber = getLoanConfirmationFieldValue("Repayment Account Number");
        String paybackPeriod = getLoanConfirmationFieldValue("Payback Period (Months)");
        String repaymentFrequency = getLoanConfirmationFieldValue("Repayment Frequency");
        String interestRate = getLoanConfirmationFieldValue("Loan Interest Rate");
        String capitalInstallment = getLoanConfirmationFieldValue("Capital Installment Amount");
        String installmentDate = getLoanConfirmationFieldValue("Installment Date");
        String fdAccountNumber = getLoanConfirmationFieldValue("Fixed Deposit Account Number");


        // Validate Loan Amount
        if (loanAmount.equalsIgnoreCase(expectedLoanAmount)) {
            addToReport("Loan amount is correct: " + loanAmount, Status.PASS);
        } else {
            addToReport("Loan amount mismatch. Expected: " + expectedLoanAmount + ", Found: " + loanAmount, Status.FAIL);
        }

        // Validate Loan Amount
        if (loanAmount1.equalsIgnoreCase(expectedLoanAmount)) {
            addToReport("Loan amount is correct: " + loanAmount1, Status.PASS);
        } else {
            addToReport("Loan amount mismatch. Expected: " + expectedLoanAmount + ", Found: " + loanAmount1, Status.FAIL);
        }

// Validate Payback Period (Months)
        if (paybackPeriod.equalsIgnoreCase(expectedMonth)) {
            addToReport("Payback period is correct: " + paybackPeriod, Status.PASS);
        } else {
            addToReport("Payback period mismatch. Expected: " + expectedMonth + ", Found: " + paybackPeriod, Status.FAIL);
        }

// Validate Repayment Account Number
        if (repaymentAccountNumber.equalsIgnoreCase(cleanRepaymentAccount)) {
            addToReport("Repayment account number is correct: " + repaymentAccountNumber, Status.PASS);
        } else {
            addToReport("Repayment account number mismatch. Expected: " + cleanRepaymentAccount + ", Found: " + repaymentAccountNumber, Status.FAIL);
        }

// Validate Fixed Deposit Account Number
        if (fdAccountNumber.equalsIgnoreCase(cleanDepositAccount)) {
            addToReport("Fixed Deposit account number is correct: " + fdAccountNumber, Status.PASS);
        } else {
            addToReport("Fixed Deposit account number mismatch. Expected: " + cleanDepositAccount + ", Found: " + fdAccountNumber, Status.FAIL);
        }

        // Check if account number starts with 3
        if (accountNumber.startsWith("3") && CommonUtils.containsNumericCharacters(accountNumber)) {
            addToReport("Loan Account Number is valid and starts with 3: " + accountNumber, Status.PASS);
        } else {
            addToReport("Invalid Loan Account Number. Either does not start with 3 or contains non-numeric characters: " + accountNumber, Status.FAIL);
        }

// Compare both account numbers
        if (accountNumber.equalsIgnoreCase(loanAccountNumber)) {
            addToReport("Loan Account Number is correctly matched: " + accountNumber, Status.PASS);
        } else {
            addToReport("Loan Account Number mismatch. Expected: " + accountNumber + ", Found: " + loanAccountNumber, Status.FAIL);
        }

        // Interest Info should contain '%' and 'p.a.'
        if (CommonUtils.containsAlphNumAndSpecialCharacters(interestInfo)) {
            addToReport("Interest Info is valid: " + interestInfo, Status.PASS);
        } else {
            addToReport("Invalid Interest Info format: " + interestInfo, Status.FAIL);
        }

// Transaction ID should be numeric
        if (CommonUtils.containsNumericCharacters(transactionId)) {
            addToReport("Transaction ID is valid: " + transactionId, Status.PASS);
        } else {
            addToReport("Invalid Transaction ID (non-numeric): " + transactionId, Status.FAIL);
        }

// Transaction Date should match a valid date format (e.g., YYYY-MM-DD)
        if (CommonUtils.containsValuesOnDateYearFirst(transactionDate)) {
            addToReport("Transaction Date is valid: " + transactionDate, Status.PASS);
        } else {
            addToReport("Invalid Transaction Date: " + transactionDate, Status.FAIL);
        }

// Repayment Frequency should be Monthly or a known frequency
        if (repaymentFrequency.equalsIgnoreCase("Monthly") || repaymentFrequency.equalsIgnoreCase("Maturity")) {
            addToReport("Repayment Frequency is valid: " + repaymentFrequency, Status.PASS);
        } else {
            addToReport("Unexpected Repayment Frequency: " + repaymentFrequency, Status.FAIL);
        }

// Interest Rate should contain '%' and be numeric before the %
        if (CommonUtils.containsAlphNumAndSpecialCharacters(interestRate)) {
            addToReport("Interest Rate is valid: " + interestRate, Status.PASS);
        } else {
            addToReport("Invalid Interest Rate: " + interestRate, Status.FAIL);
        }

// Capital Installment should be a valid currency amount
        if (CommonUtils.containsAlphNumAndSpecialCharactersandSpace(capitalInstallment)) {
            addToReport("Capital Installment Amount is valid: " + capitalInstallment, Status.PASS);
        } else {
            addToReport("Invalid Capital Installment Amount format: " + capitalInstallment, Status.FAIL);
        }


    }

    /**
     * This method validate the locator gettext message with the expectedMessage
     *
     * @param locator         - gettext location
     * @param expectedMessage - expected message
     */

    private void validateMessage(By locator, String expectedMessage) {
        if (waitForElementPresence(locator, PawnConstants.WAIT_MEDIUM)) {
            String actualMsg = getTextFromElement(locator);
            if (actualMsg != null && actualMsg.trim().equalsIgnoreCase(expectedMessage)) {
                addToReport("Validation successful: " + actualMsg.trim(), Status.PASS);
            } else {
                addToReport("Message mismatch. Expected: " + expectedMessage +
                        ", Actual: " + (actualMsg != null ? actualMsg.trim() : "null"), Status.FAIL);
            }
        } else {
            addToReport("Message not found for validation. Expected: " + expectedMessage, Status.FAIL);
        }
    }

}
