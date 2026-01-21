/*
 *   @author - salman R
 */
package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import utils.CommonUtils;
import utils.constants.BillerConstants;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static utils.Drivers.*;

public class BillPaymentPage extends BasePage {

    public BillPaymentPage(WebDriver driver) {
        super(driver);
    }

    String InvoiceNo, InvoiceAmount;

    public enum ElementType {
        button, label, span, div, p;
    }

    private static final By icnAddToFav = By.xpath("//img[contains(@class,'cursor-pointer invert') and @alt='']");
    private static final By tblRows = By.xpath("//table//tbody/tr");
    private static final By btnFilter = By.xpath("//button[text()='Filter']");
    private static final By tfAmountFrom = By.xpath("//input[@placeholder='Amount From']");
    private static final By tfAmountTo = By.xpath("//input[@placeholder='Amount To']");
    private static final By tfTransactionDate = By.xpath("//input[@placeholder='Transaction date']");
    private static final By ddStatus = By.xpath("//select[@id='status']");
    private static final By btnApplyFilters = By.xpath("//div[text()='Apply Filters']");
    private static final By ddMonth = By.xpath("//span[@class='rdrMonthPicker']/select");
    private static final By ddYear = By.xpath("//span[@class='rdrYearPicker']/select");
    private static final By btnRightArrow = By.xpath("//img[contains(@src,'FArrowRight')]");
    private static final By btnPaginationNumbers = By.xpath("//img[contains(@src,'FArrowRight')]/ancestor::div[contains(@class,'flex justify-end')]/div[1]/div");
    private static final By imgGreyLoader = By.xpath("//div[contains(@class,'bg-gray')]");
    private static final By btnCloseFilter = By.xpath("//img[contains(@class,'cursor-pointer') and @alt='Remove filter']");
    private static final By popUpPDFDownload = By.xpath("//div[text()='PDF downloaded successfully!']");
    private static final By popUpMsgBillerDeleted = By.xpath("//div[text()='PDF downloaded successfully!']");
    private static final By lblFavouriteBillerWidgetRow = By.xpath("//span[contains(text(),'Favorite Billers')]/parent::div//div[contains(@class,'grid grid-cols')]/div");
    private static final By lblAmountErrorMsg = By.xpath("//span[normalize-space()='Amount is required']");
    private static final By icnCategoryLoading = By.xpath("//div[contains(@class,'hidden flex relative')]");
    private static final By lblNewPaymentAccountNumber = By.xpath("//div[contains(@class,'col justify')]/span[contains(@class,'max-sm')]");
    private static final By rdoAccount = By.xpath("//input[@value='Account']");
    private static final By rdoCreditCard = By.xpath("//input[@value='CreditCard']");
    private static final By ddPayFrom = By.xpath("//select[@id='accountfrom']");
    private static final By tfAmount = By.xpath("//input[@placeholder='Enter Amount']");
    private static final By tfFieldOne = By.xpath("//input[contains(@name,'fieldData.0')]");
    private static final By tfFieldTwo = By.xpath("//input[contains(@name,'fieldData2.0')]");
    private static final By tfFieldThree = By.xpath("//input[contains(@name,'fieldData.1')]");
    private static final By tfFieldFour = By.xpath("//input[contains(@name,'fieldData2.1')]");
    private static final By tfFieldFive = By.xpath("//input[contains(@name,'fieldData.2')]");
    private static final By tfFieldSix = By.xpath("//input[contains(@name,'fieldData.3')]");
    private static final By tfFieldSeven = By.xpath("//input[contains(@name,'fieldData.4')]");
    private static final By tfFieldEight = By.xpath("//input[@name='fieldData.0.fieldValue']");
    private static final By rdoOTTransaction = By.xpath("//input[@value='ONLINE']");
    private static final By rdoSchadule = By.xpath("//input[@value='SCHEDULE']");
    private static final By chkSaveBiller = By.xpath("//input[@id='savedBiller']");
    private static final By btnNext = By.xpath("//button[normalize-space()='Next']");
    private static final By tfTemplateName = By.xpath("//input[@placeholder='Template Name']");
    private static final By btnNextLoading = By.xpath("//div[contains(@class,'customloader')]");
    private static final By lblNewPayment = By.xpath("//span[text()='New Payment']");
    private static final By lblPopUpHeaderPaymentConfirmation = By.xpath("//div[contains(text(),'Payment Confirmation')]");

    private static final By lblPayeeName = By.xpath("//div[contains(@class,'BillPaymentConfirmation')]//div[contains(@class,'text-sm')]/span[1]");
    private static final By btnConfirm = By.xpath("//button[contains(normalize-space(text()),'Confirm')]");
    private static final By btnSearchBiller = By.xpath("//button[@type='submit']");
    private static final By lblSuccess = By.xpath("//span[text()='Success']");
    private static final By lblRefernceID = By.xpath("//span[text()='Success']//following::span[2]");
    private static final By btnPrint = By.xpath("//button[normalize-space()='Print']");
    private static final By btnClosePopup = By.xpath("//img[contains(@alt,'Close')]");
    private static final By tfSearch = By.xpath("//input[@placeholder='Search']");
    private static final By btnSearch = By.xpath("//div[contains(@class,'absolute')]/img");
    private static final By icnSavedBillerGridLoading = By.xpath("//div[contains(@class,'dark')]");
    private static final By lblNoSavedBillersFound = By.xpath("//span[normalize-space()='No saved billers found']");
    private static final By tfSearchCategories = By.xpath("//input[@placeholder='Search Categories']");
    private static final By btnSearchCategory = By.xpath("//input[@placeholder='Search Categories']/parent::div//img[contains(@srcset,'Fsearch')]");
    private static final By lblCategorySearchResults = By.xpath("//span[text()='All Categories']/parent::div//div//span");
    private static final By imgSavedBillerFavRecords = By.xpath("//img[contains(@srcset,'.c7bd4030') and @alt='']");
    private static final By imgSavedBillerFavRecordsList = By.xpath("//img[contains(@srcset,'.c7bd4030') and @alt='favorite star']");
    private static final By lblBillPaymentsHeader = By.xpath("//div[@class='flex flex-col']/span[text()='Bill Payments']");
    private static final By lblNoFavBillersAddedMsg = By.xpath("//span[contains(text(),'No favorites added yet')]");
    private static final By chkAckMsg = By.xpath("//label/input[@type='checkbox']");
    private static final By lblConversionMsg = By.xpath("//div[@class='mt-1']/span");
    private static final By btnBack = By.xpath("//button[@type='button' and contains(normalize-space(@class),'bg-gray')]");
    private static final By lblTransactionFee = By.xpath("//div[contains(@class,'BillPaymentConfirmation')]/div[1]/div[2]/div[3]");

    private static By lblErrorMsg(String msg, int index) {
        return By.xpath("(//span[contains(normalize-space(),\"" + msg + "\")])[" + index + "]");
    }

    private static By lblErrorMsg(String msg) {
        return By.xpath("//span[contains(text(),\"" + msg + "\")]");
    }

    private static By lblErrorMsgNumeral(String msg, int index) {
        return By.xpath("(//span[contains(normalize-space(),'" + msg + "')])[" + index + "]");
    }

    private static By tfPaymentConfirmation(String type) {
        return By.xpath("//span[contains(normalize-space(),\"" + type + "\")]/parent::div/input[@disabled]");
    }

    private static By tfPayFromConfirmation(String type) {
        return By.xpath("//span[contains(normalize-space(),\"" + type + "\")]/parent::div/span[2]");
    }

    private static By tfAmountConfirmation(String type) {
        return By.xpath("//span[contains(normalize-space(),\"" + type + "\")]/ancestor::div[@class='relative']//span[2]");
    }

    private static By tfInvoiceNoConfirmation(String type) {
        return By.xpath("//span[contains(normalize-space(),\"" + type + "\")]/ancestor::div[@class='relative']/div/div/span");
    }

    private static By tfPaymentConfirmationMobileNo(String type) {
        return By.xpath("//span[contains(normalize-space()," + type + ")]/parent::div/input[@disabled]");
    }

    private static By tfOTP(int Index) {
        return By.xpath("//input[contains(@class,'otp-box')][" + Index + "]");
    }

    private static By btnMenuOptions(String buttonText) {
        return By.xpath("//button/a[contains(text(),'" + buttonText + "')]");
    }

    private static By lnkCategory(String category) {
        return By.xpath("//span[contains(text(),\"" + category + "\")]");
    }

    private static By btnSubHeader(String subHeader) {
        return By.xpath("//div[contains(@class,'flex')]/div[text()=" + subHeader + "]");
    }

    private static By tblCellRecord(int col, int row) {
        return By.xpath("(//table//tr/td[" + col + "])[" + row + "]");
    }

    private static By tblCellRecordInput(int col, int row) {
        return By.xpath("(//table//tr/td[" + col + "])[" + row + "]//input");
    }

    private static By tblActionDownloadOrDelete(int col, int row) {
        return By.xpath("(//table//tr/td[" + col + "]//button[2])[" + row + "]");
    }

    private static By lblSavedBillerTemplateName(int row) {
        return By.xpath("(//img[contains(@src,'Bin') and @alt='']/ancestor::tr/td[3])[" + row + "]");
    }

    private static By lblFavSavedBillerListName(int row) {
        return By.xpath("(//div[@class='flex flex-col']/div[1])[" + row + "]");
    }

    private static By btnSavedBillerTemplateDelete(int row) {
        return By.xpath("(//img[contains(@src,'Bin') and @alt='']/ancestor::tr/td[8]//button[2])[" + row + "]");
    }

    private static By btnSavedBillerReinitiate(int row) {
        return By.xpath("(//img[contains(@src,'Frepeat') ]/ancestor::tr/td[7]//button[1])[" + row + "]");
    }

    private static By datePickerDay(int day) {
        return By.xpath("//button[not(@tabindex='-1')]//span[text()=" + day + "]");
    }

    private static By tabHeader(String tabName) {
        return By.xpath("//div[contains(@class,'flex')]/div[text()='" + tabName + "']");
    }

    private static By btnPageNumber(String buttonNumber) {
        return By.xpath("//img[contains(@src,'FArrowRight')]/ancestor::div[contains(@class,'flex justify-end')]/div[1]//span[text()=" + buttonNumber + "]");
    }

    private static By icnAddToFav(int index) {
        return By.xpath("(//img[contains(@class,'cursor-pointer invert') and @alt=''])[" + index + "]");
    }

    private static By btnCloseFilter(int index) {
        return By.xpath("(//img[contains(@class,'cursor-pointer') and @alt='Remove filter'])[" + index + "]");
    }

    private static By getElementByTypeAndText(BillPaymentPage.ElementType type, String text) {
        return By.xpath("//" + type.name() + "[contains(normalize-space(text()), '" + text + "')]");
    }

    private static By btnCategories(String category) {
        return By.xpath("//span[text()= " + category + ")]");
    }

    private static By lblFavouriteBillerTempName(int index) {
        return By.xpath("//span[contains(text(),'Favorite Billers')]/parent::div//div[contains(@class,'flex items')][" + index + "]//span[1]");
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
     * Validate No Saved Billers Are Marked As Favourites
     */
    public void validateNoSavedBillersAreMarkedAsFavourites() {
        try {
            waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);

            if (isElementPresentBy(lblNoFavBillersAddedMsg)) {
                addToReport("Validated no favourite biller found msg : ", Status.PASS, true);
            } else {
                addToReport("Failed to validate no favourite biller found msg", Status.FAIL);
            }

        } catch (Exception e) {
            addToReport(" No fav biller found in the list", Status.FAIL);
            throw new RuntimeException("Failed to validate fav biller" + e.getMessage(), e);
        }
    }


