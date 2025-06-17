/*
 *   @author - salman R
 */
package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.CommonUtils;
import utils.constants.AdminTaskConstants;
import utils.constants.MessagingConstants;

import static utils.Drivers.*;

public class VishwaRetailAdminTaskPage extends BasePage {

    public VishwaRetailAdminTaskPage(WebDriver driver) {
        super(driver);
    }

    public enum ElementType {
        button, label, span, div, textarea, input
    }
    private static final By pnlFilter = By.xpath("//div[@data-side='bottom']");
    private static final By tfKeyWord = By.xpath("//input[@id='keyword']");
    private static final By icnButtonSpinner = By.xpath("//div[contains(@class,'animate-spin')]");
    private static final By btnSearch = By.xpath("//div[contains(@class,'flex justify')]/button[contains(@class,'solid-base')]");
    private static final By lnkMailRowCount = By.xpath("//div[contains(@class,'flex flex-row py')]");
    private static final By ddBranch = By.xpath("//select[contains(@class,'appearance-none')]");
    private static final By tfAddMessage = By.xpath("//div[@role='textbox']");
    private static final By lblRepliedMessages = By.xpath("//div[@class='inline']/p");
    private static final By btnForward = By.xpath("//select[contains(@class,'appearance-none')]");
    private static final By lblMessageThread = By.xpath("//div[@class='inline']");
    private static final By lblRepliedUser = By.xpath("//div[contains(@class,'flex flex-col items-end')]/p[1]");
    private static final By lblUsersInThread = By.xpath("//div[contains(@class,'flex flex-col items')]/p[1]");
    private static final By lblMessagesInThread = By.xpath("//div[@class='inline']");

    private static final By btnForwardPopup = By.xpath("//div[@role='tablist']//button[text()=\"Forward\"]");




    private static By btnUsingWildCard(String name) {
        return By.xpath("(//button[contains(., '" + name + "')])[2]");
    }
    private static By dpDate(String kwHeader) {
        return By.xpath("//span[text()='" + kwHeader + "']/parent::div/div/span");
    }
    private static By lnkRow(int index) {
        return By.xpath("(//div[contains(@class,'flex flex-row py')])[" + index + "]");
    }
    private static By lblUserList(int index) {
        return By.xpath("(//div[contains(@class,'flex flex-col items')]/p[1])[" + index + "]");
    }
    private static By lblMsgList(int index) {
        return By.xpath("(//div[@class='inline'])[" + index + "]");
    }
    private static By lnkRowSubject(int index) {
        return By.xpath("(//div[contains(@class,'flex flex-row py')]/div[2]/div/p[1])[" + index + "]");
    }
    private static By lnkRowReference(int index) {
        return By.xpath("(//div[contains(@class,'flex flex-row py')]//div[2]/p)[" + index + "]");
    }
    private static By lblMailReference(String mailSubject) {
        return By.xpath("//p[contains(normalize-space(text()), '" + mailSubject + "')]/ancestor::div[contains(@class,'border-b-gray')]//div[2]/p");
    }
    private static By getElementByTypeAndText(ElementType type, String text) {
        return By.xpath("//" + type.name() + "[contains(normalize-space(text()), \"" + text + "\")]");
    }
    private static By getElementByTypeAndExactText(ElementType type, String text) {
        return By.xpath("//" + type.name() + "[text()= \"" + text + "\"]");
    }
    private static By btnTaskTab(String taskTabHeader) {
        return By.xpath("//div[@role='tablist']/button[contains(normalize-space(text()), '" + taskTabHeader + "')]");
    }



