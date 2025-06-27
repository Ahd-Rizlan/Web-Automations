/*
 *   @author - salman R
 */
package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.CommonUtils;
import utils.constants.BillerConstants;
import utils.constants.MyAccountsConstants;
import java.io.File;
import java.util.List;

import static utils.Drivers.*;

public class MyAccountsPage extends BasePage {


    int recordCount, rowCount = 0;
    String repoMaturityDate, repoMaturityValue, tbillYield, tbillFaceValue, tbillMaturiyDate, currencyAndBal, currentTab, repoInterestRate, investmentDate, NoOfDays, accountNumber, aHAccountNo = "";
    String[] cardCount = new String[]{""};

    public MyAccountsPage(WebDriver driver) {
        super(driver);
    }

    public enum ElementType {
        button, label, span, div, h2
    }

    private static final By lblAccountListLoading = By.xpath("//div[contains(@class,'dark:bg-gray')]");
    private static final By tblRows = By.xpath("//table//tbody/tr");
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
    private static final By ddCollectingBranch = By.xpath("//select[@name='branch']");
    private static final By ddNoOfLeaves = By.xpath("//select[@name='noOfLeaves']");
    private static final By ddNoOfChequeBook = By.xpath("//select[@name='noOfChequeBook']");
    private static final By ddAdvancedSearchMonth = By.xpath("//span[@class='rdrMonthPicker']/select");
    private static final By ddAdvancedSearchYear = By.xpath("//span[@class='rdrYearPicker']/select");
    private static final By ddTransactionType = By.xpath("//select[@id='status']");
    private static final By imgMasterCardLogo = By.xpath("//img[contains(@srcset,'MasterCardLogo')]");
    private static final By lblInactiveCardStatus = By.xpath("//div[@class='flex gap-1']/div[1]");


    public static By lblNoDataFound(String text) {
        return By.xpath("//div[@class='gap-2']//span[contains(text(),'" + text + "')]");
    }

    public static By lblNickName(String text) {
        return By.xpath("//span[contains(text(),'" + text + "')]");
    }

    public static By lnkAdvancedSearchDay(String day) {
        return By.xpath("//span[@class='rdrDayNumber']/span[contains(text(),'" + day + "')]");
    }

    public static By lblRepoAdditionalDetails(String text) {
        return By.xpath("//li[contains(text(),'" + text + "')]/span");
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
        return By.xpath("//" + type.name() + "[contains(normalize-space(text()), " + text + ")]");
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
        return By.xpath("//div[contains(text(),'"+text+"')]/following-sibling::div/span[contains(@class,'text-green')]");
    }
    private static By lblCreditCardNumber(String text) {
        return By.xpath("//div[contains(text(),'"+text+"')]/parent::div//span[contains(@class,'flex flex-col')]");
    }
    private static By lblCreditCardCustAccNumber(String text) {
        return By.xpath("//span[contains(text(),'"+text+"')]/parent::div/span[1]");
    }

    private static By popUpPDFDownload(String msg) {
        return By.xpath("//div[text()='" + msg + "']");
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
            addToReport("----------Start of validation of that when user selects an account, category or a other product list of all available records is displaying to the user. ----------", Status.PASS, false);
            selectTabAndValidate(tabName[inc], tileHeader[inc]);

            //Validate the selected tile and its relevant data loaded at list
            waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
            waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);

