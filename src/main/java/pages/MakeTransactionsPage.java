/*
 *   @author - salman R
 */
package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.CommonUtils;

public class MakeTransactionsPage extends BasePage {

    CommonUtils commonUtils = new CommonUtils();

    public MakeTransactionsPage(WebDriver driver) {
        super(driver);
    }

    public enum ElementType {
        button, label, span, div;
    }

    private static final By lblAvailableBalance = By.xpath("//span[contains(text(),'Available Balance')]/parent::div/span[2]");
    private static final By lblPageHeader = By.xpath("//div[contains(text(),'Make Transactions')]");
    private static final By ddFromAccount = By.xpath("//select[@id='accountfrom']");
    private static final By ddToAccount = By.xpath("//select[@id='accountto']");
    private static final By tfEnterAmount = By.xpath("//input[@placeholder='Enter Amount']");
    private static final By tfEnterSenderRemark = By.xpath("//input[@name='senderRemark']");
    private static final By tfEnterBeneficiaryRemark = By.xpath("//input[@name='beneficiaryRemark']");
    private static final By btnSubmit = By.xpath("//button[@type='submit']");
    private static final By rdoOneTimeTransaction = By.xpath("//input[@value='ONLINE']");
    private static final By rdoSchedule = By.xpath("//input[@value='SCHEDULE']");
    private static final By lblRecentTransactionList = By.xpath("//div[contains(@class,'RecentTransactions')]/div");


    private static By tabHeader(String tabName) {
        return By.xpath("//div[contains(@class,'flex')]/div[text()=" + tabName + "]");
    }
    private static By lblAccountNumber(String accountType) {
        return By.xpath("//span[text()='"+accountType+"']/parent::div/span[2]");
    }
    private static By lblAccount(String accountType) {
        return By.xpath("//span[contains(text()," + accountType + ")]/parent::div/span[2]");
    }
    private static By rdoTransferMode(String value) {
        return By.xpath("//input[@value="+ value +"]");
    }


    /**
     * Select header tab
     *
     * @param headerTab - Main tabs Eg.Send money
     *
     */
    public void selectHeaderTab(String headerTab) {
        try {
            waitForElementPresence(tabHeader(headerTab));
            clickOnElement(tabHeader(headerTab));
            addToReport("Selected tab : "+headerTab, Status.PASS,false);
        } catch (Exception e) {
            addToReport("Selecting "+headerTab+" failed ", Status.FAIL);
            throw new RuntimeException("Failed to select tab" + e.getMessage(), e);
        }
    }

    /**
     * Select sub tab under send money
     *
     * @param subHeaderTab - Main tabs Eg.Send money
     *
     */
    public void selectTabUnderSendMoney(String subHeaderTab) {
        try {
            waitForElementPresence(tabHeader(subHeaderTab));
            clickOnElement(tabHeader(subHeaderTab));
            addToReport("Selected sub tab : "+subHeaderTab, Status.PASS,false);
        } catch (Exception e) {
            addToReport("Selecting sub "+subHeaderTab+" failed ", Status.FAIL);
            throw new RuntimeException("Failed to select sub tab" + e.getMessage(), e);
        }
    }


    /**
     * Validate Performing Own Account Transaction From and To accounts availability
     *
     * @param headerTab - header tab value
     * @param subTab   - sub tab value
     *
     */
    public void checkFromAccountAndToAccount(String headerTab,String subTab) {

            //Validate page title
            waitForElementPresence(lblPageHeader,10);

            //Select appropriate tab
            selectHeaderTab(headerTab);
            selectTabUnderSendMoney(subTab);
//        try {
////            //validate the page title and page header
////            boolean isTitleVisible = waitForElementPresence(lblPageTitle(expectedTitle));
////            boolean isTileVisible = waitForElementPresence(lblPageTileHeader(otpTileName));
////            if (isTitleVisible && isTileVisible) {
////                addToReport("OTP page tile heading '" + otpTileName + "' and title '" + expectedTitle + "' is visible.", Status.PASS,false);
////            } else {
////                addToReport("Title or OTP tile is not visible as expected.", Status.FAIL);
////                throw new RuntimeException("Title or OTP tile is not visible as expected.");
////            }
////
////        } catch (Exception e) {
////            addToReport("Error verifying page title '" + expectedTitle + "' and OTP tile heading '" + otpTileName + "'.", Status.FAIL);
////            throw new RuntimeException("Failed to validate the title and tile: " + e.getMessage(), e);
////        }
    }

}


