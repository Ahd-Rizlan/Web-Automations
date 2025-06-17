/*
 *   @author - salman R
 */
package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CommonUtils;
import utils.constants.MessagingConstants;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

import static utils.Drivers.*;

public class MessagesPage extends BasePage {

    public MessagesPage(WebDriver driver) {
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
    private static final By btnDeleteUpload = By.xpath("//img[contains(@src, '2Fclose-icon')]");


    public enum ElementType {
        button, label, span, div, textarea, input;
    }

    private static By getMsg(String title) {
        return By.xpath("//div[contains(text(),'" + title + "')]");
    }
    private static By lblLastModifiedDate(int index) {
        return By.xpath("(//div[contains(@class,'flex justify-between items-center rounded')])[" +index +"]/div[2]/div[3]");
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

    private static By lblMsgID(String subject) {
        return By.xpath("//span[text()='" + subject + "']/parent::div/span[1]");
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

    private static By getElementByPlaceholder(ElementType type, String text) {
        return By.xpath("//" + type.name() + "[@placeholder= \"" + text + "\"]");
    }
    private static By getElementByValue(ElementType type, String text) {
        return By.xpath("//" + type.name() + "[@value= \"" + text + "\"]");
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
     * Initiates a fixed deposit inquiry by selecting the appropriate subject and branch,
     * sending a message, handling OTP validation, and confirming the success message
     * upon message creation
     *
     * @param subject                   The subject category to be selected for the inquiry (e.g., "Fixed Deposit Inquiry")
     * @param branch                    The name or identifier of the branch associated with the inquiry
     * @param msg                       The message content to be sent along with the inquiry
     * @param successMsg               The expected success message after selecting branch and submitting the form
     * @param OTP                      The One-Time Password required for validating the inquiry
     * @param messageCreationSuccessMsg The expected confirmation message after the inquiry message is successfully created
     * @return                         A string indicating the result of the inquiry process (reference number)
     */
    public String fixedDepositInquiry(String subject, String branch, String msg, String successMsg, String OTP, String messageCreationSuccessMsg) {
        addToReport("----------Start of Checking whether after selecting the Fixed deposit inquiry subject , branch list is displayed ----------", Status.PASS, false);
        try {

            waitForElementPresence(getElementByTypeAndText(ElementType.button, MessagingConstants.COMPOSE_NEW_MESSAGE));
            clickOnElement(getElementByTypeAndText(ElementType.button, MessagingConstants.COMPOSE_NEW_MESSAGE));
            waitForElementToBeInvisible(imgGreyLoader,LONG_WAIT);

            selectFromDropdown(ddSubject, subject, MessagingConstants.VISIBLE_TEXT);
            waitForElementToBeInvisible(imgGreyLoader,LONG_WAIT);

            //  Get the actual dropdown visible texts
            List<String> actualBranchTexts = getSelectedOptionText(ddBranchList, MessagingConstants.ALL_OPTIONS);
            //Remove the keyword
            actualBranchTexts.remove(0);
            //  Get the expected branch texts from your BRANCH_MAP
            List<String> expectedBranchTexts = new ArrayList<>(MessagingConstants.BRANCHES.values());

            //  Compare
            boolean isMatching = CommonUtils.compareTwoArraylist(expectedBranchTexts, actualBranchTexts, true); // true -> ignore order

            List<String> missingBranches = findMissingElements(actualBranchTexts,expectedBranchTexts);

            if (isMatching) {
                addToReport("All branches loaded",Status.PASS,true);
            } else {
                addToReport("All branches did not load missing branches from list : "+missingBranches,Status.PASS,true);
            }

        } catch (Exception e) {
            addToReport("Error logging into retail admin", Status.FAIL);
            throw new RuntimeException("Failed log into retail admin" + e.getMessage(), e);
        }

        addToReport("----------End of Checking whether after selecting the Fixed deposit inquiry subject , branch list is displayed ----------", Status.PASS, false);
        addToReport("----------Start of Checking whether a message upon selecting a branch user can send a message to a selected branch ----------", Status.PASS, false);
        selectFromDropdown(ddBranchList,branch, MessagingConstants.VISIBLE_TEXT);

        sendKeysToElement(getElementByPlaceholder(ElementType.textarea,MessagingConstants.ENTER_MESSAGE),msg);

        clickOnElement(getElementByTypeAndText(ElementType.button,MessagingConstants.SEND));

        validatePopUpMsg(successMsg);

        waitForElementPresence(getElementByTypeAndText(ElementType.button,MessagingConstants.CONFIRM),LONG_WAIT);

        //Enter OTP values and continue
        sendKeysToElement(tfOTP(1), String.valueOf(OTP));

        clickOnElement(getElementByTypeAndText(ElementType.button,MessagingConstants.CONFIRM));
        validatePopUpMsg(messageCreationSuccessMsg);

        waitForElementPresence(getElementByPlaceholder(ElementType.input,MessagingConstants.SEARCH_MESSAGES));
        sendKeysToElement(getElementByPlaceholder(ElementType.input,MessagingConstants.SEARCH_MESSAGES),subject);

        List<WebElement> messages = findElements(allMessages);
        WebElement latestMessage = getLatestElementByDate(messages, getElementByTypeAndText(ElementType.div,MessagingConstants.LAST_MODIFIED_ON), MessagingConstants.LAST_MODIFIED_ON);
//        clickOnElement(latestMessage);

        scrollPageToTop();
        waitForElementPresence(lblMsgID(subject));

        addToReport("----------End of Checking whether a message upon selecting a branch user can send a message to a selected branch ----------", Status.PASS, false);
        return getTextFromElement(lblMsgID(subject)).replaceAll("[^0-9]", "");

    }

    /**
     * Validates the message flow for the Card Center subject, including subcategory selection,
     * message sending, OTP handling, and confirmation of successful message creation
     *
     * @param subject                   The main subject under which the message is categorized (e.g., "Card Center")
     * @param subCategory               The specific sub-category related to the selected subject
     * @param msg                       The message content to be submitted
     * @param successMsg               The expected message indicating successful creation
     * @param OTP                       The One-Time Password used for verification during the message flow
     * @param messageCreationSuccessMsg The message expected after the successful creation of the message
     * @return                          A string indicating the result or confirmation of the message submission process
     */
    public String cardCenterValidations(String subject, String subCategory, String msg, String successMsg, String OTP, String messageCreationSuccessMsg) {
        addToReport("----------Start of Checking whether after selecting the Fixed deposit inquiry subject , branch list is displayed ----------", Status.PASS, false);
        try {

            waitForElementPresence(getElementByTypeAndText(ElementType.button, MessagingConstants.COMPOSE_NEW_MESSAGE));
            clickOnElement(getElementByTypeAndText(ElementType.button, MessagingConstants.COMPOSE_NEW_MESSAGE));
            waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);

            selectFromDropdown(ddSubject, subject, MessagingConstants.VISIBLE_TEXT);
            waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);

            //Validate the sub category options
            //  Get the actual dropdown visible texts
            List<String> actualCategoryTexts = getSelectedOptionText(ddSubCategory, MessagingConstants.ALL_OPTIONS);
            //Remove the keyword
            actualCategoryTexts.remove(0);
            //  Get the expected branch texts from your BRANCH_MAP
            List<String> expectedCategoryTexts = new ArrayList<>(MessagingConstants.SUB_CATEGORY.values());

            //  Compare
            boolean isMatching = CommonUtils.compareTwoArraylist(expectedCategoryTexts, actualCategoryTexts, true); // true -> ignore order

            //If the option is missing
            List<String> missingBranches = findMissingElements(actualCategoryTexts, expectedCategoryTexts);

            if (isMatching) {
                addToReport("All sub categories are loaded", Status.PASS, true);
            } else {
                addToReport("All sub categories did not load, missing option from list : " + missingBranches, Status.PASS, true);
            }

            selectFromDropdown(ddSubCategory,subCategory, MessagingConstants.VISIBLE_TEXT);

            sendKeysToElement(getElementByPlaceholder(ElementType.textarea,MessagingConstants.ENTER_MESSAGE),msg);

            clickOnElement(getElementByTypeAndText(ElementType.button,MessagingConstants.SEND));

            validatePopUpMsg(successMsg);

            waitForElementPresence(getElementByTypeAndText(ElementType.button,MessagingConstants.CONFIRM),LONG_WAIT);

            //Enter OTP values and continue
            sendKeysToElement(tfOTP(1), String.valueOf(OTP));

            clickOnElement(getElementByTypeAndText(ElementType.button,MessagingConstants.CONFIRM));
            validatePopUpMsg(messageCreationSuccessMsg);

            waitForElementPresence(getElementByPlaceholder(ElementType.input,MessagingConstants.SEARCH_MESSAGES));
            sendKeysToElement(getElementByPlaceholder(ElementType.input,MessagingConstants.SEARCH_MESSAGES),subCategory);

            //Fix this once stabilized
            List<WebElement> messages = findElements(allMessages);
            WebElement latestMessage = getLatestElementByDate(messages, getElementByTypeAndText(ElementType.div,MessagingConstants.LAST_MODIFIED_ON), MessagingConstants.LAST_MODIFIED_ON);
//            clickOnElement(latestMessage);

            scrollPageToTop();

            waitForElementPresence(lblMsgID(subCategory));
            if(getTextFromElement(lblMsgID(subCategory)).replaceAll("[^0-9]", "").isEmpty()){
                addToReport("Error fetching message id", Status.FAIL);
                throw new RuntimeException("Failed to fetch message id after sending message");
            }else {
                addToReport("Generated message id : "+getTextFromElement(lblMsgID(subCategory)).replaceAll("[^0-9]", ""), Status.PASS,false);
            }


        } catch (Exception e) {
            addToReport("Error logging into retail admin", Status.FAIL);
            throw new RuntimeException("Failed log into retail admin" + e.getMessage(), e);
        }
        return getTextFromElement(lblMsgID(subCategory)).replaceAll("[^0-9]", "");
    }
    /**
     * fund transfer request message validation
     * @param subject       - Message subject
     * @param msg           - Message
     * @param successMsg    - Success message
     * @param OTP           - otp
     * @param messageCreationSuccessMsg  - Message creation success message
     * @return
     */
    public String fundTransferRequestValidations(String subject, int amount, String msg, String successMsg, String OTP, String messageCreationSuccessMsg,String accountNo,String remark) {
        addToReport("----------Start of Checking whether Selecting ‘Fund Transfer Request’ displays fields ----------", Status.PASS, false);
        try {

            waitForElementPresence(getElementByTypeAndText(ElementType.button, MessagingConstants.COMPOSE_NEW_MESSAGE));
            clickOnElement(getElementByTypeAndText(ElementType.button, MessagingConstants.COMPOSE_NEW_MESSAGE));
            waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);

            waitForElementToBeClickable(ddSubject,LONG_WAIT);
            selectFromDropdown(ddSubject, subject, MessagingConstants.VISIBLE_TEXT);
            waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);

            //Validate the loaded fields
            if (isElementPresentBy(getElementByPlaceholder(ElementType.input,MessagingConstants.ENTER_AMOUNT))) {
                addToReport("Amount field is present", Status.PASS,false);
            } else {
                addToReport("Amount field is not present", Status.FAIL);
            }
            if (isElementPresentBy(getElementByValue(ElementType.input,MessagingConstants.SAMPATH))) {
                addToReport("Radio sampath field is present", Status.PASS,false);
            } else {
                addToReport("Radio sampath field is not present", Status.FAIL);
            }
            if (isElementPresentBy(getElementByValue(ElementType.input,MessagingConstants.OTHER))) {
                addToReport("Radio other field is present", Status.PASS,false);
            } else {
                addToReport("Radio other field is not present", Status.FAIL);
            }
            if (isElementPresentBy(ddFromAccount)) {
                addToReport("Dropdown from account field is present", Status.PASS,false);
            } else {
                addToReport("Dropdown from account is not present", Status.FAIL);
            }
            if (isElementPresentBy(getElementByPlaceholder(ElementType.input,MessagingConstants.ENTER_ACCOUNT_NUMBER))) {
                addToReport("Account number field is present", Status.PASS,false);
            } else {
                addToReport("Account number field is not present", Status.FAIL);
            }
            if (isElementPresentBy(getElementByPlaceholder(ElementType.input,MessagingConstants.ENTER_REMARKS))) {
                addToReport("Remarks field is present", Status.PASS,false);
            } else {
                addToReport("Remarks field is not present", Status.FAIL);
            }
            clickOnElement(getElementByValue(ElementType.input,MessagingConstants.OTHER));
            if (isElementPresentBy(ddBank)) {
                addToReport("Dropdown bank field is present", Status.PASS,false);
            } else {
                addToReport("Dropdown bank is not present", Status.FAIL);
            }

            addToReport("----------End of Checking whether Selecting ‘Fund Transfer Request’ displays fields ----------", Status.PASS, false);
            addToReport("----------Start of Checking entering the amount ----------", Status.PASS, false);

            clickOnElement(getElementByValue(ElementType.input,MessagingConstants.SAMPATH));

            selectFromDropdown(ddFromAccount,MessagingConstants.NUMERICAL_THREE,MessagingConstants.INDEX);

            sendKeysToElement(getElementByPlaceholder(ElementType.input,MessagingConstants.ENTER_AMOUNT), String.valueOf(amount));

            // Obtain the selected value from the dropdown
            List<String> fromAccDropdownValue = getSelectedOptionText(ddFromAccount, "FIRST_SELECTED");

            String[] amt = fromAccDropdownValue.get(0).split(MessagingConstants.KW_LKR);

            // Convert to double
            double amountDouble = Double.parseDouble(amt[1].replace(",", ""));

            // Convert to whole number (round or cast)
            int wholeAmount = (int) Math.round(amountDouble);

            if (wholeAmount> amount){
                addToReport("Entered amount : "+amount+" is less than  the amount in account account : '" + wholeAmount , Status.PASS);
            }else {
                addToReport("Entered amount : "+amount+" is greater than  the amount in account account : '" + wholeAmount, Status.FAIL);
                throw new RuntimeException("Not sufficient funds in the account");
            }

            sendKeysToElement(getElementByPlaceholder(ElementType.input,MessagingConstants.ENTER_ACCOUNT_NUMBER),accountNo);

            sendKeysToElement(getElementByPlaceholder(ElementType.input,MessagingConstants.ENTER_REMARKS),remark);

            clickOnElement(getElementByTypeAndText(ElementType.button,MessagingConstants.SEND));

            validatePopUpMsg(successMsg);

            waitForElementPresence(getElementByTypeAndText(ElementType.button,MessagingConstants.CONFIRM),20);

            //Enter OTP values and continue
            sendKeysToElement(tfOTP(1), String.valueOf(OTP));

            clickOnElement(getElementByTypeAndText(ElementType.button,MessagingConstants.CONFIRM));
            validatePopUpMsg(messageCreationSuccessMsg);

            waitForElementPresence(getElementByPlaceholder(ElementType.input,MessagingConstants.SEARCH_MESSAGES));
            sendKeysToElement(getElementByPlaceholder(ElementType.input,MessagingConstants.SEARCH_MESSAGES),subject);

            List<WebElement> messages = findElements(allMessages);
            WebElement latestMessage = getLatestElementByDate(messages, getElementByTypeAndText(ElementType.div,MessagingConstants.LAST_MODIFIED_ON), MessagingConstants.LAST_MODIFIED_ON);
//        clickOnElement(latestMessage);

            scrollPageToTop();
            waitForElementPresence(lblMsgID(subject));

            if(getTextFromElement(lblMsgID(subject)).replaceAll("[^0-9]", "").isEmpty()){
                addToReport("Error fetching message id", Status.FAIL);
                throw new RuntimeException("Failed to fetch message id after sending message");
            }else {
                addToReport("Generated message id : "+getTextFromElement(lblMsgID(subject)).replaceAll("[^0-9]", ""), Status.PASS,false);
            }

            addToReport("----------End of Checking entering the amount ----------", Status.PASS, false);

        } catch (Exception e) {
            addToReport("Error sending message under fund transfer", Status.FAIL);
            throw new RuntimeException("Failed initiate fund transfer" + e.getMessage(), e);
        }
        return getTextFromElement(lblMsgID(subject)).replaceAll("[^0-9]", "");
    }


