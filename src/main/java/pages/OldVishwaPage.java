/*
 *   @author - salman R
 */
package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.constants.MessagingConstants;
import utils.constants.OldVishwaConstants;

import java.text.SimpleDateFormat;
import java.util.*;

public class OldVishwaPage extends BasePage {

    String dateText;
    Date cutoffDate,messageDate;
    SimpleDateFormat sdf;
    boolean allValid = true;

    public OldVishwaPage(WebDriver driver) {
        super(driver);
    }

    private static final By imgGreyLoader = By.xpath("//div[contains(@class,'bg-gray')]");
    private static final By ddSubject = By.xpath("//select[@id='subject']");
    private static final By ddSubCategory = By.xpath("//select[@id='subjectSubCategory']");
    private static final By ddFromAccount = By.xpath("//select[@id='fromAccount']");
    private static final By ddAccountNumber = By.xpath("//select[@id='accountNo']");
    private static final By ddBranchList = By.xpath("//select[@id='branchList']");
    private static final By ddPurpose = By.xpath("//select[@id='purpose']");
    private static final By lnkUploadAttachement = By.xpath("//input[@type='file']");
    private static final By lblMsg = By.xpath(".//div[contains(text(), 'Last modified on')]");
    private static final By allMessages = By.cssSelector("div.LeftInboxContainer_scroll__BKnAZ > div");
    private static final By lblMsgID = By.xpath("//span[text()='Fixed Deposit Inquiry']/parent::div/span[2]");
    private static final By btnClosePopup = By.xpath("//button[contains(@aria-label,'close')]");
    private static final By ddBank = By.xpath("//select[@id='bank']");
    private static final By inputHiddenFile = By.xpath("//input[@type='file' and contains(@accept, '.pdf')]");
    private static final By inputFileText = By.xpath("//input[@placeholder='Select Attachments']");
    private static final By lblMessageList = By.xpath("//div[contains(@class,'flex justify-between items-center rounded')]");
    private static final By lblLastModifiedDate = By.xpath("//div[contains(@class,'flex justify-between items-center rounded')]/div[2]/div[3]");
    private static final By icnRecivedMessage = By.xpath("//*[name()='svg' and contains(@class, 'lucide-mail-plus')]");
    private static final By lblMessageContent = By.xpath("//div[contains(@class,'flex flex')]//p");
    private static final By lblDraftMsgDate = By.xpath("//div[text()='"+MessagingConstants.DRAFT+"']/ancestor::div[4]//span[contains(text(), '-') and contains(text(), ':')]");
    private static final By lblDraftMsg = By.xpath("//div[text()='"+MessagingConstants.DRAFT+"']/ancestor::div[1]/div[1]");
    private static final By lblUploadedDoc = By.xpath("//span[contains(@class, 'text-sm')]");
    private static final By lblMessages = By.xpath("//div[contains(@class,'max-md')][2]//div[contains(@class,'whitespace-pre-wrap')]");
    private static final By tblTransactionRows = By.xpath("//table[contains(@class,'min-w-full')]//tbody//tr");
    private static final By lblAccountListLoading = By.xpath("//div[contains(@class,'dark:bg-gray')]");
    public enum ElementType {
        button, label, span, div, textarea, input;
    }

    private static By getHeaderByName(String headerText) {
        return By.xpath("//table[contains(@class,'min-w-full')]//th[normalize-space(text())='" + headerText + "']");
    }
    private static By getMsg(String title) {
        return By.xpath("//div[contains(text(),'" + title + "')]");
    }
    private static By lblMessages(int index) {
        return By.xpath("(//div[contains(@class,'max-md')][2]//div[contains(@class,'whitespace-pre-wrap')])[" +index +"]");
    }

    private static By icnReadMessageRecord(int index) {
        return By.xpath("(//*[name()='svg' and contains(@class, 'lucide-mail-open')])["+index+"]");
    }
    private static By btnDeleteMessage(int index) {
        return By.xpath("(//img[contains(@src, 'media/Bin')])["+index+"]");
    }
    private static By btnRecoverMessage(int index) {
        return By.xpath("(//img[contains(@src, 'FRecoverIcon')])["+index+"]");
    }

    private static By lblDeletedMessage(int index) {
        return By.xpath("(//*[name()='svg' and contains(@class, 'lucide lucide-mail-x')])["+index+"]");
    }

