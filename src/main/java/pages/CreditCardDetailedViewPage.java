package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.CommonUtils;
import utils.constants.*;

import java.io.File;
import java.util.List;
import java.util.Optional;

import static utils.Drivers.*;

public class CreditCardDetailedViewPage extends BasePage {


    public CreditCardDetailedViewPage(WebDriver driver) {
        super(driver);
    }

    String minimumAmount, lastStandingAmount, customAmount, cardNumber, accountNumber, creditLimit,expiryDate, status, balance, cardType, typeLogo, postDate, transactionDate, insufficientFundError, originalAmount, greterThanZeroError, merchant, tableAmount, transactionDatePendingSection, originalAmountPendingSection, merchantPendingSection, blockUnblock, customAmountForCurrency, amountAfterDeductionStr, valueForAmountvalidationn;
    boolean flag = true;
    public enum ElementType {
        button, label, span, div, submit;
    }

    private static final By imgmyAccount = By.xpath("(//a[@class='NavBar_navlink__CRz3E NavBar_navlinkHover__eiXyp'])[1]");
    private static final By lblCreditCard = By.xpath("//div[contains(text(), 'Credit Cards') and ancestor::a[@class='SubMenu_subMenuItem___oYCo']]");
    private static final By btnSettlement = By.xpath("(//button[contains(@class, 'font-semibold')])[2]");
    private static final By txtCustomAmount = By.xpath("//input[contains(@inputmode, 'numeric')]");
    private static final By ddFromAccount = By.xpath("//select[contains(@class, 'font-semibold')]");
    private static final By txtMinimumeAmount = By.xpath("(//div[contains(@class, 'rounded-lg') and contains(@class, 'p-4') and contains(@class, 'appearance-none')])[2]");
    private static final By txtLastStandingAmount = By.xpath("(//div[contains(@class, 'rounded-lg') and contains(@class, 'p-4') and contains(@class, 'appearance-none')])[1]");
    private static final By lblPayFrom = By.xpath("(//input[@type='text' and @disabled and contains(@class, 'rounded-lg') and contains(@class, 'p-4')])[1]");
    private static final By lblAmount = By.xpath("(//input[@type='text' and @disabled and contains(@class, 'rounded-lg') and contains(@class, 'p-4')])[2]");
    private static final By btnBack = By.xpath("(//button[contains(@class, 'font-bold rounded-lg')])[2]");
    private static final By tblTransaction = By.xpath("//table[contains(@class, 'min-w-full')]//tbody/tr[count(td)=4 and td[4][contains(text(), 'LKR')]]");
    private static final By tblPendingSection = By.xpath("//table[contains(@class, 'min-w-full')]//tbody/tr[count(td)=3]");
    private static final By tblInstalmentSection = By.xpath("//table[contains(@class, 'min-w-full')]//tbody/tr[count(td)=8]");
    private static final By tblRecentTransactions = By.xpath("//table[@class='w-full text-left whitespace-nowrap']//tbody/tr[count(td)=4]");
    private static final By lblTransactionDate = By.xpath("(//table[contains(@class, 'min-w-full')]//thead/tr[1]/th)[3]");
    private static final By tblStatement = By.xpath("//table//tr[count(td)=5]");
    private static final By lblBalance = By.xpath(".//div[contains(@class,'rounded-xl')]//div[@class='text-2xl']//span");
    private static final By lblAccontNumber = By.xpath(".//div[contains(@class,'rounded-xl')]//div[contains(@class,'text-base')]//span");
    private static final By lblStatus = By.xpath(".//div[contains(@class,'rounded-xl')]//div[contains(@class,'w-[60px]')]");
    private static final By lblGraterThanZero = By.xpath("//p[contains(@class, '') and contains(@class, 'text-xs') and contains(@class, 'mt-1')]");
    private static final By btnPending = By.xpath("(//button[contains(@class, 'cursor-pointer') and contains(@class, 'transition-colors')])[1]");
    private static final By lblLoadingIcon = By.xpath("//div[contains(@class,'AccountsCards_loader')]");
    private static final By lblBlockandUnblockAccount = By.xpath("(//span[contains(@class, 'text-center')])[1]");
    private static final By imgBlockAccount = By.xpath("//img[contains(@src, 'BlockIcon.03e9cc2b.png')]");
    private static final By imgUNBlockAccount = By.xpath("//img[contains(@src, 'card-unlock.2ff072fe.png')]");
    private static final By imgActivation = By.xpath("//img[contains(@src, 'TempBlockCard2')]");
    private static final By lblAccountListLoading = By.xpath("//div[contains(@class,'dark:bg-gray')]");
    private static final By btnAccount = By.xpath("//div[contains(@class,' AccountsCards')]//div[contains(text(),'Accounts')]");
    private static final By btnCreditCard = By.xpath("//div[contains(@class,' AccountsCards')]//div[contains(text(),'Credit Cards')]");
    private static final By lblLowFunds = By.xpath("//span[contains(@class, 'text-xs') and contains(@class, 'text-left')]");
    private static final By rdUSDagreement = By.xpath("//input[contains(@class, 'peer') and @type='checkbox']");
    private static final By lblAcknowldgementMsg = By.xpath("//span[contains(@class, 'font-medium text')]");
    private static final By lblApproximateAmount = By.xpath("//span[contains(@class, 'text-gray-800')]");
    private static final By imageSrc = By.xpath("//div[@class='flex flex-col items-center justify-center']/img[@alt='card type logo']");
    private static final By drdYearDropdown = By.xpath("//select[contains(@id, 'year')]");
    private static final By btntabSectionButtons = By.xpath("//div[contains(@class, 'overflow-x-auto')]//button[contains(text(), '-')]");
    private static final By lblbillingDate = By.xpath("//div[contains(text(),'Billing Date')]/following-sibling::div");
    private static final By creditLimitAmountDynamic = By.xpath("//span[normalize-space()='Your Credit Limit :']/following-sibling::span");
    private static final By btnClosePopup = By.xpath("//button[@aria-label='close']");

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

