package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.CommonUtils;
import utils.constants.DashboardConstants;
import utils.constants.MessagingConstants;
import utils.constants.SaveAccountConstants;


import java.util.*;

public class OpenFDPage extends BasePage {

    public OpenFDPage (WebDriver driver)  {
        super(driver);
    }

    public enum ElementType {
        button, label, span, div;
    }


    private static final By ddsourceOfFunds = By.xpath("//select[@id='branch' and @name='" + SaveAccountConstants.NAME_FUNDING_SOURCES + "']");
    private static final By ddproductAccountFD = By.xpath("//select[@id='branch' and @name='" + SaveAccountConstants.NAME_SCHEME_CODE + "']");
    private static final By ddinterestPayableMode = By.xpath("//select[@id='paybackTime' and @name='" + SaveAccountConstants.NAME_INTEREST_PAYABLE_MODE + "']");
    private static final By txtAmount = By.xpath("//input[@name='" + SaveAccountConstants.ATTR_AMOUNT + "']");
    private static final By ddPurpose = By.xpath("//select[@id='branch' and @name='" + SaveAccountConstants.NAME_PURPOSE_OF_ACCOUNT + "']");

    private static final By txtnickName = By.xpath("//input[@name='" + SaveAccountConstants.ATTR_NICKNAME + "']");
    private static final By btnsubmit= By.xpath("//button[@type='submit']");
    private static final By lblconfirmAccountOptions = By.xpath("//div[text()='" + SaveAccountConstants.TEXT_CONFIRM_ACCOUNT_OPTIONS + "']");
    private static final By btnQActionsOpenNewFD = By.xpath("//h1[text()='Quick Actions']/ancestor::div[contains(@class,'Container_container')]//span[contains(text(),'Open New')]/parent::div/span[contains(text(),'Fixed Deposit')]");
    private static final By lblOpenFDtext = By.xpath("//span[contains(text(),'" + SaveAccountConstants.TEXT_OPEN_FD_DESCRIPTION + "')]");
    private static final By btnFDContinue = By.xpath("//button[text()='" + SaveAccountConstants.TEXT_NEXT_BUTTON + "']");
    private static final By rdoFDResident = By.xpath("//input[@value='" + SaveAccountConstants.VALUE_FD_RESIDENT + "']");

    private static final By ddfdsourceOfFunds = By.xpath("//select[@id='branch' and @name='" + SaveAccountConstants.NAME_FD_SOURCE_OF_FUNDS + "']");
    private static final By dddebitAccountFD = By.xpath("//select[@id='paybackTime' and @name='" + SaveAccountConstants.NAME_DR_ACCOUNT_NUMBER + "']");
    private static final By ddintrestCredtedAccountFD = By.xpath("//select[@id='paybackTime' and @name='" + SaveAccountConstants.NAME_INT_CR_ACCOUNT + "']");

    private static final By lblMonth = By.xpath("//span[contains(@class,'font-semibold text-xs text-center')]");
    private static final By lblmaturityValue = By.xpath("//span[contains(@class,'text-lg font-bold')]");
    private static final By lblmaturityInterestAmount = By.xpath("(//span[text()='Maturity Interest'])[2]/following::span[contains(@class,'text-gray-800')][1]");
    private static final By lbltotalAmount = By.xpath("//span[text()='Total (For selected payable mode)']/following::span[contains(text(),'LKR')]");
    private static final By lblfixDepositeAccountNumber = By.xpath("//span[contains(text(),'Account -')]");
    //img[contains(@alt, 'Preloader')]
    private static final By lblfixDepositeCertificate = By.xpath("//div[contains(normalize-space(text()),'" + SaveAccountConstants.TEXT_FD_CERTIFICATE + "')]");
    private static final By btnConfirm = By.xpath("//button[contains(normalize-space(text()),'" + SaveAccountConstants.TEXT_CONFIRM_BUTTON + "')]");
    private static final By btnClose = By.xpath("//button[contains(normalize-space(text()),'" + SaveAccountConstants.TEXT_CLOSE_BUTTON + "')]");


