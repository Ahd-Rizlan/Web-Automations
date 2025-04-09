/*
 *   @author - salman R
 */
package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.CommonUtils;

import java.util.Arrays;
import java.util.List;


public class LoginPage extends BasePage {

    CommonUtils commonutils = new CommonUtils();

    public LoginPage(WebDriver driver) {

        super(driver);

    }

    public enum ElementType {
        button, label, span, div;
    }

    private static final By txtUserName = By.xpath("//input[contains(@name,'username')]");
    private static final By txtPassword = By.xpath("//input[contains(@name,'password')]");
    private static final By imgUserIcon = By.xpath("//img[contains(@class,'NavBar_user__Ena5m')]");
    private static final By lblForgotPasswordHeading = By.xpath("//div[contains(@class, 'forgot_pageContainer__TAT35')]");
    private static final By lblExclamationMark = By.xpath("//label[contains(normalize-space(text()), 'Enter New Password')]/following::div[text()='!']");
    private static final By lblPpolicyPopup = By.xpath("//div[contains(@class, 'justify-center flex')]");
    private static final By lstPolicyList = By.xpath("//ul[contains(@class,'list-disc pl-5')]");
    private static final By txtAnswerInputField = By.xpath("//input[contains(@name,'answer')]");
    private static final By txtNewPassword = By.xpath("//input[contains(@name,'newPassword')]");
    private static final By txtConfirmPassword = By.xpath("//input[contains(@name,'confirmPassword')]");
    private static final By btnCloseDashboardAlertPopup = By.xpath("//span[text()='X']");
    private static final By btnCloseAlertLabel = By.xpath(" //button[@aria-label='close']");
    private static final By btnLogin = By.xpath("//button[@type='submit']");
    private static final By btnLoginDisabled = By.xpath("//button[@type='submit' and @disabled]/div");
    private static final By msgError = By.xpath("//div[@role='alert']/div[contains(text(),'LOGIN FAILED')]");
    private static final By btnBack = By.xpath("//button[text()='Back']");
    private static final By tfVishwaID = By.xpath("//input[@id='username']");
    private static final By tfSecurityAnsOne = By.xpath("//input[@id='answer']");
    private static final By popUpIncorrectUserId = By.xpath("//div[@role='alert']/div[2]");
    private static final By popUpMsg = By.xpath("//div[@role='alert']");
    private static final By icnCustomLoader = By.xpath("//div[contains(@class,'LoginComponent_customloader')]");


    private static By getElementByTypeAndText(ElementType type, String text) {
        return By.xpath("//" + type.name() + "[contains(normalize-space(text()), \"" + text + "\")]");
    }

    private static By disabledLoginButton(String buttonText) {
        return By.xpath("//button[contains(normalize-space(text()),'" + buttonText + "') and @disabled]");
    }

    private static By getPageTitle(String title) {
        return By.xpath("//title[contains(text(),'" + title + "')]");
    }

    private static By getPageHeader(String title) {
        return By.xpath("//div[contains(text(),'" + title + "')]");
    }

    private static By getSuccessfulMsg(String title) {
        return By.xpath("//div[contains(text(),'" + title + "')]");
    }

    private static By loginTile(String tileText) {
        return By.xpath("//span[text()='" + tileText + "' and contains(@class,'LoginComponent')]");
    }

    private static By errorMessage(String errorText) {
        return By.xpath("//div[@role='alert']/div[contains(text(),'" + errorText + "')]");
    }

    private static By inputPlaceholder(String placeholderText) {
        return By.xpath("//input[@placeholder='" + placeholderText + "']");
    }

    private static By logoutButton(String buttonText) {
        return By.xpath("//div[contains(@class,'NavBar_userDropDown_')]//div[contains(normalize-space(text()), '" + buttonText + "')]");
    }

    private static By logoutPopup(String popupText) {
        return By.xpath("//div[contains(@class,' justify-center flex')]//div[contains(normalize-space(text()), '" + popupText + "')]");
    }

    private static By exclamationMark(String text) {
        return By.xpath("");
    }

