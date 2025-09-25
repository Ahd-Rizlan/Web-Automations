package pages;


import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.CommonUtils;
import utils.constants.DashboardConstants;
import utils.constants.MessagingConstants;
import utils.constants.PawnConstants;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static utils.Drivers.LONG_WAIT;
import static utils.Drivers.MODERATE_WAIT;

public class PawningTicketPage extends BasePage {

    public PawningTicketPage(WebDriver driver) {
        super(driver);
    }

    public enum ElementType {
        button, label, span, div;
    }

    private static final By imgmyAccount = By.xpath("(//a[@class='NavBar_navlink__CRz3E NavBar_navlinkHover__eiXyp'])[1]");
    private static final By lblFixedDeposits = By.xpath("//a[contains(@class, 'SubMenu_subMenuItem')]/div[normalize-space(text())='Pawning']");
    private static final By lblPawningCardOutStandingAmount = By.xpath("//div[@class='text-2xl']/span[@class='text-black']");
    private static final By lblSavingsPrimaryStatus = By.xpath("//div[contains(@class,'justify-center items-center text-center')]");
    private static final By lblPawningAccount = By.xpath("//div[contains(@class, 'flex flex-col pt-4 pl-4 gap-1')]//span[contains(@class, 'false')]");
    private static final By lblPawningInterest = By.xpath("//div[contains(@class, 'flex flex-col')]//span[contains(@class, 'text-end')]");
    private static final By tblPawningList = By.xpath("//tr[contains(@class, 'overflow-hidden shadow-md shadow-gray-300 ')]");
    private static final By tblPawningHistoryList = By.xpath("//table[contains(@class, 'border-separate border-spacing')]");
    private static final By btnSubmit = By.xpath("//button[contains(@class, 'items-center justify-center transition-colors')]");
    private static final By btnNext = By.xpath("//button[contains(@class, 'text-white  min-w')]");
    private static final By ddDebitedAccount = By.xpath("//select[@id='accountfrom']");
    private static final By txtPawningAmount = By.xpath("//input[@inputmode='numeric']");
    private static final By txtErrorMessage = By.xpath("//span[@class='text-red-500 text-xs']");
    private static final By lblPawningConfirmation = By.xpath("//span[@class='text-sm font-bold']");
    private static final By lblOutStandingPawningAmount = By.xpath("//div[@class='text-2xl']/span[@class='text-black']");
    private static final By lblLoadingIcon = By.xpath("//div[contains(@class,'AccountsCards_loader')]");
    private static final By lblAccountListLoading = By.xpath("//div[contains(@class,'dark:bg-gray')]");
    private static final By btnAccount = By.xpath("//div[contains(@class,' AccountsCards')]//div[contains(text(),'Loans')]");
    private static final By btnPawning = By.xpath("//div[contains(@class,' AccountsCards')]//div[contains(text(),'Pawning')]");
    private static final By icnAccounts = By.xpath("//div[contains(@class,'flex flex-col items-center')]/div[3]/div[1]");
    private static final By btnNextArrow = By.xpath("//div[contains(@class,'flex gap-2')]/div[2]");
    private static final By txtPawningAmountFrom = By.xpath("(//input[@inputmode='numeric'])[1]");
    private static final By txtPawningAmountTo = By.xpath("(//input[@inputmode='numeric'])[2]");

    public By lblPawningAdvanceAmount(String label) {
        return By.xpath("//div[contains(@class, 'flex') and contains(., '" + label + "')]/span[1]");
    }

    public static By getPawningDetailByLabel(String labelText) {
        return By.xpath("//li[span[1][normalize-space()='" + labelText + "']]/span[2]");
    }

    private static By tblCellRecord(int row, int col) {
        return By.xpath("//table[contains(@class, 'min-w-full')]//tbody/tr[" + row + "]/td[" + col + "]");
    }

    private static By getLoanSummaryValueByLabel(String label) {
        return By.xpath("//div[@class='flex flex-col p-2']/span[contains(text(), '" + label + "')]/span");
    }