            if (tabName[inc].equals(MyAccountsConstants.TAB_PAWNING)) {
                recordCount = 1;
            } else {
                waitForElementToBeClickable(icnAccounts, LONG_WAIT);

                //Obtain pagination value
                cardCount = CommonUtils.splitText(getAttributeOrText(icnAccounts, "text"), "/");
                //Obtain the accounts record count
                recordCount = Integer.parseInt(cardCount[1]);
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
                            if (isElementPresentBy(lblNoDataFound(MyAccountsConstants.NO_DATA_FOUND))) {
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
                            //Remove this once finacle dates are synchronized
                            advanceSearchByDate(BillerConstants.NUMBER_TWENTY_TWENTY_FOUR, BillerConstants.JULY, BillerConstants.NUMBER_TWENTY_ONE, BillerConstants.NUMBER_TWENTY_ONE);

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
                    clickOnElement(getElementByTypeAndText(ElementType.button, MyAccountsConstants.NEXT));

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
        searchAndSelectAccountList(accountNumber);

        waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);
        waitForElementPresence(lblAccountSummaryDetails(MyAccountsConstants.ACCOUNT_CURRENCY), VERY_LONG_WAIT);

        // Permanent OD Limit
        if (odLimit.equals(getAttributeOrText(lblAccountSummaryDetails(MyAccountsConstants.PERMANENET_OD_LIMIT), "text"))) {
            addToReport("Successfully validated Permanent OD Limit: '" + odLimit + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate Permanent OD Limit: Expected '" + odLimit + "'", Status.FAIL, true);
        }

        // Temporary OD Limit
        if (tempOdLimit.equals(getAttributeOrText(lblAccountSummaryDetails(MyAccountsConstants.TEMPORARY_OD_LIMIT), "text"))) {
            addToReport("Successfully validated Temporary OD Limit: '" + tempOdLimit + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate Temporary OD Limit: Expected '" + tempOdLimit + "'", Status.FAIL, true);
        }

        // Overdue Liability
        if (overdueLiability.equals(getAttributeOrText(lblAccountSummaryDetails(MyAccountsConstants.OVERDUE_LIABILITY), "text"))) {
            addToReport("Successfully validated Overdue Liability: '" + overdueLiability + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate Overdue Liability: Expected '" + overdueLiability + "'", Status.FAIL, true);
        }

        // System Reserved Amount
        if (reservedAmount.equals(getAttributeOrText(lblAccountSummaryDetails(MyAccountsConstants.SYSTEM_RESERVED_AMOUNT), "text"))) {
            addToReport("Successfully validated System Reserved Amount: '" + reservedAmount + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate System Reserved Amount: Expected '" + reservedAmount + "'", Status.FAIL, true);
        }

        // Account Type
        if (MyAccountsConstants.CURRENT_ACCOUNT.equals(getAttributeOrText(lblAccountSummaryDetails(MyAccountsConstants.ACCOUNT_TYPE), "text"))) {
            addToReport("Successfully validated Account Type: '" + MyAccountsConstants.CURRENT_ACCOUNT + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate Account Type: Expected '" + MyAccountsConstants.CURRENT_ACCOUNT + "'", Status.FAIL, true);
        }

        // Account Opened On
        if (openedOn.equals(getAttributeOrText(lblAccountSummaryDetails(MyAccountsConstants.ACCOUNT_OPENED_ON), "text"))) {
            addToReport("Successfully validated Account Opened On: '" + openedOn + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate Account Opened On: Expected '" + openedOn + "'", Status.FAIL, true);
        }

        // Account Balance
        if (accountBalance.equals(getAttributeOrText(lblAccountSummaryDetails(MyAccountsConstants.ACCOUNT_BALANCE), "text"))) {
            addToReport("Successfully validated Account Balance: '" + accountBalance + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate Account Balance: Expected '" + accountBalance + "'", Status.FAIL, true);
        }

        // Currency of the account
        if (MyAccountsConstants.CURRENCY_VALUES[0].equals(getAttributeOrText(lblAccountSummaryDetails(MyAccountsConstants.ACCOUNT_CURRENCY), "text"))) {
            addToReport("Successfully validated Currency of the account: '" + MyAccountsConstants.CURRENCY_VALUES[0] + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate Currency of the account: Expected '" + MyAccountsConstants.CURRENCY_VALUES[0] + "'", Status.FAIL, true);
        }

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
    public void ValidateAdvancedSearch(String tabName, String tileHeader, String accountNumber, String month, String year, String from, String to, String fullDate, String amountFrom, String amountTo) {
        addToReport("----------Start of validation of user should be able to view the Current account details----------", Status.PASS, false);

        waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);

        //Selecet tab and account
        selectTabAndValidate(tabName, tileHeader);
        searchAndSelectAccountList(accountNumber);
        waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);
        clickOnElement(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_ADVANCE_SEARCH));

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
        } else {
            addToReport("Failed to validate filter function by date '", Status.FAIL, true);
        }

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
        } else {
            addToReport("Failed to validate filter function by transfer type '", Status.FAIL, true);
        }
        // close filter
        clickOnElement(btnCloseFilterIcon(MyAccountsConstants.TRANSFER_TYPE));
        waitForElementToBeInvisible(btnCloseFilterIcon(MyAccountsConstants.TRANSFER_TYPE), LONG_WAIT);
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
        } else {
            addToReport("Failed to validate filter function by transfer type '", Status.FAIL, true);
        }

        addToReport("----------End of validation of user should be able to view the Current account details----------", Status.PASS, false);
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
    public void ValidateChequeRequestDetails(String tabName, String tileHeader, String accountNumber, List<String> noOFLeaves, List<String> numberOfBooks) {
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

        clickOnElementUsingJS(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_TEXT_CANCEL));
        waitForElementToBeInvisible(getElementByTypeAndText(ElementType.div, MyAccountsConstants.BUTTON_TEXT_CANCEL),SHORT_WAIT);
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
    public void ValidateSavingsAccountDetails(String tabName, String tileHeader, String accountNumber, String accHolderName, String systemReserved, String lienAmount, String accOpenedOn, String accountBalance, String floatBalance, String amountFrom, String amountTo, String downloadDirectory) {
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

        // Account Holder Name
        if (accHolderName.equals(getAttributeOrText(lblAccountSummaryDetails(MyAccountsConstants.ACCOUNT_HOLDER), "text"))) {
            addToReport("Successfully validated Account Holder Name: '" + accHolderName + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate Account Holder Name: Expected " + accHolderName + " but received " + getAttributeOrText(lblAccountSummaryDetails(MyAccountsConstants.ACCOUNT_HOLDER), "text"), Status.FAIL, true);
        }

        // System Reserved
        if (systemReserved.equals(getAttributeOrText(lblAccountSummaryDetails(MyAccountsConstants.SYSTEM_RESERVED), "text"))) {
            addToReport("Successfully validated System Reserved: '" + systemReserved + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate System Reserved: Expected " + systemReserved + " but received " + getAttributeOrText(lblAccountSummaryDetails(MyAccountsConstants.SYSTEM_RESERVED), "text"), Status.FAIL, true);
        }

        // Lien Amount
        if (lienAmount.equals(getAttributeOrText(lblAccountSummaryDetails(MyAccountsConstants.LIEN_AMOUNT), "text"))) {
            addToReport("Successfully validated Lien Amount: '" + lienAmount + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate Lien Amount: Expected " + lienAmount + " but received " + getAttributeOrText(lblAccountSummaryDetails(MyAccountsConstants.LIEN_AMOUNT), "text"), Status.FAIL, true);
        }

        // Account Opened On
        if (accOpenedOn.equals(getAttributeOrText(lblAccountSummaryDetails(MyAccountsConstants.ACCOUNT_OPENED_ON), "text"))) {
            addToReport("Successfully validated Account Opened On: '" + accOpenedOn + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate Account Opened On: Expected " + accOpenedOn + " but received " + getAttributeOrText(lblAccountSummaryDetails(MyAccountsConstants.ACCOUNT_OPENED_ON), "text"), Status.FAIL, true);
        }

        // Account Balance
        if (accountBalance.equals(getAttributeOrText(lblAccountSummaryDetails(MyAccountsConstants.ACCOUNT_BALANCE), "text"))) {
            addToReport("Successfully validated Account Balance: '" + accountBalance + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate Account Balance: Expected " + accountBalance + " but received " + getAttributeOrText(lblAccountSummaryDetails(MyAccountsConstants.ACCOUNT_BALANCE), "text"), Status.FAIL, true);
        }

        // Float Balance
        if (floatBalance.equals(getAttributeOrText(lblAccountSummaryDetails(MyAccountsConstants.FLOAT_BALANCE), "text"))) {
            addToReport("Successfully validated Float Balance: '" + floatBalance + "'", Status.PASS, false);
        } else {
            addToReport("Failed to validate Float Balance: Expected " + floatBalance + " but received " + getAttributeOrText(lblAccountSummaryDetails(MyAccountsConstants.FLOAT_BALANCE), "text"), Status.FAIL, true);
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
        if (recordCount == 10) {
            addToReport("Successfully validated number of transaction rows as  '" + recordCount, Status.PASS, true);
        } else {
            addToReport("Failed to validate number of transaction rows as  10 '", Status.FAIL, true);
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
        //Select download
        waitForElementToBeClickable(getElementByTypeAndText(ElementType.button, MyAccountsConstants.DOWNLOAD), LONG_WAIT);
        clickOnElement(getElementByTypeAndText(ElementType.button, MyAccountsConstants.DOWNLOAD));

        waitForElementPresence(popUpPDFDownload(MyAccountsConstants.PDF_DOWNLOADED_SUCCESSFULLY), LONG_WAIT);

        //Wait for download to initiate - update this with dynamic once stabilized
        waitFor(SHORT_WAIT);
        clickOnElement(btnClosePopup);

        // Get the latest downloaded file
        File latestFile = getLatestDownloadedFile(downloadDirectory);

        if (latestFile != null) {
            // Extract text from the PDF
            String extractedText = extractTextFromPDF(latestFile.getAbsolutePath()).replace("/n", "");

            addToReport(" Latest downloaded pdf :  : '" + extractedText, Status.INFO, false);

            //validate payment id
            if (extractedText.contains(amountFrom)) {
                addToReport(" Validated filtered amount " + amountFrom + " for the downloaded record", Status.PASS, false);
            } else {
                addToReport(" Failed to validate amount of the downloaded record", Status.FAIL, false);
            }
        } else {
            addToReport(" Failed to download the payment record", Status.FAIL, false);
        }
        addToReport("----------End of validation of the customer able to download the filtered transactions----------", Status.PASS, false);

    }

    String cardNo,customerAccountNumber,expiryDate,cardStatus,cardType,availableBalance;

    /**
     * Validation of credit card details
     * @param tabName
     * @param tileHeader
     * @param accountNumber
     */
    public void ValidateCreditCardDetails(String tabName, String tileHeader,String accountNumber) {
        addToReport("----------Start of validation of user should be able to view the Credit card details----------", Status.PASS, false);

        waitForElementToBeInvisible(lblLoadingIcon, VERY_LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);

        //Selecet tab and account
        selectTabAndValidate(tabName, tileHeader);

        if (isElementPresentBy(icnAccounts)){

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
                    addToReport("Expected account "+accountNumber+" found", Status.FAIL, true);
                    break;
                }
            }
        } else {
            addToReport("No accounts", Status.FAIL, true);
        }

        }

        //Obtain text
        availableBalance = getAttributeOrText(lblCreditCardAvailableBalance(MyAccountsConstants.AVAILABLE),"text");
        cardNo = getAttributeOrText(lblCreditCardNumber(MyAccountsConstants.AVAILABLE),"text");
        customerAccountNumber = getAttributeOrText(lblCreditCardCustAccNumber(MyAccountsConstants.CAN),"text");
        cardStatus= getAttributeOrText(lblInactiveCardStatus,"text");
        expiryDate = getAttributeOrText(lblCreditCardCustAccNumber(MyAccountsConstants.EXPIRY_DATE),"text");

        // card type
        if (isElementPresentBy(imgMasterCardLogo)) {
            addToReport("Successfully obtained card type ", Status.PASS, false);
            cardType = MyAccountsConstants.MASTER_INACTIVE;
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
        if (isElementPresentBy(getElementByTypeAndText(ElementType.h2,MyAccountsConstants.SUPPLEMENTARY_CARDS))) {
            addToReport("Successfully validated header " + MyAccountsConstants.SUPPLEMENTARY_CARDS, Status.PASS, false);
        } else {
            addToReport("Failed to validate header " + MyAccountsConstants.SUPPLEMENTARY_CARDS , Status.FAIL, true);
        }

        recordCount = isElementsPresentBy(tblTransactionRows);
        if (recordCount != 0) {
            for (int incr = 1; incr <= recordCount; incr++) {

                //Validate supplementary card table headers
                for (String header : MyAccountsConstants.SUPPLEMENTARY_CARD_TABLE_HEADERS) {

                    if (isElementPresentBy(getHeaderByName(header))) {
                        addToReport("Table header '" + header + "' is present under account " + accountNumber, Status.PASS, false);
                    }  else {
                        addToReport("Missing table header '" + header + "'  under account number " + accountNumber, Status.FAIL, true);
                    }

                }
                if (getAttributeOrText(tblCellRecord(1,incr),"text").equals(null) || getAttributeOrText(tblCellRecord(2,incr),"text").equals(null)){
                    addToReport("Invalid content under supplementary cards under account :" + accountNumber, Status.FAIL, true);
                }else {
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
     *
     * Performs an advanced search using a date range
     * This method is typically used to filter results between a specific start and end date
     *
     * @param year     the year to be selected in the search criteria
     * @param month    the month to be selected
     * @param dayFrom  the starting day of the date range
     * @param dayTo    the ending day of the date range (e.g., "15" for the 15th day of the month)
     */
    public void advanceSearchByDate(String year, String month,String dayFrom,String dayTo) {
        addToReport("----------Start of advance search using date----------", Status.PASS, false);

        waitForElementToBeInvisible(lblLoadingIcon, LONG_WAIT);
        waitForElementToBeInvisible(icnTileLoading, VERY_LONG_WAIT);
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
        waitForElementToBeClickable(tblRows,LONG_WAIT);
        addToReport("----------End of advance search using date----------", Status.PASS, false);
    }

}