    /**
     * Validate the title of the login page
     *
     * @param expectedTitle - expected title text
     * @param loginTileName - login tile name
     */
    public void validateTheLoginPage(String expectedTitle, String loginTileName) {
        try {
            //validate the title and tile
            boolean isTitleVisible = waitForElementPresence(getPageTitle(expectedTitle));
            boolean isTileVisible = waitForElementPresence(loginTile(loginTileName));
            if (isTitleVisible && isTileVisible) {
                addToReport("Login page tile heading '" + loginTileName + "' and title '" + expectedTitle + "' is visible.", Status.PASS, false);
            } else {
                addToReport("Title or login tile is not visible as expected", Status.FAIL);
                throw new RuntimeException("Title or login tile is not visible as expected");
            }

        } catch (Exception e) {
            addToReport("Error verifying page title '" + expectedTitle + "' and login tile heading '" + loginTileName + "'.", Status.FAIL);
            throw new RuntimeException("Error - Failed to validate the title and tile: " + e.getMessage(), e);
        }
    }

    /**
     * Login to the application
     *
     * @param name           - User name
     * @param password       - Login password
     * @param successMsg     - Success message on login in
     * @param expectedHeader - Expected header text in login page
     * @param isRevertBack   - Should the journey revert back after login in
     */
    public void loginToSampathVishwaWeb(String name, String password, String successMsg, String expectedHeader, boolean isRevertBack) {
        try {
            //Enter credentials and click login
            waitForElementToBeClickable(txtUserName,10);
            sendKeysToElement(txtUserName, name);
            sendKeysToElement(txtPassword, password);
            clickOnElement(btnLogin);

            waitForElementToBeInvisible(btnLogin,20);
            waitForElementPresence(getSuccessfulMsg(successMsg),20);
            //Validate the success message
            if (isElementPresentBy(getSuccessfulMsg(successMsg))) {
                addToReport("'" + successMsg + "' success message is present.", Status.PASS,true);
            } else {
                addToReport("'" + successMsg + "' success message is not present.", Status.FAIL);
                throw new RuntimeException("Login is unsuccessful.");
            }
        } catch (Exception e) {
            addToReport("Unable to verify dashboard page title '" + successMsg + "''.", Status.FAIL);
            throw new RuntimeException("Failed to validate the dashboard page title: " + e.getMessage(), e);
        }
        try {

            //validate the page header
            if (isElementPresentBy(getPageHeader(expectedHeader))) {
                addToReport("'" + expectedHeader + "'  page header is present.", Status.PASS, false);
            } else {
                addToReport("'" + expectedHeader + "' page header is not present.", Status.FAIL);
                throw new RuntimeException("Login is unsuccessful");
            }

            if (isRevertBack) {
                //Navigate back to login page
                clickOnElement(btnBack);
            }

        } catch (Exception e) {
            addToReport("Unable to verify dashboard page header '" + expectedHeader + "''.", Status.FAIL);
            throw new RuntimeException("Error - Failed to validate the dashboard page header " + e.getMessage(), e);

        }
    }

    /**
     * validate the incorrect Login with incorrect user ID
     *
     * @param name       - User name
     * @param password   - Login password
     * @param buttonName - Login button name
     * @param errorText  - error message text
     */
    public void ValidateLoginWithIncorrectUserID(String name, String password, String buttonName, String errorText) {
        try {
            sendKeysToElement(txtUserName, name);
            sendKeysToElement(txtPassword, password);
            clickOnElement(getElementByTypeAndText(ElementType.button, buttonName));
            boolean isErrorMessageVisible = waitForElementPresence(errorMessage(errorText));
            if (isErrorMessageVisible) {
                addToReport("User not able to Login with incorrect User ID.", Status.PASS);
            } else {
                addToReport("User able to Login with incorrect User ID.", Status.FAIL);
                throw new RuntimeException("Error - Login is unsuccessful.");
            }
        } catch (Exception e) {
            addToReport("Unable to verify incorrect user login", Status.FAIL);
            throw new RuntimeException("Failed to validate the incorrect user login. ", e);
        }
    }