    public By getLoanSummaryValueOnRightSideByLabel(String label) {
        return By.xpath("//span[normalize-space(text())='" + label + "']/following-sibling::span[1]");
    }

    private static By tfOTP(int Index) {
        return By.xpath("//input[contains(@class,'otp-box')][" + Index + "]");
    }

    private static By getSuccessfulMsg(String title) {
        return By.xpath("//div[contains(text(),'" + title + "')]");
    }

    private static By getElementByTypeAndText(ElementType type, String text, int index) {
        return By.xpath("(//" + type.name() + "[contains(normalize-space(.), \"" + text + "\")])[" + index + "]");
    }

    /**
     * This method will navigate to the pawning section
     */
    public void NavogatetoPawningPage() {

        addToReport("----------Start Navigating to the Pawning section ----------", Status.INFO, false);

        waitForElementPresence(imgmyAccount);
        waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
        hoverOverElement(imgmyAccount);
        addToReport("Hover on the My accounts tab ", Status.PASS);

        waitForElementPresence(lblFixedDeposits);
        clickOnElement(lblFixedDeposits);
        addToReport("Clicked on the Pawning deposits tab ", Status.PASS);

        waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
        if (waitForElementPresence(lblSavingsPrimaryStatus, PawnConstants.WAIT_EXTRA_LONG)) {
            addToReport("Pawning details are visible.", Status.PASS, false);
        } else {
            addToReport("Pawning details are not visible.", Status.FAIL);
        }

        addToReport("----------End Navigated to the Pawning section ----------", Status.INFO, false);

    }


    /**
     * This method will validate the pawning account and validates the pawning history table
     */

    private String ticketNumber, amount, date, status, statusRightSide, advancedAmountRightSide, capitalOutstandingRightSide, accruedInterestRightSide, itemsDescriptionRightSide, accuntStatus;
    boolean flag = true;