    /**
     * filter mail
     * @param type          keywords such as using "Keyword" or "Date" based on this the respective parameters has to be filled
     * @param keyWordText   Key word to be entered in the text field
     * @param fromDate      From date
     * @param toDate        to date
     */
    public void filterMails(String type, String keyWordText, String fromDate, String toDate) {

        waitForElementPresence(getElementByTypeAndExactText(ElementType.span, AdminTaskConstants.FILTER),LONG_WAIT);
        waitForElementToBeClickable(getElementByTypeAndExactText(ElementType.span, AdminTaskConstants.FILTER),LONG_WAIT);

        // Click the filter button based on the keyword filter text
        clickOnElement(getElementByTypeAndExactText(ElementType.span, AdminTaskConstants.FILTER));

        // Wait for the filter panel to be visible (timeout: 10 seconds)
        waitForElementPresence(pnlFilter, MODERATE_WAIT);

        // Check if the selected type is keyword-based filtering
        if (type.equals(AdminTaskConstants.KEYWORD)) {

            // Enter the keyword text into the keyword input field
            sendKeysToElement(tfKeyWord, keyWordText);

        } else if (type.equals(AdminTaskConstants.DATE)) {

            // Enter the 'from' date in the date picker
            sendKeysToElement(dpDate(AdminTaskConstants.FROM_DATE), fromDate);

            // Enter the 'to' date in the date picker
            sendKeysToElement(dpDate(AdminTaskConstants.TO_DATE), toDate);
        }else {
            addToReport("Error obtaining filtered value", Status.FAIL);
        }

        // Click the search button
        clickOnElement(btnSearch);

        // Wait for the search spinner icon to disappear (timeout: 20 seconds)
        waitForElementToBeInvisible(icnButtonSpinner, LONG_WAIT);

        // Re-click the keyword filter button (To close the filter panel)
        clickOnElement(getElementByTypeAndExactText(ElementType.span, AdminTaskConstants.FILTER));
    }

    /**
     * Select appropriate mail
     * @param mailReference
     * @param mailSubject
     */
    public void selectMail(String mailReference,String mailSubject) {

        //validate the mail list availability
        int recordCount = isElementsPresentBy(lnkMailRowCount);
        if (recordCount != 0) {

            //Search if appropriate subject appears
            if (mailReference.equals(CommonUtils.removeLeadingCharacters(getTextFromElement(lnkRowReference(1)),'0'))){

                clickOnElement(lnkRowSubject(1));
            }else {
                addToReport("Error selecting mail with reference : "+mailReference, Status.FAIL);
                throw new RuntimeException("Failed to select mail");
            }

            //Validate opened reference
            if (isElementPresentBy(lblMailReference(mailSubject))) {
                addToReport("Selected the correct mail with subject :"+mailSubject+" and reference :"+mailReference, Status.PASS, true);
            } else {
                addToReport("Didn't select correct mail with subject :"+mailSubject+" and reference :"+mailReference, Status.FAIL);
            }
        }
    }

    /**
     * Selects the task header
     *
     * @param taskHeader header tab option
     */
    public void selectTaskHeader(String taskHeader) {
        try {
            waitForElementPresence(btnTaskTab(taskHeader));
            clickOnElement(btnTaskTab(taskHeader));
            addToReport("Successfully selected the '" + taskHeader + "' tab ", Status.PASS,false);
        } catch (Exception e) {
            addToReport("Failed to select the '" + taskHeader + "' tab", Status.FAIL);
            throw new RuntimeException("Failed to select tab" + e.getMessage(), e);
        }
    }

    /**
     * Selects mail action
     *
     * @param buttonName - button name
     * @param isClicked  - clicks on  the button if true
     */
    public void performMailAction(String buttonName,boolean isClicked) {
        try {
            boolean isButtonVisible = waitForElementPresence(btnUsingWildCard(buttonName));

            if (isButtonVisible) {
                addToReport("Button name '" + buttonName + "' is visible", Status.PASS, false);
            } else {
                addToReport("Button name is not visible", Status.FAIL);
                throw new RuntimeException("Error validating button");
            }

            if(isClicked) {
                waitForElementToBeClickable(btnUsingWildCard(buttonName), LONG_WAIT);
                clickOnElement(btnUsingWildCard(buttonName));
                addToReport("Successfully selected the '" + buttonName + "' button ", Status.PASS, true);
            }
        } catch (Exception e) {
            addToReport("Failed to select the '" + buttonName + "' button", Status.FAIL);
            throw new RuntimeException("Failed to select tab" + e.getMessage(), e);
        }
    }


    /**
     * Read received mail and forward the mail
     * @param subject       - Message subject
     * @param mailReference   - Key word to be entered in the text field
     */
    public void readReceivedMailAction(String mailReference, String subject) {

        // Wait for the mail list to load
        waitForElementPresence(getElementByTypeAndExactText(ElementType.span, AdminTaskConstants.FILTER), MODERATE_WAIT);

        //Subject and reference have to come from client
        selectMail(mailReference,subject);
        selectTaskHeader(AdminTaskConstants.INBOX);
        boolean isButtonVisible = waitForElementPresence(getElementByTypeAndText(ElementType.button, AdminTaskConstants.REPLY));

        if (isButtonVisible) {
            addToReport("Button name ' Reply ' is visible", Status.PASS, false);
        } else {
            addToReport("Button name is not visible", Status.FAIL);
            throw new RuntimeException("Error validating button");
        }

        performMailAction(AdminTaskConstants.REPLY, false);
        performMailAction(AdminTaskConstants.FORWARD, true);

    }


