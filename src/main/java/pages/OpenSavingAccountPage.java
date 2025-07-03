package pages;

import com.aventstack.extentreports.Status;
import utils.constants.SaveAccountConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static utils.Drivers.*;


public class OpenSavingAccountPage extends BasePage {

    public OpenSavingAccountPage (WebDriver driver)  {
        super(driver);
    }

    public enum ElementType {
        button, label, span, div;
    }

    private static final By lblOpenSavingsAccountHeading = By.xpath("//div[normalize-space(text())='" + SaveAccountConstants.SELF_ACCOUNT_OPENING_HEADING + "']");
    private static final By lblOpenSavingsAccounttext = By.xpath("//span[normalize-space(text())='" + SaveAccountConstants.OPEN_SAVINGS_ACCOUNT_TEXT + "']");
    private static final By rdoResident = By.xpath("//input[@id='resident']");
    private static final By btnContinue = By.xpath("//button[text()='" + SaveAccountConstants.CONTINUE_BUTTON_TEXT + "']");
    private static final By ddsourceOfFunds = By.xpath("//select[@id='" + SaveAccountConstants.ATTR_SOURCE_OF_FUNDS + "' and @name='" + SaveAccountConstants.ATTR_SOURCE_OF_FUNDS + "']");
    private static final By ddproductAccount = By.xpath("//select[@id='product' and @name='" + SaveAccountConstants.ATTR_ACCOUNT_PRODUCT + "']");
    private static final By dddebitAccount =  By.xpath("//select[@id='accountfrom']");
    private static final By txtAmount = By.xpath("//input[@name='" + SaveAccountConstants.ATTR_AMOUNT + "']");
    private static final By ddPurpose =  By.xpath("//select[@id='purpose']");
    private static final By txtnickName = By.xpath("//input[@name='" + SaveAccountConstants.ATTR_NICKNAME + "']");
    private static final By btnsubmit= By.xpath("//button[@type='submit']");
    private static final By lblconfirmAccountOptions = By.xpath("//div[@class='flex text-xs font-bold']");
    private static final By ddproductAccountFD =  By.xpath("//select[@id='product' and @name='" + SaveAccountConstants.ATTR_ACCOUNT_PRODUCT + "']");
    private static final By dddebitAccountSaving = By.xpath("//select[@id='accountfrom' and @name='" + SaveAccountConstants.ATTR_ACCOUNT_FROM + "']");
    private static final By lblAccountNumberPreview =  By.xpath("//span[@class='font-bold text-[#F5883C]']");
    private static final By lblSavingAccountNumber =  By.xpath("//p[contains(@class, 'text-gray-600') and contains(@class, 'mt-8')]");
    private static final By btnsubmitConfirmation= By.xpath("//button[@type='submit' and contains(@class, 'bg-green-500') and contains(@class, 'rounded-lg')]");
    private static final By lblAccountListLoading = By.xpath("//div[contains(@class,'dark:bg-gray')]");
    private static final By tfSearch = By.xpath("//input[@placeholder='Search']");
    private static final By btnSearch = By.xpath("//div[contains(@class,'absolute')]/img");
    private static final By tblRows = By.xpath("//table//tbody/tr");

    private static By lblConfirmationFieldDynamic(String label) {
        return By.xpath("//div[normalize-space(text())='" + label + "']/following::div[contains(@class,'font-bold')][1]");
    }

    private static By lblAccountTypeConfirmation() {
        return By.xpath("//div[normalize-space()='Account Type']/following-sibling::div[contains(@class,'font-bold')]");
    }
    private static By tfOTP(int Index) {
        return By.xpath("//input[contains(@class,'otp-box')][" + Index + "]");
    }
    private static By getSuccessfulMsg(String title) {
        return By.xpath("//div[contains(text(),'" + title + "')]");
    }
    private static By getPartialSuccessMsg(String partialMsg) {
        return By.xpath("//*[contains(text(),'" + partialMsg + "')]");
    }
    private static By tblCellRecord(int col, int row) {
        return By.xpath("(//table//tr/td[" + col + "])[" + row + "]");
    }