        return By.xpath("//input[contains(@class,'otp-box')][" + Index + "]");
    }

    public static By getValueBeforeLabel(String labelText) {
        return By.xpath("//span[normalize-space(text())='" + labelText + "']/preceding-sibling::span[1]");
    }

    private static By getElementByTypeAndText(ElementType type, String text, int index) {
        return By.xpath("(//" + type.name() + "[contains(normalize-space(.), \"" + text + "\")])[" + index + "]");
    }
    private static By getCardDetailByLabell(String labelText) {
        return By.xpath("//div[contains(@class,'grid-cols-2') and contains(@class,'items-center')]" +
                "[div[normalize-space(text())='" + labelText + "']]/div[2]");
    }
    private static By popUpPDFDownload(String msg) {
        return By.xpath("//div[text()='" + msg + "']");
    }
    private static By getTransactionTableCell(int row, int col) {
        return By.xpath("(//table[contains(@class, 'min-w-full')]//tbody/tr[td[not(@colspan)]])[" + row + "]/td[" + col + "]");
    }



    /**
     * This method will navigate to the credit card details section
     */
    public void NavigatetoCreditCardDetailsPage() {

        addToReport("----------Navigating to the Credit card section ----------", Status.INFO, false);

        waitForElementPresence(imgmyAccount);
        hoverOverElement(imgmyAccount);
        addToReport("Hover on the My accounts tab ", Status.PASS, false);

        waitForElementPresence(lblCreditCard);
        clickOnElement(lblCreditCard);
        addToReport("Clicked on the Credit Card tab ", Status.PASS);

        waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
        if (waitForElementPresence(lblTransactionDate, PawnConstants.WAIT_EXTREME_LONG)) {
            addToReport(" Credit card details are visible.", Status.PASS);
        } else {
            addToReport(" Credit card details are not visible.(Not data requested)", Status.INFO);
        }

        addToReport("----------Navigated to the  Credit card section ----------", Status.INFO, false);

    }

    /**
     * This method will validate the credit card details
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
        if (status.equalsIgnoreCase(CreditCardConstants.STATUS_ACTIVE)) {
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

        typeLogo = getAttributeOrText(imageSrc, "src");
        if (typeLogo.contains(CreditCardConstants.VISA_LOGO)) {
            addToReport("Credit card Logo is visible ", Status.PASS);
        } else {
            addToReport("Credit card Logo is not visible ", Status.FAIL);
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


        addToReport("----- Validating recent Transaction Table -----", Status.INFO, false);
        waitForElementPresence(tblRecentTransactions, PawnConstants.WAIT_EXTREME_LONG);
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
     * This method will validate the credit card settlement
     *
     * @param payingAccountNumber   - Paying account number
     * @param InsufficientamountErr - Error message
     * @param ZeroAmount            - 0 amount
     * @param errorMsg              - Error message
     * @param paymentAmount         - Paying amount
     */

    public void validateCreditCardSettlement(String payingAccountNumber, String InsufficientamountErr, String ZeroAmount, String errorMsg, String paymentAmount) {

        waitForElementPresence(btnSettlement, MODERATE_WAIT);
        if (isElementPresentBy(btnSettlement)) {
            addToReport("Settlement button available", Status.PASS, false);
            clickOnElement(getElementByTypeAndText(ElementType.button, CreditCardConstants.SETTLE, 1));
        } else {
            addToReport("Settlement button not available", Status.FAIL);
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
        addToReport("---------- Start Currency type validation ----------", Status.INFO, false);

        String valueForCurrencyValidation = CommonUtils.extractCurrencyCode(getSelectedOptionText(ddFromAccount, "FIRST_SELECTED").get(0));
        customAmountForCurrency = getAttributeOrText(txtCustomAmount, "value").trim().split(" ")[0];

        if (valueForCurrencyValidation.equalsIgnoreCase(customAmountForCurrency)) {
            addToReport("From account currency " + valueForCurrencyValidation + " Custom amount section currency " + customAmountForCurrency + " matches '", Status.PASS, false);
        } else {
            addToReport("From account currency " + valueForCurrencyValidation + " Custom amount section currency" + customAmountForCurrency + " Not - matches '", Status.FAIL);
        }

        addToReport("---------- End Currency type validation ----------", Status.INFO, false);


        addToReport("---------- Start of Insufficient amount validation ----------", Status.INFO, false);

        String amountDigits = getSelectedOptionText(ddFromAccount, "FIRST_SELECTED").get(0).replaceAll(".*AVL\\.\\s*LKR\\s*", "").replaceAll("[^\\d]", "");
        sendKeysToElement(txtCustomAmount, amountDigits);

        clickOnElement(getElementByTypeAndText(ElementType.button, MessagingConstants.CONFIRM, 1));
        addToReport("clicked on the confirm button", Status.PASS);
        waitForElementPresence(lblLowFunds, MODERATE_WAIT);
        if (isElementPresentBy(lblLowFunds)) {
            insufficientFundError = getTextFromElement(lblLowFunds).trim();
            if (insufficientFundError.equalsIgnoreCase(InsufficientamountErr)) {
                addToReport("Insufficient amount Error Message Present", Status.PASS, true);
            } else {
                addToReport("Insufficient amount Error Message Not Present", Status.FAIL);
            }
        }

        addToReport("---------- End of Insufficient amount validation ----------", Status.INFO, false);

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
                addToReport("Custom amount'" + customAmount + " Minimum Amount" + minimumAmount + "'", Status.PASS, false);
            } else {
                addToReport("(This was discussed as ok) Custom amount'" + customAmount + " Minimum Amount" + minimumAmount + "'", Status.INFO);
            }

        } else {
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

            if (valueForCurrencyValidation.equalsIgnoreCase(DashboardConstants.CURRENCY_VALUES[0])) {

                if (customAmount.contains(lastStandingAmount)) {
                    addToReport("Custom amount'" + customAmount + " Last out standing Amount " + lastStandingAmount + "'", Status.PASS, true);
                } else {
                    addToReport("Custom amount'" + customAmount + " Last out standing Amount " + lastStandingAmount + "'", Status.FAIL, false);
                }
            } else {

                String customAmountOnly = CommonUtils.extractAmountOnly(customAmount);
                String lastStandingOnly = CommonUtils.extractAmountOnly(lastStandingAmount);

                if (customAmountOnly.equals(lastStandingOnly)) {
                    addToReport("Custom amount'" + customAmountOnly + " Last out standing Amount " + lastStandingOnly + "'", Status.PASS, true);
                } else {
                    addToReport("Custom amount'" + customAmountOnly + " Last out standing Amount " + lastStandingOnly + "'", Status.FAIL, false);
                }
            }

        } else {
            addToReport("Validation fail custom and last standing amounts", Status.FAIL, false);
        }

        sendKeysToElement(txtCustomAmount, Keys.BACK_SPACE, 15);
        addToReport("clear the custom amount", Status.PASS, false);
        sendKeysToElement(txtCustomAmount, ZeroAmount);

        addToReport("---------- Start of Grater than Zero error message validation ----------", Status.INFO, false);

        waitForElementPresence(lblGraterThanZero, LONG_WAIT);
        if (isElementPresentBy(lblGraterThanZero)) {
            addToReport("Greater Than Zero lbl Error Present", Status.PASS);
            greterThanZeroError = getTextFromElement(lblGraterThanZero);
        } else {
            addToReport("Greater Than Zero lbl Error Unavailable", Status.PASS, false);
        }
        if (greterThanZeroError.equalsIgnoreCase(errorMsg)) {
            addToReport("Greater Than 0 Error Message Present", Status.PASS, true);
        } else {
            addToReport("Greater Than 0 Error Message Not Present", Status.FAIL);
        }

        addToReport("---------- End of Grater than Zero error message validation ----------", Status.INFO, false);
        sendKeysToElement(txtCustomAmount, Keys.BACK_SPACE, 15);
        sendKeysToElement(txtCustomAmount, paymentAmount);
        addToReport("Added " + paymentAmount + " as the custom amount", Status.PASS);

        String selectedFundingAccount = getSelectedOptionText(ddFromAccount, "FIRST_SELECTED").get(0).trim().split(" - ")[0].trim();
        String[] parts = getSelectedOptionText(ddFromAccount, "FIRST_SELECTED").get(0).trim().split(" ");
        String valueForAmountvalidation = parts[parts.length - 1].trim();
        clickOnElement(getElementByTypeAndText(ElementType.button, MessagingConstants.CONFIRM, 1));
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

        double val1 = Double.parseDouble(valueForAmountvalidation.replace(",", ""));
        double val2 = Double.parseDouble(customAmountConfirmation.replace(",", ""));
        double amountAfterDeduction = val1 - val2;
        amountAfterDeductionStr = Double.toString(amountAfterDeduction);
        addToReport("Remaining amount after the deduction " + amountAfterDeductionStr, Status.PASS, false);
    }

    /**
     * This method is entering the OTP to navigates and validates the success message
     *
     * @param otp        - OTP
     * @param successMsg - success message
     */
    public void enterOTPAndContinueSettingsPage(String otp, String acknowlagmentmessage, String successMsg) {

        //Enter OTP values and continue
        try {
            sendKeysToElement(tfOTP(1), String.valueOf(otp));
            if (customAmountForCurrency.equalsIgnoreCase(DashboardConstants.CURRENCY_VALUES[1])) {
                waitForElementPresence(rdUSDagreement, LONG_WAIT);
                if (isElementPresentBy(lblAcknowldgementMsg, SHORT_WAIT)) {
                    String extractedAcknowldgementMessage = getTextFromElement(lblAcknowldgementMsg).trim();
                    if (extractedAcknowldgementMessage.equalsIgnoreCase(acknowlagmentmessage)) {
                        addToReport("Acknowledgment Message Present " + extractedAcknowldgementMessage, Status.PASS, false);
                    } else {
                        addToReport("Acknowledgment Message is not Present ", Status.FAIL);
                    }
                }
                if (isElementPresentBy(lblApproximateAmount, SHORT_WAIT)) {
                    String actualFeeText = getTextFromElement(lblApproximateAmount).trim();
                    String expectedRegex = BillerConstants.APPROX_LKR_LABEL_PREFIX + " \\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2} " + BillerConstants.TIME_PERIOD_REGEX + " - " + BillerConstants.LKR_TEXT + "\\s*[\\d,]+\\.\\d{2} \\(" + BillerConstants.USD_EXCHANGE_PREFIX + " [\\d,]+\\.\\d{2}\\)";
                    if (actualFeeText.matches(expectedRegex)) {
                        addToReport("Successfully validated processing fee label: " + actualFeeText, Status.PASS, false);
                    } else {
                        addToReport("Failed to validate label. Actual: '" + actualFeeText + "' | Expected pattern: '" + expectedRegex + "'", Status.FAIL, true);
                    }
                }
                clickOnElement(rdUSDagreement);
                addToReport("Click on the USD agreement radio button", Status.PASS);
            }
            clickOnElement(getElementByTypeAndText(ElementType.button, MessagingConstants.CONFIRM, 2));
        } catch (Exception e) {
            addToReport("Error when entering OTP", Status.FAIL);
            throw new RuntimeException("Failed to enter OTP " + e.getMessage(), e);
        }

        waitForElementPresence(getSuccessfulMsg(successMsg),LONG_WAIT); //Request successful
        //Validate the error message
        if (isElementPresentBy(getSuccessfulMsg(successMsg))) {
            addToReport("'" + successMsg + "' message is present.", Status.PASS, true);
        } else {
            addToReport("'" + successMsg + "'  message is not present.", Status.FAIL);
            throw new RuntimeException("Error message validation is unsuccessful.");
        }
        if (customAmountForCurrency.equalsIgnoreCase(DashboardConstants.CURRENCY_VALUES[1])) {
            waitForElementPresence(btnBack, LONG_WAIT);
            if (isElementPresentBy(lblApproximateAmount, SHORT_WAIT)) {
                String actualFeeText = getTextFromElement(lblApproximateAmount).trim();
                String expectedRegex = BillerConstants.APPROX_LKR_LABEL_PREFIX + " \\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2} " + BillerConstants.TIME_PERIOD_REGEX + " - " + BillerConstants.LKR_TEXT + "\\s*[\\d,]+\\.\\d{2} \\(" + BillerConstants.USD_EXCHANGE_PREFIX + " [\\d,]+\\.\\d{2}\\)";
                if (actualFeeText.matches(expectedRegex)) {
                    addToReport("Successfully validated processing fee label: " + actualFeeText, Status.PASS, false);
                } else {
                    addToReport("Failed to validate label. Actual: '" + actualFeeText + "' | Expected pattern: '" + expectedRegex + "'", Status.FAIL, true);
                }
            } //clickOnElement(btnBack);
            clickOnElement(getElementByTypeAndText(ElementType.button, CreditCardConstants.CLOSE, 1));
        } else {
            if (isElementPresentBy(btnBack)) {
                clickOnElement(getElementByTypeAndText(ElementType.button, CreditCardConstants.CLOSE, 1));
                //clickOnElement(btnBack);
            }
        }
    }

    /**
     * To Validate the deduct amount
     *
     * @param payingAccountNumber - Account number
     */

    public void validateDeductAmount(String payingAccountNumber) {

        if (isElementPresentBy(btnSettlement)) {
            addToReport("Settlement button available", Status.PASS, false);
            // clickOnElement(btnSettlement);
            clickOnElement(getElementByTypeAndText(ElementType.button, CreditCardConstants.SETTLE, 1));
        } else {
            addToReport("Settlement button not available", Status.FAIL);
        }

        waitForElementPresence(txtCustomAmount, PawnConstants.WAIT_EXTRA_LONG);
        addToReport("Custom Amount section is visible ", Status.PASS, false);

        if (isElementPresentBy(ddFromAccount)) {
            addToReport("From Account DropDown  available", Status.PASS, false);
            selectFromDropdown(ddFromAccount, payingAccountNumber, "value");
            valueForAmountvalidationn = getSelectedOptionText(ddFromAccount, "FIRST_SELECTED").get(0).trim().split(" ")[4].replace(",", "");
            addToReport("'" + payingAccountNumber + "'selected from the dropdown.", Status.PASS);
        } else {
            addToReport("DropDown is not available", Status.FAIL);

        }
        if (amountAfterDeductionStr.equals(valueForAmountvalidationn)) {
            addToReport("Deduct Amount Reflecting as: " + amountAfterDeductionStr, Status.PASS);
        } else {
            addToReport("Deduct Amount is not Reflecting correctly: " + amountAfterDeductionStr + "Actual " + valueForAmountvalidationn, Status.FAIL);
        }

        clickOnElement(getElementByTypeAndText(ElementType.button, CreditCardConstants.BACK, 1));
        addToReport("Clicked on back button ", Status.PASS, false);
    }

    /**
     * This method will validate the transaction tables Pending,Installment,Unbilled
     */

    public void validateTransactionTables() {

        // ----- Unbilled Section -----
        try {
            addToReport("----- Validating Unbilled Transaction Table -----", Status.INFO, false);
            clickOnElement(getElementByTypeAndText(ElementType.button, CreditCardConstants.UNBILLED, 1));
            waitForElementPresence(tblTransaction, PawnConstants.WAIT_EXTRA_LONG);

            if (isElementPresentBy(tblTransaction)) {
                addToReport("Able to load Unbilled table.", Status.PASS);

                List<WebElement> rows = driver.findElements(tblTransaction);

                for (int i = 1; i <= rows.size(); i++) {
                    postDate = getTextFromElement(tblTransactionCell(i + 1, 1)).trim();
                    transactionDate = getTextFromElement(tblTransactionCell(i + 1, 2)).trim();
                    merchant = getTextFromElement(tblTransactionCell(i + 1, 3)).trim();
                    tableAmount = getTextFromElement(tblTransactionCell(i + 1, 4)).trim();

                    if (CommonUtils.containsValuesOnDateYearFirst(postDate)) {
                        addToReport("Row " + i + ": Valid Post Date - " + postDate, Status.PASS, false);
                    } else {
                        addToReport("Row " + i + ": Invalid Post Date - " + postDate, Status.FAIL);
                        flag = false;
                    }

                    if (CommonUtils.containsValuesOnDateYearFirst(transactionDate)) {
                        addToReport("Row " + i + ": Valid Transaction Date - " + transactionDate, Status.PASS, false);
                    } else {
                        addToReport("Row " + i + ": Invalid Transaction Date - " + transactionDate, Status.FAIL);
                        flag = false;
                    }

                    if (!merchant.isEmpty()) {
                        addToReport("Row " + i + ": Valid Merchant - " + merchant, Status.PASS, false);
                    } else {
                        addToReport("Row " + i + ": Merchant field is empty", Status.FAIL);
                        flag = false;
                    }

                    if (CommonUtils.containsAlphAndNumCharacters(tableAmount)) {
                        addToReport("Row " + i + ": Valid Amount - " + tableAmount, Status.PASS, false);
                    } else {
                        addToReport("Row " + i + ": Invalid Amount - " + tableAmount, Status.FAIL);
                        flag = false;
                    }
                }

                addToReport(flag ? "All rows in Transaction Table validated successfully." : "Some rows in Transaction Table failed validation.", flag ? Status.PASS : Status.FAIL);
            } else {
                addToReport("Fail to load Unbilled table.", Status.FAIL);
            }

            addToReport("----- Unbilled Transaction Table Validation Done -----", Status.INFO, false);
        } catch (Exception e) {
            addToReport("Exception occurred during Unbilled section validation: " + e.getMessage(), Status.FAIL);
        }

        // ----- Pending Section -----
        try {
            clickOnElement(getElementByTypeAndText(ElementType.button, CreditCardConstants.PENDING, 1));
            waitForElementPresence(tblPendingSection, PawnConstants.WAIT_EXTRA_LONG);

            if (isElementPresentBy(tblTransaction)) {
                addToReport("Able to load Pending table.", Status.PASS);
                addToReport("----- Validating Pending Transaction Table -----", Status.INFO, false);

                List<WebElement> pendingRows = driver.findElements(tblPendingSection);

                for (int i = 1; i <= pendingRows.size(); i++) {
                    transactionDatePendingSection = Optional.ofNullable(getTextFromElement(tblTransactionCell(i + 1, 1))).orElse("").trim();
                    originalAmountPendingSection = Optional.ofNullable(getTextFromElement(tblTransactionCell(i + 1, 2))).orElse("").trim();
                    merchantPendingSection = Optional.ofNullable(getTextFromElement(tblTransactionCell(i + 1, 3))).orElse("").trim();

                    if (CommonUtils.containsValuesOnDateYearFirst(transactionDatePendingSection)) {
                        addToReport("Row " + i + ": Valid Transaction Date - " + transactionDatePendingSection, Status.PASS, false);
                    } else {
                        addToReport("Row " + i + ": Invalid Transaction Date - " + transactionDatePendingSection, Status.FAIL);
                        flag = false;
                    }

                    if (CommonUtils.containsAlphAndNumCharacters(originalAmountPendingSection)) {
                        addToReport("Row " + i + ": Valid Amount - " + originalAmountPendingSection, Status.PASS, false);
                    } else {
                        addToReport("Row " + i + ": Invalid Amount - " + originalAmountPendingSection, Status.FAIL);
                        flag = false;
                    }

                    if (!merchantPendingSection.isEmpty()) {
                        addToReport("Row " + i + ": Valid Merchant - " + merchantPendingSection, Status.PASS, false);
                    } else {
                        addToReport("Row " + i + ": Merchant is empty", Status.FAIL);
                        flag = false;
                    }
                }

                addToReport(flag ? "All rows in 3-column Transaction Table validated successfully." : "Some rows in 3-column Transaction Table failed validation.", flag ? Status.PASS : Status.FAIL);
            } else {
                addToReport("Fail to load Pending table. (No data requested)", Status.INFO);
            }

            addToReport("----- Pending section Validation Completed -----", Status.INFO, false);
        } catch (Exception e) {
            addToReport("Exception occurred during Pending section validation: " + e.getMessage(), Status.FAIL);
        }

        // ----- Installment Section can execute after the data been added -----
//        try {
//            addToReport("----- Validating installment section Table -----", Status.INFO, false);
//            clickOnElement(btnInstallment);
//        clickOnElement(getElementByTypeAndText(ElementType.button,CreditCardConstants.INSTALLMENT,1));
//            waitForElementPresence(tblInstalmentSection, PawnConstants.WAIT_EXTRA_LONG);
//
//            List<WebElement> installmentRows = driver.findElements(tblInstalmentSection);
//
//            for (int i = 1; i <= installmentRows.size(); i++) {
//                String transactionDate = getTextFromElement(tblTransactionCell(i, 1)).trim();
//                String transactionAmount = getTextFromElement(tblTransactionCell(i, 2)).trim();
//                String merchant = getTextFromElement(tblTransactionCell(i, 3)).trim();
//                String totalInstallments = getTextFromElement(tblTransactionCell(i, 4)).trim();
//                String installmentPaid = getTextFromElement(tblTransactionCell(i, 5)).trim();
//                String remainingInstallment = getTextFromElement(tblTransactionCell(i, 6)).trim();
//                String installmentAmount = getTextFromElement(tblTransactionCell(i, 7)).trim();
//                String remainingAmount = getTextFromElement(tblTransactionCell(i, 8)).trim();
//
//                if (CommonUtils.containsValuesOnDateYearFirst(transactionDate)) {
//                    addToReport("Row " + i + ": Valid Transaction Date - " + transactionDate, Status.PASS, false);
//                } else {
//                    addToReport("Row " + i + ": Invalid Transaction Date - " + transactionDate, Status.FAIL);
//                    flag = false;
//                }
//
//                if (transactionAmount.contains("LKR")) {
//                    addToReport("Row " + i + ": Valid Transaction Amount - " + transactionAmount, Status.PASS, false);
//                } else {
//                    addToReport("Row " + i + ": Missing LKR in Transaction Amount - " + transactionAmount, Status.FAIL);
//                    flag = false;
//                }
//
//                if (!merchant.isEmpty()) {
//                    addToReport("Row " + i + ": Valid Merchant - " + merchant, Status.PASS, false);
//                } else {
//                    addToReport("Row " + i + ": Merchant is empty", Status.FAIL);
//                    flag = false;
//                }
//
//                if (CommonUtils.containsAlphAndNumCharacters(totalInstallments)) {
//                    addToReport("Row " + i + ": Valid Total Installments - " + totalInstallments, Status.PASS, false);
//                } else {
//                    addToReport("Row " + i + ": Invalid Total Installments - " + totalInstallments, Status.FAIL);
//                    flag = false;
//                }
//
//                if (CommonUtils.containsNumericCharacters(installmentPaid)) {
//                    addToReport("Row " + i + ": Valid Installment Paid - " + installmentPaid, Status.PASS, false);
//                } else {
//                    addToReport("Row " + i + ": Invalid Installment Paid - " + installmentPaid, Status.FAIL);
//                    flag = false;
//                }
//
//                if (CommonUtils.containsNumericCharacters(remainingInstallment)) {
//                    addToReport("Row " + i + ": Valid Remaining Installment - " + remainingInstallment, Status.PASS, false);
//                } else {
//                    addToReport("Row " + i + ": Invalid Remaining Installment - " + remainingInstallment, Status.FAIL);
//                    flag = false;
//                }
//
//                if (CommonUtils.containsAlphAndNumCharacters(installmentAmount.replace("LKR", "").replace(",", "").trim())) {
//                    addToReport("Row " + i + ": Valid Installment Amount - " + installmentAmount, Status.PASS, false);
//                } else {
//                    addToReport("Row " + i + ": Invalid Installment Amount - " + installmentAmount, Status.FAIL);
//                    flag = false;
//                }
//
//                if (CommonUtils.containsAlphAndNumCharacters(remainingAmount.replace("LKR", "").replace(",", "").trim())) {
//                    addToReport("Row " + i + ": Valid Remaining Amount - " + remainingAmount, Status.PASS, false);
//                } else {
//                    addToReport("Row " + i + ": Invalid Remaining Amount - " + remainingAmount, Status.FAIL);
//                    flag = false;
//                }
//            }
//
//            addToReport(flag ? "All rows of the installment section validated successfully." : "Some rows of installment section Table failed validation.", flag ? Status.PASS : Status.FAIL);
//            addToReport("----- Validation Completed installment section -----", Status.INFO, false);
//        } catch (Exception e) {
//            addToReport("Exception occurred during Installment section validation: " + e.getMessage(), Status.FAIL);
//        }
    }

    /**
     *
     * Method will validate the credit card statement section
     *
     * @param downloadDirectory - PDF downloaded location
     */

        public void validateStatementSection(String downloadDirectory) {
            scrollDownPage();
            waitForElementPresence(tblStatement, LONG_WAIT);

            //Statement Validation
            try {
                creditLimit = getTextFromElement(creditLimitAmountDynamic).split(" ")[1];
                // Select the year 2022
                selectFromDropdown(drdYearDropdown, "0", MessagingConstants.INDEX);

                // Billing Date
                String billingDate = getTextFromElement(getCardDetailByLabell(CreditCardConstants.BILLING_DATE_LABEL)).trim();
                if (CommonUtils.containsValuesOnDateYearFirst(billingDate)) {
                    addToReport("Billing Date is valid: " + billingDate, Status.PASS, false);
                } else {
                    addToReport("Invalid Billing Date format: " + billingDate, Status.FAIL);
                }

// Minimum Due
                String minDue = getTextFromElement(getCardDetailByLabell(CreditCardConstants.MINIMUM_DUE_LABEL)).trim();
                String minDueOnlyValue = getTextFromElement(getCardDetailByLabell(CreditCardConstants.MINIMUM_DUE_LABEL)).split(" ")[1];
                if (CommonUtils.containsAlphNumAndSpecialCharactersandSpace(minDue)) {
                    addToReport("Minimum Due is valid: " + minDue, Status.PASS, false);
                } else {
                    addToReport("Invalid Minimum Due format: " + minDue, Status.FAIL);
                }

// Due Date
                String dueDate = getTextFromElement(getCardDetailByLabell(CreditCardConstants.DUE_DATE_LABEL)).trim();
                if (CommonUtils.containsValuesOnDateYearFirst(dueDate)) {
                    addToReport("Due Date is valid: " + dueDate, Status.PASS, false);
                } else {
                    addToReport("Invalid Due Date format: " + dueDate, Status.FAIL);
                }

// Opening Balance
                String openingBalance = getTextFromElement(getCardDetailByLabell(CreditCardConstants.OPENING_BALANCE_LABEL)).trim();
                if (CommonUtils.containsAlphNumAndSpecialCharactersandSpace(openingBalance)) {
                    addToReport("Opening Balance is valid: " + openingBalance, Status.PASS, false);
                } else {
                    addToReport("Invalid Opening Balance format: " + openingBalance, Status.FAIL);
                }

// Debits
                String debits = getTextFromElement(getCardDetailByLabell(CreditCardConstants.DEBITS_LABEL)).trim();
                if (CommonUtils.containsAlphNumAndSpecialCharactersandSpace(debits)) {
                    addToReport("Debits value is valid: " + debits, Status.PASS, false);
                } else {
                    addToReport("Invalid Debits format: " + debits, Status.FAIL);
                }

// Credits
                String credits = getTextFromElement(getCardDetailByLabell(CreditCardConstants.CREDITS_LABEL)).trim();
                if (CommonUtils.containsAlphNumAndSpecialCharactersandSpace(credits)) {
                    addToReport("Credits value is valid: " + credits, Status.PASS, false);
                } else {
                    addToReport("Invalid Credits format: " + credits, Status.FAIL);
                }

// Closing Balance
                String closingBalance = getTextFromElement(getCardDetailByLabell(CreditCardConstants.CLOSING_BALANCE_LABEL)).trim();
                String closingBalanceOnlyValue = getTextFromElement(getCardDetailByLabell(CreditCardConstants.CLOSING_BALANCE_LABEL)).split(" ")[1];
                if (CommonUtils.containsAlphNumAndSpecialCharactersandSpace(closingBalance)) {
                    addToReport("Closing Balance is valid: " + closingBalance, Status.PASS, false);
                } else {
                    addToReport("Invalid Closing Balance format: " + closingBalance, Status.FAIL);
                }

                List<WebElement> rows = driver.findElements(tblStatement);

                for (int i = 1; i <= rows.size(); i++) {
                    String billedDate      = getTextFromElement(getTransactionTableCell(i, 1)).trim();
                    String transactionDate = getTextFromElement(getTransactionTableCell(i, 2)).trim();
                    String merchant        = getTextFromElement(getTransactionTableCell(i, 3)).trim();
                    String originalAmount  = getTextFromElement(getTransactionTableCell(i, 4)).trim();
                    String amount          = getTextFromElement(getTransactionTableCell(i, 5)).trim();

// Dates: expect YYYY-MM-DD
                    if (CommonUtils.containsValuesOnDateYearFirst(billedDate)) {
                        addToReport("Row " + i + ": Valid Billed Date - " + billedDate, Status.PASS, false);
                    } else {
                        addToReport("Row " + i + ": Invalid Billed Date - " + billedDate, Status.FAIL);
                        flag = false;
                    }

                    if (CommonUtils.containsValuesOnDateYearFirst(transactionDate)) {
                        addToReport("Row " + i + ": Valid Transaction Date - " + transactionDate, Status.PASS, false);
                    } else {
                        addToReport("Row " + i + ": Invalid Transaction Date - " + transactionDate, Status.FAIL);
                        flag = false;
                    }

                    // Merchant: non-empty
                    if (CommonUtils.containsAlphabaticCharacters(merchant)) {
                        addToReport("Row " + i + ": Valid Merchant - " + merchant, Status.PASS, false);
                    } else {
                        addToReport("Row " + i + ": Merchant is empty", Status.FAIL);
                        flag = false;
                    }

                    if (CommonUtils.containsAlphNumAndSpecialCharactersandSpace(originalAmount)) {
                        addToReport("Row " + i + ": Valid Original Amount - " + originalAmount, Status.PASS, false);
                    } else {
                        addToReport("Row " + i + ": Invalid Original Amount - " + originalAmount, Status.FAIL);
                        flag = false;
                    }

                    if (CommonUtils.containsAlphNumAndSpecialCharactersandSpace(amount)) {
                        addToReport("Row " + i + ": Valid Amount - " + amount, Status.PASS, false);
                    } else {
                        addToReport("Row " + i + ": Invalid Amount - " + amount, Status.FAIL);
                        flag = false;
                    }
                }
                scrollPageToTop();
                waitFor(VERY_SHORT_WAIT);
                clickOnElement(getElementByTypeAndText(ElementType.button,CreditCardConstants.DOWNLOAD_STATEMENT, 1));
                if (isElementPresentBy(popUpPDFDownload(MyAccountsConstants.PDF_DOWNLOADED_SUCCESSFULLY)) && isElementClickable(btnClosePopup)) {
                    addToReport("Validated downloaded toast message", Status.PASS, true);
                } else {
                    addToReport("Failed to validate downloaded toast message", Status.FAIL);
                }

                //Wait for download to initiate - update this with dynamic once stabilized
                waitFor(SHORT_WAIT);
                // Get the latest downloaded file
                File latestFile = getLatestDownloadedFile(downloadDirectory);

                if (latestFile != null) {
                    // Extract text from the PDF
                    String extractedText = extractTextFromPDF(latestFile.getAbsolutePath()).replace("/n", "");

                    addToReport(" Latest downloaded pdf :  : '" + extractedText, Status.INFO, false);

                    if (extractedText.contains(cardNumber)) {
                        addToReport(" Card number validate successful" + cardNumber , Status.PASS, false);
                    } else {
                        addToReport(" Card number validate Unsuccessful" + cardNumber, Status.FAIL);
                    }

                    if (extractedText.contains(billingDate)) {
                        addToReport("Billing Date validate with statement success" + billingDate, Status.PASS, false);
                    } else {
                        addToReport("Billing Date validate with statement unsuccessful: " + billingDate, Status.FAIL);
                    }
                  if (extractedText.contains(customAmountForCurrency)) {
                        addToReport("currency validate with statement success '" + customAmountForCurrency , Status.PASS, false);
                    } else {
                        addToReport("currency validate with statement unsuccessful " + customAmountForCurrency , Status.FAIL);
                    }

                    if (extractedText.contains(CreditCardConstants.CREDIT_LIMIT)  && extractedText.contains(creditLimit)) {
                        addToReport("Credit Limit and text validate with statement success '" + ((CreditCardConstants.CREDIT_LIMIT) + extractedText.contains(creditLimit)) , Status.PASS, false);
                    } else {
                        addToReport("Credit Limit and text validate with statement unsuccessful " + ((CreditCardConstants.CREDIT_LIMIT) + extractedText.contains(creditLimit)) , Status.FAIL);
                    }
                    if (extractedText.contains(closingBalanceOnlyValue)) {
                        addToReport("Closing Balance validate with statement success: " + closingBalanceOnlyValue, Status.PASS, false);
                    } else {
                        addToReport("Invalid Closing Balance validate with statement unsuccessful: " + closingBalanceOnlyValue, Status.FAIL);
                    }
                    if (extractedText.contains(minDueOnlyValue)) {
                        addToReport("Minimum Due validate with statement success: " + minDueOnlyValue, Status.PASS, false);
                    } else {
                        addToReport("Invalid Minimum Due format validate with statement unsuccessful: " + minDueOnlyValue, Status.FAIL);
                    }
                }


                }catch(Exception e){
                    addToReport("Exception occurred during Statement section validation: " + e.getMessage(), Status.FAIL);
                }

        }

    /**
     *
     *
     * This method will validate the Raw names of the statement table bug upliftment
     *
     */
    public void validateBillingByYearAndMonth() {
        try {
            scrollDownPage();
            clickOnElement(getElementByTypeAndText(ElementType.button, CreditCardConstants.STATEMENT, 1));
            waitForElementPresence(tblStatement, LONG_WAIT);
            // Get all year options
            List<String> yearOptions = getSelectedOptionText(drdYearDropdown, "ALL_OPTIONS");

            for (int y = 0; y < yearOptions.size(); y++) {
                selectFromDropdown(drdYearDropdown, String.valueOf(y), "index");
                String selectedYear = getSelectedOptionText(drdYearDropdown, "FIRST_SELECTED").get(0).trim();
                addToReport("Selected year: " + selectedYear, Status.INFO, false);
                waitForElementPresence(btntabSectionButtons, SHORT_WAIT);

                // Get tab count for the selected year
                int tabCount = getValues(btntabSectionButtons).size();
                addToReport("Tab count for year " + selectedYear + ": " + tabCount, Status.INFO, false);

                for (int t = 0; t < tabCount; t++) {
                    // Re-fetch tabs each time to avoid stale references
                    List<String> tabLabels = getValues(btntabSectionButtons);
                    String tabLabel = tabLabels.get(t).trim(); // e.g., Mar-20
                    clickOnElement(btntabSectionButtons, t);
                    addToReport("Clicked tab: " + tabLabel, Status.INFO, false);
                    waitForElementPresence(lblbillingDate, SHORT_WAIT);

                    String billingDate = getTextFromElement(lblbillingDate).trim(); // e.g., 2020-03-02
                    String billingYear = billingDate.split("-")[0];
                    String billingMonth = billingDate.split("-")[1];

                    String tabMonthAbbr = tabLabel.split("-")[0];
                    String expectedMonth = CreditCardConstants.MONTH_MAP.get(tabMonthAbbr);

                    // Validate year match
                    if (selectedYear.equals(billingYear)) {
                        addToReport("Year matched: Dropdown year = " + selectedYear + ", Billing date year = " + billingYear, Status.PASS, false);
                    } else {
                        addToReport("Year mismatch! Dropdown year = " + selectedYear + ", Billing date year = " + billingYear, Status.FAIL);
                    }

                    // Validate month match
                    if (billingMonth.equals(expectedMonth)) {
                        addToReport("Month matched: Tab = " + tabMonthAbbr + ", Billing month = " + billingMonth, Status.PASS, false);
                    } else {
                        addToReport("Month mismatch! Tab = " + tabMonthAbbr + " (" + expectedMonth + "), Billing = " + billingMonth, Status.FAIL);
                    }
                }
            }
        } catch (Exception e) {
            addToReport("Exception occurred during year-month validation: " + e.getMessage(), Status.FAIL);
        }
    }


    /**
     * To validate the Point redeem
     *
     *
     */

    public void ValidatingRedeem() {
        clickOnElement(getElementByTypeAndText(ElementType.button, CreditCardConstants.REDEEM, 1));
    }


    /**
     * Validate th credit Card blocking
     *
     * @param otp                   - OTP
     * @param successMsgForBlocking - Blocking success message
     * @param emailSentSuccessMsg   - Request success message
     */
    public void ValidateCardBlockUnblock(String otp, String successMsgForBlocking, String emailSentSuccessMsg) {
        addToReport("---------- Starting to validate the Credit Card blocking ----------", Status.INFO, false);
        waitFor(SHORT_WAIT);
        clickOnElement(imgBlockAccount);
        addToReport("Click on the Block account .", Status.PASS, false);

        if (isElementPresentBy(imgActivation)) {
            addToReport("Block Card UI available", Status.PASS, false);
            clickOnElement(getElementByTypeAndText(ElementType.button, CreditCardConstants.NEXT, 1));
            addToReport("Click on the Next button", Status.PASS, false);
        } else {
            addToReport("Block Card UI is not - available", Status.PASS, false);
        }

        try {
            sendKeysToElement(tfOTP(1), String.valueOf(otp));

            clickOnElement(getElementByTypeAndText(ElementType.button, CreditCardConstants.CONFIRM, 1));
        } catch (Exception e) {
            addToReport("Error when entering OTP", Status.FAIL);
            throw new RuntimeException("Failed to enter OTP " + e.getMessage(), e);
        }

        waitForElementPresence(getSuccessfulMsg(successMsgForBlocking),LONG_WAIT); //Request successful
        //Validate the error message
        if (isElementPresentBy(getSuccessfulMsg(successMsgForBlocking))) {
            addToReport("'" + successMsgForBlocking + "' message is present.", Status.PASS, true);
        } else {
            addToReport("'" + successMsgForBlocking + "'  message is not present.", Status.FAIL);
            throw new RuntimeException("Error message validation is unsuccessful.");
        }

        while (true) {
            waitForElementToBeInvisible(lblAccountListLoading, LONG_WAIT);
            waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);

            clickOnElement(btnAccount);
            waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
            waitFor(LONG_WAIT);
            clickOnElement(btnCreditCard);
            waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);

            blockUnblock = getTextFromElement(lblBlockandUnblockAccount).trim();

            if (blockUnblock.equalsIgnoreCase(CreditCardConstants.UNBLOCK)) {
                addToReport("Label updated successfully: " + blockUnblock, Status.PASS);
                break;
            } else {
                addToReport("Label not updated yet. Current value: " + blockUnblock, Status.INFO, false);
            }
        }


        if (isElementPresentBy(imgUNBlockAccount)) {
            clickOnElement(imgUNBlockAccount);
            addToReport("Click on the UNBlock account .", Status.PASS, false);
        }
        if (isElementPresentBy(getElementByTypeAndText(ElementType.button, CreditCardConstants.NEXT, 1))) {
            addToReport("UNBlock Card UI available", Status.PASS, false);
            clickOnElement(getElementByTypeAndText(ElementType.button, CreditCardConstants.NEXT, 1));
            addToReport("Click on the Next button", Status.PASS, false);
        } else {
            addToReport("UNBlock Card UI is not - available", Status.PASS, false);
        }

        waitForElementPresence(getSuccessfulMsg(emailSentSuccessMsg),LONG_WAIT);
        if (isElementPresentBy(getSuccessfulMsg(emailSentSuccessMsg))) {
            addToReport("'" + emailSentSuccessMsg + "' message is present.", Status.PASS, true);

            try {
                sendKeysToElement(tfOTP(1), String.valueOf(otp));

                clickOnElement(getElementByTypeAndText(ElementType.button, CreditCardConstants.CONFIRM, 1));
            } catch (Exception e) {
                addToReport("Error when entering OTP", Status.FAIL);
                throw new RuntimeException("Failed to enter OTP " + e.getMessage(), e);
            }

            waitForElementPresence(getSuccessfulMsg(successMsgForBlocking),LONG_WAIT); //Request successful
            //Validate the error message
            if (isElementPresentBy(getSuccessfulMsg(successMsgForBlocking))) {
                addToReport("'" + successMsgForBlocking + "' message is present.", Status.PASS, true);
            } else {
                addToReport("'" + successMsgForBlocking + "'  message is not present.", Status.FAIL);
                throw new RuntimeException("Error message validation is unsuccessful.");
            }

            while (true) {
                waitForElementToBeInvisible(lblAccountListLoading, LONG_WAIT);
                waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);

                clickOnElement(btnAccount);
                waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
                waitFor(LONG_WAIT);
                clickOnElement(btnCreditCard);
                waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);

                blockUnblock = getTextFromElement(lblBlockandUnblockAccount).trim();

                if (blockUnblock.equalsIgnoreCase(CreditCardConstants.BLOCK)) {
                    addToReport("Label updated successfully: " + blockUnblock, Status.PASS);
                    break;
                } else {
                    addToReport("Label not updated yet. Current value: " + blockUnblock, Status.INFO, false);
                }
            }


        }

    }


}