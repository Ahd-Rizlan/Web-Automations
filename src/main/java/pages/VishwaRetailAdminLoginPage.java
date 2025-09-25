/*
 *   @author - salman R
 */
package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

import static utils.Drivers.*;

public class VishwaRetailAdminLoginPage extends BasePage {

    public VishwaRetailAdminLoginPage(WebDriver driver) {
        super(driver);
    }

    private static final By tfUserName = By.xpath("//input[@id='username']");
    private static final By tfPassword = By.xpath("//input[@id='password']");
    private static final By btnLogin = By.xpath("//button[@type='submit']");
    private static By lblPageTile(String tileText) {
        return By.xpath("//p[contains(text(),\"" + tileText + "\")]");
    }


    /**
     * Login to Admin module
     * @param headerTitle  the title/header of the current page or section
     * @param userName     the username used for login or authentication
     * @param password     the password corresponding to the given username
     */
    public void loginTOAdminModule(String headerTitle, String userName, String password) {

        try {

            waitForElementPresence(btnLogin);

            //validate if header label is available
            boolean isHeaderAvailable = waitForElementPresence(lblPageTile(headerTitle));

            if (isHeaderAvailable) {
                addToReport("Login header "+headerTitle+" loaded successfully", Status.PASS,false);
            } else {
                addToReport("Login header "+headerTitle+" did not load successfully", Status.FAIL,true);
            }

            //Enter username
            sendKeysToElement(tfUserName,userName);
            //Enter password
            sendKeysToElement(tfPassword,password);
            //Click Login
            clickOnElement(btnLogin);

            waitForElementToBeInvisible(btnLogin,MODERATE_WAIT);

        } catch (Exception e) {
            addToReport("Error logging into retail admin", Status.FAIL);
            throw new RuntimeException("Failed log into retail admin" + e.getMessage(), e);
        }
    }


    /**
     * Opens a new browser tab, navigates to a URL based on tab index, and validates mail list availability
     *
     * @param tabIndex A string representing the tab identifier or URL target
     */
    public void navigateToTab(int tabIndex) {

        // Open a new tab using JavaScript
        ((JavascriptExecutor) driver).executeScript("window.open();");
        // Convert tabIndex to an integer
        int index = tabIndex;

        // Convert the set of window handles to a list
        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());

        // Check if the index is valid
        if (index >= 0 && index < windowHandles.size()) {
            // Switch to the tab by index
            driver.switchTo().window(windowHandles.get(index));
        } else {
            throw new IllegalArgumentException("Invalid tab index: " + tabIndex);
        }
    }

}