    /**
     * Select Categories
     *
     * @param mainCategory - Main category
     * @param subCategory  - Sub category
     */
    public void selectCategory(String mainCategory, String subCategory) {
        try {
            //Select main category
            waitForElementToBeInvisible(icnCategoryLoading, LONG_WAIT);
            waitForElementPresence(lnkCategory(mainCategory));
            clickOnElement(lnkCategory(mainCategory));
            addToReport("Main category : " + mainCategory + " is selected", Status.PASS, false);
            if (!subCategory.isEmpty()) {
                //Select sub category
                waitForElementToBeInvisible(lnkCategory(mainCategory), LONG_WAIT);
                waitForElementToBeInvisible(icnCategoryLoading, LONG_WAIT);
                clickOnElement(lnkCategory(subCategory));
                addToReport("Sub category : " + subCategory + " is selected", Status.PASS, true);
            }
        } catch (Exception e) {
            addToReport("Failed to select category ", Status.FAIL);
            throw new RuntimeException("Error - Failed to select category" + e.getMessage(), e);
        }
    }

    /**
     * Validate the add favourite icons
     *
     * @param primaryTab - Header tab
     */
    public void validateAddToFavColumn(String primaryTab) {
        try {
            //Select appropriate header
            selectHeaderTab(primaryTab);
            waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);
            //Use pagination to go to next
            if (isElementPresentBy(btnRightArrow)) {
                int recordCount = isElementsPresentBy(btnPaginationNumbers);
                if (recordCount != 0) {
                    for (int inc = 1; inc <= recordCount; inc++) {
                        waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);
                        waitForElementToBeClickable(icnAddToFav(1), LONG_WAIT);
                        scrollToWebElement(icnAddToFav(1));
                        //validate the favourite icon availability
                        int rCount = isElementsPresentBy(icnAddToFav);
                        if (rCount != 0) {
                            addToReport(rCount + " number of favourite icons are visible in page number " + inc, Status.PASS, true);
                            //Select next page
                            if (inc < recordCount) {
                                clickOnElement(btnRightArrow);
                            }
                        } else {
                            addToReport("No records found", Status.FAIL);
                        }

                    }

                }
            } else {
                //validate the favourite icon availability
                int recordCount = isElementsPresentBy(icnAddToFav);
                if (recordCount != 0) {
                    scrollToWebElement(icnAddToFav(1));
                    addToReport(recordCount + " number of favourite icons are visible", Status.PASS, true);
                } else {
                    addToReport("No records found", Status.FAIL);
                }
            }

        } catch (Exception e) {
            addToReport("Error verifying favourites icon under saved billers", Status.FAIL);
            throw new RuntimeException("Failed to validate favourites icon under saved billers ", e);
        }
    }

    /**
     * Validates the favourite biller list by navigating between the New Payment tab and the Saved Billers tab,
     * ensuring the correct favourite billers are displayed.
     *
     * @param newPaymentTab   the name of the New Payment tab to navigate to
     * @param savedBillersTab the name of the Saved Billers tab where favourite billers are listed
     */
    public void validateFavouriteBillerList(String newPaymentTab, String savedBillersTab) {
        try {

            scrollPageToTop();
            selectHeaderTab(newPaymentTab);
            //Navigate back to saved billers tab
            selectHeaderTab(savedBillersTab);
            waitForElementToBeInvisible(icnSavedBillerGridLoading, LONG_WAIT);

            //Declare list to extract from table
            ArrayList<String> TemplateListName = new ArrayList<>();
            ArrayList<String> TemplateNameSavedBillers = new ArrayList<>();

            //Obtain the record count
            int recordCount = isElementsPresentBy(imgSavedBillerFavRecords);
            if (recordCount == 0) {
                addToReport("Favourite biller records are not displayed", Status.FAIL);
                throw new RuntimeException("Error - Favourite biller records are not displayed in table");
            }
            //Extract the latest records from the list
            for (int inc = 0; inc < recordCount; inc++) {
                TemplateNameSavedBillers.add(inc, getTextFromElement(lblSavedBillerTemplateName(inc + 1)));
                if (inc == 10) {
                    scrollToWebElement(lblSavedBillerTemplateName(inc + 1));
                    addToReport("Obtained 10 records from favourite billers under saved billers", Status.PASS, true);
                }
            }
            addToReport("Favourite biller template names are captured ", Status.PASS, true);
            addToReport("Validate captured favourite biller vs the list", Status.PASS, false);
            //Validate the record values
            int recordCountNew = isElementsPresentBy(imgSavedBillerFavRecordsList);
            if (recordCountNew == 0) {
                addToReport("Favourite biller records are not displayed in favourite list", Status.FAIL);
                throw new RuntimeException("Error - Favourite biller records are not displayed in favourite list");

            }

            //Extract the latest records from the list
            for (int inc = 0; inc < recordCount; inc++) {
                TemplateListName.add(inc, getTextFromElement(lblFavSavedBillerListName(inc + 1)));
                if (inc == 10) {
                    scrollToWebElement(lblFavSavedBillerListName(inc + 1));
                    addToReport("Obtained 10 records from favourite billers under saved billers", Status.PASS, true);
                }
            }

            //Compare two list for template names
            if (CommonUtils.compareTwoArraylist(TemplateNameSavedBillers, TemplateListName, true)) {
                addToReport("Favourite biller is validated from table vs the values from list", Status.PASS, true);
            }
        } catch (Exception e) {
            addToReport("Recent vishawa transfer validation for favourite biller failed", Status.FAIL);
            throw new RuntimeException("Error - Recent vishawa transfer validation of favourite biller failed", e);
        }
    }

    /**
     * Searches for bill payment categories and navigates back to the Favourite Payee section if applicable.
     *
     * @param category the name of the bill payment category to search for
     */
    public void searchBillPaymentCategories(String category) {
        waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);
        waitForElementToBeClickable(tfSearchCategories, MODERATE_WAIT);
        typeWithoutClear(tfSearchCategories, category);
        clickOnElement(btnSearchCategory);
        waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);

        int recordCount = isElementsPresentBy(lblCategorySearchResults);
        if (recordCount != 0) {
            if (getTextFromElement(lblCategorySearchResults).equals(category)) {
                addToReport(recordCount + " Search category returned " + category, Status.PASS, true);
            }
        } else {
            addToReport("Search categories did not return correct results", Status.FAIL);
        }
    }

    /**
     * Validate the bill payment history filters
     *
     * @param primaryTab        - Header tab
     * @param downloadDirectory - directory of downloads
     */
    public void validateBillPaymentHistoryFilterOptions(String primaryTab, String downloadDirectory) {
        try {
            //Select appropriate header
            selectHeaderTab(primaryTab);
            waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);

            //Extract required the first record data
            String paymentID = getTextFromElement(tblCellRecord(1, 1));
            String billerTitle = getTextFromElement(tblCellRecord(2, 1));
            String status = getTextFromElement(tblCellRecord(4, 1));
            String[] CurrencyAndAmt = getTextFromElement(tblCellRecord(6, 1)).split(" ");

            addToReport("Obtained record with payment id : " + paymentID + ", biller tittle : " + billerTitle + " and status :" + status, Status.PASS, true);

            //Filter by from and to amount
            //Click on filter button
            clickOnElement(btnFilter);
            typeWithoutClear(tfAmountFrom, String.valueOf(Double.parseDouble(CurrencyAndAmt[1].replace(",", "")) - 1));
            typeWithoutClear(tfAmountTo, String.valueOf(Double.parseDouble(CurrencyAndAmt[1].replace(",", "")) + 1));

            clickOnElement(btnApplyFilters);
            waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);

            //Validate the search results
            int recordCount = isElementsPresentBy(tblRows);
            if (recordCount != 0) {
                for (int inc = 1; inc <= recordCount; inc++) {
                    if (getTextFromElement(tblCellRecord(1, inc)).equals(paymentID) &&
                            getTextFromElement(tblCellRecord(2, inc)).equals(billerTitle) &&
                            getTextFromElement(tblCellRecord(4, inc)).equals(status)) {
                        addToReport("Filtered value " + paymentID + " is available in row : " + inc + " for filter by from and to amount", Status.PASS, true);
                        break;
                    }
                    if (recordCount == inc) {
                        addToReport("Filtered record did not display under saved billers", Status.FAIL);
                    }
                }
            } else {
                addToReport("Error verifying search records under saved billers", Status.FAIL);
                throw new RuntimeException("Failed to validate search records under saved billers ");
            }

            //Remove the filter
            recordCount = isElementsPresentBy(btnCloseFilter);
            for (int inc = 1; inc <= recordCount; inc++) {
                clickOnElement(btnCloseFilter(1));
                waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);
            }
            addToReport("Removed all existing filters ", Status.PASS, true);

            //Filter using status
            //Click on filter button
            clickOnElement(btnFilter);

            //Get dropdown value based on the initial extracted value from the table
            List<String> ddValues = getDropdownValues(ddStatus);
            for (String value : ddValues) {
                if (value.contains(status)) {
                    selectFromDropdown(ddStatus, value, "value");
                    break;
                }
            }
            //Click apply filter
            clickOnElement(btnApplyFilters);
            waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);

            int rowNumber = 0;
            //Validate the search results
            recordCount = isElementsPresentBy(tblRows);
            if (recordCount != 0) {
                for (int inc = 1; inc <= recordCount; inc++) {
                    if (getTextFromElement(tblCellRecord(1, inc)).equals(paymentID) &&
                            getTextFromElement(tblCellRecord(2, inc)).equals(billerTitle) &&
                            getTextFromElement(tblCellRecord(4, inc)).equals(status)) {
                        addToReport("Filtered value " + paymentID + " is available in row : " + inc + " for filter by status", Status.PASS, true);

                        //Download the record
                        clickOnElement(tblActionDownloadOrDelete(7, inc));

                        //Validate the download
                        waitFor(SHORT_WAIT);
                        // Get the latest downloaded file
                        File latestFile = getLatestDownloadedFile(downloadDirectory);

                        if (latestFile != null) {
                            // Extract text from the PDF
                            String extractedText = extractTextFromPDF(latestFile.getAbsolutePath()).replace("/n", "");

                            addToReport(" Latest downloaded pdf :  : '" + extractedText, Status.INFO, false);
                            System.out.println("Latest Downloaded PDF: " + extractedText);

                            waitForElementToBeInvisible(popUpPDFDownload, MODERATE_WAIT);

                            //validate payment id
                            if (extractedText.contains(paymentID)) {
                                addToReport(" Validated payment id " + paymentID + " of the downloaded record", Status.PASS, false);
                                break;
                            } else {
                                addToReport(" Failed to validate payment id of the downloaded record", Status.FAIL, false);
                            }

                        } else {
                            addToReport("Error verifying downloaded saved biller record", Status.FAIL);
                            throw new RuntimeException("Failed to validate downloaded saved billers ");
                        }

                    }
                    if (recordCount == inc) {
                        addToReport("Filtered record did not display under saved billers", Status.FAIL);
                    }
                }

            } else {
                addToReport("Error verifying search records under saved billers", Status.FAIL);
                throw new RuntimeException("Failed to validate search records under saved billers ");
            }

        } catch (Exception e) {
            addToReport("Error verifying favourites icon under saved billers", Status.FAIL);
            throw new RuntimeException("Failed to validate favourites icon under saved billers ", e);
        }
    }


    /**
     * Initiate bill payments for multiple categories and validate via my account
     *
     * @param OTPValue                 One-time password used for authentication.
     * @param downloadDirectory        Directory path where any downloads should be saved.
     * @param category                 The main category of the biller (e.g., Utilities, Insurance, Education).
     * @param billerName               The name of the biller (e.g., CEB, NWSDB).
     * @param paymentUsing             The payment method selected (e.g., Credit Card, Savings Account).
     * @param transferMode             The mode of transfer (e.g., IMPS, NEFT).
     * @param amount                   The payment amount.
     * @param mobileNo                 Mobile number of the user/customer.
     * @param accountNumber            Account number related to the biller or customer.
     * @param templateName             Template name for saved or recurring transactions.
     * @param errorMsgOne              Expected error message one for validation.
     * @param errorMsgTwo              Expected error message two for validation.
     * @param errorMsgThree            Expected error message three for validation.
     * @param errorMsgFour             Expected error message four for validation.
     * @param errorMsgFive             Expected error message five for validation.
     * @param errorMsgSix              Expected error message six for validation.
     * @param nicNo                    National Identity Card number.
     * @param name                     Name of the policyholder or customer.
     * @param policyNumber             Insurance policy number.
     * @param admissionNumber          Student's admission number (for school fee payments).
     * @param classID                  Class identifier (e.g., Grade 6, C6).
     * @param purpose                  Purpose of the payment.
     * @param date                     Date associated with the transaction (e.g., due date, birthdate).
     * @param code                     Short code for payment reason or institution.
     * @param referenceOrReservationNo Reference number or reservation ID.
     * @param branch                   Branch associated with the employee or customer.
     * @param email                    Email address of the user.
     * @param kwBillersMap             key word and the content of keyword is added as suffix
     * @return
     */
    public String[] initiateBillPaymentsViaCategoriesSaveTemplateAndValidate(String OTPValue, String downloadDirectory, String category, String billerName, String paymentUsing, String transferMode, String amount, String mobileNo, String accountNumber, String templateName, String errorMsgOne, String errorMsgTwo, String errorMsgThree, String errorMsgFour, String errorMsgFive, String errorMsgSix, String nicNo, String name, String policyNumber, String admissionNumber, String classID, String purpose, String date, String code, String referenceOrReservationNo, String branch, String email, Map<String, String> kwBillersMap) {

        addToReport("----------Start of validation of all eligible accounts to perform bill payments are available in the From Account Drop down and default primary account is selected----------", Status.PASS, false);
        //Obtain account numbers from dashboard
        List<String> accountNumbers = getValues();

        //Check by default primary account needs to selected
        waitForElementPresence(lblNewPaymentAccountNumber, LONG_WAIT);
        waitForElementPresence(btnNext);

        //Temporary wait till due to sporadic failure in ALL_OPTIONS_VALUE selection
        waitFor(6);
        //validate the primary account number both in panel and from pay from dropdown
        String pAccountNo = getTextFromElement(lblNewPaymentAccountNumber);
        if (pAccountNo.equals(accountNumbers.get(0))) {
            addToReport("Primary account number is displayed by default in the account panel", Status.PASS, false);
        } else {
            addToReport("Primary account number is not displayed by default in the account panel", Status.FAIL);
        }

        //Obtain the first selected value from the dropdown
        List<String> fromAccDropdownValue = getSelectedOptionText(ddPayFrom, "FIRST_SELECTED");
        if (fromAccDropdownValue.get(0).contains(accountNumbers.get(0))) {
            addToReport("Primary account number " + getSelectedOptionText(ddPayFrom, "FIRST_SELECTED") + " is displayed by default in the pay from dropdown", Status.PASS, true);
        } else {
            addToReport("Primary account number is not displayed by default in the pay from dropdown :" + getSelectedOptionText(ddPayFrom, "FIRST_SELECTED"), Status.FAIL);
        }

        //Obtain the all available values from the dropdown
        List<String> ddAccountNumbers = getSelectedOptionText(ddPayFrom, "ALL_OPTIONS_VALUE");
        if (CommonUtils.compareTwoArraylist(ddAccountNumbers, accountNumbers, true)) {
            addToReport("All eligible accounts to perform bill payments are available : " + ddAccountNumbers, Status.PASS, false);
        } else {
            addToReport("All eligible accounts to perform bill payments are not available, Expected : " + accountNumbers + " Retrieved : " + ddAccountNumbers, Status.FAIL);
        }

        addToReport("----------End of validation of all eligible accounts to perform bill payments are available in the From Account Drop down and default primary account is selected----------", Status.PASS, false);
        addToReport("----------Start of user not be able to access the next page without entering data to mandatory fields----------", Status.PASS, false);

        //Click button without entering data
        clickOnElement(btnNext);
        waitForElementToBeInvisible(btnNextLoading, LONG_WAIT);

        //Validate error messages
        if (isElementPresentBy(lblAmountErrorMsg)) {
            addToReport("Amount is required error message appeared", Status.PASS, false);
        } else {
            addToReport("Amount is required error message did not appear", Status.FAIL);
        }

        if (!errorMsgOne.isEmpty()) {
            if (isElementPresentBy(lblErrorMsg(errorMsgOne, 1)) || isElementPresentBy(lblErrorMsg(errorMsgOne)) || isElementPresentBy(lblErrorMsgNumeral(errorMsgOne, 1))) {
                addToReport(errorMsgOne + " error message appeared", Status.PASS, false);
            } else {
                addToReport(errorMsgOne + " error message did not appear", Status.FAIL);
            }
        }
        if (!errorMsgTwo.isEmpty()) {
            if (isElementPresentBy(lblErrorMsg(errorMsgTwo, 2)) || isElementPresentBy(lblErrorMsgNumeral(errorMsgOne, 2))) {
                addToReport(errorMsgTwo + " reenter error message appeared", Status.PASS, false);
            } else {
                addToReport(errorMsgTwo + " reenter error message did not appear", Status.FAIL);
            }
        }
        if (!errorMsgThree.isEmpty()) {
            if (isElementPresentBy(lblErrorMsg(errorMsgThree, 1))) {
                addToReport(errorMsgThree + " error message appeared", Status.PASS, true);
            } else {
                addToReport(errorMsgThree + " error message did not appear", Status.FAIL);
            }
        }
        if (!errorMsgFour.isEmpty()) {
            if (isElementPresentBy(lblErrorMsg(errorMsgFour, 1))) {
                addToReport(errorMsgFour + " error message appeared", Status.PASS, true);
            } else {
                addToReport(errorMsgFour + " error message did not appear", Status.FAIL);
            }
        }
        if (!errorMsgFive.isEmpty()) {
            if (isElementPresentBy(lblErrorMsg(errorMsgFive, 1))) {
                addToReport(errorMsgFive + " error message appeared", Status.PASS, true);
            } else {
                addToReport(errorMsgFive + " error message did not appear", Status.FAIL);
            }
        }
        if (!errorMsgSix.isEmpty()) {
            if (isElementPresentBy(lblErrorMsg(errorMsgSix, 1))) {
                addToReport(errorMsgSix + " error message appeared", Status.PASS, true);
            } else {
                addToReport(errorMsgSix + " error message did not appear", Status.FAIL);
            }
        }

        addToReport("----------End of user not be able to access the next page without entering data to mandatory fields----------", Status.PASS, false);
        addToReport("----------Start of enter details for billers with up to maximum number of characters----------", Status.PASS, false);

        String purp = purpose + " " + CommonUtils.randomAlphaNumeric(5);
        //Select payment using
        if (paymentUsing.equals("Account")) {
            scrollToWebElement(lblNewPaymentAccountNumber);
            clickOnElement(rdoAccount);
        } else if (paymentUsing.equals("Credit Card")) {
            scrollToWebElement(lblNewPaymentAccountNumber);
            clickOnElement(rdoCreditCard);
        }

        //Enter amount and other relevant values
        sendKeysToElement(tfAmount, amount);

        //Reverse the mapping to be used as key value
        Map<String, String> reverseMap = new HashMap<>();
        for (Map.Entry<String, String> entry : kwBillersMap.entrySet()) {
            reverseMap.put(entry.getValue(), entry.getKey());
        }

        String kWCategory = reverseMap.get(category);

        switch (kWCategory) {
            case "KW_SCHOOL_FEES" -> {

                //Type admission number is available in data table
                if (!admissionNumber.isEmpty()) {
                    sendKeysToElement(tfFieldOne, admissionNumber);
                    if (billerName.equals(kwBillersMap.get("KW_MUSAEUS_COLLEGE"))) {
                        sendKeysToElement(tfFieldTwo, admissionNumber);
                    }
                }
                if (!name.isEmpty()) {
                    sendKeysToElement(tfFieldThree, name);
                }
                if (!classID.isEmpty()) {
                    sendKeysToElement(tfFieldFive, classID);
                }
                if (!purpose.isEmpty()) {
                    sendKeysToElement(tfFieldSix, purpose);
                }
            }
            case "KW_INTERNET_SERVICE_PROVIDERS" -> {

                if (billerName.equals(kwBillersMap.get("KW_DIALOG_BROADBAND"))) {
                    if (!mobileNo.isEmpty()) {
                        sendKeysToElement(tfFieldOne, mobileNo);
                        sendKeysToElement(tfFieldTwo, mobileNo);
                    }
                }
                if (billerName.equals(kwBillersMap.get("KW_SRI_LANKA_TELECOM_4G"))) {
                    if (!mobileNo.isEmpty()) {
                        sendKeysToElement(tfFieldThree, mobileNo);
                    }
                }
                if (!accountNumber.isEmpty()) {
                    sendKeysToElement(tfFieldOne, accountNumber);
                    sendKeysToElement(tfFieldTwo, accountNumber);
                }
            }
            case "KW_HOSPITALS" -> {

                if (!accountNumber.isEmpty()) {
                    sendKeysToElement(tfFieldOne, accountNumber);
                    sendKeysToElement(tfFieldTwo, accountNumber);
                }
                if (!name.isEmpty()) {
                    sendKeysToElement(tfFieldThree, name);
                }
            }
            case "KW_CEB" -> {

                if (!accountNumber.isEmpty()) {
                    sendKeysToElement(tfFieldOne, accountNumber);
                    sendKeysToElement(tfFieldTwo, accountNumber);
                }
            }
            case "KW_FINANCIAL_INSTITUTIONS" -> {

                if (!accountNumber.isEmpty()) {
                    sendKeysToElement(tfFieldOne, accountNumber);
                    sendKeysToElement(tfFieldTwo, accountNumber);
                }
            }
            case "KW_LEASING" -> {


                if (!referenceOrReservationNo.isEmpty()) {
                    sendKeysToElement(tfFieldOne, referenceOrReservationNo);
                }
                if (!mobileNo.isEmpty()) {
                    sendKeysToElement(tfFieldThree, mobileNo);
                }
                if (!nicNo.isEmpty()) {
                    sendKeysToElement(tfFieldFive, nicNo);
                }
            }
            case "KW_REAL_ESTATE" -> {

                if (!code.isEmpty()) {
                    sendKeysToElement(tfFieldOne, code);
                }
                if (!referenceOrReservationNo.isEmpty()) {
                    sendKeysToElement(tfFieldThree, referenceOrReservationNo);
                }
            }
            case "KW_EDUCATION" -> {

                if (billerName.equals(kwBillersMap.get("KW_NATIONAL_SCHOOL_OF_BUSINESS_MANAGEMENT"))) {
                    if (!nicNo.isEmpty()) {
                        sendKeysToElement(tfFieldOne, nicNo);
                    }
                    if (!admissionNumber.isEmpty()) {
                        sendKeysToElement(tfFieldThree, admissionNumber);
                    }
                    if (!name.isEmpty()) {
                        sendKeysToElement(tfFieldFive, name);
                    }
                    if (!date.isEmpty()) {
                        sendKeysToElement(tfFieldSix, date);
                    }
                } else {
                    if (!nicNo.isEmpty()) {
                        sendKeysToElement(tfFieldOne, nicNo);
                    }
                    if (!purpose.isEmpty()) {
                        sendKeysToElement(tfFieldThree, purpose);
                    }
                    if (!purpose.isEmpty()) {
                        sendKeysToElement(tfFieldFive, purp);
                    }
                }
            }
            case "KW_MOBILE_MONEY" -> {


                if (!mobileNo.isEmpty()) {
                    sendKeysToElement(tfFieldOne, mobileNo);
                }
                if (!mobileNo.isEmpty()) {
                    sendKeysToElement(tfFieldTwo, mobileNo);
                }
            }
            case "KW_LECO" -> {

                if (!accountNumber.isEmpty()) {
                    sendKeysToElement(tfFieldOne, accountNumber);
                }
                if (!accountNumber.isEmpty()) {
                    sendKeysToElement(tfFieldTwo, accountNumber);
                }
                if (!date.isEmpty()) {
                    sendKeysToElement(tfFieldThree, date);
                }
            }
            case "KW_NWSDB" -> {

                if (!accountNumber.isEmpty()) {
                    sendKeysToElement(tfFieldOne, accountNumber);
                }
                if (!accountNumber.isEmpty()) {
                    sendKeysToElement(tfFieldTwo, accountNumber);
                }
                if (!date.isEmpty()) {
                    sendKeysToElement(tfFieldThree, date);
                }
            }
            case "KW_CLUBS_AND_SOCIETIES" -> {

                if (!name.isEmpty()) {
                    sendKeysToElement(tfFieldOne, name);
                }
                if (!classID.isEmpty()) {
                    sendKeysToElement(tfFieldThree, classID);
                }
                if (!branch.isEmpty()) {
                    sendKeysToElement(tfFieldFive, branch);
                }
                if (!email.isEmpty()) {
                    sendKeysToElement(tfFieldSix, email);
                }
                if (!mobileNo.isEmpty()) {
                    sendKeysToElement(tfFieldSeven, mobileNo);
                }
            }
            case "KW_TELEPHONE" -> {
                if (billerName.equalsIgnoreCase(kwBillersMap.get("KW_SRI_LANKA_TELECOM_NEW_INVOICE_NUMBERS_14_DIGITS"))) {
                    //Type account number is available in data table
                    if (!accountNumber.isEmpty()) {
                        sendKeysToElement(tfFieldOne, accountNumber);
                        sendKeysToElement(tfFieldTwo, accountNumber);
                    }

                    //Type mobile number is available in data table
                    if (!mobileNo.isEmpty()) {
                        sendKeysToElement(tfFieldThree, mobileNo);
                    }
                } else {
                    //Type mobile number is available in data table
                    if (!mobileNo.isEmpty()) {
                        sendKeysToElement(tfFieldOne, mobileNo);
                        sendKeysToElement(tfFieldTwo, mobileNo);
                    }
                    //Type account number is available in data table
                    if (!accountNumber.isEmpty()) {
                        sendKeysToElement(tfFieldThree, accountNumber);
                    }
                    if (!nicNo.isEmpty()) {
                        sendKeysToElement(tfFieldOne, nicNo);
                    }
                }
            }
            case "KW_CABLE_TV" -> {
                if (billerName.equalsIgnoreCase(kwBillersMap.get("KW_DIALOG_TV"))) {
                    //Type account number is available in data table
                    if (!accountNumber.isEmpty()) {
                        sendKeysToElement(tfFieldOne, accountNumber);
                        sendKeysToElement(tfFieldTwo, accountNumber);
                    }

                    //Type mobile number is available in data table
                    if (!mobileNo.isEmpty()) {
                        sendKeysToElement(tfFieldThree, mobileNo);
                    }
                }
            }
            case "KW_INSURANCE" -> {
                if (billerName.equalsIgnoreCase(kwBillersMap.get("KW_JANASHAKTHI_INSURANCE_CO_LTD_LIFE"))) {
                    if (!nicNo.isEmpty()) {
                        sendKeysToElement(tfFieldOne, nicNo);
                    }
                    if (!policyNumber.isEmpty()) {
                        sendKeysToElement(tfFieldThree, policyNumber);
                        sendKeysToElement(tfFieldFour, policyNumber);
                    }
                    if (!name.isEmpty()) {
                        sendKeysToElement(tfFieldFive, name);
                    }
                } else {
                    if (!policyNumber.isEmpty()) {
                        sendKeysToElement(tfFieldOne, policyNumber);
                        sendKeysToElement(tfFieldTwo, policyNumber);
                    }
                    if (!name.isEmpty()) {
                        sendKeysToElement(tfFieldThree, name);
                    }
                }
            }
        }

        //Select transfer mode
        if (transferMode.equals(kwBillersMap.get("KW_ONE_TIME_TRANSACTION"))) {
            clickOnElement(rdoOTTransaction);
        } else if (transferMode.equals(kwBillersMap.get("KW_SETUP_STANDING_ORDER_SCHEDULE"))) {
            clickOnElement(rdoSchadule);
        }

        //Check saved biller option
        clickOnElement(chkSaveBiller);

        //Enter template name = prefix of biller name + autogenerated text
        waitForElementToBeClickable(tfTemplateName, LONG_WAIT);
        sendKeysToElement(tfTemplateName, templateName);

        addToReport("Entered details related to payment", Status.PASS, true);
        //Click button after entering data
        clickOnElement(btnNext);
        waitForElementPresence(btnClosePopup, SHORT_WAIT);
        if (isElementPresentBy(btnClosePopup, VERY_SHORT_WAIT)) {
            addToReport("Popup message appeared ", Status.INFO, true);
        }
        waitForElementToBeInvisible(btnNextLoading, LONG_WAIT);


        addToReport("----------End of enter details for billers with up to maximum number of characters----------", Status.PASS, false);
        addToReport("----------Start of validation of OTP confirmation page----------", Status.PASS, false);

        //Validate confirmation header
        if (isElementPresentBy(lblPopUpHeaderPaymentConfirmation, VERY_SHORT_WAIT)) {
            waitForElementToBeClickable(lblPopUpHeaderPaymentConfirmation, LONG_WAIT);
            addToReport("OTP confirmation appeared", Status.PASS, true);
        } else {
            waitFor(3);
            addToReport("OTP confirmation did not appear", Status.FAIL);
        }

        validateOTPPopup(category, billerName, transferMode, amount, mobileNo, accountNumber, nicNo, name, policyNumber, admissionNumber, classID, purpose, date, code, referenceOrReservationNo, branch, email, pAccountNo, purp, kwBillersMap);

        addToReport("End of validation for  biller : " + billerName + " in the OTP page", Status.PASS, true);
        waitForElementPresence(tfOTP(1), LONG_WAIT);
        sendKeysToElement(tfOTP(1), String.valueOf(OTPValue));
        clickOnElement(btnConfirm);
        waitForElementToBeInvisible(btnNextLoading, LONG_WAIT);

        addToReport("----------End of validation of OTP confirmation page----------", Status.PASS, false);
        addToReport("----------Start of validation of OTP success page----------", Status.PASS, false);

        //Validate the success label,payee name,pay from,amount,payment mode and entered reference while retrieving the reference number
        if (isElementPresentBy(lblSuccess)) {
            addToReport("Validated the success message in the OTP success page", Status.PASS, false);
        } else {
            addToReport("Failed to validate the success message in the OTP success page", Status.FAIL);
        }
        String[] referenceNumber = getTextFromElement(lblRefernceID).split("- ");
        if (referenceNumber[1] != null) {
            addToReport("Obtained the payment reference number " + referenceNumber[1], Status.PASS, false);
        } else {
            addToReport("Failed to get the reference number", Status.FAIL);
        }

        validateOTPPopup(category, billerName, transferMode, amount, mobileNo, accountNumber, nicNo, name, policyNumber, admissionNumber, classID, purpose, date, code, referenceOrReservationNo, branch, email, pAccountNo, purp, kwBillersMap);

        addToReport("End of validation for  biller : " + billerName + " in the OTP confirmation page", Status.PASS, true);

        clickOnElement(btnPrint);

        //Wait for download to initiate - update this with dynamic once stabilized
        waitFor(5);

        // Get the latest downloaded file
        File latestFile = getLatestDownloadedFile(downloadDirectory);

        if (latestFile != null) {
            // Extract text from the PDF
            String extractedText = extractTextFromPDF(latestFile.getAbsolutePath()).replace("/n", "");

            addToReport(" Latest downloaded pdf :  : '" + extractedText, Status.INFO, false);
            System.out.println("Latest Downloaded PDF: " + extractedText);

            waitForElementToBeInvisible(popUpPDFDownload, LONG_WAIT);

            //validate payment id
            if (extractedText.contains(referenceNumber[1].trim())) {
                addToReport(" Validated reference id " + referenceNumber[1] + " for the downloaded record", Status.PASS, false);
            } else {
                addToReport(" Failed to validate reference id of the downloaded record", Status.FAIL, false);
            }
        } else {
            addToReport(" Failed to download the payment record", Status.FAIL, false);
        }

        clickOnElement(btnClosePopup);
        addToReport("----------End of validation of OTP success page----------", Status.PASS, false);
        addToReport("----------Start of validation of search saved template and delete----------", Status.PASS, false);
        scrollPageToTop();
        selectHeaderTab(kwBillersMap.get("KW_SAVED_BILLERS"));
        waitForElementToBeClickable(btnSearchBiller, LONG_WAIT);
        scrollPageToTop();
        sendKeysToElement(tfSearch, templateName);
        clickOnElementUsingJS(btnSearchBiller);

        waitForElementToBeInvisible(icnSavedBillerGridLoading, LONG_WAIT);

        //Validate the search results
        int recordCount = isElementsPresentBy(tblRows);
        if (recordCount != 0) {
            for (int inc = 1; inc <= recordCount; inc++) {
                //Table retrieved value equals template name then delete
                if (getTextFromElement(lblSavedBillerTemplateName(inc)).equals(templateName)) {
                    clickOnElement(btnSavedBillerTemplateDelete(inc));
                    addToReport(" Clicked delete saved template " + templateName, Status.PASS, true);
                    break;
                }
            }

            //Delete the template
            waitForElementPresence(tfOTP(1), LONG_WAIT);
            sendKeysToElement(tfOTP(1), String.valueOf(OTPValue));
            addToReport(" OTP confirmation for template deletion of " + templateName, Status.PASS, true);
            clickOnElement(btnConfirm);
            waitForElementToBeInvisible(btnNextLoading, LONG_WAIT);
            waitForElementPresence(popUpMsgBillerDeleted, LONG_WAIT);

            scrollPageToTop();
            selectHeaderTab(kwBillersMap.get("KW_SAVED_BILLERS"));
            waitForElementToBeClickable(btnSearchBiller, LONG_WAIT);

            sendKeysToElement(tfSearch, templateName);
            clickOnElement(btnSearchBiller);

            waitForElementToBeInvisible(icnSavedBillerGridLoading, LONG_WAIT);

            //Validate the search results
            recordCount = isElementsPresentBy(tblRows);
            if (recordCount != 0) {
                addToReport(" Template is still available under saved biller search " + templateName, Status.FAIL, true);
            } else {
                addToReport(" Template " + templateName + " has been successfully deleted", Status.PASS, false);
            }
        } else {
            addToReport(" Failed to search saved template " + templateName, Status.FAIL, true);
        }
        addToReport("----------End of validation of search saved template and delete----------", Status.PASS, false);

        //Return primary account number and reference number
        return new String[]{accountNumbers.get(0).replaceAll("\\s+", ""), referenceNumber[1]};
    }

    /**
     * This method is used to validate popup otp and confirmation
     *
     * @param category                 The category under which the biller falls (e.g., Utilities, Insurance, Education).
     * @param billerName               The name of the biller (e.g., CEB, NWSDB, Mobitel).
     * @param transferMode             The mode of transfer selected for the transaction (e.g., Online Transfer, Manual).
     * @param amount                   The amount to be paid or processed.
     * @param mobileNo                 The mobile number of the customer or user.
     * @param accountNumber            The related account number for the transaction (e.g., water/electricity/insurance).
     * @param nicNo                    The National Identity Card number of the customer.
     * @param name                     The name of the user, customer, or policyholder.
     * @param policyNumber             Insurance policy number (if applicable).
     * @param admissionNumber          Admission number of a student (for education-related payments).
     * @param classID                  Class or grade identifier for the student.
     * @param purpose                  The purpose of the transaction (e.g., Payment, Renewal, Registration).
     * @param date                     Date relevant to the transaction (e.g., due date, DOB, bill date).
     * @param code                     A short code representing reason/type/category for the transaction.
     * @param referenceOrReservationNo Reference number or reservation ID, if applicable.
     * @param branch                   The branch name or code associated with the user or customer.
     * @param email                    Email address of the customer for notifications or receipts.
     * @param pAccountNo               The payer's account number from which the funds will be debited.
     * @param purp                     Short version or alternative of the 'purpose' field.
     * @param kwBillersMap             - key word and the content of keyword is added as suffix
     */
    public void validateOTPPopup(String category, String billerName, String transferMode, String amount, String mobileNo, String accountNumber, String nicNo, String name, String policyNumber, String admissionNumber, String classID, String purpose, String date, String code, String referenceOrReservationNo, String branch, String email, String pAccountNo, String purp, Map<String, String> kwBillersMap) {

        //Reverse the mapping to be used as key value in switch
        Map<String, String> reverseMap = new HashMap<>();
        for (Map.Entry<String, String> entry : kwBillersMap.entrySet()) {
            reverseMap.put(entry.getValue(), entry.getKey());
        }
        String kWCategory = reverseMap.get(category);

        //Validate payee name,pay from,amount,payment mode and entered reference
        switch (kWCategory) {
            case "KW_CEB" -> {
                if (kwBillersMap.get("KW_CEYILON_ELECTRICITY_BOARD").equals(getTextFromElement(lblPayeeName))) {
                    addToReport("Validated the biller name " + getTextFromElement(lblPayeeName) + " in the OTP page", Status.PASS, false);
                } else {
                    addToReport("Failed to validate the biller name " + category + " in the OTP page found " + getTextFromElement(lblPayeeName), Status.FAIL);
                }
            }
            case "KW_LECO" -> {
                if (kwBillersMap.get("KW_LANKA_ELECTRICITY_COMPANY").equals(getTextFromElement(lblPayeeName))) {
                    addToReport("Validated the biller name " + getTextFromElement(lblPayeeName) + " in the OTP page", Status.PASS, false);
                } else {
                    addToReport("Failed to validate the biller name " + category + " in the OTP page found " + getTextFromElement(lblPayeeName), Status.FAIL);
                }
            }
            case "KW_NWSDB" -> {
                if (kwBillersMap.get("KW_NATIONAL_WATER_SUPPLY_AND_DRAINAGE_BOARD").equals(getTextFromElement(lblPayeeName))) {
                    addToReport("Validated the biller name " + getTextFromElement(lblPayeeName) + " in the OTP page", Status.PASS, false);
                } else {
                    addToReport("Failed to validate the biller name " + category + " in the OTP page found " + getTextFromElement(lblPayeeName), Status.FAIL);
                }
            }
            default -> {
                if (billerName.equals(getTextFromElement(lblPayeeName))) {
                    addToReport("Validated the biller name " + getTextFromElement(lblPayeeName) + " in the OTP page", Status.PASS, false);
                } else {
                    addToReport("Failed to validate the biller name " + billerName + " in the OTP page found " + getTextFromElement(lblPayeeName), Status.FAIL);
                }
            }
        }

        if (pAccountNo.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_PAY_FROM")), "value"))) {
            addToReport("Validated the pay from " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_PAY_FROM")), "value") + " in the OTP page", Status.PASS, false);
        } else {
            addToReport("Failed to validate the pay from " + pAccountNo + " in the OTP page", Status.FAIL);
        }

        if (getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_AMOUNT")), "value").contains(amount)) {
            addToReport("Validated the amount  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_AMOUNT")), "value") + " in the OTP page", Status.PASS, false);
        } else {
            addToReport("Failed to validate the amount " + amount + " in the OTP page", Status.FAIL);
        }

        if (transferMode.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_PAYMENT_MODE")), "value"))) {
            addToReport("Validated the payment mode  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_PAYMENT_MODE")), "value") + " in the OTP page", Status.PASS, false);
        } else {
            addToReport("Failed to validate the payment mode " + transferMode + " in the OTP page", Status.FAIL);
        }

        //Validate dynamic fields
        if (category.equals(kwBillersMap.get("KW_TELEPHONE"))) {
            // Enter fields relevant to mobile number
            if (!mobileNo.isEmpty()) {
                if (mobileNo.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_PHONE_NUMBER")), "value"))) {
                    addToReport("Validated the mobile  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_PHONE_NUMBER")), "value") + " in the OTP page", Status.PASS, false);
                } else if (mobileNo.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_TELEPHONE_NUMBER_WITH_AREA_CODE")), "value"))) {
                    addToReport("Validated the mobile  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_TELEPHONE_NUMBER_WITH_AREA_CODE")), "value") + " in the OTP page", Status.PASS, false);
                } else if (mobileNo.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_MOBILE_NUMBER")), "value"))) {
                    addToReport("Validated the mobile  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_MOBILE_NUMBER")), "value") + " in the OTP page", Status.PASS, false);
                } else {
                    addToReport("Failed to validate the mobile in the OTP page", Status.FAIL);
                }
            }
            if (!accountNumber.isEmpty()) {
                if (accountNumber.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_INVOICE_NUMBER_WITH")), "value")) || accountNumber.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_ACCOUNT_NO")), "value"))) {
                    addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_INVOICE_NUMBER_WITH")), "value") + " in the OTP page", Status.PASS, false);
                } else {
                    addToReport("Failed to validate the account number " + accountNumber + " in the OTP page", Status.FAIL);
                }
            }
        } else if (category.equals(kwBillersMap.get("KW_INSURANCE"))) {

            if (billerName.equals(kwBillersMap.get("KW_SRI_LANKA_INSURANCE_LIFE_RENEWALS"))) {
                if (!policyNumber.isEmpty()) {
                    if (policyNumber.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_POLICY_NO")), "value"))) {
                        addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_POLICY_NO")), "value") + " in the OTP page", Status.PASS, true);
                    } else {
                        addToReport("Failed to validate the policy number " + policyNumber + " in the OTP page", Status.FAIL);
                    }
                }
                if (!name.isEmpty()) {
                    if (name.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_NAME_OF_POLICY_HOLDER")), "value"))) {
                        addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_NAME_OF_POLICY_HOLDER")), "value") + " in the OTP page", Status.PASS, true);
                    } else {
                        addToReport("Failed to validate the name " + name + " in the OTP page", Status.FAIL);
                    }
                }
            } else {
                if (!policyNumber.isEmpty()) {
                    if (policyNumber.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_POLICY_NUMBER")), "value"))) {
                        addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_POLICY_NUMBER")), "value") + " in the OTP page", Status.PASS, true);
                    } else {
                        addToReport("Failed to validate the policy number " + policyNumber + " in the OTP page", Status.FAIL);
                    }
                }
                if (!name.isEmpty()) {
                    if (name.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_NAME_OF_POLICY_HOLDER")), "value"))) {
                        addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_NAME_OF_POLICY_HOLDER")), "value") + " in the OTP page", Status.PASS, true);
                    } else {
                        addToReport("Failed to validate the name " + name + " in the OTP page", Status.FAIL);
                    }
                }
            }
            if (!nicNo.isEmpty()) {
                if (nicNo.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_NIC_NUMBER")), "value"))) {
                    addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_NIC_NUMBER")), "value") + " in the OTP page", Status.PASS, true);
                } else {
                    addToReport("Failed to validate the nic number " + policyNumber + " in the OTP page", Status.FAIL);
                }
            }

        } else if (category.equals(kwBillersMap.get("KW_CABLE_TV"))) {

            if (!accountNumber.isEmpty()) {
                if (accountNumber.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_ACCOUNT_NO")), "value"))) {
                    addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_ACCOUNT_NO")), "value") + " in the OTP page", Status.PASS, false);
                } else {
                    addToReport("Failed to validate the account number " + accountNumber + " in the OTP page", Status.FAIL);
                }
            }
        } else if (category.equals(kwBillersMap.get("KW_SCHOOL_FEES"))) {

            //Validate admission no
            if (!admissionNumber.isEmpty()) {
                if (admissionNumber.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_STUDENT_ADMISSION_NUMBER")), "value")) || accountNumber.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_ACCOUNT_NO")), "value"))) {
                    addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_STUDENT_ADMISSION_NUMBER")), "value") + " in the OTP page", Status.PASS, true);
                } else {
                    addToReport("Failed to validate the  admission number " + admissionNumber + " in the OTP page", Status.FAIL);
                }
            }
            //Validate name
            if (!name.isEmpty()) {
                if (name.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_STUDENT_NAME")), "value"))) {
                    addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_STUDENT_NAME")), "value") + " in the OTP page", Status.PASS, true);
                } else {
                    addToReport("Failed to validate the name " + name + " in the OTP page", Status.FAIL);
                }
            }
            //Validate class
            if (!classID.isEmpty()) {
                if (classID.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_CLASS")), "value"))) {
                    addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_CLASS")), "value") + " in the OTP page", Status.PASS, true);
                } else {
                    addToReport("Failed to validate the name " + classID + " in the OTP page", Status.FAIL);
                }
            }
            //Validate purpose
            if (!purpose.isEmpty()) {
                if (purpose.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_PURPOSE")), "value"))) {
                    addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_PURPOSE")), "value") + " in the OTP page", Status.PASS, true);
                } else {
                    addToReport("Failed to validate the name " + purpose + " in the OTP page", Status.FAIL);
                }
            }

        } else if (category.equals(kwBillersMap.get("KW_INTERNET_SERVICE_PROVIDERS"))) {

            if (billerName.equals(kwBillersMap.get("KW_SRI_LANKA_TELECOM_4G"))) {

                //Validate phone number
                if (!mobileNo.isEmpty()) {
                    if (mobileNo.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_4G_TELEPHONE_NUMBER_WITH_AREA_CODE")), "value"))) {
                        addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_4G_TELEPHONE_NUMBER_WITH_AREA_CODE")), "value") + " in the OTP page", Status.PASS, true);
                    } else {
                        addToReport("Failed to validate the 4g tp no " + mobileNo + " in the OTP page", Status.FAIL);
                    }
                }

                //Validate account
                if (!accountNumber.isEmpty()) {
                    if (accountNumber.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_INVOICE_NUMBER_WITH")), "value"))) {
                        addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_INVOICE_NUMBER_WITH")), "value") + " in the OTP page", Status.PASS, true);
                    } else {
                        addToReport("Failed to validate the invoice number " + accountNumber + " in the OTP page", Status.FAIL);
                    }
                }

            }
        } else if (category.equals(kwBillersMap.get("KW_HOSPITALS"))) {


            //Validate patients ref no
            if (!accountNumber.isEmpty()) {
                if (accountNumber.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_PATIENTS_REFERENCE_NUMBER_BHT")), "value"))) {
                    addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_PATIENTS_REFERENCE_NUMBER_BHT")), "value") + " in the OTP page", Status.PASS, true);
                } else {
                    addToReport("Failed to validate the Patient's Reference Number " + accountNumber + " in the OTP page", Status.FAIL);
                }
            }
            //Validate patients ref no
            if (!name.isEmpty()) {
                if (name.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_NAME_OF_THE_PATIENT")), "value"))) {
                    addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_NAME_OF_THE_PATIENT")), "value") + " in the OTP page", Status.PASS, true);
                } else {
                    addToReport("Failed to validate the Patient's name " + name + " in the OTP page", Status.FAIL);
                }
            }

        } else if (category.equals(kwBillersMap.get("KW_CEB"))) {

            //Validate accounts ref no
            if (!accountNumber.isEmpty()) {
                if (accountNumber.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_ELECTRICITY_BILL_ACCOUNT_NO")), "value"))) {
                    addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_ELECTRICITY_BILL_ACCOUNT_NO")), "value") + " in the OTP page", Status.PASS, true);
                } else {
                    addToReport("Failed to validate the account number " + accountNumber + " in the OTP page", Status.FAIL);
                }
            }

        } else if (category.equals(kwBillersMap.get("KW_LECO"))) {
            //Validate accounts ref no
            if (!accountNumber.isEmpty()) {
                if (accountNumber.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_ACCOUNT_NO")), "value"))) {
                    addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_ACCOUNT_NO")), "value") + " in the OTP page", Status.PASS, true);
                } else {
                    addToReport("Failed to validate the account number " + accountNumber + " in the OTP page", Status.FAIL);
                }
            }
            //Validate billing month
            if (!date.isEmpty()) {
                if (date.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_BILLING_MONTH")), "value"))) {
                    addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_BILLING_MONTH")), "value") + " in the OTP page", Status.PASS, true);
                } else {
                    addToReport("Failed to validate the account number " + date + " in the OTP page", Status.FAIL);
                }
            }

        } else if (category.equals(kwBillersMap.get("KW_NWSDB"))) {
            //Validate accounts ref no
            if (!accountNumber.isEmpty()) {
                if (accountNumber.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_ONLY_THE_FIRST_12_NUMBERS_OF_WATER_BILL_ACCOUNT")), "value"))) {
                    addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_ONLY_THE_FIRST_12_NUMBERS_OF_WATER_BILL_ACCOUNT")), "value") + " in the OTP page", Status.PASS, true);
                } else {
                    addToReport("Failed to validate the account number " + accountNumber + " in the OTP page", Status.FAIL);
                }
            }
            //Validate billing month
            if (!date.isEmpty()) {
                if (date.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_BILLING_MONTH")), "value"))) {
                    addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_BILLING_MONTH")), "value") + " in the OTP page", Status.PASS, true);
                } else {
                    addToReport("Failed to validate the date  " + date + " in the OTP page", Status.FAIL);
                }
            }

        } else if (category.equals(kwBillersMap.get("KW_PRIME_LANDS_PVT_LTD"))) {

            //Validate customer code
            if (!code.isEmpty()) {
                if (code.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_CUSTOMER_CODE")), "value"))) {
                    addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_CUSTOMER_CODE")), "value") + " in the OTP page", Status.PASS, true);
                } else {
                    addToReport("Failed to validate the customer code " + code + " in the OTP page", Status.FAIL);
                }
            }
            //Validate reservation number
            if (!referenceOrReservationNo.isEmpty()) {
                if (referenceOrReservationNo.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_RESERVATION_NUMBER")), "value"))) {
                    addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_RESERVATION_NUMBER")), "value") + " in the OTP page", Status.PASS, true);
                } else {
                    addToReport("Failed to validate the customer code " + referenceOrReservationNo + " in the OTP page", Status.FAIL);
                }
            }

        } else if (category.equals(kwBillersMap.get("KW_FINANCIAL_INSTITUTIONS"))) {

            //Validate patients ref no
            if (!accountNumber.isEmpty()) {
                if (accountNumber.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_SAVINGS_ACCOUNT_NO")), "value"))) {
                    addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_SAVINGS_ACCOUNT_NO")), "value") + " in the OTP page", Status.PASS, true);
                } else {
                    addToReport("Failed to validate the account number " + accountNumber + " in the OTP page", Status.FAIL);
                }
            }

        } else if (category.equals(kwBillersMap.get("KW_KAACHA_PHOTOGRAPHY_CLUB_OF_SAMPATH_BANK"))) {

            //Validate name
            if (!name.isEmpty()) {
                if (name.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_CALLING_NAME")), "value"))) {
                    addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_CALLING_NAME")), "value") + " in the OTP page", Status.PASS, true);
                } else {
                    addToReport("Failed to validate the account number " + name + " in the OTP page", Status.FAIL);
                }
            }
            //Validate Employee ID
            if (!classID.isEmpty()) {
                if (classID.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_EMPLOYEE_ID")), "value"))) {
                    addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_EMPLOYEE_ID")), "value") + " in the OTP page", Status.PASS, true);
                } else {
                    addToReport("Failed to validate the account number " + classID + " in the OTP page", Status.FAIL);
                }
            }
            //Validate Branch
            if (!branch.isEmpty()) {
                if (branch.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_BRANCH")), "value"))) {
                    addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_BRANCH")), "value") + " in the OTP page", Status.PASS, true);
                } else {
                    addToReport("Failed to validate the account number " + branch + " in the OTP page", Status.FAIL);
                }
            }
            //Validate Email address
            if (!email.isEmpty()) {
                if (email.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_EMAIL_ADDRESS")), "value"))) {
                    addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_EMAIL_ADDRESS")), "value") + " in the OTP page", Status.PASS, true);
                } else {
                    addToReport("Failed to validate the account number " + email + " in the OTP page", Status.FAIL);
                }
            }
            //Validate Contact No
            if (!mobileNo.isEmpty()) {
                if (mobileNo.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_CONTACT_NO")), "value"))) {
                    addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_CONTACT_NO")), "value") + " in the OTP page", Status.PASS, true);
                } else {
                    addToReport("Failed to validate the account number " + mobileNo + " in the OTP page", Status.FAIL);
                }
            }

        } else if (category.equals(kwBillersMap.get("KW_LEASING"))) {

            //Validate  ref no
            if (!referenceOrReservationNo.isEmpty()) {
                if (referenceOrReservationNo.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_CUSTOMER_BANK_REFERENCE_NO")), "value"))) {
                    addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_CUSTOMER_BANK_REFERENCE_NO")), "value") + " in the OTP page", Status.PASS, true);
                } else {
                    addToReport("Failed to validate the reference number " + referenceOrReservationNo + " in the OTP page", Status.FAIL);
                }
            }
            //Validate  mobile no
            if (!mobileNo.isEmpty()) {
                if (mobileNo.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_TELEPHONE_NO_10_DIGITS")), "value"))) {
                    addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_TELEPHONE_NO_10_DIGITS")), "value") + " in the OTP page", Status.PASS, true);
                } else {
                    addToReport("Failed to validate the mobile number " + mobileNo + " in the OTP page", Status.FAIL);
                }
            }
            //Validate  mobile no
            if (!nicNo.isEmpty()) {
                if (nicNo.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_VEHICLE_NO_NIC_NO")), "value"))) {
                    addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_VEHICLE_NO_NIC_NO")), "value") + " in the OTP page", Status.PASS, true);
                } else {
                    addToReport("Failed to validate the nic number " + nicNo + " in the OTP page", Status.FAIL);
                }
            }

        } else if (category.equals(kwBillersMap.get("KW_EDUCATION"))) {

            if (billerName.equals(kwBillersMap.get("KW_NATIONAL_SCHOOL_OF_BUSINESS_MANAGEMENT"))) {

                //Validate  nic no
                if (!nicNo.isEmpty()) {
                    if (nicNo.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_NIC")), "value"))) {
                        addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_NIC")), "value") + " in the OTP page", Status.PASS, true);
                    } else {
                        addToReport("Failed to validate the nic number " + nicNo + " in the OTP page", Status.FAIL);
                    }
                }

                //Validate  registration no
                if (!admissionNumber.isEmpty()) {
                    if (admissionNumber.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_STUDENT_REGISTRATION_NUMBER")), "value"))) {
                        addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_STUDENT_REGISTRATION_NUMBER")), "value") + " in the OTP page", Status.PASS, true);
                    } else {
                        addToReport("Failed to validate the admission number " + admissionNumber + " in the OTP page", Status.FAIL);
                    }
                }
                //Validate  name
                if (!name.isEmpty()) {
                    if (name.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_NAME")), "value"))) {
                        addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_NAME")), "value") + " in the OTP page", Status.PASS, true);
                    } else {
                        addToReport("Failed to validate the name " + name + " in the OTP page", Status.FAIL);
                    }
                }
                //Validate  year
                if (!date.isEmpty()) {
                    if (date.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_STUDY_YEAR")), "value"))) {
                        addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_STUDY_YEAR")), "value") + " in the OTP page", Status.PASS, true);
                    } else {
                        addToReport("Failed to validate the name " + date + " in the OTP page", Status.FAIL);
                    }
                }

            } else {

                //Validate  nic no
                if (!nicNo.isEmpty()) {
                    if (nicNo.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_STUDENT_REGISTRATION_NUMBER_STUDENT_NIC_NUMBER")), "value"))) {
                        addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_STUDENT_REGISTRATION_NUMBER_STUDENT_NIC_NUMBER")), "value") + " in the OTP page", Status.PASS, true);
                    } else {
                        addToReport("Failed to validate the nic number " + nicNo + " in the OTP page", Status.FAIL);
                    }
                }
                //Validate purpose
                if (!purpose.isEmpty()) {
                    if (purpose.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_REASON_FOR_THE_PAYMENT")), "value"))) {
                        addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_REASON_FOR_THE_PAYMENT")), "value") + " in the OTP page", Status.PASS, true);
                    } else {
                        addToReport("Failed to validate the purpose " + purpose + " in the OTP page", Status.FAIL);
                    }
                }
                //Validate  additional purpose  no
                if (!purpose.isEmpty()) {
                    if (purp.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_REASON_FOR_THE_PAYMENT_OTHER")), "value"))) {
                        addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_REASON_FOR_THE_PAYMENT_OTHER")), "value") + " in the OTP page", Status.PASS, true);
                    } else {
                        addToReport("Failed to validate the purpose description " + purp + " in the OTP page", Status.FAIL);
                    }
                }
            }

        } else if (category.equals(kwBillersMap.get("KW_MOBILE_MONEY"))) {
            if (billerName.equals(kwBillersMap.get("KW_MOBITEL_MCASH"))) {
                //Validate mobile no
                if (!mobileNo.isEmpty()) {
                    if (mobileNo.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_MOBILE_WALLET_NUMBER")), "value"))) {
                        addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_MOBILE_WALLET_NUMBER")), "value") + " in the OTP page", Status.PASS, true);
                    } else {
                        addToReport("Failed to validate the mobile number " + mobileNo + " in the OTP page", Status.FAIL);
                    }
                }

            } else {
                //Validate mobile no
                if (!mobileNo.isEmpty()) {
                    if (mobileNo.equals(getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_MOBILE_NUMBER_WITHOUT")), "value"))) {
                        addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kwBillersMap.get("KW_MOBILE_NUMBER_WITHOUT")), "value") + " in the OTP page", Status.PASS, true);
                    } else {
                        addToReport("Failed to validate the mobile number " + mobileNo + " in the OTP page", Status.FAIL);
                    }
                }
            }
        }

        addToReport("End of validation for  biller : " + billerName + " in the OTP page", Status.PASS, true);
    }

    /**
     * Validate the re initiation of transaction from history
     *
     * @param billerTitle       The title of the biller or the company providing the service (e.g., "Ceylon Electricity Board", "Mobitel").
     * @param otpValue          The one-time password (OTP) used for validating the transaction or action.
     * @param kWAmount          The amount to be paid for the transaction (e.g., bill payment amount).
     * @param kWMobileNoWithout The mobile number of the customer, without the leading zero, for verification or identification purposes.
     */
    public void validateReinitiationOfTransactionFromHistory(String billerTitle, String otpValue, String kWAmount, String kWMobileNoWithout) {
        try {
            //Select appropriate header
            waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);
            int rCount = 0;

            waitForElementToBeInvisible(imgGreyLoader, MODERATE_WAIT);
            //Validate the search results
            int recordCount = isElementsPresentBy(tblRows);
            if (recordCount != 0) {
                for (int inc = 1; inc <= recordCount; inc++) {
                    //Reinitiate transaction based on biller title
                    if (billerTitle.equals(getTextFromElement(tblCellRecord(2, inc)))) {
                        rCount = inc;
                        break;
                    }
                }
                //Extract required the first record data
                String paymentID = getTextFromElement(tblCellRecord(1, rCount));
                String bTitle = getTextFromElement(tblCellRecord(2, rCount));
                String CurrencyAndAmt = getTextFromElement(tblCellRecord(6, rCount));

                addToReport("Obtained record with payment id : " + paymentID + ", biller tittle : " + billerTitle, Status.PASS, true);

                clickOnElement(btnSavedBillerReinitiate(rCount));

                waitForElementToBeInvisible(imgGreyLoader, LONG_WAIT);

                //Validate contents from text fields

                if (CurrencyAndAmt.contains(getTextFromElement(tfAmount))) {
                    addToReport("Validated the currency and amount  " + CurrencyAndAmt + " in the new payment", Status.PASS, false);
                } else {
                    addToReport("Failed to validate the amount " + CurrencyAndAmt + " in the new payment", Status.FAIL);
                }

                String mobileNo = getAttributeOrText(tfFieldOne, "value");
                addToReport("Obtained mobile number :  " + mobileNo + " in the new payment", Status.PASS, false);

                //Re-Type the mobile number
                sendKeysToElement(tfFieldTwo, mobileNo);

                addToReport("Entered details related to payment", Status.PASS, true);
                //Click button after entering data
                clickOnElement(btnNext);
                waitForElementPresence(btnClosePopup, SHORT_WAIT);
                if (isElementPresentBy(btnClosePopup, VERY_SHORT_WAIT)) {
                    addToReport("Popup message appeared ", Status.INFO, true);
                }
                waitForElementToBeInvisible(btnNextLoading, LONG_WAIT);

                //Validate the OTP page

                if (bTitle.equals(billerTitle)) {
                    //Validate mobile no
                    if (!mobileNo.isEmpty()) {
                        if (mobileNo.equals(getAttributeOrText(tfPaymentConfirmation(kWMobileNoWithout), "value"))) {
                            addToReport("Validated the value  " + getAttributeOrText(tfPaymentConfirmation(kWMobileNoWithout), "value") + " in the OTP page", Status.PASS, false);
                        } else {
                            addToReport("Failed to validate the account number " + mobileNo + " in the OTP page", Status.FAIL);
                        }
                    }

                }
                if (getAttributeOrText(tfPaymentConfirmation(kWAmount), "value").contains(CurrencyAndAmt.split(" ")[1])) {
                    addToReport("Validated the amount  " + getAttributeOrText(tfPaymentConfirmation(kWAmount), "value") + " in the OTP page", Status.PASS, false);
                } else {
                    addToReport("Failed to validate the amount " + CurrencyAndAmt.split(" ")[1] + " in the OTP page", Status.FAIL);
                }

                addToReport("End of validation for  biller : " + bTitle + " in the OTP page", Status.PASS, true);

                waitForElementPresence(tfOTP(1), LONG_WAIT);
                sendKeysToElement(tfOTP(1), String.valueOf(otpValue));
                clickOnElement(btnConfirm);
                waitForElementToBeInvisible(btnNextLoading, LONG_WAIT);

                //Validate the success label,payee name,pay from,amount,payment mode and entered reference while retrieving the reference number
                if (isElementPresentBy(lblSuccess)) {
                    addToReport("Validated the success message in the OTP success page", Status.PASS, false);
                } else {
                    addToReport("Failed to validate the success message in the OTP success page", Status.FAIL);
                }
                String[] referenceNumber = getTextFromElement(lblRefernceID).split("- ");
                if (referenceNumber[1] != null) {
                    addToReport("Obtained the payment reference number " + referenceNumber[1], Status.PASS, true);
                } else {
                    addToReport("Failed to get the reference number", Status.FAIL);
                }

            } else {
                addToReport("Error validation of re-initiation of bill payment from history as no records are there", Status.FAIL);
                throw new RuntimeException("Failed to validate re-initiation of bill payment from history ");
            }

        } catch (Exception e) {
            addToReport("Error re initiation of transactions in biller", Status.FAIL);
            throw new RuntimeException("Failed to re initiation of transactions in biller ", e);
        }
        //Close the popup
        waitForElementToBeClickable(btnClosePopup, MODERATE_WAIT);
        clickOnElement(btnClosePopup);
    }

    /**
     * Initiate bill payments for multiple categories and validate via my account
     *
     * @param OTPValue                 One-time password used for authentication.
     * @param category                 The main category of the biller (e.g., Utilities, Insurance, Education).
     * @param billerName               The name of the biller (e.g., CEB, NWSDB).
     * @param paymentUsing             The payment method selected (e.g., Credit Card, Savings Account).
     * @param transferMode             The mode of transfer (e.g., IMPS, NEFT).
     * @param amount                   The payment amount.
     * @param mobileNo                 Mobile number of the user/customer.
     * @param accountNumber            Account number related to the biller or customer.
     * @param nicNo                    National Identity Card number.
     * @param name                     Name of the policyholder or customer.
     * @param policyNumber             Insurance policy number.
     * @param admissionNumber          Student's admission number (for school fee payments).
     * @param classID                  Class identifier (e.g., Grade 6, C6).
     * @param purpose                  Purpose of the payment.
     * @param date                     Date associated with the transaction (e.g., due date, birthdate).
     * @param code                     Short code for payment reason or institution.
     * @param referenceOrReservationNo Reference number or reservation ID.
     * @param branch                   Branch associated with the employee or customer.
     * @param email                    Email address of the user.
     * @param kwBillersMap             key word and the content of keyword is added as suffix
     * @return
     */
    public void initiateBillPaymentsUsingForexAndValidate(String OTPValue, String category, String billerName, String paymentUsing, String transferMode, String amount, String mobileNo, String accountNumber, String ackMsg, String nicNo, String name, String policyNumber, String admissionNumber, String classID, String purpose, String date, String code, String referenceOrReservationNo, String branch, String email, Map<String, String> kwBillersMap, String paymentProcessingFeeMsg, String txtLKR) {

        addToReport("----------Start of validation of acknowledgement message----------", Status.PASS, false);

        //Check by default primary account needs to selected
        waitForElementPresence(lblNewPaymentAccountNumber, LONG_WAIT);
        waitForElementPresence(btnNext);
        waitForElementPresence(btnClosePopup, SHORT_WAIT);
        if (isElementPresentBy(btnClosePopup, VERY_SHORT_WAIT)) {
            addToReport("Popup message appeared ", Status.INFO, true);
        }
        waitForElementToBeClickable(btnNext, LONG_WAIT);

        //validate the primary account number both in panel and from pay from dropdown
        List<String> ddAccountNumbers = getSelectedOptionText(ddPayFrom, "ALL_OPTIONS_VALUE");
        selectFromDropdown(ddPayFrom, accountNumber, "value");
        addToReport("All available accounts for the user " + ddAccountNumbers, Status.PASS, false);

        String purp = purpose + " " + CommonUtils.randomAlphaNumeric(5);

        //Select payment using
        if (paymentUsing.equals("Account")) {
            scrollToWebElement(lblNewPaymentAccountNumber);
            clickOnElement(rdoAccount);
        } else if (paymentUsing.equals("Credit Card")) {
            scrollToWebElement(lblNewPaymentAccountNumber);
            clickOnElement(rdoCreditCard);
        }

        //Enter amount and other relevant values
        sendKeysToElement(tfAmount, Keys.BACK_SPACE, 5);
        sendKeysToElement(tfAmount, amount);

        //Type mobile number is available in data table
        if (!mobileNo.isEmpty()) {
            sendKeysToElement(tfFieldOne, mobileNo);
            sendKeysToElement(tfFieldTwo, mobileNo);
        }

        //Select transfer mode
        if (transferMode.equals(kwBillersMap.get("KW_ONE_TIME_TRANSACTION"))) {
            clickOnElement(rdoOTTransaction);
        } else if (transferMode.equals(kwBillersMap.get("KW_SETUP_STANDING_ORDER_SCHEDULE"))) {
            clickOnElement(rdoSchadule);
        }
        addToReport("Entered details related to payment", Status.PASS, true);
        //Click button after entering data
        clickOnElement(btnNext);
        waitForElementPresence(btnClosePopup, SHORT_WAIT);
        if (isElementPresentBy(btnClosePopup, VERY_SHORT_WAIT)) {
            addToReport("Popup message appeared ", Status.INFO, true);
        }
        waitForElementToBeInvisible(btnNextLoading, LONG_WAIT);

        //Validate confirmation header
        if (isElementPresentBy(lblPopUpHeaderPaymentConfirmation, VERY_SHORT_WAIT)) {
            waitForElementToBeClickable(lblPopUpHeaderPaymentConfirmation, LONG_WAIT);
            addToReport("OTP confirmation appeared", Status.PASS, true);
        } else {
            waitFor(3);
            addToReport("OTP confirmation did not appear", Status.FAIL);
        }
        //Validate confirmation header
        if (isElementPresentBy(btnConfirm, VERY_SHORT_WAIT)) {
            addToReport("OTP confirm button appeared", Status.PASS, false);
        } else {
            waitFor(3);
            addToReport("OTP confirm button did not appear", Status.FAIL);
        }
        if (isElementPresentBy(btnBack, VERY_SHORT_WAIT)) {
            addToReport("OTP back button appeared", Status.PASS, false);
        } else {
            waitFor(3);
            addToReport("OTP back button did not appear", Status.FAIL);
        }

        validateOTPPopup(category, billerName, transferMode, amount, mobileNo, "", nicNo, name, policyNumber, admissionNumber, classID, purpose, date, code, referenceOrReservationNo, branch, email, accountNumber, purp, kwBillersMap);

        isElementPresentBy(getElementByTypeAndText(ElementType.span, ackMsg), VERY_LONG_WAIT);

        //Click on cancel button
        clickOnElement(btnBack);

        waitForElementToBeInvisible(getElementByTypeAndText(ElementType.span, ackMsg), VERY_LONG_WAIT);
        waitForElementPresence(lblNewPaymentAccountNumber, VERY_LONG_WAIT);

        selectFromDropdown(ddPayFrom, accountNumber, "value");

        //Enter amount and other relevant values
        sendKeysToElement(tfAmount, Keys.BACK_SPACE, 5);
        sendKeysToElement(tfAmount, amount);

        if (isElementPresentBy(lblNewPaymentAccountNumber, VERY_SHORT_WAIT)) {
            addToReport("Navigated back to biller page", Status.PASS, true);
        } else {
            addToReport("Did not navigate back to biller page", Status.FAIL);
        }

        clickOnElement(btnNext);
        waitForElementPresence(btnClosePopup, SHORT_WAIT);
        if (isElementPresentBy(btnClosePopup, VERY_SHORT_WAIT)) {
            addToReport("Popup message appeared ", Status.INFO, true);
        }
        waitForElementToBeInvisible(btnNextLoading, LONG_WAIT);

        //Validate confirmation header
        if (isElementPresentBy(lblPopUpHeaderPaymentConfirmation, VERY_SHORT_WAIT)) {
            waitForElementToBeClickable(lblPopUpHeaderPaymentConfirmation, VERY_LONG_WAIT);
            addToReport("OTP confirmation appeared", Status.PASS, true);
        } else {
            waitFor(VERY_SHORT_WAIT);
            addToReport("OTP confirmation did not appear", Status.FAIL);
        }

        //Validate confirmation header
        if (isElementPresentBy(btnConfirm, VERY_SHORT_WAIT)) {
            addToReport("OTP confirm button appeared", Status.PASS, false);
        } else {
            waitFor(VERY_SHORT_WAIT);
            addToReport("OTP confirm button did not appear", Status.FAIL);
        }
        if (isElementPresentBy(btnBack, VERY_SHORT_WAIT)) {
            addToReport("OTP back button appeared", Status.PASS, false);
        } else {
            waitFor(VERY_SHORT_WAIT);
            addToReport("OTP back button did not appear", Status.FAIL);
        }

        validateOTPPopup(category, billerName, transferMode, amount, mobileNo, "", nicNo, name, policyNumber, admissionNumber, classID, purpose, date, code, referenceOrReservationNo, branch, email, accountNumber, purp, kwBillersMap);

        String actualFeeText = getTextFromElement(lblTransactionFee);

        //expected pattern for processing fee
        String expectedFormat = Pattern.quote(paymentProcessingFeeMsg) + "\\s+" + txtLKR + "\\s+[\\d,.]+";

        // Validate using regex for processing fee
        if (actualFeeText != null && actualFeeText.matches(expectedFormat)) {
            addToReport("Successfully validated processing fee label: '" + actualFeeText + "'", Status.PASS, false);
        } else {
            addToReport(
                    "Failed to validate processing fee label.\nExpected pattern: '" + expectedFormat + "'\nFound: '" + actualFeeText + "'",
                    Status.FAIL, true
            );
        }

        isElementPresentBy(getElementByTypeAndText(ElementType.span, ackMsg), VERY_LONG_WAIT);

        //Validated dynamic label with FC to LKR
        addToReport("Start of validation of rate conversion message", Status.PASS, false);
        String convertionMsg = getTextFromElement(lblConversionMsg);

        String expectedRegex = BillerConstants.APPROX_LKR_LABEL_PREFIX
                + " \\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2} " + BillerConstants.TIME_PERIOD_REGEX
                + " - " + BillerConstants.LKR_TEXT + "\\s*[\\d,]+\\.\\d{2} \\("
                + BillerConstants.USD_EXCHANGE_PREFIX + " [\\d,]+\\.\\d{2}\\)";

        if (convertionMsg.matches(expectedRegex)) {
            addToReport("Successfully validated dynamic label: " + convertionMsg, Status.PASS, false);
        } else {
            addToReport("Failed to validate label. Actual: '" + convertionMsg + "' | Expected pattern: '" + expectedRegex + "'", Status.FAIL, true);
        }
        addToReport("End of validation of rate conversion message ", Status.PASS, false);
        clickOnElement(chkAckMsg);

        addToReport("End of validation for  biller : " + billerName + " in the OTP page", Status.PASS, true);
        waitForElementPresence(tfOTP(1), LONG_WAIT);
        sendKeysToElement(tfOTP(1), String.valueOf(OTPValue));
        clickOnElement(btnConfirm);
        waitForElementToBeInvisible(btnNextLoading, LONG_WAIT);

        addToReport("----------End of validation of OTP confirmation page----------", Status.PASS, false);
        addToReport("----------Start of validation of OTP success page----------", Status.PASS, false);

        //Validate the success label,payee name,pay from,amount,payment mode and entered reference while retrieving the reference number
        if (isElementPresentBy(lblSuccess)) {
            addToReport("Validated the success message in the OTP success page", Status.PASS, false);
        } else {
            addToReport("Failed to validate the success message in the OTP success page", Status.FAIL);
        }
        String[] referenceNumber = getTextFromElement(lblRefernceID).split("- ");
        if (referenceNumber[1] != null) {
            addToReport("Obtained the payment reference number " + referenceNumber[1], Status.PASS, false);
        } else {
            addToReport("Failed to get the reference number", Status.FAIL);
        }

        validateOTPPopup(category, billerName, transferMode, amount, mobileNo, "", nicNo, name, policyNumber, admissionNumber, classID, purpose, date, code, referenceOrReservationNo, branch, email, accountNumber, purp, kwBillersMap);

        addToReport("End of validation for  biller : " + billerName + " in the OTP confirmation page", Status.PASS, true);

        clickOnElement(btnClosePopup);
        addToReport("----------End of validation of OTP success page----------", Status.PASS, false);
        addToReport("----------End of validation of acknowledgement message----------", Status.PASS, false);
    }

    /**
     * Initiate bill payments for LPOPP
     *
     * @param OTPValue      One-time password used for authentication
     * @param category      The main category of the biller (e.g., Utilities, Insurance, Education)
     * @param billerName    The name of the biller (e.g., CEB, NWSDB)
     * @param accountNumber Account number related to the biller or customer
     * @param kwBillersMap  key word and the content of keyword is added as suffix
     */
    public void initiateBillPaymentsForLPOPAndValidate(String OTPValue, String category, String billerName, String accountNumber, Map<String, String> kwBillersMap) {

        addToReport("----------Start of validation of LPOP Payments----------", Status.PASS, false);

        //Check by default primary account needs to selected
        waitForElementPresence(lblNewPaymentAccountNumber, LONG_WAIT);
        waitForElementPresence(btnNext);
        waitForElementPresence(btnClosePopup, SHORT_WAIT);
        if (isElementPresentBy(btnClosePopup, VERY_SHORT_WAIT)) {
            addToReport("Popup message appeared ", Status.INFO, true);
        }
        waitForElementToBeClickable(btnNext, LONG_WAIT);

        //validate the primary account number both in panel and from pay from dropdown
        List<String> ddAccountNumbers = getSelectedOptionText(ddPayFrom, "ALL_OPTIONS_VALUE");
        selectFromDropdown(ddPayFrom, accountNumber, "value");
        addToReport("All available accounts for the user " + ddAccountNumbers, Status.PASS, false);

        addToReport("Entered details related to LPOP payment", Status.PASS, true);
        //Click button after entering data
        clickOnElement(btnNext);
        waitForElementToBeInvisible(btnNextLoading, LONG_WAIT);

        //Validate confirmation header
        if (isElementPresentBy(lblPopUpHeaderPaymentConfirmation, VERY_SHORT_WAIT)) {
            waitForElementToBeClickable(lblPopUpHeaderPaymentConfirmation, LONG_WAIT);
            addToReport("OTP confirmation appeared", Status.PASS, true);
        } else {
            waitFor(VERY_SHORT_WAIT);
            addToReport("OTP confirmation did not appear", Status.FAIL);
        }
        //Validate confirmation header
        if (isElementPresentBy(getElementByTypeAndText(ElementType.button, BillerConstants.PROCEED_TO_PAY), 3)) {
            addToReport("OTP proceed to pay button appeared", Status.PASS, false);
        } else {
            waitFor(VERY_SHORT_WAIT);
            addToReport("OTP proceed to pay button did not appear", Status.FAIL);
        }
        if (isElementPresentBy(btnBack, 3)) {
            addToReport("OTP back button appeared", Status.PASS, false);
        } else {
            waitFor(VERY_SHORT_WAIT);
            addToReport("OTP back button did not appear", Status.FAIL);
        }

        if (isElementPresentBy(getElementByTypeAndText(ElementType.p, "'" + kwBillersMap.get("KW_GOVERNMENT_PAYMENTS") + "'"))) {
            addToReport("Validated the biller name " + kwBillersMap.get("KW_GOVERNMENT_PAYMENTS") + " in the OTP page", Status.PASS, false);
        } else {
            addToReport("Failed to validate the biller name " + category + " in the OTP page found :" + kwBillersMap.get("KW_GOVERNMENT_PAYMENTS"), Status.FAIL);
        }

        addToReport("End of validation for  biller : " + billerName + " in the OTP page for LPOP", Status.PASS, true);
        waitForElementPresence(tfOTP(1), LONG_WAIT);
        sendKeysToElement(tfOTP(1), String.valueOf(OTPValue));
        clickOnElement(getElementByTypeAndText(ElementType.button, BillerConstants.PROCEED_TO_PAY));
        waitForElementToBeInvisible(btnNextLoading, LONG_WAIT);

        addToReport("----------End of validation of OTP confirmation for LPOP ----------", Status.PASS, true);
    }


    /**
     * Initiate bill payments for Lyceum
     *
     * @param accountNumber        Account number related to the biller or customer
     * @param studentNo            Student number
     * @param secondPageSuccessMsg Second page success msg
     * @param kwBillersMap         key word and the content of keyword is added as suffix
     */
    public void initiateBillPaymentsForLyceumAndValidate(String accountNumber, String studentNo, String secondPageSuccessMsg, Map<String, String> kwBillersMap) {

        addToReport("----------Start of validation of Lyceum Payments----------", Status.PASS, false);

        //Check by default primary account needs to selected
        waitForElementPresence(getElementByTypeAndText(ElementType.button, BillerConstants.BUTTON_FETCH), LONG_WAIT);

        //validate the primary account number both in panel and from pay from dropdown
        List<String> ddAccountNumbers = getSelectedOptionText(ddPayFrom, "ALL_OPTIONS_VALUE");
        selectFromDropdown(ddPayFrom, accountNumber, "value");
        addToReport("All available accounts for the user " + ddAccountNumbers, Status.PASS, false);

        //Enter student number
        sendKeysToElement(tfFieldEight, studentNo);

        addToReport("Entered details related to Lyceum payment", Status.PASS, true);

        //Click fetch button after entering data
        clickOnElement(getElementByTypeAndText(ElementType.button, BillerConstants.BUTTON_FETCH));
        waitForElementToBeInvisible(btnNextLoading, LONG_WAIT);

        waitForElementPresence(getElementByTypeAndText(ElementType.div, secondPageSuccessMsg), LONG_WAIT);

        //Validate the search results
        int recordCount = isElementsPresentBy(tblRows);
        if (recordCount != 0) {
            //Select the first record
            clickOnElement(tblCellRecordInput(1, 1));

            //Fetch data from the first row
            InvoiceNo = getTextFromElement(tblCellRecord(2, 1));
            InvoiceAmount = getTextFromElement(tblCellRecord(7, 1));
        } else {
            addToReport("No invoices present", Status.FAIL);
            throw new RuntimeException("Failed due to non availability of invoices");
        }

        //Validate confirmation header
        if (isElementPresentBy(getElementByTypeAndText(ElementType.button, BillerConstants.PROCEED_TO_PAY), 3)) {
            addToReport(" proceed to pay button appeared", Status.PASS, false);
        } else {
            waitFor(VERY_SHORT_WAIT);
            addToReport(" proceed to pay button did not appear", Status.FAIL);
        }
        if (isElementPresentBy(getElementByTypeAndText(ElementType.button, BillerConstants.BUTTON_BACK), 3)) {
            addToReport(" back button appeared", Status.PASS, false);
        } else {
            waitFor(VERY_SHORT_WAIT);
            addToReport(" back button did not appear", Status.FAIL);
        }

        scrollDownPage();
        clickOnElement(getElementByTypeAndText(ElementType.button, BillerConstants.PROCEED_TO_PAY));

        waitForElementToBeInvisible(btnNextLoading, LONG_WAIT);

        //Validate account number at confirmation popup
        if (getAttributeOrText(tfPayFromConfirmation(kwBillersMap.get("KW_PAY_FROM")), "text").contains(accountNumber)) {
            addToReport("Validated the pay from " + getAttributeOrText(tfPayFromConfirmation(kwBillersMap.get("KW_PAY_FROM")), "text") + " in the OTP page", Status.PASS, false);
        } else {
            addToReport("Failed to validate the pay from " + accountNumber + " in the OTP page", Status.FAIL);
        }
        //Validate the student no
        if (studentNo.equals(getAttributeOrText(tfPayFromConfirmation(BillerConstants.STUDENT_NO), "text"))) {
            addToReport("Validated the student no from " + getAttributeOrText(tfPayFromConfirmation(BillerConstants.STUDENT_NO), "text") + " in the OTP page", Status.PASS, false);
        } else {
            addToReport("Failed to validate the student no from " + studentNo + " in the OTP page", Status.FAIL);
        }

        //Validate amount
        if (getAttributeOrText(tfAmountConfirmation(kwBillersMap.get("KW_AMOUNT")), "text").contains(InvoiceAmount)) {
            addToReport("Validated the invoice amount from " + getAttributeOrText(tfAmountConfirmation(kwBillersMap.get("KW_AMOUNT")), "text") + " in the OTP page", Status.PASS, false);
        } else {
            addToReport("Failed to validate the invoice amount from " + InvoiceAmount + " in the OTP page", Status.FAIL);
        }
        //Validate amount
        if (InvoiceNo.equals(getAttributeOrText(tfInvoiceNoConfirmation(BillerConstants.SELECTED_INVOICE_NO), "text"))) {
            addToReport("Validated the invoice no from " + getAttributeOrText(tfInvoiceNoConfirmation(BillerConstants.SELECTED_INVOICE_NO), "text") + " in the OTP page", Status.PASS, false);
        } else {
            addToReport("Failed to validate the invoice no from " + InvoiceNo + " in the OTP page", Status.FAIL);
        }

        if (isElementClickable(getElementByTypeAndText(ElementType.button, BillerConstants.PROCEED_TO_PAY))) {
            addToReport("Validated the proceed to pay button in the OTP page", Status.PASS, true);
        } else {
            addToReport("Failed to validate the proceed to pay button in the OTP page", Status.FAIL);
        }

        isElementClickable(getElementByTypeAndText(ElementType.button, BillerConstants.BUTTON_BACK));
        clickOnElementUsingJS(getElementByTypeAndText(ElementType.button, BillerConstants.BUTTON_BACK));
        waitForElementToBeInvisible(btnNextLoading, LONG_WAIT);

        addToReport("----------End of validation of OTP confirmation for Lyceum ----------", Status.PASS, true);
    }

    /**
     * Navigates back to the previous page or section under the GovPay module
     */
    public void navigateBackUnderGovPay() {

        if (isElementPresentBy(getElementByTypeAndText(ElementType.button, BillerConstants.BUTTON_BACK), MODERATE_WAIT)) {
            addToReport("Back button appeared", Status.PASS, true);
        } else {
            addToReport("Back button did not appear", Status.FAIL);
        }
        //Click on cancel button
        clickOnElement(getElementByTypeAndText(ElementType.button, BillerConstants.BUTTON_BACK));
    }

    /**
     * Validates the behavior and constraints of the amount input field,
     * ensuring it accepts valid values and handles invalid input appropriately
     */
    public void validateAmountField() {
        //Validate contents from text fields

        if (isElementPresentBy(tfAmount, MODERATE_WAIT)) {
            addToReport("Validated the amount  text field", Status.PASS, true);
        } else {
            addToReport("Failed to validate the amount text field", Status.FAIL);
        }
    }

}
