/*
 *   @author - salman R
 */
package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.CommonUtils;
import utils.constants.BillerConstants;
import utils.constants.MyAccountsConstants;

import java.io.File;
import java.util.List;
import java.util.Map;

import static utils.Drivers.*;

public class MyAccountsPage extends BasePage {


    String repoMaturityDate, repoMaturityValue, tbillYield, tbillFaceValue, tbillMaturiyDate, currencyAndBal, currentTab, repoInterestRate, investmentDate, NoOfDays, accountNumber, aHAccountNo = "";
    String[] cardCount = new String[]{""};
    int recordCount, rowCount = 0;

    public MyAccountsPage(WebDriver driver) {
        super(driver);
    }

    public enum ElementType {
        button, label, span, div, h2
    }

    private static final By lblAccountListLoading = By.xpath("//div[contains(@class,'dark:bg-gray')]");
    private static final By tblRows = By.xpath("//table//tbody/tr");
    private static final By tblInwardChqRows = By.xpath("//table//tbody[contains(@class,'text-black')]/tr");
    private static final By tfSearch = By.xpath("//input[@placeholder='Search']");
    private static final By btnSearch = By.xpath("//div[contains(@class,'absolute')]/img");
    private static final By lblLoadingIcon = By.xpath("//div[contains(@class,'AccountsCards_loader')]");
    private static final By icnAccounts = By.xpath("//div[contains(@class,'flex flex-col items-center')]/div[3]/div[1]");
    private static final By lblAccountNumber = By.xpath("//span[contains(@class,'flex flex-col justify-center items-center')]");
    private static final By lblPawningAccountNumber = By.xpath("//div[contains(@class,'flex flex-col p')]/span");
    private static final By btnNextArrow = By.xpath("//div[contains(@class,'flex gap-2')]/div[2]");
    private static final By btnPreviousArrow = By.xpath("//div[contains(@class,'flex gap-2 mt')]/div[1]");
    private static final By icnTileLoading = By.xpath("//div[contains(@class,'animate-pulse')]");
    private static final By lblCurrencyAndAvailableBalance = By.xpath("//div/span[@class='text-black']");
    private static final By tblTransactionRows = By.xpath("//table[contains(@class,'min-w-full')]//tbody//tr");
    private static final By btnEditNickName = By.xpath("//tr[contains(@class, 'bg-orange-300')]//img");
    private static final By tfNickName = By.xpath("//input[@name='nickName']");
    private static final By btnClosePopup = By.xpath("//button[@aria-label='close']");
    private static final By ddCollectingBranch = By.xpath("//select[@name='collectingBranch']");
    private static final By ddChequeBook = By.xpath("//select[@name='book']");
    private static final By chkChequeActions = By.xpath("//input[@class='sr-only']/parent::label/div");
    private static final By lblUnusedCheques = By.xpath("//span[contains(@class,'text-black') and contains(text(),'Unused Cheques')]");
    private static final By lblStoppedCheques = By.xpath("//span[contains(@class,'text-black') and contains(text(),'Stopped Cheques')]");
    private static final By ddNoOfLeaves = By.xpath("//select[@name='noOfLeaves']");
    private static final By ddNoOfChequeBook = By.xpath("//select[@name='noOfChequeBook']");
    private static final By ddRemarkStopCheque = By.xpath("//select[@name='remark']");
    private static final By ddAdvancedSearchMonth = By.xpath("//span[@class='rdrMonthPicker']/select");
    private static final By ddAdvancedSearchYear = By.xpath("//span[@class='rdrYearPicker']/select");
    private static final By ddTransactionType = By.xpath("//select[@id='status']");
    private static final By imgMasterCardLogo = By.xpath("//img[contains(@srcset,'MasterCardLogo')]");
    private static final By imgVisaCardLogo = By.xpath("//img[contains(@srcset,'VisaLogo')]");
    private static final By lblInactiveCardStatus = By.xpath("//div[@class='flex gap-1']/div[1]");
    private static final By btnConfirm = By.xpath("//div[contains(normalize-space(text()),'Confirm')]");
    private static final By btnNextLoading = By.xpath("//div[contains(@class,'customloader')]");
    private static final By btnNext = By.xpath("//button[normalize-space()='Next']");
    private static final By btnStopCheque = By.xpath("//div[contains(normalize-space(text()), 'Stop Cheque') and contains(@class, 'flex')]");

    public static By lblNoDataFound(String text) {
        return By.xpath("//div[@class='gap-2']//span[contains(text(),'" + text + "')]");
    }

    public static By lblNickName(String text) {
        return By.xpath("//td[contains(text(),'" + text + "')]");
    }

    public static By lnkAdvancedSearchDay(String day) {
        return By.xpath("//span[@class='rdrDayNumber']/span[contains(text(),'" + day + "')]");
    }

    public static By lblRepoAdditionalDetails(String text) {
        return By.xpath("//li[contains(text(),'" + text + "')]/span");
    }

    public static By lblSavingsAccountSummaryDetails(String text) {
        return By.xpath("//span[contains(text(),'" + text + "')]/parent::li/span[2]");
    }

    public static By lblAccountSummaryDetails(String text) {
        return By.xpath("//li[contains(text(),'" + text + "')]/span");
    }

    public static By lblCreditCardDetails(String text) {
        return By.xpath("//div[contains(text(),'" + text + "')]/parent::div/div[2]");
    }

    public static By lblDatesAndRates(String text) {
        return By.xpath("//span[contains(text(),'" + text + "')]/parent::div/span[1]");
    }

    public static By lblAccountHistoryAccountNo(String text) {
        return By.xpath("//span[contains(text(),'" + text + "')]/parent::div/span[2]");
    }

    public static By lblAccountNumberByHeader(String headerText) {
        return By.xpath("//h1[contains(text(),'" + headerText + "')]");
    }

    private static By getHeaderByName(String headerText) {
        return By.xpath("//table[contains(@class,'min-w-full')]//th[normalize-space(text())='" + headerText + "']");
    }

    private static By getElementByTypeAndText(MyAccountsPage.ElementType type, String text) {
        return By.xpath("//" + type.name() + "[contains(normalize-space(text()), '" + text + "')]");
    }

    private static By getAdvanceSearchFields(MyAccountsPage.ElementType type, String text) {
        return By.xpath("//" + type.name() + "[contains(normalize-space(text()), '" + text + "')]/parent::div/input");
    }

    private static By btnCloseFilterIcon(String filterText) {
        return By.xpath("//div[normalize-space()='" + filterText + "']/img");
    }

    private static By tblCellRecord(int col, int row) {
        return By.xpath("(//table//tr/td[" + col + "])[" + row + "]");
    }

    private static By tblCellRecordStopCheques(int col, int row) {
        return By.xpath("(//table[contains(@class,'border')]//tr/td[" + col + "])[" + row + "]");
    }

    private static By tblCellRecordStopCheques(int col) {
        return By.xpath("//table[contains(@class,'border')]//tr/td[" + col + "]");
    }

    private static By tblBtn(int row, String text) {
        return By.xpath("(//table[contains(@class,'border')]//tr/td[3])[" + row + "]//span[text()='" + text + "']");
    }

    private static By tabValue(String tabName) {
        return By.xpath("//div[contains(@class,'AccountsCards_scroll')]/div[contains(text(),'" + tabName + "')]");
    }

    private static By lblAccountHistory(String accountNumber) {
        return By.xpath("//span[contains(text(),'" + accountNumber + "')]");
    }

    private static By lblHighlightedAccountNo(String accountNumber) {
        return By.xpath("//tr[contains(@class, 'bg-orange-300') and translate(td[1],' ','')='" + accountNumber + "']");
    }

    private static By lblHighlightedListContent(String content) {
        return By.xpath("//tr[contains(@class, 'bg-orange-300')]/td[text()='" + content + "']");
    }

    private static By lblLoanNoDataFound(String content) {
        return By.xpath("//div[@class='h-full w-full']//span[contains(text(),'" + content + "')]");
    }

    private static By lblCreditCardAvailableBalance(String text) {
        return By.xpath("//div[contains(text(),'" + text + "')]/following-sibling::div/span[contains(@class,'text-green')]");
    }

    private static By lblCreditCardNumber(String text) {
        return By.xpath("//div[contains(text(),'" + text + "')]/parent::div//span[contains(@class,'flex flex-col')]");
    }

    private static By lblCreditCardCustAccNumber(String text) {
        return By.xpath("//span[contains(text(),'" + text + "')]/parent::div/span[1]");
    }

    private static By popUpPDFDownload(String msg) {
        return By.xpath("//div[text()='" + msg + "']");
    }

    private static By tfChequeBookRequest(String fieldHeader) {
        return By.xpath("//span[contains(normalize-space(),\"" + fieldHeader + "\")]/parent::div/input");
    }

    private static By tfRCBInput(String type) {
        return By.xpath("//span[contains(normalize-space(),\"" + type + "\")]/parent::div/input[@disabled]");
    }

    private static By tfRCBSelect(String type) {
        return By.xpath("//span[contains(normalize-space(),\"" + type + "\")]/parent::div/select[@disabled]");
    }

    private static By tfOTP(int Index) {
        return By.xpath("//input[contains(@class,'otp-box')][" + Index + "]");
    }