    private static By tfOTP(int Index) {

        return By.xpath("//input[contains(@class,'otp-box')][" + Index + "]");
    }
    private static By lblMaturity(int index) {
        return By.xpath("(//div[contains(@class, 'w-auto h-28 rounded-lg')])[" + index + "]");
    }
    private static By lblAccountNumberPreview(int index) {
        return By.xpath("(//input[contains(@class, 'appearance-none') and @readonly and @type='text'])[" + index + "]");
    }
    private static By lblConfirmationFieldByLabel(String label) {
        return By.xpath("//div[normalize-space()='" + label + "']/following-sibling::div[@class='font-bold']");
    }



    /**
     * This method is the parent method
     *
     * @param product  - product that related to the user account
     * @param accountNumber  - User account number
     * @param month - FD time period on the cards
     * @param rate - FD rates on the cards
     * @param amount - FD amount
     * @param interest - Users interest for the FD
     * @param totalAmount - Total amount with the interest
     * @param nickName - Account nickname
     */


    public void initiateFDcreationAndValidate (String product,String accountNumber,String month, String rate, String amount, String interest, String totalAmount,String nickName, String otp) {


        NavigateTotheFDOpen ();
        SelfFDAccountOpening (product);
        validateTheIntrestRateAndEnteringData (accountNumber,month, rate, amount, interest,totalAmount,nickName );
        validateFDConfirmationDetails();
        enterOTPAndContinueValidationPage(otp);
        validateFixedDepositConfirmation();

    }

    /**
     * This method will navigate the user to FD page
     *
     */

    public void NavigateTotheFDOpen () {

        //validate quick action open FD
        boolean openSA = isElementPresentBy(btnQActionsOpenNewFD);
        if (openSA) {
            addToReport("Successfully validated quick action open FD account button ", Status.PASS, false);
        } else {
            addToReport("Quick action  open FD account is not visible", Status.FAIL);

        } clickOnElement(btnQActionsOpenNewFD);

        addToReport("----------End of user not be able to access FD page Test case----------", Status.INFO, false);
        addToReport("----------Start of user Residency validation Test case----------", Status.INFO, false);

        waitForElementPresence(lblOpenFDtext, 20);
        boolean clickOpenSA = isElementPresentBy(lblOpenFDtext);
        if (clickOpenSA) {
            addToReport("Successfully validated the open FD account page description", Status.PASS);
        } else {
            addToReport("Open FD account page description is not visible.", Status.FAIL);


        } clickOnElement(rdoFDResident);
        clickOnElement(btnFDContinue);

        waitForElementPresence(ddfdsourceOfFunds, 20);
        boolean headingAccountSection = isElementPresentBy(ddfdsourceOfFunds);
        if (headingAccountSection) {
            addToReport("Successfully open the FD account page ", Status.PASS);
        } else {
            addToReport("Unable to open the FD account page ", Status.FAIL);
            //   throw new RuntimeException("Error - Open FD account page is not visible.");
        }
        addToReport("----------End of user Residency validation Test case----------", Status.INFO, false);
        addToReport("----------Start of user User specific Product list validation Test case ----------", Status.INFO, false);

    }


