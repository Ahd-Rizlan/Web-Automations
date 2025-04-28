/*
 *   @author - salman R
 */
package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.CommonUtils;
import utils.constants.MessagingConstants;

import java.util.*;

public class MessagesPage extends BasePage {

    public MessagesPage(WebDriver driver) {
        super(driver);
    }

    private static final By imgGreyLoader = By.xpath("//div[contains(@class,'bg-gray')]");
    private static final By ddSubject = By.xpath("//select[@id='subject']");
    private static final By tfMessage = By.xpath("//div[@class='relative']/textarea");
    private static final By ddSubCategory = By.xpath("//select[@id='subjectSubCategory']");
    private static final By ddFromAccount = By.xpath("//select[@id='fromAccount']");
    private static final By ddBranchList = By.xpath("//select[@id='branchList']");
    private static final By ddPurpose = By.xpath("//select[@id='purpose']");
    private static final By lnkUploadAttachement = By.xpath("//input[@type='file']");
    private static final By lblMsg = By.xpath(".//div[contains(text(), 'Last modified on')]");
    private static final By allMessages = By.cssSelector("div.LeftInboxContainer_scroll__BKnAZ > div");
    private static final By lblMsgID = By.xpath("//span[text()='Fixed Deposit Inquiry']/parent::div/span[2]");
    private static final By btnClosePopup = By.xpath("//button[contains(@aria-label,'close')]");
    private static final By ddBank = By.xpath("//select[@id='bank']");

    public enum ElementType {
        button, label, span, div, textarea, input;
    }

    private static By getMsg(String title) {
        return By.xpath("//div[contains(text(),'" + title + "')]");
    }

