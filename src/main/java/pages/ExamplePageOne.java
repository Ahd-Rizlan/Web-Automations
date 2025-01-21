package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ExamplePageOne extends BasePage {

    // Update the naming conventions for this XPath according to project requirements.
    // The current names are placeholders provided for reference only.
    private static By lbl_leftNavigation = By.xpath("//div[@id='76']");
    private static By btn_operationList = By.xpath("//a[@href='/OperationLists/List?surgeonId=130&specialityId=76']");
    private static By btn_waitingList = By.xpath("//a[@href='/Scheduler/Patients/List?surgeonId=130&specialityId=76']");
    public ExamplePageOne(WebDriver driver) {
        super(driver);
    }

    public void navigateToSurgeonWaitingList() {
        waitFor(3000);
        clickOnElement(btn_waitingList);
        waitFor(3000);
    }
}