    /**
     * This method will validate the product list and select the related product
     *
     * @param product  - product that related to the user account
     *
     */
    public void SelfFDAccountOpening(String product) {
        try {

            clickOnElement(ddproductAccountFD);

            sharedValues = getSelectedOptionText(ddproductAccountFD, "ALL_OPTIONS");
            List<String> actualDropdownValues = getValues();


            String[] expectedArray;

            if (product.equalsIgnoreCase(SaveAccountConstants.ALTERNATE_USER_PRODUCT)) {

                expectedArray = SaveAccountConstants.ALTERNATE_USER_PRODUCTS;
            } else {
                expectedArray = SaveAccountConstants.DEFAULT_USER_PRODUCTS;
            }

            List<String> expectedList = Arrays.asList(expectedArray);

            // Compare the dropdown values
            if (CommonUtils.compareTwoArraylist(expectedList, actualDropdownValues, true)) {
                addToReport("Dropdown matches expected product list.\nExpected: " + expectedList + "\nActual: " + actualDropdownValues, Status.PASS, true);
            } else {
                addToReport("Dropdown mismatch.\nExpected: " + expectedList + "\nActual: " + actualDropdownValues, Status.FAIL, true);
            }

            // Select the product from the dropdown
            clickOnElement(ddproductAccountFD);
            selectFromDropdown(ddproductAccountFD, product, MessagingConstants.VISIBLE_TEXT);
            addToReport("Selected product from dropdown: " + product, Status.PASS, true);

            addToReport("----------End of User specific Product list validation Test case ----------", Status.INFO, false);
            addToReport("----------Start of tenors and interest rates are displayed correctly based on the selected product, validation Test case ----------", Status.INFO, false);
            addToReport("----------Start of Credit accounts and data input Test case ----------", Status.INFO, false);

        } catch (Exception e) {
            addToReport("Exception during dropdown validation and selection: " + e.getMessage(), Status.FAIL);
            throw new RuntimeException("Dropdown validation failed", e);
        }
    }

    /**
     * This method will validate user's data
     *
     */