    /**
     * validate the incorrect Login with incorrect user ID
     *
     * @param name            - User name
     * @param validPassword   - Login password
     * @param invalidPassword - Invalid password
     * @param errorText       - error message text
     */
    public void ValidateLoginWithIncorrectPassword(String name, String validPassword, String invalidPassword, String errorText) {
        try {

            //Attempt to login using invalid credentials
            sendKeysToElement(txtUserName, name);
            sendKeysToElement(txtPassword, invalidPassword);
            clickOnElement(btnLogin);
            waitForElementToBeInvisible(btnLoginDisabled, 4);

            //Extract the message from alert
            String[] ErrorMsg = CommonUtils.splitText(getTextFromElement(msgError), ":");
            int attemptCount = Integer.parseInt(ErrorMsg[1].trim());
            clickOnElement(btnCloseAlertLabel);

            //Validate the error message
            if (errorText.equals(ErrorMsg[0]) && attemptCount < 5 && 1 < attemptCount) {
                addToReport("Error message successfully displayed mentioning the remaining number of attempts", Status.PASS);
            } else {
                addToReport("Error message was not successfully displayed mentioning the remaining number of attempts", Status.FAIL);
                throw new RuntimeException("Error - Error message was not successfully displayed");
            }

            //Re-Login with correct credentials
            sendKeysToElement(txtUserName, name);
            sendKeysToElement(txtPassword, validPassword);
            clickOnElement(btnLogin);
            waitForElementToBeInvisible(btnLoginDisabled, 5);
            clickOnElement(btnCloseAlertLabel);
            clickOnElement(btnBack);


            //Re-attempt to login using invalid credentials
            waitForElementPresence(txtUserName);
            sendKeysToElement(txtUserName, name);
            sendKeysToElement(txtPassword, invalidPassword);
            clickOnElement(btnLogin);
            waitForElementToBeInvisible(btnLoginDisabled, 5);
            waitForElementPresence(msgError);

            //Extract the message from alert
            String[] updatedErrorMsg = CommonUtils.splitText(getTextFromElement(msgError), ":");
            int reAttemptCount = Integer.parseInt(updatedErrorMsg[1].trim());
            waitForElementPresence(btnBack);

            //Validate reattempt counter
            if (reAttemptCount == 4) {
                addToReport("Re-Attempt counter was updated successfully", Status.PASS);
            } else {
                addToReport("Re-Attempt counter was not updated successfully", Status.FAIL);
                throw new RuntimeException("Error - Error message was not successfully displayed");
            }

        } catch (Exception e) {
            addToReport("Unable to verify incorrect Password", Status.FAIL);
            throw new RuntimeException("Error - Failed to validate the incorrect Password. " + e.getMessage(), e);
        }
    }

    /**
     * validate the incorrect Login with incorrect user ID
     *
     * @param inputValue      - User name or password value
     * @param buttonName      - Login button name
     * @param placeholderText -  placeholder text
     * @param isUserID        -  true - to check only using user ID/ false - to check only using password
     */

    public void validateLoginWithOnlyUserIDOrPassword(String inputValue, String buttonName, String placeholderText, boolean isUserID) {
        try {
            if (isUserID) {
                sendKeysToElement(txtUserName, inputValue);
            } else {
                clearTheElement(txtUserName);
                sendKeysToElement(txtPassword, inputValue);
            }

            boolean disableButton = waitForElementPresence(disabledLoginButton(buttonName));
            boolean placeholder = waitForElementPresence(inputPlaceholder(placeholderText));

            if (disableButton && placeholder) {
                String successMessage = isUserID ? "User not able to Login with only UserID." : "User not able to Login with only Password.";
                addToReport(successMessage, Status.PASS);
            } else {
                String failureMessage = isUserID ? "User able to Login with only UserID." : "User able to Login with only Password.";
                addToReport(failureMessage, Status.FAIL);
                throw new RuntimeException("Error - Login is successful.");
            }
        } catch (Exception e) {
            String errorContext = isUserID ? "UserID" : "Password";
            addToReport("Unable to verify incorrect " + errorContext, Status.FAIL);
            throw new RuntimeException("Error - Failed to validate login with only " + errorContext + ".", e);
        }
    }

    /**
     * Logs into Sampath Vishwa Web using either mouse click or Enter key, based on the provided parameter.
     *
     * @param name          - User name
     * @param password      - Login password
     * @param expectedTitle - Dashboard title text
     */
    public void loginToSampathVishwaWebUsingEnterAndMouseClick(String name, String password, String expectedTitle, String buttonName, boolean mouseClick) {
        try {
            sendKeysToElement(txtUserName, name);
            sendKeysToElement(txtPassword, password);
            if (mouseClick) {
                mouseClick(getElementByTypeAndText(ElementType.button, buttonName));
                addToReport("Login attempted using mouse click on the '" + buttonName + "' button.", Status.PASS);
            } else {
                sendEnterKeyToElement(getElementByTypeAndText(ElementType.button, buttonName));
                addToReport("Login attempted using Enter key on the '" + buttonName + "' button.", Status.PASS);
            }
            waitFor(10000);
            if (isElementPresentBy(getPageTitle(expectedTitle))) {
                addToReport("Login successful. Sampath Vishwa '" + expectedTitle + "' title is present.", Status.PASS);
            } else {
                addToReport("Login failed. Sampath Vishwa '" + expectedTitle + "' title is not present.", Status.FAIL);
                throw new RuntimeException("Error - Login is unsuccessful.");
            }
        } catch (Exception e) {
            addToReport("Unable to verify dashboard page title: '" + expectedTitle + "'.", Status.FAIL);
            throw new RuntimeException("Error - Failed to validate the dashboard page title: ", e);
        }
    }