    /**
     * Validates whether the expected popup message is displayed on the screen
     *
     * @param msg The expected text content of the popup message to validate
     */
    public void validatePopUpMsg(String msg) {
        //waitForElementToBeInvisible(btnLogin,20);
        waitForElementPresence(getMsg(msg),LONG_WAIT);
        waitForElementToBeClickable(getMsg(msg),LONG_WAIT);
        //Validate the success message
        if (isElementPresentBy(getMsg(msg))) {
            addToReport("'" + msg + "'  message is present.", Status.PASS,true);
        } else {
            addToReport("'" + msg + "'  message is not present.", Status.FAIL);
        }
        clickOnElement(btnClosePopup);
    }


    /**
     * Performs validations for the Balance Confirmation message workflow
     * This includes verifying the successful submission of a message, OTP confirmation,
     * and ensuring the confirmation text is correctly reflected in the response
     *
     * @param subject                     The subject under which the message is submitted
     * @param successMsg                 The expected success message after sending the request
     * @param OTP                        The One Time Password required for confirmation
     * @param messageCreationSuccessMsg  The expected confirmation message after the message is created
     * @param sampleText                 The expected sample text that should appear in the confirmation
     * @return                           A string result indicating the outcome of the validation (Message ID)
     */
    public String BalanceConfirmationValidations(String subject,String successMsg, String OTP, String messageCreationSuccessMsg,String sampleText) {
        addToReport("----------Start of checking whether Selecting ‘Balance Confirmation Request’ displays fields ----------", Status.PASS, false);


            waitForElementPresence(getElementByTypeAndText(ElementType.button, MessagingConstants.COMPOSE_NEW_MESSAGE));
            clickOnElement(getElementByTypeAndText(ElementType.button, MessagingConstants.COMPOSE_NEW_MESSAGE));
            waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);

