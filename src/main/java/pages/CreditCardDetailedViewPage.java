package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.CommonUtils;
import utils.constants.CreditCardConstants;
import utils.constants.PawnConstants;

import java.util.List;
import java.util.Optional;

import static utils.Drivers.*;

public class CreditCardDetailedViewPage extends BasePage {


    public CreditCardDetailedViewPage(WebDriver driver) {
        super(driver);
    }

    String minimumAmount,lastStandingAmount,customAmount,cardNumber, accountNumber, expiryDate, status, balance, cardType,postDate,transactionDate,originalAmount,greterThanZeroError,merchant,tableAmount,transactionDatePendingSection,originalAmountPendingSection,merchantPendingSection;

    public enum ElementType {
        button, label, span, div;
    }

    private static final By imgmyAccount = By.xpath("(//a[@class='NavBar_navlink__CRz3E NavBar_navlinkHover__eiXyp'])[1]");
    private static final By lblLoans = By.xpath("(//a[@class='SubMenu_subMenuItem___oYCo'])[2]");
    private static final By btnSettlement = By.xpath("(//button[contains(@class, 'font-semibold')])[2]");
    private static final By txtCustomAmount = By.xpath("//input[contains(@inputmode, 'numeric')]");
    private static final By ddFromAccount = By.xpath("//select[contains(@class, 'font-semibold')]");
    private static final By txtMinimumeAmount = By.xpath("(//div[contains(@class, 'rounded-lg') and contains(@class, 'p-4') and contains(@class, 'appearance-none')])[2]");
    private static final By txtLastStandingAmount = By.xpath("(//div[contains(@class, 'rounded-lg') and contains(@class, 'p-4') and contains(@class, 'appearance-none')])[1]");
    private static final By lblPayFrom = By.xpath("(//input[@type='text' and @disabled and contains(@class, 'rounded-lg') and contains(@class, 'p-4')])[1]");
    private static final By lblAmount = By.xpath("(//input[@type='text' and @disabled and contains(@class, 'rounded-lg') and contains(@class, 'p-4')])[2]");
    private static final By btnConfirm = By.xpath("//button[contains(@class, 'font-bold rounded-lg')]");
    private static final By btnBack = By.xpath("(//button[contains(@class, 'font-bold rounded-lg')])[2]");
    private static final By btnConfirmConfirmation = By.xpath("(//button[contains(@class, 'font-bold rounded-lg')])[3]");
    private static final By tblTransaction = By.xpath("//table[contains(@class, 'min-w-full')]//tbody/tr[count(td)=5]");
    private static final By tblPendingSection = By.xpath("//table[contains(@class, 'min-w-full')]//tbody/tr[count(td)=3]");
    private static final By tblInstalmentSection = By.xpath("//table[contains(@class, 'min-w-full')]//tbody/tr[count(td)=8]");
    private static final By tblRecentTransactions = By.xpath("//table[@class='w-full text-left whitespace-nowrap']//tbody/tr[count(td)=4]");
    private static final By lblTransactionDate = By.xpath("(//table[contains(@class, 'min-w-full')]//thead/tr[1]/th)[3]");
    private static final By lblBalance = By.xpath(".//div[contains(@class,'rounded-xl')]//div[@class='text-2xl']//span");
    private static final By lblAccontNumber = By.xpath(".//div[contains(@class,'rounded-xl')]//div[contains(@class,'text-base')]//span");
    private static final By lblStatus = By.xpath(".//div[contains(@class,'rounded-xl')]//div[contains(@class,'w-[60px]')]");
    private static final By lblGraterThanZero = By.xpath("//p[contains(@class, '') and contains(@class, 'text-xs') and contains(@class, 'mt-1')]");
    private static final By btnPending = By.xpath("(//button[contains(@class, 'cursor-pointer') and contains(@class, 'transition-colors')])[1]");
    private static final By btnUnbilled = By.xpath("(//button[contains(@class, 'cursor-pointer') and contains(@class, 'transition-colors')])[3]");