    /**
     * Logs out from Sampath Vishwa Web
     *
     * @param buttonName        - logout button text
     * @param popupText         - logout popup text
     * @param confirmButtonText - confirm logout button text
     * @param loginPageTitle    - login page title
     */
    public void logoutFromSampathVishwaWeb(String buttonName, String popupText, String confirmButtonText, String loginPageTitle) {
        try {
            clickOnElement(btnCloseDashboardAlertPopup);
            boolean userProfileIcon = isElementPresentBy(imgUserIcon);
            if (userProfileIcon) {
                clickOnElement(imgUserIcon);
                addToReport("Successfully clicked on user profile icon on top navigation bar.", Status.PASS);
                boolean logoutButton = isElementPresentBy(logoutButton(buttonName));
                if (logoutButton) {
                    clickOnElement(logoutButton(buttonName));
                    addToReport("Successfully clicked on the logout button.", Status.PASS);
                    boolean popup = isElementPresentBy(logoutPopup(popupText));
                    if (popup) {
                        addToReport("'" + popupText + "' Logout pop is visible.", Status.PASS);
                        boolean confirmButton = isElementPresentBy(getElementByTypeAndText(ElementType.button, buttonName));
                        if (confirmButton) {
                            addToReport("'" + confirmButtonText + "'Logout button is visible.", Status.PASS);
                            clickOnElement(getElementByTypeAndText(ElementType.button, buttonName));
                            addToReport("'" + confirmButtonText + "'Logout button is clicked.", Status.PASS);
                            boolean loginPage = isElementPresentBy(getPageTitle(loginPageTitle));
                            if (loginPage) {
                                addToReport("Successfully logged out from the sampath vishwa application.", Status.PASS);
                            } else {
                                addToReport("Unable to logged out from the sampath vishwa application.", Status.FAIL);
                                throw new RuntimeException("Error - User is unable to logged out from the sampath vishwa application.");
                            }
                        } else {
                            addToReport("'" + confirmButtonText + "'Logout button is not visible.", Status.FAIL);
                            throw new RuntimeException(" Error - Logout button is not visible.");
                        }
                    } else {
                        addToReport("'" + popupText + "' Logout pop is not visible.", Status.FAIL);
                        throw new RuntimeException("'" + popupText + "' Logout pop is not visible.");
                    }
                } else {
                    addToReport("Logout button inside user dropdown is not visible.", Status.FAIL);
                    throw new RuntimeException("Logout button inside user dropdown is not visible. ");
                }
            } else {
                addToReport("User profile icon is not visible.", Status.FAIL);
                throw new RuntimeException("User profile icon is not visible.");
            }
        } catch (Exception e) {
            addToReport("Unable to logged out from the system.", Status.FAIL);
            throw new RuntimeException("Unable to logged out from the system.", e);
        }
    }

    /**
     * Click on the sign-in button / reset button
     *
     * @param buttonName - button text
     */
    public void ClickOnRestOrSignupButton(String buttonName) {
        try {
            boolean buttonType = waitForElementPresence(getElementByTypeAndText(ElementType.span, buttonName));
            if (buttonType) {
                clickOnElement(getElementByTypeAndText(ElementType.span, buttonName));
                addToReport("Successfully clicks on '" + buttonName + "' button.", Status.PASS);
            } else {
                addToReport("Failed to clicks on '" + buttonName + "' button.", Status.FAIL);
                throw new RuntimeException("Error - Failed to clicks on '" + buttonName + "' button.");
            }
        } catch (Exception e) {
            addToReport("Unable to clicks on '" + buttonName + "' button.", Status.FAIL);
            throw new RuntimeException("Error - Unable to clicks on '" + buttonName + "' button.", e);
        }
    }


