package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.constants.CreditCardConstants;

import static utils.Drivers.*;

public class SettingsPage extends BasePage {


    public SettingsPage(WebDriver driver) {
        super(driver);
    }

    public enum ElementType {
        button, label, span, div;
    }

    private static final By txtUserName = By.xpath("//input[contains(@name,'username')]");
    private static final By txtPassword = By.xpath("//input[contains(@name,'password')]");
    private static final By imgUserIcon = By.xpath("//img[@class='NavBar_user__Ena5m ']");
    private static final By imgSettingsIcon = By.xpath("(//div[text()='Settings'])[1]");
    private static final By btnConfirm = By.xpath("//button[contains(normalize-space(text()),'Confirm')]");
    private static final By lblSecurityDivSettings = By.xpath("//div[normalize-space(text())='Security']");
    private static final By btnChangeOTPVerificationMode = By.xpath(" //button[contains(normalize-space(text()),'Change OTP Verification Mode')]");
    private static final By title = By.xpath("//title[text()='Sampath Vishwa | Dashboard']");
    private static final By Otptitle = By.xpath("//form//div[@class='font-bold']");
    private static final By btnChangePassword = By.xpath("//button[contains(normalize-space(text()),'Change Password')]");
    private static final By tfoldPassword = By.xpath("//input[@id='oldPassword']");
    private static final By tfnewwPassword = By.xpath("//input[@id='newPassword']");
    private static final By tfconfirmPassword = By.xpath("//input[@id='confirmPassword']");
    private static final By btnConfirmSave = By.xpath("//button[text()='Confirm & Save']");
    private static final By pageTitle = By.xpath("//title[text()='Sampath Vishwa | Login']");
    private static final By primaryAccountSpan = By.xpath("//span[text()='Primary Account']");
    private static final By primaryAccountTitle = By.xpath("//div[text()='Select Primary Account']");
    private static final By imgUpArrow = By.xpath("//img[contains(@src, 'blackArrowUp')]");
    private static final By btnLogin = By.xpath("//button[@type='submit']");
    private static final By btnBack = By.xpath("//button[text()='Back']");
    private static final By lblvishwaAccount = By.xpath("//div[normalize-space(text())='Vishwa Account Settings']");
    private static final By lblUserSettingsRows = By.xpath("//div[contains(@class, 'flex flex-col items-center ')][1]//div[contains(@class,'w-full')]");
    private static final By btnClose = By.xpath("//button[contains(@class, 'Toastify__close-button') and @aria-label='close']");

    private static By tfOTP(int Index) {
        return By.xpath("//input[contains(@class,'otp-box')][" + Index + "]");
    }

    private static By getSuccessfulMsg(String title) {
        return By.xpath("//div[contains(text(),'" + title + "')]");
    }

    private static By errorMessage(String errorText) {
        return By.xpath("//div[@role='alert']/div[contains(text(),'" + errorText + "')]");
    }

    private static By lblUserSettingsValues(int row) {
        return By.xpath("//div[contains(@class, 'flex flex-col items-center ')]//div[contains(@class,'w-full')][" + row + "]");
    }

    private static By getElementByTypeAndText(SettingsPage.ElementType type, String text, int index) {
        return By.xpath("(//" + type.name() + "[contains(normalize-space(.), \"" + text + "\")])[" + index + "]");
    }

    /**
     * This method is entering the OTP to navigates to the Settings page
     *
     * @param otp - OTP
     */
    public void enterOTPAndContinueSettingsPage(String otp) {

        //Enter OTP values and continue
        try {
            sendKeysToElement(tfOTP(1), String.valueOf(otp));

            clickOnElement(btnConfirm);
            clickOnElement(btnClose);
        } catch (Exception e) {
            addToReport("Error when entering OTP", Status.FAIL);
            throw new RuntimeException("Failed to enter OTP " + e.getMessage(), e);
        }
        addToReport("----------End the Navigation to settings section ----------", Status.INFO, false);
    }

