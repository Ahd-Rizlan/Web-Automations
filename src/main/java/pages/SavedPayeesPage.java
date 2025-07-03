/*
 *   @author - salman R
 */
package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.CommonUtils;

import static utils.Drivers.*;

public class SavedPayeesPage extends BasePage {

    CommonUtils commonutils = new CommonUtils();

    public SavedPayeesPage(WebDriver driver) {
        super(driver);
    }

    public enum ElementType {
        button, label, span, div;
    }

    private static final By tfQFTAccountName = By.xpath("//input[@placeholder='Account Name']");
    private static final By tfQFTEnterAmount = By.xpath("//input[@placeholder='Enter Amount']");
    private static final By tfQFTEnterBRemarks = By.xpath("//input[@placeholder='Enter Beneficiary Remarks']");
    private static final By btnQFTBack = By.xpath("//button[text()='Back']");
    private static final By btnQFTSubmit = By.xpath("//div/button[@type='submit']");
    private static final By tfQFTNickName = By.xpath("//input[@placeholder='Nick Name']");
    private static final By tfQFTBankName = By.xpath("//input[@placeholder='Bank Name']");
    private static final By tfQFTAccountNumber = By.xpath("//input[@placeholder='Account Number']");
    private static final By tfQFTBranchName = By.xpath("//input[@placeholder='Bank Name']/parent::div/following-sibling::div[1]//input");
    private static final By ddQFTFromAccount = By.id("accountfrom");
    private static final By ddQFTPurpose = By.id("bank");
    private static final By tblSavedPayeesRows = By.xpath("//tbody//tr");
    private static final By tblSavedBillerRows = By.xpath("//tbody//tr");
    private static final By tblSPAddToFavPayeeUnchecked = By.xpath("//tbody//tr/following::img[contains(@srcset,'5a2f492b')]");
    private static final By tblFBAddToFavPayeeUnchecked = By.xpath("//tbody//tr//img[contains(@srcset,'5a2f492b')]");
    private static final By tblSPAddToFavPayeeChecked = By.xpath("//tbody//tr/following::img[contains(@srcset,'c7bd4030')]");
    private static final By lblPopupMsgFavPayeeAdded = By.xpath("//div[contains(text(),'Favourite Payee Added Successfully!')]");
    private static final By lblPopupMsgFavPayeeRemoved = By.xpath("//div[contains(text(),'Favourite Payee Removed Successfully!')]");
    private static final By tfFBAmount = By.xpath("//input[@placeholder='Enter Amount']");
    private static final By tfFBMobilePhoneNumber = By.xpath("//input[contains(@name,'.0.fieldValue')]");
    private static final By lblFBBillerName = By.xpath("//input[@placeholder='Biller Name']");
    private static final By tfFBMobilePhoneNumberReEnter = By.xpath("//input[contains(@name,'0.reEnterValue')]");
    private static final By loadingBranchName = By.xpath("(//div[contains(@class,'animate-pulse bg-gray')])[4]");


    private static By lblQFTSavingsAccountName() {
        return By.xpath("//span[text()='Savings Account']/ancestor::div[contains(@class,'flex relative justify-between')]//span[contains(text(),'Available Balance')]");
    }
    private static By lblFBSBAName() {
        return By.xpath("//span[text()='Savings Account']/ancestor::div[contains(@class,'flex relative justify-between')]//span[contains(text(),'Available Balance')]");
    }

    private static By tfQFTAccountName() {
        return By.xpath("//input[@placeholder='Account Name']");
    }

    private static By tblObtainCellValue(int Row, int Col) {
        return By.xpath("//tbody//tr[" + Row + "]/td[" + Col + "]");
    }

    private static By tblYFLRows() {
        return By.xpath("//span[contains(text(),'Your favorite list')]/following::div[contains(@class,'flex flex-col')]/div[contains(@class,'text-base')]");
    }

    private static By tblYFLNickNameBasedOnRow(int Row) {
        return By.xpath("//span[contains(text(),'Your favorite list')]/following::div[contains(@class,'full flex justify-between items-center rounded')][" + Row + "]//div[contains(@class,'text-base')]");
    }

