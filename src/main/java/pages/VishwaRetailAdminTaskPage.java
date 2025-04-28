/*
 *   @author - salman R
 */
package pages;

import com.aventstack.extentreports.Status;
import groovyjarjarantlr4.v4.runtime.atn.LexerPopModeAction;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import utils.CommonUtils;
import utils.constants.AdminTaskConstants;

import java.util.Set;

public class VishwaRetailAdminTaskPage extends BasePage {

    public VishwaRetailAdminTaskPage(WebDriver driver) {
        super(driver);
    }

    public enum ElementType {
        button, label, span, div;
    }
    private static final By pnlFilter = By.xpath("//div[@data-side='bottom']");
    private static final By tfKeyWord = By.xpath("//input[@id='keyword']");
    private static final By icnButtonSpinner = By.xpath("//div[contains(@class,'animate-spin')]");
    private static final By btnSearch = By.xpath("//div[contains(@class,'flex justify')]/button[contains(@class,'solid-base')]");
    private static final By lnkMailRowCount = By.xpath("//div[contains(@class,'flex flex-row py')]");
    private static final By ddAppearance = By.xpath("//select[contains(@class,'appearance-none')]");
    private static final By tfAddMessage = By.xpath("//div[@role='textbox']");
    private static final By lblRepliedMessages = By.xpath("//div[@class='inline']/p");


    private static By lblPageTile(String tileText) {
        return By.xpath("//p[contains(text(),\"" + tileText + "\")]");
    }
    private static By dpDate(String kwHeader) {
        return By.xpath("//span[text()='" + kwHeader + "']/parent::div/div/span");
    }
    private static By lnkRow(int index) {
        return By.xpath("(//div[contains(@class,'flex flex-row py')])[" + index + "]");
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
    private static By getElementByTypeAndText(LoginPage.ElementType type, String text) {
        return By.xpath("//" + type.name() + "[contains(normalize-space(text()), \"" + text + "\")]");
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

        // Click the filter button based on the keyword filter text
        clickOnElement(getElementByTypeAndText(LoginPage.ElementType.button, AdminTaskConstants.FILTER));

        // Wait for the filter panel to be visible (timeout: 10 seconds)
        waitForElementPresence(pnlFilter, 10);

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
        waitForElementToBeInvisible(icnButtonSpinner, 20);

        // Re-click the keyword filter button (To close the filter panel)
        clickOnElement(getElementByTypeAndText(LoginPage.ElementType.button, AdminTaskConstants.FILTER));
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
     */
    public void performMailAction(String buttonName) {
        try {
            boolean isButtonVisible = waitForElementPresence(getElementByTypeAndText(LoginPage.ElementType.button, buttonName));

            if (isButtonVisible) {
                addToReport("Button name '" + buttonName + "' is visible", Status.PASS, false);
            } else {
                addToReport("Button name is not visible", Status.FAIL);
                throw new RuntimeException("Error validating button");
            }

            waitForElementToBeClickable(getElementByTypeAndText(LoginPage.ElementType.button, buttonName),20);

            clickOnElement(getElementByTypeAndText(LoginPage.ElementType.button, buttonName));
            addToReport("Successfully selected the '" + buttonName + "' button ", Status.PASS,true);
        } catch (Exception e) {
            addToReport("Failed to select the '" + buttonName + "' button", Status.FAIL);
            throw new RuntimeException("Failed to select tab" + e.getMessage(), e);
        }
    }


    /**
     * Read received mail and forward the mail
     * @param subject       - Message subject
     * @param type          - keywords such as using "Keyword" or "Date" based on this the respective parameters has to be filled
     * @param keyWordText   - Key word to be entered in the text field
     */
    private void readReceivedMailAndForward(String type, String keyWordText,String subject) {
        addToReport("----------Start of validation of received mail----------", Status.PASS, false);

        // Wait for the mail list to load
        waitForElementPresence(getElementByTypeAndText(LoginPage.ElementType.button, AdminTaskConstants.FILTER), 10);

        filterMails(type,keyWordText,"","");

        //Subject and reference have to come from client
        selectMail(keyWordText,subject);
        selectTaskHeader(AdminTaskConstants.INBOX);
        boolean isButtonVisible = waitForElementPresence(getElementByTypeAndText(LoginPage.ElementType.button, "Reply"));

        if (isButtonVisible) {
            addToReport("Button name ' Reply ' is visible", Status.PASS, false);
        } else {
            addToReport("Button name is not visible", Status.FAIL);
            throw new RuntimeException("Error validating button");
        }
        performMailAction(AdminTaskConstants.FORWARD);

        //Validate the mail thread using index from locator where last message is the recent msg

        // WIP -------------------------


        addToReport("----------End of validation of received mail----------", Status.PASS, false);

    }


}