    /**
     * This method is entering the OTP to navigates to the Settings page
     *
     * @param otp - OTP
     */
    public void enterOTPAndContinuePasswordChangePage(String otp) {

        //Enter OTP values and continue
        try {
            sendKeysToElement(tfOTP(1), String.valueOf(otp));

            clickOnElement(getElementByTypeAndText(ElementType.button, CreditCardConstants.NEXT, 1));
            clickOnElement(btnClose);
        } catch (Exception e) {
            addToReport("Error when entering OTP", Status.FAIL);
            throw new RuntimeException("Failed to enter OTP " + e.getMessage(), e);
        }
        addToReport("----------End Navigating Password change section ----------", Status.INFO, false);
    }


    /**
     * This method is to Navigate to the settings panel
     *
     * @param successMsg - Success message on login in
     */

    public void navigateToSettings(String successMsg) {

        addToReport("----------Start the Navigation to settings section ----------", Status.INFO, false);
        try {
            clickOnElement(imgUserIcon);
            clickOnElement(imgSettingsIcon);

            if (isElementPresentBy(getSuccessfulMsg(successMsg))) {
                addToReport("'" + successMsg + "' success in Navigating to settings.", Status.PASS, true);

            } else {
                addToReport("'" + successMsg + "' success message is not present to settings.", Status.FAIL);
                throw new RuntimeException("Navigating is unsuccessful.");
            }
        } catch (Exception e) {
            addToReport("Unable to navigate to settings page '" + successMsg + "''.", Status.FAIL);
        }


    }

    /**
     * This method is to validate to the settings panel
     *
     * @param settingsUserDetails - users details
     */
    public void validateUserSettingsData(String settingsUserDetails) {

        try {
            addToReport("----------Start validating the user data ----------", Status.INFO, false);

            boolean isTileVisible = waitForElementPresence(lblvishwaAccount, MODERATE_WAIT);

            if (isTileVisible) {
                addToReport("Settings page title is visible.", Status.PASS, false);
            } else {
                addToReport("Settings page title is not visible.", Status.FAIL);

            }
            // Split the input string using escaped pipe
            String[] expectedSettingValues = settingsUserDetails.split("\\|");

            // Obtain the settings record count
            int recordCount = isElementsPresentBy(lblUserSettingsRows);
            if (recordCount == 0) {
                addToReport("User settings section displayed no records", Status.FAIL);
                return;
            }

            int loopCount = Math.min(recordCount, expectedSettingValues.length);

            for (int row = 1; row <= loopCount; row++) {
                String settingValue = getTextFromElement(lblUserSettingsValues(row)).trim();
                String expectedValue = expectedSettingValues[row - 1].trim();

                if (!settingValue.isEmpty() && settingValue.equalsIgnoreCase(expectedValue)) {
                    addToReport("User setting record number " + row + " validated successfully with value: " + settingValue, Status.PASS, false);
                } else {
                    addToReport("Failed to validate user setting record for row: " + row + ". Expected: " + expectedValue + ", Actual: " + settingValue, Status.FAIL);
                    throw new RuntimeException("Error - Failed to validate user setting record for row " + row);
                }
            }

        } catch (Exception e) {
            addToReport("Exception during user settings validation: " + e.getMessage(), Status.FAIL);
            throw new RuntimeException("Error during user settings validation", e);
        }
        addToReport("----------End validating the user data ----------", Status.INFO, false);

    }


