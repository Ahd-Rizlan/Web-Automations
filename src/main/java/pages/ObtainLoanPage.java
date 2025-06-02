package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.Keys;
import utils.constants.ObtainLoanConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.constants.PawnConstants;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ObtainLoanPage extends BasePage {
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



    private static By txtInputFieldByIndex(int index) {
        return By.xpath("(//input[@type='text'])[" + index + "]");
    }

    public By lblObtainLoanFDSection(String sectionText) {
        return By.xpath("//span[text()='" + sectionText + "']");
    }

    /**
     * This method will navigate and validate the Obtain loan from fixed deposit section
     *
     */

    public void ValidateObtainLoaninFixedDepositSection() {

        addToReport("----------Starting the Obtain Loan validation from fixed deposits section ----------", Status.INFO, false);

        waitForElementPresence(imgmyAccount);
        hoverOverElement(driver, imgmyAccount);
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
     * This method will enter values and validate the error messages from the quick action section
     *
     * @param accountNumber1 - Account number one
     * @param minimumAmount - Minimum amount for validation
     * @param maximumAmount - Maximum amount for validation
     * @param actualAmount - Correct amount for proceed
     * @param wrongMonth - Wrong amount of months for validation
     * @param correctMonth - Correct month
     * @param purpose - The purpose for the loan
     * @param accountNumber2 - Account number two
     */

    public void ValidateObtainLoanPageContent(String accountNumber1, String minimumAmount, String maximumAmount, String actualAmount, String wrongMonth, String correctMonth, String purpose,String accountNumber2) {

        if (waitForElementPresence(dddepositAccount, PawnConstants.WAIT_EXTREME_LONG)) {
            addToReport("Deposited account is visible", Status.PASS);
        } else {
            addToReport("Deposited account is not visible.", Status.FAIL);
        }

        clickOnElement(dddepositAccount);
        try {
            waitFor(5);
            selectFromDropdown(dddepositAccount,accountNumber1, PawnConstants.DROPDOWN_SELECT_BY_VALUE);
            addToReport("Successfully selected the first item from the deposit account dropdown.", Status.PASS);
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
        sendKeysToElement(txtenterLoanAmount, Keys.BACK_SPACE, 10); // character-by-character clear
        sendKeysToElement(txtenterLoanAmount, maximumAmount);
        waitForPageLoadCompleteJS();
        waitFor(10);
        addToReport("Used '" + maximumAmount + "' for the new loan", Status.PASS, false);
        waitForElementPresence(txterrorMessageForLoanAmount, PawnConstants.WAIT_MEDIUM);
        waitForElementPresence(lblserviceFee, PawnConstants.WAIT_MEDIUM);
        validateMessage(txterrorMessageForLoanAmount, ObtainLoanConstants.LOAN_ERROR_MSG_02);
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
        addToReport("Selected first account: "+accountNumber2+".", Status.PASS);

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
            addToReport("Capital Portion"+ fullText +"visible",Status.PASS );
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
     *
     */
    public void ValidateObtainLoanConfirmation() {

        addToReport("---------- Start of Validating customer entered values----------", Status.INFO, false);

        String depositAccount = "";
        String expectedLoanAmount = "";
        String repaymentAccount = "";
        String expectedMonth = "";
        String expectedPurpose = "";

        try {
            // Selected Deposit Account
            List<String> expectedDepositeAccount = getSelectedOptionText(dddepositAccount, PawnConstants.FIRST_SELECTED_OPTION); //action
            if (expectedDepositeAccount != null && !expectedDepositeAccount.isEmpty()) {
                depositAccount = Optional.ofNullable(expectedDepositeAccount.get(0)).orElse("").trim();
            }
            addToReport("Selected Deposit Account: " + depositAccount, Status.INFO, false);

            // Loan Amount
            expectedLoanAmount = Optional.ofNullable(getAttributeOrText(txtenterLoanAmount, PawnConstants.DROPDOWN_SELECT_BY_VALUE)).orElse("").trim(); //attribute
            addToReport("Get Loan Amount: " + expectedLoanAmount, Status.INFO, false);

            // Repayment Account
            List<String> expectedRepaymentAccount = getSelectedOptionText(ddrepayment, PawnConstants.FIRST_SELECTED_OPTION);
            if (expectedRepaymentAccount != null && !expectedRepaymentAccount.isEmpty()) {
                repaymentAccount = Optional.ofNullable(expectedRepaymentAccount.get(0)).orElse("").trim();
            }
            addToReport("Selected Repayment Account: " + repaymentAccount, Status.INFO, false);

            // Repayment Period (Months)
            expectedMonth = Optional.ofNullable(getAttributeOrText(txtenterMonth,PawnConstants.DROPDOWN_SELECT_BY_VALUE)).orElse("").trim();
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

        if (waitForElementPresence(btndownloadPdf,PawnConstants.WAIT_EXTRA_LONG)) {
            addToReport("Loan agreement is visible.", Status.PASS);
        } else {
            addToReport("Loan agreement is not visible.", Status.FAIL);
        }

        clickOnElement(btnback);
        addToReport("Navigate back to the loan agreement section", Status.PASS);

        if (waitForElementPresence(rdagreementBtn,PawnConstants.WAIT_MEDIUM)) {
            clickOnElement(rdagreementBtn);
            addToReport("Agreed to the terms and conditions.", Status.PASS);
        } else {
            addToReport("Unable to click on the agreed to the terms and conditions section", Status.FAIL);
        }
    }


    /**
     * This method validate the locator gettext message with the expectedMessage
     *
     * @param locator - gettext location
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
