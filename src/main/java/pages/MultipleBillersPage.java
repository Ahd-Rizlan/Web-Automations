package pages;

import com.aventstack.extentreports.Status;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static utils.Drivers.*;
import static utils.constants.MultipleBillersConstants.*;

public class MultipleBillersPage extends BasePage {

    public MultipleBillersPage(WebDriver driver) {
        super(driver);
    }

    public enum ElementType {
        button, label, span, div, p;
    }

    private static final By imgGreyLoader = By.xpath("//div[contains(@class,'bg-gray')]");
    private static final By btnRightArrow = By.xpath("//img[contains(@src,'FArrowRight')]");
    private static final By btnPaginationNumbers = By.xpath("//img[contains(@src,'FArrowRight')]/ancestor::div[contains(@class,'flex justify-end')]/div[1]/div");
    private static final By icnAddToFav = By.xpath("//input[@type='checkbox' and @id='savePayee-undefined']");
    private static final By menuSavedPayee_Billers = By.xpath("//a[contains(@class,'NavBar_navlink__CRz3E')  and normalize-space(text())='Payees & Billers']");
    private static final By menuItem_SavedBillers = By.xpath("//div[contains(@class,'SubMenu_item__z9l12')  and normalize-space(text())='Saved Billers']");

    private static final By btnDashboard = By.xpath("//button/a[contains(normalize-space(text()), 'Dashboard')]");
    private static final By lblLoadingIcon = By.xpath("//div[contains(@class,'AccountsCards_loader')]");
    private static final By btnNewBiller= By.xpath("//button[normalize-space(text())='Add New Biller']");
    private static final By tblSavedBillersRows = By.xpath("//tbody//tr");
    private static final By tblSBSelectedRows = By.xpath("//tbody//tr[contains(@class,'bg-orange-300 ')]");
    private static final By tblSBNotSelectedRows = By.xpath("//tbody//tr[contains(@class,'bg-white')]");
    private static final By lblSelectedPayeeContainer = By.xpath("//div[contains(@class,'flex-wrap') and contains(@class,'justify-end')]");


    private static By lblSelectedPayee(String BillerName){
        return By.xpath("//div[contains(@class,'bg-[#F5883C]') and contains(normalize-space(.),'"+BillerName+"')]");
    }
    private static By tabHeader(String tabName) {
        return By.xpath("//button[span[normalize-space(text())='"+tabName+"']]");
    }
    private static By btnAddToList(int index) {
        return By.xpath("(//input[@type='checkbox' and @id='savePayee-undefined'])[" + index + "]");
    }
    private static By PageHeader(String headerTxt ,int index) {
        return By.xpath("(//span[normalize-space(text())='"+headerTxt+"' and parent::*[@class='flex flex-col']])["+index+"]");
    }

    private static By DynamicChangedButton(String btnName){
        return By.xpath("//button/span[normalize-space(text())='"+btnName+"']");
    }

    /**
     * Select header tab
     *
     * @param headerTab - Main tabs Eg.Send money
     */
    public void selectHeaderTab(String headerTab) {
        try {
            waitForElementPresence(tabHeader(headerTab));
            clickOnElement(tabHeader(headerTab));
            addToReport("Selected tab : " + headerTab, Status.PASS, false);
        } catch (Exception e) {
            addToReport("Selecting " + headerTab + " failed ", Status.FAIL);
            throw new RuntimeException("Failed to select tab" + e.getMessage(), e);
        }
    }