    private static By icnSavedPayeesAddToFav(int Row) {
        return By.xpath("//tbody//tr[contains(@class,'rounded-lg overflow-hidden')][" + Row + "]//div[contains(@class,'items-center justify-center')]/img");
    }
    private static By icnSavedBillerAddToFav(int Row) {
        return By.xpath("//tbody//tr[contains(@class,'rounded-lg overflow-hidden')][" + Row + "]//div[contains(@class,'items-center justify-center')]/img");
    }

    private static By icnSavedPayeeByNName(String NickName) {
        return By.xpath("//tbody//tr[contains(@class,'rounded-lg overflow-hidden')]//td[text()='" + NickName + "']/parent::tr//div[contains(@class,'items-center justify-center')]/img");
    }

    private static By tblYFLNickName(String NickName) {
        return By.xpath("//span[contains(text(),'Your favourite list')]/following::div[contains(@class,'full flex justify-between items-center rounded')]//div[text()='" + NickName + "']");
    }


    /**
     * Validate the title and header of the otp page
     *
     * @param QFTData - Quick fund transfer data
     */
    public void validateQFTPopup(String[] QFTData) {

        try {
            String AccNickName = QFTData[0];
            String BankName = QFTData[1];
            String AccountNumber = QFTData[2];
            waitForElementPresence(lblQFTSavingsAccountName(),LONG_WAIT);
            waitForElementToBeInvisible(loadingBranchName,LONG_WAIT);
            waitForElementPresence(tfQFTBranchName,LONG_WAIT);

            //validate account name presence
            boolean boolSavingsAccount = isElementPresentBy(lblQFTSavingsAccountName());
            if (boolSavingsAccount) {
                addToReport("Successfully validated user account label", Status.PASS, false);
            } else {
                addToReport("User account label is not visible.", Status.FAIL);
            }

            //Validate the preloaded nickname
            String nickName = getAttributetext(driver.findElement(tfQFTNickName), "value").trim();
            if (nickName.equalsIgnoreCase(AccNickName)) {
                addToReport("Successfully validated QFT nick name : " + AccNickName, Status.PASS, false);
            } else {
                addToReport("QFT nick name : " + AccNickName + " is not successfully validated", Status.FAIL);
            }

            //Validate the preloaded bank name
            String bankName = getAttributetext(driver.findElement(tfQFTBankName), "value");
            if (bankName.equalsIgnoreCase(BankName)) {
                addToReport("Successfully validated QFT bank name : " + BankName, Status.PASS, false);
            } else {
                addToReport("QFT bank name : " + BankName + " is not successfully validated", Status.FAIL);
            }

            //Validate the preloaded account number
            String accountNumber = getAttributetext(driver.findElement(tfQFTAccountNumber), "value");
            if (accountNumber.equalsIgnoreCase(AccountNumber)) {
                addToReport("Successfully validated QFT account number : " + AccountNumber, Status.PASS, false);
            } else {
                addToReport("QFT account number : " + AccountNumber + " is not successfully validated", Status.FAIL);
            }

            //Validate the preloaded from account
            String fromAccount = CommonUtils.removeSpaceCharacters(getAttributetext(driver.findElement(ddQFTFromAccount), "value"));
            if ((!fromAccount.isEmpty()) && CommonUtils.containsNumericCharacters(fromAccount)) {
                addToReport("Successfully validated QFT from account number : " + fromAccount, Status.PASS, false);
            } else {
                addToReport("QFT from account number : " + fromAccount + " is not successfully validated", Status.FAIL);
            }

            //Validate the preloaded account name
            String accountName = getAttributetext(driver.findElement(tfQFTAccountName), "value");
            if ((!accountName.isEmpty()) && CommonUtils.containsAlphabaticCharacters(accountName)) {
                addToReport("Successfully validated QFT account name : " + accountName, Status.PASS, false);
            } else {
                addToReport("QFT account name : " + accountName + " is not successfully validated", Status.FAIL);
            }

            //Validate the preloaded branch name
            String branchName = getAttributetext(driver.findElement(tfQFTBranchName), "value");
            if ((!branchName.isEmpty()) && CommonUtils.containsAlphabaticCharacters(branchName)) {
                addToReport("Successfully validated QFT branch name : " + branchName, Status.PASS, false);
            } else {
                addToReport("QFT branch name : " + branchName + " is not successfully validated", Status.FAIL);
            }

            //Validate the preloaded purpose
            String purpose = getAttributetext(driver.findElement(ddQFTPurpose), "value");
            if (purpose.isEmpty()) {
                addToReport("Successfully validated QFT purpose is empty ", Status.PASS, false);
            } else {
                addToReport("QFT purpose  " + purpose + " is not successfully validated", Status.FAIL);
            }

            //Validate the preloaded Enter amount
            String enterAmount = getAttributetext(driver.findElement(tfQFTEnterAmount), "value");
            if ((enterAmount.isEmpty())) {
                addToReport("Successfully validated QFT enter amount is empty ", Status.PASS, false);
            } else {
                addToReport("QFT enter amount  " + enterAmount + " is not successfully validated", Status.FAIL);
            }

            //Validate the preloaded Beneficiary remark
            String beneficiaryRemark = getAttributetext(driver.findElement(tfQFTEnterBRemarks), "value");
            if ((beneficiaryRemark.isEmpty())) {
                addToReport("Successfully validated QFT beneficiary remark is empty ", Status.PASS, false);
            } else {
                addToReport("QFT beneficiary remark : " + beneficiaryRemark + " is not successfully validated", Status.FAIL);
            }

            //scroll to web element and check its presence
            scrollToWebElement(btnQFTSubmit);
            if (isElementClickable(btnQFTSubmit)) {
                addToReport("Successfully validated presence of submit button in QFT ", Status.PASS, true);
            } else {
                addToReport("Failed to validate presence of submit button in  QFT ", Status.FAIL, true);
            }

        } catch (Exception e) {
            addToReport("Error verifying quick fund transfer popup", Status.FAIL);
            throw new RuntimeException("Error - verifying quick fund transfer popup " + e.getMessage(), e);
        }
        clickOnElementUsingJS(btnQFTBack);
    }