    public void ValidatingPawningAccountSummary() {

        addToReport("----------Starting the validation of Pawning section ----------", Status.INFO, false);

        accuntStatus = getTextFromElement(lblSavingsPrimaryStatus).trim();

        if (accuntStatus.equals(DashboardConstants.STATUS_VALUES[0])) { // "Active"
            addToReport("Account status is " + accuntStatus + " as expected.", Status.PASS, false);
        } else if (accuntStatus.equals(DashboardConstants.STATUS_VALUES[2])) { // "Dormant"
            addToReport("Account status is " + accuntStatus + ".", Status.PASS, false);
            String[] cardCount = CommonUtils.splitText(getAttributeOrText(icnAccounts, "text"), "/");
            int recordCount = Integer.parseInt(cardCount[1]);
            int inc = 0;
            if (recordCount != 0) {
                while (inc < recordCount) {
                    clickOnElement(btnNextArrow);
                    waitForElementToBeInvisible(lblAccountListLoading, LONG_WAIT);
                    waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
                    accuntStatus = getTextFromElement(lblSavingsPrimaryStatus).trim();
                    if (accuntStatus.equals(DashboardConstants.STATUS_VALUES[0])) {
                        clickOnElement(lblOutStandingPawningAmount);
                        addToReport("Found Active account status after " + (inc + 1) + " attempts.", Status.PASS);
                        break;
                    }
                    inc++;
                }
            }
        } else {
            addToReport("Unknown account status: '" + accuntStatus + "'", Status.FAIL);
        }


        ticketNumber = getTextFromElement(getPawningDetailByLabel(PawnConstants.PAWNING_TICKET_NUMBER)).trim();
        amount = getTextFromElement(getPawningDetailByLabel(PawnConstants.ADVANCED_AMOUNT)).trim();
        date = getTextFromElement(getPawningDetailByLabel(PawnConstants.PAWNED_DATE)).trim();
        status = getTextFromElement(getPawningDetailByLabel(PawnConstants.STATUS)).trim();


// Below 1st Validating the summary section

// Validate Pawning Ticket Number
        if (CommonUtils.containsNumericCharacters(ticketNumber)) {
            addToReport("Successfully validated pawning ticket number: '" + ticketNumber + "'", Status.PASS, false);
        } else {
            addToReport("Pawning ticket number is not valid: '" + ticketNumber + "'", Status.FAIL);
            flag = false;
        }

// Validate Advanced Amount

        if (CommonUtils.containsAlphNumAndSpecialCharactersandSpace(amount)) {
            addToReport("Successfully validated advanced amount: '" + amount + "'", Status.PASS, false);
        } else {
            addToReport("Advanced amount is not valid: '" + amount + "'", Status.FAIL);
            flag = false;
        }

// Validate Pawned Date

        if (CommonUtils.containsValuesOnDate(date)) {
            addToReport("Successfully validated pawned date: '" + date + "'", Status.PASS, false);
        } else {
            addToReport("Pawned date is not valid: '" + date + "'", Status.FAIL);
            flag = false;
        }

// Validate Status (Active or Dormant)

        if (status.equals(DashboardConstants.STATUS_VALUES[0])) {
            addToReport("Successfully validated status: '" + status + "'", Status.PASS, false);
        } else {
            addToReport("Status is not valid: '" + status + "'.", Status.FAIL);
            flag = false;
        }

//validate account number and advanced amount data that in the card with the summary section

// Fetch confirmation screen values
        String confirmAdvanceAmount = getTextFromElement(lblPawningAdvanceAmount(PawnConstants.ADVANCED_AMOUNT_LABEL)).trim();
        String confirmTicketNumber = getTextFromElement(lblPawningAccount).trim();

// Clean both sides for comparison
        String cleanAmountFromSummary = amount.replaceAll("[^\\d.,-]", "").trim();
        String cleanAmountFromConfirmation = confirmAdvanceAmount.replaceAll("[^\\d.,-]", "").trim();

        String cleanTicketFromSummary = CommonUtils.removeSpaceCharacters(ticketNumber);
        String cleanTicketFromConfirmation = CommonUtils.removeSpaceCharacters(confirmTicketNumber);

//  Validate Pawning Ticket Number
        if (cleanTicketFromSummary.equalsIgnoreCase(cleanTicketFromConfirmation)) {
            addToReport("Pawning ticket number matches: " + cleanTicketFromConfirmation, Status.PASS, false);
        } else {
            addToReport("Pawning ticket number mismatch. Expected: " + cleanTicketFromSummary + ", Actual: " + cleanTicketFromConfirmation, Status.FAIL);
            flag = false;
        }

//  Validate Advanced Amount
        if (cleanAmountFromSummary.equalsIgnoreCase(cleanAmountFromConfirmation)) {
            addToReport("Advanced amount matches: " + cleanAmountFromConfirmation, Status.PASS, false);
        } else {
            addToReport("Advanced amount mismatch. Expected: " + cleanAmountFromSummary + ", Actual: " + cleanAmountFromConfirmation, Status.FAIL);
            flag = false;
        }

        String interest = getTextFromElement(lblPawningInterest).trim();
        String cleanedInterest = interest.replaceAll("[^\\d.,-]", "").trim();

        if (CommonUtils.containsNumericCharactersWithNegativeValues(cleanedInterest)) {
            addToReport("Successfully validated interest value: '" + interest + "'", Status.PASS, false);
        } else {
            addToReport("Interest value is not valid: '" + interest + "'", Status.FAIL);
            flag = false;
        }

        clickOnElement(tblPawningList);
        addToReport("Successfully clicked on the pawning list'", Status.PASS, false);

        waitForElementPresence(tblPawningHistoryList, PawnConstants.WAIT_EXTRA_LONG);
        addToReport("Pawning history list table is Successfully visible ", Status.PASS);

        statusRightSide = safeText(getLoanSummaryValueOnRightSideByLabel(PawnConstants.STATUS));
        advancedAmountRightSide = safeText(getLoanSummaryValueOnRightSideByLabel(PawnConstants.ADVANCED_AMOUNT));
        capitalOutstandingRightSide = safeText(getLoanSummaryValueOnRightSideByLabel(PawnConstants.CAPITAL_OUTSTANDING_AMOUNT));
        accruedInterestRightSide = safeText(getLoanSummaryValueOnRightSideByLabel(PawnConstants.ACCRUED_INTEREST));
        itemsDescriptionRightSide = safeText(getLoanSummaryValueOnRightSideByLabel(PawnConstants.ITEMS_DESCRIPTION));
        String outStandingAmountInCardView = safeText(lblPawningCardOutStandingAmount);


        addToReport("Status (Right Side): " + statusRightSide, Status.PASS, false);
        addToReport("Advanced Amount (Right Side): " + advancedAmountRightSide, Status.PASS, false);
        addToReport("Capital Outstanding Amount (Right Side): " + capitalOutstandingRightSide, Status.PASS, false);
        addToReport("Accrued Interest (Right Side): " + accruedInterestRightSide, Status.PASS, false);
        addToReport("Items Description (Right Side): " + itemsDescriptionRightSide, Status.INFO, false);


        boolean allValid = true;

// Validate Status
        if (status.equalsIgnoreCase(statusRightSide)) {
            addToReport("Status matches: " + statusRightSide, Status.PASS, false);
        } else {
            addToReport("Status mismatch. Expected: " + status + ", Actual: " + statusRightSide, Status.FAIL);
            allValid = false;
        }

// Validate Advanced Amount
        if (confirmAdvanceAmount.equalsIgnoreCase(advancedAmountRightSide)) {
            addToReport("Advanced Amount matches: " + advancedAmountRightSide, Status.PASS, false);
        } else {
            addToReport("Advanced Amount mismatch. Expected: " + confirmAdvanceAmount + ", Actual: " + advancedAmountRightSide, Status.FAIL);
            allValid = false;
        }

// Validate Capital Outstanding Amount
        if (outStandingAmountInCardView.equalsIgnoreCase(capitalOutstandingRightSide)) {
            addToReport("Capital Outstanding Amount matches: " + capitalOutstandingRightSide, Status.PASS, false);
        } else {
            addToReport("Capital Outstanding Amount mismatch. Expected: " + outStandingAmountInCardView + ", Actual: " + capitalOutstandingRightSide, Status.FAIL);
            allValid = false;
        }

// Validate Accrued Interest
        if (interest.equalsIgnoreCase(accruedInterestRightSide)) {
            addToReport("Accrued Interest matches: " + accruedInterestRightSide, Status.PASS, false);
        } else {
            addToReport("Accrued Interest mismatch. Expected: " + interest + ", Actual: " + accruedInterestRightSide, Status.FAIL);
            allValid = false;
        }

// Final Summary
        if (allValid) {
            addToReport("All 4 values successfully matched with confirmation values.", Status.PASS, true);
        } else {
            addToReport("Some values mismatched in confirmation validation.", Status.FAIL, true);
        }

        addToReport("----------Ending the validation of Pawning section ----------", Status.INFO, false);
        addToReport("----------Starting the validation of Pawning history table section ----------", Status.INFO, false);
//validating the tables

        List<WebElement> rows = driver.findElements(tblPawningList);

        for (int i = 1; i <= rows.size(); i++) {
            String paymentId = getTextFromElement(tblCellRecord(i, 1)).trim();
            String paymentDate = getTextFromElement(tblCellRecord(i, 2)).trim();
            String paymentAmount = getTextFromElement(tblCellRecord(i, 3)).trim();
            String paymentStatus = getTextFromElement(tblCellRecord(i, 4)).trim();

            //  Validate Payment ID is numeric
            if (CommonUtils.containsAlphNumAndSpecialCharacters(paymentId)) {
                addToReport("Row " + i + ": Valid Payment ID: " + paymentId, Status.PASS, false);
            } else {
                addToReport("Row " + i + ": Invalid Payment ID: " + paymentId, Status.FAIL);
                flag = false;
            }

            //  Validate Date format: YYYY-MM-DD
            if (CommonUtils.containsValuesOnDateYearFirst(paymentDate)) {
                addToReport("Row " + i + ": Valid Payment Date: " + paymentDate, Status.PASS, false);
            } else {
                addToReport("Row " + i + ": Invalid Payment Date: " + paymentDate, Status.FAIL);
                flag = false;
            }

            //  Validate Amount is numeric (strip LKR and symbols)
            String cleanAmount = paymentAmount.replaceAll("[^\\d.,-]", "").trim();
            if (CommonUtils.containsNumericCharactersWithNegativeValues(cleanAmount)) {
                addToReport("Row " + i + ": Valid Payment Amount: " + paymentAmount, Status.PASS, false);
            } else {
                addToReport("Row " + i + ": Invalid Payment Amount: " + paymentAmount, Status.FAIL);
                flag = false;
            }

            //  Validate Payment Status (FAILED or SUCCESS)
            if (paymentStatus.equalsIgnoreCase(PawnConstants.STATUS_FAILED) || paymentStatus.equalsIgnoreCase(PawnConstants.STATUS_SUCCESS)) {
                addToReport("Row " + i + ": Valid Payment Status: " + paymentStatus, Status.PASS, false);
            } else {
                addToReport("Row " + i + ": Invalid Payment Status: " + paymentStatus, Status.FAIL);
                flag = false;
            }
        }

        if (flag) {
            addToReport("All table rows validated successfully.", Status.PASS, true);
        } else {
            addToReport("Some rows failed validation.", Status.FAIL, true);
        }

        addToReport("----------Ending the validation of Pawning history table section ----------", Status.INFO, false);

    }


