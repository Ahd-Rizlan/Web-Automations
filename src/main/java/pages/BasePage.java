package pages;

import com.aventstack.extentreports.Status;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import utils.report.helpers;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Month;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.concurrent.ThreadLocalRandom;

import static utils.Drivers.*;

public abstract class BasePage extends helpers {

    protected WebDriver driver;
    protected static List<String> sharedValues = new ArrayList<>();
    private String baseWindowHandle;
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }


    public enum CurrencyType {
        LOCAL,OTHER
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
            WebDriverWait wait = new WebDriverWait(driver, MODERATE_WAIT);
            WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(byLocator));
            webElement.clear();
            addToReport("Clear the input textbox.", Status.PASS, false);
            webElement.sendKeys(inputText);
            addToReport("Type '" + inputText + "' on textbox.", Status.PASS, false);
            waitFor(EXTREME_SHORT_WAIT);
        } catch (Exception e) {
            addToReport("Unable to type on '" + inputText + "'  textbox.", Status.FAIL);
            System.err.println("Error sending keys to WebElement: " + e.getMessage());
        }
    }
    /**
     * Types the specified text into an input field using JavaScript
     * This method sets the value directly and dispatches input and change events
     *
     * @param byLocator the By locator used to identify the input element
     * @param inputText the text to be typed into the input field
     */
    public void sendKeysToElementUsingJS(By byLocator, String inputText) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, MODERATE_WAIT);
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(byLocator));

            // Use JavaScript to set the value
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                    "arguments[0].value = arguments[1];" +
                            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                    element, inputText
            );

            addToReport("Typed '" + inputText + "' into the textbox using JavaScript.", Status.PASS, false);
            waitFor(EXTREME_SHORT_WAIT);
        } catch (Exception e) {
            addToReport("Unable to type '" + inputText + "' into textbox using JavaScript.", Status.FAIL);
            System.err.println("Error sending keys via JS: " + e.getMessage());
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
            WebDriverWait wait = new WebDriverWait(driver, MODERATE_WAIT);
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(webElement));
            element.clear();
            addToReport("Clear the input textbox.", Status.PASS);
            element.sendKeys(inputText);
            addToReport("Type '" + inputText + "' into the element.", Status.PASS);
            waitFor(EXTREME_SHORT_WAIT);

        } catch (Exception e) {
            addToReport("Unable to type '" + inputText + "' into the element.", Status.FAIL);
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
            WebDriverWait wait = new WebDriverWait(driver, MODERATE_WAIT);
            WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(byLocator));
            webElement.sendKeys(Keys.ENTER);
            addToReport("Press the 'Enter' key.", Status.PASS);
        } catch (Exception e) {
            addToReport("Error sending Enter key to element.", Status.FAIL);
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
            WebDriverWait wait = new WebDriverWait(driver, MODERATE_WAIT);
            WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(byLocator));
            webElement.sendKeys(Keys.TAB);
            addToReport("Press the 'Tab' key.", Status.PASS);
        } catch (Exception e) {
            addToReport("Error sending Tab key to element.", Status.FAIL);
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
            WebDriverWait wait = new WebDriverWait(driver, MODERATE_WAIT);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            locator.click();
            addToReport("Click on the '" + locator + "' web element locator.", Status.PASS);

        } catch (Exception e) {
            addToReport("Error occur when clicking on the '" + locator + "' web element locator.", Status.FAIL);
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
            WebDriverWait wait = new WebDriverWait(driver, MODERATE_WAIT);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            driver.findElement(locator).click();
            addToReport("Successfully clicked on the '" + locator + "' element.", Status.PASS, false);
        } catch (Exception e) {
            addToReport("Error occur when clicking on the '" + locator + "' element.", Status.FAIL);
            System.err.println("Error occur when clicking on the element: " + e.getMessage());
        }
    }

    /**
     * Clicks on an element identified by a locator after waiting for it to become clickable.
     * <p>
     * This method waits for the element identified by the provided By locator to become clickable
     *
     * @param element the web element used to find the element to be clicked
     */

    public void clickOnElement(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, MODERATE_WAIT);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            addToReport("Successfully clicked on the '" + element + "' element.", Status.PASS, false);
        } catch (Exception e) {
            addToReport("Error occur when clicking on the '" + element + "' element.", Status.FAIL);
            System.err.println("Error occur when clicking on the element: " + e.getMessage());
        }
    }

    /**
     * Clicks on an element identified by a locator using js after waiting for it to become clickable.
     * <p>
     * This method waits for the element identified by the provided By locator to become clickable
     *
     * @param locator the locator used to find the element to be clicked
     */
    public void clickOnElementUsingJS(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, MODERATE_WAIT);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement element = driver.findElement(locator);
            js.executeScript("arguments[0].click();", element);

            addToReport("Successfully clicked on the '" + locator + "' element.", Status.PASS, false);
        } catch (Exception e) {
            addToReport("Error occur when clicking on the '" + locator + "' element.", Status.FAIL);
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
//    public String getTextFromElement(By locator) {
//        try {
//            WebDriverWait wait = new WebDriverWait(driver, MODERATE_WAIT);
//            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
//            return driver.findElement(locator).getText();
//        } catch (Exception e) {
//            System.err.println("Error getting text from element: " + e.getMessage());
//            return null;
//        }
//    }


    public String getTextFromElement(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, MODERATE_WAIT);
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return driver.findElement(locator).getText();
        } catch (Exception e) {
            System.err.println("Error getting text from element: " + e.getMessage());
            return null;
        }
    }

    /**
     * Helper to safely get text from an element without throwing NullPointerException.
     * @param locator The element locator
     * @return The trimmed text, or "" if null/empty.
     */
    private String getSafeText(By locator) {
        String text = getTextFromElement(locator);
        if (text == null) {
            return "";
        }
        return text.trim();
    }


    /**
     * Pauses the execution for the specified amount of time.
     * <p>
     * This method uses to pause the execution for the given number of seconds.
     *
     * @param seconds the number of seconds to wait
     */
    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000L); // Convert seconds to milliseconds
        } catch (InterruptedException e) {
            System.err.println("Interrupted during wait: " + e.getMessage());
            Thread.currentThread().interrupt(); // Restore interrupt status
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
            WebDriverWait wait = new WebDriverWait(driver, MODERATE_WAIT);
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            System.err.println("Error waiting for element presence: " + e.getMessage());
            return false;
        }
    }

    /**
     * Waits until an element is present in the DOM.
     * <p>
     * This method waits for the element identified by the provided locator to be present in the DOM.
     *
     * @param locator  the locator used to find the element
     * @param waitTime dynamic wait time based on element
     * @return true if the element is present, false if an error occurs or the element is not found
     * @true -If the element is found
     * @false -If the element is not found
     */
    public boolean waitForElementPresence(By locator, int waitTime) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
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
            WebDriverWait wait = new WebDriverWait(driver, SHORT_WAIT);
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            System.err.println("Element not present: " + e.getMessage());
            return false;
        }
    }

    /**
     * Checks if an element is present in the DOM, identified by the provided locator.
     * <p>
     * This method waits for the element to be present in the DOM for up to 20 seconds.
     *
     * @param locator the locator used to find the element
     * @param waitInSeconds  wait time in seconds
     * @return true if the element is present, false if the element is not found or an error occurs
     * @true -If the element is found
     * @false -If the element is not found
     */
    public boolean isElementPresentBy(By locator,int waitInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitInSeconds);
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            System.err.println("Element not present: " + e.getMessage());
            return false;
        }
    }

    /**
     * Checks if an elements are present in the DOM, identified by the provided locator.
     * <p>
     * This method waits for the element to be present in the DOM for up to 20 seconds.
     *
     * @param locator the locator used to find the element
     * @return size if the elements are present, 0 if the element is not found or an error occurs
     */
    public int isElementsPresentBy(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, LONG_WAIT);
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            List<WebElement> records = driver.findElements(locator);
            if (!records.isEmpty()) {
                return records.size();
            } else {
                return 0;
            }
        } catch (Exception e) {
            System.err.println("Element not present: " + e.getMessage());
            return 0;
        }
    }
    /**
     * Checks if an elements are present in the DOM, identified by the provided locator.
     * <p>
     * This method waits for the element to be present in the DOM for up to 20 seconds.
     *
     * @param locator the locator used to find the element
     * @param waitTime wait time
     * @return size if the elements are present, 0 if the element is not found or an error occurs
     */
    public int isElementsPresentBy(By locator,long waitTime) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            List<WebElement> records = driver.findElements(locator);
            if (!records.isEmpty()) {
                return records.size();
            } else {
                return 0;
            }
        } catch (Exception e) {
            System.err.println("Element not present: " + e.getMessage());
            return 0;
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
            WebDriverWait wait = new WebDriverWait(driver, LONG_WAIT);

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
     * Checks if a specific attribute is present on a WebElement.
     * <p>
     * This method retrieves the value of the specified attribute from the WebElement.
     *
     * @param webElement the WebElement to check for the attribute
     * @param attribute  the name of the attribute to retrive value
     * @return value the attribute contains
     */
    public String getAttributetext(WebElement webElement, String attribute) {
        try {
            return webElement.getAttribute(attribute);
        } catch (Exception e) {
            System.err.println("Error checking attribute presence: " + e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * Retrieves the value of a specified attribute from an element identified by a By locator.
     *
     * @param byLocator the By locator used to identify the element
     * @param attribute the name of the attribute whose value is to be retrieved (e.g., "value", "class")
     * @return The value of the attribute as a String, or null if an error occurs.
     */
    public String getAttributeFromElement(By byLocator, String attribute) {
        try {
            WebElement element = driver.findElement(byLocator);
            return element.getAttribute(attribute);
        } catch (Exception e) {
            System.err.println("Error getting attribute '" + attribute + "' from element: " + e.getMessage());
            return null;
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
            WebDriverWait wait = new WebDriverWait(driver, LONG_WAIT);
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
            WebDriverWait wait = new WebDriverWait(driver, LONG_WAIT);
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
            WebDriverWait wait = new WebDriverWait(driver, LONG_WAIT);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div/img[@alt=\"loading...\"]")));
            addToReport("Loading indicator is not visible", Status.PASS);

        } catch (Exception e) {
            addToReport("Loading indicator is still visible", Status.FAIL);
            System.err.println("Loading indicator is still visible: " + e.getMessage());
        }
    }

    /**
     * Waits for the element to become invisible.
     * <p>
     * This method waits for the loading indicator, identified by a specific XPath, to become invisible
     *
     * @param Locator the Locator to become invisible
     * @param Timeout the wait time in seconds
     */
    public void waitForElementToBeInvisible(By Locator, long Timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Timeout);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(Locator));
            addToReport("Element " + Locator + " is not visible", Status.PASS, false);

        } catch (Exception e) {
            addToReport("Element " + Locator + " is still visible after " + Timeout + " seconds", Status.FAIL);
            System.err.println("Loading indicator is still visible: " + e.getMessage());
        }
    }

    /**
     * Waits for the element to become invisible.
     * <p>
     * This method waits for the loading locator, identified by a specific XPath, to become clickable
     *
     * @param Locator the Locator to become invisible
     * @param Timeout the wait time in seconds
     */
    public void waitForElementToBeClickable(By Locator, long Timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Timeout);
            wait.until(ExpectedConditions.elementToBeClickable(Locator));
            addToReport("Element " + Locator + " is clickable", Status.PASS, false);

        } catch (Exception e) {
            addToReport("Element " + Locator + " is not clickable after " + Timeout + " seconds", Status.FAIL);
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
            WebDriverWait wait = new WebDriverWait(driver, LONG_WAIT);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='css-fraxkc']")));
            addToReport("Loading dropdown is not visible:", Status.PASS);

        } catch (Exception e) {
            addToReport("Loading dropdown is still visible:", Status.FAIL);
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
            addToReport("Successfully scrolled down the page", Status.PASS);

        } catch (Exception e) {
            addToReport("Error scrolling down the page", Status.FAIL);
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
            addToReport("Successfully scrolled to the WebElement", Status.PASS);
        } catch (Exception e) {
            addToReport("Error scrolling to WebElement", Status.FAIL);
            System.err.println("Error scrolling to WebElement: " + e.getMessage());
        }
    }

    public void scrollToWebElement(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            addToReport("Successfully scrolled to the WebElement", Status.PASS);
        } catch (Exception e) {
            addToReport("Error scrolling to WebElement", Status.FAIL);
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
            addToReport("Error scrolling to the bottom of the page.", Status.PASS);
        } catch (Exception e) {
            addToReport("Successfully scrolled to the WebElement", Status.FAIL);
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
            WebDriverWait wait = new WebDriverWait(driver, MODERATE_WAIT);
            WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(byLocator));
            webElement.clear();
            addToReport("Clear the '" + byLocator + "' input textbox.", Status.PASS);
            waitFor(EXTREME_SHORT_WAIT);
        } catch (Exception e) {
            addToReport("Unable to clear the '" + byLocator + "'  textbox.", Status.FAIL);
            System.err.println("Error clearing the web element " + e.getMessage());
        }
    }

    public void mouseClick(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, MODERATE_WAIT);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            WebElement element = driver.findElement(locator);
            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().perform();
            addToReport("Successfully clicked on the '" + locator + "' element using mouse actions.", Status.PASS, false);
        } catch (Exception e) {
            addToReport("Error occurred when clicking on the '" + locator + "' element.", Status.FAIL);
            System.err.println("Error occurred when clicking on the element: " + e.getMessage());
        }
    }

    /**
     * Perform mouse hover action
     * @param locator the By locator used to identify the input element
     */
    public void mouseHover(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, MODERATE_WAIT);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            WebElement element = driver.findElement(locator);
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
            addToReport("Successfully moved to '" + locator + "' element using mouse actions.", Status.PASS, false);
        } catch (Exception e) {
            addToReport("Error occurred when hovering on the '" + locator + "' element.", Status.FAIL);
            System.err.println("Error occurred when hovering on the element: " + e.getMessage());
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
            WebDriverWait wait = new WebDriverWait(driver, MODERATE_WAIT);
            WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(byLocator));
            webElement.sendKeys(inputText);
            addToReport("Type '" + inputText + "' on textbox.", Status.PASS);
            waitFor(EXTREME_SHORT_WAIT);
        } catch (Exception e) {
            addToReport("Unable to type on '" + inputText + "'  textbox.", Status.FAIL);
            System.err.println("Error sending keys to WebElement: " + e.getMessage());
        }
    }

    /**
     * This method is used to navigate back on browser
     */
    public void browserNavigateBack() {

        driver.navigate().back();
        addToReport("Navigate back from current browser location", Status.INFO);
    }

    /**
     * This method is used to navigate forward on browser
     */
    public void browserNavigateForward() {
        driver.navigate().forward();
        addToReport("Navigate forward from current browser location", Status.INFO);
    }


    public void removeLastCharacterFromField(By fieldLocator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, MODERATE_WAIT);
            WebElement field = wait.until(ExpectedConditions.elementToBeClickable(fieldLocator));
            field.sendKeys(Keys.BACK_SPACE); // Simulates pressing the BACK_SPACE key
        } catch (Exception e) {
            System.err.println("Error removing the last character: " + e.getMessage());
        }
    }

    /**
     * Compares two images where one is extracted based on by locator and the other is provided by the path
     * <p>
     * This method also takes in threshold value as argument
     *
     * @param byLocator           the By locator used to identify the input element
     * @param pathOfExpectedImage the path of the expected image
     * @param threshold           threshold value to compare provided in pixels
     * @return true if the images are same or differences between the images is within provided threshold, false if the images are different
     */
    public boolean compareImage(By byLocator, String pathOfExpectedImage, int threshold) throws IOException {

        System.out.println("Start of image verification");

        //Obtain the element
        WebDriverWait wait = new WebDriverWait(driver, MODERATE_WAIT);
        WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(byLocator));
        //Based on web element retrieved the image
        Screenshot screenshot = new AShot()
                .coordsProvider(new WebDriverCoordsProvider())
                .takeScreenshot(driver, webElement);

        //Read images
        BufferedImage actualImage = removeWhiteBackground(screenshot.getImage());
        BufferedImage expectedImage = ImageIO.read(new File(pathOfExpectedImage));

        //Find differences between images
        ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
        int difSize = diff.getDiffSize();
        System.out.println("End of image verification");
        if (difSize < threshold) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Compares two images where one is extracted based on by locator and the other is provided by the path
     *
     * @param image Buffered image
     * @return image after removing the white background
     */
    public static BufferedImage removeWhiteBackground(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y), true);

                // If pixel is near white, make it fully transparent
                if (color.getRed() > 230 && color.getGreen() > 230 && color.getBlue() > 230) {
                    image.setRGB(x, y, new Color(255, 255, 255, 0).getRGB()); // Transparent
                }
            }
        }
        return image;
    }

    /**
     * This method is used to get the current URL of the page
     */
    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    /**
     * Method to extract text from PDF using Apache PDFBox
     *
     * @param filePath file path
     * @return extracted text from the pdf
     */
    public static String extractTextFromPDF(String filePath) {
        try (PDDocument document = PDDocument.load(new File(filePath))) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            return pdfStripper.getText(document).trim();
        } catch (IOException e) {
            System.err.println("Error reading pdf : " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method to get the latest downloaded pdf file
     *
     * @param dirPath file path
     * @return latest modified file
     */
    public static File getLatestDownloadedFile(String dirPath) {
//        waitForDownload(dirPath,20);
        //Adding sleep due to network latency
        try {
            waitFor(MODERATE_WAIT);
        } catch (Exception e) {
            System.err.println("Error on wait : " + e.getMessage());
        }
        File dir = new File(dirPath);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".pdf"));
        //Adding sleep due to network latency
        try {
            waitFor(MODERATE_WAIT);
        } catch (Exception e) {
            System.err.println("Error on wait : " + e.getMessage());
        }
        if (files != null && files.length > 0) {
            return Arrays.stream(files)
                    .max((f1, f2) -> Long.compare(f1.lastModified(), f2.lastModified()))
                    .orElse(null);
        }
        return null;
    }

    /**
     * Method to wait for .crdownload to disappear
     *
     * @param downloadDir    file path
     * @param timeoutSeconds wait time
     * @return latest modified file
     */
    public static void waitForDownload(String downloadDir, int timeoutSeconds) {
        long endTime = System.currentTimeMillis() + (timeoutSeconds * 1000);
        File dir = new File(downloadDir);

        //Wait for crdownload to disappear
        while (System.currentTimeMillis() < endTime) {
            File[] crdownloadFiles = dir.listFiles((d, name) -> name.endsWith(".crdownload"));
            if (crdownloadFiles == null || crdownloadFiles.length == 0) {
                return; // Download complete
            }
            waitFor(EXTREME_SHORT_WAIT);
        }
    }

    /**
     * Method to select from dropdown
     *
     * @param locator the By locator used to identify the input element
     * @param value   value to be selected from dropdown
     * @param type    type either (index,value,visible text)
     */
    public void selectFromDropdown(By locator, String value, String type) {
        WebElement dropdownElement = driver.findElement(locator);
        Select select = new Select(dropdownElement);

        switch (type.toLowerCase()) {
            case "index":
                select.selectByIndex(Integer.parseInt(value));
                break;
            case "value":
                select.selectByValue(value);
                break;
            case "visibletext":
                select.selectByVisibleText(value);
                break;
            default:
                throw new IllegalArgumentException("Invalid dropdown selection type: " + type);
        }
    }

    /**
     * Method to get values from dropdown
     *
     * @param locator the By locator used to identify the input element
     * @return all available option under dropdown
     */
    public  List<String> getDropdownValues(By locator) {
        ArrayList<String> values = new ArrayList<>();
        try {
            WebElement dropdownElement = driver.findElement(locator);
            Select dropdown = new Select(dropdownElement);

            // Loop through all options and get their "value" attributes
            for (WebElement option : dropdown.getOptions()) {
                // Get the value attribute
                values.add(option.getAttribute("value"));
            }
        } catch (NoSuchElementException e) {
            System.out.println("Dropdown not found: " + e.getMessage());
        }

        return values;
    }

    /**
     * Method to add values to be used across multiple classes
     *
     * @param index index of the arraylist
     * @param value value of the arraylist
     */
    public void addValue(int index,String value) {
        sharedValues.add(index,value);
    }

    /**
     * Method to get values to be used across multiple classes
     */
    public List<String> getValues() {
        return sharedValues;
    }

    /**
     * Retrieves the text content of a dropdown element identified by the provided locator.
     * <p>
     * This method waits for the element to be present in the DOM, then retrieves and returns its text content.
     *
     * @param locator the locator used to find the element
     * @param action returns dropdown options based on action key words such as FIRST_SELECTED,ALL_OPTIONS,ALL_SELECTED_OPTIONS
     * @return the text content of the selected element, or null if the element is not found or an error occurs
     */
    public List<String> getSelectedOptionText(By locator,String action) {
        try {
            //start of temp work around as data loading issue in dropdown
//            clickOnElement(locator);
//            waitFor(EXTREME_SHORT_WAIT);
//            clickOnElement(locator);
            //End of temp work around
            waitFor(EXTREME_SHORT_WAIT);
            WebElement dropdownElement = driver.findElement(locator); // Replace with actual dropdown ID
            Select dropdown = new Select(dropdownElement);
            switch (action) {
                case "FIRST_SELECTED":
                    return List.of(dropdown.getFirstSelectedOption().getText());

                case "FIRST_SELECTED_VALUE":
                    return List.of(dropdown.getFirstSelectedOption().getAttribute("value"));

                case "ALL_OPTIONS":
                    return dropdown.getOptions().stream()
                            .map(WebElement::getText)
                            .collect(Collectors.toList());

                case "ALL_OPTIONS_VALUE":
                    return dropdown.getOptions().stream()
                            .map(option -> option.getAttribute("value"))
                            .collect(Collectors.toList());

                case "ALL_SELECTED_OPTIONS":
                    return dropdown.getAllSelectedOptions().stream()
                            .map(WebElement::getText)
                            .collect(Collectors.toList());

                case "ALL_SELECTED_OPTIONS_VALUE":
                    return dropdown.getAllSelectedOptions().stream()
                            .map(option -> option.getAttribute("value"))
                            .collect(Collectors.toList());

                default:
                    throw new IllegalArgumentException("Invalid dropdown action");
            }
        } catch (Exception e) {
            System.err.println("Error getting text from dropdownelement: " + e.getMessage());
            return null;
        }

    }

    /**
     * Get the highest dropdown value lower than a given threshold for a specific currency type
     */
    public String getValueBelowThreshold(By dropdownLocator, CurrencyType currencyType, double thresholdAmount) {
        // Get all option texts
        List<String> allOptions = getSelectedOptionText(dropdownLocator, "ALL_OPTIONS");
        // Get all option values
        List<String> allValues = getSelectedOptionText(dropdownLocator, "ALL_OPTIONS_VALUE");

        if (allOptions == null || allOptions.isEmpty() || allValues == null || allValues.isEmpty()) {
            addToReport("Dropdown has no options", Status.FAIL,true);
            throw new RuntimeException("Dropdown has no options");
        }

        double maxAmountBelowThreshold = -1;
        String valueOfMaxBelowThreshold = null;

        for (int i = 0; i < allOptions.size(); i++) {
            String optionText = allOptions.get(i);
            String optionValue = allValues.get(i);

            // Filter by currency type
            if (currencyType.name().equalsIgnoreCase("local") && !optionText.contains("LKR")) continue;
            if (currencyType.name().equalsIgnoreCase("other") && optionText.contains("LKR")) continue;

            // Extract numeric amount after "AVL."
            String[] parts = optionText.split("AVL\\.");
            if (parts.length < 2) continue;
            String amountPart = parts[1].replaceAll("[^0-9.]", ""); // keep digits and dot
            double amount = Double.parseDouble(amountPart);

            // Check if amount is below threshold and higher than current max below threshold
            if (amount < thresholdAmount && amount > maxAmountBelowThreshold) {
                maxAmountBelowThreshold = amount;
                valueOfMaxBelowThreshold = optionValue;
            }
        }

        if (valueOfMaxBelowThreshold == null) {
            addToReport("No accounts found below threshold for currency type: " + currencyType, Status.INFO);
        }
        addToReport(valueOfMaxBelowThreshold+" is found below threshold for currency type: " + currencyType, Status.INFO);

        return valueOfMaxBelowThreshold;
    }



    /**
     * Get the Max amount of the Dropdown Element
     */
    public String getValueOfHighestVisibleAmount(By dropdownLocator, CurrencyType currencyType) {
        // Get all option texts
        List<String> allOptions = getSelectedOptionText(dropdownLocator, "ALL_OPTIONS");
        // Get all option values
        List<String> allValues = getSelectedOptionText(dropdownLocator, "ALL_OPTIONS_VALUE");

        if (allOptions == null || allOptions.isEmpty() || allValues == null || allValues.isEmpty()) {
            addToReport("Dropdown has no options", Status.FAIL,true);
            throw new RuntimeException("Dropdown has no options");
        }

        double maxAmount = -1;
        String valueOfMax = null;

        for (int i = 0; i < allOptions.size(); i++) {
            String optionText = allOptions.get(i);
            String optionValue = allValues.get(i);

            // Filter by currency type
            if(currencyType.name().equalsIgnoreCase("local") && !optionText.contains("LKR")) continue;
            if(currencyType.name().equalsIgnoreCase("other") && optionText.contains("LKR")) continue;

            // Extract numeric amount after "AVL."
            String[] parts = optionText.split("AVL\\.");
            if(parts.length < 2) continue;
            String amountPart = parts[1].replaceAll("[^0-9.]", ""); // keep digits and dot
            double amount = Double.parseDouble(amountPart);

            // Check if this is the new maximum
            if(amount > maxAmount) {
                maxAmount = amount;
                valueOfMax = optionValue;
            }
        }

        if(valueOfMax == null) {
            addToReport("No accounts found for currency type: " + currencyType, Status.INFO);
            throw new RuntimeException("No accounts found for currency type: " + currencyType);
        }
        addToReport( currencyType.name() + " account with highest available amount: " + maxAmount+"is Selected - "+valueOfMax , Status.INFO);
        return valueOfMax;
    }

    /**
     * Get attribute value or text of a WebElement.
     *
     * @param locator - By locator of the element
     * @param attribute - Attribute name (pass "text" to get element text)
     * @return String value of the attribute or text
     */
    public String getAttributeOrText(By locator, String attribute) {
        try {
            WebElement element = driver.findElement(locator);

            if ("text".equalsIgnoreCase(attribute)) {
                // Get text if "text" is passed
                return element.getText();
            } else {
                // Get specific attribute value
                return element.getAttribute(attribute);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving attribute/text: " + e.getMessage());
            return null;  // Return null or handle accordingly
        }
    }

    /**
     * Scrolls the page to the top.
     * <p>
     * This method use to scroll the page to the top.
     */
    public void scrollPageToTop() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo({ top: 0, behavior: 'smooth' });");
            addToReport("Error scrolling to the top of the page.", Status.PASS);
        } catch (Exception e) {
            addToReport("Successfully scrolled to the top of the page", Status.FAIL);
            System.err.println("Error scrolling to the top of the page: " + e.getMessage());
        }
    }

    /**
     * Waits until the page has fully loaded (document.readyState is 'complete').
     */
    public void waitForPageLoadCompleteJS() {
        new WebDriverWait(driver, LONG_WAIT).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    /**
     * Waits until the given number of elements are present for the specified locator.
     *
     * @param locator           the element locator
     * @param number            expected number of elements
     * @param timeoutInSeconds  max time to wait in seconds
     * @return true if expected number found, false otherwise
     */
    public boolean isExpectedNumberOfElementsPresent(By locator, int number, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            List<WebElement> elements = wait.until(ExpectedConditions.numberOfElementsToBe(locator, number));
            return elements.size() == number;
        } catch (Exception e) {
            System.err.println("Expected number of elements not found: " + e.getMessage());
            return false;
        }
    }

    /**
     * Uses FluentWait to locate an element with custom timeout and polling interval.
     *
     * @param locator   the element locator
     * @param timeout   max timeout in seconds
     * @param polling   polling interval in milliseconds
     * @return the WebElement found, or throws exception if not found
     */
    public WebElement fluentWait(By locator, int timeout, int polling) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(polling))
                .ignoring(NoSuchElementException.class);
        return wait.until(driver -> driver.findElement(locator));
    }

    /**
     * Waits until the specified attribute contains the expected value.
     *
     * @param locator           the element locator
     * @param attribute         the attribute to check (e.g., "class", "value")
     * @param value             the value to wait for
     * @param timeoutInSeconds  maximum wait time in seconds
     * @return true if attribute contains the value, false otherwise
     */
    public boolean waitForAttributeValue(By locator, String attribute, String value, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            return wait.until(ExpectedConditions.attributeContains(locator, attribute, value));
        } catch (Exception e) {
            System.err.println("Attribute value not found: " + e.getMessage());
            return false;
        }
    }

    /**
     * Waits until the given text is present in the specified element.
     *
     * @param locator           the element locator
     * @param text              the expected text to be present
     * @param timeoutInSeconds  time to wait before throwing TimeoutException
     * @return true if text is found within the timeout, false otherwise
     */
    public boolean waitForTextToBePresent(By locator, String text, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
        } catch (Exception e) {
            System.err.println("Text not found in element: " + e.getMessage());
            return false;
        }
    }

    /**
     * Sends a single special key (like BACK_SPACE, ENTER, etc.) to an element located by the given locator.
     *
     * @param locator By locator to find the element
     * @param key     Special key to send (e.g., Keys.BACK_SPACE)
     */
    public void sendKeysToElement(By locator, Keys key) {
        WebElement element = driver.findElement(locator);
        element.sendKeys(key);
    }

    /**
     * Sends a special key multiple times to the element (useful for clearing character-by-character).
     *
     * @param locator By locator to find the element
     * @param key     Special key to send (e.g., Keys.BACK_SPACE)
     * @param count   Number of times the key should be sent
     */
    public void sendKeysToElement(By locator, Keys key, int count) {
        WebElement element = driver.findElement(locator);
        for (int i = 0; i < count; i++) {
            element.sendKeys(key);
        }
    }

    /**
     * Get the latest element based on date Eg : used at instances on message list to get the latest
     * @param datePrefix    - date prefix
     * @return
     */
    public WebElement getLatestElementByDate(By containerLocator, By dateLocatorInside, String datePrefix, By clickableLocator) {
        List<WebElement> elements = driver.findElements(containerLocator);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<WebElement, Date> elementDateMap = new HashMap<>();

        for (WebElement element : elements) {
            try {
                // Find all inner elements that might contain date text
                List<WebElement> dateElements = element.findElements(dateLocatorInside);
                for (WebElement dateEl : dateElements) {
                    String text = dateEl.getText().trim();
                    // Match by prefix (e.g., "Last modified on")
                    if (text.startsWith(datePrefix)) {
                        String dateText = text.replace(datePrefix, "").trim();
                        Date parsedDate = sdf.parse(dateText);
                        elementDateMap.put(element, parsedDate);
                        break; // Only consider the first matching date
                    }
                }
            } catch (Exception e) {
                addToReport("Skipping element due to date parse error: " + e.getMessage(), Status.INFO);
            }
        }

        if (elementDateMap.isEmpty()) {
            throw new RuntimeException("No elements with valid dates found.");
        }

        // Find the element with the latest (most recent) date
        WebElement latestElement = Collections.max(
                elementDateMap.entrySet(),
                Map.Entry.comparingByValue()
        ).getKey();

        // Get the clickable part inside the element
        WebElement clickableElement = latestElement.findElement(clickableLocator);

        // Scroll and click
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", clickableElement);

        return latestElement;
    }


    /**
     * Retrieves the absolute file path of a file located in the test resources directory (src/test/resources)
     *
     * @param fileName Name of the file (including extension) located under src/test/resources
     * @return The absolute file path as a String, which can be used for file upload operations
     * @throws IllegalArgumentException if the file is not found in the resources folder
     */
    public String getFileFromResources(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            addToReport("File not found ", Status.FAIL);
            throw new IllegalArgumentException("File not found in test resources folder: " + fileName);
        } else {
            return new File(resource.getFile()).getAbsolutePath();
        }
    }

    /**
     * Close all the windows except parent windows
     * */
    public void closeAllExceptParentWindow() {
        Set<String> allWindows = driver.getWindowHandles();

        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(baseWindowHandle)) {
                driver.switchTo().window(windowHandle);
                driver.close();
            }
        }

        driver.switchTo().window(baseWindowHandle);
    }

    /**
     * capture the current window handle to identify parent window
     */
    public void captureBaseWindowHandle() {
        baseWindowHandle = driver.getWindowHandle();
    }


    /**
     * Keyboard commands to simulate paste
     * @param locator locator where content should be pasted
     */
    public void pasteIntoElement(By locator) {
        clickOnElement(locator);
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            waitFor(EXTREME_SHORT_WAIT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the cutoff date that is X months before the current date
     *
     * @param monthsBack number of months to go back
     * @return Date object representing the cutoff date
     */
    public static Date getCutoffDate(int monthsBack) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -monthsBack);
        return cal.getTime();
    }

    /**
     * Validates whether the current browser URL contains a specific expected substring.
     *
     * @param expectedUrlPart The expected part of the URL
     */
    public void validateURL(String expectedUrlPart) {
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains(expectedUrlPart)) {
            // Log success if current URL contains the expected part
            addToReport("URL contains expected part: " + expectedUrlPart, Status.PASS, true);
        } else {
            // Log failure with the actual URL if it does not contain the expected part
            addToReport("URL does not contain expected part. Actual URL: " + currentUrl, Status.FAIL, true);
        }
    }

    /**
     * Closes the current browser window.
     * This will close only the active tab or window, not all open tabs.
     */
    public void closeBrowser() {
        driver.close(); // Closes the current browser tab
    }

    /**
     * This method will hover on an element
     *
     * @param locator - location
     */
    public void hoverOverElement(By locator) {
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    /**
     * Opens a new browser tab, navigates to a URL based on tab index, and validates mail list availability
     *
     * @param tabIndex A string representing the tab identifier or URL target
     * @param openNewWindow  should open a new window true / false
     */
    public void navigateToTab(int tabIndex,boolean openNewWindow) {

        if (openNewWindow) {
            // Open a new tab using JavaScript
            ((JavascriptExecutor) driver).executeScript("window.open();");
        }
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

    /**
     * Clicks at a specific screen coordinate using actions
     * @param {number} x - The horizontal coordinate (in pixels) from the top-left of the browser viewport
     * @param {number} y - The vertical coordinate (in pixels) from the top-left of the browser viewport
     */
    public void clickAtCoordinates(int x, int y) {
        Actions actions = new Actions(driver);
        actions.moveByOffset(x, y).click().perform();
        actions.moveByOffset(-x, -y).perform(); // Resetting cursor position
    }


    /**
     * Retrieves and returns the trimmed visible text values from all elements matching the given locator.
     *
     * @param locator the By locator identifying the target elements
     * @return a list of trimmed string values of the matching elements
     */
    public List<String> getValues(By locator) {
        List<String> values = new ArrayList<>();
        try {
            List<WebElement> elements = driver.findElements(locator);
            for (WebElement element : elements) {
                values.add(element.getText().trim());
            }
        } catch (Exception e) {
            addToReport("Failed to retrieve values for locator " + locator + ": " + e.getMessage(), Status.FAIL);
        }
        return values;
    }


    /**
     * Clicks on a specific element at the given index from a list of elements matching the locator.
     *
     * @param locator the By locator that identifies a list of elements
     * @param index the zero-based index of the element to click
     */
    public void clickOnElement(By locator, int index) {
        try {
            List<WebElement> elements = driver.findElements(locator);
            if (index >= 0 && index < elements.size()) {
                elements.get(index).click();
            } else {
                addToReport("Index out of bounds: " + index + " for locator: " + locator, Status.FAIL);
            }
        } catch (Exception e) {
            addToReport("Failed to click on element at index " + index + ": " + e.getMessage(), Status.FAIL);
        }
    }

    /**
     * Obtain month number based on name
     * @param monthName Name of the month
     * @return
     */
    public static int getMonthNumber(String monthName) {
        return Month.valueOf(monthName.toUpperCase()).getValue();
    }

    /**
     * Waits for the number of open browser windows/tabs to reach the expected count within the given timeout.
     *
     * @param expectedCount  the expected total number of open windows/tabs
     * @param timeoutSeconds the maximum time to wait, in seconds
     */
    public void waitForNewWindowToOpen(int expectedCount, int timeoutSeconds) {
        new WebDriverWait(getDriver(), timeoutSeconds)
                .until(driver -> driver.getWindowHandles().size() == expectedCount);
    }



    public void clearAndSendKeysToElement(By by, String text) {
        try {
            WebElement element = driver.findElement(by);
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            throw new RuntimeException("Failed to clear and send keys to element: " + by, e);
        }
    }
    /**
     * Generates a random integer between 0 and maxNumber (inclusive).
     *
     * @param maxNumber the inclusive upper bound
     * @return a random integer between 0 and maxNumber
     * @throws IllegalArgumentException if maxNumber < 0
     */
    public static int generateRandomNumber(int maxNumber) {
        if (maxNumber < 0) {
            throw new IllegalArgumentException("maxNumber must be >= 0");
        }
        // nextInt(origin, bound) → bound is exclusive, so add +1 to include maxNumber
        return ThreadLocalRandom.current().nextInt(0, maxNumber + 1);
    }
    public double generateRandomAmount(double min, double max) {
        if (min >= max) {
            throw new IllegalArgumentException("Max must be greater than Min");
        }

        // 1. Generate random value
        double randomValue = min + (max - min) * new java.util.Random().nextDouble();

        // 2. Format to 2 decimal places (xxx.xx)
        // We convert to BigDecimal, round it, and convert back to double
        BigDecimal bd = BigDecimal.valueOf(randomValue);
        bd = bd.setScale(2, RoundingMode.HALF_UP);

        return bd.doubleValue();
    }

    public String generateRemarkText(int index) {
        // 1. Create a compact timestamp (YearMonthDayHourMinute)
        // Format: yyMMddHHmm (e.g., 2601191230 for 2026-01-19 12:30) -> 10 characters
        String timeStamp = new SimpleDateFormat("yyMMddHHmm").format(new Date());

        // 2. Construct the string with the Index
        // Example Result: "2601191230 Ref:1"
        String text = timeStamp + " Ref:" + index;

        // 3. Safety Check: Ensure it never exceeds 20 characters
        if (text.length() > 20) {
            return text.substring(0, 20);
        }

        return text;
    }

    public String generateRemarkText(String Text) {
        // 1. Create a compact timestamp (YearMonthDayHourMinute)
        // Format: yyMMddHHmm (e.g., 2601191230 for 2026-01-19 12:30) -> 10 characters
        String timeStamp = new SimpleDateFormat("MMddHHmm").format(new Date());

        // 2. Construct the string with the Index
        // Example Result: "2601191230 Ref:1"
        String text = timeStamp + " Ref:" + Text;

        // 3. Safety Check: Ensure it never exceeds 20 characters
        if (text.length() > 20) {
            return text.substring(0, 20);
        }

        return text;
    }


}
