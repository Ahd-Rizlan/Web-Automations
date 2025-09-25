package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.CommonUtils;
import utils.constants.*;

import java.util.List;

import static utils.Drivers.*;

public class WebCardDetailedViewPage extends BasePage {

    String customAmountForCurrency, lastFourDigitsCardNumber,cardNumber, accountNumber, expiryDate, status, balance, cardType, closingBalance, credits, debits, openingBalance, dueDate, minimumDue, billingDate, cardNumber1, txnAmount, blockUnblock, originalAmount, postedDate, txnId, txnDate;


    public WebCardDetailedViewPage(WebDriver driver) {
        super(driver);
    }


    public enum ElementType {
        button, label, span, div;
    }

    private static final By imgmyAccount = By.xpath("(//a[@class='NavBar_navlink__CRz3E NavBar_navlinkHover__eiXyp'])[1]");
    private static final By lblLoans = By.xpath("(//a[@class='SubMenu_subMenuItem___oYCo'])[3]");
    private static final By btnSettlement = By.xpath("//button[contains(@class, 'font-semibold')]");
    private static final By btnSettlementTopUpSection = By.xpath("(//button[contains(@class, 'font-semibold')])[2]");
    private static final By txtCustomAmount = By.xpath("//input[contains(@inputmode, 'numeric')]");
    private static final By ddFromAccount = By.xpath("//select[contains(@id, 'accountfrom')]");
    private static final By btnConfirm = By.xpath("(//button[contains(@class, 'flex items-center justify')])[2]");
    private static final By tblSettlement = By.xpath("//table[contains(@class, 'min-w-full')]//tbody/tr");
    private static final By tblRecentTransactions = By.xpath("//table[@class='w-full text-left whitespace-nowrap']//tbody/tr[count(td)=4]");
    private static final By lblTransactionDate = By.xpath("(//table[contains(@class, 'min-w-full')]//thead/tr[1]/th)[3]");
    private static final By lblBalance = By.xpath(".//div[contains(@class,'rounded-xl')]//div[@class='text-2xl']//span");
    private static final By lblAccontNumber = By.xpath(".//div[contains(@class,'rounded-xl')]//div[contains(@class,'text-base')]//span");
    private static final By lblStatus = By.xpath(".//div[contains(@class,'rounded-xl')]//div[contains(@class,'w-[60px]')]");
    private static final By imgCvc = By.xpath("(//img[@class='object-contain p-0.5'])[3]");
    private static final By lblCvc = By.xpath("(//input[@type='text' and @value])[3]");
    private static final By lblCvcExpireDate = By.xpath("(//input[@type='text' and @value])[2]");
    private static final By lblCvcCardNumber = By.xpath("(//input[@type='text' and @value])[1]");
    private static final By btnSettlementTable = By.xpath("(//button[contains(@class, 'cursor-pointer') and contains(@class, 'transition-colors')])[2]");
    private static final By lblResidence = By.xpath("//div[contains(@class,'flex justify-center')]");
    private static final By rdoResidence = By.xpath("(//input[contains(@type,'radio')])[1]");
    private static final By lblWebInfo = By.xpath("//ul[contains(@class,'flex flex-col gap-1 text-justify')]");
    private static final By ddFromAccountApply = By.xpath("//select[contains(@id,'accountfrom')]");
    private static final By rdUSDagreement = By.xpath("//input[contains(@class, 'peer') and @type='checkbox']");
    private static final By lblApproximateAmount = By.xpath("//span[contains(@class, 'text-gray-800')]");
    private static final By lblAcknowldgementMsg = By.xpath("//span[contains(@class, 'font-medium text')]");
    private static final By imgBlockAccount = By.xpath("//img[contains(@src, 'BlockIcon.03e9cc2b.png')]");
    private static final By imgActivation = By.xpath("//img[contains(@src, 'TempBlockCard2')]");
    private static final By lblAccountListLoading = By.xpath("//div[contains(@class,'dark:bg-gray')]");
    private static final By lblLoadingIcon = By.xpath("//div[contains(@class,'AccountsCards_loader')]");
    private static final By btnAccount = By.xpath("//div[contains(@class,' AccountsCards')]//div[contains(text(),'Accounts')]");
    private static final By btnWebCard = By.xpath("//div[contains(@class,' AccountsCards')]//div[contains(text(),'Web Card')]");
    private static final By lblBlockandUnblockAccount = By.xpath("(//span[contains(@class, 'text-center')])[1]");
    private static final By imgUNBlockAccount = By.xpath("//img[contains(@src, 'card-unlock.2ff072fe.png')]");
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

