package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;
;
import static utils.Drivers.LONG_WAIT;
import static utils.Drivers.SHORT_WAIT;
import static utils.constants.MultiplePaymentsConstants.*;

public class MultiplePaymentsPage extends BasePage {
    public MultiplePaymentsPage(WebDriver driver) {
        super(driver);
    }
    Set<Integer> usedIndexes = new HashSet<>();
    static float totalAmount = 0;
    private List<Map<String, String>> allSelectedPayeeDetails = new ArrayList<>();

    public enum ElementType {
        button, label, span, div, p,text,numeric,number;
    }
    public enum PayUsing {
        Card, LKR ,OTHER;
    }


    private static  String selectedFromAccount ="";

    private static final By imgGreyLoader = By.xpath("//div[contains(@class,'bg-gray')]");
    private static final By btnRightArrow = By.xpath("//img[contains(@src,'FArrowRight')]");
    private static final By btnPaginationNumbers = By.xpath("//img[contains(@src,'FArrowRight')]/ancestor::div[contains(@class,'flex justify-end')]/div[1]/div");
    private static final By rdoSavedPayee = By.xpath("//input[@type='checkbox' and @id='savePayee-undefined']");

    private static final By menuSavedPayee_Billers = By.xpath("//a[contains(@class,'NavBar_navlink__CRz3E')  and normalize-space(text())='Payees & Billers']");
    private static final By menuItem_SavedPayees = By.xpath("//div[contains(@class,'SubMenu_item__z9l12')  and normalize-space(text())='Saved Payees']");

    private static final By btnNewPayee = By.xpath("//button[.//span[normalize-space(text())='Add New Payee']]");
    private static final By lblPageSize = By.xpath("//label[@for='pageSize' and normalize-space()='Payees per page']");
    private static final By ddPageSize = By.xpath("//select[@id='pageSize']");
    private static final By ddOption = By.xpath("//select[@id='pageSize']/option");
    private static final By tblRows = By.xpath("//tbody[contains(@class,'bg-white')]/tr");
    private static  By selectedDDOption(String optionText) {
        return  By.xpath("//select[@id='pageSize']/option[normalize-space()='" + optionText + "']");
    }

