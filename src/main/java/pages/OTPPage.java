/*
 *   @author - salman R
 */
package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.CommonUtils;

public class OTPPage extends BasePage {

    CommonUtils cu = new CommonUtils();

    public OTPPage(WebDriver driver) {
        super(driver);
    }

    public enum ElementType {
        button, label, span, div;
    }

    private static final By btnConfirm = By.xpath("//button[contains(normalize-space(text()),'Confirm')]");
    private static final By btnDisabledConfirm = By.xpath("//button[contains(normalize-space(text()),'Confirm') and @disabled]");

    private static By tfOTP(int Index) {
        return By.xpath("//input[@type='password'][" + Index + "]");
    }

    private static By lblPageTitle(String title) {
        return By.xpath("//title[contains(text(),'" + title + "')]");
    }

    private static By lblPageTileHeader(String tileText) {
        return By.xpath("//div[contains(text(),\"" + tileText + "\")]");
    }

    /**
     * Validate the title and header of the otp page
     *
     * @param expectedTitle - expected title text
     * @param otpTileName   - OTP tile name
     *
     */
    public void validateTheOTPPage(String expectedTitle, String otpTileName) {
        try {
            //validate the page title and page header
            boolean isTitleVisible = waitForElementPresence(lblPageTitle(expectedTitle));
            boolean isTileVisible = waitForElementPresence(lblPageTileHeader(otpTileName));
            if (isTitleVisible && isTileVisible) {
                addToReport("OTP page tile heading '" + otpTileName + "' and title '" + expectedTitle + "' is visible.", Status.PASS,false);
            } else {
                addToReport("Title or OTP tile is not visible as expected.", Status.FAIL);
                throw new RuntimeException("Title or OTP tile is not visible as expected.");
            }

        } catch (Exception e) {
            addToReport("Error verifying page title '" + expectedTitle + "' and OTP tile heading '" + otpTileName + "'.", Status.FAIL);
            throw new RuntimeException("Failed to validate the title and tile: " + e.getMessage(), e);
        }
    }

    /**
     * Enter otp value
     *
     * @param otp - expected title text
     *
     */
    public void enterOTPAndContinue(String otp) {

        try {

            //validate if confirm button is disabled prior to entering otp
            boolean isBtnConfirmDisabled = waitForElementPresence(btnDisabledConfirm);

            if (isBtnConfirmDisabled) {
                addToReport("OTP page button confirm is disabled", Status.PASS,false);
            } else {
                addToReport("OTP page button confirm is not disabled", Status.FAIL);
                throw new RuntimeException("OTP page button confirm is not disabled as expected.");
            }

            //Enter OTP values and continue
            sendKeysToElement(tfOTP(1), String.valueOf(otp));

            clickOnElement(btnConfirm);
        } catch (Exception e) {
            addToReport("Error when entering OTP", Status.FAIL);
            throw new RuntimeException("Failed to enter OTP " + e.getMessage(), e);
        }
    }

}