    public static By getDivValueAfterLabel(String labelText) {
        return By.xpath("//div[normalize-space(text())='" + labelText + "']/following-sibling::div[1]");
    }

    private static By getElementByTypeAndText(WebCardDetailedViewPage.ElementType type, String text, int index) {
        return By.xpath("(//" + type.name() + "[contains(normalize-space(.), \"" + text + "\")])[" + index + "]");
    }


    /**
     * This method will navigate to the web card details section from Dash Board section and validate web card info
     *
     * @param residenceLable  - Residence verification message
     * @param expectedInfoRaw - Web card conditions
     */

    public void NavogatetoWebCardDetailsPageFromDashBoard(String residenceLable, String expectedInfoRaw) {

        addToReport("----------Start Navigating to the web card section from DashBoard ----------", Status.INFO, false);

        waitForElementPresence(lblResidence, MODERATE_WAIT);
        String residenceText = getTextFromElement(lblResidence).trim();
        if (residenceLable.equalsIgnoreCase(residenceText)) {
            addToReport("Residence Label Present on web Card ", Status.PASS, false);
        } else {
            addToReport("Residence Label Not Present on web Card " + residenceText, Status.FAIL);
        }
        clickOnElement(rdoResidence);
        addToReport("Clicked on the web Card Radio button", Status.PASS);
        clickOnElement(getElementByTypeAndText(ElementType.button, CreditCardConstants.NEXT, 1));
        waitForElementPresence(ddFromAccountApply, LONG_WAIT);
        String expectedInfo = expectedInfoRaw.replaceAll("\\\\n", "\n");
        String actualInfo = getTextFromElement(lblWebInfo).trim();
        if (actualInfo.equalsIgnoreCase(expectedInfo)) {
            addToReport("Web card info block matched successfully.", Status.PASS);
        } else {
            addToReport("Web card info mismatch.\nExpected: " + expectedInfo + "\nActual: " + actualInfo, Status.FAIL);
        }
        clickOnElement(getElementByTypeAndText(ElementType.button, CreditCardConstants.NEXT, 1));
        waitForElementPresence((getElementByTypeAndText(ElementType.button, CreditCardConstants.AGREEMENT, 1)),LONG_WAIT);
        clickOnElement(getElementByTypeAndText(ElementType.button, CreditCardConstants.AGREEMENT, 1));


        clickOnElement(getElementByTypeAndText(ElementType.button, CreditCardConstants.BACK, 2));
        waitForElementPresence(rdUSDagreement, LONG_WAIT);
        clickOnElement(rdUSDagreement);

        addToReport("----------END validation of Web card INFO ----------", Status.INFO,true);
    }


    /**
     * This method will navigate to the web card details section from my Account section
     */
    public void NavogatetoWebCardDetailsPage() {

        addToReport("----------Start Navigating to the web card section ----------", Status.INFO, false);

        waitForElementPresence(imgmyAccount);
        hoverOverElement(imgmyAccount);
        addToReport("Hover on the My accounts tab ", Status.PASS, false);

        waitForElementPresence(lblLoans);
        clickOnElement(lblLoans);
        addToReport("Clicked on the web Card tab ", Status.PASS);

        if (waitForElementPresence(lblTransactionDate, PawnConstants.WAIT_EXTREME_LONG)) {
            addToReport(" web card details are visible.", Status.PASS);
        } else {
            addToReport(" web card details are not visible.", Status.INFO);
        }

        addToReport("----------End Navigated to the  web card section ----------", Status.INFO, false);

    }

    /**
     * This method will validate the web card details
     */