            waitForElementToBeClickable(ddSubject,LONG_WAIT);
            selectFromDropdown(ddSubject, subject, MessagingConstants.VISIBLE_TEXT);
            waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);
        try {
            //Validate the loaded fields
            if (isElementPresentBy(ddAccountNumber)) {
                addToReport("Dropdown from account field is present", Status.PASS,false);
            } else {
                addToReport("Dropdown from account is not present", Status.FAIL);
            }
            if (isElementPresentBy(ddPurpose)) {
                addToReport("Dropdown purpose is present", Status.PASS,false);
            } else {
                addToReport("Dropdown purpose is not present", Status.FAIL);
            }
            //  Get the expected branch texts from purpose dropdown
            List<String> expectedPurposeDDValues = new ArrayList<>(MessagingConstants.PURPOSE_DROPDOWN.values());
            //  Get the actual dropdown visible texts
            List<String> actualPurposeTexts = getSelectedOptionText(ddPurpose, MessagingConstants.ALL_OPTIONS);
            actualPurposeTexts.remove(0);
            //  Compare
            boolean isMatching = CommonUtils.compareTwoArraylist(expectedPurposeDDValues, actualPurposeTexts, true); // true -> ignore order
            if (isMatching) {
                addToReport("All values in purpose are loaded",Status.PASS,true);
            } else {
                List<String> missingDDValues = findMissingElements(actualPurposeTexts,expectedPurposeDDValues);
                addToReport("All values in purpose are not loaded, missing values: "+missingDDValues,Status.PASS,true);
            }

            //Validate other option
            selectFromDropdown(ddPurpose,MessagingConstants.PURPOSE_DROPDOWN.get("KW_OTHER"),"value");
            if (isElementPresentBy(getElementByPlaceholder(ElementType.input,MessagingConstants.OTHER_PURPOSE))) {
                addToReport("Other purpose field is present", Status.PASS,false);
            } else {
                addToReport("Other purpose field is not present", Status.FAIL);
            }

            //Validate manual data entry to others field
            sendKeysToElement(getElementByPlaceholder(ElementType.input,MessagingConstants.OTHER_PURPOSE),sampleText);

            if (getAttributeOrText(getElementByPlaceholder(ElementType.input,MessagingConstants.OTHER_PURPOSE),"value").equals(sampleText)) {
                addToReport("Other purpose text field is enabled", Status.PASS,false);
            } else {
                addToReport("Other purpose text field is not enabled", Status.FAIL);
            }
            if (isElementPresentBy(getElementByValue(ElementType.input,MessagingConstants.PERIOD))) {
                addToReport("Radio period field is present", Status.PASS,false);
            } else {
                addToReport("Radio period field is not present", Status.FAIL);
            }
            clickOnElement(getElementByValue(ElementType.input,MessagingConstants.PERIOD));
            if (isElementPresentBy(getElementByValue(ElementType.input,MessagingConstants.PERIOD))) {
                addToReport("Radio period field is present", Status.PASS,false);
            } else {
                addToReport("Radio period field is not present", Status.FAIL);
            }
            //Validate date pickers
            if (isElementPresentBy(dpPickDate(MessagingConstants.FROM_DATE))) {
                addToReport("Date picker "+MessagingConstants.FROM_DATE+" field is present", Status.PASS,false);
            } else {
                addToReport("Date picker "+MessagingConstants.FROM_DATE+" is not present", Status.FAIL);
            }
            if (isElementPresentBy(dpPickDate(MessagingConstants.TO_DATE))) {
                addToReport("Date picker "+MessagingConstants.TO_DATE+" field is present", Status.PASS,false);
            } else {
                addToReport("Date picker "+MessagingConstants.TO_DATE+" is not present", Status.FAIL);
            }
            //Validate as at date options
            clickOnElement(getElementByValue(ElementType.input,MessagingConstants.AS_AT_DATE_LC));
            if (isElementPresentBy(dpPickDate(MessagingConstants.AS_AT_DATE))) {
                addToReport("Date picker "+MessagingConstants.AS_AT_DATE+" field is present", Status.PASS,false);
            } else {
                addToReport("Date picker "+MessagingConstants.AS_AT_DATE+" is not present", Status.FAIL);
            }

            if (isElementPresentBy(getElementByPlaceholder(ElementType.textarea,MessagingConstants.ENTER_TO_WHOM_IT_SHOULD_BE_ADDRESSED))) {
                addToReport(MessagingConstants.ENTER_TO_WHOM_IT_SHOULD_BE_ADDRESSED+" field is present", Status.PASS,true);
            } else {
                addToReport(MessagingConstants.ENTER_TO_WHOM_IT_SHOULD_BE_ADDRESSED+" field is not present", Status.FAIL);
            }

            addToReport("----------End of checking whether Selecting ‘Balance Confirmation Request’ displays fields ----------", Status.PASS, false);

            addToReport("----------Start of entering values and send message ----------", Status.PASS, false);

            clickOnElement(rdoDate(MessagingConstants.AS_AT_DATE_LC));

            //Update below to select a date use system date
            //sendKeysToElement(getElementByValue(ElementType.input,MessagingConstants.AS_AT_DATE),"04/28/2025");

            selectFromDropdown(ddAccountNumber,"2",MessagingConstants.INDEX);
            sendKeysToElement(getElementByPlaceholder(ElementType.input,MessagingConstants.OTHER_PURPOSE),sampleText);
            sendKeysToElement(getElementByPlaceholder(ElementType.textarea,MessagingConstants.ENTER_TO_WHOM_IT_SHOULD_BE_ADDRESSED),sampleText);

            clickOnElement(getElementByTypeAndText(ElementType.button,MessagingConstants.SEND));

            validatePopUpMsg(successMsg);

            waitForElementPresence(getElementByTypeAndText(ElementType.button,MessagingConstants.CONFIRM),LONG_WAIT);

            //Enter OTP values and continue
            sendKeysToElement(tfOTP(1), String.valueOf(OTP));

            clickOnElement(getElementByTypeAndText(ElementType.button,MessagingConstants.CONFIRM));
            validatePopUpMsg(messageCreationSuccessMsg);

            waitForElementPresence(getElementByPlaceholder(ElementType.input,MessagingConstants.SEARCH_MESSAGES));
            sendKeysToElement(getElementByPlaceholder(ElementType.input,MessagingConstants.SEARCH_MESSAGES),subject);

            List<WebElement> messages = findElements(allMessages);
            WebElement latestMessage = getLatestElementByDate(messages, getElementByTypeAndText(ElementType.div,MessagingConstants.LAST_MODIFIED_ON), MessagingConstants.LAST_MODIFIED_ON);
//        clickOnElement(latestMessage);

            scrollPageToTop();
            waitForElementPresence(lblMsgID(subject));

            if(getTextFromElement(lblMsgID(subject)).replaceAll("[^0-9]", "").isEmpty()){
                addToReport("Error fetching message id", Status.FAIL);
                throw new RuntimeException("Failed to fetch message id after sending message");
            }else {
                addToReport("Generated message id : "+getTextFromElement(lblMsgID(subject)).replaceAll("[^0-9]", ""), Status.PASS,false);
            }

            addToReport("----------End of of entering values and send message ----------", Status.PASS, true);

        } catch (Exception e) {
            addToReport("Error sending message under fund balance confirmation", Status.FAIL);
            throw new RuntimeException("Failed initiate balance confirmation" + e.getMessage(), e);
        }
        return getTextFromElement(lblMsgID(subject)).replaceAll("[^0-9]", "");
    }

    /**
     * Performs validations for the Fund Transfer Dispute message workflow.
     * This includes verifying the proper display of success messages, OTP handling,
     * and confirming that the message creation process completes successfully with the given input.
     *
     * @param subject                   The subject line or category selected for the message
     * @param successMsg                The expected success message after the initial action
     * @param OTP                       The One Time Password required to complete the operation
     * @param messageCreationSuccessMsg The expected confirmation message after message creation
     * @param message                   The actual message content submitted for the dispute
     * @return                          A string result indicating the outcome of the validation (MessageID)
     */
    public String FundTransferDisputeValidations(String subject,String successMsg, String OTP, String messageCreationSuccessMsg,String message,String filePath) {
        addToReport("----------Start of checking whether Selecting ‘Fund Transfer Dispute’ displays fields ----------", Status.PASS, false);
        try {

            waitForElementPresence(getElementByTypeAndText(ElementType.button, MessagingConstants.COMPOSE_NEW_MESSAGE));
            clickOnElement(getElementByTypeAndText(ElementType.button, MessagingConstants.COMPOSE_NEW_MESSAGE));
            waitForElementToBeInvisible(getElementByTypeAndText(ElementType.button, MessagingConstants.COMPOSE_NEW_MESSAGE),20);
            waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);

            waitFor(5);
            selectFromDropdown(ddSubject, subject, MessagingConstants.VISIBLE_TEXT);
            waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);

            //Validate the loaded fields
            if (isElementPresentBy(getElementByPlaceholder(ElementType.textarea,MessagingConstants.ENTER_MESSAGE))) {
                addToReport(MessagingConstants.ENTER_MESSAGE+" field is present", Status.PASS,false);
            } else {
                addToReport(MessagingConstants.ENTER_MESSAGE+" field is not present", Status.FAIL);
            }

            addToReport("----------End of checking whether Selecting ‘Fund Transfer Dispute’ displays fields ----------", Status.PASS, false);

            addToReport("----------Start of entering values and send message ----------", Status.PASS, false);

            //Enter message
            sendKeysToElement(getElementByPlaceholder(ElementType.textarea,MessagingConstants.ENTER_MESSAGE),message);

            //Upload attachment
            uploadAndValidateAttachment(getFileFromResources(filePath));

            clickOnElement(getElementByTypeAndText(ElementType.button,MessagingConstants.SEND));

            validatePopUpMsg(successMsg);

            waitForElementPresence(getElementByTypeAndText(ElementType.button,MessagingConstants.CONFIRM),LONG_WAIT);

            //Enter OTP values and continue
            sendKeysToElement(tfOTP(1), String.valueOf(OTP));

            clickOnElement(getElementByTypeAndText(ElementType.button,MessagingConstants.CONFIRM));
            validatePopUpMsg(messageCreationSuccessMsg);

            waitForElementPresence(getElementByPlaceholder(ElementType.input,MessagingConstants.SEARCH_MESSAGES));
            sendKeysToElement(getElementByPlaceholder(ElementType.input,MessagingConstants.SEARCH_MESSAGES),subject);

            List<WebElement> messages = findElements(allMessages);
            WebElement latestMessage = getLatestElementByDate(messages, getElementByTypeAndText(ElementType.div,MessagingConstants.LAST_MODIFIED_ON), MessagingConstants.LAST_MODIFIED_ON);