    /**
     * Forward the mail and validate
     * @param subject   The subject or title of the message
     * @param branch    The branch the message is associated with
     * @param message   The actual content of the message
     * @param user      The user who is sending or associated with the message
     */
    public void forwardMailAndValidate(String subject,String branch,String message,String user) {

        // Forward from admin branch then validate the mail in thread
        // Wait for the mail list to load
        waitForElementPresence(getElementByTypeAndExactText(ElementType.button, AdminTaskConstants.REPLY), MODERATE_WAIT);

        clickOnElement(btnForwardPopup);

        waitForElementPresence(ddBranch);

        selectFromDropdown(ddBranch, branch, MessagingConstants.VISIBLE_TEXT);

        sendKeysToElement(tfAddMessage,message);

        clickOnElement(getElementByTypeAndExactText(ElementType.button, AdminTaskConstants.SEND));
        waitForElementToBeInvisible(getElementByTypeAndExactText(ElementType.button, AdminTaskConstants.SEND),LONG_WAIT);

        //Validate opened reference
        if (message.equals(getTextFromElement(lblRepliedMessages))) {
            addToReport("Message thread is loaded with message:"+message, Status.PASS, false);
        } else {
            addToReport("Message thread is not loaded with message:"+message, Status.FAIL);
        }

        //Obtain the record count
        int msgCount = isElementsPresentBy(lblMessageThread);
        if (msgCount == 2) {
            addToReport(" Message thread is available as expected", Status.PASS,false);
        }else {
            addToReport(" Message thread is not available as expected as the number of messages in thread is : "+msgCount, Status.FAIL,true);
            throw new RuntimeException("Error - Mail thread did not display appropriately");
        }

        //Validate subject
        if (isElementPresentBy(lblMailReference(subject))) {
            addToReport("Validated forwarded message subject :"+subject, Status.PASS, true);
        } else {
            addToReport("Didn't validate correct message thread with subject :"+subject, Status.FAIL);
        }

        //Validate admin user
        if (user.equals(getTextFromElement(lblRepliedUser))) {
            addToReport("Replied admin branch user fetched :"+user, Status.PASS, true);
        } else {
            addToReport("Replied admin branch user is not fetched ", Status.FAIL);
        }
    }

    /**
     * Validate the FD inquiry request
     * @param keyWordText - Message id for tracking messages across admin
     */
    public void validateFDInquiryRequest(String keyWordText){
        addToReport("----------Start of validation of customer sent FD inquiry request are available in admin----------", Status.PASS, false);
        filterMails(AdminTaskConstants.KEYWORD,keyWordText,"","");
        addToReport("----------End of validation of customer sent FD inquiry request are available in admin----------", Status.PASS, true);

    }

    /**
     * Validate the sent email
     * @param messageID  The unique identifier of the message
     * @param subject    The subject or title of the message
     * @param message    The content of the message
     * @param user       The user associated with the message
     * @param branch     The branch  related to the message
     */
    public void validateSentMails(String messageID,String subject,String message,String user, String branch){
        addToReport("----------Start of validation of received mail----------", Status.PASS, false);
        readReceivedMailAction(messageID,subject);
        addToReport("----------End of validation of received mail----------", Status.PASS, false);
        addToReport("----------Start of validation of forwarded mail thread is available is available in mail thread----------", Status.PASS, false);
        forwardMailAndValidate(subject,branch,message,user);
        addToReport("----------End of validation of forwarded mail thread is available is available in mail thread----------", Status.PASS, false);
    }