    public void validateFDConfirmationDetails() {
        // Step 1: Read values before clicking "Next"
        List<String> productList = getSelectedOptionText(ddproductAccountFD, "FIRST_SELECTED");
        String expectedProduct = productList.get(0);

        String expectedAmount = getAttributeOrText(txtAmount, "value");
        if (expectedAmount != null) {
            expectedAmount = expectedAmount.replace("LKR", "").trim(); // remove leading/trailing spaces
        }

        String expectedNickname = getAttributeOrText(txtnickName, "value");
        String expectedProductPeriod = getTextFromElement(lblMonth).trim();

        List<String> debitedActList = getSelectedOptionText(dddebitAccountFD, "FIRST_SELECTED");
        String expectedDebittedAccount = debitedActList.get(0);
        if (expectedDebittedAccount != null && !expectedDebittedAccount.isEmpty()) {
            expectedDebittedAccount = expectedDebittedAccount.split("-")[0].trim();
        }
        addToReport("Captured Funding Account Number: " + expectedDebittedAccount, Status.INFO);

        List<String> sourceOfFoundList = getSelectedOptionText(ddsourceOfFunds, "FIRST_SELECTED");
        String expectedSourceOfFunds = sourceOfFoundList.get(0);

        List<String> purposeList = getSelectedOptionText(ddPurpose, "FIRST_SELECTED");
        String expectedPurpose = purposeList.get(0);

        List<String> payableModeList = getSelectedOptionText(ddinterestPayableMode, "FIRST_SELECTED");
        String expectedPayableMode = payableModeList.get(0);

        List<String> interestCreditAccountList = getSelectedOptionText(ddintrestCredtedAccountFD, "FIRST_SELECTED");
        String expectedInterestCredit = interestCreditAccountList.get(0);
        if (expectedInterestCredit != null && !expectedInterestCredit.isEmpty()) {
            expectedInterestCredit = expectedInterestCredit.split("-")[0].trim();
        }
        addToReport("Captured Interest Credited Account: " + expectedInterestCredit, Status.INFO);

        // Step 2: Click Next
        clickOnElement(btnsubmit);
        waitForPageLoadCompleteJS();
        waitForElementToBeInvisible(lblconfirmAccountOptions, 10);
        boolean headingAccountOptionsSection = isElementPresentBy(lblconfirmAccountOptions);
        if (headingAccountOptionsSection) {
            addToReport("Successfully open the Confirm Account Options section ", Status.PASS);
        } else {
            addToReport("Unable to open the Confirm Account Options section ", Status.FAIL);
            throw new RuntimeException("Error - Confirm Account Options section is not visible.");
        }
//  Validate Debitted (Funding) Account
        String actualDebittedAccount = getAttributeOrText(lblAccountNumberPreview(1), "value").trim();
        if (expectedDebittedAccount.equals(actualDebittedAccount)) {
            addToReport("Debitted Account matched: " + actualDebittedAccount, Status.PASS, false);
        } else {
            addToReport("Debitted Account mismatch. Expected: " + expectedDebittedAccount + ", Actual: " + actualDebittedAccount, Status.FAIL);
        }

// Validate Interest Credit Account
        String actualInterestCredit = getAttributeOrText(lblAccountNumberPreview(2), "value").trim();
        if (expectedInterestCredit.equals(actualInterestCredit)) {
            addToReport("Interest Credit Account matched: " + actualInterestCredit, Status.PASS, false);
        } else {
            addToReport("Interest Credit Account mismatch. Expected: " + expectedInterestCredit + ", Actual: " + actualInterestCredit, Status.FAIL);
        }


        // === Product ===
        String actualProduct = getAttributeOrText(lblConfirmationFieldByLabel("Account Type"), "text").trim();
        if (expectedProduct.equalsIgnoreCase(actualProduct)) {
            addToReport("Account Type matched: " + actualProduct, Status.PASS, false);
        } else {
            addToReport("Account Type mismatch. Expected: " + expectedProduct + ", Actual: " + actualProduct, Status.FAIL);
        }

        //Period
        String actualPeriod = getAttributeOrText(lblConfirmationFieldByLabel("Period"), "text").trim();
        if (expectedProductPeriod.equalsIgnoreCase(actualPeriod)) {
            addToReport(" Period matched: " + actualPeriod, Status.PASS, false);
        } else {
            addToReport(" Period mismatch. Expected: " + expectedProduct + ", Actual: " + actualPeriod, Status.FAIL);
        }

// === Amount ===
        String expectedFormattedAmount = "LKR " + expectedAmount;
        String actualAmount = getAttributeOrText(lblConfirmationFieldByLabel("Amount"), "text").trim();
        if (expectedFormattedAmount.equalsIgnoreCase(actualAmount)) {
            addToReport("Amount matched: " + actualAmount, Status.PASS, false);
        } else {
            addToReport("Amount mismatch. Expected: " + expectedFormattedAmount + ", Actual: " + actualAmount, Status.FAIL);
        }

// === Source of Funds ===
        String actualSourceOfFunds = getAttributeOrText(lblConfirmationFieldByLabel("Source of Funds"), "text").trim();
        if (expectedSourceOfFunds.equalsIgnoreCase(actualSourceOfFunds)) {
            addToReport("Source of Funds matched: " + actualSourceOfFunds, Status.PASS, false);
        } else {
            addToReport("Source of Funds mismatch. Expected: " + expectedSourceOfFunds + ", Actual: " + actualSourceOfFunds, Status.FAIL);
        }

// === Purpose of Account ===
        String actualPurpose = getAttributeOrText(lblConfirmationFieldByLabel("Purpose of Account"), "text").trim();
        if (expectedPurpose.equalsIgnoreCase(actualPurpose)) {
            addToReport("Purpose matched: " + actualPurpose, Status.PASS, false);
        } else {
            addToReport("Purpose mismatch. Expected: " + expectedPurpose + ", Actual: " + actualPurpose, Status.FAIL);
        }

// === Interest Payable Mode ===
        String actualPayableMode = getAttributeOrText(lblConfirmationFieldByLabel("Interest Payable Mode"), "text").trim();
        if (expectedPayableMode.equalsIgnoreCase(actualPayableMode)) {
            addToReport("Interest Payable Mode matched: " + actualPayableMode, Status.PASS, true);
        } else {
            addToReport("Interest Payable Mode mismatch. Expected: " + expectedPayableMode + ", Actual: " + actualPayableMode, Status.FAIL);
        }


        addToReport("----------End of validating the confirmation page data Test case ----------", Status.INFO, false);
        addToReport("----------Start of validating the confirmation message displaying FD page Test case ----------", Status.INFO, false);

    }

    /**
     * This method will enter the data that needed to create the FD account then it will validate the  tenors and interest rates
     *
     * @param accountNumber  - User account number
     * @param month - FD time period on the cards
     * @param rate - FD rates on the cards
     * @param amount - FD amount
     * @param interest - Users interest for the FD
     * @param totalAmount - Total amount with the interest
     * @param nickName - Account nickname
     */

