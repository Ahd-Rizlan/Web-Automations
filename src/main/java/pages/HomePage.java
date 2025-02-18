package pages;

import com.aventstack.extentreports.Status;
import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class HomePage extends BasePage {


    public enum ElementType {
        button, label, span, div;
    }

    // Update the naming conventions for this XPath according to project requirements.
    // The current names are placeholders provided for reference only.
    private static final By alertPopup = By.xpath("//span[text()='X']");
    private static final By userIcon = By.xpath("//div[contains(@class,'NavBar_userContainer')]/img");
    private static By title = By.xpath("//title[text()='Sampath Vishwa | Dashboard']");
    private static By btn_freeTrial = By.xpath("//input[@id='linkadd']");
    private static By btn_BookAFreeDemo = By.xpath("//li/a[text()='Book a Free Demo']");
    private static By lbl_header = By.xpath("//h3[text()='See OrangeHRM in Action']");
    private static By lbl_freeTrial = By.xpath("//h1[text()='Your free trial']");
    private static By lblSavingsAccount = By.xpath("//span[text()='Savings Account']");
    private static By lblCurrencyAndAvailableBalance = By.xpath("//div[contains(text(),'Available')]/following-sibling::div/span");
    private static By btnDeposits = By.xpath("//div[contains(@class,'Container_body')]//div[contains(text(),'Deposits')]");
    private static By lblLoadingIcon = By.xpath("//div[contains(@class,'AccountsCards_loader')]");
    private static By lblMaturityValue = By.xpath("//span[contains(text(),'Maturity Value')]/parent::div/span[1]");
    private static By lblMaturityDate = By.xpath("//span[contains(text(),'Maturity Date')]/parent::div/span[1]");
    private static By lblInterestRate = By.xpath("//span[contains(text(),'Interest Rate')]/parent::div/span[1]");
    private static By lblFDAccountNumber = By.xpath(" //span[contains(text(),'Fixed Deposit')]/ancestor::div[contains(@class,'full justify-center flex')]//div[contains(@class,'text-base')]/span");

    private static By lblAccountNumber(String accountNumber) {
        return By.xpath("//div[contains(@class,' justify-center flex')]//div[contains(normalize-space(text()), '" + accountNumber + "')]");
    }

    private static By txtlogoutPopup(String popupText) {
        return By.xpath("//div[contains(@class,' justify-center flex')]//div[contains(normalize-space(text()), '" + popupText + "')]");
    }

    private static By getElementByTypeAndText(HomePage.ElementType type, String text) {
        return By.xpath("//" + type.name() + "[contains(normalize-space(text()), \"" + text + "\")]");
    }

    private static By getPageTitle(String title) {
        return By.xpath("//title[contains(text(),'" + title + "')]");
    }

    private static By logoutButton(String buttonText) {
        return By.xpath("//div[contains(@class,'NavBar_userDropDown_')]//div[contains(normalize-space(text()), '" + buttonText + "')]");
    }


    public HomePage(WebDriver driver) {
        super(driver);
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

            //validate user profile icon and click
            boolean userProfileIcon = isElementPresentBy(userIcon);
            if (userProfileIcon) {
                clickOnElement(userIcon);
                addScreenshotToTheReport("Successfully clicked on user profile icon on top navigation bar.", Status.PASS);
            } else {
                addScreenshotToTheReport("User profile icon is not visible.", Status.FAIL);
                throw new RuntimeException("User profile icon is not visible.");
            }

            //validate logout button
            boolean logoutButton = isElementPresentBy(logoutButton(buttonName));
            if (logoutButton) {
                clickOnElement(logoutButton(buttonName));
                addScreenshotToTheReport("Successfully clicked on the logout button.", Status.PASS);
            } else {
                addScreenshotToTheReport("Logout button inside user dropdown is not visible.", Status.FAIL);
                throw new RuntimeException("Logout button inside user dropdown is not visible. ");
            }

            //validate popup
            boolean popup = isElementPresentBy(txtlogoutPopup(popupText));
            if (popup) {
                addScreenshotToTheReport("'" + popupText + "' Logout popup is visible.", Status.PASS);
            } else {
                addScreenshotToTheReport("'" + popupText + "' Logout popup is not visible.", Status.FAIL);
                throw new RuntimeException("'" + popupText + "' Logout pop is not visible.");
            }

            boolean confirmButton = isElementPresentBy(getElementByTypeAndText(HomePage.ElementType.button, buttonName));
            if (confirmButton) {
                addScreenshotToTheReport("'" + confirmButtonText + "'Logout button is visible.", Status.PASS);
                clickOnElement(getElementByTypeAndText(HomePage.ElementType.button, buttonName));
                addScreenshotToTheReport("'" + confirmButtonText + "'Logout button is clicked.", Status.PASS);
            } else {
                addScreenshotToTheReport("'" + confirmButtonText + "'Logout button is not visible.", Status.FAIL);
                throw new RuntimeException("'" + confirmButtonText + "'Logout button is not visible.");
            }

            // Validate landing page on logout
            boolean loginPage = isElementPresentBy(getPageTitle(loginPageTitle));
            if (loginPage) {
                addScreenshotToTheReport("Successfully logged out from the sampath vishwa application.", Status.PASS);
            } else {
                addScreenshotToTheReport("Unable to logged out from the sampath vishwa application.", Status.FAIL);
                throw new RuntimeException("User is unable to logged out from the sampath vishwa application.");
            }

        } catch (Exception e) {
            addScreenshotToTheReport("Unable to logged out from the system.", Status.FAIL);
            throw new RuntimeException("Unable to logged out from the system.", e);
        }
    }

    public void browserNavigateBack() {

        driver.navigate().back();
        addScreenshotToTheReport("Navigate back from current browser location", Status.INFO);
    }

    public void ValidateUserProfileIcon() {
        try {
            //validate user profile icon
            boolean userProfileIcon = isElementPresentBy(userIcon);
            if (userProfileIcon) {
                addScreenshotToTheReport("Successfully validated user profile icon on top navigation bar", Status.PASS);
            } else {
                addScreenshotToTheReport("User profile icon is not visible", Status.FAIL);
                throw new RuntimeException("User profile icon is not visible");
            }
        } catch (Exception e) {
            addScreenshotToTheReport("User profile icon is not visible", Status.FAIL);
            throw new RuntimeException("Error in loading user profile icon", e);
        }
    }


    public void browserNavigateForward() {
        driver.navigate().forward();
        addScreenshotToTheReport("Navigate forward from current browser location", Status.INFO);
    }

    public void validateTheTitle() {
        waitForElementPresence(title);
        addScreenshotToTheReport("Successfully validated the title '" + title + "'", Status.PASS);
    }

    public void validateFreeTrialNavigation() {
        clickOnElement(btn_freeTrial);
        Assert.assertEquals(getTextFromElement(lbl_freeTrial), "Your free trial\n" +
                "is almost ready!");
    }

    public void navigateToBookAFreeDemo() {
        clickOnElement(btn_BookAFreeDemo);
    }