    private static By tfOTP(int Index) {
        return By.xpath("//input[@type='password'][" + Index + "]");
    }
    //    private static By tfInputPlaceHolder(String placeHolderText) {
//        return By.xpath("//input[@placeholder=\"" + placeHolderText + "\"]");
//    }
    private static By rdoBenificiaryAccountType(String value) {
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
     *
     * @param subject
     * @param branch
     * @param msg
     * @param successMsg
     * @param OTP
     * @param messageCreationSuccessMsg
     * @return
     */
    public String fixedDepositInquiry(String subject, String branch, String msg, String successMsg, String OTP, String messageCreationSuccessMsg) {
        addToReport("----------Start of Checking whether after selecting the Fixed deposit inquiry subject , branch list is displayed ----------", Status.PASS, false);
        try {

            waitForElementPresence(getElementByTypeAndText(ElementType.button, MessagingConstants.COMPOSE_NEW_MESSAGE));
            clickOnElement(getElementByTypeAndText(ElementType.button, MessagingConstants.COMPOSE_NEW_MESSAGE));
            waitForElementToBeInvisible(imgGreyLoader,20);

            selectFromDropdown(ddSubject, subject, MessagingConstants.VISIBLE_TEXT);
            waitForElementToBeInvisible(imgGreyLoader,20);

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
        addToReport("----------Start of Checking whether a mail upon selecting a branch user can send a mail to a selected branch ----------", Status.PASS, false);
        selectFromDropdown(ddBranchList,branch, MessagingConstants.VISIBLE_TEXT);

        sendKeysToElement(getElementByPlaceholder(ElementType.textarea,MessagingConstants.ENTER_MESSAGE),msg);

//        sendKeysToElement(lnkUploadAttachement,"C:\\Projects\\sampath\\svr4-api-automations\\svr4-api-automations\\screenshot1.png");

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
        clickOnElement(latestMessage);

        scrollPageToTop();
        waitForElementPresence(getElementByTypeAndText(ElementType.span,subject));

        addToReport("----------End of Checking whether a mail upon selecting a branch user can send a mail to a selected branch ----------", Status.PASS, false);
        return getTextFromElement(getElementByTypeAndText(ElementType.span,subject)).replaceAll("[^0-9]", "");

    }

    /**
     * Card center message validation
     * @param subject
     * @param msg
     * @param successMsg
     * @param OTP
     * @param messageCreationSuccessMsg
     * @return
     */
    public String cardCenterValidations(String subject, String subCategory, String msg, String successMsg, String OTP, String messageCreationSuccessMsg) {
        addToReport("----------Start of Checking whether after selecting the Fixed deposit inquiry subject , branch list is displayed ----------", Status.PASS, false);
        try {

            waitForElementPresence(getElementByTypeAndText(ElementType.button, MessagingConstants.COMPOSE_NEW_MESSAGE));
            clickOnElement(getElementByTypeAndText(ElementType.button, MessagingConstants.COMPOSE_NEW_MESSAGE));
            waitForElementToBeInvisible(imgGreyLoader, 20);

            selectFromDropdown(ddSubject, subject, MessagingConstants.VISIBLE_TEXT);
            waitForElementToBeInvisible(imgGreyLoader, 20);

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

            //sendKeysToElement(lnkUploadAttachement,"C:\\Projects\\sampath\\svr4-api-automations\\svr4-api-automations\\screenshot1.png");

            clickOnElement(getElementByTypeAndText(ElementType.button,MessagingConstants.SEND));

            validatePopUpMsg(successMsg);

            waitForElementPresence(getElementByTypeAndText(ElementType.button,MessagingConstants.CONFIRM),20);

            //Enter OTP values and continue
            sendKeysToElement(tfOTP(1), String.valueOf(OTP));

            clickOnElement(getElementByTypeAndText(ElementType.button,MessagingConstants.CONFIRM));
            validatePopUpMsg(messageCreationSuccessMsg);

            waitForElementPresence(getElementByPlaceholder(ElementType.input,MessagingConstants.SEARCH_MESSAGES));
            sendKeysToElement(getElementByPlaceholder(ElementType.input,MessagingConstants.SEARCH_MESSAGES),subCategory);

            List<WebElement> messages = findElements(allMessages);
            WebElement latestMessage = getLatestElementByDate(messages, getElementByTypeAndText(ElementType.div,MessagingConstants.LAST_MODIFIED_ON), MessagingConstants.LAST_MODIFIED_ON);
            clickOnElement(latestMessage);

            scrollPageToTop();
            waitForElementPresence(getElementByTypeAndText(ElementType.span,subCategory));

            // Do admin validation


        } catch (Exception e) {
            addToReport("Error logging into retail admin", Status.FAIL);
            throw new RuntimeException("Failed log into retail admin" + e.getMessage(), e);
        }
        return getTextFromElement(getElementByTypeAndText(ElementType.span,subCategory)).replaceAll("[^0-9]", "");
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
    public String fundTransferRequestValidations(String subject, int amount, String msg, String successMsg, String OTP, String messageCreationSuccessMsg) {
        addToReport("----------Start of Checking whether Selecting ‘Fund Transfer Request’ displays fields ----------", Status.PASS, false);
        try {

            waitForElementPresence(getElementByTypeAndText(ElementType.button, MessagingConstants.COMPOSE_NEW_MESSAGE));
            clickOnElement(getElementByTypeAndText(ElementType.button, MessagingConstants.COMPOSE_NEW_MESSAGE));
            waitForElementToBeInvisible(imgGreyLoader, 20);

            selectFromDropdown(ddSubject, subject, MessagingConstants.VISIBLE_TEXT);
            waitForElementToBeInvisible(imgGreyLoader, 20);

            //Validate the loaded fields

            if (isElementPresentBy(getElementByPlaceholder(ElementType.input,MessagingConstants.ENTER_AMOUNT))) {
                addToReport("Amount field is present", Status.PASS,false);
            } else {
                addToReport("Amount field is not present", Status.FAIL);
            }
            if (isElementPresentBy(getElementByPlaceholder(ElementType.input,MessagingConstants.SAMPATH))) {
                addToReport("Radio sampath field is present", Status.PASS,false);
            } else {
                addToReport("Radio sampath field is not present", Status.FAIL);
            }
            if (isElementPresentBy(getElementByPlaceholder(ElementType.input,MessagingConstants.OTHER))) {
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
            clickOnElement(getElementByPlaceholder(ElementType.input,MessagingConstants.OTHER));
            if (isElementPresentBy(ddBank)) {
                addToReport("Dropdown bank field is present", Status.PASS,false);
            } else {
                addToReport("Dropdown bank is not present", Status.FAIL);
            }

            addToReport("----------End of Checking whether Selecting ‘Fund Transfer Request’ displays fields ----------", Status.PASS, false);
            addToReport("----------Start of Checking entering the amount ----------", Status.PASS, false);

            clickOnElement(getElementByPlaceholder(ElementType.input,MessagingConstants.SAMPATH));

            selectFromDropdown(ddFromAccount,"3","index");

            sendKeysToElement(getElementByPlaceholder(ElementType.input,MessagingConstants.ENTER_AMOUNT), String.valueOf(amount));

            // Obtain the selected value from the dropdown
            List<String> fromAccDropdownValue = getSelectedOptionText(ddFromAccount, "FIRST_SELECTED");

            String[] amt = fromAccDropdownValue.get(0).split("LKR ");

            // Convert to double
            double amountDouble = Double.parseDouble(amt[1]);

            // Convert to whole number (round or cast)
            int wholeAmount = (int) Math.round(amountDouble);

            if (wholeAmount> amount){
                addToReport("Entered amount : "+amount+" is less than  the amount in account account : '" + wholeAmount , Status.PASS);
            }else {
                addToReport("Entered amount : "+amount+" is greater than  the amount in account account : '" + wholeAmount, Status.FAIL);
                throw new RuntimeException("Not sufficient funds in the account");
            }

//            sendKeysToElement(getElementByPlaceholder(ElementType.input,MessagingConstants.ENTER_AMOUNT),amount);
//            Do admin validation

            addToReport("----------End of Checking entering the amount ----------", Status.PASS, false);

        } catch (Exception e) {
            addToReport("Error logging into retail admin", Status.FAIL);
            throw new RuntimeException("Failed log into retail admin" + e.getMessage(), e);
        }
        return getTextFromElement(getElementByTypeAndText(ElementType.span,"")).replaceAll("[^0-9]", "");
    }


    public void validatePopUpMsg(String msg) {
        //waitForElementToBeInvisible(btnLogin,20);
        waitForElementPresence(getMsg(msg),20);
        //Validate the success message
        if (isElementPresentBy(getMsg(msg))) {
            addToReport("'" + msg + "'  message is present.", Status.PASS,true);
        } else {
            addToReport("'" + msg + "'  message is not present.", Status.FAIL);
        }
        clickOnElement(btnClosePopup);
    }

}