    /**
     * Select Tab
     *
     * @param mainTab - Main tab
     */
    public void selectTab(String mainTab) {
        try {
            //Select main tab
            waitForElementToBeInvisible(lblAccountListLoading, LONG_WAIT);
            waitForElementPresence(tabValue(mainTab));
            waitForElementToBeClickable(tabValue(mainTab), LONG_WAIT);
            clickOnElement(tabValue(mainTab));
            waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);
            addToReport("Main tab : " + mainTab + " is selected", Status.PASS, true);

        } catch (Exception e) {
            addToReport("Failed to select tab " + mainTab, Status.FAIL);
            throw new RuntimeException("Error - Failed to select tab " + e.getMessage(), e);
        }
    }

    /**
     * Search and select account list for validation
     *
     * @param accountNo - Account number
     */
    public void searchAndSelectAccountList(String accountNo) {
        try {
            //Select main tab
            waitForElementToBeInvisible(lblAccountListLoading, LONG_WAIT);
            sendKeysToElement(tfSearch, accountNo);
            clickOnElement(btnSearch);
            waitForElementToBeInvisible(lblAccountListLoading, LONG_WAIT);

            //Validate the search results
            int recordCount = isElementsPresentBy(tblRows);
            if (recordCount == 1) {

                //Validate cell record
                if (getTextFromElement(tblCellRecord(1, 1)).equals(accountNo)) {
                    addToReport(" Account number " + accountNo + " has successfully returned on search", Status.PASS, true);
                    clickOnElement(tblCellRecord(1, 1));
                    waitForElementToBeInvisible(lblAccountListLoading, LONG_WAIT);
                    if (isElementPresentBy(lblAccountHistory(accountNo))) {
                        addToReport(" Account History for account  " + accountNo + " has successfully returned on search", Status.PASS, true);
                    } else {
                        addToReport(" Account History for account  " + accountNo + " has not been successfully returned on search", Status.FAIL, true);
                        throw new RuntimeException("Error - Failed to get to account history");
                    }
                } else {
                    addToReport(" Account number  " + accountNo + " has not been successfully returned on search", Status.FAIL, true);
                    throw new RuntimeException("Error - Failed to get to account number");
                }

            } else {
                addToReport(" Account number " + accountNo + " has not successfully returned on search", Status.FAIL, true);
            }
        } catch (Exception e) {
            addToReport("Failed to get account" + accountNo, Status.FAIL);
            throw new RuntimeException("Error - Failed to get to account " + e.getMessage(), e);
        }
    }

    /**
     * Validate value under account history
     *
     * @param referenceNo - Reference number
     */
    public void searchReferenceInAccountsHistory(String referenceNo) {
        try {
            if (referenceNo != null) {
                //Search for valid description form accounts history
                waitForElementToBeInvisible(lblAccountListLoading, LONG_WAIT);
                waitForElementPresence(tfSearch);
                sendKeysToElement(tfSearch, referenceNo);
                clickOnElement(btnSearch);
                waitForElementToBeInvisible(lblAccountListLoading, LONG_WAIT);
                //Validate the search results
                int recordCount = isElementsPresentBy(tblRows);
                if (recordCount == 1) {
                    if (getTextFromElement(tblCellRecord(2, 1)).contains(referenceNo)) {
                        addToReport("Reference No " + referenceNo + " has returned successfully", Status.PASS, true);
                    } else {
                        addToReport("Reference No " + referenceNo + " has not returned successfully", Status.FAIL, true);
                    }
                } else {
                    addToReport("Multiple values have loaded for the search of reference number :" + referenceNo, Status.FAIL, true);
                }
            } else {
                addToReport("Invalid reference number ", Status.FAIL, true);
            }

        } catch (Exception e) {
            addToReport("Failed to search reference " + referenceNo, Status.FAIL);
            throw new RuntimeException("Error - Failed to select tab " + e.getMessage(), e);
        }
    }

    /**
     * select tab and validate it's relevant tile
     *
     * @param tabName    the name of the tab to be selected
     * @param tileHeader the expected header of the tile shown after selecting the tab
     */
    public void selectTabAndValidate(String tabName, String tileHeader) {

        waitForElementToBeInvisible(lblAccountListLoading, LONG_WAIT);
        waitForElementToBeClickable(tfSearch, LONG_WAIT);

        try {
            waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
            //Select tab
            selectTab(tabName);
            waitForElementToBeInvisible(lblAccountListLoading, LONG_WAIT);
            waitForElementToBeClickable(getElementByTypeAndText(ElementType.span, tileHeader), LONG_WAIT);
            //Validate the tile header
            if (isElementPresentBy(getElementByTypeAndText(ElementType.span, tileHeader))) {
                addToReport("My Account page sub title : " + tileHeader + " is visible ", Status.PASS, true);
            } else {
                addToReport("My Account page sub title : " + tileHeader + " is visible ", Status.FAIL, true);
                throw new RuntimeException("Error - Failed to get to account history");
            }

        } catch (Exception e) {
            addToReport("Failed to get my account content", Status.FAIL);
            throw new RuntimeException("Error - Failed to get to my account content" + e.getMessage(), e);
        }
    }


    /**
     * select tab and validate it's relevant tile
     *
     * @param tabName    Table Name
     * @param tileHeader Tile header
     */
    public void navigateToAccountProductTypeAndValidate(String[] tabName, String[] tileHeader) {

        addToReport("----------Start of validation of that when user click on 'My Accounts' item in top navigation menu all account categories and other product lists displayed----------", Status.PASS, false);

        waitForElementToBeInvisible(lblLoadingIcon, VERY_LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);
        waitForElementToBeInvisible(lblAccountListLoading, VERY_LONG_WAIT);
        addToReport("----------End of validation of that when user click on 'My Accounts' item in top navigation menu all account categories and other product lists displayed----------", Status.PASS, true);
        for (int inc = 0; tabName.length > inc; inc++) {
            //Validate the tab and relative header
            addToReport("----------Start of validation of that when user selects an account, category or a other product list of all available records is displaying to the user ----------", Status.PASS, false);
            selectTabAndValidate(tabName[inc], tileHeader[inc]);

            //Validate the selected tile and its relevant data loaded at list
            waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
            waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);

            rowCount = isElementsPresentBy(icnAccounts, SHORT_WAIT);
            if (rowCount > 0) {
                waitForElementToBeClickable(icnAccounts, LONG_WAIT);

                //Obtain pagination value
                cardCount = CommonUtils.splitText(getAttributeOrText(icnAccounts, "text"), "/");
                //Obtain the accounts record count
                recordCount = Integer.parseInt(cardCount[1]);

            } else {
                recordCount = 1;
            }
            addToReport("Number of records under : " + tabName[inc] + " is  " + recordCount, Status.PASS, false);
            addToReport("----------End of validation of that when user selects an account, category or a other product list of all available records is displaying to the user ----------", Status.PASS, true);
            if (recordCount != 0) {
                for (int incr = 0; incr < recordCount; incr++) {
                    addToReport("----------Start of validation of that on click of a record user is navigating detail view of that specific record ----------", Status.PASS, false);
                    currencyAndBal = getTextFromElement(lblCurrencyAndAvailableBalance);
                    currentTab = tabName[inc];

                    switch (currentTab) {
                        case MyAccountsConstants.TAB_ACCOUNTS:
                            waitForElementToBeInvisible(lblAccountListLoading, VERY_LONG_WAIT);
                            waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
                            waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);
                            waitForElementToBeClickable(lblAccountNumber, LONG_WAIT);

                            //Obtain account number and validate
                            accountNumber = getAttributeOrText(lblAccountNumber, "text");
                            accountNumber = accountNumber.replace(MyAccountsConstants.CURRENT_OUTSTANDING, "").trim().replaceAll("\\s+", "");
                            if (isElementPresentBy(lblHighlightedAccountNo(accountNumber))) {
                                addToReport("Successfully validated account number : '" + accountNumber + "' under the tab " + tabName[inc], Status.PASS, false);
                            } else {
                                addToReport("Failed to validated account number : '" + accountNumber + "' under the tab " + tabName[inc], Status.FAIL, true);
                            }
                            if (isElementPresentBy(lblHighlightedListContent(currencyAndBal))) {
                                addToReport("Successfully validated currency and balance : '" + currencyAndBal + "' under the tab " + tabName[inc], Status.PASS, true);
                            } else {
                                addToReport("Failed to validated currency and balance '" + currencyAndBal + "' under the tab " + tabName[inc], Status.FAIL, true);
                            }
                            addToReport("----------End of validation of that on click of a record user is navigating detail view of that specific record ----------", Status.PASS, false);
                            addToReport("----------Start of validation of the content of operative Account list and functional behaviour ----------", Status.PASS, false);

                            //Validate  account list table headers
                            for (String header : MyAccountsConstants.SAVINGS_ACCOUNTS_LIST_TABLE_HEADERS) {

                                if (isElementPresentBy(getHeaderByName(header))) {
                                    addToReport("Table header '" + header + "' is present under tab '" + tabName[inc] + "'", Status.PASS, false);
                                } else {
                                    addToReport("Missing table header '" + header + "' under tab '" + tabName[inc] + "'", Status.FAIL, true);
                                }
                            }

                            //Select the account
                            clickOnElement(lblHighlightedAccountNo(accountNumber));
                            waitForElementToBeClickable(lblAccountHistoryAccountNo(MyAccountsConstants.ACCOUNT_HISTORY), VERY_LONG_WAIT);
                            waitForElementToBeInvisible(lblLoadingIcon, VERY_LONG_WAIT);

                            //Remove this once finacle dates are synchronized
                            advanceSearchByDate(BillerConstants.NUMBER_TWENTY_TWENTY_FOUR, BillerConstants.JULY, BillerConstants.NUMBER_TWENTY_ONE, BillerConstants.NUMBER_TWENTY_ONE);

                            aHAccountNo = getTextFromElement(lblAccountHistoryAccountNo(MyAccountsConstants.ACCOUNT_HISTORY));
                            if (aHAccountNo.equals(accountNumber)) {
                                addToReport("Successfully validated account number : '" + accountNumber + "' under the account history", Status.PASS, true);
                            } else {
                                addToReport("Failed to validated account number : '" + accountNumber + "' under the account history ", Status.FAIL, true);
                            }

                            //Check if data found
                            if (isElementsPresentBy(getElementByTypeAndText(ElementType.span, MyAccountsConstants.NO_DATA_FOUND_LC), VERY_SHORT_WAIT) > 1) {
                                addToReport(" No Data found under the deposit account " + aHAccountNo, Status.INFO, true);
                            } else {
                                //Validate selected account table headers
                                for (String header : MyAccountsConstants.ACCOUNT_TABLE_HEADERS) {

                                    if (isElementPresentBy(getHeaderByName(header))) {
                                        addToReport("Table header '" + header + "' is present under tab '" + tabName[inc] + " for account number " + accountNumber, Status.PASS, false);
                                    } else if (isElementPresentBy(getHeaderByName(MyAccountsConstants.AMOUNT_USD)) || isElementPresentBy(getHeaderByName(MyAccountsConstants.BALANCE_USD))) {
                                        addToReport("Table header '" + MyAccountsConstants.AMOUNT_USD + "' or '" + MyAccountsConstants.BALANCE_USD + "' is present under tab '" + tabName[inc] + " for account number " + accountNumber, Status.PASS, false);
                                    } else {
                                        addToReport("Missing table header '" + header + "' under tab '" + tabName[inc] + " for account number " + accountNumber, Status.FAIL, true);
                                    }
                                }
                                //Validate Table Rows Loaded
                                rowCount = isElementsPresentBy(tblTransactionRows);
                                if (rowCount > 0) {
                                    addToReport("Table loaded with " + rowCount + " record(s) under tab '" + tabName[inc] + "'", Status.PASS, true);
                                } else {
                                    addToReport("No records found in table under tab '" + tabName[inc] + "'", Status.FAIL, true);
                                }
                            }

                            scrollPageToTop();

                            //Click on view list
                            waitForElementToBeClickable(getElementByTypeAndText(ElementType.span, MyAccountsConstants.VIEW_LIST), VERY_LONG_WAIT);
                            clickOnElement(getElementByTypeAndText(ElementType.span, MyAccountsConstants.VIEW_LIST));
                            addToReport("----------End of validation of the content of operative Account list and functional behaviour ----------", Status.PASS, true);

                            //Navigate to next account
                            clickOnElement(btnNextArrow);

                            //WaitForElementPresence(lblLoadingIcon);
                            waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
                            break;

                        case MyAccountsConstants.TAB_DEPOSITS:
                            addToReport("----------Start of validation of the content of fixed deposits list and the functional behaviour ----------", Status.PASS, false);

                            waitForElementToBeInvisible(lblAccountListLoading, VERY_LONG_WAIT);
                            waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
                            waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);
                            waitForElementToBeClickable(lblAccountNumber, LONG_WAIT);
                            //Obtain account number and validate
                            accountNumber = getAttributeOrText(lblAccountNumber, "text");
                            accountNumber = accountNumber.replace(MyAccountsConstants.CURRENT_OUTSTANDING, "").trim().replaceAll("\\s+", "");
                            if (isElementPresentBy(lblHighlightedAccountNo(accountNumber))) {
                                addToReport("Successfully validated account number : '" + accountNumber + "' under the tab " + tabName[inc], Status.PASS, false);
                            } else {
                                addToReport("Failed to validated account number : '" + accountNumber + "' under the tab " + tabName[inc], Status.FAIL, true);
                            }
                            if (isElementPresentBy(lblHighlightedListContent(currencyAndBal))) {
                                addToReport("Successfully validated currency and balance : '" + currencyAndBal + "' under the tab " + tabName[inc], Status.PASS, true);
                            } else {
                                addToReport("Failed to validated currency and balance '" + currencyAndBal + "' under the tab " + tabName[inc], Status.FAIL, true);
                            }
                            //Validate Table Headers
                            for (String header : MyAccountsConstants.DEPOSITS_TABLE_HEADERS) {
                                if (isElementPresentBy(getHeaderByName(header))) {
                                    addToReport("Table header '" + header + "' is present under tab '" + tabName[inc] + "'", Status.PASS, false);
                                } else {
                                    addToReport("Missing table header '" + header + "' under tab '" + tabName[inc] + "'", Status.FAIL, true);
                                }
                            }
                            clickOnElement(lblHighlightedAccountNo(accountNumber));
                            waitForElementToBeClickable(lblAccountHistoryAccountNo(MyAccountsConstants.RENEWAL_HISTORY), VERY_LONG_WAIT);
                            waitForElementToBeInvisible(lblLoadingIcon, VERY_LONG_WAIT);
                            waitForElementToBeInvisible(lblAccountListLoading, VERY_LONG_WAIT);

                            aHAccountNo = getTextFromElement(lblAccountHistoryAccountNo(MyAccountsConstants.RENEWAL_HISTORY));
                            if (aHAccountNo.equals(accountNumber)) {
                                addToReport("Successfully validated account number : '" + accountNumber + "' under the account history", Status.PASS, true);
                            } else {
                                addToReport("Failed to validated account number : '" + accountNumber + "' under the account history ", Status.FAIL, true);
                            }

                            //Check if data found
                            if (isElementPresentBy(lblNoDataFound(MyAccountsConstants.NO_DATA_FOUND))) {
                                addToReport(" No Data found under the deposit account " + aHAccountNo, Status.INFO, true);
                            } else {
                                //Validate renewal history Table Headers
                                for (String header : MyAccountsConstants.DEPOSITS_RENEWAL_HISTORY_TABLE_HEADERS) {
                                    if (isElementPresentBy(getHeaderByName(header))) {
                                        addToReport("Table header '" + header + "' is present under tab '" + tabName[inc] + "'", Status.PASS, false);
                                    } else if (isElementPresentBy(getHeaderByName(MyAccountsConstants.AMOUNT_USD)) || isElementPresentBy(getHeaderByName(MyAccountsConstants.ACCOUNT_BALANCE_USD))) {
                                        addToReport("Table header '" + MyAccountsConstants.AMOUNT_USD + "' or '" + MyAccountsConstants.ACCOUNT_BALANCE_USD + "' is present under tab '" + tabName[inc] + " for account number " + accountNumber, Status.PASS, false);
                                    } else {
                                        addToReport("Missing table header '" + header + "' under tab '" + tabName[inc] + "'", Status.FAIL, true);
                                    }
                                }
                                //Validate Table Rows Loaded
                                rowCount = isElementsPresentBy(tblTransactionRows);
                                if (rowCount > 0) {
                                    addToReport("Table loaded with " + rowCount + " record(s) under tab '" + tabName[inc] + "'", Status.PASS, true);
                                } else {
                                    addToReport("No records found in table under tab '" + tabName[inc] + "'", Status.FAIL, true);
                                }

                            }
                            scrollPageToTop();
                            waitForElementToBeClickable(getElementByTypeAndText(ElementType.span, MyAccountsConstants.VIEW_LIST), VERY_LONG_WAIT);
                            clickOnElement(getElementByTypeAndText(ElementType.span, MyAccountsConstants.VIEW_LIST));

                            addToReport("----------End of validation of the content of fixed deposits list and the functional behaviour----------", Status.PASS, true);
                            //Navigate to next account
                            clickOnElement(btnNextArrow);

                            //WaitForElementPresence(lblLoadingIcon);
                            waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
                            break;

                        case MyAccountsConstants.TAB_LOANS:
                            addToReport("----------Start of validation of the content of loans list and the functional behaviour ----------", Status.PASS, false);
                            waitForElementToBeInvisible(lblAccountListLoading, VERY_LONG_WAIT);
                            waitForElementToBeInvisible(lblLoadingIcon, MODERATE_WAIT);
                            waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);
                            waitForElementToBeClickable(lblAccountNumber, LONG_WAIT);
                            //Obtain account number and validate
                            accountNumber = getAttributeOrText(lblAccountNumber, "text");
                            accountNumber = accountNumber.replace(MyAccountsConstants.CURRENT_OUTSTANDING, "").trim().replaceAll("\\s+", "");
                            if (isElementPresentBy(lblHighlightedAccountNo(accountNumber))) {
                                addToReport("Successfully validated account number : '" + accountNumber + "' under the tab " + tabName[inc], Status.PASS, false);
                            } else {
                                addToReport("Failed to validated account number : '" + accountNumber + "' under the tab " + tabName[inc], Status.FAIL, true);
                            }

                            // Remove the minus sign
                            currencyAndBal = currencyAndBal.replace("-", "");
                            if (isElementPresentBy(lblHighlightedListContent(currencyAndBal))) {
                                addToReport("Successfully validated currency and balance : '" + currencyAndBal + "' under the tab " + tabName[inc], Status.PASS, true);
                            } else {
                                addToReport("Failed to validated currency and balance '" + currencyAndBal + "' under the tab " + tabName[inc], Status.FAIL, true);
                            }
                            //Validate Table Headers
                            for (String header : MyAccountsConstants.LOANS_TABLE_HEADERS) {
                                if (isElementPresentBy(getHeaderByName(header))) {
                                    addToReport("Table header '" + header + "' is present under tab '" + tabName[inc] + "'", Status.PASS, false);
                                } else {
                                    addToReport("Missing table header '" + header + "' under tab '" + tabName[inc] + "'", Status.FAIL, true);
                                }
                            }

                            clickOnElement(lblHighlightedAccountNo(accountNumber));
                            waitForElementToBeClickable(lblAccountNumberByHeader(MyAccountsConstants.PAID_INSTALLMENTS), VERY_LONG_WAIT);
                            waitForElementToBeInvisible(lblLoadingIcon, VERY_LONG_WAIT);
                            waitForElementToBeInvisible(lblAccountListLoading, VERY_LONG_WAIT);
                            //Remove this once finacle dates are synchronized
                            advanceSearchByDate(BillerConstants.NUMBER_TWENTY_TWENTY_FOUR, BillerConstants.JULY, BillerConstants.NUMBER_TWENTY_ONE, BillerConstants.NUMBER_TWENTY_ONE);

                            aHAccountNo = getTextFromElement(lblAccountNumberByHeader(MyAccountsConstants.PAID_INSTALLMENTS));
                            accountNumber = aHAccountNo.split("-")[1].trim();

                            if (aHAccountNo.equals(accountNumber)) {
                                addToReport("Successfully validated loan account number : '" + accountNumber + "' under the account history", Status.PASS, true);
                            } else {
                                addToReport("Failed to validated loan account number : '" + accountNumber + "' under the account history ", Status.FAIL, true);
                            }
                            //Check if data found
                            if (isElementPresentBy(lblLoanNoDataFound(MyAccountsConstants.NO_DATA_FOUND))) {
                                addToReport(" No Data found under the  paid installment for account  " + aHAccountNo, Status.INFO, true);
                            } else {
                                //Validate Table Headers
                                for (String header : MyAccountsConstants.LOANS_PAID_TABLE_HEADERS) {
                                    if (isElementPresentBy(getHeaderByName(header))) {
                                        addToReport("Table header '" + header + "' is present under tab '" + tabName[inc] + "'", Status.PASS, false);
                                    } else {
                                        addToReport("Missing table header '" + header + "' under tab '" + tabName[inc] + "'", Status.FAIL, true);
                                    }
                                }
                                //Validate Table Rows Loaded
                                rowCount = isElementsPresentBy(tblTransactionRows);
                                if (rowCount > 0) {
                                    addToReport("Table loaded with " + rowCount + " record(s) under tab '" + tabName[inc] + "'", Status.PASS, true);
                                } else {
                                    addToReport("No records found in table under tab '" + tabName[inc] + "'", Status.FAIL, true);
                                }
                            }
                            waitForElementToBeClickable(getElementByTypeAndText(ElementType.div, MyAccountsConstants.PARTIAL_INSTALLMENTS), 30);
                            clickOnElement(getElementByTypeAndText(ElementType.div, MyAccountsConstants.PARTIAL_INSTALLMENTS));
                            if (isElementPresentBy(lblLoanNoDataFound(MyAccountsConstants.NO_DATA_FOUND))) {
                                addToReport(" No Data found under the  partial installment for account  " + aHAccountNo, Status.INFO, true);
                            } else {
                                //Validate Table Headers
                                for (String header : MyAccountsConstants.LOANS_PARTIAL_TABLE_HEADERS) {
                                    if (isElementPresentBy(getHeaderByName(header))) {
                                        addToReport("Table header '" + header + "' is present under tab '" + tabName[inc] + "'", Status.PASS, false);
                                    } else {
                                        addToReport("Missing table header '" + header + "' under tab '" + tabName[inc] + "'", Status.FAIL, true);
                                    }
                                }
                                //Validate Table Rows Loaded
                                rowCount = isElementsPresentBy(tblTransactionRows);
                                if (rowCount > 0) {
                                    addToReport("Table loaded with " + rowCount + " record(s) under tab '" + tabName[inc] + "'", Status.PASS, true);
                                } else {
                                    addToReport("No records found in table under tab '" + tabName[inc] + "'", Status.FAIL, true);
                                }
                            }
                            scrollPageToTop();
                            waitForElementToBeClickable(getElementByTypeAndText(ElementType.span, MyAccountsConstants.VIEW_LIST), 30);
                            clickOnElement(getElementByTypeAndText(ElementType.span, MyAccountsConstants.VIEW_LIST));
                            addToReport("----------End of validation of the content of loans list and the functional behaviour ----------", Status.PASS, true);

                            //Navigate to next account
                            clickOnElement(btnNextArrow);
                            //WaitForElementPresence(lblLoadingIcon);
                            waitForElementToBeInvisible(lblLoadingIcon, MODERATE_WAIT);
                            break;

                        case MyAccountsConstants.TAB_PAWNING:
                            addToReport("----------Start of validation of the content of pawning list and the functional behaviour ----------", Status.PASS, false);
                            waitForElementToBeInvisible(lblAccountListLoading, VERY_LONG_WAIT);
                            waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
                            waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);
                            waitForElementToBeClickable(lblPawningAccountNumber, LONG_WAIT);
                            //Obtain pawning number and validate
                            accountNumber = getAttributeOrText(lblPawningAccountNumber, "text");
                            if (isElementPresentBy(lblHighlightedAccountNo(accountNumber))) {
                                addToReport("Successfully validated account number : '" + accountNumber + "' under the tab " + tabName[inc], Status.PASS, false);
                            } else {
                                addToReport("Failed to validated account number : '" + accountNumber + "' under the tab " + tabName[inc], Status.FAIL, true);
                            }
                            if (isElementPresentBy(lblHighlightedListContent(currencyAndBal))) {
                                addToReport("Successfully validated currency and balance : '" + currencyAndBal + "' under the tab " + tabName[inc], Status.PASS, true);
                            } else {
                                addToReport("Failed to validated currency and balance '" + currencyAndBal + "' under the tab " + tabName[inc], Status.FAIL, true);
                            }
                            //Validate Table Headers
                            for (String header : MyAccountsConstants.PAWNING_LIST_TABLE_HEADERS) {
                                if (isElementPresentBy(getHeaderByName(header))) {
                                    addToReport("Table header '" + header + "' is present under tab '" + tabName[inc] + "'", Status.PASS, false);
                                } else {
                                    addToReport("Missing table header '" + header + "' under tab '" + tabName[inc] + "'", Status.FAIL, true);
                                }
                            }
                            clickOnElement(lblHighlightedAccountNo(accountNumber));
                            waitForElementToBeClickable(lblAccountHistoryAccountNo(MyAccountsConstants.TAB_PAWNING_HISTORY), LONG_WAIT);
                            waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
                            waitForElementToBeInvisible(lblAccountListLoading, VERY_LONG_WAIT);
                            //  Remove this once finacle dates are synchronized
                            //  advanceSearchByDate(BillerConstants.NUMBER_TWENTY_TWENTY_FOUR, BillerConstants.JULY, BillerConstants.NUMBER_TWENTY_ONE, BillerConstants.NUMBER_TWENTY_ONE);

                            aHAccountNo = getTextFromElement(lblAccountHistoryAccountNo(MyAccountsConstants.TAB_PAWNING_HISTORY));
                            if (aHAccountNo.equals(accountNumber)) {
                                addToReport("Successfully validated account number : '" + accountNumber + "' under the account history", Status.PASS, true);
                            } else {
                                addToReport("Failed to validated account number : '" + accountNumber + "' under the account history ", Status.FAIL, true);
                            }
                            scrollPageToTop();
                            waitForElementToBeClickable(getElementByTypeAndText(ElementType.span, MyAccountsConstants.VIEW_LIST), 30);
                            clickOnElement(getElementByTypeAndText(ElementType.span, MyAccountsConstants.VIEW_LIST));
                            addToReport("----------End of validation of the content of pawning list and the functional behaviour ----------", Status.PASS, true);
                            //Navigate to next account
                            clickOnElement(btnNextArrow);
                            //WaitForElementPresence(lblLoadingIcon);
                            waitForElementToBeInvisible(lblLoadingIcon, MODERATE_WAIT);
                            break;

                        case MyAccountsConstants.TAB_T_BILLS:
                            addToReport("----------Start of validation of user is able to view the detailed information of their treasury bill ----------", Status.PASS, false);
                            waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
                            waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);
                            waitForElementToBeInvisible(lblAccountListLoading, VERY_LONG_WAIT);

                            //Obtain account number and validate
                            accountNumber = getAttributeOrText(lblPawningAccountNumber, "text");
                            investmentDate = getAttributeOrText(lblDatesAndRates(MyAccountsConstants.INVESTMENT_DATE), "text");
                            NoOfDays = getAttributeOrText(lblDatesAndRates(MyAccountsConstants.NUMBER_OF_DAYS), "text");
                            tbillYield = getAttributeOrText(lblRepoAdditionalDetails(MyAccountsConstants.YIELD), "text");
                            tbillFaceValue = getAttributeOrText(lblRepoAdditionalDetails(MyAccountsConstants.FACE_VALUE), "text");
                            tbillMaturiyDate = getAttributeOrText(lblRepoAdditionalDetails(MyAccountsConstants.MATURITY_DATE), "text");

                            if (isElementPresentBy(lblHighlightedAccountNo(accountNumber))) {
                                addToReport("Successfully validated account number : '" + accountNumber + "' under the tab " + tabName[inc], Status.PASS, false);
                            } else {
                                addToReport("Failed to validated account number : '" + accountNumber + "' under the tab " + tabName[inc], Status.FAIL, true);
                            }
                            if (isElementPresentBy(lblHighlightedListContent(currencyAndBal))) {
                                addToReport("Successfully validated currency and balance : '" + currencyAndBal + "' under the tab " + tabName[inc], Status.PASS, true);
                            } else {
                                addToReport("Failed to validated currency and balance '" + currencyAndBal + "' under the tab " + tabName[inc], Status.FAIL, true);
                            }
                            //validate investment date
                            if (isElementPresentBy(lblHighlightedListContent(investmentDate))) {
                                addToReport("Successfully validated investment date : '" + investmentDate + "' under the tab " + tabName[inc], Status.PASS, true);
                            } else {
                                addToReport("Failed to validated investment date '" + investmentDate + "' under the tab " + tabName[inc], Status.FAIL, true);
                            }
                            //validate no of days
                            if (isElementPresentBy(lblHighlightedListContent(NoOfDays))) {
                                addToReport("Successfully validated number of days : '" + NoOfDays + "' under the tab " + tabName[inc], Status.PASS, true);
                            } else {
                                addToReport("Failed to validated number of days '" + NoOfDays + "' under the tab " + tabName[inc], Status.FAIL, true);
                            }
                            // Validate T-Bill Yield
                            if (isElementPresentBy(lblHighlightedListContent(tbillYield))) {
                                addToReport("Successfully validated T-Bill Yield : '" + tbillYield + "' under the tab " + tabName[inc], Status.PASS, true);
                            } else {
                                addToReport("Failed to validate T-Bill Yield : '" + tbillYield + "' under the tab " + tabName[inc], Status.FAIL, true);
                            }
                            // Validate T-Bill Face Value
                            if (isElementPresentBy(lblHighlightedListContent(tbillFaceValue))) {
                                addToReport("Successfully validated T-Bill Face Value : '" + tbillFaceValue + "' under the tab " + tabName[inc], Status.PASS, true);
                            } else {
                                addToReport("Failed to validate T-Bill Face Value : '" + tbillFaceValue + "' under the tab " + tabName[inc], Status.FAIL, true);
                            }

                            // Validate T-Bill Maturity Date
                            if (isElementPresentBy(lblHighlightedListContent(tbillMaturiyDate))) {
                                addToReport("Successfully validated T-Bill Maturity Date : '" + tbillMaturiyDate + "' under the tab " + tabName[inc], Status.PASS, true);
                            } else {
                                addToReport("Failed to validate T-Bill Maturity Date : '" + tbillMaturiyDate + "' under the tab " + tabName[inc], Status.FAIL, true);
                            }
                            addToReport("----------End of validation of user is able to view the detailed information of their treasury bill ----------", Status.PASS, false);
                            addToReport("----------Start of validation of the content of t-bills and the functional behaviour ----------", Status.PASS, false);
                            //Validate Table Headers
                            for (String header : MyAccountsConstants.T_BILL_TABLE_HEADERS) {
                                if (isElementPresentBy(getHeaderByName(header))) {
                                    addToReport("Table header '" + header + "' is present under tab '" + tabName[inc] + "'", Status.PASS, false);
                                } else {
                                    addToReport("Missing table header '" + header + "' under tab '" + tabName[inc] + "'", Status.FAIL, true);
                                }
                            }

                            addToReport("----------End of validation of the content of t-bills and the functional behaviour ----------", Status.PASS, true);
                            //Navigate to next account
                            clickOnElement(btnNextArrow);
                            //WaitForElementPresence(lblLoadingIcon);
                            waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);

                            break;

                        case MyAccountsConstants.TAB_REPO:
                            addToReport("----------Start of validation of user is able to view the detailed information of their repo ----------", Status.PASS, false);
                            waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
                            waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);
                            waitForElementToBeInvisible(lblAccountListLoading, VERY_LONG_WAIT);
                            //Obtain account number and validate
                            accountNumber = getAttributeOrText(lblPawningAccountNumber, "text");
                            repoMaturityDate = getAttributeOrText(lblDatesAndRates(MyAccountsConstants.MATURITY_DATE), "text");
                            repoInterestRate = getAttributeOrText(lblDatesAndRates(MyAccountsConstants.INTEREST_RATE), "text");
                            repoMaturityValue = getAttributeOrText(lblRepoAdditionalDetails(MyAccountsConstants.MATURITY_VALUE), "text");


                            if (isElementPresentBy(lblHighlightedAccountNo(accountNumber))) {
                                addToReport("Successfully validated repo number : '" + accountNumber + "' under the tab " + tabName[inc], Status.PASS, false);
                            } else {
                                addToReport("Failed to validated repo number : '" + accountNumber + "' under the tab " + tabName[inc], Status.FAIL, true);
                            }
                            // amount and validate
                            if (isElementPresentBy(lblHighlightedListContent(currencyAndBal))) {
                                addToReport("Successfully validated currency and balance : '" + currencyAndBal + "' under the tab " + tabName[inc], Status.PASS, true);
                            } else {
                                addToReport("Failed to validated currency and balance '" + currencyAndBal + "' under the tab " + tabName[inc], Status.FAIL, true);
                            }
                            // repo date
                            if (isElementPresentBy(lblHighlightedListContent(repoMaturityDate))) {
                                addToReport("Successfully validated repo date : '" + repoMaturityDate + "' under the tab " + tabName[inc], Status.PASS, true);
                            } else {
                                addToReport("Failed to validated repo date '" + repoMaturityDate + "' under the tab " + tabName[inc], Status.FAIL, true);
                            }
                            // interest rate
                            if (isElementPresentBy(lblHighlightedListContent(repoInterestRate))) {
                                addToReport("Successfully validated interest rate : '" + repoInterestRate + "' under the tab " + tabName[inc], Status.PASS, true);
                            } else {
                                addToReport("Failed to validated interest rate '" + repoInterestRate + "' under the tab " + tabName[inc], Status.FAIL, true);
                            }
                            //maturity value
                            if (isElementPresentBy(lblHighlightedListContent(repoMaturityValue))) {
                                addToReport("Successfully validated maturity value : '" + repoMaturityValue + "' under the tab " + tabName[inc], Status.PASS, true);
                            } else {
                                addToReport("Failed to validated maturity value '" + repoMaturityValue + "' under the tab " + tabName[inc], Status.FAIL, true);
                            }

                            addToReport("----------End of validation of user is able to view the detailed information of their treasury bill ----------", Status.PASS, false);
                            addToReport("----------Start of validation of the content of repo and the functional behaviour ----------", Status.PASS, false);
                            //Validate Table Headers
                            for (String header : MyAccountsConstants.REPO_TABLE_HEADERS) {
                                if (isElementPresentBy(getHeaderByName(header))) {
                                    addToReport("Table header '" + header + "' is present under tab '" + tabName[inc] + "'", Status.PASS, false);
                                } else {
                                    addToReport("Missing table header '" + header + "' under tab '" + tabName[inc] + "'", Status.FAIL, true);
                                }
                            }
                            addToReport("----------End of validation of the content of repo and the functional behaviour ----------", Status.PASS, false);
                            //Navigate to next account
                            clickOnElement(btnNextArrow);

                            //WaitForElementPresence(lblLoadingIcon);
                            waitForElementToBeInvisible(lblLoadingIcon, VERY_LONG_WAIT);
                            break;
                        default:
                            addToReport("Tab name not recognized: " + currentTab, Status.WARNING, false);
                    }

                }
            } else {
                addToReport("No cards are available", Status.FAIL, true);
            }
            addToReport("----------End of validation of that when user click on 'My Accounts' item in top navigation menu all account categories and other product lists displayed----------", Status.PASS, false);
        }
    }

    /**
     * Update and validate nickname
     *
     * @param tabName    an array of tab names where the nickname update needs to be performed
     *                   (e.g., {"Accounts", "Deposits"})
     * @param tileHeader an array of expected tile headers to validate nickname updates
     *                   (e.g., {"'Accounts / Savings'", "'My Accounts / FD'"})
     */
    public void UpdateAndValidateNickName(String[] tabName, String[] tileHeader) {

        addToReport("----------Start of validation of that when user click on 'My Accounts' item in top navigation menu all account categories and other product lists displayed----------", Status.PASS, false);

        waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);

        for (int inc = 0; tabName.length > inc; inc++) {
            //Validate the tab and relative header
            selectTabAndValidate(tabName[inc], tileHeader[inc]);
            String nickName = CommonUtils.randomAlphaNumeric(10);
            //Validate the selected tile and its relevant data loaded at list
            String currentTab = tabName[inc];
            switch (currentTab) {
                case MyAccountsConstants.TAB_ACCOUNTS:

                    waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
                    waitForElementToBeInvisible(lblAccountListLoading, VERY_LONG_WAIT);
                    waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);

                    waitForElementToBeClickable(btnEditNickName, LONG_WAIT);

                    clickOnElement(btnEditNickName);

                    waitForElementPresence(getElementByTypeAndText(ElementType.div, MyAccountsConstants.ENABLE_EDITING));
                    clickOnElement(getElementByTypeAndText(ElementType.div, MyAccountsConstants.ENABLE_EDITING));

                    sendKeysToElement(tfNickName, nickName);

                    addToReport("Enter nick name " + nickName, Status.PASS, true);
                    clickOnElement(getElementByTypeAndText(ElementType.div, MyAccountsConstants.SAVE_CHANGES));

                    waitForElementPresence(getElementByTypeAndText(ElementType.div, MyAccountsConstants.NICK_NAME_UPDATED), 20);
                    addToReport("NickName updated successfully popup received", Status.PASS, true);
                    clickOnElement(btnClosePopup);

                    clickOnElement(btnNextArrow);
                    waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
                    waitForElementToBeInvisible(lblAccountListLoading, VERY_LONG_WAIT);
                    waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);
                    clickOnElement(btnPreviousArrow);
                    waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
                    waitForElementToBeInvisible(lblAccountListLoading, VERY_LONG_WAIT);
                    waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);

                    if (isElementPresentBy(lblNickName(nickName))) {
                        addToReport("Nick name updated to  '" + nickName + "' is present under tab '" + tabName[inc] + "'", Status.PASS, true);
                    } else {
                        addToReport("Nick name was not updated to  '" + nickName + "' under tab '" + tabName[inc] + "'", Status.FAIL, true);
                    }
                    scrollPageToTop();
                    break;

                case MyAccountsConstants.TAB_DEPOSITS:
                    waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
                    waitForElementToBeInvisible(lblAccountListLoading, VERY_LONG_WAIT);
                    waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);

                    waitForElementToBeClickable(btnEditNickName, LONG_WAIT);

                    clickOnElement(btnEditNickName);

                    waitForElementToBeClickable(tfNickName, LONG_WAIT);
                    sendKeysToElement(tfNickName, nickName);

                    addToReport("Enter nick name " + nickName, Status.PASS, true);
                    clickOnElement(getElementByTypeAndText(ElementType.button, MyAccountsConstants.SAVE_CHANGES));

                    waitForElementPresence(getElementByTypeAndText(ElementType.div, MyAccountsConstants.NICK_NAME_UPDATED), LONG_WAIT);
                    addToReport("NickName updated successfully popup received", Status.PASS, true);
                    clickOnElement(btnClosePopup);

                    //Uncomment below based on data
