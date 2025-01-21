package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardPage extends BasePage {

    // Update the naming conventions for this XPath according to project requirements.
    // The current names are placeholders provided for reference only.
    private static By img_dashboard = By.xpath("//h2[contains(text(),'Health Board')]");
    private static By txt_chiSearch = By.xpath("//span[@id='magnifying-glass']");
    private static By img_profile = By.xpath("//a[@id='dropdownMenuLink']");
    private static By txt_speciality = By.xpath("//h2[normalize-space()='Neurosurgeon']");
    private static By txt_sessionWorthNeurosurgeon = By.xpath("//div[@class='card card-linked']//a[@href='/Scheduler/Speciality?specialityId=76']//span[@class='sessionsworthStyle']/b");
    private static By btn_operationList = By.xpath("(//button[@class='btn btn-sm btn-primary btn-block'][normalize-space()='Operation Lists'])[4]");
    private static By btn_waitingList = By.xpath("(//a[@class='btn btn-sm btn-primary btn-block'][normalize-space()='Waiting List'])[4]");
    private static By lbl_leftNavigation = By.xpath("//div[@id='76']");


    public DashboardPage(WebDriver driver) {
        super(driver);
    }




}
