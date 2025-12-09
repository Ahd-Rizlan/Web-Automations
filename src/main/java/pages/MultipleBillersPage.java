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
    static float totalAmount = 0;


    public enum ElementType {
        button, label, span, div, p,text,numeric,number;
    }
    public enum PayUsing {
        Card, LKR ,OTHER;
    }

    private static  String selectedFromAccount ="";
    private static  String selectedBillerName ="";

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
//    private static final By btnPayNow = By.xpath("//button[contains(normalize-space(text()),'Pay now')]");

    private static final By btnBack= By.xpath("//button[@type='button' and normalize-space(text())='Back']");
    private static final By ddFromCCard = By.xpath("//select[@name='cardSerialNumber']");
    private static final By ddFromAccount = By.xpath("//select[@id='accountfrom']");
    private static final By dynamicPayNowBtn = By.xpath("//button[@type='submit' and contains(normalize-space(.), 'Pay now') ]");
    private static final By selectedAccountContainer = By.xpath(".//div[@class='border-[1px] flex relative justify-between border-[#008926] bg-white rounded-lg w-full h-[120px] overflow-hidden']");
    private static final By btnCloseToast = By.xpath("//button[contains(@class,'close')]");

    private static final By otpConfirmationPage = By.xpath("//div[contains(@class,'BillPaymentConfirmation_scroll__yF3va')]");
    private static final By otpPageMainHeading = By.xpath("//div[@class='font-bold' and contains(text(), 'Payment Confirmation')]");
    private static final By otpPageSubHeading = By.xpath("//div[@class='font-normal' and contains(text(), 'Please review your transaction')]");
    private static final By otpPageBillerName = By.xpath(".//div[contains(@class,'flex flex-col text-sm')]//span[1]");
    private static final By otpPageFieldValue = By.xpath(".//div[contains(@class,'flex flex-col text-sm')]//span[2]");
    private static final By btnConfirmOtp = By.xpath("//button[contains(normalize-space(text()),'Confirm')]");
    private static final By btnDisabledConfirmOtp = By.xpath("//button[contains(normalize-space(text()),'Confirm') and @disabled]");
    private static final By lblPaymentProcessing =By.xpath("//div[contains(text(),'Payment Processing Fee')]");
    private static final By lblSuccess = By.xpath("//span[text()='Success']");
    private static final By lblRefernceID = By.xpath("//span[text()='Success']//following::span[2]");
    private static final By btnPrint = By.xpath("//button[normalize-space()='Print']");
    private static final By btnOTPClosePopup = By.xpath("//button[contains(@class,'absolute top-4')]/img");

    private static final By otpConfirmationPageMultipleBillers = By.xpath("//div[contains(@class,'MultipleBillPaymentConfirmation_scroll__p0Qy2')]");
    private static final By otpConfirmationPageMultipleBillersHeaderText = By.xpath("//div[@class='font-bold']");
    private static final By otpConfirmationPageMultipleBillersSubHeaderText = By.xpath("//div[text()='Use wide range of biller network to make your payments with ease.']");


    private static final By pgeSuccess = By.xpath("//div[contains(@class,'BillPaymentConfirmation_scroll__yF3va')]");
    private static final By pgeSuccessPageBillerName = By.xpath(".//div[contains(@class,'flex flex-col text-sm')]//span[1]");
    private static final By pgeSuccessPageFieldValue = By.xpath(".//div[contains(@class,'flex flex-col text-sm')]//span[2]");

    private static final By NEXT_ARROW = By.xpath("//div[contains(@class,'rounded-r-lg') and .//img[contains(@alt,'Next')]]");

    // --- Locators for Multiple Biller Success Screen ---
    private static final By pgeMultipleBillerSuccess = By.xpath("//div[contains(@class,'MultipleBillPaymentConfirmation_scroll__p0Qy2')]");
    private static final By lblSuccessCount = By.xpath("//div[contains(@class,'mx-auto text-center font-semibold')]");
    private static final By lblSuccessHeader = By.xpath("//span[text()='" + SUCCESS_MESSAGE_HEADER + "' and contains(@class,'text-[#22C060]')]");
    private static final By lblSuccessMessage = By.xpath("//span[text()='" + SUCCESS_MESSAGE_SUBTEXT + "']");
    private static final By btnNextBiller = By.xpath("//img[contains(@alt,'Next')]/parent::div");
    private static final By btnMakeAnotherPayment = By.xpath("//button[text()='Make Another Payment']");

    /**
     * Gets a locator for the currently visible (not hidden) bill detail table.
     */
    private static final By tblVisibleBillerDetails = By.xpath("//table[contains(@class,'w-full border-collapse') and not(contains(@class,'hidden'))]");
    /**
     * Gets the value from a <td> in the *currently visible* bill detail table, based on its preceding <td> label.
     * @param label The text of the <td> label (e.g., "Template Name", "Amount")
     */
    private static By getVisibleTableValue(String label) {
        return By.xpath("//table[contains(@class,'w-full border-collapse') and not(contains(@class,'hidden'))]//td[text()='" + label + "']/following-sibling::td");
    }

    /**
     * Gets the Print button from the *currently visible* bill detail table.
     */
    private static final By btnPrintReceipt = By.xpath("//table[contains(@class,'w-full border-collapse') and not(contains(@class,'hidden'))]//button[text()='Print']");

    /**
     * Gets the active pagination dot by its 1-based index.
     */
    private static By paginationDotActive(int index) {
        // Example: (//div[...]/div)[1]
        return By.xpath(String.format("(//div[contains(@class,'flex gap-1 items-center justify-center')]/div)[%d_and_contains(@class,'bg-orange-500')]", index));
    }

    // --- End of Success Screen Locators ---


    public static By getPayNowButton() {
            return By.xpath("//button[contains(normalize-space(text()),'Pay now')]");
    }

    private static By getPayNowButton(String amount) {
        return By.xpath("//button[contains(translate(normalize-space(text()), ',',''), 'Pay now LKR " + amount + "')]");
    }

    private static By getSPModelPage(String headerText) {
        return By.xpath(
                "//div[contains(@class,'fixed') and contains(@class,'backdrop-blur')]" +
                        "[.//div[contains(@class,'font-bold') and normalize-space()='" + headerText + "']]"
        );
    }
    private static By lblAmountConfirmation(String amount) {
        return By.xpath(
                "//span[contains(normalize-space(.), 'You are about to pay Total') and contains(normalize-space(.), '" + amount + "')]");
    }

    private static By tfOTP(int Index) {
        return By.xpath("//input[contains(@class,'otp-box')][" + Index + "]");
    }
    private static By getSelectedCurrencyDetails(int spanNumber){
        return By.xpath(".//div[contains(@class,'bg-[#c6cdc83f]')]//div//div//span["+spanNumber+"]");
    }
    private static By getSelectedAccountDetails(int spanNumber){
        return By.xpath(".//div[@class='flex flex-col justify-center font-semibold ml-6 mr-3 w-1/3']/span["+spanNumber+"]");
    }
    private static By validateErrorMessage(String errorMessage){
        return By.xpath("//p[contains(@class,'text-red-500') and contains(text(),'"+errorMessage+"']");
    }

    private static By validateToastMessage(String Message){
        return By.xpath("//div[@role='alert']//div[contains(text(),'"+Message+"')]");
    }

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

    private static By getSpanElements(String className, String txtContain) {
        return By.xpath("//span[contains(@class,'" + className + "') and normalize-space(text())='" + txtContain + "']");
    }
    private static By getRequiredSpanElements(String className, String txtContain) {
        return By.xpath("//span[contains(@class,'" + className + "') and normalize-space(text())='" + txtContain + "']//span[contains(@class,'text-red-500') and normalize-space(text())='*']");
    }

    private static By getInputElements(ElementType Type, ElementType inputMode) {
        return By.xpath("//input[@type='"+Type+"' and @inputmode='"+inputMode+"']");
    }
    private static By getRadioBtn(String txtContain) {
        return By.xpath("//label[normalize-space(text())='"+ txtContain.trim() +"' and input[@type='radio']]");
    }
    private static By getInputFields(ElementType InputType,String followingSiblingText ) {
        return By.xpath("//div[contains(@class,'border p-3  rounded block gap-4 flex flex-col')]//input[@type='"+InputType+"' and following-sibling::span[contains(text(),'"+followingSiblingText+"')]]");
    }

    private static By getInputFieldsMultipleBiller(ElementType InputType,String followingSiblingText ) {
        return By.xpath("//input[@type='"+InputType+"' and following-sibling::span[contains(text(),'"+followingSiblingText+"')]]");
    }

    private static By getInputElements(ElementType type, ElementType inputMode, String followingSiblingText, int billerIndex) {
        // Example fieldName: "amount" or "validations.0.fieldValue"
        return By.xpath("//div[contains(@class,'border p-3  rounded block gap-4 flex flex-col')]//input[@type='" + type + "' and @inputmode='" + inputMode +
                "' and @name='billPayList." + billerIndex + "." + followingSiblingText.toLowerCase() + "']");
    }


    public static By getTableValue(String label) {
        return By.xpath( "//table[@class='w-full border-collapse ']//td[text()='" + label +"']/following-sibling::td");
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

            allSelectedPayeeDetails.add(payeeInfo);

            

            addToReport("Successfully obtained nickname : " + payeeInfo.get("TemplateName") + " - Biller Name : "+payeeInfo.get("BillerName")
                    +" - Amount : "+payeeInfo.get("Amount")+" - FiledValue : "+payeeInfo.get("FiledValue")+
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

            // Step 1: Select up to the allowed number of billers
            for (int i = 0; i < maxNumberOfBillers; i++) {
                String templateName = selectOneSavedBillerRecord(totalRowsSP);
                addToReport("Selected biller (" + (i + 1) + "): " + templateName, Status.PASS, false);
            }

            // Step 2: Attempt one extra selection beyond the limit
            addToReport("Attempting to select one extra biller beyond the allowed limit (" + maxNumberOfBillers + ")", Status.INFO, false);
            int selectedRecord = generateUniqueRandomNumber(totalRowsSP,usedIndexes);
            clickOnElement(selectSavedBillers(selectedRecord));

            // Step 3: Wait for toast error to appear
            waitForElementPresence(validateToastMessage(MAX_BILLER_ERROR));
            if (isElementPresentBy(validateToastMessage(MAX_BILLER_ERROR))) {
                String toastMsg = getTextFromElement(validateToastMessage(MAX_BILLER_ERROR));
                addToReport("Toast error displayed correctly: '" + toastMsg + "'", Status.PASS, false);
                clickOnElement(btnCloseToast);
            } else {
                addToReport("No toast error appeared after selecting beyond limit.", Status.FAIL, true);
            }

        } catch (Exception e) {
            addToReport("Error when selecting payee", Status.FAIL, true);
            throw new RuntimeException("Error - Failed while selecting payee: " + e.getMessage(), e);
        }
    }


    public void clickPayNowButton() {
        try {
            if (isElementPresentBy(lblSelectedPayeeContainer)) {
                waitForElementPresence(getPayNowButton());
                if (isElementPresentBy(getPayNowButton())){
                    addToReport("PayNow Button is Visible",Status.PASS,false);
                    clickOnElement(getPayNowButton());


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
        //-----------------  Select Multiple payee  -----------------

    public void ValidatePayBillModelPageForMultipleSelectedBiller() {
        try {
            addToReport("-------------------------- Validating the PayBill model Page --------------------------", Status.INFO, true);
            if (isElementPresentBy(getSPModelPage(MULTIPLE_BILL_PAYMENTS))) {
                addToReport("Multiple Bill Payment Model Page is present",Status.PASS,false);

                }else {
                    addToReport("Payment Method section is not present",Status.FAIL,true);
                }

                try{
                    if (isElementPresentBy(ddFromAccount)){
                        addToReport("Accounts dropdown is Present",Status.PASS,false);
                    } else {
                        addToReport("Accounts dropdown is not Present",Status.FAIL,true);
                    }
                }catch (Exception e){
                    throw new RuntimeException("Failed to Retrieve Accounts" + e.getMessage(), e);
                }


            // --- Start of Data Cross-Validation ---
            if (allSelectedPayeeDetails.isEmpty()) {
                addToReport("Cannot perform validation. The list of selected payee details is empty.", Status.FAIL, true);
                throw new IllegalStateException("allSelectedPayeeDetails is empty.");
            }else {

// Iterate through all selected payee details
            for (int i = 0; i < allSelectedPayeeDetails.size(); i++) {

                Map<String, String> expectedDetails;
                expectedDetails = allSelectedPayeeDetails.get(i);

                String expectedTemplateName = expectedDetails.get("TemplateName");
                String expectedBillerName = expectedDetails.get("BillerName");
                String expectedAmount = expectedDetails.get("Amount");
                totalAmount+= Float.parseFloat(expectedAmount.replaceAll("[^\\d.]", ""));
                addToReport("Validating Biller Record " + (i + 1) + " of " + allSelectedPayeeDetails.size(), Status.INFO, false);

                By inputTemplateName = getInputFields(ElementType.text, TEMPLATE_NAME);
                By inputBillerName = getInputFields(ElementType.text, BILLER_NAME);
                By inputAmount = getInputElements(ElementType.text, ElementType.numeric, Amount,i);

                if (isElementPresentBy(inputTemplateName)) {
                    String actualTemplateName = getAttributeFromElement(inputTemplateName, "value");
                    if (actualTemplateName.equals(expectedTemplateName)) {
                        addToReport("Template Name validation PASSED. Expected: '" + expectedTemplateName + "', Actual: '" + actualTemplateName + "'.", Status.PASS, false);
                    } else {
                        addToReport("Template Name validation FAILED. Expected: '" + expectedTemplateName + "', Actual: '" + actualTemplateName + "'.", Status.FAIL, true);
                    }
                } else {
                    addToReport("Template Name input field could not be found on the modal.", Status.FAIL, true);
                }

                if (isElementPresentBy(inputBillerName)) {
                    String actualBillerName = getAttributeFromElement(inputBillerName, "value");
                    if (actualBillerName.equals(expectedBillerName)) {
                        addToReport("Biller Name validation PASSED. Expected: '" + expectedBillerName + "', Actual: '" + actualBillerName + "'.", Status.PASS, false);

                        if (BILLER_DATA.containsKey(actualBillerName)) {
                            List<String> selectedBiller = BILLER_DATA.get(actualBillerName);
                            ValidatePhoneBillers(selectedBiller);
                            selectedBillerName = BILLER_DATA.get(actualBillerName).get(0);
                        } else {
                            addToReport("Other Biller is selected", Status.PASS, false);
                        }
                    } else {
                        addToReport("Biller Name validation FAILED. Expected: '" + expectedBillerName + "', Actual: '" + actualBillerName + "'.", Status.FAIL, true);
                    }
                } else {
                    addToReport("Biller Name input field could not be found on the modal.", Status.FAIL, true);
                }

                if (isElementPresentBy(inputAmount)) {
                    clickOnElement(inputAmount);
                    clearTheElement(inputAmount);

                    if (getAttributeFromElement(inputAmount, "value").isEmpty()) {
                        addToReport("Amount field cleared successfully.", Status.PASS, false);

                        if (isElementPresentBy(validateErrorMessage(ERROR_MSG_AMOUNT))) {
                            addToReport("Amount required error message is displayed as expected after clearing the field.", Status.PASS, false);
                        } else {
                            addToReport("Amount required error message is NOT displayed after clearing the field.", Status.FAIL, true);
                        }
                    } else {
                        addToReport("Failed to clear the Amount field.", Status.FAIL, true);
                    }

                    sendKeysToElement(inputAmount, expectedAmount);

                    String actualAmount = getAttributeFromElement(inputAmount, "value");
                    String normalizedExpectedAmount = expectedAmount.replaceAll("[^\\d.]", "");
                    String normalizedActualAmount = actualAmount.replaceAll("[^\\d.]", "");

                    if (normalizedActualAmount.trim().equals(normalizedExpectedAmount.trim())) {
                        addToReport("Amount validation PASSED. Expected: '" + normalizedExpectedAmount + "', Actual: '" + normalizedActualAmount + "'.", Status.PASS, false);
                    } else {
                        addToReport("Amount validation FAILED. Expected: '" + normalizedExpectedAmount + "', Actual: '" + normalizedActualAmount + "'.", Status.FAIL, true);
                    }

                } else {
                    addToReport("Amount input field could not be found on the modal.", Status.FAIL, true);
                }

                if (i < allSelectedPayeeDetails.size() - 1) {
                    if (isElementPresentBy(NEXT_ARROW)) {
                        clickOnElement(NEXT_ARROW);
                        isElementPresentBy(inputBillerName, 5); // wait for next modal to load
                        addToReport("Moved to next biller record.", Status.INFO, false);
                    } else {
                        addToReport("Next arrow not found. Cannot move to the next biller.", Status.FAIL, true);
                        break;
                    }
                }
            }

            if (isElementPresentBy(btnBack)){
                addToReport("Back Button is Present",Status.PASS,false);
            }else {
                addToReport("Back Button is not Present",Status.FAIL,true);
            }
            String formattedTotalAmount = String.format("%.2f", totalAmount);

            if (isElementPresentBy(getPayNowButton(formattedTotalAmount))){
                addToReport("PayNow Button with Total Amount LKR " + formattedTotalAmount + " is Present", Status.PASS, false);
                clickOnElement(getPayNowButton(formattedTotalAmount));

            } else {
                addToReport("PayNow Button with Total Amount LKR " + formattedTotalAmount + " is not Present", Status.FAIL, true);
                throw new RuntimeException("PayNow Button is not Present");
            }
}
        } catch (Exception e) {
            addToReport("An unexpected error occurred in ValidatePayBillModelPageForSingleSelectedBiller", Status.FAIL, true);
            throw new RuntimeException("Error during PayNow Model Page validation: " + e.getMessage(), e);
        }

    }

    public void validateMultipleBillerOTPConfirmationPage( ){
        String normalizedPayFrom = "";

        addToReport("----------Start of validation of Multiple Payment Success page----------", Status.PASS, false);

        waitForElementPresence(otpConfirmationPageMultipleBillers);
        if (isElementPresentBy(otpConfirmationPageMultipleBillers)) {
            addToReport("Multiple Biller OTP Confirmation Page is Present",Status.PASS,false);

            if (isElementPresentBy(otpConfirmationPageMultipleBillersHeaderText))
            {
                String headerText = getTextFromElement(otpConfirmationPageMultipleBillersHeaderText);
                if (headerText.equals(OTP_CONFIRMATION_PAGE_MULTIPLE_BILLER_HEADER_TEXT)) {
                    addToReport("OTP Confirmation Header Text is Validated", Status.PASS, false);
                } else {
                    addToReport("OTP Confirmation Header Text Mismatches" + " Expected = " + OTP_CONFIRMATION_PAGE_MULTIPLE_BILLER_HEADER_TEXT + ", Actual=" + headerText, Status.FAIL, true);
                }
            } else {
                addToReport("OTP Confirmation Header Text is not available", Status.FAIL, true);
            }
            if (isElementPresentBy(otpConfirmationPageMultipleBillersSubHeaderText)){
                addToReport("Sub Header Text is Present",Status.PASS,false);
            }else {
                addToReport("Sub Header Text is not Present",Status.FAIL,true);
            }

            if (isElementPresentBy(getInputFieldsMultipleBiller(ElementType.text,PAYFROM))){
                String actualPayFrom = getTextFromElement(getInputFieldsMultipleBiller(ElementType.text,PAYFROM));
                String normalizedActualPayFrom = actualPayFrom.replaceAll("[^\\d.]", "");
                normalizedPayFrom = selectedFromAccount.replaceAll("[^\\d.]", "");
                if (normalizedActualPayFrom.trim().equals(normalizedPayFrom.trim())) {
                    addToReport("PayFrom validation PASSED. Expected: '" + normalizedPayFrom + "', Actual: '" + normalizedActualPayFrom + "'.", Status.PASS, false);
                } else {
                    addToReport("PayFrom validation FAILED. Expected: '" + normalizedPayFrom + "', Actual: '" + normalizedActualPayFrom + "'.", Status.FAIL, true);
                }
            }else {
                addToReport("payFrom field is not visible in OTP Page",Status.FAIL,true);
            }


            if (isElementPresentBy(getInputFieldsMultipleBiller(ElementType.text,Total_Amount))){
                String totalAmountActual = getTextFromElement(getInputFieldsMultipleBiller(ElementType.text,Total_Amount));
                String normalizedExpectedTotalAmount = Float.toString(totalAmount).replaceAll("[^\\d.]", "");
                if (totalAmountActual.trim().equals(normalizedExpectedTotalAmount.trim())) {
                    addToReport("TotalAmount validation PASSED. Expected: '" + normalizedExpectedTotalAmount + "', Actual: '" + totalAmountActual + "'.", Status.PASS, false);
                } else {
                    addToReport("TotalAmount validation FAILED. Expected: '" + normalizedExpectedTotalAmount + "', Actual: '" + totalAmountActual + "'.", Status.FAIL, true);
                }
            }else {
                addToReport("TotalAmount field is not visible in OTP Page",Status.FAIL,true);
            }

            if (allSelectedPayeeDetails.isEmpty()) {
                addToReport("Cannot perform validation. The list of selected payee details is empty.", Status.FAIL, true);
                throw new IllegalStateException("allSelectedPayeeDetails is empty.");
            }else {

                // Iterate through all selected payee details

                for (int i = 0; i < allSelectedPayeeDetails.size(); i++) {

                    Map<String, String> expectedDetails;
                    expectedDetails = allSelectedPayeeDetails.get(i);

                    String expectedTemplateName = expectedDetails.get("TemplateName");
                    String expectedFiledValue = expectedDetails.get("FiledValue");
                    String expectedBillerName = expectedDetails.get("BillerName");
                    String expectedAmount = expectedDetails.get("Amount");
                    totalAmount += Float.parseFloat(expectedAmount.replaceAll("[^\\d.]", ""));
                    addToReport("Validating Biller Record " + (i + 1) + " of " + allSelectedPayeeDetails.size(), Status.INFO, false);

                    By inputTemplateName = getTableValue(TEMPLATE_NAME);
                    By inputAmount = getTableValue(Amount);

                    if (isElementPresentBy(otpPageBillerName)) {
                        String actualBillerName = getTextFromElement(otpPageBillerName);
                        if (actualBillerName.equals(expectedBillerName)) {
                            addToReport("Biller Name validation PASSED. Expected: '" + expectedBillerName + "', Actual: '" + actualBillerName + "'.", Status.PASS, false);
                        } else {
                            addToReport("Biller Name validation FAILED. Expected: '" + expectedBillerName + "', Actual: '" + actualBillerName + "'.", Status.FAIL, true);
                        }
                    } else {
                        addToReport("Biller Name is not visible in OTP Page", Status.FAIL, true);
                    }

                    if (isElementPresentBy(otpPageFieldValue)) {
                        String actualFiledValue = getTextFromElement(otpPageFieldValue);
                        if (actualFiledValue.trim().equals(expectedFiledValue.trim())) {
                            addToReport("Filed Value validation PASSED. Expected: '" + expectedFiledValue + "', Actual: '" + actualFiledValue + "'.", Status.PASS, false);
                        } else {
                            addToReport("Filed Value validation FAILED. Expected: '" + expectedFiledValue + "', Actual: '" + actualFiledValue + "'.", Status.FAIL, true);
                        }
                    } else {
                        addToReport("Filed Value is not visible in OTP Page", Status.FAIL, true);
                    }

                    //Template Name VALIDATION
                    if (isElementPresentBy(inputTemplateName)) {
                        String actualTemplateName = getTextFromElement(inputTemplateName);
                        String normalizedExpectedTemplateName = expectedTemplateName.replaceAll("[^\\d.]", "");
                        String normalizedActualTemplateName = actualTemplateName.replaceAll("[^\\d.]", "");

                        if (normalizedActualTemplateName.trim().equals(normalizedExpectedTemplateName.trim())) {
                            addToReport("Amount validation PASSED. Expected: '" + normalizedExpectedTemplateName + "', Actual: '" + normalizedActualTemplateName + "'.", Status.PASS, false);
                        } else {
                            addToReport("Amount validation FAILED. Expected: '" + normalizedExpectedTemplateName + "', Actual: '" + normalizedActualTemplateName + "'.", Status.FAIL, true);
                        }

                    } else {
                        addToReport("TemplateName input field could not be found on the modal.", Status.FAIL, true);
                    }

                    //Amount VALIDATION

                    if (isElementPresentBy(inputAmount)) {
                        String actualAmount = getTextFromElement(inputAmount);
                        String normalizedExpectedAmount = expectedAmount.replaceAll("[^\\d.]", "");
                        String normalizedActualAmount = actualAmount.replaceAll("[^\\d.]", "");

                        if (normalizedActualAmount.trim().equals(normalizedExpectedAmount.trim())) {
                            addToReport("Amount validation PASSED. Expected: '" + normalizedExpectedAmount + "', Actual: '" + normalizedActualAmount + "'.", Status.PASS, false);
                        } else {
                            addToReport("Amount validation FAILED. Expected: '" + normalizedExpectedAmount + "', Actual: '" + normalizedActualAmount + "'.", Status.FAIL, true);
                        }

                    }else {
                        addToReport("Amount input field could not be found on the modal.", Status.FAIL, true);
                    }
                //validate dynamic fileds
                    // 4️⃣ Move to the next record (if not the last one)
                    if (i < allSelectedPayeeDetails.size() - 1) {
                        if (isElementPresentBy(NEXT_ARROW)) {
                            clickOnElement(NEXT_ARROW);
                            isElementPresentBy(otpPageBillerName, 5); // wait for next modal to load
                            addToReport("Moved to next biller record.", Status.INFO, false);
                        } else {
                            addToReport("Next arrow not found. Cannot move to the next biller.", Status.FAIL, true);
                            break;
                        }
                    }
                }

            }
        }else {
            addToReport("Multiple Biller OTP Confirmation Page is not Present",Status.FAIL,true);
        }
    }

    /**
     * Validates the entire "Multiple Biller Payment Success" screen.
     * It checks:
     * 1. Overall Pay From and Total Amount.
     * 2. The main "Success" message and payment count (e.g., "2 out of 2").
     * 3. Loops through each bill, validates its details (Template, Amount, Status).
     * 4. Clicks the "Print" button for each bill to verify it opens a new window.
     */
    public void validateMultipleBillerPaymentSuccessPage() {
        addToReport("---------- Validating Multiple Biller Payment Success Page ----------", Status.INFO, false);
        waitForElementPresence(pgeMultipleBillerSuccess, LONG_WAIT);
        if (!isElementPresentBy(pgeMultipleBillerSuccess)) {
            addToReport("Multiple Biller Success page did not load.", Status.FAIL, true);
            throw new RuntimeException("Multiple Biller Success page did not load.");
        }

        // --- 1. Validate Overall Details (Pay From & Total Amount) ---
        try {
            // Validate Pay From
            String expectedPayFrom = selectedFromAccount.replaceAll("[^\\d.]", "");
            String actualPayFrom = getAttributeFromElement(getInputFieldsMultipleBiller(ElementType.text, PAYFROM), "value").replaceAll("[^\\d.]", "");
            if (actualPayFrom.equals(expectedPayFrom)) {
                addToReport("Pay From account validation PASSED. Expected: " + expectedPayFrom + ", Actual: " + actualPayFrom, Status.PASS, false);
            } else {
                addToReport("Pay From account validation FAILED. Expected: " + expectedPayFrom + ", Actual: " + actualPayFrom, Status.FAIL, true);
            }

            // Validate Total Amount
            String expectedTotalAmount = String.format("%.2f", totalAmount);
            String actualTotalAmount = getAttributeFromElement(getInputFieldsMultipleBiller(ElementType.text, Total_Amount), "value").replaceAll("[^\\d.]", "");
            if (actualTotalAmount.equals(expectedTotalAmount)) {
                addToReport("Total Amount validation PASSED. Expected: " + expectedTotalAmount + ", Actual: " + actualTotalAmount, Status.PASS, false);
            } else {
                addToReport("Total Amount validation FAILED. Expected: " + expectedTotalAmount + ", Actual: " + actualTotalAmount, Status.FAIL, true);
            }
        } catch (Exception e) {
            addToReport("Error validating Pay From/Total Amount on success screen. " + e.getMessage(), Status.FAIL, true);
        }

        // --- 2. Validate Success Summary (Bottom) ---
        int expectedCount = allSelectedPayeeDetails.size();
        if (expectedCount == 0) {
            addToReport("Cannot validate bill details, 'allSelectedPayeeDetails' list is empty.", Status.FAIL, true);
            throw new IllegalStateException("allSelectedPayeeDetails list is empty. Cannot validate success screen.");
        }

        String expectedCountMsg = "You Have Successfully Completed " + expectedCount + " out of " + expectedCount + " Payments";

        waitForElementPresence(lblSuccessCount);
        String actualCountMsg = getTextFromElement(lblSuccessCount).trim().replaceAll("\\s+", " "); // Normalize whitespace

        if (actualCountMsg.equals(expectedCountMsg)) {
            addToReport("Success count message validation PASSED: '" + actualCountMsg + "'", Status.PASS, false);
        } else {
            addToReport("Success count message validation FAILED. Expected: '" + expectedCountMsg + "', Actual: '" + actualCountMsg + "'", Status.FAIL, true);
        }

        if (isElementPresentBy(lblSuccessHeader)) {
            addToReport("Main 'Success' label is visible.", Status.PASS, false);
        } else {
            addToReport("Main 'Success' label is NOT visible.", Status.FAIL, true);
        }

        if (isElementPresentBy(lblSuccessMessage)) {
            addToReport("Success sub-message '" + SUCCESS_MESSAGE_SUBTEXT + "' is visible.", Status.PASS, false);
        } else {
            addToReport("Success sub-message is NOT visible.", Status.FAIL, true);
        }

        // --- 3. Loop Through and Validate Each Bill Receipt ---
        for (int i = 0; i < expectedCount; i++) {
            Map<String, String> expectedBill = allSelectedPayeeDetails.get(i);
            String expectedTemplate = expectedBill.get("TemplateName");
            String expectedBillAmount = expectedBill.get("Amount"); // e.g., "LKR 100.00"

            addToReport("---------- Validating Bill " + (i + 1) + " of " + expectedCount + " ('" + expectedTemplate + "') ----------", Status.INFO, false);

            // Wait for the correct pagination dot to be active
            waitForElementPresence(paginationDotActive(i + 1), SHORT_WAIT);
            waitForElementPresence(tblVisibleBillerDetails, SHORT_WAIT);

            // Validate Template Name
            validateTextOnVisibleTable(TEMPLATE_NAME, expectedTemplate);

            // Validate Amount
            validateTextOnVisibleTable(Amount, expectedBillAmount);

            // Validate Payment Status
            validateTextOnVisibleTable(PAYMENT_STATUS_LABEL, PAYMENT_STATUS_SUCCESS_TEXT);

            // Validate Print Button Functionality
            validatePrintButton();

            // Click Next if it's not the last bill
            if (i < expectedCount - 1) {
                if(isElementPresentBy(btnNextBiller)) {
                    clickOnElement(btnNextBiller);
                    addToReport("Clicked Next to see bill " + (i + 2), Status.INFO, false);
                    // Wait for the *next* dot to become active. This is a crucial sync step.
                    waitForElementPresence(paginationDotActive(i + 2), MODERATE_WAIT);
                } else {
                    addToReport("Could not find 'Next' arrow to validate remaining bills.", Status.FAIL, true);
                    break; // Exit loop
                }
            }
        }

        // --- 4. Final Button Check ---
        if (isElementPresentBy(btnMakeAnotherPayment)) {
            addToReport("'Make Another Payment' button is visible.", Status.PASS, false);
        } else {
            addToReport("'Make Another Payment' button is NOT visible.", Status.FAIL, true);
        }

        addToReport("---------- Multiple Biller Success Page Validation Complete ----------", Status.INFO, false);
    }


    /**
     * Helper method to validate text in the currently visible bill detail table.
     * @param labelName The label of the row (e.g., "Template Name")
     * @param expectedText The expected text value for that row
     */
    private void validateTextOnVisibleTable(String labelName, String expectedText) {
        try {
            String actualText = getTextFromElement(getVisibleTableValue(labelName));
            if (actualText.trim().equals(expectedText.trim())) {
                addToReport("'" + labelName + "' validation PASSED: " + actualText, Status.PASS, false);
            } else {
                addToReport("'" + labelName + "' validation FAILED. Expected: '" + expectedText + "', Actual: '" + actualText + "'", Status.FAIL, true);
            }
        } catch (Exception e) {
            addToReport("Could not find or read '" + labelName + "' from the visible table. " + e.getMessage(), Status.FAIL, true);
        }
    }


    /**
     * Helper method to validate the "Print" button functionality on the visible table.
     * Clicks the button and verifies that a new window (print dialog) opens.
     */
    private void validatePrintButton() {
        if (!isElementPresentBy(btnPrintReceipt)) {
            addToReport("Print button is NOT present.", Status.FAIL, true);
            return;
        }
        addToReport("Print button is present.", Status.PASS, false);

        String originalWindow = driver.getWindowHandle();
        int originalWindowCount = driver.getWindowHandles().size();

        clickOnElement(btnPrintReceipt);
        addToReport("Clicked 'Print' button to check functionality.", Status.INFO, false);

        try {
            // Use the method from BasePage
            waitForNewWindowToOpen(originalWindowCount + 1, MODERATE_WAIT);

            Set<String> allWindows = driver.getWindowHandles();
            if (allWindows.size() > originalWindowCount) {
                addToReport("Print button functionality VERIFIED: A new window/tab opened.", Status.PASS, false);

                // Close the new window and switch back
                for (String windowHandle : allWindows) {
                    if (!windowHandle.equals(originalWindow)) {
                        driver.switchTo().window(windowHandle);
                        driver.close();
                        break;
                    }
                }
                driver.switchTo().window(originalWindow);
            } else {
                addToReport("Print button functionality FAILED: No new window opened after click.", Status.FAIL, true);
            }
        } catch (Exception e) {
            addToReport("Print button functionality FAILED: Timed out waiting for a new window.", Status.FAIL, true);
            // Ensure we switch back even on failure
            driver.switchTo().window(originalWindow);
        }
    }

//-----------------  Select Single payee  -----------------

    public void ValidatePayBillModelPageForSingleSelectedBiller(){
        try {
            addToReport("-------------------------- Validating the PayBill model Page --------------------------",Status.INFO,true);
            if (isElementPresentBy(getSPModelPage(QUICK_BILL_PAYMENTS))) {
                addToReport("Quick Bill Payment Model Page is present",Status.PASS,false);

                if (isElementPresentBy(getSpanElements("text-gray-500","Payment Using"))){
                    addToReport("Payment Method section is Present",Status.PASS,false);

                    if (isElementPresentBy(getRadioBtn(RDO_CREDIT_CARD))) {
                        addToReport("Credit Card Radio Button is Present", Status.PASS, false);
                        clickOnElement(getRadioBtn(RDO_CREDIT_CARD));
                        if (isElementPresentBy(ddFromCCard)){
                            addToReport("Credit Cards dropdown is Present",Status.PASS,false);
                        } else {
                            addToReport("Credit Cards dropdown is not Present",Status.FAIL,true);
                        }
                    }else {
                        addToReport("Credit Card Radio Button is not Present",Status.FAIL,true);
                    }

                    if (isElementPresentBy(getRadioBtn(RDO_ACCOUNT))){
                        addToReport("Account Radio Button is Present",Status.PASS,false);
                        clickOnElement(getRadioBtn(RDO_ACCOUNT));
                        try{
                            if (isElementPresentBy(ddFromAccount)){
                                addToReport("Accounts dropdown is Present",Status.PASS,false);
                            } else {
                                addToReport("Accounts dropdown is not Present",Status.FAIL,true);
                            }
                        }catch (Exception e){
                            throw new RuntimeException("Failed to Retrieve Accounts" + e.getMessage(), e);
                        }
                    }else {
                        addToReport("Account Radio Button is not Present",Status.FAIL,true);
                    }
                }else {
                    addToReport("Payment Method section is not present",Status.FAIL,true);
                }


                // --- Start of Data Cross-Validation ---
                if (allSelectedPayeeDetails.isEmpty()) {
                    addToReport("Cannot perform validation. The list of selected payee details is empty.", Status.FAIL, true);
                    throw new IllegalStateException("allSelectedPayeeDetails is empty.");
                }
                if (allSelectedPayeeDetails.size() > 1) {
                    addToReport("Warning: Method is for a single biller, but multiple biller details were found. Validating only the first one.", Status.WARNING, false);
                }

                // Get the details of the first selected biller from the list
                Map<String, String> expectedDetails = allSelectedPayeeDetails.get(0);
                String expectedTemplateName = expectedDetails.get("TemplateName");
                String expectedBillerName = expectedDetails.get("BillerName");
                String expectedAmount = expectedDetails.get("Amount");

                By inputTemplateName = getInputFields(ElementType.text, TEMPLATE_NAME);
                By inputBillerName = getInputFields(ElementType.text, BILLER_NAME);
                By inputAmount = getInputElements(ElementType.text, ElementType.numeric);


                // 1. Validate Template Name
                if (isElementPresentBy(inputTemplateName)) {
                    String actualTemplateName = getAttributeFromElement(inputTemplateName, "value");
                    if (actualTemplateName.equals(expectedTemplateName)) {
                        addToReport("Template Name validation PASSED. Expected: '" + expectedTemplateName + "', Actual: '" + actualTemplateName + "'.", Status.PASS, false);
                    } else {
                        addToReport("Template Name validation FAILED. Expected: '" + expectedTemplateName + "', Actual: '" + actualTemplateName + "'.", Status.FAIL, true);
                    }
                } else {
                    addToReport("Template Name input field could not be found on the modal.", Status.FAIL, true);
                }

                // 2. Validate Biller Name
                if (isElementPresentBy(inputBillerName)) {
                    String actualBillerName = getAttributeFromElement(inputBillerName, "value");
                    if (actualBillerName.equals(expectedBillerName)) {
                        addToReport("Biller Name validation PASSED. Expected: '" + expectedBillerName + "', Actual: '" + actualBillerName + "'.", Status.PASS, false);

                        if (BILLER_DATA.containsKey(actualBillerName)) {
                            List<String> selectedBiller = BILLER_DATA.get(actualBillerName);
                            ValidatePhoneBillers(selectedBiller);
                            selectedBillerName = BILLER_DATA.get(getAttributeFromElement(inputBillerName, "value")).get(0);
                        } else {
                            addToReport("Other Biller is selected", Status.PASS, false);
                        }
                    } else {
                        addToReport("Biller Name validation FAILED. Expected: '" + expectedBillerName + "', Actual: '" + actualBillerName + "'.", Status.FAIL, true);
                    }
                } else {
                    addToReport("Biller Name input field could not be found on the modal.", Status.FAIL, true);
                }

                if (isElementPresentBy(inputAmount)) {
                    clickOnElement(inputAmount);
                    clearTheElement(inputAmount);
                    if (getAttributeFromElement(inputAmount, "value").isEmpty()) {
                        addToReport("Amount field cleared successfully.", Status.PASS, false);
                        if (isElementPresentBy(validateErrorMessage(ERROR_MSG_AMOUNT))){
                            addToReport("Amount required error message is displayed as expected after clearing the field.", Status.PASS, false);
                        } else {
                            addToReport("Amount required error message is NOT displayed after clearing the field.", Status.FAIL, true);
                        }
                    } else {
                        addToReport("Failed to clear the Amount field.", Status.FAIL, true);
                    }
                    sendKeysToElement(inputAmount, expectedAmount);

                    String actualAmount = getAttributeFromElement(inputAmount, "value");
                    String normalizedExpectedAmount = expectedAmount.replaceAll("[^\\d.]", "");
                    String normalizedActualAmount = actualAmount.replaceAll("[^\\d.]", "");

                    if (normalizedActualAmount.trim().equals(normalizedExpectedAmount.trim())) {
                        addToReport("Amount validation PASSED. Expected: '" + normalizedExpectedAmount + "', Actual: '" + normalizedActualAmount + "'.", Status.PASS, false);
                    } else {
                        addToReport("Amount validation FAILED. Expected: '" + normalizedExpectedAmount + "', Actual: '" + normalizedActualAmount + "'.", Status.FAIL, true);
                    }
                } else {
                    addToReport("Amount input field could not be found on the modal.", Status.FAIL, true);
                }
                // --- End of Data Cross-Validation ---

                // Additional action: Retrieve and log account balance below a threshold
                validateLowBalanceToastMessage(ddFromAccount,expectedAmount,CurrencyType.LOCAL,ERROR_TST_INSUF_BALANCE);
                validatePayBill(ddFromAccount,CurrencyType.LOCAL,SUCCESS_OTP_SENT);
                validateOTPConfirmationPage(allSelectedPayeeDetails, selectedFromAccount);


            }else {
                addToReport("Quick Bill Payment Model Page is not Visible",Status.FAIL,true);
            }

        }catch(Exception e) {
            addToReport("An unexpected error occurred in ValidatePayBillModelPageForSingleSelectedBiller", Status.FAIL, true);
            throw new RuntimeException("Error during PayNow Model Page validation: " + e.getMessage(), e);
        }
    }

    private void validateOTPConfirmationPage(List<Map<String, String>> allSelectedPayeeDetails,String payFrom){
        String normalizedPayFrom = "";

        try {
            waitForElementPresence(otpConfirmationPage);
            if (isElementPresentBy(otpConfirmationPage)){
                addToReport("OTP Confirmation Page is loaded successfully",Status.PASS,false);
                if (isElementPresentBy(otpPageMainHeading)){
                    addToReport("OTP Page Main Heading is visible",Status.PASS,false);
                }else {
                    addToReport("OTP Page Main Heading is not visible",Status.FAIL,true);
                }
                if (isElementPresentBy(otpPageSubHeading)){
                    addToReport("OTP Page Sub Heading is visible",Status.PASS,false);
                }else {
                    addToReport("OTP Page Sub Heading is not visible",Status.FAIL,true);
                }

                if (allSelectedPayeeDetails.isEmpty()){
                    addToReport("Cannot perform validation. error loading the selected payee data.", Status.FAIL, true);
                    throw new IllegalStateException("Cannot perform validation. error loading the selected payee data..");
                }else {
                    Map<String, String> expectedDetails = allSelectedPayeeDetails.get(0);

                    String expectedTemplateName = expectedDetails.get("TemplateName");
                    String expectedBillerName = expectedDetails.get("BillerName");
                    String expectedAmount = expectedDetails.get("Amount");
                    String expectedFiledValue = expectedDetails.get("FiledValue");

//                    -----------------------validate the otp page data -------------

                    if (isElementPresentBy(otpPageBillerName)){
                        String actualBillerName = getTextFromElement(otpPageBillerName);
                        if (actualBillerName.equals(expectedBillerName)){
                            addToReport("Biller Name validation PASSED. Expected: '" + expectedBillerName + "', Actual: '" + actualBillerName + "'.", Status.PASS, false);
                        }else {
                            addToReport("Biller Name validation FAILED. Expected: '" + expectedBillerName + "', Actual: '" + actualBillerName + "'.", Status.FAIL, true);
                        }
                    }else {
                        addToReport("Biller Name is not visible in OTP Page",Status.FAIL,true);
                    }

                    if (isElementPresentBy(otpPageFieldValue)){
                        String actualFiledValue = getTextFromElement(otpPageFieldValue);
                        if (actualFiledValue.trim().equals(expectedFiledValue.trim())){
                            addToReport("Filed Value validation PASSED. Expected: '" + expectedFiledValue + "', Actual: '" + actualFiledValue + "'.", Status.PASS, false);
                        }else {
                            addToReport("Filed Value validation FAILED. Expected: '" + expectedFiledValue + "', Actual: '" + actualFiledValue + "'.", Status.FAIL, true);
                        }
                    }else {
                        addToReport("Filed Value is not visible in OTP Page",Status.FAIL,true);
                    }

                    if (isElementPresentBy(getInputFields(ElementType.text,PAYFROM))){
                        String actualPayFrom = getTextFromElement(getInputFields(ElementType.text,PAYFROM));
                        String normalizedActualPayFrom = actualPayFrom.replaceAll("[^\\d.]", "");
                         normalizedPayFrom = payFrom.replaceAll("[^\\d.]", "");
                        if (normalizedActualPayFrom.trim().equals(normalizedPayFrom.trim())) {
                            addToReport("PayFrom validation PASSED. Expected: '" + normalizedPayFrom + "', Actual: '" + normalizedActualPayFrom + "'.", Status.PASS, false);
                        } else {
                            addToReport("PayFrom validation FAILED. Expected: '" + normalizedPayFrom + "', Actual: '" + normalizedActualPayFrom + "'.", Status.FAIL, true);
                        }
                    }else {
                        addToReport("payFrom field is not visible in OTP Page",Status.FAIL,true);
                    }

                    if (isElementPresentBy(getInputFields(ElementType.text, Amount))){
                        String actualAmount = getTextFromElement(getInputFields(ElementType.text,Amount));

                        if (actualAmount.trim().equals(expectedAmount.trim())) {
                            addToReport("Amount validation PASSED. Expected: '" +expectedAmount + "', Actual: '" + actualAmount + "'.", Status.PASS, false);
                        } else {
                            addToReport("Amount validation FAILED. Expected: '" + expectedAmount + "', Actual: '" + actualAmount + "'.", Status.FAIL, true);
                        }
                    }else {
                        addToReport("Amount field is not visible in OTP Page",Status.FAIL,true);
                    }


                    String siblingText = selectedBillerName;

                    if (isElementPresentBy(getInputFields(ElementType.text, siblingText))){
                        String actualFieldValue = getTextFromElement(getInputFields(ElementType.text,siblingText));

                        if (actualFieldValue.trim().equals(expectedFiledValue.trim())) {
                            addToReport("Field validation PASSED. Expected: '" +expectedFiledValue + "', Actual: '" + actualFieldValue + "'.", Status.PASS, false);
                        } else {
                            addToReport("Field validation FAILED. Expected: '" + expectedFiledValue + "', Actual: '" + actualFieldValue + "'.", Status.FAIL, true);
                        }
                    }else {
                        addToReport("Field field is not visible in OTP Page",Status.FAIL,true);
                    }

                    if (isElementPresentBy(lblPaymentProcessing)){
                        addToReport("Payment Processing label is visible in OTP Page",Status.PASS,false);
                    }
                    else {
                        addToReport("Payment Processing label is not visible in OTP Page",Status.FAIL,true);
                    }

                    if (isElementPresentBy(lblAmountConfirmation(expectedAmount))){
                        addToReport("Amount Confirmation label is visible in OTP Page",Status.PASS,false);
                    }
                    else {
                        addToReport("Amount Confirmation label is not visible in OTP Page",Status.FAIL,true);
                    }
                }


            }else {
                addToReport("OTP Confirmation Page is not loaded",Status.FAIL,true);
            }
        }catch (Exception e){
            addToReport("Failed to validate OTP Confirmation Page",Status.FAIL,true);
            throw new RuntimeException("Failed to validate OTP Confirmation Page" + e.getMessage(), e);
        }

    }

    private void ValidatePhoneBillers(List<String> config) {
        // unpack config values
        String placeholder = config.get(0);
        String reenterPlaceholder = config.get(1);
        String errorRequired = config.get(2);
        String errorMismatch = config.get(3);

        // Get expected field value from saved payee details
        Map<String, String> expectedDetails = allSelectedPayeeDetails.get(0);
        String expectedFiledValue = expectedDetails.get("FiledValue");

        By inputFiledValue = getInputFields(ElementType.number, placeholder);
        By inputReenterFiledValue = getInputFields(ElementType.number, reenterPlaceholder);

        // ------------------- Validate Main Input -------------------
        waitForElementPresence(inputFiledValue);
        if (isElementPresentBy(inputFiledValue)) {
            clearTheElement(inputFiledValue);
            if (getAttributeFromElement(inputFiledValue, "value").isEmpty()) {
                addToReport("Phone Number field cleared successfully.", Status.PASS, false);
                clickOnElement(dynamicPayNowBtn);
                if (isElementPresentBy(validateErrorMessage(errorRequired))) {
                    addToReport("Phone Number required error message displayed correctly.", Status.PASS, false);
                } else {
                    addToReport("Phone Number required error message NOT displayed.", Status.FAIL, true);
                }
            }

            sendKeysToElement(inputFiledValue, expectedFiledValue);

            String actualValue = getAttributeFromElement(inputFiledValue, "value");
            if (normalize(actualValue).equals(normalize(expectedFiledValue))) {
                addToReport("Phone Number validation PASSED. Expected: '" + expectedFiledValue + "', Actual: '" + actualValue + "'.", Status.PASS, false);
            } else {
                addToReport("Phone Number validation FAILED. Expected: '" + expectedFiledValue + "', Actual: '" + actualValue + "'.", Status.FAIL, true);
            }
        } else {
            addToReport("Phone Number input field not found on modal.", Status.FAIL, true);
        }

        // ------------------- Validate Reenter Input -------------------
        waitForElementPresence(inputReenterFiledValue);
        if (isElementPresentBy(inputReenterFiledValue)) {
            clearTheElement(inputReenterFiledValue);
            if (getAttributeFromElement(inputReenterFiledValue, "value").isEmpty()) {
                addToReport("Re-enter field cleared successfully.", Status.PASS, false);
                clickOnElement(dynamicPayNowBtn);
                if (isElementPresentBy(validateErrorMessage(errorRequired))) {
                    addToReport("Re-enter required error message displayed correctly.", Status.PASS, false);
                }else {
                    addToReport("Re-enter required error message NOT displayed.", Status.FAIL, true);
                }
                // check mismatch case
                clearTheElement(inputReenterFiledValue);
                clickOnElement(inputFiledValue);
                sendKeysToElement(inputReenterFiledValue, "1234567");

                if (isElementPresentBy(validateErrorMessage(errorMismatch))) {
                    addToReport("Mismatch error displayed correctly.", Status.PASS, false);
                    clearTheElement(inputReenterFiledValue);
                } else {
                    addToReport("Mismatch error NOT displayed.", Status.FAIL, true);
                }
            }

            sendKeysToElement(inputReenterFiledValue, expectedFiledValue);

            String actualValue = getAttributeFromElement(inputReenterFiledValue, "value");
            if (normalize(actualValue).equals(normalize(expectedFiledValue))) {
                addToReport("Re-enter validation PASSED. Expected: '" + expectedFiledValue + "', Actual: '" + actualValue + "'.", Status.PASS, false);
            } else {
                addToReport("Re-enter validation FAILED. Expected: '" + expectedFiledValue + "', Actual: '" + actualValue + "'.", Status.FAIL, true);
            }
        } else {
            addToReport("Re-enter Phone Number input field not found on modal.", Status.FAIL, true);
        }
    }

    // helper to clean values before comparing
    private String normalize(String value) {
        return value.replaceAll("[,]", "").trim();
    }


    /**
     * Selects an account with balance below the given threshold and validates insufficient balance error message.
     *
     * @param ddFromAccount   Dropdown WebElement for 'From Account'
     * @param expectedAmount  Payment amount threshold as String or double
     * @param currencyType    Currency type (e.g., LOCAL)
     */
    public void validateLowBalanceToastMessage(By ddFromAccount, String expectedAmount, CurrencyType currencyType ,String errorMessage) {
        addToReport("------------------------------------- Check for low balance error message -------------------------------------", Status.INFO, false);
        // Get account with balance below threshold
        String minAmountAccountNumber = getValueBelowThreshold(ddFromAccount, currencyType, Double.parseDouble(expectedAmount.replaceAll("[^0-9.]", "")));
        if (minAmountAccountNumber == null) {
            addToReport("No account found with balance less than " + expectedAmount, Status.INFO, false);
            return;
        }else {
            addToReport("Account " + minAmountAccountNumber + " found with balance less than " + expectedAmount, Status.PASS, false);
        }


        // Select the account in the dropdown
        selectFromDropdown(ddFromAccount, minAmountAccountNumber, "value");
        addToReport("Selected account " + minAmountAccountNumber + " from the dropdown", Status.INFO, false);
        validateSelectedAccountCard(currencyType,minAmountAccountNumber);
        //Click Pay Now
        clickOnElement(dynamicPayNowBtn);
        // Validate insufficient balance toast
        if (isElementPresentBy(validateToastMessage(errorMessage))) {
            addToReport("Insufficient balance error message is displayed as expected when an account with low balance is selected.", Status.PASS, false);
            if(isElementPresentBy(btnCloseToast)){
                clickOnElement(btnCloseToast);
                addToReport("Closed the error message popup",Status.INFO,false);
            }else{
                addToReport("Close button for error message popup is not visible",Status.FAIL,true);
            }
        } else {
            addToReport("Insufficient balance error message is NOT displayed when an account with low balance is selected.", Status.FAIL, true);
        }
    }

    /**
     * Selects an account with max balance  and validates success otp message.
     *
     * @param ddFromAccount   Dropdown WebElement for 'From Account'
     * @param currencyType    Currency type (e.g., LOCAL)
     *
     */
    public String validatePayBill(By ddFromAccount, CurrencyType currencyType ,String OTPMessage) {
        addToReport("------------------------------------- Check for High Balanced Account -------------------------------------", Status.INFO, false);
        String payableAccountNumber = getValueOfHighestVisibleAmount(ddFromAccount, currencyType);
        selectedFromAccount = payableAccountNumber;
        if (payableAccountNumber == null) {
            addToReport("No account found ", Status.FAIL, false);
            return null;
        }
        // Select the account in the dropdown
        selectFromDropdown(ddFromAccount,payableAccountNumber, "value");
        addToReport("Selected account " + payableAccountNumber + " from the dropdown", Status.INFO, true);
        validateSelectedAccountCard(CurrencyType.LOCAL,payableAccountNumber);
        // Validate OTP Messaege
        //Click Pay Now
        clickOnElement(dynamicPayNowBtn);
        if (isElementPresentBy(validateToastMessage(OTPMessage))) {
            addToReport("OTP sent Message is sent.", Status.PASS, true);
            if (isElementPresentBy(btnCloseToast)){
                clickOnElement(btnCloseToast);
                addToReport("Closed the OTP sent message popup",Status.INFO,false);
            }else {
                addToReport("Close button for OTP sent message popup is not visible",Status.FAIL,true);
            }
        } else {
            addToReport("OTP sent Message is not sent ", Status.FAIL, true);
        }
        return payableAccountNumber;
    }

    public void validateSelectedAccountCard(CurrencyType currencyType,String selectedAccountNumber){
        try {
            waitForElementPresence(selectedAccountContainer);
            waitForElementPresence(getSelectedAccountDetails(1));
            String AccountType  = getTextFromElement(getSelectedAccountDetails(1));
            String AccountNumber  = getTextFromElement(getSelectedAccountDetails(2));
            String AccountBalance  = getTextFromElement(getSelectedCurrencyDetails(1));
            //validate the currency and the amount

            if (currencyType == CurrencyType.LOCAL && !(AccountType.trim().equals(lbl_SAVING_ACCOUNT))) {
                addToReport("Expected Account Type is "+lbl_SAVING_ACCOUNT+" Actual Account Type is :"+AccountType,Status.FAIL,true);
                }
            else if (currencyType == CurrencyType.OTHER && !(AccountType.trim().equals(lbl_FC_SAVING_ACCOUNT))) {
                addToReport("Expected Account Type is "+lbl_FC_SAVING_ACCOUNT+" Actual Account Type is :"+AccountType,Status.FAIL,true);
            }else {
                addToReport("Account Type is shown as expected",Status.PASS,false);
            }


            if (!(AccountNumber.trim().equals(selectedAccountNumber.trim()))){
                addToReport("Expected Account Number is "+selectedAccountNumber+" Actual Account Number is "+AccountNumber,Status.FAIL,true);
            }else {
                addToReport("Account Number is shown as expected",Status.PASS,false);
            }

            //validate the currency and the amount ON THR CARD


        } catch(Exception e) {
            addToReport("An unexpected error occurred in validateSelectedAccountCard", Status.FAIL, true);
            throw new RuntimeException("Error during validateSelectedAccountCard: " + e.getMessage(), e);
        }}

    /**
     * Enter otp value
     *
     * @param otp - expected title text
     *
     */
    public void


    enterOTPAndContinue(String otp) {

        try {

            //validate if confirm button is disabled prior to entering otp
//            boolean isBtnConfirmDisabled = waitForElementPresence(btnDisabledConfirm);
//
//            if (isBtnConfirmDisabled) {
//                addToReport("OTP page button confirm is disabled", Status.PASS,false);
//            } else {
//                addToReport("OTP page button confirm is not disabled", Status.FAIL);
//                throw new RuntimeException("OTP page button confirm is not disabled as expected.");
//            }

            waitForElementToBeClickable(tfOTP(1),SHORT_WAIT);
            //Enter OTP values and continue
            sendKeysToElement(tfOTP(1), String.valueOf(otp));
        } catch (Exception e) {
            addToReport("Error when entering OTP", Status.FAIL);
            throw new RuntimeException("Failed to enter OTP " + e.getMessage(), e);
        }
        waitForElementToBeClickable(btnConfirmOtp,LONG_WAIT);
        clickOnElement(btnConfirmOtp);
        waitFor(SHORT_WAIT);

    }
    /**
     * Validdate Paymnet Success Page
     */
    public void validatePaymentSuccessPage(){

        addToReport("----------Start of validation of Success page----------", Status.PASS, false);

        if (allSelectedPayeeDetails.isEmpty()){
            addToReport("Cannot perform validation. error loading the selected payee data.", Status.FAIL, true);
            throw new IllegalStateException("Cannot perform validation. error loading the selected payee data..");
        }else {
            Map<String, String> expectedDetails = allSelectedPayeeDetails.get(0);

            String expectedTemplateName = expectedDetails.get("TemplateName");
            String expectedBillerName = expectedDetails.get("BillerName");
            String expectedAmount = expectedDetails.get("Amount");
            String expectedFiledValue = expectedDetails.get("FiledValue");

//                    -----------------------validate the Success Page -------------
//                        ----- change this to success page -----

            if (isElementPresentBy(pgeSuccess)) {
                addToReport("Payment Success Page is loaded successfully", Status.PASS, false);


                if (isElementPresentBy(pgeSuccessPageBillerName)) {
                    String actualBillerName = getTextFromElement(pgeSuccessPageBillerName);
                    if (actualBillerName.equals(expectedBillerName)) {
                        addToReport("Biller Name validation PASSED. Expected: '" + expectedBillerName + "', Actual: '" + actualBillerName + "'.", Status.PASS, false);
                    } else {
                        addToReport("Biller Name validation FAILED. Expected: '" + expectedBillerName + "', Actual: '" + actualBillerName + "'.", Status.FAIL, true);
                    }
                } else {
                    addToReport("Biller Name is not visible in Payment Success Page Page", Status.FAIL, true);
                }

                if (isElementPresentBy(pgeSuccessPageFieldValue)) {
                    String actualFiledValue = getTextFromElement(pgeSuccessPageFieldValue);
                    if (actualFiledValue.trim().equals(expectedFiledValue.trim())) {
                        addToReport("Filed Value validation PASSED. Expected: '" + expectedFiledValue + "', Actual: '" + actualFiledValue + "'.", Status.PASS, false);
                    } else {
                        addToReport("Filed Value validation FAILED. Expected: '" + expectedFiledValue + "', Actual: '" + actualFiledValue + "'.", Status.FAIL, true);
                    }
                } else {
                    addToReport("Filed Value is not visible in Payment Success Page", Status.FAIL, true);
                }

                if (isElementPresentBy(getInputFields(ElementType.text, PAYFROM))) {
                    String actualPayFrom = getTextFromElement(getInputFields(ElementType.text, PAYFROM));
                    String normalizedActualPayFrom = actualPayFrom.replaceAll("[^\\d.]", "");
                    selectedFromAccount = selectedFromAccount.replaceAll("[^\\d.]", "");

                    if (normalizedActualPayFrom.trim().equals(selectedFromAccount.trim())) {
                        addToReport("PayFrom validation PASSED. Expected: '" + selectedFromAccount + "', Actual: '" + normalizedActualPayFrom + "'.", Status.PASS, false);
                    } else {
                        addToReport("PayFrom validation FAILED. Expected: '" + selectedFromAccount + "', Actual: '" + normalizedActualPayFrom + "'.", Status.FAIL, true);
                    }
                } else {
                    addToReport("payFrom field is not visible in Payment Success Page", Status.FAIL, true);
                }

                if (isElementPresentBy(getInputFields(ElementType.text, Amount))) {
                    String actualAmount = getTextFromElement(getInputFields(ElementType.text, Amount));

                    if (actualAmount.trim().equals(expectedAmount.trim())) {
                        addToReport("Amount validation PASSED. Expected: '" + expectedAmount + "', Actual: '" + actualAmount + "'.", Status.PASS, false);
                    } else {
                        addToReport("Amount validation FAILED. Expected: '" + expectedAmount + "', Actual: '" + actualAmount + "'.", Status.FAIL, true);
                    }
                } else {
                    addToReport("Amount field is not visible in Payment Success Page", Status.FAIL, true);
                }


                String siblingText = selectedBillerName;

                if (isElementPresentBy(getInputFields(ElementType.text, siblingText))) {
                    String actualFieldValue = getTextFromElement(getInputFields(ElementType.text, siblingText));

                    if (actualFieldValue.trim().equals(expectedFiledValue.trim())) {
                        addToReport("Field validation PASSED. Expected: '" + expectedFiledValue + "', Actual: '" + actualFieldValue + "'.", Status.PASS, false);
                    } else {
                        addToReport("Field validation FAILED. Expected: '" + expectedFiledValue + "', Actual: '" + actualFieldValue + "'.", Status.FAIL, true);
                    }
                } else {
                    addToReport("Field field is not visible in Payment Success Page", Status.FAIL, true);
                }

                if (isElementPresentBy(lblPaymentProcessing)) {
                    addToReport("Payment Processing label is visible in Payment Success Page", Status.PASS, false);
                } else {
                    addToReport("Payment Processing label is not visible in Payment Success Page", Status.FAIL, true);
                }

                if (isElementPresentBy(lblAmountConfirmation(expectedAmount))) {
                    addToReport("Amount Confirmation label is visible in Payment Success Page", Status.PASS, false);
                } else {
                    addToReport("Amount Confirmation label is not visible in Payment Success Page", Status.FAIL, true);
                }
                //OTP
                enterOTPAndContinue(OTP);

                // Validate the success label and other information
                if (isElementPresentBy(lblSuccess)) {
                    addToReport("Validated the success message in the Payment Success Page", Status.PASS, true);
                } else {
                    addToReport("Failed to validate the success message in the Payment Success Page", Status.FAIL, true);
                }

                // Validate reference number
                String[] referenceNumber = getTextFromElement(lblRefernceID).split("- ");
                if (referenceNumber[1] != null) {
                    addToReport("Obtained the payment reference number " + referenceNumber[1], Status.PASS, false);
                } else {
                    addToReport("Failed to get the reference number", Status.FAIL);
                }

                // Check for download option in the success page
                if (isElementPresentBy(btnPrint)) {
                    addToReport("Validated the download option availability in Payment Success Page", Status.PASS, false);
                } else {
                    addToReport("Failed to validate the download option availability in Payment Success Page", Status.FAIL);
                }

                addToReport("----------End of validation of OTP success page----------", Status.PASS, true);
                //Close the popup
                scrollPageToTop();
                waitForElementToBeClickable(btnOTPClosePopup, MODERATE_WAIT);
                clickOnElement(btnOTPClosePopup);

            }else{
                addToReport("Payment Success Page is not loaded", Status.FAIL, true);
            }
    }
    }


//    public void printAllSelectedPayeeDetails() {
//        if (allSelectedPayeeDetails.isEmpty()) {
//            addToReport("No payee details available.-------------------------------------------------------------------------------",Status.INFO);
//            return;
//        }
//
//        int index = 1;
//        for (Map<String, String> payee : allSelectedPayeeDetails) {
//            addToReport("Payee " + index + ":",Status.INFO);
//            for (Map.Entry<String, String> entry : payee.entrySet()) {
//                addToReport("  " + entry.getKey() + " : " + entry.getValue(),Status.PASS);
//            }
//            index++;
//            addToReport("-----------------------------------",Status.PASS);
//        }
//    }

            public void clearUsedIndexes () {

                //this method is used to clear the selected indexes
                usedIndexes.clear();
                allSelectedPayeeDetails.clear();
                selectedFromAccount ="";
                selectedBillerName ="";
            }

            /**
             * Navigate back to dashboard
             */
            public void navigateBackToDashboard () {
                waitFor(VERY_SHORT_WAIT);
                waitForElementPresence(btnDashboard);
                clickOnElement(btnDashboard);
                waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
                waitForPageLoadCompleteJS();
                waitFor(VERY_SHORT_WAIT);
            }



}