//------------------- W I P -----------------------------------

    /**
     * Validate the availability of 6 key points in savings account
     *
     * @param accountNumber               - logout button text
     * @param CurrencyAndAvailableBalance - logout popup text
     */
    public void validateSavingsAccountAtDashboard(String accountNumber, String CurrencyAndAvailableBalance) {
        try {
            boolean boolAccountNumber = isElementPresentBy(lblAccountNumber(accountNumber));
            //validate account number
            if (boolAccountNumber) {
                addScreenshotToTheReport("Successfully validated account number : '" + accountNumber + "'", Status.PASS);
            } else {
                addScreenshotToTheReport("Account number is not validated", Status.FAIL);
                throw new RuntimeException("Account number is not validated");
            }
            Assert.assertEquals(getTextFromElement(lblCurrencyAndAvailableBalance), CurrencyAndAvailableBalance);
            //primary status and account status

            //product name
            //nick name
        } catch (Exception e) {
            addScreenshotToTheReport("User profile icon is not visible.", Status.FAIL);
            throw new RuntimeException("User profile icon is not visible.", e);
        }


    }

    public void assertValues(String actualText, String expectedText) {
        try {
            if (actualText.equalsIgnoreCase(expectedText)) {
                addScreenshotToTheReport("Successfully validated Value : '" + actualText + "'", Status.PASS);
            } else {
                addScreenshotToTheReport("Validation failed", Status.FAIL);
                throw new RuntimeException("Validation failure");
            }
        } catch (Exception e) {
            addScreenshotToTheReport("Validation failed", Status.FAIL);
            throw new RuntimeException("Validation failed", e);
        }
    }

    /**
     * Validate the availability of 6 key points in savings account
     *
     * @param fDAccountNumber             - logout button text
     * @param currencyAndAvailableBalance - logout popup text
     */
    public void validateFixedDepositAccountAtDashboard(String fDAccountNumber, String currencyAndAvailableBalance, String maturityAmount, String maturityDate, String interestRate) {
        try {

            waitForElementToBeInvisible(lblLoadingIcon, 5);
            isElementClickable(btnDeposits);
            clickOnElement(btnDeposits);

//          mouseClick(btnDeposits);
            //validation of fd account key points
            assertValues(fDAccountNumber, getTextFromElement(lblFDAccountNumber));
            assertValues(currencyAndAvailableBalance, getTextFromElement(lblCurrencyAndAvailableBalance));

            System.out.println(getTextFromElement(lblMaturityValue));
            System.out.println(getTextFromElement(lblMaturityDate));
            System.out.println(getTextFromElement(lblInterestRate));

            assertValues(maturityAmount, getTextFromElement(lblMaturityValue));
            assertValues(maturityDate, getTextFromElement(lblMaturityDate));
            assertValues(interestRate, getTextFromElement(lblInterestRate));

        } catch (Exception e) {
            addScreenshotToTheReport("Fixed deposit key points validation failed", Status.FAIL);
            throw new RuntimeException("Fixed deposit key points validation failed", e);
        }


    }

    public void closeAlertPopup() {

        //    clickOnElement(alertPopup);
        //validate user profile icon and click
        boolean alert = isElementPresentBy(alertPopup);
        if (alert) {
            clickOnElement(alertPopup);
            addScreenshotToTheReport("Successfully closed alert popup", Status.PASS);
        } else {
            addScreenshotToTheReport("User profile icon is not visible.", Status.INFO);
        }
    }
}
