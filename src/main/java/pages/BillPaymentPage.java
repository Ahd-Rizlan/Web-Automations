/*
 *   @author - salman R
 */
package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.CommonUtils;

public class BillPaymentPage extends BasePage {

    CommonUtils commonUtils = new CommonUtils();

    public BillPaymentPage(WebDriver driver) {
        super(driver);
    }

    public enum ElementType {
        button, label, span, div;
    }

    private static final By imgAddToFav = By.xpath("//img[contains(@class,'cursor-pointer invert') and @alt='']");
    private static final By imgSavedBillerFavRecords = By.xpath("//img[contains(@srcset,'.c7bd4030') and @alt='']");
    private static final By imgSavedBillerNonFavRecords = By.xpath("//img[contains(@srcset,'.5a2f492b') and @alt='']");
    private static final By imgSampathPreLoader = By.xpath("//img[contains(@srcset,'Fpreloader')]");
    private static final By btnConfirm = By.xpath("//button[contains(normalize-space(text()),'Confirm')]");
    private static final By btnDisabledConfirm = By.xpath("//button[contains(normalize-space(text()),'Confirm') and @disabled]");
    private static final By tblRows = By.xpath("//table//tbody/tr");
    private static final By btnFilter = By.xpath("//button[text()='Filter']");
    private static final By tfAmountFrom = By.xpath("//input[@placeholder='Amount From']");
    private static final By tfAmountTo = By.xpath("//input[@placeholder='Amount To']");
    private static final By tfTransactionDate = By.xpath("//input[@placeholder='Transaction date']");
    private static final By ddStatus = By.xpath("//select[@id='status']");
    private static final By btnApplyFilters = By.xpath("//button[text()='Apply Filters']");
    private static final By ddMonth = By.xpath("//span[@class='rdrMonthPicker']/select");
    private static final By ddYear = By.xpath("//span[@class='rdrYearPicker']/select");


    private static By tfOTP(int Index) {
        return By.xpath("//input[@type='password'][" + Index + "]");
    }

    private static By lnkCategory(String category) {
        return By.xpath("//span[contains(text(),'" + category + "')]");
    }

    private static By btnSubHeader(String subHeader) {
        return By.xpath("//div[contains(@class,'flex')]/div[text()="+ subHeader +"]");
    }
    private static By lblRVTTransferPopupRecords(int col, int row) {
        return By.xpath("(//table//tr/td[" + col + "])[" + row + "]");
    }
    private static By lblSavedBillerTemplateName(int row) {
        return By.xpath("(//img[contains(@srcset,'.c7bd4030') and @alt='']/ancestor::tr/td[4])[" + row + "]");
    }

    /**
     * Validate the title and header of the otp page
     *
     * @param expectedTitle - expected title text
     *
     */
    public void validateAddToFavColumn(String headerName) {
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