    /**
     * validate forwarded mails on branchs
     * @param messageID  The unique identifier for the message
     * @param subject    The  title of the message
     * @param messages   The message contents
     * @param users      The users associated with the messages
     * @param branch     The branch related to the messages
     */
    public void validateForwardedMailsBranch(String messageID,String subject,String[] messages,String[] users, String branch){

        addToReport("----------Start of validation of forwarded mail thread is available is available in mail thread----------", Status.PASS, false);

        filterMails(AdminTaskConstants.KEYWORD,messageID,"","");

        //Obtain the record count
        int userCount = isElementsPresentBy(lblUsersInThread);
        if (userCount == 2) {
            addToReport(" Message thread user count is available as expected", Status.PASS,false);
            for (int inc = 1; inc <= userCount; inc++) {
                String c = getTextFromElement(lblUserList(inc));
                    if (getTextFromElement(lblUserList(inc)).contains(users[inc-1])){
                        addToReport(" Message thread user "+getTextFromElement(lblUserList(inc))+" is available as expected", Status.PASS,false);
                    }else {
                        addToReport(" Message thread user "+users[inc]+" is not available as expected", Status.FAIL,true);
                    }
            }
        }else {
            addToReport(" Message thread users is not available as expected as the number of messages in thread is : "+userCount, Status.FAIL,true);
            throw new RuntimeException("Error - Mail thread did not display replied users appropriately");
        }

        //Obtain the record count
        int msgCount = isElementsPresentBy(lblMessagesInThread);
        if (msgCount == 2) {
            addToReport(" Message thread is available as expected", Status.PASS,false);
            for (int inc = 1; inc <= msgCount; inc++) {
                if (getTextFromElement(lblMsgList(inc)).contains(messages[inc-1])){
                    addToReport(" Message thread message "+getTextFromElement(lblMsgList(inc))+" is available as expected", Status.PASS,false);
                }else {
                    addToReport(" Message thread message "+messages[inc]+" is not available as expected", Status.FAIL,true);
                }

            }
        }else {
            addToReport(" Message thread is not available as expected as the number of messages in thread is : "+msgCount, Status.FAIL,true);
            throw new RuntimeException("Error - Mail thread did not display appropriately");
        }

        //Validate subject
        if (isElementPresentBy(lblMailReference(subject))) {
            addToReport("Validated forwarded message subject :"+subject, Status.PASS, true);
        } else {
            addToReport("Didn't validate correct message thread with subject :"+subject, Status.FAIL);
        }

        addToReport("----------End of validation of forwarded mail thread is available is available in mail thread----------", Status.PASS, false);
    }

    /**
     * Validate the card center request
     * @param messageID The unique identifier for the message
     */
    public void validateCardCenterRequest(String messageID){
        addToReport("----------Start of validation of card center request are available in admin----------", Status.PASS, false);
        filterMails(AdminTaskConstants.KEYWORD,messageID,"","");
        addToReport("----------End of validation of card center request are available in admin----------", Status.PASS, true);

    }

    /**
     * Validate fund transfer request
     * @param messageID The unique identifier for the message
     */
    public void validateFundTransferRequest(String messageID){
        addToReport("----------Start of validation of fund transfer request are available in admin----------", Status.PASS, false);
        filterMails(AdminTaskConstants.KEYWORD,messageID,"","");
        addToReport("----------End of validation of fund transfer request are available in admin----------", Status.PASS, true);

    }

    /**
     * Reply to mail
     * @param messageID The unique identifier for the message
     * @param message   The message contents
     */
    public void replyToMail(String messageID,String message){

        waitForElementPresence(getElementByTypeAndExactText(ElementType.button, AdminTaskConstants.ATTEND));
        //Attend the mail
        clickOnElement(getElementByTypeAndExactText(ElementType.button, AdminTaskConstants.ATTEND));

        waitFor(VERY_SHORT_WAIT);
        filterMails(AdminTaskConstants.KEYWORD,messageID,"","");

        //Attend the mail
        clickOnElement(getElementByTypeAndExactText(ElementType.button, AdminTaskConstants.REPLY));

        sendKeysToElement(tfAddMessage,message);

        clickOnElement(getElementByTypeAndExactText(ElementType.button, AdminTaskConstants.SEND));
        waitForElementToBeInvisible(getElementByTypeAndExactText(ElementType.button, AdminTaskConstants.SEND),LONG_WAIT);

        //Validate opened reference
        if (message.equals(getTextFromElement(lblRepliedMessages))) {
            addToReport("Message thread is loaded with message:"+message, Status.PASS, false);
        } else {
            addToReport("Message thread is not loaded with message:"+message, Status.FAIL);
        }
    }


}


