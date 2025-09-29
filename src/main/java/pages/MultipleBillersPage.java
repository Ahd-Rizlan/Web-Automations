package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.*;

import static utils.Drivers.*;
import static utils.constants.MultipleBillersConstants.*;

public class MultipleBillersPage extends BasePage {

    public MultipleBillersPage(WebDriver driver) {
        super(driver);
    }
    Set<Integer> usedIndexes = new HashSet<>();
    private List<Map<String, String>> allSelectedPayeeDetails = new ArrayList<>();
    private Map<String, String> payeeInfo = new HashMap<String, String>();


    public enum ElementType {
        button, label, span, div, p;
    }

    private static final By imgGreyLoader = By.xpath("//div[contains(@class,'bg-gray')]");
    private static final By btnRightArrow = By.xpath("//img[contains(@src,'FArrowRight')]");
    private static final By btnPaginationNumbers = By.xpath("//img[contains(@src,'FArrowRight')]/ancestor::div[contains(@class,'flex justify-end')]/div[1]/div");
    private static final By rdoSavedPayee = By.xpath("//input[@type='checkbox' and @id='savePayee-undefined']");
    private static final By menuSavedPayee_Billers = By.xpath("//a[contains(@class,'NavBar_navlink__CRz3E')  and normalize-space(text())='Payees & Billers']");
    private static final By menuItem_SavedBillers = By.xpath("//div[contains(@class,'SubMenu_item__z9l12')  and normalize-space(text())='Saved Billers']");

    private static final By btnDashboard = By.xpath("//button/a[contains(normalize-space(text()), 'Dashboard')]");
    private static final By lblLoadingIcon = By.xpath("//div[contains(@class,'AccountsCards_loader')]");
    private static final By btnNewBiller= By.xpath("//button[normalize-space(text())='Add New Biller']");
    private static final By tblSavedBillersRows = By.xpath("//tbody//tr");
    private static final By tblSBSelectedRows = By.xpath("//tbody//tr[contains(@class,'bg-orange-300 ')]");
    private static final By tblSBNotSelectedRows = By.xpath("//tbody//tr[contains(@class,'bg-white')]");
    private static final By lblSelectedPayeeContainer = By.xpath("//div[contains(@class,'flex-wrap') and contains(@class,'justify-end')]");
    private static final By btnPayNow = By.xpath("//button[contains(normalize-space(text()),'Pay now')]");
    private static final By SPModelPage = By.xpath("//div[contains(@class,'fixed') and contains(@class,'backdrop-blur')][//div[contains(@class,'font-bold') and normalize-space()='Quick Bill Payments'] ]");
    private static final By btnBack= By.xpath("//button[@type='button' and normalize-space(text())='Back']");

    private static By lblSelectedPayee(String BillerName){
        return By.xpath("//div[contains(@class,'bg-[#F5883C]') and contains(normalize-space(.),'"+BillerName+"')]");
    }
    private static By lblSelectedPayeeCloseButton(String BillerName){
        return By.xpath("//div[contains(@class,'bg-[#F5883C]') and contains(normalize-space(.),'"+BillerName+"')]"+"/parent::div//img[contains(@src,'blackRoundCross') and contains(@class,'cursor-pointer')]");
    }
    private static By tabHeader(String tabName) {
        return By.xpath("//button[span[normalize-space(text())='"+tabName+"']]");
    }
    private static By btnAddToList(int index) {
        return By.xpath("(//input[@type='checkbox' and @id='savePayee-undefined'])[" + index + "]");
    }
    private static By pageHeader(String headerTxt , int index) {
        return By.xpath("(//span[normalize-space(text())='"+headerTxt+"' and parent::*[@class='flex flex-col']])["+index+"]");
    }

    private static By dynamicChangedButton(String btnName){
        return By.xpath("//button/span[normalize-space(text())='"+btnName+"']");
    }