    /**
     * This method use to navigate to the OTP verification mode
     */
    public void NavigatetoToOTPVerificationMode() {

        addToReport("----------Start Navigating and validating the OTP verification ----------", Status.INFO, false);


        try {
            //validate Security button and click
            boolean SecurityIcon = isElementPresentBy(lblSecurityDivSettings);
            if (SecurityIcon) {
                clickOnElement(lblSecurityDivSettings);
                addToReport("Successfully clicked on Security tab and navigated", Status.PASS);
            } else {
                addToReport("Security tab is not visible", Status.FAIL);
                throw new RuntimeException("Error - Security tab icon is not visible");
            }
            //validate OPT icon and click
            boolean ChangeOTPVerificationModebtn = isElementPresentBy(btnChangeOTPVerificationMode);
            if (ChangeOTPVerificationModebtn) {
                clickOnElement(btnChangeOTPVerificationMode);
                addToReport("Successfully clicked on OTP tab and navigated", Status.PASS);
            } else {
                addToReport("OTP tab is not visible", Status.FAIL);
                throw new RuntimeException("Error - OTP button icon is not visible");
            }

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * This method is used to navigate to the Security section
     */
    public void NavigatetoToSecuritysection() {
        try {
            //validate Security button and click
            boolean SecurityIcon = isElementPresentBy(lblSecurityDivSettings);
            if (SecurityIcon) {
                clickOnElement(lblSecurityDivSettings);
                addToReport("Successfully clicked on Security tab and navigated", Status.PASS);
            } else {
                addToReport("Security tab is not visible", Status.FAIL);
                throw new RuntimeException("Error - Security tab icon is not visible");
            }

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * This method is used to validate the OTP page
     */

    public void validateTheSettingsOTPPage() {
        try {
            //validate the page title and page header
            boolean isTitleVisible = waitForElementPresence(title);
            boolean isTileVisible = waitForElementPresence(Otptitle);

            if (isTitleVisible && isTileVisible) {
                addToReport("OTP page tile heading and title is visible.", Status.PASS, false);
            } else {
                addToReport("Title or OTP tile is not visible as expected.", Status.FAIL);

            }
            boolean Backbtn = isElementPresentBy(btnBack);
            if (Backbtn) {
                clickOnElement(btnBack);
                addToReport("Successfully clicked on Back Button and navigated", Status.PASS);
            } else {
                addToReport("Back Button is not visible", Status.FAIL);

            }

        } catch (Exception e) {
            addToReport("Error verifying page title and OTP tile heading", Status.FAIL);
            throw new RuntimeException("Failed to validate the title and tile: " + e.getMessage(), e);
        }

        addToReport("----------End Navigating and validating the OTP verification ----------", Status.INFO, false);
    }

    /**
     * This method use to navigate to the password section
     */
    public void navigateToPasswordSection() {

        addToReport("----------Start Navigating Password change section ----------", Status.INFO, false);
        try {
            boolean PasswordButton = isElementPresentBy(btnChangePassword);
            if (PasswordButton) {
                clickOnElement(btnChangePassword);
                addToReport("Successfully clicked on Password Button and navigated", Status.PASS);
                clickOnElement(btnClose);
            } else {
                addToReport("PasswordButton is not visible", Status.FAIL);
                throw new RuntimeException("Error - Back Button is not visible");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * This methoud use to Change the user password
     *
     * @param password    - Old password
     * @param newPassword - New password
     */
    public void passwordChange(String password, String newPassword) {
        addToReport("----------Start Password change  ----------", Status.INFO, false);
        try {
            //Enter new password and click save
            sendKeysToElement(tfoldPassword, password);
            isElementPresentBy(tfnewwPassword, SHORT_WAIT);
            sendKeysToElement(tfnewwPassword, newPassword);
            sendKeysToElement(tfconfirmPassword, newPassword);

            clickOnElement(btnConfirmSave);
            boolean loginPageTitle = waitForElementPresence(pageTitle, MODERATE_WAIT);
            if (loginPageTitle) {
                addToReport("successfully change the password.", Status.PASS, true);
            } else {
                addToReport("Password change fail.", Status.FAIL);
                throw new RuntimeException("Password change unsuccessful.");
            }
        } catch (Exception e) {
            addToReport("Unable to change the password", Status.FAIL);
            throw new RuntimeException("Failed to change the password : " + e.getMessage(), e);

        }
        addToReport("----------End Password change  ----------", Status.INFO, false);
    }


    /**
     * This method use to login into the app after the password change
     *
     * @param userName          - User name
     * @param password          - Old Password
     * @param newPassword       - New Password
     * @param LoginErrorMessage - Error message for the wrong password
     * @param successMsg        - Success message for the correct password
     */
    public void loginAfterChangingThePassword(String userName, String password, String newPassword, String LoginErrorMessage, String successMsg) {

        try {
            sendKeysToElement(txtUserName, userName);
            sendKeysToElement(txtPassword, password);
            clickOnElement(btnLogin);

            if (isElementPresentBy(errorMessage(LoginErrorMessage))) {
                addToReport("'" + LoginErrorMessage + "' Error message is present.", Status.PASS, true);
            } else {
                addToReport("'" + LoginErrorMessage + "' Error message is not present.", Status.FAIL);
                throw new RuntimeException("Password changing is unsuccessful.");
            }

        } catch (Exception e) {
            addToReport("Error when entering OTP", Status.FAIL);
            throw new RuntimeException("Failed to enter OTP " + e.getMessage(), e);
        }
        clearTheElement(txtUserName);
        sendKeysToElement(txtUserName, userName);
        clearTheElement(txtPassword);
        sendKeysToElement(txtPassword, newPassword);
        clickOnElement(btnLogin);

        if (isElementPresentBy(getSuccessfulMsg(successMsg))) {
            addToReport("'" + successMsg + "' success message is present.", Status.PASS, true);
        } else {
            addToReport("'" + successMsg + "' success message is not present.", Status.FAIL);
            throw new RuntimeException("Login is unsuccessful.");
        }
    }


    /**
     * This method validates the users Primary account
     */
    public void validatePrimaryAccount() {

        try {
            //validate the primary account title
            boolean primaryTitleVisible = waitForElementPresence(primaryAccountTitle, MODERATE_WAIT);
            boolean primaryTitleSpanVisible = waitForElementPresence(primaryAccountSpan, MODERATE_WAIT);

            if (primaryTitleVisible && primaryTitleSpanVisible) {

                addToReport("Primary account is available for user.", Status.PASS, false);
            } else {
                addToReport("Primary account is unavailable for user .", Status.FAIL);

            }

            boolean arrowbtun = isElementPresentBy(imgUpArrow);
            if (arrowbtun) {
                clickOnElement(imgUpArrow);
                addToReport("Successfully clicked on Arrow Button and navigated", Status.PASS);
            } else {
                addToReport("Arrow Button is not visible", Status.FAIL);
                throw new RuntimeException("Error - Arrow Button is not visible");
            }
            boolean setPrimaryAccount = waitForElementPresence(getElementByTypeAndText(ElementType.span, CreditCardConstants.SET_AS_PRIMARY_ACCOUNT, 1), MODERATE_WAIT);
            if (setPrimaryAccount) {
                clickOnElement(getElementByTypeAndText(ElementType.span, CreditCardConstants.SET_AS_PRIMARY_ACCOUNT, 1));
                addToReport("Successfully clicked on the Set primary account button", Status.PASS);
            } else {
                addToReport("unable to clicked on the Set primary account button", Status.FAIL);
                throw new RuntimeException("Error - primary account button is not visible");
            }

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * This method is entering the OTP to navigates to the Settings page and looking for the success message
     *
     * @param primaryAccountSentSuccessMsg - success message after changing the primary message
     */
    public void enterOTPToChangeThePrimaryAccount(String primaryAccountSentSuccessMsg) {

        //Enter OTP values and continue
        try {

            if (isElementPresentBy(getSuccessfulMsg(primaryAccountSentSuccessMsg))) {
                addToReport("'" + primaryAccountSentSuccessMsg + "' success message is present.", Status.PASS, false);
                clickOnElement(btnClose);
            } else {
                addToReport("'" + primaryAccountSentSuccessMsg + "' success message is not present.", Status.FAIL);
                throw new RuntimeException("Account changing is unsuccessful.");
            }
        } catch (Exception e) {
            addToReport("Error when entering OTP", Status.FAIL);
            throw new RuntimeException("Failed to enter OTP " + e.getMessage(), e);
        }
    }


}