    /**
     * validate the forgot password journey
     *
     * @param textToType         - text to type on th input field
     * @param inputFieldText     - input field name
     * @param userName           - username of the account
     * @param buttonName         - Name of the button
     * @param maidFieldText      - Maid name input field name
     * @param petFieldText       -  Pet name input field name
     * @param newPassword        - Enter new password field name
     * @param confirmPassword    - Confirm new password field name
     * @param unaReqFieldText    - username required field text
     * @param questionRFieldText - question required field text
     * @param newPwReqFldText    - new password required field text
     * @param conPwReqFldText    - confirm password required field text
     * @param submitButton       - submit button name
     * @param headingText        - forgot password heading
     */
    public void ValidateForgotPasswordSteps(String textToType, String inputFieldText, String userName, String buttonName, String maidFieldText, String petFieldText, String newPassword, String confirmPassword,
                                            String unaReqFieldText, String questionRFieldText, String newPwReqFldText, String conPwReqFldText, String submitButton, String headingText
    ) {
        try {
            boolean inputFieldName = waitForElementPresence(getElementByTypeAndText(ElementType.label, inputFieldText));
            if (inputFieldName) {
                ValidateForgotPasswordHeading(headingText, 1);
                clickOnElement(getElementByTypeAndText(ElementType.button, buttonName));
                ValidateTheRequiredFieldMessage(unaReqFieldText, inputFieldText);
                sendKeysToElement(txtUserName, userName);
                clickOnElement(getElementByTypeAndText(ElementType.button, buttonName));
                addToReport("Clicks on the '" + buttonName + "' button.", Status.PASS);
                boolean maidName = waitForElementPresence(getElementByTypeAndText(ElementType.label, maidFieldText));
                if (maidName) {
                    ValidateForgotPasswordHeading(headingText, 2);
                    clickOnElement(getElementByTypeAndText(ElementType.button, buttonName));
                    ValidateTheRequiredFieldMessage(questionRFieldText, maidFieldText);
                    sendKeysToElement(txtAnswerInputField, textToType);
                    clickOnElement(getElementByTypeAndText(ElementType.button, buttonName));
                    addToReport("Clicks on the '" + buttonName + "' button.", Status.PASS);
                    boolean petName = waitForElementPresence(getElementByTypeAndText(ElementType.label, petFieldText));
                    if (petName) {
                        ValidateForgotPasswordHeading(headingText, 3);
                        clickOnElement(getElementByTypeAndText(ElementType.button, buttonName));
                        ValidateTheRequiredFieldMessage(questionRFieldText, petFieldText);
                        sendKeysToElement(txtAnswerInputField, textToType);
                        clickOnElement(getElementByTypeAndText(ElementType.button, buttonName));
                        addToReport("Clicks on the '" + buttonName + "' button.", Status.PASS);
                        boolean newAndConfirm = waitForElementPresence(getElementByTypeAndText(ElementType.label, newPassword)) &&
                                waitForElementPresence(getElementByTypeAndText(ElementType.label, confirmPassword));
                        if (newAndConfirm) {
                            ValidateForgotPasswordHeading(headingText, 4);
                            clickOnElement(getElementByTypeAndText(ElementType.button, submitButton));
                            ValidateTheRequiredFieldMessage(newPwReqFldText, newPassword);
                            ValidateTheRequiredFieldMessage(conPwReqFldText, confirmPassword);
                            addToReport("'" + buttonName + "' and '" + buttonName + "' input fields are visible.", Status.PASS);
                        } else {
                            addToReport("'" + buttonName + "' and '" + buttonName + "' input fields are not visible.", Status.FAIL);
                            throw new RuntimeException("'" + buttonName + "' and '" + buttonName + "' input fields are not visible.");
                        }
                    } else {
                        addToReport("'" + petFieldText + "' input field is not visible.", Status.FAIL);
                        throw new RuntimeException("'" + petFieldText + "' input field is not visible.");
                    }
                } else {
                    addToReport("'" + maidFieldText + "' input field is not visible.", Status.FAIL);
                    throw new RuntimeException("'" + maidFieldText + "' input field is not visible.");
                }
            } else {
                addToReport("'" + inputFieldText + "' input field is not visible.", Status.FAIL);
                throw new RuntimeException("'" + inputFieldText + "' input field is not visible.");
            }
        } catch (Exception e) {
            addToReport("Unable to verify incorrect user login", Status.FAIL);
            throw new RuntimeException("Failed to validate the incorrect user login. ", e);
        }
    }

