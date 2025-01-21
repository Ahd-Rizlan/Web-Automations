package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;


public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public enum ElementType {
        button, label, span, div;
    }

    // Update the naming conventions for this XPath according to project requirements.
    // The current names are placeholders provided for reference only.
    private static final By enterUserName = By.xpath("//input[contains(@name,'username')]");
    private static final By enterPassWord = By.xpath("//input[contains(@name,'password')]");
    private static final By userIcon = By.xpath("//img[contains(@class,'NavBar_user__Ena5m')]");
    private static final By forgotPasswordHeading = By.xpath("//div[contains(@class, 'forgot_pageContainer__TAT35')]");
    private static final By exclamationMark = By.xpath("//label[contains(normalize-space(text()), 'Enter New Password')]/following::div[text()='!']");
    private static final By policyPopup = By.xpath("//div[contains(@class, 'justify-center flex')]");
    private static final By policyList = By.xpath("//ul[contains(@class,'list-disc pl-5')]");
    private static final By answerInputField = By.xpath("//input[contains(@name,'answer')]");
    private static final By newPassword = By.xpath("//input[contains(@name,'newPassword')]");
    private static final By confirmPassword = By.xpath("//input[contains(@name,'confirmPassword')]");
    private static final By alertPopup = By.xpath("//span[text()='X']");

    private static By getElementByTypeAndText(ElementType type, String text) {
        return By.xpath("//" + type.name() + "[contains(normalize-space(text()), \"" + text + "\")]");
    }

    private static By disabledLoginButton(String buttonText) {
        return By.xpath("//button[contains(normalize-space(text()),'" + buttonText + "') and @disabled]");
    }

    private static By getPageTitle(String title) {
        return By.xpath("//title[contains(text(),'" + title + "')]");
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
            boolean isTitleVisible = waitForElementPresence(getPageTitle(expectedTitle));
            boolean isTileVisible = waitForElementPresence(loginTile(loginTileName));
            if (isTitleVisible && isTileVisible) {
                waitFor(5000);
                addScreenshotToTheReport("Login page tile heading '" + loginTileName + "' and title '" + expectedTitle + "' is visible.", Status.PASS);
            } else {
                addScreenshotToTheReport("Title or login tile is not visible as expected.", Status.FAIL);
                throw new RuntimeException("Title or login tile is not visible as expected.");
            }

        } catch (Exception e) {
            addScreenshotToTheReport("Error verifying page title '" + expectedTitle + "' and login tile heading '" + loginTileName + "'.", Status.FAIL);
            throw new RuntimeException("Failed to validate the title and tile: " + e.getMessage(), e);
        }
    }

    /**
     * Login to the application
     *
     * @param name          - User name
     * @param password      - Login password
     * @param expectedTitle - Dashboard title text
     */
    public void loginToSampathVishwaWeb(String name, String password, String expectedTitle, String buttonName) {
        try {
            sendKeysToElement(enterUserName, name);
            sendKeysToElement(enterPassWord, password);
            clickOnElement(getElementByTypeAndText(ElementType.button, buttonName));
            clickOnElement(getElementByTypeAndText(ElementType.button, buttonName));
            waitFor(10000);
            if (isElementPresentBy(getPageTitle(expectedTitle))) {
                addScreenshotToTheReport("Login successful, Sampath vishwa '" + expectedTitle + "' title is present.", Status.PASS);
            } else {
                addScreenshotToTheReport("Login successful, Sampath vishwa '" + expectedTitle + "' title is not present.", Status.FAIL);
                throw new RuntimeException("Login is unsuccessful.");
            }
        } catch (Exception e) {
            addScreenshotToTheReport("Unable to verify dashboard page title '" + expectedTitle + "''.", Status.FAIL);
            throw new RuntimeException("Failed to validate the dashboard page title: ", e);
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
            sendKeysToElement(enterUserName, name);
            sendKeysToElement(enterPassWord, password);
            clickOnElement(getElementByTypeAndText(ElementType.button, buttonName));
            boolean isErrorMessageVisible = waitForElementPresence(errorMessage(errorText));
            if (isErrorMessageVisible) {
                addScreenshotToTheReport("User not able to Login with incorrect User ID.", Status.PASS);
            } else {
                addScreenshotToTheReport("User able to Login with incorrect User ID.", Status.FAIL);
                throw new RuntimeException("Error - Login is successful.");
            }
        } catch (Exception e) {
            addScreenshotToTheReport("Unable to verify incorrect user login", Status.FAIL);
            throw new RuntimeException("Failed to validate the incorrect user login. ", e);
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
    public void ValidateLoginWithIncorrectPassword(String name, String password, String buttonName, String errorText) {
        try {
            sendKeysToElement(enterUserName, name);
            sendKeysToElement(enterPassWord, password);
            clickOnElement(getElementByTypeAndText(ElementType.button, buttonName));
            boolean isErrorMessageVisible = waitForElementPresence(errorMessage(errorText));
            if (isErrorMessageVisible) {
                addScreenshotToTheReport("User not able to Login with incorrect Password.", Status.PASS);
            } else {
                addScreenshotToTheReport("User able to Login with incorrect Password.", Status.FAIL);
                throw new RuntimeException("Error - Login is successful.");
            }
        } catch (Exception e) {
            addScreenshotToTheReport("Unable to verify incorrect Password", Status.FAIL);
            throw new RuntimeException("Failed to validate the incorrect Password. ", e);
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
                sendKeysToElement(enterUserName, inputValue);
            } else {
                clearTheElement(enterUserName);
                sendKeysToElement(enterPassWord, inputValue);

            }

            boolean disableButton = waitForElementPresence(disabledLoginButton(buttonName));
            boolean placeholder = waitForElementPresence(inputPlaceholder(placeholderText));

            if (disableButton && placeholder) {
                String successMessage = isUserID ? "User not able to Login with only UserID." : "User not able to Login with only Password.";
                addScreenshotToTheReport(successMessage, Status.PASS);
            } else {
                String failureMessage = isUserID ? "User able to Login with only UserID." : "User able to Login with only Password.";
                addScreenshotToTheReport(failureMessage, Status.FAIL);
                throw new RuntimeException("Error - Login is successful.");
            }
        } catch (Exception e) {
            String errorContext = isUserID ? "UserID" : "Password";
            addScreenshotToTheReport("Unable to verify incorrect " + errorContext, Status.FAIL);
            throw new RuntimeException("Failed to validate login with only " + errorContext + ".", e);
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
            sendKeysToElement(enterUserName, name);
            sendKeysToElement(enterPassWord, password);
            if (mouseClick) {
                mouseClick(getElementByTypeAndText(ElementType.button, buttonName));
                addScreenshotToTheReport("Login attempted using mouse click on the '" + buttonName + "' button.", Status.PASS);
            } else {
                sendEnterKeyToElement(getElementByTypeAndText(ElementType.button, buttonName));
                addScreenshotToTheReport("Login attempted using Enter key on the '" + buttonName + "' button.", Status.PASS);
            }
            waitFor(10000);
            if (isElementPresentBy(getPageTitle(expectedTitle))) {
                addScreenshotToTheReport("Login successful. Sampath Vishwa '" + expectedTitle + "' title is present.", Status.PASS);
            } else {
                addScreenshotToTheReport("Login failed. Sampath Vishwa '" + expectedTitle + "' title is not present.", Status.FAIL);
                throw new RuntimeException("Login is unsuccessful.");
            }
        } catch (Exception e) {
            addScreenshotToTheReport("Unable to verify dashboard page title: '" + expectedTitle + "'.", Status.FAIL);
            throw new RuntimeException("Failed to validate the dashboard page title: ", e);
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
            clickOnElement(alertPopup);
            boolean userProfileIcon = isElementPresentBy(userIcon);
            if (userProfileIcon) {
                clickOnElement(userIcon);
                addScreenshotToTheReport("Successfully clicked on user profile icon on top navigation bar.", Status.PASS);
                boolean logoutButton = isElementPresentBy(logoutButton(buttonName));
                if (logoutButton) {
                    clickOnElement(logoutButton(buttonName));
                    addScreenshotToTheReport("Successfully clicked on the logout button.", Status.PASS);
                    boolean popup = isElementPresentBy(logoutPopup(popupText));
                    if (popup) {
                        addScreenshotToTheReport("'" + popupText + "' Logout pop is visible.", Status.PASS);
                        boolean confirmButton = isElementPresentBy(getElementByTypeAndText(ElementType.button, buttonName));
                        if (confirmButton) {
                            addScreenshotToTheReport("'" + confirmButtonText + "'Logout button is visible.", Status.PASS);
                            clickOnElement(getElementByTypeAndText(ElementType.button, buttonName));
                            addScreenshotToTheReport("'" + confirmButtonText + "'Logout button is clicked.", Status.PASS);
                            boolean loginPage = isElementPresentBy(getPageTitle(loginPageTitle));
                            if (loginPage) {
                                addScreenshotToTheReport("Successfully logged out from the sampath vishwa application.", Status.PASS);
                            } else {
                                addScreenshotToTheReport("Unable to logged out from the sampath vishwa application.", Status.FAIL);
                                throw new RuntimeException("User is unable to logged out from the sampath vishwa application.");
                            }
                        } else {
                            addScreenshotToTheReport("'" + confirmButtonText + "'Logout button is not visible.", Status.FAIL);
                            throw new RuntimeException("'" + confirmButtonText + "'Logout button is not visible.");
                        }
                    } else {
                        addScreenshotToTheReport("'" + popupText + "' Logout pop is not visible.", Status.FAIL);
                        throw new RuntimeException("'" + popupText + "' Logout pop is not visible.");
                    }
                } else {
                    addScreenshotToTheReport("Logout button inside user dropdown is not visible.", Status.FAIL);
                    throw new RuntimeException("Logout button inside user dropdown is not visible. ");
                }
            } else {
                addScreenshotToTheReport("User profile icon is not visible.", Status.FAIL);
                throw new RuntimeException("User profile icon is not visible.");
            }
        } catch (Exception e) {
            addScreenshotToTheReport("Unable to logged out from the system.", Status.FAIL);
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
                addScreenshotToTheReport("Successfully clicks on '" + buttonName + "' button.", Status.PASS);
            } else {
                addScreenshotToTheReport("Failed to clicks on '" + buttonName + "' button.", Status.FAIL);
                throw new RuntimeException("Failed to clicks on '" + buttonName + "' button.");
            }
        } catch (Exception e) {
            addScreenshotToTheReport("Unable to clicks on '" + buttonName + "' button.", Status.FAIL);
            throw new RuntimeException("Unable to clicks on '" + buttonName + "' button.", e);
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
                sendKeysToElement(enterUserName, userName);
                clickOnElement(getElementByTypeAndText(ElementType.button, buttonName));
                addScreenshotToTheReport("Clicks on the '" + buttonName + "' button.", Status.PASS);
                boolean maidName = waitForElementPresence(getElementByTypeAndText(ElementType.label, maidFieldText));
                if (maidName) {
                    ValidateForgotPasswordHeading(headingText, 2);
                    clickOnElement(getElementByTypeAndText(ElementType.button, buttonName));
                    ValidateTheRequiredFieldMessage(questionRFieldText, maidFieldText);
                    sendKeysToElement(answerInputField, textToType);
                    clickOnElement(getElementByTypeAndText(ElementType.button, buttonName));
                    addScreenshotToTheReport("Clicks on the '" + buttonName + "' button.", Status.PASS);
                    boolean petName = waitForElementPresence(getElementByTypeAndText(ElementType.label, petFieldText));
                    if (petName) {
                        ValidateForgotPasswordHeading(headingText, 3);
                        clickOnElement(getElementByTypeAndText(ElementType.button, buttonName));
                        ValidateTheRequiredFieldMessage(questionRFieldText, petFieldText);
                        sendKeysToElement(answerInputField, textToType);
                        clickOnElement(getElementByTypeAndText(ElementType.button, buttonName));
                        addScreenshotToTheReport("Clicks on the '" + buttonName + "' button.", Status.PASS);
                        boolean newAndConfirm = waitForElementPresence(getElementByTypeAndText(ElementType.label, newPassword)) &&
                                waitForElementPresence(getElementByTypeAndText(ElementType.label, confirmPassword));
                        if (newAndConfirm) {
                            ValidateForgotPasswordHeading(headingText, 4);
                            clickOnElement(getElementByTypeAndText(ElementType.button, submitButton));
                            ValidateTheRequiredFieldMessage(newPwReqFldText, newPassword);
                            ValidateTheRequiredFieldMessage(conPwReqFldText, confirmPassword);
                            addScreenshotToTheReport("'" + buttonName + "' and '" + buttonName + "' input fields are visible.", Status.PASS);
                        } else {
                            addScreenshotToTheReport("'" + buttonName + "' and '" + buttonName + "' input fields are not visible.", Status.FAIL);
                            throw new RuntimeException("'" + buttonName + "' and '" + buttonName + "' input fields are not visible.");
                        }
                    } else {
                        addScreenshotToTheReport("'" + petFieldText + "' input field is not visible.", Status.FAIL);
                        throw new RuntimeException("'" + petFieldText + "' input field is not visible.");
                    }
                } else {
                    addScreenshotToTheReport("'" + maidFieldText + "' input field is not visible.", Status.FAIL);
                    throw new RuntimeException("'" + maidFieldText + "' input field is not visible.");
                }
            } else {
                addScreenshotToTheReport("'" + inputFieldText + "' input field is not visible.", Status.FAIL);
                throw new RuntimeException("'" + inputFieldText + "' input field is not visible.");
            }
        } catch (Exception e) {
            addScreenshotToTheReport("Unable to verify incorrect user login", Status.FAIL);
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
                addScreenshotToTheReport("'" + fieldName + "' Required field '" + messageText + "' message is visible.", Status.PASS);
            } else {
                addScreenshotToTheReport("'" + fieldName + "' Required field '" + messageText + "' message is visible.", Status.FAIL);
                throw new RuntimeException("'" + fieldName + "' Required field '" + messageText + "' message is visible.");
            }
        } catch (Exception e) {
            addScreenshotToTheReport("Unable to verify required field message.", Status.FAIL);
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
            String actualMessage = getTextFromElement(forgotPasswordHeading);
            if (actualMessage.equals(expectedMessage)) {
                addScreenshotToTheReport("Forgot password heading is visible with the page step: '" + expectedMessage + "'.", Status.PASS);
            } else {
                addScreenshotToTheReport("Forgot password heading is not visible with the page step: '" + expectedMessage + "'.", Status.FAIL);
                throw new RuntimeException("Forgot password heading is visible with the page step: '" + expectedMessage + "'..");
            }
        } catch (Exception e) {
            addScreenshotToTheReport("Unable to verify the forgot password heading.", Status.FAIL);
            throw new RuntimeException("Unable to verify the forgot password heading", e);
        }
    }

    /***
     * Clicks on exclamation mark
     */
    public void ValidatePasswordPolicy() {
        try {
            boolean policyIcon = waitForElementPresence(exclamationMark);
            if (policyIcon) {
                clickOnElement(exclamationMark);
                addScreenshotToTheReport("Clicks on Exclamation mark on Enter New Password field.", Status.PASS);
                boolean policyWindow = waitForElementPresence(policyPopup);
                if (policyWindow) {
                    addScreenshotToTheReport("Password policy window popup is visible.", Status.PASS);

                } else {
                    addScreenshotToTheReport("Password policy window popup is not visible.", Status.FAIL);
                    throw new RuntimeException("Password policy window popup is not visible.");
                }

            } else {
                addScreenshotToTheReport("The exclamation mark is not visible on the 'Enter New Password' field.", Status.FAIL);
                throw new RuntimeException("The exclamation mark is not visible on the 'Enter New Password' field.");
            }
        } catch (Exception e) {
            addScreenshotToTheReport("Unable to verify the password policy.", Status.FAIL);
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
            WebElement listElement = driver.findElement(policyList);
            List<WebElement> listItems = listElement.findElements(By.tagName("li"));
            for (int i = 0; i < expectedItemList.size(); i++) {
                String expectedItem = expectedItemList.get(i).trim();
                String actualItem = listItems.get(i).getText().trim();
                if (expectedItem.equals(actualItem)) {
                    addScreenshotToTheReport("Validation passed for item " + (i + 1) + ": " + actualItem, Status.PASS);
                } else {
                    addScreenshotToTheReport("Validation failed for item " + (i + 1) + ". Expected: '" + expectedItem + "', but found: '" + actualItem + "'", Status.FAIL);
                    throw new RuntimeException("Validation failed for item " + (i + 1) + ". Expected: '" + expectedItem + "', but found: '" + actualItem + "'");
                }
            }
            addScreenshotToTheReport("All list items validated successfully.", Status.PASS);
        } catch (Exception e) {
            addScreenshotToTheReport("Unable to validate the list items.", Status.FAIL);
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
                typeWithoutClear(newPassword, String.valueOf(currentChar)); // Simulates typing character
                waitFor(500);
                checkPolicyMessages(typedPassword, policyLines);
                if (i > 1 && passwordText.charAt(i) == passwordText.charAt(i - 1) && passwordText.charAt(i - 1) == passwordText.charAt(i - 2)) {
                    addScreenshotToTheReport("Consecutive character policy triggered: " + typedPassword, Status.FAIL);
                    removeLastCharacterFromField(newPassword);
                    typedPassword = typedPassword.substring(0, typedPassword.length() - 1); // Remove last character
                    waitFor(500);
                    checkPolicyMessages(typedPassword, policyLines);
                }
            }
            addScreenshotToTheReport("Password typed and validated successfully.", Status.PASS);
        } catch (RuntimeException e) {
            addScreenshotToTheReport("Password validation failed: " + e.getMessage(), Status.FAIL);
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
            addScreenshotToTheReport("Expected policy message '" + expectedMessage + "' not visible.", Status.FAIL);
            throw new RuntimeException("Expected policy message '" + expectedMessage + "' not visible.");
        }
    }



}


