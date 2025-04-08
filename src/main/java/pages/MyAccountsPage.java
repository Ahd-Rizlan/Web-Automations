/*
 *   @author - salman R
 */
package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.CommonUtils;

public class MyAccountsPage extends BasePage {

    CommonUtils commonUtils = new CommonUtils();

    public MyAccountsPage(WebDriver driver) {
        super(driver);
    }

    public enum ElementType {
        button, label, span, div;
    }

    private static final By lblMyAccounts = By.xpath("//span[text()='My Accounts']");
    private static final By lblAccountListLoading = By.xpath("//div[contains(@class,'dark:bg-gray')]");
    private static final By tblRows = By.xpath("//table//tbody/tr");
    private static final By tfSearch = By.xpath("//input[@placeholder='Search']");
    private static final By btnSearch = By.xpath("//div[contains(@class,'absolute')]/img");
    private static final By lblNoDataFound = By.xpath("(//span[contains(text(),'No Data Found')])[2]");

    private static By getElementByTypeAndText(MyAccountsPage.ElementType type, String text) {
        return By.xpath("//" + type.name() + "[contains(normalize-space(text()), " + text + ")]");
    }
    private static By lnkSavingsAccountRow(String accountNo) {
        return By.xpath("//td[normalize-space()="+accountNo+"]");
    }
    private static By tblCellRecord(int col, int row) {
        return By.xpath("(//table//tr/td[" + col + "])[" + row + "]");
    }
    private static By tabValue(String tabName) {
        return By.xpath("//div[contains(@class,'AccountsCards_scroll')]/div[contains(text(),'"+tabName+"')]");
    }
    private static By lblAccountHistory(String accountNumber) {
        return By.xpath("//span[contains(text(),'"+accountNumber+"')]");
    }
    /**
     * Select Tab
     *
     * @param mainTab - Main tab
     */
    public void selectTab(String mainTab) {
        try {
            //Select main tab
            waitForElementToBeInvisible(lblAccountListLoading,20);
            waitForElementPresence(tabValue(mainTab));
            waitForElementToBeClickable(tabValue(mainTab),20);
            clickOnElement(tabValue(mainTab));
            addToReport("Main tab : "+mainTab+" is selected", Status.PASS,true);

        } catch (Exception e) {
            addToReport("Failed to select tab "+mainTab, Status.FAIL);
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
            waitForElementToBeInvisible(lblAccountListLoading,20);
            sendKeysToElement(tfSearch,accountNo);
            clickOnElement(btnSearch);
            waitForElementToBeInvisible(lblAccountListLoading,20);

            //Validate the search results
            int recordCount = isElementsPresentBy(tblRows);
            if (recordCount == 1) {

                //Validate cell record
                if (getTextFromElement(tblCellRecord(1,1)).equals(accountNo)){
                    addToReport(" Account number "+accountNo+" has successfully returned on search", Status.PASS, true);
                    clickOnElement(tblCellRecord(1,1));
                    waitForElementToBeInvisible(lblAccountListLoading,20);
                    if (isElementPresentBy(lblAccountHistory(accountNo)))
                    {
                        addToReport(" Account History for account  "+accountNo+" has successfully returned on search", Status.PASS, true);
                    }else {
                        addToReport(" Account History for account  "+accountNo+" has not been successfully returned on search", Status.FAIL, true);
                        throw new RuntimeException("Error - Failed to get to account history");
                    }
                }else {
                    addToReport(" Account number  "+accountNo+" has not been successfully returned on search", Status.FAIL, true);
                    throw new RuntimeException("Error - Failed to get to account number");
                }

            }else {
                addToReport(" Account number "+accountNo+" has not successfully returned on search", Status.FAIL, true);
            }
        } catch (Exception e) {
            addToReport("Failed to get account"+accountNo, Status.FAIL);
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
            if (referenceNo!=null){
                //Search for valid description form accounts history
                waitForElementToBeInvisible(lblAccountListLoading,20);
                waitForElementPresence(tfSearch);
                sendKeysToElement(tfSearch,referenceNo);
                clickOnElement(btnSearch);
                waitForElementToBeInvisible(lblAccountListLoading,20);
                //Validate the search results
                int recordCount = isElementsPresentBy(tblRows);
                if (recordCount==1) {
                    if (getTextFromElement(tblCellRecord(2,1)).contains(referenceNo)){
                        addToReport("Reference No "+referenceNo+" has returned successfully", Status.PASS,true);
                    }else {
                        addToReport("Reference No "+referenceNo+" has not returned successfully", Status.FAIL,true);
                    }
                }else {
                    addToReport("Multiple values have loaded for the search of reference number :"+referenceNo, Status.FAIL,true);
                }
            }else {
                addToReport("Invalid reference number ", Status.FAIL,true);
            }

        } catch (Exception e) {
            addToReport("Failed to search reference "+referenceNo, Status.FAIL);
            throw new RuntimeException("Error - Failed to select tab " + e.getMessage(), e);
        }
    }


}