    /***
     * Validate the required field message
     * @param messageText - text of the message
     * @param fieldName -  required field name
     */
    public void ValidateTheRequiredFieldMessage(String messageText, String fieldName) {
        try {
            boolean message = waitForElementPresence(getElementByTypeAndText(ElementType.span, messageText));
            if (message) {
                addToReport("'" + fieldName + "' Required field '" + messageText + "' message is visible.", Status.PASS);
            } else {
                addToReport("'" + fieldName + "' Required field '" + messageText + "' message is visible.", Status.FAIL);
                throw new RuntimeException("'" + fieldName + "' Required field '" + messageText + "' message is visible.");
            }
        } catch (Exception e) {
            addToReport("Unable to verify required field message.", Status.FAIL);
            throw new RuntimeException("Unable to verify required field message.", e);
        }
    }

    /***
     * Validate the forgot password heading and the step count
     * @param headingText - Heading text
     * @param stepNumber - number of the step
     */
    public void ValidateForgotPasswordHeading(String headingText, int stepNumber) {
        try {
            String expectedMessage = headingText + "\nStep " + stepNumber + " of 4";
            String actualMessage = getTextFromElement(lblForgotPasswordHeading);
            if (actualMessage.equals(expectedMessage)) {
                addToReport("Forgot password heading is visible with the page step: '" + expectedMessage + "'.", Status.PASS);
            } else {
                addToReport("Forgot password heading is not visible with the page step: '" + expectedMessage + "'.", Status.FAIL);
                throw new RuntimeException("Forgot password heading is visible with the page step: '" + expectedMessage + "'..");
            }
        } catch (Exception e) {
            addToReport("Unable to verify the forgot password heading.", Status.FAIL);
            throw new RuntimeException("Unable to verify the forgot password heading", e);
        }
    }

    /***
     * Validate the forgot password - Enter incorrect user
     * @param buttonName - button name reset
     * @param userName   - invalid user name
     * @param invalidMsg - invalid message
     */
    public void ValidateForgotPasswordIncorrectUser(String buttonName, String userName, String invalidMsg) {
        try {
            //Click reset button
            ClickOnRestOrSignupButton(buttonName);
            waitForElementPresence(tfVishwaID, 10);

            //Enter invalid user
            sendKeysToElement(tfVishwaID, userName);
            clickOnElement(btnLogin);

            waitForElementPresence(popUpMsg,10);
            //Validate invalid user message
            waitForElementPresence(popUpIncorrectUserId, 10);

            String actualMessage = getTextFromElement(popUpIncorrectUserId);
            if (actualMessage.equals(invalidMsg)) {
                addToReport("Received correct invalid message  : '" + invalidMsg + "'.", Status.PASS);
            } else {
                addToReport("Didn't receive correct invalid message  : '" + invalidMsg + "'.", Status.FAIL);
                throw new RuntimeException("Didn't receive correct invalid message  : '" + invalidMsg + "'..");
            }
        } catch (Exception e) {
            addToReport("Unable to verify invalid message", Status.FAIL);
            throw new RuntimeException("Unable to verify invalid message", e);
        }
    }
    /***
     * Validate the forgot password - Enter locked user
     * @param buttonName - button name reset
     * @param userName   - locked user name
     * @param errorMsg - invalid message
     */
    public void ValidateForgotPasswordLockedUser(String buttonName, String userName, String errorMsg) {
        try {
            //Click reset button
            ClickOnRestOrSignupButton(buttonName);
            waitForElementPresence(tfVishwaID, 10);

            //Enter invalid user
            sendKeysToElement(tfVishwaID, userName);
            clickOnElement(btnLogin);

            waitForElementPresence(popUpMsg,10);
            //Validate invalid user message
            waitForElementPresence(popUpIncorrectUserId, 10);

            String actualMessage = getTextFromElement(popUpIncorrectUserId);
            if (actualMessage.equals(errorMsg)) {
                addToReport("Received correct invalid message  : '" + errorMsg + "'.", Status.PASS);
            } else {
                addToReport("Didn't receive correct invalid message  : '" + errorMsg + "'.", Status.FAIL);
                throw new RuntimeException("Didn't receive correct invalid message  : '" + errorMsg + "'..");
            }
        } catch (Exception e) {
            addToReport("Unable to verify invalid message", Status.FAIL);
            throw new RuntimeException("Unable to verify invalid message", e);
        }
    }