    private static final By iconFavorite = By.xpath(".//td[7]//img[contains(@src,'Star') or contains(@src,'FavStar')]");

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
        // Matches a <div> that has the 'cursor-pointer' class and the exact text
        return By.xpath("//div[contains(@class, 'cursor-pointer') and normalize-space(text())='" + tabName + "']");
    }

    private static By tblObtainCellValue(int Row, int Col) {
        return By.xpath("//tbody//tr[" + Row + "]/td[" + Col + "]");
    }
    private static By selectSavedBillers(int Row) {
    return By.xpath("(//tbody//tr)[" + Row + "]");
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
     * Navigate to saved Payee through nave bar
     */
    public void navigateToPayeeAndBillers() {
        waitForElementPresence(menuSavedPayee_Billers);
        clickOnElement(menuSavedPayee_Billers);
        waitForElementPresence(menuItem_SavedPayees);
        clickOnElement(menuItem_SavedPayees);
    }
    /**
     * Validate the data availability:
     * 1. Iterates pages using the 'Next' arrow.
     * 2. Checks row count matches the 10-row limit (unless last page).
     * 3. Validates Favorite Icon presence in every row.
     */
    public void validateDataAvailablity(String primaryTab) {
        try {
            // 1. Select appropriate header
            selectHeaderTab(primaryTab);
            waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);

            int pageCounter = 1;
            boolean hasNextPage = true;
            // Assuming default page size is 10 based on your HTML select option
            int expectedPageSize = 10;

            try {
                if (isElementPresentBy(ddPageSize)) {
                    // Using your BasePage method 'getSelectedOptionText' with "FIRST_SELECTED"
                    List<String> selectedOptions = getSelectedOptionText(ddPageSize, "FIRST_SELECTED");

                    if (selectedOptions != null && !selectedOptions.isEmpty()) {
                        String selectedText = selectedOptions.get(0);
                        expectedPageSize = Integer.parseInt(selectedText.replaceAll("[^0-9]", ""));
                        addToReport("Validating based on selected Page Size: " + expectedPageSize, Status.INFO);
                    }
                }
            } catch (Exception e) {
                addToReport("Could not retrieve Page Size, defaulting to 10. Error: " + e.getMessage(), Status.WARNING);
            }


            do {
                addToReport("----- Validating Page " + pageCounter + " -----", Status.INFO);
                waitFor(SHORT_WAIT); // Slight pause for table render

                // Use BasePage method to get count of rows
                int actualRowCount = isElementsPresentBy(tblRows);

                // Check if Next Arrow is present and displayed using BasePage method
                // We verify if the specific arrow container is visible
                boolean isNextVisible = isElementsPresentBy(btnRightArrow) > 0
                        && driver.findElement(btnRightArrow).isDisplayed();

                // --- LOGIC: Validate Row Count ---

                if (isNextVisible) {
                    // CASE 1: NOT the last page (Next arrow is clickable)
                    // The page MUST be full.
                    if (actualRowCount == expectedPageSize) {
                        addToReport("Page " + pageCounter + ": Row count verified (Full Page: " + actualRowCount + ").", Status.PASS, false);
                    } else {
                        // Fail if we have next pages but the current page isn't full (e.g., only 9 rows)
                        addToReport("Page " + pageCounter + ": Row count Mismatch! Expected " + expectedPageSize + " (Full Page), Found " + actualRowCount, Status.FAIL, true);
                    }
                } else {
                    // CASE 2: Last page (Next arrow is NOT clickable/visible)
                    // The rows can be anything from 1 to 10.
                    if (actualRowCount > 0 && actualRowCount <= expectedPageSize) {
                        addToReport("Page " + pageCounter + " (Last Page): Row count verified (" + actualRowCount + " rows).", Status.PASS, false);
                    } else if (actualRowCount == 0) {
                        addToReport("Page " + pageCounter + ": No records found on the last page!", Status.FAIL, true);
                    } else {
                        addToReport("Page " + pageCounter + ": Row count exceeded limit! Found " + actualRowCount, Status.FAIL, true);
                    }
                }

                // --- LOGIC: Validate Favorite Icons ---
                List<WebElement> rows = driver.findElements(tblRows);
                for (int i = 0; i < rows.size(); i++) {
                    WebElement currentRow = rows.get(i);
                    // Find the star icon strictly inside this specific row
                    List<WebElement> stars = currentRow.findElements(iconFavorite);

                    if (!stars.isEmpty() && stars.get(0).isDisplayed()) {
                        // Icon present
                    } else {
                        addToReport("Favorite Icon missing in Row " + (i + 1) + " on Page " + pageCounter, Status.FAIL, true);
                    }
                }
                addToReport("Favorite Icons verified for all " + actualRowCount + " rows on Page " + pageCounter, Status.PASS, false);

                // --- NAVIGATION ---
                if (isNextVisible) {
                    clickOnElement(btnRightArrow);
                    // Use BasePage method to wait for loader
                    waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);
                    pageCounter++;
                } else {
                    // If button not visible, we are done
                    hasNextPage = false;
                }

            } while (hasNextPage);

            addToReport("Total Pages Validated: " + pageCounter, Status.PASS);

        } catch (Exception e) {
            addToReport("Error verifying Saved Payee data availability", Status.FAIL);
            throw new RuntimeException("Failed to validate Saved Payee data availability", e);
        }
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

        addToReport("---------------------Validating Types of Transfer by Tabs---------------",Status.INFO);

        validateDataAvailablity(TAB_ALL);



        addToReport("---------------------Validation of Saved Payee contents Successful---------------",Status.INFO);

    }




    private void checkTransferTypeTabsAndContents(String tabName) {
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

}