    public void validateTheIntrestRateAndEnteringData(String accountNumber, String month, String rate, String amount, String interest, String totalAmount, String nickName) {

        try {

            clickOnElement(dddebitAccountFD);
            selectFromDropdown(dddebitAccountFD,accountNumber,"value");


            clickOnElement(ddintrestCredtedAccountFD);
            selectFromDropdown(ddintrestCredtedAccountFD,accountNumber,"value");


            String selectedFundingAccount = getSelectedOptionText(dddebitAccountFD, "FIRST_SELECTED").get(0).trim();
            String selectedInterestAccount = getSelectedOptionText(ddintrestCredtedAccountFD, "FIRST_SELECTED").get(0).trim();

            if (selectedFundingAccount.contains(accountNumber) && selectedInterestAccount.contains(accountNumber)) {
                addToReport("Successfully selected account number '" + accountNumber + "' in both Funding and Interest Credited dropdowns.", Status.PASS, true);
            } else {
                addToReport("Account number selection mismatch. Funding selected: '" + selectedFundingAccount + "', Interest selected: '" + selectedInterestAccount + "'", Status.FAIL);
            }

        } catch (Exception e) {
            addToReport("Failed to select account number '" + accountNumber + "': " + e.getMessage(), Status.FAIL);
            throw new RuntimeException("Dropdown selection failed", e);
        }

        try {
            boolean maturityIcon = isElementPresentBy(lblMaturity(1));
            if (maturityIcon) {
                addToReport("Successfully visible the maturity Card", Status.PASS);
            } else {
                addToReport("Maturity Card is not visible", Status.FAIL);
            }

            scrollToWebElement(lblMaturity(1));

            boolean isMatched = false;

            if (isElementPresentBy(lblMonth) && isElementPresentBy(lblmaturityValue)) {
                String monthText = getTextFromElement(lblMonth).trim();
                String rateText = getTextFromElement(lblmaturityValue).trim();

                // Clean both UI and Excel inputs
                String cleanMonthText = monthText.replaceAll("[^0-9]", "").trim();
                waitFor(6);
                String cleanExpectedMonth = month.replaceAll("[^0-9]", "").trim();

                String cleanRateText = rateText.replaceAll("[^0-9.]", "").trim();
                String cleanExpectedRate = rate.replaceAll("[^0-9.]", "").trim();

                addToReport("UI Card Found: " + monthText + " | " + rateText, Status.INFO);

                if (cleanMonthText.equals(cleanExpectedMonth) && cleanRateText.equals(cleanExpectedRate)) {
                    clickOnElement(lblMaturity(1));
                    addToReport("Clicked term card for: " + month + " | " + rate, Status.PASS, true);
                    isMatched = true;
                }
            }

            if (!isMatched) {
                addToReport("No matching card found for: " + month + " | " + rate, Status.FAIL, true);
                throw new RuntimeException("Matching term card not found");
            }

        } catch (Exception e) {
            addToReport("Error while selecting term and rate: " + e.getMessage(), Status.FAIL);
            throw new RuntimeException("Error while selecting card", e);
        }

        try {

            clickOnElement(ddinterestPayableMode);
            selectFromDropdown(ddinterestPayableMode, "FDMAT", "value");
            addToReport("Selected first Payable Mode option: Maturity", Status.PASS, true);

            sendKeysToElement(txtAmount,amount);
            addToReport(" Used '" + amount + "' for the new account", Status.PASS, true);

            waitForElementPresence(lblmaturityInterestAmount, 10);
            waitForElementPresence(lbltotalAmount, 10);

            boolean isInterestVisible = isElementPresentBy(lblmaturityInterestAmount);
            boolean isTotalVisible = isElementPresentBy(lbltotalAmount);

            if (!isInterestVisible || !isTotalVisible) {
                addToReport("Interest or Total amount field is not visible", Status.FAIL);
                throw new RuntimeException("Interest/Total field missing");
            }

            String actualInterest = getTextFromElement(lblmaturityInterestAmount).replace("LKR.", "").replace(",", "").trim();
            String actualTotal = getTextFromElement(lbltotalAmount).replace("LKR.", "").replace(",", "").trim();

            interest = interest.replace(",", "").trim();
            totalAmount = totalAmount.replace(",", "").trim();

            boolean matchInterest = actualInterest.equals(interest);
            boolean matchTotal = actualTotal.equals(totalAmount);

            if (matchInterest && matchTotal) {
                addToReport("Maturity Interest and Total amounts are validated successfully. Interest: " + actualInterest + ", Total: " + actualTotal, Status.PASS, true);
            } else {
                addToReport("Amounts mismatch.\nExpected Interest: " + interest + ", Actual: " + actualInterest +
                        "\nExpected Total: " + totalAmount + ", Actual: " + actualTotal, Status.FAIL, true);
                throw new RuntimeException("Interest or Total amount mismatch.");
            }


        } catch (Exception e) {
            addToReport("Exception during amount validation: " + e.getMessage(), Status.FAIL);
            throw new RuntimeException("Error during amount validation", e);
        }

        try {

            clickOnElement(ddPurpose);
            selectFromDropdown(ddPurpose, "P01", "value");
            addToReport("Selected first purpose option: BUSINESS TRANSACTIONS", Status.PASS, true);

            clickOnElement(ddsourceOfFunds);
            selectFromDropdown(ddsourceOfFunds, "SF01", "value");
            addToReport("Selected first source Of Funds option: SALES AND BUSINESS TURNOVER", Status.PASS, true);


            sendKeysToElement(txtnickName,nickName);
            addToReport(" Used '" + nickName + "' for the new account", Status.PASS, true);


            addToReport("----------End of tenors and interest rates are displayed correctly based on the selected product, validation Test case ----------", Status.INFO, false);
            addToReport("----------End  of Credit accounts and data input Test case also  ----------", Status.INFO, false);
            addToReport("----------Start of validating the confirmation page data Test case ----------", Status.INFO, false);


        } catch (Exception e) {
            addToReport("Failed to enter the data " + e.getMessage(), Status.FAIL);
            throw new RuntimeException("Data Entering fail",e);
        }

    }


