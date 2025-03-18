/*
 *   @author - salman R
 */
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.CommonUtils;

public class ManageSchedulePage extends BasePage {

    CommonUtils cu = new CommonUtils();

    public ManageSchedulePage(WebDriver driver) {
        super(driver);
    }

    public enum ElementType {
        button, label, span, div;
    }

    private static final By lblManageSchedule = By.xpath("//div[text()='Schedule Management']");

    private static By getElementByTypeAndText(ManageSchedulePage.ElementType type, String text) {
        return By.xpath("//" + type.name() + "[contains(normalize-space(text()), " + text + ")]");
    }



}