    /**
     * This method will navigate the user to the saving page
     *
     */
    public void NavigateTotheSavingAccountOpen () {

        addToReport("----------Start of user navigation to the Residency validation Test case----------", Status.INFO, false);
        addToReport("----------Start of user Residency validation Test case----------", Status.INFO, false);

        //validate quick action open SA
        boolean openSA = isElementPresentBy(lblOpenSavingsAccountHeading);
        if (openSA) {
            addToReport("Successfully validated quick action open savings account button ", Status.PASS, false);
        } else {
            addToReport("Quick action  open savings account is not visible", Status.FAIL);

        }
        waitForElementPresence(lblOpenSavingsAccounttext,SHORT_WAIT);
        boolean clickOpenSA = isElementPresentBy(lblOpenSavingsAccounttext);
        if (clickOpenSA) {
            addToReport("Successfully validated the open savings account page description", Status.PASS);
        } else {
            addToReport("Open savings account page description is not visible.", Status.FAIL);
            throw new RuntimeException("Error - Open savings account page description is not visible.");

        } clickOnElement(rdoResident);
        clickOnElement(btnContinue);

        addToReport("----------End of user navigation to the Residency validation Test case----------", Status.INFO, false);
        addToReport("----------End of user Residency validation Test case----------", Status.INFO, false);

        waitForElementPresence(ddsourceOfFunds, SHORT_WAIT);
        boolean headingAccountSection = isElementPresentBy(ddsourceOfFunds);
        if (headingAccountSection) {
            addToReport("Successfully open the savings account page ", Status.PASS);
        } else {
            addToReport("Unable to open the savings account page ", Status.FAIL);
            throw new RuntimeException("Error - Open savings account page is not visible.");
        }

    }

    /**
     * This method will validate the product list
     *
     */

    public void SelfAccountOpening() {
        try {
            addToReport("----------Start of user Product validation Test case----------", Status.INFO, false);

            clickOnElement(ddproductAccount);

            sharedValues = getSelectedOptionText(ddproductAccount, "ALL_OPTIONS");
            List<String> actualDropdownValues = getValues();

            List<String> expectedList = Arrays.asList(SaveAccountConstants.USER_PRODUCTS_SAVING);


            addToReport("Expected Savings Products: " + expectedList, Status.INFO, false);
            addToReport("Actual Savings Products in dropdown: " + actualDropdownValues, Status.INFO, false);

            // Compare the dropdown values
            if (CommonUtils.compareTwoArraylist(expectedList, actualDropdownValues, true)) {
                addToReport("Dropdown matches expected Savings product list.", Status.PASS, true);
            } else {
                addToReport("Dropdown mismatch in Savings products.", Status.FAIL, true);
            }

            // Select the second product from the dropdown
            clickOnElement(ddproductAccount);
            selectFromDropdown(ddproductAccount, SaveAccountConstants.USER_PRODUCTS_SAVING[1], "visibleText");
            addToReport("Selected Savings product from dropdown: " + SaveAccountConstants.USER_PRODUCTS_SAVING[1], Status.PASS, true);

            addToReport("----------End of user Product validation Test case----------", Status.INFO, false);

        } catch (Exception e) {
            addToReport("Exception during Savings product dropdown validation and selection: " + e.getMessage(), Status.FAIL);
            throw new RuntimeException("Savings Product dropdown validation failed", e);
        }
    }



    /**
     * This method will select the account number from the debit drop down
     *
     * @param accountNumber - user account number
     *
     */

    public void selectAccountNumberFromDropdown(String accountNumber) {

        addToReport("----------Start of Entering the details----------", Status.INFO, false);

        try {
            clickOnElement(dddebitAccount);
            selectFromDropdown(dddebitAccount, accountNumber, "value");
            addToReport("Selected account number '" + accountNumber + "' from dropdown.", Status.PASS, true);

        } catch (Exception e) {
            addToReport("Failed to select account number '" + accountNumber + "': " + e.getMessage(), Status.FAIL);
            throw new RuntimeException("Dropdown selection failed", e);
        }

    }