    /**
     * This method is entering the OTP to navigates to the Settings page
     *
     * @param otp  - OTP
     */
    public void enterOTPAndContinueValidationPage(String otp) {

        //Enter OTP values and continue
        try {
            sendKeysToElement(tfOTP(1), String.valueOf(otp));

            clickOnElement(btnConfirm);
        } catch (Exception e) {
            addToReport("Error when entering OTP", Status.FAIL);
            throw new RuntimeException("Failed to enter OTP " + e.getMessage(), e);
        }
    }


    /**
     * This method is to validate the FD account confirmation messages and account number
     *
     *
     */

    public void validateFixedDepositConfirmation() {
        try {
            // First, safely get the text
            String fullText = getTextFromElement(lblfixDepositeAccountNumber);

            if (fullText == null || fullText.trim().isEmpty()) {
                addToReport("FD Account Number label is empty or not visible", Status.FAIL);
            }

            fullText = fullText.trim();

            addToReport("Full FD Text Captured: " + fullText, Status.INFO);

            // Extract account number
            String accountNumber = fullText.split("Account -")[1].trim();
            String firstDigit = String.valueOf(accountNumber.charAt(0));

            if (firstDigit.equals(SaveAccountConstants.FD_Number[0])) {

                addToReport("Valid Fixed Deposit Account Number start with number 2 : " + accountNumber, Status.PASS, true);
            } else {
                addToReport("Invalid FD Account Number: " + accountNumber, Status.FAIL, true);
            }

            boolean certificateMessage = isElementPresentBy(lblfixDepositeCertificate);
            addToReport("Certificate message visible: " + certificateMessage, Status.PASS, true);


            addToReport("----------End of validating the confirmation message displaying FD page Test case ----------", Status.INFO, false);


           clickOnElement(btnClose);

        } catch (Exception e) {
            addToReport("Error while validating FD account number: " + e.getMessage(), Status.FAIL);
            throw new RuntimeException("FD Account number validation failed", e);
        }
    }


}