    /**
     * Validate the Favourite biller popup
     *
     * @param FBillerData - Favourite biller data
     */
    public void validateFBillerPopup(String[] FBillerData) {
        try {
            String BName = FBillerData[1];
            String FieldName = FBillerData[2];

            waitForElementToBeClickable(btnQFTSubmit,LONG_WAIT);
            waitForElementPresence(lblFBBillerName,LONG_WAIT);

            //validate account label
            boolean boolSavingsAccount = isElementPresentBy(lblFBSBAName());
            if (boolSavingsAccount) {
                addToReport("Successfully validated user account label", Status.PASS, false);
            } else {
                addToReport("User account label is not visible.", Status.FAIL);
            }

            //Validate the preloaded from account
            String fromAccount = CommonUtils.removeSpaceCharacters(getAttributetext(driver.findElement(ddQFTFromAccount), "value"));
            if ((!fromAccount.isEmpty()) && CommonUtils.containsNumericCharacters(fromAccount)) {
                addToReport("Successfully validated QFT from account number : " + fromAccount, Status.PASS, false);
            } else {
                addToReport("QFT from account number : " + fromAccount + " is not successfully validated", Status.FAIL);
            }

            //Validate the preloaded biller name
            String billerName = getAttributeOrText(lblFBBillerName, "value");
            if (billerName.equalsIgnoreCase(BName)) {
                addToReport("Successfully validated biller name : " + BName, Status.PASS, false);
            } else {
                addToReport("Biller name : " + BName + " is not successfully validated", Status.FAIL);
            }

            //Validate the preloaded mobile number
//            String MNumber = getAttributetext(driver.findElement(tfFBMobilePhoneNumber), "value");
            String MNumber = getAttributeOrText(tfFBMobilePhoneNumber, "value");
            if (FieldName.equalsIgnoreCase(MNumber)) {
                addToReport("Successfully validated Pre-loaded mobile number : " + MNumber, Status.PASS, false);
            } else {
                addToReport("Pre-loaded mobile number : " + MNumber + " is not successfully validated", Status.FAIL);
            }
            //Validate the preloaded Enter amount
//            String enterAmount = getAttributetext(driver.findElement(tfFBAmount), "value");
            String enterAmount = getAttributeOrText(tfFBAmount, "value");
            if (!enterAmount.isEmpty()) {
                addToReport("Successfully validated QFT enter amount is empty ", Status.PASS, false);
            } else {
                addToReport("QFT enter amount  " + enterAmount + " is not successfully validated", Status.FAIL);
            }
            //Validate the reenter mobile number
//            String ReEnter = getAttributetext(driver.findElement(tfFBMobilePhoneNumberReEnter), "value");
            String ReEnter = getAttributeOrText(tfFBMobilePhoneNumberReEnter, "value");
            if ((ReEnter.isEmpty())) {
                addToReport("Successfully validated reenter mobile field is empty ", Status.PASS, false);
            } else {
                addToReport("Reenter mobile field  is not successfully validated", Status.FAIL);
            }

            //scroll to web element and check its presence
            scrollToWebElement(btnQFTSubmit);
            if (isElementClickable(btnQFTSubmit)) {
                addToReport("Successfully validated presence of Next button in Fav biller ", Status.PASS, true);
            } else {
                addToReport("Failed to validate presence of Next button in  Fav biller ", Status.FAIL, true);
            }
            //Close the popup
            clickOnElementUsingJS(btnQFTBack);

        } catch (Exception e) {
            addToReport("Error verifying favourite biller popup", Status.FAIL);
            throw new RuntimeException("Error - verifying favourite biller popup " + e.getMessage(), e);
        }
    }

