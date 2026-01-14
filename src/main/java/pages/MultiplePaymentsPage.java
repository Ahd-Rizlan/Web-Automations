package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;
;
import static utils.Drivers.*;
import static utils.Drivers.VERY_SHORT_WAIT;
import static utils.constants.MultipleBillersConstants.MAX_BILLER_ERROR;
import static utils.constants.MultiplePaymentsConstants.*;

public class MultiplePaymentsPage extends BasePage {
    public MultiplePaymentsPage(WebDriver driver) {
        super(driver);
    }
    Set<Integer> usedIndexes = new HashSet<>();
    static float totalNickName = 0;
    private List<Map<String, String>> allSelectedPayeeDetails = new ArrayList<>();



    public enum ElementType {
        button, label, span, div, p,text,numeric,number;
    }
    public enum PayUsing {
        Card, LKR ,OTHER;
    }


    private static  String selectedFromAccount ="";
    private static final By btnDashboard = By.xpath("//button/a[contains(normalize-space(text()), 'Dashboard')]");
    private static final By lblLoadingIcon = By.xpath("//div[contains(@class,'AccountsCards_loader')]");

    private static final By imgGreyLoader = By.xpath("//div[contains(@class,'bg-gray')]");
    private static final By btnRightArrow = By.xpath("//img[contains(@src,'FArrowRight')]");
    private static final By btnPaginationNumbers = By.xpath("//img[contains(@src,'FArrowRight')]/ancestor::div[contains(@class,'flex justify-end')]/div[1]/div");
    private static final By rdoSavedPayee = By.xpath("//input[@type='checkbox' and @id='savePayee-undefined']");
    private static final By iconFavorite = By.xpath(".//td[7]//img[contains(@src,'Star') or contains(@src,'FavStar')]");

    private static final By menuSavedPayee_Billers = By.xpath("//a[contains(@class,'NavBar_navlink__CRz3E')  and normalize-space(text())='Payees & Billers']");
    private static final By menuItem_SavedPayees = By.xpath("//div[contains(@class,'SubMenu_item__z9l12')  and normalize-space(text())='Saved Payees']");
    // Add this under your btnRightArrow locator
    private static final By btnLeftArrow = By.xpath("//div[contains(@class,'hover:cursor-pointer') and .//img[contains(@src,'ArrowLeft')]]");
    private static final By btnNewPayee = By.xpath("//button[.//span[normalize-space(text())='Add New Payee']]");
    private static final By lblPageSize = By.xpath("//label[@for='pageSize' and normalize-space()='Payees per page']");
    private static final By ddPageSize = By.xpath("//select[@id='pageSize']");
    private static final By ddOption = By.xpath("//select[@id='pageSize']/option");
    private static final By tblRows = By.xpath("//tbody[contains(@class,'bg-white')]/tr");
    private static final By btnBack = By.xpath("//button[.//div[normalize-space(text())='Back']]");
    // --- Locators for Transaction Page (Modal) ---
    private static final By lblTransferTitle = By.xpath("//div[text()='Multiple Fund Transfer']");
    private static final By ddFromAccount = By.id("accountfrom");



    private static final By tblSavedPayeesRows = By.xpath("//tbody//tr");
    private static final By tblSBNotSelectedRows = By.xpath("//tbody//tr[contains(@class,'bg-white')]");

    // Card Navigation Arrows in Transaction Modal
    private static final By btnCardNext = By.xpath("//div[.//img[@alt='Next']]");
    private static final By btnCardPrev = By.xpath("//div[.//img[@alt='Previous']]");

    // Card Details Inputs (Read-only fields)
    private static final By txtNickName = By.xpath("//input[@placeholder='Nick Name']");
    private static final By txtAccNumber = By.xpath("//input[@placeholder='Account Number']");
    private static final By txtAccName = By.xpath("//input[@placeholder='Account Name']");
    private static final By txtBankName = By.xpath("//input[@placeholder='Bank Name']");

    private static final By lblSelectedPayeeContainer = By.xpath("//div[contains(@class,'flex-wrap') and contains(@class,'justify-end')]");

    private static final By btnTransfer = By.xpath("//button[contains(text(),'Transfer LKR')]");

    private static final By toastError = By.xpath("//div[contains(@class,'Toastify__toast--error')]//div[contains(text(),'Limit reached')]");
    private static final By btnPayNow = By.xpath("//button[normalize-space()='Pay Now']");
    private static final By btnCloseToast = By.xpath("//button[contains(@class,'close')]");

