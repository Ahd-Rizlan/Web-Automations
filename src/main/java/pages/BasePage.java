package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.helpers;

public abstract class BasePage extends helpers {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }


    /**
     * Types the specified text into an input field identified by the provided By locator.
     * <p>
     * This method waits for the element to be clickable, clears any existing text, and types the provided inputText into the element.
     *
     * @param byLocator the By locator used to identify the input element
     * @param inputText the text to be typed into the input field
     */
    public void sendKeysToElement(By byLocator, String inputText) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(byLocator));
            webElement.clear();
            addScreenshotToTheReport("Clear the input textbox.", Status.PASS);
            webElement.sendKeys(inputText);
            addScreenshotToTheReport("Type '" + inputText + "' on textbox.", Status.PASS);
            waitFor(2000);
        } catch (Exception e) {
            addScreenshotToTheReport("Unable to type on '" + inputText + "'  textbox.", Status.FAIL);
            System.err.println("Error sending keys to WebElement: " + e.getMessage());
        }
    }

    /**
     * Sends keys to a specified WebElement.
     * <p>
     * This method waits until the specified WebElement becomes clickable, clears its current contents,
     * and then types the specified input text.
     *
     * @param webElement the WebElement to interact with
     * @param inputText  the text to type into the WebElement
     */
    public void sendKeysToWebElement(WebElement webElement, String inputText) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(webElement));
            element.clear();
            addScreenshotToTheReport("Clear the input textbox.", Status.PASS);
            element.sendKeys(inputText);
            addScreenshotToTheReport("Type '" + inputText + "' into the element.", Status.PASS);
            waitFor(2000);

        } catch (Exception e) {
            addScreenshotToTheReport("Unable to type '" + inputText + "' into the element.", Status.FAIL);
            System.err.println("Error sending keys to WebElement: " + e.getMessage());
        }
    }

    /**
     * Sends the Enter key to an element identified by a locator.
     * <p>
     * This method waits until the specified element becomes clickable and then sends the Enter key to it.
     *
     * @param byLocator the locator used to find the element
     */
    public void sendEnterKeyToElement(By byLocator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(byLocator));
            webElement.sendKeys(Keys.ENTER);
            addScreenshotToTheReport("Press the 'Enter' key.", Status.PASS);
        } catch (Exception e) {
            addScreenshotToTheReport("Error sending Enter key to element.", Status.FAIL);
            System.err.println("Error sending Enter key to element: " + e.getMessage());
        }
    }

    /**
     * Sends the Tab key to an element identified by a locator.
     * <p>
     * This method waits until the specified element becomes clickable and then sends the Tab key to it.
     *
     * @param byLocator the locator used to find the element
     */
    public void sendTabKeyToElement(By byLocator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(byLocator));
            webElement.sendKeys(Keys.TAB);
            addScreenshotToTheReport("Press the 'Tab' key.", Status.PASS);
        } catch (Exception e) {
            addScreenshotToTheReport("Error sending Tab key to element.", Status.FAIL);
            System.err.println("Error sending Tab key to element: " + e.getMessage());
        }
    }

    /**
     * Clicks on a WebElement after waiting for it to become clickable.
     * <p>
     * This method waits for the provided WebElement to be clickable and then clicks on it.
     *
     * @param locator the WebElement to be clicked
     */
    public void clickOnWebElement(WebElement locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            locator.click();
            addScreenshotToTheReport("Click on the '" + locator + "' web element locator.", Status.PASS);

        } catch (Exception e) {
            addScreenshotToTheReport("Error occur when clicking on the '" + locator + "' web element locator.", Status.FAIL);
            System.err.println("Error occur when clicking on the web element locator: " + e.getMessage());
        }
    }


    /**
     * Clicks on an element identified by a locator after waiting for it to become clickable.
     * <p>
     * This method waits for the element identified by the provided By locator to become clickable
     *
     * @param locator the locator used to find the element to be clicked
     */

    public void clickOnElement(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            driver.findElement(locator).click();
            addScreenshotToTheReport("Successfully clicked on the '" + locator + "' element.", Status.PASS);
        } catch (Exception e) {
            addScreenshotToTheReport("Error occur when clicking on the '" + locator + "' element.", Status.FAIL);
            System.err.println("Error occur when clicking on the element: " + e.getMessage());
        }
    }

    /**
     * Retrieves the text content of an element identified by the provided locator.
     * <p>
     * This method waits for the element to be present in the DOM, then retrieves and returns its text content.
     *
     * @param locator the locator used to find the element
     * @return the text content of the element, or null if the element is not found or an error occurs
     */
    public String getTextFromElement(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return driver.findElement(locator).getText();
        } catch (Exception e) {
            System.err.println("Error getting text from element: " + e.getMessage());
            return null;
        }
    }

    /**
     * Pauses the execution for the specified amount of time.
     * <p>
     * This method uses to pause the execution for the given number of seconds.
     *
     * @param seconds the number of seconds to wait
     */
    public void waitFor(int seconds) {
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            System.err.println("Interrupted during wait: " + e.getMessage());
        }
    }

    /**
     * Waits until an element is present in the DOM.
     * <p>
     * This method waits for the element identified by the provided locator to be present in the DOM.
     *
     * @param locator the locator used to find the element
     * @return true if the element is present, false if an error occurs or the element is not found
     * @true -If the element is found
     * @false -If the element is not found
     */
    public boolean waitForElementPresence(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            System.err.println("Error waiting for element presence: " + e.getMessage());
            return false;
        }
    }

    /**
     * Checks if an element is present in the DOM, identified by the provided locator.
     * <p>
     * This method waits for the element to be present in the DOM for up to 20 seconds.
     *
     * @param locator the locator used to find the element
     * @return true if the element is present, false if the element is not found or an error occurs
     * @true -If the element is found
     * @false -If the element is not found
     */
    public boolean isElementPresentBy(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            System.err.println("Element not present: " + e.getMessage());
            return false;
        }
    }

    /**
     * Checks if a WebElement is present and visible.
     * <p>
     * This method waits for the WebElement to be visible for up to 20 seconds.
     *
     * @param locator the WebElement to be checked
     * @return true if the WebElement is visible, false if the WebElement is not found or not visible
     * @true -If the element is visible
     * @false -If the element is not visible
     */
    public boolean isElementPresent(WebElement locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);

            wait.until(ExpectedConditions.visibilityOf(locator));
            return true;
        } catch (Exception e) {
            System.err.println("WebElement not present: " + e.getMessage());
            return false;
        }
    }

    /**
     * Checks if a specific attribute is present on a WebElement.
     * <p>
     * This method retrieves the value of the specified attribute from the WebElement.
     *
     * @param webElement the WebElement to check for the attribute
     * @param attribute  the name of the attribute to check
     * @return true if the attribute is present and has a value, false if the attribute is not present or has no value
     * @true -If the attribute is present and has a value
     * @false - If the attribute is not present
     */
    public boolean isAttributePresent(WebElement webElement, String attribute) {
        try {
            String value = webElement.getAttribute(attribute);
            return value != null;
        } catch (Exception e) {
            System.err.println("Error checking attribute presence: " + e.getMessage());
            return false;
        }
    }

    /**
     * Checks if an element is invisible, identified by the provided By locator.
     * <p>
     * This method waits for the element to become invisible for up to 10 seconds.
     *
     * @param locator the By locator used to find the element
     * @return true if the element is invisible, false if the element is still visible or an error occurs
     * @true -If the element becomes invisible.
     * @false - If the element becomes visible
     */
    public boolean isElementInvisible(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            return true;

        } catch (Exception e) {
            System.err.println("Element is still visible: " + e.getMessage());
            return false;
        }
    }

    /**
     * Checks if an element is clickable, identified by the provided By locator.
     * <p>
     * This method waits for the element to be clickable for up to 10 seconds.
     *
     * @param locator the By locator used to find the element
     * @return true if the element is clickable, false if the element is not clickable or an error occurs
     * @true -If the element becomes clickable.
     * @false - If the element becomes un clickable
     */
    public boolean isElementClickable(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            return true;
        } catch (Exception e) {
            System.err.println("Element is not clickable: " + e.getMessage());
            return false;
        }
    }

    /**
     * Waits for the loading indicator to become invisible.
     * <p>
     * This method waits for the loading indicator, identified by a specific XPath, to become invisible
     */
    public void waitForLoadingToBeInvisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div/img[@alt=\"loading...\"]")));
            addScreenshotToTheReport("Loading indicator is not visible", Status.PASS);

        } catch (Exception e) {
            addScreenshotToTheReport("Loading indicator is still visible", Status.FAIL);
            System.err.println("Loading indicator is still visible: " + e.getMessage());
        }
    }

    /**
     * Waits for the element to become invisible.
     * <p>
     * This method waits for the loading indicator, identified by a specific XPath, to become invisible
     *
     *  @param Locator the Locator to become invisible
     *  @param Timeout the wait time in seconds
     *
     */
    public void waitForElementToBeInvisible(By Locator, long Timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Timeout);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(Locator));
            addScreenshotToTheReport("Element "+Locator +" is not visible", Status.PASS);

        } catch (Exception e) {
            addScreenshotToTheReport("Element "+Locator +" is still visible after "+Timeout +" seconds", Status.FAIL);
            System.err.println("Loading indicator is still visible: " + e.getMessage());
        }
    }

    /**
     * Waits for the loading dropdown to become invisible.
     * <p>
     * This method waits for the loading dropdown, identified by a specific XPath, to become invisible
     */
    public void waitForLoadingDropToBeInvisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='css-fraxkc']")));
            addScreenshotToTheReport("Loading dropdown is not visible:", Status.PASS);

        } catch (Exception e) {
            addScreenshotToTheReport("Loading dropdown is still visible:", Status.FAIL);
            System.err.println("Loading dropdown is still visible: " + e.getMessage());
        }
    }

    /**
     * Scrolls down the page by one full viewport.
     * <p>
     * This method use to scroll the page down.
     */
    public void scrollDownPage() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, document.body.scrollHeight);");
            addScreenshotToTheReport("Successfully scrolled down the page", Status.PASS);

        } catch (Exception e) {
            addScreenshotToTheReport("Error scrolling down the page", Status.FAIL);
            System.err.println("Error scrolling down the page: " + e.getMessage());
        }
    }

    /**
     * Scrolls the page to a specific WebElement.
     * <p>
     * This method scrolls the page until the specified WebElement is in view. I
     *
     * @param webElement the WebElement to scroll to
     */
    public void scrollToWebElement(WebElement webElement) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();", webElement);
            addScreenshotToTheReport("Successfully scrolled to the WebElement", Status.PASS);
        } catch (Exception e) {
            addScreenshotToTheReport("Error scrolling to WebElement", Status.FAIL);
            System.err.println("Error scrolling to WebElement: " + e.getMessage());
        }
    }

    /**
     * Scrolls the page to the bottom.
     * <p>
     * This method use to scroll the page to the bottom.
     */
    public void scrollPageToBottom() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            addScreenshotToTheReport("Error scrolling to the bottom of the page.", Status.PASS);
        } catch (Exception e) {
            addScreenshotToTheReport("Successfully scrolled to the WebElement", Status.FAIL);
            System.err.println("Error scrolling to the bottom of the page: " + e.getMessage());
        }
    }

    /**
     * Types the specified text into an input field identified by the provided By locator.
     * <p>
     * This method waits for the element to be clickable, clears any existing text, and types the provided inputText into the element.
     *
     * @param byLocator the By locator used to identify the input element
     */
    public void clearTheElement(By byLocator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(byLocator));
            webElement.clear();
            addScreenshotToTheReport("Clear the '" + byLocator + "' input textbox.", Status.PASS);
            waitFor(2000);
        } catch (Exception e) {
            addScreenshotToTheReport("Unable to clear the '" + byLocator + "'  textbox.", Status.FAIL);
            System.err.println("Error clearing the web element " + e.getMessage());
        }
    }

    public void mouseClick(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            WebElement element = driver.findElement(locator);
            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().perform();
            addScreenshotToTheReport("Successfully clicked on the '" + locator + "' element using mouse actions.", Status.PASS);
        } catch (Exception e) {
            addScreenshotToTheReport("Error occurred when clicking on the '" + locator + "' element.", Status.FAIL);
            System.err.println("Error occurred when clicking on the element: " + e.getMessage());
        }
    }
    /**
     * Types the specified text into an input field without clearing the written text .
     * <p>
     * This method waits for the element to be clickable, clears any existing text, and types the provided inputText into the element.
     *
     * @param byLocator the By locator used to identify the input element
     * @param inputText the text to be typed into the input field
     */
    public void typeWithoutClear(By byLocator, String inputText) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(byLocator));
            webElement.sendKeys(inputText);
            addScreenshotToTheReport("Type '" + inputText + "' on textbox.", Status.PASS);
            waitFor(2000);
        } catch (Exception e) {
            addScreenshotToTheReport("Unable to type on '" + inputText + "'  textbox.", Status.FAIL);
            System.err.println("Error sending keys to WebElement: " + e.getMessage());
        }
    }

    /**
     *
     * This method is used to navigate back on browser
     *
     */
    public void browserNavigateBack() {

        driver.navigate().back();
        addScreenshotToTheReport("Navigate back from current browser location", Status.INFO);
    }

    /**
     *
     * This method is used to navigate forward on browser
     *
     */
    public void browserNavigateForward() {
        driver.navigate().forward();
        addScreenshotToTheReport("Navigate forward from current browser location", Status.INFO);
    }


    public void removeLastCharacterFromField(By fieldLocator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement field = wait.until(ExpectedConditions.elementToBeClickable(fieldLocator));
            field.sendKeys(Keys.BACK_SPACE); // Simulates pressing the BACK_SPACE key
        } catch (Exception e) {
            System.err.println("Error removing the last character: " + e.getMessage());
        }
    }

}