    private static By tblTransactionCell(int row, int col) {
        return By.xpath("//table[contains(@class, 'min-w-full')]//tbody/tr[" + row + "]/td[" + col + "]");
    }

    private static By getCardDetailByLabel(String labelText) {
        return By.xpath("//div[contains(@class,'grid-cols-2')][div[contains(text(),'" + labelText + "')]]/div[2]");
    }

    private static By tblFourColTransactionCell(int row, int col) {
        return By.xpath("//table[contains(@class, 'w-full')]//tbody/tr[" + row + "]/td[" + col + "]");
    }
    private static By getSuccessfulMsg(String title) {
        return By.xpath("//div[contains(text(),'" + title + "')]");
    }
    private static By tfOTP(int Index) {
        return By.xpath("(//input[contains(@class, 'otp-box') and @type='number'])[" + Index + "]");
    }
    public static By getValueBeforeLabel(String labelText) {
        return By.xpath("//span[normalize-space(text())='" + labelText + "']/preceding-sibling::span[1]");
    }


    /**
     *
     * This method will navigate to the credit card details section
     *
     */
    public void NavogatetoCreditCardDetailsPage() {

        addToReport("----------Navigating to the Credit card section ----------", Status.INFO, false);

        waitForElementPresence(imgmyAccount);
        hoverOverElement(driver, imgmyAccount);
        addToReport("Hover on the My accounts tab ", Status.PASS, false);

        waitForElementPresence(lblLoans);
        clickOnElement(lblLoans);
        addToReport("Clicked on the Credit Card tab ", Status.PASS);

        if (waitForElementPresence(lblTransactionDate, PawnConstants.WAIT_EXTREME_LONG)) {
            addToReport(" Credit card details are visible.", Status.PASS);
        } else {
            addToReport(" Credit card details are not visible.", Status.FAIL);
        }

        addToReport("----------Navigated to the  Credit card section ----------", Status.INFO, false);

    }

    /**
     *
     * This method will validate the credit card details
     *
     */