    private static By tfOTP(int Index) {
        return By.xpath("//input[contains(@class,'otp-box')][" + Index + "]");
    }

    private static By lblMsgSubject(String subject) {
        return By.xpath("(//span[text()='" + subject + "'])[1]");
    }


    private static By lblResponseDate(String responseMessage) {
        return By.xpath("//p[text()='"+responseMessage+"']/ancestor::div[4]//span[contains(text(), '-') and contains(text(), ':')]");
    }

    private static By rdoDate(String value) {
        return By.xpath("//input[@value=\"" + value + "\"]");
    }

    private static By dpPickDate(String text) {
        return By.xpath("//span[text()=\"" + text + "\"]/parent::div/input");
    }

    private static By getElementByTypeAndText(ElementType type, String text) {
        return By.xpath("//" + type.name() + "[contains(normalize-space(text()), \"" + text + "\")]");
    }
    private static By getElementByTypeAndTextSecondIndex(ElementType type, String text) {
        return By.xpath("(//" + type.name() + "[contains(normalize-space(text()), \"" + text + "\")])[2]");
    }

    private static By getElementByPlaceholder(ElementType type, String text) {
        return By.xpath("//" + type.name() + "[@placeholder= \"" + text + "\"]");
    }
    private static By getElementByValue(ElementType type, String text) {
        return By.xpath("//" + type.name() + "[@value= \"" + text + "\"]");
    }
    private static By tblCellRecord(int col, int row) {
        return By.xpath("(//table//tr/td[" + col + "])[" + row + "]");
    }
    private static By tblCellAction(int col, int row) {
        return By.xpath("(//table//tr/td[" + col + "])[" + row + "]//img");
    }


    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }




    public void draftMessage() {

        try {
        } catch (Exception e) {
            addToReport("Error logging into retail admin", Status.FAIL);
            throw new RuntimeException("Failed log into retail admin" + e.getMessage(), e);
        }
    }

    public static List<String> findMissingElements(List<String> list1, List<String> list2) {
        List<String> missingElements = new ArrayList<>(list1);
        missingElements.removeAll(list2);
        return missingElements;
    }

    /**
     * Returns a standard date formatter for dd/MM/yyyy pattern
     */
    public static SimpleDateFormat getDefaultDateFormatter() {
        return new SimpleDateFormat("dd/MM/yyyy");
    }

    /**
     * navigate To Old Vishwa msg And Validate Inbox And Sent Msg
     *
     */
    public void navigateToOldVishwaMailAndValidateInboxAndSentMsg() {
        addToReport("----------Start of validation of the content of Old Vishwa Inbox----------", Status.PASS, false);
        //Click on old vishwa button
        clickOnElement(getElementByTypeAndText(ElementType.button, OldVishwaConstants.KEYWORD_OLD_VISHWA));

        waitForElementToBeClickable(getElementByTypeAndText(ElementType.div, OldVishwaConstants.KEYWORD_OLD_VISHWA_INBOX), 30);
        clickOnElement(getElementByTypeAndText(ElementType.div, OldVishwaConstants.KEYWORD_OLD_VISHWA_INBOX));

        //Validate table headers for inbox
        for (String header : OldVishwaConstants.OLD_VISHWA_INBOX.values()) {
            if (isElementPresentBy(getHeaderByName(header))) {
                addToReport("Header '" + header + "' is present in Old Vishwa Inbox", Status.PASS, false);
            } else {
                addToReport("Header '" + header + "' is missing in Old Vishwa Inbox", Status.FAIL, true);
            }
        }

        //Validate table data
        int rowCount = isElementsPresentBy(tblTransactionRows);
        if (rowCount > 0) {

            addToReport("Inbox table loaded with " + rowCount + " messages.", Status.PASS, false);

            // Use refactored methods
            cutoffDate = getCutoffDate(24);
//            sdf = getDateFormatter();

            for (int row = 1; row <= rowCount; row++) {
                try {
                    //Validate Message Subject (allows text and special characters like '/')
                    String subject = getTextFromElement(tblCellRecord(1, row)).trim();
                    if (subject.isEmpty()) {
                        addToReport(" Message Subject is empty at row " + row, Status.FAIL, true);
                        allValid = false;
                    } else if (!subject.matches(".*[a-zA-Z0-9/\\s]+.*")) {
                        addToReport(" Invalid Message Subject format at row " + row + ": " + subject, Status.FAIL, true);
                        allValid = false;
                    } else {
                        addToReport(" Message Subject is valid at row " + row + ": " + subject, Status.PASS, false);
                    }

                    //Validate Message ID (should be numeric only)
                    String messageId = getTextFromElement(tblCellRecord(2, row)).trim();
                    if (messageId.isEmpty()) {
                        addToReport(" Message ID is empty at row " + row, Status.FAIL, true);
                        allValid = false;
                    } else if (!messageId.matches("\\d+")) {
                        addToReport(" Message ID must be numeric at row " + row + ": " + messageId, Status.FAIL, true);
                        allValid = false;
                    } else {
                        addToReport(" Message ID is numeric at row " + row + ": " + messageId, Status.PASS, false);
                    }

                    //Validate Date (within 24 months)
                     dateText = getTextFromElement(tblCellRecord(3, row)).trim(); // Column 3 = Date
                     sdf = new SimpleDateFormat("dd/MM/yyyy");
                     messageDate = sdf.parse(dateText);

                    if (messageDate.before(cutoffDate)) {
                        addToReport(" Message at row " + row + " is older than 24 months: " + dateText, Status.FAIL, true);
                        allValid = false;
                    } else {
                        addToReport(" Message at row " + row + " is within 24 months: " + dateText, Status.PASS, false);
                    }

                    // Validate Email From (text with special characters like @, . etc.)
                    String emailFrom = getTextFromElement(tblCellRecord(4, row)).trim();
                    if (emailFrom.isEmpty()) {
                        addToReport(" Email From is empty at row " + row, Status.FAIL, true);
                        allValid = false;
                    } else if (!emailFrom.matches("[a-zA-Z0-9@._\\-\\s]+")) {
                        addToReport(" Email From contains invalid characters at row " + row + ": " + emailFrom, Status.FAIL, true);
                        allValid = false;
                    } else {
                        addToReport(" Email From is valid at row " + row + ": " + emailFrom, Status.PASS, false);
                    }

                } catch (Exception e) {
                    addToReport(" Failed to validate value at row " + row + ". Raw value: " + getTextFromElement(tblCellRecord(3, row)), Status.FAIL, true);
                    allValid = false;
                }

            }
            if (allValid) {
                addToReport("All messages in the Old Vishwa Inbox are within the last 24 months.", Status.PASS, false);
            } else {
                addToReport("Some messages are older than 24 months or had invalid dates.", Status.FAIL, true);
            }

        } else {
            addToReport("Inbox table displayed but no rows found.", Status.FAIL, true);
        }

        addToReport("----------End of validation of the content of Old Vishwa Inbox----------", Status.PASS, true);
        addToReport("----------Start of validation of the content of Old Vishwa sent messages----------", Status.PASS, true);
        //Validate sent  msg
        clickOnElement(getElementByTypeAndText(ElementType.div, OldVishwaConstants.KEYWORD_OLD_VISHWA_SENT));

        //Validate table headers for inbox
        for (String header : OldVishwaConstants.OLD_VISHWA_SENT.values()) {
            if (isElementPresentBy(getHeaderByName(header))) {
                addToReport("Header '" + header + "' is present in Old Vishwa Inbox", Status.PASS, false);
            } else {
                addToReport("Header '" + header + "' is missing in Old Vishwa Inbox", Status.FAIL, true);
            }
        }

        //Validate table data
       rowCount = isElementsPresentBy(tblTransactionRows);
        if (rowCount > 0) {

            addToReport("Sent table loaded with " + rowCount + " messages.", Status.PASS, false);

            // Use refactored methods
            cutoffDate = getCutoffDate(24);

            for (int row = 1; row <= rowCount; row++) {
                try {
                    //Validate Message Subject (allows text and special characters like '/')
                    String subject = getTextFromElement(tblCellRecord(1, row)).trim();
                    if (subject.isEmpty()) {
                        addToReport(" Message Subject is empty at row " + row, Status.FAIL, true);
                        allValid = false;
                    } else if (!subject.matches(".*[a-zA-Z0-9/\\s]+.*")) {
                        addToReport(" Invalid Message Subject format at row " + row + ": " + subject, Status.FAIL, true);
                        allValid = false;
                    } else {
                        addToReport(" Message Subject is valid at row " + row + ": " + subject, Status.PASS, false);
                    }

                    //Validate Message ID (should be numeric only)
                    String messageId = getTextFromElement(tblCellRecord(2, row)).trim();
                    if (messageId.isEmpty()) {
                        addToReport(" Message ID is empty at row " + row, Status.FAIL, true);
                        allValid = false;
                    } else if (!messageId.matches("\\d+")) {
                        addToReport(" Message ID must be numeric at row " + row + ": " + messageId, Status.FAIL, true);
                        allValid = false;
                    } else {
                        addToReport(" Message ID is numeric at row " + row + ": " + messageId, Status.PASS, false);
                    }

                    //Validate Date (within 24 months)
                    dateText = getTextFromElement(tblCellRecord(3, row)).trim(); // Column 3 = Date
                    sdf = new SimpleDateFormat("dd/MM/yyyy");
                    messageDate = sdf.parse(dateText);

                    if (messageDate.before(cutoffDate)) {
                        addToReport(" Message at row " + row + " is older than 24 months: " + dateText, Status.FAIL, true);
                        allValid = false;
                    } else {
                        addToReport(" Message at row " + row + " is within 24 months: " + dateText, Status.PASS, false);
                    }

                    // Validate Email From (text with special characters like @, . etc.)
                    String emailFrom = getTextFromElement(tblCellRecord(4, row)).trim();
                    if (emailFrom.isEmpty()) {
                        addToReport(" Email From is empty at row " + row, Status.FAIL, true);
                        allValid = false;
                    } else if (!emailFrom.matches("[a-zA-Z0-9@._\\-\\s]+")) {
                        addToReport(" Email From contains invalid characters at row " + row + ": " + emailFrom, Status.FAIL, true);
                        allValid = false;
                    } else {
                        addToReport(" Email From is valid at row " + row + ": " + emailFrom, Status.PASS, false);
                    }

                } catch (Exception e) {
                    addToReport(" Failed to parse date at row " + row + ". Raw value: " + getTextFromElement(tblCellRecord(3, row)), Status.FAIL, true);
                    allValid = false;
                }

            }
            if (allValid) {
                addToReport("All messages in the Old Vishwa sent messages are within the last 24 months.", Status.PASS, false);
            } else {
                addToReport("Some messages are older than 24 months or had invalid dates.", Status.FAIL, true);
            }

        } else {
            addToReport("Sent messages table displayed but no rows found.", Status.FAIL, true);
        }

        addToReport("----------End of validation of the content of Old Vishwa sent messages----------", Status.PASS, true);

    }

    public void navigateToOldVishwaMailAndValidateInboxMessage(String subject) {
        addToReport("----------Start of validation of the content of Old Vishwa Inbox Message----------", Status.PASS, false);

        // Click on Old Vishwa button
        clickOnElement(getElementByTypeAndText(ElementType.button, OldVishwaConstants.KEYWORD_OLD_VISHWA));

        waitForElementToBeClickable(getElementByTypeAndText(ElementType.div, OldVishwaConstants.KEYWORD_OLD_VISHWA_INBOX), 30);
        clickOnElement(getElementByTypeAndText(ElementType.div, OldVishwaConstants.KEYWORD_OLD_VISHWA_INBOX));

        // Validate table data
        int rowCount = isElementsPresentBy(tblTransactionRows);
        if (rowCount > 0) {
            addToReport("Inbox table loaded with " + rowCount + " messages.", Status.PASS, false);

            clickOnElement(tblCellAction(5, 1)); // Click on action for row 1

            waitForElementPresence(getElementByPlaceholder(ElementType.input, MessagingConstants.SEARCH_MESSAGES));
            sendKeysToElement(getElementByPlaceholder(ElementType.input, MessagingConstants.SEARCH_MESSAGES), subject);

            scrollPageToTop();
            waitForElementPresence(lblMsgSubject(subject));

            String messageIdText = getTextFromElement(lblMsgSubject(subject)).replaceAll("[^0-9]", "");
            if (messageIdText.isEmpty()) {
                addToReport("Error fetching subject header id", Status.FAIL);
                throw new RuntimeException("Failed to fetch message id after sending message");
            } else {
                addToReport("Loaded message subject: " + messageIdText, Status.PASS, true);
            }

            // Validate loaded messages
            rowCount = isElementsPresentBy(lblMessages);
            if (rowCount > 0) {
                addToReport("Total messages loaded: " + rowCount, Status.PASS, false);

                for (int i = 1; i <= rowCount; i++) {
                    String messageContent = getTextFromElement(lblMessages(i)).trim();

                    if (messageContent.isEmpty()) {
                        addToReport(" Message #" + i + " is empty or unreadable.", Status.FAIL, true);
                    } else if (!messageContent.matches("[\\s\\S]*[a-zA-Z0-9]+[\\s\\S]*")) {
                        addToReport(" Message #" + i + " might be corrupted or lack meaningful content.", Status.FAIL, true);
                    } else {
                        addToReport(" Message #" + i + " is readable: " + messageContent.substring(0, Math.min(100, messageContent.length())) + "...", Status.PASS, false);
                    }
                }

            } else {
                addToReport(" Messages did not load.", Status.FAIL, true);
            }

        } else {
            addToReport(" No messages found in the Inbox.", Status.FAIL, true);
        }

        addToReport("----------End of validation of the content of Old Vishwa sent messages----------", Status.PASS, true);
    }


    /**
     * Returns an object array containing:
     * [0] - Cutoff date (Date) that is X months before current date
     * [1] - SimpleDateFormat for dd/MM/yyyy
     *
     * @param monthsBack number of months to go back
     * @return Object array with Date and SimpleDateFormat
     */
    private Object[] getCutoffDateAndFormatter(int monthsBack) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -monthsBack);
        Date cutoffDate = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return new Object[]{cutoffDate, sdf};
    }





    /**
     * navigate To Old Vishwa Mail And Validate Inbox And Sent Msg
     */
    public void navigateBillersAndValidateOldVishwaTransactions() {
        addToReport("----------Start of validation of the content of Old Vishwa biller content----------", Status.PASS, false);

        waitForElementToBeClickable(getElementByTypeAndText(ElementType.button,OldVishwaConstants.KEYWORD_FILTER),20);
        clickOnElement(getElementByTypeAndText(ElementType.button,OldVishwaConstants.KEYWORD_FILTER));

        waitForElementToBeClickable(getElementByTypeAndText(ElementType.div,OldVishwaConstants.KEYWORD_LOAD_OLD_V_HISTORY),20);
        clickOnElement(getElementByTypeAndText(ElementType.div,OldVishwaConstants.KEYWORD_LOAD_OLD_V_HISTORY));

        waitForElementToBeInvisible(imgGreyLoader,30);

        // Validate headers
        for (String header : OldVishwaConstants.OLD_VISHWA_BILLER_HEADER.values()) {
            if (isElementPresentBy(getHeaderByName(header))) {
                addToReport("Header '" + header + "' is present in Old Vishwa Biller", Status.PASS, false);
            } else {
                addToReport("Header '" + header + "' is missing in Old Vishwa Biller", Status.FAIL, true);
            }
        }

        int rowCount = isElementsPresentBy(tblTransactionRows);

        if (rowCount > 0) {
            addToReport("Table loaded with " + rowCount + " rows.", Status.PASS, false);

            for (int row = 1; row <= rowCount; row++) {
                // Column 1: Payment ID (numeric)
                String paymentId = getTextFromElement(tblCellRecord(1, row)).trim();
                if (paymentId.matches("\\d+")) {
                    addToReport("Payment ID valid: " + paymentId, Status.PASS, false);
                } else {
                    addToReport("Invalid Payment ID at row " + row + ": " + paymentId, Status.FAIL, true);
                }

                // Column 2: Payment Date (dd/MM/yyyy)
                String paymentDate = getTextFromElement(tblCellRecord(2, row)).trim();
                if (paymentDate.matches("\\d{2}/\\d{2}/\\d{4}")) {
                    addToReport("Payment Date valid: " + paymentDate, Status.PASS, false);
                } else {
                    addToReport("Invalid Payment Date at row " + row + ": " + paymentDate, Status.FAIL, true);
                }

                // Column 3: From Account (numeric)
                String fromAccount = getTextFromElement(tblCellRecord(3, row)).trim();
                if (fromAccount.matches("\\d+")) {
                    addToReport("From Account valid: " + fromAccount, Status.PASS, false);
                } else {
                    addToReport("Invalid From Account at row " + row + ": " + fromAccount, Status.FAIL, true);
                }

                // Column 4: Biller Name (text or "-")
                String billerName = getTextFromElement(tblCellRecord(4, row)).trim();
                if (!billerName.isEmpty()) {
                    addToReport("Biller Name valid: " + billerName, Status.PASS, false);
                } else {
                    addToReport("Missing Biller Name at row " + row, Status.FAIL, true);
                }

                // Column 5: Currency (LKR or USD)
                String currency = getTextFromElement(tblCellRecord(5, row)).trim();
                if (currency.equals(OldVishwaConstants.CURRENCY_VALUES[0]) || currency.equals(OldVishwaConstants.CURRENCY_VALUES[1])) {
                    addToReport("Currency valid: " + currency, Status.PASS, false);
                } else {
                    addToReport("Invalid currency at row " + row + ": " + currency, Status.FAIL, true);
                }

                // Column 6: Amount (e.g., LKR 93,225.64 or USD 1,000.00)
                String amount = getTextFromElement(tblCellRecord(6, row)).trim();
                if (amount.matches("(LKR|USD)\\s\\d{1,3}(,\\d{3})*(\\.\\d{2})")) {
                    addToReport("Amount format valid: " + amount, Status.PASS, false);
                } else {
                    addToReport("Invalid amount format at row " + row + ": " + amount, Status.FAIL, true);
                }

                // Column 7: Biller Ref (should be "-")
                String billerRef = getTextFromElement(tblCellRecord(7, row)).trim();
                if (billerRef.equals("-")) {
                    addToReport("Biller Ref valid: " + billerRef, Status.PASS, false);
                } else {
                    addToReport("Unexpected Biller Ref at row " + row + ": " + billerRef, Status.FAIL, true);
                }
            }

        } else {
            addToReport("No rows found in biller transaction table.", Status.FAIL, true);
        }

        addToReport("----------End of validation of the content of Old Vishwa Biller----------", Status.PASS, true);
    }


    /**
     * navigate To Old Vishwa Mail And Validate Inbox And Sent Msg
     */
    public void navigatePayeeAndValidateOldVishwaTransactions() {
        addToReport("----------Start of validation of the content of Old Vishwa payee content----------", Status.PASS, false);

        waitForElementToBeInvisible(imgGreyLoader,50);
        waitForElementToBeInvisible(lblAccountListLoading,50);

        waitForElementToBeClickable(getElementByTypeAndText(ElementType.button,OldVishwaConstants.KEYWORD_FILTER),100);
        clickOnElement(getElementByTypeAndText(ElementType.button,OldVishwaConstants.KEYWORD_FILTER));

        waitForElementToBeClickable(getElementByTypeAndText(ElementType.div,OldVishwaConstants.KEYWORD_LOAD_OLD_V_HISTORY),20);
        clickOnElement(getElementByTypeAndText(ElementType.div,OldVishwaConstants.KEYWORD_LOAD_OLD_V_HISTORY));

        waitForElementToBeInvisible(imgGreyLoader,30);

        //Iterate the tables based on the list of tabs
        for (Map.Entry<String, String> entry : OldVishwaConstants.PAYEE_TYPE_LIST.entrySet()) {

            String x =entry.getValue();

            if (entry.getValue().equals(OldVishwaConstants.MOBILE_CASH))
            {
                //Select the tab
                clickOnElement(getElementByTypeAndTextSecondIndex(ElementType.div,entry.getValue()));
                waitForElementToBeInvisible(imgGreyLoader,30);

            }else {

                //Select the tab
                clickOnElement(getElementByTypeAndText(ElementType.div,entry.getValue()));
                waitForElementToBeInvisible(imgGreyLoader,30);
            }




            // Validate headers
            for (String header : OldVishwaConstants.OLD_VISHWA_TRANSACTION_HISTORY.values()) {
                if (isElementPresentBy(getHeaderByName(header))) {
                    addToReport("Header '" + header + "' is present in Old Vishwa payee", Status.PASS, false);
                } else {
                    addToReport("Header '" + header + "' is missing in Old Vishwa payee", Status.FAIL, true);
                }
            }

            int rowCount = isElementsPresentBy(tblTransactionRows);

            if (rowCount > 0) {
                addToReport("Table loaded with " + rowCount + " rows for "+entry.getValue(), Status.PASS, false);

                for (int row = 1; row <= rowCount; row++) {

                    // Column 1: Payment ID (numeric)
                    String paymentId = getTextFromElement(tblCellRecord(1, row)).trim();
                    if (paymentId.matches("\\d+")) {
                        addToReport("Payment ID valid: " + paymentId, Status.PASS, false);
                    } else {
                        addToReport("Invalid Payment ID at row " + row + ": " + paymentId, Status.FAIL, true);
                    }

                    // Column 2: Payment Date (dd/MM/yyyy)
                    String paymentDate = getTextFromElement(tblCellRecord(2, row)).trim();
                    if (paymentDate.matches("\\d{2}/\\d{2}/\\d{4}")) {
                        addToReport("Payment Date valid: " + paymentDate, Status.PASS, false);
                    } else {
                        addToReport("Invalid Payment Date at row " + row + ": " + paymentDate, Status.FAIL, true);
                    }

                    // Column 3: From Account (numeric)
                    String fromAccount = getTextFromElement(tblCellRecord(3, row)).trim();
                    if (fromAccount.matches("\\d+")) {
                        addToReport("From Account valid: " + fromAccount, Status.PASS, false);
                    } else {
                        addToReport("Invalid From Account at row " + row + ": " + fromAccount, Status.FAIL, true);
                    }

                    // Column 4: To Account (numeric)
                    String toAccount = getTextFromElement(tblCellRecord(4, row)).trim();
                    if (toAccount.matches("\\d+")) {
                        addToReport("To Account valid: " + toAccount, Status.PASS, false);
                    } else {
                        addToReport("Invalid To Account at row " + row + ": " + toAccount, Status.FAIL, true);
                    }

                    // Column 5: Currency (LKR or USD)
                    String currency = getTextFromElement(tblCellRecord(5, row)).trim();
                    if (currency.equals("LKR") || currency.equals("USD")) {
                        addToReport("Currency valid: " + currency, Status.PASS, false);
                    } else {
                        addToReport("Invalid Currency at row " + row + ": " + currency, Status.FAIL, true);
                    }

                    // Column 6: Amount (e.g., LKR 93,225.64)
                    String amount = getTextFromElement(tblCellRecord(6, row)).trim();
                    if (amount.matches("(LKR|USD)\\s\\d{1,3}(,\\d{3})*(\\.\\d{2})")) {
                        addToReport("Amount format valid: " + amount, Status.PASS, false);
                    } else {
                        addToReport("Invalid Amount format at row " + row + ": " + amount, Status.FAIL, true);
                    }

                    // Column 7: Remarks (any non-empty text or dash)
                    String remarks = getTextFromElement(tblCellRecord(7, row)).trim();
                    if (!remarks.isEmpty()) {
                        addToReport("Remarks valid: " + remarks, Status.PASS, false);
                    } else {
                        addToReport("Remarks missing at row " + row, Status.FAIL, true);
                    }

                    // Column 8: Transfer Type (e.g., ONLINE)
                    String transferType = getTextFromElement(tblCellRecord(8, row)).trim();
                    if (!transferType.isEmpty()) {
                        addToReport("Transfer Type valid: " + transferType, Status.PASS, false);
                    } else {
                        addToReport("Missing Transfer Type at row " + row, Status.FAIL, true);
                    }

                    // Column 9: Bank Name (non-empty text)
                    String bankName = getTextFromElement(tblCellRecord(9, row)).trim();
                    if (!bankName.isEmpty()) {
                        addToReport("Bank Name valid: " + bankName, Status.PASS, false);
                    } else {
                        addToReport("Missing Bank Name at row " + row, Status.FAIL, true);
                    }
                }

            } else {
                addToReport("No rows found in payee transaction table.", Status.FAIL, true);
            }

            addToReport("End of validation for " + entry.getValue(), Status.PASS, true);
        }
        addToReport("----------End of validation of the content of Old Vishwa Payee----------", Status.PASS, true);
    }

}