    /**
     * This method will validate the pawning settlement and the validations with different values
     *
     * @param maxiumAmount - Amount Similar as the pawning amount
     * @param expectedMessage - Expected message
     * @param incorrectAmount - Incorrect amount for validation
     * @param lowBalanceAccount - Low balance account for validation
     * @param amountHigherBalance - Higher amount than low balance account
     * @param expectedinsufficientFundMessage - Insufficient balance message
     * @param correctAccount - Correct account
     * @param correctAmount - Correct amount
     */

    private String capitalAmountinConfirmation, interestAmountinConfirmation, capitalAmount, interestAmount, randomAmount;
    double convertedRandomAmount;

    public void ValidatetheSettlement(String maxiumAmount, String expectedMessage, String incorrectAmount, String lowBalanceAccount, String amountHigherBalance, String expectedinsufficientFundMessage, String correctAccount, String correctAmount) {

        addToReport("----------Starting to validate the Pawning settlement section ----------", Status.INFO, false);

        if (isElementPresentBy(btnSubmit)) {
            clickOnElement(btnSubmit);
            addToReport("Clicked on the submit button.", Status.PASS, false);
        } else {
            addToReport("Submit button is not visible or not clickable.", Status.FAIL);
        }


        if (isElementPresentBy(btnNext)) {
            waitForElementPresence(ddDebitedAccount, LONG_WAIT);
            addToReport("Pawning settlement page is visible.", Status.PASS, false);
        } else {
            addToReport("Pawning settlement page is not visible.", Status.FAIL);
        }

        sendKeysToElement(txtPawningAmount, Keys.BACK_SPACE, 14);
        sendKeysToElement(txtPawningAmount, maxiumAmount); // 200000
        waitForElementPresence(txtErrorMessage, PawnConstants.WAIT_SHORT);
        String actualMessage = getTextFromElement(txtErrorMessage).trim();

        // Settlements can only be processed for up to 75% of the advanced amount.
        if (actualMessage.equalsIgnoreCase(expectedMessage)) {
            addToReport("Settlement message is correct: '" + actualMessage + "'", Status.PASS);
        } else {
            addToReport("Settlement message mismatch. Expected: '" + expectedMessage + "', Found: '" + actualMessage + "'", Status.FAIL);
        }
        sendKeysToElement(txtPawningAmount, Keys.BACK_SPACE, 14);
        sendKeysToElement(txtPawningAmount, incorrectAmount); // 175001
        waitForElementPresence(txtErrorMessage, PawnConstants.WAIT_SHORT);
        String actualMessage1 = getTextFromElement(txtErrorMessage).trim();

        // Settlements can only be processed for up to 75% of the advanced amount.
        if (actualMessage1.equalsIgnoreCase(expectedMessage)) {
            addToReport("Settlement message is correct for above 75%: '" + actualMessage1 + "'", Status.PASS);
        } else {
            addToReport("Settlement message mismatch for above 75%. Expected: '" + expectedMessage + "', Found: '" + actualMessage1 + "'", Status.FAIL);
        }

        sendKeysToElement(txtPawningAmount, Keys.BACK_SPACE, 14);
        waitForElementToBeClickable(ddDebitedAccount, MODERATE_WAIT);
        selectFromDropdown(ddDebitedAccount, lowBalanceAccount, PawnConstants.DROPDOWN_SELECT_BY_VALUE);
        String avlAmount = getSelectedOptionText(ddDebitedAccount, "FIRST_SELECTED").get(0).split("AVL\\.")[1].trim().replace(",", "");
        sendKeysToElement(txtPawningAmount, amountHigherBalance); //4000
        clickOnElement(btnNext);
        waitForElementPresence(txtErrorMessage, PawnConstants.WAIT_SHORT); //Insufficient balance. Maximum available: LKR 3261.00
        String insufficientFundMessage1 = getTextFromElement(txtErrorMessage).trim();

        if ((expectedinsufficientFundMessage + " " + avlAmount).equalsIgnoreCase(insufficientFundMessage1)) {
            addToReport("Insufficient Fund Message is correct: '" + insufficientFundMessage1 + "'", Status.PASS);
        } else {
            addToReport("Insufficient Fund Message mismatch. Expected: '" + expectedinsufficientFundMessage + avlAmount + "', Found: '" + insufficientFundMessage1 + "'", Status.FAIL);
        }

        sendKeysToElement(txtPawningAmount, Keys.BACK_SPACE, 14);
        selectFromDropdown(ddDebitedAccount, correctAccount, PawnConstants.DROPDOWN_SELECT_BY_VALUE);
        randomAmount = CommonUtils.generateRandomAmountTwoDecimals(100, 300);
        sendKeysToElement(txtPawningAmount, randomAmount); //correct amount
        convertedRandomAmount = Double.parseDouble(randomAmount);

        capitalAmount = getTextFromElement(getLoanSummaryValueByLabel(PawnConstants.CAPITAL_OUTSTANDING_AMOUNT));
        interestAmount = getTextFromElement(getLoanSummaryValueByLabel(PawnConstants.INTEREST_AMOUNT_PAID));

        clickOnElement(btnNext);


        if (isElementPresentBy(lblPawningConfirmation)) {
            String confirmationMessage = getTextFromElement(lblPawningConfirmation).trim();
            addToReport("Pawning settlement page is visible.: " + confirmationMessage + ".", Status.PASS);
        } else {
            addToReport("Pawning settlement page is not visible.", Status.FAIL);
        }

        capitalAmountinConfirmation = getTextFromElement(getLoanSummaryValueByLabel(PawnConstants.CAPITAL_OUTSTANDING_AMOUNT));
        interestAmountinConfirmation = getTextFromElement(getLoanSummaryValueByLabel(PawnConstants.INTEREST_AMOUNT_PAID));

        if (capitalAmount.equalsIgnoreCase(capitalAmountinConfirmation)) {
            addToReport("Capital Outstanding Amount matches: " + capitalAmountinConfirmation, Status.PASS, false);
        } else {
            addToReport("Capital Outstanding Amount mismatch. Expected: " + capitalAmount + ", Actual: " + capitalAmountinConfirmation, Status.FAIL);

        }

        if (interestAmount.equalsIgnoreCase(interestAmountinConfirmation)) {
            addToReport("Interest Amount Paid matches: " + interestAmountinConfirmation, Status.PASS, false);
        } else {
            addToReport("Interest Amount Paid mismatch. Expected: " + interestAmount + ", Actual: " + interestAmountinConfirmation, Status.FAIL);

        }

        addToReport("----------Ending the validation of the Pawning settlement section ----------", Status.INFO, false);

    }

