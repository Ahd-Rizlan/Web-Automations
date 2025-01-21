package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ExamplePageThree extends BasePage {

    // Update the naming conventions for this XPath according to project requirements.
    // The current names are placeholders provided for reference only.
    private static By specialityTile = By.xpath("//h2[normalize-space()='Neurosurgeon']");
    private static By operationListBtn = By.xpath("(//a[@class='btn btn-sm btn-primary btn-block'][normalize-space()='Operation Lists'])[22]");
    private static By operationListTile = By.xpath("//h2[normalize-space()='Dinura Surgeon 03-05-23']");
    private static By addBtn = By.xpath("//a[normalize-space()='Add Patient(s)']");
    private static By plusBtn = By.xpath("(//a[@class='btn manual-selection plus-link'])[1]/img");
    private static By updateBtn = By.xpath("//a[normalize-space()='Update List']");
    private static By warningText = By.xpath("//b[normalize-space()='Theatre Utilisation Warning']");
    private static By yesBtn = By.xpath("//input[@value='Yes']");
    private static By operationListTitile = By.xpath("//h1[normalize-space()='Ready to transfer']");


    public ExamplePageThree(WebDriver driver) {
        super(driver);
    }

    public void selectaSpeciality() {
        clickOnElement(specialityTile);
        waitFor(3000);

    }

    public void selectaOplist() {
        scrollDownPage();
        clickOnElement(operationListBtn);
        waitFor(3000);
        clickOnElement(operationListTile);
        waitFor(3000);
    }

    public void selectAddoption() {
        clickOnElement(addBtn);
        waitFor(3000);

    }

    public void addPatient() {
        clickOnElement(plusBtn);
        waitFor(3000);
        isElementPresentBy(updateBtn);
        clickOnElement(updateBtn);
        waitFor(3000);
        if (isElementPresentBy(warningText)) {
            clickOnElement(yesBtn);
            waitFor(5000);
        } else {
            System.out.println("Patient added");
        }
        isElementPresentBy(operationListTile);
        waitFor(1000);
    }


}