    /***
     * Validate the security questions - Enter incorrect security answers
     * @param buttonName - button name reset
     * @param userName   - valid user name
     * @param password - valid pass
     * @param errorMessage - Relevant error message
     * @param mouseClick - if login using mouse click or enter
     */
    public void ValidateSuccessfulLoginAttemptWithLockedUserID(String buttonName,String userName,String password, String errorMessage,Boolean mouseClick) {
        try {
            //Enter correct username and pw
            sendKeysToElement(txtUserName, userName);
            sendKeysToElement(txtPassword, password);
            if (mouseClick) {
                mouseClick(getElementByTypeAndText(ElementType.button, buttonName));
                addToReport("Login attempted using mouse click on the '" + buttonName + "' button.", Status.PASS,false);
            } else {
                sendEnterKeyToElement(getElementByTypeAndText(ElementType.button, buttonName));
                addToReport("Login attempted using Enter key on the '" + buttonName + "' button.", Status.PASS,false);
            }
            waitForElementPresence(popUpMsg,10);

            //Validate error message
            waitForElementPresence(popUpIncorrectUserId, 10);
            String actualMessage = getTextFromElement(popUpIncorrectUserId);
            if (actualMessage.equals(errorMessage)) {
                addToReport("Received correct message  : '" + errorMessage + "'.", Status.PASS);
            } else {
                addToReport("Didn't receive invalid message  : '" + errorMessage + "'.", Status.FAIL);
            }
        } catch (Exception e) {
            addToReport("Unable to verify invalid message", Status.FAIL);
            throw new RuntimeException("Unable to verify invalid message", e);
        }
    }

    /***
     * Validate the forgot password - Enter incorrect user
     * @param buttonName - button name reset
     * @param userName   - invalid user name
     * @param invalidMsg - invalid message
     */
    public void ValidateForgotPasswordIncorrectSecurityAnswers(String buttonName,String randomText,String userName, String invalidMsg) {
        try {
            //Click reset button
            ClickOnRestOrSignupButton(buttonName);
            waitForElementPresence(tfVishwaID, 10);

            //Enter valid user
            sendKeysToElement(tfVishwaID, userName);
            clickOnElement(btnLogin);

            //Enter invalid answers
            waitForElementPresence(tfSecurityAnsOne,10);
            sendKeysToElement(tfSecurityAnsOne,randomText);
            clickOnElement(btnLogin);

            waitForElementPresence(popUpMsg,10);
            //Validate error message
            waitForElementPresence(popUpIncorrectUserId, 10);
            String actualMessage = getTextFromElement(popUpIncorrectUserId);
            if (actualMessage.equals(invalidMsg)) {
                addToReport("Received correct invalid message  : '" + invalidMsg + "'.", Status.PASS);
            } else {
                addToReport("Didn't receive correct invalid message  : '" + invalidMsg + "'.", Status.FAIL);
                throw new RuntimeException("Didn't receive correct invalid message  : '" + invalidMsg + "'..");
            }
        } catch (Exception e) {
            addToReport("Unable to verify invalid message", Status.FAIL);
            throw new RuntimeException("Unable to verify invalid message", e);
        }
    }

    /***
     * Clicks on exclamation mark
     */
    public void ValidatePasswordPolicy() {
        try {
            boolean policyIcon = waitForElementPresence(lblExclamationMark);
            if (policyIcon) {
                clickOnElement(lblExclamationMark);
                addToReport("Clicks on Exclamation mark on Enter New Password field.", Status.PASS);
                boolean policyWindow = waitForElementPresence(lblPpolicyPopup);
                if (policyWindow) {
                    addToReport("Password policy window popup is visible.", Status.PASS);

                } else {
                    addToReport("Password policy window popup is not visible.", Status.FAIL);
                    throw new RuntimeException("Password policy window popup is not visible.");
                }

            } else {
                addToReport("The exclamation mark is not visible on the 'Enter New Password' field.", Status.FAIL);
                throw new RuntimeException("The exclamation mark is not visible on the 'Enter New Password' field.");
            }
        } catch (Exception e) {
            addToReport("Unable to verify the password policy.", Status.FAIL);
            throw new RuntimeException("Unable to verify the password policy", e);
        }
    }


    /***
     * Validate the password policy list
     * @param expectedItems -  expected list items
     */