    public void validateCardDetails() {

        cardNumber = getTextFromElement(getCardDetailByLabel(CreditCardConstants.CARD_NUMBER_LABEL)).trim();
        if (CommonUtils.containsMaskedCardFormat(cardNumber)) {
            addToReport("Card Number format is valid: " + cardNumber, Status.PASS, false);
        } else {
            addToReport("Invalid Card Number format: " + cardNumber, Status.FAIL);
        }

        accountNumber = getTextFromElement(getCardDetailByLabel(CreditCardConstants.CUSTOMER_NUMBER_LABEL)).trim();
        if (CommonUtils.containsNumericCharacters(accountNumber)) {
            addToReport("Customer Account Number is valid: " + accountNumber, Status.PASS, false);
        } else {
            addToReport("Invalid Customer Account Number: " + accountNumber, Status.FAIL);
        }

        expiryDate = getTextFromElement(getCardDetailByLabel(CreditCardConstants.EXPIRY_DATE_LABEL)).trim();
        if (expiryDate.matches("\\d{4}/\\d{1,2}")) {
            addToReport("Expiry Date format is valid: " + expiryDate, Status.PASS, false);
        } else {
            addToReport("Invalid Expiry Date format: " + expiryDate, Status.FAIL);
        }

        status = getTextFromElement(getCardDetailByLabel(CreditCardConstants.CARD_STATUS_LABEL)).trim();
        if (status.equalsIgnoreCase("Active")) {
            addToReport("Card Status is " + status + ".", Status.PASS, false);
        } else {
            addToReport("Unexpected Card Status: " + status, Status.FAIL);
        }

        cardType = getTextFromElement(getCardDetailByLabel(CreditCardConstants.CARD_TYPE_LABEL)).trim();
        addToReport("Card Type: " + cardType, Status.PASS, false);

        balance = getTextFromElement(getCardDetailByLabel(CreditCardConstants.AVAILABLE_BALANCE_LABEL)).trim();
        if (CommonUtils.containsAlphAndNumCharacters(balance)) {
            addToReport("Available Balance is valid: " + balance, Status.PASS, false);
        } else {
            addToReport("Available Balance format issue: " + balance, Status.FAIL);
        }


        String CardViewCan = getTextFromElement(getValueBeforeLabel(CreditCardConstants.CAN_LABEL)).trim();
        String CardViewexpiryDate = getTextFromElement(getValueBeforeLabel(CreditCardConstants.EXPIRY_DATE_LABEL)).trim();
        String CardViewBalance = getTextFromElement(lblBalance).trim();
        String CardViewCardNumber = getTextFromElement(lblAccontNumber).trim();
        String CardViewStatus = getTextFromElement(lblStatus).trim();


        if (CardViewCan.equalsIgnoreCase(accountNumber)) {
            addToReport("CAN matches: " + accountNumber, Status.PASS, false);
        } else {
            addToReport("CAN mismatch: Expected " + accountNumber + ", Found " + CardViewCan, Status.FAIL);
        }

        if (CardViewexpiryDate.equalsIgnoreCase(expiryDate)) {
            addToReport("Expiry Date matches: " + expiryDate, Status.PASS, false);
        } else {
            addToReport("Expiry Date mismatch: Expected " + expiryDate + ", Found " + CardViewexpiryDate, Status.FAIL);
        }

        if (CardViewBalance.equalsIgnoreCase(balance)) {
            addToReport("Balance matches: " + balance, Status.PASS, false);
        } else {
            addToReport("Balance mismatch: Expected " + balance + ", Found " + CardViewBalance, Status.FAIL);
        }

        if (CardViewCardNumber.equalsIgnoreCase(cardNumber)) {
            addToReport("Card Number matches: " + cardNumber, Status.PASS, false);
        } else {
            addToReport("Card Number mismatch: Expected " + cardNumber + ", Found " + CardViewCardNumber, Status.FAIL);
        }

        if (CardViewStatus.equalsIgnoreCase(status)) {
            addToReport("Status matches: " + status, Status.PASS);
        } else {
            addToReport("Status mismatch: Expected " + status + ", Found " + CardViewStatus, Status.FAIL);
        }
//////////////////////////////////////////////////////////////////////////////////////////////
            addToReport("----- Validating recent Transaction Table -----", Status.INFO, false);
            waitForElementPresence(tblRecentTransactions,PawnConstants.WAIT_EXTREME_LONG);
            List<WebElement> rows = driver.findElements(tblRecentTransactions);
            boolean flag = true;

            for (int i = 1; i <= rows.size(); i++) {
                String recentTransactionDate = getTextFromElement(tblFourColTransactionCell(i, 1)).trim();
                String recentTransactionAmount = getTextFromElement(tblFourColTransactionCell(i, 2)).trim();
                String recentTransactionMerchant = getTextFromElement(tblFourColTransactionCell(i, 3)).trim();
                String recentTransactionStatus = getTextFromElement(tblFourColTransactionCell(i, 4)).trim();

                // === Date ===
                if (CommonUtils.containsValuesOnDateYearFirst(recentTransactionDate)) {
                    addToReport("Row " + i + ": Valid Date - " + recentTransactionDate, Status.PASS, false);
                } else {
                    addToReport("Row " + i + ": Invalid Date - " + recentTransactionDate, Status.FAIL);
                    flag = false;
                }

                // === Amount ===
                if (recentTransactionAmount.contains("LKR") && CommonUtils.containsAlphAndNumCharacters(recentTransactionAmount)) {
                    addToReport("Row " + i + ": Valid Amount - " + recentTransactionAmount, Status.PASS, false);
                } else {
                    addToReport("Row " + i + ": Invalid Amount - " + recentTransactionAmount, Status.FAIL);
                    flag = false;
                }

                // === Merchant ===
                if (!recentTransactionMerchant.isEmpty()) {
                    addToReport("Row " + i + ": Valid Merchant - " + recentTransactionMerchant, Status.PASS, false);
                } else {
                    addToReport("Row " + i + ": Merchant is empty", Status.FAIL);
                    flag = false;
                }

                // === Status ===
                if (recentTransactionStatus.equalsIgnoreCase(CreditCardConstants.STATUS_APPROVED) || recentTransactionStatus.equalsIgnoreCase(CreditCardConstants.STATUS_PENDING) || recentTransactionStatus.equalsIgnoreCase(CreditCardConstants.STATUS_DECLINED)) {
                    addToReport("Row " + i + ": Valid Status - " + recentTransactionStatus, Status.PASS, false);
                } else {
                    addToReport("Row " + i + ": Unexpected Status - " + recentTransactionStatus, Status.FAIL);
                    flag = false;
                }
            }

            if (flag) {
                addToReport("All rows in recent Transaction Table validated successfully.", Status.PASS);
            } else {
                addToReport("Some rows in recent Transaction Table failed validation.", Status.FAIL);
            }

            addToReport("----- Recent Transaction Table Validation Done -----", Status.INFO, false);
        }