    /**
     * Add new favourite payee
     */
    public void addNewFavouritePayee() {

        try {
            waitForElementPresence(tblSavedPayeesRows);

            //Obtain total row count of payees
            int totalRowsSP = isElementsPresentBy(tblSavedPayeesRows);
            if (totalRowsSP > 0) {
                addToReport("Successfully obtained row count as : " + totalRowsSP, Status.PASS, false);
            } else {
                addToReport("Unable to obtain row count", Status.FAIL);
            }

            //obtain non-favourites count
            int totalRowsSPNonFav = isElementsPresentBy(tblSPAddToFavPayeeUnchecked);
            if (totalRowsSPNonFav > 0) {
                addToReport("Successfully obtained row count as : " + totalRowsSPNonFav, Status.PASS, false);
            } else {
                addToReport("There are no records to update to favourite", Status.FAIL);
                throw new RuntimeException("Error - No records to update to favourite");
            }

            //Update the last record as fav payee
            //Retrieve nickname where 4the column is dedicated for nickName
            String nickName = getTextFromElement(tblObtainCellValue(totalRowsSP, 4)).trim();
            if (!nickName.isEmpty()) {
                addToReport("Successfully obtained nickname : " + nickName, Status.PASS, false);
            } else {
                addToReport("Nickname is not successfully obtained", Status.FAIL);
                throw new RuntimeException("Error - Nickname is not successfully obtained from grid");
            }

            //select last record as fav payee
            scrollToWebElement(icnSavedPayeesAddToFav(totalRowsSP));
            clickOnElement(icnSavedPayeesAddToFav(totalRowsSP));

            //validate success msg
            waitForElementPresence(lblPopupMsgFavPayeeAdded);
            addToReport("Successfully favourite payee added message appeared", Status.PASS, true);
            waitForElementToBeInvisible(lblPopupMsgFavPayeeAdded, LONG_WAIT);

            //validate nickname in your favourite payee list
            boolean boolYFN = isElementPresentBy(tblYFLNickName(nickName));
            if (boolYFN) {
                addToReport("Successfully validated added favourite payee : " + nickName, Status.PASS, true);
            } else {
                addToReport("Add favourite payee : " + nickName + " was not validated", Status.FAIL);
            }

            //Revert the changes
            scrollToWebElement(icnSavedPayeesAddToFav(totalRowsSP));
            clickOnElement(icnSavedPayeeByNName(nickName));

            //validate success msg
            waitForElementPresence(lblPopupMsgFavPayeeRemoved);
            addToReport("Successfully favourite payee removed message appeared", Status.PASS, true);
            waitForElementToBeInvisible(lblPopupMsgFavPayeeRemoved, LONG_WAIT);

        } catch (Exception e) {
            addToReport("Error when adding favourite payee", Status.FAIL);
            throw new RuntimeException("Error - Failed to adding favourite payee " + e.getMessage(), e);
        }
    }

}