    private static By validateSpanElements(String className, String txtContain) {
        return By.xpath("//span[contains(@class,'" + className + "') and normalize-space(text())='" + txtContain + "']");
    }
    private static By validateRadioBtn(String txtContain) {
        return By.xpath("//label[normalize-space(text())='"+ txtContain.trim() +"' and input[@type='radio']]");
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
                        int rCount = isElementsPresentBy(rdoSavedPayee);
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
     * Navigate to saved Biller through nave bar
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

        if (isElementPresentBy(pageHeader(MAINHEADER,1))){
            String mainHeader  = getTextFromElement(pageHeader(MAINHEADER,1));
            if (mainHeader.trim().equals(MAINHEADER)){
                addToReport("----------Main Header validated----------", Status.PASS, false);
            }else {
                addToReport("MainHeader Mismatches"+" Expected = "+mainHeader+", Actual="+mainHeader, Status.FAIL, true);
            }
        }else{
            addToReport("Main Header is not available",Status.FAIL,true);
        }

        if (isElementPresentBy(pageHeader(MAINHEADER_SIBLING,1))){
        String mainHeaderSibling  = getTextFromElement(pageHeader(MAINHEADER_SIBLING,1));
            if (mainHeaderSibling.trim().equals(MAINHEADER_SIBLING)){
                addToReport("----------Main Header Sibling Text validated----------", Status.PASS, false);
            }else {
                addToReport("Main Header Sibling Mismatches"+" Expected = "+MAINHEADER_SIBLING+", Actual="+mainHeaderSibling, Status.FAIL, true);
            }
        }else{
            addToReport("Main Header Sibling is not available",Status.FAIL,true);
        }

        if (isElementPresentBy(pageHeader(SUBHEADER,1))) {
            String subHeader = getTextFromElement(pageHeader(SUBHEADER, 2));
            if (subHeader.trim().equals(SUBHEADER)) {
                addToReport("----------subHeader validated----------", Status.PASS, false);
            } else {
                addToReport("Main Header Mismatches" + " Expected = " + SUBHEADER + ", Actual=" + subHeader, Status.FAIL, true);
            }
        }  else{
        addToReport("Sub Header  is not available",Status.FAIL,true);
    }

        if (isElementPresentBy(pageHeader(SUBHEADER_SIBLING,1))) {

            String subHeaderSibling  = getTextFromElement(pageHeader(SUBHEADER_SIBLING,1));
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


        if (isElementPresentBy(dynamicChangedButton(OLD_VISHWA))){
            String btnOldViswa  = getTextFromElement(dynamicChangedButton(OLD_VISHWA));
            if (btnOldViswa.equals(OLD_VISHWA)){
                addToReport("OLD Vishwa Button Name Validated", Status.PASS, false);
            }else {
                addToReport("OLD Vishwa Button Name Mismatches, Expected - "+OLD_VISHWA+ " Actual - "+btnOldViswa,Status.FAIL,true);
            }
        }else {
            addToReport("OLD Vishwa Button is not available",Status.FAIL,true);
        }


        clickOnElement(dynamicChangedButton(OLD_VISHWA));
        addToReport("Old Vishwa Button is Clicked",Status.INFO,false);


        if (waitForElementPresence(dynamicChangedButton(NEW_VISHWA))){
            addToReport("Old Vishwa records accessed successfully",Status.PASS,false);
        }else{
            addToReport("Old Vishwa records cannot be accessed",Status.FAIL,true);
        }


        if (isElementPresentBy(dynamicChangedButton(NEW_VISHWA))){
            String btnNewViswa  = getTextFromElement(dynamicChangedButton(NEW_VISHWA));
            if (btnNewViswa.equals(NEW_VISHWA)){
                addToReport("New Vishwa Button Name Validated", Status.PASS, false);
            }else {
                addToReport("New Vishwa Button Name Mismatches, Expected - "+NEW_VISHWA+ " Actual - "+btnNewViswa,Status.FAIL,true);
            }
        }else {
            addToReport("New Vishwa Button is not available",Status.FAIL,true);
        }

        if (waitForElementPresence(dynamicChangedButton(NEW_VISHWA))){
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
     * Returns a random unique row number between 1 and totalRows (inclusive)
     * @param totalRows set the maximum number billers should be selected
     * @param usedIndexes set to track already selected indexes
     * that hasn't been used in the current run.
     */
    private int generateUniqueRandomNumber(int totalRows, Set<Integer> usedIndexes) {
        if (usedIndexes.size() >= totalRows) {
            throw new RuntimeException("No more unique rows available to select.");
        }

        int randomIndex;
        do {
            randomIndex = generateRandomNumber(totalRows); // existing random generator: 1..totalRows
        } while (usedIndexes.contains(randomIndex));

        usedIndexes.add(randomIndex);
        return randomIndex;
    }

    /**
     * Captures screenshot and validates that the selected payee container
     * displays the expected Template Name.
     */
    private void validateSelectedPayeeContainer(String templateName) {
        waitForElementPresence(lblSelectedPayeeContainer);
        scrollPageToTop();

        if (isElementPresentBy(lblSelectedPayee(templateName))) {
            addToReport("Label for Selected Biller '" + templateName + "' is available on the Top",
                    Status.PASS, false);

            if (isElementPresentBy(lblSelectedPayeeCloseButton(templateName))){
                addToReport("Close Button is available for Biller "+templateName +".",Status.PASS,true);
            }else{
                addToReport("Close Button is not available for Biller "+templateName +".",Status.PASS,true);            }

        } else {
            addToReport("Label for Selected Biller '" + templateName + "' is NOT available",
                    Status.FAIL, true); // screenshot on failure as well
            throw new RuntimeException("Label for Selected Biller not found for: " + templateName);
        }
    }

//    /**
//     * Selects a saved biller record by picking a unique random row,
//     * retrieves its TemplateName, and validates the selection.
//     *
//     * @param totalRowsSP total number of rows in Saved Billers table or PaginationLimit
//     * @return the nickname (TemplateName) of the selected record
//     */
//    public String selectOneSavedBillerRecord(int totalRowsSP  ) {
//
//        // Generate a unique random record index
//        int selectedRecord = generateUniqueRandomNumber(totalRowsSP, usedIndexes);
//
//        // Retrieve nickname (TemplateName) from 3rd column
//        String templateName = getTextFromElement(tblObtainCellValue(selectedRecord, 3)).trim();
//        if (templateName.isEmpty()) {
//            addToReport("Nickname is not obtained", Status.FAIL);
//            throw new RuntimeException("Error - Nickname is not obtained from grid");
//        }
//
//        addToReport("Successfully obtained nickname : " + templateName +
//                ", Record Number - " + selectedRecord, Status.PASS, false);
//
//        // Select the record
//        scrollToWebElement(selectSavedBillers(selectedRecord));
//        clickOnElement(selectSavedBillers(selectedRecord));
//
//        // Validate selected payee label & capture screenshot
//        validateSelectedPayeeContainer(templateName);
//
//        return templateName;
//    }

    /**
     * Selects a saved biller record by picking a unique random row,
     * retrieves its TemplateName, and validates the selection.
     *
     * @param totalRowsSP total number of rows in Saved Billers table or PaginationLimit
     * @return the nickname (TemplateName) of the selected record
     */
    public String selectOneSavedBillerRecord(int totalRowsSP ) {

        // Generate a unique random record index
        int selectedRecord = generateUniqueRandomNumber(totalRowsSP,usedIndexes);

        // Retrieve nickname (TemplateName) from 3rd column
        String templateName = getTextFromElement(tblObtainCellValue(selectedRecord, 3)).trim();
        String billerName  = getTextFromElement(tblObtainCellValue(selectedRecord, 4)).trim();
        String amount  = getTextFromElement(tblObtainCellValue(selectedRecord, 5)).trim();
        String filedValue  = getTextFromElement(tblObtainCellValue(selectedRecord, 6)).trim();

        if (!templateName.isEmpty() && !billerName.isEmpty() && !amount.isEmpty() && !filedValue.isEmpty()) {
            Map<String, String> payeeInfo = new HashMap<>();
            payeeInfo.put("TemplateName", templateName);
            payeeInfo.put("BillerName", billerName);
            payeeInfo.put("Amount", amount);
            payeeInfo.put("FiledValue", filedValue);
            addToReport("Successfully obtained nickname : " + templateName + " - Biller Name : "+billerName
                    +" - Amount : "+amount+" - FiledValue : "+filedValue+
                    ", Record Number - " + selectedRecord, Status.PASS, false);
        }else {
            if (templateName.isEmpty()) {
                addToReport("Nickname is not obtained", Status.FAIL);
                throw new RuntimeException("Error - Nickname is not obtained from grid");
            }
            if (billerName.isEmpty()) {
                addToReport("Biller Name is not obtained", Status.FAIL);
                throw new RuntimeException("Error - Biller Name is not obtained from grid");
            }
            if (amount.isEmpty()) {
                addToReport("Amount is not obtained", Status.FAIL);
                throw new RuntimeException("Error - Amount is not obtained from grid");
            }
            if (filedValue.isEmpty()) {
                addToReport("Filed Value is not obtained", Status.FAIL);
                throw new RuntimeException("Error - Filed Value is not obtained from grid");
            }
        }


        // Select the record
        scrollToWebElement(selectSavedBillers(selectedRecord));
        clickOnElement(selectSavedBillers(selectedRecord));

        // Validate selected payee label & capture screenshot

        validateSelectedPayeeContainer(templateName);

        return templateName;
    }


    /**
     * Selects multiple saved Biller ensuring no duplicate selections in a single run.
     */
    public void SelectMultipleSavedBillers(int maxNumberOfBillers) {
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

            // Track used indexes to avoid duplicates

            // Loop to select unique random records
            for (int i = 0; i < maxNumberOfBillers; i++) {
//                String templateName = selectOneSavedBillerRecord(totalRowsSP, usedIndexes);
                String templateName = selectOneSavedBillerRecord(totalRowsSP);
                addToReport("Loop iteration " + (i + 1) + " selected: " + templateName,
                        Status.PASS, false);
            }

        } catch (Exception e) {
            addToReport("Error when selecting payee", Status.FAIL, true);
            throw new RuntimeException("Error - Failed to selecting payee " + e.getMessage(), e);
        }
    }


    public void PayBill() {
        try {
            if (isElementPresentBy(lblSelectedPayeeContainer)) {
                waitForElementPresence(btnPayNow);
                if (isElementPresentBy(btnPayNow)){
                    addToReport("PayNow Button is Visible",Status.PASS,false);
                    clickOnElement(btnPayNow);
                    ValidatePayBillModelPageForSingleSelectedBiller();
                    // validate Model Page


                }else {
                    addToReport("PayNow Button is Not-Visible",Status.FAIL,true);
                }

            }else {
                addToReport("No Biller selected or selected payee Labels are not visible",Status.FAIL,true);
            }

            }catch(Exception e) {
            addToReport("Selected Biller Label container is not visible", Status.FAIL, true);
            throw new RuntimeException("Selected Biller container is not visible" + e.getMessage(), e);
            }
        }


        private void ValidatePayBillModelPageForSingleSelectedBiller(){
            try {
                addToReport("-------------------------- Validating the PayBill model Page --------------------------",Status.INFO);
                if (isElementPresentBy(SPModelPage)) {
                    //                    --validated with Heading--
                    addToReport("Quick Bill Payment Model Page is present",Status.PASS,false);
                    if (isElementPresentBy(validateSpanElements("text-gray-500","Payment Using"))){
                        addToReport("Payment Method is Present",Status.PASS,false);
                        if (isElementPresentBy(validateRadioBtn(RDO_ACCOUNT))){
                            addToReport("Account Radio Button is Present",Status.PASS,false);
                        }else {
                            addToReport("Account Radio Button is not Present",Status.FAIL,true);
                        }
                        if (isElementPresentBy(validateRadioBtn(RDO_CREDIT_CARD))) {
                            addToReport("Credit Card Radio Button is Present", Status.PASS, false);
                        }else {
                            addToReport("Credit Card Radio Button is not Present",Status.FAIL,true);
                        }




                    }else {
                        addToReport("Payment Method Option is not present",Status.FAIL,true);
                    }
                }else {
                    addToReport("Quick Bill Payment Model Page is not Visible",Status.FAIL,true);
                }

            }catch(Exception e) {
                addToReport("something went wrong on Validate PayNow Model Page", Status.FAIL, true);
                throw new RuntimeException("something went wrong on  Validate PayNow Model Page" + e.getMessage(), e);
            }finally {
                waitForElementPresence(btnBack);
                clickOnElement(btnBack);
            }
        }


    public void clearUsedIndexes() {

        //this method is used to clear the selected indexes
        usedIndexes.clear();
        allSelectedPayeeDetails.clear();
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