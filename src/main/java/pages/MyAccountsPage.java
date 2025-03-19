/*
 *   @author - salman R
 */
package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.CommonUtils;

public class MyAccountsPage extends BasePage {

    CommonUtils cu = new CommonUtils();

    public MyAccountsPage(WebDriver driver) {
        super(driver);
    }

    public enum ElementType {
        button, label, span, div;
    }

    private static final By lblMyAccounts = By.xpath("//span[text()='My Accounts']");

    private static By getElementByTypeAndText(MyAccountsPage.ElementType type, String text) {
        return By.xpath("//" + type.name() + "[contains(normalize-space(text()), " + text + ")]");
    }



}