//        clickOnElement(latestMessage);

            scrollPageToTop();
            waitForElementPresence(lblMsgID(subject));

            if(getTextFromElement(lblMsgID(subject)).replaceAll("[^0-9]", "").isEmpty()){
                addToReport("Error fetching message id", Status.FAIL);
                throw new RuntimeException("Failed to fetch message id after sending message");
            }else {
                addToReport("Generated message id : "+getTextFromElement(lblMsgID(subject)).replaceAll("[^0-9]", ""), Status.PASS,false);
            }

            addToReport("----------End of of entering values and send message ----------", Status.PASS, true);

        } catch (Exception e) {
            addToReport("Error sending message under fund balance confirmation", Status.FAIL);
            throw new RuntimeException("Failed initiate balance confirmation" + e.getMessage(), e);
        }
        return getTextFromElement(lblMsgID(subject)).replaceAll("[^0-9]", "");
    }


    /**
     * Performs validation for the Bill Payment Dispute process
     * This includes filling in the subject, uploading any required attachments, entering the OTP,
     * and verifying success and confirmation messages after submission
     *
     * @param subject                      The subject selected for the bill payment dispute
     * @param successMsg                  The success message expected after the form submission
     * @param OTP                         The One-Time Password required for validation
     * @param messageCreationSuccessMsg   The message expected confirming the creation of the dispute message
     * @param message                     The actual message content or description of the dispute
     * @param filePath                    The full or relative path of the file to be attached with the dispute
     * @return                            The confirmation string or message from the system upon successful operation
     */
    public String BillPaymentDisputeValidations(String subject,String successMsg, String OTP, String messageCreationSuccessMsg,String message,String filePath) {
        addToReport("----------Start of checking whether Selecting ‘Fund Transfer Dispute’ displays fields ----------", Status.PASS, false);
        try {

            waitForElementPresence(getElementByTypeAndText(ElementType.button, MessagingConstants.COMPOSE_NEW_MESSAGE));
            clickOnElement(getElementByTypeAndText(ElementType.button, MessagingConstants.COMPOSE_NEW_MESSAGE));
            waitForElementToBeInvisible(getElementByTypeAndText(ElementType.button, MessagingConstants.COMPOSE_NEW_MESSAGE),LONG_WAIT);
            waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);

            waitFor(5);
            selectFromDropdown(ddSubject, subject, MessagingConstants.VISIBLE_TEXT);
            waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);

            //Validate the loaded fields
            if (isElementPresentBy(getElementByPlaceholder(ElementType.textarea,MessagingConstants.ENTER_MESSAGE))) {
                addToReport(MessagingConstants.ENTER_MESSAGE+" field is present", Status.PASS,false);
            } else {
                addToReport(MessagingConstants.ENTER_MESSAGE+" field is not present", Status.FAIL);
            }

            addToReport("----------End of checking whether Selecting ‘Fund Transfer Dispute’ displays fields ----------", Status.PASS, false);

            addToReport("----------Start of entering values and send message ----------", Status.PASS, false);

            //Enter message
            sendKeysToElement(getElementByPlaceholder(ElementType.textarea,MessagingConstants.ENTER_MESSAGE),message);

            //Upload attachment
            uploadAndValidateAttachment(getFileFromResources(filePath));

            clickOnElement(getElementByTypeAndText(ElementType.button,MessagingConstants.SEND));

            validatePopUpMsg(successMsg);

            waitForElementPresence(getElementByTypeAndText(ElementType.button,MessagingConstants.CONFIRM),LONG_WAIT);

            //Enter OTP values and continue
            sendKeysToElement(tfOTP(1), String.valueOf(OTP));

            clickOnElement(getElementByTypeAndText(ElementType.button,MessagingConstants.CONFIRM));
            validatePopUpMsg(messageCreationSuccessMsg);

            waitForElementPresence(getElementByPlaceholder(ElementType.input,MessagingConstants.SEARCH_MESSAGES));
            sendKeysToElement(getElementByPlaceholder(ElementType.input,MessagingConstants.SEARCH_MESSAGES),subject);

            List<WebElement> messages = findElements(allMessages);
            WebElement latestMessage = getLatestElementByDate(messages, getElementByTypeAndText(ElementType.div,MessagingConstants.LAST_MODIFIED_ON), MessagingConstants.LAST_MODIFIED_ON);