    /**
     * This method is entering the OTP to navigates to the Settings page
     *
     * @param otp        - OTP
     * @param successMsg - Success message
     */
    public void enterOTPAndContinueSettingsPage(String otp, String successMsg) {

        //Enter OTP values and continue
        try {
            sendKeysToElement(tfOTP(1), String.valueOf(otp));

            clickOnElement(getElementByTypeAndText(ElementType.button, MessagingConstants.CONFIRM, 1));
        } catch (Exception e) {
            addToReport("Error when entering OTP", Status.FAIL);
            throw new RuntimeException("Failed to enter OTP " + e.getMessage(), e);
        }

        waitForElementPresence(getSuccessfulMsg(successMsg), LONG_WAIT); //Request successful
        //Validate the error message
        if (isElementPresentBy(getSuccessfulMsg(successMsg))) {
            addToReport("'" + successMsg + "' message is present.", Status.PASS, true);
        } else {
            addToReport("'" + successMsg + "'  message is not present.", Status.FAIL);
            throw new RuntimeException("Error message validation is unsuccessful.");
        }
    }


    private String confirmationAmountText;
    private Double finalAmount;

    /**
     * This method validate the deducted amount will display as the new outstanding amount
     *
     * @param maxRetriesStr - Maximum tries that iterate
     */
    public void validateOutstandingAmountWithRetry(String maxRetriesStr) {
        addToReport("---------- Starting to validate the Pawning outstanding amount ----------", Status.INFO, false);

        int maxRetries = Integer.parseInt(maxRetriesStr);

        waitForElementToBeInvisible(lblPawningConfirmation, LONG_WAIT);

        double amount = Double.parseDouble(capitalAmountinConfirmation.replace("LKR", "").replace(",", "").trim());
        double interestAmount = Double.parseDouble(interestAmountinConfirmation.replace("LKR", "").replace(",", "").trim());

        //generating random amount
        if (interestAmount != 0.0) {
            finalAmount = (amount + interestAmount) - convertedRandomAmount;
            addToReport("Interest amount is non-zero. Adjusted by adding interest: " + interestAmount, Status.INFO);
        } else {
            finalAmount = amount - convertedRandomAmount;
            addToReport("Interest amount is zero. Only subtracting " + convertedRandomAmount + " from capital.", Status.INFO);
        }
        String expectedFormatted = String.format("LKR %,.2f", finalAmount);

//        confirmationAmountText = getTextFromElement(lblOutStandingPawningAmount).trim();
//
//        if (confirmationAmountText.equalsIgnoreCase(expectedFormatted)) {
//            addToReport("Capital Outstanding Amount is correct on first check: " + confirmationAmountText, Status.PASS, true);
//            return;
//        }

        addToReport("Initial check failed. Starting retry attempts...", Status.INFO, false);

        // Retry loop
        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            waitForElementToBeInvisible(lblAccountListLoading, LONG_WAIT);
            waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);