    private static  By selectedDDOption(String optionText) {
        return  By.xpath("//select[@id='pageSize']/option[normalize-space()='" + optionText + "']");
    }

    private static By tabHeader(String tabName) {
        return By.xpath("//button[span[normalize-space(text())='"+tabName+"']]");
    }
    private static By recordCount(int index) {
        return By.xpath("//tbody[contains(@class,'bg-white')]/tr["+index+"]");
    }
    private static By pageHeader(String headerTxt , int index) {
        return By.xpath("(//span[normalize-space(text())='"+headerTxt+"' and parent::*[@class='flex flex-col']])["+index+"]");
    }
    private static By dynamicChangedButton(String btnName){
        return By.xpath("//button[normalize-space(text())='"+btnName+"']");
    }
    private static By miniTabHeader(String tabName) {
        return By.xpath("//div[normalize-space(text())='" + tabName + "']");
    }

    private static By tblObtainCellValue(int Row, int Col) {
        return By.xpath("//tbody//tr[" + Row + "]/td[" + Col + "]");
    }
    private static By selectSavedPayees(int Row) {
    return By.xpath("(//tbody//tr)[" + Row + "]");
}
    private static By validateToastMessage(String Message){
        return By.xpath("//div[@role='alert']//div[contains(text(),'"+Message+"')]");
    }

    private static By lblSelectedPayee(String NickName){
        return By.xpath("//div[contains(@class,'bg-[#F5883C]') and contains(normalize-space(.),'"+NickName+"')]");
    }

    private static By getPayNowButton(String amount) {
        return By.xpath("//button[contains(translate(normalize-space(text()), ',',''), 'Pay now LKR " + amount + "')]");
    }
    public static By getPayNowButton() {
        return By.xpath("//button[contains(normalize-space(text()),'Pay now')]");
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
     * Select mini tab
     *
     * @param headerTab - Main tabs Eg.Send money
     */
    public void selectMiniTab(String headerTab) {
        try {
            waitForElementPresence(miniTabHeader(headerTab));
            clickOnElement(miniTabHeader(headerTab));
            addToReport("Selected tab : " + headerTab, Status.PASS, false);
        } catch (Exception e) {
            addToReport("Selecting " + headerTab + " failed ", Status.FAIL);
            throw new RuntimeException("Failed to select tab" + e.getMessage(), e);
        }
    }
    /**
     * Navigate to saved Payee through nave bar
     */
    public void navigateToPayeeAndBillers() {
        waitForElementPresence(menuSavedPayee_Billers);
        clickOnElement(menuSavedPayee_Billers);
        waitForElementPresence(menuItem_SavedPayees);
        clickOnElement(menuItem_SavedPayees);
    }
    // ==================================================================================
    //                           MAIN VALIDATION METHOD
    // ==================================================================================

    /**
     * Main Entry Point: Validates data availability across ALL page size options.
     * @param primaryTab The tab to select before starting validation.
     */
    public void validateDataAvailablity(String primaryTab) {
        try {
            // 1. Setup
            selectMiniTab(primaryTab);
            waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);

            // 2. Get all options to test
            List<String> optionsTextList = getAllPageSizeOptions();

            if (optionsTextList.isEmpty()) {
                addToReport("No Page Size options found to test.", Status.FAIL, true);
                return;
            }

            // 3. Iterate through each option
            for (String sizeOption : optionsTextList) {
                validatePaginationForSize(sizeOption);
            }

            addToReport("Completed validation for ALL Page Size options.", Status.PASS);

        } catch (Exception e) {
            addToReport("Error during data availability validation", Status.FAIL);
            throw new RuntimeException("Validation failed", e);
        }
    }