    /**
     * Validate the add favourite icons
     *
     * @param primaryTab - Header tab
     */
    public void validateDataAvailablity(String primaryTab) {
        try {
            //Select appropriate header
            selectHeaderTab(primaryTab);
            waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);
//            Use pagination to go to next
            if (isElementPresentBy(btnRightArrow)) {
                int recordCount = isElementsPresentBy(btnPaginationNumbers);
                if (recordCount != 0) {
                    for (int inc = 1; inc <= recordCount; inc++) {
                        waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);
                        waitForElementToBeClickable(btnAddToList(1), LONG_WAIT);
                        scrollToWebElement(btnAddToList(1));
                        //validate the favourite icon availability
                        int rCount = isElementsPresentBy(icnAddToFav);
                        if (rCount != 0) {
                            addToReport(rCount + " number of records are visible in page number " + inc, Status.PASS, true);
                            //Select next page
                            if (inc < recordCount) {
                                clickOnElement(btnRightArrow);
                            }
                        } else {
                            addToReport("No records found", Status.FAIL);
                        }

                    }

                }
            }

        } catch (Exception e) {
            addToReport("Error verifying Saved billers under saved billers", Status.FAIL);
            throw new RuntimeException("Failed to validate Saved billers under saved billers Tab ", e);
        }
    }

    /**
     * Navigate to saved payees through nave bar
     */
    public void navigateToPayeeAndBillers() {
        waitForElementPresence(menuSavedPayee_Billers);
        clickOnElement(menuSavedPayee_Billers);
        waitForElementPresence(menuItem_SavedBillers);
        clickOnElement(menuItem_SavedBillers);
    }
    /**
     * Validate the Page
     */
    public void validateSavedBillersPage() {
        addToReport("----------Start of validating the Contents of Saved Billers----------", Status.INFO, false);

        waitForPageLoadCompleteJS();

        if (isElementPresentBy(PageHeader(MAINHEADER,1))){
            String mainHeader  = getTextFromElement(PageHeader(MAINHEADER,1));
            if (mainHeader.trim().equals(MAINHEADER)){
                addToReport("----------Main Header validated----------", Status.PASS, false);
            }else {
                addToReport("MainHeader Mismatches"+" Expected = "+mainHeader+", Actual="+mainHeader, Status.FAIL, true);
            }
        }else{
            addToReport("Main Header is not available",Status.FAIL,true);
        }

        if (isElementPresentBy(PageHeader(MAINHEADER_SIBLING,1))){
        String mainHeaderSibling  = getTextFromElement(PageHeader(MAINHEADER_SIBLING,1));
            if (mainHeaderSibling.trim().equals(MAINHEADER_SIBLING)){
                addToReport("----------Main Header Sibling Text validated----------", Status.PASS, false);
            }else {
                addToReport("Main Header Sibling Mismatches"+" Expected = "+MAINHEADER_SIBLING+", Actual="+mainHeaderSibling, Status.FAIL, true);
            }
        }else{
            addToReport("Main Header Sibling is not available",Status.FAIL,true);
        }

        if (isElementPresentBy(PageHeader(SUBHEADER,1))) {
            String subHeader = getTextFromElement(PageHeader(SUBHEADER, 2));
            if (subHeader.trim().equals(SUBHEADER)) {
                addToReport("----------subHeader validated----------", Status.PASS, false);
            } else {
                addToReport("Main Header Mismatches" + " Expected = " + SUBHEADER + ", Actual=" + subHeader, Status.FAIL, true);
            }
        }  else{
        addToReport("Sub Header  is not available",Status.FAIL,true);
    }

        if (isElementPresentBy(PageHeader(SUBHEADER_SIBLING,1))) {

            String subHeaderSibling  = getTextFromElement(PageHeader(SUBHEADER_SIBLING,1));
        if (subHeaderSibling.trim().equals(SUBHEADER_SIBLING)){
            addToReport("----------subHeader validated----------", Status.PASS, false);
        }else {
            addToReport("Main Header Mismatches"+" Expected = "+SUBHEADER_SIBLING+", Actual="+subHeaderSibling, Status.FAIL, true);
        } }else{
                addToReport("Sub Header Sibling  is not available",Status.FAIL,true);
            }


        if (isElementPresentBy(btnNewBiller)){
            String btnNewBillerText  = getTextFromElement(btnNewBiller);
            if (btnNewBillerText.equals(Add_NEW_BILLER)){
                addToReport("Add New Biller Button Name Validated", Status.PASS, false);
            }else {
                addToReport("Add New Biller Button Name Mismatches, Expected - "+Add_NEW_BILLER+ " Actual - "+btnNewBillerText,Status.FAIL,true);
            }
        }else {
            addToReport("Add New Biller Button is not available",Status.FAIL,true);
        }


        if (isElementPresentBy(DynamicChangedButton(OLD_VISHWA))){
            String btnOldViswa  = getTextFromElement(DynamicChangedButton(OLD_VISHWA));
            if (btnOldViswa.equals(OLD_VISHWA)){
                addToReport("OLD Vishwa Button Name Validated", Status.PASS, false);
            }else {
                addToReport("OLD Vishwa Button Name Mismatches, Expected - "+OLD_VISHWA+ " Actual - "+btnOldViswa,Status.FAIL,true);
            }
        }else {
            addToReport("OLD Vishwa Button is not available",Status.FAIL,true);
        }


        clickOnElement(DynamicChangedButton(OLD_VISHWA));
        addToReport("Old Vishwa Button is Clicked",Status.INFO,false);


        if (waitForElementPresence(DynamicChangedButton(NEW_VISHWA))){
            addToReport("Old Vishwa records accessed successfully",Status.PASS,false);
        }else{
            addToReport("Old Vishwa records cannot be accessed",Status.FAIL,true);
        }


        if (isElementPresentBy(DynamicChangedButton(NEW_VISHWA))){
            String btnNewViswa  = getTextFromElement(DynamicChangedButton(NEW_VISHWA));
            if (btnNewViswa.equals(NEW_VISHWA)){
                addToReport("New Vishwa Button Name Validated", Status.PASS, false);
            }else {
                addToReport("New Vishwa Button Name Mismatches, Expected - "+NEW_VISHWA+ " Actual - "+btnNewViswa,Status.FAIL,true);
            }
        }else {
            addToReport("New Vishwa Button is not available",Status.FAIL,true);
        }

        if (waitForElementPresence(DynamicChangedButton(NEW_VISHWA))){
            addToReport("New Vishwa records accessed successfully",Status.PASS,false);
        }else{
            addToReport("New Vishwa records cannot be accessed",Status.FAIL,true);
        }
addToReport("---------------------Validation of Saved Payee contents Succesfull---------------",Status.INFO);

    }
    private static By tblObtainCellValue(int Row, int Col) {
        return By.xpath("//tbody//tr[" + Row + "]/td[" + Col + "]");
    }
    private static By selectSavedBillers(int Row) {
        return By.xpath("(//tbody//tr)[" + Row + "]");
    }


    /**
     * Selects a saved biller record by picking a random row,
     * retrieves its TemplateName, and validates the selection.
     *
     * @param totalRowsSP total number of rows in Saved Billers table
     * @return the nickname (TemplateName) of the selected record
     */
    private String selectRandomSavedBillerRecord(int totalRowsSP) {
        // Generate a random record index
        int selectedRecord = generateRandomNumber(totalRowsSP);

        // Retrieve nickname (TemplateName) from 3rd column
        String templateName = getTextFromElement(tblObtainCellValue(selectedRecord, 3)).trim();
        if (templateName.isEmpty()) {
            addToReport("Nickname is not obtained", Status.FAIL);
            throw new RuntimeException("Error - Nickname is not obtained from grid");
        }

        addToReport("Successfully obtained nickname : " + templateName +
                ", Record Number - " + selectedRecord, Status.PASS, false);

        // Select the record
        scrollToWebElement(selectSavedBillers(selectedRecord));
        clickOnElement(selectSavedBillers(selectedRecord));

        // Validate that label for selected biller is visible on top
        waitForElementPresence(lblSelectedPayeeContainer);
        if (isElementPresentBy(lblSelectedPayee(templateName))) {
            addToReport("Label for Selected Biller " + templateName + " is available on the Top",
                    Status.PASS, false);
        } else {
            addToReport("Label for Selected Biller " + templateName + " is not available",
                    Status.FAIL, true);
        }

        return templateName;
    }



    public void SelectMultipleSavedPayees(int MaxNumberOfBillers) {
        try {
            waitForElementPresence(tblSavedBillersRows);

            int totalRowsSP = isElementsPresentBy(tblSavedBillersRows);
            if (totalRowsSP <= 0) {
                addToReport("Unable to obtain row count", Status.FAIL, true);
                throw new RuntimeException("No Saved Biller Records were Found");
            }

            int totalRowsSPNotSelected = isElementsPresentBy(tblSBNotSelectedRows);
            if (totalRowsSPNotSelected <= 0) {
                addToReport("There are records which are already selected", Status.FAIL);
                throw new RuntimeException("Error - some records were already selected");
            }

            // Call inside a loop if you need multiple selections:
            for (int i = 0; i < MaxNumberOfBillers; i++) {  // Example: select 3 random records
                String templateName = selectRandomSavedBillerRecord(totalRowsSP);
                addToReport("Loop iteration " + (i + 1) + " selected: " + templateName,
                        Status.PASS, false);
            }

        } catch (Exception e) {
            addToReport("Error when adding favourite payee", Status.FAIL);
            throw new RuntimeException("Error - Failed to add favourite payee " + e.getMessage(), e);
        }


        //validate success msg
//            waitForElementPresence(lblPopupMsgFavPayeeAdded);
//            addToReport("Successfully favourite payee added message appeared", Status.PASS, true);
//            waitForElementToBeInvisible(lblPopupMsgFavPayeeAdded, LONG_WAIT);
//
//            //validate nickname in your favourite payee list
//            boolean boolYFN = isElementPresentBy(tblYFLNickName(TemplateName));
//            if (boolYFN) {
//                addToReport("Successfully validated added favourite payee : " + TemplateName, Status.PASS, true);
//            } else {
//                addToReport("Add favourite payee : " + TemplateName + " was not validated", Status.FAIL);
//            }
//
//            //Revert the changes
//            scrollToWebElement(icnSavedPayeesAddToFav(totalRowsSP));
//            clickOnElement(icnSavedPayeeByNName(TemplateName));
//
//            //validate success msg
//            waitForElementPresence(lblPopupMsgFavPayeeRemoved);
//            addToReport("Successfully favourite payee removed message appeared", Status.PASS, true);
//            waitForElementToBeInvisible(lblPopupMsgFavPayeeRemoved, LONG_WAIT);

//        } catch (Exception e) {
//            addToReport("Error when adding favourite payee", Status.FAIL);
//            throw new RuntimeException("Error - Failed to adding favourite payee " + e.getMessage(), e);
//        }
    }



    /**
     * Navigate back to dashboard
     */
    public void navigateBackToDashboard() {
        waitFor(VERY_SHORT_WAIT);


        waitForElementPresence(btnDashboard);
        clickOnElement(btnDashboard);
        waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
        waitForPageLoadCompleteJS();
        waitFor(VERY_SHORT_WAIT);
    }



}