    public void validateCardDetails() {

        addToReport("---------- Start the Web card details and transaction table validation   ----------", Status.INFO, false);

        cardNumber = getTextFromElement(getCardDetailByLabel(CreditCardConstants.CARD_NUMBER_LABEL)).trim();
        lastFourDigitsCardNumber = cardNumber.trim().split(" ")[cardNumber.trim().split(" ").length - 1];
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
            if (recentTransactionAmount.contains(DashboardConstants.CURRENCY_VALUES[0]) && CommonUtils.containsAlphAndNumCharacters(recentTransactionAmount)) {
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

        addToReport("---------- End the Web card details and transaction table validation   ----------", Status.INFO, false);
    }

    /**
     * Validate the billing settlement
     */
    public void validateBillingSettlementSection() {

        addToReport("---------- Start the Web card settlement and table section  ----------", Status.INFO, false);

        clickOnElement(btnSettlementTable);
        waitForElementPresence(tblSettlement, PawnConstants.WAIT_EXTRA_LONG);

        billingDate = getTextFromElement(getDivValueAfterLabel("Billing Date")).trim();
        if (CommonUtils.containsValuesOnDateYearFirst(billingDate)) {
            addToReport("Billing Date format is valid: " + billingDate, Status.PASS, false);
        } else {
            addToReport("Invalid Billing Date format: " + billingDate, Status.FAIL);
        }

        minimumDue = getTextFromElement(getDivValueAfterLabel("Minimum Due")).trim();
        if (CommonUtils.containsAlphAndNumCharacters(minimumDue)) {
            addToReport("Minimum Due is valid: " + minimumDue, Status.PASS, false);
        } else {
            addToReport("Minimum Due format issue: " + minimumDue, Status.FAIL);
        }

        dueDate = getTextFromElement(getDivValueAfterLabel("Due Date")).trim();
        if (CommonUtils.containsValuesOnDateYearFirst(dueDate)) {
            addToReport("Due Date format is valid: " + dueDate, Status.PASS, false);
        } else {
            addToReport("Invalid Due Date format: " + dueDate, Status.FAIL);
        }
        // Opening Balance
        openingBalance = getTextFromElement(getDivValueAfterLabel("Opening Balance")).trim();
        if (CommonUtils.containsAlphAndNumCharacters(openingBalance)) {
            addToReport("Opening Balance is valid: " + openingBalance, Status.PASS, false);
        } else {
            addToReport("Opening Balance format issue: " + openingBalance, Status.FAIL);
        }

        // Debits
        debits = getTextFromElement(getDivValueAfterLabel("Debits")).trim();
        if (CommonUtils.containsAlphAndNumCharacters(debits)) {
            addToReport("Debits amount is valid: " + debits, Status.PASS, false);
        } else {
            addToReport("Debits format issue: " + debits, Status.FAIL);
        }

        // Credits
        credits = getTextFromElement(getDivValueAfterLabel("Credits")).trim();
        if (CommonUtils.containsAlphAndNumCharacters(credits)) {
            addToReport("Credits amount is valid: " + credits, Status.PASS, false);
        } else {
            addToReport("Credits format issue: " + credits, Status.FAIL);
        }

        // Closing Balance
        closingBalance = getTextFromElement(getDivValueAfterLabel("Closing Balance")).trim();
        if (CommonUtils.containsAlphAndNumCharacters(closingBalance)) {
            addToReport("Closing Balance is valid: " + closingBalance, Status.PASS, false);
        } else {
            addToReport("Closing Balance format issue: " + closingBalance, Status.FAIL);
        }

        List<WebElement> rows = driver.findElements(tblSettlement);
        boolean allValid = true;

        for (int i = 1; i < rows.size(); i++) {
            int rowIndex = i + 1; // +1 to skip the masked card number row

            cardNumber1 = getTextFromElement(tblTransactionCell(rowIndex, 1)).trim();
            if (CommonUtils.containsMaskedCardFormat(cardNumber1)) {
                addToReport("Row " + i + ": Card Number valid: " + cardNumber1, Status.PASS, false);
            } else {
                addToReport("Row " + i + ": Invalid Card Number: " + cardNumber1, Status.FAIL);
                allValid = false;
            }

            txnAmount = getTextFromElement(tblTransactionCell(rowIndex, 2)).trim();
            if (CommonUtils.containsAlphAndNumCharacters(txnAmount)) {
                addToReport("Row " + i + ": Transaction Amount valid: " + txnAmount, Status.PASS, false);
            } else {
                addToReport("Row " + i + ": Invalid Transaction Amount: " + txnAmount, Status.FAIL);
                allValid = false;
            }

            originalAmount = getTextFromElement(tblTransactionCell(rowIndex, 3)).trim();
            if (CommonUtils.containsAlphAndNumCharacters(originalAmount)) {
                addToReport("Row " + i + ": Original Amount valid: " + originalAmount, Status.PASS, false);
            } else {
                addToReport("Row " + i + ": Invalid Original Amount: " + originalAmount, Status.FAIL);
                allValid = false;
            }

            postedDate = getTextFromElement(tblTransactionCell(rowIndex, 4)).trim();
            if (CommonUtils.containsValuesOnDateYearFirst(postedDate)) {
                addToReport("Row " + i + ": Posted Date valid: " + postedDate, Status.PASS, false);
            } else {
                addToReport("Row " + i + ": Invalid Posted Date: " + postedDate, Status.FAIL);
                allValid = false;
            }

            txnId = getTextFromElement(tblTransactionCell(rowIndex, 5)).trim();
            if (CommonUtils.containsNumericCharacters(txnId)) {
                addToReport("Row " + i + ": Transaction ID valid: " + txnId, Status.PASS, false);
            } else {
                addToReport("Row " + i + ": Invalid Transaction ID: " + txnId, Status.FAIL);
                allValid = false;
            }

            txnDate = getTextFromElement(tblTransactionCell(rowIndex, 6)).trim();
            if (CommonUtils.containsValuesOnDateYearFirst(txnDate)) {
                addToReport("Row " + i + ": Transaction Date valid: " + txnDate, Status.PASS, false);
            } else {
                addToReport("Row " + i + ": Invalid Transaction Date: " + txnDate, Status.FAIL);
                allValid = false;
            }
        }

        if (allValid) {
            addToReport("All transaction table rows validated successfully.", Status.PASS, false);
        }

        addToReport("---------- End the Web card settlement and table section  ----------", Status.INFO, false);
    }


    /**
     * This method will top up the web card
     *
     * @param payingAccountNumber - Account number
     * @param paymentAmount-      Amount
     */

    public void validateWebCardTopUp(String payingAccountNumber, String paymentAmount) {

        addToReport("---------- Start the Web card Top up  ----------", Status.INFO, false);

        if (isElementPresentBy(btnSettlement)) {
            addToReport("Top up button available", Status.PASS, false);
            clickOnElement(btnSettlement);
        } else {
            addToReport("Top up button not available", Status.FAIL, false);
        }

        waitForElementPresence(ddFromAccount, PawnConstants.WAIT_EXTRA_LONG);
        addToReport("Top up Amount section is visible ", Status.PASS, false);

        if (isElementPresentBy(ddFromAccount)) {
            addToReport("From Account DropDown  available", Status.PASS, false);
            selectFromDropdown(ddFromAccount, payingAccountNumber, "value");
            addToReport("'" + payingAccountNumber + "'selected from the dropdown.", Status.PASS);
        } else {
            addToReport("DropDown is not available", Status.FAIL, false);
        }

        sendKeysToElement(txtCustomAmount, paymentAmount);
        addToReport("Added " + paymentAmount + " as the top up amount", Status.PASS);
        customAmountForCurrency = getAttributeOrText(txtCustomAmount, "value").trim().split(" ")[0];

        clickOnElement(btnSettlementTopUpSection);
        addToReport("clicked on the confirm button", Status.PASS);
        addToReport("---------- End the Web card Top up  ----------", Status.INFO, false);
    }

    /**
     * This method is entering the OTP to navigates and validates the success message
     *
     * @param otp                  - OTP
     * @param successMsg           - success message
     * @param acknowlagmentmessage - FC acknowledgment
     * @param successMsgPayment    - Success message for payments
     */
    public void enterOTPAndContinueWebCardPage(String successMsg, String otp, String acknowlagmentmessage, String successMsgPayment) {

        addToReport("---------- Starting to validate Web card acknowledgment USD message and enter OTP to continue  ----------", Status.INFO, false);
        //Enter OTP values and continue
        try {
            waitForElementPresence(getSuccessfulMsg(successMsg),LONG_WAIT); //Request successful
            //Validate the error message
            if (isElementPresentBy(getSuccessfulMsg(successMsg))) {
                addToReport("'" + successMsg + "' message is present.", Status.PASS, true);
                isElementClickable(btnClosePopup);
            } else {
                addToReport("'" + successMsg + "'  message is not present.", Status.FAIL);
                throw new RuntimeException("Error message validation is unsuccessful.");
            }
            sendKeysToElement(tfOTP(1), String.valueOf(otp));
            if (customAmountForCurrency.equalsIgnoreCase(DashboardConstants.CURRENCY_VALUES[3])) {
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
                addToReport("Click on the FC account agreement radio button", Status.PASS, false);
            }
            clickOnElement(getElementByTypeAndText(ElementType.button, MessagingConstants.CONFIRM, 1));
        } catch (Exception e) {
            addToReport("Error when entering OTP", Status.FAIL);
            throw new RuntimeException("Failed to enter OTP " + e.getMessage(), e);
        }

        waitForElementPresence(getSuccessfulMsg(successMsgPayment),LONG_WAIT); //Request successful
        //Validate the error message
        if (isElementPresentBy(getSuccessfulMsg(successMsgPayment))) {
            addToReport("'" + successMsgPayment + "' message is present.", Status.PASS, true);
            isElementClickable(btnClosePopup);
        } else {
            addToReport("'" + successMsgPayment + "'  message is not present.", Status.FAIL);
            throw new RuntimeException("Error message validation is unsuccessful.");
        }
        clickOnElement(getElementByTypeAndText(ElementType.button, CreditCardConstants.CLOSE, 1));
        addToReport("---------- End to validate Web card acknowledgment USD message and enter OTP to continue  ----------", Status.INFO, false);
    }

    /**
     * This method is entering the OTP to navigates and validates the CVV
     *
     * @param otp           - OTP
     * @param successMsgCCV - success message
     */
    public void validateCVV(String otp, String successMsgCCV) {

        addToReport("---------- Starting to validate the CCV  ----------", Status.INFO, false);

        clickOnElement(imgCvc);

        waitForElementPresence(getSuccessfulMsg(successMsgCCV),LONG_WAIT); //both send
        //Validate the error message
        if (isElementPresentBy(getSuccessfulMsg(successMsgCCV))) {
            addToReport("'" + successMsgCCV + "' message is present.", Status.PASS, true);
            isElementClickable(btnClosePopup);
        } else {
            addToReport("'" + successMsgCCV + "'  message is not present.", Status.FAIL);
            throw new RuntimeException("Error message validation is unsuccessful.");
        }

        try {
            sendKeysToElement(tfOTP(1), String.valueOf(otp));

            clickOnElement(btnConfirm);
        } catch (Exception e) {
            addToReport("Error when entering OTP", Status.FAIL);
            throw new RuntimeException("Failed to enter OTP " + e.getMessage(), e);
        }

        waitForElementPresence(lblCvc, LONG_WAIT);
        String cvv = getAttributeOrText(lblCvc, "value").trim();
        String cvvExpireDate = getAttributeOrText(lblCvcExpireDate,"value").trim();
        String cvvCardNumber = getAttributeOrText(lblCvcCardNumber,"value").trim();
        cvvCardNumber = cvvCardNumber.substring(cvvCardNumber.length() - 4);

        if (cvv.matches("\\d{3}")) {
            addToReport("Input value is a valid 3-digit number: " + cvv, Status.PASS, false);
        } else {
            addToReport("Invalid input value (expected 3-digit number): " + cvv, Status.FAIL);
        }

        if (cvvExpireDate.equalsIgnoreCase(expiryDate)) {
            addToReport("CVV section Expire Date equal with the cards: " + cvvExpireDate, Status.PASS, false);
        } else {
            addToReport("CVV section Expire Date is not equal with the cards: " + cvvExpireDate, Status.FAIL);
        }

        if (cvvCardNumber.equalsIgnoreCase(lastFourDigitsCardNumber)) {
            addToReport("CVV section Card number last four digits equal with the cards: " + cvvCardNumber, Status.PASS, true);
        } else {
            addToReport("CVV section Card number last four digits is not equal with the cards: " + cvvCardNumber, Status.FAIL);
        }

        clickOnElement(getElementByTypeAndText(ElementType.button, CreditCardConstants.CLOSE_CARD_VIEW, 1));

        addToReport("---------- End to validate the CCV  ----------", Status.INFO, false);
    }

    /**
     * Validate th Web Card blocking
     *
     * @param otp                   - OTP
     * @param successMsgForBlocking - Blocking success message
     * @param emailSentSuccessMsg   - Request success message
     */
    public void ValidateCardBlockUnblock(String otp, String successMsgForBlocking, String emailSentSuccessMsg) {

        addToReport("---------- Starting to validate the Web Card blocking ----------", Status.INFO, false);

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
            addToReport("'" + successMsgForBlocking + "' message is present.", Status.PASS);
            isElementClickable(btnClosePopup);
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
            clickOnElement(btnWebCard);
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
            isElementClickable(btnClosePopup);
            try {
                sendKeysToElement(tfOTP(1), String.valueOf(otp));

                clickOnElement(getElementByTypeAndText(ElementType.button, CreditCardConstants.CONFIRM, 1));
            } catch (Exception e) {
                addToReport("Error when entering OTP", Status.FAIL);
                throw new RuntimeException("Failed to enter OTP " + e.getMessage(), e);
            }

            waitForElementPresence(getSuccessfulMsg(successMsgForBlocking), LONG_WAIT); //Request successful
            //Validate the error message
            if (isElementPresentBy(getSuccessfulMsg(successMsgForBlocking))) {
                addToReport("'" + successMsgForBlocking + "' message is present.", Status.PASS, true);
                isElementClickable(btnClosePopup);
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
                clickOnElement(btnWebCard);
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
        addToReport("---------- End to validate the Web Card blocking ----------", Status.INFO, false);
    }


}