    /**
     * Comprehensive Validation with Dynamic Column Mapping.
     * 1. Validates Table Headers.
     * 2. Dynamically finds column indexes for Checkbox, Account No, and Actions.
     * 3. Iterates pages and validates data.
     *
     * @param tabName         The tab to select.
     * @param expectedHeaders Array of expected header names (Order matters for initial validation).
     */
    public void validateDataAvailablityOfUniqueTabs(String tabName, String[] expectedHeaders) {
        try {
            addToReport("=== Starting Dynamic Validation for Tab: " + tabName + " ===", Status.INFO);

            // 1. Select Tab
            By tabLocator = miniTabHeader(tabName);
            if (isElementPresentBy(tabLocator)) {
                clickOnElement(tabLocator);
            } else {
                addToReport("Tab '" + tabName + "' not found.", Status.FAIL, true);
                return;
            }
            waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);

            // 2. Validate Headers & Get Column Indexes
            validateTableHeaders(expectedHeaders);

            // --- Dynamic Column Mapping ---
            // We convert the array to a List to easily find indexes (0-based, so we add +1 for XPath)
            List<String> headerList = Arrays.asList(expectedHeaders);

            int colCheckbox = headerList.indexOf("Add to List.") + 1;
            int colAccNum   = headerList.indexOf("Account Number") + 1;
            int colAccName  = headerList.indexOf("Account Name") + 1; // <--- NEW Extraction
            int colFav      = headerList.indexOf("Add to Favorites") + 1;
            int colActions  = headerList.indexOf("Actions") + 1;

            if (colCheckbox == 0 || colAccNum == 0 || colFav == 0 || colActions == 0 || colAccName == 0) {
                addToReport("Critical columns not found in header array!", Status.FAIL, true);
                return;
            }

            // 3. Loop through all pages
            int pageCounter = 1;
            boolean hasNextPage = true;

            do {
                addToReport("--- Validating Page " + pageCounter + " ---", Status.INFO);
                waitFor(SHORT_WAIT);

                List<WebElement> rows = driver.findElements(tblRows);

                if (rows.isEmpty()) {
                    addToReport("Page " + pageCounter + ": Warning! No records found.", Status.WARNING, true);
                } else {
                    for (int i = 0; i < rows.size(); i++) {
                        boolean doFullTest = (pageCounter == 1 && i < 3);
                        // PASS colAccName to the helper
                        validateRowSpecifics(i + 1, colCheckbox, colAccNum, colAccName, colActions, colFav, doFullTest);
                    }
                    addToReport("Page " + pageCounter + ": " + rows.size() + " rows validated.", Status.PASS, false);
                }

                // 4. Check for Next Page
                List<WebElement> nextArrows = driver.findElements(btnRightArrow);
                if (!nextArrows.isEmpty() && nextArrows.get(0).isDisplayed()) {
                    clickOnElement(btnRightArrow);
                    waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);
                    pageCounter++;
                } else {
                    hasNextPage = false;
                }

            } while (hasNextPage);

