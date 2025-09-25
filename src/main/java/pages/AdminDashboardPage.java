package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDashboardPage extends BasePage{

    // Update the naming conventions for this XPath according to project requirements.
    // The current names are placeholders provided for reference only.
    private static By img_dashboard = By.xpath("//h2[contains(text(),'Health Board')]");
    private static By img_profile = By.xpath("//a[@id='dropdownMenuLink']");
    private static By dr_manageSpeciality = By.xpath("//a[text()='Manage Specialities']");
    private static By txt_specialityManagement = By.xpath("//h1[text()='Speciality Management']");
    private static By txt_chiSearch = By.xpath("//span[@id='magnifying-glass']");

    public AdminDashboardPage(WebDriver driver) {
        super(driver);
    }

    public void click(){
        waitFor(3000);

    }



}