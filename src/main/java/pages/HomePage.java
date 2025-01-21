package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class HomePage extends BasePage {

    // Update the naming conventions for this XPath according to project requirements.
    // The current names are placeholders provided for reference only.
    private static By title = By.xpath("//h3[text()='Peace of mind is just a few clicks away!']");
    private static By btn_freeTrial = By.xpath("//input[@id='linkadd']");
    private static By btn_BookAFreeDemo = By.xpath("//li/a[text()='Book a Free Demo']");
    private static By lbl_header = By.xpath("//h3[text()='See OrangeHRM in Action']");
    private static By lbl_freeTrial = By.xpath("//h1[text()='Your free trial']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void validateTheTitle() {
        waitForElementPresence(title);
        waitFor(2000);

    }

    public void validateFreeTrialNavigation() {
        clickOnElement(btn_freeTrial);
        Assert.assertEquals(getTextFromElement(lbl_freeTrial), "Your free trial\n" +
                "is almost ready!");
    }

    public void navigateToBookAFreeDemo() {
        clickOnElement(btn_BookAFreeDemo);
    }

}