    /**
     *
     * This method will validate the credit card settlement
     *
     * @param payingAccountNumber - Account number
     * @param paymentAmount- Amount
     */

    public void validateCreditCardSettlement(String payingAccountNumber,String paymentAmount, String errorMsg) {

        if (isElementPresentBy(btnSettlement)) {
            addToReport("Settlement button available", Status.PASS, false);
            clickOnElement(btnSettlement);
        } else {
            addToReport("Settlement button not available", Status.FAIL, false);
        }

        waitForElementPresence(txtCustomAmount, PawnConstants.WAIT_EXTRA_LONG);
        addToReport("Custom Amount section is visible ", Status.PASS, false);

        if (isElementPresentBy(ddFromAccount)) {
            addToReport("From Account DropDown  available", Status.PASS, false);
            selectFromDropdown(ddFromAccount, payingAccountNumber, "value");
            addToReport("'" + payingAccountNumber + "'selected from the dropdown.", Status.PASS);
        } else {
            addToReport("DropDown is not available", Status.FAIL, false);
        }

        addToReport("---------- Start of Minimum Amount Population in custom amount section----------", Status.INFO, false);

        if (isElementPresentBy(txtMinimumeAmount)) {
            addToReport("Minimum Amount section is available", Status.PASS, false);
            clickOnElement(txtMinimumeAmount);
            addToReport("Clicked on the Minimum Amount  section", Status.PASS, false);
            minimumAmount = getTextFromElement(txtMinimumeAmount).trim();
            addToReport("'" + minimumAmount + "'as the minimum amount.", Status.PASS, false);
            if (CommonUtils.containsAlphAndNumCharacters(minimumAmount)) {
                addToReport("Valid Amount with LKR - " + minimumAmount, Status.PASS, false);
            } else {
                addToReport("Validation fail Amount - " + minimumAmount, Status.FAIL);
            }

        } else {
            addToReport("Minimum amount is not available", Status.FAIL, false);
        }

        if (isElementPresentBy(txtCustomAmount)) {
            addToReport("Custom Amount section is available", Status.PASS, false);
            customAmount = getAttributeOrText(txtCustomAmount, "value").trim();
            addToReport("'" + customAmount + "'as the Custom amount.", Status.PASS, false);
            if (customAmount.equalsIgnoreCase(minimumAmount)) {
                addToReport("Custom amount'" + customAmount + " Minimum Amount" + minimumAmount + "'", Status.PASS, true);
            } else {
                addToReport("Custom amount'" + customAmount + " Minimum Amount" + minimumAmount + "'", Status.FAIL, false);
            }

        }else {
                addToReport("Validation fail custom and Minimum Amount", Status.FAIL, false);
            }

        addToReport("---------- End of Minimum Amount Population in custom amount section----------", Status.INFO, false);

            if (isElementPresentBy(txtLastStandingAmount)) {
                addToReport("Last Standing Amount section is available", Status.PASS, false);
                clickOnElement(txtLastStandingAmount);
                addToReport("Clicked on the Last Standing amount section", Status.PASS, false);
                lastStandingAmount = getTextFromElement(txtLastStandingAmount).trim();
                addToReport("'" + lastStandingAmount + "'as the Last Standing amount.", Status.PASS, false);
                if (CommonUtils.containsAlphAndNumCharacters(lastStandingAmount)) {
                    addToReport("Valid Amount with LKR - " + lastStandingAmount, Status.PASS, true);
                } else {
                    addToReport("Validation fail Amount - " + lastStandingAmount, Status.FAIL);
                }

            } else {
                addToReport("Last Standing amount is not available", Status.FAIL, false);
            }

            if (isElementPresentBy(txtCustomAmount)) {
                addToReport("Custom Amount section is available", Status.PASS, false);
                customAmount = getAttributeOrText(txtCustomAmount, "value").trim();
                addToReport("'" + customAmount + "'as the Custom amount.", Status.PASS, false);
                if (customAmount.equalsIgnoreCase(lastStandingAmount)) {
                    addToReport("Custom amount'" + customAmount + " Last out standing Amount" + lastStandingAmount + "'", Status.PASS, true);
                } else {
                    addToReport("Custom amount'" + customAmount + " Last out standing Amount" + lastStandingAmount + "'", Status.FAIL, false);
                }

            } else {
                addToReport("Validation fail custom and last standing amounts", Status.FAIL, false);
            }

            sendKeysToElement(txtCustomAmount, Keys.BACK_SPACE, 15);
            addToReport("clear the custom amount", Status.PASS, false);

        addToReport("---------- Start of Grater than Zero error message validation ----------", Status.INFO, false);

            waitForElementPresence(lblGraterThanZero,LONG_WAIT);
              if (isElementPresentBy(lblGraterThanZero)) {
            addToReport("Greater Than Zero Error Present", Status.PASS);
             greterThanZeroError = getTextFromElement(lblGraterThanZero);
             } else {
            addToReport("Greater Than Zero Error Unavailable", Status.PASS, false);
            }
            if (greterThanZeroError.equalsIgnoreCase(errorMsg)) {
                addToReport("Greater Than 0 Error Message Present", Status.PASS, true);
            } else {
                addToReport("Greater Than 0 Error Message Not Present", Status.FAIL);
            }

        addToReport("---------- End of Grater than Zero error message validation ----------", Status.INFO, false);

            sendKeysToElement(txtCustomAmount, paymentAmount);
            addToReport("Added " + paymentAmount + " as the custom amount", Status.PASS);

            String selectedFundingAccount = getSelectedOptionText(ddFromAccount, "FIRST_SELECTED").get(0).trim().split(" - ")[0].trim();
            clickOnElement(btnConfirm);
            addToReport("clicked on the confirm button", Status.PASS);

            if (isElementPresentBy(btnBack)) {
                addToReport("Navigated to the Payment confirmation page", Status.PASS, true);
            } else {
                addToReport("Navigation failed to the Payment confirmation page", Status.PASS, false);
            }

            String fundingAccount = getAttributeOrText(lblPayFrom, "value").trim();
            String customAmountConfirmation = getAttributeOrText(lblAmount, "value").trim().split(" ")[1].trim();

            if (paymentAmount.equalsIgnoreCase(customAmountConfirmation) && selectedFundingAccount.equalsIgnoreCase(fundingAccount)) {
                addToReport("Custom amount matched: Entered = '" + paymentAmount + "', Confirmation = '" + customAmountConfirmation + "', available Funding Account = '" + fundingAccount + "', Expected Funding Account = '" + selectedFundingAccount + "'", Status.PASS, true);
            } else {
                addToReport("Mismatch detected! Entered Custom Amount = '" + paymentAmount + "', Confirmation Amount = '" + customAmountConfirmation + "', available Funding Account = '" + fundingAccount + "', Expected Funding Account = '" + selectedFundingAccount + "'", Status.FAIL, false);
            }

    }
        /**
         * This method is entering the OTP to navigates and validates the success message
         *
         * @param otp  - OTP
         * @param successMsg - success message
         *
         */
        public void enterOTPAndContinueSettingsPage(String otp, String successMsg) {

            //Enter OTP values and continue
            try {
                sendKeysToElement(tfOTP(1), String.valueOf(otp));

                clickOnElement(btnConfirmConfirmation);
            } catch (Exception e) {
                addToReport("Error when entering OTP", Status.FAIL);
                throw new RuntimeException("Failed to enter OTP " + e.getMessage(), e);
            }

            waitForElementPresence(getSuccessfulMsg(successMsg),20); //Request successful
            //Validate the error message
            if (isElementPresentBy(getSuccessfulMsg(successMsg))) {
                addToReport("'" + successMsg + "' message is present.", Status.PASS,true);
            } else {
                addToReport("'" + successMsg + "'  message is not present.", Status.FAIL);
                throw new RuntimeException("Error message validation is unsuccessful.");
            }

        }