//                    clickOnElement(btnNextArrow);
//                    waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
//                    waitForElementToBeInvisible(lblAccountListLoading, VERY_LONG_WAIT);
//                    waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);
//
//                    clickOnElement(btnPreviousArrow);
//                    waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
//                    waitForElementToBeInvisible(lblAccountListLoading, VERY_LONG_WAIT);
//                    waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);

                    for (Map.Entry<String, String> entry : MyAccountsConstants.TEST_NICKNAME_MAP.entrySet()) {
                        String nickname = entry.getKey();
                        String expectedMessage = entry.getValue();

                        clickOnElement(btnEditNickName);
                        waitForElementToBeClickable(tfNickName, LONG_WAIT);
                        sendKeysToElement(tfNickName, "");
                        sendKeysToElement(tfNickName, nickname);

                        String enteredValue = getAttributeOrText(tfNickName, "value");

                        // Validate length restriction (input level)
                        if (nickname.length() > MyAccountsConstants.MAX_NICKNAME_LENGTH) {
                            if (enteredValue.length() == MyAccountsConstants.MAX_NICKNAME_LENGTH) {
                                addToReport("Nickname input correctly restricted to max 10 characters. Entered: '" + enteredValue + "'", Status.PASS, true);
                            } else {
                                addToReport("Nickname length restriction failed. Expected max 10 but got: " + enteredValue.length(), Status.FAIL, true);
                            }

                            clickOnElementUsingJS(getElementByTypeAndText(ElementType.button, MyAccountsConstants.BUTTON_TEXT_CANCEL));
                            waitForElementToBeInvisible(getElementByTypeAndText(ElementType.button, MyAccountsConstants.BUTTON_TEXT_CANCEL), SHORT_WAIT);
                            continue;
                        }

                        // Submit the nickname
                        clickOnElement(getElementByTypeAndText(ElementType.button, MyAccountsConstants.SAVE_CHANGES));

                        // Handle error messages for invalid inputs (e.g., special characters)
                        if (!expectedMessage.isEmpty() && !expectedMessage.equals(MyAccountsConstants.NICK_NAME_UPDATED)) {
                            waitForElementPresence(getElementByTypeAndText(ElementType.span, MyAccountsConstants.NICK_NAME_UPDATED), LONG_WAIT);
                            addToReport("NickName updated successfully popup received", Status.PASS, true);

                            if (isElementPresentBy(getElementByTypeAndText(ElementType.span, expectedMessage))) {
                                addToReport("Expected error message shown for nickname '" + nickname + "': " + expectedMessage, Status.PASS, true);
                            } else {
                                addToReport("Expected error message NOT shown for nickname '" + nickname + "'", Status.FAIL, true);
                            }
                            clickOnElementUsingJS(getElementByTypeAndText(ElementType.button, MyAccountsConstants.BUTTON_TEXT_CANCEL));
                            waitForElementToBeInvisible(getElementByTypeAndText(ElementType.button, MyAccountsConstants.BUTTON_TEXT_CANCEL), SHORT_WAIT);

                            continue;
                        }

                        // If valid nickname, validate update success
                        if (expectedMessage.equals(MyAccountsConstants.NICK_NAME_UPDATED)) {
                            waitForElementPresence(getElementByTypeAndText(ElementType.div, MyAccountsConstants.NICK_NAME_UPDATED), LONG_WAIT);
                            if (isElementPresentBy(getElementByTypeAndText(ElementType.div, MyAccountsConstants.NICK_NAME_UPDATED))) {
                                addToReport("Valid nickname '" + nickname + "' updated successfully message shown", Status.PASS, true);
                            } else {
                                addToReport("Update success message not shown for valid nickname '" + nickname + "'", Status.FAIL, true);
                            }
                            clickOnElement(btnClosePopup);

                            if (isElementPresentBy(lblNickName(nickname))) {
                                addToReport("Valid nickname '" + nickname + "' saved and visible on FD card.", Status.PASS, true);
                            } else {
                                addToReport("Valid nickname '" + nickname + "' not displayed on FD card after save.", Status.FAIL, true);
                            }
                        }
                    }

                    break;

                case MyAccountsConstants.TAB_PAWNING:
                    waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
                    waitForElementToBeInvisible(lblAccountListLoading, VERY_LONG_WAIT);
                    waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);

                    waitForElementToBeClickable(btnEditNickName, LONG_WAIT);

                    clickOnElement(btnEditNickName);

                    waitForElementToBeClickable(tfNickName, LONG_WAIT);
                    sendKeysToElement(tfNickName, nickName);

                    addToReport("Enter nick name " + nickName, Status.PASS, true);
                    clickOnElement(getElementByTypeAndText(ElementType.button, MyAccountsConstants.SAVE_CHANGES));

                    waitForElementPresence(getElementByTypeAndText(ElementType.div, MyAccountsConstants.NICK_NAME_UPDATED), LONG_WAIT);
                    addToReport("NickName updated successfully popup received", Status.PASS, true);
                    clickOnElement(btnClosePopup);

                    clickOnElement(btnNextArrow);
                    waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
                    waitForElementToBeInvisible(lblAccountListLoading, VERY_LONG_WAIT);
                    waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);
                    clickOnElement(btnPreviousArrow);
                    waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
                    waitForElementToBeInvisible(lblAccountListLoading, VERY_LONG_WAIT);
                    waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);

                    if (isElementPresentBy(lblNickName(nickName))) {
                        addToReport("Nick name updated to  '" + nickName + "' is present under tab '" + tabName[inc] + "'", Status.PASS, true);
                    } else {
                        addToReport("Nick name was not updated to  '" + nickName + "' under tab '" + tabName[inc] + "'", Status.FAIL, true);
                    }
                    scrollPageToTop();
                    break;
                default:
                    addToReport("Tab name not recognized: " + currentTab, Status.WARNING, false);
            }
        }
    }

    /**
     * Validates that the error popup displays the expected message after performing an action with the given nickname
     *
     * @param nickname        the nickname value used in the action that triggers the popup
     * @param expectedMessage the expected error message text to be displayed in the popup
     */
    private void validateErrorPopup(String nickname, String expectedMessage) {
        if (isElementPresentBy(getElementByTypeAndText(ElementType.div, expectedMessage))) {
            addToReport("Nickname '" + nickname + "' correctly rejected: " + expectedMessage, Status.PASS, true);
        } else {
            addToReport("Nickname '" + nickname + "' did not trigger expected error: " + expectedMessage, Status.FAIL, true);
        }
    }

    /**
     * Validates that saving the provided nickname is successful by checking for the expected success indicators
     *
     * @param nickname the nickname value to be saved and validated
     */
    private void validateSuccessNicknameSave(String nickname) {
        if (isElementPresentBy(getElementByTypeAndText(ElementType.div, MyAccountsConstants.NICK_NAME_UPDATED))) {
            addToReport("Valid nickname '" + nickname + "' saved successfully", Status.PASS, true);
            clickOnElement(btnClosePopup);

            if (isElementPresentBy(lblNickName(nickname))) {
                addToReport("Nickname '" + nickname + "' displayed correctly", Status.PASS, true);
            } else {
                addToReport("Nickname '" + nickname + "' saved but not shown on card", Status.FAIL, true);
            }
        } else {
            addToReport("Valid nickname '" + nickname + "' failed to save", Status.FAIL, true);
        }
    }


    /**
     * Validate the current account details
     *
     * @param tabName          The name of the tab to navigate
     * @param tileHeader       The header of the account tile
     * @param accountNumber    The account number to validate
     * @param odLimit          Overdraft limit value
     * @param tempOdLimit      Temporary overdraft limit
     * @param overdueLiability Overdue liability amount
     * @param reservedAmount   Reserved amount in the account
     * @param accountBalance   Final account balance shown
     * @param openedOn         Date the account was opened
     */
    public void ValidateCurrentAccountDetails(String tabName, String tileHeader, String accountNumber, String odLimit, String tempOdLimit, String overdueLiability, String reservedAmount, String accountBalance, String openedOn) {
        addToReport("----------Start of validation of user should be able to view the Current account details----------", Status.PASS, false);

        waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);

        //Selecet tab and account
        selectTabAndValidate(tabName, tileHeader);


        //Navigate and select the account tile
        //Obtain pagination value
        cardCount = CommonUtils.splitText(getAttributeOrText(icnAccounts, "text"), "/");
        //Obtain the accounts record count
        recordCount = Integer.parseInt(cardCount[1]);
        if (recordCount != 0) {
            for (int incr = 0; incr < recordCount; incr++) {

                String accountNumberRetrived = getAttributeOrText(lblAccountNumber, "text").replace(" ", "");

                if (!accountNumberRetrived.equals(accountNumber)) {
                    //Navigate to next account
                    clickOnElement(btnNextArrow);
                    //WaitForElementPresence(lblLoadingIcon);
                    waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
                    waitForElementToBeInvisible(lblAccountListLoading, LONG_WAIT);

                } else {
                    clickOnElement(lblAccountNumber);
                    //WaitForElementPresence(lblLoadingIcon);
                    waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
                    waitForElementToBeInvisible(lblAccountListLoading, LONG_WAIT);
                    break;
                }
            }
        } else {
            addToReport("No accounts", Status.FAIL, true);
        }

        waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);
        waitForElementPresence(lblAccountSummaryDetails(MyAccountsConstants.ACCOUNT_CURRENCY), VERY_LONG_WAIT);

        // Permanent OD Limit
        String actualPermanentOD = getAttributeOrText(lblAccountSummaryDetails(MyAccountsConstants.PERMANENET_OD_LIMIT), "text");
        if (odLimit.equals(actualPermanentOD)) {
            addToReport("Successfully validated Permanent OD Limit: Expected '" + odLimit + "', Found '" + actualPermanentOD + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate Permanent OD Limit: Expected '" + odLimit + "', Found '" + actualPermanentOD + "'", Status.FAIL, true);
        }

        // Temporary OD Limit
        String actualTempOD = getAttributeOrText(lblAccountSummaryDetails(MyAccountsConstants.TEMPORARY_OD_LIMIT), "text");
        if (tempOdLimit.equals(actualTempOD)) {
            addToReport("Successfully validated Temporary OD Limit: Expected '" + tempOdLimit + "', Found '" + actualTempOD + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate Temporary OD Limit: Expected '" + tempOdLimit + "', Found '" + actualTempOD + "'", Status.FAIL, true);
        }

        // Overdue Liability
        String actualOverdue = getAttributeOrText(lblAccountSummaryDetails(MyAccountsConstants.OVERDUE_LIABILITY), "text");
        if (overdueLiability.equals(actualOverdue)) {
            addToReport("Successfully validated Overdue Liability: Expected '" + overdueLiability + "', Found '" + actualOverdue + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate Overdue Liability: Expected '" + overdueLiability + "', Found '" + actualOverdue + "'", Status.FAIL, true);
        }

        // System Reserved Amount
        String actualReserved = getAttributeOrText(lblAccountSummaryDetails(MyAccountsConstants.SYSTEM_RESERVED_AMOUNT), "text");
        if (reservedAmount.equals(actualReserved)) {
            addToReport("Successfully validated System Reserved Amount: Expected '" + reservedAmount + "', Found '" + actualReserved + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate System Reserved Amount: Expected '" + reservedAmount + "', Found '" + actualReserved + "'", Status.FAIL, true);
        }

        // Account Type
        String actualAccountType = getAttributeOrText(lblAccountSummaryDetails(MyAccountsConstants.ACCOUNT_TYPE), "text");
        if (MyAccountsConstants.CURRENT_ACCOUNT.equals(actualAccountType)) {
            addToReport("Successfully validated Account Type: Expected '" + MyAccountsConstants.CURRENT_ACCOUNT + "', Found '" + actualAccountType + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate Account Type: Expected '" + MyAccountsConstants.CURRENT_ACCOUNT + "', Found '" + actualAccountType + "'", Status.FAIL, true);
        }

        // Account Opened On
        String actualOpenedOn = getAttributeOrText(lblAccountSummaryDetails(MyAccountsConstants.ACCOUNT_OPENED_ON), "text");
        if (openedOn.equals(actualOpenedOn)) {
            addToReport("Successfully validated Account Opened On: Expected '" + openedOn + "', Found '" + actualOpenedOn + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate Account Opened On: Expected '" + openedOn + "', Found '" + actualOpenedOn + "'", Status.FAIL, true);
        }

        // Account Balance
        String actualBalance = getAttributeOrText(lblAccountSummaryDetails(MyAccountsConstants.ACCOUNT_BALANCE), "text");
        if (accountBalance.equals(actualBalance)) {
            addToReport("Successfully validated Account Balance: Expected '" + accountBalance + "', Found '" + actualBalance + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate Account Balance: Expected '" + accountBalance + "', Found '" + actualBalance + "'", Status.FAIL, true);
        }

        // Currency of the account
        String actualCurrency = getAttributeOrText(lblAccountSummaryDetails(MyAccountsConstants.ACCOUNT_CURRENCY), "text");
        if (MyAccountsConstants.CURRENCY_VALUES[0].equals(actualCurrency)) {
            addToReport("Successfully validated Currency of the account: Expected '" + MyAccountsConstants.CURRENCY_VALUES[0] + "', Found '" + actualCurrency + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate Currency of the account: Expected '" + MyAccountsConstants.CURRENCY_VALUES[0] + "', Found '" + actualCurrency + "'", Status.FAIL, true);
        }

        //Backdate to older finacle snap
        //Remove this once finacle dates are synchronized
        advanceSearchByDate(BillerConstants.NUMBER_TWENTY_TWENTY_FOUR, BillerConstants.JULY, BillerConstants.NUMBER_TWENTY_ONE, BillerConstants.NUMBER_TWENTY_ONE);

        //Validate the search results
        int recordCount = isElementsPresentBy(tblRows);
        if (recordCount == 10) {
            addToReport("Successfully validated number of transaction rows as  '" + recordCount, Status.PASS, true);
        } else {
            addToReport("Failed to validate number of transaction rows as  10 '", Status.FAIL, true);
        }

        addToReport("----------End of validation of user should be able to view the Current account details----------", Status.PASS, false);
    }

    /**
     * Performs and validates an advanced search based on various filters such as account details, date, and amount range
     *
     * @param tabName       the name of the tab to be selected for the search (e.g., "Accounts", "Deposits")
     * @param tileHeader    the expected tile header text to validate context (e.g., "'Accounts / Savings'")
     * @param accountNumber the account number to filter the search results
     * @param month         the month part of the transaction date filter
     * @param year          the year part of the transaction date filter
     * @param from          the start date of the transaction period
     * @param to            the end date of the transaction period
     * @param fullDate      the complete transaction date to filter
     * @param amountFrom    the minimum transaction amount to filter the results
     * @param amountTo      the maximum transaction amount to filter the results
     */
    public void ValidateAdvancedSearch(String tabName, String tileHeader, String accountNumber, String month, String year, String from, String to, String fullDate, String amountFrom, String amountTo, String downloadDirectory, String name, String address, String accountNo, String currency) {
        addToReport("----------Start of validation of user should be able to view the Current account details----------", Status.PASS, false);

        waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);

        //Selecet tab and account
        selectTabAndValidate(tabName, tileHeader);
        searchAndSelectAccountList(accountNumber);
        waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);
        clickOnElement(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_ADVANCE_SEARCH));

        scrollDownPage();
        waitForElementToBeClickable(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_APPLY_FILTERS), LONG_WAIT);

        clickOnElement(getAdvanceSearchFields(ElementType.span, MyAccountsConstants.TRANSACTION_DATE));

        waitForElementToBeClickable(getElementByTypeAndText(ElementType.button, MyAccountsConstants.BUTTON_TEXT_CLEAR), LONG_WAIT);

        // Select the same 'To Account' again from the dropdown
        selectFromDropdown(ddAdvancedSearchYear, year, "visibletext");
        selectFromDropdown(ddAdvancedSearchMonth, month, "visibletext");


        clickOnElement(lnkAdvancedSearchDay(from));
        clickOnElement(lnkAdvancedSearchDay(to));

        clickOnElement(getElementByTypeAndText(ElementType.button, MyAccountsConstants.BUTTON_APPLY));
        waitForElementToBeClickable(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_APPLY_FILTERS), LONG_WAIT);
        clickOnElement(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_APPLY_FILTERS));

        waitForElementToBeInvisible(lblLoadingIcon, VERY_LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);

        //Validate the filter results
        int recordCount = isElementsPresentBy(tblRows);
        if (recordCount > 0) {

            for (int inc = 1; inc <= recordCount; inc++) {
                //Validate cell record
                if (getTextFromElement(tblCellRecord(1, inc)).equals(fullDate)) {
                    addToReport(" Date " + fullDate + " has successfully returned on search for row count " + inc, Status.PASS, false);
                }
            }
            addToReport(" Date validated ", Status.PASS, true);
        } else {
            addToReport("Failed to validate filter function by date '", Status.FAIL, true);
        }
        addToReport(" End of date validation", Status.PASS, true);
        //validate close filter
        clickOnElement(btnCloseFilterIcon(MyAccountsConstants.FILTER_TRANSACTION_DATE));
        waitForElementToBeInvisible(btnCloseFilterIcon(MyAccountsConstants.FILTER_TRANSACTION_DATE), LONG_WAIT);
        waitForElementToBeInvisible(lblLoadingIcon, VERY_LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);

        scrollToWebElement(lblAccountHistoryAccountNo(MyAccountsConstants.ACCOUNT_HISTORY));
        waitForElementToBeClickable(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_ADVANCE_SEARCH), LONG_WAIT);
        clickOnElement(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_ADVANCE_SEARCH));


        waitForElementToBeClickable(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_APPLY_FILTERS), LONG_WAIT);

        sendKeysToElement(getAdvanceSearchFields(ElementType.span, MyAccountsConstants.FILTER_AMOUNT_FROM), amountFrom);
        sendKeysToElement(getAdvanceSearchFields(ElementType.span, MyAccountsConstants.FILTER_AMOUNT_TO), amountTo);
        clickOnElement(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_APPLY_FILTERS));

        //Temp search
        advanceSearchByDate(BillerConstants.NUMBER_TWENTY_TWENTY_FOUR, BillerConstants.JULY, BillerConstants.NUMBER_TWENTY_ONE, BillerConstants.NUMBER_TWENTY_ONE);


        waitForElementToBeInvisible(lblLoadingIcon, VERY_LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);

        //Validate the filter results
        recordCount = isElementsPresentBy(tblRows);
        if (recordCount > 0) {

            for (int inc = 1; inc <= recordCount; inc++) {

                String a = getTextFromElement(tblCellRecord(4, inc));
                //Validate cell record
                if (getTextFromElement(tblCellRecord(4, inc)).equals(amountFrom)) {
                    addToReport(" Amount " + amountFrom + " has successfully returned on search for row count " + inc, Status.PASS, false);
                } else {
                    addToReport("Failed to validate filter function by amount '", Status.FAIL, true);
                }
            }
            addToReport(" Amount validated ", Status.PASS, true);
        } else {
            addToReport("Failed to validate filter function by amount '", Status.FAIL, true);
        }

        //Validate debit and credit filters
        // close filter
        clickOnElement(btnCloseFilterIcon(MyAccountsConstants.FILTER_AMOUNT_FROM));
        waitForElementToBeInvisible(btnCloseFilterIcon(MyAccountsConstants.FILTER_AMOUNT_FROM), LONG_WAIT);
        waitForElementToBeInvisible(lblLoadingIcon, VERY_LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);

        scrollToWebElement(lblAccountHistoryAccountNo(MyAccountsConstants.ACCOUNT_HISTORY));
        waitForElementToBeClickable(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_ADVANCE_SEARCH), LONG_WAIT);
        clickOnElement(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_ADVANCE_SEARCH));

        waitForElementToBeClickable(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_APPLY_FILTERS), LONG_WAIT);

        selectFromDropdown(ddTransactionType, MyAccountsConstants.CREDIT, "visibletext");

        clickOnElement(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_APPLY_FILTERS));

        advanceSearchByDateAndMonth(BillerConstants.NUMBER_TWENTY_TWENTY_FOUR, BillerConstants.JULY, BillerConstants.NUMBER_TWENTY_ONE, BillerConstants.APRIL, BillerConstants.NUMBER_TWENTY_ONE);

        waitForElementToBeInvisible(lblLoadingIcon, VERY_LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);

        //Validate the filter results
        recordCount = isElementsPresentBy(tblRows);
        if (recordCount > 0) {

            for (int inc = 1; inc <= recordCount; inc++) {

                String a = getTextFromElement(tblCellRecord(3, inc));
                //Validate cell record
                if (getTextFromElement(tblCellRecord(3, inc)).equals(MyAccountsConstants.CREDIT)) {
                    addToReport(" Transfer type  " + MyAccountsConstants.CREDIT + " has successfully returned on search for row count " + inc, Status.PASS, false);
                } else {
                    addToReport("Failed to validate filter function by transfer type '", Status.FAIL, true);
                }
            }
            addToReport(" Transfer type credit validated ", Status.PASS, true);
        } else {
            addToReport("Failed to validate filter function by transfer type '", Status.FAIL, true);
        }
        // close filter
        clickOnElement(btnCloseFilterIcon(MyAccountsConstants.TRANSACTION_TYPE));
        waitForElementToBeInvisible(btnCloseFilterIcon(MyAccountsConstants.TRANSACTION_TYPE), LONG_WAIT);
        waitForElementToBeInvisible(lblLoadingIcon, VERY_LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);


        waitForElementToBeClickable(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_ADVANCE_SEARCH), LONG_WAIT);
        clickOnElement(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_ADVANCE_SEARCH));

        waitForElementToBeClickable(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_APPLY_FILTERS), LONG_WAIT);

        selectFromDropdown(ddTransactionType, MyAccountsConstants.DEBIT, "visibletext");

        clickOnElement(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_APPLY_FILTERS));

        waitForElementToBeInvisible(lblLoadingIcon, VERY_LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);

        //Validate the filter results
        recordCount = isElementsPresentBy(tblRows);
        if (recordCount > 0) {

            for (int inc = 1; inc <= recordCount; inc++) {

                String a = getTextFromElement(tblCellRecord(3, inc));
                //Validate cell record
                if (getTextFromElement(tblCellRecord(3, inc)).equals(MyAccountsConstants.DEBIT)) {
                    addToReport(" Transfer type  " + MyAccountsConstants.DEBIT + " has successfully returned on search for row count " + inc, Status.PASS, false);
                } else {
                    addToReport("Failed to validate filter function by transfer type '", Status.FAIL, true);
                }
            }
            addToReport(" Transfer type debit validated ", Status.PASS, true);
        } else {
            addToReport("Failed to validate filter function by transfer type '", Status.FAIL, true);
        }

        addToReport("----------End of validation of user should be able to view the Current account details----------", Status.PASS, false);

        addToReport("----------Start of validation the customer able to download the filtered transactions----------", Status.PASS, false);


        waitForElementToBeClickable(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_ADVANCE_SEARCH), LONG_WAIT);
        clickOnElement(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_ADVANCE_SEARCH));


        waitForElementToBeClickable(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_APPLY_FILTERS), LONG_WAIT);

        sendKeysToElement(getAdvanceSearchFields(ElementType.span, MyAccountsConstants.FILTER_AMOUNT_FROM), amountFrom);
        sendKeysToElement(getAdvanceSearchFields(ElementType.span, MyAccountsConstants.FILTER_AMOUNT_TO), amountTo);
        clickOnElement(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_APPLY_FILTERS));

        waitForElementToBeInvisible(lblLoadingIcon, VERY_LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);

        waitForElementToBeClickable(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_ADVANCE_SEARCH), LONG_WAIT);
        clickOnElement(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_ADVANCE_SEARCH));

        scrollDownPage();
        advanceSearchByDateAndMonth(BillerConstants.NUMBER_TWENTY_TWENTY_FOUR, BillerConstants.JULY, BillerConstants.NUMBER_TWENTY_ONE, BillerConstants.APRIL, BillerConstants.NUMBER_TWENTY_ONE);
        clickOnElement(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_ADVANCE_SEARCH));

        //Select download
        waitForElementToBeClickable(getElementByTypeAndText(ElementType.button, MyAccountsConstants.DOWNLOAD), LONG_WAIT);
        clickOnElement(getElementByTypeAndText(ElementType.button, MyAccountsConstants.DOWNLOAD));

        if (isElementPresentBy(popUpPDFDownload(MyAccountsConstants.PDF_DOWNLOADED_SUCCESSFULLY)) && isElementClickable(btnClosePopup)) {
            addToReport("Validated downloaded toast message", Status.PASS, true);
        } else {
            addToReport("Failed to validate downloaded toast message", Status.FAIL);
        }

        //Wait for download to initiate - update this with dynamic once stabilized
        waitFor(SHORT_WAIT);
        clickOnElement(tfSearch);

        // Get the latest downloaded file
        File latestFile = getLatestDownloadedFile(downloadDirectory);

        if (latestFile != null) {
            // Extract text from the PDF
            String extractedText = extractTextFromPDF(latestFile.getAbsolutePath()).replace("/n", "");

            addToReport(" Latest downloaded pdf :  : '" + extractedText, Status.INFO, false);

            String expectedPeriod = MyAccountsConstants.STATEMENT_PERIOD +
                    String.format("%s-%02d-%s to %s-%02d-%s", BillerConstants.NUMBER_TWENTY_ONE, getMonthNumber(BillerConstants.APRIL), BillerConstants.NUMBER_TWENTY_TWENTY_FOUR, BillerConstants.NUMBER_TWENTY_ONE, getMonthNumber(BillerConstants.JULY), BillerConstants.NUMBER_TWENTY_TWENTY_FOUR);

            //validate date
            if (extractedText.contains(MyAccountsConstants.STATEMENT_PERIOD) && extractedText.contains(expectedPeriod)) {
                addToReport(" Validated statement period from " + expectedPeriod + " for the downloaded record", Status.PASS, false);
            } else {
                addToReport(" Failed to validate statement period from " + expectedPeriod + " of the downloaded record", Status.FAIL, false);
            }


            // ---- NAME VALIDATION ----
            if (extractedText.contains(name)) {
                addToReport("Validated account holder name: " + name, Status.PASS, false);
            } else {
                addToReport("Failed to validate account holder name: " + name, Status.FAIL, false);
            }

            // ---- ADDRESS VALIDATION ----
            if (extractedText.contains(address)) {
                addToReport("Validated address: " + address, Status.PASS, false);
            } else {
                addToReport("Failed to validate address: " + address, Status.FAIL, false);
            }

            // ---- ACCOUNT NUMBER VALIDATION ----
            if (extractedText.contains(accountNo)) {
                addToReport("Validated account number: " + accountNo, Status.PASS, false);
            } else {
                addToReport("Failed to validate account number: " + accountNo, Status.FAIL, false);
            }

            // ---- CURRENCY VALIDATION ----
            if (extractedText.contains(currency)) {
                addToReport("Validated currency: " + currency, Status.PASS, false);
            } else {
                addToReport("Failed to validate currency: " + currency, Status.FAIL, false);
            }


        } else {
            addToReport(" Failed to download the payment record", Status.FAIL, false);
        }
        addToReport("----------End of validation of the customer able to download the filtered transactions----------", Status.PASS, true);


    }


    /**
     * Validates that the user can request a cheque book for a given account
     * Performs checks for UI elements such as collecting branch, number of leaves, and number of cheque books
     *
     * @param tabName       Name of the tab (e.g., "My Accounts") to be selected on the UI
     * @param tileHeader    Header text of the tile (e.g., "Savings Account") used for UI validation
     * @param accountNumber Account number
     * @param noOFLeaves    List of expected options for number of leaves per cheque book
     * @param numberOfBooks List of expected options for number of cheque books available for request
     */
    public void ValidateChequeRequestDetails(String tabName, String tileHeader, String accountNumber, List<String> noOFLeaves, List<String> numberOfBooks, String contactNumber, String branch, String otpValue, String successMsg) {
        addToReport("----------Start of validation of user should be able to  request cheques for a LKR accounts----------", Status.PASS, false);

        waitForElementToBeInvisible(lblLoadingIcon, VERY_LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);

        //Select tab and account
        selectTabAndValidate(tabName, tileHeader);
        searchAndSelectAccountList(accountNumber);

        waitForElementToBeClickable(getElementByTypeAndText(ElementType.span, MyAccountsConstants.CHEQUE_BOOK_REQUEST), LONG_WAIT);

        // Account Opened On
        if (isElementPresentBy(getElementByTypeAndText(ElementType.span, MyAccountsConstants.CHEQUE_BOOK_REQUEST))) {
            addToReport("Successfully validated button : '" + MyAccountsConstants.CHEQUE_BOOK_REQUEST, Status.PASS, true);
            clickOnElement(getElementByTypeAndText(ElementType.span, MyAccountsConstants.CHEQUE_BOOK_REQUEST));
        } else {
            addToReport("Failed to validate button '" + MyAccountsConstants.CHEQUE_BOOK_REQUEST, Status.FAIL, true);
        }

        addToReport("----------End of validation of user should be able to  request cheques for a LKR accounts----------", Status.PASS, false);
        addToReport("----------Start of validation of user should be able to  able to view the mentioned fields when requesting the cheques----------", Status.PASS, false);
        waitForElementToBeClickable(ddCollectingBranch, VERY_LONG_WAIT);

        // Collecting branch
        if (isElementPresentBy(ddCollectingBranch)) {
            addToReport("Successfully validated collecting branch", Status.PASS, false);
        } else {
            addToReport("Failed to validate collecting branch", Status.FAIL, true);
        }

        selectFromDropdown(ddCollectingBranch, branch, "visibletext");
        List<String> numberOfLeavesPerBook = getSelectedOptionText(ddNoOfLeaves, "ALL_OPTIONS");

        if (CommonUtils.compareTwoArraylist(numberOfLeavesPerBook, noOFLeaves, true)) {
            addToReport("Available leaves per book : " + numberOfLeavesPerBook, Status.PASS, false);
        } else {
            addToReport("Available leaves per book did not load appropriately Expected   " + noOFLeaves + " Retrieved : " + numberOfLeavesPerBook, Status.FAIL);
        }
        List<String> numberOfCBooks = getSelectedOptionText(ddNoOfChequeBook, "ALL_OPTIONS");

        if (CommonUtils.compareTwoArraylist(numberOfCBooks, numberOfBooks, true)) {
            addToReport("Available cheque books : " + numberOfLeavesPerBook, Status.PASS, true);
        } else {
            addToReport("Available cheque books did not load appropriately Expected   " + numberOfBooks + " Retrieved : " + numberOfCBooks, Status.FAIL);
        }

        addToReport("----------End of validation of user should be able to  able to view the mentioned fields when requesting the cheques----------", Status.PASS, false);
        addToReport("----------Start of validation of user should be able to  able to request cheque book----------", Status.PASS, false);
        //Proceed to cheque request

        //Enter contact number
        sendKeysToElement(tfChequeBookRequest(MyAccountsConstants.CONTACT_NUMBER), contactNumber);

        clickOnElementUsingJS(getElementByTypeAndText(ElementType.div, MyAccountsConstants.SEND_REQUEST));
        waitForElementToBeInvisible(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_TEXT_CANCEL), SHORT_WAIT);

        //validate the otp page
        if (branch.equals(getSelectedOptionText(tfRCBSelect(MyAccountsConstants.COLLECTING_BRANCH), "FIRST_SELECTED").get(0))) {
            addToReport("Validated the branch  " + branch + " in the OTP page", Status.PASS, false);
        } else {
            addToReport("Failed to validate the branch " + branch + " in the OTP page", Status.FAIL);
        }

        if (numberOfCBooks.get(1).equals(getSelectedOptionText(tfRCBSelect(MyAccountsConstants.NUMBERS_OF_CHEQUE_BOOKS), "FIRST_SELECTED").get(0))) {
            addToReport("Validated the no. of cheque books  " + numberOfCBooks.get(1) + " in the OTP page", Status.PASS, false);
        } else {
            addToReport("Failed to validate the no. of cheque books " + numberOfCBooks.get(1) + " in the OTP page", Status.FAIL);
        }

        if (numberOfLeavesPerBook.get(1).equals(getSelectedOptionText(tfRCBSelect(MyAccountsConstants.NUMBER_OF_LEAVES_PER_BOOK), "FIRST_SELECTED").get(0))) {
            addToReport("Validated the no. of leaves per book  " + numberOfLeavesPerBook.get(1) + " in the OTP page", Status.PASS, false);
        } else {
            addToReport("Failed to validate no. of leaves per books " + numberOfLeavesPerBook.get(1) + " in the OTP page", Status.FAIL);
        }

        if (contactNumber.equals(getAttributeOrText(tfRCBInput(MyAccountsConstants.CONTACT_NUMBER), "value"))) {
            addToReport("Validated the contact number  " + getAttributeOrText(tfRCBInput(MyAccountsConstants.CONTACT_NUMBER), "value") + " in the OTP page", Status.PASS, false);
        } else {
            addToReport("Failed to validate contact number " + contactNumber + " in the OTP page", Status.FAIL);
        }

        waitForElementPresence(tfOTP(1), LONG_WAIT);
        sendKeysToElement(tfOTP(1), String.valueOf(otpValue));
        clickOnElement(btnConfirm);
        waitForElementToBeInvisible(btnNextLoading, LONG_WAIT);

        //Validate the success toast msg
        if (isElementPresentBy(getElementByTypeAndText(ElementType.div, successMsg)) && isElementClickable(btnClosePopup)) {
            addToReport("Validated the success message in the OTP success page", Status.PASS, true);
        } else {
            addToReport("Failed to validate the success message in the OTP success page", Status.FAIL);
        }

        addToReport("----------End of validation of user should be able to  able to request cheque book----------", Status.PASS, false);
        waitForElementToBeInvisible(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_TEXT_CANCEL), SHORT_WAIT);
    }

    /**
     * Performs validation for stopping a cheque and revoking the request by navigating to the specified tab,
     * verifying the tile header, selecting the given account, and confirming the stop cheque and revoke flow
     *
     * @param tabName              the name of the tab to navigate to
     * @param tileHeader           the expected header of the tile to validate
     * @param accountNumber        the account number associated with the cheque
     * @param chequeBookNo         the cheque book number to be used in the stop request
     * @param stopChequeMsg        the expected confirmation message for stopping the cheque
     * @param proceedingMsg        the message shown before proceeding with the request
     * @param reason               the reason for stopping the cheque
     * @param stopChequeSuccessMsg the expected success message after successfully stopping the cheque
     */
    public void ValidateStopChequeAndRevokeRequest(String tabName, String tileHeader, String accountNumber, String chequeBookNo, String stopChequeMsg, String proceedingMsg, String reason, String stopChequeSuccessMsg) {
        addToReport("----------Start of validation of user should be able to  request cheques for a LKR accounts----------", Status.PASS, false);
        waitForElementToBeInvisible(lblLoadingIcon, VERY_LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);

        //Select tab and account
        selectTabAndValidate(tabName, tileHeader);
        searchAndSelectAccountList(accountNumber);

        waitForElementToBeClickable(getElementByTypeAndText(ElementType.span, MyAccountsConstants.STOP_CHEQUES), LONG_WAIT);

        // select stop cheque
        if (isElementPresentBy(getElementByTypeAndText(ElementType.span, MyAccountsConstants.STOP_CHEQUES))) {
            addToReport("Successfully validated button : '" + MyAccountsConstants.STOP_CHEQUES, Status.PASS, true);
            clickOnElement(getElementByTypeAndText(ElementType.span, MyAccountsConstants.STOP_CHEQUES));
        } else {
            addToReport("Failed to validate button '" + MyAccountsConstants.STOP_CHEQUES, Status.FAIL, true);
        }
        waitForElementToBeInvisible(icnTileLoading, MODERATE_WAIT);
        waitForElementToBeClickable(ddChequeBook, MODERATE_WAIT);

        selectFromDropdown(ddChequeBook, chequeBookNo, "visibletext");
        waitForElementToBeClickable(btnNext, MODERATE_WAIT);
        clickOnElementUsingJS(btnNext);

        if (chequeBookNo.equals(getAttributeOrText(lblAccountHistoryAccountNo(MyAccountsConstants.CHEQUE_BOOK_NO), "text"))) {
            addToReport("Validated the cheque book number header  " + chequeBookNo, Status.PASS, true);
        } else {
            addToReport("Failed to validate cheque book number header " + chequeBookNo, Status.FAIL);
        }

        //Validate unused cheques
        if (isElementPresentBy(lblUnusedCheques, SHORT_WAIT)) {

            waitForElementToBeClickable(tblBtn(1, MyAccountsConstants.BUTTON_STOP), MODERATE_WAIT);

            //Validate Table Headers
            for (String header : MyAccountsConstants.CHEQUE_TABLE_HEADERS) {
                if (isElementPresentBy(getHeaderByName(header))) {
                    addToReport("Table header '" + header + "' is present under Unused Cheques", Status.PASS, false);
                } else {
                    addToReport("Missing table header '" + header + "' under Unused Cheques", Status.FAIL, true);
                }
            }

            //Store and Stop the cheque
            String chequeNo = getAttributeOrText(tblCellRecordStopCheques(1, 1), "text");
            clickOnElement(tblBtn(1, MyAccountsConstants.BUTTON_STOP));
            addToReport("Clicked record " + chequeNo, Status.PASS, true);
            waitForElementPresence(getElementByTypeAndText(ElementType.span, proceedingMsg + chequeNo), MODERATE_WAIT);

            addToReport("----------Start of validation of relevant stop cheque message----------", Status.PASS, true);

            if (isElementPresentBy(getElementByTypeAndText(ElementType.span, stopChequeMsg), VERY_SHORT_WAIT)) {
                addToReport("Validated the " + chequeBookNo, Status.PASS, false);
            } else {
                addToReport("Failed to validate cheque book number header " + chequeBookNo, Status.FAIL);
            }
            addToReport("----------End of validation of relevant stop cheque message----------", Status.PASS, false);

            waitForElementToBeClickable(ddRemarkStopCheque, MODERATE_WAIT);
            selectFromDropdown(ddRemarkStopCheque, reason, "visibletext");

            waitForElementToBeClickable(btnStopCheque, MODERATE_WAIT);
            clickOnElement(btnStopCheque);
            if (isElementPresentBy(getElementByTypeAndText(ElementType.div, stopChequeSuccessMsg)) && isElementClickable(btnClosePopup)) {
                addToReport("Stop cheque toast message appeared successfully", Status.PASS, true);
            } else {
                addToReport("Stop cheque toast message did not appear successfully", Status.FAIL, true);
            }

            //Forced wait till stopped check moves
            waitFor(6);

            //Validate the stop cheque moved from unused cheque
            clickOnElementUsingJS(chkChequeActions);

            if (isElementPresentBy(lblStoppedCheques, SHORT_WAIT)) {


                waitForElementToBeClickable(tblBtn(1, MyAccountsConstants.BUTTON_Revoke), MODERATE_WAIT);

                //Validate Table Headers
                for (String header : MyAccountsConstants.CHEQUE_TABLE_HEADERS) {
                    if (isElementPresentBy(getHeaderByName(header))) {
                        addToReport("Table header '" + header + "' is present under stop Cheques", Status.PASS, false);
                    } else {
                        addToReport("Missing table header '" + header + "' under stop Cheques", Status.FAIL, true);
                    }
                }


                int recordCount = isElementsPresentBy(tblCellRecordStopCheques(1));
                if (recordCount > 0) {
                    for (int inc = 1; recordCount >= inc; inc++) {
                        if (chequeNo.equals(getAttributeOrText(tblCellRecordStopCheques(1, inc), "text"))) {
                            addToReport("Stopped cheque " + chequeNo + " is found under revoke", Status.PASS, true);
                            break;
                        } else if (recordCount == inc) {
                            addToReport("Stopped cheque " + chequeNo + " is not found under revoke", Status.FAIL, true);
                        }
                    }

                }

                //Add revoke once implemented in 4.1

            } else {
                addToReport("Option stopped cheques is not loaded", Status.FAIL, true);
            }

        } else {
            addToReport("Option unused cheques is not loaded", Status.FAIL, true);
        }


    }


    /**
     * Validates the savings account details for a specific account number, including summary information,
     * transaction history, and downloaded PDF transaction details
     *
     * @param tabName           Name of the tab (e.g., "My Accounts") to be selected
     * @param tileHeader        Header title of the tile (e.g., "Savings Account") for account type identification
     * @param accountNumber     Account number to be searched and validated
     * @param accHolderName     Expected account holder's name for validation
     * @param systemReserved    Expected system reserved balance to be validated
     * @param lienAmount        Expected lien amount tied to the account
     * @param accOpenedOn       Expected account opening date
     * @param accountBalance    Expected current balance of the savings account
     * @param floatBalance      Expected float balance associated with the account
     * @param amountFrom        Filter input for minimum transaction amount in advanced search
     * @param amountTo          Filter input for maximum transaction amount in advanced search
     * @param downloadDirectory Directory path where the transaction PDF will be downloaded and verified
     */
    public void ValidateSavingsAccountDetails(String tabName, String tileHeader, String accountNumber, String accHolderName, String systemReserved, String lienAmount, String accOpenedOn, String accountBalance, String floatBalance, String amountFrom, String amountTo, String downloadDirectory, String name, String address, String accountNo, String currency) {
        addToReport("----------Start of validation of user should be able to view the Savings account details----------", Status.PASS, false);


        waitForElementToBeInvisible(lblLoadingIcon, VERY_LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);

        //Selecet tab and account
        selectTabAndValidate(tabName, tileHeader);

        waitForElementToBeClickable(icnAccounts, LONG_WAIT);

        //Obtain pagination value
        cardCount = CommonUtils.splitText(getAttributeOrText(icnAccounts, "text"), "/");
        //Obtain the accounts record count
        recordCount = Integer.parseInt(cardCount[1]);
        if (recordCount != 0) {
            for (int incr = 0; incr < recordCount; incr++) {

                String accountNumberRetrived = getAttributeOrText(lblAccountNumber, "text").replace(" ", "");

                if (!accountNumberRetrived.equals(accountNumber)) {
                    //Navigate to next account
                    clickOnElement(btnNextArrow);
                    //WaitForElementPresence(lblLoadingIcon);
                    waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
                    waitForElementToBeInvisible(lblAccountListLoading, LONG_WAIT);

                } else {
                    break;
                }
            }
        } else {
            addToReport("No accounts", Status.FAIL, true);
        }
        waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
        waitForElementToBeInvisible(lblAccountListLoading, LONG_WAIT);

        // Account Holder Name
        if (accHolderName.equals(getAttributeOrText(lblSavingsAccountSummaryDetails(MyAccountsConstants.ACCOUNT_HOLDER), "text"))) {
            addToReport("Successfully validated Account Holder Name: '" + accHolderName + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate Account Holder Name: Expected " + accHolderName + " but received " + getAttributeOrText(lblSavingsAccountSummaryDetails(MyAccountsConstants.ACCOUNT_HOLDER), "text"), Status.FAIL, true);
        }

        // System Reserved
        if (systemReserved.equals(getAttributeOrText(lblSavingsAccountSummaryDetails(MyAccountsConstants.SYSTEM_RESERVED), "text"))) {
            addToReport("Successfully validated System Reserved: '" + systemReserved + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate System Reserved: Expected " + systemReserved + " but received " + getAttributeOrText(lblSavingsAccountSummaryDetails(MyAccountsConstants.SYSTEM_RESERVED), "text"), Status.FAIL, true);
        }

        // Lien Amount
        if (lienAmount.equals(getAttributeOrText(lblSavingsAccountSummaryDetails(MyAccountsConstants.LIEN_AMOUNT), "text"))) {
            addToReport("Successfully validated Lien Amount: '" + lienAmount + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate Lien Amount: Expected " + lienAmount + " but received " + getAttributeOrText(lblSavingsAccountSummaryDetails(MyAccountsConstants.LIEN_AMOUNT), "text"), Status.FAIL, true);
        }

        // Account Opened On
        if (accOpenedOn.equals(getAttributeOrText(lblSavingsAccountSummaryDetails(MyAccountsConstants.ACCOUNT_OPENED_ON), "text"))) {
            addToReport("Successfully validated Account Opened On: '" + accOpenedOn + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate Account Opened On: Expected " + accOpenedOn + " but received " + getAttributeOrText(lblSavingsAccountSummaryDetails(MyAccountsConstants.ACCOUNT_OPENED_ON), "text"), Status.FAIL, true);
        }

        // Account Balance
        if (accountBalance.equals(getAttributeOrText(lblSavingsAccountSummaryDetails(MyAccountsConstants.ACCOUNT_BALANCE), "text"))) {
            addToReport("Successfully validated Account Balance: '" + accountBalance + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate Account Balance: Expected " + accountBalance + " but received " + getAttributeOrText(lblSavingsAccountSummaryDetails(MyAccountsConstants.ACCOUNT_BALANCE), "text"), Status.FAIL, true);
        }

        // Float Balance
        if (floatBalance.equals(getAttributeOrText(lblSavingsAccountSummaryDetails(MyAccountsConstants.FLOAT_BALANCE), "text"))) {
            addToReport("Successfully validated Float Balance: '" + floatBalance + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate Float Balance: Expected " + floatBalance + " but received " + getAttributeOrText(lblSavingsAccountSummaryDetails(MyAccountsConstants.FLOAT_BALANCE), "text"), Status.FAIL, true);
        }

        //Click on account no row
        clickOnElement(lblHighlightedAccountNo(accountNumber));
        waitForElementToBeClickable(lblAccountHistoryAccountNo(MyAccountsConstants.ACCOUNT_HISTORY), VERY_LONG_WAIT);
        waitForElementToBeInvisible(lblLoadingIcon, VERY_LONG_WAIT);

        //Remove this once finacle dates are synchronized
        advanceSearchByDate(BillerConstants.NUMBER_TWENTY_TWENTY_FOUR, BillerConstants.JULY, BillerConstants.NUMBER_TWENTY_ONE, BillerConstants.NUMBER_TWENTY_ONE);

        //    validate the advance search download
        waitForElementToBeInvisible(lblLoadingIcon, VERY_LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);

        //The last 10 account transactions must be displayed
        int recordCount = isElementsPresentBy(tblRows);
        if (recordCount <= 10) {
            addToReport("Successfully validated number of transaction rows as  '" + recordCount, Status.PASS, true);
        } else {
            addToReport("Failed to validate number of transaction rows as  10 or less '", Status.FAIL, true);
        }

        //validate table headers
        //Validate selected account table headers
        for (String header : MyAccountsConstants.ACCOUNT_TABLE_HEADERS) {

            if (isElementPresentBy(getHeaderByName(header))) {
                addToReport("Table header '" + header + "' is present for account number " + accountNumber, Status.PASS, false);
            } else if (isElementPresentBy(getHeaderByName(MyAccountsConstants.AMOUNT_USD)) || isElementPresentBy(getHeaderByName(MyAccountsConstants.BALANCE_USD))) {
                addToReport("Table header '" + MyAccountsConstants.AMOUNT_USD + "' or '" + MyAccountsConstants.BALANCE_USD + "' is present for account number " + accountNumber, Status.PASS, false);
            } else {
                addToReport("Missing table header '" + header + "'  for account number " + accountNumber, Status.FAIL, true);
            }
        }

        addToReport("----------End of validation of user should be able to view the savings account details----------", Status.PASS, false);
        addToReport("----------Start of validation the customer able to download the filtered transactions----------", Status.PASS, false);


        waitForElementToBeClickable(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_ADVANCE_SEARCH), LONG_WAIT);
        clickOnElement(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_ADVANCE_SEARCH));

        waitForElementToBeClickable(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_APPLY_FILTERS), LONG_WAIT);

        sendKeysToElement(getAdvanceSearchFields(ElementType.span, MyAccountsConstants.FILTER_AMOUNT_FROM), amountFrom);
        sendKeysToElement(getAdvanceSearchFields(ElementType.span, MyAccountsConstants.FILTER_AMOUNT_TO), amountTo);
        clickOnElement(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_APPLY_FILTERS));

        waitForElementToBeInvisible(lblLoadingIcon, VERY_LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);

        waitForElementToBeClickable(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_ADVANCE_SEARCH), LONG_WAIT);
        clickOnElement(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_ADVANCE_SEARCH));

        scrollDownPage();
        advanceSearchByDateAndMonth(BillerConstants.NUMBER_TWENTY_TWENTY_FOUR, BillerConstants.JULY, BillerConstants.NUMBER_TWENTY_ONE, BillerConstants.APRIL, BillerConstants.NUMBER_TWENTY_ONE);

        clickOnElement(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_ADVANCE_SEARCH));

        //Select download
        waitForElementToBeClickable(getElementByTypeAndText(ElementType.button, MyAccountsConstants.DOWNLOAD), LONG_WAIT);
        clickOnElement(getElementByTypeAndText(ElementType.button, MyAccountsConstants.DOWNLOAD));

        if (isElementPresentBy(popUpPDFDownload(MyAccountsConstants.PDF_DOWNLOADED_SUCCESSFULLY)) && isElementClickable(btnClosePopup)) {
            addToReport("Validated downloaded toast message", Status.PASS, true);
        } else {
            addToReport("Failed to validate downloaded toast message", Status.FAIL);
        }

        //Wait for download to initiate - update this with dynamic once stabilized
        waitFor(SHORT_WAIT);
        clickOnElement(tfSearch);

        // Get the latest downloaded file
        File latestFile = getLatestDownloadedFile(downloadDirectory);

        if (latestFile != null) {
            // Extract text from the PDF
            String extractedText = extractTextFromPDF(latestFile.getAbsolutePath()).replace("/n", "");

            addToReport(" Latest downloaded pdf :  : '" + extractedText, Status.INFO, false);

            String expectedPeriod = MyAccountsConstants.STATEMENT_PERIOD +
                    String.format("%s-%02d-%s to %s-%02d-%s", BillerConstants.NUMBER_TWENTY_ONE, getMonthNumber(BillerConstants.APRIL), BillerConstants.NUMBER_TWENTY_TWENTY_FOUR, BillerConstants.NUMBER_TWENTY_ONE, getMonthNumber(BillerConstants.JULY), BillerConstants.NUMBER_TWENTY_TWENTY_FOUR);

            //validate date
            if (extractedText.contains(MyAccountsConstants.STATEMENT_PERIOD) && extractedText.contains(expectedPeriod)) {
                addToReport(" Validated statement period from " + expectedPeriod + " for the downloaded record", Status.PASS, false);
            } else {
                addToReport(" Failed to validate statement period from " + expectedPeriod + " of the downloaded record", Status.FAIL, false);
            }

            //validate payment id
            if (extractedText.contains(amountFrom)) {
                addToReport(" Validated filtered amount " + amountFrom + " for the downloaded record", Status.PASS, false);
            } else {
                addToReport(" Failed to validate amount of the downloaded record", Status.FAIL, false);
            }


            // ---- NAME VALIDATION ----
            if (extractedText.contains(name)) {
                addToReport("Validated account holder name: " + name, Status.PASS, false);
            } else {
                addToReport("Failed to validate account holder name: " + name, Status.FAIL, false);
            }

            // ---- ADDRESS VALIDATION ----
            if (extractedText.contains(address)) {
                addToReport("Validated address: " + address, Status.PASS, false);
            } else {
                addToReport("Failed to validate address: " + address, Status.FAIL, false);
            }

            // ---- ACCOUNT NUMBER VALIDATION ----
            if (extractedText.contains(accountNo)) {
                addToReport("Validated account number: " + accountNo, Status.PASS, false);
            } else {
                addToReport("Failed to validate account number: " + accountNo, Status.FAIL, false);
            }

            // ---- CURRENCY VALIDATION ----
            if (extractedText.contains(currency)) {
                addToReport("Validated currency: " + currency, Status.PASS, false);
            } else {
                addToReport("Failed to validate currency: " + currency, Status.FAIL, false);
            }

            for (String value : MyAccountsConstants.ACCOUNT_STATEMENT_VALIDATION_FIELDS) {
                if (extractedText.contains(value)) {
                    addToReport("Validated: " + value, Status.PASS, false);
                } else {
                    addToReport("Failed to validate: " + value, Status.FAIL, false);
                }
            }
        } else {
            addToReport(" Failed to download the payment record", Status.FAIL, false);
        }
        addToReport("----------End of validation of the customer able to download the filtered transactions----------", Status.PASS, true);

    }

    String cardNo, customerAccountNumber, expiryDate, cardStatus, cardType, availableBalance;

    /**
     * Validates the details of a credit card by navigating to the specified tab and verifying the tile header
     * and associated account number
     *
     * @param tabName       the name of the tab where the credit card details are displayed
     * @param tileHeader    the expected header of the credit card tile
     * @param accountNumber the credit card account number to validate
     */
    public void ValidateCreditCardDetails(String tabName, String tileHeader, String accountNumber) {
        addToReport("----------Start of validation of user should be able to view the Credit card details----------", Status.PASS, false);

        waitForElementToBeInvisible(lblLoadingIcon, VERY_LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);

        //Selecet tab and account
        selectTabAndValidate(tabName, tileHeader);

        if (isElementPresentBy(icnAccounts)) {

            waitForElementToBeClickable(icnAccounts, VERY_LONG_WAIT);
            //Obtain pagination value
            cardCount = CommonUtils.splitText(getAttributeOrText(icnAccounts, "text"), "/");
            //Obtain the accounts record count
            recordCount = Integer.parseInt(cardCount[1]);
            if (recordCount != 0) {
                for (int incr = 0; incr < recordCount; incr++) {

                    String accountNumberRetrived = getAttributeOrText(lblAccountNumber, "text").replace(" ", "");

                    if (!accountNumberRetrived.equals(accountNumber)) {
                        //Navigate to next account
                        clickOnElement(btnNextArrow);
                        waitForElementToBeInvisible(lblLoadingIcon, VERY_LONG_WAIT);
                        waitForElementToBeInvisible(lblAccountListLoading, VERY_LONG_WAIT);

                    } else {
                        addToReport("Expected account " + accountNumber + " found", Status.FAIL, true);
                        break;
                    }
                }
            } else {
                addToReport("No accounts", Status.FAIL, true);
            }

        }

        //Obtain text
        availableBalance = getAttributeOrText(lblCreditCardAvailableBalance(MyAccountsConstants.AVAILABLE), "text");
        cardNo = getAttributeOrText(lblCreditCardNumber(MyAccountsConstants.AVAILABLE), "text");
        customerAccountNumber = getAttributeOrText(lblCreditCardCustAccNumber(MyAccountsConstants.CAN), "text");
        cardStatus = getAttributeOrText(lblInactiveCardStatus, "text");
        expiryDate = getAttributeOrText(lblCreditCardCustAccNumber(MyAccountsConstants.EXPIRY_DATE), "text");

        // card type
        if (isElementPresentBy(imgMasterCardLogo)) {
            addToReport("Successfully obtained card type ", Status.PASS, false);
            cardType = MyAccountsConstants.MASTER_INACTIVE;
        } else if (isElementPresentBy(imgVisaCardLogo)) {
            addToReport("Successfully obtained card type ", Status.PASS, false);
            cardType = MyAccountsConstants.VSDC_GOLD;

        } else {
            addToReport("Failed to validate card type", Status.FAIL, true);
        }


        // Credit Card Number
        if (cardNo.equals(getAttributeOrText(lblCreditCardDetails(MyAccountsConstants.CARD_NUMBER), "text"))) {
            addToReport("Successfully validated " + MyAccountsConstants.CARD_NUMBER + " : '" + cardNo + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate " + MyAccountsConstants.CARD_NUMBER + ". Expected: '" + cardNo + "', but received: '" + getAttributeOrText(lblCreditCardDetails(MyAccountsConstants.CARD_NUMBER), "text") + "'", Status.FAIL, true);
        }

        // Customer Account Number
        if (customerAccountNumber.equals(getAttributeOrText(lblCreditCardDetails(MyAccountsConstants.CUSTOMER_ACCOUNT_NUMBER), "text"))) {
            addToReport("Successfully validated " + MyAccountsConstants.CUSTOMER_ACCOUNT_NUMBER + " : '" + customerAccountNumber + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate " + MyAccountsConstants.CUSTOMER_ACCOUNT_NUMBER + ". Expected: '" + customerAccountNumber + "', but received: '" + getAttributeOrText(lblCreditCardDetails(MyAccountsConstants.CUSTOMER_ACCOUNT_NUMBER), "text") + "'", Status.FAIL, true);
        }

        // Expiry Date
        if (expiryDate.equals(getAttributeOrText(lblCreditCardDetails(MyAccountsConstants.EXPIRY_DATE), "text"))) {
            addToReport("Successfully validated " + MyAccountsConstants.EXPIRY_DATE + " : '" + expiryDate + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate " + MyAccountsConstants.EXPIRY_DATE + ". Expected: '" + expiryDate + "', but received: '" + getAttributeOrText(lblCreditCardDetails(MyAccountsConstants.EXPIRY_DATE), "text") + "'", Status.FAIL, true);
        }

        // Card Status
        if (cardStatus.equals(getAttributeOrText(lblCreditCardDetails(MyAccountsConstants.CARD_STATUS), "text"))) {
            addToReport("Successfully validated " + MyAccountsConstants.CARD_STATUS + " : '" + cardStatus + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate " + MyAccountsConstants.CARD_STATUS + ". Expected: '" + cardStatus + "', but received: '" + getAttributeOrText(lblCreditCardDetails(MyAccountsConstants.CARD_STATUS), "text") + "'", Status.FAIL, true);
        }

        // Card Type
        if (cardType.equals(getAttributeOrText(lblCreditCardDetails(MyAccountsConstants.CARD_TYPE), "text"))) {
            addToReport("Successfully validated " + MyAccountsConstants.CARD_TYPE + " : '" + cardType + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate " + MyAccountsConstants.CARD_TYPE + ". Expected: '" + cardType + "', but received: '" + getAttributeOrText(lblCreditCardDetails(MyAccountsConstants.CARD_TYPE), "text") + "'", Status.FAIL, true);
        }

        // Available Balance
        if (availableBalance.equals(getAttributeOrText(lblCreditCardDetails(MyAccountsConstants.AVAILABLE_BALANCE), "text"))) {
            addToReport("Successfully validated " + MyAccountsConstants.AVAILABLE_BALANCE + " : '" + availableBalance + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate " + MyAccountsConstants.AVAILABLE_BALANCE + ". Expected: '" + availableBalance + "', but received: '" + getAttributeOrText(lblCreditCardDetails(MyAccountsConstants.AVAILABLE_BALANCE), "text") + "'", Status.FAIL, true);
        }

        //Validate supplementary card
        if (isElementPresentBy(getElementByTypeAndText(ElementType.div, MyAccountsConstants.SUPPLEMENTARY_CARD))) {
            addToReport("Successfully validated header " + MyAccountsConstants.SUPPLEMENTARY_CARD, Status.PASS, false);
        } else {
            addToReport("Failed to validate header " + MyAccountsConstants.SUPPLEMENTARY_CARD, Status.FAIL, true);
        }

        recordCount = isElementsPresentBy(tblTransactionRows);
        if (recordCount != 0) {
            for (int incr = 1; incr <= recordCount; incr++) {

                //Validate supplementary card table headers
                for (String header : MyAccountsConstants.SUPPLEMENTARY_CARD_TABLE_HEADERS) {

                    if (isElementPresentBy(getHeaderByName(header))) {
                        addToReport("Table header '" + header + "' is present under account " + accountNumber, Status.PASS, false);
                    } else {
                        addToReport("Missing table header '" + header + "'  under account number " + accountNumber, Status.FAIL, true);
                    }

                }
                if (getAttributeOrText(tblCellRecord(1, incr), "text").equals(null) || getAttributeOrText(tblCellRecord(2, incr), "text").equals(null)) {
                    addToReport("Invalid content under supplementary cards under account :" + accountNumber, Status.FAIL, true);
                } else {
                    addToReport("Successfully obtained card Number  " + getAttributeOrText(tblCellRecord(1, incr), "text"), Status.PASS, false);
                    addToReport("Successfully obtained card Name " + getAttributeOrText(tblCellRecord(2, incr), "text"), Status.PASS, false);
                }


            }
        } else {
            addToReport("No supplementary cards were found ", Status.FAIL, true);
        }

        addToReport("----------End of validation of user should be able to view the Credit card details----------", Status.PASS, false);
    }


    /**
     * Performs an advanced search using a date range
     * This method is typically used to filter results between a specific start and end date
     *
     * @param year    the year to be selected in the search criteria
     * @param month   the month to be selected
     * @param dayFrom the starting day of the date range
     * @param dayTo   the ending day of the date range (e.g., "15" for the 15th day of the month)
     */
    public void advanceSearchByDate(String year, String month, String dayFrom, String dayTo) {
        addToReport("----------Start of advance search using date----------", Status.PASS, false);

        waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);
        scrollToWebElement(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_ADVANCE_SEARCH));
        clickOnElement(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_ADVANCE_SEARCH));

        waitForElementToBeClickable(getAdvanceSearchFields(ElementType.span, MyAccountsConstants.TRANSACTION_DATE), LONG_WAIT);

        clickOnElement(getAdvanceSearchFields(ElementType.span, MyAccountsConstants.TRANSACTION_DATE));

        waitForElementToBeClickable(getElementByTypeAndText(ElementType.button, MyAccountsConstants.BUTTON_TEXT_CLEAR), LONG_WAIT);

        // Select the same 'To Account' again from the dropdown
        selectFromDropdown(ddAdvancedSearchYear, year, "visibletext");
        selectFromDropdown(ddAdvancedSearchMonth, month, "visibletext");


        clickOnElement(lnkAdvancedSearchDay(dayFrom));
        clickOnElement(lnkAdvancedSearchDay(dayTo));
        addToReport("Entered search values ", Status.INFO, true);
        clickOnElement(getElementByTypeAndText(ElementType.button, MyAccountsConstants.BUTTON_APPLY));
        waitForElementToBeClickable(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_APPLY_FILTERS), LONG_WAIT);
        clickOnElement(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_APPLY_FILTERS));

        waitForElementToBeInvisible(lblLoadingIcon, VERY_LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);

        if (isElementsPresentBy(getElementByTypeAndText(ElementType.span, MyAccountsConstants.NO_DATA_FOUND_LC), SHORT_WAIT) > 0) {
            waitForElementToBeClickable(tblRows, MODERATE_WAIT);
        } else {
            addToReport("No data found for advanced search", Status.FAIL, true);
        }


        addToReport("----------End of advance search using date----------", Status.PASS, false);
    }

    /**
     * Performs an advanced search using a date range
     * This method is typically used to filter results between a specific start and end date
     *
     * @param year          the year to be selected in the search criteria
     * @param startingMonth the month to be selected
     * @param dayFrom       the starting day of the date range
     * @param dayTo         the ending day of the date range (e.g., "15" for the 15th day of the month)
     */
    public void advanceSearchByDateAndMonth(String year, String startingMonth, String dayFrom, String endingMonth, String dayTo) {
        addToReport("----------Start of advance search using date----------", Status.PASS, false);

        waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);
        scrollToWebElement(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_ADVANCE_SEARCH));
        clickOnElement(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_ADVANCE_SEARCH));

        waitForElementToBeClickable(getAdvanceSearchFields(ElementType.span, MyAccountsConstants.TRANSACTION_DATE), LONG_WAIT);

        clickOnElement(getAdvanceSearchFields(ElementType.span, MyAccountsConstants.TRANSACTION_DATE));

        waitForElementToBeClickable(getElementByTypeAndText(ElementType.button, MyAccountsConstants.BUTTON_TEXT_CLEAR), LONG_WAIT);

        // Select the same 'To Account' again from the dropdown
        selectFromDropdown(ddAdvancedSearchYear, year, "visibletext");
        selectFromDropdown(ddAdvancedSearchMonth, startingMonth, "visibletext");
        clickOnElement(lnkAdvancedSearchDay(dayFrom));

        selectFromDropdown(ddAdvancedSearchMonth, endingMonth, "visibletext");
        clickOnElement(lnkAdvancedSearchDay(dayTo));
        addToReport("Entered search values ", Status.INFO, true);
        clickOnElement(getElementByTypeAndText(ElementType.button, MyAccountsConstants.BUTTON_APPLY));
        waitForElementToBeClickable(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_APPLY_FILTERS), LONG_WAIT);
        clickOnElement(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_APPLY_FILTERS));

        waitForElementToBeInvisible(lblLoadingIcon, VERY_LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);

        if (isElementsPresentBy(getElementByTypeAndText(ElementType.span, MyAccountsConstants.NO_DATA_FOUND_LC), SHORT_WAIT) > 0) {
            waitForElementToBeClickable(tblRows, MODERATE_WAIT);
        } else {
            addToReport("No data found for advanced search", Status.FAIL, true);
        }


        addToReport("----------End of advance search using date----------", Status.PASS, false);
    }

    /**
     * select tab and validate it's relevant tile for savings and current accounts
     *
     * @param tabName    Table Name
     * @param tileHeader Tile header
     */
    public void navigateToSavingsAndCurrentAccountAndValidate(String tabName, String tileHeader) {

        addToReport("----------Start of validation of that when user click on 'My Accounts' item in top navigation menu all account categories and other product lists displayed----------", Status.PASS, false);

        waitForElementToBeInvisible(lblLoadingIcon, VERY_LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);
        waitForElementToBeInvisible(lblAccountListLoading, VERY_LONG_WAIT);
        addToReport("----------End of validation of that when user click on 'My Accounts' item in top navigation menu all account categories and other product lists displayed----------", Status.PASS, true);

        //Validate the tab and relative header
        addToReport("----------Start of validation of that when user selects an account, category or a other product list of all available records is displaying to the user ----------", Status.PASS, false);
        selectTabAndValidate(tabName, tileHeader);

        //Validate the selected tile and its relevant data loaded at list
        waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);

        rowCount = isElementsPresentBy(icnAccounts, SHORT_WAIT);
        if (rowCount > 0) {
            waitForElementToBeClickable(icnAccounts, LONG_WAIT);

            //Obtain pagination value
            cardCount = CommonUtils.splitText(getAttributeOrText(icnAccounts, "text"), "/");
            //Obtain the accounts record count
            recordCount = Integer.parseInt(cardCount[1]);

            //Validate the presence of arrows
            //Check if data found
            if (isElementPresentBy(btnPreviousArrow, MODERATE_WAIT)) {
                addToReport(" Previous arrow found ", Status.PASS, true);
            } else {
                addToReport(" Previous arrow not found ", Status.FAIL, true);
            }
            if (isElementPresentBy(btnNextArrow, MODERATE_WAIT)) {
                addToReport(" Next arrow found ", Status.PASS, true);
            } else {
                addToReport(" Next arrow not found ", Status.FAIL, true);
            }

        } else {
            recordCount = 1;
            if (isElementsPresentBy(btnPreviousArrow, MODERATE_WAIT) > 0) {
                addToReport(" Previous arrow found ", Status.FAIL, true);

            } else {
                addToReport(" Previous arrow not found ", Status.PASS, true);
            }
            if (isElementsPresentBy(btnNextArrow, MODERATE_WAIT) > 0) {
                addToReport(" Next arrow found ", Status.FAIL, true);

            } else {
                addToReport(" Next arrow not found ", Status.PASS, true);
            }

        }
        addToReport("Number of records under : " + tabName + " is  " + recordCount, Status.PASS, false);
        addToReport("----------End of validation of that when user selects an account, category or a other product list of all available records is displaying to the user ----------", Status.PASS, true);
        if (recordCount != 0) {
            for (int incr = 0; incr < recordCount; incr++) {
                addToReport("----------Start of validation of that on click of a record user is navigating detail view of that specific record ----------", Status.PASS, false);
                currencyAndBal = getTextFromElement(lblCurrencyAndAvailableBalance);
                currentTab = tabName;

                waitForElementToBeInvisible(lblAccountListLoading, VERY_LONG_WAIT);
                waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
                waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);
                waitForElementToBeClickable(lblAccountNumber, LONG_WAIT);

                //Obtain account number and validate
                accountNumber = getAttributeOrText(lblAccountNumber, "text");
                accountNumber = accountNumber.replace(MyAccountsConstants.CURRENT_OUTSTANDING, "").trim().replaceAll("\\s+", "");
                if (isElementPresentBy(lblHighlightedAccountNo(accountNumber))) {
                    addToReport("Successfully validated account number : '" + accountNumber + "' under the tab " + tabName, Status.PASS, false);
                } else {
                    addToReport("Failed to validated account number : '" + accountNumber + "' under the tab " + tabName, Status.FAIL, true);
                }
                if (isElementPresentBy(lblHighlightedListContent(currencyAndBal))) {
                    addToReport("Successfully validated currency and balance : '" + currencyAndBal + "' under the tab " + tabName, Status.PASS, true);
                } else {
                    addToReport("Failed to validated currency and balance '" + currencyAndBal + "' under the tab " + tabName, Status.FAIL, true);
                }
                addToReport("----------End of validation of that on click of a record user is navigating detail view of that specific record ----------", Status.PASS, false);
                addToReport("----------Start of validation of the content of operative Account list and functional behaviour ----------", Status.PASS, false);

                //Validate  account list table headers
                for (String header : MyAccountsConstants.SAVINGS_ACCOUNTS_LIST_TABLE_HEADERS) {

                    if (isElementPresentBy(getHeaderByName(header))) {
                        addToReport("Table header '" + header + "' is present under tab '" + tabName + "'", Status.PASS, false);
                    } else {
                        addToReport("Missing table header '" + header + "' under tab '" + tabName + "'", Status.FAIL, true);
                    }
                }

                //Select the account
                clickOnElement(lblHighlightedAccountNo(accountNumber));
                waitForElementToBeClickable(lblAccountHistoryAccountNo(MyAccountsConstants.ACCOUNT_HISTORY), VERY_LONG_WAIT);
                waitForElementToBeInvisible(lblLoadingIcon, VERY_LONG_WAIT);

                //Remove this once finacle dates are synchronized
                advanceSearchByDate(BillerConstants.NUMBER_TWENTY_TWENTY_FOUR, BillerConstants.JULY, BillerConstants.NUMBER_TWENTY_ONE, BillerConstants.NUMBER_TWENTY_ONE);

                aHAccountNo = getTextFromElement(lblAccountHistoryAccountNo(MyAccountsConstants.ACCOUNT_HISTORY));
                if (aHAccountNo.equals(accountNumber)) {
                    addToReport("Successfully validated account number : '" + accountNumber + "' under the account history", Status.PASS, true);
                } else {
                    addToReport("Failed to validated account number : '" + accountNumber + "' under the account history ", Status.FAIL, true);
                }

                //Check if data found
                if (isElementsPresentBy(getElementByTypeAndText(ElementType.span, MyAccountsConstants.NO_DATA_FOUND_LC), VERY_SHORT_WAIT) > 1) {
                    addToReport(" No Data found under the deposit account " + aHAccountNo, Status.INFO, true);
                } else {
                    //Validate selected account table headers
                    for (String header : MyAccountsConstants.ACCOUNT_TABLE_HEADERS) {

                        if (isElementPresentBy(getHeaderByName(header))) {
                            addToReport("Table header '" + header + "' is present under tab '" + tabName + " for account number " + accountNumber, Status.PASS, false);
                        } else if (isElementPresentBy(getHeaderByName(MyAccountsConstants.AMOUNT_USD)) || isElementPresentBy(getHeaderByName(MyAccountsConstants.BALANCE_USD))) {
                            addToReport("Table header '" + MyAccountsConstants.AMOUNT_USD + "' or '" + MyAccountsConstants.BALANCE_USD + "' is present under tab '" + tabName + " for account number " + accountNumber, Status.PASS, false);
                        } else {
                            addToReport("Missing table header '" + header + "' under tab '" + tabName + " for account number " + accountNumber, Status.FAIL, true);
                        }
                    }
                    //Validate Table Rows Loaded
                    rowCount = isElementsPresentBy(tblTransactionRows);
                    if (rowCount > 0) {
                        addToReport("Table loaded with " + rowCount + " record(s) under tab '" + tabName + "'", Status.PASS, true);
                    } else {
                        addToReport("No records found in table under tab '" + tabName + "'", Status.FAIL, true);
                    }
                }

                validateTransactionDates(BillerConstants.NUMBER_TWENTY_ONE + "-" + BillerConstants.NUMBER_SEVEN + "-" + BillerConstants.NUMBER_TWENTY_TWENTY_FOUR, tblTransactionRows, BillerConstants.DATE);

                scrollPageToTop();

                //Click on view list
                waitForElementToBeClickable(getElementByTypeAndText(ElementType.span, MyAccountsConstants.VIEW_LIST), VERY_LONG_WAIT);
                clickOnElement(getElementByTypeAndText(ElementType.span, MyAccountsConstants.VIEW_LIST));
                addToReport("----------End of validation of the content of operative Account list and functional behaviour ----------", Status.PASS, true);

                //Navigate to next account
                clickOnElement(btnNextArrow);

                //WaitForElementPresence(lblLoadingIcon);
                waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);


            }
        } else {
            addToReport("No cards are available", Status.FAIL, true);
        }
        addToReport("----------End of validation of that when user click on 'My Accounts' item in top navigation menu all account categories and other product lists displayed----------", Status.PASS, false);

    }

    /**
     * Validates if each row in the transaction table contains the expected date.
     *
     * @param expectedDate     Expected date in format dd-MM-yyyy
     * @param tableRowsLocator Locator for transaction table rows
     * @param tabName          Name of the tab for reporting purposes
     */
    public void validateTransactionDates(String expectedDate, By tableRowsLocator, String tabName) {
        int rowCount = isElementsPresentBy(tableRowsLocator);
        if (rowCount > 0) {
            addToReport("Table loaded with " + rowCount + " record(s) under tab '" + tabName + "'", Status.PASS, true);

            List<WebElement> transactionRows = getDriver().findElements(tableRowsLocator);
            boolean allDatesMatch = true;

            for (int i = 0; i < transactionRows.size(); i++) {
                WebElement dateCell = transactionRows.get(i).findElement(By.xpath("./td[1]")); // 1st column = Date
                String actualDate = dateCell.getText().trim();

                if (expectedDate.equals(actualDate)) {
                    addToReport("Row " + (i + 1) + " date validated: " + actualDate, Status.PASS, false);
                } else {
                    addToReport("Row " + (i + 1) + " date mismatch. Expected: '" + expectedDate + "', Found: '" + actualDate + "'", Status.FAIL, true);
                    allDatesMatch = false;
                }
            }

            if (allDatesMatch) {
                addToReport("All dates match expected date: " + expectedDate, Status.PASS, false);
            } else {
                addToReport("One or more dates did not match expected date.", Status.FAIL, true);
            }

        } else {
            addToReport("No records found in table under tab '" + tabName + "'", Status.FAIL, true);
        }
    }


    /**
     * select tab and validate it's relevant tile for deposit account
     *
     * @param tabName    Table Name
     * @param tileHeader Tile header
     */
    public void navigateToDepositAccountAndValidate(String tabName, String tileHeader) {

        addToReport("----------Start of validation of that when user click on 'My Accounts' item in top navigation menu all account categories and other product lists displayed----------", Status.PASS, false);

        waitForElementToBeInvisible(lblLoadingIcon, VERY_LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);
        waitForElementToBeInvisible(lblAccountListLoading, VERY_LONG_WAIT);
        addToReport("----------End of validation of that when user click on 'My Accounts' item in top navigation menu all account categories and other product lists displayed----------", Status.PASS, true);

        //Validate the tab and relative header
        addToReport("----------Start of validation of that when user selects an account, category or a other product list of all available records is displaying to the user ----------", Status.PASS, false);
        selectTabAndValidate(tabName, tileHeader);

        //Validate the selected tile and its relevant data loaded at list
        waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);

        rowCount = isElementsPresentBy(icnAccounts, SHORT_WAIT);
        if (rowCount > 0) {
            waitForElementToBeClickable(icnAccounts, LONG_WAIT);

            //Obtain pagination value
            cardCount = CommonUtils.splitText(getAttributeOrText(icnAccounts, "text"), "/");
            //Obtain the accounts record count
            recordCount = Integer.parseInt(cardCount[1]);
            //Check if data found
            if (isElementPresentBy(btnPreviousArrow, MODERATE_WAIT)) {
                addToReport(" Previous arrow found ", Status.PASS, true);
            } else {
                addToReport(" Previous arrow not found ", Status.FAIL, true);
            }
            if (isElementPresentBy(btnNextArrow, MODERATE_WAIT)) {
                addToReport(" Next arrow found ", Status.PASS, true);
            } else {
                addToReport(" Next arrow not found ", Status.FAIL, true);
            }

        } else {
            recordCount = 1;
            if (isElementsPresentBy(btnPreviousArrow, MODERATE_WAIT) > 0) {
                addToReport(" Previous arrow found ", Status.FAIL, true);

            } else {
                addToReport(" Previous arrow not found ", Status.PASS, true);
            }
            if (isElementsPresentBy(btnNextArrow, MODERATE_WAIT) > 0) {
                addToReport(" Next arrow found ", Status.FAIL, true);

            } else {
                addToReport(" Next arrow not found ", Status.PASS, true);
            }
        }
        addToReport("Number of records under : " + tabName + " is  " + recordCount, Status.PASS, false);

        addToReport("----------End of validation of that when user selects an account, category or a other product list of all available records is displaying to the user ----------", Status.PASS, true);
        if (recordCount != 0) {
            for (int incr = 0; incr < recordCount; incr++) {
                addToReport("----------Start of validation of that on click of a record user is navigating detail view of that specific record ----------", Status.PASS, false);
                currencyAndBal = getTextFromElement(lblCurrencyAndAvailableBalance);

                currentTab = tabName;

                waitForElementToBeInvisible(lblAccountListLoading, VERY_LONG_WAIT);
                waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
                waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);
                waitForElementToBeClickable(lblAccountNumber, LONG_WAIT);

                addToReport("----------Start of validation of the content of fixed deposits list and the functional behaviour ----------", Status.PASS, false);
                //Obtain account number and validate
                accountNumber = getAttributeOrText(lblAccountNumber, "text");
                accountNumber = accountNumber.replace(MyAccountsConstants.CURRENT_OUTSTANDING, "").trim().replaceAll("\\s+", "");
                if (isElementPresentBy(lblHighlightedAccountNo(accountNumber))) {
                    addToReport("Successfully validated account number : '" + accountNumber + "' under the tab " + tabName, Status.PASS, false);
                } else {
                    addToReport("Failed to validated account number : '" + accountNumber + "' under the tab " + tabName, Status.FAIL, true);
                }
                if (isElementPresentBy(lblHighlightedListContent(currencyAndBal))) {
                    addToReport("Successfully validated currency and balance : '" + currencyAndBal + "' under the tab " + tabName, Status.PASS, true);
                } else {
                    addToReport("Failed to validated currency and balance '" + currencyAndBal + "' under the tab " + tabName, Status.FAIL, true);
                }
                //Validate Table Headers
                for (String header : MyAccountsConstants.DEPOSITS_TABLE_HEADERS) {
                    if (isElementPresentBy(getHeaderByName(header))) {
                        addToReport("Table header '" + header + "' is present under tab '" + tabName + "'", Status.PASS, false);
                    } else {
                        addToReport("Missing table header '" + header + "' under tab '" + tabName + "'", Status.FAIL, true);
                    }
                }
                waitForElementToBeClickable(lblHighlightedAccountNo(accountNumber), MODERATE_WAIT);
                clickOnElement(lblHighlightedAccountNo(accountNumber));
                waitForElementToBeInvisible(lblLoadingIcon, VERY_LONG_WAIT);
                waitForElementToBeInvisible(lblAccountListLoading, VERY_LONG_WAIT);

                aHAccountNo = getTextFromElement(lblAccountHistoryAccountNo(MyAccountsConstants.RENEWAL_HISTORY));
                if (aHAccountNo.equals(accountNumber)) {
                    addToReport("Successfully validated account number : '" + accountNumber + "' under the account history", Status.PASS, true);
                } else {
                    addToReport("Failed to validated account number : '" + accountNumber + "' under the account history ", Status.FAIL, true);
                }

                int count = isElementsPresentBy(lblNoDataFound(MyAccountsConstants.NO_DATA_FOUND), SHORT_WAIT);
                //Check if data found
                if (count > 0) {
                    addToReport(" No Data found under the deposit account " + aHAccountNo, Status.INFO, true);
                } else {
                    //Validate renewal history Table Headers
                    for (String header : MyAccountsConstants.DEPOSITS_RENEWAL_HISTORY_TABLE_HEADERS) {
                        if (isElementPresentBy(getHeaderByName(header))) {
                            addToReport("Table header '" + header + "' is present under tab '" + tabName + "'", Status.PASS, false);
                        } else if (isElementPresentBy(getHeaderByName(MyAccountsConstants.AMOUNT_USD)) || isElementPresentBy(getHeaderByName(MyAccountsConstants.ACCOUNT_BALANCE_USD))) {
                            addToReport("Table header '" + MyAccountsConstants.AMOUNT_USD + "' or '" + MyAccountsConstants.ACCOUNT_BALANCE_USD + "' is present under tab '" + tabName + " for account number " + accountNumber, Status.PASS, false);
                        } else {
                            addToReport("Missing table header '" + header + "' under tab '" + tabName + "'", Status.FAIL, true);
                        }
                    }
                    //Validate Table Rows Loaded
                    rowCount = isElementsPresentBy(tblTransactionRows);
                    if (rowCount > 0) {
                        addToReport("Table loaded with " + rowCount + " record(s) under tab '" + tabName + "'", Status.PASS, true);
                    } else {
                        addToReport("No records found in table under tab '" + tabName + "'", Status.FAIL, true);
                    }

                }
                scrollPageToTop();
                waitForElementToBeClickable(getElementByTypeAndText(ElementType.span, MyAccountsConstants.VIEW_LIST), VERY_LONG_WAIT);
                clickOnElement(getElementByTypeAndText(ElementType.span, MyAccountsConstants.VIEW_LIST));

                addToReport("----------End of validation of the content of fixed deposits list and the functional behaviour----------", Status.PASS, true);

                if (recordCount > 1) {
                    //Navigate to next account
                    clickOnElement(btnNextArrow);

                    //WaitForElementPresence(lblLoadingIcon);
                    waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
                }
            }
        } else {
            addToReport("No cards are available", Status.FAIL, true);
        }
        addToReport("----------End of validation of that when user click on 'My Accounts' item in top navigation menu all account categories and other product lists displayed----------", Status.PASS, false);

    }

    /**
     * select tab and validate it's relevant tile for loan account
     *
     * @param tabName    Table Name
     * @param tileHeader Tile header
     */
    public void navigateToLoanAccountAndValidate(String tabName, String tileHeader) {

        addToReport("----------Start of validation of the content of loans list and the functional behaviour ----------", Status.PASS, false);
        //select tab
        selectTabAndValidate(tabName, tileHeader);

        waitForElementToBeInvisible(lblAccountListLoading, VERY_LONG_WAIT);
        waitForElementToBeInvisible(lblLoadingIcon, MODERATE_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);
        waitForElementToBeClickable(lblAccountNumber, LONG_WAIT);

        //Obtain account number and validate
        accountNumber = getAttributeOrText(lblAccountNumber, "text");
        accountNumber = accountNumber.replace(MyAccountsConstants.CURRENT_OUTSTANDING, "").trim().replaceAll("\\s+", "");
        if (isElementPresentBy(lblHighlightedAccountNo(accountNumber))) {
            addToReport("Successfully validated account number : '" + accountNumber + "' under the tab " + tabName, Status.PASS, false);
        } else {
            addToReport("Failed to validated account number : '" + accountNumber + "' under the tab " + tabName, Status.FAIL, true);
        }

        rowCount = isElementsPresentBy(icnAccounts, SHORT_WAIT);
        if (rowCount > 0) {
            waitForElementToBeClickable(icnAccounts, LONG_WAIT);

            //Obtain pagination value
            cardCount = CommonUtils.splitText(getAttributeOrText(icnAccounts, "text"), "/");
            //Obtain the accounts record count
            recordCount = Integer.parseInt(cardCount[1]);

        } else {
            recordCount = 1;
        }

        currencyAndBal = getTextFromElement(lblCurrencyAndAvailableBalance);

        if (isElementPresentBy(lblHighlightedListContent(currencyAndBal))) {
            addToReport("Successfully validated currency and balance : '" + currencyAndBal + "' under the tab " + tabName, Status.PASS, true);
        } else {
            addToReport("Failed to validated currency and balance '" + currencyAndBal + "' under the tab " + tabName, Status.FAIL, true);
        }
        //Validate Table Headers
        for (String header : MyAccountsConstants.LOANS_TABLE_HEADERS) {
            if (isElementPresentBy(getHeaderByName(header))) {
                addToReport("Table header '" + header + "' is present under tab '" + tabName + "'", Status.PASS, false);
            } else {
                addToReport("Missing table header '" + header + "' under tab '" + tabName + "'", Status.FAIL, true);
            }
        }

        clickOnElement(lblHighlightedAccountNo(accountNumber));
        waitForElementToBeClickable(lblAccountNumberByHeader(MyAccountsConstants.PAID_INSTALLMENTS), VERY_LONG_WAIT);
        waitForElementToBeInvisible(lblLoadingIcon, VERY_LONG_WAIT);
        waitForElementToBeInvisible(lblAccountListLoading, VERY_LONG_WAIT);

        //Remove this once finacle dates are synchronized
        //advanceSearchByDate(BillerConstants.NUMBER_TWENTY_TWENTY_FOUR, BillerConstants.JULY, BillerConstants.NUMBER_TWENTY_ONE, BillerConstants.NUMBER_TWENTY_ONE);

        aHAccountNo = getTextFromElement(lblAccountNumberByHeader(MyAccountsConstants.PAID_INSTALLMENTS));
        accountNumber = aHAccountNo.split("-")[1].trim();

        if (aHAccountNo.contains(accountNumber)) {
            addToReport("Successfully validated loan account number : '" + accountNumber + "' under the account history", Status.PASS, true);
        } else {
            addToReport("Failed to validated loan account number : '" + accountNumber + "' under the account history ", Status.FAIL, true);
        }
        //Check if data found
        if (isElementPresentBy(lblLoanNoDataFound(MyAccountsConstants.NO_DATA_FOUND), VERY_SHORT_WAIT)) {
            addToReport(" No Data found under the  paid installment for account  " + aHAccountNo, Status.INFO, true);
        } else {
            //Validate Table Headers
            for (String header : MyAccountsConstants.LOANS_PAID_TABLE_HEADERS) {
                if (isElementPresentBy(getHeaderByName(header))) {
                    addToReport("Table header '" + header + "' is present under tab '" + tabName + "'", Status.PASS, false);
                } else {
                    addToReport("Missing table header '" + header + "' under tab '" + tabName + "'", Status.FAIL, true);
                }
            }
            //Validate Table Rows Loaded
            rowCount = isElementsPresentBy(tblTransactionRows);
            if (rowCount > 0) {
                addToReport("Table loaded with " + rowCount + " record(s) under tab '" + tabName + "'", Status.PASS, true);
            } else {
                addToReport("No records found in table under tab '" + tabName + "'", Status.FAIL, true);
            }
        }
        waitForElementToBeClickable(getElementByTypeAndText(ElementType.div, MyAccountsConstants.PARTIAL_INSTALLMENTS), 30);
        clickOnElement(getElementByTypeAndText(ElementType.div, MyAccountsConstants.PARTIAL_INSTALLMENTS));
        if (isElementPresentBy(lblLoanNoDataFound(MyAccountsConstants.NO_DATA_FOUND))) {
            addToReport(" No Data found under the  partial installment for account  " + aHAccountNo, Status.INFO, true);
        } else {
            //Validate Table Headers
            for (String header : MyAccountsConstants.LOANS_PARTIAL_TABLE_HEADERS) {
                if (isElementPresentBy(getHeaderByName(header))) {
                    addToReport("Table header '" + header + "' is present under tab '" + tabName + "'", Status.PASS, false);
                } else {
                    addToReport("Missing table header '" + header + "' under tab '" + tabName + "'", Status.FAIL, true);
                }
            }
            //Validate Table Rows Loaded
            rowCount = isElementsPresentBy(tblTransactionRows);
            if (rowCount > 0) {
                addToReport("Table loaded with " + rowCount + " record(s) under tab '" + tabName + "'", Status.PASS, true);
            } else {
                addToReport("No records found in table under tab '" + tabName + "'", Status.FAIL, true);
            }
        }
        scrollPageToTop();
        waitForElementToBeClickable(getElementByTypeAndText(ElementType.span, MyAccountsConstants.VIEW_LIST), 30);
        clickOnElement(getElementByTypeAndText(ElementType.span, MyAccountsConstants.VIEW_LIST));
        addToReport("----------End of validation of the content of loans list and the functional behaviour ----------", Status.PASS, true);
        if (recordCount > 1) {
            //Navigate to next account
            clickOnElement(btnNextArrow);
            //WaitForElementPresence(lblLoadingIcon);
            waitForElementToBeInvisible(lblLoadingIcon, MODERATE_WAIT);
        } else {
            addToReport("No cards are available", Status.INFO, true);
        }

        addToReport("----------End of validation of that when user click on 'My Accounts' item in top navigation menu all account categories and other product lists displayed----------", Status.PASS, false);

    }

    /**
     * select tab and validate it's relevant tile for pawning account
     *
     * @param tabName    Table Name
     * @param tileHeader Tile header
     */
    public void navigateToPawningAccountAndValidate(String tabName, String tileHeader) {
        //select tab
        selectTabAndValidate(tabName, tileHeader);

        waitForElementToBeInvisible(lblAccountListLoading, VERY_LONG_WAIT);
        waitForElementToBeInvisible(lblLoadingIcon, MODERATE_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);
//        waitForElementToBeClickable(lblAccountNumber, LONG_WAIT);

        addToReport("----------Start of validation of the content of pawning list and the functional behaviour ----------", Status.PASS, false);
        //Obtain pawning number and validate
        accountNumber = getAttributeOrText(lblPawningAccountNumber, "text");
        currencyAndBal = getTextFromElement(lblCurrencyAndAvailableBalance);

        rowCount = isElementsPresentBy(icnAccounts, SHORT_WAIT);
        if (rowCount > 0) {
            waitForElementToBeClickable(icnAccounts, LONG_WAIT);

            //Obtain pagination value
            cardCount = CommonUtils.splitText(getAttributeOrText(icnAccounts, "text"), "/");
            //Obtain the accounts record count
            recordCount = Integer.parseInt(cardCount[1]);

        } else {
            recordCount = 1;
        }
        waitForElementToBeInvisible(lblAccountListLoading, VERY_LONG_WAIT);
        waitForElementToBeInvisible(lblLoadingIcon, MODERATE_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);
        if (isElementPresentBy(lblHighlightedAccountNo(accountNumber))) {
            addToReport("Successfully validated account number : '" + accountNumber + "' under the tab " + tabName, Status.PASS, false);
        } else {
            addToReport("Failed to validated account number : '" + accountNumber + "' under the tab " + tabName, Status.FAIL, true);
        }
        if (isElementPresentBy(lblHighlightedListContent(currencyAndBal))) {
            addToReport("Successfully validated currency and balance : '" + currencyAndBal + "' under the tab " + tabName, Status.PASS, true);
        } else {
            addToReport("Failed to validated currency and balance '" + currencyAndBal + "' under the tab " + tabName, Status.FAIL, true);
        }
        //Validate Table Headers
        for (String header : MyAccountsConstants.PAWNING_LIST_TABLE_HEADERS) {
            if (isElementPresentBy(getHeaderByName(header))) {
                addToReport("Table header '" + header + "' is present under tab '" + tabName + "'", Status.PASS, false);
            } else {
                addToReport("Missing table header '" + header + "' under tab '" + tabName + "'", Status.FAIL, true);
            }
        }
        clickOnElement(lblHighlightedAccountNo(accountNumber));
        waitForElementToBeClickable(lblAccountHistoryAccountNo(MyAccountsConstants.TAB_PAWNING_HISTORY), LONG_WAIT);
        waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
        waitForElementToBeInvisible(lblAccountListLoading, VERY_LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, LONG_WAIT);


        aHAccountNo = getTextFromElement(lblAccountHistoryAccountNo(MyAccountsConstants.TAB_PAWNING_HISTORY));
        if (aHAccountNo.equals(accountNumber)) {
            addToReport("Successfully validated account number : '" + accountNumber + "' under the account history", Status.PASS, true);
        } else {
            addToReport("Failed to validated account number : '" + accountNumber + "' under the account history ", Status.FAIL, true);
        }
        scrollPageToTop();
        waitForElementToBeClickable(getElementByTypeAndText(ElementType.span, MyAccountsConstants.VIEW_LIST), 30);
        clickOnElement(getElementByTypeAndText(ElementType.span, MyAccountsConstants.VIEW_LIST));
        addToReport("----------End of validation of the content of pawning list and the functional behaviour ----------", Status.PASS, true);
        if (recordCount > 1) {
            //Navigate to next account
            clickOnElement(btnNextArrow);
            //WaitForElementPresence(lblLoadingIcon);
            waitForElementToBeInvisible(lblLoadingIcon, MODERATE_WAIT);
        } else {
            addToReport("No more cards are available", Status.INFO, true);
        }

        addToReport("----------End of validation of that when user click on 'My Accounts' item in top navigation menu all account categories and other product lists displayed----------", Status.PASS, false);

    }

    /**
     * select tab and validate it's relevant tile for t bill
     *
     * @param tabName    Table Name
     * @param tileHeader Tile header
     */
    public void navigateToTBillAndValidate(String tabName, String tileHeader) {
        //select tab
        selectTabAndValidate(tabName, tileHeader);

        waitForElementToBeInvisible(lblAccountListLoading, VERY_LONG_WAIT);
        waitForElementToBeInvisible(lblLoadingIcon, MODERATE_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);


        addToReport("----------Start of validation of user is able to view the detailed information of their treasury bill ----------", Status.PASS, false);

        //Obtain account number and validate
        currencyAndBal = getTextFromElement(lblCurrencyAndAvailableBalance);
        accountNumber = getAttributeOrText(lblPawningAccountNumber, "text").replace(" ", "");
        investmentDate = getAttributeOrText(lblDatesAndRates(MyAccountsConstants.INVESTMENT_DATE), "text").replace("/", "-");
        NoOfDays = getAttributeOrText(lblDatesAndRates(MyAccountsConstants.NUMBER_OF_DAYS), "text");
        tbillYield = getAttributeOrText(lblRepoAdditionalDetails(MyAccountsConstants.YIELD), "text");
        tbillFaceValue = getAttributeOrText(lblRepoAdditionalDetails(MyAccountsConstants.FACE_VALUE), "text");
        tbillMaturiyDate = getAttributeOrText(lblRepoAdditionalDetails(MyAccountsConstants.MATURITY_DATE), "text");

        if (isElementPresentBy(lblHighlightedAccountNo(accountNumber), VERY_SHORT_WAIT)) {
            addToReport("Successfully validated account number : '" + accountNumber + "' under the tab " + tabName, Status.PASS, false);
        } else {
            addToReport("Failed to validated account number : '" + accountNumber + "' under the tab " + tabName, Status.FAIL, true);
        }
        if (isElementPresentBy(lblHighlightedListContent(currencyAndBal), VERY_SHORT_WAIT)) {
            addToReport("Successfully validated currency and balance : '" + currencyAndBal + "' under the tab " + tabName, Status.PASS, true);
        } else {
            addToReport("Failed to validated currency and balance '" + currencyAndBal + "' under the tab " + tabName, Status.FAIL, true);
        }
        //validate investment date
        if (isElementPresentBy(lblHighlightedListContent(investmentDate), VERY_SHORT_WAIT)) {
            addToReport("Successfully validated investment date : '" + investmentDate + "' under the tab " + tabName, Status.PASS, true);
        } else {
            addToReport("Failed to validated investment date '" + investmentDate + "' under the tab " + tabName, Status.FAIL, true);
        }
        //validate no of days
        if (isElementPresentBy(lblHighlightedListContent(NoOfDays), VERY_SHORT_WAIT)) {
            addToReport("Successfully validated number of days : '" + NoOfDays + "' under the tab " + tabName, Status.PASS, true);
        } else {
            addToReport("Failed to validated number of days '" + NoOfDays + "' under the tab " + tabName, Status.FAIL, true);
        }
        // Validate T-Bill Yield
        if (isElementPresentBy(lblHighlightedListContent(tbillYield), VERY_SHORT_WAIT)) {
            addToReport("Successfully validated T-Bill Yield : '" + tbillYield + "' under the tab " + tabName, Status.PASS, true);
        } else {
            addToReport("Failed to validate T-Bill Yield : '" + tbillYield + "' under the tab " + tabName, Status.FAIL, true);
        }
        // Validate T-Bill Face Value
        if (isElementPresentBy(lblHighlightedListContent(tbillFaceValue))) {
            addToReport("Successfully validated T-Bill Face Value : '" + tbillFaceValue + "' under the tab " + tabName, Status.PASS, true);
        } else {
            addToReport("Failed to validate T-Bill Face Value : '" + tbillFaceValue + "' under the tab " + tabName, Status.FAIL, true);
        }

        // Validate T-Bill Maturity Date
        if (isElementPresentBy(lblHighlightedListContent(tbillMaturiyDate))) {
            addToReport("Successfully validated T-Bill Maturity Date : '" + tbillMaturiyDate + "' under the tab " + tabName, Status.PASS, true);
        } else {
            addToReport("Failed to validate T-Bill Maturity Date : '" + tbillMaturiyDate + "' under the tab " + tabName, Status.FAIL, true);
        }
        addToReport("----------End of validation of user is able to view the detailed information of their treasury bill ----------", Status.PASS, false);
        addToReport("----------Start of validation of the content of t-bills and the functional behaviour ----------", Status.PASS, false);
        //Validate Table Headers
        for (String header : MyAccountsConstants.T_BILL_TABLE_HEADERS) {
            if (isElementPresentBy(getHeaderByName(header))) {
                addToReport("Table header '" + header + "' is present under tab '" + tabName + "'", Status.PASS, false);
            } else {
                addToReport("Missing table header '" + header + "' under tab '" + tabName + "'", Status.FAIL, true);
            }
        }

        addToReport("----------End of validation of the content of t-bills and the functional behaviour ----------", Status.PASS, true);


    }

    /**
     * select tab and validate it's relevant tile for repo
     *
     * @param tabName    Table Name
     * @param tileHeader Tile header
     */
    public void navigateToRepoAndValidate(String tabName, String tileHeader) {
        //select tab
        selectTabAndValidate(tabName, tileHeader);

        addToReport("----------Start of validation of user is able to view the detailed information of their repo ----------", Status.PASS, false);
        waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);
        waitForElementToBeInvisible(lblAccountListLoading, VERY_LONG_WAIT);
        //Obtain account number and validate
        currencyAndBal = getTextFromElement(lblCurrencyAndAvailableBalance);
        accountNumber = getAttributeOrText(lblPawningAccountNumber, "text").replace(" ", "");
        repoMaturityDate = getAttributeOrText(lblDatesAndRates(MyAccountsConstants.MATURITY_DATE), "text");
        repoInterestRate = getAttributeOrText(lblDatesAndRates(MyAccountsConstants.INTEREST_RATE), "text");
        repoMaturityValue = getAttributeOrText(lblRepoAdditionalDetails(MyAccountsConstants.MATURITY_VALUE), "text");


        if (isElementPresentBy(lblHighlightedAccountNo(accountNumber))) {
            addToReport("Successfully validated repo number : '" + accountNumber + "' under the tab " + tabName, Status.PASS, false);
        } else {
            addToReport("Failed to validated repo number : '" + accountNumber + "' under the tab " + tabName, Status.FAIL, true);
        }
        // amount and validate
        if (isElementPresentBy(lblHighlightedListContent(currencyAndBal))) {
            addToReport("Successfully validated currency and balance : '" + currencyAndBal + "' under the tab " + tabName, Status.PASS, true);
        } else {
            addToReport("Failed to validated currency and balance '" + currencyAndBal + "' under the tab " + tabName, Status.FAIL, true);
        }
        // repo date
        if (isElementPresentBy(lblHighlightedListContent(repoMaturityDate))) {
            addToReport("Successfully validated repo date : '" + repoMaturityDate + "' under the tab " + tabName, Status.PASS, true);
        } else {
            addToReport("Failed to validated repo date '" + repoMaturityDate + "' under the tab " + tabName, Status.FAIL, true);
        }
        // interest rate
        if (isElementPresentBy(lblHighlightedListContent(repoInterestRate))) {
            addToReport("Successfully validated interest rate : '" + repoInterestRate + "' under the tab " + tabName, Status.PASS, true);
        } else {
            addToReport("Failed to validated interest rate '" + repoInterestRate + "' under the tab " + tabName, Status.FAIL, true);
        }
        //maturity value
        if (isElementPresentBy(lblHighlightedListContent(repoMaturityValue))) {
            addToReport("Successfully validated maturity value : '" + repoMaturityValue + "' under the tab " + tabName, Status.PASS, true);
        } else {
            addToReport("Failed to validated maturity value '" + repoMaturityValue + "' under the tab " + tabName, Status.FAIL, true);
        }

        addToReport("----------End of validation of user is able to view the detailed information of their treasury bill ----------", Status.PASS, false);
        addToReport("----------Start of validation of the content of repo and the functional behaviour ----------", Status.PASS, false);
        //Validate Table Headers
        for (String header : MyAccountsConstants.REPO_TABLE_HEADERS) {
            if (isElementPresentBy(getHeaderByName(header))) {
                addToReport("Table header '" + header + "' is present under tab '" + tabName + "'", Status.PASS, false);
            } else {
                addToReport("Missing table header '" + header + "' under tab '" + tabName + "'", Status.FAIL, true);
            }
        }
        addToReport("----------End of validation of the content of repo and the functional behaviour ----------", Status.PASS, false);

    }


    /**
     * Validates the inward and transfer requests by navigating to the specified tab, verifying the tile header,
     * and checking the details for the given account number
     *
     * @param tabName       the name of the tab where inward and transfer requests are displayed
     * @param tileHeader    the expected header of the tile to validate
     * @param accountNumber the account number associated with the requests
     */
    public void ValidateInwardAndTransferRequest(String tabName, String tileHeader, String accountNumber) {
        addToReport("----------Start of validation of Inward cheque----------", Status.PASS, false);
        waitForElementToBeInvisible(lblLoadingIcon, VERY_LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);

        //Select tab and account
        selectTabAndValidate(tabName, tileHeader);
        searchAndSelectAccountList(accountNumber);

        waitForElementToBeClickable(getElementByTypeAndText(ElementType.span, MyAccountsConstants.ISSUED_CHEQUES), LONG_WAIT);

        // select stop cheque
        if (isElementPresentBy(getElementByTypeAndText(ElementType.span, MyAccountsConstants.ISSUED_CHEQUES))) {
            addToReport("Successfully validated button : '" + MyAccountsConstants.ISSUED_CHEQUES, Status.PASS, true);
            clickOnElement(getElementByTypeAndText(ElementType.span, MyAccountsConstants.ISSUED_CHEQUES));
        } else {
            addToReport("Failed to validate button '" + MyAccountsConstants.ISSUED_CHEQUES, Status.FAIL, true);
        }
        waitForElementToBeInvisible(icnTileLoading, MODERATE_WAIT);

        waitForElementToBeClickable(getElementByTypeAndText(ElementType.div, MyAccountsConstants.INWARD_CHEQUES), MODERATE_WAIT);
        if (isElementPresentBy(getElementByTypeAndText(ElementType.div, MyAccountsConstants.INWARD_CHEQUES))) {
            addToReport("Successfully validated button : '" + MyAccountsConstants.INWARD_CHEQUES, Status.PASS, true);
            clickOnElement(getElementByTypeAndText(ElementType.div, MyAccountsConstants.INWARD_CHEQUES));
        } else {
            addToReport("Failed to validate button '" + MyAccountsConstants.INWARD_CHEQUES, Status.FAIL, true);
        }

        //Validate  inward cheque list table headers
        for (String header : MyAccountsConstants.ALL_ISSUED_CHEQUES_TABLE_HEADERS) {

            if (isElementPresentBy(getHeaderByName(header))) {
                addToReport("Table header '" + header + "' is present under tab '" + MyAccountsConstants.INWARD_CHEQUES + "'", Status.PASS, false);
            } else {
                addToReport("Missing table header '" + header + "' under tab '" + MyAccountsConstants.INWARD_CHEQUES + "'", Status.FAIL, true);
            }
        }
        rowCount = isElementsPresentBy(tblInwardChqRows, SHORT_WAIT);
        if (rowCount > 0) {
            waitForElementToBeClickable(tblInwardChqRows, LONG_WAIT);
            addToReport("Table loaded with  '" + rowCount + "' in '" + MyAccountsConstants.INWARD_CHEQUES + "'", Status.PASS, true);

            List<WebElement> rows = driver.findElements(tblInwardChqRows);

            for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
                List<WebElement> cells = rows.get(rowIndex).findElements(By.tagName("td"));

                for (int colIndex = 0; colIndex < cells.size(); colIndex++) {
                    String cellValue = cells.get(colIndex).getText().trim();
                    String pattern = MyAccountsConstants.INWARD_CHEQUE_COLUMN_PATTERNS[colIndex];

                    if (cellValue.matches(pattern)) {
                        addToReport("Row " + (rowIndex + 1) + " - Validated Column "
                                + (colIndex + 1) + ": " + cellValue, Status.PASS, false);
                    } else {
                        addToReport("Row " + (rowIndex + 1) + " - Failed to validate Column "
                                + (colIndex + 1) + ": " + cellValue, Status.FAIL, false);
                    }
                }
            }


        } else {
            addToReport("Missing table data under '" + MyAccountsConstants.INWARD_CHEQUES + "'", Status.FAIL, true);
        }
        addToReport("----------End of validation of Inward cheque----------", Status.PASS, false);
        addToReport("----------Start of validation of Transfer cheque----------", Status.PASS, false);

        waitForElementToBeClickable(getElementByTypeAndText(ElementType.div, MyAccountsConstants.TRANSFER_ZONE_CHEQUES), LONG_WAIT);

        waitForElementToBeInvisible(icnTileLoading, MODERATE_WAIT);

        waitForElementToBeClickable(getElementByTypeAndText(ElementType.div, MyAccountsConstants.TRANSFER_ZONE_CHEQUES), MODERATE_WAIT);
        if (isElementPresentBy(getElementByTypeAndText(ElementType.div, MyAccountsConstants.TRANSFER_ZONE_CHEQUES))) {
            addToReport("Successfully validated button : '" + MyAccountsConstants.TRANSFER_ZONE_CHEQUES, Status.PASS, true);
            clickOnElement(getElementByTypeAndText(ElementType.div, MyAccountsConstants.TRANSFER_ZONE_CHEQUES));
        } else {
            addToReport("Failed to validate button '" + MyAccountsConstants.TRANSFER_ZONE_CHEQUES, Status.FAIL, true);
        }

        //Validate  inward cheque list table headers
        for (String header : MyAccountsConstants.ALL_ISSUED_CHEQUES_TABLE_HEADERS) {

            if (isElementPresentBy(getHeaderByName(header))) {
                addToReport("Table header '" + header + "' is present under tab '" + MyAccountsConstants.TRANSFER_ZONE_CHEQUES + "'", Status.PASS, false);
            } else {
                addToReport("Missing table header '" + header + "' under tab '" + MyAccountsConstants.TRANSFER_ZONE_CHEQUES + "'", Status.FAIL, true);
            }
        }
        rowCount = isElementsPresentBy(tblInwardChqRows, SHORT_WAIT);
        if (rowCount > 0) {
            waitForElementToBeClickable(tblInwardChqRows, LONG_WAIT);
            addToReport("Table loaded with  '" + rowCount + "' in '" + MyAccountsConstants.TRANSFER_ZONE_CHEQUES + "'", Status.PASS, true);

            List<WebElement> rows = driver.findElements(tblInwardChqRows);

            for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
                List<WebElement> cells = rows.get(rowIndex).findElements(By.tagName("td"));

                for (int colIndex = 0; colIndex < cells.size(); colIndex++) {
                    String cellValue = cells.get(colIndex).getText().trim();
                    String pattern = MyAccountsConstants.INWARD_CHEQUE_COLUMN_PATTERNS[colIndex];

                    if (cellValue.matches(pattern)) {
                        addToReport("Row " + (rowIndex + 1) + " - Validated Column "
                                + (colIndex + 1) + ": " + cellValue, Status.PASS, false);
                    } else {
                        addToReport("Row " + (rowIndex + 1) + " - Failed to validate Column "
                                + (colIndex + 1) + ": " + cellValue, Status.FAIL, false);
                    }
                }
            }

        } else {
            addToReport("Missing table data under '" + MyAccountsConstants.TRANSFER_ZONE_CHEQUES + "'", Status.FAIL, true);
        }

        addToReport("----------End of validation of Transfer cheque----------", Status.PASS, false);
    }

}