    /**
     * This method will validate user's account number
     *
     */
    public void validatingTheAccountNumber() {

        addToReport("----------Start of validation of Account numbers Test case----------", Status.INFO, false);

        List<String> accountNumbers = getValues();
        clickOnElement(dddebitAccount);

        // Obtain all available values from the dropdown
        List<String> ddAccountNumbers = getSelectedOptionText(dddebitAccount, "ALL_OPTIONS_VALUE");


        List<String> cleanedExpected = new ArrayList<>();
        for (String acc : accountNumbers) {
            if (acc != null) {
                cleanedExpected.add(CommonUtils.removeSpaceCharacters(acc.trim()));
            }
        }

        List<String> cleanedActual = new ArrayList<>();
        for (String acc : ddAccountNumbers) {
            if (acc != null) {
                cleanedActual.add(CommonUtils.removeSpaceCharacters(acc.trim()));
            }
        }


        if (CommonUtils.compareTwoArraylist(cleanedActual, cleanedExpected, true)) {
            addToReport("All eligible accounts for the payments are available.\nExpected: " + cleanedExpected + "\nActual: " + cleanedActual, Status.PASS, false);
        } else {
            addToReport("Eligible accounts mismatch.\nExpected: " + cleanedExpected + "\nActual: " + cleanedActual, Status.FAIL, true);
        }

        addToReport("----------End of validation of Account numbers Test case----------", Status.INFO, false);
    }


    /**
     * This method will enter user's data
     *
     * @param amount - The fixed amount
     * @param nickName - Users nickName
     *
     */

    public void enteringFundDetails(String amount, String nickName) {

        try {

            sendKeysToElement(txtAmount,amount);
            addToReport(" Used '" + amount + "' for the new account", Status.PASS, true);

            clickOnElement(ddPurpose);
            selectFromDropdown(ddPurpose, "P01", "value");
            addToReport("Selected first purpose option: BUSINESS TRANSACTIONS", Status.PASS, true);

            clickOnElement(ddsourceOfFunds);
            selectFromDropdown(ddsourceOfFunds, "SF01", "value");
            addToReport("Selected first source Of Funds option: SALES AND BUSINESS TURNOVER", Status.PASS, true);

            sendKeysToElement(txtnickName,nickName);
            addToReport(" Used '" + nickName + "' for the new account", Status.PASS, true);


            addToReport("----------End of Entering the details----------", Status.INFO, false);


        } catch (Exception e) {
            addToReport("Failed to enter the data " + e.getMessage(), Status.FAIL);
            throw new RuntimeException("Data Entering fail",e);
        }
    }



    /**
     * This method will validate user's data that in the confirmation page
     *
     */

