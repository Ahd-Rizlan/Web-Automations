package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ExamplePageTwo extends BasePage {

    // Update the naming conventions for this XPath according to project requirements.
    // The current names are placeholders provided for reference only.
    private static By cb_showPendingPatients = By.xpath("//input[@id='showPendingPatient']");
    private static By btn_arrow = By.xpath("//a[@class='btn btn-outline-light btn-down-arrow']");
    private static By btn_operationDate = By.xpath("//a[@class='ui-state-default' and contains(text(),'30')]");
    private static By btn_hospital = By.xpath("//button[text()=\"Asha Negambo's (Asha_N's)\"]");
    private static By btn_save = By.xpath("//input[@value='Save']");
    private static By btn_home = By.xpath("//a[text()='Home']");
    private static By img_dashboard = By.xpath("//h2[contains(text(),'Health Board')]");

    public ExamplePageTwo(WebDriver driver) {
        super(driver);
    }

    public void clickShowPendingPatients() {
        clickOnElement(cb_showPendingPatients);
        waitFor(2000);
    }

    public void makeList() {
        clickOnElement(btn_arrow);
        waitFor(2000);
        clickOnElement(btn_operationDate);
        waitFor(2000);
        clickOnElement(btn_hospital);
        waitFor(2000);
        clickOnElement(btn_save);
        waitFor(5000);
    }

    public void navigateToHomePage() {
        waitFor(2000);
        clickOnElement(btn_home);
        isElementPresentBy(img_dashboard);
    }
}