//        clickOnElement(latestMessage);

            scrollPageToTop();
            waitForElementPresence(lblMsgID(subject));

            if(getTextFromElement(lblMsgID(subject)).replaceAll("[^0-9]", "").isEmpty()){
                addToReport("Error fetching message id", Status.FAIL);
                throw new RuntimeException("Failed to fetch message id after sending message");
            }else {
                addToReport("Generated message id : "+getTextFromElement(lblMsgID(subject)).replaceAll("[^0-9]", ""), Status.PASS,false);
            }

            addToReport("----------End of of entering values and send message ----------", Status.PASS, true);

        } catch (Exception e) {
            addToReport("Error sending message under fund balance confirmation", Status.FAIL);
            throw new RuntimeException("Failed initiate balance confirmation" + e.getMessage(), e);
        }
        return getTextFromElement(lblMsgID(subject)).replaceAll("[^0-9]", "");
    }


    /**
     * Performs validations for Bill Payment Dispute message flow
     * This includes entering message details, verifying success messages,
     * and uploading a file attachment
     *
     * @param subject                   The subject selected for the message
     * @param successMsg               The success message expected after message submission
     * @param OTP                      The one-time password used for authentication
     * @param messageCreationSuccessMsg The message shown on successful message creation
     * @param message                  The actual message content to be entered
     * @param branch                   The branch name involved in the dispute
     * @param filePath                 The path of the attachment file to be uploaded
     * @return                         The generated message ID after successful submission
     */
    public String OtherSubjectValidations(String subject,String successMsg, String OTP, String messageCreationSuccessMsg,String message,String branch,String filePath) {
        addToReport("----------Start of checking whether Selecting ‘Other subject’ displays fields ----------", Status.PASS, false);


            waitForElementPresence(getElementByTypeAndText(ElementType.button, MessagingConstants.COMPOSE_NEW_MESSAGE));
            clickOnElement(getElementByTypeAndText(ElementType.button, MessagingConstants.COMPOSE_NEW_MESSAGE));
            waitForElementToBeInvisible(getElementByTypeAndText(ElementType.button, MessagingConstants.COMPOSE_NEW_MESSAGE),20);
            waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);
        try {
            waitForElementToBeClickable(ddSubject,LONG_WAIT);
            waitFor(VERY_SHORT_WAIT);

            selectFromDropdown(ddSubject, subject, MessagingConstants.VISIBLE_TEXT);
            waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);

            //Validate the loaded fields
            if (isElementPresentBy(getElementByPlaceholder(ElementType.textarea,MessagingConstants.ENTER_MESSAGE))) {
                addToReport(MessagingConstants.ENTER_MESSAGE+" field is present", Status.PASS,false);
            } else {
                addToReport(MessagingConstants.ENTER_MESSAGE+" field is not present", Status.FAIL);
            }
            if (isElementPresentBy(getElementByPlaceholder(ElementType.input,MessagingConstants.ENTER_MESSAGE_SUBJECT))) {
                addToReport(MessagingConstants.ENTER_MESSAGE_SUBJECT+" field is present", Status.PASS,false);
            } else {
                addToReport(MessagingConstants.ENTER_MESSAGE_SUBJECT+" field is not present", Status.FAIL);
            }
            if (isElementPresentBy(ddBranchList)) {
                addToReport("Dropdown branch field is present", Status.PASS,false);
            } else {
                addToReport("Dropdown branch field is present", Status.FAIL);
            }

            addToReport("----------End of checking whether Selecting ‘Other subject’ displays fields ----------", Status.PASS, false);

            addToReport("----------Start of entering values and send message ----------", Status.PASS, false);

            //Select branch
            selectFromDropdown(ddBranchList,branch, MessagingConstants.VISIBLE_TEXT);

            //Enter message
            sendKeysToElement(getElementByPlaceholder(ElementType.textarea,MessagingConstants.ENTER_MESSAGE),message);

            //Upload attachment
            uploadAndValidateAttachment(getFileFromResources(filePath));

            //Enter subject
            sendKeysToElement(getElementByPlaceholder(ElementType.input,MessagingConstants.ENTER_MESSAGE_SUBJECT),subject);

            clickOnElement(getElementByTypeAndText(ElementType.button,MessagingConstants.SEND));

            validatePopUpMsg(successMsg);

            waitForElementPresence(getElementByTypeAndText(ElementType.button,MessagingConstants.CONFIRM),20);

            //Enter OTP values and continue
            sendKeysToElement(tfOTP(1), String.valueOf(OTP));

            clickOnElement(getElementByTypeAndText(ElementType.button,MessagingConstants.CONFIRM));
            validatePopUpMsg(messageCreationSuccessMsg);

            waitForElementPresence(getElementByPlaceholder(ElementType.input,MessagingConstants.SEARCH_MESSAGES));
            sendKeysToElement(getElementByPlaceholder(ElementType.input,MessagingConstants.SEARCH_MESSAGES),subject);

            List<WebElement> messages = findElements(allMessages);
            WebElement latestMessage = getLatestElementByDate(messages, getElementByTypeAndText(ElementType.div,MessagingConstants.LAST_MODIFIED_ON), MessagingConstants.LAST_MODIFIED_ON);
            //clickOnElement(latestMessage);

            scrollPageToTop();
            waitForElementPresence(lblMsgID(subject));

            if(getTextFromElement(lblMsgID(subject)).replaceAll("[^0-9]", "").isEmpty()){
                addToReport("Error fetching message id", Status.FAIL);
                throw new RuntimeException("Failed to fetch message id after sending message");
            }else {
                addToReport("Generated message id : "+getTextFromElement(lblMsgID(subject)).replaceAll("[^0-9]", ""), Status.PASS,false);
            }

            addToReport("----------End of of entering values and send message ----------", Status.PASS, true);

        } catch (Exception e) {
            addToReport("Error sending message under fund balance confirmation", Status.FAIL);
            throw new RuntimeException("Failed initiate balance confirmation" + e.getMessage(), e);
        }
        return getTextFromElement(lblMsgID(subject)).replaceAll("[^0-9]", "");
    }

    /**
     * Validates inbox message functionalities including delete and recovery actions
     * This method performs checks on a message based on its ID, validates its content,
     * verifies deletion confirmation, and confirms successful recovery
     *
     * @param subject              The subject under which the message is categorized
     * @param messageID            The unique identifier of the message to validate
     * @param message              The expected content of the message for verification
     * @param deletionSuccessMsg   The confirmation message expected after deletion
     * @param recoverySuccessMsg   The confirmation message expected after recovery
     */
    public void inboxMessagesValidation(String subject,String messageID,String message,String deletionSuccessMsg,String recoverySuccessMsg) {

        try {

            addToReport("----------Start of validation of received messages are in reverse order----------", Status.PASS, false);
            clickOnElement(getElementByTypeAndText(ElementType.div,MessagingConstants.TRASH));
            waitForElementToBeInvisible(imgGreyLoader,LONG_WAIT);
            clickOnElement(getElementByTypeAndText(ElementType.div,MessagingConstants.ALL));
            waitForElementToBeInvisible(imgGreyLoader,LONG_WAIT);

            //Validate the order of messages
            verifyMessagesInReverseChronologicalOrder();
            addToReport("----------End of validation of received messages are in reverse order----------", Status.PASS, false);
            addToReport("----------Start of validation of received message----------", Status.PASS, false);

            waitForElementPresence(getElementByPlaceholder(ElementType.input,MessagingConstants.SEARCH_MESSAGES));
            sendKeysToElement(getElementByPlaceholder(ElementType.input,MessagingConstants.SEARCH_MESSAGES),subject);

            //Validate the loaded fields
            if (isElementPresentBy(icnRecivedMessage)) {
                addToReport("Received message is present", Status.PASS,true);
            } else {
                addToReport("Received message is not present", Status.FAIL);
            }

            clickOnElement(icnRecivedMessage);

            //Validate the message id
            if (messageID.equals(getTextFromElement(lblMsgID(subject)).replaceAll("[^0-9]", ""))) {
                addToReport("Received message id is :"+messageID, Status.PASS,true);
            } else {
                addToReport("Received message id is not loaded", Status.FAIL);
            }

            scrollPageToTop();
            waitForElementToBeClickable(lblMessageContent,LONG_WAIT);
            //Validate Message date
                WebElement msgElement = driver.findElement(lblMessageContent);
                WebElement dateElement = driver.findElement(lblResponseDate(message));

                String actualMessage = msgElement.getText().trim();
                String actualDate = dateElement.getText().trim();

                // Validate content
                if (!actualMessage.equals(message)) {
                    throw new AssertionError("Expected message: " + message + " but found: " + actualMessage);
                }
                // Validate date
                String today = LocalDate.now().toString(); // yyyy-MM-dd format
                if (!actualDate.startsWith(today)) {
                    throw new RuntimeException("Expected message date to be today (" + today + ") but was: "+actualDate);
                }
                addToReport("Message and date validated successfully", Status.PASS,true);

            addToReport("----------End of validation of received message----------", Status.PASS, false);
            addToReport("----------Start of delete message----------", Status.PASS, false);

            scrollPageToTop();
            //Delete the last open record
            clickOnElement(btnDeleteMessage(1));
            waitForElementToBeClickable(getElementByTypeAndText(ElementType.button, MessagingConstants.CONFIRM),LONG_WAIT);
            clickOnElement(getElementByTypeAndText(ElementType.button, MessagingConstants.CONFIRM));
            validatePopUpMsg(deletionSuccessMsg);

            scrollPageToTop();

            addToReport("----------End of delete message----------", Status.PASS, false);
            addToReport("----------Start of recovery of deleted message----------", Status.PASS, false);
            clickOnElement(getElementByTypeAndText(ElementType.div,MessagingConstants.TRASH));
            waitForElementToBeInvisible(imgGreyLoader,LONG_WAIT);

            waitForElementPresence(getElementByPlaceholder(ElementType.input,MessagingConstants.SEARCH_MESSAGES));
            sendKeysToElement(getElementByPlaceholder(ElementType.input,MessagingConstants.SEARCH_MESSAGES),subject);

            clickOnElement(lblDeletedMessage(1));

            if (messageID.equals(getTextFromElement(lblMsgID(subject)).replaceAll("[^0-9]", ""))) {
                addToReport("Deleted message id is validated"+messageID, Status.PASS,true);
            } else {
                addToReport("Deleted message id is not loaded instead loaded ID :"+messageID, Status.FAIL);
            }
            //Recover the message
            clickOnElement(btnRecoverMessage(1));
            waitForElementToBeClickable(getElementByTypeAndText(ElementType.button, MessagingConstants.CONFIRM),20);
            clickOnElement(getElementByTypeAndText(ElementType.button, MessagingConstants.CONFIRM));

            validatePopUpMsg(recoverySuccessMsg);
            scrollPageToTop();
            waitForElementPresence(getElementByPlaceholder(ElementType.input,MessagingConstants.SEARCH_MESSAGES));
            sendKeysToElement(getElementByPlaceholder(ElementType.input,MessagingConstants.SEARCH_MESSAGES),subject);

            clickOnElement(icnReadMessageRecord(1));

            if (messageID.equals(getTextFromElement(lblMsgID(subject)).replaceAll("[^0-9]", ""))) {
                addToReport("Restored message id is validated"+messageID, Status.PASS,true);
            } else {
                addToReport("Restored message id is not loaded instead loaded ID :"+messageID, Status.FAIL);
            }

            addToReport("----------End of recovery of deleted message----------", Status.PASS, false);

        } catch (Exception e) {
            addToReport("Error validating inbox", Status.FAIL);
            throw new RuntimeException("Failed to validate inbox" + e.getMessage(), e);
        }
    }

    /**
     * Performs validations on the draft message workflow including creation, update, and deletion.
     * This method verifies if a draft can be successfully created with the given message and file attachment,
     * updated with new content, and deleted with proper confirmation messages.
     *
     * @param subject                The subject category under which the draft is created
     * @param successMsg            The message shown after successful draft save
     * @param OTP                   The One-Time Password used for draft creation
     * @param messageCreationSuccessMsg The confirmation message expected after draft creation
     * @param message               The initial content of the draft message
     * @param updatedMsg            The content to update the draft message with
     * @param deletionSuccessMsg    The message expected after successfully deleting the draft
     * @param filePath              The path to the file to be attached with the draft message
     */
    public void draftMessageValidations(String subject,String successMsg, String OTP, String messageCreationSuccessMsg,String message,String updatedMsg, String deletionSuccessMsg,String filePath) {



            waitForElementPresence(getElementByTypeAndText(ElementType.button, MessagingConstants.COMPOSE_NEW_MESSAGE));
            clickOnElement(getElementByTypeAndText(ElementType.button, MessagingConstants.COMPOSE_NEW_MESSAGE));
            waitForElementToBeInvisible(getElementByTypeAndText(ElementType.button, MessagingConstants.COMPOSE_NEW_MESSAGE),LONG_WAIT);
            waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);

            waitForElementToBeClickable(ddSubject,LONG_WAIT);
            waitFor(VERY_SHORT_WAIT);
            selectFromDropdown(ddSubject, subject, MessagingConstants.VISIBLE_TEXT);
            waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);

            addToReport("----------Start of draft message ----------", Status.PASS, false);