            addToReport("Validation Complete for tab " + tabName, Status.PASS);

        } catch (Exception e) {
            addToReport("Error validating tab " + tabName, Status.FAIL);
            throw new RuntimeException("Validation failed", e);
        }
    }

    /**
     * Validates headers against the expected array.
     */
    private void validateTableHeaders(String[] expectedHeaders) {
        List<WebElement> headers = driver.findElements(By.xpath("//thead//th"));

        if (headers.size() != expectedHeaders.length) {
            addToReport("Header Count Mismatch! Expected: " + expectedHeaders.length + ", Found: " + headers.size(), Status.FAIL, true);
        }

        for (int i = 0; i < Math.min(headers.size(), expectedHeaders.length); i++) {
            String actualHeader = headers.get(i).getText().trim();
            if (!actualHeader.equalsIgnoreCase(expectedHeaders[i])) {
                addToReport("Header Mismatch at index " + i + ". Expected: '" + expectedHeaders[i] + "', Found: '" + actualHeader + "'", Status.FAIL, true);
            }
        }
        addToReport("Table Headers Validated Successfully.", Status.PASS, false);
    }

    /**
     * Validates Checkbox (Syncs with Top List), Numeric Account Number, Favorites, and Action Buttons.
     * Only performs the slow sync check for the top few rows.
     */
    private void validateRowSpecifics(int rowIndex, int colCheckbox, int colAccNum, int colAccName, int colActions, int colFav, boolean performFullFunctionalTest) {
        try {
            // Get Account Name for validation (Trimmed)
            String accName = getTextFromElement(tblObtainCellValue(rowIndex, colAccName));
            if(accName != null) accName = accName.trim();

            // --- 1. Validate Checkbox & Chip Sync ---
            By chkBoxLocator = By.xpath("//tbody//tr[" + rowIndex + "]/td[" + colCheckbox + "]//input[@type='checkbox']");

            if (isElementPresentBy(chkBoxLocator)) {
                if (performFullFunctionalTest) {
                    WebElement chkBox = driver.findElement(chkBoxLocator);
                    if (chkBox.isEnabled()) {

                        // --- ACTION: Check ---
                        chkBox.click();
                        waitFor(SHORT_WAIT); // Wait for chip animation

                        // 1. Validate Box is Checked
                        if (!chkBox.isSelected())
                            addToReport("Row " + rowIndex + ": Checkbox Check Failed.", Status.FAIL, true);

//                        // 2. Validate Chip Appeared (Visual Sync)
//                        if (isElementPresentBy(selectedPayeeChip(accName))) {
//                            addToReport("Row " + rowIndex + ": Selection Chip for '" + accName + "' appeared successfully.", Status.PASS, false);
//                        } else {
//                            addToReport("Row " + rowIndex + ": Selection Chip for '" + accName + "' NOT found!", Status.FAIL, true);
//                        }
//                        waitFor(SHORT_WAIT); // Wait for removal animation

                        // --- ACTION: Uncheck ---
                        chkBox.click();
                        waitFor(SHORT_WAIT); // Wait for removal animation

                        // 3. Validate Box is Unchecked
                        if (chkBox.isSelected())
                            addToReport("Row " + rowIndex + ": Checkbox Uncheck Failed.", Status.FAIL, true);

//                        // 4. Validate Chip Disappeared (Visual Sync)
//                        if (!isElementPresentBy(selectedPayeeChip(accName))) { // Check if element is GONE
//                            addToReport("Row " + rowIndex + ": Selection Chip for '" + accName + "' removed successfully.", Status.PASS, false);
//                        } else {
//                            addToReport("Row " + rowIndex + ": Selection Chip for '" + accName + "' is STILL visible after unchecking!", Status.FAIL, true);
//                        }

                    }
                }
            } else {
                addToReport("Row " + rowIndex + ": Checkbox missing.", Status.FAIL, true);
            }

            // --- 2. Validate Favorites (Add/Remove) ---
            By favIconLocator = By.xpath("//tbody//tr[" + rowIndex + "]/td[" + colFav + "]//img[contains(@src,'Star') or contains(@src,'Fav')]");

            if (isElementPresentBy(favIconLocator)) {
                if (performFullFunctionalTest) {
                    WebElement favIcon = driver.findElement(favIconLocator);

                    // Click Add
                    favIcon.click();
                    waitFor(SHORT_WAIT);

                    // Click Remove
                    favIcon.click();
                    waitFor(SHORT_WAIT);
                    addToReport("Row " + rowIndex + ": Favorite Icon Add/Remove clickable.", Status.PASS, false);
                }
            } else {
                addToReport("Row " + rowIndex + ": Favorite icon missing.", Status.FAIL, true);
            }

            // --- 3. Validate Account Number (Numeric Only) ---
            String accNum = getTextFromElement(tblObtainCellValue(rowIndex, colAccNum));
            if (accNum != null && !accNum.trim().matches("[0-9]+")) {
                addToReport("Row " + rowIndex + ": Invalid Account Number: " + accNum, Status.FAIL, true);
            }

            // --- 4. Validate Actions (Edit & Delete Buttons) ---
            By editBtn = By.xpath("//tbody//tr[" + rowIndex + "]/td[" + colActions + "]//button[img[contains(@src,'Pencil')]]");
            By delBtn = By.xpath("//tbody//tr[" + rowIndex + "]/td[" + colActions + "]//button[img[contains(@src,'Bin')]]");

            if (!isElementPresentBy(editBtn)) addToReport("Row " + rowIndex + ": Edit button missing.", Status.FAIL, true);
            if (!isElementPresentBy(delBtn)) addToReport("Row " + rowIndex + ": Delete button missing.", Status.FAIL, true);

        } catch (Exception e) {
            addToReport("Error validating Row " + rowIndex + ": " + e.getMessage(), Status.FAIL);
        }
    }
    // ==================================================================================
    //                           REUSABLE HELPER METHODS
    // ==================================================================================

    /**
     * Helper: Retrieves all text values from the Page Size dropdown.
     */
    private List<String> getAllPageSizeOptions() {
        List<String> optionsList = new ArrayList<>();
        if (isElementPresentBy(ddPageSize)) {
            // Open dropdown to ensure options are loaded
            clickOnElement(ddPageSize);
            List<WebElement> options = driver.findElements(ddOption);
            for (WebElement opt : options) {
                optionsList.add(opt.getText().trim());
            }
            // Close dropdown
            clickOnElement(ddPageSize);
        }
        return optionsList;
    }

    /**
     * Helper: Selects a specific page size and validates all pages for that size.
     */
    private void validatePaginationForSize(String sizeOption) {
        addToReport(">>> Preparing to test Page Size: " + sizeOption, Status.INFO);

        // 1. RESET: Go to Page 1 before changing settings
        navigateToFirstPage();

        // 2. Select the Option
        clickOnElement(ddPageSize);
        waitFor(SHORT_WAIT);
        clickOnElement(selectedDDOption(sizeOption));

        // 3. Wait for reload
        waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);
        waitForPageLoadCompleteJS();

        addToReport(">>> Page Size " + sizeOption + " selected. Starting validation...", Status.INFO);

        // 4. Parse Expected Size
        int expectedPageSize = Integer.parseInt(sizeOption.replaceAll("[^0-9]", ""));

        // 5. Pagination Loop
        int pageCounter = 1;
        boolean hasNextPage = true;

        do {
            addToReport("--- Validating Page " + pageCounter + " ---"+ "with Fav icons", Status.INFO);
            waitFor(SHORT_WAIT);

            // Check if Next button is available
            boolean isNextVisible = isNextButtonAvailable();

            // Validate Rows
            verifyCurrentPageRows(pageCounter, expectedPageSize, isNextVisible);

            // Validate Icons
            verifyRowIcons(pageCounter);

            // Handle Navigation
            if (isNextVisible) {
                clickOnElement(btnRightArrow);
                waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);
                pageCounter++;
            } else {
                hasNextPage = false;
            }

        } while (hasNextPage);
    }

    /**
     * Helper: Navigates back to the first page by clicking 'Previous' until it's no longer available.
     */
    private void navigateToFirstPage() {
        int maxClicks = 50; // Safety break to prevent infinite loops
        int clickCount = 0;

        // Loop while Left Arrow is present and visible
        while (isElementsPresentBy(btnLeftArrow) > 0
                && driver.findElement(btnLeftArrow).isDisplayed()
                && clickCount < maxClicks) {

            clickOnElement(btnLeftArrow);
            waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT); // Wait for reload
            waitFor(SHORT_WAIT); // Stability wait
            clickCount++;
        }

        if (clickCount > 0) {
            addToReport("Navigated back to Page 1.", Status.INFO);
        }
    }
    /**
     * Helper: Validates row counts based on whether it is a middle page or last page.
     */
    private void verifyCurrentPageRows(int pageNum, int expectedSize, boolean isNextVisible) {
        int actualRowCount = isElementsPresentBy(tblRows);

        if (isNextVisible) {
            // Middle Page -> Must be full
            if (actualRowCount == expectedSize) {
                addToReport("Page " + pageNum + ": Full page verified (" + actualRowCount + " rows).", Status.PASS, false);
            } else {
                addToReport("Page " + pageNum + ": Row count Mismatch! Expected " + expectedSize + ", Found " + actualRowCount, Status.FAIL, true);
            }
        } else {
            // Last Page -> 1 to Limit
            if (actualRowCount > 0 && actualRowCount <= expectedSize) {
                addToReport("Page " + pageNum + " (Last Page): Row count verified (" + actualRowCount + " rows).", Status.PASS, false);
            } else if (actualRowCount == 0) {
                addToReport("Page " + pageNum + ": No records found on last page!", Status.FAIL, true);
            } else {
                addToReport("Page " + pageNum + ": Row count exceeded limit! Limit: " + expectedSize + ", Found: " + actualRowCount, Status.FAIL, true);
            }
        }
    }

    /**
     * Helper: Iterates current rows and checks for the favorite icon.
     */
    private void verifyRowIcons(int pageNum) {
        List<WebElement> rows = driver.findElements(tblRows);
        for (int i = 0; i < rows.size(); i++) {
            List<WebElement> stars = rows.get(i).findElements(iconFavorite);
            if (stars.isEmpty() || !stars.get(0).isDisplayed()) {
                addToReport("Favorite Icon missing in Row " + (i + 1) + " on Page " + pageNum, Status.FAIL, true);
            }
        }
    }

    /**
     * Utility: Checks if the Next arrow is present and displayed.
     */
    private boolean isNextButtonAvailable() {
        return isElementsPresentBy(btnRightArrow) > 0
                && driver.findElement(btnRightArrow).isDisplayed();
    }
    /**
     * Validate the Page
     */

    public void validateSavedPayeePage() {
        addToReport("----------Start of validating the Contents of Saved Payees----------", Status.INFO, false);

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


        if (isElementPresentBy(btnNewPayee)){
            String btnNewPayeeText  = getTextFromElement(btnNewPayee);
            if (btnNewPayeeText.equals(ADD_NEW_PAYEE)){
                addToReport("Add New Payee Button Name Validated", Status.PASS, false);
            }else {
                addToReport("Add New Payee Button Name Mismatches, Expected - "+ADD_NEW_PAYEE+ " Actual - "+btnNewPayeeText,Status.FAIL,true);
            }
        }else {
            addToReport("Add New Payee Button is not available",Status.FAIL,true);
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
            addToReport("New Vishwa records button is visible",Status.PASS,false);
        }else{
            addToReport("New Vishwa records button is not visible",Status.FAIL,true);
        }


        if (isElementPresentBy(dynamicChangedButton(NEW_VISHWA))){
            String btnNewViswa  = getTextFromElement(dynamicChangedButton(NEW_VISHWA));
            if (btnNewViswa.equals(NEW_VISHWA)){
                addToReport("New Vishwa Button Name Validated", Status.PASS, false);
                clickOnElement(dynamicChangedButton(NEW_VISHWA));
                waitForPageLoadCompleteJS();
                addToReport("New Vishwa Button is Clicked",Status.INFO,false);
            }else {
                addToReport("New Vishwa Button Name Mismatches, Expected - "+NEW_VISHWA+ " Actual - "+btnNewViswa,Status.FAIL,true);
            }
        }else {
            addToReport("New Vishwa Button is not available",Status.FAIL,true);
        }

        addToReport("---------------------Validating Pagination by each option---------------",Status.INFO);
        validateDataAvailablity(TAB_ALL);


        if (isElementPresentBy(btnBack)){
                addToReport("Back Button Validated", Status.PASS, false);
                clickOnElement(btnBack);
        }else {
            addToReport("Add New Payee Button is not available",Status.FAIL,true);
        }

        addToReport("---------------------Validation of Pagination by each option Successful---------------",Status.INFO);

    }

    public void validateTransferTypeTabsAndContents() {
        validateDataAvailablityOfUniqueTabs(TAB_ALL,TAB_HEADERS_ALL);
        validateDataAvailablityOfUniqueTabs(TAB_SAMPATH_BANK,TAB_HEADERS_SAMPATH_BANK);
        validateDataAvailablityOfUniqueTabs(TAB_OTHER_BANK,TAB_HEADERS_OTHER_BANK);
        validateDataAvailablityOfUniqueTabs(TAB_OTHER_CARDS,TAB_HEADERS_OTHER_CARDS);

    }

    public String selectOneSavedPayeeRecord(int totalRowsSP ) {

        // Generate a unique random record index
        int selectedRecord = generateUniqueRandomNumber(totalRowsSP,usedIndexes);

        // Column Mapping
        List<String> headerList = Arrays.asList(TAB_HEADERS_ALL);
        int colAccNum = headerList.indexOf("Account Number") + 1;
        int colAccName = headerList.indexOf("Account Name") + 1;
        int colNickName = headerList.indexOf("Nick Name") + 1;
        int colBankName = headerList.indexOf("Bank Name") + 1;
        int colTransactionType = headerList.indexOf("Transaction Type") + 1;

        String AccNum = getTextFromElement(tblObtainCellValue(selectedRecord, colAccNum)).trim();
        String AccName  = getTextFromElement(tblObtainCellValue(selectedRecord, colAccName)).trim();
        String NickName  = getTextFromElement(tblObtainCellValue(selectedRecord, colNickName)).trim();
        String BankName = getTextFromElement(tblObtainCellValue(selectedRecord, colBankName)).trim();
        String TransactionType = getTextFromElement(tblObtainCellValue(selectedRecord, colTransactionType)).trim();

        if (!AccNum.isEmpty() && !AccName.isEmpty() && !NickName.isEmpty() && !BankName.isEmpty()) {
            Map<String, String> payeeInfo = new HashMap<>();
            payeeInfo.put("AccountNumber", AccNum);
            payeeInfo.put("AccountName", AccName);
            payeeInfo.put("NickName", NickName);
            payeeInfo.put("BankName", BankName);
            payeeInfo.put("TransactionType", TransactionType);

            allSelectedPayeeDetails.add(payeeInfo);



            addToReport("Successfully obtained nickname : " + payeeInfo.get("AccountNumber") + " - Nick Name : "+payeeInfo.get("AccountName")
                    +" - NickName : "+payeeInfo.get("NickName")+" - BankName : "+payeeInfo.get("BankName")+" - TransactionType : "+payeeInfo.get("TransactionType")+
                    ", Record Number - " + selectedRecord, Status.PASS, false);
        }else {
            if (AccNum.isEmpty()) {
                addToReport("Nickname is not obtained", Status.FAIL);
                throw new RuntimeException("Error - Nickname is not obtained from grid");
            }
            if (AccName.isEmpty()) {
                addToReport("Account Number is not obtained", Status.FAIL);
                throw new RuntimeException("Error - Account Number is not obtained from grid");
            }
            if (NickName.isEmpty()) {
                addToReport("NickName is not obtained", Status.FAIL);
                throw new RuntimeException("Error - NickName is not obtained from grid");
            }
            if (BankName.isEmpty()) {
                addToReport("Filed Value is not obtained", Status.FAIL);
                throw new RuntimeException("Error - Filed Value is not obtained from grid");
            }
        }


        // Select the record
        scrollToWebElement(selectSavedPayees(selectedRecord));
        clickOnElement(selectSavedPayees(selectedRecord));

        // Validate selected payee label & capture screenshot

        validateSelectedPayeeContainer(NickName);

        return NickName;
    }
    /**
     * Selects multiple saved Biller ensuring no duplicate selections in a single run.
     */
    public void SelectMultipleSavedBillers(int maxNumberOfPayee) {
        try {
            waitForElementPresence(tblSavedPayeesRows);

            int totalRowsSP = isElementsPresentBy(tblSavedPayeesRows);
            if (totalRowsSP <= 0) {
                addToReport("Unable to obtain row count", Status.FAIL, true);
                throw new RuntimeException("No Saved Payee Records were Found");
            }

            int totalRowsSPNotSelected = isElementsPresentBy(tblSBNotSelectedRows);
            if (totalRowsSPNotSelected <= 0) {
                addToReport("There are records which are already selected", Status.FAIL);
                throw new RuntimeException("Error - some records were already selected");
            }

            // Step 1: Select up to the allowed number of billers
            for (int i = 0; i < maxNumberOfPayee; i++) {
                String templateName = selectOneSavedPayeeRecord(totalRowsSP);
                addToReport("Selected Payee (" + (i + 1) + "): " + templateName, Status.PASS, false);
            }

            // Step 2: Attempt one extra selection beyond the limit
            addToReport("Attempting to select one extra biller beyond the allowed limit (" + maxNumberOfPayee + ")", Status.INFO, false);
            int selectedRecord = generateUniqueRandomNumber(totalRowsSP,usedIndexes);
            clickOnElement(selectSavedPayees(selectedRecord));

            // Step 3: Wait for toast error to appear
            waitForElementPresence(validateToastMessage(MAX_PAYEE_ERROR));
            if (isElementPresentBy(validateToastMessage(MAX_PAYEE_ERROR))) {
                String toastMsg = getTextFromElement(validateToastMessage(MAX_PAYEE_ERROR));
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







    public void checkTransferTypeTabsAndContents(String tabName) {
        try {
            addToReport("---------------------Validating Tab " + tabName + "---------------", Status.INFO);

            By selectedTab = miniTabHeader(tabName);
            if (isElementPresentBy(selectedTab)) {
                if (isElementClickable(selectedTab)) {
                    clickOnElement(selectedTab);
                    addToReport(tabName + " is clickable.", Status.PASS);

                    // Validate the contents under each tab
                    if (isElementPresentBy(lblPageSize) && isElementPresentBy(ddPageSize)) {

                        // 1. Get all option texts first to avoid StaleElementReferenceException
                        clickOnElement(ddPageSize);
                        waitFor(SHORT_WAIT);
                        List<WebElement> optionElements = driver.findElements(ddOption);

                        if (optionElements.isEmpty()) {
                            addToReport("No Page Size options found", Status.FAIL, true);
                            return;
                        }

                        List<String> optionValues = new ArrayList<>();
                        for (WebElement ele : optionElements) {
                            optionValues.add(ele.getText().trim());
                        }

                        // 2. Iterate through the stored text values
                        for (String optionText : optionValues) {
                            // Re-open dropdown for selection
                            clickOnElement(ddPageSize);
                            waitFor(SHORT_WAIT);

                            clickOnElement(selectedDDOption(optionText));
                            waitForPageLoadCompleteJS(); // Wait for table to reload
                            addToReport("Selected Page Size option: " + optionText + " under " + tabName + " tab", Status.PASS, false);

                            // --- START: Row Count Validation ---
                            try {
                                int expectedCount = Integer.parseInt(optionText); // Convert "10" to 10

                                // Locator based on the HTML snippet provided
                                By tableRowsLocator = By.xpath("//tbody[contains(@class,'bg-white') and contains(@class,'text-black')]/tr");

                                List<WebElement> actualRows = driver.findElements(tableRowsLocator);
                                int actualCount = actualRows.size();

                                if (actualCount == expectedCount) {
                                    addToReport("Row count matched. Expected: " + expectedCount + ", Actual: " + actualCount, Status.PASS);
                                } else {
                                    // Note: If total records < page size, this might strictly fail.
                                    // If that is acceptable behavior, keep as Status.FAIL.
                                    // Otherwise, use: if (actualCount <= expectedCount)
                                    addToReport("Row count mismatch. Expected: " + expectedCount + ", Actual: " + actualCount, Status.FAIL, true);
                                }
                            } catch (NumberFormatException nfe) {
                                addToReport("Could not parse page size option to integer: " + optionText, Status.WARNING);
                            }
                            // --- END: Row Count Validation ---
                        }

                    } else {
                        addToReport("Page size label or dropdown is not available under " + tabName + " tab", Status.FAIL, true);
                    }

                } else {
                    addToReport(tabName + " is not clickable.", Status.FAIL);
                }

            } else {
                addToReport(tabName + " is not Available.", Status.FAIL, true);
            }

        } catch (Exception e) {
            addToReport("Failed to loop and select Page Size options", Status.FAIL, true);
            throw new RuntimeException("Error in Page Size selection loop", e);
        }
    }



    /**
     * Returns a random unique row number between 1 and totalRows (inclusive)
     * @param totalRows set the maximum number Payees should be selected
     * @param usedIndexes set to track already selected indexes
     * that hasn't been used in the current run.
     */
    private int generateUniqueRandomNumber(int totalRows, Set<Integer> usedIndexes) {
        if (usedIndexes.size() >= totalRows) {
            throw new RuntimeException("No more unique rows available to select.");
        }

        int randomIndex;
        do {
            randomIndex = generateRandomNumber(totalRows);
        } while (usedIndexes.contains(randomIndex));

        usedIndexes.add(randomIndex);
        return randomIndex;
    }


    /**
     * Helper: Scrolls an element into view using JavaScript.
     * Useful for table rows that are at the bottom of the visible area.
     */
    public void scrollToElement(WebElement element) {
        try {
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'nearest'});",
                    element
            );
            waitFor(VERY_SHORT_WAIT); // Small pause for scroll animation to finish
        } catch (Exception e) {
            // Ignore scroll errors, element might already be visible
        }
    }


    /**
     * Captures screenshot and validates that the selected payee container
     * displays the expected Template Name.
     */
    private void validateSelectedPayeeContainer(String NickName) {
        waitForElementPresence(lblSelectedPayeeContainer);
        scrollPageToTop();

        if (isElementPresentBy(lblSelectedPayee(NickName))) {
            addToReport("Label for Selected NickName '" + NickName + "' is available on the Top",
                    Status.PASS, false);

            if (isElementPresentBy(lblSelectedPayeeCloseButton(NickName))){
                addToReport("Close Button is available for NickName "+NickName +".",Status.PASS,true);
            }else{
                addToReport("Close Button is not available for NickName "+NickName +".",Status.PASS,true);            }

        } else {
            addToReport("Label for Selected NickName '" + NickName + "' is NOT available",
                    Status.FAIL, true); // screenshot on failure as well
            throw new RuntimeException("Label for Selected NickName not found for: " + NickName);
        }
    }

    private static By lblSelectedPayeeCloseButton(String NickName){
        return By.xpath("//div[contains(@class,'bg-[#F5883C]') and contains(normalize-space(.),'"+NickName+"')]"+"/parent::div//img[contains(@src,'blackRoundCross') and contains(@class,'cursor-pointer')]");
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