    public void validateSavingConfirmationDetails() {

        // Step 1: Read values before clicking "Next"
        List<String> productList = getSelectedOptionText(ddproductAccountFD, "FIRST_SELECTED");
        String expectedProduct = productList.get(0);
        addToReport("Entered Product: " + expectedProduct, Status.PASS, true);

        List<String> debitedActList = getSelectedOptionText(dddebitAccountSaving, "FIRST_SELECTED");
        String expectedDebittedAccount = debitedActList.get(0);
        if (expectedDebittedAccount != null && !expectedDebittedAccount.isEmpty()) {
            expectedDebittedAccount = expectedDebittedAccount.split("-")[0].trim();
        }
        addToReport("Captured Funding Account Number: " + expectedDebittedAccount, Status.INFO);


        String expectedAmount = getAttributeOrText(txtAmount, "value");
        if (expectedAmount != null) {
            expectedAmount = expectedAmount.replace("LKR", "").trim();
        }
        addToReport("Entered Amount: " + expectedAmount, Status.PASS, true);



        List<String> sourceOfFoundList = getSelectedOptionText(ddsourceOfFunds, "FIRST_SELECTED");
        String expectedSourceOfFunds = sourceOfFoundList.get(0);
        addToReport("Selected Source of Funds: " + expectedSourceOfFunds, Status.PASS, true);


        List<String> purposeList = getSelectedOptionText(ddPurpose, "FIRST_SELECTED");
        String expectedPurpose = purposeList.get(0);
        addToReport("Selected Purpose of Account: " + expectedPurpose, Status.PASS, true);


        String expectedNickname = getAttributeOrText(txtnickName, "text");
        expectedNickname = getAttributeOrText(txtnickName, "value");

        addToReport("Entered Nick Name: " + expectedNickname, Status.PASS, true);

        // Step 2: Click Next
        clickOnElement(btnsubmit);

        waitForElementPresence (lblconfirmAccountOptions, SHORT_WAIT);
        boolean headingAccountOptionsSection = isElementPresentBy(lblconfirmAccountOptions);
        if (headingAccountOptionsSection) {
            addToReport("Successfully open the Confirm Account Options section ", Status.PASS);
        } else {
            addToReport("Unable to open the Confirm Account Options section ", Status.FAIL);
        }


// Validate amount
        String actualInterestCredit = getTextFromElement(lblAccountNumberPreview).trim();
        if (expectedAmount.equals(actualInterestCredit)) {
            addToReport("Interest Credit Account matched: " + actualInterestCredit, Status.PASS, false);
        } else {
            addToReport("Interest Credit Account mismatch. Expected: " + expectedAmount + ", Actual: " + actualInterestCredit, Status.FAIL);
        }


        // === Product ===
        String actualProduct = getAttributeOrText(lblAccountTypeConfirmation(), "text").trim();
        if (expectedProduct.equalsIgnoreCase(actualProduct)) {
            addToReport("Account Type matched: " + actualProduct, Status.PASS, false);
        } else {
            addToReport("Account Type mismatch. Expected: " + expectedProduct + ", Actual: " + actualProduct, Status.FAIL);
        }

        //NickName
        String actualNickName = getTextFromElement(lblConfirmationFieldDynamic("Set Nick Name")).trim();
        if (expectedNickname.equalsIgnoreCase(actualNickName)) {
            addToReport(" Nick Name matched: " + actualNickName, Status.PASS, false);
        } else {
            addToReport(" Nick Name mismatch. Expected: " + expectedNickname + ", Actual: " + actualNickName, Status.FAIL);
        }

//  Validate Debitted (Funding) Account
        String actualDebittedAccount = getTextFromElement(lblConfirmationFieldDynamic("Debit Account")).trim();
        if (expectedDebittedAccount.equals(actualDebittedAccount)) {
            addToReport("Debitted Account matched: " + actualDebittedAccount, Status.PASS, false);
        } else {
            addToReport("Debitted Account mismatch. Expected: " + expectedDebittedAccount + ", Actual: " + actualDebittedAccount, Status.FAIL);
        }

// === Purpose of Account ===
        String actualPurpose = getTextFromElement(lblConfirmationFieldDynamic("Purpose of Account")).trim();
        if (expectedPurpose.equalsIgnoreCase(actualPurpose)) {
            addToReport("Purpose matched: " + actualPurpose, Status.PASS, false);
        } else {
            addToReport("Purpose mismatch. Expected: " + expectedPurpose + ", Actual: " + actualPurpose, Status.FAIL);
        }

// === Source of Funds ===
        String actualSourceOfFunds = getTextFromElement(lblConfirmationFieldDynamic("Source of Funds")).trim();
        if (expectedSourceOfFunds.equalsIgnoreCase(actualSourceOfFunds)) {
            addToReport("Source of Funds matched: " + actualSourceOfFunds, Status.PASS, false);
        } else {
            addToReport("Source of Funds mismatch. Expected: " + expectedSourceOfFunds + ", Actual: " + actualSourceOfFunds, Status.FAIL);
        }

    }