    public void validateListItems(String expectedItems) {
        try {
            List<String> expectedItemList = Arrays.stream(expectedItems.split("\\\\n")).map(item -> item.replaceAll("^\"|\"$", "").trim()).toList();
            WebElement listElement = driver.findElement(lstPolicyList);
            List<WebElement> listItems = listElement.findElements(By.tagName("li"));
            for (int i = 0; i < expectedItemList.size(); i++) {
                String expectedItem = expectedItemList.get(i).trim();
                String actualItem = listItems.get(i).getText().trim();
                if (expectedItem.equals(actualItem)) {
                    addToReport("Validation passed for item " + (i + 1) + ": " + actualItem, Status.PASS);
                } else {
                    addToReport("Validation failed for item " + (i + 1) + ". Expected: '" + expectedItem + "', but found: '" + actualItem + "'", Status.FAIL);
                    throw new RuntimeException("Validation failed for item " + (i + 1) + ". Expected: '" + expectedItem + "', but found: '" + actualItem + "'");
                }
            }
            addToReport("All list items validated successfully.", Status.PASS);
        } catch (Exception e) {
            addToReport("Unable to validate the list items.", Status.FAIL);
            throw new RuntimeException("Unable to validate the list items.", e);
        }
    }


    /***
     * Validate the policy by typing the password
     * @param passwordText -  password text
     * @param passwordPolicy - password policy list
     */
    public void validateAndTypeNewPassword(String passwordText, String passwordPolicy) {
        try {
            String typedPassword = "";
            String[] policyLines = passwordPolicy.split("\\n");

            for (int i = 0; i < passwordText.length(); i++) {
                char currentChar = passwordText.charAt(i);
                typedPassword += currentChar;
                typeWithoutClear(txtNewPassword, String.valueOf(currentChar)); // Simulates typing character
                waitFor(500);
                checkPolicyMessages(typedPassword, policyLines);
                if (i > 1 && passwordText.charAt(i) == passwordText.charAt(i - 1) && passwordText.charAt(i - 1) == passwordText.charAt(i - 2)) {
                    addToReport("Consecutive character policy triggered: " + typedPassword, Status.FAIL);
                    removeLastCharacterFromField(txtNewPassword);
                    typedPassword = typedPassword.substring(0, typedPassword.length() - 1); // Remove last character
                    waitFor(500);
                    checkPolicyMessages(typedPassword, policyLines);
                }
            }
            addToReport("Password typed and validated successfully.", Status.PASS);
        } catch (RuntimeException e) {
            addToReport("Password validation failed: " + e.getMessage(), Status.FAIL);
            throw new RuntimeException("Password validation failed: " + e.getMessage());
        }
    }

    private void checkPolicyMessages(String password, String[] policyLines) {
        for (String policy : policyLines) {
            if (policy.contains("Length between")) {
                String[] parts = policy.split(" ");
                int minLength = Integer.parseInt(parts[2]);
                int maxLength = Integer.parseInt(parts[4]);

                if (password.length() < minLength) {
                    verifyPolicyMessage("Password must be between " + minLength + " and " + maxLength + " characters");
                } else if (password.length() > maxLength) {
                    throw new RuntimeException("Password exceeds maximum length of " + maxLength);
                }
            } else if (policy.contains("Minimum one upper case character")) {
                if (!password.matches(".*[A-Z].*")) {
                    verifyPolicyMessage("Password must contain at least one uppercase letter");
                }
            } else if (policy.contains("Minimum one lower case character")) {
                if (!password.matches(".*[a-z].*")) {
                    verifyPolicyMessage("Password must contain at least one lowercase letter");
                }
            } else if (policy.contains("Minimum one number")) {
                if (!password.matches(".*\\d.*")) {
                    verifyPolicyMessage("Password must contain at least one number");
                }
            } else if (policy.contains("Minimum one special character")) {
                if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
                    verifyPolicyMessage("Password must contain at least one special character");
                }
            } else if (policy.contains("There cannot be more than 2 consecutive repeated characters")) {
                if (password.length() > 2 && password.charAt(password.length() - 1) == password.charAt(password.length() - 2)
                        && password.charAt(password.length() - 2) == password.charAt(password.length() - 3)) {
                    verifyPolicyMessage("Password must not contain repeated consecutive characters");
                }
            }
        }
    }

    private void verifyPolicyMessage(String expectedMessage) {
        if (!waitForElementPresence(getElementByTypeAndText(ElementType.span, expectedMessage))) {
            addToReport("Expected policy message '" + expectedMessage + "' not visible.", Status.FAIL);
            throw new RuntimeException("Expected policy message '" + expectedMessage + "' not visible.");
        }
    }


}