    /**
     *
     * This method will validate the transaction tables Pending,Installment,Unbilled
     *
     */

    public void validateTransactionTables() {

        addToReport("----- Validating Unbilled Transaction Table -----", Status.INFO, false);

        clickOnElement(btnUnbilled);
        waitForElementPresence(tblTransaction,PawnConstants.WAIT_EXTRA_LONG);
        //Validating unbilled section
        // Get all rows excluding the card number row (row[1])
        List<WebElement> rows = driver.findElements(tblTransaction);

        boolean flag = true;

        for (int i = 1; i <= rows.size(); i++) {
            // +1 to skip card number row
            postDate = getTextFromElement(tblTransactionCell(i + 1, 1)).trim();
            transactionDate = getTextFromElement(tblTransactionCell(i + 1, 2)).trim();
            originalAmount = getTextFromElement(tblTransactionCell(i + 1, 3)).trim();
            merchant = getTextFromElement(tblTransactionCell(i + 1, 4)).trim();
            tableAmount = getTextFromElement(tblTransactionCell(i + 1, 5)).trim();


            if (CommonUtils.containsValuesOnDateYearFirst(postDate)) {
                addToReport("Row " + i + ": Valid Post Date - " + postDate, Status.PASS, false);
            } else {
                addToReport("Row " + i + ": Invalid Post Date - " + postDate, Status.FAIL);
                flag = false;
            }

            // === Transaction Date ===
            if (CommonUtils.containsValuesOnDateYearFirst(transactionDate)) {
                addToReport("Row " + i + ": Valid Transaction Date - " + transactionDate, Status.PASS, false);
            } else {
                addToReport("Row " + i + ": Invalid Transaction Date - " + transactionDate, Status.FAIL);
                flag = false;
            }

            // === Original Amount with LKR ===
            if (CommonUtils.containsAlphAndNumCharacters(originalAmount)) {
                addToReport("Row " + i + ": Valid Original Amount with LKR - " + originalAmount, Status.PASS, false);
            } else {
                addToReport("Row " + i + ": Missing in Original Amount - " + originalAmount, Status.FAIL);
                flag = false;
            }

            // === Merchant Not Empty ===
            if (!merchant.isEmpty()) {
                addToReport("Row " + i + ": Valid Merchant - " + merchant, Status.PASS, false);
            } else {
                addToReport("Row " + i + ": Merchant field is empty", Status.FAIL);
                flag = false;
            }

            // === Amount Should Be Valid Numeric with Optional Negative ===
            if (CommonUtils.containsAlphAndNumCharacters(tableAmount)) {
                addToReport("Row " + i + ": Valid Amount - " + tableAmount, Status.PASS, false);
            } else {
                addToReport("Row " + i + ": Invalid Amount - " + tableAmount, Status.FAIL);
                flag = false;
            }
        }

        if (flag) {
            addToReport("All rows in Transaction Table validated successfully.", Status.PASS);
        } else {
            addToReport("Some rows in Transaction Table failed validation.", Status.FAIL);
        }

        addToReport("----- Unbilled Transaction Table Validation Done -----", Status.INFO, false);

        clickOnElement(btnPending);
        waitForElementPresence(tblPendingSection,PawnConstants.WAIT_EXTRA_LONG);
        //Validating Pending section
        addToReport("----- Validating Pending Transaction Table -----", Status.INFO, false);

        List<WebElement> PendingSectionRows = driver.findElements(tblPendingSection);

        for (int i = 1; i <= PendingSectionRows.size(); i++) {

            transactionDatePendingSection = Optional.ofNullable(getTextFromElement(tblTransactionCell(i + 1, 1))).orElse("").trim();
            originalAmountPendingSection = Optional.ofNullable(getTextFromElement(tblTransactionCell(i + 1, 2))).orElse("").trim();
            merchantPendingSection = Optional.ofNullable(getTextFromElement(tblTransactionCell(i + 1, 3))).orElse("").trim();

            // === Transaction Date ===
            if (CommonUtils.containsValuesOnDateYearFirst(transactionDatePendingSection)) {
                addToReport("Row " + i + ": Valid Transaction Date - " + transactionDatePendingSection, Status.PASS, false);
            } else {
                addToReport("Row " + i + ": Invalid Transaction Date - " + transactionDatePendingSection, Status.FAIL);
                flag = false;
            }

            // === Original Amount ===
             if (CommonUtils.containsAlphAndNumCharacters(originalAmountPendingSection)) {
                addToReport("Row " + i + ": Valid Amount with LKR - " + originalAmountPendingSection, Status.PASS, false);
            } else {
                addToReport("Row " + i + ": Amount missing LKR - " + originalAmountPendingSection, Status.FAIL);
                flag = false;
            }

            // === Merchant ===
            if (!merchantPendingSection.isEmpty()) {
                addToReport("Row " + i + ": Valid Merchant - " + merchant, Status.PASS, false);
            } else {
                addToReport("Row " + i + ": Merchant is empty", Status.FAIL);
                flag = false;
            }
        }

        if (flag) {
            addToReport("All rows in 3-column Transaction Table validated successfully.", Status.PASS);
        } else {
            addToReport("Some rows in 3-column Transaction Table failed validation.", Status.FAIL);
        }

        addToReport("----- Pending section Validation Completed -----", Status.INFO, false);

        //Uncomment below once credit card details are available

        //Validating the installment section
//
//        addToReport("----- Validating  installment section Table -----", Status.INFO, false);
//
//        clickOnElement(btnInstallment);
//        waitForElementPresence(tblInstalmentSection,PawnConstants.WAIT_EXTRA_LONG);
//
//        List<WebElement> InstallmentSectionRows = driver.findElements(tblInstalmentSection);
//
//        for (int i = 1; i <= InstallmentSectionRows.size(); i++) {
//            String transactionDate = getTextFromElement(tblTransactionCell(i, 1)).trim();
//            String transactionAmount = getTextFromElement(tblTransactionCell(i, 2)).trim();
//            String merchant = getTextFromElement(tblTransactionCell(i, 3)).trim();
//            String totalInstallments = getTextFromElement(tblTransactionCell(i, 4)).trim();
//            String installmentPaid = getTextFromElement(tblTransactionCell(i, 5)).trim();
//            String remainingInstallment = getTextFromElement(tblTransactionCell(i, 6)).trim();
//            String installmentAmount = getTextFromElement(tblTransactionCell(i, 7)).trim();
//            String remainingAmount = getTextFromElement(tblTransactionCell(i, 8)).trim();
//
//            // === Transaction Date ===
//            if (CommonUtils.containsValuesOnDateYearFirst(transactionDate)) {
//                addToReport("Row " + i + ": Valid Transaction Date - " + transactionDate, Status.PASS, false);
//            } else {
//                addToReport("Row " + i + ": Invalid Transaction Date - " + transactionDate, Status.FAIL);
//                flag = false;
//            }
//
//            // === Transaction Amount ===
//            if (transactionAmount.contains("LKR")) {
//                addToReport("Row " + i + ": Valid Transaction Amount - " + transactionAmount, Status.PASS, false);
//            } else {
//                addToReport("Row " + i + ": Missing LKR in Transaction Amount - " + transactionAmount, Status.FAIL);
//                flag = false;
//            }
//
//            // === Merchant ===
//            if (!merchant.isEmpty()) {
//                addToReport("Row " + i + ": Valid Merchant - " + merchant, Status.PASS, false);
//            } else {
//                addToReport("Row " + i + ": Merchant is empty", Status.FAIL);
//                flag = false;
//            }
//
//            // === Total Installments ===
//            if (CommonUtils.containsAlphAndNumCharacters(totalInstallments)) {
//                addToReport("Row " + i + ": Valid Total Installments - " + totalInstallments, Status.PASS, false);
//            } else {
//                addToReport("Row " + i + ": Invalid Total Installments - " + totalInstallments, Status.FAIL);
//                flag = false;
//            }
//
//            // === Installment Paid ===
//            if (CommonUtils.containsNumericCharacters(installmentPaid)) {
//                addToReport("Row " + i + ": Valid Installment Paid - " + installmentPaid, Status.PASS, false);
//            } else {
//                addToReport("Row " + i + ": Invalid Installment Paid - " + installmentPaid, Status.FAIL);
//                flag = false;
//            }
//
//            // === Remaining Installment ===
//            if (CommonUtils.containsNumericCharacters(remainingInstallment)) {
//                addToReport("Row " + i + ": Valid Remaining Installment - " + remainingInstallment, Status.PASS, false);
//            } else {
//                addToReport("Row " + i + ": Invalid Remaining Installment - " + remainingInstallment, Status.FAIL);
//                flag = false;
//            }
//
//            // === Installment Amount ===
//            if (CommonUtils.containsAlphAndNumCharacters(installmentAmount.replace("LKR", "").replace(",", "").trim())) {
//                addToReport("Row " + i + ": Valid Installment Amount - " + installmentAmount, Status.PASS, false);
//            } else {
//                addToReport("Row " + i + ": Invalid Installment Amount - " + installmentAmount, Status.FAIL);
//                flag = false;
//            }
//
//            // === Remaining Amount ===
//            if (CommonUtils.containsAlphAndNumCharacters(remainingAmount.replace("LKR", "").replace(",", "").trim())) {
//                addToReport("Row " + i + ": Valid Remaining Amount - " + remainingAmount, Status.PASS, false);
//            } else {
//                addToReport("Row " + i + ": Invalid Remaining Amount - " + remainingAmount, Status.FAIL);
//                flag = false;
//            }
//        }
//
//        if (flag) {
//            addToReport("All rows of the installment section validated successfully.", Status.PASS);
//        } else {
//            addToReport("Some rows of installment section Table failed validation.", Status.FAIL);
//        }
//
//        addToReport("----- Validation Completed installment section -----", Status.INFO, false);


    }


}