    /**
     * This method is entering the OTP, navigates, and validates the dynamic success message.
     *
     * @param otp        - OTP
     * @param successMsg - Static portion of the success message to validate
     * @param suffixMsg -  Other Static portion of the success message to validate
     */
    public void enterOTPAndContinueSettingsPage(String otp, String successMsg, String suffixMsg) {

        try {
            // Enter OTP and submit
            sendKeysToElement(tfOTP(1), String.valueOf(otp));
            clickOnElement(btnsubmitConfirmation);

            // Wait for message to appear
            waitForElementPresence(getPartialSuccessMsg(successMsg), 20); // E.g., "Your new savings account"

            // Get the full message
            String fullText = getTextFromElement(getPartialSuccessMsg(successMsg)).trim();

            // Log full message
            addToReport("Captured success message: " + fullText, Status.INFO);

            // Extract account number using regex
            Pattern pattern = Pattern.compile(Pattern.quote(successMsg) + "(\\d+)" + Pattern.quote(suffixMsg));
            Matcher matcher = pattern.matcher(fullText);

        } catch (Exception e) {
            addToReport("Error during OTP confirmation or message validation: " + e.getMessage(), Status.FAIL);
            throw new RuntimeException("OTP confirmation failed", e);
        }

        waitForElementToBeInvisible(lblconfirmAccountOptions, LONG_WAIT);
    }

    /**
     *
     * Validate the savings confirmation
     *
     */

    public String validateSaveAccountConfirmation() {
        try {
            String fullText = getTextFromElement(lblSavingAccountNumber);

            if (fullText == null || fullText.trim().isEmpty()) {
                addToReport("Saving Account Number label is empty or not visible", Status.FAIL);
                return null;
            }
            // Extract only numeric account number
            String accountNumber = fullText.replaceAll("\\D+", "").trim();
            addToReport("Captured Account Number: " + accountNumber, Status.INFO);

            if (accountNumber.startsWith(SaveAccountConstants.FD_Number[1])) {
                addToReport("Valid Saving Account Number starts with '1': " + accountNumber, Status.PASS, true);
            } else {
                addToReport("Invalid Saving Account Number: " + accountNumber, Status.FAIL, true);
            }

            addToReport("---------- End of validating the confirmation message on the FD page ----------", Status.INFO, false);
            return accountNumber;
        } catch (Exception e) {
            addToReport("Error while validating Saving account number: " + e.getMessage(), Status.FAIL);
            throw new RuntimeException("Saving Account number validation failed", e);
        }
    }


    /**
     * Search and validate the created account number
     *
     * @param accountNo - Account number
     *
     */
    public void searchAndSelectAccountList(String accountNo) {
        try {
            // Wait and perform search
            waitForElementToBeInvisible(lblAccountListLoading, LONG_WAIT);
            sendKeysToElement(tfSearch, accountNo);
            clickOnElement(btnSearch);
            waitForElementToBeInvisible(lblAccountListLoading, LONG_WAIT);

            // Check how many records are returned
            int recordCount = isElementsPresentBy(tblRows);

            if (recordCount == 1) {
                String resultAccountNo = getTextFromElement(tblCellRecord(1, 1)).trim();

                if (resultAccountNo.equals(accountNo)) {
                    addToReport("Account number " + accountNo + " successfully returned on search.", Status.PASS, true);
                } else {
                    addToReport("Mismatch in returned account. Expected: " + accountNo + ", Found: " + resultAccountNo, Status.FAIL, true);
                    throw new RuntimeException("Error - Returned account number mismatch.");
                }

            } else {
                addToReport("Search did not return exactly one result for account: " + accountNo, Status.FAIL, true);
                throw new RuntimeException("Error - Incorrect number of results returned.");
            }

        } catch (Exception e) {
            addToReport("Failed to search/select account " + accountNo, Status.FAIL);
            throw new RuntimeException("Error - Exception during account search: " + e.getMessage(), e);
        }
    }


}