            clickOnElement(btnAccount);
            waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);

            clickOnElement(btnPawning);
            waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
            if (isElementPresentBy(icnAccounts, LONG_WAIT)) {
                String[] cardCount = CommonUtils.splitText(getAttributeOrText(icnAccounts, "text"), "/");
                int recordCount = Integer.parseInt(cardCount[1]);
                int inc = 0;
                if (recordCount != 0) {
                    while (inc < recordCount) {
                        clickOnElement(btnNextArrow);
                        waitForElementToBeInvisible(lblAccountListLoading, LONG_WAIT);
                        waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
                        accuntStatus = getTextFromElement(lblSavingsPrimaryStatus).trim();
                        if (accuntStatus.equals(DashboardConstants.STATUS_VALUES[0])) {
                            clickOnElement(lblOutStandingPawningAmount);
                            addToReport("Found Active account status after " + (inc + 1) + " attempts.", Status.PASS);
                            break;
                        }
                        inc++;
                    }
                }
            } else {
                confirmationAmountText = getTextFromElement(lblOutStandingPawningAmount).trim();

                if (confirmationAmountText.equalsIgnoreCase(expectedFormatted)) {
                    addToReport("Capital Outstanding Amount is correct on attempt " + attempt + ": " + confirmationAmountText, Status.PASS, true);
                    break;
                } else {
                    addToReport("Attempt " + attempt + ": Amount not updated yet. Found: " + confirmationAmountText, Status.INFO, false);
                }
            }
        }

        addToReport("---------- Ending the validation of the Pawning outstanding amount ----------", Status.INFO, false);
        addToReport("---------- Start the validation of the Pawning amount in History table ----------", Status.INFO, false);

        clickOnElement(tblPawningList);
        addToReport("Successfully clicked on the pawning list'", Status.PASS, false);

        waitForElementPresence(tblPawningHistoryList, PawnConstants.WAIT_EXTRA_LONG);
        addToReport("Pawning history list table is Successfully visible ", Status.PASS);
        scrollDownPage();

        clickOnElement(getElementByTypeAndText(ElementType.button, PawnConstants.FILTER_TRANSACTION, 1));

        sendKeysToElement(txtPawningAmountTo, randomAmount);
        addToReport("Successfully add'" + randomAmount, Status.PASS, false);
        sendKeysToElement(txtPawningAmountFrom, randomAmount);
        addToReport("Successfully add'" + randomAmount, Status.PASS, false);

        clickOnElement(getElementByTypeAndText(ElementType.button, PawnConstants.APPLY_FILTERS, 1));
        waitForElementPresence(tblPawningHistoryList, PawnConstants.WAIT_EXTRA_LONG);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Update format as UI
        Date currentDate = new Date();
        String currentDateFormatted = sdf.format(currentDate);
        List<WebElement> rows = driver.findElements(tblPawningList);

        for (int i = 1; i <= rows.size(); i++) {
            String paymentId = getTextFromElement(tblCellRecord(i, 1)).trim();
            String paymentDate = getTextFromElement(tblCellRecord(i, 2)).trim();
            String paymentAmount = getTextFromElement(tblCellRecord(i, 3)).trim();
            String paymentStatus = getTextFromElement(tblCellRecord(i, 4)).trim();


            //  Validate Payment ID is numeric
            if (CommonUtils.containsAlphNumAndSpecialCharacters(paymentId)) {
                addToReport("Row " + i + ": Valid Payment ID: " + paymentId, Status.PASS, false);
            } else {
                addToReport("Row " + i + ": Invalid Payment ID: " + paymentId, Status.FAIL);
                flag = false;
            }

            //  Validate Date format: YYYY-MM-DD
            if (currentDateFormatted.equals(paymentDate)) {
                addToReport("Row " + i + ": Valid Payment Date: " + paymentDate, Status.PASS, false);
            } else {
                addToReport("Row " + i + ": Invalid Payment Date: " + paymentDate, Status.FAIL);
                flag = false;
            }

            if (paymentAmount.contains(randomAmount)) {
                addToReport("Row " + i + ": Valid Payment Amount: " + paymentAmount, Status.PASS, false);
            } else {
                addToReport("Row " + i + ": Invalid Payment Amount: " + paymentAmount, Status.FAIL);
                flag = false;
            }

            //  Validate Payment Status (FAILED or SUCCESS)
            if (paymentStatus.equalsIgnoreCase(PawnConstants.STATUS_FAILED) || paymentStatus.equalsIgnoreCase(PawnConstants.STATUS_SUCCESS)) {
                addToReport("Row " + i + ": Valid Payment Status: " + paymentStatus, Status.PASS, true);
            } else {
                addToReport("Row " + i + ": Invalid Payment Status: " + paymentStatus, Status.FAIL);
                flag = false;
            }
        }

        addToReport("---------- End the validation of the Pawning amount in History table ----------", Status.INFO, false);
    }

    /**
     * Safely retrieves and trims the text content from the given web element locator.
     * This method avoids NullPointerException by returning an empty string if the element's text is null.
     *
     * @param locator - the locator used to find the web element
     */
    private String safeText(By locator) {
        String text = getTextFromElement(locator);
        return text != null ? text.trim() : "";
    }


}