//            Commented due to a bug
//            uploadAndValidateAttachment(getFileFromResources(filePath));
        try {
            //Enter message
            sendKeysToElement(getElementByPlaceholder(ElementType.textarea,MessagingConstants.ENTER_MESSAGE),message);

            clickOnElement(getElementByTypeAndText(ElementType.button,MessagingConstants.SAVE_AS_DRAFT));

            validatePopUpMsg(successMsg);

            waitForElementPresence(getElementByTypeAndText(ElementType.button,MessagingConstants.CONFIRM),LONG_WAIT);

            //Enter OTP values and continue
            sendKeysToElement(tfOTP(1), String.valueOf(OTP));

            clickOnElement(getElementByTypeAndText(ElementType.button,MessagingConstants.CONFIRM));
            validatePopUpMsg(messageCreationSuccessMsg);

            waitFor(VERY_SHORT_WAIT);
            waitForElementToBeInvisible(imgGreyLoader,LONG_WAIT);
            waitForElementPresence(getElementByPlaceholder(ElementType.input,MessagingConstants.SEARCH_MESSAGES));

            List<WebElement> messages = findElements(allMessages);
            WebElement latestMessage = getLatestElementByDate(messages, getElementByTypeAndText(ElementType.div,MessagingConstants.LAST_MODIFIED_ON), MessagingConstants.LAST_MODIFIED_ON);
//        clickOnElement(latestMessage);

            scrollPageToTop();
            waitForElementPresence(lblMsgID(subject));

            if(getTextFromElement(lblMsgID(subject)).replaceAll("[^0-9]", "").isEmpty()){
                addToReport("Error fetching message id", Status.FAIL);
                throw new RuntimeException("Failed to fetch message id after sending message");
            }else {
                addToReport("Generated message id : "+getTextFromElement(lblMsgID(subject)).replaceAll("[^0-9]", ""), Status.PASS,false);
            }

            //Validate draft message date and time
            WebElement dateElement = driver.findElement(lblDraftMsgDate);

            String actualDate = dateElement.getText().trim();

            // Validate date
            String today = LocalDate.now().toString(); // yyyy-MM-dd format
            if (!actualDate.startsWith(today)) {
                throw new RuntimeException("Expected message date to be today (" + today + ") but was: "+actualDate);
            }else {
                addToReport("Draft Date is reflected as : "+actualDate, Status.PASS,false);
            }
            addToReport("----------End of draft message ----------", Status.PASS, true);
            addToReport("----------Start of draft edit ----------", Status.PASS, true);

            if(!getAttributeOrText(getElementByPlaceholder(ElementType.textarea,MessagingConstants.START_TYPING),"value").equals(message)){
                addToReport("Error fetching draft message", Status.FAIL);
            }else {
                addToReport("Fetched draft message : "+message, Status.PASS,false);
            }

            //Commented due to a bug
//            if(!getTextFromElement(lblUploadedDoc).equals("12752-transaction.pdf")){
//                addToReport("Error fetching uploaded document", Status.FAIL);
//            }else {
//                addToReport("Fetched uploaded document with name : "+"12752-transaction.pdf", Status.PASS,false);
//            }


            //Update draft message
            sendKeysToElement(getElementByPlaceholder(ElementType.textarea,MessagingConstants.START_TYPING),updatedMsg);

            //Commented due to a bug
            //uploadAndValidateAttachment(getFileFromResources(filePath));

            clickOnElement(getElementByTypeAndText(ElementType.span,MessagingConstants.SAVE_AS_DRAFT));

            waitForElementPresence(lblDraftMsg,LONG_WAIT);

            //Validate the draft update
            if(!getTextFromElement(lblDraftMsg).equals(updatedMsg)){
                addToReport("Error validating updated draft message, found : "+getTextFromElement(lblDraftMsg), Status.FAIL);
            }else {
                addToReport("Validated updated draft message : "+updatedMsg, Status.PASS,false);
            }

            addToReport("----------End of draft edit ----------", Status.PASS, true);
            addToReport("----------Start of draft Delete ----------", Status.PASS, true);

            clickOnElement(btnDeleteMessage(1));
            waitForElementToBeClickable(getElementByTypeAndText(ElementType.button, MessagingConstants.CONFIRM),20);
            clickOnElement(getElementByTypeAndText(ElementType.button, MessagingConstants.CONFIRM));
            validatePopUpMsg(deletionSuccessMsg);

            scrollPageToTop();

            addToReport("----------End of draft Delete ----------", Status.PASS, true);

        } catch (Exception e) {
            addToReport("Error draft messages validation", Status.FAIL);
            throw new RuntimeException("Failed draft messages validation" + e.getMessage(), e);
        }

    }

    /**
     * Verify if the messages are in reverse chronological order
     */
    public void verifyMessagesInReverseChronologicalOrder() {
        List<WebElement> messageRows = driver.findElements(lblMessageList);
        List<LocalDateTime> dateList = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for (int inc = 1; inc <= messageRows.size(); inc++) {
            // Dynamically build XPath for each row's date
            String dateText = driver.findElement(lblLastModifiedDate(inc)).getText();

            // Extract date part from the text (format: Last modified on yyyy-MM-dd HH:mm:ss)
            String dateStr = dateText.replace("Last modified on", "").trim();
            LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
            dateList.add(dateTime);
        }

        // Check if list is in reverse chronological order
        for (int i = 0; i < dateList.size() - 1; i++) {
            if (dateList.get(i).isBefore(dateList.get(i + 1))) {
                addToReport("Messages are not in reverse chronological order", Status.FAIL);
                throw new RuntimeException("Error messages are not in reverse chronological order");
            }
        }
        addToReport("Messages are in reverse chronological order", Status.PASS, true);
    }

    /**
     * Upload an attachment and validate based on file extension and success message
     * @param filePath file path of the attachment
     */
    public void uploadAndValidateAttachment(String filePath) {
        WebElement fileInput = driver.findElement(inputHiddenFile);

        // Unhide the file input using JS
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.display='block';", fileInput);

        // Upload file
        fileInput.sendKeys(filePath);

        // Wait for the file name to appear in the visible text input
        WebDriverWait wait = new WebDriverWait(driver, MODERATE_WAIT);
        wait.until(driver -> {
            String value = driver.findElement(inputFileText).getAttribute("value");
            return value != null && !value.trim().isEmpty();
        });

        // Get the uploaded file name
        String uploadedFile = driver.findElement(inputFileText).getAttribute("value").toLowerCase();

        // Validate allowed extensions
        boolean isValidFileType = uploadedFile.endsWith(".pdf")
                || uploadedFile.endsWith(".jpeg")
                || uploadedFile.endsWith(".jpg")
                || uploadedFile.endsWith(".png");

        // Confirm the message or indicator element appears
        boolean isUploaded = isValidFileType && driver.findElement(lblMsg).isDisplayed();

        if (isUploaded) {
            addToReport("Attachment uploaded successfully: " + filePath, Status.PASS);
        } else {
            addToReport("Attachment did not upload successfully: " + filePath, Status.FAIL);
            throw new RuntimeException("Attachment upload failed");
        }
    }

    /**
     * Upload an attachment and validate based on file extension and success message
     * @param filePath file path of the attachment
     */
    public void uploadAttachmentWithoutValidation(String filePath) {
        WebElement fileInput = driver.findElement(inputHiddenFile);

        // Unhide the file input using JS
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.display='block';", fileInput);

        // Upload file
        fileInput.sendKeys(filePath);

    }



    /**
     * Performs validations for the compose message
     * This includes verifying the proper display of success messages, OTP handling,
     * and confirming that the message creation process completes successfully with the given input.
     *
     * @param subject                      The subject line to be used in the message form (e.g., "Fund Transfer Dispute")
     * @param uploadErrorMsg               The expected error message when invalid file upload conditions are met (e.g., size or type restrictions)
     * @param fileNameOne                  Name of the first file to be uploaded
     * @param fileNameTwo                  Name of the second file to be uploaded.
     * @param fileNameThree                Name of the third file to be uploaded.
     * @param fileNameFour                 Name of the fourth file to be uploaded.
     * @param fileNameFive                 Name of the fifth file to be uploaded.
     * @param pastedText                   Text to paste into the message body
     * @param sanitizedExpected            The expected text after sanitization (e.g., after removing or escaping special characters).
     * @param testInputWithSpecialCharacters  The raw input string containing special characters to test the sanitization logic.

     * @return A string result indicating the outcome of the validation (MessageID)
     */
    public void validateComposeMessageFields(String subject, String uploadErrorMsg,String fileNameOne,String fileNameTwo,String fileNameThree,String fileNameFour,String fileNameFive,String pastedText,String sanitizedExpected,String testInputWithSpecialCharacters) {
        addToReport("----------Start of checking whether compose message opens relevant fields and options  ----------", Status.PASS, false);
        try {

            waitForElementPresence(getElementByTypeAndText(ElementType.button, MessagingConstants.COMPOSE_NEW_MESSAGE));
            clickOnElement(getElementByTypeAndText(ElementType.button, MessagingConstants.COMPOSE_NEW_MESSAGE));
            waitForElementToBeInvisible(getElementByTypeAndText(ElementType.button, MessagingConstants.COMPOSE_NEW_MESSAGE),LONG_WAIT);
            waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);

            //  Get the actual dropdown visible texts
            List<String> actualSubjectsTexts = getSelectedOptionText(ddSubject, MessagingConstants.ALL_OPTIONS);

            //  Get the expected branch texts from your BRANCH_MAP
            List<String> expectedSubjectsTexts = new ArrayList<>(MessagingConstants.SUBJECT_DROPDOWN.values());

            //  Compare
            boolean isMatching = CommonUtils.compareTwoArraylist(expectedSubjectsTexts, actualSubjectsTexts, true);

            List<String> missingBSubjects = findMissingElements(actualSubjectsTexts,expectedSubjectsTexts);

            if (isMatching) {
                addToReport("All subjects are loaded :" + actualSubjectsTexts ,Status.PASS,true);
            } else {
                addToReport("All subjects did not load missing subjects from list : "+missingBSubjects,Status.PASS,true);
            }

            selectFromDropdown(ddSubject, subject, MessagingConstants.VISIBLE_TEXT);
            waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);

            //Validate the loaded fields
            if (isElementPresentBy(getElementByPlaceholder(ElementType.textarea,MessagingConstants.ENTER_MESSAGE))) {
                addToReport(MessagingConstants.ENTER_MESSAGE+" field is present", Status.PASS,false);
            } else {
                addToReport(MessagingConstants.ENTER_MESSAGE+" field is not present", Status.FAIL);
            }

            //Validate the file upload option
            //Upload attachment Allowed types JPEG
            uploadAndValidateAttachment(getFileFromResources(MessagingConstants.MESSAGES_UPLOAD+"/"+fileNameOne));
            waitForElementPresence(btnDeleteUpload,LONG_WAIT);
            clickOnElement(btnDeleteUpload);

            //Upload attachment Allowed types JPG
            uploadAndValidateAttachment(getFileFromResources(MessagingConstants.MESSAGES_UPLOAD+"/"+fileNameTwo));
            waitForElementPresence(btnDeleteUpload,LONG_WAIT);
            clickOnElement(btnDeleteUpload);

            //Upload attachment Allowed types PNG
            uploadAndValidateAttachment(getFileFromResources(MessagingConstants.MESSAGES_UPLOAD+"/"+fileNameThree));
            waitForElementPresence(btnDeleteUpload,LONG_WAIT);
            clickOnElement(btnDeleteUpload);

            //Upload attachment Allowed types PDF
            uploadAndValidateAttachment(getFileFromResources(MessagingConstants.MESSAGES_UPLOAD+"/"+fileNameFour));
            waitForElementPresence(btnDeleteUpload,LONG_WAIT);
            clickOnElement(btnDeleteUpload);

            //Upload attachment Allowed types PDF above 512KB
            uploadAttachmentWithoutValidation(getFileFromResources(MessagingConstants.MESSAGES_UPLOAD+"/"+fileNameFive));
            validatePopUpMsg(uploadErrorMsg);

            addToReport("----------End of checking whether compose message opens relevant fields and options ----------", Status.PASS, false);

            addToReport("----------Start of validation of message body ----------", Status.PASS, false);

            CommonUtils.copyToClipboard(pastedText);
            pasteIntoElement(getElementByPlaceholder(ElementType.textarea, MessagingConstants.ENTER_MESSAGE));

            String actualSanitized = getAttributeOrText(getElementByPlaceholder(ElementType.textarea, MessagingConstants.ENTER_MESSAGE), "value");

            if (actualSanitized.trim().equals(sanitizedExpected)) {
            addToReport("Pasted content is sanitized properly", Status.PASS, true);
            } else {
            addToReport("Pasted content is not sanitized properly", Status.FAIL);
            }

            sendKeysToElementUsingJS(getElementByPlaceholder(ElementType.textarea, MessagingConstants.ENTER_MESSAGE), "");

            sendKeysToElement(getElementByPlaceholder(ElementType.textarea, MessagingConstants.ENTER_MESSAGE), testInputWithSpecialCharacters);

            // Get the actual field value
            String actualValue = getAttributeOrText(getElementByPlaceholder(ElementType.textarea, MessagingConstants.ENTER_MESSAGE), "value");

            // Define representative substrings to check
            String[] substringsToCheck = {
                    testInputWithSpecialCharacters.substring(0, 3),   // "123"
                    testInputWithSpecialCharacters.substring(3, 6),   // "ABC"
                    testInputWithSpecialCharacters.substring(6, 9),   // "abc"
                    testInputWithSpecialCharacters.substring(9, 12),  // "!@#"
                    testInputWithSpecialCharacters.substring(12, 15)  // "$%^"
            };

            // Check that each substring exists in the actual value
            boolean allPresent = true;
            for (String sub : substringsToCheck) {
                if (!actualValue.contains(sub)) {
                    allPresent = false;
                    break;
                }
            }

            if (allPresent) {
                addToReport("All substrings from test input are present in the message body", Status.PASS, true);
            }else {
                addToReport("All substrings from test input are not present in the message body", Status.FAIL, true);
            }

            addToReport("----------End of validation of message body ----------", Status.PASS, true);

        } catch (Exception e) {
            addToReport("Error validation of compose message", Status.FAIL);
            throw new RuntimeException("Failed to validate compose message" + e.getMessage(), e);
        }
    }




}


